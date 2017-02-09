package com.vmware.talentboost.esx.vmmanagerstubs;

import com.vmware.talentboost.esx.vmexceptions.VMWithCertainIdExistsException;
import com.vmware.talentboost.esx.vms.IVirtualMachine;

public class VMManagerAddVMThrowingVMWithCetainIdExistsException extends
		VirtualMachineManagerStub {
	private String id;
	public VMManagerAddVMThrowingVMWithCetainIdExistsException(String id){
		this.id = id;
	}
	
	@Override
	public void addVM(IVirtualMachine vm, boolean override) throws VMWithCertainIdExistsException{
		throw new VMWithCertainIdExistsException(id);
	}
}
