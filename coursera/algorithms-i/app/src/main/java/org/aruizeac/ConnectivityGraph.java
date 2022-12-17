package org.aruizeac;

public class ConnectivityGraph {
    private final int[] ids;
    private int count;

    public ConnectivityGraph(int capacity) {
        ids = new int[capacity];
    }

    public ConnectivityGraph(int[] src) {
        this.ids = src;
    }

    public int[] getIds() {
        return ids;
    }

    public void append(int... ids) {
        for (var i : ids) {
            this.ids[count] = i;
        }
        count += ids.length;
    }

    public void union(int a, int b) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] != a) {
                continue;
            }
            ids[i] = b;
        }
    }

    public void quickFindUF() {
    }
}