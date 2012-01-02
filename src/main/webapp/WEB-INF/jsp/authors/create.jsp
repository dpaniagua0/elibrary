<%-- 
    Document   : crear
    Created on : Dec 23, 2011, 1:58:24 PM
    Author     : arcesino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<fmt:setBundle basename="mx.edu.uvaq.elibrary.i18n.messages" var="bundle"/>
<c:set var="i18n" value="${bundle.resourceBundle}"/>

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
        <c:if test="${not empty _messages['autor-salvar-resultado']}">
          <div class="alert-message ${_messages['autor-salvar-resultado'].messageClass} ">
            <a class="close" href="#">×</a>
              ${_messages['autor-salvar-resultado'].detail}
          </div>
        </c:if>
        <c:url var="url" value="/admin/autores/salvar"/>
        <form action="${url}" method="POST">
          <table id="autores">
            <tbody>
              <tr>
                <td><label for="autor">Autor</label></td>
                <td>
                  <input id="autor" name="nombre" type="text" value="${author.name}"/>
                  <input name="id" type="hidden" value="${author.id}"/>
                </td>
              </tr>
              <tr>
                <td><label for="apellidos">Apellidos</label></td>
                <td>
                  <input id="apellidos" name="apellidos" type="text" value="${author.lastName}"/>
                </td>
              </tr>
            </tbody>
          </table>
          <div id="borde"></div>
          <div class="panel-botones" id="autores-botones">
            <button id="boton-nuevo-autor" class="btn primary" type="submit">Agregar</button>
            <c:url var="url" value="/admin/autores/listar"/>
            <a href="${url}" class="btn error">Cancelar</a>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>

