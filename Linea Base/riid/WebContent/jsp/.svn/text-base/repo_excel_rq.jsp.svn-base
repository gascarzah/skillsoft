<%@ page session="true" %>
<%@page import="util.Constantes"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css">
<link href="../styles/botones.css" rel="stylesheet" media="print" type="text/css">
<script language="JavaScript" src="../js/script2.js"></script>
<script language="javascript" src="../js/format_date.js"></script>
<script language="javascript" src="../js/stm31.js"></script>

<!--  ini CALENDARIO -->
<link rel="stylesheet" type="text/css" media="all" href="../styles/skins/aqua/theme.css" title="Aqua" />
<link rel="alternate stylesheet" type="text/css" media="all" href="../styles/calendar-blue.css" title="winter" />
<script type="text/javascript" src="../js/calendar.js"></script>
<script type="text/javascript" src="../js/calendario2.js"></script>
<script type="text/javascript" src="../js/calendar-es.js"></script>
<script language="javascript" src="../js/val_campos.js"></script>

<script type="text/javascript">
setActiveStyleSheet(document.getElementById("defaultTheme"), "Aqua");
</script>
<!--  Fin CALENDARIO -->


<script language="javascript">
function valida_rq(){
	if(document.f.CRED.value == "" && document.f.CCAS.value == "" && document.f.FECHA1.value == "" && document.f.FECHA2.value == ""){
		alert("Por favor ingrese algún criterio de búsqueda");
		event.returnValue=false;
		document.f.CRED.focus();
	}
	else if(document.f.FECHA1.value == "" && document.f.FECHA2.value != ""){
		alert("Por favor ingrese el rango de fecha");
		event.returnValue=false;
		document.f.FECHA1.focus();
	}
	else if(document.f.FECHA1.value != "" && document.f.FECHA2.value == ""){
		alert("Por favor ingrese el rango de fecha");
		event.returnValue=false;
		document.f.FECHA2.focus();
	}
}

function pasa(fun,cod){
  parent.bot.location.href='../jsp/botFicha.jsp?funcion='+fun+'&codigo='+cod;
}
</script>
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body class="body">
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
    <td align="center" class="titulob2">REGISTRO INFORM&Aacute;TICO DE ATENCI&Oacute;N AL ASEGURADO</td>
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
<form method="post" name="f" action="../servlet/CtrlReporte" onSubmit="valida_rq();" target="_blank">
<table width="75%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td align="center" class="titulo1">BUSQUEDA DE FICHAS PARA EXPORTAR A EXCEL</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#6699CC" bgcolor="#EAEFF5">
        <tr>
          <td scope="col"><table width="100%" class="celdas01">
            <tr>
              <td width="15%">&nbsp;</td>
              <td width="35%">&nbsp;</td>
              <td colspan="2">&nbsp;</td>
              </tr>
            <tr>
              <td>&nbsp;</td>
              <td><%=Constantes.UNIDADORG %></td>
              <td colspan="2">
            <select name="CRED" class="celdas01" onfocus="nextfield ='CCAS';" onchange="pasa('CASC',this.form.CRED.options[this.form.CRED.selectedIndex].value);">
                <option value="">SELECCIONA</option>
					<%
						Vector  v =  (Vector) request.getAttribute ("vRed");
						Vector vCols;
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
								<option value="<%=vCols.get(0)%>" ><%= vCols.get(1)%> </option>
          			<%
							}
						}
					%>
              </select></td>
              </tr>
            <tr>
              <td colspan="4">&nbsp;</td>
              </tr>
            
            <tr>
              <td>&nbsp;</td>
              <td><%=Constantes.UNIDADORG1 %></td>
              <td colspan="2"><select name="CCAS" class="celdas01" onfocus="nextfield ='FECHA1';">
                <option value="">SELECCIONA</option>
                <%
						v =  (Vector) request.getAttribute ("vCas");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
                <option value="<%=vCols.get(0)%>" ><%= vCols.get(2)%> </option>
                <%
							}
						}
					%>
              </select></td>
              </tr>
            
            <tr>
              <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>RANGO DE FECHAS DE REGISTRO</td>
              <td><input name="FECHA1" id="sel1" type="text" class="celdas01" size="10"  maxlength="10"><input type="reset" value=" ... " onclick="return showCalendar('sel1', '%d/%m/%Y');"></td>
              <td><input name="FECHA2" id="sel2" type="text" class="celdas01" size="10"  maxlength="10"><input type="reset" value=" ... " onclick="return showCalendar('sel2', '%d/%m/%Y');"></td>
            </tr>
            <tr>
              <td colspan="4">&nbsp;</td>
            </tr>
          </table></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
  </table>
  <table width="75%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td align="center">&nbsp;
				<input type="hidden" name="opt" value="1">
      	<input type="hidden" name="pg" value="1">
      	<input type="hidden" name="upd" value="EXPORT">
			</td>
    </tr>
    <tr>
      <td align="center"><input type="submit" name="Submit2" class="boton001" value="Exportar"></td>
    </tr>
  </table>
</form>
</body>
</html>
