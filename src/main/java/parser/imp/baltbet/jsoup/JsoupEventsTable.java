package parser.imp.baltbet.jsoup;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

public class JsoupEventsTable {
	
	private Element element;
	
	public JsoupEventsTable(Element element){
		this.element = element;
	}
	
	public String getEventsTitle(){
		return element.nodeName() + " " + element.text() + " " + element.children().iterator().next().nodeName();
	}
	
	private List<Element> getRowElements(){
		List<Element> list = new ArrayList<Element>();
		element.getElementsByTag("tr").size();
		return list;
	}
	
}
