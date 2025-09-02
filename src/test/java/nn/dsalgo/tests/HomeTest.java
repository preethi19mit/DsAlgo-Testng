package nn.dsalgo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import nn.dsalgo.dataprovider.TestdataProvider;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.helperclass.HelperClass;
import nn.dsalgo.hooks.TestNGHooks;
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

	@Test(priority = 1,groups= {"smoke"})
	public void HomePageVerifyTitle ()
	{
		helperClass.HomePageLanding();

		String expectedTitle=homePage.titleNumby();
		Assert.assertEquals(expectedTitle, "NumpyNinja");
	
	}
	@Test(priority = 2,groups= {"smoke"})
	public void VerifyRegisterButton()
	{
		helperClass.RegisterpageLanding();
		homePage.isRegisterElementDisplayed();
	}
	@Test(priority = 3,groups= {"smoke"})
	public void VerifySignInButton()
	{
		helperClass.HomePageLanding();
		homePage.clickSignIn();
		homePage.isSignInElementDisplayed();
	}
	
	 @Test(priority=4, dataProvider = "OptionsHomePage",dataProviderClass = TestdataProvider.class, groups= {"smoke"})
	 
	 public void ClickGetStartedModulesWithoutSignIn(String option)
	 {
		 helperClass.HomePageLanding();
		 switch(option) {
			case "Datastructures" :
				homePage.clickGetStartedForDS();
				String expectedtxtInDSI= homePage.getErrorMessageText();
				Assert.assertEquals(expectedtxtInDSI, "You are not logged in");
				break;
			case "Array" :
				homePage.clickGetStartedForArray();
				String expectedtxtInArray= homePage.getErrorMessageText();
				Assert.assertEquals(expectedtxtInArray, "You are not logged in");
				break;
			case "Linkedlist" :
				homePage.clickGetStartedForLinkedList(); 
				String expectedtxtInLL= homePage.getErrorMessageText();
				Assert.assertEquals(expectedtxtInLL, "You are not logged in");
				break;
			case "Stack" :
				homePage.clickGetStartedForStack();
				String expectedtxtInStack= homePage.getErrorMessageText();
				Assert.assertEquals(expectedtxtInStack, "You are not logged in");
				break;
			case "Queue" :
				homePage.clickGetStartedForQueue();
				String expectedtxtInQueue= homePage.getErrorMessageText();
				Assert.assertEquals(expectedtxtInQueue, "You are not logged in");
				break;
			case "Tree" :
				homePage.clickGetStartedForTree();
				String expectedtxtInTree= homePage.getErrorMessageText();
				Assert.assertEquals(expectedtxtInTree, "You are not logged in");
				break;
			case "Graph" :
				homePage.clickGetStartedForGraph();
				String expectedtxtInGraph= homePage.getErrorMessageText();
				Assert.assertEquals(expectedtxtInGraph, "You are not logged in");
				break;
			}
		 
	 }
	 
 @Test(priority=5, dataProvider = "OptionsHomeScenario",dataProviderClass = TestdataProvider.class, groups= {"smoke"})
	 
	 public void ClickGetStartedModulesFromDrpdownWithoutSignIn(String option) 
	 {
	 helperClass.HomePageLanding();
	 homePage.clickDrpdown();

	 switch(option) {
		case "Array" :
			homePage.clickArraysDrpdown();
			String expectedtxtInArray= homePage.getErrorMessageText();
			Assert.assertEquals(expectedtxtInArray, "You are not logged in");
			break;
		case "Linkedlist" :
			homePage.clickLinkedListDrpdown();
			String expectedtxtInLL= homePage.getErrorMessageText();
			Assert.assertEquals(expectedtxtInLL, "You are not logged in");
			break;
		case "Stack" :
			homePage.clickStackDrpdown();
			String expectedtxtInStack= homePage.getErrorMessageText();
			Assert.assertEquals(expectedtxtInStack, "You are not logged in");
			break;
		case "Queue" :
			homePage.clickQueueDrpdown();
			String expectedtxtInQueue= homePage.getErrorMessageText();
			Assert.assertEquals(expectedtxtInQueue, "You are not logged in");
			break;
		case "Tree" :
			homePage.clickTreeDrpdown();
			String expectedtxtInTree= homePage.getErrorMessageText();
			Assert.assertEquals(expectedtxtInTree, "You are not logged in");
			break;
		case "Graph" :
			homePage.clickGraphDrpdown();
			String expectedtxtInGraph= homePage.getErrorMessageText();
			Assert.assertEquals(expectedtxtInGraph, "You are not logged in");
			break;
		}
	    
	 }
 
 @Test(priority=6, dataProvider = "OptionsHomePage",dataProviderClass = TestdataProvider.class, groups= {"sanity"})
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
 
 @Test(priority=7, dataProvider = "OptionsHomeScenario",dataProviderClass = TestdataProvider.class,groups={"sanity"})
 
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
 @Test(priority=8, groups= {"smoke"})
 public void SuccesfulSignout()
 {
	 helperClass.SuccessfulLogin();
	 homePage.clickSignOut();
	 String expectedtxt=homePage.getSuccessMessageText();
		Assert.assertEquals(expectedtxt, "Logged out successfully");
		
 }
		
	@Test(priority=9, groups={"sanity"})
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



	