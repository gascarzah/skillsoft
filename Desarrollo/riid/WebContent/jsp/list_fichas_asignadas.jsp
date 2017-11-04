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
<script language="javascript">

function valida_rq(){
  if(document.f.CRED.value == "" && document.f.CCAS.value == "" && document.f.AYEAR.value == "" && document.f.CAS.value == "" && document.f.CORREL.value == "" && document.f.CRED.value == "" && document.f.DFICDID.value == "" && document.f.DAPEPAT.value == "" && document.f.DAPEMAT.value == "" && document.f.DNOM.value == "" && document.f.DAPEPATREC.value == "" && document.f.DAPEMATREC.value == "" && document.f.DNOMREC.value == "" && document.f.FECHA1.value == "" && document.f.FECHA2.value == ""){
		alert("Por favor ingrese algún criterio de búsqueda");
		event.returnValue=false;
		document.f.DFICDID.focus();
  }
}

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

function pasa(fun,cod){
  parent.bot.location.href='../jsp/botFicha.jsp?funcion='+fun+'&codigo='+cod;
}

</script>
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body class="body" bgproperties="fixed">
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
<form method="post" name="f" action="../servlet/CtrlFicha" onSubmit="valida_rq();">
<table width="70%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td align="center" class="titulo1"><%=Constantes.TITULO_BANDEJA_EXPEDIENTES_ASIGNADAS %> </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><table style="display: none; visibility: hidden;" width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#6699CC" bgcolor="#FFFFFF">
        <tr>
          <td scope="col"><table width="100%" class="celdas01">
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td colspan="2">&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td style="text-transform: uppercase;"><%= Constantes.UNIDADORG %></td>
              <td colspan="2"><select name="CRED" class="celdas01" onfocus="nextfield ='CCAS';" onchange="pasa('CASC',this.form.CRED.options[this.form.CRED.selectedIndex].value);">
                <option value="" selected="selected">SELECCIONA</option>
                <%
						v =  (Vector) request.getAttribute ("vRed");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
				%>
                <option value="<%=vCols.get(0)%>" <%= Util.jnvl((String)request.getParameter("CRED"),"").compareTo(vCols.get(0))==0?"SELECTED":"" %>><%= vCols.get(1)%> </option>
                <%
							}
						}
					%>
              </select></td>
              
            </tr>
            <tr>
              <td width="6%" height="14">&nbsp;</td>
              <td width="27%">&nbsp;</td>
              <td width="37%">&nbsp;</td>
              <td width="30%">&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td style="text-transform: uppercase;"><%= Constantes.ESTABLE_SALUD %> </td>
              <td colspan="2"><select name="CCAS" class="celdas01" onfocus="nextfield ='ACCIONES';">
                <option value="" selected="selected">SELECCIONA</option>
                <%
						v =  (Vector) request.getAttribute ("vCas");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
                <option value="<%=vCols.get(1)%>" <%= Util.jnvl((String)request.getParameter("CCAS"),"").compareTo(vCols.get(1))==0?"SELECTED":"" %>><%= vCols.get(2)%> </option>
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
              <td><%= Constantes.NRO_EXPE %></td>
              <td colspan="2">A&ntilde;o:
                <select name="AYEAR" class="celdas01" >
                  <option value="" selected="selected">SELECCIONA</option>
                  <%
						v =  (Vector) request.getAttribute ("vAyear");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								
					%>
                  <option value="<%=v.get(i)%>" <%= Util.jnvl((String)request.getParameter("AYEAR"),"").compareTo(v.get(i))==0?"SELECTED":"" %>><%= v.get(i)%> </option>
                  <%
							}
						}
					%>
                </select>
                &nbsp;&nbsp;Cas:
