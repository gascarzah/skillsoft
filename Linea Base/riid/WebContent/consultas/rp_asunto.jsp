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
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<%
  try {
		UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario");
		ListFicha_t objLst = (ListFicha_t)request.getAttribute("objLst");
		Vector v;
		Vector vCols;
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
    <td colspan="3" align="center" class="titulo1">CONSOLIDADO POR MOTIVO DE LOS RECLAMOS</td>
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
  <table width="85%" border="0" align="center" cellspacing="0">
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
  <table width="85%"  border="1" align="center" cellspacing="0" bordercolor="#6699CC">
    <tr>
      <td><table width="100%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
        <tr bgcolor="#3DB7E4" class="celdas01" align="center">
          <td width="5%">ITEM</td>
          <td width="10%">MATERIA</td>
          <td width="10%">CODIGO</td>
          <td>DESCRIPCION</td>
          <td width="10%">TOTAL</td>
          <td width="10%">%</td>
          </tr>
			<%
				int counter = 0;
				boolean bFil = true;
				String strTotal = "";
				for (Enumeration e = objLst.getHshLista().keys(); e.hasMoreElements();) {
					e.nextElement();
					v = (Vector)objLst.getHshLista().get("" + counter);
					bFil = bFil?false:true; 
					strTotal = (String)v.get(6)!=null?(String)v.get(6):"";
					counter++;
			%>
        <tr bgcolor="<%=bFil?"#EAEFF5":"#FFFFFF"%>" class="celdas01">
          <td align="center"><%= counter + ((Integer.parseInt(objLst.getPag()) - 1)  * 15) %></td>
          <td align="left"><%= v.get(7)!=null?v.get(7):"0" %></td>
          <td align="center"><%= v.get(0)!=null?v.get(0):"" %></td>
          <td><%= v.get(1)!=null?v.get(1):"0" %></td>
          <td align="center"><%= v.get(4)!=null?v.get(4):"0" %></td>
          <td align="center"><%= v.get(5)!=null?v.get(5):"0" %></td>
          </tr>
      <%
				}  
			%>
      </table></td>
    </tr>
		<tr>
			<td colspan="5"><table width="100%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE">
        <tr bgcolor="#3DB7E4">
          <td align="center">TOTAL</td>
          <td width="10%" align="center"><%= strTotal %></td>
          <td width="10%" align="center">100.00</td>
        </tr>
      </table></td>
		</tr>
  </table>
  <table width="85%" border="0" align="center">
    <tr>
      <td>&nbsp;
          <input type="hidden" name="opt" value="1">
          <input type="hidden" name="upd" value="ASUNTO">
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
