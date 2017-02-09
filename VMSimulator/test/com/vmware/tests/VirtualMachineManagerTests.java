package com.vmware.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.vmware.training.procedural.VirtualMachineManager;
import com.vmware.training.procedural.structures.UnknownVMException;

public class VirtualMachineManagerTests {

	@Test
	public void testCreatingVirtualMachines(){
		VirtualMachineManager vmManager = new VirtualMachineManager();
		try{
			vmManager.createVM("Windows_9x", "1");
			vmManager.createVM("Linux", "2");
			vmManager.createVM("Mac", "3");
			vmManager.createVM("WindowsNT", "4");
			vmManager.createVM("WindowsNT", "5");
		}catch(UnknownVMException e){
			
		}catch(Exception e){
			
		}
		
		int numberOfOfflineVMs = vmManager.getListOfOfflineVMs().size();
		int numberOfOnlineVMs = vmManager.getListOfOnlineVMs().size();
		assertEquals(5, numberOfOfflineVMs);
		assertEquals(0, numberOfOnlineVMs);
	}
	
	@Test
	public void testPoweringOn(){
		VirtualMachineManager vmManager = new VirtualMachineManager();
		try{
			vmManager.createVM("Windows_9x", "1");
			vmManager.createVM("Linux", "2");
			vmManager.createVM("Mac", "3");
			vmManager.createVM("WindowsNT", "4");
			vmManager.createVM("WindowsNT", "5");
			
			vmManager.powerOnMachine("1");
			vmManager.powerOnMachine("2");
		}catch(UnknownVMException e){
			
		}catch(Exception e){
			
		}
		
		int numberOfOfflineVMs = vmManager.getListOfOfflineVMs().size();
		int numberOfOnlineVMs = vmManager.getListOfOnlineVMs().size();
		int numberOfSmbVMs = vmManager.getListOfSmbVMs().size();
		assertEquals(3, numberOfOfflineVMs);
		assertEquals(2, numberOfOnlineVMs);
		assertEquals(1, numberOfSmbVMs);
	}
	
	@Test
	public void testPoweringOff(){
		VirtualMachineManager vmManager = new VirtualMachineManager();
		try{
			vmManager.createVM("Windows_9x", "1");
			vmManager.createVM("Linux", "2");
			vmManager.createVM("Mac", "3");
			vmManager.createVM("WindowsNT", "4");
			vmManager.createVM("WindowsNT", "5");
			
			vmManager.powerOnMachine("1");
			vmManager.powerOnMachine("2");
			vmManager.powerOffMachine("2");
		}catch(UnknownVMException e){
			
		}catch(Exception e){
			
		}
		
		int numberOfOfflineVMs = vmManager.getListOfOfflineVMs().size();
		int numberOfOnlineVMs = vmManager.getListOfOnlineVMs().size();
		int numberOfSmbVMs = vmManager.getListOfSmbVMs().size();
		assertEquals(4, numberOfOfflineVMs);
		assertEquals(1, numberOfOnlineVMs);
		assertEquals(1, numberOfSmbVMs);
	}
}
