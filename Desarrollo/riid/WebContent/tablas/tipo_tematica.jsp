<%@page import="util.Constantes"%>
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
	<% if (clusuario.getPerfil().compareTo("00")==0){ %>
	document.f.CODACCION.value = "";
	document.f.DESACCION.value = "";
	document.f.BESTADOREG.value = "";
	<% } %>
}

function goPg(np){
  document.f1.pg.value = np;
  document.f1.submit();
}  

function buscar(){
//	var strCod = document.f1.COD.value;
//	var strDes = document.f1.DES.value;
	if(document.f1.COD.value == "" && document.f1.DES.value == ""){
		alert("Por favor ingrese algún criterio de búsqueda");
		event.returnValue=false;
		document.f1.COD.focus();
	}
	else
		document.f1.submit();
}

function Actualiza(){
	location.href='../servlet/CtrlMant';	
}

function getReg(cod,des,est,tem){
	document.f.CODTEM.value = tem;
	document.f.CODACCION.value = cod;
	document.f.DESACCION.value = des;
	
	if(est == 'ACTIVO' || est == 'ACTIVADO'){
	document.f.BESTADOREG.value = '1';
	}else{
		document.f.BESTADOREG.value = '0';
	}
	document.f.graba.value = " MODIFICAR ";
	document.f.graba.title = " Graba Datos Actualizados del registro ";
	document.f.Salir.value = "CANCELAR";
	document.f.Salir.title = " Cancela la actualización del registro ";
	eval('document.f.CODACCION.readOnly=true');
	document.f.CODACCION.style.backgroundColor="#DFE6EE";
}

function getSalir(){
	var caso = document.f.Salir.value;
	if (caso == 'CANCELAR'){
		document.f.graba.value = "ADICIONAR";
		document.f.graba.title = " Ingresar Nuevo Catálogo ";
		document.f.Salir.value = " SALIR ";
		eval('document.f.CODACCION.readOnly=false');
		document.f.CODACCION.style.backgroundColor="#FFFFFF";
		inicio();
	}
	else{
		location.href='../servlet/CtrlFicha?opt=0';
	}
}

