package com.vmware.io;

public class ComplexNumberVarVisitor extends AbstractVariableVisitor {

	private static final String TYPE = "complex-number";
	private int realPart;
	private int imaginaryPart;
	private Pair<Integer, Integer> value;

	@Override
	public String getType() {
		return TYPE;
	}

	public int getRealPart() {
		return realPart;
	}

	public void setRealPart(int realPart) {
		this.realPart = realPart;
	}

	public int getImaginaryPart() {
		return imaginaryPart;
	}

	public void setImaginaryPart(int imaginaryPart) {
		this.imaginaryPart = imaginaryPart;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object value) {
		String args = (String) value;
		String[] parts = args.split(" ");
		int real = 0;
		int imag = 0;
		try{
			real = Integer.parseInt(parts[0]);
			imag = Integer.parseInt(parts[1]);			
		}catch(NumberFormatException e){
			throw new NumberFormatException();
		}

		this.realPart = real;
		this.imaginaryPart = imag;
		Pair<Integer, Integer> val = new Pair<Integer, Integer>(real, imag);
		this.value = val;
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
		int realNum = (int) other.getValue();
		int newReal = (this.realPart + realNum);

		String value = newReal + " " + this.imaginaryPart;
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor result = builder.build(this.getType(),
				"Intermediate Variable", value);
		return result;
	}

	@Override
	public AbstractVariableVisitor concreteAddition(
			ComplexNumberVarVisitor other) {
		int realOther = other.getRealPart();
		int imagOther = other.getImaginaryPart();
		int newReal = (this.realPart + realOther);
		int newImag = (this.imaginaryPart + imagOther);

		String value = newReal + " " + newImag;
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor result = builder.build(this.getType(),
				"Intermediate Variable", value);
		return result;
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
		int realNum = (int) other.getValue();
		int newReal = (this.realPart - realNum);

		String value = newReal + " " + this.imaginaryPart;
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor result = builder.build(this.getType(),
				"Intermediate Variable", value);
		return result;
	}

	@Override
	public AbstractVariableVisitor concreteSubtraction(
			ComplexNumberVarVisitor other) {
		int realOther = other.getRealPart();
		int imagOther = other.getImaginaryPart();
		int newReal = (this.realPart - realOther);
		int newImag = (this.imaginaryPart - imagOther);

		String value = newReal + " " + newImag;
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor result = builder.build(this.getType(),
				"Intermediate Variable", value);
		return result;
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
		int realNum = (int) other.getValue();
		int newReal = this.realPart * realNum;
		int newImag = this.imaginaryPart * realNum;

		String value = newReal + " " + newImag;
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor result = builder.build(this.getType(),
				"Intermediate Variable", value);
		return result;
	}

	@Override
	public AbstractVariableVisitor concreteMultiplication(
			ComplexNumberVarVisitor other) {
		int realOther = other.getRealPart();
		int imagOther = other.getImaginaryPart();
		int newReal = (this.realPart * realOther - this.imaginaryPart
				* imagOther);
		int newImag = (this.realPart * imagOther + this.imaginaryPart
				* realOther);

		String value = newReal + " " + newImag;
		VariableVisitorBuilder builder = new VariableVisitorBuilder();
		AbstractVariableVisitor result = builder.build(this.getType(),
				"Intermediate Variable", value);
		return result;
	}
}
