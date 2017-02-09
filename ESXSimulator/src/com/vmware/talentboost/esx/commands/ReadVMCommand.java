package com.vmware.talentboost.esx.commands;

import java.io.FileNotFoundException;

import com.vmware.talentboost.esx.alphanumericexceptions.AlphaNumericStringConstructionException;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.devicesexceptions.IPFormatException;
import com.vmware.talentboost.esx.devicesexceptions.MACAddresFormatException;
import com.vmware.talentboost.esx.factories.VirtualMachineFactory;
import com.vmware.talentboost.esx.managers.IVirtualMachineManager;
import com.vmware.talentboost.esx.vmexceptions.VMWithCertainIdExistsException;
import com.vmware.talentboost.esx.vms.IVirtualMachine;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class ReadVMCommand implements ICommand {

	private static final String COMMAND_NAME = "read-vm";
	private static final String DESCRIPTION = 
			"read-vm <id> - Reads the configuration of the VM with the specified id "
			+ "from the file system and replaces the current settings.  ";
	private static final int NUM_OF_ARGUMENTS = 1;
	private IVirtualMachineManager virtualMachineManager;

	/**
	 * Class constructor specifying the concrete virtual machine manager to
	 * which the new virtual machine will be saved.
	 * 
	 * @param virtualMachineManager
	 */
	public ReadVMCommand(IVirtualMachineManager virtualMachineManager) {
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
		String[] arguments = cmdArgs.split("\\s+");
		if (arguments.length < NUM_OF_ARGUMENTS) {
			throw new NumberOfArgumentsException(NUM_OF_ARGUMENTS);
		} else {

			try {
				AlphaNumericString id = new AlphaNumericString(arguments[0]);

				String path = virtualMachineManager.getLocalStoragePath(id
						.getAlphanumericString());
				IVirtualMachine vm = VirtualMachineFactory.buildFromFile(path);
				virtualMachineManager.addVM(vm, true);
			} catch (VMWithCertainIdExistsException e) {
				throw new IllegalArgumentException(e.getMessage());
			} catch (AlphaNumericStringConstructionException e) {
				throw new IllegalArgumentException(e.getMessage());
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException(e.getMessage());
			} catch (MACAddresFormatException e) {
				throw new IllegalArgumentException(e.getMessage());
			} catch (IPFormatException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
		return Statuses.OK_STATUS;
	}
	
}
