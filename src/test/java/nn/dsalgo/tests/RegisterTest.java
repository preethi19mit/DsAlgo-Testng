package nn.dsalgo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import nn.dsalgo.dataprovider.TestdataProvider;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.helperclass.HelperClass;
import nn.dsalgo.hooks.TestNGHooks;
import nn.dsalgo.listeners.RetryAnalyzer;
import nn.dsalgo.listeners.TestListeners;
import nn.dsalgo.pages.RegisterPage;


@Listeners({TestListeners.class})
public class RegisterTest extends TestNGHooks {

  private HelperClass helperclass;
  private RegisterPage registerpage;
  
	 @BeforeMethod(alwaysRun = true)
	    public void SetupStackPage() {
		 helperclass = new HelperClass();
		 registerpage = new RegisterPage(DriverFactory.getDriver());
	    }
	 @Test
	    public void getRegisterPage()
	    {
		 helperclass.RegisterpageLanding();
		 String ActualTitle = registerpage.ValidateTitleRegisterPage();
		 Assert.assertEquals(ActualTitle, "Register");
	     log.info("The user is on page: " + ActualTitle);
		 }
	 
	 @Test(dataProvider = "RegisterTestData",dataProviderClass = TestdataProvider.class, retryAnalyzer = RetryAnalyzer.class)
	    public void RegisterScenarios(String Testcondition, String ExpectedMessage) {
		 
		 helperclass.RegisterpageLanding(); 
	     String ActualMessage = "";
		 switch (Testcondition) {
	            case "EmptyValues":
	                registerpage.getEmptyValues();
	                ActualMessage = registerpage.getUsernameValidationMessage();
	                Assert.assertEquals(ActualMessage, ExpectedMessage);
	                break;      
	                
	            case "UsernameValues":
	                registerpage.getUsernameValues();
	                ActualMessage = registerpage.getPaswordValidationMessage();
	            	Assert.assertEquals(ActualMessage, ExpectedMessage);
	                break;     
	                
	            case "PasswordValues":
	                registerpage.getPasswordValues();
	                ActualMessage = registerpage.getUsernameValidationMessage();
	                Assert.assertEquals(ActualMessage, ExpectedMessage);
	                break;                
	                
	            case "NoPasswordConfirmation":
	                registerpage.getNoPasswordConfirmation();
	                ActualMessage = registerpage.getPwdConfirmationValidationMessage();
	            	Assert.assertEquals(ActualMessage, ExpectedMessage);
	                break;   
	                
	            case "MismatchPassword":
	                registerpage.getMismatchPassword();
	                ActualMessage = registerpage.getpwdmismatchtext();
	                Assert.assertEquals(ActualMessage, ExpectedMessage);
	                break;    
	                
	            case "ValidCredentials":
	                registerpage.getValidCredentials();
	                ActualMessage = registerpage.getNewAccountAlertmsg();
	                Assert.assertEquals(ActualMessage, ExpectedMessage);
	                break;
	                
	            case "InvalidUsername":
	                registerpage.getInvalidUsername();
	                ActualMessage = registerpage.getpwdmismatchtext();
	           		Assert.assertEquals(ActualMessage,ExpectedMessage, "Username is not valid");
	                break;   
	                
	            case "PwdNumericValue":
	                registerpage.getPwdNumericValue();
	                ActualMessage = registerpage.getpwdmismatchtext();
	               	Assert.assertEquals(ActualMessage,ExpectedMessage, "Your password can’t be entirely numeric.");
	                break;
	                
	            default:
	                throw new IllegalArgumentException("Unknown test condition: " + Testcondition);
		 }
		 log.info("Actual message: " + ActualMessage);
	 }
	 
	 @Test
	 public void ClickLoginfromRegisterPage()
	 {
		 helperclass.RegisterpageLanding();
		 log.info("Clicking login link from Register page");
		 registerpage.getClickLoginLink();
		 log.info("The user landed on Login Page");
		 Assert.assertTrue(registerpage.isLoginPageDisplayed());
	 }
	 
}



	 
	 
	 

