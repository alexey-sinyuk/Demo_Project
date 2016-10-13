package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.*;
import test.BaseTestBDD;

import java.io.IOException;

public class AdminPageSteps extends BaseTestBDD {

    public LoginPage loginPage;
    public AdminPage adminPage;
    public AdminUserCreation adminUserCreation;
    public AdminUserManagement adminUserManagement;


    public AdminPageSteps() throws IOException {
        super();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        adminPage = PageFactory.initElements(driver, AdminPage.class);
        adminUserCreation = PageFactory.initElements(driver, AdminUserCreation.class);
        adminUserManagement = PageFactory.initElements(driver, AdminUserManagement.class);
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

    @When("^I click Add New user from Users panel on the left$")
    public void iClickAddNewUserFromUsersPanelOnTheLeft(){
        adminPage.actionOnMenu("Dashboard", true);
        adminPage.addUserLeftPanel();
    }

    @Then("^I got to Add New User page$")
    public void iGotToAddNewUserWindow() {
        Assert.assertTrue (adminUserCreation.headerofTab.getText().contains("Add New User"), "User got on Add New User page.");
    }

    @When("^I click User from top \\+New menu$")
    public void iClickUserFromTopNewMenu(){
        adminPage.actionOnMenu("Dashboard", true);
        adminPage.addUserTopMenu();
    }

    @When("^I open All Users list from Users panel on the left$")
    public void iOpenAllUsersListFromUsersPanelOnTheLeft() throws Throwable {
        adminPage.actionOnMenu("Dashboard", true);
        adminPage.actionOnMenu("Users", true);
    }

    @And("^I click Add New user$")
    public void iClickAddNewUser() throws Throwable {
        adminUserManagement.addNewElement();
    }
}
