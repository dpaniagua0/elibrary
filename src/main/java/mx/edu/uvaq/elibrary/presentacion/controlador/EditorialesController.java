/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.edu.uvaq.elibrary.modelo.entidades.Editorial;
import mx.edu.uvaq.elibrary.modelo.negocio.servicio.EditorialesServicio;
import mx.edu.uvaq.elibrary.presentacion.Mensaje;
import org.apache.commons.lang.math.NumberUtils;

/**
 *
 * @author daniel
 */
public class EditorialesController extends AbstractController {
  
  private EditorialesServicio editorialesServicio;

  public void seteditorialesServicio(EditorialesServicio editorialesServicio) {
    this.editorialesServicio = editorialesServicio;
  }

  @Override
  public void defaultAction() {
    listar();
  }

  public void listar() {
    List<Editorial> editoriales = editorialesServicio.getEditoriales();
    Map<String, Object> modelo = new HashMap<String, Object>();
    modelo.put("editoriales", editoriales);
    renderView("index", modelo);
  }

  public void crear() {
    Map<String, Object> modelo = new HashMap<String, Object>();
    modelo.put("editorial", new Editorial());
    renderView("create", modelo);
  }

  public void salvar() {
    Editorial editorial = new Editorial();
    editorial.setNombre(getRequest().getParameter("nombre"));
    Long ideditorial = NumberUtils.toLong(getRequest().getParameter("id"));
    if (ideditorial > 0) {
      editorial.setId(ideditorial.intValue());
      if (editorialesServicio.modificarEditorial(editorial)) {
        String mensajeeditorialSalvado = String.format("El editorial %s, ha sido modificado con éxito.", editorial.getNombre());
        addMessage("editorial-salvar-resultado", Mensaje.crearMensajeExito(null, mensajeeditorialSalvado));
      } else {
        String mensajeErrorSalvar = String.format("No se pudo modificar al editorial %s.", editorial.getNombre());
        addMessage("editorial-salvar-resultado", Mensaje.crearMensajeError(null, mensajeErrorSalvar));
      }
      listar();
    } else {
      if (editorialesServicio.registrarEditorial(editorial)) {
        String mensajeeditorialSalvado = String.format("El editorial %s, ha sido registrado con éxito.", editorial.getNombre());
        addMessage("editorial-salvar-resultado", Mensaje.crearMensajeExito(null, mensajeeditorialSalvado));
      } else {
        String mensajeErrorSalvar = String.format("No se pudo registrar al editorial %s.", editorial.getNombre());
        addMessage("editorial-salvar-resultado", Mensaje.crearMensajeError(null, mensajeErrorSalvar));
      }
      crear();
    }
  }

  public void editar() {
    Long ideditorial = Long.valueOf(getRequest().getParameter("id"));
    Editorial editorial = editorialesServicio.getEditorialPorId(ideditorial);
    if (editorial != null) {
      Map<String, Object> modelo = new HashMap<String, Object>();
      modelo.put("editorial", editorial);
      renderView("crear", modelo);
    }
  }

  public void eliminar() {
    String[] idseditoriales = getRequest().getParameterValues("id");
    for(String ideditorial : idseditoriales) {
      Long id = Long.valueOf(ideditorial);
      editorialesServicio.borrarEditorial(id);
    }
    String mensajeErrorSalvar = String.format("Los editoriales han sido eliminados.");
    addMessage("editorial-eliminar-resultado", Mensaje.crearMensajeExito(null, mensajeErrorSalvar));
    listar();
  }
}
