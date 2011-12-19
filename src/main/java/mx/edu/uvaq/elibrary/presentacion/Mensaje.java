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

    public static Mensaje getWarningMessage(String summary, String message) {
        Mensaje warningMessage = new Mensaje();
        warningMessage.setSeveridad(TipoMensaje.WARNING);
        warningMessage.setResumen(summary);
        warningMessage.setDetalle(message);

        return warningMessage;
    }

    public static Mensaje crearMensajeError(String summary, String message) {
        Mensaje errorMessage = new Mensaje();
        errorMessage.setSeveridad(TipoMensaje.ERROR);
        errorMessage.setResumen(summary);
        errorMessage.setDetalle(message);

        return errorMessage;
    }

    public static Mensaje crearMensajeInformacion(String summary, String message) {
        Mensaje infoMessage = new Mensaje();
        infoMessage.setSeveridad(TipoMensaje.INFO);
        infoMessage.setResumen(summary);
        infoMessage.setDetalle(message);

        return infoMessage;
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

    public void setSeveridad(TipoMensaje severidad) {
        this.severidad = severidad;
    }
}
