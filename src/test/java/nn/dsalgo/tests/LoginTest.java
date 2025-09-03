package nn.dsalgo.tests;

import java.util.Map;

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
	
		
	@Test(priority =2,dataProvider = "MissingUsername",dataProviderClass = TestdataProvider.class,groups= {"sanity"})
	public void MissingUsername(String testcaseid)
	{
		    loginpage.navigatetologin();
		    String actualMessage = "";
		    Map<String,String>result = loginpage.UsernamePwdScenario(testcaseid);
			actualMessage = loginpage.Usernamealert();
			String expectedMessage = result.get("message");
			log.error("<----Testing Error---->");
			Assert.assertEquals(actualMessage,expectedMessage);
	}
	
	@Test(priority =3,dataProvider = "Missingpassword",dataProviderClass = TestdataProvider.class,groups= {"sanity"})
	public void MissingPassword(String testcaseid)
	{
		    loginpage.navigatetologin();
		    String actualMessage = "";
		    Map<String,String>result = loginpage.UsernamePwdScenario(testcaseid);
			actualMessage = loginpage.Passwordalert();
			String expectedMessage = result.get("message");
			log.error("<----Testing Error---->");
			Assert.assertEquals(actualMessage,expectedMessage);
		
		}
        
	    
	    
	
	
	@Test(priority = 4,dataProvider = "InvalidUsername",dataProviderClass = TestdataProvider.class,groups= {"sanity"})
	public void InvalidUsername(String testcaseid)
	{
		    loginpage.navigatetologin();
		    String actualMessage = "";
		    Map<String,String>result = loginpage.UsernamePwdScenario(testcaseid);
			actualMessage = loginpage.getErrorMessage();
			String expectedmsg = result.get("message");
			log.error("<----Testing Error---->");
			Assert.assertEquals(actualMessage,expectedmsg);
		
	     
    }
	
	@Test(priority = 4,dataProvider = "Invalidpassword",dataProviderClass = TestdataProvider.class,groups= {"sanity"})
	public void Invalidpassword(String testcaseid)
	{
		    loginpage.navigatetologin();
		    String actualMessage = "";
		    Map<String,String>result = loginpage.UsernamePwdScenario(testcaseid);
			actualMessage = loginpage.getErrorMessage();
			String expectedmsg = result.get("message");
			log.error("<----Testing Error---->");
			Assert.assertEquals(actualMessage,expectedmsg);
		
	     
    }
	
	
	
	@Test(priority = 5,groups= {"smoke","sanity"})
	public void successfullogin()
	{
		loginpage.Login();
		log.info("<-----Testing info----->");
		Assert.assertTrue(loginpage.Loggedinvalidate());
	}
	
}



