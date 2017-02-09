package com.vmware.talentboost.esx.commands;

import java.math.BigInteger;

import com.vmware.talentboost.esx.alphanumericexceptions.AlphaNumericStringConstructionException;
import com.vmware.talentboost.esx.alphanumericexceptions.AlphaNumericStringWithSpaceConstructionException;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericStringWithSpaces;
import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.managers.IVirtualMachineManager;
import com.vmware.talentboost.esx.vmexceptions.VMNotFoundException;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class EditVMCommand implements ICommand {

	private static final String COMMAND_NAME = "edit-vm";
	private static final String DESCRIPTION = 
			"edit-vm <id> '<new_name>' <new_memory> - edit the "
			+ "VM with the  given id. Even when the name or the memory of the VM "
			+ "remains unchanged, they are provided.";
	private static final int NUM_OF_ARGUMENTS = 3;
	private IVirtualMachineManager virtualMachineManager;

	/**
	 * Class constructor specifying the reference of the virtual machine manager
	 * in which the virtual machine should be edited
	 * 
	 * @param virtualMachineManager
	 */
	public EditVMCommand(IVirtualMachineManager virtualMachineManager) {
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
		String[] arguments = cmdArgs.split("'");

		if (cmdArgs == "" || arguments.length < NUM_OF_ARGUMENTS) {
			throw new NumberOfArgumentsException(arguments.length);
		} else {
			for (int i = 0; i < NUM_OF_ARGUMENTS; i++) {
				if (i != 1) {
					arguments[i] = arguments[i].replaceAll("\\s+", "");
				}
			}

			try {
				AlphaNumericString id = new AlphaNumericString(arguments[0]);
				AlphaNumericStringWithSpaces name = new AlphaNumericStringWithSpaces(
						arguments[1]);
				BigInteger sizeOfMemory = new BigInteger(arguments[2]);

				virtualMachineManager.editVM(id, name, sizeOfMemory);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(e.getMessage() + ". The new memory size must be a number");
			} catch (AlphaNumericStringConstructionException e) {
				throw new IllegalArgumentException(e.getMessage());
			} catch (AlphaNumericStringWithSpaceConstructionException e) {
				throw new IllegalArgumentException(e.getMessage());
			} catch (VMNotFoundException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
		return Statuses.OK_STATUS;
	}

}
