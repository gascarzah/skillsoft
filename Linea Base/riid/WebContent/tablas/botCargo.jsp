<%@ page import="java.util.*,java.sql.*" %>
<%@ page import="oracle.jdbc.driver.*" %>
<%@ page import="beans.WebUtil" %>
<%@ page import="beans.Util" %>
<%@ page import="bd.conexion" %>
<%@ page import="bd.BDTablas" %>
<HTML>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body>
<%
  Connection con = conexion.getConnection();
  if (con==null) {
    request.setAttribute("msg_error", "Error de Conexión a DB:");
    WebUtil.goForward(getServletContext(), request, response,"/error/error.jsp");
		System.out.println("Error en el bot.jsp");
    return;
  }  
  System.out.println("con no es null");
  CallableStatement cs = null;

 
  try {
		
	  String ccargo = (String)request.getParameter("ccargo")!=null?request.getParameter("ccargo"):"";
	  System.out.println("ccargo es "+ccargo);
	  String dcarpadre= "";
	  String cunocod = "";
	  String dcargo = "";
	  String cnivel = "";
	 
	  String cusu = "";
	  String dapepatusu="";
	  String dapematusu="";
	  String dnomusu="";
	  String dusumail="";
	  String bworkflow="";
	  String bestadoreg="";
    cs = (CallableStatement)
    con.prepareCall("{ call PKG_FICHA.SP_getCargo (?,?,?,?,?,?,?,?,?,?,?,?)}");
	cs.setString(1,ccargo);
    cs.registerOutParameter(2, OracleTypes.VARCHAR);
	cs.registerOutParameter(3, OracleTypes.VARCHAR);
	cs.registerOutParameter(4, OracleTypes.VARCHAR);
	cs.registerOutParameter(5, OracleTypes.VARCHAR);
	cs.registerOutParameter(6, OracleTypes.VARCHAR);
	cs.registerOutParameter(7, OracleTypes.VARCHAR);
	cs.registerOutParameter(8, OracleTypes.VARCHAR);
	cs.registerOutParameter(9, OracleTypes.VARCHAR);
	cs.registerOutParameter(10, OracleTypes.VARCHAR);
	cs.registerOutParameter(11, OracleTypes.VARCHAR);
	cs.registerOutParameter(12, OracleTypes.VARCHAR);
	cs.executeUpdate();
	
	dcarpadre=cs.getString(2)!=null?cs.getString(2):"Padre sin registrar!";
	cunocod=cs.getString(3);
	dcargo=cs.getString(4);
	cnivel=cs.getString(5);
	cusu=cs.getString(6)!=null?cs.getString(6):"";
	dapepatusu=cs.getString(7)!=null?cs.getString(7):"";
	dapematusu=cs.getString(8)!=null?cs.getString(8):"";
	dnomusu=cs.getString(9)!=null?cs.getString(9):"";
	dusumail=cs.getString(10)!=null?cs.getString(10):"";
	bworkflow=cs.getString(11)!=null?cs.getString(11):"";
	bestadoreg=cs.getString(12)!=null?cs.getString(12):"";
		
    cs.close();cs=null;
%>
<script language="JavaScript">       

	  parent.cuerpo.document.f.DCARPADRE.value 	= "<%= dcarpadre %>";
	  parent.cuerpo.document.f.CUNOCOD.value 	= "<%= cunocod %>";
	  parent.cuerpo.document.f.CCARGO.value 	= "<%= ccargo %>";
	  parent.cuerpo.document.f.DCARGO.value 	= "<%= dcargo %>";
	  parent.cuerpo.document.f.CNIVEL.value		= "<%= cnivel%>";
	  parent.cuerpo.document.f.CUSU.value 		= "<%= cusu %>";
	  parent.cuerpo.document.f.DAPEPATUSU.value = "<%= dapepatusu %>";
	  parent.cuerpo.document.f.DAPEMATUSU.value = "<%= dapematusu %>";
	  parent.cuerpo.document.f.DNOMUSU.value 	= "<%= dnomusu %>";
	  parent.cuerpo.document.f.DUSUMAIL.value 	= "<%= dusumail %>";
	  parent.cuerpo.document.f.BWORKFLOW.value 	= "<%= bworkflow %>";
	  parent.cuerpo.document.f.BESTADOREG.value = "<%= bestadoreg %>";
	  
    
		
  </script>

  DATOS = <%= ccargo %> 
  
<%  
  con.close();con=null;
  } 
	catch (Exception e) {
    out.println("msg_error: No se pudo obtener la información. Por favor inténtelo en un momento."+ e.getMessage());
  } 
  finally {
    conexion.closeConnection(con);
	}
%>
</body>
</html>
