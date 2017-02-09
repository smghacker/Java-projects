package com.vmware.training.procedural;

import java.util.Map;

import com.vmware.training.procedural.structures.LinuxVM;
import com.vmware.training.procedural.structures.Mac_OSVM;
import com.vmware.training.procedural.structures.UnknownVMException;
import com.vmware.training.procedural.structures.VMOfflineObserver;
import com.vmware.training.procedural.structures.VMOnlineObserver;
import com.vmware.training.procedural.structures.VMSMBObserver;
import com.vmware.training.procedural.structures.VirtualMachine;
import com.vmware.training.procedural.structures.WindowsNTVM;
import com.vmware.training.procedural.structures.Windows_9xVM;

/**
 * This class manages the {@link VirtualMachine}s
 */
public class VirtualMachineManager {

	// a list of all the VMs that are currently online
	private VMOnlineObserver onlineVMsObserver = new VMOnlineObserver();
	// a list of all the VMs that are currently offline
	private VMOfflineObserver offlineVMsObserver = new VMOfflineObserver();
	// a list of all the VMs that are part of the SMB network
	private VMSMBObserver smbVMsObserver = new VMSMBObserver();

	
	public Map<String, VirtualMachine> getListOfOnlineVMs(){
		return onlineVMsObserver.getVMs();
	}
	
	public Map<String, VirtualMachine> getListOfOfflineVMs(){
		return offlineVMsObserver.getVMs();
	}
	
	public Map<String, VirtualMachine> getListOfSmbVMs(){
		return smbVMsObserver.getVMs();
	}
	/**
	 * <p>
	 * Powers on a virtual machine based on her id
	 * </p>
	 * <p>
	 * Only works if the VM is currently powered off
	 * </p>
	 * 
	 * @param id
	 *            the unique identification of the {@link VirtualMachine}
	 */
	public void powerOnMachine(String id) {

		VirtualMachine machine = offlineVMsObserver.getVMs().get(id);

		machine.powerOn();
	}
	
	
	public void powerOffMachine(String id){
		VirtualMachine machine = onlineVMsObserver.getVMs().get(id);
		if(machine != null){
			machine.powerOff();
		}
	}
	
	public void createVM(String type, String id) throws Exception{
		VirtualMachine newVM = null;
		if(type == "Windows_9x"){
			newVM = new Windows_9xVM(id, onlineVMsObserver, offlineVMsObserver, smbVMsObserver);
		}else if(type == "Linux"){
			newVM = new LinuxVM(id, onlineVMsObserver, offlineVMsObserver);
		}else if(type == "Mac"){
			newVM = new Mac_OSVM(id, onlineVMsObserver, offlineVMsObserver);
		}else if(type == "WindowsNT"){
			newVM = new WindowsNTVM(id, onlineVMsObserver, offlineVMsObserver, smbVMsObserver);
		}else{
			throw new UnknownVMException("There is no virtual machine of type "+type);
		}
		
		offlineVMsObserver.add(newVM);
	}
}
