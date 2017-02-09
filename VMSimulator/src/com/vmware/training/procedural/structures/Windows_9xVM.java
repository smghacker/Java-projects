package com.vmware.training.procedural.structures;

public class Windows_9xVM extends VirtualMachine {
	public Windows_9xVM(String id, VMOnlineObserver onlineObserver, VMOfflineObserver offlineObserver, VMSMBObserver smbObserver){
		super(id, onlineObserver, offlineObserver);
		this.attachAnObserver(smbObserver);
	}
}
