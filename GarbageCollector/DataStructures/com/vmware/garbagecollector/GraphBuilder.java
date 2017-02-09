package com.vmware.garbagecollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GraphBuilder {
	
	private ArrayList<Integer> getSystemRoots(Map<Integer, Node> graph,
			String systemRootsInput) {
		ArrayList<Integer> systemRoots = new ArrayList<Integer>();

		Scanner scanner = new Scanner(systemRootsInput);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			int index = Integer.parseInt(line);
			systemRoots.add(index);
		}
		scanner.close();

		return systemRoots;
	}

	public Graph build(String couplesInput, String systemRootsInput) {

		Map<Integer, Node> graph = new HashMap<Integer, Node>();
		int maxIndex = 0;
		for (int i = 0; i < Graph.MAX; i++) {
			graph.put(i, new Node(i));
		}
		
		Scanner scanner = new Scanner(couplesInput);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			String[] objects = line.split(" ");
			int parentIndex = Integer.parseInt(objects[0]);
			int childIndex = Integer.parseInt(objects[1]);
			maxIndex = Math.max(Math.max(parentIndex, childIndex), maxIndex);
			Node node = new Node(childIndex);
			graph.get(parentIndex).addChild(node);
			graph.get(childIndex).incrementNumberOfInputEdges();
		}
		scanner.close();
		
		ArrayList<Integer> systemRoots = getSystemRoots(graph, systemRootsInput);

		return new Graph(graph, systemRoots, maxIndex);
	}	
}
