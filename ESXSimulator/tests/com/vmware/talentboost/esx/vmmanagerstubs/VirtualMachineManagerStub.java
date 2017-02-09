package com.vmware.talentboost.esx.vmmanagerstubs;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericStringWithSpaces;
import com.vmware.talentboost.esx.devices.IDevice;
import com.vmware.talentboost.esx.devicesexceptions.DeviceWithThisIdAlreadyExistsException;
import com.vmware.talentboost.esx.devicesexceptions.IPFormatException;
import com.vmware.talentboost.esx.devicesexceptions.MACAddresFormatException;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameIpExistsException;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameMacExistsException;
import com.vmware.talentboost.esx.managers.IVirtualMachineManager;
import com.vmware.talentboost.esx.vmexceptions.VMNotFoundException;
import com.vmware.talentboost.esx.vmexceptions.VMWithCertainIdExistsException;
import com.vmware.talentboost.esx.vms.IVirtualMachine;

public class VirtualMachineManagerStub implements IVirtualMachineManager {

	@Override
	public void addVM(IVirtualMachine vm, boolean override)
			throws VMWithCertainIdExistsException {

	}

	@Override
	public void deleteVM(AlphaNumericString id) throws VMNotFoundException,
			IOException {

	}

	@Override
	public void editVM(AlphaNumericString id,
			AlphaNumericStringWithSpaces newName, BigInteger newRamSize)
			throws VMNotFoundException {

	}

	@Override
	public void deleteDeviceInCertainVM(AlphaNumericString vmId,
			AlphaNumericString devId) throws VMNotFoundException {

	}

	@Override
	public void addDeviceInCertainVM(AlphaNumericString vmId, IDevice device)
			throws DeviceWithThisIdAlreadyExistsException,
			NetworkCardWithTheSameMacExistsException, VMNotFoundException,
			NetworkCardWithTheSameIpExistsException {
	}

	@Override
	public String printVMs() {
		return null;
	}

	@Override
	public IVirtualMachine getVM(AlphaNumericString id)
			throws VMNotFoundException {
		return null;
	}

	@Override
	public void saveVM(AlphaNumericString id) throws VMNotFoundException,
			IOException {

	}

	@Override
	public Collection<IVirtualMachine> getAllExistingVMs() {
		return null;
	}

	@Override
	public String getLocalStoragePath(String vmId) {
		return null;
	}

	@Override
	public IDevice createDevice(String[] arguments) throws MACAddresFormatException, IPFormatException {
		return null;
	}

	@Override
	public IVirtualMachine createVM(String[] arguments)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
