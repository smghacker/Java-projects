package com.vmware.training.procedural.structures;

import java.util.ArrayList;

public class VirtualMachine{
	private final int MAX_NUMBER_OF_VMOBSERVERS = 3;
	private String id;
	private boolean resetWindowsRegistry = false;
	private boolean isPowered;
	private int numberOfObservers;
	private ArrayList<VMObserver> observers;
	
	public VirtualMachine(String id, VMOnlineObserver onlineObserver, VMOfflineObserver offlineObserver){
		this.id = id;
		this.isPowered = false;
		this.observers = new ArrayList<VMObserver>();
		this.attachAnObserver(onlineObserver);
		this.attachAnObserver(offlineObserver);
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isResetWindowsRegistry() {
		return resetWindowsRegistry;
	}
	public void setResetWindowsRegistry(boolean resetWindowsRegistry) {
		this.resetWindowsRegistry = resetWindowsRegistry;
	}
	
	public boolean isPowered() {
		return isPowered;
	}

	public int getNumberOfObservers() {
		return numberOfObservers;
	}
	
	public ArrayList<VMObserver> getObservers() {
		return observers;
	}
	
	void attachAnObserver(VMObserver observer){
		if(this.numberOfObservers < MAX_NUMBER_OF_VMOBSERVERS){
			this.observers.add(observer);
			this.numberOfObservers++;
		}else{
			//throw some exception
		}
	}
	
	public void powerOn(){
		boolean oldState = this.isPowered;
		this.isPowered = true;
		this.notifyObservers(oldState);
	}
	public void powerOff(){
		boolean oldState = this.isPowered;
		this.isPowered = false;
		this.notifyObservers(oldState);
	}
	
	private void notifyObservers(boolean oldState){
		if(this.isPowered != oldState){
			for(int i = 0; i < this.numberOfObservers; i++){
				this.observers.get(i).update(this, oldState);
			}
		}		
	}
}
