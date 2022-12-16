package alonsoruiz.whitelist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SortTest {
    @Parameter()
    public String[] inList;

    @Parameter(1)
    public String[] expList;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
            {new String[]{"cc", "cb", "ca", "bc", "bb", "ba", "ab", "aa"},
                new String[]{"aa", "ab", "ba", "bb", "bc", "ca", "cb", "cc"}},
        };
        return Arrays.asList(data);
    }

    @Test
    public void tryInsertionSort() {
        Sort.insertionSort(inList, Comparator.naturalOrder());
        assertArrayEquals(expList, inList);
    }

    @Test
    public void tryMergeSort() {
        Sort.mergeSort(inList, Comparator.naturalOrder());
        assertArrayEquals(expList, inList);
    }
}