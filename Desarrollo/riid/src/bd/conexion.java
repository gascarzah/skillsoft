
package bd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
  
  public class conexion{
	  private static Logger logger = Logger.getLogger(conexion.class);
	  private static conexion instance = new conexion();
	  

		public static conexion getInstance() {
			return instance;
		}
	  
    public conexion() {
    }

    public static Connection getConnection() {
      try {
        Context initContext = new InitialContext();
        if(initContext == null) {
          logger.debug("No existe el contexto!!!!");
          return null;
        }
        DataSource ds = (DataSource)initContext.lookup("java:/comp/env/riid");
        if(ds != null) {
          Connection conn = ds.getConnection();
          if(conn == null) {
            Date dateServer = new Date();
            logger.debug("Conexion nula " + dateServer.toString());
          }
          return conn;
        }
      }
      catch(Exception e){
        Date dateServer = new Date();
        logger.debug("Conexion: No encontre conexion BD Defensoria " + e.getMessage() + " " + dateServer.toString());
      }
      return null;
    }

    public static Connection beginTrans(){
      try {
        Connection con = getConnection();
        if(con == null) 
          throw new Exception();
          
        if(con.getAutoCommit()) {
          con.setAutoCommit(false);
          con.setTransactionIsolation(2);
          return con;
        } 
        else {
          return con;
        }
      }
      catch(Exception e) {
        logger.debug("error");
      }
      return null;
    }
    public static void closeConnection(Connection p_Conn) {
      try {
        if (p_Conn != null)  p_Conn.close();
          p_Conn = null;
      }
      catch(Exception e) {
        logger.debug("CloseConnection Defensoria Error: " + e.toString());
      }
      finally {
        if(p_Conn != null) {
          try {
            p_Conn.close();
          }
          catch(Exception e) {
            ;
          }
          p_Conn = null;
        }
      }
      return;
    }

    public static void closeResultSet(ResultSet p_Rs) {
      try {
        if(p_Rs != null) p_Rs.close();
            p_Rs = null;
      }
      catch(Exception e) {
        logger.debug("closeResultSet Defensoria Error :" + e.toString());
      }
      finally {
        if(p_Rs != null) {
          try {
            p_Rs.close();
          }
          catch(Exception e) {
            ;
          }
          p_Rs = null;
        }
      }
      return;
    }

    public static void closeStatement(Statement p_stmt) {
      try {
        if(p_stmt != null) p_stmt.close();
          p_stmt = null;
      }
      catch(Exception e) {
        logger.debug("closeStatement Defensoria Error:" + e.toString());
      }
      finally {
        if(p_stmt != null) {
          try {
            p_stmt.close();
          }
          catch(Exception e) {
            ;
          }
          p_stmt = null;
        }
      }
      return;
    }

    public static void closeCallableStatement(CallableStatement p_stmt) {
      try {
        if(p_stmt != null) p_stmt.close();
          p_stmt = null;
        }
      catch(Exception e) {
        logger.debug("closeCallableStatement Defensoria Error :" + e.toString());
      }
      finally {
        if(p_stmt != null) {
          try {
            p_stmt.close();
          }
          catch(Exception e) {
            ;
          }
          p_stmt = null;
        }
      }
      return;
    }

	public static void closePreparedStatement(PreparedStatement p_stmt){
      try {
        if (p_stmt != null) {
          p_stmt.close(); p_stmt = null;
        } 
      } 
      catch (Exception e) {
        logger.debug("closePreparedStatement Defensoria:"+e.toString());
      } 
      finally {
        if (p_stmt != null){
          try { p_stmt.close(); } catch (Exception e) {;}
          p_stmt = null;
        } 
      }
    }
    
  }
