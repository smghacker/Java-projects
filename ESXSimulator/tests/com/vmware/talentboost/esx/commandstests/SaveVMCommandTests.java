package com.vmware.talentboost.esx.commandstests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.commands.SaveVMCommand;
import com.vmware.talentboost.esx.vmmanagerstubs.VMManagerSaveVMThrowingIOException;
import com.vmware.talentboost.esx.vmmanagerstubs.VMManagerSaveVMThrowingVMNotFoundException;

public class SaveVMCommandTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testWithoutArguments() throws IllegalArgumentException,
			NumberOfArgumentsException {
		thrown.expect(NumberOfArgumentsException.class);
		String arguments = "";
		SaveVMCommand cmd = new SaveVMCommand(null);
		cmd.execute(arguments);
	}

	@Test
	public void testWithWrongFormatOfVMId() throws IllegalArgumentException,
			NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String arguments = "id_1";
		SaveVMCommand cmd = new SaveVMCommand(null);
		cmd.execute(arguments);
	}

	@Test
	public void testWhenSomeIOErrorOccur() throws IllegalArgumentException,
			NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String arguments = "id1";
		VMManagerSaveVMThrowingIOException vmManager = new VMManagerSaveVMThrowingIOException();
		SaveVMCommand cmd = new SaveVMCommand(vmManager);
		cmd.execute(arguments);
	}

	@Test
	public void testWhenVMIsNotFound() throws IllegalArgumentException,
			NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String arguments = "id1";
		VMManagerSaveVMThrowingVMNotFoundException vmManager = new VMManagerSaveVMThrowingVMNotFoundException(arguments);
		SaveVMCommand cmd = new SaveVMCommand(vmManager);
		cmd.execute(arguments);
	}

}
