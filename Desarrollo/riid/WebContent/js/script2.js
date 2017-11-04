// JavaScript Document
nextfield = "Submit";
netscape = "";
ver = navigator.appVersion; len = ver.length;
for(iln = 0; iln < len; iln++) if (ver.charAt(iln) == "(") break;
netscape = (ver.charAt(iln+1).toUpperCase() != "C");

function keyDown(DnEvents) { 
  k = (netscape) ? DnEvents.which : window.event.keyCode;
  if (k == 13){
    if (nextfield == 'Submit')
      return true; 
    else{ 
	  eval('document.f.' + nextfield + '.focus()');
	  return false;
	}
  }
}

document.onkeydown = keyDown;
if (netscape) document.captureEvents(Event.KEYDOWN|Event.KEYUP);

function cambia(campo) {
  campo.value = campo.value.toUpperCase();
}

function valida(){
  if(document.f.DOCUMENTO.value == ""){
	alert("Por favor ingrese tipo de documento");
	event.returnValue=false;
	document.f.DOCUMENTO.focus();
  }
  else if(document.f.DNI.value == ""){
	alert("Por favor ingrese el n�mero de documento de identidad");
	event.returnValue=false;
	document.f.DNI.focus();
  }
  else if(document.f.TITULAR.value == ""){
	alert("Por favor ingrese los Apellidos y Nombres del titular");
	event.returnValue=false;
	document.f.TITULAR.focus();
  }
  else if(document.f.EDAD.value == ""){
	alert("Por favor ingrese la edad del titular");
	event.returnValue=false;
	document.f.EDAD.focus();
  }
  else if(document.f.SEXO.value == ""){
	alert("Por favor ingrese elo sexo del titular");
	event.returnValue=false;
	document.f.SEXO.focus();
  }
  else if(document.f.DEPARTAMENTO.value == ""){
	alert("Por favor ingrese su departamento");
	event.returnValue=false;
	document.f.DEPARTAMENTO.focus();
  }
  else if(document.f.PROVINCIA.value == ""){
	alert("Por favor ingrese su provincia");
	event.returnValue=false;
	document.f.PROVINCIA.focus();
  }
  else if(document.f.DISTRITO.value == ""){
	alert("Por favor ingrese su distrito");
	event.returnValue=false;
	document.f.DISTRITO.focus();
  }
  else if(document.f.DATOS.value == ""){
	alert("Por favor ingrese su direcci�n");
	event.returnValue=false;
	document.f.DATOS.focus();
  }
  /*
  else if(document.f.TELEFONO.value == ""){
	alert("Por favor ingrese su tel�fono");
	event.returnValue=false;
	document.f.TELEFONO.focus();
  }
  */
  else if(document.f.PRESTACION.value == ""){
	alert("Por favor ingrese el tipo de prestaci�n");
	event.returnValue=false;
	document.f.PRESTACION.focus();
  }
  else if(document.f.SOLICITUD.value == ""){
	alert("Por favor ingrese el tipo de solicitud");
	event.returnValue=false;
	document.f.SOLICITUD.focus();
  }
  else if(document.f.DESASUNTO.value == ""){
	alert("Por favor ingrese la descripcion de los hechos");
	event.returnValue=false;
	document.f.DESASUNTO.focus();
  }
  else if(document.f.CODIGO.value == ""){
	alert("Por favor ingrese el asunto");
	event.returnValue=false;
	document.f.CODIGO.focus();
  }
  else if(document.f.CRED.value == ""){
	alert("Por favor ingrese la Red Asistencial");
	event.returnValue=false;
	document.f.CRED.focus();
  }
  else if(document.f.CCAS.value == ""){
	alert("Por favor ingrese el Centro Asistencial");
	event.returnValue=false;
	document.f.CCAS.focus();
  }
  else if(document.f.ENLAREHOSCOD.value == ""){
	alert("Por favor ingrese el Area");
	event.returnValue=false;
	document.f.ENLAREHOSCOD.focus();
  }
  else if(document.f.DIAGCOD.value != "" && document.f.DESDIAGCOD.value == ""){
		alert("Por favor valide el codigo");
		event.returnValue=false;
		document.f.DIAGCOD.focus();
  }
  else if(document.f.DEFENSORIALES.value == ""){
	alert("Por favor ingrese la Acci�n Defensorial");
	event.returnValue=false;
	document.f.DEFENSORIALES.focus();
  }
  else if(document.f.ESTADO.value == ""){
		alert("Por favor ingrese el Estado Situacional");
		event.returnValue=false;
		document.f.ESTADO.focus();
  }
  else if(document.f.TINGRESO.value == ""){
		alert("Por favor ingrese el Tipo de Ingreso");
		event.returnValue=false;
		document.f.TINGRESO.focus();
  }
  else if(!(document.f.ATENCION[0].checked || document.f.ATENCION[1].checked)){
		alert("Por favor marque el tipo de atenci�n");
		event.returnValue=false;
		document.f.ATENCION[0].focus();
  }
  else if(document.f.EMAIL.value != "" && (document.f.EMAIL.value.indexOf("@")==-1 || document.f.EMAIL.value.indexOf(".")==-1)){
		alert("Ingrese su Email p.ej. miemail@hotmail.com");
		event.returnValue=false;
		document.f.EMAIL.focus();
  }
  else {
		document.f.submit();
  }
}

