package psa.naloga3;

/*
 * Razred mora imeplementirati podatkovno strukturo Razprsilne tabele.
 * Za funkcijo uporabite: h(x) = x * 53 mod size
 * V primeru kolizij uporabite LINEARNO NASLAVLJANJE.
 */
public class HashTable2 {

	public int[] data;
	int[] isDeleted;
	int[] isInserted;
	int size = 100;

	HashTable2() {
		data = new int[size];
		isDeleted = new int[size];
		isInserted = new int[size];
	}

	public int hash(int k) {
		return Math.floorMod((k * 53), size);
	}

	/*
	 * Metoda sprejme število in ga vstavi v tabelo. Metoda vrne true, ce je
	 * bilo ustavljanje uspešno in false sicer
	 */
	public boolean insert(int key) {
		if(this.search(key)) return false;
		int i = hash(key);
		int end = (i == 0) ? (size - 1) : i - 1;
		while(i != end && isInserted[i] == 1) {
			i = (i == size - 1) ? 0 : i + 1;
		}
		//end also checked
		if(isInserted[i] == 1) {
			return false; //table is full
		}

		data[i] = key;
		isInserted[i] = 1;
		isDeleted[i] = 0;
		return true;
	}

	/*
	 * Metoda sprejme število in ga poišče v tabeli. Metoda vrne true, ce je
	 * bilo ustavljanje uspešno in false sicer
	 */
	public boolean search(int key) {
		int i = hash(key);
		int end = (i == 0) ? size - 1 : i - 1;
		while(i != end && (isInserted[i] == 1 || isDeleted[i] == 1)) {
			if(data[i] == key) return isDeleted[i] != 1;
			i = (i == size - 1) ? 0 : i + 1;
		}
		//still have to check for (end)th index
		return data[end] == key && isDeleted[end] == 0;
	}

	/*
	 * Metoda sprejme število in ga izbriše iz tabele. Metoda vrne true, ce je
	 * bilo ustavljanje uspešno in false sicer
	 */
	public boolean delete(int key) {
		int i = hash(key);
		int end = i;
		do {
			if(data[i] == key) {
				if(isInserted[i] == 1 && isDeleted[i] == 0) {
					isInserted[i] = 0;
					isDeleted[i] = 1;
					return true;
				}
				else return false;
			}
			i = (i == size - 1) ? 0 : i + 1;
		} while(i != end && (isInserted[i] == 1 || isDeleted[i] == 1));

		return false;
	}
}
