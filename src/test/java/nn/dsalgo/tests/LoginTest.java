package nn.dsalgo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import nn.dsalgo.dataprovider.TestdataProvider;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.hooks.TestNGHooks;
import nn.dsalgo.pages.LoginPage;


public class LoginTest extends TestNGHooks {

	private LoginPage loginpage;
	
	@BeforeMethod(alwaysRun = true)
	public void setuppage()
	{
		loginpage = new LoginPage(DriverFactory.getDriver());
		log.info("Entered the testcase");
		
	}

	@Test(priority = 1,groups= {"smoke"})
	public void loginlandingpage()
	{
		loginpage.navigatetologin();
		log.info("<---Testing info---->");
    	Assert.assertEquals(loginpage.Loginvalidatetitle(), "Login");
				
	}
	
		
	@Test(priority =2,dataProvider = "MissingUsernameandpassword",dataProviderClass = TestdataProvider.class,groups= {"sanity"})
	public void Missinglogin(String option,String expectedMessage)
	{
		loginpage.navigatetologin();
		String actualMessage = "";
		switch(option) {
		case "Missing username" :
			loginpage.Missingusername();
			actualMessage = loginpage.Usernamealert();
			Assert.assertEquals(actualMessage,expectedMessage);
			break;
		case "Missing password" :
			loginpage.Missingpassword();
			actualMessage = loginpage.Passwordalert(); 
			Assert.assertEquals(actualMessage,expectedMessage);
			break;
		
		}
        log.error("<----Testing Error---->");
	    
	    }
	
	
	@Test(priority = 3,dataProvider = "InvalidUsernameandpassword",dataProviderClass = TestdataProvider.class,groups= {"sanity"})
	public void InvalidLogin(String option,String expectedMessage)
	{
		loginpage.navigatetologin();
		String actualMessage= "";
		switch(option) {
	case "Invalid username" :
		loginpage.Invalidusername();
		actualMessage = loginpage.getErrorMessage(); 
	    Assert.assertEquals(actualMessage,expectedMessage );
		break;
	case "Invalid password" :
		loginpage.Invalidpassword();
		actualMessage = loginpage.getErrorMessage(); 
	    Assert.assertEquals(actualMessage, expectedMessage);
		break;
	    }
		log.error("<----Testing Error---->");
	     
    }
	
	
	
	@Test(priority = 4,groups= {"smoke","sanity"})
	public void successfullogin()
	{
		loginpage.Login();
		log.info("<-----Testing info----->");
		Assert.assertTrue(loginpage.Loggedinvalidate());
	}
	
}



