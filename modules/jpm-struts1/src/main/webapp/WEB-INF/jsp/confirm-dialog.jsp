<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<div id="confirmationDialog" title="<pmfn:message key='pm.operation.confirm.title' />" style="display: none;">
    <p>
        <span class="ui-icon ui-icon-alert dialog-icon"></span>
        <pmfn:message key='pm.operation.confirm.question' />
    </p>
</div>
<script type="text/javascript">
    PM_register(function(){     
        $( "#confirmationDialog" ).dialog({
            modal: true, 
            resizable: false,
            bgiframe: true,
            width: 300,
            height: 200,
            autoOpen: false
        });
    });
</script>