package com.vmware.training.procedural.structures;

import java.util.HashMap;

public class VMOfflineObserver extends VMObserver {
	public VMOfflineObserver(){
		this.VMs = new HashMap<String, VirtualMachine>();
	}
	public void update(VirtualMachine vm, boolean oldState){
		if(oldState == true){
			this.add(vm);
		}else{
			this.delete(vm);
		}
	}
}
