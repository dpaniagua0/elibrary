/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.business.service.impl;

import java.util.Date;
import mx.edu.uvaq.elibrary.model.business.service.RegistroServicio;
import mx.edu.uvaq.elibrary.domain.User;
import mx.edu.uvaq.elibrary.model.business.service.CorreoServicio;
import mx.edu.uvaq.elibrary.model.persistence.dao.UsuarioDao;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author daniel
 */
public class RegistroServicioImpl implements RegistroServicio {

  private UsuarioDao usuarioDao;
  private CorreoServicio correoServicio;

  public void setUsuarioDao(UsuarioDao usuarioDao) {
    this.usuarioDao = usuarioDao;
  }

  public void setCorreoServicio(CorreoServicio correoServicio) {
    this.correoServicio = correoServicio;
  }

  public boolean registrarUsuario(User nuevoUsuario, String urlActivacion) {
    if (validarNuevoUsuario(nuevoUsuario)) {
      String codigoActivacion = generarCodigoActivacion(nuevoUsuario);
      nuevoUsuario.setActivationCode(codigoActivacion);
      usuarioDao.insertarUsuario(nuevoUsuario);
      correoServicio.enviarCorreoActivacionCuenta(nuevoUsuario, urlActivacion);
      return true;
    }
    return false;
  }

  public boolean existeUsuario(User nuevoUsuario) {
    User usuario = usuarioDao.encontrarUsuario(nuevoUsuario.getEmail());

    return usuario != null;
  }

  private boolean validarNuevoUsuario(User nuevoUsuario) {
    return !existeUsuario(nuevoUsuario);
  }

  public String generarCodigoActivacion(User usuario) {
    String datos = String.format("%s%s%s%s", usuario.getEmail(), usuario.getName(),
            usuario.getLastName(), new Date().getTime());

    byte[] codigoActivacion = DigestUtils.sha256(datos);

    return Base64.encodeBase64URLSafeString(codigoActivacion);
  }

  public boolean activarUsuario(User nuevoUsuario) {
    if (!validarNuevoUsuario(nuevoUsuario)) {
      usuarioDao.actualizarUsuario(nuevoUsuario.getEmail());
      usuarioDao.asignarRolUsuario(nuevoUsuario, "estudiante");
      return true;
    }
    return false;

  }
}
