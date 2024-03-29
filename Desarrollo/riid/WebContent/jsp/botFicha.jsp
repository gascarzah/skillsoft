<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="java.util.*,java.sql.*" %>
<%@ page import="oracle.jdbc.driver.*" %>
<%@ page import="beans.WebUtil" %>
<%@ page import="beans.Util" %>
<%@ page import="bd.conexion" %>
<%@ page import="bd.BDTablas" %>
<%@ page import="clases.UsuarioSistema" %>
<HTML>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body>
<%
  Connection con = conexion.getConnection();
  UsuarioSistema clusuario = (UsuarioSistema)session.getAttribute("sClusuario");
  if (con==null) {
    request.setAttribute("msg_error", "Error de Conexi�n a DB:");
    WebUtil.goForward(getServletContext(), request, response,"/error/error.jsp");
		System.out.println("Error en el botFicha.jsp");
    return;
  }  
  CallableStatement cs = null; 
  ResultSet rs = null;
  Statement stmt = null;
	String strSubmit = request.getParameter("smt")!=null?Util.jnvl(request.getParameter("smt"),"0"):"";
  String strFun = (String)request.getParameter("funcion")!=null?request.getParameter("funcion"):"";
  String strCod = (String)request.getParameter("codigo")!=null?request.getParameter("codigo"):"";
  String strCod2 = (String)request.getParameter("codigo2")!=null?request.getParameter("codigo2"):"";

 
  String strUsuario = null;
	String strCasUsua = null;
	String strPerfil 	= null;
  try {
	  
	  if(clusuario != null){
		strUsuario = StringUtils.defaultString(clusuario.getUsuario());
		strCasUsua = StringUtils.defaultString(clusuario.getCcas());
		strPerfil 	= StringUtils.defaultString(clusuario.getPerfil());
	  }
		String strCprov = "";
	  String strCdist = "";
		String strDes = "";
		String strDcas = "";
	  String strPla = "";
	  String strCcas = "";
	  String strUsua = "";
	  String strNive = "";
	  String strDarea = "";

		if (strFun.equals("PROV")) {
		
			cs = (CallableStatement)
			con.prepareCall("BEGIN ? := PKG_TABLAS.FN_getUbiProv(?,?); END;");
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.setString(2, strCod);
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			cs.executeUpdate();
	
			strCprov=cs.getString(3)!=null?cs.getString(3):"";
			cs.close();
			cs=null;
%>
			strCod = <%= strCod %>
			strCprov = <%= strCprov %>
	
			<script language="JavaScript">        
				parent.cuerpo.document.f.PROVINCIA.value = "<%= strCprov %>";
			</script>
<%
		}
		else if (strFun.compareTo("CPRO")==0) {
      int i = 0;
		  cs = (CallableStatement)
      con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getCurProv(?); END;");
      cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, strCod);
			cs.executeUpdate();
      rs = (ResultSet) cs.getObject(1); %>
      <script language="javascript">
        parent.cuerpo.document.f.PROVINCIA.length = 0;
        parent.cuerpo.document.f.PROVINCIA.options[0] = new Option ('[SELECCIONAR]','')
      </script>
<% 
		while (rs.next()) { 
        i++; %> 
        <script language=javascript>        
          parent.cuerpo.document.f.PROVINCIA.options[<%=i%>] = new Option ('<%= Util.replace(rs.getString(2),"\'","\\\'") %>', '<%= rs.getString(1) %>')
        </script>
<%      
      }
      rs.close();
      cs.close(); 
%>
<%
		} 
		else if (strFun.equals("CDIS")) {
      int i = 0;
		  cs = (CallableStatement)
      con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getCurDist(?); END;");
      cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, strCod);
			cs.executeUpdate();
      rs = (ResultSet) cs.getObject(1); %>
      <script language="javascript">
        parent.cuerpo.document.f.CFICUBIGEO.length = 0;
        parent.cuerpo.document.f.CFICUBIGEO.options[0] = new Option ('[SELECCIONAR]','')
      </script>
      