function getGrabar(){
	if (document.f.CODACCION.value == "" || document.f.DESACCION.value == "" || document.f.BESTADOREG.value == ""){
		alert("Por favor ingrese todos los datos");
		document.f.CODACCION.focus();
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
<body class="body" onload="inicio();" bgproperties="fixed">
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
    <td align="center" class="titulob2">REGISTRO INFORMÁTICO DE ATENCIÓN AL ASEGURADO</td>
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
<table width="100%" align="center">
  <tr>
    <td colspan="3" align="center" class="titulo1">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" class="titulo1"><%=Constantes.TITULO_MANTENIMIENTO_TABLA_TEMATICA %></td>
  </tr>
</table>
<form name="f1" method="post">
	<table width="700" border="0" align="center" cellspacing="0">
		<tr>
			
			<td><%= Constantes.TIPO_TEMATICA %><b>*</b>
              <select name="COD" class="celdas01" onfocus="nextfield ='DES';" >
                <option value="">SELECCIONAR</option>
                <option value="TODOS">TODOS</option>
                <%	v =  (Vector) request.getAttribute ("vSolicitud");
					if (v != null) {  
						for (int i = 0; i < v.size(); i++) {  
							vCols = (Vector) v.get(i);  
							if(!StringUtils.equals((String)vCols.get(0), "1") && !StringUtils.equals((String)vCols.get(0), "5")){
							
				%> <option value="<%=vCols.get(0)%>" <%= objLst.getCodSolicitud().equals(vCols.get(0)) ? "SELECTED":"" %> ><%= vCols.get(1)%></option>
                <%			}
						}
					}
				%>
              </select>
            </td>
            <td>
				DESCRIPCION <input name="DES" type="text" class="celdas01" value="" onChange="cambia(this);" size="20" maxlength="20">
				</td>
				
			<TD>
				<input type="hidden" name="pg" value="<%= objLst.getPag()%>">
				<input type="hidden" name="opt" value="26">
				<input type="hidden" name="COD_PAG" value="<%=objLst.getCodSolicitud()%>">
			
			</tD>
			<td>
			<input name="Button" type="button" class="boton4" value="BUSCAR" onclick="buscar();">				
			
			<!--<input name="Submit" type="submit" class="boton4" value="ACTUALIZAR" onclick="Actualiza();">-->
			</td>	
			<td align="right" ><%  if ( objLst.getPaginacion().getPaginaAnt()) { %>
				<input name="button" value="  <<  " type="button" class="boton4" onClick="goPg(<%= Integer.parseInt(objLst.getPag()) - 1%>);" title="Retroceder a pagina anterior">
					<%  } 
				if ( objLst.getPaginacion().getPaginaSgte()) { %>
				<input name="button" value="  >>  " type="button" class="boton4" onClick="goPg(<%= Integer.parseInt(objLst.getPag()) + 1%>);" title="Avanzar a pagina siguiente">
					<%  } %>
			</td>
		</tr>
	</table>
	<table width="700"  border="1" align="center" cellspacing="0" bordercolor="#007AC9">
		<tr>
			<td>
				<table width="100%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
					<tr bgcolor="#3DB7E4" class="Estilo3" align="center"> 
						<td>ITEM</td>
						<td>SOLICITUD</td>
						<td>TEMATICA</td>
						<td>ESTADO</td>
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
					   
              <a href="javascript:getReg('<%=v.get(0)%>','<%=v.get(3)%>','<%=v.get(4)%>','<%=v.get(2)%>');">
              <img src="../images/fleder.jpg" alt="Seleccione para actualizar datos del registro" width="12" height="12" border="0" /></a>
				<%= counter + ((Integer.parseInt(objLst.getPag()) - 1)  * 15) %></td>
				        <td align="center"><%= v.get(1)!=null?v.get(1):"&nbsp;" %></td> 
						<td align="center"><%= v.get(3)!=null?v.get(3):"&nbsp;" %></td>
						<td><%= v.get(4)!=null?v.get(4):"" %></td>
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
	<table width="700" border="1" align="center" cellspacing="0" bordercolor="#007AC9">
		<tr>
			<td>
				<table width="100%" border="1" align="center"  cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
					<tr class="Estilo3" bgcolor="#3DB7E4" align="center">
						<td>TIPO SOLICITUD</td>
						<td>TEMATICA</td>
						<td>ESTADO</td>
					</tr>
					<input type="hidden" name="CODTEM" />
					<tr align="center">
						<td>
               <select name="CODACCION" class="celdas01" onfocus="nextfield ='DESACCION';" >
                <option value="">SELECCIONAR</option>
                <%
						v =  (Vector) request.getAttribute ("vSolicitud");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					
								if(!StringUtils.equals((String)vCols.get(0), "1") && !StringUtils.equals((String)vCols.get(0), "5")){
					 %>
					 
                		<option value="<%=vCols.get(0)%>"><%= vCols.get(1)%></option>
                		
                	<%}
							}
						}
					%>
            </select></td>
						
						<td><input name="DESACCION" type="text" class="celdas01" onfocus="nextfield ='BESTADOREG';" onchange="cambia(this);" size="50" maxlength="200"></td>
						<td>
							<select name="BESTADOREG" class="celdas01" onfocus="nextfield ='graba';">
								<option value="1">ACTIVO</option>
								<option value="0">INACTIVO</option>
							</select>
						</td>
					</tr>
					<tr align="center">
						<td colspan="3" bgcolor="#3DB7E4">
							<input type="hidden" name="opt" value="27">
						  <input type="hidden" name="caso" value="I">
				  		<input name="graba" class="boton4" type="button" value="ADICIONAR" title="Ingresa Nuevo Registro" onclick="getGrabar();">
			  		<input name="Salir" class="boton4" type="button" value="  SALIR  " onclick="getSalir();"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
<% 
		
	} 
%>
<table width="70%" border="0" align="center" class="titulo6">
  <tr align="center">
    <td><% if(msg != null){ %><%= msg %><% } %>&nbsp;</td>
  </tr>
</table>
</body>
<% 
	request.removeAttribute("objLst");
%>
</html>
