package com.vmware.talentboost.esx.application;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.vmware.talentboost.esx.server.ESXSimulator;

public class Application {
	public static void main(String[] args) throws IOException{
		BlockingQueue<String> requestsQueue = new LinkedBlockingDeque<String>();
		ESXSimulator esxSimulator = new ESXSimulator(requestsQueue);
		new Thread(esxSimulator).start();
	}
}
