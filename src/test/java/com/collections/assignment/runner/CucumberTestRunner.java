package com.collections.assignment.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features =  {"src/test/resources/features"},
        glue = {"com.collections.assignment.steps"},
        plugin = {"pretty",
                "json:target/cucumber/cucumber-report.json",
                "junit:target/cucumber/cucumber-results.xml"},
        stepNotifications = true,
        tags = "not @Ignore"
)
public class CucumberTestRunner {
}
