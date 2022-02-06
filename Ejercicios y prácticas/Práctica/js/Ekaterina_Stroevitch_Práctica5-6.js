$(document).ready(function(){

    let estrella = $(".star");
    let oculto = $(".oculto");
    let contenedor = $(".contenedor");
    let rating = $(".rating");
    let titulo = $("#titulo");
    let descarga = $(".descarga");
    let footer = $(".footer");
    let link = $(".footer .nav-link");
    $("#toggle").on('click',function() {
        if ($(this).attr('data-click-state') == 1) {
            $(this).attr('data-click-state', 0);
            estrella.css('opacity', '1');
            oculto.css('opacity', '0');
            contenedor.css('background-color', '#f1f1f1');
            rating.css('color', '#18191a');
            titulo.css('color', '#18191a');
            footer.css('background-color', '#18191a');
            link.removeClass('nav-oscuro');
            descarga.css('border', '2px solid #18191a');
            $(this).text('Cambia a modo oscuro');
            $(this).css({'background-color' : '#18191a', 'color' : '#f1f1f1'});

        } else {
            $(this).attr('data-click-state', 1);
            estrella.css('opacity', '0');
            oculto.css('opacity', '1');
            contenedor.css('background-color', '#18191a');
            rating.css('color', '#f1f1f1');
            titulo.css('color', '#f1f1f1');
            footer.css('background-color', '#ff4c70');
            link.addClass('nav-oscuro');
            descarga.css('border', '2px solid #f1f1f1');
            $(this).text('Cambia a modo claro');
            $(this).css({'background-color' : '#f1f1f1', 'color' : '#18191a'});

        }
    })
});