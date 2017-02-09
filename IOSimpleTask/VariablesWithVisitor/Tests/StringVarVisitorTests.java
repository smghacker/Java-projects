package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.vmware.io.AbstractVariableVisitor;
import com.vmware.io.StringVarVisitor;

public class StringVarVisitorTests {

	private static final String NAME = "custom";
	private static final String SECOND_NAME = "custom2";
	private static final String RESULT_NAME = "Intermediate Variable";
	private static final String VALUE = "value";
	private static final String VALUE_OF_SECOND = "secVar";
	private static final String EMPTY = "";
	@Test
	public void concreteAdditionWithStringVar() {
		StringVarVisitor firstString = new StringVarVisitor();
		firstString.setName(NAME);
		firstString.setValue(VALUE);
		
		StringVarVisitor secondString = new StringVarVisitor();
		secondString.setName(NAME);
		secondString.setValue(VALUE_OF_SECOND);
		
		AbstractVariableVisitor result = firstString.concreteAddition(secondString);
		
		assertEquals(RESULT_NAME, result.getName());
		String expectedValue = VALUE + VALUE_OF_SECOND;
		assertEquals(expectedValue, result.getValue());
	}

}
