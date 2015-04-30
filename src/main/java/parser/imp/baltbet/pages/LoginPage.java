package parser.imp.baltbet.pages;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.name;

import org.openqa.selenium.WebDriver;

import parser.core.web.WebPage;
import parser.core.web.elements.Button;
import parser.core.web.elements.TextInput;

public class LoginPage extends WebPage<LoginPage>{

	private static final String PAGE_URL = BASE_URL + "/access/login";
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public LoginPage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getUsernameInput().isAvailable() && 
				getPasswordInput().isAvailable() &&
				getLoginButton().isAvailable();
	}
	
	public AdminHomePage loginUser(String username, String password) {
		getUsernameInput().inputText(username);
		getPasswordInput().inputText(password);
		getLoginButton().click();
		return new AdminHomePage(driver).waitUntilAvailable();
	}
	
	private TextInput getUsernameInput() {
		return new TextInput(driver, id("username"));
	}
	
	private TextInput getPasswordInput() {
		return new TextInput(driver, id("password"));
	}
	
	private Button getLoginButton() {
		return new Button(driver, name("commit"));
	}
	
}
