package com.vmware.talentboost.esx.factories;

import com.vmware.talentboost.esx.argumentexceptions.NumberOfArgumentsException;
import com.vmware.talentboost.esx.devices.DeviceType;
import com.vmware.talentboost.esx.devices.HardDisk;
import com.vmware.talentboost.esx.devices.HardDiskController;
import com.vmware.talentboost.esx.devices.IDevice;
import com.vmware.talentboost.esx.devices.NetworkCard;
import com.vmware.talentboost.esx.devices.VideoCard;
import com.vmware.talentboost.esx.devicesexceptions.IPFormatException;
import com.vmware.talentboost.esx.devicesexceptions.MACAddresFormatException;
import com.vmware.talentboost.esx.devicespecifications.HardDiskControllerSpecification;
import com.vmware.talentboost.esx.devicespecifications.HardDiskSpecification;
import com.vmware.talentboost.esx.devicespecifications.IDeviceSpecification;
import com.vmware.talentboost.esx.devicespecifications.NetworkCardSpecification;
import com.vmware.talentboost.esx.devicespecifications.VideoCardSpecification;

/**
 * The purpose of this class is to provide the ability to the user to build a
 * device.
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public class DeviceFactory {
	/**
	 * Builds a device using some command's arguments
	 * 
	 * @param arguments
	 * @return the new device
	 * @throws IllegalArgumentException
	 *             when the input device type isn't equal to the existing one
	 * @throws NumberOfArgumentsException
	 *             when the arguments of the command are not enough
	 * @throws IPFormatException
	 * @throws MACAddresFormatException
	 */
	public static IDevice build(String[] arguments)
			throws IllegalArgumentException, NumberOfArgumentsException,
			IPFormatException, MACAddresFormatException {
		if (arguments.length < 2) {
			throw new NumberOfArgumentsException(arguments.length);
		}
		IDeviceSpecification deviceSpecification = DeviceSpecificationFactory
				.build(arguments);
		if (deviceSpecification.getTypeOfDevice() == DeviceType.NETWORK_CARD) {
			return new NetworkCard(
					(NetworkCardSpecification) deviceSpecification);
		} else if (deviceSpecification.getTypeOfDevice() == DeviceType.VIDEO_CARD) {
			return new VideoCard((VideoCardSpecification) deviceSpecification);
		} else if ((deviceSpecification.getTypeOfDevice() == DeviceType.IDE_HARD_DISK_CONTROLLER)
				|| (deviceSpecification.getTypeOfDevice() == DeviceType.SCSI_HARD_DISK_CONTROLLER)) {
			return new HardDiskController(
					(HardDiskControllerSpecification) deviceSpecification);
		} else if ((deviceSpecification.getTypeOfDevice() == DeviceType.HARD_DISK)) {
			return new HardDisk((HardDiskSpecification) deviceSpecification);
		} else {
			throw new IllegalArgumentException(
					"There is no such type of device "
							+ deviceSpecification.getTypeOfDevice());
		}
	}

	/**
	 * Builds a device which specifications are written in a file
	 * 
	 * @param arguments
	 * @return
	 * @throws IllegalArgumentException
	 *             when the input device type isn't equal to the existing one
	 * @throws NumberOfArgumentsException
	 *             when the arguments of the command are not enough
	 * @throws MACAddresFormatException
	 * @throws IPFormatException
	 */
	public static IDevice buildFromFile(String[] arguments)
			throws IllegalArgumentException, NumberOfArgumentsException,
			MACAddresFormatException, IPFormatException {
		if (arguments.length < 2) {
			throw new NumberOfArgumentsException(arguments.length);
		}
		IDeviceSpecification deviceSpecification = DeviceSpecificationFactory
				.buildFromFile(arguments);
		if (deviceSpecification.getTypeOfDevice() == DeviceType.NETWORK_CARD) {
			return new NetworkCard(
					(NetworkCardSpecification) deviceSpecification);
		} else if (deviceSpecification.getTypeOfDevice() == DeviceType.VIDEO_CARD) {
			return new VideoCard((VideoCardSpecification) deviceSpecification);
		} else if ((deviceSpecification.getTypeOfDevice() == DeviceType.IDE_HARD_DISK_CONTROLLER)
				|| (deviceSpecification.getTypeOfDevice() == DeviceType.SCSI_HARD_DISK_CONTROLLER)) {
			return new HardDiskController(
					(HardDiskControllerSpecification) deviceSpecification);
		} else if ((deviceSpecification.getTypeOfDevice() == DeviceType.HARD_DISK)) {
			return new HardDisk((HardDiskSpecification) deviceSpecification);
		} else {
			throw new IllegalArgumentException(
					"There is no such type of device "
							+ deviceSpecification.getTypeOfDevice());
		}
	}
}
