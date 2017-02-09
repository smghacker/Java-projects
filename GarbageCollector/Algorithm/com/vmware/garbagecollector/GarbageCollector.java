package com.vmware.garbagecollector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class GarbageCollector {
	private String sector;
	private Graph currentGraph;
	private ArrayList<Boolean> reachableBySystemRoots;
	private PriorityQueue<Node> listOfRootNodes;

	private void getTrajectoriesAndPost() throws IOException {
		while (!listOfRootNodes.isEmpty()) {
			Node currentNode = listOfRootNodes.poll();
			int index = currentNode.getIndex();
			if (reachableBySystemRoots.get(index) == false) {

				StringBuilder sb = new StringBuilder();
				reachableBySystemRoots.set(index, true);
				sb.append(index);
				int numberOfChildren = currentNode.getChildren().size();
				constructTrajectory(currentNode, sb, numberOfChildren);

				String path = sb.toString();				
				postTrajectory(path);
			}
		}
	}

	private void constructTrajectory(Node currentNode, StringBuilder sb,
			int numberOfChildren) {
		while (numberOfChildren != 0) {
			boolean found = false;
			ArrayList<Node> children = currentNode.getChildren();
			PriorityQueue<Node> childrenNodes = new PriorityQueue<Node>();
			for (int j = 0; j < numberOfChildren; ++j) {
				int childIndex = children.get(j).getIndex();
				childrenNodes.add(currentGraph.getGraph().get(childIndex));
			}
			while (!childrenNodes.isEmpty()) {
				Node child = childrenNodes.poll();
				int childIndex = child.getIndex();
				if (reachableBySystemRoots.get(childIndex) == false) {
					listOfRootNodes.remove(child);
					reachableBySystemRoots.set(childIndex, true);
					found = true;
					currentNode = child;
					sb.append(" ");
					sb.append(childIndex);
					break;
				}
			}
			if (found) {
				numberOfChildren = currentNode.getChildren().size();
			} else {
				numberOfChildren = 0;
			}
		}
	}

	private void postTrajectory(String path) throws IOException {
		SimpleRestClient.httpPost(sector,
				path, 200, 418);
	}

	public GarbageCollector(Graph currentGraph, String sector) {
		currentGraph.settingNumberOfNodesInLongestPathOfEachNode();
		this.sector = sector;
		this.currentGraph = currentGraph;
		this.reachableBySystemRoots = currentGraph.getReachableBySystemRoots();
		this.listOfRootNodes = currentGraph.getOrderedNodes();
	}

	public void collect() throws IOException {
		getTrajectoriesAndPost();
	}
}
