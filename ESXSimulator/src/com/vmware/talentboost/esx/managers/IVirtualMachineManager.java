package com.vmware.talentboost.esx.managers;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericStringWithSpaces;
import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.devices.IDevice;
import com.vmware.talentboost.esx.devicesexceptions.DeviceWithThisIdAlreadyExistsException;
import com.vmware.talentboost.esx.devicesexceptions.IPFormatException;
import com.vmware.talentboost.esx.devicesexceptions.MACAddresFormatException;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameIpExistsException;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameMacExistsException;
import com.vmware.talentboost.esx.vmexceptions.VMNotFoundException;
import com.vmware.talentboost.esx.vmexceptions.VMWithCertainIdExistsException;
import com.vmware.talentboost.esx.vms.IVirtualMachine;

/**
 * This interface provides the base functionality which every virtual machine
 * manager should implement.
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public interface IVirtualMachineManager {
	/**
	 * @param vm
	 * @param override
	 * @throws VMWithCertainIdExistsException
	 */
	void addVM(IVirtualMachine vm, boolean override)
			throws VMWithCertainIdExistsException;

	/**
	 * @param id
	 * @throws VMNotFoundException
	 * @throws IOException
	 */
	void deleteVM(AlphaNumericString id) throws VMNotFoundException,
			IOException;

	/**
	 * @param id
	 * @param newName
	 * @param newRamSize
	 * @throws VMNotFoundException
	 */
	void editVM(AlphaNumericString id, AlphaNumericStringWithSpaces newName,
			BigInteger newRamSize) throws VMNotFoundException;

	/**
	 * @param vmId
	 * @param devId
	 * @throws VMNotFoundException
	 */
	void deleteDeviceInCertainVM(AlphaNumericString vmId,
			AlphaNumericString devId) throws VMNotFoundException;

	/**
	 * @param vmId
	 * @param device
	 * @throws DeviceWithThisIdAlreadyExistsException
	 * @throws NetworkCardWithTheSameMacExistsException
	 * @throws VMNotFoundException
	 * @throws NetworkCardWithTheSameIpExistsException
	 */
	void addDeviceInCertainVM(AlphaNumericString vmId, IDevice device)
			throws DeviceWithThisIdAlreadyExistsException,
			NetworkCardWithTheSameMacExistsException, VMNotFoundException,
			NetworkCardWithTheSameIpExistsException;

	/**
	 * @param id
	 * @throws VMNotFoundException
	 * @throws IOException
	 */
	void saveVM(AlphaNumericString id) throws VMNotFoundException, IOException;

	/**
	 * @param arguments
	 * @return new device
	 * @throws IllegalArgumentException
	 * @throws NumberOfArgumentsException
	 * @throws MACAddresFormatException
	 * @throws IPFormatException
	 */
	IDevice createDevice(String[] arguments) throws IllegalArgumentException,
			NumberOfArgumentsException, MACAddresFormatException,
			IPFormatException;

	/**
	 * @param arguments
	 * @return new virtual machine
	 * @throws IllegalArgumentException
	 */
	IVirtualMachine createVM(String[] arguments)
			throws IllegalArgumentException;

	/**
	 * @param id
	 * @return
	 * @throws VMNotFoundException
	 */
	IVirtualMachine getVM(AlphaNumericString id) throws VMNotFoundException;

	Collection<IVirtualMachine> getAllExistingVMs();

	/**
	 * @param vmId
	 * @return the path to the file in which the virtual machine with id 'vm_id'
	 *         is saved
	 */
	String getLocalStoragePath(String vmId);

	/**
	 * @return in a string format all virtual machines
	 */
	String printVMs();
}
