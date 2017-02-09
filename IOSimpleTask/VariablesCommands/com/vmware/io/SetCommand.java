package com.vmware.io;

public class SetCommand implements Command {

	private static final String COMMAND_NAME = "set";
	private VariableStorage variableStorage;

	public SetCommand(VariableStorage variableStorage) {
		this.variableStorage = variableStorage;
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

	@Override
	public String execute(String cmdArgs) {
		String[] arguments = cmdArgs.split("\\s+");
		String name = arguments[0];
		String type = arguments[1];
		StringBuilder argValue = new StringBuilder();
		for (int i = 2; i < arguments.length; i++) {
			argValue.append(arguments[i]);
			if (i != arguments.length - 1) {
				argValue.append(" ");
			}
		}
		String value = argValue.toString();

		VariableVisitorBuilder varBuilder = new VariableVisitorBuilder();
		AbstractVariableVisitor newVariable = varBuilder.build(type, name,
				value);
		
		if (newVariable == null) {
			return "Err";
		}
		
		this.variableStorage.put(name, newVariable);
		return "Ok";
	}

}
