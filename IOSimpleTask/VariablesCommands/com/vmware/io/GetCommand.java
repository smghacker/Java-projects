package com.vmware.io;

public class GetCommand implements Command {

	private static final String COMMAND_NAME = "get";
	private VariableStorage variableStorage;

	public GetCommand(VariableStorage variableStorage) {
		this.variableStorage = variableStorage;
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

	@Override
	public String execute(String cmdArgs) {
		String nameOfVariable = cmdArgs;
		AbstractVariableVisitor variable = this.variableStorage
				.get(nameOfVariable);
		if (variable == null) {
			return "Err";
		}
		StringBuilder output = new StringBuilder();
		output.append("[");
		output.append(variable.getType());
		output.append("]");
		output.append(" ");
		output.append(variable.getValue());

		return output.toString();
	}

}
