package com.vmware.talentboost.esx.vms;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericStringWithSpaces;
import com.vmware.talentboost.esx.devices.IDevice;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameMacExistsException;
import com.vmware.talentboost.esx.managers.IDeviceManager;
import com.vmware.talentboost.esx.memories.RAM;

/**
 * This class represents a single virtual machine and all its
 * properties(name,id,number of CPUs, memory and device manager)
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public class VirtualMachine implements IVirtualMachine {

	public static final int NUMBER_OF_SPECS = 2;
	private AlphaNumericStringWithSpaces name;
	private AlphaNumericString id;
	private int numCPUs;
	private RAM ram;
	private IDeviceManager deviceManager;

	/**
	 * Class constructor specifying the name, id, memory, concrete device
	 * manager and the number of CPUs for the new virtual machine
	 * 
	 * @param name
	 * @param id
	 * @param memory
	 * @param deviceManager
	 * @param numCPUs
	 */
	public VirtualMachine(AlphaNumericStringWithSpaces name,
			AlphaNumericString id, RAM memory, IDeviceManager deviceManager,
			int numCPUs) {
		this.name = name;
		this.id = id;
		this.ram = memory;
		this.deviceManager = deviceManager;
		this.numCPUs = numCPUs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.vms.IVirtualMachine#getName()
	 */
	@Override
	public AlphaNumericStringWithSpaces getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.vms.IVirtualMachine#getId()
	 */
	@Override
	public AlphaNumericString getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.vms.IVirtualMachine#getDeviceManager()
	 */
	@Override
	public IDeviceManager getDeviceManager() {
		return deviceManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.vms.IVirtualMachine#listOfAllDevicesInStringFormat
	 * ()
	 */
	@Override
	public String listOfAllDevicesInStringFormat() {
		return deviceManager.listDevices();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.vms.IVirtualMachine#edit(com.vmware.talentboost
	 * .esx.alphanumerics.AlphaNumericStringWithSpaces,
	 * com.vmware.talentboost.esx.memories.RAM)
	 */
	@Override
	public void edit(AlphaNumericStringWithSpaces newName, RAM newRam) {
		this.name = newName;
		this.ram = newRam;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.vms.IVirtualMachine#deleteDevice(com.vmware
	 * .talentboost.esx.alphanumerics.AlphaNumericString)
	 */
	@Override
	public void deleteDevice(AlphaNumericString id) {
		deviceManager.deleteDevice(id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.vms.IVirtualMachine#addDevice(com.vmware.
	 * talentboost.esx.devices.IDevice)
	 */
	@Override
	public void addDevice(IDevice newDevice)
			throws NetworkCardWithTheSameMacExistsException {
		deviceManager.addDevice(newDevice);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Virtual Machine\n");
		result.append("Name of VM: " + name + "\n");
		result.append("ID: " + id + "\n");
		result.append(ram.toString());
		result.append("Number of CPUs: " + numCPUs + "\n");
		result.append(listOfAllDevicesInStringFormat());
		return result.toString();
	}

}
