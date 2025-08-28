package nn.dsalgo.pages;

import nn.dsalgo.utilities.BaseLogger;
import nn.dsalgo.utilities.ConfigReader;
import nn.dsalgo.utilities.ElementsUtil;
import nn.dsalgo.utilities.ExcelReader;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;
import java.util.NoSuchElementException;

public class ArrayPage extends BaseLogger {

    ElementsUtil elementsUtil;
    private WebDriver driver;

    private By getTitle_Array=By.cssSelector("h4[class='bg-secondary text-white']");
    private By getTopics_Array=By.xpath("//div[@class='col-sm']//p[@class='bg-secondary text-white']");
    private By tryHereBtn = By.xpath("//a[@class='btn btn-info']");
    private By tryEditor = By.xpath("//div[@class='input']");
    private By runBtn = By.xpath("//button[@type='button']");
    private By tryEditor_text = By.cssSelector(".CodeMirror div.CodeMirror-code");
    private By outputConsole = By.xpath("//pre[@id='output']");
    private By practiceQuestions=By.xpath("//a[@class='list-group-item list-group-item-light text-info']");
    private By practiceQuestions_PageText=By.xpath("//a[@href='/question/1']");
    //private By runBtn = By.cssSelector("button[type='button']");

    private By getTopicLinkByText(String topic) {
        String xpath = "//a[text()='" + topic + "']";
        return By.xpath(xpath);
    }

    private By getPracticeQuestionsTopicLinkByText(String topic) {
        String xpath = "//a[text()='" + topic + "']";
        return By.xpath(xpath);
    }

    public ArrayPage(WebDriver driver) {
        this.driver = driver;
        this.elementsUtil = new ElementsUtil(driver);

    }

    public String getTitleforArrayPage() {
        log.info("Getting the title of Array Page");
        return driver.findElement(getTitle_Array).getText();
    }

    public void clickArrayTopicLink(String arrayTopics) {
        log.info("Clicking on the topic link : " + arrayTopics);
        driver.findElement(getTopicLinkByText(arrayTopics)).click();
}

    public String validateTitle(String title) {
        log.info("Title of the page : " + title);
        //By xpath = getTitleXPath(title);
        return driver.findElement(getTopics_Array).getText();
    }

    public void clickTryHereBtn() {
        log.info("Clicking on the Try Here Button");
        driver.findElement(tryHereBtn).click();
    }
    public boolean tryEditorVisible() {
        try {
            return elementsUtil.isElementDisplayed(tryEditor);
        } catch (NoSuchElementException e) {
            return false; // Element(s) not found
        }
    }
    public boolean runBtnVisible() {
        try {
            return elementsUtil.isElementDisplayed(runBtn);
        } catch (NoSuchElementException e) {
            return false; // Element(s) not found
        }
    }

    public String getPythonCodeDataDriven()
    {
        Map<String, String> getCode = ExcelReader.getRowByTestCaseId("Array","ValidCode");
        String codeToInput = getCode.get("Python Code");
        return codeToInput;
    }

    public void enterPythonCode(String input)
    {
        WebElement editor = driver.findElement(tryEditor_text);
        Actions actions = new Actions(driver);
        actions.moveToElement(editor).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).sendKeys(input).perform();
    }

    public void clickRunBtn()
    {
        driver.findElement(runBtn).click();
    }

    public String getOutputFromConsole()
    {
        return driver.findElement(outputConsole).getText();
    }

    public String getOutputDataDriven()
    {
        Map<String, String> getOutput = ExcelReader.getRowByTestCaseId("Array","ValidCode");
        String output = getOutput.get("Expected Output");
        return output;
    }

    public String getInvalidCodeDataDriven()
    {
        Map<String, String> getCode = ExcelReader.getRowByTestCaseId("Array","InvalidCode");
        String codeToInput = getCode.get("Python Code");
        return codeToInput;
    }

    public String getInvalidCodeOutput()
    {
        Map<String, String> getCode = ExcelReader.getRowByTestCaseId("Array","InvalidCode");
        String outputForInvalid = getCode.get("Expected Output");
        return outputForInvalid;
    }

    public String getAlertPopup()
    {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.alertIsPresent());

            Alert alert = driver.switchTo().alert();
            String alertMsg=alert.getText();
            log.info("Alert text: " + alert.getText());
            alert.accept();
            return alertMsg;

        } catch (TimeoutException e) {
            log.info("No alert appeared.");
            return "";
        }
    }

    public void clickPracticeQuestions()
    {
        driver.findElement(practiceQuestions).click();
    }

    public String practiceQuestionsPageVisibility()
    {
        return driver.findElement(practiceQuestions_PageText).getText();
    }

    public void clickPracticeQuestionsArrayTopicLink(String practiceQuestionsTopics) {
        log.info("Clicking on the Practice Questions topic link : " + practiceQuestionsTopics);
        driver.findElement(getPracticeQuestionsTopicLinkByText(practiceQuestionsTopics)).click();
    }

}