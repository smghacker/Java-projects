package com.vmware.networking;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

public class AllNetworkInterfacesInfo {
	private Enumeration<NetworkInterface> networkInterfaces;
	
	public AllNetworkInterfacesInfo() {
		try{
			this.networkInterfaces = NetworkInterface.getNetworkInterfaces();
		}catch(SocketException e){
			e.printStackTrace();
		}
	}
	
	public void printInfo(){
		for(NetworkInterface nif : Collections.list(this.networkInterfaces)){
			try{
				printInfoForTheInterface(nif);
			}catch(SocketException e){
				e.printStackTrace();
			}
		}
	}

	private void printInfoForTheInterface(NetworkInterface nif) throws SocketException {
		System.out.printf("Display name: %s\n", nif.getDisplayName());
		System.out.printf("Name: %s\n", nif.getName());
		
		displaySubInterfaces(nif);
		
		Enumeration<InetAddress> inetAddresses = nif.getInetAddresses();
		for(InetAddress inetAddress : Collections.list(inetAddresses)){
			System.out.printf("InetAddress: %s\n", inetAddress);
		}
		
		System.out.printf("Index %s\n", nif.getIndex());
		System.out.printf("Up? %s\n", nif.isUp());
		System.out.printf("Loopback? %s\n", nif.isLoopback());
		System.out.printf("PointToPoint? %s\n", nif.isPointToPoint());
		System.out.printf("Supports multicast? %s\n", nif.supportsMulticast());
        System.out.printf("Virtual? %s\n", nif.isVirtual());
        System.out.printf("Hardware address: %s\n",
                    Arrays.toString(nif.getHardwareAddress()));
        System.out.printf("MTU: %s\n", nif.getMTU());
        System.out.printf("\n");
	}
	
	private void displaySubInterfaces(NetworkInterface netIf) throws SocketException {
		Enumeration<NetworkInterface> subif = netIf.getSubInterfaces();
		
		for(NetworkInterface subIf : Collections.list(subif)){
			printInfoForTheInterface(subIf);
		}
		
	}
}
