package com.vmware.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientSide {

	private static void process(InputStreamReader in, String hostname, int portNumber) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(in);
		try (Socket clientSocket = new Socket(hostname, portNumber);
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				){
			String singleLine = bufferedReader.readLine();
			while (singleLine != null) {
				out.println(singleLine);
				System.out.println("Server: " + clientIn.readLine());
				singleLine = bufferedReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bufferedReader.close();
		}
	}
	public static void main(String[] args) throws IOException {
		InputStreamReader in = new InputStreamReader(System.in);
		process(in, "Valeri", 4443);
		in.close();
	}

}
