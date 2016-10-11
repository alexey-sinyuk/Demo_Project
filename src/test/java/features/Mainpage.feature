Feature: Few tests using Main page as first navigation point

  Background:
    Given I open Mainpage

  Scenario: Login as regular user
    Given I click on login link
    When I got on login page
    And I login as "viewer_as" with password "P@ssw0rd"
    Then I got on Mainpage as a regular user
    And I do logout from Mainpage

  Scenario: Login as admin user
    Given I click on login link
    When I got on login page
    And I login as "admin_as" with password "P@ssw0rd"
    Then I got on Dashboard as a admin user
    And I do logout from Dashboard