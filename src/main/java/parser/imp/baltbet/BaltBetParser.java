package parser.imp.baltbet;

import parser.core.Parser;

public class BaltBetParser extends Parser{
	
	@Override
	protected String enironmentKey() {
		return "baltbet";
	}
	
	@Override
	public void parse() {
		driver.get(settings.scheme + "://" + settings.host);
		try{Thread.sleep(10000);} catch(Exception e){}
	}

}
