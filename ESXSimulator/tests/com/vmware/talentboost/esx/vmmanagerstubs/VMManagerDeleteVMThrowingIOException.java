package com.vmware.talentboost.esx.vmmanagerstubs;

import java.io.IOException;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;

public class VMManagerDeleteVMThrowingIOException extends
		VirtualMachineManagerStub {
	@Override
	public void deleteVM(AlphaNumericString vmId) throws IOException {
		throw new IOException();
	}
}
