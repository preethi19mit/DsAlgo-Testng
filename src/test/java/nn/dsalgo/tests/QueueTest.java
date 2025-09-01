package nn.dsalgo.tests;



import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import nn.dsalgo.dataprovider.TestdataProvider;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.hooks.TestNGHooks;
import nn.dsalgo.listeners.TestListeners;
import nn.dsalgo.pages.QueuePage;
import nn.dsalgo.helperclass.HelperClass;


@Listeners({TestListeners.class})

public class QueueTest extends TestNGHooks {
	
	private HelperClass helperClass;
	private QueuePage queue;

	
	@BeforeMethod(alwaysRun = true)
	public void setuppage()
	{
		helperClass = new HelperClass();
		queue = new QueuePage(DriverFactory.getDriver());
		log.info("Entered the testcase");
		
	}
	
	@Test(priority = 1,groups= {"smoke"})
	public void queuelandingpage()
	{
		helperClass.QueuepageLanding();
		Assert.assertEquals(queue.getTitleforQueue(), "Queue");
	}
	
	@Test (priority = 2,dataProvider = "Queuetopics",dataProviderClass = TestdataProvider.class,groups= {"sanity"})
	public void navigatetoQueuetopics(String links)
	{
		helperClass.QueuepageLanding();
		queue.QueueTopics(links);
		List<String> expected = Arrays.asList(
			    "Implementation of Queue in python",
			    "Implementation using collections deque",
			    "Implementation using Array",
			    "Queue operations"
			);
			
			Assert.assertTrue(expected.contains(links),"Invalid option passed: " + links);

				
	}
	
	@Test (priority = 3,dataProvider = "Queuetopics",dataProviderClass = TestdataProvider.class,groups= {"sanity1"})
	public void clicktryhere(String links)
	{
		helperClass.QueuepageLanding();
		Assert.assertEquals(queue.getTitleforQueue(), "Queue");
		queue.QueueTopics(links);
		List<String> expected = Arrays.asList(
			    "Implementation of Queue in python",
			    "Implementation using collections deque",
			    "Implementation using Array",
			    "Queue operations"
			);
			
			Assert.assertTrue(expected.contains(links),"Invalid option passed: " + links);
		queue.Tryhere();
		Assert.assertTrue(queue.tryEditorVisible(), "Try Editor not available");
	}

	@Test(priority = 4,dataProvider = "Queuetopics",dataProviderClass = TestdataProvider.class,groups= {"sanity2"})
	public void Pythoncode(String links) {
		helperClass.QueuepageLanding();
		Assert.assertEquals(queue.getTitleforQueue(), "Queue");
		queue.QueueTopics(links);
		List<String> expected = Arrays.asList(
			    "Implementation of Queue in python",
			    "Implementation using collections deque",
			    "Implementation using Array",
			    "Queue operations"
			);
			
			Assert.assertTrue(expected.contains(links),"Invalid option passed: " + links);
		queue.Tryhere();
		Assert.assertTrue(queue.tryEditorVisible(), "Try Editor not available");
		queue.enterPythonCode(queue.getPythonCodeDataDriven("Queue", "ValidCode"));
		queue.Run();
		String ActualOutput = queue.getOutputFromConsole();
		String ExpectedOutput = queue.getOutputDataDriven();
	      log.info("Expected Output: " + ActualOutput);
	      Assert.assertEquals(ActualOutput,ExpectedOutput);
		
		
		
		
	}
	
	@Test(priority = 5,dataProvider = "Queuetopics",dataProviderClass = TestdataProvider.class,groups= {"sanity3"})
	public void InvalidPythoncode(String links) {
		helperClass.QueuepageLanding();
		Assert.assertEquals(queue.getTitleforQueue(), "Queue");
		queue.QueueTopics(links);
		List<String> expected = Arrays.asList(
			    "Implementation of Queue in python",
			    "Implementation using collections deque",
			    "Implementation using Array",
			    "Queue operations"
			);
			
			Assert.assertTrue(expected.contains(links),"Invalid option passed: " + links);
		queue.Tryhere();
		Assert.assertTrue(queue.tryEditorVisible(), "Try Editor not available");
		queue.enterPythonCode(queue.getPythonCodeDataDriven("Queue", "InValidCode"));
		queue.Run();
		log.error("<----Invalid code--->");
		Assert.assertTrue(queue.Handlealert());
}
	@Test(priority = 6,dataProvider = "Queuetopics",dataProviderClass = TestdataProvider.class,groups= {"test"})
	public void PracticeQuestionBrokenLink(String links)
	{
		helperClass.QueuepageLanding();
		queue.QueueTopics(links);
		queue.PracticeQuestionLink();
		Assert.assertTrue(queue.emptyPage());
	}
}
