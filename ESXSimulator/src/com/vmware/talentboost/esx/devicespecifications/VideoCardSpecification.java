package com.vmware.talentboost.esx.devicespecifications;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devices.DeviceType;
import com.vmware.talentboost.esx.memories.VideoRAM;

/**
 * This class represent the specification of a video card. Defines the maximum
 * and minimum number of displays. The user of this class can access the number
 * of displays and the video RAM
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public class VideoCardSpecification extends DeviceSpecification {

	private static final int MIN_NUM_OF_DISPLAYS = 0;
	private static final int MAX_NUM_OF_DISPLAYS = 2;
	public static final int NUM_OF_SPECS = 4;
	private VideoRAM videoRam;
	private int numberOfDisplays;

	private boolean isNumberOfDisplaysValid(int numberOfDisplays) {
		return numberOfDisplays >= 0 && numberOfDisplays <= 2;
	}

	/**
	 * Class constructor specifying the id, the video RAM and the number of
	 * displays of the new video card. Constructor checks whether the number of
	 * display is between the minimum and maximum allowed number and if it is
	 * not, throws an exception
	 * 
	 * @param id
	 * @param videoRam
	 * @param numberOfDisplays
	 * @throws IllegalArgumentException
	 */
	public VideoCardSpecification(AlphaNumericString id, VideoRAM videoRam,
			int numberOfDisplays) throws IllegalArgumentException {
		super(id, DeviceType.VIDEO_CARD);
		this.videoRam = videoRam;
		if (!isNumberOfDisplaysValid(numberOfDisplays)) {
			throw new IllegalArgumentException(
					"The number of displays must be between "
							+ MIN_NUM_OF_DISPLAYS + " and "
							+ MAX_NUM_OF_DISPLAYS + ". Your input is "
							+ numberOfDisplays);
		}
		this.numberOfDisplays = numberOfDisplays;
	}

	public VideoRAM getVideoRam() {
		return videoRam;
	}

	public int getNumberOfDisplays() {
		return numberOfDisplays;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.devicespecifications.DeviceSpecification#toString
	 * ()
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(getVideoRam().toString());
		result.append("Number of Displays: ");
		result.append(getNumberOfDisplays());
		return result.toString();
	}
}
