package week9;

import week9.AVLTree;
import week9.BST;

import java.util.*;

public class ComparePerformance {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		int length = 500000;
		Random rand = new Random();

		for (int i = 0; i < length; i++) {
			list.add(rand.nextInt(length));
		}

		AVLTree<Integer> avl = new AVLTree<>();
		BST<Integer> bst = new BST<>();

		// Begin week9.BST testing.
		long time = System.currentTimeMillis();
		for (int i = 0; i < length; i++) {
			bst.insert(list.get(i));
		}
		System.out.println("week9.BST insertion took " + (System.currentTimeMillis() - time) + " ms.");
		Collections.shuffle(list);
		time = System.currentTimeMillis();
		for (int i = 0; i < length; i++) {
			bst.search(list.get(i));
		}
		System.out.println("week9.BST search took " + (System.currentTimeMillis() - time) + " ms.");
		Collections.shuffle(list);
		time = System.currentTimeMillis();
		for (int i = 0; i < length; i++) {
			bst.delete(list.get(i));
		}
		System.out.println("week9.BST deletion took " + (System.currentTimeMillis() - time) + " ms.");

		// Begin AVL testing.
		time = System.currentTimeMillis();
		for (int i = 0; i < length; i++) {
			avl.insert(list.get(i));
		}
		System.out.println("AVL insertion took " + (System.currentTimeMillis() - time) + " ms.");
		Collections.shuffle(list);
		time = System.currentTimeMillis();
		for (int i = 0; i < length; i++) {
			avl.search(list.get(i));
		}
		System.out.println("AVL search took " + (System.currentTimeMillis() - time) + " ms.");
		Collections.shuffle(list);
		time = System.currentTimeMillis();
		for (int i = 0; i < length; i++) {
			avl.delete(list.get(i));
		}
		System.out.println("AVL deletion took " + (System.currentTimeMillis() - time) + " ms.");
	}
}
