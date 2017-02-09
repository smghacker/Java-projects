package com.vmware.talentboost.esx.vmmanagerstubs;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.vmexceptions.VMNotFoundException;

public class VMManagerSaveVMThrowingVMNotFoundException extends
		VirtualMachineManagerStub {
	private String id;
	public VMManagerSaveVMThrowingVMNotFoundException(String id) {
		this.id = id;
	}
	
	@Override
	public void saveVM(AlphaNumericString vmId) throws VMNotFoundException{
		throw new VMNotFoundException(id);
	}
}
