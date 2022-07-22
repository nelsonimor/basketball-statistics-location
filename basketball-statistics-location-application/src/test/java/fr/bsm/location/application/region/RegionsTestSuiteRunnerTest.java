package fr.bsm.location.application.region;

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
        "fr.basketball.statistics.location.application.region",
        "cucumber.api.spring",
        "cucumber.runtime.java.spring"
    }
)
@CucumberContextConfiguration
@ContextConfiguration
public class RegionsTestSuiteRunnerTest {

}