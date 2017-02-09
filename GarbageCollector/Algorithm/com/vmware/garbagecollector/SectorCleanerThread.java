package com.vmware.garbagecollector;

import java.io.IOException;

public class SectorCleanerThread implements Runnable {

	private String sector;

	public SectorCleanerThread(String sector) {
		this.sector = sector;
	}

	@Override
	public void run() {
		String getObjects;
		try {
			getObjects = SimpleRestClient.httpGetObjects(sector);
			String getRoots = SimpleRestClient.httpGetRoots(sector);
			GraphBuilder graphBuilder = new GraphBuilder();
			Graph g = graphBuilder.build(getObjects, getRoots);
			GarbageCollector gc = new GarbageCollector(g, sector);
			gc.collect();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
