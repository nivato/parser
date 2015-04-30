package parser.test;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import parser.imp.baltbet.pages.AdminHomePage;
import parser.imp.baltbet.pages.CreateNewUserPage;

public class TestOne {
	
	protected WebDriver driver = new FirefoxDriver();
	
	public void testCreateUserErrorMessages(Map<String, String> testData) {
		CreateNewUserPage createUserPage = new AdminHomePage(driver)
			.loadAsAnonymousUser()
			.loginUser(testData.get("admin_username"), testData.get("admin_password"))
			.goToManageUsersPage()
			.goToCreateNewUserPage();
		createUserPage.fillInForm(
				testData.get("username"),
				testData.get("password"),
				testData.get("password_confirmation"),
				testData.get("first_name"),
				testData.get("last_lame"),
				testData.get("email"));
		createUserPage.submitFormExpectingError();
	}
	
	public void logoutUser() {
		new AdminHomePage(driver)
			.loadAndWaitUntilAvailable()
			.logoutUser();
	}
	
}
