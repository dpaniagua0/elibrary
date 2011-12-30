/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.uvaq.elibrary.model.persistence.dao;

import mx.edu.uvaq.elibrary.domain.Book;
import mx.edu.uvaq.elibrary.domain.BookSeries;

/**
 * @author arcesino
 */
public interface BookSeriesDao {

  BookSeries findBookSeriesOfBook(Book book);
}
