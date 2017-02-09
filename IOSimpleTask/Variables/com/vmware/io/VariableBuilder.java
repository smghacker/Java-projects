package com.vmware.io;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VariableBuilder {
	private Collection<AbstractVariable> variables = Arrays.asList(new StringVar(),
			new NumberVar());

	private Map<String, AbstractVariable> allVariables = new HashMap<String, AbstractVariable>() {
		{
			for (AbstractVariable var : variables) {
				put(var.getType(), var);
			}
		}
	};

	public AbstractVariable build(String type, String name, Object value) {
		AbstractVariable variable = allVariables.get(type);
		variable.setName(name);
		variable.setValue(value);
		return variable;
	}
}
