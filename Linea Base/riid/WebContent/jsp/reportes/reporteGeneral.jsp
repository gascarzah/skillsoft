<%@page import="util.Constantes"%>
<%@page import="util.Comun"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Locale"%>
<%@page import="bd.conexion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporter"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.Connection"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporter"%>
<%@page import="java.util.HashMap"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperCompileManager"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@ page import="clases.UsuarioSistema" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Report Viewer</title>
</head>
<body>
<%
UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario");


try{
	String relativeWebPath = "reportes/reporteGeneralXLS.jrxml";
	String strRealPath = request.getSession().getServletContext().getRealPath(relativeWebPath);
	//strRealPath = strRealPath.replaceAll("jakarta", "jakarta-tomcat-5.0.28");
	
	JasperReport jasperReport = JasperCompileManager.compileReport(strRealPath);	
	

Map parameter = (Map)request.getAttribute("parametros");

Connection connection = conexion.getConnection();

JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameter,connection);

//Export report as PDF

//response.setContentType("application/pdf");
ServletOutputStream servletOutputStream = response.getOutputStream();
//JasperExportManager.exportReportToPdfStream(jasperPrint,servletOutputStream);
//response.flushBuffer();

//Export report as EXCEL

JRXlsExporter jrXlsExporter = new JRXlsExporter();
response.setContentType("application/vnd.ms-excel");
response.setHeader("Content-disposition", "attachment; filename="+Constantes.REPORTE_GENERAL+Comun.getFechaActual()+".xls");
jrXlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,servletOutputStream);
jrXlsExporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
jrXlsExporter.exportReport();

servletOutputStream.flush();
servletOutputStream.close();
conexion.closeConnection(connection);

}catch(Exception e){
	e.printStackTrace();
}
%>

</body>
</html>