package nn.dsalgo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import nn.dsalgo.dataprovider.TestdataProvider;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.helperclass.HelperClass;
import nn.dsalgo.hooks.TestNGHooks;
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
	 
	 @Test(priority=1,groups= {"smoke","sanity","regression"})
	    public void getRegisterPage() {
		 
		 helperclass.RegisterpageLanding();
		 String ActualTitle = registerpage.ValidateTitleRegisterPage();
		 Assert.assertEquals(ActualTitle, "Register");
	     log.info("The user is on page: " + ActualTitle);
		 }
	 
	 @Test(priority=2,dataProvider = "RegisterMissingFieldsValidation",dataProviderClass = TestdataProvider.class,groups= {"smoke","regression"})
	    public void RegisterMissingFieldsValidate(String Testcondition, String ExpectedMessage) {
		 
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
	            
	            default:
	                throw new IllegalArgumentException("Unknown test condition: " + Testcondition);
		    }
		 log.info("Actual message: " + ActualMessage);    
 }
	         	 
	 @Test(priority=3,dataProvider = "RegisterValidcredentials",dataProviderClass = TestdataProvider.class,groups= {"smoke","regression"})
	    public void RegisterValidCredential(String ActualMessage, String ExpectedMessage) {
		 
		 helperclass.RegisterpageLanding();
		 registerpage.getValidCredentials();
         ActualMessage = registerpage.getNewAccountAlertmsg();
         Assert.assertEquals(ActualMessage, ExpectedMessage);
	 }
	 
	 @Test(priority=4,dataProvider = "RegisterMismatchPassword",dataProviderClass = TestdataProvider.class,groups= {"sanity","regression"})
	    public void RegisterMismatchPassword(String ActualMessage, String ExpectedMessage) {
		 
		 helperclass.RegisterpageLanding();
         registerpage.getMismatchPassword();
         ActualMessage = registerpage.getpwdmismatchtext();
         Assert.assertEquals(ActualMessage, ExpectedMessage);
	 }

	 @Test(priority=5,dataProvider = "RegisterInvalidCredentials",dataProviderClass = TestdataProvider.class,groups= {"sanity","regression"})
	    public void RegisterInvalidCredential(String Testcondition, String ExpectedMessage) {
		
		 helperclass.RegisterpageLanding(); 
	     String ActualMessage = "";
		 switch (Testcondition) {
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
			 }
		 log.info("Actual message: " + ActualMessage);
	 }
	 
	 @Test(priority=6,groups= {"smoke","sanity"})
	 public void ClickLoginfromRegisterPage()
	 {
		 helperclass.RegisterpageLanding();
		 log.info("Clicking login link from Register page");
		 registerpage.getClickLoginLink();
		 log.info("The user landed on Login Page");
		 Assert.assertTrue(registerpage.isLoginPageDisplayed());
	 }
	 
}



	 
	 
	 

