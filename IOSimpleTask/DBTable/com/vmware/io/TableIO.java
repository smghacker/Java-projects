package com.vmware.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TableIO {
	public Table loadCSV(String filename)throws IOException{
		BufferedReader bufferedReader = null;
		Table loaded = new Table();
		try{
			bufferedReader = new BufferedReader(new FileReader(filename));
			String firstLine = bufferedReader.readLine();
			String[] columnNames = firstLine.split(",");
			int index = 0;
			for(String columnName:columnNames){
				loaded.addColumn(columnName, index);
				index++;
			}
			
			for(int i = 0; i < index; i++){
				loaded.addAnEmptyColumnInTheTable();
			}
			
			String currentLine = bufferedReader.readLine();
			while(currentLine != null){
				String[] args = currentLine.split(",");
				for(int i = 0; i < args.length; i++){
					loaded.getTable().get(i).add(args[i]);
				}
				currentLine = bufferedReader.readLine();
			}
		}finally{
			if(bufferedReader != null){
				bufferedReader.close();				
			}
		}
		
		return loaded;
	}
}
