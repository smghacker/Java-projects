package com.vmware.talentboost.esx.devicesexceptions;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class NetworkCardWithTheSameIpExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1944414746674471436L;

	private String ipAddress;

	/**
	 * Class constructor specifying the IP address which is repeating
	 * 
	 * @param ipAddress
	 */
	public NetworkCardWithTheSameIpExistsException(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		StringBuilder result = new StringBuilder();
		result.append("Network card with this IP " + ipAddress
				+ " already exists");
		return result.toString();
	}
}
