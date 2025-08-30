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

public class QueuePage extends BaseLogger {
	

    private WebDriver driver;
    private ElementsUtil elementsutil;
    
    private By getTitle_Queue = By.xpath("//div/h4[text()='Queue']");
    private By Tryhere = By.cssSelector("a[href='/tryEditor']");
    private By Run = By.xpath("//button[@type='button']");
    private By output = By.xpath("//pre[@id='output']");
    private By ImplementofQueue = By.cssSelector("a[href='implementation-lists']");
    private By ImplementCollections = By.cssSelector("a[href='implementation-collections']");
    private By ImplementArray = By.cssSelector("a[href='Implementation-array']");
    private By QueueOp = By.cssSelector("a[href='QueueOp']");
    private By tryEditor_text = By.cssSelector(".CodeMirror div.CodeMirror-code");
    private By pq_brokenLink = By.cssSelector(".list-group-item.list-group-item-light.text-info");
    private By tryEditor = By.xpath("//div[@class='input']");
    private By outputConsole = By.xpath("//*[@id='output']");
 
    
    public QueuePage (WebDriver driver) 
    {
        this.driver = driver;
        this.elementsutil = new ElementsUtil(driver);
    }

    public String getTitleforQueue()
    {
       return driver.findElement(getTitle_Queue).getText();
    }

    
    public void QueueTopics(String option) {
    	System.out.println("Received option: " + option);
    	if(option.equalsIgnoreCase("Implementation of Queue in python"))
    	{
    		elementsutil.doClick(ImplementofQueue);
    	}
    	else if (option.equalsIgnoreCase("Implementation using collections deque")) {
        
        elementsutil.doClick(ImplementCollections);
        } 
        else if (option.equalsIgnoreCase("Implementation using Array")) {
        	
        elementsutil.doClick(ImplementArray);
        } 
        else if (option.equalsIgnoreCase("Queue operations")) {
        	
        	elementsutil.doClick(QueueOp);
        } 
        else {
            throw new IllegalArgumentException("Unknown option: " + option);
        }
    
    }
    
    
    public void Tryhere()
    {
    	elementsutil.doClick(Tryhere);
    	
    	
    }
    public boolean tryEditorVisible() {
        try {
            return elementsutil.isElementDisplayed(tryEditor);
        } catch (NoSuchElementException e) {
            return false; 
        }
    }
    public String getOutputFromConsole()
    {
       
      return driver.findElement(outputConsole).getText();
    }
          
    public void enterPythonCode(String input)
    {
        WebElement editor = driver.findElement(tryEditor_text);
        Actions actions = new Actions(driver);
        actions.moveToElement(editor).click().sendKeys(input).perform();
    }
	
    
    public String getOutputDataDriven()
    {
        Map<String, String> getOutput = ExcelReader.getRowByTestCaseId("Queue","ValidCode");
        String output = getOutput.get("Expected Output");
        return output;
    }

    
    public void Run()
    {
    	
    	driver.findElement(Run).click();
     }
    
    public boolean Handlealert()
    {
    	try {
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    	    wait.until(ExpectedConditions.alertIsPresent());

    	    Alert alert = driver.switchTo().alert();
    	    System.out.println("Alert text: " + alert.getText());
    	    alert.accept(); 

    	} catch (TimeoutException e) {
    	    System.out.println("No alert appeared.");
    	}
    	return true;
    } 
    
    public String getPythonCodeDataDriven(String sheetName, String inputID)
    {
        Map<String, String> getCode = ExcelReader.getRowByTestCaseId(sheetName,inputID);
        String codeToInput = getCode.get("Python Code");
        log.info("The code to input is "+codeToInput+" for the scenario "+inputID);
        return codeToInput;
    }

       
    public void PracticeQuestionLink()
    {
        driver.findElement(pq_brokenLink).click();
        String pageSource = driver.getPageSource();
        if (pageSource.trim().isEmpty() || pageSource.contains("404") || pageSource.contains("Not Found")) {
            log.warn("❌ Broken Link Navigated to Empty/404 Page");
        } else {
            log.info("✅ Practice Questions Link Working Fine");
        }
    }
    public boolean emptyPage()
    {
        WebElement container = driver.findElement(By.cssSelector("div.container"));
        if (container.getText().trim().isEmpty()) {
            log.warn("⚠️ The container is empty → no practice content found");
            return true;
        } else {
            log.info("✅ Container has content: " + container.getText());
            return false;
        }
       
    }
    
}
