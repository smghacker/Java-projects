package com.vmware.io;

public class CountWordsCommand implements Command{
	private static final String COMMAND_NAME = "count-words";
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
	
	@Override
	public String execute(String cmdArgs){
		int numberOfWords = cmdArgs.split("\\s+").length;
		return String.valueOf(numberOfWords);
	}
}
