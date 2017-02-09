package com.vmware.talentboost.esx.commandstests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.commands.AddDeviceCommand;
import com.vmware.talentboost.esx.devicesexceptions.DeviceWithThisIdAlreadyExistsException;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameIpExistsException;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameMacExistsException;
import com.vmware.talentboost.esx.vmexceptions.VMNotFoundException;
import com.vmware.talentboost.esx.vmmanagerstubs.VMManagerAddDeviceInCertainVMThrowingDeviceWithThisIsAlreadyExistsException;
import com.vmware.talentboost.esx.vmmanagerstubs.VMManagerAddDeviceInCertainVMThrowingIpFormatException;
import com.vmware.talentboost.esx.vmmanagerstubs.VMManagerAddDeviceInCertainVMThrowingMacFormatException;
import com.vmware.talentboost.esx.vmmanagerstubs.VMManagerAddDeviceInCertainVMThrowingNetworkCardWithTheSameIpExists;
import com.vmware.talentboost.esx.vmmanagerstubs.VMManagerAddDeviceInCertainVMThrowingNetworkCardWithTheSameMacExists;

public class AddDeviceCommandTests {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testWithoutArguments() throws IllegalArgumentException,
			NumberOfArgumentsException {
		thrown.expect(NumberOfArgumentsException.class);
		String arguments = "";
		AddDeviceCommand cmd = new AddDeviceCommand(null);
		cmd.execute(arguments);
	}

	@Test
	public void testWithVMIdConsistingOfForbiddenCharacters()
			throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String id = "id_1";
		thrown.expectMessage("The input string " + id
				+ " consists forbidden characters. It should be alphanumeric!");
		AddDeviceCommand cmd = new AddDeviceCommand(null);
		cmd.execute(id);
	}

	@Test
	public void testPutADeviceWithIdAlreadyExists()
			throws IllegalArgumentException, NumberOfArgumentsException,
			DeviceWithThisIdAlreadyExistsException,
			NetworkCardWithTheSameMacExistsException, VMNotFoundException,
			NetworkCardWithTheSameIpExistsException {
		thrown.expect(IllegalArgumentException.class);
		String id = "id1";

		thrown.expectMessage("Device with this " + id
				+ " already exists in this VM.");
		VMManagerAddDeviceInCertainVMThrowingDeviceWithThisIsAlreadyExistsException vmManager = new VMManagerAddDeviceInCertainVMThrowingDeviceWithThisIsAlreadyExistsException(
				id);
		AddDeviceCommand cmd = new AddDeviceCommand(vmManager);
		cmd.execute("id1");
	}

	@Test
	public void testPutANetworkCardWithMACAlreadyExists()
			throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String id = "id1";
		String macAddress = "1A-1A-1A-1A-1A-1A";
		thrown.expectMessage("Network card with this MAC " + macAddress
				+ " already exists");
		VMManagerAddDeviceInCertainVMThrowingNetworkCardWithTheSameMacExists vmManager = new VMManagerAddDeviceInCertainVMThrowingNetworkCardWithTheSameMacExists(
				macAddress);
		AddDeviceCommand cmd = new AddDeviceCommand(vmManager);
		cmd.execute(id);
	}

	@Test
	public void testPutANetworkCardWithIPAlreadyExists()
			throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String id = "id1";
		String ipAddress = "127.0.0.1";
		thrown.expectMessage("Network card with this IP " + ipAddress
				+ " already exists");
		VMManagerAddDeviceInCertainVMThrowingNetworkCardWithTheSameIpExists vmManager = new VMManagerAddDeviceInCertainVMThrowingNetworkCardWithTheSameIpExists(
				ipAddress);
		AddDeviceCommand cmd = new AddDeviceCommand(vmManager);
		cmd.execute(id);
	}

	@Test
	public void testPutANetworkCardWithWrongFormatOfMAC()
			throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String id = "id1";
		String macAddress = "1a-1c-1a-1k-1q-1k";
		thrown.expectMessage("Provided MAC address "
				+ macAddress
				+ "  is invalid. MAC address should be in the format HJ-KL-GF-DS-AB-CX where  H,J,K,L,G,F,D,S,A,B,C,X are some hexadecimal digits {0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,A,B,C,D,E,F}");
		VMManagerAddDeviceInCertainVMThrowingMacFormatException vmManager =
				new VMManagerAddDeviceInCertainVMThrowingMacFormatException(macAddress);
		AddDeviceCommand cmd = new AddDeviceCommand(vmManager);
		cmd.execute(id);
	}

	@Test
	public void testPutANetworkCardWithWrongFormatOfIP()
			throws IllegalArgumentException, NumberOfArgumentsException {
		thrown.expect(IllegalArgumentException.class);
		String id = "id1";
		String ipAddress = "290.290.1.1";
		thrown.expectMessage("Provided IP address " + ipAddress + "  is invalid. IP address should be in the format H.M.L.K where H,M,L,K are number in the interval [0, 255]");
		VMManagerAddDeviceInCertainVMThrowingIpFormatException vmManager =
				new VMManagerAddDeviceInCertainVMThrowingIpFormatException(ipAddress);
		AddDeviceCommand cmd = new AddDeviceCommand(vmManager);
		cmd.execute(id);
	}
}
