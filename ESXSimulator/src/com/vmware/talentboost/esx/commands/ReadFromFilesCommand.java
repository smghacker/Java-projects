package com.vmware.talentboost.esx.commands;

import java.io.FileNotFoundException;

import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.server.ESXSimulator;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class ReadFromFilesCommand implements ICommand {

	private static final String COMMAND_NAME = "read-files";
	private static final String DESCRIPTION = 
			"read-files- makes the program to read from multiple files. "
			+ "Parameters are the names of the files, separated by commas";
	private ESXSimulator cmdConsumer;

	/**
	 * Class constructor specifying the concrete ESX server, which will start to
	 * read from multiple files
	 * 
	 * @param cmdConsumer
	 */
	public ReadFromFilesCommand(ESXSimulator cmdConsumer) {
		this.cmdConsumer = cmdConsumer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.commands.ICommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.commands.ICommand#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return DESCRIPTION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.commands.ICommand#execute(java.lang.String)
	 */
	@Override
	public String execute(String cmdArgs) throws NumberOfArgumentsException,
			IllegalArgumentException {
		String[] arguments = cmdArgs.split(",");
		for (int i = 0; i < arguments.length; i++) {
			arguments[i] = arguments[i].trim();
		}

		try {
			cmdConsumer.startReadingFromFiles(arguments);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return Statuses.OK_STATUS;
	}

}
