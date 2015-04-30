package parser.core;

import static parser.core.DriverMaster.dismissDriver;
import static parser.core.DriverMaster.getDriver;
import static parser.core.config.Configuration.getEnvironmentSettings;

import org.openqa.selenium.WebDriver;

import parser.core.config.Environment;

public abstract class Parser {

	protected WebDriver driver;
	protected Environment settings;
	
	protected abstract String enironmentKey();
	protected abstract void parse();
	
	public void start(){
		this.setUp();
		this.parse();
		this.tearDown();
	}
	
	protected void setUp(){
		settings = getEnvironmentSettings(enironmentKey());
		driver = getDriver(getClass().getName(), settings.browser, settings.remoteSelenium);
	}
	
	protected void tearDown(){
		dismissDriver(getClass().getName());
	}
	
}
