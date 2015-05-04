package parser.imp.baltbet.pages;

import static org.openqa.selenium.By.className;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import parser.core.web.WebPage;
import parser.core.web.elements.Link;
import parser.imp.baltbet.elements.EventList;

public class BaltBetPage extends WebPage<BaltBetPage>{

	public BaltBetPage(WebDriver driver, String url) {
		super(driver, url);
	}

	@Override
	public boolean isAvailable() {
		return getEventListElement().isAvailable();
	}
	
	public void printAllEvents(){
		EventList listElement = getEventListElement();
		for (Map.Entry<String, Link> entry: listElement.getEventLinks().entrySet()){
		    String key = entry.getKey();
		    Link value = entry.getValue();
		    System.out.println("EVENT: " + key);
		}
	}
	
	public Set<String> getEventsSet(){
		Map<String, Link> eventLinks = getEventListElement().getEventLinks();
		return eventLinks.keySet();
	}
	
	private EventList getEventListElement(){
		return new EventList(driver, className("allbet"));
	}
	
}
