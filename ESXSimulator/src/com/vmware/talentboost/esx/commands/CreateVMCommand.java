package com.vmware.talentboost.esx.commands;

import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.managers.IVirtualMachineManager;
import com.vmware.talentboost.esx.vmexceptions.VMWithCertainIdExistsException;
import com.vmware.talentboost.esx.vms.IVirtualMachine;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class CreateVMCommand implements ICommand {

	private static final String COMMAND_NAME = "create-vm";
	private static final String DESCRIPTION = "create-vm <id> '<name>' <memory> <num_cpus>- "
			+ "creates a new VM with the specified id, name, memory and number of cpus and with "
			+ "no devices.\n Memory parameter is in bytes. Number of CPUs is between 1 and 8";
	private static final int NUM_OF_ARGUMENTS = 4;
	private IVirtualMachineManager virtualMachineManager;

	/**
	 * Class constructor specifying the reference of the virtual machine manager
	 * in which the virtual machine should be created and then added
	 * 
	 * @param virtualMachineManager
	 */
	public CreateVMCommand(IVirtualMachineManager virtualMachineManager) {
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
	public String execute(String cmdArgs) throws NumberOfArgumentsException,
			IllegalArgumentException {
		// first we split the string in three parts
		// first part = <id>, second = <name> and third part = <memory> <num_cpus>
		String[] firstSplit = cmdArgs.split("'");
		
		if (firstSplit.length < NUM_OF_ARGUMENTS - 1) {
			throw new NumberOfArgumentsException(firstSplit.length);
		}
		String[] thirdPartSplit = firstSplit[2].trim().split("\\s+");
		if (thirdPartSplit.length <= 1) {
			throw new NumberOfArgumentsException(thirdPartSplit.length);
		}
		String[] arguments = new String[] { firstSplit[0], firstSplit[1],
				thirdPartSplit[0], thirdPartSplit[1] };

		for (int i = 0; i < arguments.length; i++) {
			if (i != 1) {
				arguments[i] = arguments[i].replaceAll("\\s+", "");
			}
		}

		IVirtualMachine vm = virtualMachineManager.createVM(arguments);
		try {
			virtualMachineManager.addVM(vm, false);
		} catch (VMWithCertainIdExistsException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return Statuses.OK_STATUS;
	}
}
