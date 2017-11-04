<%@page import="util.Comun"%>
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
	String fechaHoy = (String)request.getAttribute("fechaHoy");
	Vector v;
	Vector vCols;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css">
<link href="../styles/botones.css" rel="stylesheet" media="print" type="text/css">
<script language="JavaScript" src="../js/ubigeo.js"></script>
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


function goPg(np) {
  document.f.pg.value = np;
  document.f.submit();
}  

function getReg(cas,ayear,correl) { 
	//location.href='../servlet/CtrlFicha?opt=5&CODIGODA='+cda+'&perfil='+pfil;
	location.href='../servlet/CtrlFicha?opt=27&CAS='+cas+'&AYEAR='+ayear+'&CORREL='+correl;
}

function getComprobante(cda) {  
	location.href='../servlet/CtrlFicha?opt=20&CODIGODA='+cda;
}

function exportar(){
	if(document.f.FECHA1.value == "" ){
		alert("Por favor ingrese una fecha");
		event.returnValue=false;
		document.f.FECHA1.focus();
	}
	
	location.href='../servlet/CtrlFicha?opt=9&FECHA1='+document.f.FECHA1.value ;
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
    <td align="center" class="celdasb"><%= clusuario.getDescripcion() %></td>
    <td rowspan="3" class="titulo4">&nbsp;</td>
  </tr>
  <tr align="center">
    <td align="center" class="celdasb"><%= clusuario.getdPerfil() %></td>
  </tr>
  <tr align="center">
    <td align="center" class="celdasb"><%= clusuario.getdCas() %></td>
  </tr>
</table>
<form method="post" name="f" action="../servlet/CtrlFicha" >
<table width="70%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td align="center" class="titulo1">BANDEJA DE FICHAS DIARIAS </td>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><table width="30%" border="0" cellpadding="0" cellspacing="0"  bgcolor="#FFFFFF" align="center">
        
            <tr>
              <td colspan="3">&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>FECHA</td>
              <td><input name="FECHA1" id="sel1" type="text" class="celdas01" size="10"  maxlength="10" value="<%=fechaHoy%>"><input type="reset" value=" ... " onclick="return showCalendar('sel1', '%d/%m/%Y');" ></td>
              
            </tr>
            <tr>
              <td colspan="3">&nbsp;</td>
            </tr>
          </table></td>
        </tr>
      </table>
      

  <table  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td align="center">&nbsp;
		<input type="hidden" name="opt" value="5">
      	<input type="hidden" name="pg" value="1">
	  </td>
    </tr>
    <tr>
      <td align="center"><input name="Submit" type="submit" class="boton4" value="Buscar"/>      </td>
      <td align="center"><input type="button" name="Submit2" class="boton001" value="Exportar" onclick="exportar();"></td>
    </tr>
  </table>
</form>
<%
	if (objLst!=null) {
%>
<form name="f1" method="post">
  <input type="hidden" name="pg" value="<%= objLst.getPag()%>"/>
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
