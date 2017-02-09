package com.vmware.talentboost.esx.vmmanagerstubs;

import java.io.IOException;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;

public class VMManagerSaveVMThrowingIOException extends
		VirtualMachineManagerStub {
	@Override
	public void saveVM(AlphaNumericString id) throws IOException{
		throw new IOException();
	}
}
