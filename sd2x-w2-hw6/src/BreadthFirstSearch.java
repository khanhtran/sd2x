
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * SD2x Homework #6
 * This is an implementation of Breadth First Search (BFS) on a graph.
 * You may modify and submit this code if you'd like.
 */

public class BreadthFirstSearch {
	protected Set<Node> marked;
	protected Graph graph;

	public BreadthFirstSearch(Graph graphToSearch) {
		marked = new HashSet<Node>();
		graph = graphToSearch;
	}

	/**
	 * This method was discussed in the lesson
	 */
	public boolean bfs(Node start, String elementToFind) {
		System.out.println("begin bfs " + elementToFind);
		if (!graph.containsNode(start)) {
			System.out.println("end bfs " + elementToFind);
			return false;
		}
		if (start.getElement().equals(elementToFind)) {
			System.out.println("end bfs " + elementToFind);
			return true;
		}
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.add(start);
		toExplore.add(start);
		while (!toExplore.isEmpty()) {
			Node current = toExplore.remove();
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.contains(neighbor)) {
					if (neighbor.getElement().equals(elementToFind)) {
						System.out.println("end bfs " + elementToFind);
						return true;
					}
					marked.add(neighbor);
					toExplore.add(neighbor);
				}
			}
		}
		System.out.println("end bfs " + elementToFind);
		return false;
	}

	public int shortestPath(Node start, String elementToFind) {
		String fcn = "shortestPath " + this.graph + " - " + start + " - " + elementToFind;
		System.out.println("begin " + fcn);
		if (!graph.containsNode(start)) {
			System.out.println("end " + fcn);
			return -1;
		}
		if (start.getElement().equals(elementToFind)) {
			System.out.println("end " + fcn);
			return 0;
		}
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.add(start);
		toExplore.add(start);
		Map<String, String> backtrack = new HashMap<>();
		boolean found = false;
		all: while (!toExplore.isEmpty()) {
			Node current = toExplore.remove();
			for (Node neighbor : graph.getNodeNeighbors(current)) {								
				if (!marked.contains(neighbor)) {
					System.out.println(neighbor.getElement() + "->" + current.getElement());
					backtrack.put(neighbor.getElement(), current.getElement());
					if (neighbor.getElement().equals(elementToFind)) {
						found = true;
						toExplore.clear();
						break all;
					}
					marked.add(neighbor);
					toExplore.add(neighbor);
				}
			}
		}
		if (found) {
			System.out.println("backtrack: " + backtrack);
			System.out.println("elementToFind: " + elementToFind);
			System.out.println("start: " + start.getElement());
			int stepCount = 0;
			String currentStep = elementToFind;
			while (!currentStep.equals(start.getElement())) {
				currentStep = backtrack.get(currentStep);
				stepCount++;
				if (currentStep == null) {
					System.out.println("end " + fcn);
					return -1;
				}
			}
			System.out.println("end " + fcn);
			return stepCount;
		}
		System.out.println("end " + fcn);
		return -1;
	}

	public Set<String> nodesWithinDistance(Graph graph, Node srcNode, int distance) {
		System.out.println("begin nodesWithinDistance " + srcNode + ", " + distance);
		if (!graph.containsNode(srcNode)) {
			System.out.println("end nodesWithinDistance " + srcNode + ", " + distance);
			return null;
		}
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.add(srcNode);
		toExplore.add(srcNode);
		int count = 0;
		Set<String> elementsInDistance = new HashSet<>();
		while (count < distance && !toExplore.isEmpty()) {
			Node current = toExplore.remove();
			boolean add = false;
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.contains(neighbor)) {
					marked.add(neighbor);
					add = true;
					toExplore.add(neighbor);
					elementsInDistance.add(neighbor.getElement());
				}
			}
			if (add) {
				count++;
			}
		}
		System.out.println("end nodesWithinDistance " + srcNode + ", " + distance);
		return elementsInDistance;
	}

}
