package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.AdminUserCreation;
import pages.AdminUserDeletion;
import pages.AdminUserManagement;
import test.BaseTestBDD;

import java.io.IOException;

public class AdminUserManagementSteps extends BaseTestBDD {

    public AdminUserCreation adminUserCreation;
    public AdminUserManagement adminUserManagement;
    public AdminUserDeletion adminUserDeletion;

    public AdminUserManagementSteps() throws IOException {
        super();
        adminUserCreation = PageFactory.initElements(driver, AdminUserCreation.class);
        adminUserManagement = PageFactory.initElements(driver, AdminUserManagement.class);
        adminUserDeletion = PageFactory.initElements(driver, AdminUserDeletion.class);
    }

    @And("^I create User with \"([^\"]*)\" role$")
    public void iCreateUserWithRole(String role) throws Throwable {

        adminUserCreation.createUser(dateStamp + "_" + role,
                dateStamp + "_" + role + "@test.com",
                dateStamp,
                role,
                "www." + role + ".com",
                role,
                true,
                role);
    }

    @Then("^I check that user with \"([^\"]*)\" role is created$")
    public void iCheckThatUserWithRoleIsCreated(String role){
        WebElement username;
        adminUserManagement.doSearch(dateStamp + "_" + role);
        username = adminUserManagement.userList.get(0).findElement(By.xpath(".//td[@data-colname='Username']/strong/a"));
        Assert.assertTrue(username.getText().contains(dateStamp + "_" + role), "Verify user " + dateStamp + "_" + role + " created");
    }

    @And("^I do search for created users$")
    public void iDoSearchForCreatedUsers(){
        adminUserManagement.doSearch(dateStamp);
        Assert.assertTrue(adminUserManagement.userList.size() != 0);
    }

    @And("^I check all$")
    public void iCheckAll(){
        adminUserManagement.selectAllCheckbox.click();
    }

    @And("^I choose Delete action$")
    public void iChooseDeleteAction(){
        adminUserManagement.selectBulkAction("delete");
    }

    @And("^I click Apply$")
    public void iClickApply(){
        adminUserManagement.submitActionButton.click();
    }

    @Then("^I got on deletion confirmation page$")
    public void iGotOnDeletionConfirmationPage(){
        Assert.assertTrue(adminUserDeletion.usersToDelete.getText().contains(dateStamp), "Right users specified for deletion.");
    }

    @And("^I confirm deletion$")
    public void iConfirmDeletion(){
        adminUserDeletion.submitDeletion();
    }

    @And("^I check users were deleted$")
    public void iCheckUsersWereDeleted(){
        Assert.assertTrue(adminUserManagement.message.getText().contains("users deleted."), "\"User deleted.\" message appeared");
        adminUserManagement.doSearch(dateStamp);
        Assert.assertTrue(adminUserManagement.searchMessage.getText().contains("No users found."), "Deleted users not found.");
    }
}
