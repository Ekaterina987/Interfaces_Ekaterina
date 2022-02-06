$(document).ready(function(){

    let estrella = $(".star");
    let oculto = $(".oculto");
    let contenedor = $(".contenedor");
    let rating = $(".rating");
    let titulo = $("#titulo");
    let descarga = $(".descarga");
    let footer = $(".footer");
    let link = $(".footer .nav-link");
    let gracias = $(".gracias");
    let nombre = $(".nombre")
    $("#toggle").on('click',function() {
        if ($(this).attr('data-click-state') == 1) {
            $(this).attr('data-click-state', 0);
            estrella.removeClass("no-opacity");
            oculto.removeClass("opacity");
            contenedor.removeClass("fondo-oscuro");
            rating.removeClass("claro");
            titulo.removeClass("claro");
            footer.removeClass("fondo-rosa");
            link.removeClass('nav-oscuro');
            descarga.removeClass('borde-claro');
            gracias.removeClass("claro");
            nombre.removeClass("claro");
            $(this).text('Cambia a modo oscuro');
            $(this).removeClass('fondo-claro').removeClass('oscuro');

        } else {
            $(this).attr('data-click-state', 1);
            estrella.addClass("no-opacity");
            oculto.addClass("opacity");
            contenedor.addClass("fondo-oscuro");
            rating.addClass("claro");
            titulo.addClass("claro");
            footer.addClass("fondo-rosa");
            link.addClass('nav-oscuro');
            descarga.addClass('borde-claro');
            gracias.addClass("claro");
            nombre.addClass("claro");
            $(this).text('Cambia a modo claro');
            $(this).addClass('fondo-claro').addClass('oscuro');

        }
    })
    let star1 = $("#star-rating1");
    let star2 = $("#star-rating2");
    let star3 = $("#star-rating3");
    let star4 = $("#star-rating4");
    let star5 = $("#star-rating5");

    let button1 = $("#button1");
    let button2 = $("#button2");
    let button3 = $("#button3");
    let button4 = $("#button4");
    let button5 = $("#button5");
    button1.hover(function (){
        star1.addClass('enfocado');
    }, function (){
        star1.removeClass('enfocado');
    });
    button2.hover(function (){
        star1.addClass('enfocado');
        star2.addClass('enfocado');
    }, function (){
        star1.removeClass('enfocado');
        star2.removeClass('enfocado');
    });
    button3.hover(function (){
        star1.addClass('enfocado');
        star2.addClass('enfocado');
        star3.addClass('enfocado');
    }, function (){
        star1.removeClass('enfocado');
        star2.removeClass('enfocado');
        star3.removeClass('enfocado');
    });
    button4.hover(function (){
        star1.addClass('enfocado');
        star2.addClass('enfocado');
        star3.addClass('enfocado');
        star4.addClass('enfocado');
    }, function (){
        star1.removeClass('enfocado');
        star2.removeClass('enfocado');
        star3.removeClass('enfocado');
        star4.removeClass('enfocado');
    });
    button5.hover(function (){
        $('.star-rating').addClass('enfocado');
    }, function (){
        $('.star-rating').removeClass('enfocado');
    });

    button1.click(function (){
        star1.addClass('fas');
        star1.removeClass('far');

        star2.addClass('far');
        star2.removeClass('fas');
        star3.addClass('far');
        star3.removeClass('fas');
        star4.addClass('far');
        star4.removeClass('fas');
        star5.addClass('far');
        star5.removeClass('fas');
        $(".gracias").text('Sentimos que no esté satisfecho');
    });
    button2.click(function (){

        star1.addClass('fas');
        star1.removeClass('far');
        star2.addClass('fas');
        star2.removeClass('far');

        star3.addClass('far');
        star3.removeClass('fas');
        star4.addClass('far');
        star4.removeClass('fas');
        star5.addClass('far');
        star5.removeClass('fas');
        $(".gracias").text('Esperamos mejorar pronto');
    });
    button3.click(function (){
        star1.addClass('fas');
        star1.removeClass('far');
        star2.addClass('fas');
        star2.removeClass('far');
        star3.addClass('fas');
        star3.removeClass('far');

        star4.addClass('far');
        star4.removeClass('fas');
        star5.addClass('far');
        star5.removeClass('fas');
        $(".gracias").text('Gracias');
    });
    button4.click(function (){
        star1.addClass('fas');
        star1.removeClass('far');
        star2.addClass('fas');
        star2.removeClass('far');
        star3.addClass('fas');
        star3.removeClass('far');
        star4.addClass('fas');
        star4.removeClass('far');

        star5.addClass('far');
        star5.removeClass('fas');
        $(".gracias").text('¡Gracias por tu voto!');
    });
    button5.click(function (){
        $('.star-rating').addClass('fas');
        $('.star-rating').removeClass('far');
        $(".gracias").text('¡Muchas gracias por tu voto, cuéntales a tus amigos sobre nosotros!');
    });
});