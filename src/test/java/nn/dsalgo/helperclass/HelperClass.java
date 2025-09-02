package nn.dsalgo.helperclass;

import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.pages.HomePage;
import nn.dsalgo.pages.LandingPage;
import nn.dsalgo.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HelperClass {
    private static final Logger log = LogManager.getLogger(HelperClass.class);
    LandingPage landingPage = new LandingPage(DriverFactory.getDriver());
    HomePage homePage = new HomePage(DriverFactory.getDriver());
    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

    public void SuccessfulLogin()
    {
        landingPage.getURL();
        landingPage.clickGetStartedBtn();
        homePage.clickSignIn();
        loginPage.performLoginDataDriven();
        homePage.CheckName();
    }

    public void dsPageLanding()
    {
        SuccessfulLogin();
        homePage.clickGetStartedForDS();
        log.info("Landed in Data structures page");
    }
    
    public void StackpageLanding() {
        SuccessfulLogin();
        homePage.clickGetStartedForStack();
    }

    public void QueuepageLanding()
    {
    	SuccessfulLogin();
    	homePage.clickGetStartedForQueue();
    	log.info("Queue page Loaded");
    }

    public void treePageLanding()
    {
        SuccessfulLogin();
        homePage.clickGetStartedForTree();
        log.info("Landed in Tree page");
    }
    
    public void RegisterpageLanding()
    {
    	landingPage.getURL();
        landingPage.clickGetStartedBtn();
        homePage.clickRegister();
    }
    
    public void LinkedListPageLanding()
    {
    	SuccessfulLogin();
    	homePage.clickGetStartedForLinkedList();
        log.info("Landed on Linked List page");
    }
    public void HomePageLanding()
    {
    	 landingPage.getURL();
         landingPage.clickGetStartedBtn();
         log.info("Landed on Home Page");
    }
    public void arrayPageLanding()
    {
        SuccessfulLogin();
        homePage.clickGetStartedForArray();
        log.info("Landed in Array page");
    }

    public void graphPageLanding()
    {
        SuccessfulLogin();
        homePage.clickGetStartedForGraph();
        log.info("Landed in Graph page");
    }
}
