package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class WebDriverHelper {

   public static boolean WaitTillVisible (WebElement webElement, WebDriver driver){

        WebDriverWait wait = null;

        try {
            wait = new WebDriverWait(driver, Long.parseLong(PropertyLoader.loadProperty("timeToTimeout")), Long.parseLong(PropertyLoader.loadProperty("timeToSleep")));
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return (false);
        }

        WebElement element = null;
        element = wait.until(ExpectedConditions.visibilityOf(webElement));
        return (element != null);
    }

    public static void getScreenShot() throws Exception
    {
        WebDriver driver = new FirefoxDriver();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
        FileUtils.copyFile(scrFile, new File("%USERPROFILE%" + "\\IdeaProjects\\Demo_Project\\target\\surefire-reports\\screenshot.png"));
    }


    public static void MouseOver (WebElement webElement, WebDriver driver){
        //        Actions action = new Actions(driver);                     // old code, works only with FF
        //        action.moveToElement(webElement).build().perform();       // old code, works only with FF
        String javaScript = "var ourClass = arguments[0].getAttribute('class');"+
                "ourClass = ourClass + ' hover';"+
                "arguments[0].setAttribute('class', ourClass);";
        ((JavascriptExecutor)driver).executeScript(javaScript, webElement);
    }

    public static void highLight(WebElement webElement, WebDriver driver) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", webElement);
            try {
                Thread.sleep(300);
            } catch (Exception ex) { }
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", webElement);
            try {
                Thread.sleep(300);
            } catch (Exception ex) { }
//            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", webElement);
//            try {
//                Thread.sleep(300);
//            } catch (Exception ex) { }
//            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", webElement);
        }
    }

}

