package com.vmware.talentboost.esx.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;

/**
 * This class will serve as a command reader from a single file, which then will
 * be pushed in the queue, which then will be used to process these commands. It is the producing part of Producer-Consumer pattern
 * 
 * @author Valeri Colov
 * @version 1.0
 */
public class CommandProducer implements Runnable {
	private BlockingQueue<String> requestQueue;
	private InputStreamReader in;

	/**
	 * Class constructor specifying the path and the reference to the queue of
	 * requests
	 * 
	 * @param path
	 * @param requestQueue
	 * @throws FileNotFoundException
	 */
	public CommandProducer(String path, BlockingQueue<String> requestQueue)
			throws FileNotFoundException {
		this.in = new FileReader(path);
		this.requestQueue = requestQueue;
	}

	public CommandProducer(InputStream in, BlockingQueue<String> requestQueue) {
		this.requestQueue = requestQueue;
		this.in = new InputStreamReader(in);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		BufferedReader bufferedReader = new BufferedReader(in);

		String singleLine = "";
		try {
			singleLine = bufferedReader.readLine();
			while (singleLine != null) {
				try {
					requestQueue.put(singleLine);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				singleLine = bufferedReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
