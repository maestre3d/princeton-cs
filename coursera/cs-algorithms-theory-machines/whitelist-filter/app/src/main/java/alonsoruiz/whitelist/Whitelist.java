package alonsoruiz.whitelist;

/**
 * A {@code Whitelist} is a list containing several number of items inside which will be later allowed is {@code isAllowed} was called.
 * Thus, any value not containing inside the list is not allowed.
 */
public interface Whitelist {
    /**
     * Builds and retrieves a String vector from the whitelist.
     */
    String[] getList();

    /**
     * Checks if {@code s} is on the whitelist.
     *
     * @param s String to be checked.
     */
    Boolean isAllowed(String s);
}