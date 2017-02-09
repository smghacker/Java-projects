package com.vmware.talentboost.esx.storages;

import java.util.Collection;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.vms.IVirtualMachine;

/**
 * This interface provides basic functionality which every virtual machine storage must
 * have. The user of this interface can check whether the local virtual machine storage
 * contains virtual machine with certain id, can add virtual machine to and delete virtual machine from the storage,
 * can construct path for a certain VM(i.e we want to save this vm and we should construct the path and the save it), can access file with IDs of all VMs
 * storage.
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public interface IVMStorage {
	boolean containsId(AlphaNumericString id);
	void addVM(IVirtualMachine vm);
	void deleteVM(AlphaNumericString id);
	IVirtualMachine getVM(AlphaNumericString id);
	Collection<IVirtualMachine> getAllVMs();
}
