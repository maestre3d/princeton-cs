package alonsoruiz.whitelist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class WhitelistArrayTest {
    private final String ALLOW_LIST_RAW = "foo\nbar\rbaz";

    @Parameter()
    public String[] expList;

    @Parameter(1)
    public String[] expOrderedList;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
            {new String[]{"foo", "bar", "baz"}, new String[]{"bar", "baz", "foo"}},
        };
        return Arrays.asList(data);
    }

    @Test
    public void loadsDataFromReader() throws IOException {
        // We create separate StringReader(s) as WhitelistArray() inner buffer closes the reader. Sharing the reader
        // will lead to operation executions with a closed Reader, thus IO exception(s) will surface.
        Whitelist list = new WhitelistArray(WhitelistArrayLookupType.SEQUENTIAL, new StringReader(ALLOW_LIST_RAW));
        assertArrayEquals(expList, list.getList());

        list = new WhitelistArray(WhitelistArrayLookupType.BINARY_SEARCH, new StringReader(ALLOW_LIST_RAW));
        assertArrayEquals(expOrderedList, list.getList());
    }

    @Test
    public void allowsEntry() throws IOException {
        Whitelist list = new WhitelistArray(WhitelistArrayLookupType.SEQUENTIAL, new StringReader(ALLOW_LIST_RAW));
        assertNotNull("whitelist SEQUENTIAL should not be null", list.getList());
        assertTrue("whitelist SEQUENTIAL should allow foo", list.isAllowed("foo"));
        assertTrue("whitelist SEQUENTIAL should allow bar", list.isAllowed("bar"));
        assertTrue("whitelist SEQUENTIAL should allow baz", list.isAllowed("baz"));
        assertFalse("whitelist SEQUENTIAL should not allow foobar", list.isAllowed("foobar"));

        list = new WhitelistArray(WhitelistArrayLookupType.BINARY_SEARCH, new StringReader(ALLOW_LIST_RAW));
        assertNotNull("whitelist BINARY_SEARCH should not be null", list.getList());
        assertTrue("whitelist BINARY_SEARCH should allow foo", list.isAllowed("foo"));
        assertTrue("whitelist BINARY_SEARCH should allow bar", list.isAllowed("bar"));
        assertTrue("whitelist BINARY_SEARCH should allow baz", list.isAllowed("baz"));
        assertFalse("whitelist BINARY_SEARCH should not allow foobar", list.isAllowed("foobar"));

        list = new WhitelistArray(WhitelistArrayLookupType.BINARY_SEARCH_RECURSIVE, new StringReader(ALLOW_LIST_RAW));
        assertNotNull("whitelist BINARY_SEARCH_RECURSIVE should not be null", list.getList());
        assertTrue("whitelist BINARY_SEARCH_RECURSIVE should allow foo", list.isAllowed("foo"));
        assertTrue("whitelist BINARY_SEARCH_RECURSIVE should allow bar", list.isAllowed("bar"));
        assertTrue("whitelist BINARY_SEARCH_RECURSIVE should allow baz", list.isAllowed("baz"));
        assertFalse("whitelist BINARY_SEARCH_RECURSIVE should not allow foobar", list.isAllowed("foobar"));
    }
}