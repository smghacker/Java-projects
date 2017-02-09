package com.vmware.talentboost.esx.commands;

import com.vmware.talentboost.esx.alphanumericexceptions.AlphaNumericStringConstructionException;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.devices.IDevice;
import com.vmware.talentboost.esx.devicesexceptions.DeviceWithThisIdAlreadyExistsException;
import com.vmware.talentboost.esx.devicesexceptions.IPFormatException;
import com.vmware.talentboost.esx.devicesexceptions.MACAddresFormatException;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameIpExistsException;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameMacExistsException;
import com.vmware.talentboost.esx.managers.IVirtualMachineManager;
import com.vmware.talentboost.esx.vmexceptions.VMNotFoundException;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class AddDeviceCommand implements ICommand {

	private static final String COMMAND_NAME = "add-dev";

	private static final String DESCRIPTION = "add-dev <vm_id> <dev_type> <device_spec> - adds a new device to the "
			+ "virtual machine with id - vm_id. Each device has a unique id among all "
			+ "devices in this VM. Device id is specified in device_spec. \n "
			+ "dev_type will be VIDEO_CARD, NETWORK_CARD, HDD_CONTROLLER or HDD for video card , network card, hdd controller, hdd respectively.\n"
			+ " device_spec will be a device specification for a device of the type specified.";

	public static final int NUM_OF_ARGUMENTS = 1;
	private IVirtualMachineManager virtualMachineManager;

	/**
	 * Class constructor specifying the reference of the virtual machine manager
	 * in which the device should be added
	 * 
	 * @param virtualMachineManager
	 */
	public AddDeviceCommand(IVirtualMachineManager virtualMachineManager) {
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
	public String execute(String cmdArgs) throws IllegalArgumentException,
			NumberOfArgumentsException {
		String[] arguments = cmdArgs.split("\\s+");
		if (cmdArgs == "" || arguments.length < NUM_OF_ARGUMENTS) {
			throw new NumberOfArgumentsException(arguments.length);
		}
		AlphaNumericString vmId;
		try {
			vmId = new AlphaNumericString(arguments[0]);
		} catch (AlphaNumericStringConstructionException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		String[] argumentsForDevice = new String[arguments.length - 1];
		for (int i = 1; i < arguments.length; i++) {
			argumentsForDevice[i - 1] = arguments[i];
		}

		try {
			IDevice device = virtualMachineManager.createDevice(argumentsForDevice);
			virtualMachineManager.addDeviceInCertainVM(vmId, device);
		} catch (DeviceWithThisIdAlreadyExistsException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (NetworkCardWithTheSameMacExistsException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (VMNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (NetworkCardWithTheSameIpExistsException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (IPFormatException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (MACAddresFormatException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return Statuses.OK_STATUS;
	}

}
