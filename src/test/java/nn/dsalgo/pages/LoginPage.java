package nn.dsalgo.pages;


import nn.dsalgo.utilities.BaseLogger;
import nn.dsalgo.utilities.ConfigReader;
import nn.dsalgo.utilities.ElementsUtil;
import nn.dsalgo.utilities.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class LoginPage extends BaseLogger {

    private WebDriver driver;
    private ElementsUtil elementsUtil;

    //1. By Locators :
    private By SignInLink = By.xpath("//a[text()='Sign in']");
    private By Username = By.xpath("//input[@name='username']");
    private By Password = By.xpath("//input[@name='password']");
    private By LoginBtn = By.cssSelector("input[value='Login']");
    private By UsrAlert = By.id("id_username");
    private By PwdAlert = By.id("id_password");
    private By InvalidAlertmsg = By.xpath("//div[@role='alert']");

    //2. Constructor of the page class :
    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
        this.elementsUtil = new ElementsUtil(driver);
    }
    
    //3. page actions
    public void enterUserName(String un)
    {
        driver.findElement(Username).sendKeys(un);
    }

    public void enterPassword(String pwd)
    {
        driver.findElement(Password).sendKeys(pwd);
    }

    public void clickSignInLink()
    {
                
        elementsUtil.doClick(SignInLink);
    }

    public void performLoginDataDriven()
    {
        log.info("Performing Login with TestData from Excel");
        Map<String, String> loginData = ExcelReader.getDefaultLogin();
        String username = loginData.get("Username");
        String password = loginData.get("Password");
        log.info ("Entering the Username : "+username);
        driver.findElement(Username).sendKeys(username);
        log.info ("Entering the Password : "+password);
        driver.findElement(Password).sendKeys(password);
        driver.findElement(LoginBtn).click();
        log.info("Clicked Login button");
    }

   public void Missingusername()
   {
    	Map<String, String> loginData = ExcelReader.getRowByTestCaseId("Login","Missing username");
    	
       String Excelusername = loginData.get("username");
       String Excelpassword = loginData.get("password");
       
       if (Excelusername != null && !Excelusername.trim().isEmpty()) {
           driver.findElement(Username).sendKeys(Excelusername);
       }

       driver.findElement(Password).sendKeys(Excelpassword);
       
    	driver.findElement(LoginBtn).click();
    	
   }
    
  
    public void Usernamealert()
    {
		
		  WebElement Usrfield = driver.findElement(UsrAlert);  
		  
		  Boolean Uservalid = (Boolean) ((JavascriptExecutor) driver)
		  .executeScript("return arguments[0].checkValidity();", Usrfield);
		  
		  String validationMessage = (String)((JavascriptExecutor) driver)
		   .executeScript("return arguments[0].validationMessage;", Usrfield);
		   
		   System.out.println("Username valid? " + Uservalid + " | Message: " + validationMessage);
		   
    	
    }
    
    public void Missingpassword()
    {
     	Map<String, String> loginData = ExcelReader.getRowByTestCaseId("Login","Missing password");
     	
        String Excelusername = loginData.get("username");
        String Excelpassword = loginData.get("password");
        
        driver.findElement(Username).sendKeys(Excelusername);
        
        if (Excelpassword != null && !Excelpassword.trim().isEmpty()) {
            driver.findElement(Password).sendKeys(Excelpassword);
        }
        
     	driver.findElement(LoginBtn).click();
     	
    }
    
    
    public void Passwordalert()
    {
    	WebElement Pwdfield = driver.findElement(PwdAlert);
    	Boolean Passvalid = (Boolean) ((JavascriptExecutor) driver)
				  .executeScript("return arguments[0].checkValidity();", Pwdfield);
				  
		String validationMessage1 = (String)((JavascriptExecutor) driver)
				   .executeScript("return arguments[0].validationMessage;", Pwdfield);
		System.out.println("Password valid? " + Passvalid + " | Message: " + validationMessage1);

    }
    
    public void Invalidusername()
    {
    	    	
        Map<String, String> loginData = ExcelReader.getRowByTestCaseId("Login","Invalid username");
     	
        String Excelusername = loginData.get("username");
        String Excelpassword = loginData.get("password");
        
        driver.findElement(Username).sendKeys(Excelusername);
       
        driver.findElement(Password).sendKeys(Excelpassword);
        
     	driver.findElement(LoginBtn).click();
     	
     	elementsUtil.doGetText(InvalidAlertmsg);
    }
    
    public void Invalidpassword()
    {
    	    	
        Map<String, String> loginData = ExcelReader.getRowByTestCaseId("Login","Invalid password");
     	
        String Excelusername = loginData.get("username");
        String Excelpassword = loginData.get("password");
        
        driver.findElement(Username).sendKeys(Excelusername);
       
        driver.findElement(Password).sendKeys(Excelpassword);
        
     	driver.findElement(LoginBtn).click();
     	
     	elementsUtil.doGetText(InvalidAlertmsg);
    }

}
