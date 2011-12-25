/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
  $("a#editar-autor-link").click(function(evento) {
    evento.preventDefault();
    var $columnaId = $("td.columna-checkbox:has(:checked)").first().siblings("td.columna-id").first();
    var url = $(this).attr("href") + "&id=" + $columnaId.text();
    console.log(url);
    window.location.replace(url);
  });
});