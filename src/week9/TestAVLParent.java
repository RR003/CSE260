package week9;

import week9.AVLTree;

import java.util.ArrayList;

public class TestAVLParent {
	public static void main(String[] args) {
		ArrayList<Integer> allInts = new ArrayList<>();
		AVLTree<Integer> avl = new AVLTree<>();

		for (int i = 1; i < 101; i++) {
			allInts.add(i);
			avl.insert(i);
		}

		for (int i : allInts) {
			if (avl.isLeaf(i)) {
				ArrayList<AVLTree.TreeNode<Integer>> list = avl.path(i);
				System.out.print("[");
				for (int j = 0; j < list.size() - 1; j++) {
					System.out.print(list.get(j).element + " ");
				}
				System.out.print(list.get(list.size() - 1).element + "]" + "\n");
			}
		}
	}
}