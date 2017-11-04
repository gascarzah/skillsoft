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
  Ficha_t F1t = (Ficha_t)request.getAttribute("objFicha_t");
  String cmso = (String) session.getAttribute("cmso");
  String cconclu = (String) session.getAttribute("cconclu");
  String bestadoreg = (String) session.getAttribute("bestadoreg");
  
	String msg = (String)request.getAttribute("msg_error");
	Vector v;
	Vector vCols;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>EsSalud - Defensor&iacute;a del Asegurado</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"  />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
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

function mostrarOcultos(fcon, ccon){
	//alert(" --- "+fcon+ " --- "+ ccon)
	/* if(fcon !=null ){
	if(fcon == "1" && ccon == "3"  ){
		document.f.CCON2.style.display = "block";
		document.f.CCON22.style.display = "block";
		document.f.DFICOBS.style.display = "block";
		document.f.Cars4.style.display = "block";	
		document.f.title_dirigido.style.display = "block";	
		document.f.title_cargo.style.display = "block";	
		document.f.title_gestion.style.display = "block";	
		document.f.CQUIEN.style.display = "block";	
		document.f.DCARGO.style.display = "block";	
		

	  }else{ 
			document.f.CCON2.style.display = "none";
			document.f.CCON22.style.display = "none";
			document.f.DFICOBS.style.display = "none";
			document.f.Cars4.style.display = "none";
			document.f.title_dirigido.style.display = "none";	
			document.f.title_cargo.style.display = "none";	
			document.f.title_gestion.style.display = "none";	
			document.f.CQUIEN.style.display = "none";	
			document.f.DCARGO.style.display = "none";	
		}
	} */
}

function mostrarOcultosInicio(fcon, ccon, anular){
	//alert(" --- "+fcon+ " --- "+ ccon + " --anular --"+anular)
	/* if(anular != '2'){
		
		if(fcon !=null ){
			
			if(fcon == "1" && ccon == "3"  ){
				
				document.f.CCON2.style.display = "block";
				document.f.CCON22.style.display = "block";
				document.f.DFICOBS.style.display = "block";
				document.f.Cars4.style.display = "block";	
				document.f.title_dirigido.style.display = "block";	
				document.f.title_cargo.style.display = "block";	
				document.f.title_gestion.style.display = "block";	
				document.f.CQUIEN.style.display = "block";	
				document.f.DCARGO.style.display = "block";
							
		
		
			  }else{ 
					document.f.CCON2.style.display = "none";
					document.f.CCON22.style.display = "none";
					document.f.DFICOBS.style.display = "none";
					document.f.Cars4.style.display = "none";
					document.f.title_dirigido.style.display = "none";	
					document.f.title_cargo.style.display = "none";	
					document.f.title_gestion.style.display = "none";	
					document.f.CQUIEN.style.display = "none";	
					document.f.DCARGO.style.display = "none";
		
				}
		}
	} */
}

function inicioPagina(){
	//alert(" --- "+fcon+ " --- "+ ccon + " --anular --"+anular)
				document.f.CCON2.style.display = "none";
				document.f.CCON22.style.display = "none";
				document.f.DFICOBS.style.display = "none";
				document.f.Cars4.style.display = "none";
				document.f.title_dirigido.style.display = "none";	
				document.f.title_cargo.style.display = "none";	
				document.f.title_gestion.style.display = "none";	
				document.f.CQUIEN.style.display = "none";	
				document.f.DCARGO.style.display = "none";
}



function goPg(np){
	 
	  document.f.pg.value = np;
	  document.f.opt.value = 26;
	  document.f.submit();
	}
function getReg(){ 

}

function pasa(fun,cod){
	parent.bot.location.href='../jsp/botFicha.jsp?funcion='+fun+'&codigo='+cod;
}

