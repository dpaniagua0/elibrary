/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
  $("a#editar-autor-link").click(function(evento) {
    evento.preventDefault();
    var $columnaId = $("td.columna-checkbox:has(:checked)").first().siblings("td.columna-id").first();
    var url = $(this).attr("href") + "?id=" + $columnaId.text();
    window.location.replace(url);
  });
  
  $("a#eliminar-autor-link").click(function(evento) {
    evento.preventDefault();
    var $ids = $("td.columna-checkbox:has(:checked)").siblings("td.columna-id");
    var url = $(this).attr("href") + '?'
    $ids.each(function() {
      url += "id=" + $(this).text() + '&';
    });
    window.location.replace(url);
  });
});