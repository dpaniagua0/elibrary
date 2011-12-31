/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var UVAQ = UVAQ || {};
UVAQ.eLibrary = UVAQ.eLibrary || {};
UVAQ.eLibrary.libros = UVAQ.eLibrary.libros || {};

UVAQ.eLibrary.libros.mostrarFormularioLibro = function(modo) {
  modo = modo || 'nuevo';
  
  if (modo === 'nuevo') {
    // Limpiar el formulario de libro.
    
    $('#info-libro form').each(function() {
      this.reset();
    });
  }
  
  // Mostrar el header correspondiente al modo.
  $('#info-libro .modal-header').hide();
  $('#info-libro-header-' + modo).show();
  
  // Mostrar el boton correspondiente al modo.
  $('.panel-botones button[name=accion]').hide();
  $('.panel-botones #boton-' + modo + '-libro').show();
  
  //  $('#info-libro').show('fold');
  $("#info-libro").modal({
    modal: true,
    backdrop: true,
    keyboard: true
  });
};

UVAQ.eLibrary.libros.cargarFormularioEdicion = function() {
  var librosSeleccionados = $('table#libros > tbody > tr:has(td.columna-checkbox input:checked)');
  if (librosSeleccionados.length > 0) {
    // Tengo libro seleccionado.
    var id = $('td.columna-id', librosSeleccionados[0])[0].textContent;
    $.post('/elibrary/inicio', {
      id: id,
      accion: 'buscar-libro'
    }, function(libro) {
      $('input[name=isbn]').val(libro.isbn);
      $('input[name=titulo]').val(libro.titulo);
      $('input[name=lista_autores]').val(UVAQ.eLibrary.libros.unirLista(libro.autores, 'nombreCompleto'));
      $('input[name=ids_autores]').val(UVAQ.eLibrary.libros.unirLista(libro.autores, 'id'));
      $('input[name=lista_editoriales]').val(UVAQ.eLibrary.libros.unirLista(libro.editoriales, 'nombre'));
      $('input[name=ids_eitoriales]').val(UVAQ.eLibrary.libros.unirLista(libro.editoriales, 'id'));
      $('input[name=lista_categorias]').val(UVAQ.eLibrary.libros.unirLista(libro.categorias, 'nombre'));
      $('input[name=ids_categorias]').val(UVAQ.eLibrary.libros.unirLista(libro.categorias, 'id'));
      UVAQ.eLibrary.libros.mostrarFormularioLibro('editar');
    });
  } else {
    // Mandar un mensaje.
    $( "#seleccion-invalida" ).dialog({
      modal: true,
      resizable: false,
      buttons: {
        Ok: function() {
          $( this ).dialog( "close" );
        }
      }
    });
  }
};

UVAQ.eLibrary.libros.unirLista = function(lista, propiedad) {
  var listaUnida = '';
  propiedad = propiedad || 'nombre';
  propiedad = propiedad ||  'id';
  $.each(lista, function(index, item) {
    listaUnida += item[propiedad] + ', ';
  });
  return listaUnida.substr(0, listaUnida.length - 2);
};

UVAQ.eLibrary.libros.crearLibro = function() {
  if ($('#archivo').val()) {
    $('#panel-progreso-libro').dialog({
      modal: true,
      resizable: false,
      width: 200,
      open: function(event, ui) {
        $('#progreso-libro').progressbar({
          value: 0
        });
        UVAQ.eLibrary.libros.actualizarProgresoLibro();
      }
    });
  }
};

UVAQ.eLibrary.libros.actualizarProgresoLibro = function() {
  $.post('/elibrary/admin/controlador-carga-libro', {
    accion: 'progreso-carga'
  }, function(uploadStats) {
    var value, status;
    if (uploadStats) {
      value = parseInt(uploadStats.totalSize / uploadStats.bytesRead * 100);
      status = '(' + value + '%) ';
      $('#progreso-libro').progressbar('option', 'value', value);
      if (value < 100) {
        status += 'Guardando libro...';
        $('#estatus-progreso-libro').text(status);
        setTimeout('UVAQ.eLibrary.libros.actualizarProgresoLibro()', 500);
      } else {
        status += 'Libro guardado correctamente.';
        $('#estatus-progreso-libro').text(status);
      }
    } else {
      setTimeout('UVAQ.eLibrary.libros.actualizarProgresoLibro()', 500);
    }
  });
};

