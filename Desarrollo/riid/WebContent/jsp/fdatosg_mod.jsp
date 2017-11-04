
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="util.Constantes"%>
<%@ page session="true" %>
<%@ page import="java.util.*" %>
<%@ page import="beans.WebUtil" %>
<%@ page import="beans.Util" %>
<%@ page import="clases.Ficha_t" %>
<%@ page import="clases.UsuarioSistema" %>
<%@ page import="java.util.GregorianCalendar"%>
<%@ include file="../jsp/formato_fecha.jsp" %>
<%
  UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario");
    Ficha_t F1t = (Ficha_t)session.getAttribute("objFicha_t");
	String msg = (String)request.getAttribute("msg_error");
	Vector v;
	Vector vCols;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css" />
<link href="../styles/botones.css" rel="stylesheet" media="print" type="text/css" />
<script language="JavaScript" src="../js/ubigeo.js"></script>
<script language="JavaScript" src="../js/script2.js"></script>
<script language="javascript" src="../js/format_date.js"></script>
<script language="javascript" src="../js/stm31.js"></script>
<script language="javascript" src="../js/val_campos.js"></script>
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language="javascript">

function locationServer1 (valor1, valor2) {
  parent.bot.location.href='../jsp/botREC.jsp?tipo=' + valor1 + '&num=' + valor2;
}

function getMigra1 (obj1, obj2) {
	
  document.f.DAPEPATREC.value = "";
  document.f.DAPEMATREC.value = "";
  document.f.DNOMREC.value = "";
  if (obj1.value == "" || obj2.value == "") {
	  alert ("Debe completar el tipo y n�mero de documento");
    document.f.DFICDIDREC.focus();
  }
  else if (obj1.value != "" && obj2.value != "") {
	  locationServer1(obj1.value, obj2.value);
  } 
	else {
	  alert("No hay informaci�n");
  }
}
//separador
function locationServer (valor1, valor2) {
  parent.bot.location.href='../jsp/bot.jsp?tipo=' + valor1 + '&num=' + valor2;
}

function getMigra (obj1, obj2) {
	
  document.f.DAPEPAT.value = "";
  document.f.DAPEMAT.value = "";
  document.f.DNOM.value = "";
  document.f.DDICSEX.value = "";
  document.f.NFICEDA.value = "";
	//document.f.CCAS.value = "";
	
  if (obj1.value == "" || obj2.value == "") {
	  alert ("Debe completar el tipo y n�mero de documento");
    document.f.DFICDID.focus();
  }
  else if (obj1.value != "" && obj2.value != "") {
	  locationServer(obj1.value, obj2.value);
  } 
	else {
	  alert("No hay informaci�n");
  }
}

function pasa(fun,cod){
	parent.bot.location.href='../jsp/botFicha.jsp?funcion='+fun+'&codigo='+cod;
}

function pasa2(fun,cod,cod2){
	parent.bot.location.href='../jsp/botFicha.jsp?funcion='+fun+'&codigo='+cod+'&codigo2='+cod2;
}


