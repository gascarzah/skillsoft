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
   Ficha_t F1t = (Ficha_t)request.getAttribute("objUpload"); 
 // String cmso = (String) session.getAttribute("cmso");
 // String cconclu = (String) session.getAttribute("cconclu");
 // String bestadoreg = (String) session.getAttribute("bestadoreg");
  
	String msg = (String)request.getAttribute("msg_error");
	
	 // String grabo = (String)request.getAttribute("grabo");
	Vector vi;
	Vector v3;
	Vector vCols3;
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




function goPg(np){
	 
	  document.f.pg.value = np;
	  document.f.opt.value = 41;
	  document.f.submit();
	}
function getReg(){ 

}

function showImagexx(listaFicheros, nomImagen){
	var msgForNormal = '';
	var valor= '';
	for (var i=0; i<listaFicheros.length; i++) { 
		//if(listaFicheros[i])
		
		//valor= 	listaFicheros[i];break;
		
	 
		//alert(nomImagen);
		msgForNormal = msgForNormal + listaFicheros[i] ; }
	//alert(valor);
	//alert ('msgForNormal contiene ' + msgForNormal );
	valor ='/riid/archivosCargados/'+nomImagen;
	 return  valor;
}


function showImage(ayear,cas,correl,idarchivo,nomArchivo){
	
	w = opwinMS("../servlet/CtrlFicha?opt=46&AYEAR="+ayear+"&CAS="+cas+"&CORREL="+correl+"&IDARCHIVO="+idarchivo+"&DFNOMB="+nomArchivo,			
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


function getFile(ayear,cas,correl,idarchivo,nomArchivo){ 
   

		w = opwinMS("../servlet/CtrlFicha?opt=42&AYEAR="+ayear+"&CAS="+cas+"&CORREL="+correl+"&IDARCHIVO="+idarchivo+"&DFNOMB="+nomArchivo,			
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

function eliminarReg(idarchivo){ 
	   
	if(idarchivo == ""){
		alert("Debe de elegir un registro de la grilla");
	}else{
			
		var ask = confirm ("Se eliminará este registro, continuar?");
		
		if(ask){
		
			
			     w = opwinMS("../servlet/CtrlFicha?opt=43&IDARCHIVO="+idarchivo,			
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

}


</script>
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<%
try {
%>
<body>
<form action="../servlet/CtrlFicha" method="post" name="f"  > 

<input type="hidden" name="opt" value="41"/>


<input type="hidden" name="CAS" value="<%=F1t.getcas()!=null?F1t.getcas():""%>" />
           <input type="hidden" name="AYEAR" value="<%=F1t.getayear()!=null?F1t.getayear():""%>" />
            <input type="hidden" name="CORREL" value="<%=F1t.getcorrel()!=null?F1t.getcorrel():""%>" />
            <input type="hidden" name="IDTDATDEF" value="<%=F1t.getCconclu()!=null?F1t.getIdtdatdef():""%>" /> 
           
   <br/>
  

  <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      
      <td>&nbsp;</td>
    </tr>
    
    <tr>
      <td colspan="3" align="center">
				
				<input name="Button3" type="button" class="boton4" value="Imprimir" onclick="window.print();"/>
				<input name="Button3" type="button" class="boton4" value="Cerrar" onclick="window.close();"/>
      </td>
    </tr>
    
  </table>
  
  			
  
<br/>



   <table width="700"  border="1" align="center" cellspacing="0" bordercolor="#007ac9">
		<tr>
			<td>
				<table width="100%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF">
					<tr bgcolor="#3DB7E4" class="Estilo3" align="center">
					    <td><%= Constantes.TAB_ITEM_ARCHIVO %></td> 
						<td><%= Constantes.ARCH_NOMBRE %></td>
						<td><%= Constantes.ARCH_ACCION %></td>
					</tr>
					<input type="hidden" name="IDDEF"/>
					<%			String value="";
					           // String valor="";
					            
								int counter = 0;
								int counter2 = Integer.parseInt(F1t.getdtotal_reg()) +1;
								boolean bFil = true;
								for (Enumeration e = F1t.getHshLista().keys(); e.hasMoreElements();) {
									e.nextElement();
									v3 = (Vector)F1t.getHshLista().get("" + counter);
									bFil = bFil?false:true; 
									counter++;
									counter2--;
									
						%>
						
										<tr bgcolor="<%=bFil?"#EAEFF5":"#FFFFFF"%>" class="celdas01">
										  <td align="center">
					            &nbsp;&nbsp; <%= counter2 - ((Integer.parseInt(F1t.getPag()) - 1)  * 15) %></td>
					            			<td align="center">
					            			   <% if(F1t.getfichero()!=""){ 
					            				   List v= new ArrayList();
					            				   Iterator it=F1t.getLstFichero().iterator();
					                               while(it.hasNext())
					                               {
					                                  value=(String)it.next();
					                                  if(value.endsWith(v3.get(3).toString())){  System.out.println("Value*********** coincidio");   
					                                  		//v.add(Integer.parseInt(v3.get(3).toString()), value);
					                                  		break;
					                                  }
					                               }
					            			   %>
					            			  
					            			         <a href="<%= F1t.getfichero()+v3.get(3).toString() %>" target="_new" style="color: blue; font-weight: bold;"><%= v3.get(3)!=null?v3.get(3):"&nbsp;" %></a>
					            			       <% } %>
					            			    </td>
											<td align="center"> <a href="javascript:eliminarReg('<%= v3.get(8)%>');"><img src="../images/cerrar.jpg" alt="Seleccione para eliminar datos del registro" width="12" height="12" border="0" /></a></td>
										</tr>
						<%}%>
				</table>
				 
				</td>
			
				</tr>
				
				</table>
				
	                     
</form>


</body>

<%
  session.removeAttribute("obj");
  } 
  catch (NullPointerException e) {
		System.out.println("NullPointerException[archivoupload.jsp]: " + e);
    request.setAttribute("msg_error", "Error al mostrase la pagina: " + e); 
    WebUtil.goForward(getServletContext(), request, response, "/error/error.jsp"); 
  }
	finally{
	  F1t = null;
	}
%>
</html>