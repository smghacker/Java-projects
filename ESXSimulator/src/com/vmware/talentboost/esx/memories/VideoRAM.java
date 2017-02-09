package com.vmware.talentboost.esx.memories;

import java.math.BigInteger;

public class VideoRAM extends Memory {
	public static final BigInteger MAX_MEMORY_SIZE = BigInteger.valueOf(1_024_000_000);
	public VideoRAM(BigInteger size) {
		super(MemoryType.VideoRAM, "KB");
		this.size = size;
	}
}
