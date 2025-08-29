package nn.dsalgo.tests;

import nn.dsalgo.dataprovider.TestdataProvider;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.hooks.TestNGHooks;
import nn.dsalgo.listeners.TestListeners;
import nn.dsalgo.helperclass.HelperClass;
import nn.dsalgo.pages.TreePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListeners.class})
public class TreeTest extends TestNGHooks {
    private HelperClass helperClass;
    private TreePage treePage;

    @BeforeMethod(alwaysRun = true)
    public void setupPagesTree() {
        helperClass = new HelperClass();
        treePage = new TreePage(DriverFactory.getDriver());
        log.info("Entered the Tree testcases");
    }

    @Test
    public void treePageLanding()
    {
        helperClass.treePageLanding();
        Assert.assertEquals(treePage.getTitleforTree(),"Tree");
    }
    @Test(dataProvider = "topicsTree",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"treePageLanding"})
    public void navigateToTopicsInTree(String topic)
    {
        helperClass.treePageLanding();
        treePage.clickTopicLink(topic);
        Assert.assertTrue(treePage.validateTitleforTopics(topic));
    }
    @Test(dataProvider = "topicsTree",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"navigateToTopicsInTree"})
    public void navigateToTryEditorInTree(String topic)
    {
        helperClass.treePageLanding();
        treePage.clickTopicLink(topic);
        treePage.clickTryHereBtn(topic);
        Assert.assertTrue(treePage.tryEditorPageWithRunBtn());

    }
    @Test(dataProvider = "inputCodeForTopicsInTree",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"navigateToTryEditorInTree"})
    public void generateOutputForInputInTree(String topic, String input)
    {
        helperClass.treePageLanding();
        treePage.clickTopicLink(topic);
        treePage.clickTryHereBtn(topic);
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
    @Test(dataProvider = "topicsTree",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"treePageLanding"})
    public void validateBrokenLinkEmptyPageInTree(String topic)
    {
        helperClass.treePageLanding();
        treePage.clickTopicLink(topic);
        treePage.clickOnPQLink();
        Assert.assertTrue(treePage.emptyPage());
    }
    @Test
    public void getTotalsTopicsCountInTree()
    {
        helperClass.treePageLanding();
        treePage.topicsCoveredSection();
        log.info("Total topics present in tree page : "+treePage.getTotalCountofTopicsLink());
        Assert.assertEquals(treePage.getTotalCountofTopicsLink(),13);
    }
}
