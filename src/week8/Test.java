package week8;

import week8.BST;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter integers, separated by spaces."); //60 55 45 57 100 67 107
		String[] ints = input.nextLine().split(" ");

		BST<Integer> bst = new BST<>();
		for (String s : ints) {
			bst.insert(Integer.parseInt(s));
		}
		
		// Testing part 1 methods:
		System.out.println();
		bst.breadthFirstTraversal(); // week8.week11.Test breadth-first traversal
		System.out.println(" is the breadth-first traversal");
		
		System.out.println("Height: " + bst.height()); // week8.week11.Test height
		System.out.println("Number of leaves: " + bst.getNumberOfLeaves()); // week8.week11.Test getNumberOfLeaves
		System.out.println("Number of non-leaves: " + bst.getNumberOfNonLeaves());
		System.out.println("Is this a full week8.week9.BST: " + bst.isFullBST()); // week8.week11.Test isFullBST
		
		System.out.println("\nCreating a clone of the week8.week9.BST:");
		BST<Integer> bst2 = bst.clone(); //Deep copy of bst
		bst2.breadthFirstTraversal(); // week8.week11.Test breadth-first for bst2
		System.out.println(" is the breadth-first traversal");
		System.out.println("Are the two BSTs equal: " + bst.equals(bst2));
		
		// Testing part 2 methods:
		System.out.println("\nWith stack:");
		bst.inorderStack();
		System.out.println("is the inorder traversal.");
		bst.preorderStack();
		System.out.println("is the preorder traversal.");
		bst.postorderStack();
		System.out.println("is the postorder traversal.");
	}
}
