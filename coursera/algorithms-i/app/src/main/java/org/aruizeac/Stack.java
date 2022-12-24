package org.aruizeac;

import java.util.Iterator;

public interface Stack<T> extends Iterator<T> {
    void push(T val);

    T pop();

    int count();

    T peek();
}
