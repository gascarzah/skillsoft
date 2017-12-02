
package bd;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;

import oracle.jdbc.driver.OracleTypes;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import clases.Ficha_t;

public class BDTablas {
  private static Logger logger = Logger.getLogger(BDTablas.class);
 

  public static Vector getUbigeo ( Connection p_con, String pCod1, String pCod2 ) {

	Vector vListado = new Vector();
	Statement  stmt    = null;
	ResultSet  rs      = null;                                    
	String	   strSQL  = "";
	
	strSQL = "select (substr(t.ubicubi,0,2)), t.ubitdpt, (substr(t.ubicubi,3,2)), t.ubitprv, (substr(t.ubicubi,5,2)), t.ubitdtr from sccmdgat s, cpccubit t where s.dgactdi = '"+ pCod1 +"' and s.dgandid = '"+ pCod2 +"' and s.dgacugd = t.ubicubi "; 
	logger.debug("strSQL: getUbigeo " + strSQL);
	try {
	  stmt = p_con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  while (rs.next()) {
		Vector v = new Vector();
		v.add(0, rs.getString(1));
		v.add(1, rs.getString(2));
		v.add(2, rs.getString(3));
		v.add(3, rs.getString(4));
		v.add(4, rs.getString(5));
		v.add(5, rs.getString(6));
		vListado.add(v);
	  }
	  
  	}
  	catch (SQLException e) {
	  logger.debug("Error al llamar procedimiento getUbigeo " + e.getMessage());
  	} 
  	finally {
	  conexion.closeResultSet(rs);
	  conexion.closeStatement(stmt);
	}   
    return vListado;
  }  

  public static Vector getDepartamento (Connection p_con) {

   Vector vListado = new Vector();
   Statement  stmt    = null;
   ResultSet  rs      = null;                                    
  
	String strSQL="select distinct(substr(t.ubicubi,0,2)), trim(t.ubitdpt) dp from cpccubit t order by dp asc";

   try {
	 stmt = p_con.createStatement();
	 rs = stmt.executeQuery(strSQL);
	 while (rs.next()) {
	   Vector v = new Vector();
	   v.add(0, rs.getString(1));
	   v.add(1, rs.getString(2));
	   vListado.add(v);
	 }
	 
   }
   catch (SQLException e) {
	 logger.debug("Error al llamar procedimiento getDepartamento " + e.getMessage());
   } 
   finally {
	 conexion.closeResultSet(rs);
	 conexion.closeStatement(stmt);
   }   
   
   return vListado;
 }  

  public static Vector getProvincia ( Connection p_con ) {

  Vector vListado	= new Vector();
  Statement  stmt	= null;
  ResultSet  rs  	= null;
  String strSQL	 	= null;                          

		strSQL =  "select distinct(substr(t.ubicubi,0,4)) cp, trim(t.ubitprv) dp from cpccubit t order by cp asc ";
		
  try {
		stmt = p_con.createStatement();
		rs = stmt.executeQuery(strSQL);
		while (rs.next()) {
			Vector v = new Vector();
		  v.add(0, rs.getString(1));
		  v.add(1, rs.getString(2));
		  vListado.add(v);
		}
	
  }
  catch (SQLException e) {
		logger.debug("Error al llamar procedimiento getProvincia " + e.getMessage());
  } 
  finally {
		conexion.closeResultSet(rs);
		conexion.closeStatement(stmt);
  }   
  return vListado;
 }  
  public static Vector getDistrito ( Connection p_con ) {

   Vector vListado = new Vector();
   Statement  stmt    = null;
   ResultSet  rs      = null;                                    
  
   String strSQL="select t.ubicubi, t.ubitdtr from cpccubit t order by t.ubicubi asc ";

   try {
	 stmt = p_con.createStatement();
	 rs = stmt.executeQuery(strSQL);
	 while (rs.next()) {
	   Vector v = new Vector();
	   v.add(0, rs.getString(1));
	   v.add(1, rs.getString(2));
	   vListado.add(v);
	 }
	 
   }
   catch (SQLException e) {
	 logger.debug("Error al llamar procedimiento getDistrito " + e.getMessage());
   } 
   finally {
	 conexion.closeResultSet(rs);
	 conexion.closeStatement(stmt);
   }   
   return vListado;
 }  

