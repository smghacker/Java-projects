package com.vmware.talentboost.esx.commandstests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.commands.CreateVMCommand;
import com.vmware.talentboost.esx.vmmanagerstubs.VMManagerAddVMThrowingVMWithCetainIdExistsException;

public class CreateVMCommandTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testWithoutArguments() throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(NumberOfArgumentsException.class);
		String arguments = "";
		CreateVMCommand cmd = new CreateVMCommand(null);
		cmd.execute(arguments);
	}
	
	@Test
	public void testWithLessArguments1() throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(NumberOfArgumentsException.class);
		String arguments = "id 123123 2";
		CreateVMCommand cmd = new CreateVMCommand(null);
		cmd.execute(arguments);
	}
	
	@Test
	public void testWithLessArguments2() throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(NumberOfArgumentsException.class);
		String arguments = "id 'name' 123123";
		CreateVMCommand cmd = new CreateVMCommand(null);
		cmd.execute(arguments);
	}

	@Test
	public void testWithVMWithIdWhichExists() throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String arguments = "id 'name' 123123 2";
		String id = "id";
		thrown.expectMessage("There is already a VM with this id = " + id);
		VMManagerAddVMThrowingVMWithCetainIdExistsException vmManager =
				new VMManagerAddVMThrowingVMWithCetainIdExistsException(id);
		CreateVMCommand cmd = new CreateVMCommand(vmManager);
		cmd.execute(arguments);
	}
}
