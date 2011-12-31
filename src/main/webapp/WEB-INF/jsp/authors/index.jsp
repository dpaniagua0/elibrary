<%-- 
    Document   : authors
    Created on : Jun 5, 2011, 9:46:10 PM
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
    <script type="text/javascript" src="${rutaContexto}/js/authors.js"></script>
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
      <div class="sidebar">
        <div class="well">
          <h5>${i18n['books.labels.actions']}</h5>
          <ul>
            <li>
              <c:url var="url" value="/admin/autores/crear"/>
              <a id="agregar-autor-link" href="${url}" title="${i18n['books.addAuthor']}">
                ${i18n['books.addAuthor']}
                <c:url var="url" value="/images/plus-circle-frame.png"/>
                <img src="${url}" alt="[plus-circle-frama.png]"/>
              </a>
            </li>
            <li>
              <c:url var="url" value="/admin/autores/editar"/>
              <a id="editar-autor-link" href="${url}" title="${i18n['books.editAuthor']}">
                ${i18n['books.editAuthor']}
                <c:url var="url" value="/images/pencil.png"/>
                <img src="${url}" alt="[pencil.png]"/>
              </a>
            </li>
            <li>
              <c:url var="url" value="/admin/autores/eliminar"/>
              <a id="eliminar-autor-link" href="${url}" title="${i18n['books.deleteAuthor']}">
                ${i18n['books.deleteAuthor']}
                <c:url var="url" value="/images/minus-circle-frame.png"/>
                <img src="${url}" alt="[minus-circle-frame.png]"/>
              </a>
            </li>
          </ul>
        </div>
      </div>
      <div class="content">
        <!-- Main hero unit for a primary marketing message or call to action -->
        <div class="hero-unit">
          <c:if test="${not empty messages['autor-salvar-resultado']}">
            <div class="alert-message ${messages['autor-salvar-resultado'].messageClass} ">
              <a class="close" href="#">×</a>
                ${messages['autor-salvar-resultado'].detail}
            </div>
          </c:if>
          <c:if test="${not empty messages['autor-eliminar-resultado']}">
            <div class="alert-message ${messages['autor-eliminar-resultado'].messageClass} ">
              <a class="close" href="#">×</a>
                ${messages['autor-eliminar-resultado'].detail}
            </div>
          </c:if>
          <h2>${i18n['books.authors']}</h2>
          <div class="row" style="text-align: center;">
            <div class="span12">
              <form>
                <fieldset>
                  <div class="span14 clearfix">
                    <select class="small" name="criterio">
                      <option value="1">${i18n['labels.name']}</option>
                      <option value="2">${i18n['labels.lastName']}</option>
                    </select>
                    <input class="xxlarge busqueda" id="busqueda" name="busqueda" type="text"/>
                    <input class="small" name="buscar" value="${i18n['labels.search']}" type="submit"/>
                  </div>
                </fieldset>
              </form>
            </div>
          </div>
          <c:choose>
            <c:when test="${not empty authors}">
              <table class="tabla-datos bordered-table">
                <thead>
                  <tr>
                    <th class="columna-checkbox"><input type="checkbox"/></th>
                    <th class="columna-id">ID</th>
                    <th>
                      Autor
                    </th>
                    <th>
                      Apellidos
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${authors}" var="author">
                    <tr class="alt">
                      <td class="columna-checkbox"><input type="checkbox"/></td>
                      <td class="columna-id">${author.id}</td>
                      <td>${author.name}</td>
                      <td>${author.lastName}</td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </c:when>
            <c:otherwise>
              <div>
                  ${i18n['books.messages.noAuthors']}
              </div>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </div>
    <c:if test="${not empty modelo.autor}">
      <div id="info-autores" title="Nuevo autor" class="ui-helper-hidden">
        <c:url var="url" value="/admin/autores"/>
        <form action="${url}">
          <table id="autores">
            <tbody>
              <tr>
                <td><label for="autor">Autor</label></td>
                <td>
                  <input id="autor" name="nombre" type="text" value="${modelo.autor.nombre}"/>
                  <input name="id_autor" type="hidden"/>
                </td>
              </tr>
              <tr>
                <td><label for="apellidos">Apellidos</label></td>
                <td>
                  <input id="apellidos" name="apellidos" type="text" value="${modelo.autor.apellidos}"/>
                </td>
              </tr>
            </tbody>
          </table>
          <div id="borde"></div>
          <div class="panel-botones" id="autores-botones">
            <button id="boton-nuevo-autor" name="accion" value="agregar" type="submit">Agregar</button>
            <button id="boton-cancelar" type="button">Cancelar</button>
          </div>
        </form>
      </div>
    </c:if>
  </body>
</html>
