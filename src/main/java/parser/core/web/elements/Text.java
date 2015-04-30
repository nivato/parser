package parser.core.web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import parser.core.web.WebComponent;

public class Text extends WebComponent<Text>{

	public Text(WebDriver driver, By findByMethod) {
		super(driver, findByMethod);
	}
	
	public Text(WebDriver driver, WebElement cachedWebElement) {
		super(driver, cachedWebElement);
	}
	
}
