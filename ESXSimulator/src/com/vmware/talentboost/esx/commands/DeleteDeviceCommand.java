package com.vmware.talentboost.esx.commands;

import com.vmware.talentboost.esx.alphanumericexceptions.AlphaNumericStringConstructionException;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.managers.IVirtualMachineManager;
import com.vmware.talentboost.esx.vmexceptions.VMNotFoundException;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class DeleteDeviceCommand implements ICommand {

	private static final String COMMAND_NAME = "delete-dev";
	private static final String DESCRIPTION = "delete-dev <vm_id> <dev_id> - deletes the device with the specified 'dev_id' from the virtual  machine with id 'vm_id'.";
	private static final int NUM_OF_ARGUMENTS = 2;
	private IVirtualMachineManager virtualMachineManager;

	/**
	 * Class constructor specifying the reference of the virtual machine manager
	 * in which the device should be deleted
	 * 
	 * @param virtualMachineManager
	 */
	public DeleteDeviceCommand(IVirtualMachineManager virtualMachineManager) {
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
		if (arguments.length < NUM_OF_ARGUMENTS) {
			throw new NumberOfArgumentsException(arguments.length);
		} else {
			AlphaNumericString vmId;
			try {
				vmId = new AlphaNumericString(arguments[0]);
			} catch (AlphaNumericStringConstructionException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
			AlphaNumericString devId;
			try {
				devId = new AlphaNumericString(arguments[1]);
			} catch (AlphaNumericStringConstructionException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
			try {
				virtualMachineManager.deleteDeviceInCertainVM(vmId, devId);
			} catch (VMNotFoundException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
		return Statuses.OK_STATUS;
	}

}
