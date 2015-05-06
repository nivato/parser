package parser.imp.baltbet.elements;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import parser.core.web.WebComponent;

public class EventsTable extends WebComponent<EventsTable>{
	
	private String tableName = null;
	private List<String> columnNames = null;
	
	public EventsTable(WebDriver driver, WebElement cachedWebElement) {
		super(driver, cachedWebElement);
	}
	
	public String getTableName(){
		if (tableName == null){
			getColumnNames();
		}
		return tableName;
	}
	
	public List<String> getColumnNames(){
		if (columnNames == null){
			columnNames = new ArrayList<String>();
			Element table = getParsedContents();
			Element tbody = table.child(0);
			Element tr = tbody.child(0);
			Element[] headRowCells = tr.children().toArray(new Element[0]);
			for (Element element: headRowCells){
				columnNames.add(element.text());
			}
			tableName = columnNames.remove(1);
			String[] additionalColumnsReversed = {"Событие", "Врємя", "Дата"};
			for (String column: additionalColumnsReversed){
				columnNames.add(1, column);
			}
		}
		return columnNames;
	}
	
	public List<EventsTableRow> getEventRows(){
		List<EventsTableRow> list = new ArrayList<EventsTableRow>();
		List<String> columns = getColumnNames();
		Element table = getParsedContents();
		Element[] trs = table.child(0).children().toArray(new Element[0]);
		for (Element tr: trs){
			Element td = tr.child(0);
			if (td.children().size() > 0){
				Element span = td.child(0);
				if (span.attr("class").equals("evnum")){
					list.add(new EventsTableRow(tr, columns));
				}
			}
		}
		return list;
	}
	
}
