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
import org.apache.commons.lang.math.NumberUtils;
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
    } else if ("crear".equals(accion)) {
      crearAutor();
    } else if ("salvar".equals(accion)) {
      salvarAutor();
    } else if ("editar".equals(accion)) {
      editarAutor();
    } else if ("eliminar".equals(accion)) {
      eliminarAutores();
    }
  }

  @Override
  public void ejecutarAccionDefecto() {
    listarAutores();
  }

  private void listarAutores() {
    List<Autor> autores = autoresServicio.getAutores();
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
    Long idAutor = NumberUtils.toLong(getRequest().getParameter("id"));
    if (idAutor > 0) {
      autor.setId(idAutor.intValue());
      if (autoresServicio.modificarAutor(autor)) {
        String mensajeAutorSalvado = String.format("El autor %s, ha sido modificado con éxito.", autor.getNombreCompleto());
        agregarMensaje("autor-salvar-resultado", Mensaje.crearMensajeExito(null, mensajeAutorSalvado));
      } else {
        String mensajeErrorSalvar = String.format("No se pudo modificar al autor %s.", autor.getNombreCompleto());
        agregarMensaje("autor-salvar-resultado", Mensaje.crearMensajeError(null, mensajeErrorSalvar));
      }
      listarAutores();
    } else {
      if (autoresServicio.registrarAutor(autor)) {
        String mensajeAutorSalvado = String.format("El autor %s, ha sido registrado con éxito.", autor.getNombreCompleto());
        agregarMensaje("autor-salvar-resultado", Mensaje.crearMensajeExito(null, mensajeAutorSalvado));
      } else {
        String mensajeErrorSalvar = String.format("No se pudo registrar al autor %s.", autor.getNombreCompleto());
        agregarMensaje("autor-salvar-resultado", Mensaje.crearMensajeError(null, mensajeErrorSalvar));
      }
      crearAutor();
    }
  }

  private void editarAutor() {
    Long idAutor = Long.valueOf(getRequest().getParameter("id"));
    Autor autor = autoresServicio.getAutorPorId(idAutor);
    if (autor != null) {
      Map<String, Object> modelo = new HashMap<String, Object>();
      modelo.put("autor", autor);
      desplegarVista(VISTA_CREAR_AUTOR, modelo);
    }
  }

  private void eliminarAutores() {
    String[] idsAutores = getRequest().getParameterValues("id");
    for(String idAutor : idsAutores) {
      Long id = Long.valueOf(idAutor);
      autoresServicio.borrarAutor(id);
    }
    String mensajeErrorSalvar = String.format("Los autores han sido eliminados.");
    agregarMensaje("autor-eliminar-resultado", Mensaje.crearMensajeExito(null, mensajeErrorSalvar));
    listarAutores();
  }
}
