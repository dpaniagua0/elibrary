/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.edu.uvaq.elibrary.modelo.entidades.Autor;
import mx.edu.uvaq.elibrary.modelo.negocio.servicio.AutoresServicio;
import mx.edu.uvaq.elibrary.presentacion.Mensaje;
import org.apache.commons.lang.math.NumberUtils;

/**
 *
 * @author daniel
 */
public class CategoriasController extends AbstractController {
  
  private AutoresServicio autoresServicio;

  public void setAutoresServicio(AutoresServicio autoresServicio) {
    this.autoresServicio = autoresServicio;
  }

  @Override
  public void defaultAction() {
    listar();
  }

  public void listar() {
    List<Autor> autores = autoresServicio.getAutores();
    Map<String, Object> modelo = new HashMap<String, Object>();
    modelo.put("autores", autores);
    renderView("index", modelo);
  }

  public void crear() {
    Map<String, Object> modelo = new HashMap<String, Object>();
    modelo.put("autor", new Autor());
    renderView("create", modelo);
  }

  public void salvar() {
    Autor autor = new Autor();
    autor.setNombre(getRequest().getParameter("nombre"));
    autor.setApellidos(getRequest().getParameter("apellidos"));
    Long idAutor = NumberUtils.toLong(getRequest().getParameter("id"));
    if (idAutor > 0) {
      autor.setId(idAutor.intValue());
      if (autoresServicio.modificarAutor(autor)) {
        String mensajeAutorSalvado = String.format("El autor %s, ha sido modificado con éxito.", autor.getNombreCompleto());
        addMessage("autor-salvar-resultado", Mensaje.crearMensajeExito(null, mensajeAutorSalvado));
      } else {
        String mensajeErrorSalvar = String.format("No se pudo modificar al autor %s.", autor.getNombreCompleto());
        addMessage("autor-salvar-resultado", Mensaje.crearMensajeError(null, mensajeErrorSalvar));
      }
      listar();
    } else {
      if (autoresServicio.registrarAutor(autor)) {
        String mensajeAutorSalvado = String.format("El autor %s, ha sido registrado con éxito.", autor.getNombreCompleto());
        addMessage("autor-salvar-resultado", Mensaje.crearMensajeExito(null, mensajeAutorSalvado));
      } else {
        String mensajeErrorSalvar = String.format("No se pudo registrar al autor %s.", autor.getNombreCompleto());
        addMessage("autor-salvar-resultado", Mensaje.crearMensajeError(null, mensajeErrorSalvar));
      }
      crear();
    }
  }

  public void editar() {
    Long idAutor = Long.valueOf(getRequest().getParameter("id"));
    Autor autor = autoresServicio.getAutorPorId(idAutor);
    if (autor != null) {
      Map<String, Object> modelo = new HashMap<String, Object>();
      modelo.put("autor", autor);
      renderView("crear", modelo);
    }
  }

  public void eliminar() {
    String[] idsAutores = getRequest().getParameterValues("id");
    for(String idAutor : idsAutores) {
      Long id = Long.valueOf(idAutor);
      autoresServicio.borrarAutor(id);
    }
    String mensajeErrorSalvar = String.format("Los autores han sido eliminados.");
    addMessage("autor-eliminar-resultado", Mensaje.crearMensajeExito(null, mensajeErrorSalvar));
    listar();
  }
}
