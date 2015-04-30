package parser.core;

import java.util.HashMap;
import java.util.Map;

public enum BrowserType {
	
	FIREFOX("firefox"),
	CHROME("chrome"),
	IE("ie"),
	OPERA("opera");
	
	private String browserKey;
	private static Map<String, BrowserType> browserMap = new HashMap<String, BrowserType>();
	static {
		for(BrowserType d: BrowserType.values()){
			browserMap.put(d.key(), d);
		}
	}
	
	private BrowserType(String key) {
		this.browserKey = key;
	}
	
	private String key(){
		return this.browserKey;
	}
	
	public static BrowserType get(String key){
		if(browserMap.containsKey(key)){
			return browserMap.get(key);
		}
		return FIREFOX;
	}
	
}
