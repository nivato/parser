package parser.core.web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import parser.core.web.WebComponent;

public class Button extends WebComponent<Button>{

	public Button(WebDriver driver, By findByMethod) {
		super(driver, findByMethod);
	}
	
	public Button(WebDriver driver, WebElement cachedWebElement) {
		super(driver, cachedWebElement);
	}
	
}
