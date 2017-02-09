package com.vmware.vmCustomization;

public class RHEL extends Customization {

	public RHEL(FileWriter fileWriter, CustSpec custSpec){
		super(fileWriter, custSpec);
	}
	@Override
	public boolean DetectOS() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void CustomizeHostName() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void CustomizeUTCTZ() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void CustomizeNICs() {
		// TODO Auto-generated method stub

	}

}
