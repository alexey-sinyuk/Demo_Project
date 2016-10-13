package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.LoginPage;
import pages.MainPage;
import test.BaseTestBDD;

import java.io.IOException;

public class MainPageSteps extends BaseTestBDD {

    public MainPage mainPage;
    public LoginPage loginPage;

    public MainPageSteps() throws IOException {
        super();
        mainPage = PageFactory.initElements(driver, MainPage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
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

    @And("^I do logout from Mainpage$")
    public void iDoLogout(){
        mainPage.ClickLogoutLink();
        Assert.assertTrue(loginPage.getTitle().contains("Log In"),"Login page opened.");
        Assert.assertTrue(loginPage.loginMessage.getText().contains("You are now logged out"),"User logged out.");
    }

    @When("^I click on \"([^\"]*)\" tab$")
    public void iClickOnTab(String tabLink){
        switch (tabLink) {
            case "Home" :
                mainPage.ClickHomeLink();
                break;
            case "Cart" :
                mainPage.ClickCartLink();
                break;
            case "Checkout" :
                mainPage.ClickCheckoutLink();
                break;
            case "My account" :
                mainPage.ClickMyAccountLink();
                break;
            case "News" :
                mainPage.ClickNewsLink();
                break;
            case "Our products" :
                mainPage.ClickOurProductsLink();
                break;
        }
    }

    @Then("^I got on \"([^\"]*)\" page$")
    public void iGotOnPage(String page){
        Assert.assertTrue(mainPage.getTitle().contains(page), page + " tab is opened");
    }
}
