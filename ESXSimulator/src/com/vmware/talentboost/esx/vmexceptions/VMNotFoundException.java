package com.vmware.talentboost.esx.vmexceptions;

/**
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public class VMNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 967974582526889082L;
	private String id;

	/**
	 * Class constructor specifying the id to which there is no corresponding
	 * virtual machine
	 * 
	 * @param id
	 */
	public VMNotFoundException(String id) {
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
		result.append("VM with id:" + id + " was not found!");
		return result.toString();
	}
}