function validaOAS(strPerfil){
	var perfil = strPerfil;
  if(document.f.DOCUMENTO.value == ""){
	alert("Por favor ingrese tipo de documento");
	event.returnValue=false;
	document.f.DOCUMENTO.focus();
  }
  else if(document.f.DNI.value == ""){
	alert("Por favor ingrese el n�mero de documento de identidad");
	event.returnValue=false;
	document.f.DNI.focus();
  }
  else if(document.f.TITULAR.value == ""){
	alert("Por favor ingrese los Apellidos y Nombres del titular");
	event.returnValue=false;
	document.f.TITULAR.focus();
  }
  else if(document.f.EDAD.value == ""){
	alert("Por favor ingrese la edad del titular");
	event.returnValue=false;
	document.f.EDAD.focus();
  }
  else if(document.f.SEXO.value == ""){
	alert("Por favor ingrese elo sexo del titular");
	event.returnValue=false;
	document.f.SEXO.focus();
  }
  else if(document.f.DEPARTAMENTO.value == ""){
	alert("Por favor ingrese su departamento");
	event.returnValue=false;
	document.f.DEPARTAMENTO.focus();
  }
  else if(document.f.PROVINCIA.value == ""){
	alert("Por favor ingrese su provincia");
	event.returnValue=false;
	document.f.PROVINCIA.focus();
  }
  else if(document.f.DISTRITO.value == ""){
	alert("Por favor ingrese su distrito");
	event.returnValue=false;
	document.f.DISTRITO.focus();
  }
  else if(document.f.DATOS.value == ""){
	alert("Por favor ingrese su direcci�n");
	event.returnValue=false;
	document.f.DATOS.focus();
  }
  /*else if(document.f.TELEFONO.value == ""){
	alert("Por favor ingrese su tel�fono");
	event.returnValue=false;
	document.f.TELEFONO.focus();
  }*/
  else if(document.f.PRESTACION.value == ""){
	alert("Por favor ingrese el tipo de prestaci�n");
	event.returnValue=false;
	document.f.PRESTACION.focus();
  }
  else if(document.f.SOLICITUD.value == ""){
	alert("Por favor ingrese el tipo de solicitud");
	event.returnValue=false;
	document.f.SOLICITUD.focus();
  }
  else if(document.f.DESASUNTO.value == ""){
	alert("Por favor ingrese la descripcion de los hechos");
	event.returnValue=false;
	document.f.DESASUNTO.focus();
  }
  else if(document.f.CODIGO.value == ""){
	alert("Por favor ingrese el asunto");
	event.returnValue=false;
	document.f.CODIGO.focus();
  }
  else if(document.f.CRED.value == ""){
	alert("Por favor ingrese la Red Asistencial");
	event.returnValue=false;
	document.f.CRED.focus();
  }
  else if(document.f.CCAS.value == ""){
	alert("Por favor ingrese el Centro Asistencial");
	event.returnValue=false;
	document.f.CCAS.focus();
  }
  else if(document.f.ESTADO.value == ""){
		alert("Por favor ingrese el Estado Situacional");
		event.returnValue=false;
		document.f.ESTADO.focus();
  }
  else if(document.f.ENLAREHOSCOD.value == ""){
		alert("Por favor ingrese el Area");
		event.returnValue=false;
		document.f.ENLAREHOSCOD.focus();
  }
  else if(document.f.DIAGCOD.value != "" && document.f.DESDIAGCOD.value == ""){
		alert("Por favor valide el codigo");
		event.returnValue=false;
		document.f.DIAGCOD.focus();
  }
  else if(document.f.TINGRESO.value == "" && (perfil!="3" || perfil!="4")){
		alert("Por favor ingrese el Tipo de Ingreso");
		event.returnValue=false;
		document.f.TINGRESO.focus();
	}
  else if(!(document.f.ATENCION[0].checked || document.f.ATENCION[1].checked)){
		alert("Por favor marque el tipo de atenci�n");
		event.returnValue=false;
		document.f.ATENCION[0].focus();
  }
	else if(document.f.WORKFLOW.value == "1" && !(document.f.ATENCION[0].checked || document.f.ATENCION[1].checked)){
		alert("Por favor chequee si es Atenci�n Mediata o Atenci�n Inmediata");
		event.returnValue=false;
  }
  else if(document.f.EMAIL.value != "" && (document.f.EMAIL.value.indexOf("@")==-1 || document.f.EMAIL.value.indexOf(".")==-1)){
		alert("Ingrese su Email p.ej. miemail@hotmail.com");
		event.returnValue=false;
		document.f.EMAIL.focus();
  }
  else {
		document.f.submit();
  }
}

