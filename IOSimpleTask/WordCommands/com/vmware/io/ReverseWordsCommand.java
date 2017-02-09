package com.vmware.io;

public class ReverseWordsCommand implements Command {

	private static final String COMMAND_NAME = "reverse-words";
	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return COMMAND_NAME;
	}

	@Override
	public String execute(String cmdArgs) {
		String[] allWords = cmdArgs.split("\\s+");
		int n = allWords.length - 1;
		String result = "";
		for(int i = n; i >= 0; --i){
			result += allWords[i];
			if(i > 0){
				result += " ";
			}
		}
		return result;
	}

}
