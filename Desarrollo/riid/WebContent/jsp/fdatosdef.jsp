<%@page import="org.apache.commons.lang.StringUtils"%>

<%@page import="util.Constantes"%>
<%@ page session="true" %>
<%@ page import="java.util.*" %>
<%@ page import="beans.WebUtil" %>
<%@ page import="beans.Util" %>
<%@ page import="clases.Tablas_t" %>
<%@ page import="clases.ListFicha_t" %>
<%@ page import="clases.UsuarioSistema" %>
<%@ page import="java.util.GregorianCalendar"%>
<%@ include file="../jsp/formato_fecha.jsp" %>
<%@ page import="clases.Ficha_t" %>

<%
	UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario");
	Ficha_t objLst = (Ficha_t)request.getAttribute("objLst");
	//Ficha_t objLst2 = (Ficha_t)request.getAttribute("objLst2");
	//Ficha_t fichaOriginal = (Ficha_t)request.getAttribute("fichaOriginal");
	
	String msg = (String)request.getAttribute("msg_error");
	String opc = (String)request.getAttribute("opc");
	Vector v;
	Vector vCols;
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css"/>
<link href="../styles/botones.css" rel="stylesheet" media="print" type="text/css"/>

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
function locationServer1 (valor1, valor2) {
	  parent.bot.location.href='../jsp/botREC.jsp?tipo=' + valor1 + '&num=' + valor2;
	}

function revisar_pdf(idtdatdef) {
	  var ext = document.f.FICHERO.value;
	  //alert(ext.toString());
	  //alert(ext.length);
	 // var file = document.getElementById('FICHERO').files;
	  //alert(file.size);
	  ext = ext.substring(ext.length-3,ext.length);
	  ext = ext.toLowerCase();
	  
	  
	  if(ext != 'pdf'  &&  ext != 'jpg' ) {
	    alert('Seleccione un archivo PDF o JPG, no un archivo '+ext+'.');
	    document.f.FICHERO.value='';
	    event.returnValue=false;
	  }else{
	  	document.f.enctype='multipart/form-data';
	  	document.f.encoding='multipart/form-data';
	  	document.f.opt.value = '37';
	  	document.f.ext.value = ext;
	  	//document.f.IDGESTION.value=idtdatdef;
	  	
		var ask = confirm ("Se guardara el archivo, continuar?");
		
		if(ask){
		document.f.submit();
		}
	  }
}

