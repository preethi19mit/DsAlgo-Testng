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
import nn.dsalgo.pages.HomePage;


public class HomeTest extends TestNGHooks {
	
	private HelperClass helperClass;
	private HomePage homePage;
	
	@BeforeMethod(alwaysRun = true)
	public void setuppage()
	{
		 helperClass = new HelperClass();
		homePage = new HomePage(DriverFactory.getDriver());
		log.info("Entered the testcase");
		
	}

	@Test
	public void HomePageVerifyTitle ()
	{
		helperClass.HomePageLanding();

		String expectedTitle=homePage.titleNumby();
		Assert.assertEquals(expectedTitle, "NumpyNinja");
	
	}
	@Test
	public void VerifyRegisterButton()
	{
		helperClass.RegisterpageLanding();
		homePage.isRegisterElementDisplayed();
	}
	@Test
	public void VerifySignInButton()
	{
		helperClass.HomePageLanding();
		homePage.clickSignIn();
		homePage.isSignInElementDisplayed();
	}
	
	 @Test(dataProvider = "OptionsHomePage",dataProviderClass = TestdataProvider.class)
	 
	 public void ClickGetStartedModulesWithoutSignIn(String option)
	 {
		 helperClass.HomePageLanding();
		 switch(option) {
			case "Datastructures" :
				homePage.clickGetStartedForDS();
				break;
			case "Array" :
				homePage.clickGetStartedForArray(); 
				break;
			case "Linkedlist" :
				homePage.clickGetStartedForLinkedList(); 
				break;
			case "Stack" :
				homePage.clickGetStartedForStack();
				break;
			case "Queue" :
				homePage.clickGetStartedForQueue();
				break;
			case "Tree" :
				homePage.clickGetStartedForTree();
				break;
			case "Graph" :
				homePage.clickGetStartedForGraph(); 
				break;
			}
		 homePage.getErrorMessageText();
	 }
	 
 @Test(dataProvider = "OptionsHomeScenario",dataProviderClass = TestdataProvider.class)
	 
	 public void ClickGetStartedModulesFromDrpdownWithoutSignIn(String option) 
	 {
	 helperClass.HomePageLanding();
	 homePage.clickDrpdown();

		switch(option) {
		case "Array" :
			homePage.clickArraysDrpdown();
			break;
		case "Linkedlist" :
			homePage.clickLinkedListDrpdown();
			break;
		case "Stack" :
			homePage.clickStackDrpdown();
			break;
		case "Queue" :
			homePage.clickQueueDrpdown(); 
			break;
		case "Tree" :
			homePage.clickTreeDrpdown();
			break;
		case "Graph" :
			homePage.clickGraphDrpdown();
			break;
		}
		homePage.getErrorMessageText();
	    
	 }
 
 @Test(dataProvider = "OptionsHomePage",dataProviderClass = TestdataProvider.class)
  public void ClickGetStartedModulesAfterSignIn(String option)
 {
	 helperClass.SuccessfulLogin();
	 switch(option) {
	 case "Datastructures" :
			homePage.clickDataStructureAftsign();
			String titleDSI=homePage.getDataStructuretitle();
			Assert.assertEquals(titleDSI, "Data Structures-Introduction");
			break;
		case "Array" :
			homePage.clickArrayAftsign();
			String titleArray=homePage.getArraytitle();
			Assert.assertEquals(titleArray, "Array");
			break;
		case "Linkedlist" :
			homePage.clickLinkedListAftsign();
			String titleLL=homePage.getLinkedListtitle();
			Assert.assertEquals(titleLL, "Linked List");
		    break;
	    case "Stack" :
	    	homePage.clickStackAftsign();
	    	String titleStack=homePage.getStacktitle();
			Assert.assertEquals(titleStack, "Stack");
		    break;
	    case "Queue" :
	    	homePage.clickQueueAftsign();
	    	String titleQueue=homePage.getQueuetitle();
			Assert.assertEquals(titleQueue, "Queue");
			break;
		case "Tree" :
			homePage.clickTreeAftsign();
			String titleTree=homePage.getTreetitle();
			Assert.assertEquals(titleTree, "Tree");
			break;
		case "Graph" :
			homePage.clickGraphAftsign();
			String titleGraph=homePage.getGraphtitle();
			Assert.assertEquals(titleGraph, "Graph");
			break;
	   		}
	 
				
	}
 
 @Test(dataProvider = "OptionsHomeScenario",dataProviderClass = TestdataProvider.class)
 
 public void ClickGetStartedModulesFromDrpdownAfterSignIn(String option) 
 {
	 helperClass.SuccessfulLogin();
     homePage.clickDrpdown();

		switch(option) {
		case "Array" :
			homePage.clickArraysDrpdown();
			String titleArray=homePage.getArraytitle();
			Assert.assertEquals(titleArray, "Array");
			break;
		case "Linkedlist" :
			homePage.clickLinkedListDrpdown();
			String titleLL=homePage.getLinkedListtitle();
			Assert.assertEquals(titleLL, "Linked List");
			break;
		case "Stack" :
			homePage.clickStackDrpdown();
			String titleStack=homePage.getStacktitle();
			Assert.assertEquals(titleStack, "Stack");
			break;
		case "Queue" :
			homePage.clickQueueDrpdown(); 
			String titleQueue=homePage.getQueuetitle();
			Assert.assertEquals(titleQueue, "Queue");
			break;
		case "Tree" :
			homePage.clickTreeDrpdown();
			String titleTree=homePage.getTreetitle();
			Assert.assertEquals(titleTree, "Tree");
			break;
		case "Graph" :
			homePage.clickGraphDrpdown();
			String titleGraph=homePage.getGraphtitle();
			Assert.assertEquals(titleGraph, "Graph");
			break;
		}

 }
 @Test
 public void SuccesfulSignout()
 {
	 helperClass.SuccessfulLogin();
	 homePage.clickSignOut();
	 String expectedtxt=homePage.getSuccessMessageText();
		Assert.assertEquals(expectedtxt, "Logged out successfully");
		
 }
		
	@Test
	public void VerifyIncorrectPageTitle()
	{
		helperClass.HomePageLanding();
		 String actualTitle =homePage.verifyIncorrectTitle();
	       	String expectedTitle = "Data structure";
	       	if (!actualTitle.equals(expectedTitle)) {
	       	    Assert.fail("Condition failed: expected true but got false.");
	}
	}	
		
		
 }



	