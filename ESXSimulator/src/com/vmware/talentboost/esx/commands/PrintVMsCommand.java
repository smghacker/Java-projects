package com.vmware.talentboost.esx.commands;

import com.vmware.talentboost.esx.managers.IVirtualMachineManager;

public class PrintVMsCommand implements ICommand {

	private static final String COMMAND_NAME = "print-vms";
	private static final String DESCRIPTION = "print-vms - prints a human-friendly summary of all the current VMs on the standard output for the program. ";
	private IVirtualMachineManager virtualMachineManager;

	/**
	 * Class constructor specifying the reference of the virtual machine manager
	 * from which we want to print the virtual machines
	 * @param virtualMachineManager
	 */
	public PrintVMsCommand(IVirtualMachineManager virtualMachineManager) {
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
	public String execute(String cmdArgs) {
		return virtualMachineManager.printVMs();
	}

}
