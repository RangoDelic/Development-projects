package psa.naloga3;

import java.util.Random;

public class SkipList {

	private NodeSkipList sentinel;
	private long maxNodes;
	private int height;

	/*
	 * The creator takes as a parameter the number of elements he is able to process
	 */
	public SkipList(long maxNodes) {
		this.maxNodes = maxNodes;
		height = (int) Math.ceil(Math.log((int) maxNodes) / Math.log(2));
		System.out.println(height);
		sentinel = new NodeSkipList(Integer.MIN_VALUE, height);

	}

	//gives one indexed height
	public long coin() {
		boolean tail = false;
		long height = 0; //one indexed
		while(!tail) {
			Random random = new Random();
			int chance = random.nextInt(2);
			//System.out.println(chance);
			tail = chance == 1;
			height++;
		}
		//System.out.println("coin height: " + height);
		return height % this.height;
	}

	/*
	 * The method takes a number and inserts it into the skip list. This element is
	 * exists in the data structure, returns false. The method returns true if it was
	 * element successfully inserted and false otherwise.
	 */
	public boolean insert(int searchKey) {
		if(maxNodes == 0) return false;

		//System.out.println("\n\n");
		//System.out.println("=========== inserting " + searchKey);
		//System.out.println("current skiplist");
		//printSkipList();
		NodeSkipList pointer = sentinel;
		int level = height - 1;
		//System.out.println("level is: " + level);
		NodeSkipList[] predecessors = new NodeSkipList[height];
		while (level >= 0) {
			//System.out.println("while conditions: " + pointer.next[level] + " and " + (pointer.next[level].key < searchKey));
			while (pointer.next[level] != null && pointer.next[level].key < searchKey)
				pointer = pointer.next[level];
			if (pointer.next[level] != null && pointer.next[level].key == searchKey) return false;
			//System.out.println("about to store predecessor: " + pointer.key);
			predecessors[level--] = pointer;
		}

		NodeSkipList node = new NodeSkipList(searchKey, coin());
		//System.out.println("got height: " + node.next.length);
		//updting pointers
		//System.out.println("updating pointers in insert func");
		for (int i = 0; i < node.next.length; i++) {
			//System.out.println(predecessors[i].next[i]);
			node.next[i] = predecessors[i].next[i];
			predecessors[i].next[i] = node;
		}
		//System.out.println("after insert:");
		//printSkipList();
		maxNodes--;
		return true;
	}

	/*
	 * The method takes a number and finds an item in the skip list. Method
	 * returns true if the item was successfully found in the data structure, and
	 * false otherwise
	 */
	public boolean search(int searchKey) {
		System.out.println("searching " + searchKey + " in following list:");
		printSkipList();
		if (sentinel == null) return false;

		else {
			NodeSkipList pointer = sentinel;
			int level = height - 1;
			while(level >= 0) {
				//System.out.println("while cond: " + pointer.next[level].toString() + " " + (pointer.next[level] == null ? "null" : pointer.next[level].key));
				while(pointer.next[level] != null && pointer.next[level].key < searchKey) pointer = pointer.next[level];
				level--;
				//System.out.println("down to level " + level + " and elem: " + pointer.key);
			}
			//System.out.println("predecessor key: " + pointer.key);
			return pointer.next[0] != null && pointer.next[0].key == searchKey;
		}
	}

	/*
	 * Metoda sprejme stevilo in izbrise element iz preskocnega seznama. Metoda
	 * vrne true, ce je bil element uspesno izbrisan iz podatkovne strukture, in
	 * false sicer
	 */
	public boolean delete(int key) {
		boolean removed = false;
		NodeSkipList pointer = sentinel;
		NodeSkipList[] predecessors = new NodeSkipList[height];
		int level = height - 1;

		while(level >= 0) {
			while(pointer.next[level] != null && pointer.next[level].key < key) pointer = pointer.next[level];

			if(pointer.next[level] != null && pointer.next[level].key == key) {
				pointer.next[level] = pointer.next[level].next[level];
				removed = true;
			}
			level--;
		}
		if (removed) maxNodes++;
		return removed;
	}

	public void printSkipList() {
		for (int i = height - 1; i >= 0; i--) {
			if(sentinel.next[i] == null) {
				System.out.println(i + " -> Null");
			}
			else {
				//System.out.println("len is " + sentinel.next.length);
				NodeSkipList list = sentinel.next[i];
				System.out.print(i + " -> ");
				while (list != null) {
					System.out.print(list.key + " -- ");
					list = list.next[i];
				}
				System.out.print(" Null ");
				System.out.println();
			}
		}
	}
}
