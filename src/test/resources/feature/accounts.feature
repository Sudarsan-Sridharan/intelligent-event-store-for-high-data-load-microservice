Feature: Retrieve All User Accounts
  Scenario: client makes a call to GET /account
    Given call GET on /api/restaurant2
    Then the client receives status code of 200