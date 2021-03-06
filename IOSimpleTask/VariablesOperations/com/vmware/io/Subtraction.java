package com.vmware.io;

public class Subtraction implements VariablesOperation {

	private final String OPERATION_NAME = "-";

	@Override
	public String getOperationName() {
		return OPERATION_NAME;
	}

	@Override
	public AbstractVariableVisitor execute(AbstractVariableVisitor left, AbstractVariableVisitor right) {
		AbstractVariableVisitor result = right.subtraction(left);
		return result;
	}

}
