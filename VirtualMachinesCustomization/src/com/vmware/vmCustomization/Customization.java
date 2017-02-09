package com.vmware.vmCustomization;

public abstract class Customization {
	public Customization(FileWriter fileWriter, CustSpec custSpec){
		this.fileWriter = fileWriter;
		this.custSpec = custSpec;
	}
	
	public abstract boolean DetectOS();
	
	protected void CustomizeHostsFile(){
		
	}
	
	protected abstract void CustomizeHostName();
	protected abstract void CustomizeUTCTZ();
	protected abstract void CustomizeNICs();
	
	protected void CustomizeDNS(){
		
	}	
	
	protected FileWriter fileWriter;
	protected CustSpec custSpec;
}
