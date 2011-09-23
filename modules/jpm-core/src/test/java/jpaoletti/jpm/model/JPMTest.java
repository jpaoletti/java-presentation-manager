package jpaoletti.jpm.model;

import java.util.Date;

/**
 * Test model class
 *
 * @author jpaoletti
 * @since 21/09/2011
 * @version 1.0
 *
 */
public class JPMTest {

    private Long id;
    private String string;
    private Integer integer;
    private Date date;
    private Boolean bool;

    public JPMTest(Long id, String string, Integer integer, Date date, Boolean bool) {
        this.id = id;
        this.string = string;
        this.integer = integer;
        this.date = date;
        this.bool = bool;
    }

    public JPMTest() {
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
