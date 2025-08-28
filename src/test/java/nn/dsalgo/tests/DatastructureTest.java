package nn.dsalgo.tests;

import nn.dsalgo.dataprovider.TestdataProvider;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.hooks.TestNGHooks;
import nn.dsalgo.listeners.TestListeners;
import nn.dsalgo.pagemanager.PageManager;
import nn.dsalgo.pages.DataStructurePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestListeners.class})
public class DatastructureTest extends TestNGHooks {
    private PageManager pageManager;
    private DataStructurePage dsp;

    @BeforeMethod(alwaysRun = true)
    public void setupPages() {
        pageManager = new PageManager();
        dsp = new DataStructurePage(DriverFactory.getDriver());
        log.info("Entered the testcase");
    }

    @Test()
    public void dsPageLanding()
    {
        pageManager.dsPageLanding();
        Assert.assertEquals(dsp.getTitleforDSI(),"Data Structures-Introduction");
    }
    @Test(dataProvider = "topicsDS",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"dsPageLanding"})
    public void navigateToTopicsInDS(String topic)
    {
        pageManager.dsPageLanding();
        dsp.clickTopicLink(topic);
        Assert.assertEquals(dsp.validateTitle(topic),"Time Complexity");
    }
    @Test(dataProvider = "topicsDS",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"navigateToTopicsInDS"})
    public void navigateToTryEditorInDS(String topic)
    {
        pageManager.dsPageLanding();
        dsp.clickTopicLink(topic);
        Assert.assertEquals(dsp.validateTitle(topic),"Time Complexity");
        dsp.clickTryHereBtn();
        Assert.assertTrue(dsp.tryEditorVisible(),"Try Editor not available");
    }
}
