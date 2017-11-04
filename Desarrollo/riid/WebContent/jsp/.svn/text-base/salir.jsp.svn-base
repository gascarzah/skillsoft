<%@ page import="clases.UsuarioSistema" %>
<%	UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario"); %>
<script language="javascript"> 
var altKey  = false;
var ctrlKey = false;
var keyCode = 0;
var tipoBrowser = "";
var teclaCodigo = 0 ;
var posx = 0;
var posy = 0;
if (navigator.userAgent.indexOf("MSIE")<0){
	if (navigator.userAgent.indexOf("Opera")<0){
		tipoBrowser = "Mozilla";
	} 
	else{
		tipoBrowser = "Opera";
		//alert("Su browser es Opera");
	}
} 
else {
	tipoBrowser = "Ms";
}
if (tipoBrowser == "Ms") { 
	document.onkeydown = whatKeyIE;
} 
else {
	window.onkeydown  =  whatKey;
	window.onmousedown = doPosMouse;
}
function confirmclose(evt){
	var bolSalida = false;
	if(altKey == true && teclaCodigo == 115){ 
		bolSalida = true; 
		//alert("Se Cancela la Session - alt + f4" );
		salir();
		//alert("Su sesión ha sido cancelada");
	} 
	else if(ctrlKey == true && teclaCodigo == 87){
		bolSalida = true; 
		//alert("Se Cancela la Session - ctrl + w" );
		salir();
		//alert("Su sesión ha sido cancelada");
	} 
	else if(ctrlKey == true && teclaCodigo == 17){
		if(navigator.userAgent.indexOf("Chrome")>0){
			bolSalida = true; 
			//alert("Se Cancela la Session - ctrl + w Chrome" );
			salir();
			//alert("Su sesión ha sido cancelada");
		} 
	} 
	else if(teclaCodigo == 116){
		//alert("Refresh")
	} 
	else if(tipoBrowser == "Ms"){
		evt =  window.event; 
		posx = evt.clientX + document.body.scrollLeft	+ document.documentElement.scrollLeft;
		posy = evt.clientY + document.body.scrollTop + document.documentElement.scrollTop;
		if(posy<0){
			bolSalida = true; 
			//alert("Se Cancela la Session IE" + posx + ", " + posy);
			salir();
			//alert("Su sesión ha sido cancelada");
		}
	} 
	else {
		if (posy==0) {
			bolSalida = true; 
			//alert("Se Cancela la Session FF " + posx + ", " + posy);
			salir();
			//alert("Su sesión ha sido cancelada");
		}
	}
	if(bolSalida == true){
		//alert("Se Cancelo la Session " );
		alert("Su sesión ha sido cancelada");
		salir();
	}
}
function whatKeyIE(evt){ 
	altKey  = event.altKey; 
	ctrlKey = event.ctrlKey;
	teclaCodigo = event.keyCode
} 
function whatKey(evt){ 
	altKey  = evt.altKey; 
	teclaCodigo = evt.keyCode; 
	ctrlKey = evt.ctrlKey;
}
function doPosMouse(evt) {
	if(!evt) 
		var evt = window.event;
	if(evt.pageX || evt.pageY){
		posx = evt.pageX;
		posy = evt.pageY;
	}
}
//window.onbeforeunload = confirmclose;

function salir(){
<% if(clusuario.getUsuario()!=null){ %>
	location.href = "../servlet/Index?upd=Salir&USER=<%= clusuario.getUsuario() %>";
<% } %>
}
</script> 