function valida2(){
  if(document.f.DOCUMENTO.value == ""){
	alert("Por favor ingrese tipo de documento");
	event.returnValue=false;
	document.f.DOCUMENTO.focus();
  }
  else if(document.f.DNI.value == ""){
	alert("Por favor ingrese el n�mero de documento de identidad");
	event.returnValue=false;
	document.f.DNI.focus();
  }
  else if(document.f.TITULAR.value == ""){
	alert("Por favor ingrese los Apellidos y Nombres del titular");
	event.returnValue=false;
	document.f.TITULAR.focus();
  }
  else if(document.f.EDAD.value == ""){
	alert("Por favor ingrese la edad del titular");
	event.returnValue=false;
	document.f.EDAD.focus();
  }
  else if(document.f.SEXO.value == ""){
	alert("Por favor ingrese elo sexo del titular");
	event.returnValue=false;
	document.f.SEXO.focus();
  }
  else if(document.f.DEPARTAMENTO.value == ""){
	alert("Por favor ingrese su departamento");
	event.returnValue=false;
	document.f.DEPARTAMENTO.focus();
  }
  else if(document.f.PROVINCIA.value == ""){
	alert("Por favor ingrese su provincia");
	event.returnValue=false;
	document.f.PROVINCIA.focus();
  }
  else if(document.f.DISTRITO.value == ""){
		alert("Por favor ingrese su distrito");
		event.returnValue=false;
		document.f.DISTRITO.focus();
  }
  else if(document.f.DATOS.value == ""){
		alert("Por favor ingrese su direcci�n");
		event.returnValue=false;
		document.f.DATOS.focus();
  }
  /*
  else if(document.f.TELEFONO.value == ""){
		alert("Por favor ingrese su tel�fono");
		event.returnValue=false;
		document.f.TELEFONO.focus();
  }
  */
  else if(document.f.SOLICITUD.value == ""){
		alert("Por favor ingrese el tipo de solicitud");
		event.returnValue=false;
		document.f.SOLICITUD.focus();
  }
  else if(document.f.DESASUNTO.value == ""){
	alert("Por favor ingrese la descripcion de los hechos");
	event.returnValue=false;
	document.f.DESASUNTO.focus();
  }
  else if(document.f.DCAS.value == "" && document.f.SOLICITUD.selectedIndex != "1"){
		alert("Por favor ingrese el Centro Asistencial");
		event.returnValue=false;
		document.f.DCAS.focus();
  }
  else if(document.f.ENLAREHOSCOD.value == ""){
		alert("Por favor ingrese el Area");
		event.returnValue=false;
		document.f.ENLAREHOSCOD.focus();
  }
  else if(document.f.DIAGCOD.value != "" && document.f.DESDIAGCOD.value == ""){
		alert("Por favor valide el codigo");
		event.returnValue=false;
		document.f.DIAGCOD.focus();
  }
  else if(document.f.EMAIL.value != "" && (document.f.EMAIL.value.indexOf("@")==-1 || document.f.EMAIL.value.indexOf(".")==-1)){
		alert("Ingrese su Email p.ej. miemail@hotmail.com");
		event.returnValue=false;
		document.f.EMAIL.focus();
  }
	else{
		document.f.submit();
	}
}

