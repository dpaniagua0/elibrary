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
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href='${rutaContexto}/images/logo.png' rel='shortcut icon' />
    <link rel="stylesheet" href="${rutaContexto}/css/global.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/css/usuarios.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/css/libros.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/js/jquery/plugins/qtip/jquery.qtip.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/css/fg.menu.css" type="text/css" />
<!--    <link rel="stylesheet" href="${rutaContexto}/js/jquery/themes/redmond/jquery-ui-redmond.css" type="text/css" />-->
    <link rel="stylesheet" href="${rutaContexto}/js/jquery/themes/start/jquery-ui-start.css" type="text/css" /><link rel="stylesheet" href="${rutaContexto}/css/global.css" type="text/css" />
    <script type="text/javascript" src="${rutaContexto}/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/jquery/plugins/qtip/jquery.qtip.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/jquery/ui/jquery-ui.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/fg.menu.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/utilidades.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/libros.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/navegacion.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/modal-form.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/combo-box.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/bootstrap-modal.js"></script>
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
    <jsp:include page="/WEB-INF/jspf/encabezadoAdministracion.jspf">
      <jsp:param name="encabezado" value="${encabezadoUsuarios}" />
    </jsp:include>
    <div id="cuerpo">
      <form id="formulario-busqueda" action="/inicio">
        <table>
          <tbody>
            <tr>
              <td>
                <select name="criterio">
                  <option value="1">${i18n['etiquetas.nombre']}</option>
                  <option value="2">${i18n['etiquetas.apellidos']}</option>
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
      <div id="acciones">
        <table>
          <tr>
            <td>${i18n['libros.acciones']}:</td>
            <td>
              <a id="agregar-autor-link" href="#" title="${i18n['libros.agregarAutor']}">
                <c:url var="url" value="/images/plus-circle-frame.png" />
                <img src="${url}" alt="[plus-circle-frama.png]" />
              </a>
            </td>
            <td>
              <a id="editar-autor-link" href="#" title="${i18n['libros.editarAutor']}">
                <c:url var="url" value="/images/pencil.png" />
                <img src="${url}" alt="[pencil.png]"/>
              </a>
            </td>
            <td>
              <a id="eliminar-autor-link" href="#" title="${i18n['libros.eliminarAutor']}">
                <c:url var="url" value="/images/minus-circle-frame.png" />
                <img src="${url}" alt="[minus-circle-frame.png]" />
              </a>
            </td>
          </tr>
        </table>
      </div>
      <div>
        <div id="info-autores" title="Nuevo autor" class="ui-helper-hidden">
          <c:url var="url" value="/admin/autores" />
          <form action="${url}">
            <table id="autores">
              <tbody>
                <tr>
                  <td><label for="autor">Autor</label></td>
                  <td>
                    <input id="autor" name="nombre" type="text" value="${autoresForma.nombre}"/>
                    <input name="id_autor" type="hidden"/>
                  </td>
                </tr>
                <tr>
                  <td><label for="apellidos">Apellidos</label></td>
                  <td>
                    <input id="apellidos" name="apellidos" type="text" value="${autoresForma.apellidos}"/>
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
        <div>
          <div id="edicion-autores" title="Editar autor" class="ui-helper-hidden">
            <div>
              <c:url var="url" value="/inicio" />
              <form action="${url}">
                <table id="autores">
                  <tbody>
                    <tr>
                      <td><label for="autor">Autor</label></td>
                      <td>
                        <input id="autor" name="autor" type="text"/>
                        <input name="id_autor" type="hidden"/>
                      </td>
                    </tr>
                    <tr>
                      <td><label for="apellidos">Apellidos</label></td>
                      <td>
                        <input id="apellidos" name="apellidos" type="text" value="${autoresForma.apellidos}"/>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <div id="borde"></div>
                <div class="panel-botones" id="autores-botones">
                  <button id="boton-editar-autor" name="accion" value="nuevo-libro" type="submit">Guardar</button>
                  <button id="boton-cancelar" type="button">Cancelar</button>
                </div>
              </form>
            </div>
          </div>
          <c:choose>
            <c:when test="${not empty autoresForma.autores}">
              <table id="tabla-autores" class="tabla-datos">
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
                  <c:forEach items="${autoresForma.autores}" var="autor">
                    <tr class="alt">
                      <td class="columna-checkbox"><input  id="checkbox" type="checkbox" /></td>
                      <td class="columna-id">${autor.id}</td>
                      <td>${autor.nombre}</td>
                      <td>${autor.apellidos}</td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </c:when>
            <c:otherwise>
              <div>
                ${i18n['libros.mensajes.noHayAutores']}
              </div>
            </c:otherwise>
          </c:choose>
        </div>
        <div id="seleccion-invalida" class="ui-helper-hidden" title="${i18n['libros.mensaje.info']}">
          ${i18n['libros.mensaje.seleccionInvalida']}
        </div>
        <c:if test="${not empty autoresForma.mensajes['exito-registro']}">
          <script type="text/javascript">
            $(function() {
              $( "#exito-registro" ).dialog({
                modal: true,
                buttons: {
                  Ok: function() {
                    $( this ).dialog( "close" );
                  }
                }
              });
            });
          </script>
          <div id="exito-registro" title="${autoresForma.mensajes['exito-registro'].resumen}">
            ${autoresForma.mensajes['exito-registro'].detalle}
          </div>
        </c:if>
        <c:if test="${not empty autoresForma.mensajes['error-registro']}">
          <script type="text/javascript">
            $(function() {
              $( "#error-registro" ).dialog({
                modal: true,
                buttons: {
                  Ok: function() {
                    $( this ).dialog( "close" );
                  }
                }
              });
            });
          </script>
          <div id="error-registro" title="${autoresForma.mensajes['error-registro'].resumen}">
            ${autoresForma.mensajes['error-registro'].detalle}
          </div>
        </c:if>
      </div>
    </div>
  </body>
</html>
