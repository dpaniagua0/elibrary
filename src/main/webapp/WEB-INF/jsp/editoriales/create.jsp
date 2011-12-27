<%-- 
    Document   : create
    Created on : Dec 26, 2011, 11:17:21 PM
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<fmt:setBundle basename="mx.edu.uvaq.elibrary.i18n.MensajesELibrary" var="bundle" />
<c:set var="i18n" value="${bundle.resourceBundle}" />
<c:set var="rutaContexto" value="${pageContext.servletContext.contextPath}" />

<html>
  <head>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href='${rutaContexto}/images/logo.png' rel='shortcut icon' />
    <link rel="stylesheet" href="${rutaContexto}/css/global.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/js/jquery/plugins/qtip/jquery.qtip.css" type="text/css" />
    <script type="text/javascript" src="${rutaContexto}/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/jquery/ui/jquery-ui.js"></script>
    <link rel="stylesheet" href="${rutaContexto}/js/jquery/themes/start/jquery-ui-start.css" type="text/css" /><link rel="stylesheet" href="${rutaContexto}/css/global.css" type="text/css" />
    <script type="text/javascript" src="${rutaContexto}/js/jquery/plugins/qtip/jquery.qtip.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/utilidades.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/libros.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/bootstrap/bootstrap-modal.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/bootstrap/bootstrap-dropdown.js"></script>
    <script type="text/javascript">
      $(function() {
        UVAQ.eLibrary.estilizarTabla();
        UVAQ.eLibrary.generarTooltips();
        UVAQ.eLibrary.usarQtips();
      });
    </script>
    <title>${i18n['login.encabezado']}</title>
  </head>
  <body>
    <jsp:include page="/WEB-INF/jspf/encabezadoAdministracion.jspf">
      <jsp:param name="encabezado" value="${encabezadoUsuarios}" />
    </jsp:include>
    <div class="container-fluid">
      <div class="sidebar">
        <div class="well">
          <h5>${i18n['libros.acciones']}</h5>
          <ul>
            <li>
              <c:url var="url" value="/admin/editoriales/crear"/>
              <a id="agregar-editorial-link" href="${url}" title="${i18n['libros.agregarEditorial']}">
                ${i18n['libros.agregarEditorial']}
                <c:url var="url" value="/images/plus-circle-frame.png" />
                <img src="${url}" alt="[plus-circle-frama.png]" />
              </a>
            </li>
            <li>
              <c:url var="url" value="/admin/editoriales/editar"/>
              <a id="editar-editorial-link" href="${url}" title="${i18n['libros.editarEditorial']}">
                ${i18n['libros.editarEditorial']}
                <c:url var="url" value="/images/pencil.png" />
                <img src="${url}" alt="[pencil.png]"/>
              </a>
            </li>
            <li>
              <c:url var="url" value="/admin/editoriales/eliminar"/>
              <a id="eliminar-editorial-link" href="${url}" title="${i18n['libros.eliminarEditorial']}">
                ${i18n['libros.eliminarEditorial']}
                <c:url var="url" value="/images/minus-circle-frame.png" />
                <img src="${url}" alt="[minus-circle-frame.png]" />
              </a>
            </li>
          </ul>
        </div>
      </div>
      <div class="content hero-unit">
        <c:if test="${not empty mensajes['editorial-salvar-resultado']}">
          <div class="alert-message ${mensajes['editorial-salvar-resultado'].claseMensaje} ">
            <a class="close" href="#">×</a>
            ${mensajes['editorial-salvar-resultado'].detalle}
          </div>
        </c:if>
        <c:url var="url" value="/admin/editoriales/salvar"/>
        <form action="${url}" method="POST">
          <table id="editoriales">
            <tbody>
              <tr>
                <td><label for="editorial">editorial</label></td>
                <td>
                  <input id="editorial" name="nombre" type="text" value="${modelo.editorial.nombre}"/>
                  <input name="id" type="hidden" value="${modelo.editorial.id}"/>
                </td>
              </tr>
            </tbody>
          </table>
          <div id="borde"></div>
          <div class="panel-botones" id="editoriales-botones">
            <button id="boton-nuevo-editorial" type="submit">Agregar</button>
              <c:url var="url" value="/admin/editoriales/listar"/>
              <a href="${url}" class="btn error">Cancelar</a>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>