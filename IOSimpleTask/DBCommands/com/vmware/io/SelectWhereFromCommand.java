package com.vmware.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SelectWhereFromCommand implements Command {

	private static final String COMMAND_NAME = "select-where";
	private Storage storage;
	private final Collection<Operation> operations = 
			Arrays.asList(
					new Equal(),
					new GreaterThan(),
					new GreaterThanOrEqual(),
					new LessThan(),
					new LessThanOrEqual());
	
	private Map<String, Operation> allOperations = new HashMap<String, Operation>(){{
		for(Operation op:operations){
			put(op.getOperationName(), op);
		}
	}};
	
	public SelectWhereFromCommand(Storage storage){
		this.storage = storage;
	}
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

	@Override
	public String execute(String cmdArgs) {
		String[] arguments = cmdArgs.split("\\s+");
		String firstColumnName = arguments[0];
		String nameOftable = arguments[2];
		String secondColumnName = arguments[4];
		String leftOperand = secondColumnName;
		String operation = arguments[5];
		String rightOperand = arguments[6];
		Table neededTable = this.storage.getTable(nameOftable);
		
		ArrayList<String> firstColumn = neededTable.getColumn(firstColumnName);
		ArrayList<String> secondColumn = neededTable.getColumn(secondColumnName);
		
		StringBuilder result = new StringBuilder();
		Operation currentOperation = this.allOperations.get(operation);
		for(int i = 0; i < secondColumn.size(); i++){
			if(currentOperation.execute(secondColumn.get(i), rightOperand)){
				result.append(firstColumn.get(i));
				result.append("\n");
			}
		}
		
		return result.toString();
	}

}
