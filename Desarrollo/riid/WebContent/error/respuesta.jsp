<%@ page session="true" %>
<%@ page import="java.util.*" %>
<%@ page import="beans.WebUtil" %>
<%@ page import="beans.Util" %>
<%@ page import="clases.Ficha_t" %>
<%@ page import="clases.UsuarioSistema" %>
<%@ page import="java.util.GregorianCalendar"%>
<%@ include file="../jsp/formato_fecha.jsp" %>
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
 function regresa() {
    top.location.href='../servlet/CtrlFicha?opt=FICHA';
 } 
</script>
</head>
<%
  UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario");
	String msg = (String)request.getAttribute("msg_error");
%>
<body class="body">
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="21%" rowspan="2" align="center"><img src="../images/es03.jpg" width="120" height="100"/></td>
    <td align="center" class="titulo1"><% if(clusuario.getCcas().compareTo("000")==0){ %> DEFENSORIA DEL ASEGURADO <% } else { %> OFICINA DE ATENCION AL ASEGURADO <% } %></td>
    <td width="21%" rowspan="4" align="right"><img src="../images/defensoria01.jpg" width="120" height="100"/></td>
  </tr>
  <tr>
    <td align="center" class="titulo3">FICHA DE REGISTRO DE RECLAMOS</td>
  </tr>
  <tr>
    <td width="21%" align="center"><input name="INPUTUSER" class="pie" value="<%= clusuario.getDescripcion() %>" size="30" maxlength="30" /></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center" class="pie"><%= dia + "/" + mes + "/" + ano + " " + hor + ":" + min + ":" + seg %></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" class="titulo4">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center"><% if(clusuario.getCcas().compareTo("000")==0){ %><script language="JavaScript" src="../js/menu_sede.js" type="text/javascript"></script><% } else { %><script language="JavaScript" src="../js/menu_oas.js" type="text/javascript"></script><% } %></td>
  </tr>
</table>
<form method="post" name="f">
  <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td align="center"><table cellpadding="2" cellspacing="2" border="1" bgcolor="#B0C4DE" bordercolor="#B0C4DE">
        <tr class="titulo2">
          <td bgcolor="#FFFFFF">&nbsp; <%= msg!=null?msg:"Mensaje de error no especificado Informe al Supervisor" %> &nbsp; </td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
	   <td align="center"><input name="boton" type="button" class="boton4" onclick="regresa();" value="Aceptar" /></td>
    </tr>
  </table>
</form>
</body>

</html>
