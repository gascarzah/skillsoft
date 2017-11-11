package control;
/*
  *****************************************************************
  CtrlHelp.java 
  Este servlet extrae la lista de registros de las tablas de codificación.
  Modificaciones:
  acahuana		04-04-2002 : creación del esqueleto del programa con comentarios. 
  privera		  21-04-2004
  cperaltilla	16-07-2007
 ******************************************************************
*/

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import bd.conexion;
import beans.WebUtil;

public class CtrlHelp extends HttpServlet implements Serializable {
	private static Logger logger = Logger.getLogger(CtrlHelp.class);
  public void init (ServletConfig conf) throws ServletException {
	super.init(conf);
  }    
  public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	  HttpSession session  = req.getSession(true);
	  Connection con = conexion.getConnection();	
	  if (con == null) {
			req.setAttribute("msg_error", "CtrlHelp: Error de Conexión a DB.");		
			WebUtil.goForward(getServletContext(), req, res, "/error/error.jsp");
			return;
	  }
	/*	*/
  } //Fin de la función doPost
  
  public void doGet (HttpServletRequest req, HttpServletResponse res)	throws IOException, ServletException {
	  doPost(req,res);
  } //Fin de la función doGet
  
  public void destroy()  {
    super.destroy();
  }

} // Fin de la clase
