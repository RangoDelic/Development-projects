package psa.naloga2;


import java.util.Vector;

public class UnionFind {
	public int[] id;

	public UnionFind(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++) id[i] = i;
	}

	/*
	 * Metoda sprejme index in vrne predstavnika mnozice, katere clan je index.
	 */
	public int find(int i) {
		int parent = i;
		Vector<Integer> trace = new Vector<Integer>();
		while(parent != id[parent]) {
			trace.add(parent);
			parent = id[parent];
		}
		for (int e: trace
			 ) {
			id[e] = parent;
		}
		return parent;
	}

	/*
	 * Metoda sprejme da indexa in naredi unijo
	 */
	public void unite(int p, int q) {
		int parent = find(q);
		id[parent] = find(p);
	}
	
	/*
	 * Metoda vrne true, ce sta p in q v isti mnozici
	 */
	public boolean isInSameSet(int p, int q) {
		return find(p) == find(q);
	}
}
