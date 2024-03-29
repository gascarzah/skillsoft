<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page session="true" %>
<%@ page import="java.util.*" %>
<%@ page import="beans.WebUtil" %>
<%@ page import="beans.Util" %>
<%@ page import="clases.Tablas_t" %>
<%@ page import="clases.ListFicha_t" %>
<%@ page import="clases.UsuarioSistema" %>
<%@ page import="java.util.GregorianCalendar"%>
<%@ include file="../jsp/formato_fecha.jsp" %>
<%
  UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario");
  Tablas_t objLst = (Tablas_t)request.getAttribute("objLst");
	String msg = (String)request.getAttribute("msg_error");
	Vector v;
	Vector vCols;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css">
<link href="../styles/botones.css" rel="stylesheet" media="print" type="text/css">
<script language="JavaScript" src="../js/ubigeo.js"></script>
<script language="JavaScript" src="../js/script2.js"></script>
<script language="javascript" src="../js/format_date.js"></script>
<script language="javascript" src="../js/stm31.js"></script>
<script language="javascript">
function inicio(){
	document.f1.COD.value = "";
	document.f1.DES.value = "";
	
	document.f.CODIGO.value = "";
	document.f.DESCRIPCION.value = "";
	document.f.BESTADOREG.value = "";
	
}

function goPg(np){
  document.f1.pg.value = np;
  document.f1.submit();
}  

function buscar(){
	if(document.f1.COD.value == "" && document.f1.DES.value == ""){
		alert("Por favor ingrese alg�n criterio de b�squeda");
		event.returnValue=false;
		document.f1.COD.focus();
	}
	else
		document.f1.submit();
}

function Actualiza(){
	document.f1.pg.value = '1';
	document.f1.submit();
}

function getReg(cod,des,est,codRed){ 
	document.f.CODIGO.value = cod;
	document.f.CRED.value = codRed;
	document.f.CODRED.value = codRed;
	document.f.DESCRIPCION.value = des;
	document.f.BESTADOREG.value = est=='ACTIVO'?'1':'0';
	document.f.graba.value = " MODIFICAR ";
	document.f.graba.title = " Graba Datos Actualizados del registro ";
	document.f.Salir.value = "CANCELAR";
	document.f.Salir.title = " Cancela la actualizaci�n del registro ";
	eval('document.f.CODIGO.readOnly=true');
	document.f.CODIGO.style.backgroundColor="#DFE6EE";
	document.f.CRED.style.backgroundColor="#DFE6EE";
}

function getSalir(){
	var caso = document.f.Salir.value;
	if (caso == 'CANCELAR'){
		document.f.graba.value = "ADICIONAR";
		document.f.graba.title = " Ingresar Nuevo Cat�logo ";
		document.f.Salir.value = " SALIR ";
		eval('document.f.CODIGO.readOnly=false');
		document.f.CODIGO.style.backgroundColor="#FFFFFF";
		inicio();
	}
	else{
		location.href='../servlet/CtrlFicha?opt=0';
	}
}

function getGrabar(){
	if (document.f.CODIGO.value == "" || document.f.DESCRIPCION.value == "" || document.f.BESTADOREG.value == ""){
		alert("Por favor ingrese todos los datos");
		document.f.CODIGO.focus();
		return;
	}
	if (document.f.graba.value == 'ADICIONAR'){
		document.f.caso.value = 'I';
	} 
	else{
		document.f.caso.value = 'M';
	}
	document.f.submit();
}



</script>
</head>
<body class="body" onload="inicio();" >
<table class="banner01" >
  <tr>
    <td width="220" rowspan="4" align="center"><img src="../images/es03.jpg" width="120" height="100"/></td>
    <td align="center"><%@include file="../js/menu_all.js" %></td>
    <td width="220" rowspan="7" align="right"><img src="../images/defensoria01.jpg" width="120" height="100"/></td>
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
    <td width="220" align="center" class="celdasb"><%= clusuario.getDescripcion() %></td>
    <td align="center" class="titulob1">&nbsp;</td>
  </tr>
  <tr align="center">
    <td align="center" class="celdasb"><%= clusuario.getdPerfil() %></td>
    <td>&nbsp;</td>
  </tr>
  <tr align="center">
    <td align="center" class="celdasb"><%= clusuario.getdCas() %></td>
    <td>&nbsp;</td>
  </tr>
</table>
<table align="center" width="100%">
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" class="titulo1">MANTENIMIENTO DE LA TABLA DE UNIDADES ORGANICAS NIVEL 02</td>
  </tr>
