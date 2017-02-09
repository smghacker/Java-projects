package com.vmware.talentboost.esx.devices;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devicespecifications.DeviceSpecification;

/**
 * Base, abstract class which have the functionality:
 * 	1.To initialize its specification
 *  2.To return the id of the device
 *  3.To return the device type
 *  4.To return nice String representation of each device
 * @author Valeri Colov
 * @version 1.0
 */
public abstract class Device implements IDevice {

	private DeviceSpecification specification;

	/**
	 * Class constructor specifying the concrete device specification
	 * 
	 * @param specification
	 * @see com.vmware.talentboost.esx.devicespecifications
	 */
	public Device(DeviceSpecification specification) {
		this.specification = specification;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.devices.IDevice#getId()
	 */
	@Override
	public AlphaNumericString getId() {
		return specification.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.devices.IDevice#getType()
	 */
	@Override
	public DeviceType getType() {
		return specification.getTypeOfDevice();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.devices.IDevice#getOtherSpecs()
	 */
	@Override
	public abstract String getOtherSpecs();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Device\n");
		result.append(specification.toString());
		result.append(getOtherSpecs());
		return result.toString();
	}
}
