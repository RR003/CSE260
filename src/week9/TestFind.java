package week9;

import week9.AVLTree;

import java.util.Scanner;

public class TestFind {
	public static void main(String[] args) {
		AVLTree<Double> tree = new AVLTree<>();
		Scanner input = new Scanner(System.in);
		System.out.print("Enter 15 doubles, separated by spaces: ");
		String[] doubles = input.nextLine().split(" ");
		for (int i = 0; i < 15; i++) {
			tree.insert(Double.parseDouble(doubles[i]));
		}
		while (true) {
			System.out.print("Enter k: ");
			int k = input.nextInt();
			System.out.println("The " + k + "th smallest number is " + tree.find(k, tree.root));
		}
	}
}