function getVisualizarArchAdjuntos(ayear,cas,correl,idtdatdef) { 
	w = opwinMS("../servlet/CtrlFicha?opt=41&AYEAR="+ayear+"&CAS="+cas+"&CORREL="+correl+"&IDTDATDEF="+idtdatdef,			
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

function visualiza() {
	  //document.f.DAPEPATREC.value = "";
	 // document.f.DAPEMATREC.value = "";
	 // document.f.DNOMREC.value = "";
	 
	 
	  
	    // if(ext != 'pdf'  &&  ext != 'jpg' ) {
		 //   alert('Seleccione un archivo PDF o JPG, no un archivo '+ext+'.');
		 //   document.f.FICHERO.value='';
		 //   event.returnValue=false;
		 // }else{
		  	
		  	document.f.opt.value = '41';
		  //	document.f.ext.value = ext;
		  
			//var ask = confirm ("Se guardara el archivo, continuar?");
			
			//if(ask){
			document.f.submit();
			//}
		 // }
}



function grabar(){
	   
	if(document.f.CQUIEN.value == "" ){
		alert("Campo Dirigido a no puede estar vacio");
		event.returnValue=false;
		document.f.CQUIEN.focus();
	}else if(document.f.FECHAQ.value == ""){
		alert("Campo fecha no puede estar vacio");
		event.returnValue=false;
		document.f.FECHAQ.focus();
	}
	//else if(document.f.DCARGO.value == ""){
	//	alert("Campo Cargo no puede estar vacio");
	//	event.returnValue=false;
	//	document.f.FECHAQ.focus();
	//}
	else if(document.f.DFICOBS.value == "" ){
		alert("Campo Gesti�n no puede estar vacio");
		event.returnValue=false;
		document.f.DFICOBS.focus();
	}else if(document.f.CACCREAL.value == ""){
		alert("Campo Accion realizada no puede estar vacio");
		event.returnValue=false;
		document.f.CACCREAL.focus();
	} 
	else{
		document.f.opt.value = '31';
		document.f.FICHERO.value='';
		var ask = confirm ("Se guardara un nuevo registro, continuar?");
		
		if(ask){
		document.f.submit();
		}
	}
}

function actualiza(){
	
	if(document.f.IDDEF.value == ""){
		alert("Debe de elegir un registro de la grilla");
	}else{
	   
		if(document.f.CQUIEN.value == "" ){
			alert("Campo quien no puede estar vacio");
			event.returnValue=false;
			document.f.CQUIEN.focus();
		}else if(document.f.FECHAQ.value == ""){
			alert("Campo fecha no puede estar vacio");
			event.returnValue=false;
			document.f.FECHAQ.focus();
		}else if(document.f.DFICOBS.value == "" ){
			alert("Campo Gestor no puede estar vacio");
			event.returnValue=false;
			document.f.DFICOBS.focus();
		}else if(document.f.CACCREAL.value == ""){
			alert("Campo Accion realizada no puede estar vacio");
			event.returnValue=false;
			document.f.CACCREAL.focus();
		}else{
			document.f.opt.value = '32';
			document.f.FICHERO.value='';
			var ask = confirm ("Se modificara el registro, continuar?");
			
			if(ask){
			document.f.submit();
			}
		}
	}
}

function eliminar(){
	
	if(document.f.IDDEF.value == ""){
		alert("Debe de elegir un registro de la grilla");
	}else{
	   
			document.f.opt.value = '32';
			document.f.FICHERO.value='';
			document.f.ESTADO.value='0';
			document.f.accion.value = 'E';
			var ask = confirm ("Se eliminar� este registro, continuar?");
			
			if(ask){
			document.f.submit();
			}
		
	}
}

function getSalir(){
	var caso = document.f.Salir.value;
	if (caso == 'CANCELAR'){
		document.f.graba.value = "ADICIONAR";
		document.f.graba.title = " Ingresar Nuevo Cat�logo ";
		document.f.Salir.value = " SALIR ";
		eval('document.f.CODIGO.readOnly=false');
		document.f.CODIGO.style.backgroundColor="#FFFFFF";
		inicio();
	}
	else{
		location.href='../servlet/CtrlFicha?opt=0';
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

	function getReg(cquien,fechaq,gestor,daccreal,estado,caccreal,idtdatdef,dcargo){ 
		
		document.f.IDDEF.value = idtdatdef;
		document.f.CQUIEN.value = cquien;
		document.f.FECHAQ.value = fechaq;
		document.f.DCARGO.value = dcargo;
		
		document.f.DFICOBS.value = gestor;
		document.f.CACCREAL.value = caccreal;
		document.f.ESTADO.value = estado=='ACTIVO'?'1':'0';
	}

	function goPg(np){
		 
		  document.f.pg.value = np;
		  document.f.opt.value = 25;
		  document.f.submit();
	}  
	
</script>
<script src="../Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>

<body class="body"  bgproperties="fixed">

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
    <td align="center" class="titulob2">REGISTRO INFORM�TICO DE ATENCI�N AL ASEGURADO</td>
  </tr>
  <form name="f" method="post" action="../servlet/CtrlFicha" >
  <input type="hidden" name="accion" />
  <tr align="center">
    <td width="220" align="center" class="celdasb"><%= clusuario.getDescripcion() %></td>
    <td rowspan="3" class="titulo4">
      	<table width="720" align="center">
        	<tr>
        	<% if (!clusuario.getPerfil().equals(Constantes.ESSALUD_EN_LINEA)){%>
            	<td><a href="javascript:toDatosG();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('b1','','../images/uno.jpg',1)"><img src="../images/uno0.jpg" name="b1" width="160" height="50" border="0" id="b1" /></a></td>
                <td><a href="javascript:toDatosAd();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('b2','','../images/dos0.jpg',1)"><img src="../images/dos.jpg" name="b2" width="160" height="50" border="0" id="b2" /></a></td>
                <td><img src="../images/tres0.jpg"/ width="160" height="50"></td>
                <td><a href="javascript:toDatosSeg();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('b4','','../images/cuatro0.jpg',1)"><img src="../images/cuatro.jpg" name="b4" width="160" height="50" border="0" id="b4" /></a></td>
                <%} %>
          </tr>
        </table>
      </td>
  </tr>
  <tr align="center">
    <td align="center" class="celdasb"><%= clusuario.getdPerfil() %></td>
    <td>&nbsp;</td>
  </tr>
  <tr align="center">
    <td align="center" class="celdasb"><%= clusuario.getdCas() %></td>
    <td>&nbsp;</td>
  </tr>
  
</table>
<table width="100%">
    <tr><td align="center" class="titulo_gerencia" >Gerencia Central de Atenci�n al Asegurado</td></tr>
  </table>
<table align="center" width="100%">
	  <tr>
		    <td align="left" class="titulo3" width="33%"><%= Constantes.NOM_DELEGADO %> <%=StringUtils.defaultString(objLst.getDescDelegado())%></td>
          <td align="center" class="titulo3" width="34%"><%= Constantes.OFICINA %> <%= StringUtils.defaultString(objLst.getDescRedDelegado())%></td>
		    <td colspan="1" align="right" class="titulo3"><%= Constantes.NRO_EXPE %> <%=objLst.getayear()!=null?objLst.getayear():""%>-<%=objLst.getcas()!=null?objLst.getcas():""%>-<%=objLst.getcorrel()!=null?objLst.getcorrel():""%>&nbsp;&nbsp;</td>
	  </tr>
	   <tr>
          <td align="left" class="titulo3" width="33%"></td>
          <td align="center" class="titulo3" width="34%"></td>
          <td align="right" class="titulo3" width="33%"><%=StringUtils.isNotEmpty(objLst.getfusucrea())?Constantes.FECHA_REGISTRO:"" %> <%= StringUtils.defaultString(objLst.getfusucrea()) %> </td>
     </tr>
     <tr>
          <td align="left" class="titulo3" width="33%"></td>
          <td align="center" class="titulo3" width="34%"></td>
          <td align="right" class="titulo3" width="33%"><%= StringUtils.isNotEmpty(objLst.getfusumodi())?Constantes.FECHA_ULTIMA:"" %> <%= StringUtils.defaultString(objLst.getfusumodi()) %> </td>
     </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" class="titulo1"></td>
  </tr>
</table>


		<input type="hidden" name="CAS" value='<%=objLst.getcas()!=null?objLst.getcas():""%>' />
        <input type="hidden" name="AYEAR" value='<%=objLst.getayear()!=null?objLst.getayear():""%>' />
        <input type="hidden" name="CORREL" value='<%=objLst.getcorrel()!=null?objLst.getcorrel():""%>' />
       
       
        <input type="hidden" name="opt" />
        <input type="hidden" name="ext" />
        <input type="hidden" name="IDGESTION" />
        
	<table width="700" border="0" align="center" cellspacing="1">
		  
		<tr>
          <td>&nbsp;</td>
          <td nowrap ="nowrap" width="150px"><%= Constantes.ACCIONES_REA %><b>*</b></td>
          <td colspan="2">
            <select name="CACCREAL" class="celdas01" style="width: 272px;" >
              <option value="" selected="selected">SELECCIONA</option>
              <%
						v =  (Vector) request.getAttribute ("vAccReal");
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
          <td></td>
          
          <td>&nbsp;</td>
          <td nowrap ="nowrap"  width="150px"><%= Constantes.DIRIGIDO_A %><b>*</b></td>
          <td>
              <input name="CQUIEN" onchange="cambia(this); " type="text" onkeypress="return soloLetras(event)" onfocus="nextfield ='FECHAQ';" size="50" maxlength="50" class="celdas01" />
          </td>
          
          
        </tr>
        <tr><td>&nbsp;</td></tr>
        <input type="hidden" name="ESTADO"/>
        <tr>
        
        <td colspan="1" width="150px">&nbsp;</td>
          <td nowrap ="nowrap" ><%= Constantes.FECHA%><b>*</b></td>
          <td colspan="2">
          <input type="text" name="FECHAQ" id="sel3" size="12"  readonly="readonly" style="width: 231px; "><input type="reset" value=" ... " onclick="return showCalendar('sel3', '%d/%m/%Y');"> 
<br />
          </td>
          </b>
        
        <td>&nbsp;</td>
        <td></td>
        <!--  -->
        <td nowrap ="nowrap"  width="150px"><%= Constantes.CARGO %></td>
          <td> 
            <input name="DCARGO" type="text" onchange="cambia(this);" size="50" onkeypress="return soloLetras(event)" maxlength="50" class="celdas01" />
          </td>
          <!--  -->
          <td>&nbsp;</td>
        </tr>
        <tr><td>&nbsp;</td></tr>
        <tr>
        <td></td>
        <td valign="top" nowrap ="nowrap" width="150px"><%= Constantes.GESTION %><b>*</b></td>
          <td colspan="2"> 
            <textarea rows="3" cols="15" name="DFICOBS" onchange="cambia(this);" onkeypress="corta4(this);"onkeyup="corta4(this);" style="height: 112px; width: 271px; "></textarea><br />
            <input name="Cars4" type="text" class="celdas01" onfocus="blur();" value="200" size="3" maxlength="3" align="right"/>
          </td>
          <td></td>
          
          <td>&nbsp;</td>
          <td valign="top" nowrap ="nowrap"  width="150px"><%= Constantes.DOC_RELAC %></td>
            <td id="resetf" valign="top"><input type="file" name="FICHERO" id="FICHERO" onchange="revisar_pdf();" /><br />&nbsp;Archivo PDF o JPG, tama�o max. 1MB.
           <br/>
											
											<input name="button" type="button" class="boton5" value="Ver Adjuntos" 
											onclick="javascript:getVisualizarArchAdjuntos(document.f.AYEAR.value,document.f.CAS.value,document.f.CORREL.value,'<%=v.get(6) %>');" >
											
            
            </td>
            
        </tr>
       
        
			</table>
			<br/>
			
			<table width="200" border="0" align="center" cellspacing="0">
			<tr></tr>
			<tr>
			<td>&nbsp;</td>
		<%if(objLst.getbestadoreg()!=null && !objLst.getbestadoreg().equals(Constantes.ESTADO_INACTIVO) 
			&& (clusuario.getPerfil().equals(Constantes.PERFIL_DELEGADO_DEFENSORIAL) || clusuario.getPerfil().equals(Constantes.PERFIL_COORDINADOR) )) {%>
				
				<td align="left"><input type="button" class="boton4" value="GRABAR" onclick="grabar();"/></td>
				<td>
				<input type="button" class="boton4" value="ELIMINAR" onclick="eliminar();"/>
				</td>
				
			    <td>
				    <input type="button" class="boton4" value="ACTUALIZAR" onclick="actualiza();"/>
				</td>
			    
			<%} %>
			
			<% if(clusuario.getPerfil().equals(Constantes.PERFIL_COORDINADOR)   && objLst.getbestadoreg().equals(Constantes.ESTADO_INACTIVO )) {%>
			    <td align="left"><input type="button" class="boton4" value="GRABAR" onclick="grabar();"/></td>
				<td>
				<input type="button" class="boton4" value="ELIMINAR" onclick="eliminar();"/>
				</td>
				
			    <td>
				    <input type="button" class="boton4" value="ACTUALIZAR" onclick="actualiza();"/>
				</td>
			
			<%} %>
			
			
			<td>
			<input name="Button3" type="button" class="boton4" value=" Imprimir " onclick="window.print();"/>
			</td>
			<td>&nbsp;</td>
			</tr>
			<tr>
			<td>
				<input type="hidden" name="pg" value="<%= objLst!=null?objLst.getPag():""%>"/>
				
			</td>
			
			</tr>
			</table>
			<table width="700" border="0" align="center" cellspacing="0">
			<tr>
			<td align="right" width="25%"><%  if ( objLst!=null && objLst.getPaginacion().getPaginaAnt()) { %>
				<input name="button" value="  <<  " type="button" class="boton4" onClick="goPg(<%= Integer.parseInt(objLst.getPag()) - 1%>);" title="Retroceder a pagina anterior"/>
					<%  } 
				 if (objLst!=null && objLst.getPaginacion().getPaginaSgte()) { %> 
				<input name="button" value="  >>  " type="button" class="boton4" onClick="goPg(<%= Integer.parseInt(objLst.getPag()) + 1%>);" title="Avanzar a pagina siguiente"/>
					 <%  } %>
			</td>
		</tr>
	</table>
	<br />
	<table align="center">
	<tr>
		<td>&nbsp;</td>
          <td colspan="3" height="30px"><label style="font-weight: bold;font-size: 15px;">Lista de Acciones </label>  </td>
          <td>&nbsp;</td>
	</tr>
	</table>
	<table width="1000px"  border="1" align="center" cellspacing="0" bordercolor="#007ac9"/>
		<tr>
			<td>
				<table width="100%" border="1" align="center" cellspacing="0" bordercolor="#DFE6EE" bgcolor="#FFFFFF"/>
					<tr bgcolor="#3DB7E4" class="Estilo3" align="center">
					    <td><%= Constantes.TAB_ITEM %></td> 
						<td><%= Constantes.TAB_ACC_REALIZADAS %></td>
						<td><%= Constantes.TAB_DIRIGIDO %></td>
						<td><%= Constantes.TAB_CARGO %></td>
						<td><%= Constantes.TAB_FECHA %></td>
						<td><%= Constantes.TAB_GESTION %></td>
						<%-- <td><%= Constantes.TAB_SUBIR_ADJUNTOS %></td> --%>
						<%-- <td><%= Constantes.TAB_ARCHIVOS_ADJUNTOS %></td> --%>
					</tr>
					<input type="hidden" name="IDDEF"/>
					<%
								//int counter2 = 1;							
								boolean bFil = true;
								int counter2 = Integer.parseInt(objLst.getdtotal_reg()) +1;
								int counter = 0;
								for (Enumeration e = objLst.getHshLista().keys(); e.hasMoreElements();) {
									e.nextElement();
									
									v = (Vector)objLst.getHshLista().get("" + counter);
									bFil = bFil?false:true; 
									counter++;
									counter2--;
						%>
										<tr bgcolor="<%=bFil?"#EAEFF5":"#FFFFFF"%>" class="celdas01">
										    <td align="center" width="30px">
										    <% if (clusuario.getPerfil().equals(Constantes.PERFIL_COORDINADOR) || clusuario.getPerfil().equals(Constantes.PERFIL_ADMINISTRADOR)|| clusuario.getPerfil().equals(Constantes.PERFIL_DELEGADO_DEFENSORIAL)){ %>
					              <a href="javascript:getReg('<%=v.get(0)%>','<%=v.get(1)%>','<%=v.get(2)%>','<%=v.get(3)%>','<%=v.get(4)%>','<%=v.get(5)%>','<%=v.get(6)%>','<%= v.get(7)!=null? v.get(7):""%>');"><img src="../images/fleder.jpg" alt="Seleccione para actualizar datos del registro" width="12" height="12" align="left" border="0" /></a>
					              <% } %><font style="align:right;"><%=  counter2 - ((Integer.parseInt(objLst.getPag()) - 1)  * 15) %></font></td>
					            			<td align="center"><%= v.get(3)!=null?v.get(3):"&nbsp;" %></td>
											<td align="center"><%= v.get(0)!=null?v.get(0):"" %></td>
											<td align="center"><%= v.get(7)!=null?v.get(7):"" %></td>
											<td><%= v.get(1)!=null?v.get(1):"" %></td>
											<td align="center" ><textarea rows="4" cols="45" readonly="readonly" ><%= v.get(2)!=null?v.get(2):"&nbsp;" %></textarea></td>
											
											
										</tr>
						<%			
								}  
						%>

				</table>
			</td>
		</tr>
	</table>
	
	
	
	
</form>


<table width="70%" border="0" align="center" class="titulo6">
  <tr align="center">
    <td><% if(msg != null){ %><%= msg %><% } %>&nbsp;</td>
  </tr>
</table>
</body>
<% 
	request.removeAttribute("objLst");
%>





</html>
