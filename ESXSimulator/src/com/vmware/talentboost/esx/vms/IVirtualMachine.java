package com.vmware.talentboost.esx.vms;

import com.vmware.talentboost.esx.alphanumerics.*;
import com.vmware.talentboost.esx.devices.IDevice;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameMacExistsException;
import com.vmware.talentboost.esx.managers.IDeviceManager;
import com.vmware.talentboost.esx.memories.RAM;

/**
 * The user of this interface is able to get the name ad id of certain virtual
 * machine. They are also able to get the device manager and receive a list of
 * all devices in a string format. They can also edit the virtual machine,
 * delete and add a device to it.
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public interface IVirtualMachine {
	AlphaNumericStringWithSpaces getName();

	AlphaNumericString getId();

	IDeviceManager getDeviceManager();

	String listOfAllDevicesInStringFormat();

	void edit(AlphaNumericStringWithSpaces newName, RAM newRam);

	void deleteDevice(AlphaNumericString id) throws IllegalArgumentException;

	void addDevice(IDevice newDevice) throws IllegalArgumentException,
			NetworkCardWithTheSameMacExistsException;
}
