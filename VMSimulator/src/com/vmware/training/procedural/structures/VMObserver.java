package com.vmware.training.procedural.structures;

import java.util.Map;

public abstract class VMObserver {
	protected Map<String, VirtualMachine> VMs;
	
	public Map<String, VirtualMachine> getVMs() {
		return VMs;
	}
	
	public void add(VirtualMachine vm){
		this.VMs.put(vm.getId(), vm);
	}
	public void delete(VirtualMachine vm){
		this.VMs.remove(vm.getId());
	}
	
	public abstract void update(VirtualMachine vm, boolean oldState);
}
