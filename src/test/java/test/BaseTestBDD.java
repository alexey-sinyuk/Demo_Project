package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import pages.LoginPage;
import pages.MainPage;
import ru.stqa.selenium.factory.WebDriverFactory;
import ru.stqa.selenium.factory.WebDriverFactoryMode;

import util.EventListener;
import util.PropertyLoader;

public class BaseTestBDD {

    protected static String gridHubUrl;
    protected static String baseUrl;
    protected static Capabilities capabilities;

    protected static WebDriver driver;

    public BaseTestBDD (){
        try {
            initTestSuite();
            initWebDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initTestSuite() throws IOException {
        baseUrl = PropertyLoader.loadProperty("site.url");
        gridHubUrl = PropertyLoader.loadProperty("grid.url");
        if ("".equals(gridHubUrl)) {
            gridHubUrl = null;
        }

    }

    public void initWebDriver() throws IOException {
        if (driver == null){
            capabilities = PropertyLoader.loadCapabilities();
            System.out.println("baseulr = " + baseUrl);
            System.out.println("gridHubUrl = " + gridHubUrl);
            System.out.println("capabilities = " + capabilities);
            WebDriverFactory.setMode(WebDriverFactoryMode.THREADLOCAL_SINGLETON);
            driver = WebDriverFactory.getDriver(gridHubUrl, capabilities);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }
}
