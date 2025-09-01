package nn.dsalgo.tests;

import nn.dsalgo.dataprovider.TestdataProvider;
import nn.dsalgo.factory.DriverFactory;
import nn.dsalgo.helperclass.HelperClass;
import nn.dsalgo.hooks.TestNGHooks;
import nn.dsalgo.listeners.TestListeners;
import nn.dsalgo.pages.ArrayPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


public class ArrayTest extends TestNGHooks {

    private HelperClass helperClass;
    private ArrayPage arrayPage;

    @BeforeMethod(alwaysRun = true)
    public void setupArrayPage() {
        helperClass = new HelperClass();
        arrayPage = new ArrayPage(DriverFactory.getDriver());
        log.info("Entered the Array testcases");
    }

    @Test(priority=1,groups= {"AP"})
    public void arrayLandingPage()
    {
        helperClass.arrayPageLanding();
        Assert.assertEquals(arrayPage.getTitleforArrayPage(),"Array");
    }

    @Test(priority=2,dataProvider = "arrayTopics",dataProviderClass = TestdataProvider.class,groups= {"AP"})
    public void arrayTopicPage(String arrayTopics)
    {
        helperClass.arrayPageLanding();
        arrayPage.clickArrayTopicLink(arrayTopics);
        Assert.assertEquals(arrayPage.validateTitle(arrayTopics),arrayTopics);
        log.info("Landed in " + arrayTopics + " page");
    }

    @Test(priority=3,dataProvider = "arrayTopics",dataProviderClass = TestdataProvider.class,groups= {"AP"})
    public void arrayTryEditorPage(String arrayTopics)
    {
        helperClass.arrayPageLanding();
        arrayPage.clickArrayTopicLink(arrayTopics);
        arrayPage.clickTryHereBtn();
        Assert.assertTrue(arrayPage.runBtnVisible(),"Run button not visible");
    }

    @Test(priority=4,dataProvider = "arrayTopics",dataProviderClass = TestdataProvider.class,groups= {"AP"})
    public void arrayValidCode(String arrayTopics)
    {
        helperClass.arrayPageLanding();
        arrayPage.clickArrayTopicLink(arrayTopics);
        arrayPage.clickTryHereBtn();
        arrayPage.enterPythonCode(arrayPage.getPythonCodeDataDriven());
        log.info("Clicking on Run button");
        arrayPage.clickRunBtn();
        String actualOutput = arrayPage.getOutputFromConsole();
        log.info("Output printed in the console : "+actualOutput);
        String expectedOutput = arrayPage.getOutputDataDriven();
        log.info("Expected Output : "+expectedOutput);
        Assert.assertEquals(actualOutput,expectedOutput);
        log.info("Actual Output matched with the Expected Output");
    }

    @Test(priority=5,dataProvider = "arrayTopics",dataProviderClass = TestdataProvider.class,groups= {"AP"})
    public void arrayInvalidCode(String arrayTopics)
    {
        helperClass.arrayPageLanding();
        arrayPage.clickArrayTopicLink(arrayTopics);
        arrayPage.clickTryHereBtn();
        log.info("Entering invalid python code in the editor : "+arrayPage.getInvalidCodeDataDriven());
        arrayPage.enterPythonCode(arrayPage.getInvalidCodeDataDriven());
        log.info("Clicking on Run button");
        arrayPage.clickRunBtn();
        Assert.assertEquals(arrayPage.getAlertPopup(),arrayPage.getInvalidCodeOutput());
    }

    @Test(priority=6,dataProvider = "arrayTopics",dataProviderClass = TestdataProvider.class,groups= {"AP"})
    public void arrayPracticeQuestionsPage(String arrayTopics)
    {
        helperClass.arrayPageLanding();
        arrayPage.clickArrayTopicLink(arrayTopics);
        log.info("Clicking Practice Questions hyperlink");
        arrayPage.clickPracticeQuestions();
        log.info("Checking for Practice Questions page");
        Assert.assertEquals(arrayPage.practiceQuestionsPageVisibility(),"Search the array");
    }

    @Test(priority=7,dataProvider = "arrayPracticeQuestions",dataProviderClass = TestdataProvider.class,groups= {"AP"})
    public void arrayPracticeQuestionsPageOptions(String arrayTopics,String practiceQuestionsTopic)
    {
        helperClass.arrayPageLanding();
        arrayPage.clickArrayTopicLink(arrayTopics);
        log.info("Clicking Practice Questions hyperlink");
        arrayPage.clickPracticeQuestions();
        log.info("Checking for Practice Questions page");
        arrayPage.clickPracticeQuestionsArrayTopicLink(practiceQuestionsTopic);
        log.info("Checking the Run button is available");
        Assert.assertTrue(arrayPage.runBtnVisible(),"Run button not visible");
    }

    @Test(priority=8,dataProvider = "arrayPracticeQuestions",dataProviderClass = TestdataProvider.class,groups= {"AP"})
    public void arrayPracticeQuestionsValidCode(String arrayTopics,String practiceQuestionsTopic)
    {
        helperClass.arrayPageLanding();
        arrayPage.clickArrayTopicLink(arrayTopics);
        log.info("Clicking Practice Questions hyperlink");
        arrayPage.clickPracticeQuestions();
        log.info("Checking for Practice Questions page");
        arrayPage.clickPracticeQuestionsArrayTopicLink(practiceQuestionsTopic);
        log.info("Entering valid python code in the editor : "+arrayPage.getPythonCodeDataDriven());
        arrayPage.enterPythonCode(arrayPage.getPythonCodeDataDriven());
        log.info("Clicking on Run button");
        arrayPage.clickRunBtn();
        String actualOutput = arrayPage.getOutputFromConsole();
        log.info("Output printed in the console : "+actualOutput);
        String expectedOutput = arrayPage.getOutputDataDriven();
        log.info("Expected Output : "+expectedOutput);
        Assert.assertEquals(actualOutput,expectedOutput);
        log.info("Actual Output matched with the Expected Output");
    }

    @Test(priority=9,dataProvider = "arrayPracticeQuestions",dataProviderClass = TestdataProvider.class,groups= {"AP"})
    public void arrayPracticeQuestionsInvalidCode(String arrayTopics,String practiceQuestionsTopic)
    {
        helperClass.arrayPageLanding();
        arrayPage.clickArrayTopicLink(arrayTopics);
        log.info("Clicking Practice Questions hyperlink");
        arrayPage.clickPracticeQuestions();
        log.info("Checking for Practice Questions page");
        arrayPage.clickPracticeQuestionsArrayTopicLink(practiceQuestionsTopic);
        log.info("Entering invalid python code in the editor : "+arrayPage.getInvalidCodeDataDriven());
        arrayPage.enterPythonCode(arrayPage.getInvalidCodeDataDriven());
        log.info("Clicking on Run button");
        arrayPage.clickRunBtn();
        Assert.assertEquals(arrayPage.getAlertPopup(),arrayPage.getInvalidCodeOutput());
    }
}
