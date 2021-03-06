package jpaoletti.jpm.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jpaoletti.jpm.core.PMCoreObject;

/**
 *
 * @author jpaoletti
 */
public class Validator extends PMCoreObject{

    private static final ResourceBundle bundle =
            ResourceBundle.getBundle(Validator.class.getName());
    private static final Map patterns = new HashMap();

    /**
     *
     * @param patternId
     * @param value
     * @return
     */
    public static boolean validate(String patternId, String value) {
        if (value == null) {
            return false;
        }
        Pattern p = (Pattern) patterns.get(patternId);
        if (p == null) {
            String patternString = bundle.getString(patternId);
            p = Pattern.compile(patternString);
            patterns.put(patternId, p);    // cache it
        }
        Matcher m = getPattern(patternId).matcher(value);
        return m.matches();
    }

    /**
     * Validate if the given value matches the pattern
     * @param pattern The pattern
     * @param value The value
     * @return true if value matches pattern
     */
    public static boolean matches(String pattern, String value) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(value);
        return m.matches();
    }

    /**
     * Validate if the given string is a name
     * @param s
     * @return
     */
    public static boolean isName(String s) {
        return validate("name", s);
    }

    /**
     * Validate if the given string is a query
     * @param s
     * @return
     */
    public static boolean isQuery(String s) {
        return validate("query", s);
    }

    /**
     * Validate if the given string is an alphamumeric
     * @param s
     * @return
     */
    public static boolean isAlpha(String s) {
        return validate("alpha", s);
    }

    /**
     * Validate if the given string is an alphanumeric and has a max length
     * @param s
     * @param len
     * @return
     */
    public static boolean isAlpha(String s, int len) {
        return validate("alpha", s) && s.length() <= len;
    }

    /**
     * Validate if the given string is a nickname
     * @param s
     * @return
     */
    public static boolean isNick(String s) {
        return validate("nick", s);
    }

    /**
     * Validate if the given string is a long
     * @param s
     * @return
     */
    public static boolean isLong(String s) {
        if (s == null) {
            return false;
        }
        try {
            Long.parseLong(s.trim());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Validate if the given string is a decimal
     * @param s
     * @return
     */
    public static boolean isDecimal(String s) {
        return validate("decimal", s);
    }

    /**
     * Validate if the given string is a state
     * @param s
     * @return
     */
    public static boolean isState(String s) {
        return validate("state", s);
    }

    /**
     * Validate if the given string is a boolean
     * @param s
     * @return
     */
    public static boolean isBoolean(String s) {
        return validate("boolean", s);
    }

    private static Pattern getPattern(String id) {
        Pattern p = (Pattern) patterns.get(id);
        if (p == null) {
            synchronized (patterns) {
                p = (Pattern) patterns.get(id);
                if (p == null) {
                    String s = bundle.getString(id);
                    p = Pattern.compile(s);
                    patterns.put(id, p);    // cache it
                }
            }
        }
        return p;
    }
}
