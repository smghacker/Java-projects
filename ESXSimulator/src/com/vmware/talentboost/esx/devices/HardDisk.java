package com.vmware.talentboost.esx.devices;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devicespecifications.DeviceSpecification;
import com.vmware.talentboost.esx.devicespecifications.HardDiskSpecification;

/**
 * This class represents Hard Disk and the user of this class has the following
 * abilities: 
 *  1.To initialize its specification
 *  2.To return the id of the controller
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public class HardDisk extends Device {

	private HardDiskSpecification specification;

	/**
	 * Class constructor specifying the concrete hard disk specification
	 * 
	 * @param specification
	 */
	public HardDisk(HardDiskSpecification specification) {
		super(new DeviceSpecification(specification.getId(),
				specification.getTypeOfDevice()));
		this.specification = specification;
	}

	public AlphaNumericString getIdOfController() {
		return specification.getIdOfController();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.devices.Device#getType()
	 */
	@Override
	public DeviceType getType() {
		return specification.getTypeOfDevice();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.devices.Device#getOtherSpecs()
	 */
	@Override
	public String getOtherSpecs() {
		return specification.toString();
	}

}
