package org.runnerclass;

import org.junit.AfterClass;

import org.junit.runner.RunWith;
import org.jvm.report.generate.RepoJvm;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resources", glue="org.stepdefinitions", 
                 monochrome=true, plugin = {"html:HtmlReport", "junit:JReport\\jrep.xml","json:JsReport\\JsRepo.json"})
public class RunnerClass {
	
	@AfterClass
	public static void reportGen() {

		RepoJvm.repoGeneration("C:\\Users\\VISHAL\\eclipse-workspace\\Fitpeo\\JsReport\\JsRepo.json");
	}

}
