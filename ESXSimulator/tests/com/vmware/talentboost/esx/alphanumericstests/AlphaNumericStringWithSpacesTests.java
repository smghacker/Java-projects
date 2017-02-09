package com.vmware.talentboost.esx.alphanumericstests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.vmware.talentboost.esx.alphanumericexceptions.AlphaNumericStringWithSpaceConstructionException;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericStringWithSpaces;

public class AlphaNumericStringWithSpacesTests {
	@Rule public ExpectedException thrown= ExpectedException.none();
	@Test
	public void testWithValidInput() throws AlphaNumericStringWithSpaceConstructionException{
		String inputString = "inputString 1";
		AlphaNumericStringWithSpaces alphaNumeric = new AlphaNumericStringWithSpaces(inputString);
		assertEquals(inputString, alphaNumeric.getAlphanumericStringWithSpaces());
	}
	@Test
	public void testWithInvalidInput() throws AlphaNumericStringWithSpaceConstructionException{
		thrown.expect(AlphaNumericStringWithSpaceConstructionException.class);
		String inputString = "inputString_1";
		AlphaNumericStringWithSpaces alphaNumeric = new AlphaNumericStringWithSpaces(inputString);
	}

}
