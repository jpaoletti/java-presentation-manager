package jpaoletti.jpm.parser;

import java.util.List;
import jpaoletti.jpm.converter.*;
import jpaoletti.jpm.core.PresentationManager;

/**
 * Parser for class-converter xml file
 *
 * @author jpaoletti
 */
public class ClassConverterParser extends ParserSupport {

    public ClassConverterParser(PresentationManager pm) {
        super(pm);
    }

    @Override
    protected void init() {
        super.init();
        getXstream().alias("class-converters", ClassConverterList.class);
        getXstream().addImplicitCollection(ClassConverterList.class, "classConverters", ClassConverter.class);

        getXstream().alias("class-converter", ClassConverter.class);

        getXstream().aliasAttribute("class-name", "className");
        getXstream().useAttributeFor(ClassConverter.class, "econverter");
        getXstream().useAttributeFor(ClassConverter.class, "className");
        getXstream().useAttributeFor(ClassConverter.class, "operations");
    }

    @Override
    protected Object newObject() {
        return new ClassConverterList(getPm());
    }

    @Override
    protected void afterParse(Object result) {
        final ClassConverterList list = (ClassConverterList) result;
        list.spreadPm();
    }
}
