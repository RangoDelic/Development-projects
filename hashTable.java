package psa.naloga3;

/* The class must implement the data structure of the HashTable.
		* For the function use: h (x) = x * 701 mod 2000
		* In case of collisions, use CHAINING and use data as a Dictionary
		* Scatter table structure to be implemented (HashTable2 class).
		* Be careful, because the key can also be a negative number
		*/
public class HashTable {
	public HashTable2[] data;

	HashTable() {
		data = new HashTable2[2000];
	}

	public int hash(int k) {
		return Math.floorMod(k * 701, 2000);
	}

	/*
	 * Metoda sprejme število in ga vstavi v tabelo. Metoda vrne true, ce je
	 * bilo ustavljanje uspešno in false sicer
	 */
	public boolean insert(int key) {
		int index = hash(key);
		if(data[index] == null) data[index] = new HashTable2();
		return data[index].insert(key);
	}

	/*
	 * Metoda sprejme število in ga poišče v tabeli. Metoda vrne true, ce je
	 * bilo ustavljanje uspešno in false sicer
	 */
	public boolean search(int key) {
		return data[hash(key)] != null && data[hash(key)].search(key);
	}

	/*
	 * Metoda sprejme število in ga izbriše iz tabele. Metoda vrne true, ce je
	 * bilo ustavljanje uspešno in false sicer
	 */
	public boolean delete(int key) {
		return data[hash(key)] != null && data[hash(key)].delete(key);
	}

	public void printing() {
		for(int i = -10000; i < 10; i++) {
			if (hash(i) >= 2000) System.out.println(i + " => " + hash(i));
		}
		System.out.println("done");
	}
}