function inicio(){
	if(document.f.DFICDIDREC.value==""){
	document.f.saberec[1].checked="true";
	
	
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
	
	document.f.DG_RAZ_SOCIAL.disabled="disabled";
	document.f.DG_RAZ_SOCIAL.style.backgroundColor="#DFE6EE";
	}else{
		document.f.saberec[0].checked="true";
	}
	cambioPersona(); 
	cambioSector();
     
	
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
function ocultar(){
document.getElementById('delegado').style.display = 'none';
}

function mostrar(){
document.getElementById('delegado').style.display = 'block';
}




function cambioSector(){
	//de natural
	if(document.f.tipoPersona[0].checked){
		
		document.f.tipoSector[0].disabled=false;
		document.f.tipoSector[1].disabled=false;
		
		document.getElementById("idRazSocial").style.display = 'block';
	       
		document.getElementById("idDocIdentidad").value='04';

	}else{
		//de juridica
		document.f.tipoSector[0].disabled=true;
		document.f.tipoSector[1].disabled=true;
		
       document.getElementById("idRazSocial").style.display = 'none';
		
		document.getElementById("idDocIdentidad").value='1';
		
	}
}

function cambioPersona(){
	//de parte 0
	if(document.f.tipoIngreso[0].checked){
		document.f.tipoPersona[0].disabled=false;
		document.f.tipoPersona[1].disabled=false;
		cambioSector();
		//cambiarValor("");
		document.getElementById('o1').style.visibility = 'visible'; //nponce
		document.getElementById('o2').style.visibility = 'visible';
		document.getElementById('o3').style.visibility = 'visible';
		document.getElementById('o4').style.visibility = 'visible';
		document.getElementById('o5').style.visibility = 'visible';
		document.getElementById('o6').style.visibility = 'visible';
		document.getElementById('o7').style.visibility = 'visible';
		document.getElementById('o8').style.visibility = 'visible';
		document.getElementById('o9').style.visibility = 'visible';
		document.getElementById('o10').style.visibility = 'visible';
		document.getElementById('o11').style.visibility = 'visible';
		
		
		<%if(!clusuario.getPerfil().equals(Constantes.PERFIL_ESSALUD_LINEA)){ %>
			
		if(document.getElementById('o12')!=null){
				document.getElementById('o12').style.visibility = 'visible';
		
		}
		
		document.getElementById('medioIngreso').style.visibility = 'visible';
		<%}%>
		document.getElementById('o13').style.visibility = 'visible';
		document.getElementById('o14').style.visibility = 'visible';
		document.getElementById('o15').style.visibility = 'visible';
		
		
	}else {
		//de oficio 1	
		document.f.tipoPersona[0].disabled=true;
		document.f.tipoPersona[1].disabled=true;
		document.f.tipoSector[0].disabled=true;
		document.f.tipoSector[1].disabled=true;
		//cambiarValor("11");
		document.getElementById('o1').style.visibility = 'hidden'; //nponce
		document.getElementById('o2').style.visibility = 'hidden';
		document.getElementById('o3').style.visibility = 'hidden';
		document.getElementById('o4').style.visibility = 'hidden';
		document.getElementById('o5').style.visibility = 'hidden';
		document.getElementById('o6').style.visibility = 'hidden';
		document.getElementById('o7').style.visibility = 'hidden';
		document.getElementById('o8').style.visibility = 'hidden';
		document.getElementById('o9').style.visibility = 'hidden';
		document.getElementById('o10').style.visibility = 'hidden';
		document.getElementById('o11').style.visibility = 'hidden';
		
		
		<%if(!clusuario.getPerfil().equals(Constantes.PERFIL_ESSALUD_LINEA) &&  (F1t.getTipoIngreso() != null && !F1t.getTipoIngreso().equals(Constantes.TIPO_INGRESO_OPCION_OFICIO))){ %>
		
		if(document.getElementById('o12')!=null){
			document.getElementById('o12').style.visibility = 'hidden';
		}
		
		//MEDIO O VIA DE INGRESO
		 <%if(F1t.getctin().equals(Constantes.CODIGO_LIBRO_RECLAMACIONES)){ %>
			document.getElementById('medioIngreso').style.visibility = 'visible';
		<%}else{%>
			document.getElementById('medioIngreso').style.visibility = 'hidden';
		<%}%>
		<%}%>
		document.getElementById('o13').style.visibility = 'hidden';
		document.getElementById('o14').style.visibility = 'hidden';
		document.getElementById('o15').style.visibility = 'hidden';
		
		
		
		
	}
}

function tipoIngresoInicial(){
	
	var ctin = <%=F1t.getctin()%>
	var check = <%=F1t.getTipoIngreso()%>	
	
if(ctin == '10'){
	document.f.tipoIngreso[0].checked = false;
	document.f.tipoIngreso[0].disabled=true;
	document.f.tipoIngreso[1].checked = false;	
	document.f.tipoIngreso[1].disabled=true;
	
	document.f.tipoPersona[0].checked = false;
	document.f.tipoPersona[0].disabled=true;
	document.f.tipoPersona[1].checked = false;	
	document.f.tipoPersona[1].disabled=true;
	
	document.f.tipoSector[0].checked = false;
	document.f.tipoSector[0].disabled=true;
	document.f.tipoSector[1].checked = false;	
	document.f.tipoSector[1].disabled=true;
	
	//alert("1");
}else{	
	
	if (check != null){
	
		if(check == '0'){ //DE PARTE
		//	alert("2");	
			document.f.tipoIngreso[0].checked = true;
			document.f.tipoIngreso[1].checked = false;
			
			//MEDIO O VIA DE INGRESO
			document.getElementById('medioIngreso').style.visibility = 'visible';
		}else{
			//alert("3");
			// DE OFICIO
			document.f.tipoIngreso[0].checked = false;
			document.f.tipoIngreso[1].checked = true;
			
			//MEDIO O VIA DE INGRESO
			document.getElementById('medioIngreso').style.visibility = 'hidden';
		}
	}
}	
	
	
}


</script>
</head>
<%
try {
%>
<body class="body" onload="tipoIngresoInicial();inicio();MM_preloadImages('../images/fdatosadon.JPG','../images/fdatosdefon.JPG','../images/fdatossegon.JPG'); " bgproperties="fixed">
<form action="../servlet/CtrlFicha" method="post" name="f" onsubmit="return registroMod(this)">
 <table class="banner01" >
    <tr>
      <td width="220" rowspan="5" align="center"><img src="../images/es03.jpg" width="120" height="100"/></td>
      <td align="center"><%@include file="../js/menu_all.js" %></td>
      <td width="220" rowspan="8" align="right"><img src="../images/defensoria01.jpg" width="120" height="100"/></td>
    </tr>
    <tr>
      <td align="center" class="titulob1">&nbsp;</td>
    </tr>
    <tr align="center">
      <td>&nbsp;</td>
    </tr>
    <tr align="center">
      <td align="center" class="titulob2">REGISTRO INFORM�TICO DE ATENCI�N AL ASEGURADO</td>
    </tr>
    <tr align="center">
      <td align="center" class="titulob1">&nbsp;</td>
    </tr>
    <tr align="center">
      <td align="center" class="celdasb"><%= clusuario.getDescripcion() %></td>
      <input type="hidden" name="perfiles" value="<%=clusuario.getPerfil() %>" />
      <td rowspan="3" class="titulo4">
      
      	<table width="720" align="center">
        	<tr>
        	<% if (!clusuario.getPerfil().equals(Constantes.ESSALUD_EN_LINEA)){%>
            	<td><img src="../images/uno.jpg" width="160" height="50"/></td>
                <td><a href="javascript:toDatosAd();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('b2','','../images/dos0.jpg',1)"><img src="../images/dos.jpg "  name="b2" width="160" height="50" border="0" id="b2" /></a></td>
                <td><a href="javascript:toDatosDef();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('b3','','../images/tres0.jpg',1)"><img src="../images/tres.jpg"  name="b3" width="160" height="50" border="0" id="b3" /></a></td>
                <td><a href="javascript:toDatosSeg();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('b4','','../images/cuatro0.jpg',1)"><img src="../images/cuatro.jpg"  name="b4" width="160" height="50" border="0" id="b4" /></a></td>
                <%} %>
            </tr>
        </table>
      
      </td>
    </tr>
    <tr align="center">
      <td align="center" class="celdasb"><%= clusuario.getdPerfil() %></td>
    </tr>
    <tr align="center">
      <td align="center" class="celdasb"><%= clusuario.getdCas() %></td>
    </tr>
  </table>
  <table width="100%">
    <tr><td align="center" class="titulo_gerencia" >Gerencia Central de Atenci�n al Asegurado</td></tr>
  </table>
  <table width="100%" border="0" align="center" >
     <tr>
          <td>
            <input type="hidden" name="CAS" value="<%=F1t.getcas()!=null?F1t.getcas():""%>" />
            <input type="hidden" name="AYEAR" value="<%=F1t.getayear()!=null?F1t.getayear():""%>" />
            <input type="hidden" name="CORREL" value="<%=F1t.getcorrel()!=null?F1t.getcorrel():""%>" />
          </td>
          <td align="left" class="titulo3" width="33%"><%= Constantes.NOM_DELEGADO %> <%=StringUtils.defaultString(F1t.getDescDelegado())%></td>
          <td align="center" class="titulo3" width="34%"><%= Constantes.OFICINA %> <%= StringUtils.defaultString(F1t.getDescRedDelegado())%></td>
          <td align="right" class="titulo3" width="33%"><%= Constantes.NRO_EXPE %> <%=F1t.getayear()!=null?F1t.getayear():""%>-<%=F1t.getcas()!=null?F1t.getcas():""%>-<%=F1t.getcorrel()!=null?F1t.getcorrel():""%>&nbsp;&nbsp;</td>
     </tr>
     <tr>
          <td></td>
          <td align="left" class="titulo3" width="33%"></td>
          <td align="center" class="titulo3" width="34%"></td>
          <td align="right" class="titulo3" width="33%"><%=StringUtils.isNotEmpty(F1t.getfusucrea())?Constantes.FECHA_REGISTRO:"" %> <%= StringUtils.defaultString(F1t.getfusucrea()) %> </td>
     </tr>
     <tr>
          <td></td>
          <td align="left" class="titulo3" width="33%"></td>
          <td align="center" class="titulo3" width="34%"></td>
          <td align="right" class="titulo3" width="33%"><%= StringUtils.isNotEmpty(F1t.getfusumodi())?Constantes.FECHA_ULTIMA:"" %> <%= StringUtils.defaultString(F1t.getfusumodi()) %> </td>
     </tr>
  </table>
<br />

  <table width="85%"  border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#007AC9" bgcolor="#ffffff" class="celdas">
    <tr>
      <td><table width="100%"  border="0" align="right" cellpadding="0" cellspacing="0">

	  <tr>
          <td>&nbsp;</td>
          <td colspan="3" height="50px">
          <label style="font-weight: bold;font-size: 15px;"><%= Constantes.TIPO_PARTE_OFICIO %> </label> <br/>
		  DE PARTE<input type="radio"  name="tipoIngreso" value="0" onclick="cambioPersona(this);"  />&nbsp;&nbsp;&nbsp;
		  DE OFICIO<input type="radio" name="tipoIngreso" value="1" onclick="cambioPersona(this);"  /></td>
          <td>&nbsp;</td>
        </tr>
          
        
        <tr id="tp_per">
          <td>&nbsp;</td>
          
          <td colspan="3" height="50px">
		  <%= Constantes.TIPO_PERSONA_JURIDICA %><input type="radio" name="tipoPersona" onclick="cambioSector(this);" value="1" <%= F1t.getTipoPersona()!=null && StringUtils.equals(F1t.getTipoPersona(), "1")? "checked":"" %> />&nbsp;&nbsp;&nbsp;
		  <%= Constantes.TIPO_PERSONA_NATURAL %><input type="radio" name="tipoPersona" value="0" onclick="cambioSector(this);" <%= F1t.getTipoPersona()!=null && StringUtils.equals(F1t.getTipoPersona(), "0")? "checked":"" %>/></td>
          <td>&nbsp;</td>
        </tr>
        
        
        <tr id="tp_sec">
          <td>&nbsp;</td>
          
          <td colspan="3" height="50px">
		  <%= Constantes.TIPO_SECTOR_PUBLICO %><input type="radio" name="tipoSector" value="1" <%= F1t.getTipoSector()!=null && StringUtils.equals(F1t.getTipoSector(), "1")? "checked":"" %>/>&nbsp;&nbsp;&nbsp;
		  <%= Constantes.TIPO_SECTOR_PRIVADO %><input type="radio" name="tipoSector" value="0" <%= F1t.getTipoSector()!=null && StringUtils.equals(F1t.getTipoSector(), "0")? "checked":"" %>/></td>
          <td>&nbsp;</td>
        </tr>
		

        <tr>
          <td>&nbsp;</td>
          <td colspan="3" height="50px"><label style="font-weight: bold;font-size: 15px;"><%= Constantes.TITULO_SOLICITANTE %></label><br /> <%= Constantes.DESCRIPTI_SOLICITANTE %>
		  	&nbsp;Si
		  	<input type="radio" name="saberec" value="1" onclick="cambiodis();"/>&nbsp;&nbsp;&nbsp;No<input  type="radio" name="saberec" value="0" onclick="cambiodis();"/></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td height="25">&nbsp;</td>
          <td colspan="3"><%= Constantes.DOCUMENTO_IDENTIDAD %>
            <select   id="idDocIdentidad"   name="CTDIREC" class="celdas01" onfocus="nextfield ='DFICDID';">
              <option value="">SELECCIONA</option>
              
              <%
						v =  (Vector) request.getAttribute ("vDocumento");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
					<% if(F1t.getctdirec()!=null && !F1t.getctdirec().equals("")) {%>
              
              <option value="<%=vCols.get(0)%>" <%= F1t.getctdirec().equals((String)vCols.get(0))?"SELECTED":"" %>><%= vCols.get(1)%> </option>
              <%} else { %>
              <option value="<%=vCols.get(0)%>" <%= "1".compareTo(vCols.get(0))==0?"SELECTED":"" %>><%= vCols.get(1)%> </option>
              
              
              <% }
							}
						}
					%>
            </select>
            <%= Constantes.NUMERO %>
            <input name="DFICDIDREC" type="text" size="15" maxlength="15" class="celdas01" onfocus="nextfield ='SOLICITUD';" onchange="veridigitos(this);" value="<%=F1t.getdficdidrec()!=null?F1t.getdficdidrec():""%>" />
            <input name="brec" type="button" class="boton4" onclick="getMigra1(document.f.CTDIREC, document.f.DFICDIDREC);" value=" Buscar "  /> &nbsp;&nbsp;
            </td>
   			</tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td width="33%"><%= Constantes.APE_PATERNO %>&nbsp;&nbsp;
            <input name="DAPEPATREC" type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='DAPEMATREC';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" value="<%=F1t.getdapepatrec()!=null?F1t.getdapepatrec():""%>" style="width: 182px; " /></td>
          <td width="33%"><%= Constantes.APE_MATERNO%>
            <input name="DAPEMATREC" type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='DNOMREC';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" value="<%=F1t.getdapematrec()!=null?F1t.getdapematrec():""%>" /></td>
          <td width="34%"><%= Constantes.NOM %>
            <input name="DNOMREC" type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='DNOMREC';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01"  value="<%=F1t.getdnomrec()!=null?F1t.getdnomrec():""%>"/></td>
          <td>&nbsp;</td>
        </tr>
        
         
        
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>      
         	<td>&nbsp;</td> 	        	
          	<td><%= Constantes.TELEFONO1 %> <b></b>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
          	<input name="TELRECLA" type="text" onkeypress="return soloNumeros(event)" size="20" maxlength="7" value="<%=F1t.getReclaTel()!=null?F1t.getReclaTel():""%>" style="width: 182px; "/></td>          	
          	<td><%= Constantes.CORREO_ELECT %>
          	<input  onchange="cambia(this);"  type="text" name="MAILRECLA" size="20" maxlength="50" value="<%=F1t.getReclaMail()!=null?F1t.getReclaMail():""%>" style="width: 232px; "/></td>
          	<!--  <td>&nbsp;</td>-->
          	<td colspan="4"><%= Constantes.DIRECCION %>
          	<input  type="text" name="DIRRECLA"  onchange="cambia(this);" size="80" maxlength="500" class="celdas01" value="<%=F1t.getReclaDir()!=null?F1t.getReclaDir():""%>" style="width: 376px; "></td>
         </tr>        
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>      
         	<td>&nbsp;</td> 
         	<td><%= Constantes.TELEFONO2%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         	<input type="text" size="30" name="CELRECLA" maxlength="9" onkeypress="return soloNumeros(event)" value="<%=F1t.getReclaCel()!=null?F1t.getReclaCel():""%>" style="width: 182px; "></td>		        	
         	
         	<%-- <td><%= Constantes.DAT_GEN_RUC %> <b></b>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
          		<input name="DG_RUC" type="text" onkeypress="return soloNumeros(event)"  onfocus="" size="20" maxlength="11" value="" style="width: 182px; "></td>          	 --%>
          	<td id="idRazSocial"  style="display: none;" ><%= Constantes.DAT_GEN_RAZ_SOCIAL %>
          		<input name="DG_RAZ_SOCIAL" type="text" onfocus="" size="80" maxlength="50" value="<%=F1t.getrazsoc()!=null?F1t.getrazsoc():""%>"  style="width: 224px; "   /></td>
          	<td colspan="2">&nbsp;</td>
         	<td>&nbsp;</td>
         </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><hr color="#007ac9" /></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3" height="30px"><label style="font-weight: bold;font-size: 15px;"><%= Constantes.TITULO_INVOLUCRADO %></label>  </td>
          <td>&nbsp;</td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><%= Constantes.DOCUMENTO_IDENTIDAD %><strong id="o1">*</strong>
              <select name="CTDI" class="celdas01" onfocus="nextfield ='DFICDID';">
                <option value="">SELECCIONA</option>
                
					<%
						v =  (Vector) request.getAttribute ("vDocumento");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
								<option value="<%=vCols.get(0)%>" <%= F1t.getctdi()!=null && F1t.getctdi().equals(vCols.get(0))?"SELECTED":"" %>><%= vCols.get(1)%> </option>
          <%
							}
						}
					%>
              </select>
            <%= Constantes.NUMERO %>
            <input name="DFICDID" type="text" onfocus="nextfield ='DAPEPAT';" onchange="veridigitos(this);" size="15" maxlength="15"
            value="<%=F1t.getdficdid()!=null?F1t.getdficdid():""%>" />
						<input name="Button" type="button" class="boton4" onclick="getMigra(document.f.CTDI, document.f.DFICDID);" value="Buscar"/></td>
		      <td>&nbsp;</td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
        	<td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><%= Constantes.APE_PATERNO%><strong id="o2">*</strong>
            <input name="DAPEPAT" readonly="readonly" type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='DAPEMAT';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" value="<%=F1t.getdapepat()!=null?F1t.getdapepat():""%>" />
          </b></td>
          <td><%= Constantes.APE_MATERNO %><strong id="o3">*</strong>
            <input name="DAPEMAT" readonly="readonly" type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='DNOM';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" value="<%=F1t.getdapemat()!=null?F1t.getdapemat():""%>" />
          </b></td>
          <td><%= Constantes.NOM%><strong id="o4">*</strong>
              <input name="DNOM" readonly="readonly" type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='EDAD';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" value="<%=F1t.getdnom()!=null?F1t.getdnom():""%>" style="width: 239px; "/>
          </b></td>
		      <td>&nbsp;</td>
          </tr>
        <tr>
        	<td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
		      <td>&nbsp;</td>
          </tr>
        <tr>
        	<td>&nbsp;</td>
          <td><%= Constantes.SEXO %><strong id="o5">*</strong>
            <select name="DDICSEX" class="celdas01" >
              <option value="">SELECCIONA</option>
              <option value="M" <%=(("M".toString().equals(F1t.getddicsex()==null?"":F1t.getddicsex()))?"selected":"")%>>MASCULINO</option>
              <option value="F" <%=(("F".toString().equals(F1t.getddicsex()==null?"":F1t.getddicsex()))?"selected":"")%>>FEMENINO</option>
            </select></td>
          <td><%= Constantes.EDAD %><strong id="o6">*</strong>
            <input name="NFICEDA" type="text" size="3" maxlength="3" class="celdas01" onfocus="nextfield ='DEPARTAMENTO';" onchange="veridigitos(this);" value="<%=F1t.getnficeda()!=null?F1t.getnficeda():""%>" /></td>
          <td><%= Constantes.TELEFONO1 %> <b></b>
            <input name="DFICTEL" type="text" class="celdas01" onkeypress="return soloNumeros(event)" onfocus="nextfield ='EMAIL';" onchange="veridigitos(this);" size="15" maxlength="20" value="<%=F1t.getdfictel()!=null?F1t.getdfictel():""%>" /></td>
		      <td>&nbsp;</td>
          </tr>
        <tr>
        	<td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
		      <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><%= Constantes.DEPARTAMENTO %><strong id="o7">*</strong>
            <select name="DEPARTAMENTO" class="celdas01" onfocus="nextfield ='PROVINCIA';" onchange="pasa('CPRO',this.form.DEPARTAMENTO.options[this.form.DEPARTAMENTO.selectedIndex].value);">
           <option value="<%=F1t.get2ubigeo()!=null?F1t.get2ubigeo():""%>" selected="selected"><%=F1t.getubitdpt()!=null?F1t.getubitdpt():""%></option>
              <%
								v =  (Vector) request.getAttribute ("vDepartamento");
								if (v != null) {  
									for (int i = 0; i < v.size(); i++) {  
										vCols = (Vector) v.get(i);  
							%>
              <option value="<%=vCols.get(0)%>" <%= Util.jnvl((String)request.getParameter("DEPARTAMENTO"),"").compareTo(vCols.get(0))==0?"SELECTED":"SELECCIONA" %>><%= vCols.get(1)%> </option>
              <%
									}
								}
							%>
            </select>
           
            
			</td>
          <td><%= Constantes.PROVINCIA %><strong id="o8">*</strong>
              <select name="PROVINCIA" class="celdas01" onfocus="nextfield ='DISTRITO';" onchange="pasa('CDIS',this.form.PROVINCIA.options[this.form.PROVINCIA.selectedIndex].value);">
                <option value="<%=F1t.get4ubigeo()!=null?F1t.get4ubigeo():""%>"><%=F1t.getubitprv()!=null?F1t.getubitprv():""%></option>
              <%
								v =  (Vector) request.getAttribute ("vProvincia");
								if (v != null) {  
									for (int i = 0; i < v.size(); i++) {  
										vCols = (Vector) v.get(i);  
							%>
              <option value="<%=vCols.get(0)%>" <%= Util.jnvl((String)request.getParameter("PROVINCIA"),"").compareTo(vCols.get(0))==0?"SELECTED":"SELECCIONA" %>><%= vCols.get(1)%> </option>
              <%
									}
								}
							%>
            </select>
                        
            </td>
          <td><%= Constantes.DISTRITO %><strong id="o9">*</strong>
              <select name="CFICUBIGEO" class="celdas01" onfocus="nextfield ='DATOS';">
                <option value="<%=F1t.getcficubigeo()%>"><%=F1t.getubitdtr()%></option>
              <%
								v =  (Vector) request.getAttribute ("vDistrito");
								if (v != null) {  
									for (int i = 0; i < v.size(); i++) {  
										vCols = (Vector) v.get(i);  
							%>
              <option value="<%=vCols.get(0)%>" <%= Util.jnvl((String)request.getParameter("CFICUBIGEO"),"").compareTo(vCols.get(0))==0?"SELECTED":"SELECCIONA" %>><%= vCols.get(1)%> </option>
              <%
									}
								}
							%>
            </select>
					</td>
		      <td>&nbsp;</td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
        	<td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><%= Constantes.DIRECCION %><strong id="o10">*</strong>
              <input name="DFICDIR" type="text" class="celdas01" onfocus="nextfield ='TELEFONO';" onchange="cambia(this);" size="45" maxlength="120" value="<%=F1t.getdficdir()!=null?F1t.getdficdir():""%>" /></td>
          <td colspan="2"><%= Constantes.CORREO_ELECT %>
            <input  name="DFICMAIL" type="text" class="celdas01" onfocus="nextfield ='PRESTACION';" onchange="cambia(this);" size="23" maxlength="30" value="<%=F1t.getdficmail()!=null?F1t.getdficmail():""%>" /></td>
          <td>&nbsp;</td>
          
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><%= Constantes.TIPO_PRESTACIONES %><strong id="o11">*</strong>
            <select name="CTPR" class="celdas01" onfocus="nextfield ='CSEGURO';">
              <option value="">SELECCIONA</option>
              <%
						v =  (Vector) request.getAttribute ("vPrestacion");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
              <option value="<%=vCols.get(0)%>" <%= F1t.getctpr()!=null && StringUtils.equals(F1t.getctpr(), (String)vCols.get(0))?"SELECTED":"" %>><%=vCols.get(1)%> </option>
              <%
							}
						}
					%>
            </select>          </td>
          <td><%= Constantes.TIPO_SEGURO %>
            <input type="hidden" name="CFICTIPSEG" value="<%= F1t.getcfictipseg() != null ? F1t.getcfictipseg() : ""%>"/>
             
            <input type="text" name="DFICTIPSEG"  readonly="readonly" value="<%= F1t.getDfictipseg() != null ? F1t.getDfictipseg() : ""%>"/>
			</td>        
        
            
           <td colspan="2" id="medioIngreso">
             <%if(!clusuario.getPerfil().equals(Constantes.PERFIL_ESSALUD_LINEA)){ %>
							<%= Constantes.TIPO_INGRESO %><strong id="o12">*</strong>
							<select name="CTIN" class="celdas01" onchange="cambioCtin();" >
              				<option value="">SELECCIONE</option>
					<%
						v =  (Vector) request.getAttribute ("vTingreso");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
								<option value="<%=vCols.get(0)%>" <%= StringUtils.equals(F1t.getctin(), (String)vCols.get(0))?"SELECTED":"" %>><%= vCols.get(1)%></option>
					<%
							}
						}
					%>
			</select>
			<% } else {%>
			<input type="hidden" name="CTIN" value="<%=F1t.getctin()%>"/><%= Constantes.TIPO_INGRESO %> <%= F1t.getdtin() %> 
			<%} %>
			</td>
            <td>&nbsp;</td>
          </tr>        
          
           <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        
        <tr>
	          <td>&nbsp;</td>
	          <td colspan="3" height="30px"><label style="font-weight: bold;font-size: 15px;"><%=Constantes.DATOS_PRESTADOR %></label>  </td>
	          <td>&nbsp;</td>
          </tr>
          
          <tr>
          <td>&nbsp;</td>
          <td><%= Constantes.UNIDADORG %><strong id="o13">*</strong>
            <select name="CRED" class="celdas01" onfocus="nextfield ='CCAS';" onchange="pasa('CASC',this.form.CRED.options[this.form.CRED.selectedIndex].value);" style="width: 184px; ">
              <option value="">SELECCIONA</option>
              <%				
								v =  (Vector) request.getAttribute ("vRed");
								if (v != null) {  
									for (int i = 0; i < v.size(); i++) {  
										vCols = (Vector) v.get(i);
									
							%>
							
									<option value="<%=vCols.get(0)%>" <%= F1t.getcred()!= null && F1t.getcred().equals(vCols.get(0).toString()) ? "SELECTED":""%> ><%= vCols.get(1)%> </option>
									<%
									}
								}
							%>
            </select></td>
          <td><%= Constantes.UNIDADORG1%><strong id="o14">*</strong>
            <select name="CCAS" class="celdas01" >
              <option value="">SELECCIONA</option>
					<%
						v =  (Vector) request.getAttribute ("vCas");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
              <option value="<%=vCols.get(0)%>" <%= F1t.getccas()!= null && F1t.getccas().equals(vCols.get(0).toString()) ?"SELECTED":"" %>><%= vCols.get(1)%></option>
              <%
							}
						}
					%>
            </select></td>
         </tr>

       

        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><hr color="#007ac9" /></td>
          <td>&nbsp;</td>
        </tr>
        
        <input  type="hidden" name="CMSO" value="<%= F1t.getcmso()!= null && !F1t.getcmso().equals("")?F1t.getcmso():""%>"/>
        
        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><%= Constantes.DESCRIPCION %><strong id="o15">*</strong></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td colspan="3" align="center"><textarea name="DFICHEC" class="celdas01" onchange="cambia(this);" onfocus="nextfield ='CODIGO';" onkeypress="corta1(this);" onkeyup="corta1(this);" cols="160" rows="10"><%=F1t.getdfichec()!=null?F1t.getdfichec():""%></textarea>
            <input name="Cars1" type="text" class="celdas01" onfocus="blur();" value="900" size="3" maxlength="3" /></td>
            <td>&nbsp;</td>
          </tr>
         <tr>
          <td>&nbsp;</td>
          <td colspan="3"><hr color="#007ac9" /></td>
          <td>&nbsp;</td>
        </tr>
   <% if (clusuario.getPerfil().equals(Constantes.PERFIL_COORDINADOR))
   	  {%>
     <tr>
        <td>&nbsp;</td>
		<td><label style="font-weight: bold;font-size: 15px;">Asignar: </label></td>
		<td>&nbsp;</td>
	 </tr>
	 <tr>
          <td>&nbsp;</td>
          <td colspan="3">Desea asignar?
		  	&nbsp;Si
		  	<input type="radio" id="asignaSi" name="asignar" value="1" onclick="mostrar();" />
		  	&nbsp;&nbsp;No
		  	<input type="radio" id="asignaNo" name="asignar" checked="checked" value="0" onclick="ocultar();"/>		  
		  </td>
          <td>&nbsp;</td>
        </tr>
     <tr>
          <td colspan="3" >&nbsp;</td>
          <td  >&nbsp;</td>
          <td>&nbsp;</td>
     </tr>
     <tr>
     <td colspan="5">
     <table>
     <tr id='delegado' style="display:none;">
     
          <td colspan="2">&nbsp;</td>
          <td><%= Constantes.UNIDADORG %><b></b>
          <select name="CRED1" class="celdas01"  style="width: 206px; " onfocus="nextfield ='CCAS1';" onchange="pasa('CASC1',this.form.CRED1.options[this.form.CRED1.selectedIndex].value);">
         
              <option value="" selected="selected">SELECCIONA</option>
              <%
								v =  (Vector) request.getAttribute ("vRed");
								if (v != null) {  
									for (int i = 0; i < v.size(); i++) {  
										vCols = (Vector) v.get(i);  
							%>
									<option value="<%=vCols.get(0)%>" <%= F1t.getRdelegado() != null && F1t.getRdelegado().equals(vCols.get(0))?"SELECTED":"" %>><%= vCols.get(1)%> </option>
									<%
									}
								}
							%>
            </select></td>
            
            <td><%= Constantes.UNIDADORG1%>
            <select name="CCAS1" class="celdas01" style="width: 206px; " onfocus="nextfield ='CDELEGADO';" onchange="pasa2('CDEL',this.form.CRED1.options[this.form.CRED1.selectedIndex].value,this.form.CCAS1.options[this.form.CCAS1.selectedIndex].value);">
              <option value="">SELECCIONA</option>
					<%
						v =  (Vector) request.getAttribute ("vCas1");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
              <option value="<%=vCols.get(0)%>" <%= F1t.getCasdelegado()!= null && F1t.getCasdelegado().equals(vCols.get(0).toString()) ?"SELECTED":"" %>><%= vCols.get(1)%></option>
              <%
							}
						}
					%>
            </select></td>
            
            
          <td><%= Constantes.DELEGADO %>
            <select name="CDELEGADO" class="celdas01" style="width: 206px; " >
              <option value="">SELECCIONA</option>
					<%
						v =  (Vector) request.getAttribute ("vDelegado");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
              <option value="<%=vCols.get(0)%>" <%= F1t.getCdelegado() != null && F1t.getCdelegado().equals(vCols.get(0))?"SELECTED":"" %>><%= vCols.get(1)%></option>
              <%
							}
						}
					%>
            </select></td>
         </tr>
         </table>
         
         </td>
     </tr>
       <%} %>
   
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
          </table>
          
          	  </td>
	</tr>
	
  </table>
          
  <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="25%"><%= Constantes.INFO_OBLI %></td>
      <td align="center">
				<input type="hidden" name="opt" value="2"/>
        <input type="hidden" name="caso" value="M"/>
        
	  </td>
      <td width="25%">&nbsp;</td>
    </tr>
    
    <tr>
      <td colspan="1" align="right">
				
				</td>

	<td colspan="1" align="center">
				
				<% if(F1t.getbestadoreg()!=null) { %>
					<%	if(F1t.getbestadoreg().equals(Constantes.ESTADO_ACTIVADO))	{	%>
						
						<% if(clusuario.getPerfil().equals(Constantes.PERFIL_COORDINADOR) ) { %>
						    <input name="Button2" type="submit" class="boton4" value=" Modificar "/>&nbsp;
	
						<% } else if(clusuario.getPerfil().equals(Constantes.PERFIL_DELEGADO_DEFENSORIAL) && StringUtils.equals(clusuario.getUsuario(), F1t.getCdelegado())) { %>
							<input name="Button2" type="submit" class="boton4" value=" Modificar "/>&nbsp;

						<% } else if(clusuario.getPerfil().equals(Constantes.PERFIL_ESSALUD_LINEA) && F1t.getctin().equals(Constantes.PERFIL_ESSALUD_LINEA)) { %>
							<input name="Button2" type="submit" class="boton4" value=" Modificar "/>&nbsp;

						<%} %>
						
					<%} else if(F1t.getbestadoreg().equals(Constantes.ESTADO_INACTIVO)){ %>
						
						<% if(clusuario.getPerfil().equals(Constantes.PERFIL_COORDINADOR) ) { %>
						    <input name="Button2" type="submit" class="boton4" value=" Modificar "/>&nbsp;
						<% } else if(clusuario.getPerfil().equals(Constantes.PERFIL_DELEGADO_DEFENSORIAL)) { %>
							
						<% } else if(clusuario.getPerfil().equals(Constantes.PERFIL_ESSALUD_LINEA)) { %>
							
						<%} %>
						
					<%} %>
				<%} %>
				<input name="Button3" type="button" class="boton4" value=" Imprimir " onclick="window.print();"/>
				&nbsp;</td>
				

	            
    </tr>
    
    <tr>
      <td colspan="3" align="center" class="titulo4">
        <% if(msg != null){ %>
        <%= msg %>
        <% } %>
      </td>
    </tr>
  </table>
  <input type="hidden" name="ORIRECLA" value="<%=StringUtils.defaultString(F1t.getOriRecla()) %>"/>
</form>
</body>
<% 
  session.removeAttribute("obj");
  } 
  catch (NullPointerException e) {
		System.out.println("NullPointerException[fdatosg_mod.jsp]: " + e);
    request.setAttribute("msg_error", "Error al mostrase la pagina: " + e); 
    WebUtil.goForward(getServletContext(), request, response, "/error/error.jsp"); 
  }
	finally{
	  F1t = null;
	}
%>
</html>
