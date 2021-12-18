package week10;

import week10.AbstractGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestPath {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a file name: ");
		String fileName = "C:\\Users\\Joshua Rajan\\eclipse\\CSE 260 TA\\src\\lab17\\test1.txt";
		
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
		
		UnweightedGraph<Integer> g = new UnweightedGraph<>(list, vertices);
		g.printEdges();
		
		List path = g.getPath(0, 4);
		System.out.println(path);
	}
}
