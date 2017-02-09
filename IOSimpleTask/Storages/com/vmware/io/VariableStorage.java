package com.vmware.io;

import java.util.HashMap;
import java.util.Map;

public class VariableStorage {
	private Map<String, AbstractVariableVisitor> variables;

	public VariableStorage() {
		this.variables = new HashMap<String, AbstractVariableVisitor>();
	}

	public AbstractVariableVisitor get(String name) {
		return this.variables.get(name);
	}

	public void put(String name, AbstractVariableVisitor variable) {
		this.variables.put(name, variable);
	}
}
