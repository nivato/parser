package parser.imp.baltbet.elements;

import java.util.List;

import org.jsoup.nodes.Element;

public class EventsTableRow{
	
	private Element row;
	private List<String> columnNames;
	
	public EventsTableRow(Element row, List<String> columnNames) {
		this.row = row;
		this.columnNames = columnNames;
	}
	
	public void printCells(){
		for (int i = 0; i < columnNames.size(); i++) {
			String columnName = columnNames.get(i);
			String cellValue = row.child(i).text();
			System.out.println("    " + columnName + ": " + cellValue);
		}
	}
	
}
