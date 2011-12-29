/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.edu.uvaq.elibrary.presentation.command;

import java.util.LinkedHashMap;
import java.util.Map;
import mx.edu.uvaq.elibrary.presentation.Mensaje;

/**
 *
 * @author daniel
 */
public abstract class AbstractForma {
    private Map<String, Mensaje> mensajes;
    private String accion;

    public AbstractForma() {
        mensajes = new LinkedHashMap<String, Mensaje>();
        accion = "defecto";
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Map<String, Mensaje> getMensajes() {
        return mensajes;
    }

    public void agregarMensaje(String clave, Mensaje mensaje) {
        mensajes.put(clave, mensaje);
    }


}
