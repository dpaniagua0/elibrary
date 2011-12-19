/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.negocio.servicio.impl;

import java.util.Date;
import mx.edu.uvaq.elibrary.modelo.negocio.servicio.RegistroServicio;
import mx.edu.uvaq.elibrary.modelo.entidades.Usuario;
import mx.edu.uvaq.elibrary.modelo.negocio.servicio.CorreoServicio;
import mx.edu.uvaq.elibrary.modelo.persistencia.dao.UsuarioDao;
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

  public boolean registrarUsuario(Usuario nuevoUsuario, String urlActivacion) {
    if (validarNuevoUsuario(nuevoUsuario)) {
      String codigoActivacion = generarCodigoActivacion(nuevoUsuario);
      nuevoUsuario.setCodigoActivacion(codigoActivacion);
      usuarioDao.insertarUsuario(nuevoUsuario);
      correoServicio.enviarCorreoActivacionCuenta(nuevoUsuario, urlActivacion);
      return true;
    }
    return false;
  }

  public boolean existeUsuario(Usuario nuevoUsuario) {
    Usuario usuario = usuarioDao.encontrarUsuario(nuevoUsuario.getCorreoElectronico());

    return usuario != null;
  }

  private boolean validarNuevoUsuario(Usuario nuevoUsuario) {
    return !existeUsuario(nuevoUsuario);
  }

  public String generarCodigoActivacion(Usuario usuario) {
    String datos = String.format("%s%s%s%s", usuario.getCorreoElectronico(), usuario.getNombre(),
            usuario.getApellidos(), new Date().getTime());

    byte[] codigoActivacion = DigestUtils.sha256(datos);

    return Base64.encodeBase64URLSafeString(codigoActivacion);
  }

  public boolean activarUsuario(Usuario nuevoUsuario) {
    if (!validarNuevoUsuario(nuevoUsuario)) {
      usuarioDao.actualizarUsuario(nuevoUsuario.getCorreoElectronico());
      usuarioDao.asignarRolUsuario(nuevoUsuario, "estudiante");
      return true;
    }
    return false;

  }
}
