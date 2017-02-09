package com.vmware.talentboost.esx.factories;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

import com.vmware.talentboost.esx.alphanumericexceptions.AlphaNumericStringConstructionException;
import com.vmware.talentboost.esx.alphanumericexceptions.AlphaNumericStringWithSpaceConstructionException;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericStringWithSpaces;
import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.devices.DeviceType;
import com.vmware.talentboost.esx.devices.IDevice;
import com.vmware.talentboost.esx.devicesexceptions.IPFormatException;
import com.vmware.talentboost.esx.devicesexceptions.MACAddresFormatException;
import com.vmware.talentboost.esx.devicesexceptions.NetworkCardWithTheSameMacExistsException;
import com.vmware.talentboost.esx.managers.DeviceManager;
import com.vmware.talentboost.esx.memories.MemoryType;
import com.vmware.talentboost.esx.memories.RAM;
import com.vmware.talentboost.esx.storages.DeviceStorageWithHashMap;
import com.vmware.talentboost.esx.vms.IVirtualMachine;
import com.vmware.talentboost.esx.vms.VirtualMachine;

/** 
 * @author Valeri Colov
 * @version 1.0
 */
public class VirtualMachineFactory {
	/**
	 * Builds a virtual machine from the command arguments
	 * @param arguments
	 * @return new virtual machine
	 * @throws IllegalArgumentException
	 */
	public static IVirtualMachine build(String[] arguments)
			throws IllegalArgumentException {
		AlphaNumericString id = null;
		try {
			id = new AlphaNumericString(arguments[0]);
		} catch (AlphaNumericStringConstructionException e) {
			throw new IllegalArgumentException(
					"Given ID = "
							+ arguments[0]
							+ "  consists of forbidden symbols. It should consist only alphanumeric ones!");
		}

		AlphaNumericStringWithSpaces name = null;
		try {
			name = new AlphaNumericStringWithSpaces(arguments[1]);
		} catch (AlphaNumericStringWithSpaceConstructionException e) {
			throw new IllegalArgumentException(
					"Given Name = "
							+ arguments[1]
							+ "  consists of forbidden symbols. It should consist only alphanumeric symbols and space!");
		}
		BigInteger sizeOfMemory = new BigInteger(arguments[2]);
		RAM ram = (RAM) MemoryFactory.build(MemoryType.RAM, sizeOfMemory);
		int numCPUs = Integer.parseInt(arguments[3]);
		if (numCPUs < 1 || numCPUs > 8) {
			//TODO:refactor
			throw new IllegalArgumentException(
					"The number of cpus must be between 1 and 8");
		}
		return new VirtualMachine(name, id, ram, new DeviceManager(
				new DeviceStorageWithHashMap()), numCPUs);
	}

