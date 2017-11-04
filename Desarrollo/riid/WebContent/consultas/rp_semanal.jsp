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
		String f0 = objLst.getcond01()!=null?objLst.getcond01():"";
		String f1 = "";
		String f2 = "";
		String f3 = "";
		String f4 = "";
		String f5 = "";
		String f6 = "";
		String f7 = "";
		String f8 = "";
		String f9 = "";
		if(f0.compareTo("1")==0){ f1 = "07"; f2 = "08"; f3 = "14"; f4 = "15"; f5 = "21"; f6 = "22"; f7 = "28"; f8 = "29"; f9 = "31"; } 
		if(f0.compareTo("2")==0){ f1 = "06"; f2 = "07"; f3 = "13"; f4 = "14"; f5 = "20"; f6 = "21"; f7 = "27"; f8 = "28"; f9 = "31"; } 
		if(f0.compareTo("3")==0){ f1 = "05"; f2 = "06"; f3 = "12"; f4 = "13"; f5 = "19"; f6 = "20"; f7 = "26"; f8 = "27"; f9 = "31"; } 
		if(f0.compareTo("4")==0){ f1 = "04"; f2 = "05"; f3 = "11"; f4 = "12"; f5 = "18"; f6 = "19"; f7 = "25"; f8 = "26"; f9 = "31"; } 
		if(f0.compareTo("5")==0){ f1 = "03"; f2 = "04"; f3 = "10"; f4 = "11"; f5 = "17"; f6 = "18"; f7 = "24"; f8 = "25"; f9 = "31"; } 
		if(f0.compareTo("6")==0){ f1 = "02"; f2 = "03"; f3 = "09"; f4 = "10"; f5 = "16"; f6 = "17"; f7 = "23"; f8 = "24"; f9 = "31"; } 
		if(f0.compareTo("7")==0){ f1 = "08"; f2 = "09"; f3 = "15"; f4 = "16"; f5 = "22"; f6 = "23"; f7 = "29"; f8 = "30"; f9 = "31"; } 
%>
<body class="body">
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="21%" rowspan="2" align="center"><img src="../images/es03.jpg" width="120" height="100"/></td>
    <td align="center" class="titulo1"><% if(clusuario.getCcas().compareTo("000")==0){ %>
      DEFENSORIA DEL ASEGURADO
      <% } else { %>
      OFICINA DE ATENCION AL ASEGURADO
      <% } %></td>
    <td width="21%" rowspan="4" align="right"><img src="../images/defensoria01.jpg" width="120" height="100"/></td>
  </tr>
  <tr>
    <td align="center" class="titulo3">FICHA DE REGISTRO DE RECLAMOS</td>
  </tr>
  <tr>
    <td width="21%" align="center"><input name="INPUTUSER" class="pie" value="<%= clusuario.getUsuario() %>" size="20" maxlength="20" /></td>
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
    <td colspan="3" align="center" class="titulo1">RECLAMOS POR MODALIDAD DE ATENCION SEMANAL</td>
  </tr>
  <tr>
    <td colspan="3" align="center" class="titulo1">&nbsp;</td>
  </tr>
</table>
<form name="f" method="post" action="../servlet/CtrlReporte" target="_self">
  <table width="60%" border="0" align="center">
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
      <td>MES Y A&Ntilde;O DE CONSULTA</td>
      <td><%= objLst.getdda()!=null?objLst.getdda():"" %> DEL  <%= objLst.getdda2()!=null?objLst.getdda2():"" %></td>
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
          <td>FECHA</td>
          <td>RESUELTO</td>
          <td>PENDIENTE</td>
          <td>EN TRAMITE</td>
          <td>TOTAL</td>
          <td>%</td>
          </tr>
        <tr class="celdas01">
          <td colspan="6" align="center">&nbsp;</td>
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
        <tr align="center" class="celdas01">
          <td>DEL 01 AL <%= f1 %></td>
          <td><%= v.get(0)!=null?v.get(0):"0" %></td>
          <td><%= v.get(1)!=null?v.get(1):"0" %></td>
          <td><%= v.get(2)!=null?v.get(2):"0" %></td>
          <td><%= v.get(3)!=null?v.get(3):"0" %></td>
          <td><%= v.get(4)!=null?v.get(4):"0" %></td>
        </tr>
        <tr align="center" class="celdas01">
          <td>DEL <%= f2 %> AL <%= f3 %></td>
          <td><%= v.get(5)!=null?v.get(5):"0" %></td>
          <td><%= v.get(6)!=null?v.get(6):"0" %></td>
          <td><%= v.get(7)!=null?v.get(7):"0" %></td>
          <td><%= v.get(8)!=null?v.get(8):"0" %></td>
          <td><%= v.get(9)!=null?v.get(9):"0" %></td>
          </tr>
        <tr align="center" class="celdas01">
          <td>DEL <%= f4 %> AL <%= f5 %></td>
          <td><%= v.get(10)!=null?v.get(10):"0" %></td>
          <td><%= v.get(11)!=null?v.get(11):"0" %></td>
          <td><%= v.get(12)!=null?v.get(12):"0" %></td>
          <td><%= v.get(13)!=null?v.get(13):"0" %></td>
          <td><%= v.get(14)!=null?v.get(14):"0" %></td>
          </tr>
        <tr align="center" class="celdas01">
          <td>DEL <%= f6 %> AL <%= f7 %></td>
          <td><%= v.get(15)!=null?v.get(15):"0" %></td>
          <td><%= v.get(16)!=null?v.get(16):"0" %></td>
          <td><%= v.get(17)!=null?v.get(17):"0" %></td>
          <td><%= v.get(18)!=null?v.get(18):"0" %></td>
          <td><%= v.get(19)!=null?v.get(19):"0" %></td>
        </tr>
        <tr align="center" class="celdas01">
          <td>DEL <%= f8 %> AL <%= f9 %></td>
          <td><%= v.get(20)!=null?v.get(20):"0" %></td>
          <td><%= v.get(21)!=null?v.get(21):"0" %></td>
          <td><%= v.get(22)!=null?v.get(22):"0" %></td>
          <td><%= v.get(23)!=null?v.get(23):"0" %></td>
          <td><%= v.get(24)!=null?v.get(24):"0" %></td>
        </tr>
        <tr class="celdas01">
          <td colspan="6" align="center">&nbsp;</td>
          </tr>
        <tr bgcolor="#3DB7E4" class="celdas01">
          <td align="center">TOTAL</td>
          <td align="center"><%= v.get(25)!=null?v.get(25):"0" %></td>
          <td align="center"><%= v.get(26)!=null?v.get(26):"0" %></td>
          <td align="center"><%= v.get(27)!=null?v.get(27):"0" %></td>
          <td align="center"><%= v.get(28)!=null?v.get(28):"0" %></td>
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
				<input type="hidden" name="upd" value="SEMANAL">
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
