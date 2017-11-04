<%@page import="util.Constantes"%>
<%@ page session="true" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css">
<link href="../styles/botones.css" rel="stylesheet" media="print" type="text/css">
<script language="javascript" src="../js/format_date.js"></script>
<script language="javascript" src="../js/stm31.js"></script>
<script language="javascript1.2">
function goPg(np) {
  document.f.pg.value = np;
  document.f.submit();
}  
/*function getReg(cda,pfil) { 
	location.href='../servlet/CtrlFicha?opt=7&CODIGODA='+cda;
}*/

function getReg(cas,ayear,correl) { 
	//location.href='../servlet/CtrlFicha?opt=5&CODIGODA='+cda+'&perfil='+pfil;
	location.href='../servlet/CtrlFicha?opt=27&CAS='+cas+'&AYEAR='+ayear+'&CORREL='+correl;
}

</script>
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
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



<%
  ListFicha_t objLst = (ListFicha_t)request.getAttribute("objLst");
	if (objLst!=null) {
%>
<form name="f" method="post" action="../servlet/CtrlFicha?opt=20">


  <table width="70%" border="0" align="center" cellspacing="0">
  <tr align="center">
    <td>&nbsp;</td>
  </tr>
  
    <tr><td>&nbsp;</td>
      <td align="center" class="titulo1"><%=Constantes.TITULO_EXPEDIENTE_LIBRO_RECLAMACIONES %></td>
      <td>&nbsp;</td>
    </tr>
  
    <tr class="celdas01" >
      <td width="25%"><input type="hidden" name="pg" value="<%= objLst.getPag()%>" /></td>
      <td>&nbsp;</td>
      <td align="right" width="25%"><%  if ( objLst.getPaginacion().getPaginaAnt()) { %>
          <input name="button" value="  <<  " type="button" class="boton4" onClick="goPg(<%= Integer.parseInt(objLst.getPag()) - 1%>);" title="Retroceder a pagina anterior">
          <%  } 
		    if ( objLst.getPaginacion().getPaginaSgte()) { %>
          <input name="button" value="  >>  " type="button" class="boton4" onClick="goPg(<%= Integer.parseInt(objLst.getPag()) + 1%>);" title="Avanzar a pagina siguiente">
      <%  } %>      </td>
    </tr>
  </table>
  <table width="70%"  border="1" align="center" cellspacing="0" bordercolor="#6699CC">
   <tr>
     <td>
		   <table width="100%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
         <tr bgcolor="#3DB7E4" class="celdas01" align="center"> 
           <td colspan="2" bgcolor="#3DB7E4" class="Estilo3">ITEM</td>
           <td bgcolor="#3DB7E4" class="Estilo3">CODIGO</td>
           <td bgcolor="#3DB7E4" class="Estilo3">FECHA DE REGISTRO</td>
           <td bgcolor="#3DB7E4" class="Estilo3">DATOS DEL SOLICITANTE</td>
           <td bgcolor="#3DB7E4" class="Estilo3" style="text-transform: uppercase;"><%= Constantes.TITULO_INVOLUCRADO %></td>
           <td bgcolor="#3DB7E4" class="Estilo3" ><%= Constantes.TIPO_ENVIO %></td>
           <td bgcolor="#3DB7E4" class="Estilo3">ESTADO</td>
          </tr>
<%
			int counter = 0;
			boolean bFil = true;
			String strCtin = "";
			String strDtin = "";
			for (Enumeration e = objLst.getHshLista().keys(); e.hasMoreElements();) {
				strCtin = "";
	    	e.nextElement();
	      Vector v = (Vector)objLst.getHshLista().get("" + counter);
			  bFil = bFil?false:true; 
				
	      counter++;
%>
			<tr bgcolor="<%=bFil?"#EAEFF5":"#FFFFFF"%>" class="celdas"> 
            <td align="center"><a href="javascript:getReg('<%=v.get(03)%>','<%=v.get(04)%>','<%=v.get(05)%>');">
            <img src="../images/fleder.jpg" alt="Seleccione para actualizar datos del registro" width="12" height="12" border="0">
            </a></td>
            <td align="center"><%= counter + ((Integer.parseInt(objLst.getPag()) - 1)  * 15) %></td>
            <td align="center"><%=v.get(04)%> - <%=v.get(03)%> - <%=v.get(05)%></td>
           <td align="center"><%= v.get(0)!=null?v.get(0):"" %></td>
            <td><%= v.get(1)!=null?v.get(1):"" %></td>
            <td align="center"><%= v.get(6)!=null?v.get(6):"" %></td>
            <td align="center"><%= v.get(7)!=null?v.get(7):"" %></td>
            <td align="center"><%= v.get(2)!=null?v.get(2):"" %></td>
          </tr>
<%
			}  
%>
				</table>
			</td>
		</tr>
  </table>
</form>
<% } %>
<table width="70%" border="0" align="center" class="celdas01">
  <tr>
    <td align="center" class="titulo4">&nbsp;
        <% if(msg != null){ %>
        <%= msg %>
        <% } %>
		</td>
  </tr>
</table>
</body>
<% 
	request.removeAttribute("objLst");
%>
</html>
