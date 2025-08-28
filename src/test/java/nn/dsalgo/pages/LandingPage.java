package nn.dsalgo.pages;


import nn.dsalgo.utilities.BaseLogger;
import nn.dsalgo.utilities.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BaseLogger {

    private WebDriver driver;

    private By GetStartedBtn = By.cssSelector(".btn");
    private By Title_lp = By.cssSelector("div[class='content'] h1");
    public LandingPage(WebDriver driver)
    {
        this.driver = driver;
    }

    String url = ConfigReader.getProperty("baseurl");
    public void clickGetStartedBtn ()
    {
        log.info("Clicking the Get Started Button of Landing page");
        driver.findElement(GetStartedBtn).click();
    }

    public String getTitle()
    {
        log.info("Getting the title of the landing page");
        return driver.findElement(Title_lp).getText();
    }
    public void getURL()
    {
        log.info("Getting the URL of DSALGO");
        driver.get(url);
    }


}
