package com.vmware.io;

import java.util.HashMap;
import java.util.Map;

public class Storage {
	private Map<String, Table> tables;
	
	public Storage(){
		this.tables = new HashMap<String, Table>();
	}
	
	public void addTable(String name, Table table){
		this.tables.put(name, table);
	}
	
	public Table getTable(String name){
		return this.tables.get(name);
	}
}
