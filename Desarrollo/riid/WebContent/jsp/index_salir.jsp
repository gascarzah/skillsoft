
<%@page import="clases.Tablas_t"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %> 
<%@ page import="javax.servlet.http.HttpServletResponse" %> 
<%@ page import="javax.servlet.http.HttpSession" %> 
<%@ page session="true" %>
<%@ include file="formato_fecha.jsp" %>
<%
	Tablas_t clusuario = (Tablas_t)session.getAttribute("sClusuario");
	String msg = (String)request.getAttribute("msg_error");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="imagetoolbar" content="no"> 
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css" />

<script language="javascript">
function cerrar(){
	location.href = "../jsp/index_carga.jsp";
/*
	if (event.clientY < 0){
		event.returnValue = ""; 
		setTimeout('micierre = false', 100); 
		micierre = true; 
		window.parent.close();
	}
	window.parent.close();
*/
}
</script> 
</head>
<body>
<table width="85%" border="0" align="center" class="banner01">
  <tr>
    <td width="220" rowspan="5" align="center"><img src="../images/es03.jpg" width="120" height="100"/></td>
    <td align="center" class="titulo1" style="color: white;">REGISTRO  INFORMÁTICO DE ATENCIÓN AL ASEGURADO</td>
    <td width="220" rowspan="5" align="right" ><img src="../images/defensoria01.jpg" width="120" height="100"/></td>
  </tr>
  <tr>
    <td align="center" class="titulo1">&nbsp;</td>
    
  </tr>
  <tr>
    <td rowspan="2" align="center" class="titulob2">Gerencia Central de Atención al Asegurado</td>
  </tr>
</table>
<form name="f" method="post">
  <table width="85%" border="0" align="center">
    <tr>
      <td align="center"><a href="../jsp/index_carga.jsp" ><img border="0" src="../images/salir.png" width="370" height="400"/></a></td>   
    </tr>    
    <tr>
      <td align="center" class="titulo6">&nbsp;
          <% if(msg != null){ %>
          <%= msg %>
          <% } %>      </td>
    </tr>
    <tr>    
      <td align="center"></td>
   </tr>
    <tr>
    <td align="center" ></td>
    </tr>
  </table>
</form>
<% 
	session.removeAttribute("sClusuario");
	//session.invalidate();
%>
</body>
</html>