function params_tipo(){
  if (document.f.TIPO.selectedIndex == "1"){
		eval('document.f.TIPO_OTRO.disabled = true');
		document.f.TIPO_OTRO.style.backgroundColor="#DFE6EE";
		eval("document.f.TIPO_OTRO.value=''");
  }
  if (document.f.TIPO.selectedIndex == "2"){
		eval('document.f.TIPO_OTRO.disabled = true');
		document.f.TIPO_OTRO.style.backgroundColor="#DFE6EE";
		eval("document.f.TIPO_OTRO.value=''");
  }
  if (document.f.TIPO.selectedIndex == "3"){
		eval('document.f.TIPO_OTRO.disabled = false');
		document.f.TIPO_OTRO.style.backgroundColor="#FFFFFF";
		document.f.TIPO_OTRO.focus();
  }
  if (document.f.TIPO.selectedIndex == "4"){
		eval('document.f.TIPO_OTRO.disabled = false');
		document.f.TIPO_OTRO.style.backgroundColor="#FFFFFF";
		document.f.TIPO_OTRO.focus();
  }
}

function params_queja(){
  if (document.f.QUEJA.selectedIndex == "5"){
		eval('document.f.QUEJA_OTRO.disabled = false');
		document.f.QUEJA_OTRO.style.backgroundColor="#FFFFFF";
		document.f.QUEJA_OTRO.focus();
  }
  if (document.f.QUEJA.selectedIndex != "5"){
		eval('document.f.QUEJA_OTRO.disabled = true');
		document.f.QUEJA_OTRO.style.backgroundColor="#DFE6EE";
		eval("document.f.QUEJA_OTRO.value=''");
  }
}

function veridigitos(dato){
	var esto = dato;
	for(i=0;i<esto.value.length;i++){
		caracter = esto.value.charCodeAt(i);//convierte a ASCII
		if(!(caracter >= 48 && caracter <= 57)){ //verifica que sean s�lo numeros
			alert("Por favor, ingrese s�lo d�gitos");
			event.returnValue=false;
			esto.value = "";
			esto.focus();
			break;
		}
	}
}

// rutina para poner los checks
imagen1=new Image
imagen1.src="../images/asterisco.gif"
imagen2=new Image
imagen2.src="../images/transparent.gif"
var i=1;
function checkasegura(){
  if (i == 1){
      document.images['CHECK1'].src=imagen2.src
      document.images['CHECK2'].src=imagen2.src
      i=2;
  }
  else{
      document.images['CHECK1'].src=imagen1.src;
      document.images['CHECK2'].src=imagen1.src;
      i=1;
  }
}
// fin de rutina para poner los checks

