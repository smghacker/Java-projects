package com.vmware.io;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VariableVisitorBuilder {
	private Collection<AbstractVariableVisitor> variables = Arrays.asList(
			new StringVarVisitor(), new NumberVarVisitor(),
			new ComplexNumberVarVisitor());

	private Map<String, AbstractVariableVisitor> allVariables = new HashMap<String, AbstractVariableVisitor>() {
		{
			for (AbstractVariableVisitor var : variables) {
				put(var.getType(), var);
			}
		}
	};

	public AbstractVariableVisitor build(String type, String name, Object value) {
		AbstractVariableVisitor variable = allVariables.get(type);
		variable.setName(name);
		try{
			variable.setValue(value);
		}catch(NumberFormatException e){
			return null;
		}
		return variable;
	}
}