	/**
	 * Builds a virtual machine from file
	 * @param pathToFile
	 * @return new virtual machine
	 * @throws IllegalArgumentException
	 * @throws NumberOfArgumentsException
	 * @throws FileNotFoundException
	 * @throws MACAddresFormatException
	 * @throws IPFormatException
	 */
	public static IVirtualMachine buildFromFile(String pathToFile)
			throws IllegalArgumentException, NumberOfArgumentsException,
			FileNotFoundException, MACAddresFormatException, IPFormatException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				pathToFile));
		IVirtualMachine vm = null;
		try {
			String singleLine = bufferedReader.readLine();
			while (singleLine != null) {
				if (singleLine.compareTo("Virtual Machine") == 0) {
					vm = buildVMFromFile(bufferedReader);
				} else if (singleLine.compareTo("Device") == 0) {
					IDevice newDevice = null;
					singleLine = bufferedReader.readLine();
					String[] arguments = singleLine.split(": ");
					String id = arguments[1];
					singleLine = bufferedReader.readLine();
					arguments = singleLine.split(": ");
					String deviceType = arguments[1];
					if (deviceType
							.compareTo(DeviceType.NETWORK_CARD.toString()) == 0) {
						newDevice = buildNetworkCardFromAFile(bufferedReader,
								id, deviceType);
					} else if (deviceType.compareTo(DeviceType.VIDEO_CARD
							.toString()) == 0) {
						newDevice = buildMemoryFromFile(bufferedReader, id,
								deviceType);
					} else if (deviceType
							.compareTo(DeviceType.IDE_HARD_DISK_CONTROLLER
									.toString()) == 0) {
						//bufferedReader.readLine();
						newDevice = DeviceFactory.buildFromFile(new String[] {
								deviceType, id });
					} else if (deviceType
							.compareTo(DeviceType.SCSI_HARD_DISK_CONTROLLER
									.toString()) == 0) {
						//bufferedReader.readLine();
						newDevice = DeviceFactory.buildFromFile(new String[] {
								deviceType, id });
					} else if (deviceType.compareTo(DeviceType.HARD_DISK
							.toString()) == 0) {
						newDevice = buildHardDiskFromFile(bufferedReader, id, deviceType);
					} else {
						throw new IllegalArgumentException(
								"There is no such type of device as "
										+ deviceType);
					}
					vm.addDevice(newDevice);
				}
				singleLine = bufferedReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NetworkCardWithTheSameMacExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vm;
	}

	private static IDevice buildHardDiskFromFile(BufferedReader bufferedReader,
			String id, String deviceType) throws IOException,
			NumberOfArgumentsException, MACAddresFormatException,
			IPFormatException {
		String singleLine = bufferedReader.readLine();
		String size = singleLine.split(": ")[1];
		singleLine = bufferedReader.readLine();
		String controllerId = singleLine.split(": ")[1];
		IDevice newDevice = DeviceFactory.buildFromFile(new String[] {
				deviceType, id, size, controllerId });
		
		return newDevice;
	}


	private static IDevice buildMemoryFromFile(BufferedReader bufferedReader,
			String id, String deviceType) throws IOException,
			NumberOfArgumentsException, MACAddresFormatException,
			IPFormatException {
		IDevice newDevice = null;
		String singleLine = bufferedReader.readLine();
		//String memoryType = singleLine.split(": ")[1];
		singleLine = bufferedReader.readLine();
		String memSize = singleLine.split(": ")[1].split(" ")[0];
		singleLine = bufferedReader.readLine();
		String numberOfDisplays = singleLine.split(": ")[1];
		newDevice = DeviceFactory.buildFromFile(new String[] { deviceType, id,
				memSize, numberOfDisplays });
		return newDevice;
	}


	private static IDevice buildNetworkCardFromAFile(
			BufferedReader bufferedReader, String id, String deviceType)
			throws IOException, NumberOfArgumentsException,
			MACAddresFormatException, IPFormatException,
			NetworkCardWithTheSameMacExistsException {
		String singleLine;
		singleLine = bufferedReader.readLine();
		String macAddress = singleLine.split(":")[1];
		singleLine = bufferedReader.readLine();
		String ipAddress = singleLine.split(":")[1];
		IDevice newDevice1 = DeviceFactory.buildFromFile(new String[] {
				deviceType, id, macAddress, ipAddress });
		return newDevice1;
	}
	
	private static IVirtualMachine buildVMFromFile(BufferedReader bufferedReader)
			throws IOException {
		IVirtualMachine vm = null;
		String singleLine1 = bufferedReader.readLine();
		String name = singleLine1.split(": ")[1];
		singleLine1 = bufferedReader.readLine();
		String id = singleLine1.split(": ")[1];
		singleLine1 = bufferedReader.readLine();
		//String memoryType = singleLine1.split(": ")[1];
		singleLine1 = bufferedReader.readLine();
		String memSize = singleLine1.split(": ")[1].split(" ")[0];
		singleLine1 = bufferedReader.readLine();
		String numCPUs = singleLine1.split(": ")[1];
		vm = VirtualMachineFactory.build(new String[] { id, name, memSize,
				numCPUs });
		return vm;
	}
}
