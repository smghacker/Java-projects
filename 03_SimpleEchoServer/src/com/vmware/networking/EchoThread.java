package com.vmware.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EchoThread extends Thread {
	private BufferedReader in;
	private PrintWriter out;
	public EchoThread(BufferedReader in, PrintWriter out){
		this.in = in;
		this.out = out;
	}
	public void run(){
		String inputLine;
		 try {
			inputLine = this.in.readLine();
			while(inputLine != null){
				out.println("EchoThread");
				 out.println(inputLine);
				 inputLine = in.readLine();
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
	}
}
