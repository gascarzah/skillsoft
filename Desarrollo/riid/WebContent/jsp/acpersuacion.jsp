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
   Ficha_t F1t = (Ficha_t)request.getAttribute("objPersua"); 
  String cmso = (String) session.getAttribute("cmso");
  String cconclu = (String) session.getAttribute("cconclu");
  String bestadoreg = (String) session.getAttribute("bestadoreg");
  
	String msg = (String)request.getAttribute("msg_error");
	Vector v2;
	Vector vCols2;
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
<script type="text/javascript">

function grabaPersuasion(tipoOperacion){
	
	  var cper = document.f.CPER.value;
	  var datpres = document.f.NOMPRESTADOR.value;
	  var fecpres = document.f.FECHARESP.value;
	  var cargo = document.f.CARGO.value;
	  var detalle = document.f.DETALLE.value;
	  document.f.TIPOOPERACION.value=tipoOperacion;
	  
	  if (cper == "" ) {
		  alert ("Debe seleccionar una Acción de Persuasión");
		  document.f.CPER.focus();
		  
	  }else if  (datpres == "" ) {
		  alert ("Debe ingresar el Nombre del Prestador");
		  document.f.NOMPRESTADOR.focus();
		  
      }else if  (fecpres == "" ) {
		  alert ("Debe ingresar la Fecha de Respuesta");
		  document.f.FECHARESP.focus();
		  
      }else if  (cargo == "" ) {
		  alert ("Debe ingresar el cargo");
		  document.f.CARGO.focus();
		  
      }else if  (detalle == "" ) {
		  alert ("Debe ingresar el Detalle");
		  document.f.DETALLE.focus();
	  
	  }else {
		  document.f.submit();
	  }
}

function actualiza(tipoOperacion){
	
	document.f.TIPOOPERACION.value=tipoOperacion;
	if(document.f.IDPERSUA.value == ""){
		alert("Debe de elegir un registro de la grilla");
	}else{
	   
		if(document.f.NOMPRESTADOR.value == "" ){
			alert("Campo Nombre del Prestador no puede estar vacio");
			event.returnValue=false;
			document.f.NOMPRESTADOR.focus();
		}else if(document.f.FECHARESP.value == ""){
			alert("Campo Fecha de Respuesta no puede estar vacio");
			event.returnValue=false;
			document.f.FECHARESP.focus();
		}else if(document.f.DETALLE.value == "" ){
			alert("Campo Detalle no puede estar vacio");
			event.returnValue=false;
			document.f.DETALLE.focus();
		}else if(document.f.CPER.value == ""){
			alert("Campo Accion realizada no puede estar vacio");
			event.returnValue=false;
			document.f.CPER.focus();
		}else{
			document.f.opt.value = '44';
			var ask = confirm ("Se modificara el registro, continuar?");
			
			if(ask){
			document.f.submit();
			}
		}
	}
}


function eliminar(tipoOperacion){
	
	if(document.f.IDPERSUA.value == ""){
		alert("Debe de elegir un registro de la grilla");
	}else{
	   
			document.f.opt.value = '45';
			document.f.ESTADO.value='0';
			document.f.TIPOOPERACION.value=tipoOperacion;
			
			var ask = confirm ("Se eliminará este registro, continuar?");
			
			if(ask){
			document.f.submit();
			}
		
	}
}

