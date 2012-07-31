package jpaoletti.jpm.parser;

import jpaoletti.jpm.converter.Converter;
import jpaoletti.jpm.converter.ExternalConverter;
import jpaoletti.jpm.core.*;
import jpaoletti.jpm.validator.Validator;

/**
 *
 * @author jpaoletti
 */
public class EntityParser extends ParserSupport {

    public EntityParser(PresentationManager pm) {
        super(pm);
    }

    @Override
    protected void init() {
        super.init();
        getXstream().alias("entity", Entity.class);

        getXstream().aliasAttribute("no-count", "noCount");

        getXstream().useAttributeFor(Entity.class, "id");
        getXstream().useAttributeFor(Entity.class, "noCount");
        getXstream().useAttributeFor(Entity.class, "clazz");
        getXstream().useAttributeFor(Entity.class, "extendz");
        getXstream().useAttributeFor(Entity.class, "cached");

        getXstream().alias("field", Field.class);

        getXstream().useAttributeFor(Field.class, "id");
        getXstream().useAttributeFor(Field.class, "display");
        getXstream().useAttributeFor(Field.class, "align");
        getXstream().useAttributeFor(Field.class, "width");
        getXstream().useAttributeFor(Field.class, "property");

        getXstream().alias("field-validator", Validator.class);
        getXstream().alias("validator", Validator.class);
        getXstream().alias("operations", Operations.class);
        getXstream().alias("operation", Operation.class);

        getXstream().aliasField("audit-level", Operation.class, "auditLevel");
        getXstream().useAttributeFor(Operation.class, "id");
        getXstream().useAttributeFor(Operation.class, "enabled");
        getXstream().useAttributeFor(Operation.class, "scope");
        getXstream().useAttributeFor(Operation.class, "display");
        getXstream().useAttributeFor(Operation.class, "confirm");
        getXstream().useAttributeFor(Operation.class, "perm");
        getXstream().useAttributeFor(Operation.class, "follows");
        getXstream().useAttributeFor(Operation.class, "compact");
        getXstream().useAttributeFor(Operation.class, "popup");
        getXstream().useAttributeFor(Operation.class, "auditLevel");
        getXstream().useAttributeFor(Operation.class, "navigable");

        getXstream().alias("owner", EntityOwner.class);
        getXstream().alias("highlights", Highlights.class);
        getXstream().alias("highlight", Highlight.class);

        getXstream().useAttributeFor(PMCoreObject.class, "debug");

        getXstream().useAttributeFor(Highlight.class, "field");
        getXstream().useAttributeFor(Highlight.class, "color");
        getXstream().useAttributeFor(Highlight.class, "value");
        getXstream().useAttributeFor(Highlight.class, "scope");
        getXstream().useAttributeFor(Highlight.class, "style");

        getXstream().addImplicitCollection(Entity.class, "fields", Field.class);
        getXstream().addImplicitCollection(Field.class, "validators", Validator.class);

        getXstream().addImplicitCollection(Operations.class, "operations", Operation.class);
        getXstream().addImplicitCollection(Highlights.class, "highlights", Highlight.class);
        getXstream().addImplicitCollection(Operation.class, "validators", Validator.class);

        getXstream().alias("converter", Converter.class);
        getXstream().useAttributeFor(Converter.class, "operations");
        getXstream().useAttributeFor(Converter.class, "validate");

        getXstream().alias("econverter", ExternalConverter.class);
        getXstream().useAttributeFor(ExternalConverter.class, "id");
        getXstream().useAttributeFor(ExternalConverter.class, "operations");

        getXstream().registerConverter(new ConverterConverter());
    }

    @Override
    protected Object newObject() {
        return new Entity(getPm());
    }
}
