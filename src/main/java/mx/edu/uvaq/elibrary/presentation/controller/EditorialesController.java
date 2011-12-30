/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.edu.uvaq.elibrary.domain.Publisher;
import mx.edu.uvaq.elibrary.model.business.service.PublisherService;
import mx.edu.uvaq.elibrary.presentation.Mensaje;
import org.apache.commons.lang.math.NumberUtils;

/**
 *
 * @author daniel
 */
public class EditorialesController extends AbstractController {
  
  private PublisherService publisherService;

  public void seteditorialesServicio(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @Override
  public void defaultAction() {
    listar();
  }

  public void listar() {
    List<Publisher> editoriales = publisherService.getPublishers();
    Map<String, Object> modelo = new HashMap<String, Object>();
    modelo.put("editoriales", editoriales);
    renderView("index", modelo);
  }

  public void crear() {
    Map<String, Object> modelo = new HashMap<String, Object>();
    modelo.put("editorial", new Publisher());
    renderView("create", modelo);
  }

  public void salvar() {
    Publisher editorial = new Publisher();
    editorial.setName(getRequest().getParameter("nombre"));
    Long ideditorial = NumberUtils.toLong(getRequest().getParameter("id"));
    if (ideditorial > 0) {
      editorial.setId(ideditorial.intValue());
      if (publisherService.editPublisher(editorial)) {
        String mensajeEditorialSalvado = String.format("El editorial %s, ha sido modificado con éxito.", editorial.getName());
        addMessage("editorial-salvar-resultado", Mensaje.crearMensajeExito(null, mensajeEditorialSalvado));
      } else {
        String mensajeErrorSalvar = String.format("No se pudo modificar al editorial %s.", editorial.getName());
        addMessage("editorial-salvar-resultado", Mensaje.crearMensajeError(null, mensajeErrorSalvar));
      }
      listar();
    } else {
      if (publisherService.registerPublisher(editorial)) {
        String mensajeEditorialSalvado = String.format("El editorial %s, ha sido registrado con éxito.", editorial.getName());
        addMessage("editorial-salvar-resultado", Mensaje.crearMensajeExito(null, mensajeEditorialSalvado));
      } else {
        String mensajeErrorSalvar = String.format("No se pudo registrar al editorial %s.", editorial.getName());
        addMessage("editorial-salvar-resultado", Mensaje.crearMensajeError(null, mensajeErrorSalvar));
      }
      crear();
    }
  }

  public void editar() {
    Long ideditorial = Long.valueOf(getRequest().getParameter("id"));
    Publisher editorial = publisherService.getPublisherById(ideditorial);
    if (editorial != null) {
      Map<String, Object> modelo = new HashMap<String, Object>();
      modelo.put("editorial", editorial);
      renderView("create", modelo);
    }
  }

  public void eliminar() {
    String[] idsEditoriales = getRequest().getParameterValues("id");
    for(String idEditorial : idsEditoriales) {
      Long id = Long.valueOf(idEditorial);
      publisherService.removePublisher(id);
    }
    String mensajeErrorSalvar = String.format("Los editoriales han sido eliminados.");
    addMessage("editorial-eliminar-resultado", Mensaje.crearMensajeExito(null, mensajeErrorSalvar));
    listar();
  }
}
