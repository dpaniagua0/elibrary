<%-- 
    Document   : editoriales
    Created on : Jun 5, 2011, 9:18:57 PM
    Author     : daniel
--%>

<%--
    Document   : autores
    Created on : Jun 5, 2011, 9:46:10 PM
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
    <jsp:include page="/WEB-INF/jspf/headDefault.jspf"/>
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
              <c:url var="url" value="/admin/editoriales/editar" />
              <a id="editar-editorial-link" href="${url}" title="${i18n['libros.editarEditorial']}">
                ${i18n['libros.editarEditorial']}
                <c:url var="url" value="/images/pencil.png" />
                <img src="${url}" alt="[pencil.png]"/>
              </a>
            </li>
            <li>
              <c:url var="url" value="/admin/editoriales/eliminar" />
              <a id="eliminar-editorial-link" href="${url}" title="${i18n['libros.eliminarEditorial']}">
                ${i18n['libros.eliminarEditorial']}
                <c:url var="url" value="/images/minus-circle-frame.png" />
                <img src="${url}" alt="[minus-circle-frame.png]" />
              </a>
            </li>
          </ul>
        </div>
      </div>
      <div class="content">
        <!-- Main hero unit for a primary marketing message or call to action -->
        <div class="hero-unit">
          <c:if test="${not empty mensajes['editorial-eliminar-resultado']}">
            <div class="alert-message ${mensajes['editorial-eliminar-resultado'].claseMensaje} ">
              <a class="close" href="#">×</a>
              ${mensajes['editorial-eliminar-resultado'].detalle}
            </div>
          </c:if>
          <h2>${i18n['libros.editoriales']}</h2>
          <div class="row" style="text-align: center;">
            <div class="span14">
              <form>
                <fieldset>
                  <div class="clearfix">
                    <input class="xxlarge busqueda" id="busqueda" name="busqueda" type="text"/>
                    <input class="small" name="buscar" value="${i18n['etiquetas.buscar']}" type="submit" />
                  </div>
                </fieldset>
              </form>
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
                      <td class="columna-checkbox"><input  id="checkbox" type="checkbox" /></td>
                      <td class="columna-id">${editorial.id}</td>
                      <td>${editorial.nombre}</td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </c:when>
            <c:otherwise>
              <div>
                ${i18n['libros.mensajes.noHayEditoriales']}
              </div>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
      <div id="seleccion-invalida" class="ui-helper-hidden" title="${i18n['libros.mensaje.info']}">
        ${i18n['libros.mensaje.seleccionInvalida']}
      </div>
    </div>
  </div>
</body>
</html>