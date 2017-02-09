package com.vmware.talentboost.esx.vmexceptions;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class VMWithCertainIdExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6840888287811657618L;

	private String id;

	/**
	 * Class constructor specifying the virtual machine's id which already
	 * exists
	 * 
	 * @param id
	 */
	public VMWithCertainIdExistsException(AlphaNumericString id) {
		this.id = id.getAlphanumericString();
	}

	public VMWithCertainIdExistsException(String id) {
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
		result.append("There is already a VM with this id = " + id);
		return result.toString();
	}
}
