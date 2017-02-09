package com.vmware.talentboost.esx.managers;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericStringWithSpaces;
import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.devices.DeviceType;
import com.vmware.talentboost.esx.devices.IDevice;
import com.vmware.talentboost.esx.devices.NetworkCard;
import com.vmware.talentboost.esx.devicesexceptions.DeviceWithThisIdAlreadyExistsException;
import com.vmware.talentboost.esx.devicesexceptions.IPFormatException;
import com.vmware.talentboost.esx.devicesexceptions.MACAddresFormatException;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameIpExistsException;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameMacExistsException;
import com.vmware.talentboost.esx.factories.DeviceFactory;
import com.vmware.talentboost.esx.factories.MemoryFactory;
import com.vmware.talentboost.esx.factories.VirtualMachineFactory;
import com.vmware.talentboost.esx.memories.MemoryType;
import com.vmware.talentboost.esx.memories.RAM;
import com.vmware.talentboost.esx.storages.ILocalVMStorage;
import com.vmware.talentboost.esx.storages.IVMStorage;
import com.vmware.talentboost.esx.vmexceptions.VMNotFoundException;
import com.vmware.talentboost.esx.vmexceptions.VMWithCertainIdExistsException;
import com.vmware.talentboost.esx.vms.IVirtualMachine;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class VirtualMachineManager implements IVirtualMachineManager {

	private IVMStorage storage;
	private ILocalVMStorage localStorage;

	/**
	 * Class constructor specifying the storage and local storage(i.e where to
	 * be saved on the local machine's hard disk) of the virtual machines
	 * 
	 * @param storage
	 * @param localStorage
	 */
	public VirtualMachineManager(IVMStorage storage,
			ILocalVMStorage localStorage) {
		this.storage = storage;
		this.localStorage = localStorage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.managers.IVirtualMachineManager#addVM(com.
	 * vmware.talentboost.esx.vms.IVirtualMachine, boolean)
	 */
	@Override
	public void addVM(IVirtualMachine vm, boolean override)
			throws VMWithCertainIdExistsException {
		if (storage.containsId(vm.getId()) && !override) {
			throw new VMWithCertainIdExistsException(vm.getId());
		}
		storage.addVM(vm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.managers.IVirtualMachineManager#deleteVM(com
	 * .vmware.talentboost.esx.alphanumerics.AlphaNumericString)
	 */
	@Override
	public void deleteVM(AlphaNumericString id) throws VMNotFoundException,
			IOException {
		if (storage.containsId(id)) {
			storage.deleteVM(id);
			localStorage.deleteVM(id.getAlphanumericString());
		} else {
			throw new VMNotFoundException(id.getAlphanumericString());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.managers.IVirtualMachineManager#editVM(com
	 * .vmware.talentboost.esx.alphanumerics.AlphaNumericString,
	 * com.vmware.talentboost.esx.alphanumerics.AlphaNumericStringWithSpaces,
	 * java.math.BigInteger)
	 */
	@Override
	public void editVM(AlphaNumericString id,
			AlphaNumericStringWithSpaces newName, BigInteger newRamSize)
			throws VMNotFoundException {
		RAM newRam = (RAM) MemoryFactory.build(MemoryType.RAM, newRamSize);
		if (storage.containsId(id)) {
			storage.getVM(id).edit(newName, newRam);
		} else {
			throw new VMNotFoundException(id.getAlphanumericString());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.managers.IVirtualMachineManager#
	 * deleteDeviceInCertainVM
	 * (com.vmware.talentboost.esx.alphanumerics.AlphaNumericString,
	 * com.vmware.talentboost.esx.alphanumerics.AlphaNumericString)
	 */
	@Override
	public void deleteDeviceInCertainVM(AlphaNumericString vmId,
			AlphaNumericString devId) throws VMNotFoundException {
		if (storage.containsId(vmId)) {
			storage.getVM(vmId).deleteDevice(devId);
		} else {
			throw new VMNotFoundException(vmId.getAlphanumericString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.managers.IVirtualMachineManager#
	 * addDeviceInCertainVM
	 * (com.vmware.talentboost.esx.alphanumerics.AlphaNumericString,
	 * com.vmware.talentboost.esx.devices.IDevice)
	 */
	@Override
	public void addDeviceInCertainVM(AlphaNumericString vmId, IDevice device)
			throws DeviceWithThisIdAlreadyExistsException,
			NetworkCardWithTheSameMacExistsException, VMNotFoundException,
			NetworkCardWithTheSameIpExistsException {
		boolean doesExistDeviceWithThisId = false;
		for (IVirtualMachine vm : storage.getAllVMs()) {
			if (vm.getDeviceManager().doesExistDeviceWithId(device.getId())) {
				doesExistDeviceWithThisId = true;
			}
		}
		if (doesExistDeviceWithThisId) {
			throw new DeviceWithThisIdAlreadyExistsException(device.getId()
					.getAlphanumericString());
		}

		if (device.getType() == DeviceType.NETWORK_CARD) {
			String newMacAddress = ((NetworkCard) device).getMacAddress()
					.toLowerCase();
			String ipAddress = ((NetworkCard) device).getIpAddress();
			for (IVirtualMachine vm : storage.getAllVMs()) {
				if (vm.getDeviceManager().checkForIdenticalMacAddresses(
						newMacAddress)) {
					throw new NetworkCardWithTheSameMacExistsException(
							newMacAddress);
				} else if (vm.getDeviceManager().checkForIdenticalIpAddresses(
						ipAddress)) {
					throw new NetworkCardWithTheSameIpExistsException(ipAddress);
				}
			}
		}

		if (storage.containsId(vmId)) {
			storage.getVM(vmId).addDevice(device);
		} else {
			throw new VMNotFoundException(vmId.getAlphanumericString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.managers.IVirtualMachineManager#saveVM(com
	 * .vmware.talentboost.esx.alphanumerics.AlphaNumericString)
	 */
	@Override
	public void saveVM(AlphaNumericString id) throws VMNotFoundException,
			IOException {
		if (storage.containsId(id)) {
			IVirtualMachine vm = storage.getVM(id);
			localStorage.addVM(vm);
		} else {
			throw new VMNotFoundException(id.getAlphanumericString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.managers.IVirtualMachineManager#createDevice
	 * (java.lang.String[])
	 */
	@Override
	public IDevice createDevice(String[] arguments)
			throws IllegalArgumentException, NumberOfArgumentsException,
			MACAddresFormatException, IPFormatException {
		return DeviceFactory.build(arguments);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.managers.IVirtualMachineManager#createVM(java
	 * .lang.String[])
	 */
	@Override
	public IVirtualMachine createVM(String[] arguments)
			throws IllegalArgumentException {
		return VirtualMachineFactory.build(arguments);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.managers.IVirtualMachineManager#getVM(com.
	 * vmware.talentboost.esx.alphanumerics.AlphaNumericString)
	 */
	@Override
	public IVirtualMachine getVM(AlphaNumericString id)
			throws VMNotFoundException {
		if (storage.containsId(id)) {
			return storage.getVM(id);
		}
		throw new VMNotFoundException(id.getAlphanumericString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.managers.IVirtualMachineManager#getAllExistingVMs
	 * ()
	 */
	@Override
	public Collection<IVirtualMachine> getAllExistingVMs() {
		return storage.getAllVMs();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.managers.IVirtualMachineManager#
	 * getLocalStoragePath(java.lang.String)
	 */
	@Override
	public String getLocalStoragePath(String vmId) {
		return localStorage.constructPath(vmId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.managers.IVirtualMachineManager#printVMs()
	 */
	@Override
	public String printVMs() {
		StringBuilder result = new StringBuilder();
		for (IVirtualMachine vm : storage.getAllVMs()) {
			result.append(vm.toString() + "\n");
		}
		return result.toString();
	}

}
