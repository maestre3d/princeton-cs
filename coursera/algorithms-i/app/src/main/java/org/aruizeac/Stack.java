package org.aruizeac;

public interface Stack<T> {
    void push(T val);

    T pop();

    int count();
}
