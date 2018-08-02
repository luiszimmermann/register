<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'billing.label', default: 'Billing')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-billing" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="list" href="${createLink(uri: '/')}"><g:message code="default.homelist.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-billing" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <fieldset class="form">
                    <g:form action="search" method="GET" class="form-inline">
                        <div class="fieldcontain">
                            <div class="col-md-6">
                                <div>
                                    <label for="name">Nome/E-mail:</label>
                                    <g:textField name="name" value="${params.name}" />
                                </div>
                                <div>
                                    <label for="desc">Descrição:</label>
                                    <g:textField name="desc" value="${params.desc}" />
                                </div>
                                <div>
                                    <label for="paymentMethod">Forma de pagamento:</label>
                                    <g:select name="paymentMethod"from="${billing.constrainedProperties.paymentMethod.inList}"  value="${params.paymentMethod}" />
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div>
                                    <label for="dueDateBegin">Vencimento inicial:</label>
                                    <g:datePicker name="dueDateBegin" value="${params.dueDateBegin}" precision="day" />
                                </div>
                                <div>
                                    <label for="dueDateEnd">Vencimento final:</label>
                                    <g:datePicker name="dueDateEnd" value="${params.dueDateEnd}" precision="day" />
                                </div>
                            </div>
                            <div>
                                <g:actionSubmit value="Buscar" action="search" />
                            </div>
                        </div>
                    </g:form>
                </fieldset>

            <f:table collection="${billingList}"  properties="['id', 'name', 'email', 'cpf', 'dueDate', 'value', 'paymentMethod', 'description', 'deleted']" />

        </div>
    </body>
</html>