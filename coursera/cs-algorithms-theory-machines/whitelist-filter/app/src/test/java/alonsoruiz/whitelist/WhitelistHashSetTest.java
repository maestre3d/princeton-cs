package alonsoruiz.whitelist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class WhitelistHashSetTest {
    private final String ALLOW_LIST_RAW = "foo\nbar\rbaz";

    @Parameter()
    public String[] expList;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
            {new String[]{"bar", "baz", "foo"}},
        };
        return Arrays.asList(data);
    }

    @Test
    public void loadsDataFromReader() throws IOException {
        WhitelistHashSet list = new WhitelistHashSet(new StringReader(ALLOW_LIST_RAW));
        // We order data as hashmap does not ensures ordering.
        String[] out = list.getList();
        Arrays.sort(out);
        assertArrayEquals(this.expList, out);
    }

    @Test
    public void loadsDataFromStringList() {
        WhitelistHashSet list = new WhitelistHashSet(Arrays.stream(new String[]{"foo", "bar", "baz"}).toList());
        // We order data as hashmap does not ensures ordering.
        String[] out = list.getList();
        Arrays.sort(out);
        assertArrayEquals(this.expList, out);
    }

    @Test
    public void allowsEntry() throws IOException {
        Whitelist list = new WhitelistHashSet(new StringReader(ALLOW_LIST_RAW));
        assertNotNull("whitelist should have a list", list.getList());
        assertTrue("whitelist should allow foo", list.isAllowed("foo"));
        assertTrue("whitelist should allow bar", list.isAllowed("bar"));
        assertTrue("whitelist should allow baz", list.isAllowed("baz"));
        assertFalse("whitelist should not allow foobar", list.isAllowed("foobar"));
    }
}