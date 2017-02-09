package com.vmware.talentboost.esx.devicespecifications;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devices.DeviceType;

/**
 * This class extends the hard disk controller specification and represents the
 * SCSI hard disk controller specification, and the maximum number of supported
 * hard disks is 16
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public class SCSIHardDiskControllerSpecification extends
		HardDiskControllerSpecification {
	private static final int MAX_NUMBER_OF_SUPPORTED_HDDS = 16;

	/**
	 * Class constructor specifying the id of the specification
	 * 
	 * @param id
	 */
	public SCSIHardDiskControllerSpecification(AlphaNumericString id) {
		super(id, MAX_NUMBER_OF_SUPPORTED_HDDS,
				DeviceType.SCSI_HARD_DISK_CONTROLLER);
	}

}
