package assign08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Represents a generically-typed binary search tree.
 * 
 * For every node X, all elements in X's left subtree are smaller than X.element
 * and all elements in X's right subtree are larger then X.element.
 * 
 * @author Erin Parker
 * @version March 17, 2022
 */
public class BinarySearchTreeOld1<Type extends Comparable<? super Type>> implements SortedSet<Type> {

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
			this.dotLabel = dotLabelCount++;
		}

		public void traverse(BinaryNode child) {
			if (child == null) {
				return;
			}
			current = child;
			traverse(child.leftChild);
			traverse(child.rightChild);
		}

		public void search(BinaryNode child, Type target) {
			if (child == null) {
				return;
			}
			current = child;
			if (child.element == target) {
				return;
			}

			search(child.leftChild, target);
			search(child.rightChild, target);
		}

		/**
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

	// representation of a binary search tree
	private BinaryNode root;
	private BinaryNode current;
	private int size;

	// additional member used only for generating DOT format
	private int dotLabelCount;

	public BinarySearchTreeOld1() {
		root = null;
		size = 0;
		dotLabelCount = 0;
		current = null;
	}

//	/**
//	 * Return true if the item exists in the BST.
//	 * 
//	 * COST: O(tree height)
//	 */
//	public boolean contains(Type item) {
//		// recursion: use this method as a driver, add recursive method to BinaryNode
//		// (BASE CASE? RECURSIVE CALL?)
//		if(item.compareTo(root.element)< 0) {
//			root.traverse(root.leftChild);
//		} else(item.compareTo(root.element)> 0) {
//			root.traverse(root.rightChild);
//		}
//
//		// iterative:
//		// set temp node to root
//		// while temp is not null
//		// compare ?? to item (WHAT ARE THE 3 CASES?)
//
//		return false;
//	}

//	/**
//	 * Add the new item to the BST such that the order is maintained. Duplicates are
//	 * not allowed.
//	 * 
//	 * COST: O(tree height)
//	 */
//	public void add(Type item) {
//		// recursion: use this method as a driver, add recursive method to BinaryNode
//		// (BASE CASE? RECURSIVE CALL?)
//
//		// iterative:
//		// set temp node to root
//		// in a loop
//		// compare temp's element to item
//		// CASE 1--they are equal: return (do nothing because item is a duplicate)
//		// CASE 2--item is bigger: if temp has a right child, advance temp to the right;
//		// else set temp's right child to be a new node containing item and return
//		// CASE 3--item is smaller: do the opposite of CASE 2 (i.e., go down the left
//		// side of the tree)
//	}

	/**
	 * Find and remove the item from the BST such that that order is maintained. If
	 * the BST does not contain the item, do nothing.
	 * 
	 * COST: O(tree height)
	 */
	public boolean remove(Type item) {
		// recursion: gets complicated . . .
		current = root;
		if (size == 0) {

		}
		if (item.compareTo(root.element) < 0) {
			root.search(root.leftChild, item);
		} else {
			root.search(root.rightChild, item);
		}

		if (current.leftChild == null && current.rightChild == null) {
			current = null;
		} else if (current.leftChild == null) {
			current = current.rightChild;
		} else if (current.rightChild == null) {
			current = current.leftChild;
		} else {

		}
//		item.compareTo(root.element) < 0
		// Remove leaf node

		// Removing a node with a single right child

		// Removing a node with a single left child

		// Removing a node with two children

		// iterative:
		// set temp node to root
		// in a loop
		// compare temp's element to item
		// CASE 1--they are equal: do the remove, see CASES A-C below
		// CASE 2--item is bigger: if temp does not have a right child, return (the item
		// is not in the tree);
		// else advance temp to the right
		// CASE 3--item is smaller: if temp does not have a left child, return (the item
		// is not in the tree);
		// else advance temp to the left

		// to remove a node,
		// CASE A--leaf node: simply delete it (i.e., set the parent's child link to
		// null)
		// CASE B--node with one child: adjust its parent's child link to bypass the
		// node and go directly to the node's child
		// CASE C--node with two children: replace the node's data with that of the
		// smallest node of its right subtree (its successor),
		// then remove the successor node (guaranteed to have at most one child)
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

//	@Override
//	public boolean add(Type item) {
//		if (size == 0) {
//			root = new BinaryNode(item, null, null, null);
//			current = root;
//		}
//		else (item.compareTo(root.element) < 0) {
//			BinaryNode temp = root;
//			if () {
//				
//			}
//
//		} else if (item.compareTo(root.element) > 0) {
//			BinaryNode temp = root;
//			if () {
//				
//			}
//		}
//
//		if (item.compareTo(root.element) < 0) {
//			BinaryNode tempLeft = root.leftChild;
//		}
//		temp.traverse(temp.rightChild, temp)
//	}

	@Override
	public boolean addAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub

		for (Type t : items) {
			add(t);
		}
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		for (Type t : items) {
			if (!contains(t)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Type first() throws NoSuchElementException {
		BinaryNode temp = root;
		temp.traverse(temp.leftChild);

		return temp.element;
	}

	@Override
	public boolean isEmpty() {
		if (root.element != null) {

		}
		return false;
	}

	@Override
	public Type last() throws NoSuchElementException {
		current = root;
		root.traverse(root.rightChild);

		return root.element;
	}

	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Type> toArrayList() {
		// TODO Auto-generated method stub
		return null;
	}
}