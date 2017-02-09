package com.vmware.io;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CalcCommand implements Command {

	private static final String COMMAND_NAME = "calc";
	private VariableStorage variableStorage;
	private Collection<VariablesOperation> operations = Arrays.asList(
			new Addition(), new Subtraction(), new Multiplication());

	private Map<String, VariablesOperation> allOperations = new HashMap<String, VariablesOperation>() {
		{
			for (VariablesOperation varOp : operations) {
				put(varOp.getOperationName(), varOp);
			}
		}
	};

	public CalcCommand(VariableStorage variableStorage) {
		this.variableStorage = variableStorage;
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

	@Override
	public String execute(String cmdArgs) {
		// extract arguments
		String[] arguments = cmdArgs.split("\\s+");
		String nameFirstVariable = arguments[0];
		String nameSecondVariable = arguments[1];
		String operationSign = arguments[2];
		String nameThridVariable = arguments[3];

		// check existence of variables

		AbstractVariableVisitor firstVar = this.variableStorage
				.get(nameFirstVariable);
		AbstractVariableVisitor secondVar = this.variableStorage
				.get(nameSecondVariable);
		AbstractVariableVisitor thirdVar = this.variableStorage
				.get(nameThridVariable);
		if (firstVar == null || secondVar == null || thirdVar == null) {
			return "Err";
		}

		// generate command
		String operationName = operationSign;
		// call the operation
		VariablesOperation operation = this.allOperations.get(operationName);
		firstVar = operation.execute(secondVar, thirdVar);
		// save the result
		if (firstVar == null) {
			return "Err";
		}
		this.variableStorage.put(nameFirstVariable, firstVar);
		return "Ok";
	}

}
