package alonsoruiz.whitelist;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

public class App {
    private static final String ALLOWED_ADDRESSES_RAW = "foo@example.com\nbar@example.com\nbaz@example.com";

    public static void main(String[] args) throws IOException {
        final String IN_ADDRESS = "barro@example.com";

        Whitelist filter = new WhitelistHashSet(new StringReader(ALLOWED_ADDRESSES_RAW));
        Arrays.stream(filter.getList()).forEach(System.out::println);
        Boolean isAllowed = filter.isAllowed(IN_ADDRESS);
        System.out.println(isAllowed);
    }
}
