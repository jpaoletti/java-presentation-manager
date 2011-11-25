<%@include file="../inc/inc-full.jsp"  %>
<%-- Standard display page for an entity instance item --%>
<pm:page title="titles.${ctx.operation.id}">
    <div id="add" class="boxed">
        <pm:std-header ctx="${ctx}" />
        <pm:std-form contextPath="${es.context_path}" entity="${ctx.entity}" operation="${ctx.operation}" editable="true" resetable="false">
            <table class="std-table">
                <tbody id="list_body" >
                    <tr>
                        <th scope="row">
                            <pmfn:message key="operation.export.filter" />
                        </th>
                        <td>
                            <input type="checkbox" name="filter" />
                        </td>                        
                    </tr>
                    <tr>
                        <th scope="row">
                            <pmfn:message key="operation.export.exporters" />
                        </th>
                        <td>
                            <select name="exporter">
                                <c:forEach var="exporter" items="${ctx.map.exporters}">
                                    <option value="${exporter.id}">
                                        <pmfn:message key="operation.export.exporters.${exporter.id}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </td>                        
                    </tr>
                    <tr>
                        <th scope="row">
                            <pmfn:message key="operation.export.fields" />
                        </th>
                        <td>
                            <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}">
                                <input type="checkbox" name="fields" checked="checked" value="${field.id}"/>
                                <pm:field-name field="${field}" /><br/>
                            </c:forEach>
                        </td>                        
                    </tr>
                </tbody>
            </table>
        </pm:std-form>
        <div class="entity_message_container_${entity.id}"></div>
        <div class="message_container"></div>
    </div>
</pm:page>