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
//        $("info-libro").modal({
//          modal: true,
//          backdrop: true,
//          keyboard: true
//        });
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
    <div class="container-fluid">
      <div class="sidebar">
        <div class="well">
          <h5>${i18n['libros.acciones']}</h5>
          <ul>
            <li>
              <a id="agregar-libro-link" href="#" title="${i18n['libros.agregarLibro']}">
                ${i18n['libros.agregarLibro']}
                <c:url var="url" value="/images/book-plus.png" />
                <img src="${url}" alt="[book-plus.png]" />
              </a></li>
            <li>
              <a id="editar-libro-link" href="#" title="${i18n['libros.editarLibro']}">
                ${i18n['libros.editarLibro']}
                <c:url var="url" value="/images/book-pencil.png" />
                <img src="${url}" alt="[book-pencil.png]"/>
              </a>
            </li>
            <li>
              <a id="eliminar-libro-link" href="#" title="${i18n['libros.eliminarLibro']}">
                ${i18n['libros.eliminarLibro']}
                <c:url var="url" value="/images/book-minus.png" />
                <img src="${url}" alt="[book-minus.png]" />
              </a>
            </li>
          </ul>
        </div>
      </div>
      <div class="content">
        <!-- Main hero unit for a primary marketing message or call to action -->
        <div class="hero-unit">
          <h2>${i18n['libros.encabezado']}</h2>
          <div class="row" style="text-align: center;">
            <div class="span12">
              <form>
                <fieldset>
                  <div class="span14 clearfix">
                    <select class="small" name="criterio">
                      <option value="1">${i18n['libros.categoria']}</option>
                      <option value="2">${i18n['libros.titulo']}</option>
                      <option value="3">${i18n['libros.autor']}</option>
                      <option value="4">${i18n['libros.editorial']}</option>
                    </select>
                    <input class="xxlarge busqueda" id="busqueda" name="busqueda" type="text"/>
                    <input class="small"  name="buscar" value="${i18n['etiquetas.buscar']}" type="submit" />
                  </div>
                </fieldset>
              </form>    
<!--                  class="ui-widget ui-corner-all ui-helper-hidden" -->
              <div id="info-libro" style="text-align: center;" class="modal hide fade in">
                <!-- Ese frame se utiliza para que la carga de archivo se cargue en él y no se tenga que cambiar de archivo. -->
                <!--        <iframe id='target_upload' name='target_upload' src='' style="width: 0px; height: 0px"></iframe>-->
                <div id="info-libro-header-nuevo" class="modal-header"><a href='#' class='close'>×</a>Nuevo libro</div>
                <div id="info-libro-header-editar" class="modal-header"><a href='#' class='close'>×</a>Editar libro</div>
                <div class="modal-body">
                  <c:url var="url" value="/admin/controlador-carga-libro" />
                  <form action="${url}" method="POST" enctype="multipart/form-data" target="target_upload" >
                    <input name="id" type="hidden" />
                    <table class="tamano-completo">
                      <caption>* Los campos en negrita son requeridos</caption>
                      <tbody>
                        <tr>
                          <td><label for="isbn">ISBN</label></td>
                          <td><input id="isbn" name="isbn" type="text"/></td>
                          <td><label for="titulo" class="campo-requerido">Titulo</label></td>
                          <td><input id="titulo" name="titulo" type="text" class="tamano-completo"/></td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td><label for="fecha-publicacion">Fecha publicacion</label></td>
                          <td><input id="fecha-publicacion" name="fecha_publicacion" type="text"/></td>
                          <td><label for="archivo">Archivo</label></td>
                          <td><input id="archivo" name="archivo" type="file" class="tamano-completo" size="50"/></td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td><label for="autores">Autor(es)</label></td>
                          <td colspan="3">
                            <input id="autores" name="lista_autores" type="text" readonly="true" class="tamano-completo"/>
                            <input name="ids_autores" type="hidden"/>
                          </td>
                          <td><button class="autor"></button></td>
                        </tr>
                        <tr>
                          <td><label for="editoriales">Editorial(es)</label></td>
                          <td colspan="3">
                            <input id="editoriales" name="lista_editoriales" type="text" readonly="true" class="tamano-completo"/>
                            <input name="ids_editoriales" type="hidden"/>
                          </td>
                          <td><button class="editorial"></button></td>
                        </tr>
                        <tr>
                          <td><label for="categorias">Categoria(s)</label></td>
                          <td colspan="3">
                            <input id="categorias" name="lista_categorias" type="text" readonly="true" class="tamano-completo"/>
                            <input name="ids_categorias" type="hidden"/>
                          </td>
                          <td><button class="categoria"></button></td>
                        </tr>
                      </tbody>
                    </table>
                    <div class="panel-botones modal-footer">
                      <button id="boton-nuevo-libro" class="btn primary" name="accion" value="nuevo-libro" type="submit">Nuevo</button>
                      <button id="boton-editar-libro" class="btn primary" name="accion" value="guardar-libro" type="submit">Guardar</button>
                      <button id="boton-cancelar" class="btn error" type="button">Cancelar</button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
          <c:choose>
            <c:when test="${not empty librosForma.libros}">
              <table id="libros" class="tabla-datos bordered-table">
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
          <div id="panel-progreso-libro" class="ui-helper-hidden" title="Progreso operacion">
            <div>
              <div id="progreso-libro" />
              <div id="estatus-progreso-libro" style="text-align: center">
                (0%) Guardando libro...
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
