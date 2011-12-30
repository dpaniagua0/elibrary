/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.controller;

import mx.edu.uvaq.elibrary.domain.Author;
import mx.edu.uvaq.elibrary.model.business.service.AuthorService;
import mx.edu.uvaq.elibrary.presentation.Message;
import org.apache.commons.lang.math.NumberUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daniel
 */
public class CategoriesController extends AbstractController {

  private AuthorService authorService;

  public void setAuthorService(AuthorService authorService) {
    this.authorService = authorService;
  }

  @Override
  public void defaultAction() {
    listar();
  }

  public void listar() {
    List<Author> authors = authorService.getAuthors();
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("autores", authors);
    renderView("index", model);
  }

  public void crear() {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("autor", new Author());
    renderView("create", model);
  }

  public void salvar() {
    Author author = new Author();
    author.setName(getRequest().getParameter("nombre"));
    author.setLastName(getRequest().getParameter("apellidos"));
    Long authorId = NumberUtils.toLong(getRequest().getParameter("id"));
    if (authorId > 0) {
      author.setId(authorId.intValue());
      if (authorService.editAuthor(author)) {
        String authorSavedMessage = String.format("El autor %s, ha sido modificado con éxito.", author.getFullName());
        addMessage("autor-salvar-resultado", Message.createSuccessMessage(null, authorSavedMessage));
      } else {
        String saveErrorMessage = String.format("No se pudo modificar al autor %s.", author.getFullName());
        addMessage("autor-salvar-resultado", Message.createErrorMessage(null, saveErrorMessage));
      }
      listar();
    } else {
      if (authorService.registerAuthor(author)) {
        String authorSavedMessage = String.format("El autor %s, ha sido registrado con éxito.", author.getFullName());
        addMessage("autor-salvar-resultado", Message.createSuccessMessage(null, authorSavedMessage));
      } else {
        String saveErrorMessage = String.format("No se pudo registrar al autor %s.", author.getFullName());
        addMessage("autor-salvar-resultado", Message.createErrorMessage(null, saveErrorMessage));
      }
      crear();
    }
  }

  public void editar() {
    Long authorId = Long.valueOf(getRequest().getParameter("id"));
    Author author = authorService.getAuthorById(authorId);
    if (author != null) {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("autor", author);
      renderView("crear", model);
    }
  }

  public void eliminar() {
    String[] authorsIds = getRequest().getParameterValues("id");
    for (String idAutor : authorsIds) {
      Long id = Long.valueOf(idAutor);
      authorService.removeAuthor(id);
    }
    String authorsDeletedMessage = String.format("Los autores han sido eliminados.");
    addMessage("autor-eliminar-resultado", Message.createSuccessMessage(null, authorsDeletedMessage));
    listar();
  }
}
