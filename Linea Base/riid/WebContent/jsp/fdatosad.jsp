
<%@page import="org.apache.commons.lang.StringUtils"%>
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
  UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario");
    Ficha_t F1t = (Ficha_t)session.getAttribute("objFicha_t");
    
    String var_cras = F1t.getcras()!=null?F1t.getcras():"";
	String msg = (String)request.getAttribute("msg_error");
	Vector v;
	Vector vCols;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"  />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css"/>
<link href="../styles/botones.css" rel="stylesheet" media="print" type="text/css"/>
<script language="JavaScript" src="../js/ubigeo.js"></script>
<script language="JavaScript" src="../js/script2.js"></script>
<script language="javascript" src="../js/format_date.js"></script>
<script language="javascript" src="../js/stm31.js"></script>
<script language="javascript" src="../js/val_campos.js"></script>

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

function pasa(fun,cod){
	parent.bot.location.href='../jsp/botFicha.jsp?funcion='+fun+'&codigo='+cod;
}


function reload_cas(){
  parent.bot.location.href='../jsp/botFicha.jsp?funcion=CASTOTAL';
}
function locationServer (valor1, valor2) {
  parent.bot.location.href='../jsp/botAD.jsp?tipo=' + valor1 + '&num=' + valor2;
}

function getMigra (obj1, obj2) {
  document.f.DAPEPATQUEJA.value = "";
  document.f.DAPEMATQUEJA.value = "";
  document.f.DNOMQUEJA.value = "";
  if (obj1.value == "" || obj2.value == "") {
	  alert ("Debe completar el tipo y n�mero de documento");
    document.f.DDOCQUEJA.focus();
  }
  else if (obj1.value != "" && obj2.value != "") {
	  locationServer(obj1.value, obj2.value);
  }else{
	  alert("No hay informaci�n");
  }
}
function envia(){
	var ucas = <%= clusuario.getCcas() %>;
	if(ucas == "000")	valida();
	else validaOAS(<%= clusuario.getPerfil() %>);
}








function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}


function ocultar(codigo)
{   //var codigo = cod.value;
	if(codigo == null){
		codigo = '';
	}
		
	
	if( codigo != ''){
		
		
		if(codigo=="1" || codigo=="5")
		{
			
			// habilitamos
			document.getElementById("CMATERIA").disabled=false;
			document.getElementById("CASU").disabled=false;
			document.getElementById("CTEM").disabled=true;
			document.getElementById("btnamotivo").disabled=false;
			document.getElementById("btnqmotivo").disabled=false;
			document.getElementById("dlis").style.visibility = 'visible'; 
			document.getElementById("CTMA").disabled=true;
			document.getElementById("CTMC").disabled=true;
			document.getElementById("fechaq").disabled=true;
			document.getElementById("sel3").disabled=true;
			document.f.fechaq.value = "";
			document.f.CTMA.value = "";
			document.f.CTMC.value = "";
			
		}else if(codigo=="2" || codigo=="3" || codigo=="4"){
			
			// deshabilitamos
			document.getElementById("CMATERIA").disabled=true;
			document.getElementById("CASU").disabled=true;
			document.getElementById("CTEM").disabled=false;
			document.getElementById("btnamotivo").disabled=true;
			document.getElementById("btnqmotivo").disabled=true;
			document.getElementById("dlis").style.visibility = 'hidden'; 
			document.getElementById("CTMA").disabled=true;
			document.getElementById("CTMC").disabled=true;
			document.getElementById("fechaq").disabled=true;
			document.getElementById("sel3").disabled=true;
			document.f.fechaq.value = "";
			document.f.CTMA.value = "";
			document.f.CTMC.value = "";
			
		}else if(codigo=='6'){
			document.getElementById("CMATERIA").disabled=true;
			document.getElementById("CASU").disabled=true;
			document.getElementById("CTEM").disabled=true;
			document.getElementById("btnamotivo").disabled=true;
			document.getElementById("btnqmotivo").disabled=true;
			document.getElementById("dlis").style.visibility = 'hidden';
			document.getElementById("CTMA").disabled=false;
			document.getElementById("CTMC").disabled=false;
			document.getElementById("fechaq").disabled=false;
			document.getElementById("sel3").disabled=false;
			
		}
		
	}
	
	else {
		
		// deshabilitamos
		document.getElementById("CMATERIA").disabled=true;
		document.getElementById("CASU").disabled=true;
		document.getElementById("CTEM").disabled=true;
		document.getElementById("btnamotivo").disabled=true;
		document.getElementById("btnqmotivo").disabled=true;
		document.getElementById("dlis").style.visibility = 'hidden';
		document.getElementById("CTMA").disabled=true;
		document.getElementById("CTMC").disabled=true;
		document.getElementById("fechaq").disabled=true;
		document.getElementById("sel3").disabled=true;
		document.f.fechaq.value = "";
		document.f.CTMA.value = "";
		document.f.CTMC.value = "";
		
	}
}

