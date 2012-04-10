package jpaoletti.jpm.parser;

import jpaoletti.jpm.converter.*;
import jpaoletti.jpm.core.PresentationManager;

/**
 *
 * @author jpaoletti
 */
public class ExternalConverterParser extends ParserSupport {

    public ExternalConverterParser(PresentationManager pm) {
        super(pm);
    }

    @Override
    protected void init() {
        super.init();
        getXstream().alias("external-converters", ExternalConverters.class);
        getXstream().addImplicitCollection(ExternalConverters.class, "converters", ConverterWrapper.class);

        getXstream().alias("external-converter", ConverterWrapper.class);
        getXstream().useAttributeFor(ConverterWrapper.class, "id");
        getXstream().useAttributeFor(Converter.class, "operations");
        getXstream().useAttributeFor(Converter.class, "validate");

        getXstream().registerConverter(new EConverterConverter(getPm()));
    }

    @Override
    protected Object newObject() {
        return new ExternalConverters(getPm());
    }
}
