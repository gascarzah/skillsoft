<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="util.Constantes"%>
<%@ page session="true" %>
<%@ page import="java.util.*" %>
<%@ page import="beans.WebUtil" %>
<%@ page import="beans.Util" %>
<%@ page import="clases.Ficha_t" %>
<%@ page import="clases.UsuarioSistema" %>
<%@ page import="java.util.GregorianCalendar"%>
<%@ include file="../jsp/formato_fecha.jsp" %>
<%
  
  Ficha_t F1t = (Ficha_t)request.getAttribute("objFicha_t");
	String msg = (String)request.getAttribute("msg_error");
	Vector v;
	Vector vCols;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css"/>
<link href="../styles/botones.css" rel="stylesheet" media="print" type="text/css"/>
<script language="JavaScript" src="../js/ubigeo.js"></script>
<script language="JavaScript" src="../js/script2.js"></script>
<script language="javascript" src="../js/format_date.js"></script>
<script language="javascript" src="../js/stm31.js"></script>
<script language="javascript" src="../js/val_campos.js"></script>

<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language="javascript">

function locationServer1 (valor1, valor2) {
	
  parent.bot.location.href='../jsp/botREC.jsp?tipo=' + valor1 + '&num=' + valor2;
}

function getMigra1 (obj1, obj2) {

  document.f.DAPEPATREC.value = "";
  document.f.DAPEMATREC.value = "";
  document.f.DNOMREC.value = "";
  if (obj1.value == "" || obj2.value == "") {
	  alert ("Debe completar el tipo y número de documento");
    document.f.DFICDIDREC.focus();
  }
  else if (obj1.value != "" && obj2.value != "") {
	  locationServer1(obj1.value, obj2.value);
  } 
	else {
	  alert("No hay información");
  }
}
//separador
function locationServer (valor1, valor2) {
  parent.bot.location.href='../jsp/bot1.jsp?tipo=' + valor1 + '&num=' + valor2;
}

function getMigra (obj1, obj2) {

  document.f.DAPEPAT.value = "";
  document.f.DAPEMAT.value = "";
  document.f.DNOM.value = "";
  document.f.DDICSEX.value = "";
  document.f.NFICEDA.value = "";
//document.f.CCAS.value = "";

  if (obj1.value == "" || obj2.value == "") {

	  alert ("Debe completar el tipo y número de documento");
    document.f.DFICDID.focus();
  }
  else if (obj1.value != "" && obj2.value != "") {

	  locationServer(obj1.value, obj2.value);
  } 
	else {
	  alert("No hay información");
  }
}

function pasa(fun,cod){
	parent.bot.location.href='../jsp/botFicha.jsp?funcion='+fun+'&codigo='+cod;
}




function inicio(){
	if(document.f.DFICDIDREC.value==""){
	document.f.saberec[1].checked="checked";
	document.f.CTDIREC.disabled="multiple disabled";
	document.f.DFICDIDREC.disabled="disabled";
	document.f.DFICDIDREC.style.backgroundColor="#DFE6EE";
	document.f.brec.disabled="disabled";
	document.f.DAPEPATREC.disabled="disabled";
	document.f.DAPEPATREC.style.backgroundColor="#DFE6EE";
	document.f.DAPEMATREC.disabled="disabled";
	document.f.DAPEMATREC.style.backgroundColor="#DFE6EE";
	document.f.DNOMREC.disabled="disabled";
	document.f.DNOMREC.style.backgroundColor="#DFE6EE";
	
	
	document.f.TELRECLA.disabled="disabled";
	document.f.TELRECLA.style.backgroundColor="#DFE6EE";
	document.f.MAILRECLA.disabled="disabled";
	document.f.MAILRECLA.style.backgroundColor="#DFE6EE";
	document.f.CELRECLA.disabled="disabled";
	document.f.CELRECLA.style.backgroundColor="#DFE6EE";
	document.f.DIRRECLA.disabled="disabled";
	document.f.DIRRECLA.style.backgroundColor="#DFE6EE";
	}else{
		document.f.saberec[0].checked="true";
	}
	
}


