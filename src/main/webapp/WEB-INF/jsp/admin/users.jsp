<%--
    Document   : usuario
    Created on : Apr 4, 2011, 11:18:19 AM
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<fmt:setBundle basename="mx.edu.uvaq.elibrary.i18n.messages" var="bundle"/>
<c:set var="i18n" value="${bundle.resourceBundle}"/>
<c:set var="rutaContexto" value="${pageContext.servletContext.contextPath}"/>

<html>
  <head>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href='${rutaContexto}/images/logo.png' rel='shortcut icon'/>
    <link rel="stylesheet" href="${rutaContexto}/css/global.css" type="text/css"/>
    <link rel="stylesheet" href="${rutaContexto}/css/bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="${rutaContexto}/js/jquery/plugins/qtip/jquery.qtip.css" type="text/css"/>
    <script type="text/javascript" src="${rutaContexto}/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/jquery/ui/jquery-ui.js"></script>
    <link rel="stylesheet" href="${rutaContexto}/js/jquery/themes/start/jquery-ui-start.css" type="text/css"/>
    <link rel="stylesheet" href="${rutaContexto}/css/global.css" type="text/css"/>
    <script type="text/javascript" src="${rutaContexto}/js/jquery/plugins/qtip/jquery.qtip.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/utilities.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/books.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/bootstrap/bootstrap-dropdown.js"></script>
    <script type="text/javascript">
      $(UVAQ.eLibrary.estilizarTabla);
    </script>
    <title>${i18n['usuarios.titulo']}</title>
  </head>
  <body>
    <jsp:include page="/WEB-INF/jspf/admin-header.jspf"/>

    <div class="container-fluid">
      <div class="sidebar">
        <div class="well">
          <h5>${i18n['libros.acciones']}</h5>
          <ul>
            <li>
              <a id="agregar-editorial-link" href="#" title="${i18n['usuarios.etiquetas.agregarUsuario']}">
                ${i18n['usuarios.etiquetas.agregarUsuario']}
                <c:url var="url" value="/images/user--plus.png"/>
                <img src="${url}" alt="[user--plus.png]"/>
              </a>
            </li>
            <li>
              <a id="editar-editorial-link" href="#" title="${i18n['usuarios.etiquetas.editarUsuario']}">
                ${i18n['usuarios.etiquetas.editarUsuario']}
                <c:url var="url" value="/images/user--pencil.png"/>
                <img src="${url}" alt="[user--pencil.png]"/>
              </a>
            </li>
            <li>
              <a id="eliminar-editorial-link" href="#" title="${i18n['usuarios.etiquetas.eliminarUsuario']}">
                ${i18n['usuarios.etiquetas.eliminarUsuario']}
                <c:url var="url" value="/images/user--minus.png"/>
                <img src="${url}" alt="[user--minus.png]"/>
              </a>
            </li>
          </ul>
        </div>
      </div>
      <div class="content">
        <!-- Main hero unit for a primary marketing message or call to action -->
        <div class="hero-unit">
          <h2>${i18n['usuarios.encabezado']}</h2>
          <div class="row" style="text-align: center;">
            <div class="span14">
              <form>
                <fieldset>
                  <div class="clearfix">
                    <select class="small" name="criterio">
                      <option value="1">${i18n['etiquetas.nombre']}</option>
                      <option value="2">${i18n['etiquetas.apellidos']}</option>
                    </select>
                    <input class="busqueda xxlarge" name="busqueda" type="text"/>
                    <input name="buscar" value="${i18n['usuarios.etiquetas.buscar']}" type="submit"/>
                  </div>
                </fieldset>
              </form>
            </div>
          </div>
          <c:choose>
            <c:when test="${not empty usuariosForma.usuarios}">
              <table class="tabla-datos bordered-table">
                &emsp;
                <thead>
                  <tr>
                    <th class="columna-checkbox"><input type="checkbox"/></th>
                    <th>
                        ${i18n['etiquetas.correoElectronico']}
                    </th>
                    <th>
                        ${i18n['labels.password']}
                    </th>
                    <th>
                        ${i18n['etiquetas.nombre']}
                    </th>
                    <th>
                        ${i18n['etiquetas.apellidos']}
                    </th>
                    <th>
                        ${i18n['labels.roles']}
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${usuariosForma.usuarios}" var="usuario">
                    <tr class="alt">
                      <td class="columna-checkbox"><input type="checkbox"/></td>
                      <td>${usuario.correoElectronico}</td>
                      <td>${usuario.password}</td>
                      <td>${usuario.nombre}</td>
                      <td>${usuario.apellidos}</td>
                      <td>${usuario.roles}</td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </c:when>
            <c:otherwise>
              <div>
                  ${i18n['usuarios.mensajes.noHayUsuarios']}
              </div>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </div>
  </body>
</html>