</script>
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<%
try {
%>
<body class="body" onload="MM_preloadImages('../images/fdatosgon.JPG','../images/fdatosdefon.JPG','../images/fdatossegon.JPG');ponerLD();ocultar('<%=F1t.getcmso() %>');"  bgproperties="fixed">
<form action="../servlet/CtrlFicha" method="post" name="f" >
<table class="banner01" >
    <tr>
      <td width="220" rowspan="5" align="center"><img src="../images/es03.jpg" width="120" height="100"/></td>
      <td align="center"><%@include file="../js/menu_all.js" %></td>
      <td width="220" rowspan="8" align="right"><img src="../images/defensoria01.jpg" width="120" height="100"/></td>
    </tr>
    <tr>
      <td align="center" class="titulob1">&nbsp;</td>
    </tr>
    <tr align="center">
      <td>&nbsp;</td> 
    </tr>
    <tr align="center">
      <td align="center" class="titulob2">REGISTRO INFORM�TICO DE ATENCI�N AL ASEGURADO</td>
    </tr>
    <tr align="center">
      <td align="center" class="titulob1">&nbsp;</td>
    </tr>
    <tr align="center">
      <td align="center" class="celdasb"><%= clusuario.getDescripcion() %></td>
      <td rowspan="3" class="titulo4">
      	<table width="720" align="center">
        	<tr>
        	<% if (!clusuario.getPerfil().equals(Constantes.ESSALUD_EN_LINEA)){%>
            	<td><a href="javascript:toDatosG();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('b1','','../images/uno.jpg',1)"><img src="../images/uno0.jpg" name="b1" width="160" height="50" border="0" id="b1" /></a></td>
                <td><img src="../images/dos0.jpg" width="160" height="50"/></td>
                <td><a href="javascript:toDatosDef();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('b3','','../images/tres0.jpg',1)"><img src="../images/tres.jpg" name="b3" width="160" height="50" border="0" id="b3" /></a></td>
                <td><a href="javascript:toDatosSeg();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('b4','','../images/cuatro0.jpg',1)"><img src="../images/cuatro.jpg" name="b4" width="160" height="50" border="0" id="b4" /></a></td>
                <%} %>
            </tr>
        </table>
      </td>
    </tr>
    <tr align="center">
      <td align="center" class="celdasb"><%= clusuario.getdPerfil() %></td>
    </tr>
    <tr align="center">
      <td align="center" class="celdasb"><%= clusuario.getdCas() %></td>
    </tr>
  </table>
  <table width="100%">
    <tr><td align="center" class="titulo_gerencia" >Gerencia Central de Atenci�n al Asegurado</td></tr>
  </table>
  <table align="center" cellpadding="5">
    
  </table>

   <table width="100%" border="0">
    <tr>
          <td style="visibility: hidden">
           <input type="hidden" name="CAS" value="<%=F1t.getcas()!=null?F1t.getcas():""%>" />
            <input type="hidden" name="AYEAR" value="<%=F1t.getayear()!=null?F1t.getayear():""%>" />
            <input type="hidden" name="CORREL" value="<%=F1t.getcorrel()!=null?F1t.getcorrel():""%>" /></td>
           
           <td align="left" class="titulo3" width="33%"><%= Constantes.NOM_DELEGADO %> <%=StringUtils.defaultString(F1t.getDescDelegado())%></td>
           <td align="center" class="titulo3" width="34%"><%= Constantes.OFICINA %> <%= StringUtils.defaultString(F1t.getDescRedDelegado())%></td>
           <td width="33%" align="right" class="titulo3"><%= Constantes.NRO_EXPE %> <%=F1t.getayear()!=null?F1t.getayear():""%>-<%=F1t.getcas()!=null?F1t.getcas():""%>-<%=F1t.getcorrel()!=null?F1t.getcorrel():""%>&nbsp;&nbsp;</td>
    </tr>
    <tr>
          <td></td>
          <td align="left" class="titulo3" width="33%"></td>
          <td align="center" class="titulo3" width="34%"></td>
          <td align="right" class="titulo3" width="33%"><%=StringUtils.isNotEmpty(F1t.getfusucrea())?Constantes.FECHA_REGISTRO:"" %> <%= StringUtils.defaultString(F1t.getfusucrea()) %> </td>
     </tr>
     <tr>
          <td></td>
          <td align="left" class="titulo3" width="33%"></td>
          <td align="center" class="titulo3" width="34%"></td>
          <td align="right" class="titulo3" width="33%"><%= StringUtils.isNotEmpty(F1t.getfusumodi())?Constantes.FECHA_ULTIMA:"" %> <%= StringUtils.defaultString(F1t.getfusumodi()) %> </td>
     </tr>
   </table>
   <br />
   <%
   
   	  if(F1t.getcred()==null || F1t.getcred().equals(""))
	  { 
   %>
   
   <div style="height: 30px;width: 100%;text-align: center;">
   	  <span class="titulo4">
        <% msg="Por favor termine de llenar la Ficha 1: Ingreso de datos generales."; 
        if(msg != null){ %>
        <%= msg %>
        <% } %>
      </span>
   </div>
   <%
	  }
   %>   
  <table width="85%"  border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#007AC9" bgcolor="#ffffff" class="celdas">
		
		 
		<tr>
    	<td>
	     	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
         
          <tr>
          
          <td colspan="2"><%= Constantes.TIPO_SOLICITUD %><b>*</b>
          <select name="CMSO" class="celdas01" onfocus="nextfield ='CMSO';" onchange="pasa('DTEMA',this.form.CMSO.options[this.form.CMSO.selectedIndex].value); ocultar(this.value);">
                <option value="">SELECCIONAR</option>
                <%
						v =  (Vector) request.getAttribute ("vSolicitud");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					
								
					 %>
					 
                		<option value="<%=vCols.get(0)%>" <%= F1t.getcmso()!= null && F1t.getcmso().equals(vCols.get(0)) ?"SELECTED":"" %>><%= vCols.get(1)%></option>
                	<%
							}
						}
					%>
            </select></td>
            
            <td colspan="3"><%= Constantes.TEMATICA %><b><font size="1px">(1)</font></b> 
          	<select id="CTEM" name="CTEM" class="celdas01" onfocus="nextfield ='CTEM';" >
          		<option value="">SELECCIONAR</option>
                <%
						v =  (Vector) request.getAttribute ("vTematica");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					
					 %>
                		<option value="<%=vCols.get(0)%>" <%= F1t.getCtem() != null && F1t.getCtem().equals(vCols.get(0)) ?"SELECTED":"" %>><%= vCols.get(1)%></option>
                	<%
							}
						}
					%>
            </select></td>
          </tr>
          
          <tr>
            <td>&nbsp;</td>       
            <td colspan="4">&nbsp;</td>
          </tr>
          
         
          <td colspan="2"><%= Constantes.MOTIVO_NO_ADMISIBLE %><b></b>
           <select name="CTMA" id="CTMA" class="celdas01" onfocus="nextfield ='CTMA';">
              <option value="">SELECCIONA</option>
					<%
						v =  (Vector) request.getAttribute ("vMotivoNoAdm");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
              <option value="<%=vCols.get(0)%>" <%= F1t.getctma() != null && F1t.getctma().equals(vCols.get(0)) ?"SELECTED":"" %>><%= vCols.get(1)%></option>
					<%
							}
						}
					%>
            </select>

            </td>
            
            
            <td colspan="2"><%= Constantes.MODALIDAD_CONCLUSION %><b></b>
            
            <select name="CTMC" id="CTMC" class="celdas01" onfocus="nextfield ='CTMC';">
              <option value="">SELECCIONA</option>
					<%
						v =  (Vector) request.getAttribute ("vModelDeConclu");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
              <option value="<%=vCols.get(0)%>" <%= F1t.getctmc() != null && F1t.getctmc().equals(vCols.get(0)) ?"SELECTED":"" %> ><%= vCols.get(1)%></option>
					<%
							}
						}
					%>
            </select>
          
          
          <%= Constantes.FECHA%>
           
          <input type="text" name="fechaq" value="<%= F1t.getfechaq()!= null ? F1t.getfechaq() : "" %>" id="fechaq" size="12"  readonly="readonly" style="width: 100px; "><input type="reset" value=" ... " onclick="return showCalendar('fechaq', '%d/%m/%Y');" id="sel3"> 
