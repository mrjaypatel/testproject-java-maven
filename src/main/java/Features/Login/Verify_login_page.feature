Feature: Check Login System
#------------------------------------------------------------------------------#
  Scenario: I logged in with correct credentials
    Given Launched url "https://mrjay.in/testing/"
    When I set my Username as "admin" and Password as "admin"
    And I click [Log in]
    Then Verify Message "Login success."

  Scenario: I logged in with correct credentials
    Given Launched url "https://mrjay.in/testing/"
    When I set my Username as "wrongusername" and Password as "wrongpassword"
    And I click [Log in]
    Then Verify Message "Login fail."
