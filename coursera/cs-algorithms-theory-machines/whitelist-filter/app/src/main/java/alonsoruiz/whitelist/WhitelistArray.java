package alonsoruiz.whitelist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;

public class WhitelistArray implements Whitelist {
    private final ArrayList<String> list = new ArrayList<>();
    private final WhitelistArrayLookupType lookupType;

    public WhitelistArray(WhitelistArrayLookupType lookupType, Reader in) throws IOException {
        this.lookupType = lookupType;
        BufferedReader buf = new BufferedReader(in);
        buf.lines().forEach(list::add);
        if (lookupType == WhitelistArrayLookupType.BINARY_SEARCH || lookupType == WhitelistArrayLookupType.BINARY_SEARCH_RECURSIVE) {
            list.sort(Comparator.naturalOrder());
        }
        buf.close();
    }

    @Override
    public String[] getList() {
        return list.toArray(new String[]{});
    }

    @Override
    public Boolean isAllowed(String s) {
        return switch (lookupType) {
            case SEQUENTIAL -> sequentialLookup(s);
            case BINARY_SEARCH -> binaryLookup(s);
            case BINARY_SEARCH_RECURSIVE -> binaryLookupRecursive(s, 0, list.size());
        };
    }

    /**
     * {@code sequentialLookup} is the item lookup implementation on an array with O(n) time complexity (i.e. linear time).
     */
    private Boolean sequentialLookup(String s) {
        // We implement manually list.contains() to observe the full lookup process.
        for (int i = 0; i < this.list.size(); i++) {
            String val = this.list.get(i);
            if (val.equals(s)) return true;
        }

        return false;
    }

    /**
     * {@code binaryLookup} is the item lookup implementation on an array with O(log n) time complexity (i.e. logarithmic time).
     */
    private Boolean binaryLookup(String s) {
        // We implement <<Arrays.binarySearch(getList(), s) >= 0>> manually to observe the full lookup process.
        int lowPtr = 0, highPtr = list.size();

        while (lowPtr < highPtr) {
            int midPtr = lowPtr + (highPtr - lowPtr) / 2;
            int cmp = list.get(midPtr).compareTo(s);

            if (cmp < 0)
                lowPtr = midPtr + 1;
            else if (cmp > 0)
                highPtr = midPtr;
            else
                return true;
        }

        return false;
    }

    /**
     * {@code binaryLookupRecursive} is the item lookup implementation on an array with O(log n) time complexity (i.e. logarithmic time) using recursive routine calls.
     */
    private Boolean binaryLookupRecursive(String s, int low, int high) {
        if (high <= low) return false;

        int mid = low + (high - low) / 2;
        int cmp = list.get(mid).compareTo(s);

        if (cmp < 0)
            return binaryLookupRecursive(s, mid + 1, high);
        else if (cmp > 0)
            return binaryLookupRecursive(s, low, mid);

        return true;
    }
}