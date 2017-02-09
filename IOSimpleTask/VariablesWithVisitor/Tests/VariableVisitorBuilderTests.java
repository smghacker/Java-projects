package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.vmware.io.AbstractVariableVisitor;
import com.vmware.io.Pair;
import com.vmware.io.VariableVisitorBuilder;

public class VariableVisitorBuilderTests {

	private static final String STRING_TYPE = "string";
	private static final String NUMBER_TYPE = "number";
	private static final String COMPLEX_NUMBER_TYPE = "complex-number";
	private static final String NAME = "custom";
	
	@Test
	public void BuildStringTest() {
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor var = builder.build(STRING_TYPE, NAME, "aaa");
		assertEquals(STRING_TYPE, var.getType());
		assertEquals(NAME, var.getName());
		assertEquals("aaa", var.getValue());
	}
	
	@Test
	public void BuildNumberTest() {
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor var = builder.build(NUMBER_TYPE, NAME, "5");
		assertEquals(NUMBER_TYPE, var.getType());
		assertEquals(NAME, var.getName());
		assertEquals(5, (int)var.getValue());
	}
	
	@Test
	public void BuildNumberPuttingInvalidIntegerAsValueTest() {
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor var = builder.build(NUMBER_TYPE, NAME, "5a");
		assertEquals(null, var);
	}
	
	@Test
	public void BuildComplexNumberTest() {
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor var = builder.build(COMPLEX_NUMBER_TYPE, NAME, "5 4");
		assertEquals(COMPLEX_NUMBER_TYPE, var.getType());
		assertEquals(NAME, var.getName());
		
		Pair<Integer, Integer> expected = new Pair<Integer, Integer>(5,4);
		assertEquals(expected, (Pair<Integer,Integer>)var.getValue());
	}
	
	@Test
	public void BuildComplexNumberPuttingInvalidIntegerAsValueTest() {
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor var = builder.build(COMPLEX_NUMBER_TYPE, NAME, "5a 4");
		assertEquals(null, var);
	}

}
