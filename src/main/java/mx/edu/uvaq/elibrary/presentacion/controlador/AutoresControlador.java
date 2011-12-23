/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion.controlador;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import mx.edu.uvaq.elibrary.modelo.entidades.Autor;
import mx.edu.uvaq.elibrary.modelo.negocio.servicio.AutoresServicio;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author daniel
 */
public class AutoresControlador extends AbstractControlador {

  private AutoresServicio autoresServicio;
  private String NOMBRE_FORMA = "autoresForma";
  private String VISTA_AUTORES = "vista-autores";

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void init() throws ServletException {
    autoresServicio = (AutoresServicio) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean("autorServicio");
  }

  @Override
  public void ejecutarAccion(String accion) {
    if ("listar".equals(accion)) {
      listarAutores();
    }
  }
  
  @Override
  public void ejecutarAccionDefecto() {
    listarAutores();
  }

  private void listarAutores() {
    List<Autor> autores = autoresServicio.encontrarAutores();
    Map<String, Object> modelo = new HashMap<String, Object>();
    modelo.put("autores", autores);
    desplegarVista(VISTA_AUTORES, modelo);
  }
}
