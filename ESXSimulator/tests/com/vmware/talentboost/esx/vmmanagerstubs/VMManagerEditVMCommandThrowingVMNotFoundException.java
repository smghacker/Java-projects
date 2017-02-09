package com.vmware.talentboost.esx.vmmanagerstubs;

import java.math.BigInteger;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericStringWithSpaces;
import com.vmware.talentboost.esx.vmexceptions.VMNotFoundException;

public class VMManagerEditVMCommandThrowingVMNotFoundException extends VirtualMachineManagerStub{
	private String id;
	public VMManagerEditVMCommandThrowingVMNotFoundException(String id) {
		this.id = id;
	}
	
	@Override
	public void editVM(AlphaNumericString vmId,
			AlphaNumericStringWithSpaces newName, BigInteger newRamSize) throws VMNotFoundException{
		throw new VMNotFoundException(id);
	}
}
