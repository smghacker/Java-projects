package com.vmware.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	private int portNumber = 4443;
	
	public void connect(){
		 try (ServerSocket serverSocket =
		                new ServerSocket(this.portNumber);
		            Socket clientSocket = serverSocket.accept();     
		            PrintWriter out =
		                new PrintWriter(clientSocket.getOutputStream(), true);                   
		            BufferedReader in = new BufferedReader(
		                new InputStreamReader(clientSocket.getInputStream()));
		        ){
			 System.out.println("baasassaa");
			 EchoThread echoThread = new EchoThread(in, out);
		        echoThread.start();
		 }catch(IOException e){
			 System.out.println("Exception caught when trying to listen on port "
		                + this.portNumber + " or listening for a connection");
		 }
	}
	
	public static void main(String[] args) {
		EchoServer echoServer = new EchoServer();

			echoServer.connect();
	}
}
