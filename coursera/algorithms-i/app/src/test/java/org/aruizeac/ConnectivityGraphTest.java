package org.aruizeac;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ConnectivityGraphTest {
    @Parameter()
    public int[] inList;

    @Parameter(1)
    public int a;

    @Parameter(2)
    public int b;

    @Parameter(3)
    public int[] expList;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
            {
                new int[]{0, 1, 2, 3, 3, 5, 6, 7, 8, 9},
                3, 8,
                new int[]{0, 1, 2, 8, 8, 5, 6, 7, 8, 9}
            },
        };
        return Arrays.asList(data);
    }

    @Test
    public void testUnion() {
        ConnectivityGraph graph = new ConnectivityGraph(inList);
        graph.union(a, b);
        assertArrayEquals(String.format("graph should union a (%d) to b (%d)", a, b), expList, graph.getIds());
    }
}