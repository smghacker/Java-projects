package com.vmware.talentboost.esx.commands;

import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;

/**
 * @author Valeri Colov
 * @version 1.0
 * com.vmware.talentboost.esx.commands
 * This interface represents the basic functionality of commands. The user of this interface
 * can access the command name, command description. The user can also execute the command
 */
public interface ICommand {
	String getCommandName();
	String getDescription();
	/**
	 * This function executes the command, accepting its arguments as a string,
	 * returns OK status if the execution was finished, throws an exception if the number
	 * of arguments is less than required and throws an exception if some of the 
	 * arguments is illegal 
	 * @param cmdArgs
	 * @return OK status or nothing if it throws
	 * @throws NumberOfArgumentsException
	 * @throws IllegalArgumentException
	 */
	String execute(String cmdArgs) throws IllegalArgumentException, NumberOfArgumentsException;
}
