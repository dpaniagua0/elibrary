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
    <link rel="stylesheet" href="${rutaContexto}/css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="${rutaContexto}/css/login.css" type="text/css" />
    <script type="text/javascript" src="${rutacontexto}/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/jquery/ui/jquery-ui.js"></script>
    <link rel="stylesheet" href="${rutaContexto}/js/jquery/themes/start/jquery-ui-start.css" type="text/css" />
    <script type="text/javascript" src="${rutaContexto}/js/mensajes.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/bootstrap/bootstrap-alerts.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/bootstrap/bootstrap-buttons.js"></script>
    <script type="text/javascript" src="${rutaContexto}/js/bootstrap/bootstrap-modal.js"></script>
    <!--    <script type="text/javascript">
          $(UVAQ.eLibrary.mostrarMensaje(formaRegistro));
        </script> -->
    <title>${i18n['login.titulo']}</title>
  </head>
  <body>

    <div class="topbar">
      <div class="fill">
        <div class="container">
          <c:url var="url" value="/inicio" />
          <a href="${url}" class="logo brand">
            Elibrary
          </a>
          <form action="j_security_check" method="POST"  class="pull-right">
            <input id="username" class="input-medium " name="j_username" type="text" value="${param['j_username']}" placeholder="${i18n['etiquetas.correoElectronico']}"/>
            <input id="password" class="input-medium" name="j_password" type="password" placeholder="${i18n['etiquetas.contraseña']}"/>
            <button class="btn primary" type="submit">${i18n['login.etiquetas.entrar']}</button>
          </form>
        </div>
      </div>
      <c:choose>
        <c:when test="${param['j_username'] ne null or param['j_password'] ne null}">
          <div id='mensaje-error' data-alert='alert' acceskey='true' class='alert-message warning'>
            <a href="#" class="close">×</a>
            ${i18n['error.loginIncorrecto']}
          </div>
        </c:when>
        <c:otherwise>&nbsp;</c:otherwise>
      </c:choose>
    </div>

    <div class="container">
      <div class="content">
        <div class="page-header">
          <a href="${url}" class="logo brand">
            <img src="${rutaContexto}/images/elibrary.png"/>
          </a>
        </div>
        <div class="row">
          <div class="span12">
            <c:url var="url" value="/registro" />
            <h2>
              ${i18n['login.etiquetas.registrate']}
            </h2>
            <form id="forma-registro" action="${url}" method="POST">
              <fieldset>
                <!--                <h2>
                ${i18n['login.etiquetas.registrate']}
              </h2>-->
                <div class="clearfix">
                  <label class="xlarge" for="nombre">${i18n['etiquetas.nombre']}:</label>
                  <div class="input">
                    <input name="nombre" id="nombre" class="xlarge" value="${formaRegsitro.nombre}" type="text" />
                  </div>
                </div>
                <div class="clearfix">
                  <label class="xlarge" for="apellidos">${i18n['etiquetas.apellidos']}:</label>
                  <div class="input">
                    <input class="xlarge" name="apellidos" id="apellidos" type="text" value="${formaRegsitro.apellidos}" />
                  </div>
                </div>
                <div class="clearfix">
                  <label class="xlarge" for="correoElectronico">${i18n['login.etiquetas.correoElectronico']}:</label>
                  <div class="input">
                    <input class="xlarge" name="correoElectronico" id="correo-electronico" type="text" value="${formaRegsitro.correoElectronico}" />
                  </div>
                </div>
                <div class="clearfix">
                  <label class="xlarge" for="contraseña">${i18n['etiquetas.contraseña']}:</label>
                  <div class="input">
                    <input class="xlarge" name="password" id="contrasena" type="password" value="${formaRegsitro.contrasena}" />
                  </div>
                </div>
                <div class="clearfix">
                  <div class="button-succes">
                    <input class="btn success" type="submit" value="${i18n['login.etiquetas.registrar']}"/>
                  </div>
                </div>
              </fieldset>
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
        </div>
      </div>
    </div>
  </body>
</html>