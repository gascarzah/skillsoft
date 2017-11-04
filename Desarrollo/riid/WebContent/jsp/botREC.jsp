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
	System.out.println("Dentro de botREC");
  Connection con = conexion.getConnection();
  if (con==null) {
    request.setAttribute("msg_error", "Error de Conexión a DB:");
    WebUtil.goForward(getServletContext(), request, response,"/error/error.jsp");
		System.out.println("Error en el botAD.jsp");
    return;
  }  
  CallableStatement cs = null;
 ResultSet rs = null;
 
  try {
		String strSubmit = Util.jnvl(request.getParameter("smt"),"0");
	  String strTip = (String)request.getParameter("tipo")!=null?request.getParameter("tipo"):"";
	  String strNum = (String)request.getParameter("num")!=null?request.getParameter("num"):"";
	  System.out.println("tipo="+strTip+" y num="+strNum);
	  String strApepat = "";
	  String strApemat = "";
	  String strNomb = "";
		String strDir = "";
		String strCseg = "";
		String strSeg = "";
		String strCas = "";
		String strCcas = "";
		String strCred = "";
		String strCsex = "";
		String strCubi = "";
		String strCdep = "";
		String strCprov = "";
		String strCdist = "";
		String strNficeda = "";
		String strDred = "";
		String strRuc = "";
		String strRazSoc = "";
		
    cs = (CallableStatement)
    con.prepareCall("BEGIN ? := PKG_TABLAS.FN_getDatosAseg(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;");
    cs.registerOutParameter(1, OracleTypes.NUMBER);
    cs.setString(2, strTip);
    cs.setString(3, strNum);
    cs.registerOutParameter(4, OracleTypes.VARCHAR);
    cs.registerOutParameter(5, OracleTypes.VARCHAR);
    cs.registerOutParameter(6, OracleTypes.VARCHAR);
    cs.registerOutParameter(7, OracleTypes.VARCHAR);
    cs.registerOutParameter(8, OracleTypes.VARCHAR);
    cs.registerOutParameter(9, OracleTypes.VARCHAR);
    cs.registerOutParameter(10, OracleTypes.VARCHAR);
    cs.registerOutParameter(11, OracleTypes.VARCHAR);
    cs.registerOutParameter(12, OracleTypes.VARCHAR);
    cs.registerOutParameter(13, OracleTypes.VARCHAR);
    cs.registerOutParameter(14, OracleTypes.VARCHAR);
    cs.registerOutParameter(15, OracleTypes.VARCHAR);
    cs.registerOutParameter(16, OracleTypes.VARCHAR);
    cs.registerOutParameter(17, OracleTypes.VARCHAR);
    cs.registerOutParameter(18, OracleTypes.VARCHAR);
   cs.registerOutParameter(19, OracleTypes.VARCHAR); 
    cs.executeUpdate();
    
    // cs.registerOutParameter(20, OracleTypes.VARCHAR);
    // cs.registerOutParameter(21, OracleTypes.VARCHAR);

		strApepat=cs.getString(4)!=null?cs.getString(4):"";
		strApemat=cs.getString(5)!=null?cs.getString(5):"";
		strNomb=cs.getString(6)!=null?cs.getString(6):"";
	//	strRuc = cs.getString(19)!=null?cs.getString(19):"";;
	//	strRazSoc =cs.getString(20)!=null?cs.getString(20):"";;
		
    cs.close();cs=null;
%>
<script language="JavaScript">        
	  parent.cuerpo.document.f.DAPEPATREC.value = "<%= strApepat %>";
	  parent.cuerpo.document.f.DAPEMATREC.value = "<%= strApemat %>";
	  parent.cuerpo.document.f.DNOMREC.value = "<%= strNomb %>";
	  
	  parent.cuerpo.document.f.DG_RAZ_SOCIAL.value = "<%= strRazSoc %>";
	 
	  
		if(parent.cuerpo.document.f.DAPEPATREC.value != ""){
			eval('parent.cuerpo.document.f.DAPEPATREC.readOnly=true');
			eval('parent.cuerpo.document.f.DAPEMATREC.readOnly=true');
			eval('parent.cuerpo.document.f.DNOMREC.readOnly=true');
			eval('parent.cuerpo.document.f.DG_RAZ_SOCIAL.readOnly=true');
			parent.cuerpo.document.f.DAPEPATREC.style.backgroundColor="#DFE6EE";
			parent.cuerpo.document.f.DAPEMATREC.style.backgroundColor="#DFE6EE";
			parent.cuerpo.document.f.DNOMREC.style.backgroundColor="#DFE6EE";
			parent.cuerpo.document.f.DG_RAZ_SOCIAL.style.backgroundColor="#DFE6EE";
			
		}
		else{
			eval('parent.cuerpo.document.f.DAPEPATREC.readOnly=false');
			eval('parent.cuerpo.document.f.DAPEMATREC.readOnly=false');
			eval('parent.cuerpo.document.f.DNOMREC.readOnly=false');
			eval('parent.cuerpo.document.f.DG_RAZ_SOCIAL.readOnly=false');
			parent.cuerpo.document.f.DAPEPATREC.style.backgroundColor="#FFFFFF";
			parent.cuerpo.document.f.DAPEMATREC.style.backgroundColor="#FFFFFF";
			parent.cuerpo.document.f.DNOMREC.style.backgroundColor="#FFFFFF";
			parent.cuerpo.document.f.DG_RAZ_SOCIAL.style.backgroundColor="#FFFFFF";
			
			alert("No Hay Información");		
		}
  </script>

  DATOS = <%= strDir %> 
  strDcas = <%= strCas %>
<%  
  con.close();con=null;
  } 
	catch (Exception e) {
    out.println("msg_error: No se pudo obtener la información. Por favor inténtelo en un momento."+ e.getMessage());
  } 
  finally {
    conexion.closeConnection(con);
    conexion.closeCallableStatement(cs);
	}
%>
</body>
</html>