<br />
          

           	</td>
       		</tr>
          
            <td>&nbsp;</td>
            <td> <%= Constantes.MOTIVO_PRINCIPAL %><b><font size="1px">(2)</font></b> 
              <select id="CMATERIA" name="CMATERIA" class="celdas01" onchange="pasa('MAT1',this.form.CMATERIA.options[this.form.CMATERIA.selectedIndex].value);">
                <option value="" selected="selected">SELECCIONA</option>
					<%
            v =  (Vector) request.getAttribute ("vMateria");
            if (v != null) {  
              for (int i = 0; i < v.size(); i++) {  
                vCols = (Vector) v.get(i);  
          %>
	              <option value="<%=vCols.get(0)%>" <%= Util.jnvl((String)request.getParameter("CMATERIA"),"").compareTo(vCols.get(1))==0?"SELECTED":"" %>><%= vCols.get(1)%></option>
					<%
						}
							}
          %>
              </select>
            </td>
          	<td colspan="3">&nbsp;&nbsp;
            	<select id = "CASU" name="CASU" class="celdas01" onfocus="nextfield ='';">
              	<option value="">SELECCIONA</option>
          <%
						v =  (Vector) request.getAttribute ("vCodigo");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					%>
              	<option value="<%=vCols.get(0)%>" <%= Util.jnvl((String)request.getParameter("CODIGO"),"").compareTo(vCols.get(0))==0?"SELECTED":"" %>><%= vCols.get(2)%></option>
              <%
							}
						}
					%>
              </select>
            </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="4">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>       
            <td colspan="4"><input  type="button" value="Agregar motivo" class="boton4" onclick="addLD();" id="btnamotivo"/>&nbsp;&nbsp;&nbsp;
			  <input type="button" value="Quitar motivo" class="boton4" onclick="delLD();" id="btnqmotivo"/>
            </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="4">
            <div id="dlis">
            <p id="lis"></p>
            </div>
            </td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="4"><input type="hidden" name="CMOTIVOS" value="<%=F1t.getcmotivos()!=null?F1t.getcmotivos():""%>"/>
            <input type="hidden" name="DMOTIVOS" value="<%=F1t.getdmotivos()!=null?F1t.getdmotivos():""%>"/></td>
            <td width="0%">&nbsp;</td>
          </tr>
          
		  <tr>
		    <td>&nbsp;</td>
		    <td colspan="4"><hr color="#007ac9" /></td>
		    
		    </tr>
		  <tr>
		  <td>&nbsp;</td>
		  <td colspan="2"><%= Constantes.UNIDADORG %><b>*</b>
            <select name="CRED" class="celdas01" disabled="disabled" onfocus="nextfield ='CCAS';"  onchange="pasa('CASC',this.form.CRED.options[this.form.CRED.selectedIndex].value);">
              <option value="<%=F1t.getcras()!=null?F1t.getcras():""%>" selected="selected"><%=F1t.getdras()!=null?F1t.getdras():""%></option>
              <%
              v =  (Vector) request.getAttribute ("vRed");
              if (v != null) {  
                for (int i = 0; i < v.size(); i++) {  
                  vCols = (Vector) v.get(i);  
            %>
              <option value="<%=vCols.get(0)%>" 
              <%= F1t.getcred()!= null && F1t.getcred().equals(vCols.get(0).toString()) ? "SELECTED":""%> 
              <%= Util.jnvl((String)request.getParameter("CRED"),"").compareTo(vCols.get(0))==0?"SELECTED":"" %>
              >
              <%= vCols.get(1)%>
              </option>
              <%
                }
              }
            %>
            </select></td>
		  <td colspan="2"><%= Constantes.UNIDADORG1 %><b>*</b>
            <select name="CCAS" class="celdas01" disabled="disabled" onchange="pasa('DCAS',this.form.CCAS.options[this.form.CCAS.selectedIndex].value);">
              <option value="<%=F1t.getccas()!=null?F1t.getccas():""%>" selected="selected"><%=F1t.getdcas()!=null?F1t.getdcas():""%></option>
              <%
              v =  (Vector) request.getAttribute ("vCas");
              if (v != null) {  
                for (int i = 0; i < v.size(); i++) {  
                  vCols = (Vector) v.get(i);  
            %>
              <option value="<%=vCols.get(0)%>" 
              <%= F1t.getccas()!= null && F1t.getccas().equals(vCols.get(0).toString()) ?"SELECTED":"" %>><%= vCols.get(1)%>
              </option>
              <%
                }
              }
            %>
            </select>
            
		  
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td colspan="2">&nbsp;</td>
		    <td width="33%">&nbsp;</td>
		    <td width="4%">&nbsp;</td>
		    </tr>
		  <tr>
		  <td>&nbsp;</td>
		  <td colspan="2"><%= Constantes.AREA %><b>*</b>
            <select name="ENLAREHOSCOD" class="celdas01" onfocus="nextfield ='ENLSERVHOSCOD';" onchange="pasa('AREASERV',this.form.ENLAREHOSCOD.options[this.form.ENLAREHOSCOD.selectedIndex].value); ">
             <option value="">SELECCIONA</option>
            <%
              v =  (Vector) request.getAttribute ("vEnlarehoscod");
              if (v != null) {
                for (int i = 0; i < v.size(); i++) {  
                  vCols = (Vector) v.get(i);  
	              	
                  if(F1t.getenlarehoscod()==null){
                	  F1t.setenlarehoscod("");
                  }
              %>
              <option value="<%=vCols.get(0)%>" <%=  StringUtils.isNotEmpty(F1t.getenlarehoscod()) && StringUtils.equals(F1t.getenlarehoscod(), (String) vCols.get(0))?"SELECTED":"" %>><%= vCols.get(1)%></option>
            <%
                }
              }
            %>
            </select>
            <input name="ESPECIALIDAD" type="hidden" /></td>
		  <td colspan="2"><%= Constantes.SERVICIO %><b>*</b>
            <select name="ENLSERVHOSCOD" class="celdas01" onfocus="nextfield ='ENLAREHOSCOD';">
              <option value="">SELECCIONA</option>
              <%
                  v =  (Vector) request.getAttribute ("vEnlservhoscod");
                  if (v != null) {  
                    for (int i = 0; i < v.size(); i++) {  
                      vCols = (Vector) v.get(i);  
                %>
              <option value="<%=vCols.get(0)%>" <%= StringUtils.isNotEmpty(F1t.getenlservhoscod()) && StringUtils.equals(F1t.getenlservhoscod(), (String) vCols.get(0)) ?"SELECTED":""%> > <%= vCols.get(1)%></option>
              <%
                    }
                  }
                %>
            </select></td>
		  
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		    <td colspan="5">&nbsp;</td>
		    </tr>
		  <tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="4"><hr color="#007ac9" /></td>
          </tr>
          <tr>
          </tr>
          <tr>
	          <td>&nbsp;</td>
	          <td colspan="3" height="30px"><label style="font-weight: bold;font-size: 15px;">Datos del servidor o funcionario de quien se reclama: </label>  </td>
	          <td>&nbsp;</td>
          </tr>
          <tr>
            <td height="18">&nbsp;</td>       
            <td width="32%"><%= Constantes.APE_PATERNO %>
                <input name="DAPEPATQUEJA" type="text" value="<%=F1t.getdapepatqueja()!=null?F1t.getdapepatqueja():""%>" onkeypress="return soloLetras(event);" onfocus="nextfield ='DAPEMATQUEJA';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" />
            </b></td>
            <td width="31%"><%= Constantes.APE_MATERNO %>
                <input name="DAPEMATQUEJA" type="text" value="<%=F1t.getdapematqueja()!=null?F1t.getdapematqueja():""%>" onkeypress="return soloLetras(event);" onfocus="nextfield ='DNOMQUEJA';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" />
            </b></td>
            <td><%= Constantes.NOMBRE %>
                <input name="DNOMQUEJA" type="text" value="<%=F1t.getdnomqueja()!=null?F1t.getdnomqueja():""%>" onkeypress="return soloLetras(event);" onfocus="nextfield ='DNOMQUEJA';" onchange="cambia(this);" size="30" maxlength="30" class="celdas01" />
            </b></td>
            <td>&nbsp;</td>       
          </tr>
          <tr><td>&nbsp;</td></tr>
          <td colspan="2"><%= Constantes.GRUPO_OCUP %> 
                <input type="text" onkeypress="return soloLetras(event);" class="celdas01" name="GRUPOCUP" onchange="cambia(this);" value="<%=F1t.getGrupOcup()!=null?F1t.getGrupOcup():""%>"/><br></br>
                
            </td>
            <td>&nbsp;</td>
            <td colspan="4"></td>
            
          </tr>
         
        </table>
      </td>
      
    </tr>
  </table>
  <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
    <tr><table>
    <tr><td width="20%"><%= Constantes.INFO_OBLI %>: </td></tr>
    <tr><td width="20%"><%= Constantes.OP_1  %></td></tr>
	<tr><td width="20%"><%= Constantes.OP_2  %></td></tr>
	</table>
      <td align="center">
        <input type="hidden" name="opt" value="28">
        <input type="hidden" name="caso" value="I">
        <input name="dato" type="hidden" />
        
        <input type="hidden" name="WORKFLOW" value="<%= clusuario.getBworkflow() %>">
        <input type="hidden" name="CONSEJEROS">
