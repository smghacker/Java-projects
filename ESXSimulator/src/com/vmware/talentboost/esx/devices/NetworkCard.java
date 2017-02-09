package com.vmware.talentboost.esx.devices;

import com.vmware.talentboost.esx.devicespecifications.DeviceSpecification;
import com.vmware.talentboost.esx.devicespecifications.NetworkCardSpecification;

/**
 * This class represents Network card and the user of this class has the following abilities:
 * 	1.To initialize its specification
 *  2.To get its MAC address
 *  3.To get its IP address
 *  
 * @author Valeri Colov
 * @version 1.0
 */
public class NetworkCard extends Device {

	private NetworkCardSpecification specification;

	/**
	 * Class constructor specifying the concrete hard disk specification
	 *  
	 * @param specification
	 */
	public NetworkCard(NetworkCardSpecification specification) {
		super(new DeviceSpecification(specification.getId(),
				specification.getTypeOfDevice()));
		this.specification = specification;
	}

	public String getMacAddress() {
		return specification.getMacAddress();
	}

	public String getIpAddress() {
		return specification.getIP();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.devices.Device#getType()
	 */
	@Override
	public DeviceType getType() {
		return DeviceType.NETWORK_CARD;
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
