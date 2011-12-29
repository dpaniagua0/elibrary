/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import mx.edu.uvaq.elibrary.domain.Libro;
import mx.edu.uvaq.elibrary.domain.Serie;

/**
 *
 * @author arcesino
 */
public interface SerieDao {

  Serie encontrarSerieDeLibro(Libro libro);
}