<%    while (rs.next()) { 
        i++; %> 
        <script language=javascript>        
          parent.cuerpo.document.f.CFICUBIGEO.options[<%=i%>] = new Option ('<%= Util.replace(rs.getString(2),"\'","\\\'") %>', '<%= rs.getString(1) %>')
        </script>
<%      
      }
      rs.close();
      cs.close();
   } 
	else if (strFun.equals("CASTOTAL")) {
		int i = 0;
		String strSQL = "";
		stmt = con.createStatement();
		strSQL = "select t.ccas, t.dcas from dmcas10 t";
		rs = stmt.executeQuery(strSQL);		 
		while (rs.next()) { 
        i++; 
%> 
        <script language=javascript>        
          parent.cuerpo.document.f.CCAS.options[<%=i%>] = new Option ('<%= Util.replace(rs.getString(2),"\'","\\\'") %>', '<%= rs.getString(1) %>')
        </script>
<%      
      }
      rs.close();
      cs.close();
	} 
	else if (strFun.equals("DIST")) {
			cs = (CallableStatement)
			con.prepareCall("BEGIN ? := PKG_TABLAS.FN_getUbiDist(?,?); END;");
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.setString(2, strCod);
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			cs.executeUpdate();
	
			strCdist=cs.getString(3)!=null?cs.getString(3):"";
			cs.close();
			cs=null;
%>
			strCod = <%= strCod %>
			strCdist = <%= strCdist %>
	
			<script language="JavaScript">        
				parent.cuerpo.document.f.DISTRITO.value = "<%= strCdist %>";
			</script>
			<!-- Eliminado getAsunto -->
<%
		}     
	else if (strFun.equals("DCAS")) {
			cs = (CallableStatement)
			con.prepareCall("BEGIN ? := PKG_TABLAS.FN_getDatosDcas(?,?); END;");
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.setString(2, strCod);
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			cs.executeUpdate();
	
			strDcas=cs.getString(3)!=null?cs.getString(3):"";
			cs.close();
			cs=null;
%>
			strCod = <%= strCod %>
			strDcas = <%= strDcas %>
	
			<script language="JavaScript">        
				parent.cuerpo.document.f.DCAS.value = "<%= strDcas %>";
			</script>
<%
		}
    else if (strFun.equals("CAS")) {
			cs = (CallableStatement)
			con.prepareCall("BEGIN ? := PKG_TABLAS.FN_getDatosCas(?,?); END;");
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.setString(2, strCod);
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			cs.executeUpdate();
	
			strCcas=cs.getString(3)!=null?cs.getString(3):"";
			cs.close();
			cs=null;
%>
			strCod = <%= strCod %>
			strCcas = <%= strCcas %>
		
			<script language="JavaScript">        
				parent.cuerpo.document.f.CCAS.value = "<%= strCcas %>";
			</script>
<%
		}
    else if (strFun.equals("AREA")) {
			cs = (CallableStatement)
			con.prepareCall("BEGIN ? := PKG_TABLAS.FN_getDarea(?,?); END;");
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.setString(2, strCod);
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			cs.executeUpdate();
	
			strDarea=cs.getString(3)!=null?cs.getString(3):"";
			cs.close();
			cs=null;
%>
			strCod = <%= strCod %>
			strDarea = <%= strDarea %>
		
			<script language="JavaScript">        
				parent.cuerpo.document.f.ESPECIALIDAD.value = "<%= strDarea %>";
			</script>
<%
		}
	 else if (strFun.equals("CASC")) {
     int i = 0;
		  cs = (CallableStatement)
      con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getCurRedCas(?); END;");
      cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, strCod);
			cs.executeUpdate();
      rs = (ResultSet) cs.getObject(1); %>
      <script language="javascript">
        parent.cuerpo.document.f.CCAS.length = 0;
        parent.cuerpo.document.f.CCAS.options[0] = new Option ('[SELECCIONAR]','')
      </script>
<%    while (rs.next()) { 
        i++; %> 
        <script language=javascript>
          parent.cuerpo.document.f.CCAS.options[<%=i%>] = new Option ('<%= Util.replace(rs.getString(2),"\'","\\\'") %>', '<%= rs.getString(1) %>')
        </script>
<%      
      }
      rs.close();
      cs.close();
   } 
	 else if (strFun.equals("AREASERV")) {
      int i = 0;
		  cs = (CallableStatement)
      con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getAreaServ(?,?); END;");
      cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, strCod);
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			cs.executeUpdate();
      rs = (ResultSet) cs.getObject(1); 
			%>
      <script language="javascript">
        parent.cuerpo.document.f.ENLSERVHOSCOD.length = 0;
        parent.cuerpo.document.f.ENLSERVHOSCOD.options[0] = new Option ('[SELECCIONAR]','')
      </script>
