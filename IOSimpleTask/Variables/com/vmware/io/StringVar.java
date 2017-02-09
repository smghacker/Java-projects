package com.vmware.io;

public class StringVar extends AbstractVariable {
	private static final String TYPE = "string";
	private String value;

	public StringVar() {

	}

	public Object getValue() {
		return this.value;
	}

	@Override
	public void setValue(Object value) {
		this.value = (String) value;
	}

	public String getType() {
		return TYPE;
	}
}
