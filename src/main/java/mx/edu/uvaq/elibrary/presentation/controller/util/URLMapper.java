/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.controller.util;

import mx.edu.uvaq.elibrary.ApplicationConstants;
import mx.edu.uvaq.elibrary.presentation.exception.WebResourceNotFoundException;
import mx.edu.uvaq.elibrary.web.SpringWebApplicationContext;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author arcesino
 */
public class URLMapper {

  public static URLMapping mapRequest(HttpServletRequest request) {
    String requestURL = getRequestURL(request);
    String action = null;
    String controllerURL = null;
    if (existControllerForURL(requestURL)) {
      controllerURL = requestURL;
      String actionParameter = request.getParameter(ApplicationConstants.ACTION_PARAMETER_NAME);
      action = StringUtils.isNotBlank(actionParameter) ? actionParameter : "defaultAction";
    } else {
      controllerURL = request.getServletPath();
      action = getFixedPathInfo(request);
      if (action.contains("/")) {
        throw new WebResourceNotFoundException();
      }
    }
    return new URLMapping(controllerURL, action);
  }

  private static String getRequestURL(HttpServletRequest request) {
    String servletPath = request.getServletPath();
    String pathInfo = request.getPathInfo();
    String requestURL = StringUtils.defaultString(servletPath) + StringUtils.defaultString(pathInfo);
    if (requestURL.endsWith("/")) {
      requestURL = requestURL.substring(0, requestURL.length() - 1);
    }
    return requestURL;
  }

  private static boolean existControllerForURL(String controllerURL) {
    String controllerId = controllerURL.replace('/', '_');
    return SpringWebApplicationContext.containsBean(controllerId);
  }

  private static String getFixedPathInfo(HttpServletRequest request) {
    String pathInfo = request.getPathInfo();
    if (StringUtils.isNotEmpty(pathInfo)) {
      pathInfo = pathInfo.substring(1);
      if (pathInfo.endsWith("/")) {
        pathInfo = pathInfo.substring(0, pathInfo.length() - 1);
      }
    }
    return pathInfo;
  }
}
