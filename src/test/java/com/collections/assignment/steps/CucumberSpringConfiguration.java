package com.collections.assignment.steps;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import com.collections.assignment.configs.SpringTestConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = {SpringTestConfiguration.class})
public class CucumberSpringConfiguration {
}
