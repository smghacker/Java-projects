package com.vmware.talentboost.esx.alphanumerics;

import com.vmware.talentboost.esx.alphanumericexceptions.AlphaNumericStringWithSpaceConstructionException;

/**
 * This class will represent a string which consists only  of alphanumeric symbols and spaces
 * @author Valeri Colov
 * @version 1.0
 */
public class AlphaNumericStringWithSpaces{	
	private String alphaNumericStringWithSpaces;
	/**
	 * Class constructor specifying the string which would be validated and then saved
	 * as an internal object of type String
	 * @param inputString
	 * @throws AlphaNumericStringWithSpaceConstructionException
	 */
	public AlphaNumericStringWithSpaces(String inputString)throws AlphaNumericStringWithSpaceConstructionException{
		boolean isValid = checkValidity(inputString);
		if(!isValid){
			throw new AlphaNumericStringWithSpaceConstructionException(inputString);
		}
		this.alphaNumericStringWithSpaces = inputString;
	}
	
	/**
	 * @param inputString
	 * @return whether certain string is valid(consists only of alphanumeric symbols and spaces)
	 */
	private boolean checkValidity(String inputString) {
		if(inputString.matches("^[a-zA-Z0-9 ]+$")){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @return the internal string object
	 */
	public String getAlphanumericStringWithSpaces() {
		return alphaNumericStringWithSpaces;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return this.alphaNumericStringWithSpaces;
	}
}
