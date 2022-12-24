package org.aruizeac;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ArrayStackQueueTest {

    @Test
    public void testStackFixed() {
        Stack<String> stack = new ArrayStack<>(3);
        stack.push("foo");
        stack.push("bar");
        stack.push("baz");
        assertEquals(3, stack.count());
        assertEquals("baz", stack.peek());
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
    public void testStack() {
        Stack<String> stack = new ArrayStack<>();
        stack.push("foo");
        stack.push("bar");
        stack.push("baz");
        assertEquals(3, stack.count());
        assertEquals("baz", stack.peek());
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
    public void testQueueFixed() {
        Queue<String> queue = new ArrayQueue<>(3);
        queue.enqueue("foo");
        assertEquals(1, queue.count());
        assertEquals("foo", queue.dequeue());
        assertEquals(0, queue.count());
        queue.enqueue("foo");
        queue.enqueue("bar");
        queue.enqueue("baz");
        assertEquals(3, queue.count());
        assertEquals("foo", queue.peek());
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

    @Test
    public void testQueue() {
        Queue<String> queue = new ArrayQueue<>();
        queue.enqueue("foo");
        assertEquals(1, queue.count());
        assertEquals("foo", queue.dequeue());
        assertEquals(0, queue.count());
        queue.enqueue("foo");
        queue.enqueue("bar");
        queue.enqueue("baz");
        assertEquals(3, queue.count());
        assertEquals("foo", queue.peek());
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

    @Test
    public void testStackIterator() {
        Stack<String> stack = new ArrayStack<>();
        stack.push("foo");
        stack.push("bar");
        stack.push("baz");

        Map<Integer, String> exp = new HashMap<>() {{
            put(0, "baz");
            put(1, "bar");
            put(2, "foo");
        }};
        int count = 0;
        while (stack.hasNext()) {
            assertEquals(exp.get(count), stack.next());
            count++;
        }
    }

    @Test
    public void testQueueIterator() {
        Queue<String> queue = new ArrayQueue<>();
        queue.enqueue("foo");
        queue.enqueue("bar");
        queue.enqueue("baz");

        Map<Integer, String> exp = new HashMap<>() {{
            put(0, "foo");
            put(1, "bar");
            put(2, "baz");
        }};
        int count = 0;
        while (queue.hasNext()) {
            assertEquals(exp.get(count), queue.next());
            count++;
        }
    }
}