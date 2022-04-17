package assign08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	private BinaryNode root;
	private int size;
	private BinaryNode current;

	/**
	 * Represents a (generic) node in a binary tree.
	 */
	private class BinaryNode {
		// representation of a binary node
		Type element;
		BinaryNode leftChild;
		BinaryNode rightChild;
		// additional member used only for generating DOT format
		public int dotLabel;

		public BinaryNode(Type element, BinaryNode leftChild, BinaryNode rightChild) {
			this.element = element;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}

		public BinaryNode findMin() {
			if (getLeft() == null)
				return this;

			return leftChild.findMin();
		}

		public BinaryNode findMax() {
			if (getRight() == null)
				return this;

			return rightChild.findMax();

		}

		public BinaryNode getRight() {
			return rightChild;
		}

		public BinaryNode getLeft() {
			return leftChild;
		}

		public Type getData() {
			return element;
		}

		public void setLeftChild(BinaryNode item) {
			leftChild = item;
		}

		public void setRightChild(BinaryNode item) {
			rightChild = item;
		}

//		public void traverse(Type item) {
//			if (this.leftChild != null && !this.element.equals(item) && this.element.compareTo(item) > 0)
//				this.leftChild.traverse(item);
//			else
//				current = this;
//
//			if (this.rightChild != null && !this.element.equals(item) && this.element.compareTo(item) < 0)
//				this.rightChild.traverse(item);
//		}

		public boolean contains(Type elem) {
			int compare = elem.compareTo(this.element);
			if (compare == 0)
				return true;
			else if (compare > 0) {
				if (getRight() != null)
					return getRight().contains(elem);
				else
					return false;
			} else {
				if (getLeft() != null)
					return getLeft().contains(elem);
				else
					return false;
			}
		}

		public void inOrderTraversal(List<Type> list) {
			if (this.leftChild != null)
				this.leftChild.inOrderTraversal(list);
			list.add(this.element);
			if (this.rightChild != null)
				this.rightChild.inOrderTraversal(list);
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

	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	// FIX incrementing size
	public boolean addHelper(Type item) {
		BinaryNode parent = root;
		while (true) {
			if (item.compareTo(parent.element) < 0) {
				if (parent.getLeft() != null) {
					parent = parent.getLeft();
				} else {
					parent.setLeftChild(new BinaryNode(item, null, null));
					size++;
					return true;
				}
			} else if (item.compareTo(parent.element) > 0) {
				// if we can traverse right
				if (parent.getRight() != null) {
					parent = parent.getRight();
				} else {
					parent.setRightChild(new BinaryNode(item, null, null));
					size++;
					return true;
				}
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean add(Type item) {
		if (item == null)
			throw new NullPointerException();

		if (isEmpty()) {
			root = new BinaryNode(item, null, null);
			return true;
		} else {
			return addHelper(item);
		}
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

//	@Override
//	public boolean contains(Type item) {
//
//		if (root.element.compareTo(item) == 0) {
//			current = root;
//			return true;
//		}
//
//		root.traverse(item);
//
//		if (current.element.equals(item))
//			return true;
//
//		return false;
//
//	}

	@Override
	public boolean contains(Type item) {
		// check for null
		if (item == null)
			throw new NullPointerException();

		if (isEmpty())
			return false;
		else
			return root.contains(item);

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
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		return root.findMin().getData();
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public Type last() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return root.findMax().getData();
	}

	@Override
	public boolean remove(Type item) {
		if (item == null) {
			throw new NullPointerException();
		}
		if (isEmpty()) {
			return true;
		}
		BinaryNode toRemove = null;
		BinaryNode parent = this.root;
		while (!parent.getData().equals(item)) {
			if (item.compareTo(parent.getData()) < 0) {
				if (parent.getLeft() != null) {
					toRemove = parent;
					parent = parent.getLeft();
				} else {
					return false;
				}

			} else if (item.compareTo(parent.getData()) > 0) {
				if (parent.getRight() != null) {
					toRemove = parent;
					parent = parent.getRight();
				} else {
					return false;
				}
			}
		}

		// CASES
		if (parent.getRight() == null) {
			if (toRemove == null) {
				this.root = parent.getLeft();
			} else if (parent == toRemove.rightChild) {
				// Changed
				toRemove.setRightChild(parent.getRight());
			} else {
				toRemove.setLeftChild(parent.getLeft());
			}
		} else if (parent.getLeft() == null) {
			if (toRemove == null) {
				this.root = parent.getRight();
			} else if (parent == toRemove.leftChild) {
				toRemove.setLeftChild(parent.getRight());
			} else {
				toRemove.setRightChild(parent.getRight());
			}
		} else {
			// NOT USING GETSUCCESSOR BECAUSE WE WANT REFERENCE TO PARENT
			BinaryNode successor = parent.getRight();
			BinaryNode parentSucc = parent;
			while (successor.getLeft() != null) {
				parentSucc = successor;
				successor = successor.getRight();
			}

			// Move the successor
			if (toRemove == null) {
				this.root = successor;
			} else if (parent == toRemove.leftChild) {
				toRemove.setLeftChild(successor);
			} else {
				toRemove.setRightChild(successor);
			}

			// Set the successors parent to the target value
			if (successor == parentSucc.getLeft()) {
				parentSucc.setLeftChild(successor.getRight());
			} else {
				parentSucc.setRightChild(successor.getRight());
			}

			successor.setRightChild(parent.getRight());
			successor.setLeftChild(parent.getLeft());
		}
		size--;
		return true;
	}

	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		int initialSize = size;
		for (Type t : items)
			remove(t);
		return initialSize != size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ArrayList<Type> toArrayList() {
		ArrayList<Type> temp = new ArrayList<>();
		root.inOrderTraversal(temp);

		return temp;
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
