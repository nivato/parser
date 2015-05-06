package parser.imp.baltbet.jsoup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Element;

public class JsoupEventsTable {
	
	private Element element;
	
	public JsoupEventsTable(Element element){
		this.element = element;
	}
	
	public String getTitle(){
		Element headRow = null;
		for (Element row: getRowElements()){
			if (row.attr("class").equals("head")){
				headRow = row;
				break;
			}
		}
		if (headRow != null){
			return element.nodeName() + " " + headRow.text();
		}
		return element.nodeName() + " null";
	}
	
	private List<Element> getRowElements(){
		List<Element> list = new ArrayList<Element>();
		Iterator<Element> rowElements = element.children().iterator().next().children().iterator();
		while (rowElements.hasNext()){
			Element row = rowElements.next();
			System.out.println(row.nodeName());
			if (row.nodeName().equals("tr")){
				list.add(row);
			}
		}
		return list;
	}
	
}
