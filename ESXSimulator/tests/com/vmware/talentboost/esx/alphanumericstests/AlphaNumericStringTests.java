package com.vmware.talentboost.esx.alphanumericstests;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.vmware.talentboost.esx.alphanumericexceptions.AlphaNumericStringConstructionException;
import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;

public class AlphaNumericStringTests {
	@Rule public ExpectedException thrown= ExpectedException.none();
	@Test
	public void testWithValidInput() throws AlphaNumericStringConstructionException{
		String inputString = "inputString1";
		AlphaNumericString alphaNumeric = new AlphaNumericString(inputString);
		assertEquals(inputString, alphaNumeric.getAlphanumericString());
	}
	@Test
	public void testWithInvalidInput() throws AlphaNumericStringConstructionException{
		thrown.expect(AlphaNumericStringConstructionException.class);
		String inputString = "inputString_1";
		AlphaNumericString alphaNumeric = new AlphaNumericString(inputString);
	}

}
