package psa.naloga5;

import java.util.Arrays;

public class Prim {
	int[][] data;
	int n;

	public Prim(int n) {
		data = new int[n][n];
		this.n = n;
	}

	public Prim(int[][] d) {
		this.n = d.length;
		this.data = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.data[i][j] = d[i][j];
			}
		}
	}

	public void addEdge(int i, int j, int d) {
		data[i][j] = d;
		data[j][i] = d;
	}

	public int MSTcost() {
		int[] parent = new int[n];
		boolean[] visited = new boolean[n];
		int[] key = new int[n];

		Arrays.fill(key, Integer.MAX_VALUE);
		key[0] = 0; // Start with the first vertex as the root

		for (int count = 0; count < n - 1; count++) {
			int u = minKey(key, visited);
			visited[u] = true;

			for (int v = 0; v < n; v++) {
				if (data[u][v] != 0 && !visited[v] && data[u][v] < key[v]) {
					parent[v] = u;
					key[v] = data[u][v];
				}
			}
		}

		return Arrays.stream(key).sum();
	}

	private int minKey(int[] key, boolean[] visited) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;

		for (int v = 0; v < n; v++) {
			if (!visited[v] && key[v] < min) {
				min = key[v];
				minIndex = v;
			}
		}

		return minIndex;
	}

	public int[] prim(int s) {
		int[] parent = new int[n];
		boolean[] visited = new boolean[n];
		int[] key = new int[n];

		Arrays.fill(key, Integer.MAX_VALUE);
		key[s] = 0; // Start with the specified vertex as the root
		parent[s] = 0;

		for (int count = 0; count < n - 1; count++) {
			int u = minKey(key, visited);
			visited[u] = true;

			for (int v = 0; v < n; v++) {
				if (data[u][v] != 0 && !visited[v] && data[u][v] < key[v]) {
					parent[v] = u;
					key[v] = data[u][v];
				}
			}
		}

		return parent;
	}

	public static void main(String[] args) {
		int[][] graph = {
				{0, 2, 0, 6, 0},
				{2, 0, 3, 8, 5},
				{0, 3, 0, 0, 7},
				{6, 8, 0, 0, 9},
				{0, 5, 7, 9, 0}
		};

		Prim prim = new Prim(graph.length);
		prim.data = graph;

		System.out.println("Minimum Spanning Tree cost: " + prim.MSTcost());

		int[] mst = prim.prim(0);
		System.out.println("Minimum Spanning Tree Parent Array: " + Arrays.toString(mst));
	}
}

