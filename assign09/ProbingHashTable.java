package assign09;

import java.util.ArrayList;

public class ProbingHashTable<K, V> extends HashTable<K, V> {

	private ArrayList<MapEntry<K, V>> table;
	private int collisions = 0;

	public ProbingHashTable(int capacity, HashFunctor functor) {
		if (!isPrime(capacity))
			capacity = nextPrime(capacity);

		this.tableSize = capacity;
		this.functor = functor;
		this.lambda = 0;
		this.table = new ArrayList<MapEntry<K, V>>();
	}

	public boolean add(String item) {
		// Check if we need to rehash
		if (this.lambda > 0.5) {
			this.size = 0;// need this for insert function so size is accurate
			this.rehash();
		}
		// Get hash value
		int hashVal = functor.hash(item) % this.tableSize;

		// Helper insert method
		return insert(item, hashVal, this.table);
	}

	/**
	 * Helper method used in adding and rehashing
	 * 
	 * @param item    - item to be added
	 * @param hashVal - the item's hash value
	 * @param table   - the table to be inserted to
	 */
	private boolean insert(String item, int hashVal, ArrayList<MapEntry<K, V>> table) {
		int i = 1;// hash + i^2, used for quadratic probing
		int temp = hashVal;// insert the item at temp
		temp %= table.length;

		// Finds place to insert item
		while (true) {
			// If an empty slot, insert here
			if (table[temp] == null) {
				table[temp] = item;
				this.size++;
				this.lambda = (double) this.size / this.tableSize;// update lambda
				return true;
			}

			if (table[temp].equals(item))// check if the table contains item already
				return false;

			// Else:
			temp = hashVal + i * i;// Uses quadratic probing on next spot
			this.collisions++;
			// Loop around
			temp %= table.length;

			i++;// Increment quadratic probe
		}

	}

	/**
	 * Helper method used to rehash a table if lambda gets too large
	 */
	private void rehash() {
		int prime = this.tableSize * 2;
		prime = nextPrime(prime);// create a prime number atleast twice as large as the size of the table

		this.collisions = 0;

		// The replacement array
		String[] tempArr = new String[prime];

		// Move every item over to the new array
		for (int i = 0; i < this.tableSize; i++) {
			String tempStr = this.table[i];// Set a temp string
			if (tempStr != null) {
				// Get a new hash value
				int hashVal = functor.hash(tempStr);
				hashVal %= prime;
				insert(tempStr, hashVal, tempArr);// Insert all the items in the correct position
			}
		}

		// Set the pointers
		this.table = tempArr;
		this.tableSize = prime;

	}

	public boolean contains(String item) {
		final int HASH = functor.hash(item) % this.tableSize;// don't want HASH to change for this item
		int search = HASH;// probing value
		int i = 1;
		while (table[search] != null) {
			if (table[search].equals(item))
				return true;
			search = HASH + i * i;
			if (search > this.table.length)// loop around
				search %= table.length;
			i++;
		}
		return false;
	}

}
