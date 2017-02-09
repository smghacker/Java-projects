
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
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
class ServerThread extends Thread {

	private int port;
	private String pathToDictionary;
	private Map<String, String> dictionary;

	public ServerThread(int port, String pathToDictionary) {
		this.port = port;
		this.pathToDictionary = pathToDictionary;
		this.dictionary = new HashMap<String, String>();
	}

	@Override
	public void run() {
		initialReadOfDictionary();
		BufferedOutputStream bos = null;
		Socket clientSocket = null;
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			clientSocket = serverSocket.accept();
			bos = new BufferedOutputStream(clientSocket.getOutputStream());
			OutputStreamWriter osw = new OutputStreamWriter(bos, "UTF-8");
			try (BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
				String clientLine = null;
				while ((clientLine = br.readLine()) != null) {
					String answer = null;
					if (dictionary.containsKey(clientLine)) {
						answer = dictionary.get(clientLine);
					} else {
						answer = "There is no such word";
					}
					osw.write(answer + System.getProperty("line.separator"));
					osw.flush();
				}
			}
		} catch (IOException ex) {
			Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (clientSocket != null) {
				try {
					clientSocket.close();
				} catch (IOException ex) {
					Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void initialReadOfDictionary() {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File(pathToDictionary)), "UTF-8"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] splittedLine = line.split("\\s+");
				dictionary.put(splittedLine[0], splittedLine[1]);
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