function corta1(forma) {
	with (forma){
	  if (document.f.DFICHEC.value.length>900)
		document.f.DFICHEC.value=document.f.DFICHEC.value.substring(0,900);
	  if (event.keyCode == 13) event.returnValue = false;
		cuenta1(forma);
	  document.f.DFICHEC.focus();
	}
}

function cuenta1(forma) {
	with(forma){
	  document.f.Cars1.value=900-document.f.DFICHEC.value.length;
	}
}

function corta2(forma) {
	with (forma){
	  if (document.f.DFICACC.value.length>500)
		document.f.DFICACC.value=document.f.DFICACC.value.substring(0,500);
	  if (event.keyCode == 13) event.returnValue = false;
		cuenta2(forma);
	  document.f.DFICACC.focus();
	}
}

function cuenta2(forma) {
	with(forma){
	  document.f.Cars2.value=500-document.f.DFICACC.value.length;
	}
}

function corta3(forma) {
	with (forma){
	  if (document.f.DFICOBS.value.length>350)
		document.f.DFICOBS.value=document.f.DFICOBS.value.substring(0,350);
	  if (event.keyCode == 13) event.returnValue = false;
		cuenta3(forma);
	  document.f.DFICOBS.focus();
	}
}

function cuenta3(forma) {
	with(forma){
	  document.f.Cars3.value=350-document.f.DFICOBS.value.length;
	}
}

function corta4(forma) {
	
	with (forma){
	  if (document.f.DFICOBS.value.length>200)
		document.f.DFICOBS.value=document.f.DFICOBS.value.substring(0,200);
	  if (event.keyCode == 13) event.returnValue = false;
		cuenta4(forma);
	  document.f.DFICOBS.focus();
	}
}

function cuenta4(forma) {
	with(forma){
	  document.f.Cars4.value=200-document.f.DFICOBS.value.length;
	}
}

function corta5(forma) { //NPONCE 02/02/2015
	
	with (forma){
	  if (document.f.DFICOBS.value.length>120)
		document.f.DFICOBS.value=document.f.DFICOBS.value.substring(0,120);
	  if (event.keyCode == 13) event.returnValue = false;
		cuenta5(forma);
	  document.f.DFICOBS.focus();
	}
}

function cuenta5(forma) { //NPONCE 02/02/2015
	with(forma){
	  document.f.Cars4.value=120-document.f.DFICOBS.value.length;
	}
}

function corta6(forma) { //NPONCE 03/02/2015
	
	with (forma){
	  if (document.f.DETALLE.value.length>200)
		document.f.DETALLE.value=document.f.DETALLE.value.substring(0,200);
	  if (event.keyCode == 13) event.returnValue = false;
		cuenta6(forma);
	  document.f.DETALLE.focus();
	}
}

function cuenta6(forma) {
	with(forma){
	  document.f.Cars4.value=200-document.f.DETALLE.value.length;
	}
}

function opwinMS(u,n,r,s,w,h,t,l) {
 
  args="width="+w+",height="+h+",resizable="+ r+ ", scrollbars="+ s + ",top=" + t + ",left="+ l + ", status=0";
  remote=window.open(u,n,args);
  if (remote != null) {
    if (remote.opener == null)
      remote.opener = self;
  }
  return remote;
}

function toDatosG(){
parent.cuerpo.location.href='../servlet/CtrlFicha?opt=27&CAS='+document.f.CAS.value+'&AYEAR='+document.f.AYEAR.value+'&CORREL='+document.f.CORREL.value;
	}
function toDatosAd(){
parent.cuerpo.location.href='../servlet/CtrlFicha?opt=24&CAS='+document.f.CAS.value+'&AYEAR='+document.f.AYEAR.value+'&CORREL='+document.f.CORREL.value;
	}
function toDatosDef(){
parent.cuerpo.location.href='../servlet/CtrlFicha?opt=25&CAS='+document.f.CAS.value+'&AYEAR='+document.f.AYEAR.value+'&CORREL='+document.f.CORREL.value;
	}
