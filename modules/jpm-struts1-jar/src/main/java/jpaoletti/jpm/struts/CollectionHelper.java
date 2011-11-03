package jpaoletti.jpm.struts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jpaoletti.jpm.converter.ConverterException;
import jpaoletti.jpm.core.Entity;
import jpaoletti.jpm.core.EntityFilter;
import jpaoletti.jpm.core.EntityInstanceWrapper;
import jpaoletti.jpm.core.FilterBehavior;
import jpaoletti.jpm.core.FilterOperation;
import jpaoletti.jpm.core.ListFilter;
import jpaoletti.jpm.core.ListSort;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.PresentationManager;
import jpaoletti.jpm.util.KeyValue;

/**
 *
 * @author jpaoletti
 */
public class CollectionHelper {

    private static final Pattern DISPLAY_PATTERN = Pattern.compile("\\{.*?\\}");
    private String display;

    public CollectionHelper(String _display) throws ConverterException {
        if (_display == null) {
            throw new ConverterException("display.cannot.be.null");
        }
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
        final Map<String, String> replaces = new HashMap<String, String>();
        final Matcher matcher = DISPLAY_PATTERN.matcher(getDisplay());
        while (matcher.find()) {
            final String _display_field = matcher.group().replaceAll("\\{", "").replaceAll("\\}", "");
            replaces.put("\\{" + _display_field + "\\}", PresentationManager.getPm().getAsString(object, _display_field));
        }
        String result = getDisplay();
        for (Map.Entry<String, String> entry : replaces.entrySet()) {
            result = result.replaceFirst(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public String getDisplay() {
        return display;
    }

    public List<KeyValue> getFullList(PMContext ctx,
            Entity entity,
            String listFilter,
            String stringFilter,
            String sortField,
            String sortd) throws ConverterException {

        final ListSort sort = new ListSort(sortField, (sortd != null) ? ListSort.SortDirection.ASC : ListSort.SortDirection.DESC);

        ListFilter lfilter = null;
        if (listFilter != null && listFilter.compareTo("null") != 0 && listFilter.compareTo("") != 0) {
            lfilter = (ListFilter) ctx.getPresentationManager().newInstance(listFilter);
        }
        final List<KeyValue> result = new ArrayList<KeyValue>();
        try {
            final List<?> list;
            if (stringFilter != null) {
                final EntityFilter filter = entity.getDataAccess().createFilter(ctx);
                filter.setBehavior(FilterBehavior.OR);
                final Matcher matcher = DISPLAY_PATTERN.matcher(getDisplay());
                while (matcher.find()) {
                    final String _display_field = matcher.group().replaceAll("\\{", "").replaceAll("\\}", "");
                    filter.addFilter(_display_field, stringFilter, FilterOperation.LIKE);
                }
                filter.process(entity);
                list = entity.getDataAccess().list(ctx, filter, lfilter, sort, null, null);
            } else {
                list = entity.getDataAccess().list(ctx, null, lfilter, sort, null, null);
            }
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
