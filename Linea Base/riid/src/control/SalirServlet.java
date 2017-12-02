
package control;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.WebUtil;

public class SalirServlet extends HttpServlet implements Serializable {
	public SalirServlet() {
	}

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		
		String strUrl = null;
		String strUpd = req.getParameter("upd") != null ? req
				.getParameter("upd") : "";

				try {
				//	UsuarioSistema usuario = (UsuarioSistema) session.getAttribute("sClusuario");

			if (strUpd.compareTo("Salir") == 0) {
				session.removeAttribute("sClusuario");
				req.setAttribute("msg_error", "Usted ha salido del sistema.");
				strUrl = "/jsp/index_salir.jsp";
				 
			}

			WebUtil.goForward(getServletContext(), req, res, strUrl);
		} catch (Exception e) {
			req.setAttribute(
					"msg_error",
					"Se ha presentado un error en la pagina[Index],Por favor Vuelva a Ingresar <br>"
							+ e.getMessage());
			WebUtil.goForward(getServletContext(), req, res,
					"/error/error_general.jsp");
		} finally {

		}
		return;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}

	public void destroy() {
	}
}