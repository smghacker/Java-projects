package com.vmware.talentboost.esx.devicespecifications;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devices.DeviceType;

/**
 * The user of this class has the ability to create hard disk specification
 * object which represents a hard disk with following constraints:
 *  1. Maximum size 100TB
 * 
 * The user also has the ability to get the size of already created hard disk
 * specification object and the id of its controller
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public class HardDiskSpecification extends DeviceSpecification {

	private static final long MAXIMUM_SIZE = 109_951_162_777_600L;
	private long size;
	private AlphaNumericString idOfController;
	public static final int NUM_OF_ARGUMENTS = 4;

	/**
	 * Class constructor specifying the id and size(which is checked whether it
	 * is non-negative number between 0 and 100*1024^4 of our device.Also the id
	 * of the hard disk controller which controls our hard disk.
	 * 
	 * @param id
	 * @param size
	 * @param idOfController
	 */
	public HardDiskSpecification(AlphaNumericString id, long size,
			AlphaNumericString idOfController) {
		super(id, DeviceType.HARD_DISK);
		if (!isGivenSizeValid(size)) {
			throw new IllegalArgumentException("The size" + size
					+ " is too big. The maximum allowed size is "
					+ MAXIMUM_SIZE);
		}
		this.size = size;
		this.idOfController = idOfController;
	}

	public long getSize() {
		return size;
	}

	public AlphaNumericString getIdOfController() {
		return idOfController;
	}

	private boolean isGivenSizeValid(long size) {
		return size >= 0 && size <= MAXIMUM_SIZE;
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
		result.append("Size: " + size + "\n");
		result.append("Controller ID: " + idOfController);
		return result.toString();
	}
}
