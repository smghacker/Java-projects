package com.vmware.talentboost.esx.devicespecifications;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.devices.DeviceType;
import com.vmware.talentboost.esx.devicesexceptions.IPFormatException;
import com.vmware.talentboost.esx.devicesexceptions.MACAddresFormatException;

/**
 * This class is a specification for the network card. Consists of MAC address
 * and IP address. The user of this class is able to safety create network card,
 * because it validates the MAC address and IP address format. The user is also
 * able to get both MAC address and IP address.
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public class NetworkCardSpecification extends DeviceSpecification {

	private static final String MAC_PATTERN = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";
	private static final String IP_PATTERN = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	public static final int NUM_OF_SPECS = 4;
	private String macAddress;
	private String ipAddress;

	private boolean validateIPAddress(final String IP) {
		return IP.matches(IP_PATTERN);
	}

	private boolean validateMacAddress(final String macAddress) {
		return macAddress.matches(MAC_PATTERN);
	}

	/**
	 * Class constructor specifying the id, MAC address and IP address of the
	 * new network card. The constructor validates the two addresses and if
	 * there is an error through the validation process throws
	 * MACAddresFormatException or IPFormatException
	 * 
	 * @param id
	 * @param macAddress
	 * @param ipAddress
	 * @throws MACAddresFormatException
	 * @throws IPFormatException
	 */
	public NetworkCardSpecification(AlphaNumericString id, String macAddress,
			String ipAddress) throws MACAddresFormatException,
			IPFormatException {
		super(id, DeviceType.NETWORK_CARD);
		if (!validateMacAddress(macAddress)) {
			throw new MACAddresFormatException(macAddress);
		} else if (!validateIPAddress(ipAddress)) {
			throw new IPFormatException(ipAddress);
		}

		this.macAddress = macAddress;
		this.ipAddress = ipAddress;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public String getIP() {
		return ipAddress;
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
		result.append("MAC address: ");
		result.append(getMacAddress());
		result.append("\n");
		result.append("IP address: " + getIP());
		return result.toString();
	}
}
