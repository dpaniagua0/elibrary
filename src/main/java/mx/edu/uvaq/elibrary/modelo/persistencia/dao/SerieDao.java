/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.modelo.persistencia.dao;

import mx.edu.uvaq.elibrary.modelo.entidades.Libro;
import mx.edu.uvaq.elibrary.modelo.entidades.Serie;

/**
 *
 * @author arcesino
 */
public interface SerieDao {

  Serie encontrarSerieDeLibro(Libro libro);
}
