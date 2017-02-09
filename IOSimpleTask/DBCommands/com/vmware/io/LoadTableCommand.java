package com.vmware.io;

import java.io.IOException;

public class LoadTableCommand implements Command {

	private static final String COMMAND_NAME = "load-table";
	private Storage storage;
	
	public LoadTableCommand(Storage storage){
		this.storage = storage;
	}
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

	@Override
	public String execute(String cmdArgs) {
		String[] arguments = cmdArgs.split("\\s+");
		String name = arguments[0];
		String filename = arguments[1];
		
		Table newTable = new Table();
		TableIO newTableLoader = new TableIO();
		try{
			newTable = newTableLoader.loadCSV(filename);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		this.storage.addTable(name, newTable);
		return null;
	}

}
