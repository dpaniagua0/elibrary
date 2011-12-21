<%--
    Document   : inicio
    Created on : Jun 6, 2010, 2:21:16 PM
    Author     : daniel
--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>

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
    <link rel="stylesheet" href="${rutaContexto}/js/jquery/plugins/qtip/jquery.qtip.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/js/jquery/themes/start/jquery-ui-start.css" type="text/css" /><link rel="stylesheet" href="${rutaContexto}/css/global.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/css/usuarios.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/css/libros.css" type="text/css" />
    <script type="text/javascript" src="${rutaContexto}/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/jquery/plugins/qtip/jquery.qtip.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/jquery/ui/jquery-ui.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/utilidades.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/libros.js"></script>
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
    <fmt:message key="login.encabezado" var="encabezadoInicio" />
    <fmt:message key="usuarios.encabezado" var="encabezadoUsuarios" />
    <jsp:include page="/WEB-INF/jspf/encabezado.jspf">
      <jsp:param name="encabezado" value="${encabezadoUsuarios}" />
    </jsp:include>
    <div id="cuerpo">
      <form id="formulario-busqueda" action="/inicio">
        <table>
          <tbody>
            <tr>
              <td>
                <select name="criterio">
                  <option value="1">${i18n['libros.categoria']}</option>
                  <option value="2">${i18n['libros.titulo']}</option>
                  <option value="3">${i18n['libros.autor']}</option>
                  <option value="4">${i18n['libros.editorial']}</option>
                </select>
              </td>
              <td class="tamano-completo">
                <input class="busqueda" id="busqueda" name="busqueda" type="text"/>
              </td>
              <td>
                <input id="buscar" name="buscar" value="${i18n['etiquetas.buscar']}" type="submit" />
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <c:choose>
        <c:when test="${not empty librosForma.libros}">
          <table id="libros" class="tabla-datos">
            <thead>
              <tr>
                <th class="columna-checkbox"><input type="checkbox"/></th>
                <th class="columna-id">ID</th>
                <th>
                  ${i18n['libros.isbn']}
                </th>
                <th>
                  ${i18n['libros.titulo']}
                </th>
                <th>
                  ${i18n['libros.autor']}
                </th>
                <th>
                  ${i18n['libros.editorial']}
                </th>
                <th>
                  ${i18n['libros.categoria']}
                </th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${librosForma.libros}" var="libro">
                <tr class="alt">
                  <td class="columna-checkbox"><input  id="checkbox" type="checkbox" /></td>
                  <td class="columna-id">${libro.id}</td>
                  <td>${libro.isbn}</td>
                  <td>${libro.titulo}</td>
                  <td>
                    <my:tooltip-lista lista="${libro.autores}" tooltipItem="nombreCompleto" titulo="Autores" />
                  </td>
                  <td>
                    <my:tooltip-lista lista="${libro.editoriales}" tooltipItem="nombre" titulo="Editoriales" />
                  </td>
                  <td>
                    <my:tooltip-lista lista="${libro.categorias}" tooltipItem="nombre" titulo="Categorias" />
                  </td>
                  <td>
                    <a id="descarga" href="#">
                      <c:url var="url" value="/images/document-pdf-text.png"/>
                      <img src="${url}" alt="pdf.png" title="Descargar"/>
                    </a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:when>
        <c:otherwise>
          <div>
            ${i18n['libros.mensajes.noHayLibros']}
          </div>
        </c:otherwise>
      </c:choose>
      <div id="seleccion-invalida" class="ui-helper-hidden" title="${i18n['libros.mensaje.info']}">
        ${i18n['libros.mensaje.seleccionInvalida']}
      </div>
    </div>
  </body>
</html>
