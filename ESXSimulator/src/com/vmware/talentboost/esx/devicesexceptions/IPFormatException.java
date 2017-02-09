package com.vmware.talentboost.esx.devicesexceptions;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class IPFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 153620629956432680L;

	private String ipAddress;

	/**
	 * Class constructor specifying the IP address which has invalid format
	 * 
	 * @param ipAddress
	 */
	public IPFormatException(String ipAddress) {
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
		result.append("Provided IP address "
				+ ipAddress
				+ "  is invalid. IP address should be in the format H.M.L.K where H,M,L,K are number in the interval [0, 255]");
		return result.toString();
	}
}
