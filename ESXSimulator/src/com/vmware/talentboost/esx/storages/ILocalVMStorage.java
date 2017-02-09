package com.vmware.talentboost.esx.storages;

import java.io.IOException;

import com.vmware.talentboost.esx.vms.IVirtualMachine;

/**
 * This interface provides basic functionality which every virtual machine local(i.e storage using text files) storage must
 * have. The user of this interface can check whether the local virtual machine storage
 * contains virtual machine with certain id, can add virtual machine to and delete virtual machine from the storage,
 * can construct path for a certain VM(i.e we want to save this vm and we should construct the path and the save it), can access file with IDs of all VMs
 * storage.
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public interface ILocalVMStorage {
	boolean containsId(String id);
	void addVM(IVirtualMachine vm) throws IOException;
	void deleteVM(String id);
	String constructPath(String vmId);
	String getFileNameWithAllVms();
}
