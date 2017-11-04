
package control;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import bd.Ficha;
import bd.conexion;
import beans.WebUtil;
import clases.ListFicha_t;
import clases.UsuarioSistema;

public class CtrlReporte extends HttpServlet implements Serializable {
	private static Logger logger = Logger.getLogger(CtrlReporte.class);
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
    
	public void destroy() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session = request.getSession(true);
	  response.setContentType("text/html");
	  UsuarioSistema usuario = (UsuarioSistema)session.getAttribute("sClusuario");
	  if(usuario == null) {
			request.setAttribute("msg_error", "La sesion ha finalizado. Ingrese de nuevo.");
			WebUtil.goForward(getServletContext(), request, response, "/error/cerrar.jsp");
			return;
	  }
	  Connection con = conexion.getConnection();
	  if (con == null) {
			request.setAttribute("msg_error", "CtrlReporte: Error de Conexión a DB.");
			WebUtil.goForward(getServletContext(), request, response, "/error/error.jsp");
			return;
	  }

	  String opt = request.getParameter("opt") == null ? "" : request.getParameter("opt");
	  String upd = request.getParameter("upd") == null ? "" : request.getParameter("upd");
	  String strUrl="/error/error.jsp";
		try{
			ListFicha_t lstFicha = new ListFicha_t();
			lstFicha.setcred(request.getParameter("CRED")!=null?request.getParameter("CRED"):"");
			lstFicha.setccas(request.getParameter("CCAS")!=null?request.getParameter("CCAS"):"");
			lstFicha.setFechaInicio(request.getParameter("FECHA1")!=null?request.getParameter("FECHA1"):"");
			lstFicha.setFechaFin(request.getParameter("FECHA2")!=null?request.getParameter("FECHA2"):"");
			lstFicha.setestado(request.getParameter("ESTADO")!=null?request.getParameter("ESTADO"):"");
			lstFicha.setiRegxPag(65537); //EXCEL SOLO PROPORCIONA 65536 FILAS, SI SE EXCEDE EL ARCHIVO ENVIA UN AVISO
			lstFicha.setPag(request.getParameter("pg")!=null?request.getParameter("pg"):"1");
			
			
			
			
			Ficha objFicha = new Ficha();
			if(upd.equals("EXPORT")){// Exportar a excel
			//Map map = objFicha.obtenerCondiciones(lstFicha);
				//lstFicha = objFicha.getLisFicha(con, lstFicha,map);
				List lista = objFicha.getListaReporteGeneral(con, lstFicha);
				lstFicha.setLista(lista);
				if (objFicha.isOK()) {
					request.setAttribute("objLst",lstFicha);
					//request.setAttribute("parametros",map);
					strUrl = "/consultas/rp_excel_ex.jsp";
					//strUrl = "/jsp/reportes/reporteGeneral.jsp";
					response.setContentType ("text/html;charset=utf-8");
				}
				else {
					request.setAttribute("msg_error", objFicha.getStringError());
					strUrl = "/error/cerrar.jsp";
				}
			}
			
			else{
				request.setAttribute("msg_error","CtrlReporte: No encontró upd " + objFicha.getStringError());
				strUrl = "/error/error.jsp";
			}
			objFicha=null;
			lstFicha=null;
		WebUtil.goForward(getServletContext(), request, response, strUrl);
		}
	  catch(Exception e) {
		  
			request.setAttribute("msg_error"," Error en Servlet CtrlReporte <br> " + e.getMessage());
			WebUtil.goForward(getServletContext(), request, response, "/error/cerrar.jsp");
			logger.debug("Error Servlet CtrlReporte [" + getClass().getName() + "]. " + e.getMessage());
	  } 
	  finally {
			conexion.closeConnection(con);
	  }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
			doGet(request, response);
		}
  }

