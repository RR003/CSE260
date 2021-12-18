package week11;

import week11.AbstractGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a file name: ");
		//C:\\Users\\Joshua Rajan\\eclipse\\CSE 260 TA\\src\\lab18\\test1.txt
		String fileName = input.nextLine();
		
		Scanner graph = new Scanner(new File(fileName));
		int vertices = graph.nextInt();
		
		List<AbstractGraph.Edge> list = new ArrayList<>();
		String s = graph.nextLine();
		while (graph.hasNext()) {
			s = graph.nextLine();
			String[] tokens = s.split("[\\s+]");
			int u = Integer.parseInt(tokens[0]);
			for (int i = 1; i < tokens.length; i++) {
				int v = Integer.parseInt(tokens[i]);
				list.add(new AbstractGraph.Edge(u, v));
			}
		}
		
		UnweightedGraph<Integer> g = new UnweightedGraph<>(list, vertices);
		g.printEdges();
		
		System.out.println(g.isCyclic() ? "The graph is cylic. " + g.getACycle(0) : "The graph is not cyclic.");
		System.out.println(g.isBipartite() ? "The graph is bipartite." : "The graph is not bipartite.");
		
	}
}
