
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author valeri
 */
public class ClientThread extends Thread {

	private int port;

	public ClientThread(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		Socket socket = null;
		BufferedReader inputReader = null;
		BufferedReader br = null;
		PrintWriter printWriter = null;
		try {
			socket = new Socket("0.0.0.0", port);
			inputReader = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			printWriter = new PrintWriter(socket.getOutputStream(), true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			while ((line = inputReader.readLine()) != null) {
				printWriter.println(line.toLowerCase());
				String serverAnswer;
				serverAnswer = br.readLine();
				System.out.println(line + " means " + serverAnswer);
			}

		} catch (IOException ex) {
			Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException ex) {
					Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			if (inputReader != null) {
				try {
					inputReader.close();
				} catch (IOException ex) {
					Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException ex) {
					Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}
}
