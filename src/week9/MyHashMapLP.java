package week9;

public class MyHashMapLP<K, V> implements MyMap<K, V> {
	private static int DEFAULT_INITIAL_CAPACITY = 4;
	private static int MAXIMUM_CAPACITY = 1 << 30;
	private int capacity;
	private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;
	private float loadFactorThreshold;
	private int size = 0;
	MyMap.Entry<K, V>[] table;

	/** Construct a map with the default capacity and load factor */
	public MyHashMapLP() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
	}

	/**
	 * Construct a map with the specified initial capacity and default load factor
	 */
	public MyHashMapLP(int initialCapacity) {
		this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
	}

	/**
	 * Construct a map with the specified initial capacity and load factor
	 */
	public MyHashMapLP(int initialCapacity, float loadFactorThreshold) {
		if (initialCapacity > MAXIMUM_CAPACITY)
			this.capacity = MAXIMUM_CAPACITY;
		else
			this.capacity = trimToPowerOf2(initialCapacity);
		this.loadFactorThreshold = loadFactorThreshold;
		table = new MyMap.Entry[capacity];
	}

	@Override /** Remove all of the entries from this map */
	public void clear() {
		size = 0;
		removeEntries();
	}

	@Override /** Return true if the specified key is in the map */
	public boolean containsKey(K key) {
		return get(key) != null;
	}

	@Override /** Return the value that matches the specified key */
	public V get(K key) {
		int index = hash(key.hashCode());
		int h = 1;
		while (table[index] != null) {
			if (table[index].getKey().equals(key)) {
				return table[index].getValue();
			}
			index = (index + h) % capacity;
			h++;
		}
		return null;
	}

	@Override /** Return true if this map contains no entries */
	public boolean isEmpty() {
		return size == 0;
	}

	@Override /** Return a set consisting of the keys in this map */
	public java.util.Set<K> keySet() {
		java.util.Set<K> set = new java.util.HashSet<K>();
		for (int i = 0; i < capacity; i++)
			if (table[i] != null)
				set.add(table[i].getKey());
		return set;
	}

	@Override /** Return true if this map contains the value */
	public boolean containsValue(V value) {
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				if (table[i].getValue().equals(value))
					return true;
			}
		}
		return false;
	}

	@Override /** Return a set of entries in the map */
	public java.util.Set<MyMap.Entry<K, V>> entrySet() {
		java.util.Set<MyMap.Entry<K, V>> set = new java.util.HashSet<MyMap.Entry<K, V>>();
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				set.add(table[i]);
			}
		}
		return set;
	}

	@Override /** Return the number of entries in this map */
	public int size() {
		return size;
	}

	@Override /** Add an entry (key, value) into the map */
	public V put(K key, V value) {
		if (get(key) != null) { // The key is already in the map
			int index = hash(key.hashCode());
			V oldValue = table[index].getValue();
			table[index].value = value;
			return oldValue;
		}

		// Check load factor
		if (size >= capacity * loadFactorThreshold) {
			if (capacity == MAXIMUM_CAPACITY)
				throw new RuntimeException("Exceeding maximum capacity");
			rehash();
		}
		int index = hash(key.hashCode());
		int i = index;
		int h = 1;
		do {
			if (table[index] == null) {
				table[index] = new MyMap.Entry<K, V>(key, value);
				size++;
				return value;
			} else { //There's been a collision. Do linear probing.
				index = (index + h) % capacity;
				h++;
			}
		} while (i != index);
		return null;
	}

	@Override /** Remove the entries for the specified key */
	public void remove(K key) {
		int index = hash(key.hashCode());
		int h = 1;
		while (table[index].getKey() != key) {
			index = (index + h) % capacity;
			h++;
		}
		table[index] = null;
		size--;
		rehash();
	}

	@Override /** Return a set consisting of the values in this map */
	public java.util.Set<V> values() {
		java.util.Set<V> set = new java.util.HashSet<V>();
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				set.add(table[i].getValue());
			}
		}
		return set;
	}

	/** Hash function */
	/*
	 * private int hash(int hashCode) { return supplementalHash(hashCode) &
	 * (capacity - 1); }
	 */

	private int hash(int key) {
		return key % capacity;
	}

	/** Ensure the hashing is evenly distributed */
	private static int supplementalHash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	/** Return a power of 2 for initialCapacity */
	private int trimToPowerOf2(int initialCapacity) {
		int capacity = 1;
		while (capacity < initialCapacity) {
			capacity <<= 1;
		}
		return capacity;
	}

	/** Remove all entries from each bucket */
	private void removeEntries() {
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				table[i] = null;
			}
		}
	}

	/** Rehash the map */
	private void rehash() {
		java.util.Set<MyMap.Entry<K, V>> set = entrySet();
		capacity <<= 1;
		table = new MyMap.Entry[capacity];
		size = 0;
		for (MyMap.Entry<K, V> entry : set) {
			put(entry.getKey(), entry.getValue()); // Store to new table
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null)
				builder.append(table[i]);
		}
		builder.append("]");
		return builder.toString();
	}
}