package PuneethChidura.SeleniumFrameworkDesign.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/PuneethChidura/SeleniumFrameworkDesign/cucumber",
		glue = "PuneethChidura.SeleniumFrameworkDesign.stepdefinations",
		monochrome = true,
		plugin = {"html:target/cucumber-html"},
		tags = "@ErrorValidation"
		)
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
