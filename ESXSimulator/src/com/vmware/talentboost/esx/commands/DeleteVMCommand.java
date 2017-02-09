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
public class DeleteVMCommand implements ICommand {

	private static final String COMMAND_NAME = "delete-vm";
	private static final String DESCRIPTION = "delete-vm <id> - deletes the VM with the given id.";
	private static final int NUM_OF_ARGUMENTS = 1;
	private IVirtualMachineManager virtualMachineManager;

	/**
	 * Class constructor specifying the reference of the virtual machine manager
	 * in which the virtual machine should be deleted
	 * 
	 * @param virtualMachineManager
	 */
	public DeleteVMCommand(IVirtualMachineManager virtualMachineManager) {
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
			throw new NumberOfArgumentsException(arguments.length);
		} else {
			AlphaNumericString vmId;
			try {
				vmId = new AlphaNumericString(arguments[0]);
			} catch (AlphaNumericStringConstructionException e) {
				throw new IllegalArgumentException(e.getMessage());
			}

			try {
				virtualMachineManager.deleteVM(vmId);
			} catch (VMNotFoundException e) {
				throw new IllegalArgumentException(e.getMessage());
			} catch (IOException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
		return Statuses.OK_STATUS;
	}

}
