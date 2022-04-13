import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {".\\src\\main\\java\\Features\\Create_Dealership_And_Add_Product.feature"},
    glue = "StepDefinitions",
    dryRun = false,
    plugin = { "pretty","io.testproject.sdk.internal.reporting.extensions.cucumber.CucumberReporter","html:target\\log\\report.html" },
    monochrome = true)

public class run {}