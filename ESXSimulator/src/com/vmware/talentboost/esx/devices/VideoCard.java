package com.vmware.talentboost.esx.devices;

import com.vmware.talentboost.esx.devicespecifications.DeviceSpecification;
import com.vmware.talentboost.esx.devicespecifications.VideoCardSpecification;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class VideoCard extends Device {

	private VideoCardSpecification specification;

	/**
	 * Class constructor specifying the concrete hard disk specification
	 *  
	 * @param specification
	 */
	public VideoCard(VideoCardSpecification specification) {
		super(new DeviceSpecification(specification.getId(),
				specification.getTypeOfDevice()));
		this.specification = specification;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.devices.Device#getType()
	 */
	@Override
	public DeviceType getType() {
		return DeviceType.VIDEO_CARD;
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
