package com.vmware.talentboost.esx.devicespecifications;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devices.DeviceType;

/**
 * The user of this class has the ability to get the maximum number of supported
 * hard disks of certain hard disk controller
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public class HardDiskControllerSpecification extends DeviceSpecification {

	private int maxNumberOfSupportedHdds;
	public static final int NUM_OF_SPECS = 3;

	/**
	 * Class constructor specifying the id, maximum number of supported devices,
	 * and the device type
	 * 
	 * @param id
	 * @param maxNumberOfSupportedHdds
	 * @param deviceType
	 */
	public HardDiskControllerSpecification(AlphaNumericString id,
			int maxNumberOfSupportedHdds, DeviceType deviceType) {
		super(id, deviceType);
		this.maxNumberOfSupportedHdds = maxNumberOfSupportedHdds;
	}

	public int getMaxNumberOfSupportedHdds() {
		return maxNumberOfSupportedHdds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.devicespecifications.DeviceSpecification#toString
	 * ()
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Max number of supported hdds: "
				+ maxNumberOfSupportedHdds);
		return result.toString();
	}
}
