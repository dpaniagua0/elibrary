/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.Usuario;

/**
 *
 * @author arcesino
 */
public class UsuariosForma extends AbstractForma {

    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario>  usuarios) {
        this.usuarios = usuarios;
    }
}
