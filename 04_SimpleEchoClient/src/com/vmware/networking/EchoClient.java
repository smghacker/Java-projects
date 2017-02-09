package com.vmware.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
	private String hostName = "Valeri";
	private int portNumber = 4443;

	public void request(){
		try(
				Socket clientSocket = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				 BufferedReader stdIn =
			                new BufferedReader(new InputStreamReader(System.in));
				FileWriter fw = new FileWriter("responses.txt");
				BufferedWriter bw = new BufferedWriter(fw);
			){
			String userInput = stdIn.readLine();
			
			while(userInput != null){
				out.println(userInput);
                bw.write("Server: " + in.readLine());
                bw.newLine();
                userInput = stdIn.readLine();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		EchoClient echoClient = new EchoClient();
			echoClient.request();
	}
}
