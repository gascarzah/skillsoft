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
	<% if (clusuario.getPerfil().compareTo("09")==0 || clusuario.getPerfil().compareTo("00")==0){ %>
	document.f.CODIGO.value = "";
	document.f.DESCRIPCION.value = "";
	
	document.f.BESTADOREG.value = "";
	<% } %>
}

function goPg(np){
  document.f1.pg.value = np;
  document.f1.submit();
}  

function buscar(){
	if(document.f1.COD.value == "" && document.f1.DES.value == "" && document.f1.MAT.value == ""){
		alert("Por favor ingrese algún criterio de búsqueda");
		event.returnValue=false;
		document.f1.COD.focus();
	}
	else
		document.f1.submit();
}

function Actualiza(){
	document.f1.pg.value = "1";
	document.f1.COD.value = "";
	document.f1.DES.value = "";
	document.f1.MAT.value = "";
	document.f1.submit();
}

function getReg(cod,des,pla,est,mat,estmat,estgru){
	if(estgru == "ACTIVO"){
		if(estmat == "ACTIVO"){
			document.f.CODIGO.value = cod;
			document.f.DESCRIPCION.value = des;
			
			document.f.BESTADOREG.value = est=='ACTIVO'?'1':'0';
			document.f.CMATERIA.value = mat;
			document.f.graba.value = " MODIFICAR ";
			document.f.graba.title = " Graba Datos Actualizados del registro ";
			document.f.Salir.value = "CANCELAR";
			document.f.Salir.title = " Cancela la actualización del registro ";
			eval('document.f.CODIGO.readOnly=true');
			document.f.CODIGO.style.backgroundColor="#DFE6EE";
		}
		else{
			alert("      El estado de la materia es inactivo\rTiene que activar la materia para modificar.");
			inicio();
		}
	}
	else{
		alert("      El estado del grupo es inactivo\rTiene que activar el grupo para modificar.");
		inicio();
	}
}

