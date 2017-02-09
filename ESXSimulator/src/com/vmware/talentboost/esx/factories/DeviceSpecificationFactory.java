package com.vmware.talentboost.esx.factories;

import java.math.BigInteger;

import com.vmware.talentboost.esx.alphanumericexceptions.AlphaNumericStringConstructionException;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.devices.DeviceType;
import com.vmware.talentboost.esx.devicesexceptions.IPFormatException;
import com.vmware.talentboost.esx.devicesexceptions.MACAddresFormatException;
import com.vmware.talentboost.esx.devicespecifications.HardDiskControllerSpecification;
import com.vmware.talentboost.esx.devicespecifications.HardDiskSpecification;
import com.vmware.talentboost.esx.devicespecifications.IDEHardDiskControllerSpecification;
import com.vmware.talentboost.esx.devicespecifications.IDeviceSpecification;
import com.vmware.talentboost.esx.devicespecifications.NetworkCardSpecification;
import com.vmware.talentboost.esx.devicespecifications.SCSIHardDiskControllerSpecification;
import com.vmware.talentboost.esx.devicespecifications.VideoCardSpecification;
import com.vmware.talentboost.esx.memories.MemoryType;
import com.vmware.talentboost.esx.memories.VideoRAM;

/**
 * The purpose of this class is to provide the ability to the user to build a
 * device specification.
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public class DeviceSpecificationFactory {
	/**
	 * Builds a device specification using some command's arguments
	 * 
	 * @param arguments
	 * @return new device specification
	 * @throws IllegalArgumentException
	 * @throws NumberOfArgumentsException
	 */
	public static IDeviceSpecification build(String[] arguments)
			throws IllegalArgumentException, NumberOfArgumentsException {
		String devType = arguments[0];
		AlphaNumericString id;
		try {
			id = new AlphaNumericString(arguments[1]);
		} catch (AlphaNumericStringConstructionException e) {
			throw new IllegalArgumentException("ID should be alphanumeric. "
					+ e.getMessage());
		}
		if (devType.compareTo("VIDEO_CARD") == 0) {
			if (arguments.length < VideoCardSpecification.NUM_OF_SPECS) {
				throw new NumberOfArgumentsException(arguments.length);
			}
			return buildVideoCardSpec(arguments, id);
		} else if (devType.compareTo("NETWORK_CARD") == 0) {
			if (arguments.length < NetworkCardSpecification.NUM_OF_SPECS) {
				throw new NumberOfArgumentsException(arguments.length);
			}
			return buildNetworkCardSpec(arguments, id);
		} else if (devType.compareTo("HDD_CONTROLLER") == 0) {
			if (arguments.length < HardDiskControllerSpecification.NUM_OF_SPECS) {
				throw new NumberOfArgumentsException(arguments.length);
			}

			String typeOfController = arguments[2];
			if (typeOfController.compareTo("IDE") == 0) {
				return new IDEHardDiskControllerSpecification(id);
			} else if (typeOfController.compareTo("SCSI") == 0) {
				return new SCSIHardDiskControllerSpecification(id);
			} else {
				throw new IllegalArgumentException(
						"There is no such type of HDD controller as "
								+ typeOfController);
			}
		} else if (devType.compareTo("HDD") == 0) {
			if (arguments.length < HardDiskSpecification.NUM_OF_ARGUMENTS) {
				throw new NumberOfArgumentsException(arguments.length);
			}
			return buildHardDiskSpec(arguments, id);
		} else {
			throw new IllegalArgumentException(
					"There is no such type of device " + devType);
		}
	}

	/**
	 * Builds a device specification using arguments that have been read from a
	 * file
	 * 
	 * @param arguments
	 * @return new device specification
	 * @throws IllegalArgumentException
	 * @throws NumberOfArgumentsException
	 */
	public static IDeviceSpecification buildFromFile(String[] arguments)
			throws IllegalArgumentException, NumberOfArgumentsException {
		String devType = arguments[0];
		AlphaNumericString id;
		try {
			id = new AlphaNumericString(arguments[1]);
		} catch (AlphaNumericStringConstructionException e) {
			throw new IllegalArgumentException("ID should be alphanumeric. "
					+ e.getMessage());
		}
		if (devType.compareTo("VIDEO_CARD") == 0) {
			return buildVideoCardSpec(arguments, id);
		} else if (devType.compareTo("NETWORK_CARD") == 0) {
			return buildNetworkCardSpec(arguments, id);
		} else if (devType.compareTo(DeviceType.IDE_HARD_DISK_CONTROLLER
				.toString()) == 0) {
			return new IDEHardDiskControllerSpecification(id);
		} else if (devType.compareTo(DeviceType.SCSI_HARD_DISK_CONTROLLER
				.toString()) == 0) {
			return new SCSIHardDiskControllerSpecification(id);
		} else if (devType.compareTo(DeviceType.HARD_DISK.toString()) == 0) {
			return buildHardDiskSpec(arguments, id);
		} else {
			throw new IllegalArgumentException(
					"There is no such type of device " + devType);
		}
	}

	private static IDeviceSpecification buildVideoCardSpec(String[] arguments,
			AlphaNumericString id) {
		BigInteger size = BigInteger.ZERO;
		try {
			size = new BigInteger(arguments[2]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"Size of memory should be a number. " + e.getMessage());
		}
		int numberOfDisplays = 0;
		try {
			numberOfDisplays = Integer.parseInt(arguments[3]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"Number of displays should be a number. " + e.getMessage());
		}

		VideoRAM videoRAM = (VideoRAM) MemoryFactory.build(MemoryType.VideoRAM,
				size);
		VideoCardSpecification videoSpec = new VideoCardSpecification(id,
				videoRAM, numberOfDisplays);
		return videoSpec;
	}

	private static IDeviceSpecification buildNetworkCardSpec(
			String[] arguments, AlphaNumericString id) {
		String macAddress = arguments[2];
		String ip = arguments[3];
		try {
			NetworkCardSpecification netSpec = new NetworkCardSpecification(id,
					macAddress, ip);
			return netSpec;
		} catch (MACAddresFormatException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (IPFormatException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	private static IDeviceSpecification buildHardDiskSpec(String[] arguments,
			AlphaNumericString id) {
		try {
			long size = Long.parseLong(arguments[2]);
			AlphaNumericString controllerId = new AlphaNumericString(
					arguments[3]);
			return new HardDiskSpecification(id, size, controllerId);
		} catch (AlphaNumericStringConstructionException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"The size of hard disk should be a number" + e.getMessage());
		}
	}
}
