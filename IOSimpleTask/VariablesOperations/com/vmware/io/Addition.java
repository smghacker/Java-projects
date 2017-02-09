package com.vmware.io;

public class Addition implements VariablesOperation {

	private final String OPERATION_NAME = "+";

	@Override
	public String getOperationName() {
		return OPERATION_NAME;
	}

	@Override
	public AbstractVariableVisitor execute(AbstractVariableVisitor left, AbstractVariableVisitor right) {
		AbstractVariableVisitor result = right.addition(left);
		return result;
	}

}
