package parser.imp.baltbet.pages;

import static org.openqa.selenium.By.xpath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import parser.core.web.elements.Text;
import parser.imp.baltbet.elements.EventsTable;
import parser.imp.baltbet.elements.EventsTableRow;

public class EventsPage extends BaltBetPage<EventsPage>{
	
	protected String categoryName;
	
	public EventsPage(WebDriver driver, String categoryName) {
		super(driver, null);
		this.categoryName = categoryName;
	}

	@Override
	public boolean isAvailable() {
		sleep(3000);
		return getEventsTitleElement().getText().equals("Предстоящие события: " + this.categoryName);
	}
	
	@Override
	public EventsPage waitUntilAvailable(){
		try {
			return super.waitUntilAvailable();
		} catch (TimeoutException e){
			refresh();
			clickCategoryLink(categoryName);
			return waitUntilAvailable();
		}
	}
	
	public void printEventTables(){
		for (Map.Entry<String, EventsTable> entry: getEventTablesMap().entrySet()){
		    String name = entry.getKey();
		    EventsTable table = entry.getValue();
		    System.out.println("===========================================================================================");
		    System.out.println(name);
		    System.out.println("===========================================================================================");
		    for (EventsTableRow row: table.getEventRows()){
		    	row.printCells();
		    	System.out.println("    ---------------------------------------------------------------------------------------");
		    }
		}
	}
	
	private Map<String, EventsTable> getEventTablesMap(){
		Map<String, EventsTable> map = new HashMap<String, EventsTable>();
		List<WebElement> tableElements = driver.findElements(xpath("id('livediv')/table"));
		try {
			for (WebElement element: tableElements){
				EventsTable table = new EventsTable(driver, element);
				map.put(table.getTableName(), table);
			}
		} catch (StaleElementReferenceException e){
			clickCategoryLink(categoryName);
			waitUntilAvailable();
			map = getEventTablesMap();
		}
		return map;
	}
	
	private Text getEventsTitleElement(){
		return new Text(driver, xpath("id('livediv')/h1"));
	}
	
}
