package control;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import bd.BDSeguridad;
import bd.BeListados;
import beans.WebUtil;
import clases.BDUsuarioSistema;
import clases.BeUsuario;
import clases.Tablas_t;
import clases.UsuarioSistema;

public class CtrlUsuarios extends HttpServlet implements Serializable {
	private static Logger logger = Logger.getLogger(CtrlUsuarios.class);
  public void init(ServletConfig conf)  throws ServletException {
    super.init(conf);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    HttpSession session = req.getSession(true);
    res.setContentType("text/html");
    String strUrl = "";
    UsuarioSistema usuario = (UsuarioSistema)session.getAttribute("sClusuario");
    if(usuario == null) {
      req.setAttribute("msg_error", "La sesion ha finalizado. Ingrese de nuevo.");
      WebUtil.goForward(getServletContext(), req, res, "/error/error_general.jsp");
      return; 
    }
    BDUsuarioSistema objProce = new BDUsuarioSistema();
    try {
			Tablas_t lst = new Tablas_t();
      BeListados objList = (BeListados) session.getAttribute("BeListados");
      if (objList == null){ objList = new BeListados();}
      objList.setPag(req.getParameter("pg")==null?"1":String.valueOf(req.getParameter("pg")).compareTo("")==0?"1":req.getParameter("pg"));
      objList.setiRegxPag(10);
		  BDSeguridad objSecur = new BDSeguridad();
      if(String.valueOf(req.getParameter("upd")!=null?req.getParameter("upd"):"").compareTo("cambio")==0) {
        // ******* actualizar password
        BeUsuario bean = new BeUsuario();
        bean.setUsuario(req.getParameter("USUARIO")!=null?req.getParameter("USUARIO"):"");
        bean.setClave(objSecur.getMD5(req.getParameter("CLAVE")!=null?req.getParameter("CLAVE"):"")); 
        objProce.getActualiza(req.getParameter("upd")!=null?req.getParameter("upd"):"",bean);
        strUrl = "/htm/cerrar.htm";
      }
		  else if(String.valueOf(req.getParameter("upd")!=null?req.getParameter("upd"):"").compareTo("RESTART")==0) {
				String strCuser = String.valueOf(req.getParameter("CUSER"));
				String strUser = String.valueOf(req.getParameter("USER"));
				objProce.getRestart(strUser, strCuser, lst);
				if (objProce.isOK()) {
				  req.setAttribute("msg_error", "La contraseña ha sido reiniciada");
				  strUrl = "/servlet/CtrlMant?opt=3";
				}
				else{
				  req.setAttribute("msg_error", "Usuario o contraseña inválida. Si el problema persite contacte con el Administrador");
				  strUrl = "/error/cerrar.jsp";
				}
		  }
      else {
        objProce.setMessage("Transaccion no valida");
      }
      if(objProce.getMessage().compareTo("")!=0) {
        req.setAttribute("msg_error", objProce.getMessage());    
        strUrl="/error/error.jsp";objList.setError("");
      }
      objProce.closeBDUsuarioSistema();
      WebUtil.goForward(getServletContext(), req, res, strUrl);
    }
    catch (Exception e) {
      objProce.closeBDUsuarioSistema();
      logger.debug("Error [" + getClass().getName() + "]. " + e.getMessage());
      req.setAttribute("msg_error", "Vuelva a ingresar por favor el proceso se cancelo");
      WebUtil.goForward(getServletContext(), req, res, "/error/error.jsp");
    } finally {
      objProce=null;
    }
    
  }

  public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
    doPost(req, res);
  }

  public void destroy() { }
}