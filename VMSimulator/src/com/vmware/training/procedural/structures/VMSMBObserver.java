package com.vmware.training.procedural.structures;

import java.util.HashMap;

public class VMSMBObserver extends VMObserver {
	public VMSMBObserver(){
		this.VMs = new HashMap<String, VirtualMachine>();
	}
	public void update(VirtualMachine vm, boolean oldState){
		if(oldState == false){
			this.add(vm);
		}else{
			this.delete(vm);
		}
	}
}
