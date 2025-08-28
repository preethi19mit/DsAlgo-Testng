package nn.dsalgo.tests;

import nn.dsalgo.dataprovider.TestdataProvider;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.hooks.TestNGHooks;
import nn.dsalgo.listeners.TestListeners;
import nn.dsalgo.pagemanager.PageManager;
import nn.dsalgo.pages.TreePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListeners.class})
public class TreeTest extends TestNGHooks {
    private PageManager pageManager;
    private TreePage treePage;

    @BeforeMethod(alwaysRun = true)
    public void setupPagesTree() {
        pageManager = new PageManager();
        treePage = new TreePage(DriverFactory.getDriver());
        log.info("Entered the Tree testcases");
    }

    @Test
    public void treePageLanding()
    {
        pageManager.treePageLanding();
        Assert.assertEquals(treePage.getTitleforTree(),"Tree");
    }
    @Test(dataProvider = "inputCodeForTopicsInTree",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"treePageLanding"})
    public void generateOutputForInputInTree(String topic, String input)
    {
        pageManager.treePageLanding();
        Assert.assertEquals(treePage.getTitleforTree(),"Tree");
        treePage.clickTopicLink(topic);
        Assert.assertTrue(treePage.validateTitleforTopics(topic));
        treePage.clickTryHereBtn(topic);
        log.info("Checking the try editor code block is available");
        Assert.assertTrue(treePage.tryEditorVisible(),"Try Editor Code block not available");
        log.info("Checking the Run button is available");
        Assert.assertTrue(treePage.runBtnVisible(),"Run button not visible");
        String code = treePage.getPythonCodeDataDriven(input);
        treePage.enterPythonCode(code);
        log.info("The code entered in the editor : "+code);
        treePage.clickRunBtn();
        log.info ("Run button clicked");
        String actualOutput = treePage.processInputAndReturnStatus();
        if (actualOutput != null) {
            String expectedOutput = treePage.getOutputDataDriven(input);
            log.info("Expected Output: " + expectedOutput);
            Assert.assertEquals(actualOutput, expectedOutput);
            log.info("✅ Actual Output matched with the Expected Output");
        } else {
            log.warn("Test failed due to invalid input, alert was handled.");
        }


    }
}
