<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="util.Constantes"%>
<%@ page session="true" %>
<%@ page import="java.util.*" %>
<%@ page import="beans.WebUtil" %>
<%@ page import="beans.Util" %>
<%@ page import="clases.Ficha_t" %>
<%@ page import="clases.ListFicha_t" %>
<%@ page import="clases.UsuarioSistema" %>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.util.Calendar"%>
<%@ include file="../jsp/formato_fecha.jsp" %>
<%
  UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario");
  Ficha_t  objLst = (Ficha_t )request.getAttribute("objLst");
	String msg = (String)request.getAttribute("msg_error");
	Vector v;
	Vector vCols;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Upload - Lista Ficheros</title>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css">
<link href="../styles/botones.css" rel="stylesheet" media="print" type="text/css">
<script language="JavaScript" src="../js/ubigeo.js"></script>
<script language="JavaScript" src="../js/script2.js"></script>
<script language="javascript" src="../js/format_date.js"></script>
<script language="javascript" src="../js/stm31.js"></script>

</head>


<body>

<%
	if (objLst!=null) {
%>
<form name="f1" method="post">
  <input type="hidden" name="pg" value="<%= objLst.getPag()%>">
  <table width="85%" border="0" align="center" cellspacing="0">
    <tr class="celdas01" >
      <td width="25%">&nbsp;</td>
      <td>&nbsp;</td>
      <td align="right" width="25%"><%  if ( objLst.getPaginacion().getPaginaAnt()) { %>
          <input name="button" value="  <<  " type="button" class="boton4" onClick="goPg(<%= Integer.parseInt(objLst.getPag()) - 1%>);" title="Retroceder a pagina anterior">
          <%  } 
		    if ( objLst.getPaginacion().getPaginaSgte()) { %>
          <input name="button" value="  >>  " type="button" class="boton4" onClick="goPg(<%= Integer.parseInt(objLst.getPag()) + 1%>);" title="Avanzar a pagina siguiente">
          <%  } %>
      </td>
    </tr>
  </table>
  <table width="85%"  border="1" align="center" cellspacing="0" bordercolor="#6699CC">
   <tr>
     <td>
		   <table width="100%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
         <tr bgcolor="#B0C4DE" class="celdas01" align="center"> 
           <td colspan="2" bgcolor="#3DB7E4" class="Estilo3"><%= Constantes.TAB_ITEM %></td>
           <td bgcolor="#3DB7E4" class="Estilo3">CODIGO</td>
           <td bgcolor="#3DB7E4" class="Estilo3">FECHA DE REGISTRO</td>
           <td bgcolor="#3DB7E4" class="Estilo3">DATOS DEL SOLICITANTE</td>
           <td bgcolor="#3DB7E4" class="Estilo3" style="text-transform: uppercase;"><%= Constantes.TITULO_INVOLUCRADO %></td>
           
           <!-- <td bgcolor="#3DB7E4" class="Estilo3" style="text-transform: uppercase;"><%= Constantes.TITULO_SOLICITANTE %></td> -->
           <td bgcolor="#3DB7E4" class="Estilo3">ESTADO DEL REGISTRO </td>
          </tr>
<%
			int counter = 0;
			int counter2 = Integer.parseInt(objLst.getdtotal_reg()) +1;
			boolean bFil = true;
			for (Enumeration e = objLst.getHshLista().keys(); e.hasMoreElements();) {
	    	e.nextElement();
	      	v = (Vector)objLst.getHshLista().get("" + counter);
			  bFil = bFil?false:true; 
	      	counter++;
	      	counter2--;
%>
					<tr bgcolor="<%=bFil?"#EAEFF5":"#FFFFFF"%>" class="celdas"> 
            <td align="center"><a href="javascript:getReg('<%=v.get(1)%>','<%=v.get(0)%>','<%=v.get(2)%>');"><img src="../images/fleder.jpg" alt="Seleccione para actualizar datos del registro" width="12" height="12" border="0"></a></td>
            <td align="center"><%= counter + ((Integer.parseInt(objLst.getPag()) - 1)  * 15) %></td>
            <td align="center"><%= v.get(0)!=null?v.get(0):"" %>-<%= v.get(1)!=null?v.get(1):"" %>-<%= v.get(2)!=null?v.get(2):"" %></td>
            <td align="center"><%= v.get(3)!=null?v.get(3):"" %></td>
            <td align="left"><%= v.get(7)!=null?v.get(7):"&nbsp;" %>&nbsp;<%= v.get(8)!=null?v.get(8):"&nbsp;" %>,<%= v.get(9)!=null?v.get(9):"&nbsp;" %></td>
            <td><%= v.get(4)!=null?v.get(4):"" %>&nbsp;<%= v.get(5)!=null?v.get(5):"" %>,<%= v.get(6)!=null?v.get(6):"" %></td>
            
           <!--  <td align="center"><%= v.get(10)!=null?v.get(10):"&nbsp;" %></td> -->
					  <td align="center"><%= v.get(11)!=null?v.get(11):"&nbsp;" %></td>
				  </tr>
<%
			}  
%>
				</table>
			</td>
		</tr>
  </table>
</form>
<% }else{
	if(msg!=null)out.print("<center><span class=\"titulo4\" align=\"center\">"+msg+"</span></center>");
	}
%>

</body>
<% 
	request.removeAttribute("objLst");
%>



</html>