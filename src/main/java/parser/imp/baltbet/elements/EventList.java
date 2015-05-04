package parser.imp.baltbet.elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import parser.core.web.WebComponent;
import parser.core.web.elements.Link;

public class EventList extends WebComponent<EventList>{

	public EventList(WebDriver driver, By findByMethod) {
		super(driver, findByMethod);
	}
	
	public Map<String, Link> getEventLinks(){
		Map<String, Link> linksMap = new HashMap<String, Link>();
		List<WebElement> linkElements = getWebElement().findElements(By.xpath("./ul/li/a"));
		for (WebElement element: linkElements){
			linksMap.put(element.getText(), new Link(driver, element));
		}
		return linksMap;
	}
	
}
