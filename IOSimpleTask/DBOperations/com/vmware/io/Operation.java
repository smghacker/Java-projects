package com.vmware.io;

public interface Operation {
	public String getOperationName();
	public boolean execute(String left, String right);
}
