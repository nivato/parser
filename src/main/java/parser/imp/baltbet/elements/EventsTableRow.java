package parser.imp.baltbet.elements;

import static org.openqa.selenium.By.xpath;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import parser.core.web.WebComponent;

public class EventsTableRow extends WebComponent<EventsTableRow>{
	
	private List<String> columnNames;
	
	public EventsTableRow(WebDriver driver, WebElement cachedWebElement, List<String> columnNames) {
		super(driver, cachedWebElement);
		this.columnNames = columnNames;
	}
	
	public void printCells(){
		for (int i = 0; i < columnNames.size(); i++) {
			String columnName = columnNames.get(i);
			String cellValue = getWebElement().findElement(xpath("./td[" + (i + 1) + "]")).getText();
			System.out.println("    " + columnName + ": " + cellValue);
		}
	}
	
}
