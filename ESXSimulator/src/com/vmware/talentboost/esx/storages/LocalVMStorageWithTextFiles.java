package com.vmware.talentboost.esx.storages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.vmware.talentboost.esx.vms.IVirtualMachine;

public class LocalVMStorageWithTextFiles implements ILocalVMStorage {
	private static final String FOLDER = "VMs";
	private static final String EXTENSION = "txt";
	private final String FILE_NAME_WITH_ALL_VMS = "idsOfSavedVms";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.storages.ILocalVMStorage#getFileNameWithAllVms
	 * ()
	 */
	@Override
	public String getFileNameWithAllVms() {
		return FILE_NAME_WITH_ALL_VMS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.storages.ILocalVMStorage#constructPath(java
	 * .lang.String)
	 */
	@Override
	public String constructPath(String fileName) {
		StringBuilder result = new StringBuilder();
		result.append(FOLDER + "/");
		result.append(fileName);
		result.append("." + EXTENSION);
		return result.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.storages.ILocalVMStorage#containsId(java.lang
	 * .String)
	 */
	@Override
	public boolean containsId(String id) {

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.storages.ILocalVMStorage#addVM(com.vmware.
	 * talentboost.esx.vms.IVirtualMachine)
	 */
	@Override
	public void addVM(IVirtualMachine vm) throws IOException {
		String vmId = vm.getId().getAlphanumericString();
		String path = constructPath(vmId);
		String pathToFileithVMIds = constructPath(FILE_NAME_WITH_ALL_VMS);
		File newVMFile = new File(path);

		FileWriter fw = new FileWriter(newVMFile, false);

		fw.write(vm.toString());
		fw.close();

		File newIdVMFile = new File(pathToFileithVMIds);

		fw = new FileWriter(newIdVMFile, true);
		fw.write(vm.getId().getAlphanumericString() + "\n");
		fw.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vmware.talentboost.esx.storages.ILocalVMStorage#deleteVM(java.lang
	 * .String)
	 */
	@Override
	public void deleteVM(String id) {
		String path = constructPath(id);
		File currentVMBackup = new File(path);
		currentVMBackup.delete();

	}
}
