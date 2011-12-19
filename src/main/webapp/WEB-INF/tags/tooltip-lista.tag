<%-- 
    Document   : tooltip-lista
    Created on : Jun 4, 2011, 4:11:35 PM
    Author     : daniel
--%>

<%@tag description="Crea una vista de una lista con soporte para tooltip" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="lista" required="true" type="java.util.Collection" description="Lista que se visualizara con soporte de tooltip."%>
<%@attribute name="titulo" description="Titulo del tooltip de la lista."%>
<%@attribute name="tooltipItem" description="Propiedad de los objetos de la lista que se enlista en el tooltip."%>

<%-- any content can be specified here e.g.: --%>
<c:set var="nombreContexto" value="${pageContext.servletContext.contextPath}" />
<c:choose>
  <c:when test="${fn:length(lista) eq 1}">
    <span>${lista[0][tooltipItem]}</span>
  </c:when>
  <c:when test="${fn:length(lista) gt 1}">
    <div class="tooltip-trigger">
      ${lista[0][tooltipItem]}
      <img src="${nombreContexto}/images/eye.png" alt="eye.png"/>
    </div>
    <div class="ui-helper-hidden" title="${titulo}">
      <c:forEach items="${lista}" var="item" begin="1">
        <div>${item[tooltipItem]}</div>
      </c:forEach>
    </div>
  </c:when>
</c:choose>