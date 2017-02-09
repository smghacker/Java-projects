package com.vmware.talentboost.esx.vmmanagerstubs;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.vmexceptions.VMNotFoundException;

public class VMManagerDeleteDeviceInCertainVMThrowingVMNotFoundException extends
		VirtualMachineManagerStub {
	private String id;

	public VMManagerDeleteDeviceInCertainVMThrowingVMNotFoundException(String id) {
		this.id = id;
	}

	@Override
	public void deleteDeviceInCertainVM(AlphaNumericString vmId,
			AlphaNumericString devId) throws VMNotFoundException {
		throw new VMNotFoundException(id);
	}
}
