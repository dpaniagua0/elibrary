/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.presentation.command;

import java.util.List;
import mx.edu.uvaq.elibrary.domain.User;

/**
 *
 * @author arcesino
 */
public class UsuariosForma extends AbstractForma {

    private List<User> usuarios;

    public List<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<User>  usuarios) {
        this.usuarios = usuarios;
    }
}
