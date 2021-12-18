package week9;

import week9.AbstractTree;

public class BST<E extends Comparable<E>> extends AbstractTree<E> {
	protected TreeNode<E> root;
	protected int size = 0;

	/** Create a default binary tree */
	public BST() {
	}

	/** Create a binary tree from an array of objects */
	public BST(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			insert(objects[i]);
	}

	@Override /** Returns true if the element is in the tree */
	public boolean search(E e) {
		TreeNode<E> current = root; // Start from the root
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else // element matches current.element
				return true; // Element is found
		}
		return false;
	}

	public TreeNode<E> searchAndGet(E e) {
		TreeNode<E> current = root; // Start from the root
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else // element matches current.element
				return current; // Element is found
		}
		return null;
	}

	public boolean isLeaf(E e) {
		TreeNode<E> current = root; // Start from the root
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else // element matches current.element
			if (current.left == null && current.right == null) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override /**
				 * Insert element o into the binary tree Return true if the element is inserted
				 * successfully
				 */
	public boolean insert(E e) {
		if (root == null)
			root = createNewNode(e); // Create a new root
		else {
			// Locate the parent node
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null)
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else
					return false; // Duplicate node not inserted
			// Create the new node and attach it to the parent node
			if (e.compareTo(parent.element) < 0) {
				TreeNode<E> temp = createNewNode(e);
				parent.left = temp;
				temp.parent = parent;
			} else {
				TreeNode<E> temp = createNewNode(e);
				parent.right = temp;
				temp.parent = parent;
			}
		}
		size++;
		return true; // Element inserted successfully
	}

	@Override /** Preorder traversal from the root */
	public void preorder() {
		preorder(root);
	}

	/** Preorder traversal from a subtree */
	protected void preorder(TreeNode<E> root) {
		if (root == null)
			return;
		System.out.print(root.element + " ");
		preorder(root.left);
		preorder(root.right);
	}

	/**
	 * This inner class is static, because it does not access any instance members
	 * defined in its outer class
	 */
	public static class TreeNode<E extends Comparable<E>> {
		protected E element;
		protected TreeNode<E> left;
		protected TreeNode<E> right;
		protected TreeNode<E> parent;
		protected int size = 1;

		public TreeNode(E e) {
			element = e;
		}
	}

	protected TreeNode<E> createNewNode(E e) {
		return new TreeNode<>(e);
	}

	@Override /** Inorder traversal from the root */
	public void inorder() {
		inorder(root);
	}

	/** Inorder traversal from a subtree */
	protected void inorder(TreeNode<E> root) {
		if (root == null)
			return;
		inorder(root.left);
		System.out.print(root.element + " ");
		inorder(root.right);
	}

	@Override /** Postorder traversal from the root */
	public void postorder() {
		postorder(root);
	}

	/** Postorder traversal from a subtree */
	protected void postorder(TreeNode<E> root) {
		if (root == null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.element + " ");
	}

	@Override /** Get the number of nodes in the tree */
	public int getSize() {
		return size;
	}

	/** Returns the root of the tree */
	public TreeNode<E> getRoot() {
		return root;
	}

	/** Returns a path from the root leading to the specified element */
	public java.util.ArrayList<TreeNode<E>> path(E e) {
		java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();

		TreeNode<E> current = root; // Start from the root
		while (current != null) {
			list.add(current); // Add the node to the list
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else
				break;
		}
		return list; // Return an array list of nodes
	}

	public java.util.ArrayList<TreeNode<E>> getPath(E e) {
		java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();

		TreeNode<E> current = root; // Start from the root
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				list.add(current);
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				list.add(current);
				current = current.right;
			} else {// element matches current.element
				list.add(current);
				break;
			}
		}

		return list;
	}

	@Override /**
				 * Delete an element from the binary tree. Return true if the element is deleted
				 * successfully Return false if the element is not in the tree
				 */
	public boolean delete(E e) {
		// Locate the node to be deleted and also locate its parent node
		TreeNode<E> parent = null;
		TreeNode<E> current = root;
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				parent = current;
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				parent = current;
				current = current.right;
			} else
				break; // Element is in the tree pointed at by current
		}
		if (current == null)
			return false; // Element is not in the tree
		// Case 1: current has no left child
		if (current.left == null) {
			// Connect the parent with the right child of the current node
			if (parent == null) {
				root = current.right;
			} else {
				if (e.compareTo(parent.element) < 0) {
					parent.left = current.right;
					if (current.right != null)
						current.right.parent = current.parent;
				}
				else {
					parent.right = current.right;
					if (current.right != null)
						current.right.parent = current.parent;
				}
			}
		} else {
			// Case 2: The current node has a left child
			// Locate the rightmost node in the left subtree of
			// the current node and also its parent
			TreeNode<E> parentOfRightMost = current;
			TreeNode<E> rightMost = current.left;
			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right; // Keep going to the right
			}
			// Replace the element in current with the element in rightMost
			current.element = rightMost.element;
			// Eliminate rightmost node
			if (parentOfRightMost.right == rightMost) {
				parentOfRightMost.right = rightMost.left;
				if (rightMost.left != null)
					rightMost.left.parent = rightMost.parent;
			}
			else {
				// Special case: parentOfRightMost == current
				parentOfRightMost.left = rightMost.left;
				if (rightMost.left != null)
					rightMost.left.parent = rightMost.parent;
			}
		}
		size--;
		return true; // Element deleted successfully
	}

	@Override /** Obtain an iterator. Use inorder. */
	public java.util.Iterator<E> iterator() {
		return new InorderIterator();
	}

	// Inner class InorderIterator
	private class InorderIterator implements java.util.Iterator<E> {
		// Store the elements in a list
		private java.util.ArrayList<E> list = new java.util.ArrayList<>();
		private int current = 0; // Point to the current element in list

		public InorderIterator() {
			inorder(); // Traverse binary tree and store elements in list
		}

		/** Inorder traversal from the root */
		private void inorder() {
			inorder(root);
		}

		/** Inorder traversal from a subtree */
		private void inorder(TreeNode<E> root) {
			if (root == null)
				return;
			inorder(root.left);
			list.add(root.element);
			inorder(root.right);
		}

		@Override /** More elements for traversing? */
		public boolean hasNext() {
			if (current < list.size())
				return true;
			return false;
		}

		@Override /** Get the current element and move to the next */
		public E next() {
			return list.get(current++);
		}

		@Override /** Remove the current element */
		public void remove() {
			delete(list.get(current)); // Delete the current element
			list.clear(); // Clear the list
			inorder(); // Rebuild the list
		}

	}

	/** Remove all elements from the tree */
	public void clear() {
		root = null;
		size = 0;
	}

	public java.util.ListIterator<E> bidirectionalIterator() {
		return new BidirectionalIterator();
	}

	// Inner class InorderIterator
	private class BidirectionalIterator implements java.util.ListIterator<E> {
		// Store the elements in a list
		private java.util.ArrayList<E> list = new java.util.ArrayList<>();
		private int current = 0; // Point to the current element in list

		public BidirectionalIterator() {
			inorder(); // Traverse binary tree and store elements in list
		}

		private void inorder() {
			inorder(root);
		}

		private void inorder(TreeNode<E> root) {
			if (root == null)
				return;
			inorder(root.left);
			list.add(root.element);
			inorder(root.right);
		}

		@Override /** More elements for traversing? */
		public boolean hasNext() {
			if (current < list.size())
				return true;
			return false;
		}

		@Override /** Get the current element and move to the next */
		public E next() {
			return list.get(current++);
		}

		@Override /** Remove the current element */
		public void remove() {
			delete(list.get(current)); // Delete the current element
			list.clear(); // Clear the list
			inorder(); // Rebuild the list
		}

		@Override
		public void add(E e) {
			insert(e);
			list.clear();
			inorder();
		}

		@Override
		public boolean hasPrevious() {
			if (current > 0) {
				return true;
			}
			return false;
		}

		@Override
		public int nextIndex() {
			return (current + 1);
		}

		@Override
		public E previous() {
			return list.get(current--);
		}

		@Override
		public int previousIndex() {
			return (current - 1);
		}

		@Override
		public void set(E arg0) {

		}
	}

}