package com.vmware.talentboost.esx.devicespecifications;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devices.DeviceType;

/**
 * This class extends the hard disk controller specification and represents the
 * IDE hard disk controller specification, and the maximum number of supported
 * hard disks is 4
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public class IDEHardDiskControllerSpecification extends
		HardDiskControllerSpecification {

	private static final int MAX_NUMBER_OF_SUPPORTED_HDDS = 4;

	/**
	 * Class constructor specifying the id of the specification
	 * 
	 * @param id
	 */
	public IDEHardDiskControllerSpecification(AlphaNumericString id) {
		super(id, MAX_NUMBER_OF_SUPPORTED_HDDS,
				DeviceType.IDE_HARD_DISK_CONTROLLER);
	}

}
