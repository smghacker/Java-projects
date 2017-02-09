package com.vmware.talentboost.esx.memories;

import java.math.BigInteger;

public class RAM extends Memory {

	public RAM(BigInteger size){
		super(MemoryType.RAM, "bytes");
		this.size = size;
	}
}
