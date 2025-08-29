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
import nn.dsalgo.pagemanager.PageManager;
import nn.dsalgo.pages.Queue;



@Listeners({TestListeners.class})

public class QueueTest extends TestNGHooks {
	
	private PageManager pagemanager;
	private Queue queue;
	
	@BeforeMethod(alwaysRun = true)
	public void setuppage()
	{
		pagemanager = new PageManager();
		queue = new Queue(DriverFactory.getDriver());
		log.info("Entered the testcase");
		
	}
	
	@Test
	public void queuelandingpage()
	{
		pagemanager.QueuepageLanding();
		Assert.assertEquals(queue.getTitleforQueue(), "Queue");
	}
	
	@Test (dataProvider = "Queuetopics",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"queuelandingpage"})
	public void navigatetoQueuetopics(String links)
	{
		pagemanager.QueuepageLanding();
		queue.QueueTopics(links);
		List<String> expected = Arrays.asList(
			    "Implementation of Queue in python",
			    "Implementation using collections deque",
			    "Implementation using Array",
			    "Queue operations"
			);
			
			Assert.assertTrue(expected.contains(links),"Invalid option passed: " + links);

				
	}
	
	@Test (dataProvider = "Queuetopics",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"navigatetoQueuetopics"})
	public void clicktryhere(String links)
	{
		pagemanager.QueuepageLanding();
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

	@Test(dataProvider = "Queuetopics",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"clicktryhere"})
	public void Pythoncode(String links) {
		pagemanager.QueuepageLanding();
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
		queue.enterPythonCode(pagemanager.getPythonCodeDataDriven("Queue", "ValidCode"));
		queue.Run();
		
		
		
		
	}
	
	@Test(dataProvider = "Queuetopics",dataProviderClass = TestdataProvider.class,dependsOnMethods = {"clicktryhere"})
	public void InvalidPythoncode(String links) {
		pagemanager.QueuepageLanding();
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
		queue.enterPythonCode(pagemanager.getPythonCodeDataDriven("Queue", "InValidCode"));
		queue.Run();
		Assert.assertEquals(queue.getOutputFromConsole(),"Console output did not match");
}
}
