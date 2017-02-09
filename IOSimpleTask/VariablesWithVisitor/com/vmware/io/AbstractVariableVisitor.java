package com.vmware.io;

public abstract class AbstractVariableVisitor {
	private String name;

	public AbstractVariableVisitor() {

	}

	public AbstractVariableVisitor(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract String getType();

	public abstract Object getValue();

	public abstract void setValue(Object value);

	public abstract AbstractVariableVisitor addition(
			AbstractVariableVisitor other);

	public abstract AbstractVariableVisitor concreteAddition(
			StringVarVisitor other);

	public abstract AbstractVariableVisitor concreteAddition(
			NumberVarVisitor other);

	public abstract AbstractVariableVisitor concreteAddition(
			ComplexNumberVarVisitor other);

	public abstract AbstractVariableVisitor subtraction(
			AbstractVariableVisitor other);

	public abstract AbstractVariableVisitor concreteSubtraction(
			StringVarVisitor other);

	public abstract AbstractVariableVisitor concreteSubtraction(
			NumberVarVisitor other);

	public abstract AbstractVariableVisitor concreteSubtraction(
			ComplexNumberVarVisitor other);

	public abstract AbstractVariableVisitor multiplication(
			AbstractVariableVisitor other);

	public abstract AbstractVariableVisitor concreteMultiplication(
			StringVarVisitor other);

	public abstract AbstractVariableVisitor concreteMultiplication(
			NumberVarVisitor other);

	public abstract AbstractVariableVisitor concreteMultiplication(
			ComplexNumberVarVisitor other);
}
