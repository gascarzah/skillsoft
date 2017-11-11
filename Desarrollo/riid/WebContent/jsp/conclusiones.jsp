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
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"  />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css">
<link href="../styles/botones.css" rel="stylesheet" media="print" type="text/css">
<script language="JavaScript" src="../js/ubigeo.js"></script>
<script language="JavaScript" src="../js/script2.js"></script>
<script language="javascript" src="../js/format_date.js"></script>
<script language="javascript" src="../js/stm31.js"></script>
<script language="javascript">

function mostrarOcultos(fcon, ccon){
	//alert(" --- "+fcon+ " --- "+ ccon)
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
}

function mostrarOcultosInicio(fcon, ccon, anular){
	//alert(" --- "+fcon+ " --- "+ ccon + " --anular --"+anular)
	if(anular != '2'){
		
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
	}
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

function getHelp(ayear,cas,correl,fcon,ccon,idconclu) { 
	w = opwinMS("../servlet/CtrlFicha?opt=39&AYEAR="+ayear+"&CAS="+cas+"&CORREL="+correl+"&FCON="+fcon+"&CCON="+ccon+"&IDCONCLU="+idconclu+"&TIPOOPERACION='C'",			
			"wCita",
			"yes",
			"yes",
       1200,
      490,
      screen.height/2-195,
      screen.width/2-545)
	if (w.opener == null)
		w.opener = self;
	w.focus();
}


function grabaConclusiones(){
	  var fcon = <%=F1t.getcmso() %>;
	  var ccon = document.f.CCON.value;
		
	  if (fcon == "" ) {
		  alert ("Debe seleccionar una forma de conclusion");
	      document.f.FCON.focus();
	  }else if (ccon == "" ) {
		  alert ("Debe seleccionar una conclusion");
		  document.f.CCON.focus();
		  
	  }else {
		  document.f.submit();
	  }
}

function actualizarConclusiones(){
	  var fcon = <%=F1t.getcmso() %>;
	  var ccon = document.f.CCON.value;
		
	  if (fcon == "" ) {
		  alert ("Debe seleccionar una forma de conclusion");
	      document.f.FCON.focus();
	  }else if (ccon == "" ) {
		  alert ("Debe seleccionar una conclusion");
		  document.f.CCON.focus();
		  
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
<input type="hidden" name="opt" value="30"/>
<input type="hidden" name="idconclu" />
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
      <td align="center" class="titulob2">REGISTRO INFORMÁTICO DE ATENCIÓN AL ASEGURADO</td>
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
            	<td><a href="javascript:toDatosG();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('b1','','../images/uno.jpg',1)"><img src="../images/uno0.jpg" width="160" height="50" border="0" id="b1" /></a></td>
                <td><a href="javascript:toDatosAd();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('b2','','../images/dos0.jpg',1)"><img src="../images/dos.jpg" width="160" height="50" border="0" id="b2" /></a></td>
                <td><a href="javascript:toDatosDef();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('b3','','../images/tres0.jpg',1)"><img src="../images/tres.jpg" name="b3" width="160" height="50" border="0" id="b3" /></a></td>
                <td><img src="../images/cuatro0.jpg" width="160" height="50"></td>
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
    <tr><td align="center" class="titulo_gerencia" >Gerencia Central de Atención al Asegurado</td></tr>
  </table>
  <table align="center" cellpadding="5">
    
  </table>
    <table width="100%" border="0">
    <tr>
		      
          <td style="visibility: hidden"> <input type="hidden" name="CAS" value="<%=F1t.getcas()!=null?F1t.getcas():""%>" />
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
  
  <table width="85%"  border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#007AC9" bgcolor="#ffffff" class="celdas">
		<tr>
    	<td>
	      <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <input type="hidden" name="FCON" value="<%=StringUtils.defaultString(F1t.getcmso()) %>"></input>
          
          <tr>
            <td>&nbsp;</td>       
            <td colspan="4">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
         
          	<td colspan="2" valign="top">&nbsp;&nbsp;Conclusion<b>*</b>
            	<select name="CCON" class="celdas01" onfocus="nextfield ='CCON2';" onchange="pasa('DCONCLU2',this.form.CCON.options[this.form.CCON.selectedIndex].value);mostrarOcultos(<%=F1t.getcmso() %>,this.value);" >
              	<option value="">SELECCIONA</option>
              	  <%
						v =  (Vector) request.getAttribute ("vConclusiones");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					
					 %>
                		<option value="<%=vCols.get(0)%>" ><%= vCols.get(1)%></option>
                	<%
							}
						}
					%>

              </select>
            </td>
            <!-- INI 3er combo -->
            
            <td id='AccionesCombo' name="C_ACCIONES" >
            	<input id="CCON22" name="CCON22" style="display:none;border:none;" value='Acciones de Seguimiento:' readonly="readonly"/>
            	<select id="CCON2" name="CCON2" class="celdas01"  style="display:none;">
              	<option value="">SELECCIONA</option>
              	    <%
						v =  (Vector) request.getAttribute ("vAccSeg");
						if (v != null) {  
							for (int i = 0; i < v.size(); i++) {  
								vCols = (Vector) v.get(i);  
					
					 %>
                		<option value="<%=vCols.get(0)%>" ><%= vCols.get(1)%></option>
                	<%
							}
						}
					%>

              	</select>
              	</td>
             </tr> 
               <tr>
               <td colspan="3">&nbsp;</td>
               <td colspan="2">
               
              	<input id="title_dirigido" name="title_dirigido" style="border:none;display:none; width: 174px" value='<%= Constantes.DIRIGIDO_A %>' readonly="readonly"/>
              	<input name="CQUIEN" onchange="cambia(this); " type="text"  size="40" maxlength="50" class="celdas01" style="display:none;" />
              	</td>
              </tr>    	
             <tr></tr>
             
              <tr>
               <td colspan="3">&nbsp;</td>
               <td colspan="2">
              	<input id="title_cargo" name="title_cargo" style="border:none;display:none; width: 179px" value='<%= Constantes.CARGO %>' readonly="readonly" />
              	<input name="DCARGO" type="text" onchange="cambia(this);" size="40"  maxlength="50" class="celdas01" style="display:none;" />
            	</td>
            	</tr>   
            
            <tr></tr>
            	 
              <tr>
               <td colspan="3">&nbsp;</td>
               <td colspan="2">
            	<input id="title_gestion" name="title_gestion" style="border:none;display:none; width: 179px" value='<%= Constantes.GESTION %>' readonly="readonly" />
            	<textarea rows="6" cols="20" id="DFICOBS" name="DFICOBS" onchange="cambia(this);" onkeypress="corta5(this);"onkeyup="corta5(this);" style="display:none;"  ></textarea>
            	<input id="Cars4" name="Cars4" type="text" class="celdas01" onfocus="blur();" value="120" size="3" maxlength="3" align="right" style="display:none;" readonly="readonly"/>
               </td>
			  </tr>    	
              
          <tr>
            <td>&nbsp;</td>
            <td colspan="4">&nbsp;</td>
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <%if(cmso == null){ %>
  <table align="center"><tr>
        <td><span class="titulo4">Nota: Para Grabar la Conclusión, se necesita que seleccione un Tipo de solicitud de la pestaña: "(2) Admisión y Calificación"</span> </td>
        </tr>
  </table>
  <%} %>
  <input type="hidden" name="pg" value="<%= F1t!=null?F1t.getPag():""%>">
  <% if(F1t.isEmpty()) {%>
		
				
			
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
    
    <%if(bestadoreg!=null &&  bestadoreg.equals(Constantes.ESTADO_ACTIVADO) &&
				(clusuario.getPerfil().equals(Constantes.PERFIL_COORDINADOR) || clusuario.getPerfil().equals(Constantes.PERFIL_DELEGADO_DEFENSORIAL))) {%>
   
	   <%  if(cmso != null  && cconclu == null ) { %>
	    <tr>
	      <td colspan="3" align="center">
					<input name="button" type="button" class="boton4" value=" Grabar " onclick="grabaConclusiones();" >   
					<input name="Button3" type="button" class="boton4" value=" Imprimir " onclick="window.print();"/>     
	      </td>
	    </tr>
	    <% } else if(cmso!=null && cmso.equals("1")  && cconclu!=null && cconclu.equals("3")) { %>
	    <tr>
	      <td colspan="3" align="center">
				<input name="button" type="button" class="boton4" value=" Grabar " onclick="grabaConclusiones();" >        
				<input name="Button3" type="button" class="boton4" value=" Imprimir " onclick="window.print();"/>
		</td>
	    </tr>
	
	    <%} %>
    <%} else if(bestadoreg!=null &&  bestadoreg.equals(Constantes.ESTADO_INACTIVO) &&  (clusuario.getPerfil().equals(Constantes.PERFIL_COORDINADOR))) {  %>
	          
       	
		<tr>
	      <td colspan="3" align="center">
				<input name="button" type="button" class="boton4" value=" Grabar " onclick="grabaConclusiones();" >        
				<input name="Button3" type="button" class="boton4" value=" Imprimir " onclick="window.print();"/>
		</td>
	    </tr>
				
				
	 <%} else if(bestadoreg!=null &&  bestadoreg.equals(Constantes.ESTADO_INACTIVO) &&  clusuario.getPerfil().equals(Constantes.PERFIL_DELEGADO_DEFENSORIAL)) {  %>	
	 
	     <tr>
	      <td colspan="3" align="center">
				       
				<input name="Button3" type="button" class="boton4" value=" Imprimir " onclick="window.print();"/>
		</td>
	    </tr>
	 		
      <%} else if(bestadoreg!=null &&    (clusuario.getPerfil().equals(Constantes.PERFIL_ESSALUD_LINEA))) { %>  
      
      			
         <tr>
	      <td colspan="3" align="center">
				       
				<input name="Button3" type="button" class="boton4" value=" Imprimir " onclick="window.print();"/>
		</td>
	    </tr>
      
      
      <%} %>    
    <tr>
      <td colspan="3" align="center"><span class="titulo4">
        <% if(msg != null){ %>
        <%= msg %>
        <% } %>
      </span></td>
    </tr>
  </table>
  
  		
<br />
	<% if(F1t.isEmpty()) {%>
   <table width="700"  border="1" align="center" cellspacing="0" bordercolor="#007ac9">
		<tr>
			<td>
				<table width="100%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
					<tr bgcolor="#3DB7E4" class="Estilo3" align="center">
					    <td><%= Constantes.TAB_ITEM %></td> 
						<td><%= Constantes.TAB_CONCLUSION %></td>
						<td><%= Constantes.TAB_ACC_SEGUI %></td>
						<td><%= Constantes.TAB_DIRIGIDO %></td>
						<TD><%= Constantes.TAB_CARGO %></TD>
						<TD><%= Constantes.TAB_DESCRIP %></TD>
						<TD></TD>
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
										  
					              
					              
					            &nbsp;&nbsp; <%= counter2 - ((Integer.parseInt(F1t.getPag()) - 1)  * 15) %></td>
					            			<td align="center"><%= v.get(1)!=null?v.get(1):"&nbsp;" %></td>
											<td align="center"><%= v.get(2)!=null?v.get(2):"" %></td>
											<td align="center"><%= v.get(6)!=null?v.get(6):"" %></td>
											<td align="center"><%= v.get(7)!=null?v.get(7):"" %></td>
											<td><textarea align="center"><%= v.get(3)!=null?v.get(3):"" %></textarea></td>
											<td>
											
											<% if(StringUtils.equals(F1t.getcmso(), Constantes.TIPO_SOLICITUD_RECLAMO) && String.valueOf(v.get(5)).equals(Constantes.RECLAMO_FUNDADO_NO_SOLUCIONADO)){ %>
											<input name="button" type="button" class="boton5" value="Acción de Persuación" 
											onclick="javascript:getHelp(document.f.AYEAR.value,document.f.CAS.value,document.f.CORREL.value,'<%=F1t.getcmso() %>','<%=v.get(5) %>','<%=v.get(0) %>');" ></td>
											<%} %>
										</tr>
						<%
								}  
						%>
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
