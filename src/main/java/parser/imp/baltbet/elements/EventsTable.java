package parser.imp.baltbet.elements;

import static org.openqa.selenium.By.xpath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import parser.core.web.WebComponent;

public class EventsTable extends WebComponent<EventsTable>{
	
	private String categoryName;
	
	public EventsTable(WebDriver driver, WebElement cachedWebElement, String categoryName) {
		super(driver, cachedWebElement);
		this.categoryName = categoryName;
	}

	public String getTableName(){
		return getWebElement().findElement(xpath(".//td[contains(./text(), '" + categoryName + ". ')]")).getText();
	}
	
}
