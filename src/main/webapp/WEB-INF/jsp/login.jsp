<%--
    Document   : usuario
    Created on : Apr 4, 2011, 11:18:19 AM
    Author     : daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<fmt:setBundle basename="mx.edu.uvaq.elibrary.i18n.MensajesELibrary" var="bundle"/>
<c:set var="rutaContexto" value="${pageContext.servletContext.contextPath}" />
<c:set var="i18n" value="${bundle.resourceBundle}"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href='${rutaContexto}/images/logo.png' rel='shortcut icon' />
    <link rel="stylesheet" href="${rutaContexto}/css/global.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/css/login.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/js/jquery/themes/redmond/jquery-ui-redmond.css" type="text/css" />  <script type="text/javascript" src="${rutaContexto}/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/jquery/ui/jquery-ui.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/mensajes.js"></script>
    <!--    <script type="text/javascript">
          $(UVAQ.eLibrary.mostrarMensaje(formaRegistro));
        </script> -->
    <title>${i18n['login.titulo']}</title>
  </head>
  <body>
    <div class="encabezado">
      <form class="forma-login" action="j_security_check" method="POST">
        <c:url var="url" value="/inicio" />
        <a href="${url}" class="logo">
          <img src="${rutaContexto}/images/elibrary.png" alt="logo.png" />
        </a>
        <%--<span class="texto-encabezado"><fmt:message key="login.encabezado" /></span>--%>
        <table>
          <tbody>
            <tr>
              <td>
                <label for="correo-electronico">${i18n['etiquetas.correoElectronico']}:</label>
              </td>
              <td>
                <label for="contrasena">${i18n['etiquetas.contraseña']}:</label>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>
                <input id="correo-electronico" name="j_username" type="text" value="${param['j_username']}" />
              </td>
              <td>
                <input id="contrasena" name="j_password" type="password" />
              </td>
              <td>
                <input class="login" type="submit" value="${i18n['login.etiquetas.entrar']}" />
              </td>
            </tr>
            <tr>
              <td class="mensaje-error" colspan="3">
                <c:choose>
                  <c:when test="${param['j_username'] ne null or param['j_password'] ne null}">
                    <label>
                      ${i18n['error.loginIncorrecto']}
                    </label>
                  </c:when>
                  <c:otherwise>&nbsp;</c:otherwise>
                </c:choose>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
    </div>
    <div id="cuerpo">
      <c:url var="url" value="/registro" />
      <form id="forma-registro" action="${url}" method="POST">
        <table>
          <tbody>
            <tr>
              <td colspan="2">
                <h2>
                  ${i18n['login.etiquetas.nuevo']}
                </h2>
                <h4>
                  ${i18n['login.etiquetas.registrate']}
                </h4>
              </td>
            </tr>
            <tr>
              <td>
                <label for="nombre">${i18n['etiquetas.nombre']}:</label>
              </td>
              <td>
                <input name="nombre" id="nombre"  value="${formaRegsitro.nombre}" type="text" />
              </td>
            </tr>
            <tr>
              <td>
                <label for="apellidos">${i18n['etiquetas.apellidos']}:</label>
              </td>
              <td>
                <input name="apellidos" id="apellidos" type="text" value="${formaRegsitro.apellidos}" />
              </td>
            </tr>
            <tr>
              <td>
                <label for="correoElectronico">${i18n['login.etiquetas.correoElectronico']}</label>
              </td>
              <td>
                <input name="correoElectronico" id="correo-electronico" type="text" value="${formaRegsitro.correoElectronico}" />
              </td>
            </tr>
            <tr>
              <td>
                <label for="contraseña">${i18n['etiquetas.contraseña']}:</label>
              </td>
              <td>
                <input name="password" id="contrasena" type="password" value="${formaRegsitro.contrasena}" />
              </td>
            </tr>
            <tr>
              <td colspan="2">
                <input class="accept" type="submit" value="${i18n['login.etiquetas.registrar']}" />
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <c:if test="${not empty formaRegistro.mensajes['exito-registro']}">
        <script type="text/javascript">
          $(function() {
            $( "#exito-registro" ).dialog({
              modal: true,
              hide: "explode",
              buttons: {
                Ok: function() {
                  $( this ).dialog( "close" );
                }
              }
            });
          });
        </script>
        <div id="exito-registro" title="${formaRegistro.mensajes['exito-registro'].resumen}">
          ${formaRegistro.mensajes['exito-registro'].detalle}
        </div>
      </c:if>
      <c:if test="${not empty formaRegistro.mensajes['exito-activacion']}">
        <script type="text/javascript">
          $(function() {
            $( "#exito-activacion" ).dialog({
              modal: true,
              hide: "explode",
              buttons: {
                Ok: function() {
                  $( this ).dialog( "close" );
                }
              }
            });
          });
        </script>
        <div id="exito-activacion" title="${formaRegistro.mensajes['exito-activacion'].resumen}">
          ${formaRegistro.mensajes['exito-activacion'].detalle}
        </div>
      </c:if>
    </div>
  </body>
</html>