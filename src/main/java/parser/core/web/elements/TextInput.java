package parser.core.web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import parser.core.web.WebComponent;

public class TextInput extends WebComponent<TextInput>{

	public TextInput(WebDriver driver, By findByMethod) {
		super(driver, findByMethod);
	}
	
	public TextInput(WebDriver driver, WebElement cachedWebElement) {
		super(driver, cachedWebElement);
	}
	
	public TextInput inputText(String text){
		getWebElement().sendKeys(text);
		return this;
	}
	
	public TextInput clearText(){
		getWebElement().clear();
		return this;
	}
	
}
