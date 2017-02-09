package com.vmware.training.procedural.structures;

public class LinuxVM extends VirtualMachine {
	public LinuxVM(String id, VMOnlineObserver onlineObserver, VMOfflineObserver offlineObserver){
		super(id, onlineObserver, offlineObserver);
	}
}
