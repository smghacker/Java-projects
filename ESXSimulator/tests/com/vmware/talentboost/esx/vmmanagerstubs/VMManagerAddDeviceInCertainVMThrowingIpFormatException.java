package com.vmware.talentboost.esx.vmmanagerstubs;

import com.vmware.talentboost.esx.devices.IDevice;
import com.vmware.talentboost.esx.devicesexceptions.IPFormatException;

public class VMManagerAddDeviceInCertainVMThrowingIpFormatException extends
		VirtualMachineManagerStub {
	private String ipAddress;
	public VMManagerAddDeviceInCertainVMThrowingIpFormatException(String ipAddress){
		this.ipAddress = ipAddress;
	}
	@Override
	public IDevice createDevice(String[] arguments) throws IPFormatException{
		throw new IPFormatException(ipAddress);
	}
}
