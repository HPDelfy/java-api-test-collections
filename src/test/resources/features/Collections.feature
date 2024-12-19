Feature: Collections test scenarios

  Scenario: SC1 Validate the collections count between languages
    Given the list of collections for 'nl'
    And the list of collections for 'en'
    Then Collections count should match for both 'nl' and 'en' languages

  Scenario Outline: SC2-TC<TC> Retrieve collections without key for <language>
    Given retrieve the list of collections for '<language>' without key
    Then the collections response status code should be 401

    Examples:
      | TC | language |
      | T1 | nl       |
      | T2 | en       |

  Scenario Outline: SC3-TC<TC> Validate the <language> collection detail api link in collections
    Given the list of collections for '<language>'
    Then All the Collection detail links should be available for '<language>'

    Examples:
      | TC | language |
      | T1 | nl       |
      | T2 | en       |