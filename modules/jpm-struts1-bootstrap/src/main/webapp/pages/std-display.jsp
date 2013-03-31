<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/inc-full.jsp"  %>
<%-- Standard display page for an entity instance item --%>
<pm:page title="titles.${ctx.operation.id}">
    <pm:std-header ctx="${ctx}" />
    <c:if test="${not empty ctx.selected}">
        <div class="row-fluid">
            <pm:std-form contextPath="${es.context_path}" entity="${ctx.entity}" operation="${ctx.operation}" editable="${ctx.map.editable}" resetable="true">
                <c:if test="${empty ctx.entity.panels}">
                    <c:forEach var="field" items="${pmfn:displayedFields(ctx.user, entity, ctx.operation.id)}">
                        <div id="control-group-${field.id}" class="control-group">
                            <label class="control-label" for="f_${field.id}">${field.title}</label>
                            <div class="controls">
                                <pmfn:converted-item ctx="${ctx}" field="${field}" />
                                <c:if test="${not empty field.tooltip}">
                                    <p class="help-block">${field.tooltip}</p>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${not empty ctx.entity.panels}">
                    <c:forEach var="row" items="${ctx.entity.panels.rows}">
                        <div class="row-fluid">
                            <c:forEach var="panel" items="${row.panels}">
                                <div class="span${pmfn:floor(12 / fn:length(row.panels))}">
                                    <div class="widget">
                                        <div class="widget-header">
                                            <i class="${panel.icon}"></i>
                                            <h3><pmfn:message key="${panel.title}"/></h3>
                                        </div> 
                                        <div class="widget-content">
                                            <c:forEach var="field" items="${pmfn:panelDisplayedFields(ctx.user, entity, ctx.operation.id, panel)}">
                                                <div id="control-group-${field.id}" class="control-group">
                                                    <label class="control-label" for="f_${field.id}">${field.title}</label>
                                                    <div class="controls">
                                                        <pmfn:converted-item ctx="${ctx}" field="${field}" />
                                                        <c:if test="${not empty field.tooltip}">
                                                            <p class="help-block">${field.tooltip}</p>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <script type="text/javascript">
                            PM_register(function() {
                                //Removes empty widgets
                                $(".widget-content:not(:has(div))").parent(".widget").parent().remove();
                                $(".row-fluid:not(:has(div))").remove();
                            });
                        </script>
                    </c:forEach>
                </c:if>
            </pm:std-form>
        </div>
    </c:if>
    <div class="entity_message_container_${entity.id}"></div>
    <div class="message_container"></div>
</pm:page>