<% 
		while (rs.next()) { 
        i++; %> 
        <script language=javascript>        
          parent.cuerpo.document.f.ENLSERVHOSCOD.options[<%=i%>] = new Option ('<%= Util.replace(rs.getString(2),"\'","\\\'") %>', '<%= rs.getString(1) %>')
	        parent.cuerpo.document.f.ESPECIALIDAD.value = '<%= rs.getString(3) %>';
        </script>
<%      
      }
      rs.close();
      cs.close(); 
    } 
    //eliminado diagnostico 
	 else if (strFun.equals("MAT1")) {
     int i = 0;
		  cs = (CallableStatement)
      con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getMateria(?); END;");
      cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, strCod);
			cs.executeUpdate();
      rs = (ResultSet) cs.getObject(1); %>
      <script language="javascript">
        parent.cuerpo.document.f.CASU.length = 0;
        parent.cuerpo.document.f.CASU.options[0] = new Option ('[SELECCIONAR]','')
      </script>
<%    while (rs.next()) { 
        i++; %> 
        <script language=javascript>        
          parent.cuerpo.document.f.CASU.options[<%=i%>] = new Option ('<%= Util.replace(rs.getString(2),"\'","\\\'") %>', '<%= rs.getString(1) %>')
        </script>
<%
      }
      rs.close();
      cs.close();
		} 
	 else if (strFun.equals("MAT2")) {
     int i = 0;
		  cs = (CallableStatement)
      con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getMateria(?); END;");
      cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, strCod);
			cs.executeUpdate();
      rs = (ResultSet) cs.getObject(1); %>
      <script language="javascript">
        parent.cuerpo.document.f.CODIGO1.length = 0;
        parent.cuerpo.document.f.CODIGO1.options[0] = new Option ('[SELECCIONAR]','')
      </script>
<%    while (rs.next()) { 
        i++; %> 
        <script language=javascript>        
          parent.cuerpo.document.f.CODIGO1.options[<%=i%>] = new Option ('<%= Util.replace(rs.getString(2),"\'","\\\'") %>', '<%= rs.getString(1) %>')
        </script>
<%
      }
      rs.close();
      cs.close();
		} 
	 else if (strFun.equals("MAT3")) {
     int i = 0;
		  cs = (CallableStatement)
      con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getMateria(?); END;");
      cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, strCod);
			cs.executeUpdate();
      rs = (ResultSet) cs.getObject(1); %>
      <script language="javascript">
        parent.cuerpo.document.f.CODIGO2.length = 0;
        parent.cuerpo.document.f.CODIGO2.options[0] = new Option ('[SELECCIONAR]','')
      </script>
