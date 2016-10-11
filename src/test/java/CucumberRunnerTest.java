import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, features = "src/test/java/features",
        format = {"json:target/cucumber_reports/cucumber.json", "pretty", "html:target/cucumber_reports"},
        tags = "@all")

public class CucumberRunnerTest {
}