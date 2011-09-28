package jpaoletti.jpm.util;

/**
 * Utility helpers
 * 
 * @author jpaoletti
 */
public class Utils {

    /**
     * Pad to the left
     * @param s  string
     * @param len desired len
     * @param c padding char
     * @return padded string
     */
    public static String padleft(String s, int len, char c) {
        s = s.trim();
        if (s.length() > len) {
            return s;
        }
        final StringBuilder sb = new StringBuilder(len);
        int fill = len - s.length();
        while (fill-- > 0) {
            sb.append(c);
        }
        sb.append(s);
        return sb.toString();
    }

    /**
     * Pad to the right
     * @param s string
     * @param len desired len
     * @param c padding char
     * @return padded string
     */
    public static String padright(String s, int len, char c) {
        s = s.trim();
        if (s.length() > len) {
            return s;
        }
        final StringBuilder sb = new StringBuilder(len);
        int fill = len - s.length();
        sb.append(s);
        while (fill-- > 0) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static String hexdump(byte[] p) {
        return p.toString();
    }

    public static String zeropad(String s, int i) {
        return padright(s, i, '0');
    }
}