function toDatosSeg(){
	//alert("En construccion");
parent.cuerpo.location.href='../servlet/CtrlFicha?opt=26&CAS='+document.f.CAS.value+'&AYEAR='+document.f.AYEAR.value+'&CORREL='+document.f.CORREL.value;
	}
//variables globales para la lista din�mica que seran manipuladas por addLD y delLD
var lddes=new Array();
var ldcod=new Array();
var codind=0;
var desind=0;

function addLD(){
	
	var x=document.getElementById("lis");
	var aux=x.innerHTML;
	var casu=document.f.CASU.value;
	var dasu=document.f.CASU.options[document.f.CASU.selectedIndex].text;
//	var cmateria=document.f.CMATERIA.value;
	var dmateria=document.f.CMATERIA.options[document.f.CMATERIA.selectedIndex].text; 

	
	if(casu==''){
		alert('Por favor, seleccione un motivo y presione Agregar motivo');
	}
	else{
		var bool=1;
		for(var y=0;y<codind;y++){
			
			if(ldcod[y]==casu){bool=0;}
		}
		if(bool==1){
			ldcod[codind]=casu;
			codind++;
			lddes[desind]=dasu;
			desind++;
			x.innerHTML=aux+dmateria+' -> '+dasu+'<br>';
			document.f.CMATERIA.value="";
			document.f.CASU.value="";
		}else{
			alert("La opcion ya se encuentra en la lista...!");	
			document.f.CASU.value="";
		}
	}
}
function delLD(){
	//alert('delLD responde al evento click');	
	if(codind==0 && desind==0){
		alert("La lista no contiene elementos");
	}else{
		document.f.CMATERIA.value="";
		document.f.CASU.value="";
		codind--;
		desind--;
		var ld="";
		for(var i=0;i<desind;i++){
		ld=ld+lddes[i]+"<br>";	
		}
		document.getElementById("lis").innerHTML=ld;
	
	}
}	
	
function alaBD(){
	//alert("responde al click!!");
	
	
	var cmso1=document.f.CMSO.value;
	var cred1=document.f.CRED.value;
	var ccas1=document.f.CCAS.value;
	var enla1=document.f.ENLAREHOSCOD.value;
	var enle1=document.f.ENLSERVHOSCOD.options[document.f.ENLSERVHOSCOD.selectedIndex].value;
	//aqui carga el valor de tematica
	//var tema=document.f.CTEM.options[document.f.CTEM.selectedIndex].value;
	//Motivo Principal
	var mov=document.f.CMATERIA.options[document.f.CMATERIA.selectedIndex].value;
	//Detalle del motivo principal
	var detmov=document.f.CASU.options[document.f.CASU.selectedIndex].value;
	var solicitud=document.f.CMSO.options[document.f.CMSO.selectedIndex].value;

	if(cmso1==''){
		alert('Por favor, seleccione un Tipo de Solicitud');
		document.f.CMSO.focus();// && (mov=='SELECCIONA' || detmov=='SELECCIONA')
	}
	else if(solicitud=='2' || solicitud=='3' || solicitud=='4' ){
		//aqui carga el valor de tematica
		var tema=document.f.CTEM.options[document.f.CTEM.selectedIndex].value;
		if(tema=='')
		alert('Por favor, seleccione Tematica');
		document.f.CMSO.focus();
	}
	/*else if(solicitud=='3' && tema==''){
		alert('Por favor, seleccione Tematica');
		document.f.CMSO.focus();
	}
	else if(solicitud=='4' && tema==''){
		alert('Por favor, seleccione Tematica');
		document.f.CMSO.focus();
	}
	*/
	else if(solicitud=='1' || solicitud == '5' ){
		
		if(codind==0 && desind==0){
		alert('Por favor, agregue un motivo');
		document.f.CMSO.focus();
		}
	}
	/*else if(solicitud=='1' && codind==0 && desind==0){
		alert('Por favor, agregue un motivo');
		document.f.CMSO.focus();
	}
	else if(solicitud=='5' && codind==0 && desind==0){
		alert('Por favor, agregue un motivo');
		document.f.CMSO.focus();
	}*/
	else if(solicitud=='6'){
		var ctma=document.f.CTMA.value;
		var ctmc=document.f.CTMC.value;
		var sel3=document.f.sel3.value;
		
		if(ctma==''){
			alert('Por favor, seleccione motivo de No Admisibilidad');
			document.f.CTMA.focus();
		}
		
		if( ctmc==''){
			alert('Por favor, seleccione Modalidad de Conclusión ');	
			document.f.CTMC.focus();
		}
		
		if( sel3==''){
			alert('Por favor, seleccione Fecha de Conclusión ');	
			document.f.sel3.focus();
		}
	}
/*	}else if(solicitud=='6' && ctmc==''){
			alert('Por favor, seleccione Modalidad de Conclusión ');	
			document.f.CTMC.focus();
	}else if(solicitud=='6' && sel3==''){
			alert('Por favor, seleccione Fecha de Conclusión ');	
			document.f.sel3.focus();
	}*/
	
	 if(cred1==''){
		alert('Por favor, seleccione una Unidad Organica Nivel 1');
		document.f.CRED.focus();
		
	} 
	 if(ccas1==''){
		alert('Por favor, seleccione un Unidad Organica Nivel 2');
		document.f.CCAS.focus();
	}
	 if(enla1==''){
		alert('Por favor, seleccione un Area');
		//alert(tema);
		document.f.ENLAREHOSCOD.focus();
		//ad.options[Sel.ad.selectedIndex].value
	}
	 if(enle1==''){
		alert('Por favor, seleccione un Servicio');
		document.f.ENLSERVHOSCOD.focus();
	}
	else{
		if(codind>0){
			var motivos="";
			for(i=0;i<codind;i++){
				motivos=motivos+ldcod[i]+" ";
				document.f.CMOTIVOS.value=motivos;
			}
		}else{
			document.f.CMOTIVOS.value="";	
		}

		document.f.submit();
	}
}