function grabaPersuasion(){
	
	  var cper = document.f.CPER.value;
	  var datpres = document.f.NOMPRESTADOR.value;
	  var fecpres = document.f.FECHARESP.value;
	  var cargo = document.f.CARGO.value;
	  var detalle = document.f.DETALLE.value;
	  
	  if (cper == "" ) {
		  alert ("Debe seleccionar una Acción de Persuasión");
		  document.f.CPER.focus();
		  
	  }else {
		  document.f.submit();
	  }
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

</script>
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<%
try {
%>
<body class="body" onload="MM_preloadImages('../images/fdatosgon.JPG','../images/fdatosdefon.JPG','../images/fdatossegon.JPG');inicioPagina();" bgproperties="fixed">
<form action="../servlet/CtrlFicha" method="post" name="f">
<input type="hidden" name="opt" value="40"/>


<input type="hidden" name="CAS" value="<%=F1t.getcas()!=null?F1t.getcas():""%>" />
            <input type="hidden" name="AYEAR" value="<%=F1t.getayear()!=null?F1t.getayear():""%>" />
            <input type="hidden" name="CORREL" value="<%=F1t.getcorrel()!=null?F1t.getcorrel():""%>" />
            <input type="hidden" name="CMSO" value="<%=F1t.getcmso()!=null?F1t.getcmso():""%>" />
            <input type="hidden" name="CCONCLU" value="<%=F1t.getCconclu()!=null?F1t.getCconclu():""%>" />
            
   <br/>
  
  <table width="700" border="0" align="center" cellspacing="1">
		  
		<tr>
	          <td>&nbsp;</td>
	          <td nowrap ="nowrap" width="150px"><%= Constantes.ACCION_PERSUASION%><b></b></td>
	          <td colspan="2">
	            <select name="CPER" class="celdas01" style="width: 272px;" >
	              <option value="" selected="selected">SELECCIONA</option>
	              <%
							v =  (Vector) request.getAttribute ("vAccPersuas");
							if (v != null) {  
								for (int i = 0; i < v.size(); i++) {  
									vCols = (Vector) v.get(i);  
						%>
						<option value="<%=vCols.get(0)%>" ><%= vCols.get(1)%></option>
	              <%
								}
							}
						%>
	            </select></td>
	          <td>&nbsp;</td>
	          
	          <td>&nbsp;</td>
	          <td nowrap ="nowrap"  width="150px"><%= Constantes.DATOS_PRESTADOR1%><b></b></td>
	          <td>
	              <input name="NOMPRESTADOR"  type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='FECHARESP';" size="50" maxlength="50" class="celdas01" />
	          </td>
         
        </tr>
        
        <tr><td colspan="7">&nbsp;</td></tr>
       
          <!--  <input type="hidden" name="ESTADO"/> -->
        <tr>
        
		     <td colspan="1" width="150px">&nbsp;</td>
		     <td nowrap ="nowrap" ><%= Constantes.FECHA_RESPUESTA%><b></b></td>
		     <td colspan="2">
		          <input type="text" name="FECHARESP" id="sel3" size="12"  readonly="readonly" style="width: 231px; "><input type="reset" value=" ... " onclick="return showCalendar('sel3', '%d/%m/%Y');"> 
	         </td>
	         
	        <td>&nbsp;</td>
	        
	        <td nowrap ="nowrap"  width="150px"><%= Constantes.CARGO_PREST%></td>
	        <td> 
	            <input name="CARGO" type="text"  size="50" onkeypress="return soloLetras(event)" maxlength="50" class="celdas01" />
	        </td>
	       
        </tr>
        
        <tr><td colspan="7">&nbsp;</td></tr>
        
        <tr>
	       <td valign="top" nowrap ="nowrap" width="150px"><b></b></td>
	       <td colspan="2"> 
	            <%= Constantes.DETALLE %>
	            <textarea rows="3" cols="15" name="DETALLE" onchange="cambia(this);" onkeypress="corta4(this);"onkeyup="corta4(this);" style="height: 112px; width: 271px; "></textarea>
	            <input name="Cars4" type="text" class="celdas01" onfocus="blur();" value="200" size="3" maxlength="3" align="right"/>
	       </td>
	       <td colspan="4">&nbsp;</td>
        
        </tr> 
</table>
  
  <%if(cmso == null){ %>
  <table align="center"><tr>
        <td><span class="titulo4">Nota: Para Grabar la Conclusión, se necesita que seleccione un Tipo de solicitud de la pestaña: "(2) Admisión y Calificación"</span> </td>
        </tr>
  </table>
  <%} %>
  <input type="hidden" name="pg" value="<%= F1t!=null?F1t.getPag():""%>">
  <% if(!F1t.getHshLista().isEmpty()) {%>
		
				
			
  <table width="700" border="0" align="center" cellspacing="0">
			<tr>
			<td align="right" width="25%"><%  if ( F1t!=null && F1t.getPaginacion().getPaginaAnt()) { %>
				<input name="button" value="  <<  " type="button" class="boton4" onClick="goPg(<%= Integer.parseInt(F1t.getPag()) - 1%>);" title="Retroceder a pagina anterior">
					<%  } 
				if (F1t!=null && F1t.getPaginacion().getPaginaSgte()) { %>
				<input name="button" value="  >>  " type="button" class="boton4" onClick="goPg(<%= Integer.parseInt(F1t.getPag()) + 1%>);" title="Avanzar a pagina siguiente">
					<%  } %>
			</td>
		</tr>
	</table>
  <%} %>
  <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      
      <td>&nbsp;</td>
    </tr>
    
    <tr>
      <td colspan="3" align="center">
				<input name="button" type="button" class="boton4" value="Grabar" onclick="grabaPersuasion();" />
				<input name="Button3" type="button" class="boton4" value="Imprimir" onclick="window.print();"/>
				<input name="Button3" type="button" class="boton4" value="Cerrar" onclick="window.close();"/>
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
  
  			
  
<br/>
	
	 <% if(!F1t.getHshLista().isEmpty()) {%>
   <table width="700"  border="1" align="center" cellspacing="0" bordercolor="#007ac9">
		<tr>
			<td>
				<table width="100%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
					<tr bgcolor="#3DB7E4" class="Estilo3" align="center">
					    <td><%= Constantes.TAB_ITEM %></td> 
						<td><%= Constantes.GRILLA_ACCIONES_PERSUASION %></td>
						<td><%= Constantes.GRILLA_DATOS_PRESTADOR %></td>
						<td><%= Constantes.GRILLA_FECHA_RESPUESTA %></td>
						<td><%= Constantes.GRILLA_CARGO %></td>
						<td><%= Constantes.GRILLA_DETALLE %></td>
						
					</tr>
					<input type="hidden" name="IDDEF"></input>
					<%
								int counter = 0;
								int counter2 = Integer.parseInt(F1t.getdtotal_reg()) +1;
								boolean bFil = true;
								for (Enumeration e = F1t.getHshLista().keys(); e.hasMoreElements();) {
									e.nextElement();
									v = (Vector)F1t.getHshLista().get("" + counter);
									bFil = bFil?false:true; 
									counter++;
									counter2--;
						%>
						
										<tr bgcolor="<%=bFil?"#EAEFF5":"#FFFFFF"%>" class="celdas01">
										  <td align="center">
										  
					              <a href="javascript:getReg('<%= v.get(0)%>');"><img src="../images/fleder.jpg" alt="Seleccione para actualizar datos del registro" width="12" height="12" border="0" /></a>
					              
					            &nbsp;&nbsp; <%= counter2 - ((Integer.parseInt(F1t.getPag()) - 1)  * 15) %></td>
					            			<td align="center"><%= v.get(6)!=null?v.get(6):"&nbsp;" %></td>
											<td align="center"><%= v.get(1)!=null?v.get(1):"" %></td>
											<td align="center"><%= v.get(3)!=null?v.get(3):"" %></td>
											<td align="center"><%= v.get(4)!=null?v.get(4):"" %></td>
											<td><textarea  cols="20" rows="3"><%= v.get(5)!=null?v.get(5):"" %></textarea> </td>
											
										</tr>
						<%
								}  
						%>
				</table>
				 
				</td>
			
				</tr>
				
				</table>
				
					
  <%} %>
                        
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
