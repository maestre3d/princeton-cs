package alonsoruiz.whitelist;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Comparator;

public class App {
    private static final String ALLOWED_ADDRESSES_RAW = "foo@example.com\nbar@example.com\nbaz@example.com";

    public static void main(String[] args) throws IOException {
        String[] sortedList = new String[]{"cc", "cb", "ca", "bb", "ba", "ab", "aa"};
        Sort.insertionSort(sortedList, Comparator.naturalOrder());
        Arrays.stream(sortedList).forEach(System.out::println);

        final String IN_ADDRESS = "bar@example.com";

        Whitelist filter = new WhitelistHashSet(new StringReader(ALLOWED_ADDRESSES_RAW));
        Arrays.stream(filter.getList()).forEach(System.out::println);
        Boolean isAllowed = filter.isAllowed(IN_ADDRESS);
        System.out.println(isAllowed);
    }
}
