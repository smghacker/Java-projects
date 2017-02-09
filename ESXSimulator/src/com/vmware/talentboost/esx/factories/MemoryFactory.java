package com.vmware.talentboost.esx.factories;

import java.math.BigInteger;

import com.vmware.talentboost.esx.memories.Memory;
import com.vmware.talentboost.esx.memories.MemoryType;
import com.vmware.talentboost.esx.memories.RAM;
import com.vmware.talentboost.esx.memories.VideoRAM;

/**
 * @author Valeri Colov
 * @version 1.0
 *
 */
public class MemoryFactory {
	/**
	 * Creates a memory of a given memory type and given size. Throws
	 * IllegalArgumentException if the given size is negative number, if the
	 * given size is bigger than the maximal allowed, and if the given type of
	 * memory doesn't exist
	 * 
	 * @param memoryType
	 * @param size
	 * @return new memory
	 * @throws IllegalArgumentException
	 */
	public static Memory build(MemoryType memoryType, BigInteger size)
			throws IllegalArgumentException {
		if (size.compareTo(BigInteger.ZERO) < 0) {
			throw new IllegalArgumentException(
					"The size of memory should be non-negative number!");
		}
		if (memoryType == MemoryType.RAM) {
			return new RAM(size);
		} else if (memoryType == MemoryType.VideoRAM) {
			if (size.compareTo(VideoRAM.MAX_MEMORY_SIZE) > 0) {
				throw new IllegalArgumentException(
						"The size of memory should be less than"
								+ VideoRAM.MAX_MEMORY_SIZE + " " + "KB!");
			}
			return new VideoRAM(size);
		} else {
			throw new IllegalArgumentException(
					"There is no such memory type as " + memoryType);
		}
	}
}
