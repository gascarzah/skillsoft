<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
  Ficha_t F1t = (Ficha_t)session.getAttribute("obj");
	String msg = (String)request.getAttribute("msg_error");
	
	Vector v;
	Vector vCols;
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>EsSalud - Defensor&iacute;a del Asegurado</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css"/>
<link href="../styles/botones.css" rel="stylesheet" media="print" type="text/css"/>
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
	  alert ("Debe completar el tipo y número de documento");
    document.f.DFICDIDREC.focus();
  }
  else if (obj1.value != "" && obj2.value != "") {
	  locationServer1(obj1.value, obj2.value);
  } 
	else {
	  alert("No hay información");
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
	  alert ("Debe completar el tipo y número de documento");
    document.f.DFICDID.focus();
  }
  else if (obj1.value != "" && obj2.value != "") {
	  locationServer(obj1.value, obj2.value);
  } 
	else {
	  alert("No hay información");
  }
}

function pasa(fun,cod){
	parent.bot.location.href='../jsp/botFicha.jsp?funcion='+fun+'&codigo='+cod;
}



function inicio(){
	document.f.saberec[1].checked="checked";
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
	
	
	document.f.tipoSector[0].disabled="disabled";
	document.f.tipoSector[1].disabled="disabled";
	document.f.tipoPersona[0].disabled="disabled";
	document.f.tipoPersona[1].disabled="disabled";
	
	
	
	/*var elemento3 = document.getElementById("tp_per");
	elemento3.style.display = 'none';
	var elemento4 = document.getElementById("tp_sec");
	elemento4.style.display = 'none';*/
	
	
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
	
	cambioPersona();
	//21/02/2015
	

}



function ocultaSector(){
	//de natural
	if(document.f.tipoPersona[0].checked){
		//oculte campo razon social
	     document.getElementById("idRazSocial").style.display = 'none';
		

	}else{
		//de juridica
		 document.getElementById("idRazSocial").style.display = 'block';
		
	}
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
	//de parte
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
		document.getElementById('o12').style.visibility = 'visible';
		<%}%>
		document.getElementById('o13').style.visibility = 'visible';
		document.getElementById('o14').style.visibility = 'visible';
		document.getElementById('o15').style.visibility = 'visible';
		//MEDIO O VIA DE INGRESO
		document.getElementById('medioIngreso').style.visibility = 'visible';
	}else {
		//de oficio	
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
		<%if(!clusuario.getPerfil().equals(Constantes.PERFIL_ESSALUD_LINEA)){ %>
		document.getElementById('o12').style.visibility = 'hidden';
		<%}%>
		document.getElementById('o13').style.visibility = 'hidden';
		document.getElementById('o14').style.visibility = 'hidden';
		document.getElementById('o15').style.visibility = 'hidden';
		//MEDIO O VIA DE INGRESO
		document.getElementById('medioIngreso').style.visibility = 'hidden';
	}
}



