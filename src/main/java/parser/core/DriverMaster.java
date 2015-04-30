package parser.core;

import static org.openqa.selenium.remote.DesiredCapabilities.chrome;
import static org.openqa.selenium.remote.DesiredCapabilities.firefox;
import static org.openqa.selenium.remote.DesiredCapabilities.internetExplorer;
import static org.openqa.selenium.remote.DesiredCapabilities.opera;
import static parser.core.config.Configuration.getRemoteSeleniumSettings;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import parser.core.config.SeleniumServer;

import com.opera.core.systems.OperaDriver;

public class DriverMaster {

	private static Map<String, WebDriver> driverMap = new HashMap<String, WebDriver>();
	
	private DriverMaster(){};
	
	public static WebDriver getDriver(String driverKey, String browserKey, String remoteServerKey){
		if (driverMap.containsKey(driverKey)){
			return driverMap.get(driverKey);
		} else {
			driverMap.put(driverKey, createDriver(browserKey, remoteServerKey));
		}
		return driverMap.get(driverKey);
	}
	
	public static void dismissDriver(String driverKey){
		if (driverMap.containsKey(driverKey)){
			WebDriver driver = driverMap.remove(driverKey);
			driver.quit();
		}
	}
	
	private static URL getRemoteSeleniumServerUrl(String remoteServerKey){
		URL serverUrl = null;
		SeleniumServer remoteServer = getRemoteSeleniumSettings(remoteServerKey);
		String urlString = "";
		if (remoteServer != null){
			urlString = remoteServer.scheme  + "://" + remoteServer.host + ":" + remoteServer.port + "/" + remoteServer.path;
		}
		try {serverUrl = new URL(urlString);} catch (MalformedURLException e){}
		return serverUrl;
	}
	
	private static WebDriver createDriver(String browserKey, String remoteServerKey){
		WebDriver driver;
		URL serverUrl = getRemoteSeleniumServerUrl(remoteServerKey);
		BrowserType browser = BrowserType.get(browserKey);
		switch (browser) {
			case FIREFOX:
				if (serverUrl != null){
					driver = new RemoteWebDriver(serverUrl, firefox());
				} else {
					driver = new FirefoxDriver();
				}
				break;
			case CHROME:
				if (serverUrl != null){
					driver = new RemoteWebDriver(serverUrl, chrome());
				} else {
					driver = new ChromeDriver();
				}
				break;
			case IE:
				if (serverUrl != null){
					driver = new RemoteWebDriver(serverUrl, internetExplorer());
				} else {
					driver = new InternetExplorerDriver();
				}
				break;
			case OPERA:
				if (serverUrl != null){
					driver = new RemoteWebDriver(serverUrl, opera());
				} else {
					driver = new OperaDriver();
				}
				break;
			default:
				driver = new FirefoxDriver();
				break;
		}
		driver.manage().window().maximize();
		return driver;
	}
	
}
