package nn.dsalgo.tests;

import nn.dsalgo.dataprovider.TestdataProvider;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.helperclass.HelperClass;
import nn.dsalgo.hooks.TestNGHooks;
import nn.dsalgo.listeners.TestListeners;
import nn.dsalgo.pages.ArrayPage;
import nn.dsalgo.pages.GraphPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class GraphTest extends TestNGHooks {

    private HelperClass helperClass;
    private GraphPage graphPage;

    @BeforeMethod(alwaysRun = true)
    public void setupGraphPage() {
        helperClass = new HelperClass();
        graphPage = new GraphPage(DriverFactory.getDriver());
        log.info("Entered the Graph testcases");
    }

    @Test()
    public void graphLandingPage()
    {
        helperClass.graphPageLanding();
        Assert.assertEquals(graphPage.getTitleforGraphPage(),"Graph");
    }

    @Test(dataProvider = "graphTopics",dataProviderClass = TestdataProvider.class)
    public void graphValidCode(String graphTopics)
    {
        helperClass.graphPageLanding();
        graphPage.selectGraphOption(graphTopics);
        graphPage.clickOnPQLink();
        graphPage.emptyPage();
        graphPage.Tryhere();
        graphPage.enterPythonCode(graphPage.getPythonCodeDataDriven());
        graphPage.Run();
        log.info("<----Testing Info---->");
    }

    @Test(dataProvider = "graphTopics",dataProviderClass = TestdataProvider.class)
    public void graphInvalidCode(String graphTopics)
    {
        helperClass.graphPageLanding();
        graphPage.selectGraphOption(graphTopics);
        graphPage.clickOnPQLink();
        graphPage.emptyPage();
        graphPage.Tryhere();
        graphPage.enterPythonCode(graphPage.getInvalidCodeDataDriven());
        graphPage.Run();
        log.error("<-----Testing error----->");
    }

}
