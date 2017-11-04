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
<script language="javascript">
function goPg(np) {
  document.f.pg.value = np;
  document.f.submit();
}  
</script>
</head>
<%
  try {
		UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario");
		ListFicha_t objLst = (ListFicha_t)request.getAttribute("objLst");
		Vector v;
		Vector vCols;
		String strEstado = "";
		if(objLst.getestado().compareTo("01")==0){
			strEstado = "PENDIENTE";
		}
		else if(objLst.getestado().compareTo("02")==0){
			strEstado = "EN TRAMITE";
		}
		else if(objLst.getestado().compareTo("03")==0){
			strEstado = "RESUELTO";
		}
		else if(objLst.getestado().compareTo("04")==0){
			strEstado = "ARCHIVADO";
		}
		else{
			strEstado = "TODOS";
		}
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
    <td colspan="3" align="center" class="titulo1">PRODUCCION DE USUARIOS POR CODIGO DEL ASUNTO</td>
  </tr>
  <tr>
    <td colspan="3" align="center" class="titulo1">&nbsp;</td>
  </tr>
</table>
<form name="f" method="post">
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
    <tr>
      <td>&nbsp;</td>
      <td>ESTADO</td>
      <td><%= strEstado %></td>
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
  <table width="95%" border="0" align="center" cellspacing="0">
    <tr class="celdas01" >
      <td width="25%">&nbsp;</td>
      <td>&nbsp;</td>
      <td align="right" width="25%"><%  if ( objLst.getPaginacion().getPaginaAnt()) { %>
          <input name="button" value="  <<  " type="button" class="boton03" onClick="goPg(<%= Integer.parseInt(objLst.getPag()) - 1%>);" title="Retroceder a pagina anterior">
          <%  } 
		    if ( objLst.getPaginacion().getPaginaSgte()) { %>
          <input name="button" value="  >>  " type="button" class="boton03" onClick="goPg(<%= Integer.parseInt(objLst.getPag()) + 1%>);" title="Avanzar a pagina siguiente">
          <%  } %>
      </td>
    </tr>
  </table>
  <table width="95%"  border="1" align="center" cellspacing="0" bordercolor="#6699CC">
    <tr>
      <td>
				<table width="100%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
        	<tr bgcolor="#3DB7E4" class="celdas01" align="center">
						<td width="4%">ITEM</td>
					<% if(objLst.getccas().compareTo("")==0){ %>
						<td>CTRO ASISTENCIAL</td>
					<% } %>
						<td>USUARIO</td>
						<td>C01</td>
						<td>C02</td>
						<td>C03</td>
						<td>C04</td>
						<td>C05</td>
						<td>C06</td>
						<td>C07</td>
						<td>C08</td>
						<td>C09</td>
						<td>C10</td>
						<td>C11</td>
						<td>C12</td>
						<td>C13</td>
						<td>C14</td>
						<td>C15</td>
						<td>C16</td>
						<td>C17</td>
						<td>C18</td>
						<td>C19</td>
						<td>C20</td>
						<td>C21</td>
						<td>C22</td>
						<td>C23</td>
						<td>C24</td>
						<td>C25</td>
						<td>C26</td>
						<td>C27</td>
						<td>C28</td>
						<td>C29</td>
						<td>C30</td>
						<td>C31</td>
						<td>C32</td>
						<td>C33</td>
						<td width="4%">TOTAL</td>
					</tr>
			<%
				int counter = 0;
				String t01 = "", t02 = "", t03 = "", t04 = "", t05 = "", t06 = "", t07 = "", t08 = "", t09 = "", t10 = "";
				String t11 = "", t12 = "", t13 = "", t14 = "", t15 = "", t16 = "", t17 = "", t18 = "", t19 = "", t20 = "";
				String t21 = "", t22 = "", t23 = "", t24 = "", t25 = "", t26 = "", t27 = "", t28 = "", t29 = "", t30 = "";
				String t31 = "", t32 = "", t33 = "", total = "";
				boolean bFil = true;
				for (Enumeration e = objLst.getHshLista().keys(); e.hasMoreElements();) {
					e.nextElement();
					v = (Vector)objLst.getHshLista().get("" + counter);
					bFil = bFil?false:true; 
					counter++;
					total = (String)v.get(38)!=null?(String)v.get(38):"0";
					t01		= (String)v.get(39)!=null?(String)v.get(39):"0";
					t02		= (String)v.get(40)!=null?(String)v.get(40):"0";
					t03		= (String)v.get(41)!=null?(String)v.get(41):"0";
					t04		= (String)v.get(42)!=null?(String)v.get(42):"0";
					t05		= (String)v.get(43)!=null?(String)v.get(43):"0";
					t06		= (String)v.get(44)!=null?(String)v.get(44):"0";
					t07		= (String)v.get(45)!=null?(String)v.get(45):"0";
					t08		= (String)v.get(46)!=null?(String)v.get(46):"0";
					t09		= (String)v.get(47)!=null?(String)v.get(47):"0";
					t10		= (String)v.get(48)!=null?(String)v.get(48):"0";
					t11		= (String)v.get(49)!=null?(String)v.get(49):"0";
					t12		= (String)v.get(50)!=null?(String)v.get(50):"0";
					t13		= (String)v.get(51)!=null?(String)v.get(51):"0";
					t14		= (String)v.get(52)!=null?(String)v.get(52):"0";
					t15		= (String)v.get(53)!=null?(String)v.get(53):"0";
					t16		= (String)v.get(54)!=null?(String)v.get(54):"0";
					t17		= (String)v.get(55)!=null?(String)v.get(55):"0";
					t18		= (String)v.get(56)!=null?(String)v.get(56):"0";
					t19		= (String)v.get(57)!=null?(String)v.get(57):"0";
					t20		= (String)v.get(58)!=null?(String)v.get(58):"0";
					t21		= (String)v.get(59)!=null?(String)v.get(59):"0";
					t22		= (String)v.get(60)!=null?(String)v.get(60):"0";
					t23		= (String)v.get(61)!=null?(String)v.get(61):"0";
					t24		= (String)v.get(62)!=null?(String)v.get(62):"0";
					t25		= (String)v.get(63)!=null?(String)v.get(63):"0";
					t26		= (String)v.get(64)!=null?(String)v.get(64):"0";
					t27		= (String)v.get(65)!=null?(String)v.get(65):"0";
					t28		= (String)v.get(66)!=null?(String)v.get(66):"0";
					t29		= (String)v.get(67)!=null?(String)v.get(67):"0";
					t30		= (String)v.get(68)!=null?(String)v.get(68):"0";
					t31		= (String)v.get(69)!=null?(String)v.get(69):"0";
					t32		= (String)v.get(70)!=null?(String)v.get(70):"0";
					t33		= (String)v.get(71)!=null?(String)v.get(71):"0";
			%>
					<tr align="right" bgcolor="<%=bFil?"#EAEFF5":"#FFFFFF"%>" class="celdas01">
						<td align="center"><%= counter + ((Integer.parseInt(objLst.getPag()) - 1)  * 15) %></td>
					<% if(objLst.getccas().compareTo("")==0){ %>
						<td align="center"><%= v.get(1)!=null?v.get(1):"0" %></td>
					<% } %>
						<td align="center"><%= v.get(2)!=null?v.get(2):"0" %></td>
						<td><%= v.get(3)!=null?v.get(3):"0" %></td>
						<td><%= v.get(4)!=null?v.get(4):"0" %></td>
						<td><%= v.get(5)!=null?v.get(5):"0" %></td>
						<td><%= v.get(6)!=null?v.get(6):"0" %></td>
						<td><%= v.get(7)!=null?v.get(7):"0" %></td>
						<td><%= v.get(8)!=null?v.get(8):"0" %></td>
						<td><%= v.get(9)!=null?v.get(9):"0" %></td>
						<td><%= v.get(10)!=null?v.get(10):"0" %></td>
						<td><%= v.get(11)!=null?v.get(11):"0" %></td>
						<td><%= v.get(12)!=null?v.get(12):"0" %></td>
						<td><%= v.get(13)!=null?v.get(13):"0" %></td>
						<td><%= v.get(14)!=null?v.get(14):"0" %></td>
						<td><%= v.get(15)!=null?v.get(15):"0" %></td>
						<td><%= v.get(16)!=null?v.get(16):"0" %></td>
						<td><%= v.get(17)!=null?v.get(17):"0" %></td>
						<td><%= v.get(18)!=null?v.get(18):"0" %></td>
						<td><%= v.get(19)!=null?v.get(19):"0" %></td>
						<td><%= v.get(20)!=null?v.get(20):"0" %></td>
						<td><%= v.get(21)!=null?v.get(21):"0" %></td>
						<td><%= v.get(22)!=null?v.get(22):"0" %></td>
						<td><%= v.get(23)!=null?v.get(23):"0" %></td>
						<td><%= v.get(24)!=null?v.get(24):"0" %></td>
						<td><%= v.get(25)!=null?v.get(25):"0" %></td>
						<td><%= v.get(26)!=null?v.get(26):"0" %></td>
						<td><%= v.get(27)!=null?v.get(27):"0" %></td>
						<td><%= v.get(28)!=null?v.get(28):"0" %></td>
						<td><%= v.get(29)!=null?v.get(29):"0" %></td>
						<td><%= v.get(30)!=null?v.get(30):"0" %></td>
						<td><%= v.get(31)!=null?v.get(31):"0" %></td>
						<td><%= v.get(32)!=null?v.get(32):"0" %></td>
						<td><%= v.get(33)!=null?v.get(33):"0" %></td>
						<td><%= v.get(34)!=null?v.get(34):"0" %></td>
						<td><%= v.get(35)!=null?v.get(35):"0" %></td>
						<td><%= v.get(37)!=null?v.get(37):"0" %></td>
					</tr>
				<% } %>
				<% if (!(objLst.getPaginacion().getPaginaSgte())) { %>
					<tr align="right" bgcolor="#3DB7E4" class="celdas01">
					  <td bgcolor="#DFE6EE">&nbsp;</td>
					<% if(objLst.getccas().compareTo("")==0){ %>
					  <td bgcolor="#DFE6EE">&nbsp;</td>
					<% } %>
					  <td align="center">TOTAL</td>
					  <td><%= t01 %></td>
					  <td><%= t02 %></td>
					  <td><%= t03 %></td>
					  <td><%= t04 %></td>
					  <td><%= t05 %></td>
					  <td><%= t06 %></td>
					  <td><%= t07 %></td>
					  <td><%= t08 %></td>
					  <td><%= t09 %></td>
					  <td><%= t10 %></td>
					  <td><%= t11 %></td>
					  <td><%= t12 %></td>
					  <td><%= t13 %></td>
					  <td><%= t14 %></td>
					  <td><%= t15 %></td>
					  <td><%= t16 %></td>
					  <td><%= t17 %></td>
					  <td><%= t18 %></td>
					  <td><%= t19 %></td>
					  <td><%= t20 %></td>
					  <td><%= t21 %></td>
					  <td><%= t22 %></td>
					  <td><%= t23 %></td>
					  <td><%= t24 %></td>
					  <td><%= t25 %></td>
					  <td><%= t26 %></td>
					  <td><%= t27 %></td>
					  <td><%= t28 %></td>
					  <td><%= t29 %></td>
					  <td><%= t30 %></td>
					  <td><%= t31 %></td>
					  <td><%= t32 %></td>
					  <td><%= t33 %></td>
					  <td><%= total %></td>
				  </tr>
				<% } %>
				</table>
			</td>
    </tr>
  </table>
  <table width="95%" border="0" align="center">
    <tr>
      <td>&nbsp;
          <input type="hidden" name="opt" value="17">
          <input type="hidden" name="upd" value="PRDASUN">
					<input type="hidden" name="pg" value="<%= objLst.getPag()%>">
          <input type="hidden" name="CRED" value="<%= objLst.getcred()!=null?objLst.getcred():"" %>">
          <input type="hidden" name="CCAS" value="<%= objLst.getccas()!=null?objLst.getccas():"" %>">
          <input type="hidden" name="FECHA1" value="<%= objLst.getdda()!=null?objLst.getdda():"" %>">
          <input type="hidden" name="FECHA2" value="<%= objLst.getdda2()!=null?objLst.getdda2():"" %>">
      </td>
    </tr>
    <tr>
      <td class="titulo5">Fuente: Base de datos de la Oficina de Atenci&oacute;n al Asegurado</td>
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
		System.out.println("Error en rp_asunto.jsp");
	} 
	finally {
		request.removeAttribute("objLst");
	}
%>
</html>
