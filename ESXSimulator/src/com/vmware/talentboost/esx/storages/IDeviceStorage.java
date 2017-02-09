package com.vmware.talentboost.esx.storages;

import java.util.Collection;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devices.IDevice;

/**
 * This interface provides basic functionality which every device storage must
 * have. The user of this interface can check whether the device storage
 * contains device with certain id, can add device to and delete device from the storage,
 * can access certain device and can get a collection of all devices in this
 * storage.
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public interface IDeviceStorage {
	boolean containsId(AlphaNumericString id);

	void addDevice(IDevice dev);

	void deleteDevice(AlphaNumericString id);

	IDevice getDevice(AlphaNumericString id);

	Collection<IDevice> getAllDevices();
}
