<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@ page session="true" %>
<html>
<head>
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../js/script2.js"></script>
<script language="javascript1.2">
function valida(){
  if (document.f.CLAVE.value == ""){
		alert("Por favor ingrese su nueva Contraseña");
		event.returnValue=false;
		document.f.CLAVE.focus();
  }
  else if (document.f.CLAVE1.value == ""){
		alert("Por favor ingrese su confirmación de Contraseña");
		event.returnValue=false;
		document.f.CLAVE1.focus();
  }
  else if (document.f.CLAVE.value != document.f.CLAVE1.value){
		alert("Por favor la nueva contraseña y la confimación deben ser iguales");
		event.returnValue=false;
		document.f.CLAVE.focus();
  }
  else if (document.f.CLAVE.value == "123"){
		alert("Por favor cambie su Contraseña");
		event.returnValue=false;
		document.f.CLAVE.focus();
  }
	else {
	  document.f.submit();
	}
}
function confirma() {
  if(valida()){
	if (confirm("¿Está seguro de cambiar de contraseña?")) {
	  document.f.submit();
	}
  }
}
</script>
</head>
<%
  String user = request.getParameter("USUARIO")!=null?request.getParameter("USUARIO"):"";
%>
<body onLoad="document.f.CLAVE.focus();" class="body">
  <form action="../servlet/CtrlUsuarios" method="post" name="f" target="_self" onSubmit="valida();">
	<table width="380" height="135"  border="1" align="center" bgcolor="#DFE6EE">
	  <tr bordercolor="#B0C4DE"> 
		<td width="146" height="130" align="center" bgcolor="#B0C4DE"><img src="../images/intranet4.jpg" width="87" height="105"></td>
		<td width="373"> 
		  <table width="100%" border="0" align="center" bgcolor="#B0C4DE">
			<tr class="celdas"> 
			  <td colspan="2">&nbsp;</td>
			</tr>
			<tr class="celdas"> 
			  <td class="titulo1">&nbsp;NUEVA CONTRASE&Ntilde;A</td>
			  <td align="center"><input name="CLAVE" type="password" class="celdas" onFocus="nextfield ='CLAVE1';" size="10" maxlength="10"></td>
			</tr>
			<tr align="center" class="celdas"> 
			  <td colspan="2">&nbsp;</td>
			</tr>
			<tr class="celdas"> 
			  <td class="titulo1">&nbsp;CONFIRMA CONTRASE&Ntilde;A</td>
			  <td align="center"><input name="CLAVE1" type="password" class="celdas" onFocus="nextfield ='Submit';" size="10" maxlength="10"></td>
			</tr>
			<tr align="center" class="celdas"> 
			  <td colspan="2">&nbsp;</td>
			</tr>
			<tr align="center" class="celdas"> 
			  <td colspan="2">
					<input type="submit" name="Submit" class="boton01" value="GRABAR">
					<input name="cerrar" type="button" class="boton01" onClick="javascript:window.close();" value="CERRAR">
					<input name="upd" type="hidden" value="cambio">
					<input name="USUARIO" type="hidden" value="<%= user %>">
			  </td>
			</tr>
	    </table>
		</td>
	  </tr>
	</table>
  </form>
</body>
</html>
