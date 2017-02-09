package com.vmware.talentboost.esx.commandstests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.commands.DeleteDeviceCommand;
import com.vmware.talentboost.esx.vmmanagerstubs.VMManagerDeleteDeviceInCertainVMThrowingVMNotFoundException;

public class DeleteDeviceCommandTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testWithoutArguments() throws IllegalArgumentException,
			NumberOfArgumentsException {
		thrown.expect(NumberOfArgumentsException.class);
		String arguments = "";
		DeleteDeviceCommand cmd = new DeleteDeviceCommand(null);
		cmd.execute(arguments);
	}

	@Test
	public void testWithLessArgumentsThanRequired()
			throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(NumberOfArgumentsException.class);
		String arguments = "id";
		DeleteDeviceCommand cmd = new DeleteDeviceCommand(null);
		cmd.execute(arguments);
	}

	@Test
	public void testWithWrongFormatOfVMId()
			throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String arguments = "id_1 id2";
		DeleteDeviceCommand cmd = new DeleteDeviceCommand(null);
		cmd.execute(arguments);
	}
	
	@Test
	public void testWithWrongFormatOfDeviceId()
			throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String arguments = "id1 id_2";
		DeleteDeviceCommand cmd = new DeleteDeviceCommand(null);
		cmd.execute(arguments);
	}
	
	@Test
	public void testWhenAVmIsNotFound() throws IllegalArgumentException, NumberOfArgumentsException{
		thrown.expect(IllegalArgumentException.class);
		String arguments = "id1 id2";
		VMManagerDeleteDeviceInCertainVMThrowingVMNotFoundException vmManager =
				new VMManagerDeleteDeviceInCertainVMThrowingVMNotFoundException("id1");
		DeleteDeviceCommand cmd = new DeleteDeviceCommand(vmManager);
		cmd.execute(arguments);
	}
}
