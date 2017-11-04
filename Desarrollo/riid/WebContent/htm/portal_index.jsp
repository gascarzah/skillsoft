
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
  
  Ficha_t F1t = (Ficha_t)application.getAttribute("obj");
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
<link href="../styles/celdas.css" rel="stylesheet" type="text/css"/>
<link href="../styles/botones.css" rel="stylesheet" media="print" type="text/css"/>
<script language="JavaScript" src="../js/ubigeo.js"></script>
<script language="JavaScript" src="../js/script2.js"></script>
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
	
  parent.bot.location.href='../jsp/bot1.jsp?tipo=' + valor1 + '&num=' + valor2;
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
	
	
	
	document.f.TELRECLA.disabled="disabled";
	document.f.TELRECLA.style.backgroundColor="#DFE6EE";
	document.f.MAILRECLA.disabled="disabled";
	document.f.MAILRECLA.style.backgroundColor="#DFE6EE";
	document.f.CELRECLA.disabled="disabled";
	document.f.CELRECLA.style.backgroundColor="#DFE6EE";
	document.f.DIRRECLA.disabled="disabled";
	document.f.DIRRECLA.style.backgroundColor="#DFE6EE";
	
	
}


</script>
</head>
<%
try {
%>
<body class="body"  bgproperties="fixed" onload="inicio();">
<form action="../servlet/CtrlFicha" method="post" name="f" onsubmit="return registroPortal(this)">
 <table class="banner01" >
    <tr>
      <td width="220" rowspan="5" align="center"><img src="../images/es03.jpg" width="120" height="100"/></td>
      <td align="center"></td>
      <td width="220" rowspan="8" align="right"><img src="../images/defensoria01.jpg" width="120" height="100"/></td>
    </tr>
    <tr>
      <td align="center" class="titulob1">&nbsp;</td>
    </tr>
    <tr align="center">
      <td>&nbsp;</td>
    </tr>
    <tr align="center">
      <td align="center" class="titulob2">REGISTRO INFORMÁTICO DE ATENCIÓN AL ASEGURADO</td>
    </tr>
    <tr align="center">
      <td align="center" class="titulob1">&nbsp;</td>
    </tr>
    <tr align="center">
      <td align="center" class="celdasb"></td>
      <td rowspan="3" class="titulo4">
      	<table width="100">
        	
        </table>
      </td>
    </tr>
    <tr align="center">
      <td align="center" class="celdasb"></td>
    </tr>
    <tr align="center">
      <td align="center" class="celdasb"></td>
    </tr>
  </table>
  
  <table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><p style="color: #FD0202; "><b>Terminada de llenar la ficha y guardada, imprimir la misma para hacer seguimiento a su solicitud</b></p></td>
    </tr>
    
   
  </table>
  <table width="85%"  border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#007AC9" bgcolor="#ffffff" class="celdas">
    <tr>
      <td><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
      
      	<tr>
          <td>&nbsp;</td>
          <td colspan="3" height="50px"><label style="font-weight: bold;font-size: 15px;"><%= Constantes.TITULO_SOLICITANTE %> </label><br /> <%= Constantes.DESCRIPTI_SOLICITANTE %> 
		  Si<input type="radio" name="saberec" value="1" onclick="cambiodis();"/>&nbsp;&nbsp;&nbsp;No<input type="radio" name="saberec" value="0" onclick="cambiodis();" />            </td>
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
            <select name="CTDIREC" class="celdas01" onfocus="nextfield ='DFICDID';" style="width: 186px; ">
              <option value="">SELECCIONA</option>
              
              <%
						v =  (Vector) application.getAttribute ("vDocumento");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
              <option value="<%=vCols.get(0)%>" <%= ((String)vCols.get(0)).equals("1")?"SELECTED":"" %>><%= vCols.get(1)%> </option>
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
        <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
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
          	<td><%= Constantes.TELEFONO1 %> <b>*</b>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
          		<input name="TELRECLA" type="text" onkeypress="return soloNumeros(event)" onchange="veridigitos(this);" onfocus="nextfield ='TELRECLA';" size="20" maxlength="9" value="" style="width: 182px; "></td>          	
          	<td><%= Constantes.CORREO_ELECT %>
          		<input name="MAILRECLA" type="text" onfocus="nextfield ='MAILRECLA';" size="20" maxlength="30" value=""  onchange="cambia(this);" style="width: 224px; "></td>
          	<td colspan="4"><%= Constantes.DIRECCION %>
          	<input  type="text" name="DIRRECLA" size="80" maxlength="500" onchange="cambia(this);" class="celdas01" value=""></td>
          	<td>&nbsp;</td>
         </tr>
         
        <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        <tr>      
         	<td>&nbsp;</td> 
         	<td><%= Constantes.TELEFONO2 %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          		<input name="CELRECLA" type="text" onfocus="nextfield ='CELRECLA';" size="30" onkeypress="return soloNumeros(event)" maxlength="9" value=""  onchange="veridigitos(this);" style="width: 182px; "></td>
          	<td>&nbsp;</td>		        	
         	
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
          <td colspan="3"><%= Constantes.DOCUMENTO_IDENTIDAD %><strong>*</strong>&nbsp;&nbsp;
              <select name="CTDI" class="celdas01" onfocus="nextfield ='DFICDID';">
                <option value="">SELECCIONA</option>
					<%
						v =  (Vector) application.getAttribute ("vDocumento");
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
            <input name="DFICDID" type="text" onfocus="nextfield ='DFICDID';" onchange="veridigitos(this);" size="15" maxlength="15">
	<input name="Button" type="button" class="boton4" onclick="getMigra(document.f.CTDI, document.f.DFICDID);" value="Buscar"></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><%= Constantes.APE_PATERNO %> <b>*
              <input name="DAPEPAT" type="text" onfocus="nextfield ='DAPEMAT';" onchange="cambia(this);" size="30" maxlength="30" onkeypress="return soloLetras(event)" class="celdas01" style="width: 171px; "/>
          </b></td>
          <td><%= Constantes.APE_MATERNO %> <b>*
            <input name="DAPEMAT" type="text" onfocus="nextfield ='DNOM';" onkeypress="return soloLetras(event)" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" />
          </b></td>
          <td><%= Constantes.NOM %><b>*
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
          <td><%= Constantes.SEXO %>*</b>
            <select name="DDICSEX" class="celdas01" onfocus="nextfield='DEPARTAMENTO';" >
              <option value="" selected="selected">SELECCIONA</option>
              <option value="M">Masculino</option>
              <option value="F">Femenino</option>
            </select></td>
          <td><%= Constantes.EDAD %><b>*</b>
            <input name="NFICEDA" type="text" size="3" maxlength="3" class="celdas01" onfocus="nextfield ='DFICTEL';" onchange="veridigitos(this);" /></td>
          <td><%= Constantes.TELEFONO1 %> <b>*</b>
            <input name="DFICTEL" type="text" class="celdas01" onfocus="nextfield ='DFICMAIL';" onchange="veridigitos(this);" size="15" onkeypress="return soloNumeros(event)" maxlength="9" style="width: 171px; "/></td>
        	<td>&nbsp;</td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
       
        	<input type="hidden" name="DEPARTAMENTO" />
        	<input type="hidden" name="PROVINCIA" />
        	<input type="hidden" name="CFICUBIGEO"/>
          
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><%=Constantes.DIRECCION %><b>*</b>
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
          
            <input type="hidden" name="CFICTIPSEG"/>
            <input type="hidden" name="DFICTIPSEG"/></td>

        
          
          
          <td>&nbsp;</td>
        </tr>
        
       
        
        
        
                
        <tr>
          <td>&nbsp;</td>
          <td>
            <select name="CRED" style="display: none;" class="celdas01" onfocus="nextfield ='CCAS';" onchange="pasa('CASC',this.form.CRED.options[this.form.CRED.selectedIndex].value);">
              <option value="" selected="selected">SELECCIONA</option>
              <%
								v =  (Vector) application.getAttribute ("vRed");
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

          <td>
            <select name="CCAS"  style="display: none;" class="celdas01" />
              <option value="">SELECCIONA</option>
					<%
						v =  (Vector) application.getAttribute ("vCas");
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
         </tr>

        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><hr color="#BOC4DE" /></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><%= Constantes.DESCRIPCION %></td>
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
				<input type="hidden" name="opt" value="35">
        <input type="hidden" name="caso" value="I">
        <input name="portal" type="hidden"   value="1"/>
        
			</td>
      <td width="20%">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3" align="center">
				<input name="Button2" type="submit" class="boton4" value=" Grabar " >&nbsp; <!-- onclick="grabar();" -->
      <input name="Reset" type="reset" class="boton4" value=" Borrar ">
      </td>
    </tr>
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
  
  } 
  catch (NullPointerException e) {
		System.out.println("NullPointerException[alta_i.jsp]: " + e);
    request.setAttribute("msg_error", "Error al mostrase la pagina: " + e); 
    WebUtil.goForward(getServletContext(), request, response, "/error/error.jsp"); 
  }
	finally{
	  F1t = null;
	}
%>
</html>