</script>
</head>
<%
try {
%>
<body class="body" onload="inicio();" bgproperties="fixed">
<form action="../servlet/CtrlFicha" method="post" name="f" onsubmit="return registro(this)">
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
      <td align="center" class="titulob2">REGISTRO INFORMATICO DE INTERVENCION DEFENSORIAL</td>
    </tr>
    <tr align="center">
      <td align="center" class="titulob1">&nbsp;</td>
    </tr>
    <tr align="center">
      <td align="center" class="celdasb"><%= clusuario.getDescripcion() %></td>
      <td rowspan="3" class="titulo4">
      	<table width="100">
      	<input type="hidden" name="perfiles" value="<%=clusuario.getPerfil() %>" />
        	<tr>
        			<% if (!clusuario.getPerfil().equals(Constantes.ESSALUD_EN_LINEA)){%>
						<td><img src="../images/uno.jpg" width="160" height="50"/></td>
						<td><img src="../images/dos.jpg" width="160" height="50"/></td>
						<td><img src="../images/tres.jpg" width="160" height="50"/></td>
						<td><img src="../images/cuatro.jpg" width="160" height="50"/></td>
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
  
  <table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
    
    <tr>
      <td>&nbsp;</td>
    </tr>
  </table>
  <table width="85%"  border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#007AC9" bgcolor="#ffffff" class="celdas">
    <tr>
      <td><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td>&nbsp;</td>
          <td colspan="3" height="50px">
          <label style="font-weight: bold;font-size: 15px;"><%= Constantes.TIPO_PARTE_OFICIO %> </label> <br/>
		  DE PARTE<input type="radio" name="tipoIngreso" value="0"   onclick="cambioPersona(this);"/>&nbsp;&nbsp;&nbsp;
		  DE OFICIO<input type="radio" name="tipoIngreso" value="1"  onclick="cambioPersona(this);"/></td>
          <td>&nbsp;</td>
        </tr>
          
        
        <tr id="tp_per">
          <td>&nbsp;</td>
          
          <td colspan="3" height="50px">
		  <%= Constantes.TIPO_PERSONA_JURIDICA %><input type="radio" name="tipoPersona" onclick="cambioSector(this);" value="1" />&nbsp;&nbsp;&nbsp;
		  <%= Constantes.TIPO_PERSONA_NATURAL %><input type="radio" name="tipoPersona" value="0" onclick="cambioSector(this);"/></td>
          <td>&nbsp;</td>
        </tr>
        
        
        <tr id="tp_sec">
          <td>&nbsp;</td>
          
          <td colspan="3" height="50px">
		  <%= Constantes.TIPO_SECTOR_PUBLICO %><input type="radio" name="tipoSector" value="1" />&nbsp;&nbsp;&nbsp;
		  <%= Constantes.TIPO_SECTOR_PRIVADO %><input type="radio" name="tipoSector" value="0"  /></td>
          <td>&nbsp;</td>
        </tr>
        
      	<tr>
          <td>&nbsp;</td>
          <td colspan="3" height="50px"><label style="font-weight: bold;font-size: 15px;"><%= Constantes.TITULO_SOLICITANTE %> </label><br /> <%= Constantes.DESCRIPTI_SOLICITANTE %> 
		  Si<input type="radio" name="saberec" value="1" onclick="cambiodis(this);"/>&nbsp;&nbsp;&nbsp;No<input type="radio" name="saberec" value="0" onclick="cambiodis(this);" />            </td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td height="32">&nbsp;</td>
          <td colspan="3" valign="middle"><%= Constantes.DOCUMENTO_IDENTIDAD %>
            <select  id="idDocIdentidad" name="CTDIREC" class="celdas01" onfocus="nextfield ='DFICDID';" style="width: 186px; ">
              <option value="">SELECCIONA</option>
              
              <%
						v =  (Vector) request.getAttribute ("vDocumento");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
              <option value="<%=vCols.get(0)%>" <%= ((String)vCols.get(0)).equals("1")  ?"SELECTED":"" %>><%= vCols.get(1)%> </option>
              <%
							}
						}
					%>
            </select>
            <%= Constantes.NUMERO %>
            &nbsp;&nbsp;<input name="DFICDIDREC" type="text" size="15" maxlength="15" class="celdas01" onfocus="nextfield ='CMSO';" onchange="veridigitos(this);" />
            &nbsp;&nbsp;&nbsp;<input name="brec" type="button" class="boton4" onclick="getMigra1(document.f.CTDIREC, document.f.DFICDIDREC);" value="Buscar"/></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
        <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td><%= Constantes.APE_PATERNO%>&nbsp;&nbsp;
            <input name="DAPEPATREC" type="text" onfocus="nextfield ='DAPEMATREC';" onkeypress="return soloLetras(event)" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" style="width: 182px; "/></td>
          <td><%= Constantes.APE_MATERNO%>
          <input name="DAPEMATREC" type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='DNOMREC';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" />
            </td>
          <td><%= Constantes.NOM%>
            <input name="DNOMREC" type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='DFICDIDREC';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" /></td>
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
          		<input name="TELRECLA" type="text" onkeypress="return soloNumeros(event)"  onfocus="nextfield ='TELRECLA';" size="20" maxlength="9" value="" style="width: 182px; "/></td>          	
          	<td><%= Constantes.CORREO_ELECT %>
          		<input name="MAILRECLA" type="text" onfocus="nextfield ='MAILRECLA';" size="20" maxlength="50" value=""  onchange="cambia(this);" style="width: 224px; "   /></td>
          	<td colspan="4"><%= Constantes.DIRECCION %>
          	<input  type="text" name="DIRRECLA" size="80" maxlength="500" onchange="cambia(this);" class="celdas01" value=""/></td>
          	<td>&nbsp;</td>
        </tr>
        <tr>  
	        <td>&nbsp;</td>
	          <td colspan="3">&nbsp;</td>
	          <td>&nbsp;</td>
	     </tr>      
        <tr>      
         	<td>&nbsp;</td> 
         	<td><%= Constantes.TELEFONO2 %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          		<input name="CELRECLA" type="text" onfocus="nextfield ='CELRECLA';" size="30" onkeypress="return soloNumeros(event)" maxlength="9" value=""  onchange="veridigitos(this);" style="width: 182px; "/></td>
          	
          	
          	<td id="idRazSocial"  style="display: none;" ><%= Constantes.DAT_GEN_RAZ_SOCIAL %>
          	<input name="DG_RAZ_SOCIAL" type="text"  size="80" maxlength="50" value=""  style="width: 224px; "   /></td>
          	
          	<td colspan="2">&nbsp;</td>		        	
         	
         </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><hr color="#BOC4DE" /></td>
          <td>&nbsp;</td>
        </tr>
        <!--  FIN -->
         <tr>
          <td>&nbsp;</td>
          <td colspan="3" height="30px"><label style="font-weight: bold;font-size: 15px;"><%= Constantes.TITULO_INVOLUCRADO %> </label>  </td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><%= Constantes.DOCUMENTO_IDENTIDAD %><strong id="o1">*</strong>&nbsp;&nbsp;
              <select name="CTDI" class="celdas01" onfocus="nextfield ='DFICDID';">
                <option value="">SELECCIONA</option>
					<%
						v =  (Vector) request.getAttribute ("vDocumento");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
								<option value="<%=vCols.get(0)%>" <%= ((String)vCols.get(0)).equals("1")?"SELECTED":"" %>><%= vCols.get(1)%> </option>
          <%
							}
						}
					%>
              </select>&nbsp;&nbsp;&nbsp;
            <%= Constantes.NUMERO %>
            <input name="DFICDID" type="text" onfocus="nextfield ='DFICDID';" onchange="veridigitos(this);" size="15" maxlength="15"/>
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
          <td><%= Constantes.APE_PATERNO %> <strong id="o2">*</strong><b>
              <input name="DAPEPAT" type="text" onfocus="nextfield ='DAPEMAT';" onchange="cambia(this);" size="30" maxlength="30" onkeypress="return soloLetras(event)" class="celdas01" style="width: 171px; "/>
          </b></td>
          <td><%= Constantes.APE_MATERNO %> <strong id="o3">*</strong><b>
            <input name="DAPEMAT" type="text" onfocus="nextfield ='DNOM';" onkeypress="return soloLetras(event)" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" />
          </b></td>
          <td><%= Constantes.NOM %><strong id="o4">*</strong><b>
            <input name="DNOM" type="text" onfocus="nextfield ='NFICEDA';" onkeypress="return soloLetras(event)" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" style="width: 191px; "/>
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
            <select name="DDICSEX" class="celdas01" onfocus="nextfield='DEPARTAMENTO';" >
              <option value="" selected="selected">SELECCIONA</option>
              <option value="M">Masculino</option>
              <option value="F">Femenino</option>
            </select></td>
          <td><%= Constantes.EDAD %><strong id="o6">*</strong>
            <input name="NFICEDA" type="text" size="3" maxlength="3" class="celdas01" onfocus="nextfield ='DFICTEL';" onchange="veridigitos(this);" /></td>
          <td><%= Constantes.TELEFONO1 %> <b></b>
            <input name="DFICTEL" type="text" class="celdas01" onfocus="nextfield ='DFICMAIL';" onchange="veridigitos(this);" size="15" onkeypress="return soloNumeros(event)" maxlength="9" style="width: 171px; "/></td>
        	<td>&nbsp;</td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
          <td><%= Constantes.DEPARTAMENTO %> <strong id="o7">*</strong>
            <select name="DEPARTAMENTO" class="celdas01" onfocus="nextfield ='PROVINCIA';" onchange="pasa('CPRO',this.form.DEPARTAMENTO.options[this.form.DEPARTAMENTO.selectedIndex].value);">
              <option value="" selected="selected">SELECCIONA</option>
              <%
								v =  (Vector) request.getAttribute ("vDepartamento");
								if (v != null) {  
									for (int i = 0; i < v.size(); i++) {  
										vCols = (Vector) v.get(i);  
							%>
              <option value="<%=vCols.get(0)%>" <%= Util.jnvl((String)request.getParameter("DEPARTAMENTO"),"").compareTo(vCols.get(0))==0?"SELECTED":"SELECCIONA" %>><%= vCols.get(1)%></option>
              <%
									}
								}
							%>
            </select></td>
          <td><%= Constantes.PROVINCIA %><strong id="o8">*</strong>
            <select name="PROVINCIA" class="celdas01" onfocus="nextfield ='CFICUBIGEO';" onchange="pasa('CDIS',this.form.PROVINCIA.options[this.form.PROVINCIA.selectedIndex].value);">
              <option value="" selected="selected">SELECCIONA</option>
              <%
								v =  (Vector) request.getAttribute ("vProvincia");
								if (v != null) {  
									for (int i = 0; i < v.size(); i++) {  
										vCols = (Vector) v.get(i);  
							%>
              <option value="<%=vCols.get(0)%>" <%= Util.jnvl((String)request.getParameter("PROVINCIA"),"").compareTo(vCols.get(0))==0?"SELECTED":"SELECCIONA" %>><%= vCols.get(1)%></option>
              <%
									}
								}
							%>
            </select></td>
          <td><%= Constantes.DISTRITO %> <strong id="o9">*</strong>
            <select name="CFICUBIGEO" class="celdas01" onfocus="nextfield ='DFICDIR';" style="width: 128px; ">
              <option value="">SELECCIONA</option>
              <%
								v =  (Vector) request.getAttribute ("vDistrito");
								if (v != null) {  
									for (int i = 0; i < v.size(); i++) {  
										vCols = (Vector) v.get(i);  
							%>
              <option value="<%=vCols.get(0)%>" <%= Util.jnvl((String)request.getParameter("DISTRITO"),"").compareTo(vCols.get(0))==0?"SELECTED":"SELECCIONA" %>><%= vCols.get(1)%></option>
              <%
									}
								} 
							%>
            </select></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><%=Constantes.DIRECCION %><strong id="o10">*</strong>
            <input name="DFICDIR" type="text" class="celdas01" onfocus="nextfield ='DFICTEL';" onchange="cambia(this);" size="45" maxlength="120" /></td>
          <td colspan="2"><%=Constantes.CORREO_ELECT %>
            <input name="DFICMAIL" type="text" class="celdas01" onfocus="nextfield ='CTPR';" onchange="cambia(this);" size="23" maxlength="30" style="width: 399px; "/></td>
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
            <select name="CTPR" class="celdas01" onfocus="nextfield ='CFICTIPSEG';">
              <option value="">SELECCIONA</option>
					<%
						v =  (Vector) request.getAttribute ("vPrestacion");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
              <option value="<%=vCols.get(0)%>"><%= vCols.get(1)%></option>
					<%
							}
						}
					%>
            </select></td>
            
          <td><%= Constantes.TIPO_SEGURO %>
          
          <input type="hidden" name="CFICTIPSEG"/>
            <input type="text" name="DFICTIPSEG"/>
            
            </td>
         
			
          
          <td colspan="2" id="medioIngreso">
					<%if(!clusuario.getPerfil().equals(Constantes.PERFIL_ESSALUD_LINEA)){ %>
						<%= Constantes.TIPO_INGRESO %><strong id="o12">*</strong>
						
						<select name="CTIN" class="celdas01" onfocus="nextfield ='OBSERVACIONES';" onchange="cambioCtin();"  >
							<option value="" selected="selected">SELECCIONA</option>
							<%
								v =  (Vector) request.getAttribute ("vTingreso");
								if (v != null) {  
									for (int i = 0; i < v.size(); i++) {  
										vCols = (Vector) v.get(i);  
							%>
							<option value="<%=vCols.get(0)%>" ><%= vCols.get(1)%></option>
							<%
									}
								}
							%>
						</select>
				<% } else { %>
				<input name="CTIN" type="hidden" value="<%=Constantes.CODIGO_ESSALUD_LINEA%>"/>
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
            <select name="CRED" class="celdas01" onfocus="nextfield ='CCAS';" onchange="pasa('CASC',this.form.CRED.options[this.form.CRED.selectedIndex].value);">
              <option value="" selected="selected">SELECCIONA</option>
              <%
								v =  (Vector) request.getAttribute ("vRed");
								if (v != null) {  
									for (int i = 0; i < v.size(); i++) {  
										vCols = (Vector) v.get(i);  
							%>
									<option value="<%=vCols.get(0)%>" <%= Util.jnvl((String)request.getParameter("CRED"),"").compareTo(vCols.get(0))==0?"SELECTED":"" %>><%= vCols.get(1)%> </option>
									<%
									}
								}
							%>
            </select></td>

          <td><%= Constantes.UNIDADORG1 %><strong id="o14">*</strong>
            <select name="CCAS" class="celdas01" onfocus="nextfield ='BESTADOREG';">
						  <option value="" selected="selected">SELECCIONA</option>
						  
					  </select></td>
         </tr>
        
        
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        
        
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><hr color="#BOC4DE" /></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><%= Constantes.DESCRIPCION %><strong id="o15">*</strong></td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
        <td colspan="3">&nbsp;</td> 
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3" align="center"><textarea name="DFICHEC" class="celdas01" onchange="cambia(this);" onfocus="nextfield ='CODIGO';" onkeypress="corta1(this);" onkeyup="corta1(this);" cols="160" rows="10"></textarea>
            <input name="Cars1" type="text" class="celdas01" onfocus="blur();" value="900" size="3" maxlength="3" /></td>
          <td>&nbsp;</td>
        </tr>
      </table>
	  </td>
	</tr>
	
	
	 
	</table>
	
	
  <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="20%"><%= Constantes.INFO_OBLI %></td>
      <td align="center">
				<input type="hidden" name="opt" value="2"/>
        <input type="hidden" name="caso" value="I"/>
        <input name="dato" type="hidden" />
        <% if(clusuario.getPerfil().compareTo("11")==0){ %>
        <input type="hidden" name="TINGRESO" value="07"/>
				<% } %>
			</td>
      <td width="20%">&nbsp;</td>
    </tr>
    
     <%if(  clusuario.getPerfil().equals(Constantes.PERFIL_COORDINADOR) || clusuario.getPerfil().equals(Constantes.PERFIL_DELEGADO_DEFENSORIAL) || clusuario.getPerfil().equals(Constantes.PERFIL_ESSALUD_LINEA) ) {%>
				
    
			    <tr>
			      <td colspan="3" align="center">
				  <input name="Button2" type="submit" class="boton4" value=" Grabar " />&nbsp; 
			      <input name="Reset" type="reset" class="boton4" value=" Borrar "/>
			      </td>
			    </tr>
			
	  
	  <% } %>
	  		    
    <tr>
      <td colspan="3" align="center"><span class="titulo4">
        <% if(msg != null){ %>
        <%= msg %>
        <% } %>
      </span></td>
    </tr>
  </table>
</form>
</body>
<%
  session.removeAttribute("obj");
  } 
  catch (NullPointerException e) {
		System.out.println("NullPointerException[fdatosg.jsp]: " + e);
    request.setAttribute("msg_error", "Error al mostrase la pagina: " + e); 
    WebUtil.goForward(getServletContext(), request, response, "/error/error.jsp"); 
  }
	finally{
	  F1t = null;
	}
%>
</html>
