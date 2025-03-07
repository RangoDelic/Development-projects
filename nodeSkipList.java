package psa.naloga3;

import java.util.Random;

public class NodeSkipList {
    int key;
    NodeSkipList[] next;

    NodeSkipList(int key, long height) {
        this.key = key;
        next = new NodeSkipList[(int) height];
    }

    public void setNext(NodeSkipList[] list) {
        this.next = list;
    }

    public int getKey() {return this.key;}


}
