<%@page import="util.Constantes"%>
<%@ page session="true"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.WebUtil"%>
<%@ page import="beans.Util"%>
<%@ page import="clases.Ficha_t"%>
<%@ page import="clases.ListFicha_t"%>
<%@ page import="clases.UsuarioSistema"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.util.Calendar"%>
<%@ include file="../jsp/formato_fecha.jsp"%>
<%
	UsuarioSistema clusuario = (UsuarioSistema) session
			.getAttribute("sClusuario");
	Ficha_t objLst = (Ficha_t) request.getAttribute("objLst");
	String msg = (String) request.getAttribute("msg_error");
	Vector v;
	Vector vCols;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>EsSalud - Registro Inform&aacute;tico de Atenci&oacute;n al Asegurado</title>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css" />
<link href="../styles/botones.css" rel="stylesheet" media="print"
	type="text/css" />
<script language="JavaScript" src="../js/ubigeo.js"></script>
<script language="JavaScript" src="../js/script2.js"></script>
<script language="javascript" src="../js/format_date.js"></script>
<script language="javascript" src="../js/stm31.js"></script>
<script language="javascript" src="../js/val_campos.js"></script>

<!--  ini CALENDARIO -->
<link rel="stylesheet" type="text/css" media="all"
	href="../styles/skins/aqua/theme.css" title="Aqua" />
<link rel="alternate stylesheet" type="text/css" media="all"
	href="../styles/calendar-blue.css" title="winter" />
<script type="text/javascript" src="../js/calendar.js"></script>
<script type="text/javascript" src="../js/calendario2.js"></script>
<script type="text/javascript" src="../js/calendar-es.js"></script>
<script language="javascript" src="../js/val_campos.js"></script>

