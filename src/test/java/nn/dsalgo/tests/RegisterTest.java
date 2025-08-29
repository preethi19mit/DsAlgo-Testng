package nn.dsalgo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

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
	    public void BeforeSetStackPage() {
		 helperclass = new HelperClass();
		 registerpage = new RegisterPage(DriverFactory.getDriver());
	    }
	 @Test()
	    public void getRegisterPage()
	    {
		 helperclass.RegisterpageLanding();
		 Assert.assertTrue(registerpage.getRegister());
	    }
}
