package assign08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class BinarySearchTreeOld<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	private BinaryNode root;
	private int size;
	private BinaryNode current;

	/**
	 * Represents a (generic) node in a binary tree.
	 */
	private class BinaryNode {
		// representation of a binary node
		public Type element;
		public BinaryNode leftChild;
		public BinaryNode rightChild;
		public BinaryNode parent;
		// additional member used only for generating DOT format
		public int dotLabel;

		public BinaryNode(Type element, BinaryNode leftChild, BinaryNode rightChild, BinaryNode parent) {
			this.element = element;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.parent = parent;
		}

		public void insert(BinaryNode child, Type item) {
			if (child == null)
				return;
			current = child;
			if (child.element.compareTo(item) > 0)
				insert(child.leftChild, item);
			if (child.element.compareTo(item) < 0)
				insert(child.rightChild, item);
			else
				return;
		}

		public void findMin(BinaryNode child) {
			if (child == null)
				return;
			current = child;

			findMin(child.leftChild);

		}

		public void findMax(BinaryNode child) {
			if (child == null)
				return;
			current = child;

			findMax(child.rightChild);

		}

		public void search(BinaryNode child, Type target) {
			if (child == null)
				return;

			if (child.element.equals(target)) {
				current = child;
				return;
			}

			search(child.leftChild, target);
			search(child.rightChild, target);
		}

		/***
		 * @return a string containing all of the edges in the tree rooted at "this"
		 *         node, in DOT format
		 */
		public String generateDot() {
			String ret = "\tnode" + dotLabel + " [label = \"<f0> |<f1> " + element + "|<f2> \"]\n";
			if (leftChild != null)
				ret += "\tnode" + dotLabel + ":f0 -> node" + leftChild.dotLabel + ":f1\n" + leftChild.generateDot();
			if (rightChild != null)
				ret += "\tnode" + dotLabel + ":f2 -> node" + rightChild.dotLabel + ":f1\n" + rightChild.generateDot();
			return ret;
		}

	}

	public BinarySearchTreeOld() {
		root = null;
		size = 0;
	}

	@Override
	public boolean add(Type item) {
		if (size == 0) {
			root = new BinaryNode(item, null, null, null);
			current = root;
			size++;
			return true;
		} else if (!contains(item)) {
			if (item.compareTo(root.element) < 0) {
				current = root;

				root.insert(root.leftChild, item);

				if (item.compareTo(current.element) < 0)
					current.leftChild = new BinaryNode(item, null, null, current);
				else
					current.rightChild = new BinaryNode(item, null, null, current);

			}

			else if (item.compareTo(root.element) > 0) {
				current = root;

				root.insert(root.rightChild, item);

				if (item.compareTo(current.element) < 0)
					current.leftChild = new BinaryNode(item, null, null, current);
				else
					current.rightChild = new BinaryNode(item, null, null, current);

			}
			size++;
			return true;
		}
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Type> items) {
		int initialSize = size;
		for (Type t : items)
			add(t);
		return initialSize != size;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public boolean contains(Type item) {
		if (item.compareTo(root.element) == 0) {
			current = root;
			return true;
		}
		current = root;
		if (item.compareTo(root.element) < 0) {
			root.search(root.leftChild, item);
			if (current.element.equals(item))
				return true;

		}
		if (item.compareTo(root.element) > 0) {
			root.search(root.rightChild, item);
			if (current.element.equals(item))
				return true;

		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		for (Type t : items) {
			if (!contains(t))
				return false;
		}

		return true;
	}

	@Override
	public Type first() throws NoSuchElementException {
		current = root;
		if (root == null) {
			throw new NoSuchElementException();
		}

		root.findMin(root.leftChild);
		return current.element;
	}

	@Override
	public boolean isEmpty() {
		return root.equals(null);
	}

	@Override
	public Type last() throws NoSuchElementException {
		current = root;
		if (root == null) {
			throw new NoSuchElementException();
		}
		root.findMax(root.rightChild);
		return current.element;
	}

	@Override
	public boolean remove(Type item) {
		if (contains(item)) {
			current = root;
			BinaryNode toRemove;
			if (item.compareTo(root.element) < 0) {
				// FIX
				root.search(root.leftChild, item);
			} else
				root.search(root.rightChild, item);

			if (current.rightChild == null && current.leftChild == null) {
				if (current.parent.rightChild.equals(current)) {
					// FIX
					current = current.parent;
					current.rightChild = null;
				} else {
					// FIX
					current = current.parent;
					current.leftChild = null;
				}
			} else if (current.rightChild == null) {
				current.element = current.leftChild.element;

				current.rightChild = current.leftChild.rightChild;
				current.leftChild = current.leftChild.leftChild;
			} else if (current.leftChild == null) {
				current.element = current.rightChild.element;

				// Handles null pointers
				current.leftChild = current.rightChild.leftChild;
				current.rightChild = current.rightChild.rightChild;
			} else {
				toRemove = current;

				current.findMax(current.leftChild);

				// FIX: 7's children are both null
				toRemove.element = current.element;
				current = null;
			}
			size--;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		int initialSize = size;
		for (Type t : items)
			add(first());
		remove(first());
		return initialSize != size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ArrayList<Type> toArrayList() {
		List<Type> temp = new ArrayList<>();

		return null;
	}

	/**
	 * Write a DOT representation of this BST to file.
	 * 
	 * @param filename
	 */
	public void generateDotFile(String filename) {
		try {
			PrintWriter out = new PrintWriter(filename);
			out.println("digraph Tree {\n\tnode [shape=record]\n");
			if (root == null)
				out.println("");
			else
				out.print(root.generateDot());
			out.println("}");
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