function ponerLD(){
	//alert("Debo proceder a poner la lista...");
	var cmotivos=document.f.CMOTIVOS.value;
	var dmotivos=document.f.DMOTIVOS.value;
	if(cmotivos.length>0){
		//alert("hay motivos grabados, queda pendiente ponerlos en pantalla\n"+
			  //"La lista es %"+cmotivos+"%");
		codind=0;
		desind=0;
		ldcod=cmotivos.split(" ");
		lddes=dmotivos.split("???");
		codind=ldcod.length;
		desind=codind;
		//recuperamos cada descripcion segun cada codigo
		var txt="";
		
		for(i=0;i<codind;i++){
			//txt=txt+ldcod[i]+'<br>';	
			txt=txt+lddes[i]+'<br>';
		}
		document.getElementById("lis").innerHTML=txt;
		
		
		
	}else{
		
		//alert("No hay motivos grabados, la cadena esta vacia....\n"+
			  //"La lista es %"+cmotivos+"%");
				codind=0;
				desind=0;
		
	}
}

function cambiodis(){
	if(document.f.saberec[0].checked){
		//alert('Si esta con check');
		
		document.f.CTDIREC.disabled="";
		document.f.DFICDIDREC.disabled="";
		document.f.DFICDIDREC.style.backgroundColor="#FFFFFF";
		document.f.brec.disabled="";
		document.f.DAPEPATREC.disabled="";
		document.f.DAPEPATREC.style.backgroundColor="#FFFFFF";
		document.f.DAPEMATREC.disabled="";
		document.f.DAPEMATREC.style.backgroundColor="#FFFFFF";
		document.f.DNOMREC.disabled="";
		document.f.DNOMREC.style.backgroundColor="#FFFFFF";
		
		
		document.f.TELRECLA.disabled="";
		document.f.TELRECLA.style.backgroundColor="#FFFFFF";
		document.f.MAILRECLA.disabled="";
		document.f.MAILRECLA.style.backgroundColor="#FFFFFF";
		document.f.CELRECLA.disabled="";
		document.f.CELRECLA.style.backgroundColor="#FFFFFF";
		document.f.DIRRECLA.disabled="";
		document.f.DIRRECLA.style.backgroundColor="#FFFFFF";
		
		/*document.f.DG_RUC.disabled="";
		document.f.DG_RUC.style.backgroundColor="#FFFFFF";*/
		document.f.DG_RAZ_SOCIAL.disabled="";
		document.f.DG_RAZ_SOCIAL.style.backgroundColor="#FFFFFF";
		
	}
	if(document.f.saberec[1].checked){
		//alert('No esta con check');
		
		document.f.CTDIREC.disabled="multiple disabled";
		document.f.DFICDIDREC.disabled="disabled";
		document.f.DFICDIDREC.style.backgroundColor="#DFE6EE";
		document.f.brec.disabled="disabled";
		document.f.DAPEPATREC.disabled="disabled";
		document.f.DAPEPATREC.style.backgroundColor="#DFE6EE";
		document.f.DAPEMATREC.disabled="disabled";
		document.f.DAPEMATREC.style.backgroundColor="#DFE6EE";
		document.f.DNOMREC.disabled="disabled";
		document.f.DNOMREC.style.backgroundColor="#DFE6EE";
		
		document.f.TELRECLA.disabled="disabled";
		document.f.TELRECLA.style.backgroundColor="#DFE6EE";
		document.f.MAILRECLA.disabled="disabled";
		document.f.MAILRECLA.style.backgroundColor="#DFE6EE";
		document.f.CELRECLA.disabled="disabled";
		document.f.CELRECLA.style.backgroundColor="#DFE6EE";
		document.f.DIRRECLA.disabled="disabled";
		document.f.DIRRECLA.style.backgroundColor="#DFE6EE";
		
		/*document.f.DG_RUC.disabled="disabled";
		document.f.DG_RUC.style.backgroundColor="#DFE6EE";*/
		document.f.DG_RAZ_SOCIAL.disabled="disabled";
		document.f.DG_RAZ_SOCIAL.style.backgroundColor="#DFE6EE";
	}
	
}

