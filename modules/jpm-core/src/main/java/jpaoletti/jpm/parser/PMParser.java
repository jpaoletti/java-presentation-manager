package jpaoletti.jpm.parser;

/**
 *
 * @author jpaoletti
 */
public interface PMParser {
    public Object parseFile(String filename)throws Exception;
    public void saveToFile(Object object, String filename)throws Exception;
}
