package parser.imp.baltbet.elements;

import static org.openqa.selenium.By.xpath;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<String> getColumnNames(){
		List<String> list = new ArrayList<String>();
		List<WebElement> colElements = getWebElement().findElements(xpath("./tbody/tr[@class='head']/td"));
		for (WebElement element: colElements){
			list.add(element.getText());
		}
		list.remove(1);
		String[] additionalColumnsReversed = {"Событие", "Врємя", "Дата"};
		for (String column: additionalColumnsReversed){
			list.add(1, column);
		}
		return list;
	}
	
	public List<EventsTableRow> getEventRows(){
		List<EventsTableRow> list = new ArrayList<EventsTableRow>();
		List<String> columnNames = getColumnNames();
		List<WebElement> rowElements = getWebElement().findElements(xpath("./tbody/tr[not(@class)][./td[1]/span[@class='evnum']]"));
		for (WebElement elemet: rowElements){
			list.add(new EventsTableRow(driver, elemet, columnNames));
		}
		return list;
	}
	
}
