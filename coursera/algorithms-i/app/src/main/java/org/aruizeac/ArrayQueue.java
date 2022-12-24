package org.aruizeac;

public class ArrayQueue<T> implements Queue<T> {
    private Object[] buffer;
    private int headPivot, tailPivot;
    private final boolean isDynamic;

    public ArrayQueue() {
        buffer = new Object[1];
        isDynamic = true;
    }

    public ArrayQueue(int capacity) {
        buffer = new Object[capacity];
        isDynamic = false;
    }

    private void resizeBuffer(int capacity) {
        Object[] tmpBuf = new Object[capacity];
        System.arraycopy(buffer, 0, tmpBuf, 0, buffer.length);
        buffer = tmpBuf;
    }

    public void enqueue(T val) {
        if (tailPivot == buffer.length && isDynamic) resizeBuffer(buffer.length * 2);
        else if (tailPivot == buffer.length) return;
        buffer[tailPivot] = val;
        tailPivot++;
    }

    public T dequeue() {
        if (tailPivot == 0) return null;
        T res = (T)buffer[headPivot];
        buffer[headPivot] = null;
        headPivot++;
        if (headPivot == tailPivot) headPivot = tailPivot = 0;
//        if (headPivot > 0 && headPivot == buffer.length/4) resizeBuffer(buffer.length / 2);
        return res;
    }

    public int count() {
        return tailPivot - headPivot;
    }
}