function eliminarcc(tipoOperacion){ 
	
	if(document.f.IDPERSUA.value == ""){
		alert("Debe de elegir un registro de la grilla");
	}else{
     
			document.f.ESTADO.value='0';
			document.f.TIPOOPERACION.value=tipoOperacion;
		
			w = opwinMS("../servlet/CtrlFicha?opt=45&IDPERSUA="+document.f.IDPERSUA.value+"&TIPOOPERACION="+tipoOperacion+"&FCON="+document.f.CMSO.value+"&CCON="+document.f.CCONCLU.value+"&IDCONCLU="+ document.f.IDCONCLU.value+"&ESTADO='0'",			
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

}

function getReg(nomper,fechper,detaper,estado,accper,idpersua,cargper){ 
	//console.info('----------------------'+estado);
	document.f.IDPERSUA.value = idpersua;
	document.f.NOMPRESTADOR.value = nomper;
	document.f.FECHARESP.value = fechper;
	document.f.CARGO.value = cargper;
	
	document.f.DETALLE.value = detaper;
	if(accper=='NO SOLUCIONADO'){
		accper='2';
	}else if(accper=='SIN RESPUESTA'){
		accper='3';
	}else{
		accper='1';
	}
	document.f.CPER.value = accper;
	//document.f.ESTADO.value = estado=='ACTIVO'?'1':'0';
}



function goPg(np){
	 
	  document.f.pg.value = np;
	  document.f.opt.value = 26;
	  document.f.submit();
	}


</script>
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<%
try {
%>
<body>
<form action="../servlet/CtrlFicha" method="post" name="f"  > 

<input type="hidden" name="opt" value="40"/>


<input type="hidden" name="CAS" value="<%=F1t.getcas()!=null?F1t.getcas():""%>" />
           <input type="hidden" name="AYEAR" value="<%=F1t.getayear()!=null?F1t.getayear():""%>" />
            <input type="hidden" name="CORREL" value="<%=F1t.getcorrel()!=null?F1t.getcorrel():""%>" />
            <input type="hidden" name="CMSO" value="<%=F1t.getcmso()!=null?F1t.getcmso():""%>" />
            <input type="hidden" name="CCONCLU" value="<%=F1t.getCconclu()!=null?F1t.getCconclu():""%>" /> 
            <input type="hidden" name="IDCONCLU" value="<%=F1t.getIdConclu()!=null?F1t.getIdConclu():""%>" /> 
            <input type="hidden" name="FCON" value="<%=F1t.getcmso()!=null?F1t.getcmso():""%>" />
            <input type="hidden" name="CCON" value="<%=F1t.getCconclu()!=null?F1t.getCconclu():""%>" /> 
            
   <br/>
  
  <table width="700" border="0" align="center" cellspacing="1">
		  
		<tr>
	          <td>&nbsp;</td>
	          <td nowrap ="nowrap" width="150px"><%= Constantes.ACCION_PERSUASION%><b>*</b></td>
	          <td colspan="2">
	            <select name="CPER" class="celdas01" style="width: 272px;" >
	              <option value="" selected="selected">SELECCIONA</option>
	              <%
							v2 =  (Vector) request.getAttribute ("vAccPersuas");
							if (v2 != null) {  
								for (int i = 0; i < v2.size(); i++) {  
									vCols2 = (Vector) v2.get(i);  
						%>
						<option value="<%=vCols2.get(0)%>" ><%= vCols2.get(1)%></option>
	              <%
								}
							}
						%>
	            </select></td>
	          <td>&nbsp;</td>
	          
	          <td nowrap ="nowrap"  width="150px"><%= Constantes.DATOS_PRESTADOR1%><b>*</b></td>
	          <td>
	              <input name="NOMPRESTADOR"  type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='FECHARESP';" size="50" maxlength="50" class="celdas01" />
	          </td>
         
        </tr>
        
        <tr><td colspan="7">&nbsp;</td></tr>
       
          <!--  <input type="hidden" name="ESTADO"/> -->
        <tr>
        
		     <td colspan="1" width="150px">&nbsp;</td>
		     <td nowrap ="nowrap" ><%= Constantes.FECHA_RESPUESTA%><b>*</b></td>
		     <td>
		          <input type="text" name="FECHARESP" id="sel3" size="12"  readonly="readonly" style="width: 231px; "><input type="reset" value=" ... " onclick="return showCalendar('sel3', '%d/%m/%Y');">
		          <br/> 
	         </td>
	         
	        <td>&nbsp;</td>
	        <td>&nbsp;</td>
	        <td nowrap ="nowrap"  width="150px"><%= Constantes.CARGO_PREST%><b>*</b></td>
	        <td> 
	            <input name="CARGO" type="text"  size="50" onkeypress="return soloLetras(event)" maxlength="50" class="celdas01" />
	        </td>
	       
        </tr>
        
        <tr><td colspan="7">&nbsp;</td></tr>
        
        <tr>
	       <td valign="top" nowrap ="nowrap" width="150px"><b></b></td>
	       <td colspan="2"> 
	            <%= Constantes.DETALLE %><b>*</b>
	            <textarea rows="3" cols="15" name="DETALLE" onchange="cambia(this);" onkeypress="corta6(this);"onkeyup="corta6(this);" style="height: 112px; width: 271px; "></textarea>
	            <input name="Cars4" type="text" class="celdas01" onfocus="blur();" value="200" size="3" maxlength="3" align="right"/>
	       </td>
	       <td colspan="4">&nbsp;</td>
        </tr>
        
        <tr><td width="20%"><%= Constantes.INFO_OBLI %>: </td></tr> 
       </table>
        
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
				<input name="button" type="button" class="boton4" value="Grabar" onclick="grabaPersuasion('G');" />
				<input type="button" class="boton4" value="Eliminar" onclick="eliminar('E');"/>
				<input type="button" class="boton4" value="Actualizar" onclick="actualiza('A');"/>
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

<% if(F1t.isEmpty()) {%>
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
					<input type="hidden" name="IDPERSUA"></input>
					<input type="hidden" name="ESTADO"/></input>
					<input type="hidden" name="TIPOOPERACION"/></input>
					<%
								int counter = 0;
								int counter2 = Integer.parseInt(F1t.getdtotal_reg()) +1;
								boolean bFil = true;
								for (Enumeration e = F1t.getHshLista().keys(); e.hasMoreElements();) {
									e.nextElement();
									v2 = (Vector)F1t.getHshLista().get("" + counter);
									bFil = bFil?false:true; 
									counter++;
									counter2--;
						%>
						
										<tr bgcolor="<%=bFil?"#EAEFF5":"#FFFFFF"%>" class="celdas01">
										  <td align="center">
										  
					              <a href="javascript:getReg('<%=v2.get(1)%>','<%=v2.get(3)%>','<%= v2.get(5)%>', '<%= v2.get(4)%>', '<%= v2.get(6)%>', '<%= v2.get(0)%>', '<%= v2.get(4)!=null? v2.get(4):""%>'  );"><img src="../images/fleder.jpg" alt="Seleccione para actualizar datos del registro" width="12" height="12" border="0" /></a>
					              
					            &nbsp;&nbsp; <%= counter2 - ((Integer.parseInt(F1t.getPag()) - 1)  * 15) %></td>
					            			<td align="center"><%= v2.get(6)!=null?v2.get(6):"&nbsp;" %></td>
											<td align="center"><%= v2.get(1)!=null?v2.get(1):"" %></td>
											<td align="center"><%= v2.get(3)!=null?v2.get(3):"" %></td>
											<td align="center"><%= v2.get(4)!=null?v2.get(4):"" %></td>
											<td><textarea  cols="20" rows="3"><%= v2.get(5)!=null?v2.get(5):"" %></textarea> </td>
											
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