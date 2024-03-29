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
<% response.setContentType("application/vnd.ms-excel"); 

	response.setHeader ("Content-disposition", "inline;  filename="+Constantes.REPORTE_GENERAL+Comun.getFechaActual()+".xls");%> 

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
%>
<body bgcolor="#DFE6EE">
<form name="f" method="get" action="../servlet/CtrlReporte">
	<table width="85%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
		<tr bgcolor="#FFFFFF" class="style1" align="center">
			<td colspan="51" align="right"><%= clusuario.getUsuario() %></td>
		</tr>
		<tr bgcolor="#FFFFFF" class="style1" align="center">
			<td colspan="51" align="right"><%= dia + "/" + mes + "/" + ano + " " + hor + ":" + min + ":" + seg %></td>
		</tr>
		<tr bgcolor="#FFFFFF" class="style1" align="center">
			<td colspan="51">GERENCIA CENTRAL DE ATENCIÓN AL ASEGURADO</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="style1" align="center">
			<td colspan="53">REPORTE DEL TOTAL DE EXPEDIENTES REGISTRADOS</td>
		</tr>
<% if(!objLst.getcred().equals("")){ %>
		<tr bgcolor="#FFFFFF" class="style1" align="center">
			<td colspan="53"><%= objLst.getdred()!=null?objLst.getdred():"" %></td>
		</tr>
<% } if(objLst.getccas().equals("")){ %>
		<tr bgcolor="#FFFFFF" class="style1" align="center">
			<td colspan="53"><%= objLst.getcenasides()!=null?objLst.getcenasides():"" %></td>
		</tr>
<% } if(objLst.getFechaInicio().equals("")){ %>
		<tr bgcolor="#FFFFFF" class="style1" align="center">
			<td colspan="53">FECHA DE REGISTRO: DEL <%= objLst.getFechaInicio()!=null?objLst.getFechaInicio():"" %> AL <%= objLst.getFechaFin()!=null?objLst.getFechaFin():"" %></td>
		</tr>
<% } %>
		<tr bgcolor="#FFFFFF" class="style1" align="center">
			<td colspan="53">&nbsp;</td>
		</tr>
		<tr>
		  <td></td>
		  <td colspan="9" bgcolor="#DBEEF3" align="center" >SOLICITANTE</td>
		  <td colspan="15" bgcolor="#B0C4DE" align="center" >INVOLUCRADO</td>
		  <td colspan="11"></td>
		  <td colspan="4" bgcolor="#DBEEF3" align="center"  >SERVIDOR O FUNCIONARIO DE QUIEN SE RECLAMA</td>
		  <td align="center" bgcolor="#B0C4DE" >INTERVENCION DEFENSORIAL</td>
		  <td colspan="2" align="center" bgcolor="#B0C4DE" >CONCLUSION Y SEGUIMIENTO</td>
		  <td colspan="5"></td>
		</tr>
		<tr class="style2" align="center">
			<td bgcolor="#B0C4DE">CODIGO DE EXPEDIENTE</td>
			<td bgcolor="#DBEEF3">TIPO DE DOCUMENTO PARA LA IDENTIDAD</td>
			<td bgcolor="#DBEEF3">NUMERO DEL DOCUMENTO</td>
			<td bgcolor="#DBEEF3">APELLIDO PATERNO</td>
			<td bgcolor="#DBEEF3">APELLIDO MATERNO</td>
			<td bgcolor="#DBEEF3">NOMBRE</td>
			<td bgcolor="#DBEEF3">TELEFONO 1</td>
			<td bgcolor="#DBEEF3">TELEFONO 2</td>
			<td bgcolor="#DBEEF3">DIRECCION</td>
			<td bgcolor="#DBEEF3">CORREO ELECTRONICO</td>
			
			<td bgcolor="#B0C4DE">TIPO DE DOCUMENTO PARA LA IDENTIDAD</td>
			<td bgcolor="#B0C4DE">NUMERO DEL DOCUMENTO </td>
			<td bgcolor="#B0C4DE">APELLIDO PATERNO</td>
			<td bgcolor="#B0C4DE">APELLIDO MATERNO </td>
			<td bgcolor="#B0C4DE">NOMBRE</td>
			<td bgcolor="#B0C4DE">SEXO</td>
			<td bgcolor="#B0C4DE">EDAD</td>
			<td bgcolor="#B0C4DE">TELEFONO</td>
			<td bgcolor="#B0C4DE">DEPARTAMENTO</td>
			<td bgcolor="#B0C4DE">PROVINCIA</td>
			
			<td bgcolor="#B0C4DE">DISTRITO</td>
			<td bgcolor="#B0C4DE">DIRECCION</td>
			<td bgcolor="#B0C4DE">CORREO ELECTRONICO</td>
			<td bgcolor="#B0C4DE">TIPO DE PRESTACION</td>
			<td bgcolor="#B0C4DE">TIPO DE SEGURO</td>
			<td bgcolor="#B0C4DE">MEDIO O VIA DE INGRESO</td>
			<td bgcolor="#B0C4DE">UNIDAD ORGANICA NIVEL 01</td>
			<td bgcolor="#B0C4DE">UNIDAD ORGANICA NIVEL 02</td>
			<td bgcolor="#B0C4DE">DESCRIPCION DE LOS HECHOS</td>
			<td bgcolor="#B0C4DE">ESTADO</td>
			
			<td bgcolor="#B0C4DE">TIPO DE LA SOLICITUD</td>
			<td bgcolor="#B0C4DE">TEMATICA</td>
			<td bgcolor="#B0C4DE">MOTIVO PRINCIPAL</td>
			<td bgcolor="#B0C4DE">DETALLE DE MOTIVO PRINCIPAL</td>
			<td bgcolor="#B0C4DE">AREA</td>
			<td bgcolor="#B0C4DE">SERVICIO</td>
			<td bgcolor="#DBEEF3">APELLIDO PATERNO</td>
			<td bgcolor="#DBEEF3">APELLIDO MATERNO</td>
			<td bgcolor="#DBEEF3">NOMBRE</td>		
			<td bgcolor="#DBEEF3">GRUPO OCUPACIONAL</td>	
			
			<td bgcolor="#B0C4DE">[ITEM, ACCIONES REALIZADAS, CON QUE PERSONA,  FECHA, CARGO / ROL / CONDICION]</td>	
			<td bgcolor="#B0C4DE">[ITEM, CONCLUSION, ACCIONES DE SEGUIMIENTO, CON QUE PERSONA, CARGO / ROL / CONDICION]</td>	
			<td bgcolor="#B0C4DE">FECHA DE CONCLUSION</td>
			<td bgcolor="#B0C4DE">DELEGADO ASIGNADO</td>	
			<td bgcolor="#B0C4DE">UNIDAD ORGANICA NIVEL 1 - DELEGADO ASIGNADO</td>
			<td bgcolor="#B0C4DE">UNIDAD ORGANICA NIVEL 2 - DELEGADO ASIGNADO</td>
			<td bgcolor="#B0C4DE">USUARIO CREADOR</td>
			<td bgcolor="#B0C4DE">FECHA DE REGISTRO</td>
			<td bgcolor="#B0C4DE">USUARIO MODIFICADOR</td>
			<td bgcolor="#B0C4DE">FECHA DE MODIFICACION</td>
			<td bgcolor="#B0C4DE">FECHA DE ASIGNACION</td>
			<td bgcolor="#B0C4DE">TIPO INGRESO LIBRO RECLAMACIONES</td>
			<td bgcolor="#B0C4DE">NRO HOJA LIBRO RECLAMACIONES</td>
		</tr>
		  <%
		  List lista = objLst.getLista();
		  	ReporteGeneralBean general = null;
			for (int i=0; i<lista.size();i++) {
				general = (ReporteGeneralBean) lista.get(i);
		  %>
		<tr bgcolor="#FFFFFF" class="style2">
			<td align="center"><%= StringUtils.defaultString(general.getCodigoficha()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getTipodocumentosolicitante()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getNumerodocumentosolicitante()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getApellidopaternosolicitante()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getApellidomaternosolicitante()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getNombresolicitante())%></td>			
			<td align="center"><%= StringUtils.defaultString(general.getTelefonosolicitante()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getCelularsolicitante()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getDireccionsolicitante()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getMailsolicitante()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getTipodocumentoagraviado()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getNumerodocumentoagraviado()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getApellidopaternoagraviado()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getApellidomaternoagraviado()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getNombreagraviado()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getSexo()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getEdad()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getTelefonoagraviado()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getDepartamento()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getProvincia()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getDistrito()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getDireccionagraviado()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getMailagraviado()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getTipoprestasion()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getTiposeguro()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getViaingreso()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getUnidadorganica1()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getUnidadorganica2()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getDescripcionhechos()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getEstado()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getTiposolicitud()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getTematica()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getMotivoprincipal()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getDescripcionmotivo()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getArea()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getServicio()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getApellidopaternodequiensequeja()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getApellidomaternodequiensequeja()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getNombredequiensequeja()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getOcupaciondequiensequeja()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getAccrealizadas()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getAccseguimiento()) %></td>		
			<td align="center"><%= StringUtils.defaultString(general.getFechaconclusion()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getDelegado()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getUnidadorganica1delegado()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getUnidadorganica2delegado()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getUsuariocreador()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getFechacreador()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getUsuariomodificador()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getFechamodificacion()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getFechaasignacion()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getTipoingresoexpediente()) %></td>
			<td align="center"><%= StringUtils.defaultString(general.getNrohojareclamo()) %></td>
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
