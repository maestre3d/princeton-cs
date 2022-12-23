package org.aruizeac;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LinkedStackTest {
    @Test
    public void testLinkedList() {
        Stack<String> stack = new LinkedStack<>();
        stack.push("foo");
        stack.push("bar");
        stack.push("baz");
        assertEquals(3, stack.count());
        assertEquals("baz", stack.pop());
        assertEquals(2, stack.count());
        assertEquals("bar", stack.pop());
        assertEquals(1, stack.count());
        assertEquals("foo", stack.pop());
        assertEquals(0, stack.count());
        assertNull(null, stack.pop());
        assertEquals(0, stack.count());
    }

    @Test
    public void testLinkedQueue() {
        Queue<String> queue = new LinkedQueue<>();
        queue.enqueue("foo");
        queue.enqueue("bar");
        queue.enqueue("baz");
        assertEquals(3, queue.count());
        assertEquals("foo", queue.dequeue());
        assertEquals(2, queue.count());
        assertEquals("bar", queue.dequeue());
        assertEquals(1, queue.count());
        assertEquals("baz", queue.dequeue());
        assertEquals(0, queue.count());
        assertNull(null, queue.dequeue());
        assertEquals(0, queue.count());
    }
}