package com.vmware.talentboost.esx.devicesexceptions;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class DeviceWithThisIdAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2605867710865898712L;

	private String id;

	/**
	 * Class constructor specifying the id of the device which already exists
	 * 
	 * @param id
	 */
	public DeviceWithThisIdAlreadyExistsException(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		StringBuilder result = new StringBuilder();
		result.append("Device with this " + id + " already exists in this VM.");
		return result.toString();
	}
}