function f_asignar(){
	if(document.f.asignar[0].checked){
		//alert('Si esta con check');
		document.f.getElementById('delegado').style.display = 'block';
		
	}
	if(document.f.asignar[1].checked){
		//alert('No esta con check');	
		document.f.getElementById('delegado').style.display = 'none';
	}
	
}


function cambiorec(){
	if(document.f.sabequej[0].checked){
		//alert('Si esta con check');
		document.f.CTDIQUEJA.disabled="";
		document.f.DDOCQUEJA.disabled="";
		document.f.DDOCQUEJA.style.backgroundColor="#FFFFFF";
		document.f.Button3.disabled="";
		document.f.DAPEPATQUEJA.disabled="";
		document.f.DAPEPATQUEJA.style.backgroundColor="#FFFFFF";
		document.f.DAPEMATQUEJA.disabled="";
		document.f.DAPEMATQUEJA.style.backgroundColor="#FFFFFF";
		document.f.DNOMQUEJA.disabled="";
		document.f.DNOMQUEJA.style.backgroundColor="#FFFFFF";
		
		
	}
	if(document.f.sabequej[1].checked){
		//alert('No esta con check');		
		document.f.CTDIQUEJA.disabled="multiple disabled";
		document.f.DDOCQUEJA.disabled="disabled";
		document.f.DDOCQUEJA.style.backgroundColor="#DFE6EE";
		document.f.Button3.disabled="disabled";
		document.f.DAPEPATQUEJA.disabled="disabled";
		document.f.DAPEPATQUEJA.style.backgroundColor="#DFE6EE";
		document.f.DAPEMATQUEJA.disabled="disabled";
		document.f.DAPEMATQUEJA.style.backgroundColor="#DFE6EE";
		document.f.DNOMQUEJA.disabled="disabled";
		document.f.DNOMQUEJA.style.backgroundColor="#DFE6EE";
		
}

}




