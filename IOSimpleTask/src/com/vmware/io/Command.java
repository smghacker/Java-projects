package com.vmware.io;

public interface Command {
	String getCommandName();

	String execute(String cmdArgs);
}
