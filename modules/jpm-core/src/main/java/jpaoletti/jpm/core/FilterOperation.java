package jpaoletti.jpm.core;

/**
 *
 * @author jpaoletti
 */
public enum FilterOperation {
     EQ //Equal
    ,NE //Not Equal
    ,LIKE //Like, only work for strings
    ,GT //Greater Than
    ,GE //Greater Equal
    ,LT //Less Than
    ,LE //Less Equal
    ,BETWEEN //Between
}
