package parser.imp.baltbet.pages;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

import org.openqa.selenium.WebDriver;

import parser.core.web.WebPage;
import parser.core.web.elements.Button;
import parser.core.web.elements.Text;
import parser.core.web.elements.TextInput;

public class CreateNewUserPage extends WebPage<CreateNewUserPage>{
	
	public CreateNewUserPage(WebDriver driver, String url) {
		super(driver, url);
	}

	@Override
	public boolean isAvailable() {
		return getUsernameInput().isAvailable() &&
				getPasswordInput().isAvailable() &&
				getPasswordConfirmationInput().isAvailable() &&
				getFirstNameInput().isAvailable() &&
				getLastNameInput().isAvailable() &&
				getEmailInput().isAvailable() &&
				getCreateUserButton().isAvailable();
	}
	
	public CreateNewUserPage fillInForm(String username, String password, String passwordConfirmation, String firstName, String lastName, String email) {
		getUsernameInput().inputText(username);
		getPasswordInput().inputText(password);
		getPasswordConfirmationInput().inputText(passwordConfirmation);
		getFirstNameInput().inputText(firstName);
		getLastNameInput().inputText(lastName);
		getEmailInput().inputText(email);
		return this;
	}
	
	public CreateNewUserPage submitFormExpectingError(){
		getCreateUserButton().click();
		return this.waitUntilAvailable();
	}
	
	public String getErrorMessage() {
		return new Text(driver, xpath("id('errorExplanation')/ul/li")).getText();
	}
	
	private TextInput getUsernameInput() {
		return new TextInput(driver, id("admin_user_username"));
	}
	
	private TextInput getPasswordInput() {
		return new TextInput(driver, id("admin_user_password"));
	}
	
	private TextInput getPasswordConfirmationInput() {
		return new TextInput(driver, id("admin_user_password_confirmation"));
	}
	
	private TextInput getFirstNameInput() {
		return new TextInput(driver, id("admin_user_first_name"));
	}
	
	private TextInput getLastNameInput() {
		return new TextInput(driver, id("admin_user_last_name"));
	}
	
	private TextInput getEmailInput() {
		return new TextInput(driver, id("admin_user_email"));
	}
	
	private Button getCreateUserButton() {
		return new Button(driver, name("commit"));
	}
	
}
