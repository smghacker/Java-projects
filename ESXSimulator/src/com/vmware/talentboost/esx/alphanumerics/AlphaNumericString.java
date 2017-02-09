package com.vmware.talentboost.esx.alphanumerics;

import com.vmware.talentboost.esx.alphanumericexceptions.AlphaNumericStringConstructionException;


/**
 * This class will represent a string which consists only of alphanumeric symbols
 * @author Valeri Colov
 * @version 1.0
 */
public class AlphaNumericString {
	private String alphaNumericString;
	
	/**
	 * Class constructor specifying the string which would be validated and then saved
	 * as an internal object of type String
	 * @param inputString
	 * @throws AlphaNumericStringConstructionException
	 */
	public AlphaNumericString(String inputString) throws AlphaNumericStringConstructionException{
		boolean isValid = checkValidity(inputString);
		if(!isValid){
			throw new AlphaNumericStringConstructionException(inputString);
		}
		this.alphaNumericString = inputString;
	}
	
	/**
	 * @param inputString
	 * @return whether certain string is valid(consists only of alphanumeric symbols)
	 */
	private boolean checkValidity(String inputString) {
		if(inputString.matches("^[a-zA-Z0-9]+$")){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @return the internal string object
	 */
	public String getAlphanumericString() {
		return alphaNumericString;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return this.alphaNumericString;
	}
}
