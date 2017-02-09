package com.vmware.io;

public class NumberVar extends AbstractVariable {
	private static final String TYPE = "number";
	private int value;

	public NumberVar() {

	}

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		String currVal = (String) value;
		this.value = Integer.parseInt(currVal);
	}

	public String getType() {
		return TYPE;
	}
}
