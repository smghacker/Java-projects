package com.vmware.vmCustomization;

public class Customizer {

	public static void main(String[] args) {
		FileWriter fileWriter = new FileWriter();
		CustSpec custSpec = new CustSpec();
		SLES slesCustomization = new SLES(fileWriter, custSpec);
	}

}
