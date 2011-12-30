/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation;

/**
 * @author daniel
 */
public class Message {

  private String summary;
  private String detail;
  private MessageType type;

  private Message() {
  }

  public static Message createInformationMessage(String summary, String detail) {
    Message infoMessage = createMessage(summary, detail);
    infoMessage.setType(MessageType.INFO);

    return infoMessage;
  }

  public static Message createSuccessMessage(String summary, String detail) {
    Message successMessage = createMessage(summary, detail);
    successMessage.setType(MessageType.SUCCESS);

    return successMessage;
  }

  public static Message createWarningMessage(String summary, String detail) {
    Message warningMessage = createMessage(summary, detail);
    warningMessage.setType(MessageType.WARNING);

    return warningMessage;
  }

  public static Message createErrorMessage(String summary, String detail) {
    Message errorMessage = createMessage(summary, detail);
    errorMessage.setType(MessageType.ERROR);

    return errorMessage;
  }

  private static Message createMessage(String resumen, String detalle) {
    Message message = new Message();
    message.setSummary(resumen);
    message.setDetail(detalle);

    return message;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public MessageType getType() {
    return type;
  }

  public void setType(MessageType type) {
    this.type = type;
  }

  public String getMessageClass() {
    return getType().name().toLowerCase();
  }
}
