package org.aruizeac;

public class RingBuffer<T> {
    private final T[] buf;
    private int headPivot, tailPivot, count;

    public RingBuffer(int capacity) {
        buf = (T[])new Object[capacity];
    }

    public void write(T val) {
        if (headPivot == buf.length) {
            headPivot = 0;
            count--;
        }
        buf[headPivot++] = val;
        count++;
    }

    public T read() {
        if (count == 0) return null; 
        if (tailPivot == buf.length) tailPivot = 0;
        T res = buf[tailPivot];
        buf[tailPivot] = null;
        tailPivot++;
        count--;
        return res;
    }

    public int count() {
        return count;
    }
}