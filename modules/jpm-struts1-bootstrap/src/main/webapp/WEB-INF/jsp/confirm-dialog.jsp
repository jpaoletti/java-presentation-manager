<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<div class="modal hide fade" id="confirmation-dlg">
    <div class="modal-header">
        <a class="close" data-dismiss="modal">×</a>
        <h3><pmfn:message key='pm.operation.confirm.title' /></h3>
    </div>
    <div class="modal-body">
        <p><pmfn:message key='pm.operation.confirm.question' /></p>
    </div>
    <div class="modal-footer">
        <a class="btn" data-dismiss="modal"><pmfn:message key='pm.operation.confirm.cancel' /></a>
        <a class="btn btn-danger" id="confirmation-dlg-ok"><pmfn:message key='pm.operation.confirm.ok' /></a>
    </div>
</div>
<script type="text/javascript">
    $('#confirmation-dlg').modal({
        show: false
    })
</script>