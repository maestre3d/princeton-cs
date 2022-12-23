package org.aruizeac;

public interface Queue<T> {
    void enqueue(T val);
    T dequeue();
    int count();
}