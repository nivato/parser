package parser.imp.baltbet.pages;

import static org.openqa.selenium.By.linkText;

import org.openqa.selenium.WebDriver;

import parser.core.web.WebPage;
import parser.core.web.elements.Link;

public class AdminHomePage extends WebPage<AdminHomePage>{

	private static final String PAGE_URL = BASE_URL + "/admin";
	
	public AdminHomePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public AdminHomePage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getManageUsersLink().isAvailable() &&
				getLogoutLink().isAvailable();
	}
	
	public LoginPage loadAsAnonymousUser() {
		load();
		return new LoginPage(driver).waitUntilAvailable();
	}
	
	public LoginPage logoutUser() {
		getLogoutLink().click();
		return new LoginPage(driver).waitUntilAvailable();
	}
	
	public ManageUsersPage goToManageUsersPage(){
		getManageUsersLink().click();
		return new ManageUsersPage(driver).waitUntilAvailable();
	}
	
	private Link getManageUsersLink() {
		return new Link(driver, linkText("Manage Admin Users"));
	}
	
	private Link getLogoutLink() {
		return new Link(driver, linkText("Logout"));
	}
	
}
