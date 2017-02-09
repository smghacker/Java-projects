package com.vmware.io;

import java.util.ArrayList;

public class SelectFromCommand implements Command {

	private static final String COMMAND_NAME = "select-from";
	private Storage storage;
	
	public SelectFromCommand(Storage storage){
		this.storage = storage;
	}
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

	@Override
	public String execute(String cmdArgs) {
		String[] arguments = cmdArgs.split("\\s+");
		String column = arguments[0];
		String nameOftable = arguments[1];
		Table neededTable = this.storage.getTable(nameOftable);
		
		ArrayList<String> wholeColumn = neededTable.getColumn(column);
		
		StringBuilder result = new StringBuilder();
		
		for(String s : wholeColumn){
			result.append(s);
			result.append("\n");
		}
		
		return result.toString();
	}

}
