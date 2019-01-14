import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<K, V> implements Iterable<HashPair<K, V>> {
	// num of entries to the table
	private int numEntries;
	// num of buckets
	private int numBuckets;
	// load factor needed to check for rehashing
	private static final double MAX_LOAD_FACTOR = 0.75;
	// ArrayList of buckets. Each bucket is a LinkedList of HashPair
	private ArrayList<LinkedList<HashPair<K, V>>> buckets;

	// constructor
	public MyHashTable(int initialCapacity) {
		// ADD YOUR CODE BELOW THIS
		buckets = new ArrayList<LinkedList<HashPair<K, V>>>();

		this.numBuckets = initialCapacity;
		this.numEntries = 0;

		for (int i = 0; i < numBuckets; i++) {
			LinkedList<HashPair<K, V>> initList = new LinkedList<HashPair<K, V>>();
			buckets.add(i, initList);
		}

		// ADD YOUR CODE ABOVE THIS
	}

	public int size() {
		return this.numEntries;
	}

	public int numBuckets() {
		return this.numBuckets;
	}

	/**
	 * Returns the buckets variable. Usefull for testing purposes.
	 */
	public ArrayList<LinkedList<HashPair<K, V>>> getBuckets() {
		return this.buckets;
	}

	/**
	 * Given a key, return the bucket position for the key.
	 */
	public int hashFunction(K key) {
		int hashValue = Math.abs(key.hashCode()) % this.numBuckets;
		return hashValue;
	}

	/**
	 * Takes a key and a value as input and adds the corresponding HashPair to this
	 * HashTable. Expected average run time O(1)
	 */

	public V put(K key, V value) {
		// ADD YOUR CODE BELOW HERE

		int bucketatpos = hashFunction(key);
		LinkedList<HashPair<K, V>> llatpos = new LinkedList<HashPair<K, V>>();
		llatpos = buckets.get(bucketatpos);
		HashPair<K, V> newhashpair = new HashPair<K, V>(key, value);

		for (HashPair<K, V> hashpair : llatpos) {
			if (hashpair.getKey().equals(key)) {
				V prevVal = hashpair.getValue();
				hashpair.setValue(value);
				return prevVal;
			}
		}

		numEntries++;
		buckets.get(bucketatpos).add(newhashpair);

		// loadfactor check
		double currentload = ((double) numEntries)/numBuckets;
		if (currentload >= MAX_LOAD_FACTOR) {
			rehash();
		}
		return null;
	}

	// ADD YOUR CODE ABOVE HERE

	/**
	 * Get the value corresponding to key. Expected average runtime = O(1)
	 */
	public V get(K key) {

		int bucketatpos = hashFunction(key);

		Iterator<HashPair<K, V>> iterator = buckets.get(bucketatpos).iterator();
		while (iterator.hasNext()) {
			HashPair<K, V> cur = iterator.next();
			if (cur.getKey().equals(key))
				return (cur.getValue());
		}

		return null;
	}
	// ADD YOUR CODE ABOVE HERE

	/**
	 * Remove the HashPair correspoinding to key . Expected average runtime O(1)
	 */
	public V remove(K key) {
		// ADD YOUR CODE BELOW HERE
		

		int bucketpos = hashFunction(key);
		LinkedList<HashPair<K, V>> poslist = new LinkedList<HashPair<K, V>>();

		poslist = buckets.get(bucketpos);
		if (poslist.size() > 0) {
			Iterator<HashPair<K, V>> hashiter = poslist.iterator();
			while (hashiter.hasNext()) {
				HashPair<K, V> current = hashiter.next();
				if (current.getKey().hashCode() == key.hashCode()) {
					V result = current.getValue();
					poslist.remove(current);
					numEntries--;
					return result;
				}

			}
		}
		return null;

		// ADD YOUR CODE ABOVE HERE
	}

	// Method to double the size of the hashtable if load factor increases
	// beyond MAX_LOAD_FACTOR.
	// Made public for ease of testing.

	public void rehash() {
		// ADD YOUR CODE BELOW HERE

		int rehashCapacity = this.numBuckets * 2;
		// new rehash arraylist
		ArrayList<LinkedList<HashPair<K, V>>> rehash = new ArrayList<LinkedList<HashPair<K, V>>>();

		for (int k = 0; k < rehashCapacity; k++) {
			LinkedList<HashPair<K, V>> initList = new LinkedList<HashPair<K, V>>();
			rehash.add(k, initList);
		}

		// only look at positions that have more than 1 element

		for (int i = 0; i < numBuckets; i++) {
			if(buckets.get(i).size() > 0) {
				Iterator<HashPair<K, V>> hashiter = buckets.get(i).iterator();
				while (hashiter.hasNext()) {
					HashPair<K, V> current = hashiter.next();
					int positiontoaddto = Math.abs(current.getKey().hashCode()) % rehashCapacity;
					rehash.get(positiontoaddto).add(current);
				}
			}
		}
		buckets = rehash;
		this.numBuckets = buckets.size();
		// ADD YOUR CODE ABOVE HERE
	}

	/**
	 * Return a list of all the keys present in this hashtable.
	 */

	public ArrayList<K> keys() {
		// ADD YOUR CODE BELOW HERE
		ArrayList<K> keyslist = new ArrayList<K>();
		for (int i = 0; i < numBuckets; i++) {
			// cycle through the buckets

			LinkedList<HashPair<K, V>> listatpos = new LinkedList<HashPair<K, V>>();
			listatpos = buckets.get(i);

			if (listatpos.size() > 0) {
				Iterator<HashPair<K, V>> hashiter = listatpos.iterator();
				while (hashiter.hasNext()) {
					HashPair<K, V> current = hashiter.next();
					keyslist.add(current.getKey());
				}
			}

			// ADD YOUR CODE ABOVE HERE
		}
		return keyslist;
	}

	/**
	 * Returns an ArrayList of unique values present in this hashtable. Expected
	 * average runtime is O(n)
	 */
	public ArrayList<V> values() {
		// ADD CODE BELOW HERE
			MyHashTable<V, Integer> valuesinhash = new MyHashTable<V, Integer>(this.numEntries);

			for (int i = 0; i < this.numBuckets; i++) {
				if (buckets.get(i).size() > 0) {
					Iterator<HashPair<K, V>> iterator = buckets.get(i).iterator();
					while (iterator.hasNext()) {
						HashPair<K, V> cur = iterator.next();
						valuesinhash.put(cur.getValue(), 0);
					}
				}
			}

			ArrayList<V> ListedValues = new ArrayList<V>();
			ListedValues = valuesinhash.keys();

			return ListedValues;
		}
		
		
	@Override
	public MyHashIterator iterator() {
		return new MyHashIterator();
	}



	private class MyHashIterator implements Iterator<HashPair<K, V>> {

		private LinkedList<HashPair<K, V>> entries;

		private MyHashIterator() {

			// ADD YOUR CODE BELOW HERE

			entries = new LinkedList<HashPair<K, V>>();

			for (LinkedList<HashPair<K, V>> l : buckets) {

				for (HashPair<K, V> h : l) {

					entries.add(h);

				}

			}

			// ADD YOUR CODE ABOVE HERE

		}

		@Override

		public boolean hasNext() {

			// ADD YOUR CODE BELOW HERE

			boolean b = !entries.isEmpty();
			return b;

			// ADD YOUR CODE ABOVE HERE

		}

		@Override

		public HashPair<K, V> next() {

			// ADD YOUR CODE BELOW HERE
			HashPair<K, V> rh = entries.removeFirst();

			return rh;

			// ADD YOUR CODE ABOVE HERE

		}
	}
}