  public static Vector getIdentidad ( Connection p_con ) {

	Vector vListado = new Vector();
	Statement  stmt    = null;
	ResultSet  rs      = null;                                    
  
	String strSQL="select t.ctdi, t.dtdi from dbtdi10 t where t.bestadoreg = '1' order by t.ctdi asc ";

	try {
	  stmt = p_con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  while (rs.next()) {
		Vector v = new Vector();
		v.add(0, rs.getString(1));
		v.add(1, rs.getString(2));
		vListado.add(v);
	  }
	  
	}
	catch (SQLException e) {
	  logger.debug("Error al llamar procedimiento getIdentidad " + e.getMessage());
	} 
	finally {
	  conexion.closeResultSet(rs);
	  conexion.closeStatement(stmt);
	}   
	return vListado;
  }  

  
  public static Vector getListadoFicheros ( Connection p_con, Integer id ) {

		Vector vListado = new Vector();
		Statement  stmt    = null;
		ResultSet  rs      = null;     
		
		
	  
		String strSQL="select t.correl, t.dfnomb, t.fusucrea from tficheros t where t.correl = " +id+"   order by t.fusucrea Desc ";

		try {
		  stmt = p_con.createStatement();
		  rs = stmt.executeQuery(strSQL);
		  while (rs.next()) {
			Vector v = new Vector();
			v.add(0, rs.getString(1));
			v.add(1, rs.getString(2));
			vListado.add(v);
		  }
		  
		}
		catch (SQLException e) {
		  logger.debug("Error al llamar procedimiento getIdentidad " + e.getMessage());
		} 
		finally {
		  conexion.closeResultSet(rs);
		  conexion.closeStatement(stmt);
		}   
		return vListado;
	  }  

  
  public static Vector getPrestacion ( Connection p_con ) {

	Vector vListado = new Vector();
	Statement  stmt    = null;
	ResultSet  rs      = null;                                    
  
	String strSQL="select t.ctpr, t.dtpr from dbtpr10 t where t.btprestareg = '1' order by t.dtpr asc ";

	try {
	  stmt = p_con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  while (rs.next()) {
		Vector v = new Vector();
		v.add(0, rs.getString(1));
		v.add(1, rs.getString(2));
		vListado.add(v);
	  }
	  
	}
	catch (SQLException e) {
	  logger.debug("Error al llamar procedimiento getPrestacion " + e.getMessage());
	} 
	finally {
	  conexion.closeResultSet(rs);
	  conexion.closeStatement(stmt);
	}   
	return vListado;
  }  

  //inicio nponce 30/01/2015
  public static Vector getMotivoNoAdm ( Connection p_con ) {

		Vector vListado = new Vector();
		Statement  stmt    = null;
		ResultSet  rs      = null;                                    
	  
		String strSQL="select t.ctma, t.dtma from tmotnoadm t where t.estadreg = '1' order by t.dtma asc ";

		try {
		  stmt = p_con.createStatement();
		  rs = stmt.executeQuery(strSQL);
		  while (rs.next()) {
			Vector v = new Vector();
			v.add(0, rs.getString(1));
			v.add(1, rs.getString(2));
			vListado.add(v);
		  }
		  
		}
		catch (SQLException e) {
		  logger.debug("Error al llamar procedimiento getMotivoNoAdm " + e.getMessage());
		} 
		finally {
		  conexion.closeResultSet(rs);
		  conexion.closeStatement(stmt);
		}   
		return vListado;
	  }  

  public static Vector getModelDeConclu ( Connection p_con ) {  

		Vector vListado = new Vector();
		Statement  stmt    = null;
		ResultSet  rs      = null;                                    
	  
		String strSQL="select t.ctmc, t.dtmc from tmodconclu t where t.estadreg = '1' order by t.dtmc asc ";

		try {
		  stmt = p_con.createStatement();
		  rs = stmt.executeQuery(strSQL);
		  while (rs.next()) {
			Vector v = new Vector();
			v.add(0, rs.getString(1));
			v.add(1, rs.getString(2));
			vListado.add(v);
		  }
		  
		}
		catch (SQLException e) {
		  logger.debug("Error al llamar procedimiento getModelDeConclu " + e.getMessage());
		} 
		finally {
		  conexion.closeResultSet(rs);
		  conexion.closeStatement(stmt);
		}   
		return vListado;
	  }  
  //fin nponce 30/01/2015
  
