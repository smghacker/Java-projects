package com.vmware.training.procedural.structures;

public class UnknownVMException extends Exception {
	private String message;
	public UnknownVMException(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
}
