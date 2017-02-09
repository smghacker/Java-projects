package com.vmware.io;

public interface VariablesOperation {
	String getOperationName();

	AbstractVariableVisitor execute(AbstractVariableVisitor left, AbstractVariableVisitor right);
}
