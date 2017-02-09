package com.vmware.talentboost.esx.managers;

import java.util.Map;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devices.DeviceType;
import com.vmware.talentboost.esx.devices.HardDisk;
import com.vmware.talentboost.esx.devices.HardDiskController;
import com.vmware.talentboost.esx.devices.IDevice;
import com.vmware.talentboost.esx.devices.NetworkCard;
import com.vmware.talentboost.esx.storages.IDeviceStorage;

/**
 * The purpose of this class is to holds and manages devices of exactly one
 * virtual machine. Device manager defines the maximal number of IDE and SCSI
 * controllers which one virtual machine can have.
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public class DeviceManager implements IDeviceManager {

	private static final int MAX_NUMBER_OF_IDE = 1;
	private static final int MAX_NUMBER_OF_SCSI = 4;
	private IDeviceStorage devices;
	private int numberOfIDE;
	private int numberOfSCSI;

	/**
	 * Class constructor specifying the device storage which will be used and
	 * setting the curent number of IDE controllers and the current number of
	 * SCSI controllers
	 * 
	 * @param devices
	 */
	public DeviceManager(IDeviceStorage devices) {
		this.devices = devices;
		this.numberOfIDE = 0;
		this.numberOfSCSI = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.managers.IDeviceManager#addDevice(com.vmware
	 * .talentboost.esx.devices.IDevice)
	 */
	@Override
	public void addDevice(IDevice newDevice) throws IllegalArgumentException {
		if (newDevice.getType() == DeviceType.IDE_HARD_DISK_CONTROLLER) {
			if (numberOfIDE <= MAX_NUMBER_OF_IDE) {
				numberOfIDE++;
			} else {
				throw new IllegalArgumentException(
						"There is no place for this IDE controller "
								+ newDevice.getId().getAlphanumericString()
								+ ". Maximal number of supported IDE controllers is "
								+ MAX_NUMBER_OF_IDE);
			}
		} else if (newDevice.getType() == DeviceType.SCSI_HARD_DISK_CONTROLLER) {
			if (numberOfSCSI <= MAX_NUMBER_OF_SCSI) {
				numberOfSCSI++;
			} else {
				throw new IllegalArgumentException(
						"There is no place for this SCSI controller "
								+ newDevice.getId().getAlphanumericString()
								+ ". Maximal number of supported SCSI controllers is "
								+ MAX_NUMBER_OF_SCSI);
			}
		} else if (newDevice.getType() == DeviceType.HARD_DISK) {
			((HardDiskController) devices.getDevice(((HardDisk) newDevice)
					.getIdOfController())).addHardDisk(((HardDisk) newDevice));
		}
		devices.addDevice(newDevice);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.managers.IDeviceManager#deleteDevice(com.vmware
	 * .talentboost.esx.alphanumerics.AlphaNumericString)
	 */
	@Override
	public void deleteDevice(AlphaNumericString id) {
		IDevice device = devices.getDevice(id);
		if (device.getType() == DeviceType.IDE_HARD_DISK_CONTROLLER) {
			Map<String, HardDisk> allHardDisks = ((HardDiskController) device)
					.getHardDisks();
			for (HardDisk hdd : allHardDisks.values()) {
				deleteDevice(hdd.getId());
			}
			numberOfIDE--;

		} else if (device.getType() == DeviceType.SCSI_HARD_DISK_CONTROLLER) {
			Map<String, HardDisk> allHardDisks = ((HardDiskController) device)
					.getHardDisks();
			for (HardDisk hdd : allHardDisks.values()) {
				deleteDevice(hdd.getId());
			}
			numberOfSCSI--;
		}
		devices.deleteDevice(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.managers.IDeviceManager#doesExistDeviceWithId
	 * (com.vmware.talentboost.esx.alphanumerics.AlphaNumericString)
	 */
	@Override
	public boolean doesExistDeviceWithId(AlphaNumericString id) {
		return devices.containsId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.managers.IDeviceManager#
	 * checkForIdenticalMacAddresses(java.lang.String)
	 */
	@Override
	public boolean checkForIdenticalMacAddresses(String newMacAddress) {
		boolean doesExistNetworkCardWithTheSameMac = false;
		for (IDevice dev : devices.getAllDevices()) {
			if (dev.getType() == DeviceType.NETWORK_CARD) {
				String devMacAddress = ((NetworkCard) dev).getMacAddress()
						.toLowerCase();
				if (devMacAddress.compareTo(newMacAddress) == 0) {
					doesExistNetworkCardWithTheSameMac = true;
					break;
				}
			}
		}
		return doesExistNetworkCardWithTheSameMac;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.managers.IDeviceManager#
	 * checkForIdenticalIpAddresses(java.lang.String)
	 */
	@Override
	public boolean checkForIdenticalIpAddresses(String ipAddress) {
		boolean doesExistNetworkCardWithTheSameIp = false;
		for (IDevice dev : devices.getAllDevices()) {
			if (dev.getType() == DeviceType.NETWORK_CARD) {
				String devIpAddress = ((NetworkCard) dev).getIpAddress();
				if (devIpAddress.compareTo(ipAddress) == 0) {
					doesExistNetworkCardWithTheSameIp = true;
					break;
				}
			}
		}
		return doesExistNetworkCardWithTheSameIp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.managers.IDeviceManager#listDevices()
	 */
	@Override
	public String listDevices() {
		StringBuilder result = new StringBuilder();
		for (IDevice dev : devices.getAllDevices()) {
			if (dev.getType() != DeviceType.HARD_DISK) {
				result.append(dev.toString() + "\n");
			}
		}
		return result.toString();
	}

}