<script type="text/javascript">
setActiveStyleSheet(document.getElementById("defaultTheme"), "Aqua");
</script>
<!--  Fin CALENDARIO -->

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
<body class="body">
	<table class="banner01">
		<tr>
			<td width="220" rowspan="4" align="center"><img
				src="../images/es03.jpg" width="120" height="100" /></td>
			<td align="center"><%@include file="../js/menu_all.js"%></td>
			<td width="220" rowspan="7" align="right"><img
				src="../images/defensoria01.jpg" width="120" height="100" /></td>
		</tr>
		<tr>
			<td align="center" class="titulob1">&nbsp;</td>
		</tr>
		<tr align="center">
			<td>&nbsp;</td>
		</tr>
		<tr align="center">
			<td align="center" class="titulob2">REGISTRO  INFORMÁTICO DE ATENCIÓN AL ASEGURADO</td>
		</tr>
		<tr align="center">
			<td align="center" class="celdasb"><%=clusuario.getDescripcion()%></td>
			<td rowspan="3" class="titulo4">&nbsp;</td>
		</tr>
		<tr align="center">
			<td align="center" class="celdasb"><%=clusuario.getdPerfil()%></td>
		</tr>
		<tr align="center">
			<td align="center" class="celdasb"><%=clusuario.getdCas()%></td>
		</tr>
	</table>
	<form method="post" name="f" action="../servlet/CtrlFicha">
		<table width="70%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td align="center" class="titulo1">HISTORIAL DE ASIGNACIONES</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><table width="100%" border="1" cellpadding="0"
						cellspacing="0" bordercolor="#6699CC" bgcolor="#FFFFFF">
						<tr>
							<td scope="col"><table width="100%" class="celdas01">
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td colspan="2">&nbsp;</td>
									</tr>

									<tr>
										<td width="6%" height="14">&nbsp;</td>
										<td width="27%">&nbsp;</td>
										<td width="37%">&nbsp;</td>
										<td width="30%">&nbsp;</td>
									</tr>

									<tr>
										<td colspan="4">&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td style="text-transform: uppercase;"><%=Constantes.NRO_EXPE%></td>
										<td colspan="2">A&ntilde;o: 
										<select name="AYEAR"
											class="celdas01">
												<option value="" selected="selected">SELECCIONA</option>
												<option value="2015" >2015</option>
												<option value="2014" >2014</option>
												
										</select> &nbsp;&nbsp;Cas: 
										<input name="CAS" type="text" onkeypress="return soloNumeros(event)" class="celdas01"
											value="<%=(String) request.getParameter("CAS") == null ? "": request.getParameter("CAS")%>"
											onfocus="nextfield ='CORREL';" size="10" maxlength="10" />
											
											&nbsp;&nbsp;Correlativo: 
										<input name="CORREL" type="text" onkeypress="return soloNumeros(event)" class="celdas01"
											value="<%=(String) request.getParameter("CORREL") == null ? "": request.getParameter("CORREL")%>"
											onfocus="nextfield ='DFICDID';" size="10" maxlength="10" onchange="veridigitos(this);" /></td>
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
		<table width="75%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center">&nbsp; <input type="hidden" name="opt" value="10"> <input type="hidden" name="pg" value="1"></td>
				
			</tr>
			<tr>
				<td align="center"><input name="Submit" type="submit"
					class="boton4" value="Buscar"></td>
			</tr>
		</table>
	</form>
	<%
		if (objLst != null) {
	%>
	<form name="f1" method="post">
		<input type="hidden" name="pg" value="<%=objLst.getPag()%>">
			<table width="85%" border="0" align="center" cellspacing="0">
				<tr class="celdas01">
					<td width="25%">&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right" width="25%">
						<%
							if (objLst.getPaginacion().getPaginaAnt()) {
						%> <input
						name="button" value="  <<  " type="button" class="boton4"
						onClick="goPg(<%=Integer.parseInt(objLst.getPag()) - 1%>);"
						title="Retroceder a pagina anterior"> <%
 	}
 		if (objLst.getPaginacion().getPaginaSgte()) {
 %> <input
							name="button" value="  >>  " type="button" class="boton4"
							onClick="goPg(<%=Integer.parseInt(objLst.getPag()) + 1%>);"
							title="Avanzar a pagina siguiente"> <%
 	}
 %>
					</td>
				</tr>
			</table>
			<table width="85%" border="1" align="center" cellspacing="0"
				bordercolor="#6699CC">
				<tr>
					<td>
						<table width="100%" border="1" align="center" cellspacing="0"
							bordercolor="#DFE6EE" bgcolor="#FFFFFF">
							<tr bgcolor="#B0C4DE" class="celdas01" align="center">
								<td  bgcolor="#3DB7E4" class="Estilo3"><%=Constantes.TAB_ITEM%></td>
								<td bgcolor="#3DB7E4" class="Estilo3">CODIGO</td>
								<td bgcolor="#3DB7E4" class="Estilo3">USUARIO ASIGNADO</td>
								<td bgcolor="#3DB7E4" class="Estilo3">FECHA ASIGNADA</td>
								
							</tr>
							<%
								int counter = 0;
									boolean bFil = true;
									for (Enumeration e = objLst.getHshLista().keys(); e
											.hasMoreElements();) {
										e.nextElement();
										v = (Vector) objLst.getHshLista().get("" + counter);
										bFil = bFil ? false : true;
										counter++;
							%>
							<tr bgcolor="<%=bFil ? "#EAEFF5" : "#FFFFFF"%>" class="celdas">
								
								<td align="center"><%=counter
							+ ((Integer.parseInt(objLst.getPag()) - 1) * 15)%></td>
								<td align="center"><%=v.get(0) != null ? v.get(0) : ""%>-<%=v.get(1) != null ? v.get(1) : ""%>-<%=v.get(2) != null ? v.get(2) : ""%></td>
								<td align="center"><%=v.get(3) != null ? v.get(3) : ""%></td>
								<td align="center"><%=v.get(4) != null ? v.get(4) : ""%></td>
								
							</tr>
							<%
								}
							%>
						</table>
					</td>
				</tr>
			</table>
	</form>
	<%
		} else {
			if (msg != null)
				out.print("<center><span class=\"titulo4\" align=\"center\">"
						+ msg + "</span></center>");
		}
	%>

</body>
<%
	request.removeAttribute("objLst");
%>
</html>
