package parser.core.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class WebComponent<T extends WebComponent<T>> extends Component<T>{

	protected final By findByMethod;
	protected final WebElement cachedWebElement;
	private Element parsedContetns = null;
	
	public WebComponent(WebDriver driver, By findByMethod) {
		super(driver);
		this.findByMethod = findByMethod;
		this.cachedWebElement = null;
	}

	public WebComponent(WebDriver driver, WebElement cachedWebElement) {
		super(driver);
		this.cachedWebElement = cachedWebElement;
		this.findByMethod = null;
	}
	
	@Override
	public boolean isAvailable() {
		try {
            return getWebElement() != null;
        } catch (NoSuchElementException noSuchElementExc) {
            return false;
        }
	}

	public void click(){
		getWebElement().click();
	}
	
	public void emulateClick(){
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement()).click().build().perform();
	}
	
	public void emulateMouseOver(){
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement()).build().perform();
	}
	
	public String getText(){
		return getWebElement().getText();
	}
	
	public String getAttribute(String attributeName){
		return getWebElement().getAttribute(attributeName);
	}
	
	protected Element getParsedContents(){
		if (parsedContetns == null){
			Document document = Jsoup.parse(html());
			parsedContetns = document.getElementsByTag("body").first().child(0);
		}
		return parsedContetns;
	}
	
	protected String html(){
		return getWebElement().getAttribute("outerHTML");
	}
	
	protected WebElement getWebElement() {
		if (cachedWebElement != null) {
			return cachedWebElement;
		}
		return driver.findElement(findByMethod);
	}
	
}
