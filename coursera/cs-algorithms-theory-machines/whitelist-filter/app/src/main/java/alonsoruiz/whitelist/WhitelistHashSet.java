package alonsoruiz.whitelist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;

/**
 * {@code WhitelistHashSet} is a {@link Whitelist} implementation backed by a {@link HashSet}.
 * This leads to high-performance lookup with time complexity of O(1) (i.e. constant time).
 */
public class WhitelistHashSet implements Whitelist {
    private final HashSet<String> allowList = new HashSet<>();

    /**
     * {@code WhitelistHashSet} is a {@link Whitelist} implementation backed by a {@link HashSet}.
     * This leads to high-performance lookup with time complexity of O(1) (i.e. constant time).
     *
     * @param in implementation of {@link Reader}, useful to abstract {@link java.io.FileReader}, {@link java.io.StringReader} and more.
     */
    public WhitelistHashSet(Reader in) throws IOException {
        BufferedReader buf = new BufferedReader(in);
        buf.lines().forEach(allowList::add);
        buf.close();
    }

    /**
     * {@code WhitelistHashSet} is a {@link Whitelist} implementation backed by a {@link HashSet}.
     * This leads to high-performance lookup with time complexity of O(1) (i.e. constant time).
     *
     * @param in an agnostic {@link List} of strings.
     */
    public WhitelistHashSet(List<String> in) {
        allowList.addAll(in);
    }

    @Override
    public String[] getList() {
        return allowList.toArray(new String[]{});
    }

    @Override
    public Boolean isAllowed(String address) {
        return allowList.contains(address);
    }
}