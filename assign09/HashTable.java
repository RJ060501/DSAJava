package assign09;

import java.util.List;

public class HashTable<K, V> implements Map<K, V> {
	// Protected so we can access in derived classes

	protected int size;
	protected int tableSize;
	protected HashFunctor functor;
	protected double lambda;

	/**
	 * Helper method used to generate the next prime integer
	 * 
	 * @param n - the lower bound, we want a prime larger than this
	 * @return - the next prime
	 */
	protected final static int nextPrime(int n) {
		int prime;
		// In case of even number.
		if (n % 2 == 0)
			n++;

		// Bertrand's postulate:
		// Presence of prime numbers in
		// range (n, 2n - 2)
		for (prime = n; prime < 2 * n + 2; prime += 2) {
			if (isPrime(prime))
				return prime;
		}

		return -1;// Detects an error, since this should never happen.
	}

	/**
	 * Helper method used to check if an integer is prime, returns True if the
	 * integer is prime, false otherwise.
	 */
	protected final static boolean isPrime(int n) {
		if (n % 2 == 0)
			return false;
		// If it is not a prime number (composite integer), then it has a divisor <= to
		// the sqrt(p)
		for (int i = 3; i <= Math.sqrt(n); i += 2) {
			if (n % i == 0)
				return false;
		}

		return true;
	}

	@Override
	public void clear() {
		// Sets everything to null...
		// FIX: Easier implemented in ProbingHashTable class. However, must be
		// implemented here. Maybe call the one from probing class somehow.
		for (int i = 0; i < this.tableSize; i++) {
			this.table[i] = null;
		}
		this.size = 0;
	}

	@Override
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MapEntry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

}
