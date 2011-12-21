<%--
    Document   : usuario
    Created on : Apr 4, 2011, 11:18:19 AM
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<fmt:setBundle basename="mx.edu.uvaq.elibrary.i18n.MensajesELibrary" var="bundle" />
<c:set var="i18n" value="${bundle.resourceBundle}" />
<c:set var="rutaContexto" value="${pageContext.servletContext.contextPath}" />

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href='${rutaContexto}/images/logo.png' rel='shortcut icon' />
    <link rel="stylesheet" href="${rutaContexto}/css/global.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/css/usuarios.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/css/fg.menu.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/css/bootstrap.css" type="text/css" />
    <script type="text/javascript" src="${rutaContexto}/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/jquery/ui/jquery-ui.js"></script>
    <link rel="stylesheet" href="${rutaContexto}/js/jquery/themes/start/jquery-ui-start.css" type="text/css" />
    <script type="text/javascript" src="${rutaContexto}/js/fg.menu.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/utilidades.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/bootstrap/bootstrap-dropdown.js"></script>
    <script type="text/javascript">
      $(UVAQ.eLibrary.estilizarTabla);
    </script>
    <script type="text/javascript" src="${rutaContexto}/js/navegacion.js"></script>
    <title>${i18n['usuarios.titulo']}</title>
  </head>
  <body>
    <jsp:include page="/WEB-INF/jspf/encabezadoAdministracion.jspf" />
    <div id="cuerpo">
      <h1>${i18n['usuarios.encabezado']}</h1>
      <form id="formulario-busqueda" action="/elibrary/admin/usuarios">
        <table>
          <tbody>
            <tr>
              <td class="tamano-completo">
                <input class="busqueda" id="busqueda" name="busqueda" type="text"/>
              </td>
              <td>
                <input id="buscar" name="buscar" value="${i18n['usuarios.etiquetas.buscar']}" type="submit" />
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <c:choose>
        <c:when test="${not empty usuariosForma.usuarios}">
          <table id="usuarios" class="tabla-datos">
            &emsp;
            <thead>
              <tr>
                <th class="columna-checkbox"><input type="checkbox" /></th>
                <th>
                  ${i18n['etiquetas.correoElectronico']}
                </th>
                <th>
                  ${i18n['etiquetas.contraseña']}
                </th>
                <th>
                  ${i18n['etiquetas.nombre']}
                </th>
                <th>
                  ${i18n['etiquetas.apellidos']}
                </th>
                <th>
                  ${i18n['etiquetas.roles']}
                </th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${usuariosForma.usuarios}" var="usuario">
                <tr class="alt">
                  <td class="columna-checkbox"><input type="checkbox" /></td>
                  <td>${usuario.correoElectronico}</td>
                  <td>${usuario.password}</td>
                  <td>${usuario.nombre}</td>
                  <td>${usuario.apellidos}</td>
                  <td>${usuario.roles}</td>
                </tr>
              </c:forEach>
            </tbody>
            <caption class="acciones">
              <input class="agregar" id="agregar-usuario" name="accion" type="submit" value="${i18n['etiquetas.agregar']}" title="${i18n['usuarios.etiquetas.agregarUsuario']}"/>
              <input class="editar" id="editar-usuario" name="accion" type="submit" value="${i18n['etiquetas.editar']}" title="${i18n['usuarios.etiquetas.editarUsuario']}"/>
              <input class="eliminar" id="eliminar-usuario" name="accion" type="submit" value="${i18n['etiquetas.eliminar']}" title="${i18n['usuarios.etiquetas.eliminarUsuario']}"/>
            </caption>
          </table>
        </c:when>
        <c:otherwise>
          <div>
            ${i18n['usuarios.mensajes.noHayUsuarios']}
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </body>
</html>