<%    while (rs.next()) { 
        i++; %> 
        <script language=javascript>        
          parent.cuerpo.document.f.CODIGO2.options[<%=i%>] = new Option ('<%= Util.replace(rs.getString(2),"\'","\\\'") %>', '<%= rs.getString(1) %>')
        </script>
<%
      }
      rs.close();
      cs.close();
		}  else if (strFun.equals("DTEMA")) {
		     int i = 0;
				  cs = (CallableStatement)
		      con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getTematica(?); END;");
		      cs.registerOutParameter(1, OracleTypes.CURSOR);
					cs.setString(2, strCod);
					cs.executeUpdate();
		      rs = (ResultSet) cs.getObject(1); %>
		      <script language="javascript">
		        parent.cuerpo.document.f.CTEM.length = 0;
		        parent.cuerpo.document.f.CTEM.options[0] = new Option ('[SELECCIONAR]','')
		      </script>
		<%    while (rs.next()) { 
		        i++; %> 
		        <script language=javascript>        
		          parent.cuerpo.document.f.CTEM.options[<%=i%>] = new Option ('<%= Util.replace(rs.getString(2),"\'","\\\'") %>', '<%= rs.getString(1) %>')
		        </script>
		<%      
		      }
		      rs.close();
		      cs.close();
		   } else if (strFun.equals("DCONCLU")) {
			     int i = 0;
					  cs = (CallableStatement)
			      con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getConclusiones(?); END;");
			      cs.registerOutParameter(1, OracleTypes.CURSOR);
						cs.setString(2, strCod);
						cs.executeUpdate();
			      rs = (ResultSet) cs.getObject(1); %>
			      <script language="javascript">
			        parent.cuerpo.document.f.CCON.length = 0;
			        parent.cuerpo.document.f.CCON.options[0] = new Option ('[SELECCIONAR]','')
			      </script>
			<%    while (rs.next()) { 
			        i++; %> 
			        <script language=javascript>        
			          parent.cuerpo.document.f.CCON.options[<%=i%>] = new Option ('<%= Util.replace(rs.getString(2),"\'","\\\'") %>', '<%= rs.getString(1) %>')
			        </script>
			<%      
			      }
			      rs.close();
			      cs.close();
			   } else if (strFun.equals("CDEL")) {
				     int i = 0;
					  cs = (CallableStatement)
			      con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getDelegado(?,?); END;");
			      cs.registerOutParameter(1, OracleTypes.CURSOR);
						cs.setString(2, strCod);
						cs.setString(3, strCod2);
						cs.executeUpdate();
			      rs = (ResultSet) cs.getObject(1); %>
			      <script language="javascript">
			        parent.cuerpo.document.f.CDELEGADO.length = 0;
			        parent.cuerpo.document.f.CDELEGADO.options[0] = new Option ('[SELECCIONAR]','')
			      </script>
			<%    while (rs.next()) { 
			        i++; %> 
			        <script language=javascript>        
			          parent.cuerpo.document.f.CDELEGADO.options[<%=i%>] = new Option ('<%= Util.replace(rs.getString(2),"\'","\\\'") %>', '<%= rs.getString(1) %>')
			        </script>
			<%      
			      }
			      rs.close();
			      cs.close();
			   } 
				 else if (strFun.equals("CASC1")) {
				     int i = 0;
						  cs = (CallableStatement)
				      con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getCurRedCas(?); END;");
				      cs.registerOutParameter(1, OracleTypes.CURSOR);
							cs.setString(2, strCod);
							cs.executeUpdate();
				      rs = (ResultSet) cs.getObject(1); %>
				      <script language="javascript">
				        parent.cuerpo.document.f.CCAS1.length = 0;
				        parent.cuerpo.document.f.CCAS1.options[0] = new Option ('[SELECCIONAR]','')
				        parent.cuerpo.document.f.CDELEGADO.length = 0;
				        parent.cuerpo.document.f.CDELEGADO.options[0] = new Option ('[SELECCIONAR]','')
				      </script>
				<%    while (rs.next()) { 
				        i++; %> 
				        <script language=javascript>        
				          parent.cuerpo.document.f.CCAS1.options[<%=i%>] = new Option ('<%= Util.replace(rs.getString(2),"\'","\\\'") %>', '<%= rs.getString(1) %>')
				        </script>
				<%      
				      }
				      rs.close();
				      cs.close();
				   } 
		
				 else if (strFun.equals("CDELASIG")) {
				     int i = 0;
				     
				     //System.out.println("strFun ===>>>" + strFun );
				    // System.out.println(" strCod ===>>>" + strCod );
				    // System.out.println(" strCod2 ===>>>" + strCod2 );
					  cs = (CallableStatement)
			      con.prepareCall("BEGIN ? := PKG_TABLAS.fn_DelegadosAsignados(?); END;");
			      cs.registerOutParameter(1, OracleTypes.CURSOR);
						cs.setString(2, strCod);						
						cs.executeUpdate();
			      rs = (ResultSet) cs.getObject(1); %>
			      <script language="javascript">
			        parent.cuerpo.document.f.CDELEGADO.length = 0;
			        parent.cuerpo.document.f.CDELEGADO.options[0] = new Option ('[SELECCIONAR]','')
			      </script>
			<%    while (rs.next()) { 
			        i++; %> 
			        <script language=javascript>        
			          parent.cuerpo.document.f.CDELEGADO.options[<%=i%>] = new Option ('<%= Util.replace(rs.getString(2),"\'","\\\'") %>', '<%= rs.getString(1) %>')
			        </script>
			<%      
			      }
			      rs.close();
			      cs.close();
			   } 
		
	}
	catch (Exception e) {
    System.out.println("msg_error: No se pudo obtener la informaci�n. Por favor int�ntelo en un momento. " + strCod + ' '+ strFun +' '+  e.getMessage());
    
  } 
  finally {
    conexion.closeConnection(con);
		conexion.closeCallableStatement(cs);
		conexion.closeResultSet(rs);
	}
%>
</body>
</html>
