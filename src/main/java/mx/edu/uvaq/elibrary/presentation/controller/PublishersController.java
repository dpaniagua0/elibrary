/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.controller;

import mx.edu.uvaq.elibrary.domain.Publisher;
import mx.edu.uvaq.elibrary.model.business.service.PublisherService;
import mx.edu.uvaq.elibrary.presentation.Message;
import org.apache.commons.lang.math.NumberUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daniel
 */
public class PublishersController extends AbstractController {

  private PublisherService publisherService;

  public void setPublisherService(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @Override
  public void defaultAction() {
    listar();
  }

  public void listar() {
    List<Publisher> publishers = publisherService.getPublishers();
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("editoriales", publishers);
    renderView("index", model);
  }

  public void crear() {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("editorial", new Publisher());
    renderView("create", model);
  }

  public void salvar() {
    Publisher publisher = new Publisher();
    publisher.setName(getRequest().getParameter("nombre"));
    Long publisherId = NumberUtils.toLong(getRequest().getParameter("id"));
    if (publisherId > 0) {
      publisher.setId(publisherId.intValue());
      if (publisherService.editPublisher(publisher)) {
        String message = String.format("El editorial %s, ha sido modificado con éxito.", publisher.getName());
        addMessage("editorial-salvar-resultado", Message.createSuccessMessage(null, message));
      } else {
        String message = String.format("No se pudo modificar al editorial %s.", publisher.getName());
        addMessage("editorial-salvar-resultado", Message.createErrorMessage(null, message));
      }
      listar();
    } else {
      if (publisherService.registerPublisher(publisher)) {
        String message = String.format("El editorial %s, ha sido registrado con éxito.", publisher.getName());
        addMessage("editorial-salvar-resultado", Message.createSuccessMessage(null, message));
      } else {
        String message = String.format("No se pudo registrar al editorial %s.", publisher.getName());
        addMessage("editorial-salvar-resultado", Message.createErrorMessage(null, message));
      }
      crear();
    }
  }

  public void editar() {
    Long publisherId = Long.valueOf(getRequest().getParameter("id"));
    Publisher publisher = publisherService.getPublisherById(publisherId);
    if (publisher != null) {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("editorial", publisher);
      renderView("create", model);
    }
  }

  public void eliminar() {
    String[] publishersIds = getRequest().getParameterValues("id");
    for (String publisherId : publishersIds) {
      Long id = Long.valueOf(publisherId);
      publisherService.removePublisher(id);
    }
    String message = String.format("Los editoriales han sido eliminados.");
    addMessage("editorial-eliminar-resultado", Message.createSuccessMessage(null, message));
    listar();
  }
}
