package com.vmware.talentboost.esx.alphanumericexceptions;

/**
 * @author Valeri Colov
 * @version 1.0
 */
public class AlphaNumericStringWithSpaceConstructionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1179797305142503794L;

	private String inputString;
	/**
	 * Class constructor specifying which exactly input string cause to fail 
	 * construction of an object of type AlphaNumericStringWithSpaceConstruction
	 * @param inputString
	 */
	public AlphaNumericStringWithSpaceConstructionException(String inputString) {
		this.inputString = inputString;
	}
	
	@Override
	public String getMessage(){
		StringBuilder result = new StringBuilder();
		result.append("The input string" + inputString + " consists forbidden characters. It is allowed to consist alphanumeric symbols and space!");
		return result.toString();
	}
}
