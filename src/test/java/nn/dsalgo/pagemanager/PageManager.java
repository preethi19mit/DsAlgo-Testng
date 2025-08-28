package nn.dsalgo.pagemanager;

import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.pages.HomePage;
import nn.dsalgo.pages.LandingPage;
import nn.dsalgo.pages.LoginPage;
import nn.dsalgo.utilities.BaseLogger;
import nn.dsalgo.utilities.ExcelReader;

import java.util.Map;

public class PageManager extends BaseLogger {
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
    public String getPythonCodeDataDriven(String sheetName, String inputID)
    {
        Map<String, String> getCode = ExcelReader.getRowByTestCaseId(sheetName,inputID);
        String codeToInput = getCode.get("Python Code");
        log.info("The code to input is "+codeToInput+" for the scenario "+inputID);
        return codeToInput;
    }

    public String getOutputDataDriven(String sheetName,String inputID)
    {
        Map<String, String> getOutput = ExcelReader.getRowByTestCaseId(sheetName,inputID);
        String output = getOutput.get("Expected Output");
        return output;
    }
}
