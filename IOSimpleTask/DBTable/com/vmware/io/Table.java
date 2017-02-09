package com.vmware.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Table {
	private Map<String, Integer> columns;
	private ArrayList<ArrayList<String>> table;
	
	public Table(){
		this.columns = new HashMap<String, Integer>();
		this.table = new ArrayList<ArrayList<String>>();
	}
	
	public Map<String, Integer> getColumns() {
		return columns;
	}

	public ArrayList<ArrayList<String>> getTable() {
		return table;
	}
	
	public void addColumn(String columnName, Integer columnNumber){
		this.columns.put(columnName, columnNumber);
	}
	
	public void addAnEmptyColumnInTheTable(){
		this.table.add(new ArrayList<String>());
	}
	
	public void addSingleMemberOfColumn(int columnIndex, String value){
		this.table.get(columnIndex).add(value);
	}
	
	public ArrayList<String> getColumn(String column){
		int indexOfTheColumn = this.columns.get(column);
		return this.table.get(indexOfTheColumn);
	}
}
