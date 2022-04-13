package Features.Login;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                ".\\src\\main\\java\\Features\\Login\\Verify_login_page.feature"
        },

        glue = "StepDefinitions",
        dryRun = false,
        plugin = { "pretty","io.testproject.sdk.internal.reporting.extensions.cucumber.CucumberReporter", "html:target\\cucumber-reports\\report.html" },
        monochrome = true)
public class runModule {}