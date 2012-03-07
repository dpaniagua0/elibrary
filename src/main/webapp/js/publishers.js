/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
  $("a#edit-publisher-link").click(function(evento) {
    evento.preventDefault();
    var $columnaId = $("td.columna-checkbox:has(:checked)").first().siblings("td.columna-id").first();
    if($columnaId.text()){
      var url = $(this).attr("href") + "?id=" + $columnaId.text();
      window.location.replace(url);
    } else {
      $("#error-seleccion").modal({
        "backdrop"  : "static",
        "keyboard"  : true,
        "show"      : true    
      });
      
      $("#error-seleccion a#error").click(function(){
        $("#error-seleccion").modal('hide');
      });
    }
  });
  
  $("a#delete-publisher-link").click(function(evento) {
    evento.preventDefault();
    var $ids = $("td.columna-checkbox:has(:checked)").siblings("td.columna-id");
    var url = $(this).attr("href") + '?'
    if($ids.text()){
      $("#myModal").modal({
        "backdrop"  : "static",
        "keyboard"  : true,
        "show"      : true    
      });
      $("#myModal a#ok").click(function(e) {
        $ids.each(function() {
          url += "id=" + $(this).text() + '&';
        });
        window.location.replace(url);
        $("#myModal").modal('hide');
      });
      $("#myModal a#cancel").click(function(e){
        $("#myModal").modal('hide');
        $("td.columna-checkbox").attr('checked', false);
      });
    } else {
      $("#error-seleccion").modal({
        "backdrop"  : "static",
        "keyboard"  : true,
        "show"      : true    
      });
      
      $("#error-seleccion a#error").click(function(){
        $("#error-seleccion").modal('hide');
      });
    }
  });
});

