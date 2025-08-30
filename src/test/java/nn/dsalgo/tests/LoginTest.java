package nn.dsalgo.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import nn.dsalgo.dataprovider.TestdataProvider;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.hooks.TestNGHooks;
import nn.dsalgo.listeners.RetryAnalyzer;
import nn.dsalgo.listeners.TestListeners;
import nn.dsalgo.pages.LoginPage;

@Listeners({TestListeners.class})
public class LoginTest extends TestNGHooks {
	
	
	private LoginPage loginpage;
	
	@BeforeMethod(alwaysRun = true)
	public void setuppage()
	{
		loginpage = new LoginPage(DriverFactory.getDriver());
		log.info("Entered the testcase");
		
	}

	@Test
	public void loginlandingpage()
	{
		loginpage.navigatetologin();
		log.info("<---Testing info---->");
    	Assert.assertEquals(loginpage.Loginvalidatetitle(), "Login");
				
	}
	
		
	@Test(dataProvider = "Loginscenario",dataProviderClass = TestdataProvider.class,retryAnalyzer = RetryAnalyzer.class)
	public void performlogin(String option)
	{
		loginpage.navigatetologin();
		switch(option) {
		case "Missing username" :
			loginpage.Missingusername();
			break;
		case "Missing password" :
			loginpage.Missingpassword();
			break;
		case "Invalid username" :
			loginpage.Invalidusername();
			break;
		case "Invalid password" :
			loginpage.Invalidpassword();
			break;
		}
			
		Map<String, String> expectedMessages = new HashMap<>();
	    expectedMessages.put("Missing username", "Please fill out this field.");
	    expectedMessages.put("Missing password", "Please fill out this field.");
	    expectedMessages.put("Invalid username", "Invalid Username and Password");
	    expectedMessages.put("Invalid password", "Invalid Username and Password");

	    String actualMessage;
	    if (option.equals("Missing username")) {
	        actualMessage = loginpage.Usernamealert();
	    } else if (option.equals("Missing password")) {
	        actualMessage = loginpage.Passwordalert(); 
	    } else {
	        actualMessage = loginpage.getErrorMessage(); 
	    }
        log.error("<----Testing Error---->");
	    Assert.assertEquals(actualMessage, expectedMessages.get(option),
	        "Error message mismatch for option: " + option);
	    
		}
	
	
	@Test
	public void successfullogin()
	{
		loginpage.Login();
		log.info("<-----Testing info----->");
		Assert.assertTrue(loginpage.Loggedinvalidate());
	}
	
}



