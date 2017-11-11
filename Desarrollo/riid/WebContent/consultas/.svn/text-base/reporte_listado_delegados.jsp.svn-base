<%@page import="bean.ReporteFichaPorDelegadoBean"%>
<%@page import="util.Comun"%>
<%@page import="util.Constantes"%>
<%@page import="bean.ReporteGeneralBean"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.apache.commons.beanutils.DynaBean"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="clases.ListFicha_t" %>
<%@ page import="clases.UsuarioSistema" %>
<%@ page import="beans.WebUtil" %>
<%@ page import="java.util.*"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ include file="../jsp/formato_fecha.jsp" %>
<%  
	response.setContentType("application/pdf");
	response.setHeader ("Content-disposition", "inline;  filename="+Constantes.REPORTE_GENERAL+Comun.getFechaActual()+".pdf");%> 

<html>
<head>
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
<!--
.style1 {font-size: 12px; font-family: Arial, Helvetica, sans-serif; }
.style2 {font-size: 10px; font-family: Arial, Helvetica, sans-serif; }
-->
</style>
</head>
<%
  try {
		UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario");
		ListFicha_t objLst = (ListFicha_t)request.getAttribute("objLst");
		List lista = (List)request.getAttribute("listadoFichaDiaria");
		String cdelegado = (String)request.getAttribute("cdelegado");
		
	
		
		
%>
<body bgcolor="#DFE6EE">
<form name="f" method="get" action="../servlet/CtrlReporte">
	<table>
		<tr bgcolor="#FFFFFF" class="style1" align="center">
			<td colspan="12" align="right">USUARIO :<%= clusuario.getUsuario() %></td>
		</tr>
		<tr bgcolor="#FFFFFF" class="style1" align="center">
			<td colspan="12" align="right"><%= dia + "/" + mes + "/" + ano + " " + hor + ":" + min + ":" + seg %></td>
		</tr>
		<% if(StringUtils.isNotEmpty(cdelegado)) { %>
		<tr bgcolor="#FFFFFF" class="style1" align="center">
			<td colspan="12" align="right">DELEGADO : <%= cdelegado %> </td>
		</tr>
		<%} %>
		<tr bgcolor="#FFFFFF" class="style1" align="center">
			<td colspan="12">DEFENSORIA DEL ASEGURADO</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="style1" align="center">
			<td colspan="12">REPORTE FICHAS REGISTRADAS POR DELEGADO</td>
		</tr>
		
		
		<tr bgcolor="#FFFFFF" class="style1" align="center">
			<td colspan="12">&nbsp;</td>
		</tr>
		<tr>
		<td  align="center" colspan="2" ></td>
		  <td align="center" colspan="2">CODIGO</td>
		  <td  align="center" colspan="2">FECHA DE REGISTRO</td>
		  <td align="center" colspan="2">DATOS SOLICITANTE</td>
		  <td align="center" colspan="2">Datos del Involucrado/Involucrada</td>
		  <td  align="center" colspan="2" >ESTADO</td>
		  
		</tr>
		
		
		  <%
		  
		  ReporteFichaPorDelegadoBean general = null;
			for (int i=0; i<lista.size();i++) {
				general = (ReporteFichaPorDelegadoBean) lista.get(i);
		  %>
		<tr bgcolor="#FFFFFF" class="style2">
		    <td align="center" colspan="2"></td>
			<td align="center" colspan="2"><%= StringUtils.defaultString(general.getCodigo()) %></td>
			<td align="center" colspan="2"><%= StringUtils.defaultString(general.getFecharegistro()) %></td>
			<td align="center" colspan="2"><%= StringUtils.defaultString(general.getSolicitante()) %></td>
			<td align="center" colspan="2"><%= StringUtils.defaultString(general.getInvolucrado()) %></td>
			<td align="center" colspan="2"><%= StringUtils.defaultString(general.getEstado()) %></td>
			<td align="center" colspan="2"></td>
			
		</tr>
		  <%
			}  //del for
		  %>
	</table>
</form>
</body>
<%
	request.removeAttribute("objLst");
  }
  catch (Exception e) {
	  e.printStackTrace();
  } 
%>
</html>
