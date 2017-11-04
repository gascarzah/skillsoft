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
  CallableStatement cs = null;
 ResultSet rs = null;
 
  try {
		String strSubmit = Util.jnvl(request.getParameter("smt"),"0");
	  String strTip = (String)request.getParameter("tipo")!=null?request.getParameter("tipo"):"";
	  String strNum = (String)request.getParameter("num")!=null?request.getParameter("num"):"";
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
		String strDTipoSeguro= "";
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

		strApepat=cs.getString(4)!=null?cs.getString(4):"";
		strApemat=cs.getString(5)!=null?cs.getString(5):"";
		strNomb=cs.getString(6)!=null?cs.getString(6):"";
		strDir=cs.getString(7)!=null?cs.getString(7):"";
		strCseg=cs.getString(8)!=null?cs.getString(8):"";
		strSeg=cs.getString(9)!=null?cs.getString(9):"";
		strCas=cs.getString(10)!=null?cs.getString(10):"";
		strCcas=cs.getString(11)!=null?cs.getString(11):"";
		strCred=cs.getString(12)!=null?cs.getString(12):"";
		strCsex=cs.getString(13)!=null?cs.getString(13):"";
		strCdep=cs.getString(14)!=null?cs.getString(14):"";
		strCprov=cs.getString(15)!=null?cs.getString(15):"";
		strCdist=cs.getString(16)!=null?cs.getString(16):"";
		strNficeda=cs.getString(17)!=null?cs.getString(17):"";
		strDred=cs.getString(18)!=null?cs.getString(18):"";
		strDTipoSeguro=cs.getString(19)!=null?cs.getString(19):"";
    cs.close();cs=null;
%>
<script language="JavaScript">        
	  parent.cuerpo.document.f.DAPEPAT.value = "<%= strApepat %>";
	  parent.cuerpo.document.f.DAPEMAT.value = "<%= strApemat %>";
	  parent.cuerpo.document.f.DNOM.value = "<%= strNomb %>";
    parent.cuerpo.document.f.DFICDIR.value = "<%= strDir %>";
    parent.cuerpo.document.f.CFICTIPSEG.value = "<%= strCseg %>";
//    parent.cuerpo.document.f.SEGURO.value = "<%= strSeg %>";
    //parent.cuerpo.document.f.CCAS.value = "<%= strCcas %>";
//    parent.cuerpo.document.f.DCAS.value = "<%= strCas %>";
//    parent.cuerpo.document.f.CRED.value = "<%= strCred %>";
//    parent.cuerpo.document.f.DRED.value = "<%= strDred %>";
    parent.cuerpo.document.f.DDICSEX.value = "<%= strCsex %>";
    parent.cuerpo.document.f.NFICEDA.value = "<%= strNficeda %>";
    parent.cuerpo.document.f.DEPARTAMENTO.value = "<%= strCdep %>";
    parent.cuerpo.document.f.PROVINCIA.value = "<%= strCdep %><%= strCprov %>";
    parent.cuerpo.document.f.CFICUBIGEO.value = "<%= strCdep %><%= strCprov %><%= strCdist %>";
    parent.cuerpo.document.f.DFICTIPSEG.value = "<%= strDTipoSeguro %>";
		if(parent.cuerpo.document.f.DFICDIR.value != ""){
			eval('parent.cuerpo.document.f.DAPEPAT.readOnly=true');
			eval('parent.cuerpo.document.f.DAPEMAT.readOnly=true');
			eval('parent.cuerpo.document.f.DNOM.readOnly=true');
			eval('parent.cuerpo.document.f.CFICTIPSEG.readOnly=true');
			eval('parent.cuerpo.document.f.DDICSEX.readOnly=true');
			eval('parent.cuerpo.document.f.NFICEDA.readOnly=true');
			eval('parent.cuerpo.document.f.DFICTIPSEG.readOnly=true');
			parent.cuerpo.document.f.DAPEPAT.style.backgroundColor="#DFE6EE";
			parent.cuerpo.document.f.DAPEMAT.style.backgroundColor="#DFE6EE";
			parent.cuerpo.document.f.DNOM.style.backgroundColor="#DFE6EE";
			parent.cuerpo.document.f.CFICTIPSEG.style.backgroundColor="#DFE6EE";
			parent.cuerpo.document.f.DDICSEX.style.backgroundColor="#DFE6EE";
			parent.cuerpo.document.f.NFICEDA.style.backgroundColor="#DFE6EE";
			parent.cuerpo.document.f.DFICTIPSEG.style.backgroundColor="#DFE6EE";

		}
		else{
			eval('parent.cuerpo.document.f.DAPEPAT.readOnly=false');
			eval('parent.cuerpo.document.f.DAPEMAT.readOnly=false');
			eval('parent.cuerpo.document.f.DNOM.readOnly=false');
			eval('parent.cuerpo.document.f.CFICTIPSEG.readOnly=true');
			eval('parent.cuerpo.document.f.DDICSEX.readOnly=false');
			eval('parent.cuerpo.document.f.NFICEDA.readOnly=false');
			eval('parent.cuerpo.document.f.DFICTIPSEG.readOnly=false');
//			eval('parent.cuerpo.document.f.CCAS.readOnly=false');
			parent.cuerpo.document.f.DAPEPAT.style.backgroundColor="#FFFFFF";
			parent.cuerpo.document.f.DAPEMAT.style.backgroundColor="#FFFFFF";
			parent.cuerpo.document.f.DNOM.style.backgroundColor="#FFFFFF";
			parent.cuerpo.document.f.CFICTIPSEG.style.backgroundColor="#FFFFFF";
			parent.cuerpo.document.f.DDICSEX.style.backgroundColor="#FFFFFF";
			parent.cuerpo.document.f.NFICEDA.style.backgroundColor="#FFFFFF";
			parent.cuerpo.document.f.DFICTIPSEG.style.backgroundColor="#FFFFFF";
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
	}
%>
</body>
</html>
