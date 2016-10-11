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

public class MyAccountPageSteps extends BaseTestBDD {

    public MyAccountPage myAccountPage;

    public MyAccountPageSteps() throws IOException {
        super();
        myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    }


    @And("^I submit username \"([^\"]*)\"$")
    public void iSubmitUsername(String username){
        myAccountPage.inputUsernameEmail.sendKeys(username);
        myAccountPage.buttonResetPassword.click();
    }

    @Then("^I got message \"([^\"]*)\"$")
    public void iGotMessage(String msg){
        Assert.assertTrue(myAccountPage.validMessage.getText().contains(msg), "User got valid message.");
    }

    @Then("^I got error message \"([^\"]*)\"$")
    public void iGotErrorMessage(String errorMsg){
        Assert.assertTrue(myAccountPage.loginErrorMessage.getText().contains(errorMsg), "User got valid error message.");
    }
}
