package e2e.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/e2e/features",
        glue = {"e2e.stepDefinitions"},
        dryRun = false,
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/jsonReports/cucumber-report.json",
                "html:target/jsonReports/cucumber.html"
        }
)
public class TestCucumberRunner extends AbstractTestNGCucumberTests {
        public TestCucumberRunner() {
        }
}