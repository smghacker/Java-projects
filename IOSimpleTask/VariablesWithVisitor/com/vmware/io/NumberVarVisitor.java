package com.vmware.io;

public class NumberVarVisitor extends AbstractVariableVisitor {

	private static final String TYPE = "number";
	private int value;

	public NumberVarVisitor() {

	}

	@Override
	public Object getValue() {
		return this.value;
	}

	@Override
	public void setValue(Object value) {
		String currVal = (String) value;
		try{
			this.value = Integer.parseInt(currVal);
		}catch(NumberFormatException e){
			throw new NumberFormatException();
		}
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public AbstractVariableVisitor addition(AbstractVariableVisitor other) {
		return other.concreteAddition(this);
	}

	@Override
	public AbstractVariableVisitor concreteAddition(StringVarVisitor other) {
		return null;
	}

	@Override
	public AbstractVariableVisitor concreteAddition(NumberVarVisitor other) {
		int value = this.value + other.value;
		String val = "" + value;
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor result = builder.build(this.getType(),
				"Intermediate Variable", val);
		return result;
	}

	@Override
	public AbstractVariableVisitor concreteAddition(
			ComplexNumberVarVisitor other) {
		return null;
	}

	@Override
	public AbstractVariableVisitor subtraction(AbstractVariableVisitor other) {
		return other.concreteSubtraction(this);
	}

	@Override
	public AbstractVariableVisitor concreteSubtraction(StringVarVisitor other) {
		return null;
	}

	@Override
	public AbstractVariableVisitor concreteSubtraction(NumberVarVisitor other) {
		int value = this.value - other.value;
		String val = "" + value;
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor result = builder.build(this.getType(),
				"Intermediate Variable", val);
		return result;
	}

	@Override
	public AbstractVariableVisitor concreteSubtraction(
			ComplexNumberVarVisitor other) {
		return null;
	}

	@Override
	public AbstractVariableVisitor multiplication(AbstractVariableVisitor other) {
		return other.concreteMultiplication(this);
	}

	@Override
	public AbstractVariableVisitor concreteMultiplication(StringVarVisitor other) {
		return null;
	}

	@Override
	public AbstractVariableVisitor concreteMultiplication(NumberVarVisitor other) {
		int value = this.value * other.value;
		String val = "" + value;
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor result = builder.build(this.getType(),
				"Intermediate Variable", val);
		return result;
	}

	@Override
	public AbstractVariableVisitor concreteMultiplication(
			ComplexNumberVarVisitor other) {
		return null;
	}
}
