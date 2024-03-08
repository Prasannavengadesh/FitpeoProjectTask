package org.stepdefinitions;

import org.base.LibGlobal;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class HooksClass extends LibGlobal {
	
	@Before
	public void preRequest() {

		browserLaunch("Edge");
	}
	
	
	@After
	private void postRequest() {

		quitBrowser();
	}
	

}