</table>
<form name="f1" method="post">
	<table width="800" border="0" align="center" cellspacing="0">
		<tr class="celdas01">
			<td>RED <input name="COD" type="text" class="celdas01" value="" size="10" maxlength="5"/>
				CAS <input name="DES" type="text" class="celdas01" value="" onChange="cambia(this);" size="25" maxlength="30"/>
				<input name="Button" type="button" class="boton4" value="BUSCAR" onclick="buscar();"/>
			</td>
			<td>
				<input type="hidden" name="pg" value="<%= objLst.getPag()%>"/>
				<input type="hidden" name="opt" value="34"/>
			</td>
			<td align="right" width="25%"><%  if ( objLst.getPaginacion().getPaginaAnt()) { %>
				<input name="button" value="  <<  " type="button" class="boton4" onClick="goPg(<%= Integer.parseInt(objLst.getPag()) - 1%>);" title="Pagina anterior"/>
					<%  } 
				if ( objLst.getPaginacion().getPaginaSgte()) { %>
				<input name="button" value="  >>  " type="button" class="boton4" onClick="goPg(<%= Integer.parseInt(objLst.getPag()) + 1%>);" title="Pagina siguiente"/>
					<%  } %>
			</td>
		</tr>
	</table>
	<table width="800"  border="1" align="center" cellspacing="0" bordercolor="##3DB7E4">
		<tr>
			<td>
				<table width="100%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
					<tr bgcolor="#3DB7E4" class="Estilo3" align="center">
					  <td >ITEM</td>
					  <td>UNIDAD ORGANICA 01</td>
					  <td>CODIGO UNIDAD ORGANICA 02</td>
					  <td>UNIDADES ORGANICA 02</td>
					  <td >ESTADO</td>
					</tr>
				
					<%
			int counter = 0;
			boolean bFil = true;
			for (Enumeration e = objLst.getHshLista().keys(); e.hasMoreElements();) {
				e.nextElement();
				v = (Vector)objLst.getHshLista().get("" + counter);
				bFil = bFil?false:true; 
				counter++;
	%>
					<tr bgcolor="<%=bFil?"#EAEFF5":"#FFFFFF"%>" class="celdas01">
					  <td align="center">
						
              <a href="javascript:getReg('<%=StringUtils.defaultString(String.valueOf(v.get(0)))%>','<%=StringUtils.defaultString(String.valueOf(v.get(1)))%>','<%=StringUtils.defaultString(String.valueOf(v.get(2)))%>','<%=StringUtils.defaultString(String.valueOf(v.get(3)))%>');">
              <img src="../images/fleder.jpg" alt="Seleccione para actualizar datos del registro" width="12" height="12" border="0" /></a>
            
            &nbsp;&nbsp; <%= counter + ((Integer.parseInt(objLst.getPag()) - 1)  * 15) %></td>
            			<td><%= StringUtils.defaultString(String.valueOf(v.get(4)))%></td>
            			<td><%= StringUtils.defaultString(String.valueOf(v.get(0)))%></td> 
						<td><%= StringUtils.defaultString(String.valueOf(v.get(1)))%></td>
						<td align="center"><%= StringUtils.defaultString(String.valueOf(v.get(2)))%></td>
					</tr>
	<%
			}  
	%>
				</table>
			</td>
		</tr>
	</table>
</form>
<%
	if (objLst!=null) {
		
%>
<form action="../servlet/CtrlMant" method="post" name="f">
	<table width="800" border="1" align="center" cellspacing="0" >
		<tr>
			<td>
				<table width="100%" border="1" align="center"  cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
					
					<tr class="Estilo3"  bgcolor="#3DB7E4" align="center">
					<td>RED</td>
					  <td>CODIGO</td>
					  <td>NUEVA UNIDAD ORGANICA NIVEL 02</td>
					    <td>ESTADO</td>
					</tr>
					<tr align="center">
					<input name="CODRED" type="hidden" class="celdas01"></input>
					 <td><select name="CRED" class="celdas01">
					    <option value="">SELECCIONA</option>
				 <%
				      v =  (Vector) request.getAttribute ("vRed");
				      if (v != null) {  
				        for (int i = 0; i < v.size(); i++) {  
				          vCols = (Vector) v.get(i);  
				    %>
									    <option value="<%=vCols.get(0)%>" ><%= vCols.get(1)%></option>
						<%
				        }
				      }
				    %>
				    </select></td>
					
					  <td><input name="CODIGO" type="text" class="celdas01" onfocus="nextfield ='DESCRIPCION';" size="10" maxlength="20"/></td>
						<td><input name="DESCRIPCION" type="text" class="celdas01" onchange="cambia(this);" size="50" maxlength="100" /></td>
						<td>
							<select name="BESTADOREG" class="celdas01" onfocus="nextfield ='graba';">
								<option value="">SELECCIONA</option>
								<option value="1" <%= objLst.getBestadoreg().toString().equals("1")?"selected":"" %>>ACTIVO</option>
								<option value="0" <%= objLst.getBestadoreg().toString().equals("0")?"selected":"" %>>INACTIVO</option>
														
							</select>
						</td>
					</tr>
					<tr align="center">
						<td colspan="4" bgcolor="#3DB7E4">
							<input type="hidden" name="pg" value="<%= objLst.getPag()%>"/>
							<input type="hidden" name="opt" value="35"/>
						  <input type="hidden" name="caso" value="I"/>
				  		<input name="graba" class="boton4" type="button" value="ADICIONAR" title="Ingresa Nuevo Registro" onclick="getGrabar();"/>
				  		<input name="Salir" class="boton4" type="button" value="  SALIR  " onclick="getSalir();"/>
            </td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
<% 
		
	} 
%>
<table width="850" border="0" align="center" class="titulo6">
  <tr align="center">
    <td><% if(msg != null){ %><%= msg %><% } %>&nbsp;</td>
  </tr>
</table>
</body>
<% 
	request.removeAttribute("objLst");
%>
</html>
