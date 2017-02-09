package com.vmware.talentboost.esx.commands;

import java.util.ArrayList;

import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class HelpCommand implements ICommand {

	private static final String COMMAND_NAME = "help";
	private static final String DESCRIPTION = "Gives a description of all commands";
	
	/**
	 * List of all commands constructed with parameter null because we are
	 * interested only in getting their description
	 */
	private static final ArrayList<ICommand> allCommands = new ArrayList<ICommand>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2008967112304888224L;

		{
			add(new AddDeviceCommand(null));
			add(new CreateVMCommand(null));
			add(new DeleteDeviceCommand(null));
			add(new DeleteVMCommand(null));
			add(new EditVMCommand(null));
			add(new HelpCommand());
			add(new PrintVMsCommand(null));
			add(new ReadVMCommand(null));
			add(new SaveVMCommand(null));
			add(new StopAllVMCommand(null));
		}
	};

	/* (non-Javadoc)
	 * @see com.vmware.talentboost.esx.commands.ICommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

	/* (non-Javadoc)
	 * @see com.vmware.talentboost.esx.commands.ICommand#getDescription()
	 */
	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	/* (non-Javadoc)
	 * @see com.vmware.talentboost.esx.commands.ICommand#execute(java.lang.String)
	 */
	@Override
	public String execute(String cmdArgs) throws NumberOfArgumentsException {
		StringBuilder result = new StringBuilder();
		for(ICommand cmd : allCommands){
			result.append(cmd.getDescription() + "\n");
		}
		return result.toString();
	}

}
