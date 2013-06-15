package jpaoletti.jpm.struts;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.*;
import jpaoletti.jpm.util.KeyValue;

/**
 *
 * @author jpaoletti
 */
public class CollectionHelper {

    private static final Pattern DISPLAY_PATTERN = Pattern.compile("\\{.*?\\}");
    private String display;

    public CollectionHelper(String _display) throws ConverterException {
        this.display = _display;
    }

    public List<String> getListOfTexts(final Collection collection) throws ConverterException {
        final List<String> result = new ArrayList();
        for (Object object : collection) {
            result.add(getObjectDisplay(object));
        }
        return result;
    }

    public String getObjectDisplay(Object object) {
        if (getDisplay() == null) {
            return object == null ? "" : object.toString();
        }
        final Map<String, String> replaces = new HashMap<String, String>();
        final Matcher matcher = DISPLAY_PATTERN.matcher(getDisplay());
        while (matcher.find()) {
            final String _display_field = matcher.group().replaceAll("\\{", "").replaceAll("\\}", "");
            replaces.put("{" + _display_field + "}", PresentationManager.getPm().getAsString(object, _display_field));
        }
        String result = getDisplay();
        for (Map.Entry<String, String> entry : replaces.entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public String getDisplay() {
        if (display != null && "".equals(display.trim())) {
            return null;
        }
        return display;
    }

    public List<KeyValue> getFullList(PMContext ctx,
            Entity entity,
            String listFilter,
            String stringFilter,
            String sortField,
            String sortd,
            String relatedFieldName,
            Object relatedFieldValue) throws ConverterException {

        final ListSort sort = new ListSort(sortField, (sortd == null) ? ListSort.SortDirection.ASC : ListSort.SortDirection.DESC);

        ListFilter lfilter = null;
        if (listFilter != null && listFilter.compareTo("null") != 0 && listFilter.compareTo("") != 0) {
            lfilter = (ListFilter) ctx.getPresentationManager().newInstance(listFilter);
        }
        final List<KeyValue> result = new ArrayList<KeyValue>();
        try {
            final List<?> list;
            final EntityFilter filter = entity.getDataAccess().createFilter(ctx);
            if (stringFilter != null && !"".equals(stringFilter.trim()) && getDisplay() != null) {
                filter.setBehavior(FilterBehavior.OR);
                final Matcher matcher = DISPLAY_PATTERN.matcher(getDisplay());
                while (matcher.find()) {
                    final String _display_field = matcher.group().replaceAll("\\{", "").replaceAll("\\}", "");
                    filter.addFilter(_display_field, stringFilter, FilterOperation.LIKE);
                }
                filter.process(entity);
            }
            if (relatedFieldName != null && relatedFieldValue != null) {
                //If we have a related object, search is with "AND" behaviour 
                //and will break any multi field display search.
                filter.setBehavior(FilterBehavior.AND);
                filter.addFilter(relatedFieldName, relatedFieldValue, FilterOperation.EQ);
            }
            list = entity.getDataAccess().list(ctx, filter, lfilter, sort, null, null);
            if (list == null) {
                return null;
            }
            for (Object object : list) {
                result.add(new KeyValue(
                        entity.getDataAccess().getInstanceId(ctx, new EntityInstanceWrapper(object)).getValue(),
                        getObjectDisplay(object)));
            }
        } catch (PMException ex) {
            throw new ConverterException(ex);
        }
        return result;
    }
}
