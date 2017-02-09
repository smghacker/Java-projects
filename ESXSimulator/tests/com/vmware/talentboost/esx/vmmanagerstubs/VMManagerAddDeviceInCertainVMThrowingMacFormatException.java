/**
 * 
 */
package com.vmware.talentboost.esx.vmmanagerstubs;

import com.vmware.talentboost.esx.devices.IDevice;
import com.vmware.talentboost.esx.devicesexceptions.MACAddresFormatException;

/**
 * @author Ghost
 *
 */
public class VMManagerAddDeviceInCertainVMThrowingMacFormatException extends
		VirtualMachineManagerStub {
	private String wrongMacAddress;
	
	public VMManagerAddDeviceInCertainVMThrowingMacFormatException(String wrongMacAddress){
		this.wrongMacAddress = wrongMacAddress;
	}
	@Override
	public IDevice createDevice(String[] arguments) throws MACAddresFormatException{
		throw new MACAddresFormatException(wrongMacAddress);
	}
}

