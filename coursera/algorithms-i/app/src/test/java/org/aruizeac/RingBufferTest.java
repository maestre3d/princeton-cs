package org.aruizeac;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RingBufferTest {
    @Test
    public void testBuffer() {
        RingBuffer<String> buf = new RingBuffer<>(3);
        assertNull(buf.read());

        buf.write("foo");
        assertEquals("foo", buf.read());
        buf.write("bar");
        buf.write("baz");
        buf.write("foobar");
        buf.write("foo");
        assertEquals(3, buf.count());
        assertEquals("foo", buf.read());
        assertEquals("baz", buf.read());
        assertEquals("foobar", buf.read());
    }
}