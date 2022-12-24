package org.aruizeac;

import java.util.Iterator;

public interface Queue<T> extends Iterator<T> {
    void enqueue(T val);
    T dequeue();
    int count();
    T peek();
}