package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.AdminPage;
import pages.LoginPage;
import pages.MainPage;
import test.BaseTestBDD;

import java.io.IOException;

public class MainPageSteps extends BaseTestBDD {

    public MainPage mainPage;
    public LoginPage loginPage;
    public AdminPage adminPage;

    public MainPageSteps() throws IOException {
        super();
        mainPage = PageFactory.initElements(driver, MainPage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        adminPage = PageFactory.initElements(driver, AdminPage.class);
    }


    @Then("^I got on Mainpage as a regular user$")
    public void iGotOnMainpageAsARegularUser() {
        Assert.assertTrue(mainPage.myAccountContent.getText().contains("Hello"),"Login made as viewer user.");
    }

    @Given("^I open Mainpage$")
    public void iOpenMainpage() {
        mainPage.Open(baseUrl);
        Assert.assertTrue(mainPage.getTitle().contains("virtual-shop"),"Main page opened.");
    }

    @Given("^I click on login link$")
    public void iClickOnLoginLink() {
        mainPage.ClickLoginLink();
    }

    @When("^I got on login page$")
    public void iGotOnLoginPage() {
        Assert.assertTrue(loginPage.getTitle().contains("Log In"),"Login page opened.");
    }

    @And("^I login as \"([^\"]*)\" with password \"([^\"]*)\"$")
    public void iLoginAsWithPassword(String username, String password){
        loginPage.LoginAs(username, password);
    }

    @And("^I do logout from Mainpage$")
    public void iDoLogout(){
        mainPage.ClickLogoutLink();
        Assert.assertTrue(loginPage.getTitle().contains("Log In"),"Login page opened.");
        Assert.assertTrue(loginPage.loginMessage.getText().contains("You are now logged out"),"User logged out.");
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