<% if(clusuario.getCcas().compareTo("000")!=0){ %>
				<input type="hidden" name="DEFENSORIALES" value="06">
        <input type="hidden" name="PLAZO">
      	<% } %>
        
			</td>
      
    </tr>
<tr>
          <td>&nbsp;</td>
          <td colspan="3">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
    <tr>
    
      <td colspan="5" align="center" >
      
      <%  
      	  if(F1t.getcred()!= null && !F1t.getcred().equals(""))
      	  { 
      		
	      		if(F1t.getbestadoreg()!=null && F1t.getbestadoreg().equals(Constantes.ESTADO_ACTIVADO)
	      				&& (clusuario.getPerfil().equals(Constantes.PERFIL_COORDINADOR)||clusuario.getPerfil().equals(Constantes.PERFIL_DELEGADO_DEFENSORIAL)  ||  clusuario.getPerfil().equals(Constantes.PERFIL_ESSALUD_LINEA)) ) {%>
	      		<div align="center">
					<input name="button" type="button" class="boton4" value=" Grabar " onclick="alaBD();" />
					 <input name="Button3" type="button" class="boton4" value=" Imprimir " onclick="window.print();"/>
				</div>
				
		    
	      <%  } else if (F1t.getbestadoreg()!=null && F1t.getbestadoreg().equals(Constantes.ESTADO_INACTIVO) && (clusuario.getPerfil().equals(Constantes.PERFIL_DELEGADO_DEFENSORIAL)  ||  clusuario.getPerfil().equals(Constantes.PERFIL_ESSALUD_LINEA)) ) { %>
	      	 <div align="center">
	      	     <input name="Button3" type="button" class="boton4" value=" Imprimir " onclick="window.print();"/>
	      	 </div>
	      	 
          <%    }  else if (F1t.getbestadoreg()!=null && F1t.getbestadoreg().equals(Constantes.ESTADO_INACTIVO) && (clusuario.getPerfil().equals(Constantes.PERFIL_COORDINADOR))) { %>
       
               <div align="center">
					<input name="button" type="button" class="boton4" value=" Grabar " onclick="alaBD();" />
					 <input name="Button3" type="button" class="boton4" value=" Imprimir " onclick="window.print();"/>
				</div>
       
       
           <%    } %>
      
    <%    } %>
      	 
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
  session.removeAttribute("obj");
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
