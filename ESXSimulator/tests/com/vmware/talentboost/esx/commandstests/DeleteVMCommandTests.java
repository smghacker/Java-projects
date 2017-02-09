package com.vmware.talentboost.esx.commandstests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.commands.DeleteVMCommand;
import com.vmware.talentboost.esx.vmmanagerstubs.VMManagerDeleteVMThrowingIOException;
import com.vmware.talentboost.esx.vmmanagerstubs.VMManagerDeleteVMThrowingVMNotFoundException;

public class DeleteVMCommandTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testWithoutArguments() throws IllegalArgumentException,
			NumberOfArgumentsException {
		thrown.expect(NumberOfArgumentsException.class);
		String arguments = "";
		DeleteVMCommand cmd = new DeleteVMCommand(null);
		cmd.execute(arguments);
	}
	
	@Test
	public void testWithWrongFormatOfVMId()
			throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String arguments = "id_1";
		DeleteVMCommand cmd = new DeleteVMCommand(null);
		cmd.execute(arguments);
	}
	
	@Test
	public void testWhenTheVMIsNotFound() throws IllegalArgumentException, NumberOfArgumentsException{
		thrown.expect(IllegalArgumentException.class);
		String id = "id1";
		VMManagerDeleteVMThrowingVMNotFoundException vmManager =
				new VMManagerDeleteVMThrowingVMNotFoundException(id);
		DeleteVMCommand cmd = new DeleteVMCommand(vmManager);
		cmd.execute(id);
	}
	
	@Test
	public void testWhenThereIsSomeIOException() throws IllegalArgumentException, NumberOfArgumentsException{
		thrown.expect(IllegalArgumentException.class);
		String id = "id1";
		VMManagerDeleteVMThrowingIOException vmManager =
				new VMManagerDeleteVMThrowingIOException();
		DeleteVMCommand cmd = new DeleteVMCommand(vmManager);
		cmd.execute(id);
	}

}
