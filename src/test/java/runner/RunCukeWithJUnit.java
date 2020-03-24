package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(features = {"src/test/resources/features"}, glue = {"src/test/java/acceptance/steps"})
@RunWith(Cucumber.class)
public class RunCukeWithJUnit {

}
