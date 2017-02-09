package com.vmware.io;

public abstract class AbstractVariable {

	private String name;

	public AbstractVariable() {

	}

	public AbstractVariable(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract String getType();

	public abstract Object getValue();

	public abstract void setValue(Object value);
}
