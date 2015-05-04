package parser.core.web;

import org.openqa.selenium.WebDriver;

public abstract class WebPage<T extends WebPage<T>> extends Component<T>{
	
	protected final String PAGE_URL;
	
	public WebPage(WebDriver driver, String url) {
		super(driver);
		PAGE_URL = url;
	}

	public T load(){
		if (PAGE_URL == null || PAGE_URL.equals("")){
			driver.quit();
			throw new RuntimeException(this.getClass().getSimpleName() + " page cannot be loaded!");
		}
		driver.get(PAGE_URL);
		return (T) this;
	};
	
	public T loadAndWaitUntilAvailable() {
        T page = load();
        waitUntilAvailable();
        return page;
    }
	
}
