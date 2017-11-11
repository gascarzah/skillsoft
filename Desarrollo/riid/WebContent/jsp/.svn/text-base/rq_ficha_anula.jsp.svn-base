<%@page import="util.Constantes"%>
<%@ page session="true" %>
<%@ page import="java.util.*" %>
<%@ page import="beans.WebUtil" %>
<%@ page import="beans.Util" %>
<%@ page import="clases.Ficha_t" %>
<%@ page import="clases.ListFicha_t" %>
<%@ page import="clases.UsuarioSistema" %>
<%@ page import="java.util.GregorianCalendar"%>
<%@ include file="../jsp/formato_fecha.jsp" %>
<%
  UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario");
	String msg = (String)request.getAttribute("msg_error");
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
function valida_rq(){
	if(document.f.AYEAR.value == ""){
		alert("Por favor ingrese el año");
		event.returnValue=false;
		document.f.AYEAR.focus();
	}
	else if(document.f.CCAS.value == ""){
		alert("Por favor ingrese el CAS");
		event.returnValue=false;
		document.f.CCAS.focus();
	}
	else if(document.f.CORREL.value == ""){
		alert("Por favor ingrese el Correlativo");
		event.returnValue=false;
		document.f.CORREL.focus();
	}
}
function getSalir(){
	location.href='../servlet/CtrlFicha?opt=0';
}
function getCancela(){
	location.href='../servlet/CtrlFicha?opt=16';
}
</script>
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body class="body" bgproperties="fixed" onload="javascript:document.f.AYEAR.focus();">
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
    <td align="center" class="celdasb"><%= clusuario.getDescripcion() %></td>
    <td rowspan="3" class="titulo4">&nbsp;</td>
  </tr>
  <tr align="center">
    <td align="center" class="celdasb"><%= clusuario.getdPerfil() %></td>
  </tr>
  <tr align="center">
    <td align="center" class="celdasb"><%= clusuario.getdCas() %></td>
  </tr>
</table>
<form method="post" name="f" action="../servlet/CtrlFicha" onSubmit="valida_rq();">
<table width="65%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td align="center" class="titulo1"><%=Constantes.TITULO_ANULACION_EXPEDIENTE %></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#007AC9" bgcolor="#FFFFFF">
        <tr>
          <td scope="col"><table width="100%" class="celdas01">
            <tr>
              <td width="15%">&nbsp;</td>
              <td width="35%">&nbsp;</td>
              <td>&nbsp;</td>
              </tr>
            <tr>
              <td>&nbsp;</td>
              <td><%=Constantes.SUBTITULO_CODIGO_DE_EXPEDIENTE%> </td>
              <td>
								<input name="AYEAR" type="text" class="celdas01" value="" size="4" maxlength="4" onfocus="nextfield ='CCAS';" /> -
								<input name="CCAS" type="text" class="celdas01" value="" size="3" maxlength="3" onfocus="nextfield ='CORREL';" /> - 
                <input name="CORREL" type="text" class="celdas01" value="" size="5" maxlength="5" onfocus="nextfield ='Submit';" />
							</td>
						</tr>
            <tr>
              <td colspan="3">&nbsp;</td>
            </tr>
          </table></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
  </table>
  <table width="65%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td align="center">&nbsp;
        <input type="hidden" name="opt" value="14">
      	<input type="hidden" name="pg" value="1">
			</td>
    </tr>
    <tr>
      <td align="center"><input name="Submit" type="submit" class="boton4" value="BUSCAR">
      <input name="Salir22" class="boton4" type="button" value="  SALIR  " onclick="getSalir();" /></td>
    </tr>
  </table>
</form>
<%
  ListFicha_t objLst = (ListFicha_t)request.getAttribute("objLst");
	if (objLst!=null) {
%>
<form name="f1" method="get" action="../servlet/CtrlFicha">
  <table width="85%"  border="1" align="center" cellspacing="0" bordercolor="#007AC9">
		<tr>
    	<td>
		  	<table width="100%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
					<tr bgcolor="#3DB7E4" class="Estilo3" align="center"> 
          	<td>CODIGO DE FICHA </td>
          	<td>FECHA DE REGISTRO</td>
          	<td bgcolor="#3DB7E4">TITULAR DE LA QUEJA</td>
          	<td>USUARIO QUE REGISTRO</td>
          	<td>ESTADO DEL REGISTRO</td>
          </tr>
					<tr class="celdas01"> 
            <td align="center"><%= objLst.getcodigoda()!=null?objLst.getcodigoda():"&nbsp;" %></td>
            <td align="center"><%= objLst.getdda()!=null?objLst.getdda():"&nbsp;" %></td>
            <td><%= objLst.gettitular()!=null?objLst.gettitular():"&nbsp;" %></td>
            <td align="center"><%= objLst.getinputuser()!=null?objLst.getinputuser():"&nbsp;" %></td>
					  <td align="center">
							<select name="BESTADOREG" class="celdas01">
								<option value="1" <%= objLst.getbestadoreg().toString().equals("1")?"selected":"" %>>ACTIVO</option>
								<option value="2" <%= objLst.getbestadoreg().toString().equals("2")?"selected":"" %>>ANULADO</option>
							</select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
  </table>
  <table width="85%" border="0" align="center" class="celdas01">
    <tr>
      <td align="center">&nbsp;
        <input type="hidden" name="AYEAR" value="<%= objLst.getcond01()!=null?objLst.getcond01():"&nbsp;" %>">
        <input type="hidden" name="CAS" value="<%= objLst.getcond02()!=null?objLst.getcond02():"&nbsp;" %>">
        <input type="hidden" name="CORREL" value="<%= objLst.getcond03()!=null?objLst.getcond03():"&nbsp;" %>">
        <input type="hidden" name="INPUTUSER" value="<%= clusuario.getUsuario() %>">
				<input type="hidden" name="opt" value="15">
				<input type="hidden" name="caso" value="E">
			</td>
    </tr>
    <tr align="center">
      <td>
				<input name="Submit2" type="submit" class="boton4" value="GRABAR">
				<input name="Salir" class="boton4" type="button" value="CANCELAR" onclick="getCancela();" />
				<input name="Salir2" class="boton4" type="button" value="  SALIR  " onclick="getSalir();" />
			</td>
    </tr>
  </table>
</form>
<%
	} 
%>
<table width="70%" border="0" align="center" class="titulo6">
  <tr align="center">
    <td><% if(msg != null){ %>
        <%= msg %>
        <% } %>
  &nbsp;</td>
  </tr>
</table>
</body>
<% 
	request.removeAttribute("objLst");
%>
</html>