  public static Vector getSeguro ( Connection p_con ) {

	Vector vListado = new Vector();
	Statement  stmt    = null;
	ResultSet  rs      = null;                                    
  
	String strSQL="select t.celemen, t.delemen from tcod_gendets t where t.tcgc_ctabla = '005' order by t.delemen asc ";

	try {
	  stmt = p_con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  while (rs.next()) {
		Vector v = new Vector();
		v.add(0, rs.getString(1));
		v.add(1, rs.getString(2));
		vListado.add(v);
	  }
	  
	}
	catch (SQLException e) {
	  logger.debug("Error al llamar procedimiento getSeguro " + e.getMessage());
	} 
	finally {
	  conexion.closeResultSet(rs);
	  conexion.closeStatement(stmt);
	}   
	return vListado;
  }  


  public static Vector getSolicitud ( Connection p_con ) {

	Vector vListado = new Vector();
	Statement  stmt    = null;
	ResultSet  rs      = null;                                    
  
	String strSQL="select t.cmso, t.dmso from dbmso10 t where t.bmsoestareg = '1' order by t.dmso asc ";

	try {
	  stmt = p_con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  while (rs.next()) {
		Vector v = new Vector();
		v.add(0, rs.getString(1));
		v.add(1, rs.getString(2));
		vListado.add(v);
	  }
	  rs.close();rs=null;
	  stmt.close();stmt=null;
	}
	catch (SQLException e) {
	  logger.debug("Error al llamar procedimiento getSolicitud " + e.getMessage());
	} 
	finally {
	  conexion.closeResultSet(rs);
	  conexion.closeStatement(stmt);
	}   
	return vListado;
  }  
  
  public static Vector getCodigo ( Connection p_con ) {

	Vector vListado = new Vector();
	Statement  stmt    = null;
	ResultSet  rs      = null;                                    
  
	String strSQL="select t.casu, t.nasupzo, t.dasudes, t.cusu from dbasu10 t, dbasu20 a, dbasu30 b ";
	strSQL= strSQL + "where t.casuestareg = '1' and t.cmateria = a.cmateria(+) and a.bestadoreg = '1' ";
	strSQL= strSQL + "and a.cgrupo = b.cgrupo(+) and b.bestadoreg = '1' order by t.casu asc ";
	
	//logger.debug("strSQL= "+strSQL);

	try {
	  stmt = p_con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  while (rs.next()) {
		Vector v = new Vector();
		v.add(0, rs.getString(1));
		v.add(1, rs.getString(2));
		v.add(2, rs.getString(3));
		v.add(3, rs.getString(4));
		vListado.add(v);
	  }
	  
	}
	catch (SQLException e) {
	  logger.debug("Error al llamar procedimiento getCodigo " + e.getMessage());
	} 
	finally {
	  conexion.closeResultSet(rs);
	  conexion.closeStatement(stmt);
	}   
	return vListado;
  }  
  
  public static Vector getEnlarehoscod ( Connection p_con ) {//dmare10 t 1

	Vector vListado = new Vector();
	Statement  stmt    = null;
	ResultSet  rs      = null;                                    
  
	String strSQL="select t.enlarehoscod, (select a.arehosdes from dbarea a where a.enlarehoscod=t.enlarehoscod)  from dmare10 t where t.estadoreg = '1' group by t.enlarehoscod, t.arehosdes order by t.arehosdes asc ";

	try {
	  stmt = p_con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  while (rs.next()) {
		Vector v = new Vector();
		v.add(0, rs.getString(1));
		v.add(1, rs.getString(2));
		vListado.add(v);
	  }
	  
	}
	catch (SQLException e) {
	  logger.debug("Error al llamar procedimiento getEnlareoscod " + e.getMessage());
	} 
	finally {
	  conexion.closeResultSet(rs);
	  conexion.closeStatement(stmt);
	}   
		return vListado;
  }  
  
