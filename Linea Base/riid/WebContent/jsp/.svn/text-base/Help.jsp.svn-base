<%@ page session="true" %>
<%@ page import="java.util.*" %>
<%//@ page import="clases.Listhelp_t" %>
<%@ page import="clases.Listhelp_t" %>
<HTML>
<head>
<title>EsSalud - Defensoria del Asegurado</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="../styles/celdas.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/script2.js"></script>
<SCRIPT LANGUAGE="javascript">
<% 
    Listhelp_t objHelp = (Listhelp_t)request.getAttribute("objHelp");
    if (objHelp == null) {
	   return;
	} %>
	
   function celda(y,o)  {
   if (o=='e' ) y.children["dest"].color='#e00a00';
   if (o=='s' ) y.children["dest"].color='#0000CC';
   
   }

   function passBack(x,y)  {
   var codigo="";
   var desc ="";
   if (y.id == "campo") {
       desc=y.children["dest"].innerText;
       codigo =x;
   }
   opener.document.<%=(String)objHelp.getForm()%>.<%=(String)objHelp.getObj1()%>.value=codigo;
   <%  if (objHelp.getCond1().compareTo("") !=0) { // objecto 2 %>
      opener.document.<%=(String)objHelp.getForm()%>.<%=objHelp.getCond1()%>.value=desc;
   <% } 
      if (objHelp.getCond2().compareTo("")!=0) {  // focus %>
      opener.document.<%=(String)objHelp.getForm()%>.<%=(String)objHelp.getCond2()%>.focus();
   <%  } else { %>
      opener.document.<%=(String)objHelp.getForm()%>.<%=(String)objHelp.getObj1()%>.focus();
   <%  }  
	 if(objHelp.getCond4().compareTo("")!=0) { // funcion %>
	  opener.<%=objHelp.getCond4()%>;
<%  } %>
		close();
  }

  function Buscar(x) {
    document.frmSearch.pg.value = x;
    cambia(document.frmSearch.ww);
    document.frmSearch.submit();
  }

  function BuscarAntSgte(x,w) {
    document.frmSearch.ww.value = w;
    document.frmSearch.pg.value = x;
    document.frmSearch.submit();
  }
</SCRIPT>
</head>
<body bgcolor="#FFFFFF" leftMargin="10" rightMargin="10" topMargin="10" bottomMargin="10" onLoad="document.frmSearch.ww.focus();">
  <form name="frmSearch" action="../servlet/CtrlHelp" method="get">
    <input type="hidden" name="pg"  value="<%= Integer.parseInt(objHelp.getPag()!=null?objHelp.getPag():"1") %>">
    <input type="hidden" name="ta"  value="<%=objHelp.getTabla()%>">
    <input type="hidden" name="ff"  value="<%=objHelp.getForm()%>">
    <input type="hidden" name="ob1" value="<%=objHelp.getObj1()%>">
    <input type="hidden" name="ob2" value="<%=objHelp.getCond1()%>">
    <input type="hidden" name="fo"  value="<%=objHelp.getCond2()%>">
    <input type="hidden" name="pp"  value="<%=objHelp.getCond3()%>">
    <input type="hidden" name="fu"  value="<%=objHelp.getCond4()%>">
    <input type="hidden" name="se"  value="<%=objHelp.getSede()%>">
    <table cellpadding="3" cellspacing="2" border="0" width="250" align="center">
	  <tr>
		<td align="center">
		  <input name="ww" type="text" class="celdas01" onChange="cambia(this);" value="<%=(String)objHelp.getCond5()%>">
		  <input type="button" class="boton03" onClick="javascript:Buscar(1);" value="Buscar"> 
		  <input type="button" class="boton03" onClick="javascript:window.close();" value="Cerrar"> 
		</td>
	  </tr>
    </table>
<%
    //Hashtable hshLista = (Hashtable)objHelp.getHshLista();   
    if (objHelp.getHshLista() == null || objHelp.getHshLista().isEmpty()) {  %>
      <br><br><br>
      <table border="1" align="center" cellpadding="3" cellspacing="2" class="titulo2">
      <tr>
        <td>&nbsp; No se encontraron coincidencias.&nbsp; </td>
      </tr>
    </table>
    <center>
      <font face="Arial" size=2>
      <input type="button" class="boton03" onClick="javascript:window.history.back()" value="Regresar">
      </font>
      </center>
      <p><br>  
  <%  }
    else {   %>
        <br>
      </p>
      <table width=250 border=0 align="center" cellpadding="0" cellspacing="10" class="celdas">      
<%      int counter = 0;
        for (Enumeration e = objHelp.getHshLista().keys(); e.hasMoreElements();) {
          e.nextElement();
          Vector v = (Vector)objHelp.getHshLista().get("" + counter);        
          counter++;     %>
          <tr><td id="campo" onMouseOver="celda(this,'e');" onMouseOut="celda(this,'s');" class="mano" onClick="passBack('<%=v.get(0)%>',this);"><font id="dest" color="#0000CC"><%= v.get(1)!=null?v.get(1):"" %></font></td></tr>
<%      }  //Fin del For  %>      
    </table>
      <br><br>
      <table width="250" border="0" align="center" cellpadding="0" cellspacing="0" class="celdas01">
      <tr>    
        <td align="left">Registro(s):
		  
          <b><font color="#0060C0"> <%= objHelp.getPaginacion().getRegDesde() %> </font></b> - 
          <b><font color="#0060C0"> <%= objHelp.getPaginacion().getRegHasta() %></font></b>     
      <font face="Arial" size=2>&nbsp;&nbsp;
       <% //int iPagina = ((Integer)session.getAttribute("IntPagina")).intValue();
      if ( objHelp.getPaginacion().getPaginaAnt()) { %>
              <input type="button" class="boton03" onClick="javascript:BuscarAntSgte(<%=Integer.parseInt(objHelp.getPag()!=null?objHelp.getPag():"1")-1%>,'<%=(String)objHelp.getCond5()%>')" value="Anterior">
      <%    } 
      if ( objHelp.getPaginacion().getPaginaSgte()) { %>
          <input type="button" class="boton03" onClick="javascript:BuscarAntSgte(<%=Integer.parseInt(objHelp.getPag()!=null?objHelp.getPag():"1")+1%>,'<%=(String)objHelp.getCond5()%>')" value="Siguiente">
      <%    }
      objHelp.getHshLista().clear(); 
     %>
          </font>
        </td>
      </tr>
    </table>
<% }    %>
</form>
<% request.removeAttribute("objHelp"); %>
</body>
</html>