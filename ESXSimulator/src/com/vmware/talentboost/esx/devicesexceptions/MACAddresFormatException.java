package com.vmware.talentboost.esx.devicesexceptions;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class MACAddresFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4115543665351358204L;

	private String macAddress;

	/**
	 * Class constructor specifyung the MAC address which format is invalid
	 * @param macAddress
	 */
	public MACAddresFormatException(String macAddress) {
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
		result.append("Provided MAC address "
				+ macAddress
				+ "  is invalid. MAC address should be in the format HJ-KL-GF-DS-AB-CX where  H,J,K,L,G,F,D,S,A,B,C,X are some hexadecimal digits {0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,A,B,C,D,E,F}");
		return result.toString();
	}
}
