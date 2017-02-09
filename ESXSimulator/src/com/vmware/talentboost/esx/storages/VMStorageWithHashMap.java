package com.vmware.talentboost.esx.storages;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.vmware.talentboost.esx.alphanumerics.AlphaNumericString;
import com.vmware.talentboost.esx.vms.IVirtualMachine;

@Component
public class VMStorageWithHashMap implements IVMStorage {

	private Map<String, IVirtualMachine> allVMs = new HashMap<String, IVirtualMachine>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.storages.IVMStorage#containsId(com.vmware.
	 * talentboost.esx.alphanumerics.AlphaNumericString)
	 */
	@Override
	public boolean containsId(AlphaNumericString id) {
		return allVMs.containsKey(id.getAlphanumericString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.storages.IVMStorage#addVM(com.vmware.talentboost
	 * .esx.vms.IVirtualMachine)
	 */
	@Override
	public void addVM(IVirtualMachine vm) {
		allVMs.put(vm.getId().getAlphanumericString(), vm);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.storages.IVMStorage#deleteVM(com.vmware.
	 * talentboost.esx.alphanumerics.AlphaNumericString)
	 */
	@Override
	public void deleteVM(AlphaNumericString id) {
		allVMs.remove(id.getAlphanumericString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.storages.IVMStorage#getVM(com.vmware.talentboost
	 * .esx.alphanumerics.AlphaNumericString)
	 */
	@Override
	public IVirtualMachine getVM(AlphaNumericString id) {
		return allVMs.get(id.getAlphanumericString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vmware.talentboost.esx.storages.IVMStorage#getAllVMs()
	 */
	@Override
	public Collection<IVirtualMachine> getAllVMs() {
		return allVMs.values();
	}

}
