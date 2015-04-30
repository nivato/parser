package parser.main;

import parser.imp.baltbet.BaltBetParser;

public class StartPoint {

	public static void main(String[] args) {
		for (String arg: args){
			System.out.println(arg);
		}
		BaltBetParser baltbet = new BaltBetParser();
		baltbet.start();
	}

}
