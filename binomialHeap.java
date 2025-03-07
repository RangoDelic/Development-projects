package psa.naloga4;

public class BinomialHeap {
	BinomialNode[] data;

	BinomialHeap(){
		data = new BinomialNode[1];
	}

	public boolean insert(int key) {
		if(data[0] == null) {
			data[0] = new BinomialNode(key);
		}
		else {
			BinomialNode newNode = new BinomialNode(key);
			int index = 0;
			while(data[index] != null) {
				if (index == data.length - 1) resizeArray();
				newNode = merge(newNode, data[index]);
				data[index++] = null;
			}
			data[index] = newNode;
		}
		return true;
	}

	public int getMin() {
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < data.length; i++) {
			if (data[i] != null && data[i].getKey() < min) min = data[i].getKey();
		}
		return min;
	}

	public boolean delMin() {
		int min = this.getMin();
		if (min == Integer.MAX_VALUE) return false;
		int i = 0;

		while(true){
			if (data[i] != null && data[i].getKey() == min) {
				if(i == 0) data[i] = null;

				else{
					BinomialNode[] children = new BinomialNode[i];
					for(int k = 0; k < i; k++) {
						children[k] = data[i].getChilds().elementAt(k);
					}
					data[i] = null;

					for(int p = children.length - 1; p >= 0; p--) {
						int q = p;
						BinomialNode node = children[p];
						while(data[q] != null) {
							node = merge(node, data[q]);
							data[q++] = null;
						}
						data[q] = node;
					}
				}
				return true;
			}
			else i++;
		}
	}

	private void resizeArray() {
		BinomialNode[] newData = new BinomialNode[data.length * 2];
		System.arraycopy(data, 0, newData, 0, data.length);
		data = newData;
	}


	private BinomialNode merge(BinomialNode t1, BinomialNode t2) {
		if (t1 == null) return t2;
		else if (t2 == null) return t1;
		else {
			if (t1.getKey() < t2.getKey()) {
				t1.addChild(t2);
				return t1;
			} else {
				t2.addChild(t1);
				return t2;
			}
		}
	}
}


