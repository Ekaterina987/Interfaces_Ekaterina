var contador = 1;
window.onload = function () {
   let intervalo = setInterval(()=>{mostrarTexto(intervalo)}, 1000);
}
function mostrarTexto(intervalo){
    let texto = document.createElement("p");
    let contenedor = document.getElementById("contenedor");

    switch (contador){
        case 1:
            texto.appendChild(document.createTextNode("Primero"));
            texto.classList.add("primero");
            break;
        case 2:
            texto.appendChild(document.createTextNode("Segundo"));
            texto.classList.add("segundo");
            break;
        case 3:
            texto.appendChild(document.createTextNode("Tercero"));
            texto.classList.add("tercero");
            break;
        case 4:
            texto.appendChild(document.createTextNode("Cuarto"));
            texto.classList.add("cuarto");
            break;
        case 5:
            clearInterval(intervalo);
            contador = 0;
            break;
    }
    texto.addEventListener("click", ()=>{restablecer(intervalo)});
    contenedor.appendChild(texto);
    contador++;
}
function restablecer(intervalo){
    let contenedor = document.getElementById("contenedor");
    clearInterval(intervalo);
    contenedor.innerHTML = "";
    contador = 1;
    intervalo = setInterval(()=>{mostrarTexto(intervalo)}, 1000);
}