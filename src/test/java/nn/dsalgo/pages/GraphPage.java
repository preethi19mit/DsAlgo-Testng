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

public class GraphPage extends BaseLogger {
	
	    private WebDriver driver;
	    private ElementsUtil elementsutil;
	    
	    private By Tryhere = By.xpath("//a[text()='Try here>>>']");
	    private By Run = By.xpath("//button[@type='button']");
	    private By tryEditor_text = By.cssSelector(".CodeMirror div.CodeMirror-code");
	    private By GraphLink = By.cssSelector("a[href='graph']");
	    private By GraphRepLink = By.cssSelector("a[href='graph-representations']");
	    private By pq_brokenLink = By.cssSelector(".list-group-item.list-group-item-light.text-info");

	    public GraphPage (WebDriver driver) 
	    {
	        this.driver = driver;
	        this.elementsutil = new ElementsUtil(driver);
	    }
	    
	    public void selectGraphOption(String option) {
	        switch (option) {
	            case "Graph":
	                elementsutil.doClick(GraphLink);
	                break;

	            case "Graph Representations":
	                elementsutil.doClick(GraphRepLink);
	                break;

	            default:
	                throw new IllegalArgumentException("Invalid option: " + option);
	        }
	    }
	    
	        
	    
	    public void Tryhere()
	    {
	    	
	    	driver.findElement(Tryhere).click();
	    }
	      
	    public void enterPythonCode(String input)
	    {
	        WebElement editor = driver.findElement(tryEditor_text);
	        Actions actions = new Actions(driver);
	        actions.moveToElement(editor).click().sendKeys(input).perform();
	    }
	    public String getPythonCodeDataDriven()
	    {
	        Map<String, String> getCode = ExcelReader.getRowByTestCaseId("Graph","ValidCode");
	        String codeToInput = getCode.get("Python Code");
	        return codeToInput;
	    }
	    public String getInvalidCodeDataDriven()
	    {
	        Map<String, String> getCode = ExcelReader.getRowByTestCaseId("Graph","InvalidCode");
	        String codeToInput = getCode.get("Python Code");
	        return codeToInput;
	    }
	    
	    public void Run()
	    {
	    	
	    	driver.findElement(Run).click();
	    	Handlealert();
	        driver.navigate().back();
	    }
	    
	    public void Handlealert()
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
	    } 
	    
	    public void clickOnPQLink()
	    {
	        driver.findElement(pq_brokenLink).click();
	        String pageSource = driver.getPageSource();
	        if (pageSource.trim().isEmpty() || pageSource.contains("404") || pageSource.contains("Not Found")) {
	            log.warn("❌ Broken Link Navigated to Empty/404 Page");
	        } else {
	            log.info("✅ Practice Questions Link Working Fine");
	        }
	    }
	    public void emptyPage()
	    {
	        WebElement container = driver.findElement(By.cssSelector("div.container"));
	        if (container.getText().trim().isEmpty()) {
	            log.warn("⚠️ The container is empty → no practice content found");
	        } else {
	            log.info("✅ Container has content: " + container.getText());
	        }
            driver.navigate().back();
	    }

}
