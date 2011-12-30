/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.web;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author daniel
 */
public class FlashScope {

  public static final String FLASH_ATTRIBUTE_PREFIX = "scope.flash.";
  private HttpServletRequest request;

  public FlashScope(HttpServletRequest request) {
    this.request = request;
  }

  public Object getAttribute(String key) {
    return request.getAttribute(key);
  }

  public void setAttribute(String key, Object object) {
    if (StringUtils.isNotBlank(key)) {
      String flashKey = FLASH_ATTRIBUTE_PREFIX + key;
      request.setAttribute(flashKey, object);
    } else {
      throw new IllegalArgumentException("key");
    }
  }

  public void removeAttribute(String key) {
    String flashKey = FLASH_ATTRIBUTE_PREFIX + key;
    request.removeAttribute(flashKey);
  }
}
