/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import mx.edu.uvaq.elibrary.domain.Publisher;

import java.util.List;

/**
 * @author daniel
 */
public class PublisherForm extends AbstractForm {

  private int id;
  private String name;
  private List<Publisher> publishers;

  public List<Publisher> getPublishers() {
    return publishers;
  }

  public void setPublishers(List<Publisher> publishers) {
    this.publishers = publishers;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Publisher getPublisher() {
    Publisher publisher = new Publisher();
    publisher.setName(name);
    return publisher;
  }
}
