package com.vmware.multithreading;

import java.io.File;

public class FilesCounter {
	public long countFiles(String path){
		File folder = new File(path);
		
		long count = listFilesForFolder(folder);
		return count;
	}
	
	private int listFilesForFolder(File folder) {
		int i = 0;
		File[] allFiles = folder.listFiles();
		if(allFiles == null){
			return i;
		}
	    for (File fileEntry : folder.listFiles()) {
	    	if(fileEntry != null){
		        if (fileEntry.isDirectory()) {
		           i += listFilesForFolder(fileEntry);
		        } else {
		        	i += 1;
		        }
	    	}
	    }
	    return i;
	}
}