// Agregar manejadores a los botones y links.
$(function() {
  $('#agregar-libro-link').click(function() {
    UVAQ.eLibrary.libros.mostrarFormularioLibro();
  });
  $('#editar-libro-link').click(function() {
    UVAQ.eLibrary.libros.cargarFormularioEdicion();
  });
  
  $('.panel-botones button').button();
   
//  $('#boton-cancelar').click(function() {
//    $('#info-libro').hide('unfold');
//  });
    
  $.datepicker.setDefaults( $.datepicker.regional['es'] );
  $('#fecha-publicacion').datepicker({
    maxDate: 0,
    changeMonth: true,
    changeYear: true
  });
  
//  $('#boton-nuevo-libro').click(function() {
//    UVAQ.eLibrary.libros.createBook();
//  });
});


$(function(){
  $(".panel-botones > #boton-cancelar").click(function(){
    $("#info-autores").dialog("close");
  });  
  
  $(".panel-botones > #boton-cancelar").click(function(){
    $("#edicion-autores").dialog("close");
  });
  
  $(".panel-botones > #boton-cancelar").click(function(){
    $("#info-editoriales").dialog("close");
  });
  
  $(".panel-botones > #boton-cancelar").click(function(){
    $("#edicion-editoriales").dialog("close");
  });
  
  $(".panel-botones > #boton-cancelar").click(function(){
    $("#info-categoria").dialog("close");
  });
  
  $(".panel-botones > #boton-cancelar").click(function(){
    $("#edicion-categoria").dialog("close");
  });
});

//$(function() {
//  $( "#info-autores" ).dialog({
//    autoOpen: false,
//    height: 200,
//    width: 360,
//    modal: true
//  });
//  $( "#agregar-autor-link" ).click(function() {
//    $( "#info-autores" ).dialog( "open" );
//  });
//
//  $( "#edicion-autores" ).dialog({
//    autoOpen: false,
//    height: 180,
//    width: 350,
//    modal: true
//  });
//  $( "#editar-autor-link" ).click(function() {
//    $( "#edicion-autores" ).dialog( "open" );
//  });
//});

$(function() {
  $( "#info-editoriales" ).dialog({
    autoOpen: false,
    height: 180,
    width: 350,
    modal: true
  });
  $( "#agregar-editorial-link" ).click(function() {
    $( "#info-editoriales" ).dialog( "open" );
  });

  $( "#edicion-editoriales" ).dialog({
    autoOpen: false,
    height: 180,
    width: 350,
    modal: true
  });
  $( "#editar-editorial-link" ).click(function() {
    $( "#edicion-editoriales" ).dialog( "open" );
  });
});

$(function() {
  $( "#info-categoria" ).dialog({
    autoOpen: false,
    height: 180,
    width: 350,
    modal: true
  });
  $( "#agregar-categoria-link" ).click(function() {
    $( "#info-categoria" ).dialog( "open" );
  });

  $( "#edicion-categoria" ).dialog({
    autoOpen: false,
    height: 180,
    width: 350,
    modal: true
  });
  $( "#editar-categoria-link" ).click(function() {
    $( "#edicion-ecategoria" ).dialog( "open" );
  });
});


UVAQ.eLibrary.libros.cargarFormularioEdicionCategoria = function() {
  var categoriaSeleccionada = $('#tabla-categorias > tbody > tr:has(td.columna-checkbox input:checked)');
  
  if (categoriaSeleccionada.length > 0) {
    // Tengo libro seleccionado.
    var id = $('td.columna-id', categoriaSeleccionada[0])[0].textContent;
    $.post('/elibrary/admin/categorias', {
      id: id
    }, function(categoria) {
      $('input[name=categoria]').val(categoria.nombre);
      UVAQ.eLibrary.libros.mostrarFormularioCategoria('editar');
    });
  } else {
    // Mandar un mensaje.
    $( "#seleccion-invalida" ).dialog({
      modal: true,
      resizable: false,
      buttons: {
        Ok: function() {
          $( this ).dialog( "close" );
        }
      }
    });
  }
};

UVAQ.eLibrary.libros.mostrarFormularioCategoria = function(modo) {
  modo = modo || 'nuevo';

  if (modo === 'nuevo') {
    // Limpiar el formulario de libro.
    $('#info-libro form').each(function() {
      this.reset();
      $( "#info-categoria" ).dialog({
        autoOpen: false,
        height: 150,
        width: 350,
        modal: true
      });
      $( "#agregar-categoria-link" ).click(function() {
        $( "#info-categoria" ).dialog( "open" );
      });
    });
  }

  if(modo == 'editar') {
    $( "#edicion-categoria" ).dialog({
      autoOpen: false,
      height: 150,
      width: 350,
      modal: true
    });
    $( "#editar-categoria-link" ).click(function() {
      $( "#edicion-categoria" ).dialog( "open" );
    });

  }
};