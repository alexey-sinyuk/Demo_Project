@all
Feature: Main page login flows

  Background:
    Given I open Mainpage
    And I click on login link
    And I got on login page

  @sanity
  Scenario: Login as regular user
    When I login as "viewer_as" with password "P@ssw0rd"
    Then I got on Mainpage as a regular user
    And I do logout from Mainpage

  @sanity
  Scenario: Login as admin user
    When I login as "admin_as" with password "P@ssw0rd"
    Then I got on Dashboard as a admin user
    And I do logout from Dashboard

  @negative
  Scenario: Login with invalid username
    When I login as "1111" with password "1111"
    Then I got login error message "ERROR: Invalid username."

  @negative
  Scenario: Login with invalid password
    When I login as "viewer_as" with password "1111"
    Then I got login error message "ERROR: The password you entered for the username viewer_as is incorrect."

  Scenario: Recover lost password for valid username
    When I login as "viewer_as" with password "1111"
    And I got login error message "ERROR: The password you entered for the username viewer_as is incorrect."
    And I click Lost password and got redirected to Lost Password page
    And I submit username "viewer_as"
    Then I got message "Password reset email has been sent."

  @negative
  Scenario: Recover lost password for invalid username
    When I login as "viewer_as" with password "1111"
    And I got login error message "ERROR: The password you entered for the username viewer_as is incorrect."
    And I click Lost password and got redirected to Lost Password page
    And I submit username "1111"
    Then I got error message "Invalid username or e-mail."