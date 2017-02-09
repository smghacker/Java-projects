package com.vmware.training.procedural.structures;

public class WindowsNTVM extends VirtualMachine {
	public WindowsNTVM(String id, VMOnlineObserver onlineObserver, VMOfflineObserver offlineObserver, VMSMBObserver smbObserver){
		super(id, onlineObserver, offlineObserver);
		this.attachAnObserver(smbObserver);
	}
}