</script>
</head>
<%
try {
%>
<body class="body" bgproperties="fixed" onload="inicio();" >
<form action="../servlet/CtrlFicha"  method="post" name="f" >
 <table class="banner01" >
    <tr>
      <td width="220" rowspan="5" align="center"><img src="../images/es03.jpg" width="120" height="100"/></td>
      <td align="center"></td>
      <td width="220" rowspan="8" align="right"><img src="../images/defensoria01.jpg" width="120" height="100"/></td>
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
  <table width="100%" border="0" align="center" >
     <tr>
          <td>
            <input type="hidden" name="CAS" value="<%=F1t.getcas()!=null?F1t.getcas():""%>" />
            <input type="hidden" name="AYEAR" value="<%=F1t.getayear()!=null?F1t.getayear():""%>" />
            <input type="hidden" name="CORREL" value="<%=F1t.getcorrel()!=null?F1t.getcorrel():""%>" />
          </td>
          <td align="left" class="titulo3" width="33%"></td>
          <td align="center" class="titulo3" width="34%"></td>
		      
          <td align="right" class="titulo3" width="33%"><%= Constantes.NRO_EXPE %> <%=F1t.getayear()!=null?F1t.getayear():""%>-<%=F1t.getcas()!=null?F1t.getcas():""%>-<%=F1t.getcorrel()!=null?F1t.getcorrel():""%>&nbsp;&nbsp;</td>
     </tr>
  </table>
  <table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
    
    
    <tr>
      <td>&nbsp;</td>
    </tr>
  </table>
  <table width="85%"  border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#007AC9" bgcolor="#ffffff" class="celdas">
    <tr>
      <td><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
      
      	<tr>
          <td>&nbsp;</td>
          <td colspan="3" height="50px"><label style="font-weight: bold;font-size: 15px;"><%= Constantes.TITULO_SOLICITANTE %> </label><br /> <%= Constantes.DESCRIPTI_SOLICITANTE %> 
		  Si<input type="radio" name="saberec" value="1" onclick="cambiodis();"/>&nbsp;&nbsp;&nbsp;No<input type="radio" name="saberec" value="0" onclick="cambiodis();" />            </td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td height="32">&nbsp;</td>
          <td colspan="3" valign="middle"><%= Constantes.DOCUMENTO_IDENTIDAD %>
            <select name="CTDIREC" class="celdas01" onfocus="nextfield ='DFICDID';" style="width: 186px; " >              
              <option value="<%=F1t.getctdirec()!=null?F1t.getctdirec():""%>"><%=F1t.getdtdirec()!=null?F1t.getdtdirec():""%></option>
              <option value="">SELECCIONA</option>
              
              <%
						v =  (Vector) application.getAttribute ("vDocumento");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
              
              <option value="<%=vCols.get(0)%>" <%= F1t.getdtdirec() != null && F1t.getdtdirec().equals(vCols.get(0))?"SELECTED":"" %>><%= vCols.get(1)%></option>
              <%
							}
						}
					%>
            </select>
            <%= Constantes.NUMERO %>
            &nbsp;&nbsp;<input name="DFICDIDREC" type="text" size="15" maxlength="15" class="celdas01" onfocus="nextfield ='CMSO';" onchange="veridigitos(this);" value="<%=F1t.getdficdidrec()!=null?F1t.getdficdidrec():""%>"/>
            &nbsp;&nbsp;&nbsp;<input name="brec" type="button" class="boton4" onclick="getMigra1(document.f.CTDIREC, document.f.DFICDIDREC);" value="Buscar"/></td>
          <td>&nbsp;</td>
        </tr>
        <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        <tr>
          <td>&nbsp;</td>
          <td><%= Constantes.APE_PATERNO%>&nbsp;&nbsp;
            <input name="DAPEPATREC" type="text" onfocus="nextfield ='DAPEMATREC';" onkeypress="return soloLetras(event)" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" value="<%=F1t.getdapepatrec()!=null?F1t.getdapepatrec():""%>" style="width: 182px; "/></td>
          <td><%= Constantes.APE_MATERNO%>
          <input name="DAPEMATREC" type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='DNOMREC';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" value="<%=F1t.getdapematrec()!=null?F1t.getdapematrec():""%>" />
            </td>
          <td><%= Constantes.NOM%>
            <input name="DNOMREC" type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='DFICDIDREC';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" value="<%=F1t.getdnomrec()!=null?F1t.getdnomrec():""%>"/></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>      
         	<td>&nbsp;</td> 		        	
          	<td><%= Constantes.TELEFONO1 %> <b>*</b>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
          		<input name="TELRECLA" type="text" onkeypress="return soloNumeros(event)" onchange="veridigitos(this);" onfocus="nextfield ='TELRECLA';" size="20" maxlength="9" value="<%=F1t.getReclaTel()!=null?F1t.getReclaTel():""%>" style="width: 182px; "></td>          	
          	<td><%= Constantes.CORREO_ELECT %>
          		<input name="MAILRECLA" type="text" onfocus="nextfield ='MAILRECLA';" size="20" maxlength="30" value="<%=F1t.getReclaMail()!=null?F1t.getReclaMail():""%>"  onchange="cambia(this);" style="width: 224px; "></td>
          	<td colspan="4"><%= Constantes.DIRECCION %>
          	<input  type="text" name="DIRRECLA" size="80" maxlength="500" onchange="cambia(this);" class="celdas01" value="<%=F1t.getReclaDir()!=null?F1t.getReclaDir():""%>"></td>
          	<td>&nbsp;</td>
         </tr>
         
        <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        <tr>      
         	<td>&nbsp;</td> 
         	<td><%= Constantes.TELEFONO2 %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          		<input name="CELRECLA" type="text" onfocus="nextfield ='CELRECLA';" size="30" onkeypress="return soloNumeros(event)" maxlength="9" value="<%=F1t.getReclaCel()!=null?F1t.getReclaCel():""%>"   onchange="veridigitos(this);" style="width: 182px; "></td>
          	<td>&nbsp;</td>		        	
         	
         </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><hr color="#BOC4DE" /></td>
          <td>&nbsp;</td>
        </tr>
        <!--  FIN -->
         <tr>
          <td>&nbsp;</td>
          <td colspan="3" height="30px"><label style="font-weight: bold;font-size: 15px;"><%= Constantes.TITULO_INVOLUCRADO %> </label>  </td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><%= Constantes.DOCUMENTO_IDENTIDAD %><strong>*</strong>&nbsp;&nbsp;
              <select name="CTDI" class="celdas01" onfocus="nextfield ='DFICDID';">
                <option value="<%=F1t.getctdi()!=null?F1t.getctdi():""%>"><%=F1t.getdtdi()!=null?F1t.getdtdi():""%></option>
                <option value="1" selected="selected">D.N.I.</option>
					<%
						v =  (Vector) application.getAttribute ("vDocumento");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
								<option value="<%=vCols.get(0)%>" <%= F1t.getctdi()!=null && F1t.getctdi().equals(vCols.get(0))?"SELECTED":""%>><%= vCols.get(1)%> </option>

          <%
							}
						}
					%>
              </select>&nbsp;&nbsp;&nbsp;
            <%= Constantes.NUMERO %> 
            <input name="DFICDID" type="text" onfocus="nextfield ='DAPEPAT';" onchange="veridigitos(this);" size="15" maxlength="15" value="<%=F1t.getdficdid()!=null?F1t.getdficdid():""%>">
						<input name="Button" type="button" class="boton4" onclick="getMigra(document.f.CTDI, document.f.DFICDID);" value="Buscar"></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><%= Constantes.APE_PATERNO %> <b>*
              <input name="DAPEPAT" type="text" onfocus="nextfield ='DAPEMAT';" onchange="cambia(this);" size="30" maxlength="30" onkeypress="return soloLetras(event)" class="celdas01" value="<%=F1t.getdapepat()!=null?F1t.getdapepat():""%>" style="width: 171px; "/>
          </b></td>
          <td><%= Constantes.APE_MATERNO %> <b>*
            <input name="DAPEMAT" type="text" onfocus="nextfield ='DNOM';" onkeypress="return soloLetras(event)" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" value="<%=F1t.getdapemat()!=null?F1t.getdapemat():""%>"/>
          </b></td>
          <td><%= Constantes.NOM %><b>*
            <input name="DNOM" type="text" onfocus="nextfield ='NFICEDA';" onkeypress="return soloLetras(event)" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" value="<%=F1t.getdnom()!=null?F1t.getdnom():""%>" style="width: 191px; "/>
          </b></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
          <td><%= Constantes.SEXO %>*</b>
            <select name="DDICSEX" class="celdas01" onfocus="nextfield='DEPARTAMENTO';" >
              <option value="" selected="selected">SELECCIONA</option>
              <option value="M" <%=(("M".toString().equals(F1t.getddicsex()==null?"":F1t.getddicsex()))?"selected":"")%>>MASCULINO</option>
              <option value="F" <%=(("F".toString().equals(F1t.getddicsex()==null?"":F1t.getddicsex()))?"selected":"")%>>FEMENINO</option>
            </select></td>
          <td><%= Constantes.EDAD %><b>* </b>
            <input name="NFICEDA" type="text" size="3" maxlength="3" class="celdas01" onfocus="nextfield ='DFICTEL';"  value="<%=F1t.getnficeda()!=null?F1t.getnficeda():""%>"/></td>
          <td><%= Constantes.TELEFONO1 %> <b>*</b>
            <input name="DFICTEL" type="text" class="celdas01" onfocus="nextfield ='DFICMAIL';" onchange="veridigitos(this);" size="15" onkeypress="return soloNumeros(event)" maxlength="9" style="width: 171px; "  value="<%=F1t.getdfictel()!=null?F1t.getdfictel():""%>"/></td>
        	<td>&nbsp;</td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        
        <input type="hidden" name="DEPARTAMENTO" value="<%= F1t.getubitdpt() != null ? F1t.getubitdpt() : ""%>"/>
        <input type="hidden" name="PROVINCIA" value="<%= F1t.get4ubigeo() != null ? F1t.get4ubigeo() : ""%>"/>
        <input type="hidden" name="CFICUBIGEO" value="<%= F1t.getcficubigeo() != null ? F1t.getcficubigeo() : ""%>"/>
        
        
        <input type="hidden" name="CFICTIPSEG" value="<%= F1t.getcfictipseg() != null ? F1t.getcfictipseg() : ""%>"/>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><%=Constantes.DIRECCION %><b>*</b>
            <input name="DFICDIR" type="text" class="celdas01" onfocus="nextfield ='DFICTEL';" onchange="cambia(this);" size="45" maxlength="120"  value="<%=F1t.getdficdir()!=null?F1t.getdficdir():""%>"/></td>
          <td colspan="2"><%=Constantes.CORREO_ELECT %>
            <input name="DFICMAIL" type="text" class="celdas01" onfocus="nextfield ='CTPR';" onchange="cambia(this);" size="23" maxlength="30" style="width: 399px; " value="<%=F1t.getdficmail()!=null?F1t.getdficmail():""%>"/></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          
          <td>
            </td>

        
          
          
          <td>&nbsp;</td>
        </tr>
        

        
        
        
                
        <tr>
          <td>&nbsp;</td>
          <td>
            <select name="CRED" class="celdas01"  style="display: none;" onfocus="nextfield ='CCAS';" onchange="pasa('CASC',this.form.CRED.options[this.form.CRED.selectedIndex].value);">
              <option value="" selected="selected">SELECCIONA</option>
              <%
								v =  (Vector) application.getAttribute ("vRed");
								if (v != null) {  
									for (int i = 0; i < v.size(); i++) {  
										vCols = (Vector) v.get(i);  
							%>
									<option value="<%=vCols.get(0)%>" <%= F1t.getcred()!= null && F1t.getcred().equals(vCols.get(0).toString()) ? "SELECTED":""%> ><%= vCols.get(1)%> </option>
									<%
									}
								}
							%>
            </select></td>

          <td>
            <select name="CCAS" class="celdas01" style="display: none;"/>
              <option value="">SELECCIONA</option>
					<%
						v =  (Vector) application.getAttribute ("vCas");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
         
              <option value="<%=vCols.get(0)%>" <%= F1t.getccas()!= null && F1t.getccas().equals(vCols.get(0).toString()) ?"SELECTED":"" %>><%= vCols.get(1)%></option>
              <%
							}
						}
					%>
            </select></td>
         </tr>

        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><hr color="#BOC4DE" /></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3"><%= Constantes.DESCRIPCION %></td>
          <td>&nbsp;</td>
        </tr>
         <tr>
          <td>&nbsp;</td>
        <td colspan="3">&nbsp;</td> 
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="3" align="center"><textarea name="DFICHEC" class="celdas01" onchange="cambia(this);" onfocus="nextfield ='CODIGO';" onkeypress="corta1(this);" onkeyup="corta1(this);" cols="160" rows="10"><%=F1t.getdfichec()!=null?F1t.getdfichec():""%></textarea>
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
				<input type="hidden" name="opt" value="35">
        <input type="hidden" name="caso" value="M">
        <input name="portal" type="hidden"   value="1"/>
        
			</td>
      <td width="20%">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3" align="center">
			<input name="Button3" class="boton4" value=" Imprimir " style="cursor: pointer;text-align: center;" onclick="window.print();">
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
		System.out.println("NullPointerException[alta_i.jsp]: " + e);
    request.setAttribute("msg_error", "Error al mostrase la pagina: " + e); 
    WebUtil.goForward(getServletContext(), request, response, "/error/error.jsp"); 
  }
	finally{
	  F1t = null;
	}
%>
</html>
