package com.vmware.talentboost.esx.alphanumericexceptions;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class AlphaNumericStringConstructionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 295129940405974956L;

	private String inputString;

	/**
	 * Class constructor specifying which exactly input string cause to fail 
	 * construction of an object of type AlphaNumericString
	 * @param inputString
	 */
	public AlphaNumericStringConstructionException(String inputString) {
		this.inputString = inputString;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		StringBuilder result = new StringBuilder();
		result.append("The input string " + inputString
				+ " consists forbidden characters. It should be alphanumeric!");
		return result.toString();
	}
}
