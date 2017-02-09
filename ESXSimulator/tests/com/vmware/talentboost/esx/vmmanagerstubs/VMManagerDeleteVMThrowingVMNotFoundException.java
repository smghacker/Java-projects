package com.vmware.talentboost.esx.vmmanagerstubs;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.vmexceptions.VMNotFoundException;

public class VMManagerDeleteVMThrowingVMNotFoundException extends
		VirtualMachineManagerStub {
	private String id;
	public VMManagerDeleteVMThrowingVMNotFoundException(String id) {
		this.id = id;
	}
	
	@Override
	public void deleteVM(AlphaNumericString vmId) throws VMNotFoundException{
		throw new VMNotFoundException(id);
	}
}
