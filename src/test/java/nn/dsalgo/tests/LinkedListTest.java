package nn.dsalgo.tests;
import nn.dsalgo.dataprovider.TestdataProvider;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.hooks.TestNGHooks;

import nn.dsalgo.helperclass.HelperClass;
import nn.dsalgo.pages.LinkedListPage;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedListTest extends TestNGHooks {
    private HelperClass helperClass;
    private LinkedListPage linkedListPage;

    @BeforeMethod(alwaysRun = true)
    public void setupPageLinkedList() {
        helperClass = new HelperClass();
        linkedListPage= new LinkedListPage(DriverFactory.getDriver());
        log.info("Entered the Linked List testcases");
    }

    @Test(priority=1, groups= {"smoke"})
    public void LinkedListPageLanding()
    {
        helperClass.LinkedListPageLanding();
        Assert.assertEquals(linkedListPage.getTitleforLinkedList(),"Linked List");
    }
    
    @Test(priority=2, dataProvider = "OptionsLinkedList",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"LinkedListPageLanding"}, groups= {"sanity"})
    public void navigateToLinksInLinkedList(String option)
    {
        helperClass.LinkedListPageLanding();
        linkedListPage.clickLinkFromLL(option);        
        String pageTitle=linkedListPage.getLinksTitle(option);
    	log.info("Verifying the title of " +option + " : Page Title value: "+pageTitle);
       Assert.assertEquals(pageTitle, option);
        
    }
	
	  @Test(priority=3,dataProvider = "OptionsLinkedList",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"navigateToLinksInLinkedList"},groups={"regression"}) 
	  public void navigateToTryEditorInLinkedList(String option) {
		  helperClass.LinkedListPageLanding();
		  linkedListPage.clickLinkFromLL(option);		  
		  linkedListPage.clickTryHereButton();		  
		  Assert.assertTrue(linkedListPage.istryEditorDisplayed());
	  
	  }
	  
	  @Test(priority=4, dataProvider = "OptionsLinkedList",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"navigateToTryEditorInLinkedList"}, groups= {"regression"})
	  public void generateOutputForInputInLinkedList(String option) {
		  helperClass.LinkedListPageLanding();
		  linkedListPage.clickLinkFromLL(option);		  
		  linkedListPage.clickTryHereButton();		  
		  String code = linkedListPage.getPythonCodeFromExcel();
		  linkedListPage.enterPythonCode(code);
	      log.info("Entered Code is : " +code);
	      linkedListPage.clickRunButton();
	      String ActualOutput = linkedListPage.getOutputFromConsole();
	      String ExpectedOutput = linkedListPage.getOutputDataDriven();
	      log.info("Expected Output: " + ExpectedOutput);
	      Assert.assertEquals(ActualOutput, ExpectedOutput);
	  } 
	  
	  @Test(priority=5, dataProvider = "OptionsLinkedList",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"navigateToTryEditorInLinkedList"},groups= {"sanity,regression"})
	    public void getInValidPythonCode(String option)
	    {
		  helperClass.LinkedListPageLanding();
	      linkedListPage.clickLinkFromLL(option);		  
		  linkedListPage.clickTryHereButton();
	      log.info("The User is in : " + linkedListPage.istryEditorDisplayed());
	      String code = linkedListPage.getInvalidPythonCodeFromExcel();
	      linkedListPage.enterPythonCode(code);
	      log.info("Entered Code is : " +code);
	      linkedListPage.clickRunButton();
	      String actualOutput=linkedListPage.alertwindow();
	  	  String expectedOutput=linkedListPage.getAlertOutputFromDataDriven();
	  	  Assert.assertEquals(actualOutput, expectedOutput);
	      log.info("Actual result matched with expected Result");
	             
	    }
	  
	  
	  @Test(priority=6, dataProvider = "OptionsLinkedList",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"LinkedListPageLanding"},groups= {"smoke,sanity"}) 
	  public void validateBrokenLinkEmptyPageInTree(String option) {
	  helperClass.LinkedListPageLanding();
	  linkedListPage.clickIntrolink();
	  linkedListPage.clickPracticeQuestionsLink(); 
	  Assert.assertTrue(linkedListPage.emptyPage());
	  }	   
	 
}
