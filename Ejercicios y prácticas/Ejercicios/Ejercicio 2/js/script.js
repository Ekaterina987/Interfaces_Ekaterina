var contador = 0;
var cuentaAtras;
window.onload = function () {
    let iniciar = document.getElementById("iniciar");
    iniciar.addEventListener("click", ()=>{iniciarContador()});

    let parar = document.getElementById("parar");
    parar.addEventListener("click", ()=>{pararContador()});
}
function iniciarContador(){
    let segundos = document.getElementById("numSegundos");
    if(isNaN(segundos.value)||segundos.value<0){
        alert("Debes introducir un número mayor que 0");
    }else{
        let contadorTexto = document.getElementById("contador");
        contadorTexto.classList.remove("rojo");
        contador = segundos.value;
        mostrarNum();
        cuentaAtras = setInterval(()=>{mostrarNum()}, 1000)
    }
}
function mostrarNum(){
    let contadorTexto = document.getElementById("contador");
    contadorTexto.innerText = contador;
    if(contador>0){

        contador--;
    }else{
        contadorTexto.classList.add("rojo");
        clearInterval(cuentaAtras);
    }
}
function pararContador(){
    clearInterval(cuentaAtras);
}