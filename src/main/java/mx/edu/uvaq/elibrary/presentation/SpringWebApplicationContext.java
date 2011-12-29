/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation;

import javax.servlet.ServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author arcesino
 */
public class SpringWebApplicationContext {

  private static WebApplicationContext webApplicationContext;

  private SpringWebApplicationContext() {
  }

  public static void initialize(ServletContext servletContext) {
    if (webApplicationContext != null) {
      String initializationError = SpringWebApplicationContext.class.getName() + "has been already initialized!";
      throw new IllegalStateException(initializationError);
    }
    webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
  }
  
  public static Object getBean(String id) {
    return webApplicationContext.getBean(id);
  }
  
  public static <T> T getBean(String id, Class<T> type) {
    return webApplicationContext.getBean(id, type);
  }
  
  public static boolean containsBean(String id) {
    return webApplicationContext.containsBean(id);
  }
}
