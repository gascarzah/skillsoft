
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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import util.Constantes;
import bd.BDTablas;
import bd.Tablas;
import bd.conexion;
import beans.WebUtil;
import clases.Tablas_t;
import clases.UsuarioSistema;


public class CtrlMant extends HttpServlet implements Serializable {
	private static Logger logger = Logger.getLogger(CtrlMant.class);
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}   
	public void destroy() {}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		res.setContentType("text/html");
		UsuarioSistema usuario = (UsuarioSistema)session.getAttribute("sClusuario");
		String strCas = usuario.getCcas();
		if(usuario == null) {
			req.setAttribute("msg_error", "La sesion ha finalizado. Ingrese de nuevo.");
			WebUtil.goForward(getServletContext(), req, res, "/error/error_general.jsp");
			return;
		}
		Connection con = conexion.getConnection();
		if (con == null) {        
			req.setAttribute("msg_error", "CtrlFicha: Error de Conexión a DB.");
			WebUtil.goForward(getServletContext(), req, res, "/error/error.jsp");
			return;
		}
		String strOpt = req.getParameter("opt")!=null?req.getParameter("opt"):"";
		String strCaso = req.getParameter("caso")!=null?req.getParameter("caso"):"";
		String strUrl="/error/error.jsp";
		String strPerfil = usuario.getPerfil();
		try{
			if (strOpt.equals("1")) { // INGRESO AL MANTENIMIENTO DE ACCIONES REALIZADAS
				logger.debug("Dentro CtrlMant de la opcion 1");
				Tablas_t lstTablas = new Tablas_t();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setstrcodaccion(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				lstTablas.setstrdesaccion(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				Tablas objTablas = new Tablas();
				lstTablas = objTablas.getLisAccionesRealizada(con, lstTablas);
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/accionesRealizadas.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt1: Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				objTablas=null;
				lstTablas=null;
			}
			else if (strOpt.equals("2")) { // MODIFICA TABLA ACCIONES REALIZADAS
				logger.debug("Dentro CtrlMant de la opcion 2");
				if(strPerfil.compareTo("09")==0 || strPerfil.compareTo("00")==0) {
					Tablas_t lstTablas = new Tablas_t();
					Tablas objTablas = new Tablas();
					lstTablas.setstrcodaccion(req.getParameter("CODACCION")!=null?req.getParameter("CODACCION"):"");
					lstTablas.setstrdesaccion(req.getParameter("DESACCION")!=null?req.getParameter("DESACCION"):"");
					lstTablas.setstrbestadoreg(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
					objTablas.AccionesUpdate(con, lstTablas, strPerfil, strCaso);
					if (objTablas.isOK()) {
						session.setAttribute("objLst",lstTablas);
						req.setAttribute("msg_error","El registro se grabó correctamente");
						strUrl="/servlet/CtrlMant?opt=1";
					}
					else {
					  req.setAttribute("msg_error"," CtrlMant strOpt2: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}
					objTablas = null; 
					lstTablas = null;
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt2: Sin privilegios para anular fichas ");
				  strUrl = "/error/error.jsp";
				}
			}
			else if (strOpt.equals("3")) { // INGRESO AL MANTENIMIENTO DE USUARIOS
				logger.debug("Dentro CtrlMant de la opcion 3");
				Tablas_t lstTablas = new Tablas_t();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setUsuUsuario(req.getParameter("USU")!=null?req.getParameter("USU"):"");
				lstTablas.setUsuDescripcion(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				lstTablas.setUsuPerfil(req.getParameter("PER")!=null?req.getParameter("PER"):"");
				Tablas objTablas = new Tablas();
				lstTablas = objTablas.getLisUsuarios(con, lstTablas);
				req.setAttribute ("vRed", BDTablas.getRed(con));
				req.setAttribute ("vCas", BDTablas.getCas(con));
				req.setAttribute ("vPerfil", BDTablas.getPerfil(con, strPerfil));
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/usuarios.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt3: Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				objTablas=null;
				lstTablas=null;
			}
			else if (strOpt.equals("4")) { // MODIFICA TABLA USUARIOS
				logger.debug("Dentro CtrlMant de la opcion 4");
				if(strPerfil.compareTo("10")==0 || strPerfil.compareTo("00")==0) {
					Tablas_t lstTablas = new Tablas_t();
					Tablas objTablas = new Tablas();
					lstTablas.setUsuUsuario(req.getParameter("USUARIO")!=null?req.getParameter("USUARIO"):"");
					lstTablas.setUsuClave(req.getParameter("CLAVE")!=null?req.getParameter("CLAVE"):"");
					lstTablas.setUsuDescripcion(req.getParameter("DESCRIPCION")!=null?req.getParameter("DESCRIPCION"):"");
					lstTablas.setUsuMail(req.getParameter("DUSUMAIL")!=null?req.getParameter("DUSUMAIL"):"");
					lstTablas.setUsuPerfil(req.getParameter("PERFIL")!=null?req.getParameter("PERFIL"):"");
					lstTablas.setUsuCcas(req.getParameter("CCAS")!=null?req.getParameter("CCAS"):"");
					lstTablas.setUsuCred(req.getParameter("CRED")!=null?req.getParameter("CRED"):"");
					lstTablas.setBworkflow(req.getParameter("BWORKFLOW")!=null?req.getParameter("BWORKFLOW"):"");
					lstTablas.setUsuBestadoreg(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
					lstTablas.setUsuMat(req.getParameter("DAPEMATUSU")!=null?req.getParameter("DAPEMATUSU"):"");
					lstTablas.setUsuNom(req.getParameter("DNOMUSU")!=null?req.getParameter("DNOMUSU"):"");
					objTablas.UsuariosUpdate(con, lstTablas, strPerfil, strCaso);
					if (objTablas.isOK()) {
						session.setAttribute("objLst",lstTablas);
						req.setAttribute("msg_error","El registro se grabó correctamente");
						strUrl="/servlet/CtrlMant?opt=3";
					}
					else {
					  req.setAttribute("msg_error"," CtrlMant strOpt4: Error en la grabacion. Usuario duplicado ");
					  strUrl = "/error/error.jsp"; 
					}
					objTablas = null; 
					lstTablas = null;
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt4: Sin privilegios para anular fichas ");
				  strUrl = "/error/error.jsp";
				}
			}
			else if (strOpt.equals("5")) { // INGRESO AL MANTENIMIENTO DE MOTIVOS o MATERIAS
				logger.debug("Dentro CtrlMant de la opcion 5");
				Tablas_t lstTablas = new Tablas_t();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setAsunCodigo(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				lstTablas.setAsunDescripcion(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				lstTablas.setCmateria(req.getParameter("MAT")!=null?req.getParameter("MAT"):"");
				lstTablas.setOrden(req.getParameter("ORDEN")!=null?req.getParameter("ORDEN"):"");
				Tablas objTablas = new Tablas();
				lstTablas = objTablas.getLisAsuntos(con, lstTablas);
				//req.setAttribute ("vConsejero", BDTablas.getConsejeros(con));
				req.setAttribute ("vMateria", BDTablas.getMaterias(con));
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/motivos.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt5: Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				objTablas=null;
				lstTablas=null;
			}
			else if (strOpt.equals("6")) { // MODIFICA TABLA ASUNTOS
				logger.debug("Dentro CtrlMant de la opcion 6");
				if(strPerfil.compareTo("09")==0 || strPerfil.compareTo("00")==0) {
					Tablas_t lstTablas = new Tablas_t();
					Tablas objTablas = new Tablas();
					lstTablas.setAsunCodigo(req.getParameter("CODIGO")!=null?req.getParameter("CODIGO"):"");
					lstTablas.setCmateria(req.getParameter("CMATERIA")!=null?req.getParameter("CMATERIA"):"");
					lstTablas.setAsunDescripcion(req.getParameter("DESCRIPCION")!=null?req.getParameter("DESCRIPCION"):"");
					lstTablas.setAsunPlazo(String.valueOf(req.getParameter("PLAZO")!=null?req.getParameter("PLAZO"):""));
					lstTablas.setAsunBestadoreg(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
					lstTablas.setAsunClaveusu(req.getParameter("USER")!=null?req.getParameter("USER"):"");
					objTablas.AsuntosUpdate(con, lstTablas, strPerfil, strCaso);
					if (objTablas.isOK()) {
						session.setAttribute("objLst",lstTablas);
						req.setAttribute("msg_error","El registro se grabó correctamente");
						strUrl="/servlet/CtrlMant?opt=5";
					}
					else {
					  req.setAttribute("msg_error"," CtrlMant strOpt6: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}
					objTablas = null; 
					lstTablas = null;
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt6: Sin privilegios para anular fichas ");
				  strUrl = "/error/error.jsp";
				}
			}
			else if (strOpt.equals("7")) { // INGRESO AL MANTENIMIENTO DE ESTADOS
				logger.debug("Dentro CtrlMant de la opcion 7");
				Tablas_t lstTablas = new Tablas_t();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setEstEstado(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				lstTablas.setEstDescripcion(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				Tablas objTablas = new Tablas();
				lstTablas = objTablas.getLisEstados(con, lstTablas);
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/estados.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt7: Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				objTablas=null;
				lstTablas=null;
			}
			else if (strOpt.equals("8")) { // MODIFICA TABLA ESTADOS
				logger.debug("Dentro CtrlMant de la opcion 8");
				if(strPerfil.compareTo("09")==0 || strPerfil.compareTo("00")==0) {
					Tablas_t lstTablas = new Tablas_t();
					Tablas objTablas = new Tablas();
					lstTablas.setEstEstado(req.getParameter("ESTADO")!=null?req.getParameter("ESTADO"):"");
					lstTablas.setEstDescripcion(req.getParameter("DESCRIPCION")!=null?req.getParameter("DESCRIPCION"):"");
					lstTablas.setEstBestadoreg(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
					objTablas.EstadosUpdate(con, lstTablas, strPerfil, strCaso);
					if (objTablas.isOK()) {
						session.setAttribute("objLst",lstTablas);
						req.setAttribute("msg_error","El registro se grabó correctamente");
						strUrl="/servlet/CtrlMant?opt=7";
					}
					else {
					  req.setAttribute("msg_error"," CtrlMant strOpt8: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}
					objTablas = null; 
					lstTablas = null;
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt8: Sin privilegios para anular fichas ");
				  strUrl = "/error/error.jsp";
				}
			}
			else if (strOpt.equals("9")) { // INGRESO AL MANTENIMIENTO DE MOTIVOS DBMSO10
				logger.debug("Dentro CtrlMant de la opcion 9");
				Tablas_t lstTablas = new Tablas_t();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setMotCod(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				lstTablas.setMotDesc(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				Tablas objTablas = new Tablas();
				lstTablas = objTablas.getLisMotivos(con, lstTablas);
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/tipo_solicitud.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt9: Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				objTablas=null;
				lstTablas=null;
			}
			else if (strOpt.equals("10")) { // MODIFICA TABLA MOTIVOS
				logger.debug("Dentro CtrlMant de la opcion 10");
				if(strPerfil.compareTo("09")==0 || strPerfil.compareTo("00")==0) {
					Tablas_t lstTablas = new Tablas_t();
					Tablas objTablas = new Tablas();
					lstTablas.setMotCod(req.getParameter("CODIGO")!=null?req.getParameter("CODIGO"):"");
					lstTablas.setMotDesc(req.getParameter("DESCRIPCION")!=null?req.getParameter("DESCRIPCION"):"");
					lstTablas.setMotBestado(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
					objTablas.MotivosUpdate(con, lstTablas, strPerfil, strCaso);
					if (objTablas.isOK()) {
						session.setAttribute("objLst",lstTablas);
						req.setAttribute("msg_error","El registro se grabó correctamente");
						strUrl="/servlet/CtrlMant?opt=9";
					}
					else {
					  req.setAttribute("msg_error"," CtrlMant strOpt10: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}
					objTablas = null; 
					lstTablas = null;
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt10: Sin privilegios para anular fichas ");
				  strUrl = "/error/error.jsp";
				}
			}
			else if (strOpt.equals("11")) { // INGRESO AL MANTENIMIENTO DE TIPO DE PRESTACION DBTPR10
				logger.debug("Dentro CtrlMant de la opcion 11");
				Tablas_t lstTablas = new Tablas_t();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setTipCod(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				lstTablas.setTipDesc(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				Tablas objTablas = new Tablas();
				lstTablas = objTablas.getLisTiposPres(con, lstTablas);
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/tipos_prestacion.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt11: Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				objTablas=null;
				lstTablas=null;
			}
			else if (strOpt.equals("12")) { // MODIFICA TABLA TIPO DE PRESTACION DBTPR10
				logger.debug("Dentro CtrlMant de la opcion 12");
				if(strPerfil.compareTo("09")==0 || strPerfil.compareTo("00")==0) {
					Tablas_t lstTablas = new Tablas_t();
					Tablas objTablas = new Tablas();
					lstTablas.setTipCod(req.getParameter("CODIGO")!=null?req.getParameter("CODIGO"):"");
					lstTablas.setTipDesc(req.getParameter("DESCRIPCION")!=null?req.getParameter("DESCRIPCION"):"");
					lstTablas.setTipBestado(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
					objTablas.TiposPresUpdate(con, lstTablas, strPerfil, strCaso);
					if (objTablas.isOK()) {
						session.setAttribute("objLst",lstTablas);
						req.setAttribute("msg_error","El registro se grabó correctamente");
						strUrl="/servlet/CtrlMant?opt=11";
					}
					else {
					  req.setAttribute("msg_error"," CtrlMant strOpt12: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}
					objTablas = null; 
					lstTablas = null;
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt12: Sin privilegios para anular fichas ");
				  strUrl = "/error/error.jsp";
				}
			}
			else if (strOpt.equals("13")) { // INGRESO AL MANTENIMIENTO DE TIPO DE INGRESO DE FICHAS
				logger.debug("Dentro CtrlMant de la opcion 13");
				Tablas_t lstTablas = new Tablas_t();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setTipIngCod(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				lstTablas.setTipIngDesc(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				Tablas objTablas = new Tablas();
				lstTablas = objTablas.getLisTiposIng(con, lstTablas);
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/tipo_ingreso.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt13: Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				objTablas=null;
				lstTablas=null;
			}
			else if (strOpt.equals("14")) { // MODIFICA TABLA TIPO DE TIPO DE INGRESO DE FICHAS
				logger.debug("Dentro CtrlMant de la opcion 14");
				if(strPerfil.compareTo("09")==0 || strPerfil.compareTo("00")==0) {
					Tablas_t lstTablas = new Tablas_t();
					Tablas objTablas = new Tablas();
					lstTablas.setTipIngCod(req.getParameter("CODIGO")!=null?req.getParameter("CODIGO"):"");
					lstTablas.setTipIngDesc(req.getParameter("DESCRIPCION")!=null?req.getParameter("DESCRIPCION"):"");
					lstTablas.setTipIngBestado(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
					objTablas.TiposIngUpdate(con, lstTablas, strPerfil, strCaso);
					if (objTablas.isOK()) {
						session.setAttribute("objLst",lstTablas);
						req.setAttribute("msg_error","El registro se grabó correctamente");
						strUrl="/servlet/CtrlMant?opt=13";
					}
					else {
					  req.setAttribute("msg_error"," CtrlMant strOpt14: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}
					objTablas = null; 
					lstTablas = null;
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt14: Sin privilegios para anular fichas ");
				  strUrl = "/error/error.jsp";
				}
			}
			else if (strOpt.equals("15")) { // INGRESO AL MANTENIMIENTO DE MOTIVOS PRINCIPALES
				logger.debug("Dentro CtrlMant de la opcion 15");
				Tablas_t lstTablas = new Tablas_t();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setCmateria(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				lstTablas.setDmateria(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				lstTablas.setOrden(req.getParameter("ORDEN")!=null?req.getParameter("ORDEN"):"");
				Tablas objTablas = new Tablas();
				lstTablas = objTablas.getLisMaterias(con, lstTablas);
				req.setAttribute ("vGrupos", BDTablas.getGrupos(con));
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/materias.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt15: Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				objTablas=null;
				lstTablas=null;
			}
			else if (strOpt.equals("16")) { // MODIFICA TABLA MATERIAS
				logger.debug("Dentro CtrlMant de la opcion 16");
				if(strPerfil.compareTo("09")==0 || strPerfil.compareTo("00")==0) {
					Tablas_t lstTablas = new Tablas_t();
					Tablas objTablas = new Tablas();
					lstTablas.setCmateria(req.getParameter("CODIGO")!=null?req.getParameter("CODIGO"):"");
					lstTablas.setDmateria(req.getParameter("DESCRIPCION")!=null?req.getParameter("DESCRIPCION"):"");
					lstTablas.setCgrupo(req.getParameter("CGRUPO")!=null?req.getParameter("CGRUPO"):"");
					lstTablas.setBestadoreg(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
					objTablas.MateriasUpdate(con, lstTablas, strPerfil, strCaso);
					if (objTablas.isOK()) {
						session.setAttribute("objLst",lstTablas);
						req.setAttribute("msg_error","El registro se grabó correctamente");
						strUrl="/servlet/CtrlMant?opt=15";
					}
					else {
					  req.setAttribute("msg_error"," CtrlMant strOpt16: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}
					objTablas = null; 
					lstTablas = null;
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt16: Sin privilegios ");
				  strUrl = "/error/error.jsp";
				}
			}
			else if (strOpt.equals("17")) { // INGRESO AL MANTENIMIENTO DE GRUPOS
				logger.debug("Dentro CtrlMant de la opcion 17");
				Tablas_t lstTablas = new Tablas_t();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setCgrupo(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				lstTablas.setDgrupo(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				lstTablas.setOrden(req.getParameter("ORDEN")!=null?req.getParameter("ORDEN"):"");
				Tablas objTablas = new Tablas();
				lstTablas = objTablas.getLisGrupos(con, lstTablas);
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/grupos.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt17: Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				objTablas=null;
				lstTablas=null;
			}
			else if (strOpt.equals("18")) { // MODIFICA TABLA GRUPOS
				logger.debug("Dentro CtrlMant de la opcion 18");
				if(strPerfil.compareTo("09")==0 || strPerfil.compareTo("00")==0) {
					Tablas_t lstTablas = new Tablas_t();
					Tablas objTablas = new Tablas();
					lstTablas.setCgrupo(req.getParameter("CODIGO")!=null?req.getParameter("CODIGO"):"");
					lstTablas.setDgrupo(req.getParameter("DESCRIPCION")!=null?req.getParameter("DESCRIPCION"):"");
					lstTablas.setBestadoreg(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
					objTablas.GruposUpdate(con, lstTablas, strPerfil, strCaso);
					if (objTablas.isOK()) {
						session.setAttribute("objLst",lstTablas);
						req.setAttribute("msg_error","El registro se grabó correctamente");
						strUrl="/servlet/CtrlMant?opt=17";
					}
					else {
					  req.setAttribute("msg_error"," CtrlMant strOpt18: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}
					objTablas = null; 
					lstTablas = null;
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt18: Sin privilegios ");
				  strUrl = "/error/error.jsp";
				}
			}
			//
			else if (strOpt.equals("19")) { // INGRESO AL MANTENIMIENTO DE CARGOS
				/*logger.debug("Dentro CtrlMant de la opcion 19");
				*/
			}
			else if (strOpt.equals("20")) { // MODIFICA TABLA GRUPOS
				/*logger.debug("Dentro CtrlMant de la opcion 20");
				*/
			}
			else if(strOpt.equals("21")){
				/*logger.debug("Dentro CtrlMant de la opcion 21");
				*/
			}			
			else if (strOpt.equals("22")) { // INGRESO AL MANTENIMIENTO DE PERFILES DE USUARIO
				logger.debug("Dentro CtrlMant de la opcion 22");
				Tablas_t lstTablas = new Tablas_t();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setCperfil(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				lstTablas.setDperfil(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				lstTablas.setOrden(req.getParameter("ORDEN")!=null?req.getParameter("ORDEN"):"");
				Tablas objTablas = new Tablas();
				lstTablas = objTablas.getLisPerfiles(con, lstTablas);
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/perfiles.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt22: Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				objTablas=null;
				lstTablas=null;
			}
			else if (strOpt.equals("23")) { // MODIFICA TABLA DE PERFILES DE USUARIO
				logger.debug("Dentro CtrlMant de la opcion 23");
				if(strPerfil.compareTo("09")==0 || strPerfil.compareTo("00")==0) {
					Tablas_t lstTablas = new Tablas_t();
					Tablas objTablas = new Tablas();
					lstTablas.setCperfil(req.getParameter("CODIGO")!=null?req.getParameter("CODIGO"):"");
					lstTablas.setDperfil(req.getParameter("DESCRIPCION")!=null?req.getParameter("DESCRIPCION"):"");
					lstTablas.setBestadoreg(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
					lstTablas.setCusu(usuario.getUsuario());
					objTablas.PerfilesUpdate(con, lstTablas, strPerfil, strCaso);
					if (objTablas.isOK()) {
						session.setAttribute("objLst",lstTablas);
						req.setAttribute("msg_error","El registro se grabó correctamente");
						strUrl="/servlet/CtrlMant?opt=22";
					}
					else {
					  req.setAttribute("msg_error"," CtrlMant strOpt23: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}
					objTablas = null; 
					lstTablas = null;
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt23: Sin privilegios ");
				  strUrl = "/error/error.jsp";
				}
			}else if (StringUtils.equals(strOpt,Constantes.IR_MANTENIMIENTO_TIPO_CONCLUSIONES)) { // TIPO DE CONCLUSIONES
				logger.debug("Dentro CtrlMant de la opcion 24");
				 // IR_MANTENIMIENTO_TIPO_TEMATICA
				req.setAttribute ("vSolicitud", BDTablas.getSolicitud(con));//checked
				
				Tablas_t lstTablas = Tablas_t.getInstance();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setCodSolicitud(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				lstTablas.setDescConclusion(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				
				Tablas objTablas =  Tablas.getInstance();
				
				objTablas.getListaConclusiones(con, lstTablas);
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/tipo_conclusiones.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt"+Constantes.IR_MANTENIMIENTO_TIPO_CONCLUSIONES+": Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				
			}else if (StringUtils.equals(strOpt,Constantes.MANTENIMIENTO_TIPO_CONCLUSIONES)) { // REDIRECCIONAMIENTO A TIPO CONCLUSIONES
				logger.debug("Dentro CtrlMant de la opcion 25");
				Tablas_t lstTablas = new Tablas_t();
				Tablas objTablas = new Tablas();
				lstTablas.setCodSolicitud(req.getParameter("CODACCION")!=null?req.getParameter("CODACCION"):"");
				lstTablas.setCodConclusion(req.getParameter("CODTEM")!=null?req.getParameter("CODTEM"):"");
				lstTablas.setDescConclusion(req.getParameter("DESACCION")!=null?req.getParameter("DESACCION"):"");
				
				lstTablas.setBestadoreg(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
				
				objTablas.conclusionesUpdate(con, lstTablas, strCaso);
				if (objTablas.isOK()) {
					session.setAttribute("objLst",lstTablas);
					req.setAttribute("msg_error","El registro se grabó correctamente");
					strUrl="/servlet/CtrlMant?opt=24";
				}
				else {
				  req.setAttribute("msg_error"," CtrlMant strOpt23: Error en la grabacion ");
				  strUrl = "/error/error.jsp"; 
				}
				
				
			}else if (StringUtils.equals(strOpt,Constantes.IR_MANTENIMIENTO_TIPO_TEMATICA)) {
				logger.debug("Dentro CtrlMant de la opcion 26");
				 // IR_MANTENIMIENTO_TIPO_TEMATICA
				req.setAttribute ("vSolicitud", BDTablas.getSolicitud(con));//checked
				
				Tablas_t lstTablas = Tablas_t.getInstance();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setCodSolicitud(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				lstTablas.setDescTematica(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				
				String cod_pag = req.getParameter("COD_PAG")!=null?req.getParameter("COD_PAG"):"";
				
				if(!lstTablas.getCodSolicitud().equals("TODOS"))
				{
					
					if(!cod_pag.equals("") && lstTablas.getCodSolicitud().equals("")){
						lstTablas.setCodSolicitud(cod_pag);
					}
					
					if(!lstTablas.getCodSolicitud().equals(cod_pag)){
						lstTablas.setPag("1");
					}
				}else{
					lstTablas.setCodSolicitud("");
					lstTablas.setPag("1");
				}
				
				Tablas objTablas =  Tablas.getInstance();
				
				objTablas.getListaTematica(con, lstTablas);
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/tipo_tematica.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt"+Constantes.IR_MANTENIMIENTO_TIPO_TEMATICA+": Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				
			
				
			}else if (StringUtils.equals(strOpt,Constantes.MANTENIMIENTO_TIPO_TEMATICA)) {  // REDIRECCIONAMIENTO A TIPO CONCLUSIONES
				logger.debug("Dentro CtrlMant de la opcion 27");
				Tablas_t lstTablas = new Tablas_t();
				Tablas objTablas = new Tablas();
				lstTablas.setCodSolicitud(req.getParameter("CODACCION")!=null?req.getParameter("CODACCION"):"");
				lstTablas.setCodTematica(req.getParameter("CODTEM")!=null?req.getParameter("CODTEM"):"");
				lstTablas.setDescTematica(req.getParameter("DESACCION")!=null?req.getParameter("DESACCION"):"");
				
				lstTablas.setBestadoreg(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
				lstTablas.setCusu(usuario.getUsuario());
				objTablas.tematicaUpdate(con, lstTablas, strCaso);
				if (objTablas.isOK()) {
					session.setAttribute("objLst",lstTablas);
					req.setAttribute("msg_error","El registro se grabó correctamente");
					strUrl="/servlet/CtrlMant?opt=26";
				}
				else {
				  req.setAttribute("msg_error"," CtrlMant strOpt23: Error en la grabacion ");
				  strUrl = "/error/error.jsp"; 
				}
			}else if (StringUtils.equals(strOpt,Constantes.IR_MANTENIMIENTO_TIPO_AREA)) { 
				logger.debug("Dentro CtrlMant de la opcion 28");
				 // IR_MANTENIMIENTO_TIPO_AREA
				//req.setAttribute ("vEnlarehoscod", BDTablas.getEnlarehoscod(con));//checked
				
				Tablas_t lstTablas = Tablas_t.getInstance();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setCodArea(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				lstTablas.setDescArea(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				
				Tablas objTablas =  Tablas.getInstance();
				
				objTablas.getListaArea(con, lstTablas);
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/tipo_area.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt"+Constantes.IR_MANTENIMIENTO_TIPO_AREA+": Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				
			
				
			}else if (StringUtils.equals(strOpt,Constantes.MANTENIMIENTO_TIPO_AREA)) {  // REDIRECCIONAMIENTO A TIPO AREA
				logger.debug("Dentro CtrlMant de la opcion 29");
				Tablas_t lstTablas = new Tablas_t();
				Tablas objTablas = new Tablas();
				lstTablas.setCodArea(req.getParameter("CODACCION")!=null?req.getParameter("CODACCION"):"");
				lstTablas.setDescArea(req.getParameter("DESACCION")!=null?req.getParameter("DESACCION"):"");
				lstTablas.setBestadoreg(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
				
				objTablas.areaUpdate(con, lstTablas, strCaso);
				if (objTablas.isOK()) {
					session.setAttribute("objLst",lstTablas);
					req.setAttribute("msg_error","El registro se grabó correctamente");
					strUrl="/servlet/CtrlMant?opt=28";
				}
				else {
				  req.setAttribute("msg_error"," CtrlMant strOpt23: Error en la grabacion ");
				  strUrl = "/error/error.jsp"; 
				}
			}else if (StringUtils.equals(strOpt,Constantes.IR_MANTENIMIENTO_TIPO_SERVICIO)) { 
				logger.debug("Dentro CtrlMant de la opcion 30");
				 // IR_MANTENIMIENTO_TIPO_SERVICIO
				req.setAttribute ("vListArea", BDTablas.getListArea(con));//checked
				
				Tablas_t lstTablas = Tablas_t.getInstance();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setCodServicio(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				lstTablas.setDescServicio(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				
				//INI temas busqueda
				String cod_pag = req.getParameter("COD_PAG")!=null?req.getParameter("COD_PAG"):"";
				
				if(!lstTablas.getCodServicio().equals("TODOS"))
				{
					
					if(!cod_pag.equals("") && lstTablas.getCodServicio().equals("")){
						lstTablas.setCodServicio(cod_pag);
					}
					
					if(!lstTablas.getCodServicio().equals(cod_pag)){
						lstTablas.setPag("1");
					}
				}else{
					lstTablas.setCodServicio("");
					lstTablas.setPag("1");
				}
				//FIN temas busqueda
				
				Tablas objTablas =  Tablas.getInstance();
				
				objTablas.getListaServicio(con, lstTablas);
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/tipo_servicio.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt"+Constantes.IR_MANTENIMIENTO_TIPO_AREA+": Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				
				
			}else if (StringUtils.equals(strOpt,Constantes.MANTENIMIENTO_TIPO_SERVICIO)) {  // REDIRECCIONAMIENTO A TIPO AREA
				logger.debug("Dentro CtrlMant de la opcion 31");
				Tablas_t lstTablas = new Tablas_t();
				Tablas objTablas = new Tablas();
				lstTablas.setCodArea(req.getParameter("CODAREA")!=null?req.getParameter("CODAREA"):"");
				lstTablas.setCodServicio(req.getParameter("CODSERVICIO")!=null?req.getParameter("CODSERVICIO"):"");
				lstTablas.setDescServicio(req.getParameter("DESSERVICIO")!=null?req.getParameter("DESSERVICIO"):"");
				lstTablas.setBestadoreg(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
				
				objTablas.servicioUpdate(con, lstTablas, strCaso);
				if (objTablas.isOK()) {
					session.setAttribute("objLst",lstTablas);
					req.setAttribute("msg_error","El registro se grabó correctamente");
					strUrl="/servlet/CtrlMant?opt=30";
				}
				else {
				  req.setAttribute("msg_error"," CtrlMant strOpt23: Error en la grabacion ");
				  strUrl = "/error/error.jsp"; 
				}
			}
			else if (strOpt.equals("32")) { // INGRESO AL MANTENIMIENTO DE UNIDAD ORGANICA NIVEL 01
				logger.debug("Dentro CtrlMant de la opcion 32");
				Tablas_t lstTablas = new Tablas_t();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setStrCras01(StringUtils.defaultString(req.getParameter("COD")));
				lstTablas.setStrDras01(StringUtils.defaultString(req.getParameter("DES")));
				Tablas objTablas = new Tablas();
				lstTablas = objTablas.getListUnidadOrganica01(con, lstTablas);
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/unidOrg01.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt32: Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
			
			}
	
			else if (strOpt.equals("33")) { // MODIFICA TABLA UNIDAD ORGANICA NIVEL 01
				logger.debug("Dentro CtrlMant de la opcion 33");
				
					Tablas_t lstTablas = new Tablas_t();
					Tablas objTablas = new Tablas();
					lstTablas.setStrCras01(req.getParameter("CODIGO")!=null?req.getParameter("CODIGO"):"");
					lstTablas.setStrDras01(req.getParameter("DESCRIPCION")!=null?req.getParameter("DESCRIPCION"):"");
					lstTablas.setStrBrasEstareg01(StringUtils.defaultString(req.getParameter("BESTADOREG")));
					objTablas.actualizarUnidadOrganica01(con, lstTablas, strPerfil, strCaso);
					if (objTablas.isOK()) {
						session.setAttribute("objLst",lstTablas);
						req.setAttribute("msg_error","El registro se grabó correctamente");
						strUrl="/servlet/CtrlMant?opt=32";
					}
					else {
					  req.setAttribute("msg_error"," CtrlMant strOpt33: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}

				
			}
			
			else if (strOpt.equals("34")) { // INGRESO AL MANTENIMIENTO DE UNIDAD ORGANICA NIVEL 02
				logger.debug("Dentro CtrlMant de la opcion 34");
				Tablas_t lstTablas = new Tablas_t();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setStrCcas02(StringUtils.defaultString(req.getParameter("COD")));
				lstTablas.setStrDcas02(StringUtils.defaultString(req.getParameter("DES")));
				req.setAttribute ("vRed", BDTablas.getRed(con));
				Tablas objTablas = new Tablas();
				lstTablas = objTablas.getListUnidadOrganica02(con, lstTablas);
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/unidOrg02.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt34: Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}

			}
			
			else if (strOpt.equals("35")) { // MODIFICA TABLA UNIDAD ORGANICA NIVEL 02
				logger.debug("Dentro CtrlMant de la opcion 35");
				
					Tablas_t lstTablas = new Tablas_t();
					Tablas objTablas = new Tablas();
					lstTablas.setStrCcas02(StringUtils.defaultString(req.getParameter("CODIGO")));
					lstTablas.setStrDcas02(req.getParameter("DESCRIPCION"));
					lstTablas.setStrBcasEstareg02(StringUtils.defaultString(req.getParameter("BESTADOREG")));
					String codRed = StringUtils.defaultString(req.getParameter("CRED"));
					lstTablas.setStrCras02(codRed);
					objTablas.actualizarUnidadOrganica2(con, lstTablas, strPerfil, strCaso);
					if (objTablas.isOK()) {
						session.setAttribute("objLst",lstTablas);
						req.setAttribute("msg_error","El registro se grabó correctamente");
						strUrl="/servlet/CtrlMant?opt=34";
					}
					else {
					  req.setAttribute("msg_error"," CtrlMant strOpt35: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}
					
			}
			
			else if (strOpt.equals("36")) { // INGRESO AL MANTENIMIENTO DE LOS MOTIVOS DE NO ADMISIBIIDAD
				logger.debug("Dentro CtrlMant de la opcion 36");
				Tablas_t lstTablas = new Tablas_t();
				lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstTablas.setStrCtma01(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				lstTablas.setStrDtma01(req.getParameter("DES")!=null?req.getParameter("DES"):"");
				Tablas objTablas = new Tablas();
				lstTablas = objTablas.getListMotivoNoAdmi(con, lstTablas);
				if (objTablas.isOK()) {
					req.setAttribute("objLst",lstTablas);
					strUrl="/tablas/motivo_noadm.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt36: Según los criterios seleccionados, no hay información");
				  strUrl = "/error/error.jsp";
				}
				objTablas=null;
				lstTablas=null;
			}
			
			else if (strOpt.equals("37")) { // MODIFICA TABLA DE LOS MOTIVOS DE NO ADMISIBIIDAD
				logger.debug("Dentro CtrlMant de la opcion 36");
				if(strPerfil.compareTo("09")==0 || strPerfil.compareTo("00")==0) {
					Tablas_t lstTablas = new Tablas_t();
					Tablas objTablas = new Tablas();
					lstTablas.setStrCtma01(req.getParameter("CODIGO")!=null?req.getParameter("CODIGO"):"");
					lstTablas.setStrDtma01(req.getParameter("DESCRIPCION")!=null?req.getParameter("DESCRIPCION"):"");
					lstTablas.setBestadoreg(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
					objTablas.actualizarMotivosNoAdm01(con, lstTablas, strPerfil, strCaso);
					if (objTablas.isOK()) {
						session.setAttribute("objLst",lstTablas);
						req.setAttribute("msg_error","El registro se grabó correctamente");
						strUrl="/servlet/CtrlMant?opt=36";
					}
					else {
					  req.setAttribute("msg_error"," CtrlMant strOpt37: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}
					objTablas = null; 
					lstTablas = null;
				}
				else {
					req.setAttribute("msg_error"," CtrlMant strOpt37: Sin privilegios para anular fichas ");
				  strUrl = "/error/error.jsp";
				}
			}
			
				else if (strOpt.equals("38")) { // INGRESO AL MANTENIMIENTO DE LA MODALIDAD DE CONCLUSIONES -- NPONCE 23/01/2015
					logger.debug("Dentro CtrlMant de la opcion 38");
					Tablas_t lstTablas = new Tablas_t();
					lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
					lstTablas.setStrCtmc01(req.getParameter("COD")!=null?req.getParameter("COD"):"");
					lstTablas.setStrDtmc01(req.getParameter("DES")!=null?req.getParameter("DES"):"");
					Tablas objTablas = new Tablas();
					lstTablas = objTablas.getListModalConclu(con, lstTablas);
					if (objTablas.isOK()) {
						req.setAttribute("objLst",lstTablas);
						strUrl="/tablas/modal_deconclu.jsp";
					}
					else {
						req.setAttribute("msg_error"," CtrlMant strOpt38: Según los criterios seleccionados, no hay información");
					  strUrl = "/error/error.jsp";
					}
					objTablas=null;
					lstTablas=null;
				}
				
				else if (strOpt.equals("39")) { // MODIFICA TABLA DE MODALIDAD DE CONCLUSIONES 23/01/2015 -- NPONCE
					logger.debug("Dentro CtrlMant de la opcion 38");
					if(strPerfil.compareTo("09")==0 || strPerfil.compareTo("00")==0) {
						Tablas_t lstTablas = new Tablas_t();
						Tablas objTablas = new Tablas();
						lstTablas.setStrCtmc01(req.getParameter("CODIGO")!=null?req.getParameter("CODIGO"):"");
						lstTablas.setStrDtmc01(req.getParameter("DESCRIPCION")!=null?req.getParameter("DESCRIPCION"):"");
						lstTablas.setBestadoreg(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
						objTablas.actualizarModalDeConclu01(con, lstTablas, strPerfil, strCaso);
						if (objTablas.isOK()) {
							session.setAttribute("objLst",lstTablas);
							req.setAttribute("msg_error","El registro se grabó correctamente");
							strUrl="/servlet/CtrlMant?opt=38";
						}
						else {
						  req.setAttribute("msg_error"," CtrlMant strOpt39: Error en la grabacion ");
						  strUrl = "/error/error.jsp"; 
						}
						objTablas = null; 
						lstTablas = null;
					}else {
						req.setAttribute("msg_error"," CtrlMant strOpt38: Sin privilegios para anular fichas ");
					  strUrl = "/error/error.jsp";
					}
				}
				else if (strOpt.equals("40")) { // INGRESO AL COMBO DE ACCION DE PERSUACION --  NPONCE 29/01/2015
					logger.debug("Dentro CtrlMant de la opcion 40");
					Tablas_t lstTablas = new Tablas_t();
				    lstTablas.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
					lstTablas.setStrCper01(req.getParameter("COD")!=null?req.getParameter("COD"):"");
				    lstTablas.setStrDper01(req.getParameter("DES")!=null?req.getParameter("DES"):"");
					Tablas objTablas = new Tablas();
					lstTablas = objTablas.getListAccPer(con, lstTablas);
					if (objTablas.isOK()) {
						req.setAttribute("objLst",lstTablas);
						strUrl="/tablas/acc_persuas.jsp";
					}
					else {
						req.setAttribute("msg_error"," CtrlMant strOpt40: Según los criterios seleccionados, no hay información");
					  strUrl = "/error/error.jsp";
					}
					objTablas=null;
					lstTablas=null;
				}
				
			else {
			  req.setAttribute("msg_error"," CtrlMant: Error al llegar... No tengo opt ");
			  strUrl = "/error/error.jsp";
			}
			try {
				con.close();
				con=null;
			} 
			catch(Exception ec){;
			}
			WebUtil.goForward(getServletContext(), req, res, strUrl);
		}
		catch(Exception e) {
			e.printStackTrace();
		  req.setAttribute("msg_error","Error en Servlet CtrlMant <br> " + e.getMessage());
		  WebUtil.goForward(getServletContext(), req, res, "/error/error.jsp");
		  logger.debug("CtrlMant: Error[" + getClass().getName() + "]." + e.getMessage());
		} 
		finally {
		  conexion.closeConnection(con);
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
}
