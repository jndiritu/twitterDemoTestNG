/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import conf.appConstant;
import java.util.List;
import junit.framework.Assert;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author mutts
 */
public class TestNGTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public int time = 2000;

    public TestNGTest() {
    }

    @org.testng.annotations.BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("Setting up the chrome driver...");
        System.setProperty("webdriver.chrome.driver", appConstant.chromeDriverPath);

    }

    @org.testng.annotations.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.testng.annotations.BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("Initializing the browser...");
        driver = new ChromeDriver();
        System.out.println("Setting the browser to full screen...");
        driver.manage().window().fullscreen();
        wait = new WebDriverWait(driver, appConstant.WAIT_TIME);
    }

    @org.testng.annotations.AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("Preparing to log out...");
        driver.findElement(By.xpath("//*[@id=\"user-dropdown\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"signout-button\"]/button")));
        driver.findElement(By.xpath("//*[@id=\"signout-button\"]/button")).click();
        System.out.println("Logged out successfully...");
        System.out.println("Closing the web driver...");
        driver.close();
        driver.quit();
    }

    @Test (priority=1)
    public void logInOptionWithOutCheckBox() throws InterruptedException {
        System.out.println("Getting the url to use on the browser: " + appConstant.url);
        driver.get(appConstant.url);
        System.out.println("Confirming if we are at the login page");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Log in")));
        List<WebElement> loginButton = driver.findElements(By.linkText("Log in"));
        if (loginButton.size() > 0 && loginButton.get(0).isDisplayed()) {
            System.out.println("We are at the login page since the login button is displayed");
            System.out.println("Entering username...");
            driver.findElement(By.name("session[username_or_email]")).clear();
            driver.findElement(By.name("session[username_or_email]")).sendKeys(appConstant.USERNAME);
            System.out.println("Entering password...");
            Thread.sleep(1000);
            driver.findElement(By.name("session[password]")).clear();
            Thread.sleep(2000);
            driver.findElement(By.name("session[password]")).sendKeys(appConstant.PASSWORD);
            Thread.sleep(2000);
            System.out.println("Clicking on the login button to login");
            driver.findElement(By.xpath("//*[@id=\"doc\"]/div/div[1]/div[1]/div[1]/form/input[1]")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Advertise with Twitter")));
            assertEquals("Advertise with Twitter", driver.findElement(By.partialLinkText("Advertise")).getText());
            System.out.println("Successfully logged in...");

        } else {
            System.out.println("We are not at the login page...");
        }
    }
    
    @Test (priority=1)
    public void logInOptionWithCheckBox() throws InterruptedException {
        driver.get(appConstant.url);
        Thread.sleep(time);
        List<WebElement> bt = driver.findElements(By.linkText("Log in"));

        if (bt.size() > 0 && bt.get(0).isDisplayed()) {
            System.out.println("Login button is displayed so we at the login page");
            System.out.println("clicking the logIn button so that one can input credentials...");
            driver.findElement(By.xpath("//*[@id=\"doc\"]/div/div[1]/div[1]/div[2]/div[2]/div/a[2]")).click();

            System.out.println("logIn button clicked, sleeping for: " + time + "ms");
            Thread.sleep(time);
            System.out.println("Enter username");

            driver.findElement(By.xpath("//*[@id=\"page-container\"]/div/div[1]/form/fieldset/div[1]/input")).clear();
            driver.findElement(By.xpath("//*[@id=\"page-container\"]/div/div[1]/form/fieldset/div[1]/input")).sendKeys(appConstant.USERNAME);
            Thread.sleep(time);
            System.out.println("Enter password");
            driver.findElement(By.xpath("//*[@id=\"page-container\"]/div/div[1]/form/fieldset/div[2]/input")).clear();
            driver.findElement(By.xpath("//*[@id=\"page-container\"]/div/div[1]/form/fieldset/div[2]/input")).sendKeys(appConstant.USERNAME);
            
            System.out.println("Credentials entered. Sleeping for: " + time + "ms");
            Thread.sleep(time);
            
            System.out.println("Checking if the 'Remember me' checkbox is checked...");
            if (driver.findElement(By.xpath("//*[@id=\"page-container\"]/div/div[1]/form/div[2]/div/label/input")).isSelected()) {
                System.out.println("'Remember me' checkbox is Checked. Unchecking it...");
                driver.findElement(By.xpath("//*[@id=\"page-container\"]/div/div[1]/form/div[2]/div/label/input")).click();
                System.out.println("'Remember me' Checkbox unchecked successfully...");
            } else {
                System.out.println("'Remember me' Checkbox is not checked. ...");
            }
            
            System.out.println("Clicking on the 'Log In' Button to log into the TWITTER Acc now...");
            driver.findElement(By.xpath("//*[@id=\"page-container\"]/div/div[1]/form/div[2]/button")).click();
            driver.findElement(By.xpath("//*[@id=\"doc\"]/div/div[1]/div[1]/div[1]/form/input[1]")).click();
            System.out.println("Chu chuuuuu... we finally there. PEACE...");

        } else {
            System.out.println("Login button NOT Displayed...");
        }
    }
}
