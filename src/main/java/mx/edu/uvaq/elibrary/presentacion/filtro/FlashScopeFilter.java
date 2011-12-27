/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion.filtro;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import mx.edu.uvaq.elibrary.presentacion.FlashScope;

/**
 *
 * @author daniel
 */
public class FlashScopeFilter implements Filter {

  private static final String FLASH_SESSION_KEY = "FLASH_SESSION_KEY";

  @SuppressWarnings("unchecked")
  public void doFilter(ServletRequest request, ServletResponse response,
          FilterChain chain) throws IOException, ServletException {

    //reinstate any flash scoped params from the users session
    //and clear the session
    if (request instanceof HttpServletRequest) {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      HttpSession session = httpRequest.getSession(false);
      if (session != null) {
        Map<String, Object> flashParams = (Map<String, Object>) session.getAttribute(FLASH_SESSION_KEY);
        if (flashParams != null) {
          for (Map.Entry<String, Object> flashEntry : flashParams.entrySet()) {
            request.setAttribute(flashEntry.getKey(), flashEntry.getValue());
          }
          session.removeAttribute(FLASH_SESSION_KEY);
        }
      }
    }

    //process the chain
    chain.doFilter(request, response);

    //store any flash scoped params in the user's session for the
    //next request
    if (request instanceof HttpServletRequest) {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      Map<String, Object> flashParams = new HashMap();
      Enumeration e = httpRequest.getAttributeNames();
      while (e.hasMoreElements()) {
        String paramName = (String) e.nextElement();
        if (paramName.startsWith(FlashScope.FLASH_ATTRIBUTE_PREFIX)) {
          Object value = request.getAttribute(paramName);
          paramName = paramName.substring(FlashScope.FLASH_ATTRIBUTE_PREFIX.length());
          flashParams.put(paramName, value);
        }
      }
      if (flashParams.size() > 0) {
        HttpSession session = httpRequest.getSession(false);
        session.setAttribute(FLASH_SESSION_KEY, flashParams);
      }
    }
  }

  public void init(FilterConfig filterConfig) throws ServletException {
    //no-op
  }

  public void destroy() {
    //no-op
  }
}