package com.vmware.io;

public class StringVarVisitor extends AbstractVariableVisitor {

	private static final String TYPE = "string";
	private String value;

	public StringVarVisitor() {

	}

	public Object getValue() {
		return this.value;
	}

	@Override
	public void setValue(Object value) {
		this.value = (String) value;
	}

	public String getType() {
		return TYPE;
	}

	@Override
	public AbstractVariableVisitor addition(AbstractVariableVisitor other) {
		return other.concreteAddition(this);
	}

	@Override
	public AbstractVariableVisitor concreteAddition(StringVarVisitor other) {
		String value = this.value + other.value;
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor result = builder.build(this.getType(),
				"Intermediate Variable", value);
		return result;
	}

	@Override
	public AbstractVariableVisitor concreteAddition(NumberVarVisitor other) {
		String value = this.value + other.getValue();
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor result = builder.build(this.getType(),
				"Intermediate Variable", value);
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
		return null;
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
		StringBuilder value = new StringBuilder();
		int times = (int) other.getValue();
		for (int i = 0; i < times; i++) {
			value.append(this.value);
		}

		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor result = builder.build(this.getType(),
				"Intermediate Variable", value.toString());
		return result;
	}

	@Override
	public AbstractVariableVisitor concreteMultiplication(
			ComplexNumberVarVisitor other) {
		return null;
	}
}
