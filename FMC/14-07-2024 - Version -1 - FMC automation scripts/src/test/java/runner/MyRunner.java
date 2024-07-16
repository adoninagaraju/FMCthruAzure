package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
       (
		features="src/test/resources/features",
		glue={"stepdefinitions","hooks"},
		tags="@Loginfunctionality", 

		//	  publish=true, 
		//    publish = true - it is used for report publishing into cloud
		plugin= {"pretty","html:target/htmlReports/CucumberReport.html",
//				"pretty","json:target/JSONReports/report.json",
//				"pretty","junit:target/JunitReports/report.junit"
		        }
		)

public class MyRunner
{


}


/*public class TestRunner
{

}*/