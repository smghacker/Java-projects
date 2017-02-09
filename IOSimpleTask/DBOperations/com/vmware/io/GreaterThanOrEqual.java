package com.vmware.io;

public class GreaterThanOrEqual implements Operation {

private static final String OPERATION_NAME = ">=";
	
	public String getOperationName(){
		return OPERATION_NAME;
	}
	@Override
	public boolean execute(String left, String right) {
		boolean isLeftGreaterThan = false;
		if(left.compareTo(right) >= 0){
			isLeftGreaterThan = true;
		}
		return isLeftGreaterThan;
	}

}
