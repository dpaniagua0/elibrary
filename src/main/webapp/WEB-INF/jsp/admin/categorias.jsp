<%-- 
    Document   : categorias
    Created on : Jun 5, 2011, 9:18:46 PM
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
    <link rel="stylesheet" href="${rutaContexto}/js/jquery/plugins/qtip/jquery.qtip.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/css/fg.menu.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/css/libros.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/js/jquery/themes/redmond/jquery-ui-redmond.css" type="text/css" /><script type="text/javascript" src="${rutaContexto}/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/jquery/plugins/qtip/jquery.qtip.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/jquery/ui/jquery-ui.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/fg.menu.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/utilidades.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/libros.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/navegacion.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/modal-form.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/combo-box.js"></script>
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
              <a id="agregar-categoria-link" href="#" title="${i18n['libros.agregarCategoria']}" onclick="UVAQ.eLibrary.libros.mostrarFormularioCategoria('nuevo')">
                <c:url var="url" value="/images/plus-circle-frame.png" />
                <img src="${url}" alt="[plus-circle-frama.png]" />
              </a>
            </td>
            <td>
              <a id="editar-categoria-link" href="#" title="${i18n['libros.editarCategoria']}" onclick="UVAQ.eLibrary.libros.mostrarFormularioCategoria('editar')">
                <c:url var="url" value="/images/pencil.png" />
                <img src="${url}" alt="[pencil.png]"/>
              </a>
            </td>
            <td>
              <a id="eliminar-categoria-link" href="#" title="${i18n['libros.eliminarCategoria']}">
                <c:url var="url" value="/images/minus-circle-frame.png" />
                <img src="${url}" alt="[minus-circle-frame.png]" />
              </a>
            </td>
          </tr>
        </table>
      </div>
      <div>
        <div id="info-categoria" title="Nueva categoria" class="ui-helper-hidden">
          <div>
            <c:url var="url" value="/admin/categorias" />
            <form action="${url}" method="POST">
              <table>
                <tbody>
                  <tr>
                    <td><label for="categorias">Categoria</label></td>
                    <td>
                      <input id="categorias" name="nombre" type="text" value="${categoriasForma.nombre}"/>
                      <input name="id_categoria" type="hidden"/>
                    </td>
                  </tr>
                </tbody>
              </table>
              <div id="borde"></div>
              <div class="panel-botones" id="categoria-botones">
                <button id="boton-nueva-categoria" name="accion" value="agregar" type="submit">Agregar</button>
                <button id="boton-cancelar" type="button">Cancelar</button>
              </div>
            </form>
          </div>
        </div>
        <div>
          <div id="edicion-categoria" title="Editar categoria" class="ui-helper-hidden">
            <div>
              <c:url var="url" value="/admin/categorias" />
              <form action="${url}" method="POST">
                <table>
                  <tbody>
                    <tr>
                      <td><label for="categorias">Categoria</label></td>
                      <td>
                        <input id="categorias" name="categorias" type="text"/>
                        <input name="ids_categorias" type="hidden"/>
                      </td>
                    </tr>
                  </tbody>
                </table>
                <div id="borde"></div>
                <div class="panel-botones" id="categoria-botones">
                  <button id="boton-editar-libro" class="ui-helper-hidden" name="accion" value="guardar-libro" type="submit">Guardar</button>
                  <button id="boton-cancelar" type="button">Cancelar</button>
                </div>
              </form>
            </div>
          </div>
          <c:choose>
            <c:when test="${not empty categoriasForma.categorias}">
              <table id="tabla-categorias" class="tabla-datos">
                <thead>
                  <tr>
                    <th class="columna-checkbox"><input type="checkbox"/></th>
                    <th class="columna-id">ID</th>
                    <th>
                      Categoria
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${categoriasForma.categorias}" var="categoria">
                    <tr class="alt">
                      <td class="columna-checkbox"><input  id="checkbox" type="checkbox" /></td>
                      <td class="columna-id">${categoria.id}</td>
                      <td>${categoria.nombre}</td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </c:when>
            <c:otherwise>
              <div>
              </div>
            </c:otherwise>
          </c:choose>
        </div>
        <div id="seleccion-invalida" class="ui-helper-hidden" title="${i18n['libros.mensaje.info']}">
          ${i18n['libros.mensaje.seleccionInvalida']}
        </div>
        <c:if test="${not empty categoriasForma.mensajes['exito-registro']}">
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
          <div id="exito-registro" title="${categoriasForma.mensajes['exito-registro'].resumen}">
            ${categoriasForma.mensajes['exito-registro'].detalle}
          </div>
        </c:if>
        <c:if test="${not empty categoriasForma.mensajes['error-registro']}">
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
          <div id="error-registro" title="${categoriasForma.mensajes['error-registro'].resumen}">
            ${categoriasForma.mensajes['error-registro'].detalle}
          </div>
        </c:if>
      </div>
    </div>
  </body>
</html>
