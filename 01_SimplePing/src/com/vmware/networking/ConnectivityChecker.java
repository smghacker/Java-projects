package com.vmware.networking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectivityChecker {
	private boolean checkConnectivity(String ipAddress) throws IOException{
		InetAddress inet = InetAddress.getByName(ipAddress);
		boolean isReachable = inet.isReachable(5000);
		return isReachable;
	}
	
	private long checkConnectivityTime(String ipAddress) throws IOException{
		InetAddress inet = InetAddress.getByName(ipAddress);
		long old = System.currentTimeMillis();
		inet.isReachable(5000);
		long newTime = System.currentTimeMillis();
		return newTime - old;
	}
	
	public static void main(String[] args) throws IOException{
		String localHost = "localhost";
		ConnectivityChecker cc = new ConnectivityChecker();
		boolean isReachable = cc.checkConnectivity(localHost);
		long time = cc.checkConnectivityTime(localHost);
		
		String message = "";
		if(isReachable){
			message +=time;
		}else{
			message = "failure";
		}
		System.out.println("Is reachable " + isReachable + " " + message);
	}
}