  public static Vector getEnlservhoscod ( Connection p_con ) {//dmare10 t 2

	Vector vListado = new Vector();
	Statement  stmt    = null;
	ResultSet  rs      = null;                                    
  
	String strSQL="select t.enlservhoscod, t.servhosdes from dmare10 t where t.estadoreg = '1' group by t.enlservhoscod, t.servhosdes order by t.servhosdes asc ";

	try {
	  stmt = p_con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  while (rs.next()) {
		Vector v = new Vector();
		v.add(0, rs.getString(1));
		v.add(1, rs.getString(2));
		vListado.add(v);
	  }
	  
	}
	catch (SQLException e) {
	  logger.debug("Error al llamar procedimiento getEnlservhoscod " + e.getMessage());
	} 
	finally {
	  conexion.closeResultSet(rs);
	  conexion.closeStatement(stmt);
	}   
		return vListado;
  }  
  
  public static Vector getRed ( Connection p_con ) {

	Vector vListado = new Vector();
	Statement  stmt    = null;
	ResultSet  rs      = null;                                    
  
	String strSQL="select t.cras, t.drasmed from dmras10 t where t.brasestareg = '1' order by t.drasmed asc ";

	try {
	  stmt = p_con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  while (rs.next()) {
		Vector v = new Vector();
		v.add(0, rs.getString(1));
		v.add(1, rs.getString(2));
		vListado.add(v);
	  }
	  
	}
	catch (SQLException e) {
	  logger.debug("Error al llamar procedimiento getRed " + e.getMessage());
	} 
	finally {
	  conexion.closeResultSet(rs);
	  conexion.closeStatement(stmt);
	}   
	return vListado;
  }  
  
  public static Vector getCas ( Connection p_con ) {

	Vector vListado = new Vector();
	Statement  stmt    = null;
	ResultSet  rs      = null;                                    
  
	String strSQL = "select t.cras, t.ccas, t.dcascor from dmcas10 t where t.bcasestareg = '1' order by t.dcas asc ";

	try {
	  stmt = p_con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  while (rs.next()) {
		Vector v = new Vector();
		v.add(0, rs.getString(1));
		v.add(1, rs.getString(2));
		v.add(2, rs.getString(3));
		vListado.add(v);
	  }
	  
	}
	catch (SQLException e) {
	  logger.debug("Error al llamar procedimiento getCas " + e.getMessage());
	} 
	finally {
	  conexion.closeResultSet(rs);
	  conexion.closeStatement(stmt);
	}   
	return vListado;
  }  
 
  public static Vector getEstado ( Connection p_con ) {

	Vector vListado = new Vector();
	Statement  stmt    = null;
	ResultSet  rs      = null;                                    
  
	String strSQL="select t.cefi, t.defi from dbefi10 t where t.befiestareg = '1' order by t.defi asc ";

	try {
	  stmt = p_con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  while (rs.next()) {
		Vector v = new Vector();
		v.add(0, rs.getString(1));
		v.add(1, rs.getString(2));
		vListado.add(v);
	  }
	  
	}
	catch (SQLException e) {
	  logger.debug("Error al llamar procedimiento getEstado " + e.getMessage());
	} 
	finally {
	  conexion.closeResultSet(rs);
	  conexion.closeStatement(stmt);
	}   
	return vListado;
  }  
  
  public static Vector getTingreso (Connection p_con) {

		Vector vListado = new Vector();
		Statement  stmt = null;
		ResultSet  rs   = null; 
		String strSQL		= "";
		strSQL="select t.ctin, t.dtin from dbtin10 t where t.btinestareg = '1'  order by t.dtin asc  ";
	try {
	  stmt = p_con.createStatement();
	  rs = stmt.executeQuery(strSQL);
	  while (rs.next()) {
		Vector v = new Vector();
		v.add(0, rs.getString(1));
		v.add(1, rs.getString(2));
		vListado.add(v);
	  }
	  
	}
	catch (SQLException e) {
	  logger.debug("Error al llamar procedimiento getTingreso " + e.getMessage());
	} 
	finally {
	  conexion.closeResultSet(rs);
	  conexion.closeStatement(stmt);
	}   
	return vListado;
  }

	public static clases.Listhelp_t getListhelp(Connection pcon, clases.Listhelp_t objLis, String pSql)
	throws SQLException {
	  try {
			beans.ListaRegistros lstRegistros = new beans.ListaRegistros();
			lstRegistros.setConnection(pcon);
			lstRegistros.setNumRegistros(objLis.getiRegxPag());
			objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), pSql, false));
			objLis.setPaginacion(new util.Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
	  } 
	  catch (Throwable exception) {
			logger.debug("Error Throwable en BDTablas getListhelp " + exception.getMessage()+ " " ) ;   
			objLis.setError("Disculpenos por favor no se pudo obtener informacion, se presentaron errores <br>");
	  }
	  return objLis;
	}


