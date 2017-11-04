<%@ page session="true" %>
<%@ page import="java.util.*"%>
<%@ page import="beans.WebUtil" %>
<%@ page import="beans.Util" %>
<%@ page import="clases.Ficha_t" %>
<%@ page import="clases.ListFicha_t" %>
<%@ page import="clases.UsuarioSistema" %>
<%@ page import="java.util.GregorianCalendar"%>
<%@ include file="../jsp/formato_fecha.jsp" %>

<html>
<head>
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../styles/celdas.css" rel="stylesheet" type="text/css">
<link href="../styles/botones.css" rel="stylesheet" media="print" type="text/css">
</head>
<%
  try {
		UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario");
		ListFicha_t objLst = (ListFicha_t)request.getAttribute("objLst");
%>
<body class="body" >
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

<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center" class="pie"></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" class="titulo4">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" class="titulo1">RECLAMOS POR ESTADO SITUACIONAL</td>
  </tr>
  <tr>
    <td colspan="3" align="center" class="titulo1">&nbsp;</td>
  </tr>
</table>
<form name="f" method="post" action="../servlet/CtrlReporte" target="_self">
  <table width="70%" border="0" align="center">
    <tr>
      <td colspan="3" align="center" class="titulo5">CRITERIOS DE BUSQUEDA</td>
    </tr>
    <tr>
      <td width="20%">&nbsp;</td>
      <td width="30%">&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
<% if(objLst.getcred().compareTo("")!=0){ %>
    <tr>
      <td>&nbsp;</td>
      <td>RED ASISTENCIAL</td>
      <td>&nbsp;<%= objLst.getdred()!=null?objLst.getdred():"" %></td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
<% } if(objLst.getccas().compareTo("")!=0){ %>
    <tr>
      <td>&nbsp;</td>
      <td>CENTRO ASISTENCIAL </td>
      <td>&nbsp;<%= objLst.getcenasides()!=null?objLst.getcenasides():"" %></td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
<% } if(objLst.getdda().compareTo("")!=0){ %>
    <tr>
      <td>&nbsp;</td>
      <td>RANGO DE FECHAS DE REGISTRO </td>
      <td>DEL <%= objLst.getdda()!=null?objLst.getdda():"" %> AL <%= objLst.getdda2()!=null?objLst.getdda2():"" %></td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
<% } %>
  </table>
  <table width="60%"  border="1" align="center" cellspacing="0" bordercolor="#6699CC">
    <tr>
      <td><table width="100%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
        <tr bgcolor="#3DB7E4" class="celdas01" align="center">
          <td bgcolor="#3DB7E4">POR ESTADO SITUACIONAL</td>
          <td>N&ordm;</td>
          <td>%</td>
          </tr>
        <tr class="celdas01">
          <td colspan="3" align="center">&nbsp;</td>
			<%
				Vector v;
				Vector vCols;
				int counter = 0;
				for (Enumeration e = objLst.getHshLista().keys(); e.hasMoreElements();) {
					e.nextElement();
					v = (Vector)objLst.getHshLista().get("" + counter);
					counter++;
			%>
        </tr>
        <tr class="celdas01">
          <td align="center">RESUELTAS</td>
          <td align="center"><%= v.get(0)!=null?v.get(0):"0" %></td>
          <td align="center"><%= v.get(1)!=null?v.get(1):"0" %></td>
          </tr>
        <tr class="celdas01">
          <td align="center">PENDIENTES</td>
          <td align="center"><%= v.get(2)!=null?v.get(2):"0" %></td>
          <td align="center"><%= v.get(3)!=null?v.get(3):"0" %></td>
          </tr>
        <tr class="celdas01">
          <td align="center">EN TRAMITE</td>
          <td align="center"><%= v.get(4)!=null?v.get(4):"0" %></td>
          <td align="center"><%= v.get(5)!=null?v.get(5):"0" %></td>
        </tr>
        <tr class="celdas01">
          <td colspan="3" align="center">&nbsp;</td>
          </tr>
        <tr bgcolor="#3DB7E4" class="celdas01">
          <td align="center">TOTAL</td>
          <td align="center"><%= v.get(6)!=null?v.get(6):"0" %></td>
          <td align="center">100.00</td>
        </tr>
			<%
				}
			%>
      </table></td>
    </tr>
  </table>
  <table width="60%" border="0" align="center">
    <tr>
      <td>&nbsp;
				<input type="hidden" name="opt" value="1">
				<input type="hidden" name="upd" value="ESTADOS">
				<input type="hidden" name="CRED" value="<%= objLst.getcred()!=null?objLst.getcred():"" %>">
				<input type="hidden" name="CCAS" value="<%= objLst.getccas()!=null?objLst.getccas():"" %>">
				<input type="hidden" name="FECHA1" value="<%= objLst.getdda()!=null?objLst.getdda():"" %>">
				<input type="hidden" name="FECHA2" value="<%= objLst.getdda2()!=null?objLst.getdda2():"" %>">
			</td>
    </tr>
    <tr>
      <td class="titulo5">Fuente: Base de datos de la Oficina de Atención al Asegurado</td>
    </tr>
    <tr>
      <td align="center">&nbsp;</td>
    </tr>
    <tr>
      <td align="center"><input type="submit" name="Submit" value="Imprimir" onClick="javascript:window.print()">
      <input type="submit" name="Submit2" value="Cerrar" onClick="javascript:window.close()"></td>
    </tr>
  </table>
</form>
</body>
<%
  }
  catch (NullPointerException e) {
		System.out.println("Error en rp_excel.jsp");
	} 
	finally {
		request.removeAttribute("objLst");
	}
%>
</html>
