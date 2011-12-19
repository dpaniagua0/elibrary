/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentacion;

/**
 *
 * @author daniel
 */
public enum ModoEdicion {

    NONE("Ninguna"),
    REGISTER("Registrar"),
    UPDATE("Actualizar"),
    DELETE("Eliminar");
    private String editionTitle;

    public String getEditionTittle() {
        return editionTitle;
    }

    public void setEditionTittle(String editionTittle) {
        this.editionTitle = editionTittle;
    }

    private ModoEdicion(String editionTittle) {
        this.editionTitle = editionTittle;
    }
}
