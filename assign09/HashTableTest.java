package assign09;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class HashTableTest<K, V> {
	ArrayList<MapEntry<K, V>> table;

	@Before
	public void setUp() {
		table = new ArrayList<MapEntry<K, V>>();
		for (int i = 0; i < 7; i++)
			table.add(null);
	}

	@Test
	public void testProbAdd() {
		V value = null;
		K key = null;
		table.add(7, table.add(e));
	}
}
