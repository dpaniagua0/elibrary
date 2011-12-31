/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var UVAQ = {
  eLibrary: {}
};

UVAQ.eLibrary.estilizarTabla = function(config) {
  config = config || {};
  config.claseTabla = config.claseTabla || 'tabla-datos';
  config.claseFilaPar = config.claseFilaPar || 'fila-par';
  config.claseFilaImpar = config.claseFilaImpar || 'fila-impar';
  config.claseFilaCursor = config.claseFilaCursor || 'fila-cursor';
  config.claseFilaSeleccionada = config.claseFilaSeleccionada || 'fila-seleccionada';

  var agregarClaseCursor = function() {
    $(this).addClass('fila-cursor');
  };

  var quitarClaseCursor = function() {
    $(this).removeClass('fila-cursor');
  };

  var cambiarSeleccionFila = function() {
    if (this.checked) {
      $(this).closest('tr').addClass('fila-seleccionada');
    } else {
      $(this).closest('tr').removeClass('fila-seleccionada');
    }
  };

  $("table." + config.claseTabla).each(function() {
    $(this).find("tbody > tr:odd").addClass(config.claseFilaPar);
    $(this).find("tbody > tr:even").addClass(config.claseFilaImpar);
    $(this).find("tbody > tr").hover(agregarClaseCursor, quitarClaseCursor);
    $(this).find("tbody > tr > td:(0) input:checkbox").change(cambiarSeleccionFila);
    $(this).find("thead th:eq(0) input:checkbox").change( $(this), function(evento) {
      var tabla = evento.data;
      tabla.find("tbody > tr > td:(0) input:checkbox").attr('checked', this.checked);
      if (this.checked) {
        tabla.find("tbody > tr").addClass(config.claseFilaSeleccionada);
      } else {
        tabla.find("tbody > tr").removeClass(config.claseFilaSeleccionada);
      }
    });
  });
};

UVAQ.eLibrary.tooltipRenderHandler = function(event, api) {
  api.elements.content.children().first().show();
};

UVAQ.eLibrary.generarTooltips = function(config) {
  config = config || {};
  config.selectorTrigger = config.selectorTrigger || '.tooltip-trigger';
  $(config.selectorTrigger).each(function(index, element) {
    var $element = $(element);
    var $tooltip = $element.next();
    var titulo = $tooltip.attr('title');
    $tooltip.removeAttr('title');
    $element.qtip({
      content: {
        text: $tooltip,
        title: {
          text: titulo
        }
      },
      position: {
        my: 'bottom center',
        at: 'top center'
      },
      style: {
        classes: 'ui-tooltip-shadow ui-tooltip-dark  ui-tooltip-tipsy ui-tooltip-rounded'

      },
      events: {
        render: UVAQ.eLibrary.tooltipRenderHandler
      }
    });
  });
};

UVAQ.eLibrary.usarQtips = function() {
  $('*[title]').qtip();
};