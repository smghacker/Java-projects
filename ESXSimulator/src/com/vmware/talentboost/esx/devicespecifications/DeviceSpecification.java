package com.vmware.talentboost.esx.devicespecifications;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devices.DeviceType;

/**
 * @author Valeri Colov
 * @version 1.0
 *
 */
public class DeviceSpecification implements IDeviceSpecification {

	private AlphaNumericString id;
	private DeviceType typeOfDevice;

	/**
	 * Class constructor specifying the id and the type of new device
	 * specification
	 * 
	 * @param id
	 * @param typeOfDevice
	 */
	public DeviceSpecification(AlphaNumericString id, DeviceType typeOfDevice) {
		this.id = id;
		this.typeOfDevice = typeOfDevice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.devicespecifications.IDeviceSpecification#
	 * getId()
	 */
	@Override
	public AlphaNumericString getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.devicespecifications.IDeviceSpecification#
	 * getTypeOfDevice()
	 */
	@Override
	public DeviceType getTypeOfDevice() {
		return typeOfDevice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("ID: " + id.getAlphanumericString() + "\n");
		result.append("Type: " + typeOfDevice.toString() + "\n");
		return result.toString();
	}
}
