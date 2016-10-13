@all
Feature: User management tests

  Background:
    Given I open Mainpage
    And I click on login link
    And I login as "admin_as" with password "P@ssw0rd"

    @sanity
    Scenario: Check add new user page access
      When I click Add New user from Users panel on the left
      Then I got to Add New User page
      When I click User from top +New menu
      Then I got to Add New User page
      When I open All Users list from Users panel on the left
      And I click Add New user
      Then I got to Add New User page
      And I do logout from Dashboard

    Scenario Outline: Create user for each role
      When I click Add New user from Users panel on the left
      And I create User with "<role>" role
      Then I check that user with "<role>" role is created
      And I do logout from Dashboard

      Examples:
      |role         |
      |subscriber   |
      |shop_manager |
      |customer     |
      |contributor  |
      |author       |
      |editor       |
      |administrator|

    Scenario: Delete all created users
      When I open All Users list from Users panel on the left
      And I do search for created users
      And I check all
      And I choose Delete action
      And I click Apply
      Then I got on deletion confirmation page
      And I confirm deletion
      And I check users were deleted
      And I do logout from Dashboard