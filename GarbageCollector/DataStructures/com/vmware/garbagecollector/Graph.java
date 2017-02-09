package com.vmware.garbagecollector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	public static final int MAX = 150000;
	private Map<Integer, Node> graph;
	private PriorityQueue<Node> orderedNodes;
	private ArrayList<Integer> systemRoots;
	private ArrayList<Boolean> reachableBySystemRoots = new ArrayList<Boolean>();
	private int maxIndex = 0;

	private void markReachableByASingleSystemRoot(int rootData) {
		Queue<Node> queue = new LinkedList<Node>();
		Node rootNode = graph.get(rootData);
		queue.add(rootNode);
		reachableBySystemRoots.set(rootData, true);
		while (!queue.isEmpty()) {
			Node node = (Node) queue.remove();
			ArrayList<Node> siblings = node.getChildren();
			int length = siblings.size();
			for (int i = 0; i < length; ++i) {
				Node currentSibling = siblings.get(i);
				int index = currentSibling.getIndex();

				if (reachableBySystemRoots.get(index) == false) {
					reachableBySystemRoots.set(index, true);
					currentSibling = graph.get(index);
					queue.add(currentSibling);
				}
			}
		}
	}
	
	private void settingNumberOfNodesInLongestPathOfEachNodeInCertainComponent(int index, ArrayList<Boolean> used) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(index);
		while (!stack.isEmpty()) {
			int currentIndex = stack.peek();
			Node currentNode = graph.get(currentIndex);

			used.set(currentIndex, true);
			ArrayList<Node> currentNodeChildren = currentNode.getChildren();
			int size = currentNodeChildren.size();

			boolean found = false;
			for (int i = 0; i < size; i++) {
				if (used.get(currentNodeChildren.get(i).getIndex()) == false) {
					found = true;
					stack.push(currentNodeChildren.get(i).getIndex());
					break;
				}
			}

			if (!found) {
				int oldTop = stack.pop();
				if (!stack.isEmpty()) {
					int onTop = stack.peek();
					graph.get(onTop)
							.setNumberOfNodesInLongestPath(
									graph.get(oldTop)
											.getNumberOfNodesInLongestPath() + 1);
				} else {
					graph.get(oldTop).setNumberOfNodesInLongestPath(1);
				}
			}
		}
	}
	
	private void markReachableBySystemRoots() {
		for (int i = 0; i < maxIndex + 1; i++) {
			reachableBySystemRoots.add(false);
		}

		int size = systemRoots.size();
		for (int i = 0; i < size; ++i) {
			markReachableByASingleSystemRoot(systemRoots.get(i));
		}
	}
	
	private void orderNodes() {
		orderedNodes = new PriorityQueue<Node>();
		for (int i = 0; i < maxIndex + 1; i++) {
			if (reachableBySystemRoots.get(i) == false) {
				orderedNodes.add(graph.get(i));
			}
		}
	}
	
	public Graph(Map<Integer, Node> graph, ArrayList<Integer> systemRoots,
			int maxIndex) {
		this.graph = graph;
		this.systemRoots = systemRoots;
		this.maxIndex = maxIndex;
	}

	public Map<Integer, Node> getGraph() {
		return graph;
	}
	
	public PriorityQueue<Node> getOrderedNodes() {
		return orderedNodes;
	}

	public ArrayList<Integer> getSystemRoots() {
		return systemRoots;
	}

	public ArrayList<Boolean> getReachableBySystemRoots() {
		return reachableBySystemRoots;
	}	

	public int getMaxIndex() {
		return maxIndex;
	}
	
	public void settingNumberOfNodesInLongestPathOfEachNode() {
		markReachableBySystemRoots();
		orderNodes();
		ArrayList<Boolean> used = new ArrayList<Boolean>();
		for (int i = 0; i < maxIndex + 1; i++) {
			used.add(reachableBySystemRoots.get(i));
		}

		while (!orderedNodes.isEmpty()) {
			Node currentNode = orderedNodes.poll();
			if (used.get(currentNode.getIndex()) == false) {
				settingNumberOfNodesInLongestPathOfEachNodeInCertainComponent(currentNode.getIndex(), used);
			}
		}
		orderNodes();
	}
}
