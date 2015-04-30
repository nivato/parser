package parser.core.config;

import parser.core.YamlParser;

public final class Configuration {
	
	private static final Environments ALL_ENVIRONMENTS;
	private static final RemoteSeleniumServers ALL_REMOTE_SERVERS;
	static {
		YamlParser yml = new YamlParser("environment.yml");
		ALL_ENVIRONMENTS = yml.parseAs(Environments.class);
		yml = new YamlParser("remote_selenium.yml");
		ALL_REMOTE_SERVERS = yml.parseAs(RemoteSeleniumServers.class);
	}
	
	private Configuration(){}
	
	public static Environment getEnvironmentSettings(String environmentKey){
		return ALL_ENVIRONMENTS.all.get(environmentKey);
	}
	
	public static SeleniumServer getRemoteSeleniumSettings(String remoteServerKey){
		return ALL_REMOTE_SERVERS.all.get(remoteServerKey);
	}
	
}
