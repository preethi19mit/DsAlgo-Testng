package nn.dsalgo.tests;

import nn.dsalgo.dataprovider.TestdataProvider;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.hooks.TestNGHooks;
import nn.dsalgo.listeners.TestListeners;
import nn.dsalgo.helperclass.HelperClass;
import nn.dsalgo.pages.DataStructurePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListeners.class})
public class DatastructureTest extends TestNGHooks {
    private HelperClass helperClass;
    private DataStructurePage dsp;

    @BeforeMethod(alwaysRun = true)
    public void setupPagesDS() {
        helperClass = new HelperClass();
        dsp = new DataStructurePage(DriverFactory.getDriver());
        log.info("Entered the DS testcases");
    }

    @Test()
    public void dsPageLanding()
    {
        helperClass.dsPageLanding();
        Assert.assertEquals(dsp.getTitleforDSI(),"Data Structures-Introduction");
    }
    @Test(dataProvider = "topicsDS",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"dsPageLanding"})
    public void navigateToTopicsInDS(String topic)
    {
        helperClass.dsPageLanding();
        dsp.clickTopicLink(topic);
        Assert.assertEquals(dsp.validateTitle(topic),"Time Complexity");
    }
    @Test(dataProvider = "topicsDS",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"navigateToTopicsInDS"})
    public void navigateToTryEditorInDS(String topic)
    {
        helperClass.dsPageLanding();
        dsp.clickTopicLink(topic);
        dsp.clickTryHereBtn();
        Assert.assertTrue(dsp.tryEditorVisible(),"Try Editor not available");
    }
    @Test(dataProvider = "inputCodeForTopicsInDS",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"navigateToTryEditorInDS"})
    public void generateOutputForInputInDS(String topic, String input)
    {
        helperClass.dsPageLanding();
        dsp.clickTopicLink(topic);
        dsp.clickTryHereBtn();
        String code = dsp.getPythonCodeDataDriven(input);
        dsp.enterPythonCode(code);
        log.info("The code entered in the editor : "+code);
        dsp.clickRunBtn();
        log.info ("Run button clicked");
        String actualOutput = dsp.processInputAndReturnStatus();
        if (actualOutput != null) {
            String expectedOutput = dsp.getOutputDataDriven(input);
            log.info("Expected Output: " + expectedOutput);
            Assert.assertEquals(actualOutput, expectedOutput);
            log.info("✅ Actual Output matched with the Expected Output");
        } else {
            log.warn("Test failed due to invalid input, alert was handled.");
        }
    }
    @Test(dataProvider = "topicsDS",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"dsPageLanding"})
    public void validateBrokenLinkEmptyPageInDS(String topic)
    {
        helperClass.dsPageLanding();
        dsp.clickTopicLink(topic);
        dsp.clickOnPQLink();
        Assert.assertTrue(dsp.emptyPage());
    }
    @Test
    public void getTotalTopicsCountInDS()
    {
        helperClass.dsPageLanding();
        dsp.topicsCoveredSection();
        log.info("Total topics present in this page : "+dsp.getTotalCountofTopicsLink());
        Assert.assertEquals(dsp.getTotalCountofTopicsLink(),1);

    }

}
