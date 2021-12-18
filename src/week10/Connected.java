package week10;

import week10.AbstractGraph;

import java.io.*;
import java.util.*;

public class Connected {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a file name: ");
		//C:\\Users\\Joshua Rajan\\eclipse\\CSE 260 TA\\src\\lab17\\test1.txt
		String fileName = input.nextLine();
		
		Scanner graph = new Scanner(new File(fileName));
		int vertices = graph.nextInt();
		System.out.println("The number of vertices is " + vertices);
		
		List<AbstractGraph.Edge> list = new ArrayList<>();
		String s = graph.nextLine();
		while (graph.hasNext()) {
			s = graph.nextLine();
			String[] tokens = s.split("[\\s+]");
			int u = Integer.parseInt(tokens[0]);
			for (int i = 0; i < tokens.length; i++) {
				int v = Integer.parseInt(tokens[i]);
				list.add(new AbstractGraph.Edge(u, v));
			}
		}
		
		Graph<Integer> g = new UnweightedGraph<>(list, vertices);
		g.printEdges();
		boolean connected = false;
		for (int i = 0; i < vertices; i++) {
			AbstractGraph<Integer>.Tree tree = g.dfs(i);
			
			if (tree.getNumberOfVerticesFound() == vertices) {
				connected = true;
				break;
			}
		}
		System.out.print("The graph is");
		if (connected)
			System.out.print(" connected.");
		else 
			System.out.print(" not connected.");

		String st = "rahul";
		st += 'f';
	}
}
