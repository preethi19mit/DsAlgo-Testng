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
import nn.dsalgo.pages.Stackpage;

@Listeners({TestListeners.class})

public class StackTest extends TestNGHooks{
	private HelperClass helperclass;
	private Stackpage stackpage;

	 @BeforeMethod(alwaysRun = true)
	    public void BeforeSetStackPage() {
		 helperclass = new HelperClass();
		 stackpage = new Stackpage(DriverFactory.getDriver());
	    }

	  @Test(priority=1,groups= {"smoke","regression"})
	    public void getStackpage()
	    {
	     helperclass.StackpageLanding();   
	     Assert.assertEquals(stackpage.getStackpagetitle(),"Stack");
	     log.info("The User landed in : " + stackpage.getStackpagetitle());
        }
	    
	    @Test(priority=2,dataProvider = "StackTopics",dataProviderClass = TestdataProvider.class,groups={"sanity","regression"})
	    public void NavigatetoStackTopics(String Expectedpagetitle)
	    {
	    	helperclass.StackpageLanding();
	    	String Actualpagetitle = stackpage.getStackpageTopicstitle(Expectedpagetitle);
	       	Assert.assertEquals(Actualpagetitle, Expectedpagetitle);
		}
	    
	    @Test(priority=3,dataProvider = "StackTopics",dataProviderClass = TestdataProvider.class,groups={"sanity","regression"})
	    public void NavigatetoTryEditor(String topic)
	    {
	    	helperclass.StackpageLanding();
	    	stackpage.clickTopicLink(topic);
	    	String Actualpagevalidation = stackpage.validateTitle(topic);
	    	Assert.assertEquals(Actualpagevalidation,stackpage.validateTitle(topic));
	        stackpage.clickTryHereBtn();
	        Assert.assertTrue(stackpage.tryEditorVisible(),"Try Editor is not available");
	        
	    }
	    
	    @Test(priority=4,dataProvider = "StackTopics",dataProviderClass = TestdataProvider.class,groups={"sanity","regression"})
	    public void getValidPythonCode(String topic)
	    {
	      helperclass.StackpageLanding();
	      stackpage.clickTopicLink(topic);
	      stackpage.clickTryHereBtn();
	      String code = stackpage.getPythonCodeDataDriven();
	      stackpage.enterPythonCode(code);
	      log.info("Entered Code is : " +code);
	      stackpage.ClickRun();
	      String ActualOutput = stackpage.getOutputFromConsole();
	      String ExpectedOutput = stackpage.getOutputDataDriven();
	      log.info("Expected Output: " + ExpectedOutput);
	      Assert.assertEquals(ActualOutput, ExpectedOutput);      
	        }
	    
	    @Test(priority=5,dataProvider = "StackTopics",dataProviderClass = TestdataProvider.class,groups={"sanity","regression"})
	    public void getInValidPythonCode(String topic)
	    {
	      helperclass.StackpageLanding();
	      stackpage.clickTopicLink(topic);
	      stackpage.clickTryHereBtn();
	      log.info("The User is in : " + stackpage.tryEditorVisible());
	      String code = stackpage.getInvalidPythonCodeDataDriven();
	      stackpage.enterPythonCode(code);
	      log.info("Entered Code is : " +code);
	      stackpage.ClickRun();
	      Assert.assertTrue(stackpage.Alertmessage());             
	    }
	      
	   @Test(priority=6,dataProvider = "StackTopics",dataProviderClass = TestdataProvider.class,groups={"smoke"})
	   public void NavigatePracticeQuestion(String topic)
	   {
		helperclass.StackpageLanding();
		stackpage.clickTopicLink(topic);
		stackpage.ClickPracticeQuestionsLink();
		Assert.assertTrue(stackpage.isPracticePageDisplayed());
	   }
	
	  @Test(priority=7,dataProvider = "StackTopics",dataProviderClass = TestdataProvider.class,groups= {"smoke","sanity"})
	  public void PracticeQuestionBrokenLink(String topic)
	  {
		helperclass.StackpageLanding();
		stackpage.clickTopicLink(topic);
		stackpage.PracticeQuestionLink();
		Assert.assertTrue(stackpage.emptyPage());
	   }
}
