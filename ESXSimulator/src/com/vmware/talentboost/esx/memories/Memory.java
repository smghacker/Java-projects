package com.vmware.talentboost.esx.memories;

import java.math.BigInteger;

public  class Memory {
	private MemoryType type;
	protected BigInteger size;
	private String measurementUnit;
	
	public Memory(MemoryType memoryType, String measurementUnit){
		this.type = memoryType;
		this.measurementUnit = measurementUnit;
	}
	
	public MemoryType getType(){
		return type;
	}
	
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("MemoryType: " + type.toString() + "\n");
		result.append("Size: ");
		result.append(size);
		result.append(" ");
		result.append(measurementUnit + "\n");
		return result.toString();
	}
}