<input name="CAS" type="text" class="celdas01" value="<%=(String)request.getParameter("CAS")==null?"":request.getParameter("CAS")%>" onfocus="nextfield ='CORREL';" size="10" maxlength="10" />
&nbsp;&nbsp;Correlativo:
<input name="CORREL" type="text" class="celdas01" value="<%=(String)request.getParameter("CORREL")==null?"":request.getParameter("CORREL")%>" onfocus="nextfield ='DFICDID';" size="10" maxlength="10" onchange="veridigitos(this);" /></td>
            </tr>
            <tr>
              <td colspan="4">&nbsp;</td>
              </tr>
            <tr>
              <td>&nbsp;</td>
              <td style="text-transform: uppercase;">POR <%= Constantes.DOCUMENTO_IDENTIDAD %></td>
              <td><select name="CTDI" class="celdas01">
                <option value="">SELECCIONA</option>
                
					<%
						v =  (Vector) request.getAttribute ("vDocumento");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
								<option value="<%=vCols.get(0)%>" <%= ((String)vCols.get(0)).equals("1")?"SELECTED":"" %>><%= vCols.get(1)%> </option>
          <%
							}
						}
					%>
              </select>&nbsp;&nbsp;
                <input name="DFICDID" type="text" class="celdas01" value="<%=(String)request.getParameter("DFICDID")==null?"":request.getParameter("DFICDID")%>" onfocus="nextfield ='DAPEPAT';" size="10" maxlength="10" onchange="veridigitos(this);" /></td>
              <td>&nbsp;</td>
              </tr>
            <tr>
              <td colspan="4">&nbsp;</td>
              </tr>
            <tr>
              <td>&nbsp;</td>
              <td style="text-transform: uppercase;"><%= Constantes.TITULO_INVOLUCRADO %></td>
              <td colspan="2">
			  AP:
			  <input name="DAPEPAT" type="text" class="celdas01" value="<%=(String)request.getParameter("DAPEPAT")==null?"":request.getParameter("DAPEPAT")%>" size="20" onfocus="nextfield ='DAPEMAT';" onchange="cambia(this);" maxlength="20">
               AM: <input name="DAPEMAT" type="text" class="celdas01" value="<%=(String)request.getParameter("DAPEMAT")==null?"":request.getParameter("DAPEMAT")%>" size="20" onfocus="nextfield ='DNOM';" onchange="cambia(this);" maxlength="20" />
               Nomb: <input name="DNOM" type="text" class="celdas01" value="<%=(String)request.getParameter("DNOM")==null?"":request.getParameter("DNOM")%>" size="20" onfocus="nextfield ='DAPEPATREC';" onchange="cambia(this);" maxlength="20" /></td>
              </tr>
            <tr>
              <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td style="text-transform: uppercase;"><%= Constantes.TITULO_SOLICITANTE %></td>
              <td colspan="2">AP:
                <input name="DAPEPATREC" type="text" class="celdas01" value="<%=(String)request.getParameter("DAPEPATREC")==null?"":request.getParameter("DAPEPATREC")%>" size="20" onfocus="nextfield ='DAPEMATREC';" onchange="cambia(this);" maxlength="20" />
AM:
<input name="DAPEMATREC" type="text" class="celdas01" value="<%=(String)request.getParameter("DAPEMATREC")==null?"":request.getParameter("DAPEMATREC")%>" size="20" onfocus="nextfield ='DNOMREC';" onchange="cambia(this);" maxlength="20" />
Nomb:
<input name="DNOMREC" type="text" class="celdas01" value="<%=(String)request.getParameter("DNOMREC")==null?"":request.getParameter("DNOMREC")%>" size="20" onfocus="nextfield ='FECHA1';" onchange="cambia(this);" maxlength="20" /></td>
              </tr>
            <tr>
              <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>RANGO DE FECHAS DE REGISTRO</td>
              <td>De: <input name="FECHA1" type="text" class="celdas01" value="<%=(String)request.getParameter("FECHA1")==null?"":request.getParameter("FECHA1")%>" size="10" onKeyPress="nextfield ='FECHA2';" onFocus="javascript:vDateType='3'" onKeyUp="DateFormat(this,this.value,event,false,'3')" onBlur="DateFormat(this,this.value,event,true,'3');" maxlength="10"></td>
              <td>Hasta: <input name="FECHA2" type="text" class="celdas01" value="<%=(String)request.getParameter("FECHA2")==null?"":request.getParameter("FECHA2")%>" size="10" onKeyPress="nextfield ='Submit';" onFocus="javascript:vDateType='3'" onKeyUp="DateFormat(this,this.value,event,false,'3')" onBlur="DateFormat(this,this.value,event,true,'3');" maxlength="10"></td>
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
  <table style="display: none;visibility: hidden;" width="75%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td align="center">&nbsp;
				<input type="hidden" name="opt" value="34">
      	<input type="hidden" name="pg" value="1">
			</td>
    </tr>
    <tr>
      <td align="center"><input name="Submit" type="submit" class="boton4" value="Buscar">      </td>
    </tr>
  </table>
</form>
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
