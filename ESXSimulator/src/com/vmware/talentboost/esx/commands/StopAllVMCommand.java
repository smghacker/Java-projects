package com.vmware.talentboost.esx.commands;

import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.server.ESXSimulator;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class StopAllVMCommand implements ICommand {

	private static final String COMMAND_NAME = "stop";
	private static final String DESCRIPTION = "stop - will save all virtual machines state and the stops them,"
			+ " then shutdowns the ESX server";
	private ESXSimulator cmdConsumer;

	/**
	 * Class constructor specifying the concrete ESX server, which will save all
	 * virtual machines state and then shutdown
	 * 
	 * @param cmdConsumer
	 */
	public StopAllVMCommand(ESXSimulator cmdConsumer) {
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
		return DESCRIPTION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.commands.ICommand#execute(java.lang.String)
	 */
	@Override
	public String execute(String cmdArgs) throws NumberOfArgumentsException {
		cmdConsumer.shutDown();
		return Statuses.OK_STATUS;
	}

}
