package parser.imp.baltbet.pages;

import static org.openqa.selenium.By.linkText;

import org.openqa.selenium.WebDriver;

import parser.core.web.WebPage;
import parser.core.web.elements.Button;

public class ManageUsersPage extends WebPage<ManageUsersPage>{
	
	public ManageUsersPage(WebDriver driver, String url) {
		super(driver, url);
	}

	@Override
	public ManageUsersPage load() {
		throw new RuntimeException("ManageUsersPage cannot be loaded");
	}

	@Override
	public boolean isAvailable() {
		return getAddNewUserButton().isAvailable();
	}
	
	public CreateNewUserPage goToCreateNewUserPage() {
		getAddNewUserButton().click();
		return new CreateNewUserPage(driver, null).waitUntilAvailable();
	}
	
	private Button getAddNewUserButton() {
		return new Button(driver, linkText("Add New Admin User"));
	}
	
}
