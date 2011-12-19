/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(function(){
  // BUTTONS
  $('.fg-button').hover(
    function(){
      $(this).removeClass('ui-state-default').addClass('ui-state-focus');
    },
    function(){
      $(this).removeClass('ui-state-focus').addClass('ui-state-default');
    }
    );

  // MENUS
  $('#flat').menu({
    content: $('#flat').next().html(), // grab content from this page
    showSpeed: 400
  });

  $('#hierarchy').menu({
    content: $('#hierarchy').next().html(),
    crumbDefaultText: ' '
  });

  $('#hierarchybreadcrumb').menu({
    content: $('#hierarchybreadcrumb').next().html(),
    backLink: false
  });
});