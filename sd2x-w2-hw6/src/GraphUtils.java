
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {
		if (graph == null || src == null || dest == null) {
			return -1;
		}

		if (!graph.containsElement(src)) {
			return -1;
		}

		if (!graph.containsElement(dest)) {
			return -1;
		}

		BreadthFirstSearch s = new BreadthFirstSearch(graph);
		Node start = graph.getNode(src);

		return s.shortestPath(start, dest);
	}

	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {

		if (graph == null || src == null || distance < 1) {
			return null;
		}

		if (!graph.containsElement(src)) {
			return null;
		}

		BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
		Node srcNode = graph.getNode(src);
		return bfs.nodesWithinDistance(graph, srcNode, distance);
	}

	public static boolean isHamiltonianPath(Graph g, List<String> values) {
		System.out.println("begin isHamiltonianPath " + values);
		if (g == null || values == null) {
			System.out.println("end isHamiltonianPath " + values);
			return false;
		}
		
		if (values.size() < 2) {
			System.out.println("end isHamiltonianPath " + values);
			return false;
		}
		
		if (!values.get(0).equals(values.get(values.size() - 1))) {
			System.out.println("end isHamiltonianPath " + values);
			return false;
		}
		
		List<String> visited = new ArrayList<>();
		for (int i = 1; i < values.size(); i++) {
			String element = values.get(i);
			if (!g.containsElement(element)) {
				System.out.println("end isHamiltonianPath " + values);
				return false;
			}
			if (visited.contains(element)) {
				System.out.println("end isHamiltonianPath " + values);
				return false;
			}
			visited.add(element);
			Node currNode = g.getNode(element);
			Node prevNode = g.getNode(values.get(i -1 ));
			Set<Node> prevNeighbors = g.getNodeNeighbors(prevNode);
			if (!prevNeighbors.contains(currNode)) {
				System.out.println("end isHamiltonianPath " + values);
				return false;
			}
		}
		if (visited.size() < g.getNumNodes()) {
			System.out.println("end isHamiltonianPath " + values);
			return false;
		}
//		System.out.println("graph: " + g);
//		System.out.println("values:" + values);
		
		System.out.println("end isHamiltonianPath " + values);
		return true; // this line is here only so this code will compile if you don't modify it
	}

}
