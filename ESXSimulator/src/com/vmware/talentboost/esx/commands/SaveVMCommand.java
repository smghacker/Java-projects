package com.vmware.talentboost.esx.commands;

import java.io.IOException;

import com.vmware.talentboost.esx.alphanumericexceptions.AlphaNumericStringConstructionException;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.managers.IVirtualMachineManager;
import com.vmware.talentboost.esx.vmexceptions.VMNotFoundException;
/**
 * @author Valeri Colov
 * @version 1.0
 */
public class SaveVMCommand implements ICommand {

	private static final String COMMAND_NAME = "save-vm";
	private static final String DESCRIPTION = 
			"save-vm <id> - Saves the VM with the specified  id on the file system. ";
	private static final int NUM_OF_ARGUMENTS = 1;
	private IVirtualMachineManager virtualMachineManager;

	/**
	 * Class constructor specifying the reference of the virtual machine manager
	 * which virtual machines will be saved
	 * 
	 * @param virtualMachineManager
	 */
	public SaveVMCommand(IVirtualMachineManager virtualMachineManager) {
		this.virtualMachineManager = virtualMachineManager;
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
		String[] arguments = cmdArgs.split("\\s+");
		if (cmdArgs == "" || arguments.length < NUM_OF_ARGUMENTS) {
			throw new NumberOfArgumentsException(NUM_OF_ARGUMENTS);
		} else {

			try {
				String vmId = arguments[0];
				AlphaNumericString id = new AlphaNumericString(vmId);

				virtualMachineManager.saveVM(id);
			} catch (AlphaNumericStringConstructionException e) {
				throw new IllegalArgumentException(e.getMessage());
			} catch (IOException e) {
				throw new IllegalArgumentException(e.getMessage());
			} catch (VMNotFoundException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
			return Statuses.OK_STATUS;
		}
	}
	
}
