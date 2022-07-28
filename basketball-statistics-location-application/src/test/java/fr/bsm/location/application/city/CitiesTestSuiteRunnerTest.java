package fr.bsm.location.application.city;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
        "pretty",
        "html:target/cucumber"
    },
    glue = {
        "fr.bsm.location.application.city",
        "cucumber.api.spring",
        "cucumber.runtime.java.spring"
    }
)
@CucumberContextConfiguration
@ContextConfiguration
public class CitiesTestSuiteRunnerTest {

}