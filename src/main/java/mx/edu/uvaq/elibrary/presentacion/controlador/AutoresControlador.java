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
import mx.edu.uvaq.elibrary.presentacion.Mensaje;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author daniel
 */
public class AutoresControlador extends AbstractControlador {

  private AutoresServicio autoresServicio;
  private final String NOMBRE_FORMA = "autoresForma";
  private final String VISTA_AUTORES = "vista-autores";
  private final String VISTA_CREAR_AUTOR = "vista-crear-autor";

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
    } else if ("crear".equals(accion)){
      crearAutor();
    } else if ("salvar".equals(accion)) {
      salvarAutor();
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

  private void crearAutor() {
    Map<String, Object> modelo = new HashMap<String, Object>();
    modelo.put("autor", new Autor());
    desplegarVista(VISTA_CREAR_AUTOR, modelo);
  }

  private void salvarAutor() {
    Autor autor = new Autor();
    autor.setNombre(getRequest().getParameter("nombre"));
    autor.setApellidos(getRequest().getParameter("apellidos"));
    if (autoresServicio.registrarAutor(autor)) {
      String mensajeAutorSalvado = String.format("El autor %s, ha sido guardado con éxito.", autor.getNombreCompleto());
      agregarMensaje("autor-salvar-resultado", Mensaje.crearMensajeExito(null, mensajeAutorSalvado));
    } else {
      String mensajeErrorSalvar = String.format("No se pudo guaradar al autor %s.", autor.getNombreCompleto());
      agregarMensaje("autor-salvar-resultado", Mensaje.crearMensajeError(null, mensajeErrorSalvar));
    }
    crearAutor();
  }
}
