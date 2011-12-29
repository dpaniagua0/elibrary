/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import mx.edu.uvaq.elibrary.presentacion.SpringWebApplicationContext;

/**
 *
 * @author arcesino
 */
public class SpringWebApplicationContextListener implements ServletContextListener {

  public void contextInitialized(ServletContextEvent sce) {
    SpringWebApplicationContext.initialize(sce.getServletContext());
  }

  public void contextDestroyed(ServletContextEvent sce) { }
}