function getSalir(){
	var caso = document.f.Salir.value;
	if (caso == 'CANCELAR'){
		document.f.graba.value = "ADICIONAR";
		document.f.graba.title = " Ingresar Nuevo Catálogo ";
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
	if (document.f.CODIGO.value == "" || document.f.DESCRIPCION.value == "" || document.f.CMATERIA.value == "" || document.f.BESTADOREG.value == ""){
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

function ordena(ord){
	if(ord=="gru") location.href="../servlet/CtrlMant?opt=5&ORDEN=b.dgrupo"; 
	if(ord=="mat") location.href="../servlet/CtrlMant?opt=5&ORDEN=a.dmateria"; 
	if(ord=="cod") location.href="../servlet/CtrlMant?opt=5&ORDEN=t.casu"; 
	if(ord=="des") location.href="../servlet/CtrlMant?opt=5&ORDEN=t.dasudes"; 
	if(ord=="pla") location.href="../servlet/CtrlMant?opt=5&ORDEN=t.nasupzo"; 
	if(ord=="con") location.href="../servlet/CtrlMant?opt=5&ORDEN=t.cusu"; 
	if(ord=="est") location.href="../servlet/CtrlMant?opt=5&ORDEN=t.casuestareg"; 
}
</script>
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
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
<table align="center" width="100%">
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" class="titulo1">MANTENIMIENTO DE LA TABLA DE DESCRIPCION DE MOTIVOS</td>
  </tr>
</table>
<form name="f1" method="post">
	<table width="90%" border="0" align="center" cellspacing="0">
		<tr class="celdas01">
			<td>CODIGO <input name="COD" type="text" class="celdas01" value="" size="5" maxlength="10">&nbsp;
				DESCRIPCION <input name="DES" type="text" class="celdas01" value="" onChange="cambia(this);" size="25" maxlength="30">&nbsp;
        MOTIVO PRINCIPAL
				<select name="MAT" class="celdas01">
				  <option value="" selected="selected">SELECCIONA</option>
		<%
      v =  (Vector) request.getAttribute ("vMateria");
      if (v != null) {  
        for (int i = 0; i < v.size(); i++) {  
          vCols = (Vector) v.get(i);  
    %>
				  <option value="<%=vCols.get(0)%>" <%= Util.jnvl((String)request.getParameter("MAT"),"").compareTo(vCols.get(1))==0?"SELECTED":"" %>><%= vCols.get(1)%></option>
		<%
        }
      }
    %>
			  </select>&nbsp;
				<input name="Button" type="button" class="boton4" value="BUSCAR" onclick="buscar();">
			</td>
			<td>
				<input type="hidden" name="pg" value="<%= objLst.getPag()%>">
				<input type="hidden" name="opt" value="5">
			</td>
			<td align="right" width="25%"><%  if ( objLst.getPaginacion().getPaginaAnt()) { %>
				<input name="button" value="  <<  " type="button" class="boton4" onClick="goPg(<%= Integer.parseInt(objLst.getPag()) - 1%>);" title="Retroceder a pagina anterior">
					<%  } 
				if ( objLst.getPaginacion().getPaginaSgte()) { %>
				<input name="button" value="  >>  " type="button" class="boton4" onClick="goPg(<%= Integer.parseInt(objLst.getPag()) + 1%>);" title="Avanzar a pagina siguiente">
					<%  } %>
			</td>
		</tr>
	</table>
	<table width="90%"  border="1" align="center" cellspacing="0" bordercolor="#3DB7E4">
		<tr>
			<td>
				<table width="100%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
					<tr bgcolor="#3DB7E4" class="Estilo3" align="center">
					  <td rowspan="2">ITEM</td>
					  <td colspan="2">GRUPO</td>
					  <td colspan="2">MOTIVO PRINCIPAL</td>
					  <td colspan="2">DESCRIPCION DEL MOTIVO</td>
					  <td colspan="2" rowspan="2"><a href="javascript:ordena('est');" class="a" title="Ordenar por este campo">ESTADO</a></td>
				  </tr>
					<tr bgcolor="#3DB7E4" class="Estilo3" align="center">
					  <td colspan="2"><a href="javascript:ordena('gru');" class="a" title="Ordenar por este campo">DESCRIPCION</a></td>
					  <td colspan="2"><a href="javascript:ordena('mat');" class="a" title="Ordenar por este campo">DESCRIPCION</a></td>
					  <td><a href="javascript:ordena('cod');" class="a" title="Ordenar por este campo">CODIGO</a></td>
						<td><a href="javascript:ordena('des');" class="a" title="Ordenar por este campo">DESCRIPCION</a></td>
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
						<% if (clusuario.getPerfil().compareTo("09")==0 || clusuario.getPerfil().compareTo("00")==0){ %>
            
            <a href="javascript:getReg('<%=v.get(0)%>','<%=v.get(1)%>','<%=v.get(2)%>','<%=v.get(4)%>','<%=v.get(5)%>','<%=v.get(7)%>','<%=v.get(10)%>');">
            <img src="../images/fleder.jpg" alt="Seleccione para actualizar datos del registro" width="12" height="12" border="0"></a>
            <% } %>&nbsp;&nbsp;
						<%= counter + ((Integer.parseInt(objLst.getPag()) - 1)  * 15) %></a></td> 
						<td colspan="2" align="left"><%= v.get(9)!=null?v.get(9):"" %></td>
						<td colspan="2" align="left"><%= v.get(6)!=null?v.get(6):"" %></td>
						<td align="center"><%= v.get(0)!=null?v.get(0):"" %></td>
						<td><%= v.get(1)!=null?v.get(1):"" %></td>
						<td colspan="2" align="center"><%= v.get(4)!=null?v.get(4):"&nbsp;" %></td>
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
		if (clusuario.getPerfil().compareTo("09")==0 || clusuario.getPerfil().compareTo("00")==0){
%>
<form action="../servlet/CtrlMant" method="post" name="f">
	<table width="90%" border="1" align="center" cellspacing="0" bordercolor="#3DB7E4">
		<tr>
			<td>
				<table width="100%" border="1" align="center"  cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
					<tr class="Estilo3"  bgcolor="#3DB7E4" align="center">
					  <td rowspan="2">MOTIVO PRINCIPAL</td>
					  <td colspan="2">DESCRIPCION DEL MOTIVO</td>
					 
					  <td rowspan="2">ESTADO</td>
				  </tr>
					<tr class="Estilo3"  bgcolor="#3DB7E4" align="center">
					  <td>CODIGO</td>
						<td>DETALLE</td>
					</tr>
					<tr align="center">
					  <td>
            <select name="CMATERIA" class="celdas01" onfocus="nextfield ='CODIGO';">
					    <option value="" selected="selected">SELECCIONA</option>
					<%
            v =  (Vector) request.getAttribute ("vMateria");
            if (v != null) {  
              for (int i = 0; i < v.size(); i++) {  
                vCols = (Vector) v.get(i);  
          %>
					    <option value="<%=vCols.get(0)%>" <%= Util.jnvl((String)request.getParameter("CMATERIA"),"").compareTo(vCols.get(1))==0?"SELECTED":"" %>><%= vCols.get(1)%></option>
					<%
              }
            }
          %>
				    </select></td>
						<td><input name="CODIGO" type="text" class="celdas01" onfocus="nextfield ='DESCRIPCION';" size="10" maxlength="20"></td>
						<td><input name="DESCRIPCION" type="text" class="celdas01" onfocus="nextfield ='BESTADOREG';" onchange="cambia(this);" size="100" maxlength="100" /></td>
						
						<td>
							<select name="BESTADOREG" class="celdas01" onfocus="nextfield ='graba';">
								<option value="">SELECCIONA</option>
								<option value="1" <%= objLst.getAsunBestadoreg().toString().equals("1")?"selected":"" %>>ACTIVO</option>
								<option value="0" <%= objLst.getAsunBestadoreg().toString().equals("0")?"selected":"" %>>INACTIVO</option>
							</select>						</td>
					</tr>
					<tr align="center">
						<td colspan="5" bgcolor="#3DB7E4">
							<input type="hidden" name="USER" value="<%= clusuario.getUsuario() %>" />
							<input type="hidden" name="pg" value="<%= objLst.getPag()%>">
							<input type="hidden" name="opt" value="6">
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
	} 
%>
<table width="85%" border="0" align="center" class="titulo6">
  <tr align="center">
    <td><% if(msg != null){ %><%= msg %><% } %>&nbsp;</td>
  </tr>
</table>
</body>
<% 
	request.removeAttribute("objLst");
%>
</html>
