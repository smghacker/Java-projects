package com.vmware.talentboost.esx.managers;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devices.IDevice;

/**
 * 
 * The user of this interface is able to add and delete device to device
 * manager, check existence of a device with certain id, check whether in
 * certain device manager there is certain MAC or IP address and to get in
 * printable form all devices
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public interface IDeviceManager {
	void addDevice(IDevice newDevice);

	void deleteDevice(AlphaNumericString id);

	boolean doesExistDeviceWithId(AlphaNumericString id);

	boolean checkForIdenticalMacAddresses(String newMacAddress);

	boolean checkForIdenticalIpAddresses(String ipAddress);

	String listDevices();
}
