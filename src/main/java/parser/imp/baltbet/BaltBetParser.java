package parser.imp.baltbet;

import parser.core.Parser;
import parser.imp.baltbet.pages.BaltBetPage;

public class BaltBetParser extends Parser{
	
	@Override
	protected String enironmentKey() {
		return "baltbet";
	}
	
	@Override
	public void parse() {
		BaltBetPage page = new BaltBetPage(driver, settings.scheme + "://" + settings.host);
		page.loadAndWaitUntilAvailable();
		page.printAllEvents();
	}

}
