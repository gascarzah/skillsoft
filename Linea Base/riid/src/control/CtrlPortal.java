
package control;

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

import bd.BDTablas;
import bd.Ficha;
import bd.conexion;
import beans.WebUtil;
import clases.Ficha_t;



public class CtrlPortal extends HttpServlet implements Serializable {
	private static Logger logger = Logger.getLogger(CtrlPortal.class);
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}   
	public void destroy() {}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		res.setContentType("text/html");
//		UsuarioSistema usuario = (UsuarioSistema)session.getAttribute("sClusuario");
		Connection con = conexion.getConnection();
		if (con == null) {
			req.setAttribute("msg_error", "CtrlFicha: Error de Conexión a DB.");
			WebUtil.goForward(getServletContext(), req, res, "/error/error.jsp");
			return;
		}
		String strOpt = req.getParameter("opt")!=null?req.getParameter("opt"):"";
//		String strPerfil = usuario.getPerfil();
		String strUrl="/error/error.jsp";
		
		try{

			//req.setAttribute ("vConsejeros", BDTablas.getConsejeros(con));
			req.setAttribute ("vDepartamento", BDTablas.getDepartamento(con));
			req.setAttribute ("vProvincia", BDTablas.getProvincia(con));
			req.setAttribute ("vDistrito", BDTablas.getDistrito(con));
			req.setAttribute ("vDocumento", BDTablas.getIdentidad(con));
			req.setAttribute ("vPrestacion", BDTablas.getPrestacion(con));
			req.setAttribute ("vSeguro", BDTablas.getSeguro(con));
			req.setAttribute ("vSolicitud", BDTablas.getSolicitud(con));
			req.setAttribute ("vCodigo", BDTablas.getCodigo(con));
			req.setAttribute ("vRed", BDTablas.getRed(con));
			req.setAttribute ("vCas", BDTablas.getCas(con));
			//req.setAttribute ("vAccion", BDTablas.getAccion(con));
			req.setAttribute ("vEstado", BDTablas.getEstado(con));
			//req.setAttribute ("vEnlarehoscod", BDTablas.getEnlarehoscod(con));30/09/2014
			//req.setAttribute ("vEnlservhoscod", BDTablas.getEnlservhoscod(con));
//			req.setAttribute ("vTingreso", BDTablas.getTingreso(con, strPerfil));

			Ficha objFicha = new Ficha();
			Ficha_t obj = new Ficha_t();
			
			
		  if(strOpt.compareTo("OK")==0){
				objFicha.FichaUpdate(con, obj, "M", "I");
				
				objFicha = null; 
				obj = null;
		  }
		  else if(strOpt.compareTo("PORTAL")==0){
				strUrl="/jsp/ficha2.jsp";
		  }
		  else {
				req.setAttribute("msg_error"," CtrlFicha: Error en la grabacion ");
				strUrl = "/error/error.jsp";
		  }
			WebUtil.goForward(getServletContext(), req, res, strUrl);
		}
		catch(Exception e) {
		  req.setAttribute("msg_error","Error en Servlet CtrlPortal <br> " + e.getMessage());
		  WebUtil.goForward(getServletContext(), req, res, "/error/error.jsp");
		  logger.debug("CtrlPortal: Error[" + getClass().getName() + "]." + e.getMessage());
		} 
		finally {
		  conexion.closeConnection(con);
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
}
