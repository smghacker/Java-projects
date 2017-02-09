package com.vmware.talentboost.esx.storages;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devices.IDevice;

@Component
public class DeviceStorageWithHashMap implements IDeviceStorage {

	private Map<String, IDevice> allDevices = new HashMap<String, IDevice>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.storages.IDeviceStorage#containsId(com.vmware
	 * .talentboost.esx.alphanumerics.AlphaNumericString)
	 */
	@Override
	public boolean containsId(AlphaNumericString id) {
		return allDevices.containsKey(id.getAlphanumericString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.storages.IDeviceStorage#addDevice(com.vmware
	 * .talentboost.esx.devices.IDevice)
	 */
	@Override
	public void addDevice(IDevice dev) {
		allDevices.put(dev.getId().getAlphanumericString(), dev);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.storages.IDeviceStorage#deleteDevice(com.vmware
	 * .talentboost.esx.alphanumerics.AlphaNumericString)
	 */
	@Override
	public void deleteDevice(AlphaNumericString id) {
		allDevices.remove(id.getAlphanumericString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.storages.IDeviceStorage#getDevice(com.vmware
	 * .talentboost.esx.alphanumerics.AlphaNumericString)
	 */
	@Override
	public IDevice getDevice(AlphaNumericString id) {
		return allDevices.get(id.getAlphanumericString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.storages.IDeviceStorage#getAllDevices()
	 */
	@Override
	public Collection<IDevice> getAllDevices() {
		return allDevices.values();
	}

}
