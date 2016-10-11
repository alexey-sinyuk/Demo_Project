package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.AdminPage;
import pages.LoginPage;
import pages.MainPage;
import pages.MyAccountPage;
import test.BaseTestBDD;

import java.io.IOException;

public class AdminPageSteps extends BaseTestBDD {

    public LoginPage loginPage;
    public AdminPage adminPage;


    public AdminPageSteps() throws IOException {
        super();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        adminPage = PageFactory.initElements(driver, AdminPage.class);
    }

    @Then("^I got on Dashboard as a admin user$")
    public void iGotOnMainpageAsAAdminUser(){
        Assert.assertTrue(adminPage.getTitle().contains("Dashboard"),"Login made as administrator user.");
    }

    @And("^I do logout from Dashboard$")
    public void iDoLogoutFromDashboard(){
        adminPage.ClickLogoutLink();
        Assert.assertTrue(loginPage.getTitle().contains("Log In"),"Login page opened.");
        Assert.assertTrue(loginPage.loginMessage.getText().contains("You are now logged out"),"User logged out.");
    }
}
