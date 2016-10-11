package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.AdminPage;
import pages.LoginPage;
import pages.MainPage;
import pages.MyAccountPage;
import test.BaseTestBDD;

import java.io.IOException;

public class LoginPageSteps extends BaseTestBDD {

    public LoginPage loginPage;
    public MyAccountPage myAccountPage;

    public LoginPageSteps() throws IOException {
        super();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    }

    @When("^I got on login page$")
    public void iGotOnLoginPage() {
        Assert.assertTrue(loginPage.getTitle().contains("Log In"),"Login page opened.");
    }

    @And("^I login as \"([^\"]*)\" with password \"([^\"]*)\"$")
    public void iLoginAsWithPassword(String username, String password){
        loginPage.LoginAs(username, password);
    }

    @Then("^I got login error message \"([^\"]*)\"$")
    public void iGotLoginErrorMessage(String errorMsg){
        Assert.assertTrue(loginPage.loginErrorMessage.getText().contains(errorMsg),
                "Error message appeared");
    }

    @And("^I click Lost password and got redirected to Lost Password page$")
    public void iClickLostPasswordAndGotRedirectedToLostPasswordPage() throws Throwable {
        loginPage.LostPasswordClick();
        Assert.assertTrue(myAccountPage.entryTitle.getText().contains("Lost Password"), "User got redirected on Lost Password page.");
    }

}
