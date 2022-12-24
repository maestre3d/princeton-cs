package org.aruizeac;

public class ArrayQueue<T> implements Queue<T> {
    private T[] buffer;
    private int headPivot, tailPivot;
    private final boolean isDynamic;

    public ArrayQueue() {
        buffer = (T[]) new Object[1];
        isDynamic = true;
    }

    public ArrayQueue(int capacity) {
        buffer = (T[])new Object[capacity];
        isDynamic = false;
    }

    private void resizeBuffer(int capacity) {
        T[] tmpBuf = (T[])new Object[capacity];
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
        T res = buffer[headPivot];
        buffer[headPivot] = null;
        headPivot++;
        if (headPivot == tailPivot) headPivot = tailPivot = 0;
        //        if (headPivot > 0 && headPivot == buffer.length/4) resizeBuffer(buffer.length / 2);
        return res;
    }

    public T peek() {
        if (tailPivot == 0) return null;
        return buffer[headPivot];
    }

    public int count() {
        return tailPivot - headPivot;
    }

    public boolean hasNext() {
        return count() > 0;
    }

    public T next() {
        return dequeue();
    }
}