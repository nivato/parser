package parser.imp.baltbet.pages;

import static org.openqa.selenium.By.xpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.xalan.templates.ElemApplyImport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import parser.core.web.elements.Text;
import parser.imp.baltbet.elements.EventsTable;
import parser.imp.baltbet.elements.EventsTableRow;
import parser.imp.baltbet.jsoup.JsoupEventsTable;

public class EventsPage extends BaltBetPage<EventsPage>{
	
	protected String categoryName;
	
	public EventsPage(WebDriver driver, String categoryName) {
		super(driver, null);
		this.categoryName = categoryName;
	}

	@Override
	public boolean isAvailable() {
		return getEventsTitleElement().getText().equals("Предстоящие события: " + this.categoryName);
	}
	
	@Override
	public EventsPage waitUntilAvailable(){
		try {
			return super.waitUntilAvailable();
		} catch (TimeoutException e){
			driver.navigate().refresh();
			clickCategoryLink(categoryName);
			return waitUntilAvailable();
		}
	}
	
	public void parseHtml(){
		for (JsoupEventsTable table: getEventsTableList()){
			System.out.println(table.getEventsTitle());
		}
	}
	
	public List<JsoupEventsTable> getEventsTableList(){
		List<JsoupEventsTable> list = new ArrayList<JsoupEventsTable>();
		Document document = Jsoup.parse(driver.findElement(xpath("id('livediv')")).getAttribute("innerHTML"));
		Iterator<Element> elements = document.getElementsByTag("table").iterator();
		while (elements.hasNext()){
			list.add(new JsoupEventsTable(elements.next()));
		}
		return list;
	}
	
	public void printEventTables(){
		for (Map.Entry<String, EventsTable> entry: getEventTablesMap().entrySet()){
		    String name = entry.getKey();
		    EventsTable table = entry.getValue();
		    System.out.println("===========================================================================================");
		    System.out.println("EVENTS TABLE: " + name);
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
				EventsTable table = new EventsTable(driver, element, categoryName);
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
