@all
Feature: Main page common tests

  Background:
    Given I open Mainpage
    And I click on login link
    And I got on login page
    And I login as "viewer_as" with password "P@ssw0rd"

  @sanity
  Scenario Outline: Check accessibility of each tab of Main page
    When I click on "<tab>" tab
    Then I got on "<page>" page
    And I do logout from Mainpage

    Examples:
    |tab          |page       |
    |Home         |Simple shop|
    |Cart         |Cart       |
    |Checkout     |Checkout   |
    |My account   |My account |
    |News         |News       |
    |Our products |Products   |