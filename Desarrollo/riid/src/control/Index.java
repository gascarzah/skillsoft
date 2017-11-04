
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
import beans.WebUtil;
import clases.BDUsuarioSistema;

public class Index extends HttpServlet implements Serializable{
	private static Logger logger = Logger.getLogger(Index.class);
    public Index()   { }
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
      HttpSession session = req.getSession(true);
      String strUrl = null;
		  String strUpd = req.getParameter("upd") != null ? req.getParameter("upd") : "";
		  String strUser = String.valueOf(req.getParameter("USUARIO"))==null?"":String.valueOf(req.getParameter("USUARIO"));
		  String strPass = String.valueOf(req.getParameter("CLAVE"))==null?"":String.valueOf(req.getParameter("CLAVE"));
		  String strTass = "";
      BDUsuarioSistema objProce = new BDUsuarioSistema();
	  	BDSeguridad objSecur = new BDSeguridad();
      try  {
				strTass = objSecur.getMD5(strPass);
				//logger.debug("strPass= "+strPass+" strTass= "+strTass);
        objProce.BuscaUsuario(strUser, strTass, "index");
        if(objProce.getMessage().compareTo("")==0){
          if(strUpd.compareTo("index")==0) {
            session.setAttribute("sClusuario", objProce.getBean());
            strUrl = "/servlet/CtrlFicha";
          }
          else if(strUpd.compareTo("cambio")==0) {
        	  logger.debug("entre a Cambio");
            session.setAttribute("sClusuario", objProce.getBean());
            strUrl = "/jsp/cambio.jsp";
          } 
				  else if(strUpd.compareTo("Salir")==0) {
						session.setAttribute("sClusuario", objProce.getBean());
						strUrl = "/servlet/CtrlFicha?opt=0";
				  } 
        }
        else {
          session.removeAttribute("sClusuario");
          req.setAttribute("msg_error", objProce.getMessage());
          if(strUpd.compareTo("index")==0) {
            strUrl = "/error/error_general.jsp";
          }
          else if(strUpd.compareTo("cambio")==0) {
            strUrl = "/error/error_pass.jsp";            
          }
        }
        objProce.closeBDUsuarioSistema();
        WebUtil.goForward(getServletContext(), req, res, strUrl);
      } 
      catch (Exception e) {
        req.setAttribute("msg_error", "Se ha presentado un error en la pagina[Index],Por favor Vuelva a Ingresar <br>" + e.getMessage());
        WebUtil.goForward(getServletContext(), req, res, "/error/error_general.jsp");
      } 
      finally {
        objProce.closeBDUsuarioSistema();
        objProce=null;
      }
      return;
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException
    {
        doPost(req, res);
    }
    public void destroy(){ }
}