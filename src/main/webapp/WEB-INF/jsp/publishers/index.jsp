<%-- 
    Document   : publishers
    Created on : Jun 5, 2011, 9:18:57 PM
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<fmt:setBundle basename="mx.edu.uvaq.elibrary.i18n.messages" var="bundle"/>
<c:set var="i18n" value="${bundle.resourceBundle}"/>
<c:set var="rutaContexto" value="${pageContext.servletContext.contextPath}"/>

<html>
  <head>
    <jsp:include page="/WEB-INF/jspf/head-default.jspf"/>
    <script type="text/javascript" src="${rutaContexto}/js/publishers.js"></script>
    <script type="text/javascript">
      $(function() {
        UVAQ.eLibrary.estilizarTabla();
        UVAQ.eLibrary.generarTooltips();
        UVAQ.eLibrary.usarQtips();
      });
    </script>
    <title>${i18n['login.header']}</title>
  </head>
  <body>

    <jsp:include page="/WEB-INF/jspf/admin-header.jspf">
      <jsp:param name="encabezado" value="${encabezadoUsuarios}"/>
    </jsp:include>


    <div class="container-fluid">
      <jsp:include page="/WEB-INF/jspf/sidebar.jspf"/>
      <div class="content">
        <!-- Main hero unit for a primary marketing message or call to action -->
        <div class="hero-unit">
          <c:if test="${not empty _messages['editorial-eliminar-resultado']}">
            <div class="alert-message ${_messages['editorial-eliminar-resultado'].messageClass} ">
              <a class="close" href="#">×</a>
              ${_messages['editorial-eliminar-resultado'].detail}
            </div>
          </c:if>
          <h2>${i18n['books.publishers']}</h2>
          <div class="row" style="text-align: center;">
            <div class="span14">
              <form>
                <fieldset>
                  <div class="clearfix">
                    <input class="xxlarge busqueda" id="busqueda" name="busqueda" type="text"/>
                    <input class="small" name="buscar" value="${i18n['labels.search']}" type="submit"/>
                  </div>
                </fieldset>
              </form>
            </div>
          </div>
          <div class="row" style="text-align: center;margin-bottom:  21px;">
            <div class="clearfix">
              <c:url var="addPublisher" value="/admin/editoriales/crear"/>
              <c:url var="editPublisher" value="/admin/editoriales/editar"/>
              <c:url var="deletePublisher" value="/admin/editoriales/eliminar"/>
              <a class="btn primary small" href="${addPublisher}">${i18n['books.add']}</a>
              <a id="edit-publisher-link" class="btn small" href="${editPublisher}">${i18n['books.edit']}</a>
              <a id="delete-publisher-link" class="btn danger small" href="${deletePublisher}">${i18n['books.delete']}</a>
            </div>
          </div>
          <c:choose>
            <c:when test="${not empty editoriales}">
              <table class="tabla-datos bordered-table">
                <thead>
                  <tr>
                    <th class="columna-checkbox"><input type="checkbox"/></th>
                    <th class="columna-id">ID</th>
                    <th>
                      Editorial
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${editoriales}" var="editorial">
                    <tr class="alt">
                      <td class="columna-checkbox"><input id="checkbox" type="checkbox"/></td>
                      <td class="columna-id">${editorial.id}</td>
                      <td>${editorial.name}</td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </c:when>
            <c:otherwise>
              <div>
                ${i18n['books.messages.noPublishers']}
              </div>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
      <div id="seleccion-invalida" class="hidden">
        ${i18n['books.messages.noPublisherSelected']}
      </div>
    </div>
    <div id="myModal" class="modal hide fade">
      <div class="modal-body">
        Estas seguro, se perderan los datos de forma permanente.
      </div>
      <div class="modal-footer">
        <a id="ok" href="#" class="btn primary">OK</a>
        <a id="cancel" href="#" class="btn warning">Cancelar</a>
      </div>
    </div>
    <div id="error-seleccion" class="modal hide fade">
      <div class="modal-body">
        <p>Debe selecionar algo.</p>
      </div>
      <div class="modal-footer">
        <a id="error" href="#" class="btn primary">OK</a>
      </div>
    </div>
  </body>
</html>