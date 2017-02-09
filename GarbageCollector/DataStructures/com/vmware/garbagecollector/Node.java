package com.vmware.garbagecollector;

import java.util.ArrayList;

public class Node implements Comparable<Node> {
	private final int index;
	private int numberOfInputEdges;
	private int numberOfNodesInLongestPath;
	private ArrayList<Node> children;

	public Node(int data) {
		this.index = data;
		this.numberOfInputEdges = 0;
		this.setNumberOfNodesInLongestPath(0);
		this.children = new ArrayList<Node>();
	}

	public int getIndex() {
		return index;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void incrementNumberOfInputEdges() {
		numberOfInputEdges++;
	}

	public void setNumberOfNodesInLongestPath(int numberOfNodesInLongestPath) {
		this.numberOfNodesInLongestPath = Math.max(numberOfNodesInLongestPath,
				this.numberOfNodesInLongestPath);
	}

	public int getNumberOfNodesInLongestPath() {
		return numberOfNodesInLongestPath;
	}

	public void addChild(Node newChild) {
		children.add(newChild);
	}

	public int compareTo(Node other) {
		//po malko wliza6ti rebra powe4e nodowwe waw patq:)
		if (other.numberOfNodesInLongestPath == numberOfNodesInLongestPath) {
			return numberOfInputEdges - other.numberOfInputEdges;
		} else {
			return other.numberOfNodesInLongestPath
					- numberOfNodesInLongestPath;
		}
	}
}
