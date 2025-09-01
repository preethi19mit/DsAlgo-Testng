package nn.dsalgo.pages;


import nn.dsalgo.utilities.BaseLogger;
import nn.dsalgo.utilities.ConfigReader;
import nn.dsalgo.utilities.ElementsUtil;
import nn.dsalgo.utilities.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Map;

public class LoginPage extends BaseLogger {

    private WebDriver driver;
    private ElementsUtil elementsUtil;

    //1. By Locators :
    private By SignInLink = By.cssSelector("a[href='/login']");
    private By Username = By.xpath("//input[@name='username']");
    private By Password = By.xpath("//input[@name='password']");
    private By LoginBtn = By.cssSelector("input[value='Login']");
    private By UsrAlert = By.id("id_username");
    private By PwdAlert = By.id("id_password");
    private By Alertmsg = By.xpath("//div[@role='alert']");
    private By GetStartedBtn = By.cssSelector(".btn");
    private By NameCheckAfterLogin = By.xpath("//a[contains(text(),'Prasanna')]");
    

    String url = ConfigReader.getProperty("baseurl");
    
    //2. Constructor of the page class :
    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
        this.elementsUtil = new ElementsUtil(driver);
    }
    public void Loginurl()
    {
    	driver.get(url);
    }
    
    //3. page actions
    
       
    public String Loginvalidatetitle()
    {
    	
    	elementsUtil.doClick(SignInLink);
    	String titlelogin = driver.getTitle();
    	return titlelogin;
    	
    }
    public void navigatetologin()
    {
    	driver.get(url);
    	driver.findElement(GetStartedBtn).click();
    	elementsUtil.doClick(SignInLink);
    }

    public void Login()
    {
    	driver.get(url);
    	driver.findElement(GetStartedBtn).click();
    	elementsUtil.doClick(SignInLink);
    	performLoginDataDriven();
    }
    
    public String getErrorMessage()
    {
    	String errormsg = driver.findElement(Alertmsg).getText();
    	return errormsg;
    }
    
    public boolean Loggedinvalidate()
    {
    	return driver.findElement(NameCheckAfterLogin).isDisplayed();
    	 
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

   public String Missingusername()
   {
    	Map<String, String> loginData = ExcelReader.getRowByTestCaseId("Login","Missing username");
    	
       String Excelusername = loginData.get("username");
       String Excelpassword = loginData.get("password");
       String Errormsg = loginData.get("message");
       
       if (Excelusername != null && !Excelusername.trim().isEmpty()) {
           driver.findElement(Username).sendKeys(Excelusername);
       }

       driver.findElement(Password).sendKeys(Excelpassword);
       
    	driver.findElement(LoginBtn).click();
    	return Errormsg;
    	
   }
      
    public String Usernamealert()
    {
		
		  WebElement Usrfield = driver.findElement(UsrAlert);  
		  
		  Boolean Uservalid = (Boolean) ((JavascriptExecutor) driver)
		  .executeScript("return arguments[0].checkValidity();", Usrfield);
		  
		  String validationMessage = (String)((JavascriptExecutor) driver)
		   .executeScript("return arguments[0].validationMessage;", Usrfield);
		   
		   System.out.println("Username valid? " + Uservalid + " | Message: " + validationMessage);
		   return validationMessage;
    	
    }
    
    public String Missingpassword()
    {
     	Map<String, String> loginData = ExcelReader.getRowByTestCaseId("Login","Missing password");
     	
        String Excelusername = loginData.get("username");
        String Excelpassword = loginData.get("password");
        String Errormsg = loginData.get("message");
        
        driver.findElement(Username).sendKeys(Excelusername);
        
        if (Excelpassword != null && !Excelpassword.trim().isEmpty()) {
            driver.findElement(Password).sendKeys(Excelpassword);
        }
        
     	driver.findElement(LoginBtn).click();
     	return Errormsg;
     	
    }
    
    
    public String Passwordalert()
    {
    	WebElement Pwdfield = driver.findElement(PwdAlert);
    	Boolean Passvalid = (Boolean) ((JavascriptExecutor) driver)
				  .executeScript("return arguments[0].checkValidity();", Pwdfield);
				  
		String validationMessage1 = (String)((JavascriptExecutor) driver)
				   .executeScript("return arguments[0].validationMessage;", Pwdfield);
		System.out.println("Password valid? " + Passvalid + " | Message: " + validationMessage1);
        return validationMessage1;
    }
    
    public String Invalidusername()
    {
    	    	
        Map<String, String> loginData = ExcelReader.getRowByTestCaseId("Login","Invalid username");
     	
        String Excelusername = loginData.get("username");
        String Excelpassword = loginData.get("password");
        String Errormsg = loginData.get("message");
        
        driver.findElement(Username).sendKeys(Excelusername);
       
        driver.findElement(Password).sendKeys(Excelpassword);
        
     	driver.findElement(LoginBtn).click();
     	
     	return elementsUtil.doGetText(Alertmsg);
     
     	
    }
    
    public String Invalidpassword()
    {
    	    	
        Map<String, String> loginData = ExcelReader.getRowByTestCaseId("Login","Invalid password");
     	
        String Excelusername = loginData.get("username");
        String Excelpassword = loginData.get("password");
        String Errormsg = loginData.get("message");
        
        driver.findElement(Username).sendKeys(Excelusername);
       
        driver.findElement(Password).sendKeys(Excelpassword);
        
     	driver.findElement(LoginBtn).click();
     	
     	elementsUtil.doGetText(Alertmsg);
     	return Errormsg;
     	
    }

}
