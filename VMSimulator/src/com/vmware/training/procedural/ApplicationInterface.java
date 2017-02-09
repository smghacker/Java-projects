package com.vmware.training.procedural;

import java.util.Collection;

import com.vmware.training.procedural.structures.UnknownVMException;
import com.vmware.training.procedural.structures.VirtualMachine;

/**
 * Serves as an interface of our application
 */
public class ApplicationInterface {

	private VirtualMachineManager vmManager;
	
	public ApplicationInterface(){
		this.vmManager = new VirtualMachineManager();
	}
	/**
	 * @return a {@link Collection} of all the VMs that are powered on
	 */
	public Collection<VirtualMachine> listPoweredOnVirtualMachines() {
		return vmManager.getListOfOnlineVMs().values();
	}

	/**
	 * @return a {@link Collection} of all the VMs that are powered off
	 */
	public Collection<VirtualMachine> listPoweredOffVirtualMachines() {
		return vmManager.getListOfOfflineVMs().values();
	}

	/**
	 * Creates a new Virtual Machine
	 * 
	 * @param type
	 *            the {@link VirtualMachineType} of the {@link VirtualMachine}
	 * @param id
	 *            the unique identifier of the {@link VirtualMachine}
	 */
	public void createVirtualMachine(String type, String id) {
		try {
			vmManager.createVM(type, id);
		} catch (UnknownVMException e) {
			e.getMessage();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Powers on a {@link VirtualMachine}
	 * 
	 * @param id
	 *            the unique identifier of the {@link VirtualMachine}
	 */
	public void powerOnVirtualMachine(String id) {
		vmManager.powerOnMachine(id);
	}

	/**
	 * Powers off a {@link VirtualMachine}
	 * 
	 * @param id
	 *            the unique identifier of the {@link VirtualMachine}
	 */
	public void powerOffVirtualMachine(String id) {
		vmManager.powerOffMachine(id);
	}
}
