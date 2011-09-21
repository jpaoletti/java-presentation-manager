package jpaoletti.jpm.parser;

import jpaoletti.jpm.converter.*;

/**
 *
 * @author jpaoletti
 */
public class ExternalConverterParser extends ParserSupport {

    @Override
    protected void init() {
        super.init();
        getXstream().alias("external-converters", ExternalConverters.class);
        getXstream().addImplicitCollection(ExternalConverters.class, "converters", ConverterWrapper.class);

        getXstream().alias("external-converter", ConverterWrapper.class);
        getXstream().useAttributeFor(ConverterWrapper.class, "id");
        getXstream().useAttributeFor(Converter.class, "operations");
        getXstream().useAttributeFor(Converter.class, "validate");

        getXstream().registerConverter(new EConverterConverter());
    }

    @Override
    protected Object newObject() {
        return new ExternalConverters();
    }
}
