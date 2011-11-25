package jpaoletti.jpm.core.operations;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import jpaoletti.jpm.core.EntityFilter;
import jpaoletti.jpm.core.Field;
import jpaoletti.jpm.core.PMContext;
import jpaoletti.jpm.core.PMException;
import jpaoletti.jpm.core.exporter.Exporter;
import jpaoletti.jpm.core.message.MessageFactory;

/**
 * Operation for export. <br/>
 * 
 * Parameters: <br/>
 * 
 * <dl>
 *      <dt>exporters</dt><dd>Comma separated list with exporter classes</dd>
 * </dl>
 *
 * @author jpaoletti
 * @since 25/11/2011
 * @version
 * @issue
 *
 */
public class Export extends OperationCommandSupport {

    public Export(String operationId) {
        super(operationId);
    }

    public Export() {
        super("export");
    }

    protected List<Field> getExportFields(PMContext ctx) {
        final Object[] fieldIds = ctx.getParameters("fields");
        final List<Field> fields = new ArrayList<Field>();
        for (Object object : fieldIds) {
            final String fieldId = (String) object;
            final Field field = ctx.getEntity().getFieldById(fieldId);
            fields.add(field);
        }
        return fields;
    }

    @Override
    protected boolean prepare(PMContext ctx) throws PMException {
        super.prepare(ctx);
        final List<Exporter> exporterList = getExporterList(ctx);
        ctx.put("exporters", exporterList);
        return true;
    }

    @Override
    protected void doExecute(PMContext ctx) throws PMException {
        if (finished(ctx)) {
            final OutputStream out = (OutputStream) ctx.get("out");
            if (out == null) {
                throw new PMException("exporter.out.undefined");
            } else {
                final List<Exporter> exporterList = getExporterList(ctx);
                final String _exporter = (String) ctx.getParameter("exporter");
                Exporter exporter = null;
                for (Exporter e : exporterList) {
                    if (e.getId().equalsIgnoreCase(_exporter)) {
                        exporter = e;
                    }
                }
                if (exporter != null) {
                    final List<Field> fields = getExportFields(ctx);
                    final List<String> headers = getHeaders(ctx, fields);

                    final List<List<String>> toExport = new ArrayList<List<String>>();
                    EntityFilter filter = null;
                    if (ctx.getParameter("filter") != null) {
                        filter = ctx.getEntityContainer().getFilter();
                    }
                    final List<?> list = ctx.getEntity().getDataAccess().list(ctx, filter, ctx.getList().getListFilter(), ctx.getList().getSort(), null, null);
                    for (Object item : list) {
                        ctx.setEntityInstance(item);
                        final List<String> row = new ArrayList<String>();
                        for (Field field : fields) {
                            field.visualize(ctx, ctx.getOperation(), ctx.getEntity());
                            final Object visualize = ctx.getFieldValue();
                            final String cell = (visualize == null) ? "" : visualize.toString();
                            row.add(cell);
                        }
                        toExport.add(row);
                    }

                    try {
                        exporter.export(toExport, headers, out);
                    } catch (IOException ex) {
                        ctx.addMessage(MessageFactory.error(ctx.getEntity(), "exporter.error", ex.getMessage(), _exporter));
                    }
                } else {
                    ctx.addMessage(MessageFactory.error(ctx.getEntity(), "exporter.undefined", _exporter));
                }
            }
        }
    }

    protected List<Exporter> getExporterList(PMContext ctx) {
        final String _exporters = ctx.getOperation().getConfig("exporters", "jpaoletti.jpm.core.exporter.ExporterCSV");
        final String[] exporters = _exporters.split("[,]");
        final List<Exporter> exporterList = new ArrayList<Exporter>();
        for (String string : exporters) {
            exporterList.add((Exporter) ctx.getPresentationManager().newInstance(string));
        }
        return exporterList;
    }

    protected List<String> getHeaders(PMContext ctx, List<Field> fields) {
        final List<String> headers = new ArrayList<String>();
        for (Field field : fields) {
            headers.add(field.getTitle());
        }
        return headers;
    }
}
