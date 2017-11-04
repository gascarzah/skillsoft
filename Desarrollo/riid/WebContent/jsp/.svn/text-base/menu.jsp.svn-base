<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="clases.UsuarioSistema" %>
<%@ page import="java.util.GregorianCalendar"%>
<%@ include file="../jsp/formato_fecha.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="imagetoolbar" content="no"> 
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css" >
<script language="javascript" src="../js/stm31.js"></script>
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<%
  UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario");
%>
<body class="body" bgproperties="fixed">
<table class="banner01" border="0" >
  <tr>
    <td width="220" rowspan="4" align="center"><img src="../images/es03.jpg" width="110" height="90" ></td>
    <td align="center" ><%@include file="../js/menu_all.js" %></td>
    <td width="220" rowspan="7" align="right"><img src="../images/defensoria01.jpg" width="120" height="100"></td>
  </tr>
  <tr>
    <td  align="center" rowspan="2"  class="titulob2">REGISTRO INFORMÁTICO DE ATENCIÓN AL ASEGURADO</td>
  </tr>
  <tr align="center" >
    
  </tr>
  <tr align="center">
    <td rowspan="4"  align="center" class="titulob2">
      Gerencia Central de Atención al Asegurado
    </td>
  </tr>
  <tr align="center">
    <td width="220" align="center" class="celdasb"><%= clusuario.getDescripcion() %></td>
    <!-- <td align="center" class="titulob1">&nbsp;</td> -->
  </tr>
  <tr align="center">
    <td align="center" class="celdasb"><%= clusuario.getdPerfil() %></td>
    <!-- <td>&nbsp;</td> -->
  </tr>
  <tr align="center">
    <td align="center" class="celdasb"><%= clusuario.getdCas() %></td>
    <!-- <td>&nbsp;</td> -->
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
    <td valign="top" align="center"><img src="../images/fondo05.jpg" style="height: 567px; width: 972px; "/></td>
  </tr>
</table>
</body>
</html>
