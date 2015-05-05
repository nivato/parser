package parser.imp.baltbet.pages;

import static org.openqa.selenium.By.className;

import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import parser.core.web.WebPage;
import parser.core.web.elements.Link;
import parser.imp.baltbet.elements.EventCategoryList;

public abstract class BaltBetPage<T extends BaltBetPage<T>> extends WebPage<T>{

	public BaltBetPage(WebDriver driver, String url) {
		super(driver, url);
	}

	@Override
	public boolean isAvailable() {
		return getEventCategoryListElement().isAvailable();
	}
	
	public void printAllEvents(){
		EventCategoryList listElement = getEventCategoryListElement();
		for (Map.Entry<String, Link> entry: listElement.getCategoryLinks().entrySet()){
		    String key = entry.getKey();
		    Link value = entry.getValue();
		    System.out.println("EVENT: " + key);
		}
	}
	
	public Set<String> getEventCategories(){
		Map<String, Link> categoryLinks = getEventCategoryListElement().getCategoryLinks();
		return categoryLinks.keySet();
	}
	
	public EventsOfCategoryPage goToEventsPage(String categoryName){
		clickCategoryLink(categoryName);
		return new EventsOfCategoryPage(driver, categoryName).waitUntilAvailable();
	}
	
	protected void clickCategoryLink(String categoryName){
		Link categoryLink = getEventCategoryListElement().getCategoryLinks().get(categoryName);
		categoryLink.emulateClick();
	}
	
	private EventCategoryList getEventCategoryListElement(){
		return new EventCategoryList(driver, className("allbet")).waitUntilAvailable();
	}
	
}
