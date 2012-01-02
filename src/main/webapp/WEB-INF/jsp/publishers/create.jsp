<%-- 
    Document   : create
    Created on : Dec 26, 2011, 11:17:21 PM
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
      <div class="content hero-unit">
        <c:if test="${not empty _messages['editorial-salvar-resultado']}">
          <div class="alert-message ${_messages['editorial-salvar-resultado'].claseMensaje} ">
            <a class="close" href="#">×</a>
              ${_messages['editorial-salvar-resultado'].detalle}
          </div>
        </c:if>
        <c:url var="url" value="/admin/editoriales/salvar"/>
        <form action="${url}" method="POST">
          <table id="editoriales">
            <tbody>
              <tr>
                <td><label for="editorial">editorial</label></td>
                <td>
                  <input id="editorial" name="nombre" type="text" value="${editorial.name}"/>
                  <input name="id" type="hidden" value="${editorial.id}"/>
                </td>
              </tr>
            </tbody>
          </table>
          <div id="borde"></div>
          <div class="panel-botones" id="editoriales-botones">
            <button id="boton-nuevo-editorial" class="btn primary" type="submit">Agregar</button>
            <c:url var="url" value="/admin/editoriales/listar"/>
            <a href="${url}" class="btn error">Cancelar</a>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>