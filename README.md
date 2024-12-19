## Documentation
####  Collections api E2E Tests
Documentation of collection api endpoints
https://data.rijksmuseum.nl/docs/api/

Test coverage
* Data types of the collections are verified as part of the models
* Validate total counts of the arts between different languages
* RestAPI call to connections endpoint without key
* Verify collections detail endpoint returned in the collections response

Observation
* Page query parameters are not working as expected
* Collections detail link in the api documentation is incorrect

#### Frameworks used
* Cucumber Junit
* Spring boot

### How to run the test

#### Requirements to run test using maven/cucumber test runner
* jdk 17
* maven 3.8.8(Any maven version compatible to java version installed)

#### Prerequisite
API key belongs to rijksmuseum account should be filled in the config file or set as env variable

````
java-api-test-collections/src/test/resources/application.properties
````

#### Run via mvn

run `mvn verify -P e2e` at root directory

#### Run via Cucumber Test Runner

run `mvn clean install -DskipTests` at root directory

run CucumberTestRunner class
``
/java-api-test-collections/src/test/java/com/collections/assignment/runner/CucumberTestRunner.java
``

#### Requirements to run test using docker
* docker

#### Run via docker

run `_ci/run.sh` at root directory

Report Outputs in
``
/java-api-test-collections/target/cucumber/cucumber-html-reports/overview-features.html
``

