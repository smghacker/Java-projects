package com.vmware.talentboost.esx.devicesexceptions;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class NetworkCardWithTheSameMacExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1047019916670307813L;

	private String macAddress;

	/**
	 * Class constructor specifying the repeating MAC address
	 * 
	 * @param macAddress
	 */
	public NetworkCardWithTheSameMacExistsException(String macAddress) {
		this.macAddress = macAddress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		StringBuilder result = new StringBuilder();
		result.append("Network card with this MAC " + macAddress
				+ " already exists");
		return result.toString();
	}
}
