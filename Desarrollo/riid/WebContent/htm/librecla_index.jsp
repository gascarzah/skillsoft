
<%@page import="util.Constantes"%>
<%@ page session="true" %>
<%@ page import="java.util.*" %>
<%@ page import="beans.WebUtil" %>
<%@ page import="beans.Util" %>
<%@ page import="clases.Ficha_t" %>
<%@ page import="clases.UsuarioSistema" %>
<%@ page import="java.util.GregorianCalendar"%>

<%
  
  Ficha_t F1t = (Ficha_t)request.getAttribute("obj");
	String msg = (String)request.getAttribute("msg_error");
	Vector v;
	Vector vCols;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>EsSalud - Libro de Reclamaciones</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css"/>
<link href="../styles/botones.css" rel="stylesheet" media="print" type="text/css"/>
<script language="JavaScript" src="../js/ubigeo.js"></script>
<script language="JavaScript" src="../js/script2.js"></script>
<script language="javascript" src="../js/stm31.js"></script>
<script language="javascript" src="../js/val_campos.js"></script>

<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language="javascript">



</script>
</head>
<%
try {
%>
<body class="body"  bgproperties="fixed" >
<form action="../servlet/CtrlFicha" method="post" name="f" onsubmit="return validacionLibroRecla(this)">
 <table class="banner01" >
    <tr>
      <td width="220" rowspan="5" align="center"><img src="../images/es04.jpg" width="120" height="100"/></td>
      <td align="center"></td>
      <td width="220" rowspan="8" align="right"><img src="../images/libroRecla.jpg" width="120" height="100"/></td>
    </tr>
    <tr>
      <td align="center" class="titulob1">&nbsp;</td>
    </tr>
    <tr align="center">
      <td>&nbsp;</td>
    </tr>
    <tr align="center">
      <td align="center" class="titulob2"><%= Constantes.LIBRO_RECLAMACIONES_VIRTUAL %></td>
    </tr>
    <tr align="center">
      <td align="center" class="titulob1">&nbsp;</td>
    </tr>
    <tr align="center">
      <td align="center" class="celdasb"></td>
      <td rowspan="3" class="titulo4">
      	<table width="100">
        	
        </table>
      </td>
    </tr>
    <tr align="center">
      <td align="center" class="celdasb"></td>
    </tr>
    <tr align="center">
      <td align="center" class="celdasb"></td>
    </tr>
  </table>
  
  <table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td></td>
    </tr>
    
   
  </table>
  <table width="85%"  border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#007AC9" bgcolor="#ffffff" class="celdas">
    <tr>
      <td><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
      
      	<tr>
          <td>&nbsp;</td>
          <td colspan="3"></td> 
          <td>&nbsp;</td>
        </tr>
      
        <tr>
          <td>&nbsp;</td>
          <td colspan="3" height="50px"><label style="font-weight: bold;font-size: 15px;"><%= Constantes.TITULO_LIBRECLA_USUARIO %> </label><br /> 
		  </td>
          <td>&nbsp;</td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td><%= Constantes.NOM%><b>*</b>
            <input name="DNOM" type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='DAPEPAT';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" />
            </td>
          <td><%= Constantes.APE_PATERNO%><b>*</b>&nbsp;&nbsp;
            <input name="DAPEPAT" type="text" onfocus="nextfield ='DAPEMAT';" onkeypress="return soloLetras(event)" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" style="width: 182px; "/>
            </td>
          <td><%= Constantes.APE_MATERNO%><b>*</b>
          <input name="DAPEMAT" type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='DFICDIR';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" />
            </td>
          
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>      
         	<td>&nbsp;</td> 		        	
          	<td><%= Constantes.DOMICILIO %><b>*</b>
          	<input  type="text" name="DFICDIR" size="80" maxlength="500" onchange="cambia(this);" class="celdas01" value="" onfocus="nextfield ='CTDI';"/>
          	</td>
          	<td>&nbsp;</td>
         </tr>
        <!--  FIN -->
        <tr>
          <td>&nbsp;</td>
          <td colspan="3"></td>
          <td>&nbsp;</td>
        </tr>
        
        <tr>
          <td height="32">&nbsp;</td>
          <td valign="middle"><%= Constantes.DOCUMENTO_IDENTIDAD %>
            <select name="CTDI" class="celdas01" onfocus="nextfield ='DFICDID';" style="width: 186px; ">
              <option value="">SELECCIONA</option>
              
              <%
						v =  (Vector) application.getAttribute ("vDocumento");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
              <option value="<%=vCols.get(0)%>" <%= ((String)vCols.get(0)).equals("1")?"SELECTED":"" %>><%= vCols.get(1)%> </option>
              <%
							}
						}
					%>
            </select>
            <%= Constantes.NUMERO %><b>*</b>
            &nbsp;&nbsp;<input name="DFICDID" type="text" size="15" maxlength="15" class="celdas01" onfocus="nextfield ='DFICTEL';" onchange="veridigitos(this);" /> </td>
          <td><%= Constantes.TELEFONO %> <b>*</b>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
          		<input name="DFICTEL" type="text" onkeypress="return soloNumeros(event)" onchange="veridigitos(this);" onfocus="nextfield ='DFICMAIL';" size="20" maxlength="9" value="" style="width: 182px; "/>
          		</td>          	
          	<td><%= Constantes.CORREO_ELECT %><b>*</b>
          		<input name="DFICMAIL" type="text" onfocus="nextfield ='CCAS';" size="20" maxlength="30" value=""  onchange="cambia(this);" style="width: 224px; "/>
          		</td>
        </tr>
        <tr>
        <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
          </tr>
        
        
        
        <tr>
          <td height="32">&nbsp;</td>
          <td><%= Constantes.CENTRO_ASISTENCIAL%><b>*</b>
            <select name="CCAS" class="celdas01" style="width: 206px; " >
              <option value="">SELECCIONA</option>
					<%
						v =  (Vector)  application.getAttribute ("vCas");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
              <option value="<%=vCols.get(1)%>"><%= vCols.get(2)%></option>
              <%
							}
						}
					%>
            </select></td>
          <td></td>          	
          	<td></td>
        </tr>
         <tr>
          <td>&nbsp;</td>
          <td colspan="3" height="50px"><label style="font-weight: bold;font-size: 15px;"><%= Constantes.TITULO_LIBRECLA_ATENCION %> </label><br /> 
		  </td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><%= Constantes.DESCRIPCION_RECLAMO %></td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
        <td colspan="3">&nbsp;</td> 
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3" align="center"><textarea name="DFICHEC" class="celdas01" onchange="cambia(this);" onkeypress="corta1(this);" onkeyup="corta1(this);" cols="160" rows="10"></textarea>
            <input name="Cars1" type="text" class="celdas01" onfocus="blur();" value="900" size="3" maxlength="3" /></td>
          <td>&nbsp;</td>
        </tr>
      </table>
	  </td>
	</tr>
	
	
	 
	</table>
	
	
  <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="20%"><%= Constantes.INFO_OBLI %></td>
      <td align="center">
				<input type="hidden" name="opt" value="17"/>
        <input type="hidden" name="caso" value="I"/>
        <input name="libRecla" type="hidden"   value="1"/>
        
			</td>
      <td width="20%">&nbsp;</td>
    </tr>
    
    <tr>
      <td colspan="3" align="center">
				<input name="Button2" type="submit" class="boton4" value=" Grabar " />&nbsp; <!-- onclick="grabar();" -->
      <input name="Reset" type="reset" class="boton4" value=" Borrar "/>
      </td>
    </tr>
    
    <tr>
      <td colspan="3" align="center"><span class="titulo4">
        <% if(msg != null){ %>
        <%= msg %>
        <% } %>
      </span></td>
    </tr>
  </table>
</form>
</body>
<%
  
  } 
  catch (NullPointerException e) {
	  
		System.out.println("NullPointerException[librecla_index.jsp]: " + e);
    request.setAttribute("msg_error", "Error al mostrase la pagina: " + e); 
    WebUtil.goForward(getServletContext(), request, response, "/error/error.jsp"); 
  }
	finally{
	  F1t = null;
	}
%>
</html>
