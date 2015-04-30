package parser.core.web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import parser.core.web.WebComponent;

public class Link extends WebComponent<Link>{

	public Link(WebDriver driver, By findByMethod) {
		super(driver, findByMethod);
	}
	
	public Link(WebDriver driver, WebElement cachedWebElement) {
		super(driver, cachedWebElement);
	}
	
}
