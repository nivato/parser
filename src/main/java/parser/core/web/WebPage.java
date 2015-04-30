package parser.core.web;

import static parser.core.config.Configuration.getEnvironmentSettings;

import org.openqa.selenium.WebDriver;

import parser.core.config.Environment;

public abstract class WebPage<T extends WebPage<T>> extends Component<T>{
	
	protected static String enironmentKey;
	protected static final Environment SETTINGS = getEnvironmentSettings(enironmentKey);
	protected static final String BASE_URL = SETTINGS.scheme + "://" + SETTINGS.host;
	
	public WebPage(WebDriver driver) {
		super(driver);
	}

	public abstract T load();
	
	public T loadAndWaitUntilAvailable() {
        T page = load();
        waitUntilAvailable();
        return page;
    }
	
}
