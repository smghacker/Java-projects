package com.vmware.talentboost.esx.devices;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;

/**
 * This interface represents the basic functionality of devices. The user of
 * this interface can access the id of the device name, type of device and get
 * in a string format other specifications.
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public interface IDevice {
	AlphaNumericString getId();

	DeviceType getType();

	String getOtherSpecs();
}
