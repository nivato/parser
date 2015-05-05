package parser.imp.baltbet;

import parser.core.Parser;
import parser.imp.baltbet.pages.HomePage;

public class BaltBetParser extends Parser{
	
	@Override
	protected String enironmentKey() {
		return "baltbet";
	}
	
	@Override
	public void parse() {
		HomePage page = new HomePage(driver, settings.scheme + "://" + settings.host);
		page.loadAndWaitUntilAvailable();
		page.goToEventsPage("Футбол").printEventTableNames();
	}

}
