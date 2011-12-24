/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion;

/**
 *
 * @author daniel
 */
public class Mensaje {

  private String resumen;
  private String detalle;
  private TipoMensaje severidad;

  private Mensaje() {
  }

  public static Mensaje getWarningmensaje(String resumen, String mensaje) {
    Mensaje warningmensaje = new Mensaje();
    warningmensaje.setSeveridad(TipoMensaje.WARNING);
    warningmensaje.setResumen(resumen);
    warningmensaje.setDetalle(mensaje);

    return warningmensaje;
  }

  public static Mensaje crearMensajeError(String resumen, String mensaje) {
    Mensaje mensajeError = crearMensaje(resumen, mensaje);
    mensajeError.setSeveridad(TipoMensaje.ERROR);

    return mensajeError;
  }

  public static Mensaje crearMensajeInformacion(String resumen, String mensaje) {
    Mensaje mensajeInformacion = crearMensaje(resumen, mensaje);
    mensajeInformacion.setSeveridad(TipoMensaje.INFO);

    return mensajeInformacion;
  }

  public static Mensaje crearMensajeExito(String resumen, String mensaje) {
    Mensaje mensajeExito = crearMensaje(resumen, mensaje);
    mensajeExito.setSeveridad(TipoMensaje.SUCCESS);

    return mensajeExito;
  }

  private static Mensaje crearMensaje(String resumen, String detalle) {
    Mensaje successmensaje = new Mensaje();
    successmensaje.setResumen(resumen);
    successmensaje.setDetalle(detalle);

    return successmensaje;
  }

  public String getDetalle() {
    return detalle;
  }

  public void setDetalle(String detalle) {
    this.detalle = detalle;
  }

  public String getResumen() {
    return resumen;
  }

  public void setResumen(String resumen) {
    this.resumen = resumen;
  }

  public TipoMensaje getSeveridad() {
    return severidad;
  }

  public String getClaseMensaje() {
    return getSeveridad().name().toLowerCase();
  }

  public void setSeveridad(TipoMensaje severidad) {
    this.severidad = severidad;
  }
}
