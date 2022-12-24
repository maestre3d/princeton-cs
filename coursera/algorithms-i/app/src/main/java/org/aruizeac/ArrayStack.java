package org.aruizeac;

public class ArrayStack<T> implements Stack<T> {
    private Object[] buf;
    private int pivot;

    public ArrayStack() {
        buf = new Object[1];
    }

    public ArrayStack(int capacity) {
        buf = new Object[capacity];
    }

    private void resizeBuffer(int capacity) {
        // repeated doubling - Golang-like slice implementation
        Object[] tmpBuf = new Object[capacity];
        System.arraycopy(buf, 0, tmpBuf, 0, buf.length);
        buf = tmpBuf;
    }

    public void push(T val) {
        if (pivot >= buf.length) resizeBuffer(buf.length * 2);
        buf[pivot] = val;
        pivot++;
    }

    public T pop() {
        if (pivot == 0) return null;
        pivot--;
        T res = (T)buf[pivot];
        buf[pivot] = null;
//        if (pivot > 0 && pivot == buf.length/4) resizeBuffer(buf.length/2);
        return res;
    }

    public int count() {
        return pivot;
    }
}