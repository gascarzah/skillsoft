
package clases;

//import java.sql.CallableStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import bd.conexion;
public  class BDUsuarioSistema {
  private static Logger logger = Logger.getLogger(BDUsuarioSistema.class);
  protected static Connection con =null;
  protected static String SQL_SELECT ="select a.cusu, a.cusunivacc, a.dapepatusu||' '||a.dapematusu||' '||a.dnomusu, a.ccas, a.cusucla, a.bworkflow, sysdate, b.dperfil, c.dcascor , (select d.cras from dmras10 d where d.cras = c.cras) from tusuarios a, tperfiles b, dmcas10 c where a.busuestareg = '1' and b.bestadoreg = '1' and a.cusunivacc = b.cperfil and a.ccas = c.ccas and a.ccasori = c.ccasori and "; 
  protected UsuarioSistema bean;
  protected String message="";

  public BDUsuarioSistema(){ 
   if (bean == null) bean = new UsuarioSistema();
   this.setBean(bean);
  }
  
  public UsuarioSistema getBean() {
  return bean;
  }

  public void setBean(UsuarioSistema pbean) {
    bean=pbean;
  }

  private boolean bOK = false;
 

  public boolean isOK() {
	return bOK;
  }

  public void BuscaUsuario(String p_strUsuario, String p_strPasswd, String p_caso) {
		if (con==null) con = conexion.getConnection();
		if (con==null) { setMessage("BDUsuarioSistema: Error de Conexión a DB.");  return;}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	  try {
			if (p_caso.compareTo("index")==0) {
				pstmt=con.prepareStatement(SQL_SELECT + "a.cusu = '"+ p_strUsuario +"' and a.cusucla = '"+ p_strPasswd +"'");
	    } 
	    else {
	    	pstmt=con.prepareStatement(SQL_SELECT + "a.cusu = '"+ p_strUsuario +"'" );
	    }
	    //logger.debug("SQL_SELECT= "+SQL_SELECT);
	    rs = pstmt.executeQuery();
	    if(rs.next()) {
				bean.setUsuario(rs.getString(1));
				bean.setPerfil(rs.getString(2));
				bean.setDescripcion(rs.getString(3));
				bean.setCcas(rs.getString(4));
				bean.setClave(rs.getString(5));
				bean.setBworkflow(rs.getString(6));
				bean.setdPerfil(rs.getString(8));
				bean.setdCas(rs.getString(9));
				bean.setCras(rs.getString(10));
	 		}
	    else {
	    	setMessage("BDUsuarioSistema: Usuario no existe, contraseña inválida o el usuario puede estar inhabilitado");
	    }
	    rs.close(); rs=null; pstmt.close();pstmt=null;
	  } 
	  catch (SQLException e) {
	  	setMessage("BDUsuarioSistema: Por Favor Avise al Administrador, error al realizar la busqueda del usuario<br>" + e);
	  } 
		finally {
			conexion.closeResultSet(rs);
			conexion.closePreparedStatement(pstmt);
		}
	}


  public void getActualiza(String pUpd,BeUsuario obj ) throws SQLException {
	  if (con==null) con = conexion.getConnection();
	  if (con==null) { setMessage("Error de Conexión a DB.");  return; }
	  CallableStatement cs = null;
	  logger.debug("entra a actualizar el usuario");
	  try {
			con.setAutoCommit(false);      
			try {
				if(pUpd.equals("cambio")) {
					cs = (CallableStatement)
					con.prepareCall("{ call PKG_USUARIOS.SP_MOD_PASSWORD (?,?) }");
					cs.setString(1, obj.getUsuario());
				 	cs.setString(2, obj.getClave());
			  }
			  cs.executeUpdate();
			  cs.close();cs=null;
			}
			catch (SQLException e) {
			  con.rollback();
			  setMessage("Error al actualizar<br>" + e.getMessage());
			}
	  }
    catch (SQLException e) {
	  	setMessage("Error al deshabilitar el modo AutoCommit " );
   	}
   	finally {
	 		conexion.closeStatement(cs);
   	}      
	}

  public void closeBDUsuarioSistema() {
	if (con !=null) {
	  try {con.close();} catch (SQLException e) { }con=null; 
	} 
	if (bean!=null) { 
	  bean=null ;
	} 
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String pmessage) {
	message=pmessage;
  }

	public Tablas_t getRestart(String strUser, String strCuser, Tablas_t lst) throws SQLException {
		if (con == null)
		  con = conexion.getConnection();
		if (con == null) {
		  setMessage("BDUsuarioSistema: Error de Conexión a DB.");
		  return null;
		}
		Statement stmt = null;
		String strSQL = "";
		try {
		  stmt = con.createStatement();
		  strSQL = "update tusuarios t set t.cusucla = '202cb962ac59075b964b07152d234b70' where  t.cusu = '"+strUser+"' ";
		  stmt.executeUpdate(strSQL);
		  //logger.debug(strSQL);
		  bOK = true;
		}
		catch (SQLException e) {
		  logger.debug(strSQL);
		  logger.debug("Error en getRestart");
		}
		stmt.close();
		stmt = null;
		strSQL = null;
		return lst;
	}

}
