package com.vmware.io;

public class ReverseStringCommand implements Command {

	private static final String COMMAND_NAME = "reverse";
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

	@Override
	public String execute(String cmdArgs) {
		StringBuilder result = new StringBuilder(cmdArgs);
		result.reverse();
		return result.toString();
	}

}
