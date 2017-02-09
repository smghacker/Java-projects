package com.vmware.talentboost.esx.devicespecifications;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devices.DeviceType;

/**
 * This interface represents the basic functionality of device specification
 * objects. The user of this interface can access the id and the type of the
 * device.
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public interface IDeviceSpecification {
	public AlphaNumericString getId();

	public DeviceType getTypeOfDevice();
}
