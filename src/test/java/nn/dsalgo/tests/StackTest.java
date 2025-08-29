package nn.dsalgo.tests;

import nn.dsalgo.helperclass.HelperClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import nn.dsalgo.dataprovider.TestdataProvider;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.hooks.TestNGHooks;
import nn.dsalgo.listeners.TestListeners;
import nn.dsalgo.pages.Stackpage;

@Listeners({TestListeners.class})

public class StackTest extends TestNGHooks{
	private HelperClass helperClass;
	private Stackpage stackpage;

	 @BeforeMethod(alwaysRun = true)
	    public void BeforeSetStackPage() {
		 helperClass = new HelperClass();
		 stackpage = new Stackpage(DriverFactory.getDriver());
	    }

	    @Test()
	    public void getStackpage()
	    {
			helperClass.StackpageLanding();
	     Assert.assertEquals(stackpage.getStackpagetitle(),"Stack");
	     log.info("The User landed in : " + stackpage.getStackpagetitle());
        }
	    
	    @Test(dataProvider = "StackTopics",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"getStackpage"})
	    public void NavigatetoStackTopics(String Expectedpagetitle)
	    {
			helperClass.StackpageLanding();
	    	String Actualpagetitle = stackpage.getStackpageTopicstitle(Expectedpagetitle);
	       	Assert.assertEquals(Actualpagetitle, Expectedpagetitle);
		}
	    
	    @Test(dataProvider = "StackTopics",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"NavigatetoStackTopics"})
	    public void NavigatetoTryEditor(String topic)
	    {
			helperClass.StackpageLanding();
	    	stackpage.clickTopicLink(topic);
	    	String Actualpagevalidation = stackpage.validateTitle(topic);
	    	Assert.assertEquals(Actualpagevalidation,stackpage.validateTitle(topic));
	        stackpage.clickTryHereBtn();
	        Assert.assertTrue(stackpage.tryEditorVisible(),"Try Editor is not available");
	    }
	    
	    @Test(dataProvider = "StackTopics",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"NavigatetoTryEditor"})
	    public void getValidPythonCode(String topic)
	    {
			helperClass.StackpageLanding();
	      stackpage.clickTopicLink(topic);
	      stackpage.clickTryHereBtn();
//	      stackpage.enterPythonCode(pagemanager.getPythonCodeDataDriven("Stack","ValidCode"));
	      stackpage.ClickRun();
	      String ActualOutput = stackpage.getOutputFromConsole();
	      log.info("Actual Output in the console : "+ ActualOutput);
	      String ExpectedOutput = stackpage.getOutputDataDriven();
	      log.info("Expected Output : "+ ExpectedOutput);
	      Assert.assertEquals(ActualOutput,ExpectedOutput);
	        
	    }
	    
	    @Test(dataProvider = "StackTopics",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"NavigatetoTryEditor"})
	    public void getInValidPythonCode(String topic)
	    {
			helperClass.StackpageLanding();
	      stackpage.clickTopicLink(topic);
	      stackpage.clickTryHereBtn();
//	      stackpage.enterPythonCode(pagemanager.getPythonCodeDataDriven("Stack","InvalidCode"));
	      stackpage.ClickRun();
	      Assert.assertTrue(stackpage.Alertmessage(),"No alert appeared");
	             
	    }
	    
	
}
