package com.vmware.talentboost.esx.commandstests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.commands.EditVMCommand;
import com.vmware.talentboost.esx.vmmanagerstubs.VMManagerEditVMCommandThrowingVMNotFoundException;

public class EditVMCommandTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testWithoutArguments() throws IllegalArgumentException,
			NumberOfArgumentsException {
		thrown.expect(NumberOfArgumentsException.class);
		String arguments = "";
		EditVMCommand cmd = new EditVMCommand(null);
		cmd.execute(arguments);
	}
	
	@Test
	public void testWithLessArgumentsThanRequired()
			throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(NumberOfArgumentsException.class);
		String arguments = "id";
		EditVMCommand cmd = new EditVMCommand(null);
		cmd.execute(arguments);
	}
	
	@Test
	public void testWithWrongFormatOfVMId()
			throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String arguments = "id_1 'name' 123 1";
		EditVMCommand cmd = new EditVMCommand(null);
		cmd.execute(arguments);
	}
	
	@Test
	public void testWithWrongFormatOfVMName()
			throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String arguments = "id1 'na_me' 123 1";
		EditVMCommand cmd = new EditVMCommand(null);
		cmd.execute(arguments);
	}
	
	@Test
	public void testWhenTheVMIsNotFound()
			throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String arguments = "id1 'name' 123 1";
		String id = "id1";
		VMManagerEditVMCommandThrowingVMNotFoundException vmManager =
				new VMManagerEditVMCommandThrowingVMNotFoundException(id);
		EditVMCommand cmd = new EditVMCommand(vmManager);
		cmd.execute(arguments);
	}

}
 