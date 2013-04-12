<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<div class="modal hide" id="popup-modal">
    <div class="modal-header">
        <button class="close" data-dismiss="modal">×</button>
        <span class="hide loading-text">${pmfn:message('jpm.popup.loading')}</span>
        <h3></h3>
    </div>
    <div class="modal-body" id="modal-body">
        <div class="center"><img alt="loading..." src="${es.context_path}/loading.gif" /></div>
    </div>
    <div class="modal-footer">
    </div>
</div>
