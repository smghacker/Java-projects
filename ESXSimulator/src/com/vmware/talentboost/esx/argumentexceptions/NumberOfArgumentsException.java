package com.vmware.talentboost.esx.argumentexceptions;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class NumberOfArgumentsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8137043946929414728L;

	private int numberOfArguments;
	/**Class constructor specifying the number of arguments which cause
	 * somewhere a failure
	 * @param numberOfArguments
	 */
	public NumberOfArgumentsException(int numberOfArguments) {
		this.numberOfArguments = numberOfArguments;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage(){
		StringBuilder result = new StringBuilder();
		result.append("The number of arguments " + numberOfArguments + " is too small for this operation");
		return result.toString();
	}

}
