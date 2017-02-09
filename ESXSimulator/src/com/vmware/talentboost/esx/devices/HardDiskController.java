package com.vmware.talentboost.esx.devices;

import java.util.HashMap;
import java.util.Map;

import com.vmware.talentboost.esx.devicespecifications.DeviceSpecification;
import com.vmware.talentboost.esx.devicespecifications.HardDiskControllerSpecification;

/**
 * This class represents Hard Disk Controller and the user of this class has the
 * following abilities: 
 *  1.To initialize its specification
 *  2.To add a hard disk safety(taking into consideration what type of controller is) 
 *  3.To get the list of hard disks
 * 
 * @author Valeri Colov
 * @version 1.0
 * @see Device.java
 */
public class HardDiskController extends Device {

	private HardDiskControllerSpecification specification;
	private Map<String, HardDisk> hardDisks = new HashMap<String, HardDisk>();

	/**
	 * Class constructor specifying the concrete hard disk specification
	 * 
	 * @param specification
	 */
	public HardDiskController(HardDiskControllerSpecification specification) {
		super(new DeviceSpecification(specification.getId(),
				specification.getTypeOfDevice()));
		this.specification = specification;
	}

	/**
	 * @param hdd
	 * @throws
	 */
	public void addHardDisk(HardDisk hdd) {
		if (hardDisks.containsKey(hdd.getId().getAlphanumericString())) {
			throw new IllegalArgumentException(
					"Hard Disk with this id is already attached to this controller "
							+ specification.getId().getAlphanumericString());
		} else if (hardDisks.keySet().size() == specification
				.getMaxNumberOfSupportedHdds()) {
			throw new IllegalArgumentException(
					"There is no place to attach new hard disk on this controller "
							+ specification.getId().getAlphanumericString()
							+ ". The maximal number of supported hdds is "
							+ specification.getMaxNumberOfSupportedHdds());
		} else {
			hardDisks.put(hdd.getId().getAlphanumericString(), hdd);
		}
	}

	public Map<String, HardDisk> getHardDisks() {
		return hardDisks;
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
		StringBuilder result = new StringBuilder();
		result.append(specification.toString() + "\n");
		for (HardDisk hdd : hardDisks.values()) {
			result.append(hdd.toString());
		}
		return result.toString();
	}

}
