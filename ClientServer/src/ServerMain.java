/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author valeri
 */
public class ServerMain {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		int port = 2259;
		new ServerThread(port, "dictionary.txt").start();

	}

}
