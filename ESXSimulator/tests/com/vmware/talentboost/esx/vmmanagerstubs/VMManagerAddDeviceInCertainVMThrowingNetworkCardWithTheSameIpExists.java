package com.vmware.talentboost.esx.vmmanagerstubs;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devices.IDevice;
import com.vmware.talentboost.esx.devicesexceptions.DeviceWithThisIdAlreadyExistsException;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameIpExistsException;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameMacExistsException;
import com.vmware.talentboost.esx.vmexceptions.VMNotFoundException;

public class VMManagerAddDeviceInCertainVMThrowingNetworkCardWithTheSameIpExists
		extends VirtualMachineManagerStub {
	private String existingIp;

	public VMManagerAddDeviceInCertainVMThrowingNetworkCardWithTheSameIpExists(
			String existingIp) {
		this.existingIp = existingIp;
	}

	@Override
	public void addDeviceInCertainVM(AlphaNumericString vmId, IDevice device)
			throws DeviceWithThisIdAlreadyExistsException,
			NetworkCardWithTheSameMacExistsException, VMNotFoundException,
			NetworkCardWithTheSameIpExistsException {
		throw new NetworkCardWithTheSameIpExistsException(existingIp);
	}
	
}