// para los listbox
	public static Vector getMaterias ( Connection p_con ) {

	  Vector vListado = new Vector();
	  Statement  stmt = null;
	  ResultSet  rs   = null;                                    
	  String strSQL="select t.cmateria, t.dmateria from dbasu20 t, dbasu30 a where t.bestadoreg = '1' "; 	  strSQL= strSQL + "and t.cgrupo = a.cgrupo(+) and a.bestadoreg = '1' order by t.dmateria asc ";
	  try {
			stmt = p_con.createStatement();
			rs = stmt.executeQuery(strSQL);
			while (rs.next()) {
			  Vector v = new Vector();
			  v.add(0, rs.getString(1));
			  v.add(1, rs.getString(2));
			  vListado.add(v);
			}
			
	  }
	  catch (SQLException e) {
			logger.debug("Error al llamar procedimiento getMaterias " + e.getMessage());
	  } 
	  finally {
			conexion.closeResultSet(rs);
			conexion.closeStatement(stmt);
	  }   
	  return vListado;
	}  

//	para los listbox
	public static Vector getGrupos ( Connection p_con ) {

	  Vector vListado = new Vector();
	  Statement  stmt = null;
	  ResultSet  rs   = null;                                    
	  String strSQL="select t.cgrupo, t.dgrupo from dbasu30 t where t.bestadoreg = '1' order by t.cgrupo ";
	  try {
			stmt = p_con.createStatement();
			rs = stmt.executeQuery(strSQL);
			while (rs.next()) {
			  Vector v = new Vector();
			  v.add(0, rs.getString(1));
			  v.add(1, rs.getString(2));
			  vListado.add(v);
			}
			rs.close();rs=null;
			stmt.close();stmt=null;
	  }
	  catch (SQLException e) {
			logger.debug("Error al llamar procedimiento getGrupos " + e.getMessage());
	  } 
	  finally {
			conexion.closeResultSet(rs);
			conexion.closeStatement(stmt);
	  }   
	  return vListado;
	}
	//lista de motivos de cierta ficha determinada
	public static String getcMotivos(Connection p_con,Ficha_t obj){
		String cmotivos="";
		Statement stmt=null;
		ResultSet rs=null;
		StringBuffer query=new StringBuffer();
		query.append("select t.casu from tmotivos t where t.cas="+obj.getcas()+" and t.ayear="+obj.getayear()+" and t.correl="+obj.getcorrel());
		try{
			stmt=p_con.createStatement();
			rs=stmt.executeQuery(query.toString());
			while(rs.next()){
				cmotivos=cmotivos+rs.getString(1)+" ";
			}
			stmt.close();
			stmt=null;
			rs.close();
			rs=null;
		}catch(SQLException e){
			logger.debug("Error al recuperar la lista de motivos. Problem: "+e.getMessage());
			
		}finally{
			conexion.closeResultSet(rs);
			conexion.closeStatement(stmt);
		
		}
		return cmotivos.trim();
	
	} 
	
	//lista de descripciones de motivos de una ficha determinada
	public static String getdMotivos(Connection p_con,Ficha_t obj){
			String dmotivos="";
			Statement stmt=null;
			ResultSet rs=null;
			StringBuffer query=new StringBuffer();
			query.append("select (select b.dmateria from dbasu20 b where a.cmateria = b.cmateria) || ' => ' || a.dasudes from tmotivos t,dbasu10 a where t.casu=a.casu and t.cas="+obj.getcas()+" and t.ayear="+obj.getayear()+" and t.correl="+obj.getcorrel());
			logger.debug("sql getdMotivos: "+query.toString());
			try{
				stmt=p_con.createStatement();
				rs=stmt.executeQuery(query.toString());
				while(rs.next()){
					dmotivos=dmotivos+rs.getString(1)+"???";
				}
				
			}catch(SQLException e){
				logger.debug("Error al recuperar la lista de motivos. Problam: "+e.getMessage());
			
			}finally{
				conexion.closeResultSet(rs);
				conexion.closeStatement(stmt);
		
			}
			return dmotivos.trim();
	
		}

	public static Object getPerfil(Connection p_con, String strPerfil) {

	  Vector vListado = new Vector();
	  Statement  stmt    = null;
	  ResultSet  rs      = null;                                    
  
	  String strSQL="select t.cperfil, t.dperfil from tperfiles t where t.bestadoreg = '1' ";
	  /*if(strPerfil.compareTo("0")!=0){                                   
			strSQL= strSQL + "and t.cperfil not in ('0') ";
	  }  */
	  strSQL= strSQL + "order by t.dperfil asc ";
	  
	  try {
			stmt = p_con.createStatement();
			rs = stmt.executeQuery(strSQL);
			while (rs.next()) {
			  Vector v = new Vector();
			  v.add(0, rs.getString(1));
			  v.add(1, rs.getString(2));
			  vListado.add(v);
			}
			
	  }
	  catch (SQLException e) {
			logger.debug("Error al llamar procedimiento getPerfil " + e.getMessage());
	  } 
	  finally {
			conexion.closeResultSet(rs);
			conexion.closeStatement(stmt);
	  }   
	  return vListado;
	}
	
	//tabla tficheros no esta implemntada aun 28082014
	public static Vector getvExp(Connection p_con,String cas,String ayear,String correl) {

			Vector vListado = new Vector();
			Statement  stmt = null;
			ResultSet  rs   = null; 
			String strSQL		= "";
			strSQL="select t.dfnomb from tficheros t where t.cas='"+
						  cas+"' and t.ayear='"+
							ayear+"' and t.correl='"+
							correl+"' order by t.fusucrea";
		try {
		  stmt = p_con.createStatement();
		  rs = stmt.executeQuery(strSQL);
		  while (rs.next()) {
			vListado.add(rs.getString(1));
		  }
		  
		}
		catch (SQLException e) {
		  logger.debug("Error al llamar procedimiento getvExp " + e.getMessage());
		} 
		finally {
		  conexion.closeResultSet(rs);
		  conexion.closeStatement(stmt);
		}   
		return vListado;
	  }
	
	public static Vector getAyear () {

		Vector vListado = new Vector();
		Calendar cal=Calendar.getInstance();                         
	  int year=cal.get(Calendar.YEAR);
	  for(int i=0;i<5;i++){
		  String ob=(year-i)+"";
		  vListado.add(ob);
	  }
		return vListado;
	}

	public static Vector getcmet(Connection p_con,String strCod) {
		Vector vListado = new Vector();
		
		ResultSet  rs   = null; 
		CallableStatement cs = null;
		try {
			cs = (CallableStatement)
					p_con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getTematica(?); END;");
				      cs.registerOutParameter(1, OracleTypes.CURSOR);
							cs.setString(2, strCod);
							cs.executeUpdate();
				      rs = (ResultSet) cs.getObject(1);
				      
				      while (rs.next()) {
							Vector aux=new Vector();
							aux.add(rs.getString(1));
							aux.add(rs.getString(2));
							vListado.add(aux);
					  }
				      
		  
		}
		catch (SQLException e) {
		  logger.debug("Error al llamar procedimiento getcmet " + e.getMessage());
		} 
		finally {
		  conexion.closeResultSet(rs);
		  conexion.closeStatement(cs);
		}   
		return vListado;
  }
	
	public static Vector getConclusiones(Connection p_con,String strCod) {
		Vector vListado = new Vector();
		
		ResultSet  rs   = null; 
		CallableStatement cs = null;
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement)
					//p_con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getConclusiones(?); END;");
					p_con.prepareStatement("SELECT  t.cconclu, t.dconclu FROM     tconclusiones t WHERE  " +
							"  t.cmso    = ? and t.estadreg = '1' ORDER BY t.dconclu ASC");
				     // cs.registerOutParameter(1, OracleTypes.CURSOR);
			ps.setString(1, strCod);
			rs =	ps.executeQuery();
				    //  rs = (ResultSet) cs.getObject(1);
				      
				      while (rs.next()) {
				    	
							Vector aux=new Vector();
							aux.add(String.valueOf(rs.getInt(1)));
							aux.add(rs.getString(2));
							vListado.add(aux);
					  }
				      
		  
		}
		catch (SQLException e) {
		  logger.debug("Error al llamar procedimiento getConclusiones " + e.getMessage());
		} 
		finally {
		  conexion.closeResultSet(rs);
		  conexion.closeStatement(cs);
		}   
		return vListado;
  }

	
	public static Vector getAccPersuas(Connection p_con) {
		Vector vListado = new Vector();
		
		ResultSet  rs   = null; 
		CallableStatement cs = null;
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement)
					//p_con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getConclusiones(?); END;");
					p_con.prepareStatement("SELECT  t.cper, t.dper FROM   taccpersuas t  WHERE  " +
							"   t.estadreg = '1' ORDER BY t.dper ASC");
				     // cs.registerOutParameter(1, OracleTypes.CURSOR);
			//ps.setString(1, strCod);
			rs =	ps.executeQuery();
				    //  rs = (ResultSet) cs.getObject(1);
				      
				      while (rs.next()) {
				    	
							Vector aux=new Vector();
							aux.add(String.valueOf(rs.getInt(1)));
							aux.add(rs.getString(2));
							vListado.add(aux);
					  }
				      
		  
		}
		catch (SQLException e) {
		  logger.debug("Error al llamar procedimiento getConclusiones " + e.getMessage());
		} 
		finally {
		  conexion.closeResultSet(rs);
		  conexion.closeStatement(cs);
		}   
		return vListado;
  }

	
	
	public static Vector getAccPersuas__(Connection p_con,String strCod) {
		Vector vListado = new Vector();
		
		ResultSet  rs   = null; 
		CallableStatement cs = null;
		try {
			cs = (CallableStatement)
					p_con.prepareCall("BEGIN ? := PKG_TABLAS.fn_getAccPersuas(?,?); END;");
				      cs.registerOutParameter(1, OracleTypes.CURSOR);
							cs.setString(2, strCod);
							cs.executeUpdate();
				      rs = (ResultSet) cs.getObject(1);
				      
				      while (rs.next()) {
							Vector aux=new Vector();
							aux.add(rs.getString(1));
							aux.add(rs.getString(2));
							vListado.add(aux);
					  }
				      
		  
		}
		catch (SQLException e) {
		  logger.debug("Error al llamar procedimiento getConclusiones " + e.getMessage());
		} 
		finally {
		  conexion.closeResultSet(rs);
		  conexion.closeStatement(cs);
		}   
		return vListado;
  }

	  public static Vector getAccReal ( Connection p_con ) {

			Vector vListado = new Vector();
			Statement  stmt    = null;
			ResultSet  rs      = null;                                    
		  
			String strSQL="select to_char(t.idaccreal), t.descaccreal from taccreal t where t.estareg = '1' ";
//order by t.descaccreal asc
			try {
			  stmt = p_con.createStatement();
			  rs = stmt.executeQuery(strSQL);
			  while (rs.next()) {
				Vector v = new Vector();
				v.add(0, rs.getString(1));
				v.add(1, rs.getString(2));
				vListado.add(v);
			  }
			  
			}
			catch (SQLException e) {
			  logger.debug("Error al llamar procedimiento getAccion " + e.getMessage());
			} 
			finally {
			  conexion.closeResultSet(rs);
			  conexion.closeStatement(stmt);
			}   
			return vListado;
		  }  
	
	  
	  public static Vector getAccSeg ( Connection p_con ) {

			Vector vListado = new Vector();
			Statement  stmt    = null;
			ResultSet  rs      = null;                                    
		  
			String strSQL="select to_char(t.idaccseg), t.daccseg  FROM taccseg t WHERE t.bestadreg = '1' ORDER BY t.daccseg ASC ";

			try {
			  stmt = p_con.createStatement();
			  rs = stmt.executeQuery(strSQL);
			  while (rs.next()) {
				Vector v = new Vector();
				v.add(0, rs.getString(1));
				v.add(1, rs.getString(2));
				vListado.add(v);
			  }
			  
			}
			catch (SQLException e) {
			  logger.debug("Error al llamar procedimiento getAccion " + e.getMessage());
			} 
			finally {
			  conexion.closeResultSet(rs);
			  conexion.closeStatement(stmt);
			}   
			return vListado;
		  }

	  public static Vector getDelegado ( Connection p_con, String pcodigo, String ccas, String asignado ) {

			Vector vListado = new Vector();
			Statement  stmt    = null;
			ResultSet  rs      = null;                                    
		    StringBuffer sb = new StringBuffer();
			
			
			sb.append("SELECT  t.cusu, t.dapepatusu || ' ' || t.dapematusu  || ' ' || t.dnomusu  " +
					"FROM     tusuarios t , DMCAS10 C, DMRAS10 R where    c.cras = r.cras  " +
					"and t.ccas = c.ccas and t.busuestareg  = '1' " + " and c.cras = '"+ pcodigo+ "' and c.ccas = '"+ccas + "' ");
			
			/*String strSQL="SELECT  t.cusu, t.dapepatusu || ' ' || t.dapematusu  || ' ' || t.dnomusu  " +
					"FROM     tusuarios t , DMCAS10 C, DMRAS10 R where    c.cras = r.cras  " +
					"and t.ccas = c.ccas and t.busuestareg  = '1' " + " and c.cras = '"+ pcodigo+ "' and c.ccas = '"+ccas + "' "+*/
			
			if(StringUtils.isNotEmpty(asignado)){
				sb.append("  and t.cusunivacc = '08' " ); 
			}	
			
			sb.append(" ORDER BY t.dapepatusu ASC ");
			logger.debug(" getDelegado " + sb.toString());
			try {
			  stmt = p_con.createStatement();
			  rs = stmt.executeQuery(sb.toString());
			  while (rs.next()) {
				Vector v = new Vector();
				v.add(0, rs.getString(1));
				v.add(1, rs.getString(2));
				vListado.add(v);
			  }
			  
			}
			catch (SQLException e) {
			  logger.debug("Error al llamar procedimiento getSolicitud " + e.getMessage());
			} 
			finally {
			  conexion.closeResultSet(rs);
			  conexion.closeStatement(stmt);
			}   
			return vListado;
		  }  
	  
	  public static Vector getCas ( Connection p_con, String codigo ) {

			Vector vListado = new Vector();
			Statement  stmt    = null;
			ResultSet  rs      = null;                                    
		  
			String strSQL = "select t.ccas, t.dcascor from dmcas10 t where t.cras = '"+codigo+"' and t.bcasestareg = '1' order by t.dcas asc ";
			logger.debug(" strSQL " + strSQL);
			try {
			  stmt = p_con.createStatement();
			  rs = stmt.executeQuery(strSQL);
			  while (rs.next()) {
				Vector v = new Vector();
				v.add(0, rs.getString(1));
				v.add(1, rs.getString(2));
				vListado.add(v);
			  }
			  
			}
			catch (SQLException e) {
			  logger.debug("Error al llamar procedimiento getCas " + e.getMessage());
			} 
			finally {
			  conexion.closeResultSet(rs);
			  conexion.closeStatement(stmt);
			}return vListado;
	  }
	  
	  //Obtener Lista de area
	  public static Vector getListArea ( Connection p_con ) {

			Vector vListado = new Vector();
			Statement  stmt    = null;
			ResultSet  rs      = null;                                    
		  
			String strSQL="select t.enlarehoscod, t.arehosdes from dbarea t where t.estadoreg = '1' order by t.arehosdes asc ";

			try {
			  stmt = p_con.createStatement();
			  rs = stmt.executeQuery(strSQL);
			  while (rs.next()) {
				Vector v = new Vector();
				v.add(0, rs.getString(1));
				v.add(1, rs.getString(2));
				vListado.add(v);
			  }
			  
			}
			catch (SQLException e) {
			  logger.debug("Error al llamar procedimiento getListArea " + e.getMessage());
			} 
			finally {
			  conexion.closeResultSet(rs);
			  conexion.closeStatement(stmt);
			}   
				return vListado;
		  }
	  
	  public static String getRedXCas ( Connection p_con, String codigo ) {

			
			Statement  stmt    = null;
			ResultSet  rs      = null;                                    
		  
			String strSQL = "select t.cras from dmcas10 t where t.ccas = '"+codigo+"' and t.bcasestareg = '1' order by t.dcas asc ";
			logger.debug(" strSQL " + strSQL);
			String cred = "";
			try {
			  stmt = p_con.createStatement();
			  rs = stmt.executeQuery(strSQL);
			  if (rs.next()) {
				  cred = rs.getString(1);
				  
			  }
			  
			}
			catch (SQLException e) {
			  logger.debug("Error al llamar procedimiento getCas " + e.getMessage());
			} 
			finally {
			  conexion.closeResultSet(rs);
			  conexion.closeStatement(stmt);
			}return cred;
	  }	
}