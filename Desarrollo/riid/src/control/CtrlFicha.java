
package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import util.Comun;
import util.Constantes;
import bd.BDTablas;
import bd.Ficha;
import bd.conexion;
import bean.ArchivoImagenBean;
import beans.WebUtil;
import clases.Ficha_t;
import clases.Filtro;
import clases.ListFicha_t;
import clases.UsuarioSistema;
//import com.lowagie.text.pdf.codec.Base64.InputStream;




public class CtrlFicha extends HttpServlet implements Serializable {
	private static Logger logger = Logger.getLogger(CtrlFicha.class);
	public static final int MAX_FILE_SIZE_BYTES = 1024*1024;
	private ArchivoImagenBean ficheroBean = new ArchivoImagenBean();
	private Blob blob = null;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}   
	public void destroy() {}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		res.setContentType("text/html");
		UsuarioSistema usuario = (UsuarioSistema)session.getAttribute("sClusuario");
		String portal = req.getParameter("portal")!=null?req.getParameter("portal"):"";
		String libRecla = req.getParameter("libRecla")!=null?req.getParameter("libRecla"):"";			
			if(usuario == null && !portal.equals("1")&& !libRecla.equals("1")) {
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
		//String strExt = req.getParameter("ext")!=null?req.getParameter("ext"):"";
		
		String strExt=null;
		
		//byte[] dataFichero = null;
		//byte[] dataFihero = null;// = (byte[])mapaArchivo.get("data");
		
		if(strOpt.equals("")){
			
			req = procesaFicheros(req,res);
			logger.debug("Msg: Grabo?"+ req.getAttribute("opt"));
			strOpt =  req.getAttribute("opt").toString();
			strExt=  req.getAttribute("ext").toString();
			//dataFichero=  (byte[]) req.getAttribute("dataFichero");
			logger.debug("Aqui esta......................."+ficheroBean.getArchivoImagen());
			//logger.debug("dataFichero "+dataFichero);
		}
		
	
		
		
		String strCaso = req.getParameter("caso")!=null?req.getParameter("caso"):"";
		String strUrl="/error/error.jsp";
		
		String strCas = null;
		String strPerfil = null;
		if(usuario!=null){
		strCas = usuario.getCcas();
		strPerfil = usuario.getPerfil();
		}
		//logger.debug("Entre al servlet ctrlficha con opt="+strOpt);
		try{
			if (strOpt.equals("0")) { // INGRESO A MENU
				logger.debug("Dentro ctrlFicha de la opcion 0");
				strUrl="/jsp/menu.jsp";
			}else if (strOpt.compareTo("99") == 0) {
				logger.debug("Dentro ctrlFicha de la opcion 99");
				req.setAttribute("msg_error", "Usted ha salido del sistema.");
				strUrl = "/jsp/index_salir.jsp";
			}
			else if (strOpt.equals("1")) { // INGRESO A LA FICHA
				logger.debug("Dentro ctrlFicha de la opcion 1");
				req.setAttribute ("vDepartamento", BDTablas.getDepartamento(con));//checked
				req.setAttribute ("vProvincia", BDTablas.getProvincia(con));//checked
				req.setAttribute ("vDistrito", BDTablas.getDistrito(con));//checked
				req.setAttribute ("vDocumento", BDTablas.getIdentidad(con));//checked
				req.setAttribute ("vPrestacion", BDTablas.getPrestacion(con));//checked
				req.setAttribute ("vSeguro", BDTablas.getSeguro(con));//checked
				req.setAttribute ("vSolicitud", BDTablas.getSolicitud(con));//checked
				req.setAttribute ("vTingreso", BDTablas.getTingreso(con));
				req.setAttribute ("vRed", BDTablas.getRed(con));
				req.setAttribute ("vCas", BDTablas.getCas(con));
				strUrl="/jsp/fdatosg.jsp";
			}
			else if (strOpt.equals("2")) { // INGRESO A PESTAÑA DE DATOS GENERALES TDATOSG.JSP
				logger.debug("Dentro ctrlFicha de la opcion 2");
				
					Ficha objFicha = new Ficha();
					Ficha_t obj = new Ficha_t();

					obj.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS").trim():strCas);
					obj.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR").trim():"");
					obj.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL").trim():"");
					obj.setctdi(req.getParameter("CTDI")!=null?req.getParameter("CTDI").trim():"");//cheked
					obj.setdficdid(req.getParameter("DFICDID")!=null?req.getParameter("DFICDID").trim():"");//cheked
					obj.setdapepat(req.getParameter("DAPEPAT")!=null?req.getParameter("DAPEPAT").trim():"");//cheked
					obj.setdapemat(req.getParameter("DAPEMAT")!=null?req.getParameter("DAPEMAT").trim():"");//cheked
					obj.setdnom(req.getParameter("DNOM")!=null?req.getParameter("DNOM").trim():"");//cheked
					obj.setnficeda(req.getParameter("NFICEDA")!=null?req.getParameter("NFICEDA").trim():"");//cheked
					obj.setddicsex(req.getParameter("DDICSEX")!=null?req.getParameter("DDICSEX").trim():"");//cheked
					//codigo extra
					obj.set2ubigeo(req.getParameter("DEPARTAMENTO")!=null?req.getParameter("DEPARTAMENTO").trim():"");
					obj.set4ubigeo(req.getParameter("PROVINCIA")!=null?req.getParameter("PROVINCIA").trim():"");
					//fin codigo extra
					obj.setcficubigeo(req.getParameter("CFICUBIGEO")!=null?req.getParameter("CFICUBIGEO").trim():"");//cheked
					obj.setdficdir(req.getParameter("DFICDIR")!=null?req.getParameter("DFICDIR").trim():"");//cheked
					obj.setdfictel(req.getParameter("DFICTEL")!=null?req.getParameter("DFICTEL").trim():"");//cheked
					obj.setdficmail(req.getParameter("DFICMAIL")!=null?req.getParameter("DFICMAIL").trim():"");//cheked
					obj.setctpr(req.getParameter("CTPR")!=null?req.getParameter("CTPR").trim():"");//cheked
					obj.setcfictipseg(req.getParameter("CFICTIPSEG")!=null?req.getParameter("CFICTIPSEG").trim():"");//cheked
					obj.setdapepatrec(req.getParameter("DAPEPATREC")!=null?req.getParameter("DAPEPATREC").trim():"");//cheked
					obj.setdapematrec(req.getParameter("DAPEMATREC")!=null?req.getParameter("DAPEMATREC").trim():"");//cheked
					obj.setdnomrec(req.getParameter("DNOMREC")!=null?req.getParameter("DNOMREC").trim():"");//cheked
					obj.setdficdidrec(req.getParameter("DFICDIDREC")!=null?req.getParameter("DFICDIDREC").trim():"");//cheked
					obj.setctdirec(req.getParameter("CTDIREC")!=null?req.getParameter("CTDIREC").trim():"");
					obj.setcmso(req.getParameter("CMSO")!=null?req.getParameter("CMSO").trim():"");//cheked
					obj.setbrevhclini(req.getParameter("BREVHCLINI")!=null?req.getParameter("BREVHCLINI").trim():"0");//checked
					obj.setrazsoc(req.getParameter("DG_RAZ_SOCIAL")!=null?req.getParameter("DG_RAZ_SOCIAL").trim():"");//cheked     22/02/2015
					
					
					
					//30012015 --GASCARZA INICIO
					String tipoSector = req.getParameter("tipoSector");
					String tipoPersona = req.getParameter("tipoPersona");
					String tipoIngreso = req.getParameter("tipoIngreso");
					
					obj.setTipoSector(tipoSector!=null?tipoSector.trim():"");//cheked
					obj.setTipoPersona(tipoPersona!=null?tipoPersona.trim():"");//cheked
					obj.setTipoIngreso(tipoIngreso!=null?tipoIngreso.trim():"");//cheked
					
					//--FIN
					


					if(StringUtils.equalsIgnoreCase(tipoIngreso, Constantes.TIPO_INGRESO_OFICIO)){
						obj.setctin(Constantes.TIPO_INGRESO_DE_OFICION);
					}else{
						if(strPerfil.equals(Constantes.ESSALUD_EN_LINEA)){
							obj.setctin(Constantes.CODIGO_ESSALUD_LINEA);//tipo de ingreso
						}else{
							obj.setctin(StringUtils.defaultString(req.getParameter("CTIN")));//tipo de ingreso
							
							if(StringUtils.isEmpty(obj.getctin())){
								strCaso = Constantes.OPCION_MODIFICAR;
								obj.setctin(Constantes.CODIGO_PORTAL_ESSALUD);//tipo de ingreso
							}else{
								 
								//if(obj.getctin().equals(Constantes.CODIGO_LIBRO_RECLAMACIONES)){
								//	obj.setViaIngreso(Constantes.VIA_INGRESO_PRESENCIAL);
								//}
							}
						}
											
					}
					
					obj.setdfichec(req.getParameter("DFICHEC")!=null?req.getParameter("DFICHEC").trim():"");
					obj.setReclaDir(req.getParameter("DIRRECLA")!=null?req.getParameter("DIRRECLA").trim():"");
					obj.setReclaTel(req.getParameter("TELRECLA")!=null?req.getParameter("TELRECLA").trim():"");
					obj.setReclaMail(req.getParameter("MAILRECLA")!=null?req.getParameter("MAILRECLA").trim():"");
					obj.setReclaCel(req.getParameter("CELRECLA")!=null?req.getParameter("CELRECLA").trim():"");
					if(!StringUtils.equals(usuario.getPerfil(), Constantes.PERFIL_ESSALUD_LINEA)){//agregado 18/12/2014
							obj.setCdelegado(req.getParameter("CDELEGADO")!=null ?req.getParameter("CDELEGADO").trim():usuario.getUsuario());
							
							//if(StringUtils.equals(usuario.getPerfil(), Constantes.PERFIL_DELEGADO_DEFENSORIAL)){//agregado 12/01/2014
							//	obj.setCdelegado(usuario.getUsuario());
							//}
						
					}
					obj.setcred(req.getParameter("CRED")!=null?req.getParameter("CRED").trim():"");
					obj.setccas(req.getParameter("CCAS")!=null?req.getParameter("CCAS").trim():"");
					obj.setcusucrea(usuario.getUsuario());
					obj.setDfictipseg(req.getParameter("DFICTIPSEG")!=null?req.getParameter("DFICTIPSEG").trim():"");//cheked
					//obj.setCdelegado(usuario.getUsuario());
					//logger.debug("fichero= "+obj.getfichero().toString());
					
					objFicha.FichaUpdate(con, obj, strPerfil, strCaso);
					if (objFicha.isOK()) {
						if(strCaso.equals(Constantes.OPCION_INSERTAR)){
							if(!obj.getcorrel().equals("err") && !obj.getayear().equals("err")){
								//obj = objFicha.getComprobante(con, obj);
								//session.setAttribute("objFicha_t", obj);
								req.setAttribute("msg_error","El registro se grabó correctamente");
								//strUrl = "/servlet/CtrlFicha?opt=24";
								//strUrl = "/jsp/fdatosg_mod.jsp";
								logger.debug("Los datos que requiero: "+obj.getayear()+", el cas "+obj.getcas()+" y el correl= "+obj.getcorrel());
								//req.setAttribute("");
								
								strUrl="/servlet/CtrlFicha?opt=27&CAS="+obj.getcas()+"&AYEAR="+obj.getayear()+"&CORREL="+obj.getcorrel();
							}
							else{
								req.setAttribute("msg_error","Error throwable: por favor intente mas tarde");
								logger.debug("Error en CtrlFicha: Sin codigo");
								strUrl = "/error/error_general.jsp";
							}
						}
						else if(strCaso.equals(Constantes.OPCION_MODIFICAR)){
							session.setAttribute("objFicha_t", obj);
							req.setAttribute("msg_error","La actualizacion se realizo correctamente");
							//logger.debug("Regrese del Update");
							strUrl="/servlet/CtrlFicha?opt=27&CAS="+obj.getcas()+"&AYEAR="+obj.getayear()+"&CORREL="+obj.getcorrel();
						}
						else{
							req.setAttribute("msg_error","Error throwable CtrlFicha Opt2: por favor intente mas tarde");
							logger.debug("Error throwable CtrlFicha Opt2: no tengo opt");
							strUrl = "/error/error.jsp";
						}
					}
					else {
					  req.setAttribute("msg_error"," CtrlFicha strOpt2: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}
					objFicha = null; 
					obj = null;
				
			}else if (strOpt.equals("3")) { // FORMULARIO BUSCAR FICHA
				logger.debug("Dentro ctrlFicha de la opcion 3");
				req.setAttribute ("vDocumento", BDTablas.getIdentidad(con));
				req.setAttribute ("vRed", BDTablas.getRed(con));
				req.setAttribute ("vCas", BDTablas.getCas(con));
				req.setAttribute ("vAyear", BDTablas.getAyear());
				strUrl="/jsp/rq_ficha.jsp";
			}
			else if (strOpt.equals("4")) { // BUSCAR FICHA
				logger.debug("Dentro ctrlFicha de la opcion 4");
				req.setAttribute("vDocumento",BDTablas.getIdentidad(con));
				req.setAttribute("vRed",BDTablas.getRed(con));
				req.setAttribute("vCas",BDTablas.getCas(con));
				req.setAttribute ("vAyear", BDTablas.getAyear());
				Ficha_t params=new Ficha_t();
				params.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				params.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				params.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				params.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				params.setcred(req.getParameter("CRED")!=null?req.getParameter("CRED"):"");
				params.setccas(req.getParameter("CCAS")!=null?req.getParameter("CCAS"):"");
				params.setctdi(req.getParameter("CTDI")!=null?req.getParameter("CTDI"):"");
				params.setdficdid(req.getParameter("DFICDID")!=null?req.getParameter("DFICDID"):"");
				params.setdapepat(req.getParameter("DAPEPAT")!=null?req.getParameter("DAPEPAT"):"");
				params.setdapemat(req.getParameter("DAPEMAT")!=null?req.getParameter("DAPEMAT"):"");
				params.setdnom(req.getParameter("DNOM")!=null?req.getParameter("DNOM"):"");
				params.setdapepatrec(req.getParameter("DAPEPATREC")!=null?req.getParameter("DAPEPATREC"):"");
				params.setdapematrec(req.getParameter("DAPEMATREC")!=null?req.getParameter("DAPEMATREC"):"");
				params.setdnomrec(req.getParameter("DNOMREC")!=null?req.getParameter("DNOMREC"):"");
				params.setdda(req.getParameter("FECHA1")!=null?req.getParameter("FECHA1"):"");
				params.setdda1(req.getParameter("FECHA2")!=null?req.getParameter("FECHA2"):"");
				Ficha objFicha=new Ficha();
				params=objFicha.getLisConsulta(con,params);
				if(objFicha.isOK()){
					req.setAttribute("objLst",params);
					strUrl="/jsp/rq_ficha.jsp";
				}
				else{
					logger.debug("CtrlFicha strOpt4: No hay información según los criterios ingresados ");
					req.setAttribute("msg_error","No se obtuvieron resultados.");
					strUrl="/jsp/rq_ficha.jsp";
				}
			  objFicha=null;
				params=null;
			}
			else if (strOpt.equals("5")) { // LISTA FICHAS DIARIAS
				logger.debug("Dentro ctrlFicha de la opcion 5 FICHAS DIARIAS");
				
				 // LISTA LAS FICHAS ASIGNADAS
				logger.debug("Dentro ctrlFicha de la opcion 5");
				Ficha_t params=new Ficha_t();
				params.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				params.setdda(StringUtils.defaultString(req.getParameter("FECHA1"),Comun.getFechaHoy()));
				req.setAttribute("fechaHoy", params.getdda());
				
				params.setCdelegado(usuario.getUsuario());
				
				Ficha objFicha=new Ficha();
				params=objFicha.getListFichaDiaria(con, params);
						
				if(objFicha.isOK()){
					req.setAttribute("objLst",params);
					strUrl="/jsp/lista_fichas_diarias.jsp";
				}
				else{
					logger.debug("CtrlFicha strOpt5: No hay información según los criterios ingresados ");
					req.setAttribute("msg_error","No se obtuvieron resultados.");
					strUrl="/jsp/lista_fichas_diarias.jsp";
				}
				
									
				
			}
			else if (strOpt.equals("6")) { // BUSCA con filtros lista diaria ficha por delegados
				 // LISTA FICHAS DIARIAS
				logger.debug("Dentro ctrlFicha de la opcion 6 FICHAS DELEGADOS");
				
				String ccas = StringUtils.defaultString(req.getParameter("CCAS"));
				String cdelegado = StringUtils.defaultString(req.getParameter("CDELEGADO"));
				
				Vector vCas = BDTablas.getCas(con, usuario.getCras());	
				req.setAttribute("vCas", vCas);
				
				if(StringUtils.isNotEmpty(ccas)){
					Vector vDelegados = BDTablas.getDelegado(con, usuario.getCras(), ccas, "08");
					req.setAttribute("vDelegados", vDelegados);
				}
				
				Ficha_t params=new Ficha_t();
				params.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				params.setdda(StringUtils.defaultString(req.getParameter("FECHA1"),Comun.getFechaHoy()));
				req.setAttribute("fechaHoy", params.getdda());
				req.setAttribute("ccas", ccas);
				
				if(StringUtils.isNotEmpty(cdelegado)){
					req.setAttribute("cdelegado", cdelegado);
					params.setCdelegado(cdelegado);
					
					Ficha objFicha=new Ficha();
					params=objFicha.getListFichaDiaria(con, params);
							
					if(objFicha.isOK()){
						req.setAttribute("objLst",params);
						strUrl="/jsp/lista_fichas_delegados.jsp";
					}
					else{
						logger.debug("CtrlFicha strOpt6: No hay información según los criterios ingresados ");
						req.setAttribute("msg_error","No se obtuvieron resultados.");
						strUrl="/jsp/lista_fichas_delegados.jsp";
					}
				}else{
					//inicial
					req.setAttribute("msg_error","Favor de seleccionar un delegado.");
					
					if(StringUtils.isEmpty(ccas)){
						req.setAttribute("msg_error","Favor de seleccionar una unidad organica y un delegado.");
						
					}
					
					
					strUrl="/jsp/lista_fichas_delegados.jsp";
				}
									
				
			
				
			}
			else if (strOpt.equals("7")) { // REPORTE DE LISTA DE FICHAS POR DELEGADOS
				logger.debug("Dentro ctrlFicha de la opcion 7");
				String fecha = StringUtils.defaultString(req.getParameter("FECHA1"));
				String unidadOrganica2 = StringUtils.defaultString(req.getParameter("CCAS"));
				String cdelegado = StringUtils.defaultString(req.getParameter("CDELEGADO"));
				
				Ficha objFicha=new Ficha();
				//List lista=objFicha.getReporteListFichaDiaria(con, fecha, usuario.getCras(), unidadOrganica2, cdelegado);
				Map parametros = objFicha.getReporteDiario(con, fecha, usuario.getCras(), unidadOrganica2, cdelegado, true);
				//req.setAttribute("listadoFichaDiaria", lista);
				req.setAttribute("parametros", parametros);
				
				req.setAttribute("cdelegado", cdelegado);
				//strUrl="/consultas/reporte_listado_delegados.jsp";
				strUrl="/jsp/reportes/reporteDiario.jsp";
				
			}
			else if (strOpt.equals("8")) { // FORMULARIO BUSCA CONSULTA EXCEL
				logger.debug("Dentro ctrlFicha de la opcion 8");
				req.setAttribute ("vRed", BDTablas.getRed(con));
				req.setAttribute ("vCas", BDTablas.getCas(con));
				strUrl="/jsp/repo_excel_rq.jsp";
			}
			else if (strOpt.equals("9")) { // REPORTE DIARIO DE DELEGADO
				logger.debug("Dentro ctrlFicha de la opcion 7");
				String fecha = StringUtils.defaultString(req.getParameter("FECHA1"));
				String cdelegado = usuario.getUsuario();
				
				Ficha objFicha=new Ficha();
				//List lista=objFicha.getReporteFichaDiaria(con, fecha, cdelegado);
				Map parametros = objFicha.getReporteDiario(con, fecha, "", "", cdelegado, false);
				req.setAttribute("parametros", parametros);
				req.setAttribute("cdelegado", cdelegado);
				//strUrl="/consultas/reporte_listado_delegados.jsp";
				strUrl="/jsp/reportes/reporteDiario.jsp";
				
			}
			else if (strOpt.equals("10")) { // CONSULTA SEGUIMIENTO DE EXPEDIENTES
				logger.debug("Dentro ctrlFicha de la opcion 10, CONSULTA SEGUIMIENTO DE EXPEDIENTES");				
				
					 // LISTA FICHAS DIARIAS
					logger.debug("Dentro ctrlFicha de la opcion 6 FICHAS DELEGADOS");
					
					String anio = StringUtils.defaultString(req.getParameter("AYEAR"));
					String ccas = StringUtils.defaultString(req.getParameter("CAS"));
					String correl = StringUtils.defaultString(req.getParameter("CORREL"));
					
					if(StringUtils.isNotEmpty(anio) || StringUtils.isNotEmpty(ccas) || StringUtils.isNotEmpty(correl)){
					
						Ficha_t params=new Ficha_t();
						params.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
						params.setayear(anio);
						params.setccas(ccas);
						params.setcorrel(correl);
						
						Ficha objFicha=new Ficha();
						params=objFicha.getListSeguimientoFicha(con, params);
									
						if(objFicha.isOK()){
							req.setAttribute("objLst",params);
							strUrl="/jsp/lista_seguim_expediente.jsp";
						}
						else{
							logger.debug("CtrlFicha strOpt10: No hay información según los criterios ingresados ");
							req.setAttribute("msg_error","No se obtuvieron resultados.");
							strUrl="/jsp/lista_seguim_expediente.jsp";
						}
					}else{
						logger.debug("CtrlFicha strOpt10: No hay información según los criterios ingresados ");
						req.setAttribute("msg_error","No se obtuvieron resultados.");
						strUrl="/jsp/lista_seguim_expediente.jsp";
					}
					
				}
			else if (strOpt.equals("11")) { // FORMULARIO BUSCA CONSULTA ATENCION
				/*logger.debug("Dentro ctrlFicha de la opcion 11");
				*/
			}
			else if (strOpt.equals("12")) { // FORMULARIO BUSCA CONSULTA ATENCION
				/*logger.debug("Dentro ctrlFicha de la opcion 12");
				*/
			}
			else if (strOpt.equals("13")) { // FORMULARIO BUSCA CONSULTA ATENCION POR SEMANA
				/*logger.debug("Dentro ctrlFicha de la opcion 13");
				*/
			}
			else if (strOpt.equals("14")) { // BUSCAR FICHA PARA ANULAR
				logger.debug("Dentro de la opcion 14");
				if(strPerfil.compareTo("00")==0 || strPerfil.compareTo("09")==0){
					ListFicha_t lstFicha = new ListFicha_t();
					lstFicha.setcond01(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
					lstFicha.setcond02(req.getParameter("CCAS")!=null?req.getParameter("CCAS"):"");
					lstFicha.setcond03(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
					Ficha objFicha = new Ficha();
					lstFicha = objFicha.getFichaAnula(con, lstFicha);
					if (objFicha.isOK()) {
						req.setAttribute("objLst",lstFicha);
						strUrl="/jsp/rq_ficha_anula.jsp";
					}
					else {
						req.setAttribute("msg_error"," No hay información según los criterios ingresados ");
						strUrl="/jsp/rq_ficha_anula.jsp";
					}
					objFicha=null;
					lstFicha=null;
				}
				else {
					req.setAttribute("msg_error"," CtrlFicha strOpt14: Sin privilegios para esta accion ");
				  strUrl = "/error/error.jsp";
				}
			}
			else if (strOpt.equals("15")) { // ELIMINACION (ANULACION) LOGICA DE FICHAS
				logger.debug("Dentro ctrlFicha de la opcion 15");
				
					Ficha objFicha = new Ficha();
					Ficha_t obj = new Ficha_t();
					obj.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
					obj.setccas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
					obj.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
					obj.setbestadoreg(req.getParameter("BESTADOREG")!=null?req.getParameter("BESTADOREG"):"");
					obj.setcusucrea(req.getParameter("INPUTUSER")!=null?req.getParameter("INPUTUSER"):"");
					//solo se modifica
					objFicha.FichaUpdate(con, obj, strPerfil, strCaso);
					if (objFicha.isOK()){
						session.setAttribute("objFicha_t", obj);
						req.setAttribute("msg_error","La Ficha se grabó correctamente");
						strUrl = "/jsp/rq_ficha_anula.jsp";
					}
					else {
					  req.setAttribute("msg_error"," CtrlFicha strOpt15: Error en la grabacion ");
					  strUrl = "/error/rq_ficha_anula.jsp"; 
					}
					objFicha = null; 
					obj = null;
				
			}
			else if (strOpt.equals("16")) { // INGRESO A ANULACION LOGICA DE FICHAS
				logger.debug("Dentro ctrlFicha de la opcion 16");
				if(strPerfil.compareTo("00")==0 || strPerfil.compareTo("09")==0){
					strUrl="/jsp/rq_ficha_anula.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlFicha strOpt16: Sin privilegios para anular fichas ");
				  strUrl = "/error/error.jsp";
				}
			}
			//else if (strOpt.equals("17")) { // PRODUCCION DE USUARIOS POR CODIGO DE ASUNTO
				/*logger.debug("Dentro ctrlFicha de la opcion 17");
				*/
			//}
			//else if (strOpt.equals("18")) { // REGISTRO DE USUARIOS POR CODIGO DE ASUNTO
				/*logger.debug("Dentro ctrlFicha de la opcion 18");
				*/
			//}
			else if (strOpt.equals("19")) { // BUSCA REGISTROS INGRESADAS POR ESSALUD EN LINEA
				logger.debug("Dentro ctrlFicha de la opcion 19");
				ListFicha_t lstFicha = new ListFicha_t();
				lstFicha.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstFicha.setCodUsuario(usuario.getUsuario());	
				Ficha objFicha = new Ficha();
				lstFicha = objFicha.getLisFichaEssalud(con, lstFicha, strPerfil);
				if (objFicha.isOK()) {
					req.setAttribute ("vCodigo", BDTablas.getCodigo(con));
					req.setAttribute("objLst",lstFicha);
					strUrl="/jsp/rq_ficha_esel.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlFicha strOpt6: Error al buscar ");
				  strUrl = "/error/error.jsp";
				}
				objFicha=null;
				lstFicha=null;
			}
			else if (strOpt.equals("20")) { 	 // BUSCA REGISTROS INGRESADAS POR LIBRO DE RECLAMACIONES
			
				logger.debug("Dentro ctrlFicha de la opcion 19");
				ListFicha_t lstFicha = new ListFicha_t();
				lstFicha.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstFicha.setCodUsuario(usuario.getUsuario());	
				Ficha objFicha = new Ficha();
				lstFicha = objFicha.getLibRecla(con, lstFicha);
				if (objFicha.isOK()) {
					req.setAttribute ("vCodigo", BDTablas.getCodigo(con));
					req.setAttribute("objLst",lstFicha);
					strUrl="/jsp/rq_lib_recla.jsp";
				}
				else {
					req.setAttribute("msg_error"," CtrlFicha strOpt6: Error al buscar ");
				  strUrl = "/error/error.jsp";
				}
				objFicha=null;
				lstFicha=null;
			
			}
			else if (strOpt.equals("21")) { // ABRE FORMULARIO DE ENVIO DE DOCUMENTOS
				/*logger.debug("Dentro ctrlFicha de la opcion 21");
				*/
			}
			else if (strOpt.equals("22")) { // UPLOADFICHERO
				/*logger.debug("Dentro ctrlFicha de la opcion 22");
				*/
			}
			else if (strOpt.equals("23")) { // PESTAÑA DATOS GENERALES
				//
				// disponible
				//
			}
			else if (strOpt.equals("24")) { // PESTAÑA DATOS ADMISION Y CALIFICACION
				logger.debug("Dentro ctrlFicha de la opcion 24");
				Ficha objFicha=new Ficha();
				Ficha_t obj=(Ficha_t)session.getAttribute("objFicha_t");
				//obtenemos cas, ayear y correl para proceder con el query
				/*obj.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				obj.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				obj.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				obj=objFicha.getFicha(con,obj);
				String cccas= obj.getccas();*/
				
				objFicha.getFicha(con,obj);
				objFicha.getFdatosAd(con,obj);
				
				//obj.setccas(cccas);
				if(objFicha.isOK()){
					if(obj.getcmso() == null){
						//se recupera objeto anterior 02072014
						Ficha_t objAnt = (Ficha_t) session.getAttribute("objFicha_t");
						if(objAnt != null && objAnt.getcmso() != null){
							obj.setcmso(objAnt.getcmso());
						}
					}
					
					
					
					//recupero los motivos
					obj.setcmotivos(BDTablas.getcMotivos(con,obj));
					obj.setdmotivos(BDTablas.getdMotivos(con,obj));
					//logger.debug("cmotivos: %"+obj.getcmotivos());
					//logger.debug("dmotivos: %"+obj.getdmotivos());
					session.setAttribute("objFicha_t",obj);
					req.setAttribute ("vDocumento", BDTablas.getIdentidad(con));
					req.setAttribute ("vMateria", BDTablas.getMaterias(con));
					req.setAttribute ("vCodigo", BDTablas.getCodigo(con));
					req.setAttribute ("vEnlarehoscod", BDTablas.getEnlarehoscod(con));
					req.setAttribute ("vEnlservhoscod", BDTablas.getEnlservhoscod(con));
					req.setAttribute ("vRed", BDTablas.getRed(con));
					req.setAttribute ("vMotivoNoAdm", BDTablas.getMotivoNoAdm(con));//checked nponce 22/01/2015
					req.setAttribute ("vModelDeConclu", BDTablas.getModelDeConclu(con));//checked nponce 22/01/2015
					
					
					req.setAttribute ("vCas", BDTablas.getCas(con,obj.getcred()));
					
					if(obj.getcmso()!=null){
					req.setAttribute ("vTematica", BDTablas.getcmet(con, obj.getcmso()));
					}
					//incluido 02072014
					req.setAttribute ("vSolicitud", BDTablas.getSolicitud(con));//checked
					strUrl="/jsp/fdatosad.jsp";
				}
				else{
					req.setAttribute("msg_error"," CtrlFicha strOpt24: error al recuperar de la tabla tdatosad... ");
					strUrl = "/error/error.jsp";
				}
			}
			else if (strOpt.equals("25")) { // PESTAÑA DATOS GESTION DEFENSORIAL
				logger.debug("Dentro ctrlFicha de la opcion 25");
				logger.debug("El ccas de este usuario es:"+strCas);
				Ficha objFicha=new Ficha();
				//ListFicha_t lstFicha = new ListFicha_t();
				Ficha_t fdef=(Ficha_t)session.getAttribute("objFicha_t");
				//Ficha_t fichaOriginal = new Ficha_t();
				fdef.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				/*lstFicha.setcond01(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				lstFicha.setcond02(strCas);
				lstFicha.setcond03(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");*/
				/*fdef.setccas(strCas);
				fdef.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				fdef.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				fdef.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				*/
				
				
				//String relativeWebPath = "archivosCargados/"+fdef.getayear()+"_"+fdef.getcas()+"_"+fdef.getcorrel()+".pdf";
				String relativeWebPath = "archivosCargados/";
				String strRealPath = req.getSession().getServletContext().getRealPath(relativeWebPath);
				//strRealPath = strRealPath.replaceAll("jakarta", "jakarta-tomcat-5.0.28");
				logger.debug("Visualizar file: "+strRealPath);
				//String path="D:\\ESSALUD\\RIID\\ProyRiid\\riid\\WebContent\\archivosCargados\\"+fdef.getayear()+"_"+fdef.getcas()+"_"+fdef.getcorrel()+".pdf";
				//String path=fdef.getayear()+"_"+fdef.getcas()+"_"+fdef.getcorrel()+".pdf";
				File fichero = new File(strRealPath);
				 fdef.setLstFichero(new ArrayList());
		        if (fichero.exists())
		        {	//fichero.canRead();
		        	fdef.getLstFichero();
		            //fichero.list(new Filtro(".pdf")); 
		            
		            File[] lstficherosPDF = fichero.listFiles(new Filtro(".pdf"));
		           
                    for (int x=0;x<lstficherosPDF.length;x++){
                    	  String prefix=fdef.getayear()+"_"+fdef.getcas()+"_"+fdef.getcorrel();
                    	
                  	      if(lstficherosPDF[x].getName().startsWith(prefix)){
			                  	      logger.debug(lstficherosPDF[x].getName());
			                  	      logger.debug("paso carga archivos");
		                              fdef.setfichero(req.getContextPath()+"/archivosCargados/"+lstficherosPDF[x].getName());
		                              fdef.getLstFichero().add(fdef.getfichero());
		                          // List ls=new ArrayList();
		                              // ImageIO.read(new File("2015_038_4498.pdf"));
		                          //    listaArchivosSubidos.get(x).setnum_corr_archivo
		                              HashMap oFilaAdd = new HashMap();
		                                oFilaAdd.put("num_corr_archivo", String.valueOf(x));
		                                oFilaAdd.put("nom_archivo",lstficherosPDF[x].getName());
		                             // listaArchivosSubidos.set(x,oFilaAdd);
		                            // listaArchivosSubidos.add(oFilaAdd);
                            }          
                    }
                    
                    
                    
                    File[] lstficherosJPG = fichero.listFiles(new Filtro(".jpg"));
 		           
                    for (int x=0;x<lstficherosJPG.length;x++){
                    	  String prefix=fdef.getayear()+"_"+fdef.getcas()+"_"+fdef.getcorrel();
                    	
                  	      if(lstficherosJPG[x].getName().startsWith(prefix)){
			                  	      logger.debug(lstficherosJPG[x].getName());
			                  	      logger.debug("paso carga archivos");
		                              fdef.setfichero(req.getContextPath()+"/archivosCargados/"+lstficherosJPG[x].getName());
		                              fdef.getLstFichero().add(fdef.getfichero());
		                          // List ls=new ArrayList();
		                              // ImageIO.read(new File("2015_038_4498.pdf"));
		                          //    listaArchivosSubidos.get(x).setnum_corr_archivo
		                              HashMap oFilaAdd = new HashMap();
		                                oFilaAdd.put("num_corr_archivo", String.valueOf(x));
		                                oFilaAdd.put("nom_archivo",lstficherosJPG[x].getName());
		                             // listaArchivosSubidos.set(x,oFilaAdd);
		                            // listaArchivosSubidos.add(oFilaAdd);
                            }          
                    }
                    
                    Iterator it=fdef.getLstFichero().iterator();

                    while(it.hasNext())
                    {
                      String value=(String)it.next();

                      logger.debug("Value :"+value);
                    }
                    
                    
		           // sdsdsd
		        	//if("pdf".equals(strExt)) {
		        	  //fdef.setfichero(req.getContextPath()+"/archivosCargados/"+fdef.getayear()+"_"+fdef.getcas()+"_"+fdef.getcorrel()+".pdf");
		        	// }
		        	//else if("jpg".equals(strExt)){ 
		        		 // fdef.setfichero(req.getContextPath()+"/archivosCargados/"+fdef.getayear()+"_"+fdef.getcas()+"_"+fdef.getcorrel()+".jpg");
                    fdef.setfichero(req.getContextPath()+"/archivosCargados/");
		        	//}
		        	  //fdef.setfichero(path);
		        	//req.getContextPath()
		         }else{
		        	fdef.setfichero("");
		        }
				
				
				req.setAttribute ("vAccReal", BDTablas.getAccReal(con));
				objFicha.getListFdatosDef(con,fdef);
				objFicha.getFicha(con,fdef);
				/*
				
				fichaOriginal.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				fichaOriginal.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				fichaOriginal.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				fichaOriginal=objFicha.getFicha(con,fichaOriginal);
				req.setAttribute("fichaOriginal",fichaOriginal);
				*/
				
				//if(objFicha.isOK()){
				req.setAttribute("objLst",fdef);
				//	session.setAttribute("objFicha_t",fichaOriginal);
					strUrl="/jsp/fdatosdef.jsp";
				//}				
			}	
			
			else if (strOpt.equals("26")) { // CONCLUSIONES 20062014
				logger.debug("Dentro ctrlFicha de opt 26");
				
				Ficha objFicha=new Ficha();
				Ficha_t lstFicha=(Ficha_t) session.getAttribute("objFicha_t");
				/*lstFicha.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				lstFicha.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				lstFicha.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");*/
				lstFicha.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				objFicha.getTipoSolicitud(con,lstFicha);
				//objFicha.getUltimoRegistro(con,lstFicha);
				logger.debug("TIPO SOLICITUD >>>>>>> "+lstFicha.getcmso());
				/*
				
				if(StringUtils.equalsIgnoreCase(lstFicha.getcmso(), Constantes.TIPO_SOLICITUD_INADMISIBLE)){
					if( StringUtils.isEmpty(lstFicha.getIdConclu())){
					Ficha_t objFichat = new Ficha_t();
					objFichat.setcas(lstFicha.getcas());
					objFichat.setayear(lstFicha.getayear());
					objFichat.setcorrel(lstFicha.getcorrel());
					objFichat.setCconclu(Constantes.TIPO_CONCLUIDO);
					objFichat.setbestadoreg(Constantes.ESTADO_INACTIVO);
					objFicha.fichaInsertarConclusiones(con, objFichat);
					}else{
						
					}
				}*/
				
				if(objFicha.isOK()){
					objFicha.getConcluSeguimiento(con, lstFicha);
					objFicha.getFicha(con, lstFicha);
					session.setAttribute("objFicha_t",lstFicha);
					session.removeAttribute("cmso");
					session.removeAttribute("cconclu");
					session.setAttribute("cmso", lstFicha.getcmso());
					session.setAttribute("cconclu", lstFicha.getCconclu());
					session.setAttribute("bestadoreg", lstFicha.getbestadoreg());
					session.setAttribute("idConclu", lstFicha.getcmso());
					//req.setAttribute ("vSolicitud", BDTablas.getSolicitud(con));//checked
					req.setAttribute ("vConclusiones",BDTablas.getConclusiones(con,lstFicha.getcmso()));
					req.setAttribute ("vAccSeg",BDTablas.getAccSeg(con));
					strUrl="/jsp/conclusiones.jsp";
					}				
			
			}else if (strOpt.equals("27")){ // LUEGO DE GRABAR VIENE A TDATOSG_MOD.JSP
				logger.debug("Dentro ctrlFicha de la opcion 27");
				Ficha objFicha = new Ficha();
				Ficha_t lstFicha = new Ficha_t();
				lstFicha.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				lstFicha.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				lstFicha.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				lstFicha=objFicha.getFicha(con,lstFicha);
				if(objFicha.isOK()){
					session.setAttribute("objFicha_t",lstFicha);
					req.setAttribute ("vDepartamento", BDTablas.getDepartamento(con));//checked
					req.setAttribute ("vProvincia", BDTablas.getProvincia(con));//checked
					req.setAttribute ("vDistrito", BDTablas.getDistrito(con));//checked
					req.setAttribute ("vDocumento", BDTablas.getIdentidad(con));//checked
					req.setAttribute ("vPrestacion", BDTablas.getPrestacion(con));//checked
					req.setAttribute ("vSeguro", BDTablas.getSeguro(con));//checked
					req.setAttribute ("vSolicitud", BDTablas.getSolicitud(con));//checked
					req.setAttribute ("vTingreso", BDTablas.getTingreso(con));
					req.setAttribute ("vRed", BDTablas.getRed(con));
					req.setAttribute ("vCas", BDTablas.getCas(con, lstFicha.getcred()));
					
					if(lstFicha.getRdelegado()!=null && !lstFicha.getRdelegado().equals("")){
						req.setAttribute ("vCas1", BDTablas.getCas(con, lstFicha.getRdelegado()));
						req.setAttribute ("vDelegado", BDTablas.getDelegado(con, lstFicha.getRdelegado(),lstFicha.getCasdelegado(),""));
						}
					
					strUrl="/jsp/fdatosg_mod.jsp";
				}
				else{
					req.setAttribute("msg_error"," CtrlFicha strOpt27: error en la busqueda de la ficha... ");
					strUrl = "/error/error.jsp";
				}
			}else if (strOpt.equals("28")) { // GRABAR PESTAÑA DATOS DE ADMISION
				logger.debug("Dentro ctrlFicha de la opcion 28");
				
				Ficha objFicha=new Ficha();
				Ficha_t obj=new Ficha_t();
				//ponemos en obj los parameters de fdatosad.jsp
				obj.setdfichec(req.getParameter("DFICHEC")!=null?req.getParameter("DFICHEC"):"");
				obj.setctdiqueja(req.getParameter("CTDIQUEJA")!=null?req.getParameter("CTDIQUEJA"):"");
				obj.setddocqueja(req.getParameter("DDOCQUEJA")!=null?req.getParameter("DDOCQUEJA"):"");
				obj.setdapepatqueja(req.getParameter("DAPEPATQUEJA")!=null?req.getParameter("DAPEPATQUEJA"):"");
				obj.setdapematqueja(req.getParameter("DAPEMATQUEJA")!=null?req.getParameter("DAPEMATQUEJA"):"");
				obj.setdnomqueja(req.getParameter("DNOMQUEJA")!=null?req.getParameter("DNOMQUEJA"):"");
				
				obj.setenlarehoscod(req.getParameter("ENLAREHOSCOD")!=null?req.getParameter("ENLAREHOSCOD"):"");
				
				obj.setenlservhoscod(req.getParameter("ENLSERVHOSCOD")!=null?req.getParameter("ENLSERVHOSCOD"):"");
				obj.setdiagcod(req.getParameter("DIAGCOD")!=null?req.getParameter("DIAGCOD"):"");
				obj.setccas(req.getParameter("CCAS")!=null?req.getParameter("CCAS"):"");
				//ahora ayear, cas y correl 
				obj.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				obj.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				obj.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				//finalmente el cusumodi
				obj.setcusumodi(usuario.getUsuario());
				//ponemos el string de motivos para grabar
				obj.setcmotivos(req.getParameter("CMOTIVOS")!=null?req.getParameter("CMOTIVOS").trim():"");
				obj.setCtem(req.getParameter("CTEM")!=null?req.getParameter("CTEM").trim():"");
				obj.setcmso(req.getParameter("CMSO")!=null?req.getParameter("CMSO").trim():"");
				obj.setctma(req.getParameter("CTMA")!=null?req.getParameter("CTMA").trim():""); //TABLA DE MOTIVOS DE NO ADMISIBILIDAD --NPONCE
				obj.setctmc(req.getParameter("CTMC")!=null?req.getParameter("CTMC").trim():""); //TABLA DE MODALIDAD DE CONCLUSIONES --NPONCE
				obj.setfechaq(req.getParameter("fechaq"));
				obj.setGrupOcup(req.getParameter("GRUPOCUP")!=null?req.getParameter("GRUPOCUP").trim():"");
				logger.debug("Cadena de motivos: %"+obj.getcmotivos()+"%");
				//mostramos en el log que estamos grabando:
				//procedemos a grabar...
				objFicha.UpdateDatosAd(con,obj);
				if(objFicha.isOK()){
					session.setAttribute("objLst", obj);
					req.setAttribute("msg_error","El registro se grabó correctamente");
					strUrl="/servlet/CtrlFicha?opt=24&CAS="+obj.getcas()+"&AYEAR="+obj.getayear()+"&CORREL="+obj.getcorrel();
					//strUrl="/jsp/fdatosad.jsp";
				}else{
					req.setAttribute("msg_error","Error, opt=28; error en grabar en tdatosg");
					strUrl = "/error/error_general.jsp";
				}					
				
			
														
		}	else if (strOpt.equals("29")) { // GRABAR PESTAÑA DATOS DE GESTION DEFENSORIAL TDATOSDEF
			logger.debug("Dentro ctrlFicha de la opcion 29");
			
								Ficha objFicha=new Ficha();
								
								Ficha_t obj=new Ficha_t();
								String[] sup;
								sup=req.getParameterValues("SUP");
								for(int i=0;i<sup.length;i++)logger.debug("sup: "+sup[i]);
								//ponemos en obj los parameters de fdatosad.jsp
								obj.setdficacc(req.getParameter("DFICACC")!=null?req.getParameter("DFICACC"):"");
								obj.setcade(req.getParameter("CADE")!=null?req.getParameter("CADE"):"");
								obj.setcefi(req.getParameter("CEFI")!=null?req.getParameter("CEFI"):"");
								obj.setfichero(req.getParameter("FICHERO")!=null?req.getParameter("FICHERO"):"");
								obj.setfficciecaso(req.getParameter("FFICCIECASO")!=null?req.getParameter("FFICCIECASO"):"");
								obj.setctin(req.getParameter("CTIN")!=null?req.getParameter("CTIN"):"");
								obj.setdficobs(req.getParameter("DFICOBS")!=null?req.getParameter("DFICOBS"):"");
								obj.setbfictipate(req.getParameter("BFICTIPATE")!=null?req.getParameter("BFICTIPATE"):"");
								obj.setbficcer(req.getParameter("BFICCER")!=null?req.getParameter("BFICCER"):"0");
								//ahora ayear, cas y correl 
								obj.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
								obj.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
								obj.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
								//finalmente el cusumodi
								obj.setcusumodi(usuario.getUsuario());
								//procedemos a grabar...
								objFicha.grabaFichero(con,obj);
								
								if(objFicha.isOK()){
									req.setAttribute("msg_error","tdatosdef se grabo correctamente");
									strUrl="/servlet/CtrlFicha?opt=25&CAS="+obj.getcas()+"&AYEAR="+obj.getayear()+"&CORREL="+obj.getcorrel();
									//strUrl="/jsp/fdatosad.jsp";
								}else{
									req.setAttribute("msg_error","Error, opt=28; error en grabar en tdatosg");
									strUrl = "/error/error_general.jsp";
								}					
					
							
						}				
			
			
			else if (strOpt.equals("30")) { // Insertar Conclusiones
			
			logger.debug("Dentro ctrlFicha de opt 30 ");
			logger.debug("insertar conclusiones ");
			Ficha objFicha=new Ficha();
			Ficha_t fdef=(Ficha_t)session.getAttribute("objFicha_t");
			/*fdef.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
			fdef.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
			fdef.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");*/
			fdef.setCconclu(req.getParameter("CCON")!=null?req.getParameter("CCON"):"");
			fdef.setdCargo(req.getParameter("DCARGO")!=null?req.getParameter("DCARGO"):"");
			fdef.setCquien(req.getParameter("CQUIEN")!=null?req.getParameter("CQUIEN"):"");
			
			fdef.setcusucrea(usuario.getUsuario());
			fdef.setCanula(req.getParameter("CANULAR")!=null?req.getParameter("CANULAR"):"");
			
			if(fdef.getCanula().equals("3") || fdef.getCanula().equals("4")){
				fdef.setbestadoreg(fdef.getCanula());
				//actualizar estado en ficha
				objFicha.actualizarFicha(con, fdef);
				if (objFicha.isOK()){
					//objFicha.getFicha(con,fdef);//obtinee ficha nuevamente al grabar
					session.setAttribute("objFicha_t", fdef);
					session.removeAttribute("cmso");
					session.removeAttribute("cconclu");
					session.setAttribute("cmso", fdef.getcmso());
					session.setAttribute("cconclu", fdef.getCconclu());
					req.setAttribute("msg_error","El registro se grabó correctamente");
					strUrl="/servlet/CtrlFicha?opt=26&CAS="+fdef.getcas()+"&AYEAR="+fdef.getayear()+"&CORREL="+fdef.getcorrel();
				}
				else {
				  req.setAttribute("msg_error"," CtrlFicha strOpt30: Error en la grabacion ");
				  strUrl = "/error/error.jsp"; 
				}
				
			}else{
			
			objFicha.getTipoSolicitud(con,fdef);
			if(fdef.getcmso().equals("1") && fdef.getCconclu().equals("3")){
				fdef.setcAccSeg(req.getParameter("CCON2")!=null?req.getParameter("CCON2"):"");
				fdef.setdAccSeg(req.getParameter("DFICOBS")!=null?req.getParameter("DFICOBS"):"");
				fdef.setbestadoreg("1");
			}else{
				fdef.setcAccSeg("");
				fdef.setdAccSeg("");
				fdef.setbestadoreg("0");
			}
			
			objFicha.fichaInsertarConclusiones(con, fdef);
				if (objFicha.isOK()){
					session.setAttribute("objFicha_t", fdef);
					session.removeAttribute("cmso");
					session.removeAttribute("cconclu");
					session.setAttribute("cmso", fdef.getcmso());
					session.setAttribute("cconclu", fdef.getCconclu());
					req.setAttribute("msg_error","El registro se grabó correctamente");
					strUrl="/servlet/CtrlFicha?opt=26&CAS="+fdef.getcas()+"&AYEAR="+fdef.getayear()+"&CORREL="+fdef.getcorrel();
				}
				else {
				  req.setAttribute("msg_error"," CtrlFicha strOpt30: Error en la grabacion ");
				  strUrl = "/error/error.jsp"; 
				}
			}	
		
		}else if (strOpt.equals("31")) { // GRABAR
				logger.debug("Dentro ctrlFicha de la opcion 31");
				logger.debug("Dentro de opt 31, GRABAR en la coleccion de datos para tdatosdef.jsp");
				logger.debug("El ccas de este usuario es:"+strCas);
			
				Ficha objFicha=new Ficha();
				Ficha_t fdef=(Ficha_t)session.getAttribute("objFicha_t");
				/*
				fdef.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				fdef.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				fdef.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				fdef.setccas(strCas);*/
				fdef.setCquien(req.getParameter("CQUIEN"));
				fdef.setCfecha(req.getParameter("FECHAQ"));
				fdef.setGestor(req.getParameter("DFICOBS"));
				fdef.setCaccreal(req.getParameter("CACCREAL"));
				fdef.setbestadoreg(req.getParameter("ESTADO"));
				fdef.setdCargo(req.getParameter("DCARGO"));
				fdef.setcusucrea(usuario.getUsuario());
				// ini img
				//procesaFicheros(req);
				//fdef.setfichero(req.getParameter("FICHERO"));
				// fin img 
				
				objFicha.insertarDatosDef(con,fdef);
				if(objFicha.isOK()){
					
					session.setAttribute("objFicha_t",fdef);
					req.setAttribute("msg_error","El registro se grabó correctamente");
					strUrl="/servlet/CtrlFicha?opt=25&CAS="+fdef.getcas()+"&AYEAR="+fdef.getayear()+"&CORREL="+fdef.getcorrel();
				}				
			}else if (strOpt.equals("32")) { // MODIFICAR
				logger.debug("Dentro ctrlFicha de opt 32");
				logger.debug("El ccas de este usuario es:"+strCas);
				Ficha objFicha=new Ficha();
				String accion = StringUtils.defaultString(req.getParameter("CAS"));
				Ficha_t fdef=(Ficha_t)session.getAttribute("objFicha_t");
				fdef.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				fdef.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				fdef.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				fdef.setccas(strCas);
				fdef.setCquien(req.getParameter("CQUIEN")!=null?req.getParameter("CQUIEN"):"");
				fdef.setCfecha(req.getParameter("FECHAQ")!=null?req.getParameter("FECHAQ"):"");
				fdef.setGestor(req.getParameter("DFICOBS")!=null?req.getParameter("DFICOBS"):"");
				fdef.setCaccreal(req.getParameter("CACCREAL")!=null?req.getParameter("CACCREAL"):"");
				fdef.setbestadoreg(req.getParameter("ESTADO")!=null?req.getParameter("ESTADO"):"");
				fdef.setIddatdef(req.getParameter("IDDEF")!=null?req.getParameter("IDDEF"):"");
				fdef.setdCargo(req.getParameter("DCARGO")!=null?req.getParameter("DCARGO"):"");
				fdef.setcusumodi(usuario.getUsuario());
				if(StringUtils.equals(accion, "E")){
					objFicha.deleteDatosDef(con,fdef);
				}else{
					objFicha.updateDatosDef(con,fdef);
				}
				if(objFicha.isOK()){
					
					session.setAttribute("objFicha_t",fdef);
					req.setAttribute("msg_error","El registro se modificó correctamente");
					strUrl="/servlet/CtrlFicha?opt=25&CAS="+fdef.getcas()+"&AYEAR="+fdef.getayear()+"&CORREL="+fdef.getcorrel();
				}				
			}else if (strOpt.equals("33")) { // Modificar
				logger.debug("Dentro ctrlFicha de opt 33");
				logger.debug("El ccas de este usuario es:"+strCas);
				Ficha objFicha=new Ficha();
				Ficha_t fdef=new Ficha_t();
				fdef.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				fdef.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				fdef.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				fdef.setCfconclu(req.getParameter("FCON")!=null?req.getParameter("FCON"):"");
				fdef.setCconclu(req.getParameter("CCON")!=null?req.getParameter("CCON"):"");
				
				if(fdef.getCfconclu().equals("1") && fdef.getCconclu().equals("3")){
					fdef.setcAccSeg(req.getParameter("CCON2")!=null?req.getParameter("CCON2"):"");
					fdef.setdAccSeg(req.getParameter("DFICOBS")!=null?req.getParameter("DFICOBS"):"");	
				}else{
					fdef.setcAccSeg("");
					fdef.setdAccSeg("");
				}
				
				objFicha.getConcluSeguimiento(con, fdef);
				if (objFicha.isOK()){
					//insertar
				}else{
					//actualizar
				}
				
				objFicha.fichaUpdateConclusiones(con, fdef);
					if (objFicha.isOK()){
						session.setAttribute("objFicha_t", objFicha);
						
						req.setAttribute("msg_error","El registro se grabó correctamente");
						strUrl="/servlet/CtrlFicha?opt=26&CAS="+fdef.getcas()+"&AYEAR="+fdef.getayear()+"&CORREL="+fdef.getcorrel();
						
					}
					else {
					  req.setAttribute("msg_error"," CtrlFicha strOpt30: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}
			
			} else if (strOpt.equals("34")) { // LISTA LAS FICHAS ASIGNADAS
				logger.debug("Dentro ctrlFicha de la opcion 34");
				req.setAttribute("vDocumento",BDTablas.getIdentidad(con));
				req.setAttribute("vRed",BDTablas.getRed(con));
				req.setAttribute("vCas",BDTablas.getCas(con));
				req.setAttribute ("vAyear", BDTablas.getAyear());
				Ficha_t params=new Ficha_t();
				params.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				params.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				params.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				params.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				params.setcred(req.getParameter("CRED")!=null?req.getParameter("CRED"):"");
				params.setccas(req.getParameter("CCAS")!=null?req.getParameter("CCAS"):"");
				params.setctdi(req.getParameter("CTDI")!=null?req.getParameter("CTDI"):"");
				params.setdficdid(req.getParameter("DFICDID")!=null?req.getParameter("DFICDID"):"");
				params.setdapepat(req.getParameter("DAPEPAT")!=null?req.getParameter("DAPEPAT"):"");
				params.setdapemat(req.getParameter("DAPEMAT")!=null?req.getParameter("DAPEMAT"):"");
				params.setdnom(req.getParameter("DNOM")!=null?req.getParameter("DNOM"):"");
				params.setdapepatrec(req.getParameter("DAPEPATREC")!=null?req.getParameter("DAPEPATREC"):"");
				params.setdapematrec(req.getParameter("DAPEMATREC")!=null?req.getParameter("DAPEMATREC"):"");
				params.setdnomrec(req.getParameter("DNOMREC")!=null?req.getParameter("DNOMREC"):"");
				params.setdda(req.getParameter("FECHA1")!=null?req.getParameter("FECHA1"):"");
				params.setdda1(req.getParameter("FECHA2")!=null?req.getParameter("FECHA2"):"");
				
				params.setCdelegado(usuario.getUsuario());
				
				Ficha objFicha=new Ficha();
				params=objFicha.getLisFichaAsignadaxUser(con, params);
						
				if(objFicha.isOK()){
					req.setAttribute("objLst",params);
					strUrl="/jsp/list_fichas_asignadas.jsp";
				}
				else{
					logger.debug("CtrlFicha strOpt4: No hay información según los criterios ingresados ");
					req.setAttribute("msg_error","No se obtuvieron resultados.");
					strUrl="/jsp/list_fichas_asignadas.jsp";
				}
				objFicha=null;
				params=null;
									
				}
			
			
			else if (strOpt.equals("35")) { // INGRESO A datos desde portal
				logger.debug("Dentro ctrlFicha de la opcion 35");
					Ficha objFicha = new Ficha();
					Ficha_t obj = new Ficha_t();
					obj.setcas(Constantes.CODIGO_SEDE_CENTRAL);
					obj.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR").trim():"");
					obj.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL").trim():"");
					obj.setctdi(req.getParameter("CTDI")!=null?req.getParameter("CTDI").trim():"");//cheked
					obj.setdficdid(req.getParameter("DFICDID")!=null?req.getParameter("DFICDID").trim():"");//cheked
					obj.setdapepat(req.getParameter("DAPEPAT")!=null?req.getParameter("DAPEPAT").trim():"");//cheked
					obj.setdapemat(req.getParameter("DAPEMAT")!=null?req.getParameter("DAPEMAT").trim():"");//cheked
					obj.setdnom(req.getParameter("DNOM")!=null?req.getParameter("DNOM").trim():"");//cheked
					obj.setnficeda(req.getParameter("NFICEDA")!=null?req.getParameter("NFICEDA").trim():"");//cheked
					obj.setddicsex(req.getParameter("DDICSEX")!=null?req.getParameter("DDICSEX").trim():"");//cheked
					//codigo extra
					obj.set2ubigeo(req.getParameter("DEPARTAMENTO")!=null?req.getParameter("DEPARTAMENTO").trim():"");
					obj.set4ubigeo(req.getParameter("PROVINCIA")!=null?req.getParameter("PROVINCIA").trim():"");
					//fin codigo extra
					obj.setcficubigeo(req.getParameter("CFICUBIGEO")!=null?req.getParameter("CFICUBIGEO").trim():"");//cheked
					obj.setdficdir(req.getParameter("DFICDIR")!=null?req.getParameter("DFICDIR").trim():"");//cheked
					obj.setdfictel(req.getParameter("DFICTEL")!=null?req.getParameter("DFICTEL").trim():"");//cheked
					obj.setdficmail(req.getParameter("DFICMAIL")!=null?req.getParameter("DFICMAIL").trim():"");//cheked
					obj.setctpr(req.getParameter("CTPR")!=null?req.getParameter("CTPR").trim():"");//cheked
					obj.setcfictipseg(req.getParameter("CFICTIPSEG")!=null?req.getParameter("CFICTIPSEG").trim():"");//cheked
					obj.setdapepatrec(req.getParameter("DAPEPATREC")!=null?req.getParameter("DAPEPATREC").trim():"");//cheked
					obj.setdapematrec(req.getParameter("DAPEMATREC")!=null?req.getParameter("DAPEMATREC").trim():"");//cheked
					obj.setdnomrec(req.getParameter("DNOMREC")!=null?req.getParameter("DNOMREC").trim():"");//cheked
					obj.setdficdidrec(req.getParameter("DFICDIDREC")!=null?req.getParameter("DFICDIDREC").trim():"");//cheked
					obj.setctdirec(req.getParameter("CTDIREC")!=null?req.getParameter("CTDIREC").trim():"");
					obj.setcmso(req.getParameter("CMSO")!=null?req.getParameter("CMSO").trim():"");//cheked
					obj.setbrevhclini(req.getParameter("BREVHCLINI")!=null?req.getParameter("BREVHCLINI").trim():"0");//checked
					obj.setctin(Constantes.CODIGO_PORTAL_ESSALUD);//tipo de ingreso
					obj.setdfichec(req.getParameter("DFICHEC")!=null?req.getParameter("DFICHEC").trim():"");
					obj.setReclaDir(req.getParameter("DIRRECLA")!=null?req.getParameter("DIRRECLA").trim():"");
					obj.setReclaTel(req.getParameter("TELRECLA")!=null?req.getParameter("TELRECLA").trim():"");
					obj.setReclaMail(req.getParameter("MAILRECLA")!=null?req.getParameter("MAILRECLA").trim():"");
					obj.setReclaCel(req.getParameter("CELRECLA")!=null?req.getParameter("CELRECLA").trim():"");
					//obj.setCdelegado(req.getParameter("CDELEGADO")!=null?req.getParameter("CDELEGADO").trim():"");
					obj.setcred(req.getParameter("CRED")!=null?req.getParameter("CRED").trim():"");
					obj.setccas(req.getParameter("CCAS")!=null?req.getParameter("CCAS").trim():"");
					obj.setcusucrea(Constantes.USUARIO_PORTAL);
					obj.setCdelegado(null);

					objFicha.FichaUpdate(con, obj, strPerfil, strCaso);
					if (objFicha.isOK()) {
						if(strCaso.compareTo(Constantes.OPCION_INSERTAR)==0){
							if(obj.getcorrel().compareTo("err")!=0 && obj.getayear().compareTo("err")!=0){
								req.setAttribute("msg_error","El registro se grabó correctamente");
								logger.debug("Los datos que requiero: "+obj.getayear()+", el cas "+obj.getcas()+" y el correl= "+obj.getcorrel());
								strUrl="/servlet/CtrlFicha?opt=38&CAS="+obj.getcas()+"&AYEAR="+obj.getayear()+"&CORREL="+obj.getcorrel();
								//strUrl="/htm/portal_index.jsp";
							}
							else{
								req.setAttribute("msg_error","Error throwable: por favor intente mas tarde");
								logger.debug("Error en CtrlFicha: Sin codigo");
								strUrl = "/error/error_general.jsp";
							}
						}else{
							req.setAttribute("msg_error","Error throwable CtrlFicha Opt2: por favor intente mas tarde");
							logger.debug("Error throwable CtrlFicha Opt2: no tengo opt");
							strUrl = "/error/error.jsp";
						}
					}
					else {
					  req.setAttribute("msg_error"," CtrlFicha strOpt2: Error en la grabacion ");
					  strUrl = "/error/error.jsp"; 
					}
					objFicha = null; 
					obj = null;
				
				
			}else if (strOpt.equals("36")) { // BUSCA REGISTROS INGRESADAS POR EL PORTAL
				logger.debug("Dentro ctrlFicha de la opcion 36");
				ListFicha_t lstFicha = new ListFicha_t();
				lstFicha.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				lstFicha.setCodUsuario(usuario.getUsuario());	
				Ficha objFicha = new Ficha();
				lstFicha = objFicha.getLisFichaPortal(con, lstFicha);
				if (objFicha.isOK()) {
					req.setAttribute ("vCodigo", BDTablas.getCodigo(con));
					req.setAttribute("objLst",lstFicha);
					strUrl="/jsp/rq_ficha_portal.jsp";
					
				}
				else {
					req.setAttribute("msg_error"," CtrlFicha strOpt6: Error al buscar ");
				  strUrl = "/error/error.jsp";
				}
				objFicha=null;
				lstFicha=null;
			}
			else if (strOpt.equals("37")) { //El strOpt.equals("37") es reservado para la carga
				logger.debug("Dentro ctrlFicha de la opcion 37");
				
				//List listaArchivosSubidos = new ArrayList();
				Ficha objFicha=new Ficha();
				Ficha_t fdef=new Ficha_t();
				fdef.setcas(req.getAttribute("CAS").toString()!=null?req.getAttribute("CAS").toString():"");
				fdef.setayear(req.getAttribute("AYEAR").toString()!=null?req.getAttribute("AYEAR").toString():"");
				fdef.setcorrel(req.getAttribute("CORREL").toString()!=null?req.getAttribute("CORREL").toString():"");
				
				// fin img 
				String tamano = req.getAttribute("TAMANO").toString()!=null?req.getAttribute("TAMANO").toString():"";
				

				logger.debug("ficheroBean.getArchivoImagen() "+tamano);
				if(Integer.parseInt(tamano)<=1024){
					//Object dataFicheros=session.getAttribute("dataFichero");
					
					//logger.debug(""+fdef.getIdGestion());
					//objFicha.insertarFichero(con,fdef,strExt,usuario.getUsuario(),ficheroBean.getArchivoImagen());
					objFicha.insertarFichero(con,fdef,strExt,usuario.getUsuario());
					
					//String strRealPath = "D:\\archivosCargados\\";
					String strRealPath = req.getSession().getServletContext().getRealPath("")+"/archivosCargados/";
					//String strRealPath = req.getContextPath()+"/archivosCargados";
					logger.debug("strRealPath "+strRealPath);
					File destino = new File(strRealPath);
					FileItem item = getFileItem();
					String _extension = FilenameUtils.getExtension(item.getName());
					logger.debug("**********************extension del archivo: "+_extension);
					
					if(objFicha.isOK()){
						if((item.getSize()/1024)<=1024){
							if("pdf".equals(_extension) || "jpg".equals(_extension)){
							   item.write(new File(destino,req.getAttribute("AYEAR")+"_"+req.getAttribute("CAS")+"_"+req.getAttribute("CORREL")+"_"+fdef.getIdFichero()+"."+ _extension));
							}
						}
						
					

					String sDirectorio = req.getContextPath()+"/archivosCargados";
					logger.debug("strRealPath "+sDirectorio);
					

						session.setAttribute("objFicha_t",fdef);
						//session.setAttribute("listaArchivos","listaArchivosSubidos");
						 req.setAttribute("opc","registro");
						req.setAttribute("msg_error","El archivo se grabó correctamente");
						strUrl="/servlet/CtrlFicha?opt=25&CAS="+fdef.getcas()+"&AYEAR="+fdef.getayear()+"&CORREL="+fdef.getcorrel();
				}
					
				}else{
					req.setAttribute("msg_error","El tamaño del archivo es mayor a 1MB. ");
					strUrl="/servlet/CtrlFicha?opt=25&CAS="+fdef.getcas()+"&AYEAR="+fdef.getayear()+"&CORREL="+fdef.getcorrel();
				}
			}
			
			
			else if (strOpt.equals("38")){ // LUEGO DE GRABAR VIENE A datos portal con datos.JSP
				logger.debug("Dentro ctrlFicha de la opcion 38");
				Ficha objFicha = new Ficha();
				Ficha_t lstFicha = new Ficha_t();
				lstFicha.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				lstFicha.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				lstFicha.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				lstFicha=objFicha.getFicha(con,lstFicha);
				if(objFicha.isOK()){
					req.setAttribute("objFicha_t",lstFicha);
					strUrl="/htm/portal_modifica.jsp";
				}
				else{
					req.setAttribute("msg_error"," CtrlFicha strOpt27: error en la busqueda de la ficha... ");
					strUrl = "/error/error.jsp";
				}
			}
			else if (strOpt.equals("39")){ //CREACION DE POPUP ACPERSUASION.JSP -- NPONCE PESTAÑA CONCLUSIONES.JSP
				logger.debug("Dentro ctrlFicha de la opcion 39");
				
				
				Ficha objFicha = new Ficha();
				//Ficha_t lstFicha = new Ficha_t();
				Ficha_t lstFicha = (Ficha_t)session.getAttribute("objFicha_t");
				 lstFicha.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				 lstFicha.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				 lstFicha.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				 lstFicha.setcmso(req.getParameter("FCON")!=null?req.getParameter("FCON"):"");
				 lstFicha.setCconclu(req.getParameter("CCON")!=null?req.getParameter("CCON"):"");
				 lstFicha.setIdConclu(req.getParameter("IDCONCLU")!=null?req.getParameter("IDCONCLU"):"");
				  lstFicha.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");

				  //HashTable d=new HashTable();
				  lstFicha.setHshLista1(new Hashtable());

					req.setAttribute ("vAccReal", BDTablas.getAccReal(con));
					objFicha.getFdatosPersuasion(con, lstFicha);
					objFicha.getFicha(con, lstFicha);
				  
				   req.setAttribute("objPersua",lstFicha);
				    req.setAttribute ("vAccPersuas",BDTablas.getAccPersuas(con));
					strUrl="/jsp/acpersuacion.jsp";
				//}
				/*else{
				req.setAttribute("msg_error"," CtrlFicha strOpt39: error en la busqueda de la ficha... ");
				strUrl = "/error/error.jsp";
				}*/
			}
			
			else if (strOpt.equals("40")){ //grabar ACPERSUASION.JSP -- NPONCE PESTAÑA CONCLUSIONES.JSP
				logger.debug("Dentro ctrlFicha de la opcion 40");
				Ficha_t ficha = new Ficha_t();
				Ficha objFicha = new Ficha();
				String accpersua = req.getParameter("CPER")!=null?req.getParameter("CPER"):"";
				String ayear =req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"";
				String cas =req.getParameter("CAS")!=null?req.getParameter("CAS"):"";
				String cmso =req.getParameter("CMSO")!=null?req.getParameter("CMSO"):"";
				String cconclu =req.getParameter("CCONCLU")!=null?req.getParameter("CCONCLU"):"";
				String correl = req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"";
				String cdatospres = req.getParameter("NOMPRESTADOR")!=null?req.getParameter("NOMPRESTADOR"):"";
				String fecResp = req.getParameter("FECHARESP")!=null?req.getParameter("FECHARESP"):"";
				String cargo = req.getParameter("CARGO")!=null?req.getParameter("CARGO"):"";
				String detalle = req.getParameter("DETALLE")!=null?req.getParameter("DETALLE"):"";
				String idconclu = req.getParameter("IDCONCLU")!=null?req.getParameter("IDCONCLU"):"";
				String tipoOperacion =  req.getParameter("TIPOOPERACION")!=null?req.getParameter("TIPOOPERACION"):"";
				
				
				ficha.setcper(accpersua);
				ficha.setayear(ayear);
				ficha.setcas(cas);
				ficha.setcorrel(correl);
				ficha.setNombrePrestador(cdatospres);
				ficha.setFecharesp(fecResp);
				ficha.setCargoper(cargo);
				ficha.setDetalleper(detalle);
				ficha.setcmso(cmso);
				ficha.setcusucrea(usuario.getUsuario());
				ficha.setCconclu(cconclu);
				ficha.setIdConclu(idconclu);
				ficha.setTipoOperacion(tipoOperacion);
				//lstFicha.setcmso(req.getParameter("IDPERSUA")!=null?req.getParameter("IDPERSUA"):"");
				
				objFicha.grabarPersuasion(con,ficha);
				//System.out.println("objFicha.....: "+objFicha);
				if(objFicha.isOK()){
				
				    req.setAttribute("objFicha_t",ficha);
				    req.setAttribute("cmso",cmso);
				   //req.setAttribute ("vAccPersuas",BDTablas.getAccPersuas(con,ficha.getcper()));
				    strUrl="/servlet/CtrlFicha?opt=39&CAS="+ficha.getcas()+"&AYEAR="+ficha.getayear()+"&CORREL="+ficha.getcorrel()+"&FCON="+cmso+"&CCON="+cconclu+"&IDCONCLU="+idconclu;
				}else{
				req.setAttribute("msg_error"," CtrlFicha strOpt: error en grabar persuasion... ");
				strUrl = "/error/error.jsp";
				}
			}
			
			else if (strOpt.equals("41")){//creacion del jsp --archivosUpload --25/02/2015
			  
                logger.debug("Dentro ctrlFicha de la opcion 41");
				
				
				Ficha objFicha = new Ficha();
				//Ficha_t lstFicha = new Ficha_t();
				Ficha_t lstFicha = (Ficha_t)session.getAttribute("objFicha_t");
				 lstFicha.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				 lstFicha.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				 lstFicha.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				// lstFicha.setIdGestion(req.getParameter("IDTDATDEF")!=null?req.getParameter("IDTDATDEF"):"");
				
				  lstFicha.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");

				  //HashTable d=new HashTable();
				  lstFicha.setHshLista2(new Hashtable());

					//req.setAttribute ("vAccFicheros", BDTablas.getAccReal(con));
					objFicha.getFdatosFicheros(con, lstFicha);
					objFicha.getFicha(con, lstFicha);
				  
				
				  
				   req.setAttribute("objUpload",lstFicha);
				   //req.setAttribute("grabo","0");
				  //  req.setAttribute ("vAccPersuas",BDTablas.getAccPersuas(con));
					strUrl="/jsp/archivosUpload.jsp";
				//}
				/*else{
				req.setAttribute("msg_error"," CtrlFicha strOpt39: error en la busqueda de la ficha... ");
				strUrl = "/error/error.jsp";
				}*/
				
			}	
			
			else if (strOpt.equals("46")){//creacion del jsp --archivosUpload --25/02/2015
				  
                logger.debug("Dentro ctrlFicha de la opcion 46");
				
				
				Ficha objFicha = new Ficha();
				//Ficha_t lstFicha = new Ficha_t();
				Ficha_t lstFicha = (Ficha_t)session.getAttribute("objFicha_t");
				 lstFicha.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				 lstFicha.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				 lstFicha.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				 lstFicha.setIdArchivo(req.getParameter("IDARCHIVO")!=null?req.getParameter("IDARCHIVO"):"");
				 String nomfichero=req.getParameter("DFNOMB")!=null?req.getParameter("DFNOMB"):"";
				// lstFicha.setIdGestion(req.getParameter("IDTDATDEF")!=null?req.getParameter("IDTDATDEF"):"");
				
				  lstFicha.setPag(req.getParameter("pg")!=null?req.getParameter("pg"):"1");
				  
				  lstFicha.setfichero(req.getContextPath()+"/archivosCargados/"+lstFicha.getayear()+"_"+lstFicha.getcas()+"_"+lstFicha.getcorrel()+nomfichero);

					//req.setAttribute ("vAccFicheros", BDTablas.getAccReal(con));
					objFicha.getFdatosFicheros(con, lstFicha);
					objFicha.getFicha(con, lstFicha);
				  
				
				  
				   req.setAttribute("objUpload",lstFicha);
				   //req.setAttribute("grabo","0");
				  //  req.setAttribute ("vAccPersuas",BDTablas.getAccPersuas(con));
					strUrl="/jsp/archivosUpload.jsp";
				//}
				/*else{
				req.setAttribute("msg_error"," CtrlFicha strOpt39: error en la busqueda de la ficha... ");
				strUrl = "/error/error.jsp";
				}*/
				
			}	
			
			
			
			
			else if (strOpt.equals("43")){ //Eliminar archivoupload.JSP -- erick vargas 
				logger.debug("Dentro ctrlFicha de la opcion 43");
				
				Ficha objFicha=new Ficha();
				Ficha_t lstFicha = (Ficha_t)session.getAttribute("objFicha_t");
				
				//fdef.setcas(req.getParameter("CAS").toString()!=null?req.getParameter("CAS").toString():"");
				//fdef.setayear(req.getParameter("AYEAR").toString()!=null?req.getParameter("AYEAR").toString():"");
				//fdef.setcorrel(req.getParameter("CORREL").toString()!=null?req.getParameter("CORREL").toString():"");
				//fdef.setDfNomb(req.getParameter("DFNOMB").toString()!=null?req.getParameter("DFNOMB").toString():"");
				lstFicha.setIdArchivo(req.getParameter("IDARCHIVO").toString()!=null?req.getParameter("IDARCHIVO").toString():"");
				lstFicha.setcusumodi(usuario.getUsuario());
			
				
				objFicha.actualizarEstadoUploads(con,lstFicha);//borrado logico 1:activo  0:eliminado
				//System.out.println("objFicha.....: "+objFicha);
				if(objFicha.isOK()){
				
				    req.setAttribute("objFicha_t",lstFicha);
				    req.setAttribute("msg_error","El registro se elimino correctamente");
				    strUrl="/servlet/CtrlFicha?opt=41&CAS="+lstFicha.getcas()+"&AYEAR="+lstFicha.getayear()+"&CORREL="+lstFicha.getcorrel();
				}
				 //   else{
				//req.setAttribute("msg_error"," CtrlFicha strOpt: error en eliminar uploads... ");
				//strUrl = "/error/error.jsp";
				//}
			}
			
			 else if (strOpt.equals("44")) { // MODIFICAR PERSUACION 28/02/2015
				logger.debug("Dentro ctrlFicha de opt 44");
				logger.debug("El ccas de este usuario es:"+strCas);
				Ficha objFicha=new Ficha();
				Ficha_t ficha=(Ficha_t)session.getAttribute("objFicha_t");
				ficha.setcas(req.getParameter("CAS")!=null?req.getParameter("CAS"):"");
				ficha.setayear(req.getParameter("AYEAR")!=null?req.getParameter("AYEAR"):"");
				ficha.setcorrel(req.getParameter("CORREL")!=null?req.getParameter("CORREL"):"");
				ficha.setccas(strCas);
				ficha.setNombrePrestador(req.getParameter("NOMPRESTADOR")!=null?req.getParameter("NOMPRESTADOR"):"");
				ficha.setFecharesp(req.getParameter("FECHARESP")!=null?req.getParameter("FECHARESP"):"");
				ficha.setDetalleper(req.getParameter("DETALLE")!=null?req.getParameter("DETALLE"):"");
				ficha.setcper(req.getParameter("CPER")!=null?req.getParameter("CPER"):"");
				ficha.setbestadoreg(req.getParameter("ESTADO")!=null?req.getParameter("ESTADO"):"");
				ficha.setIdPersua(req.getParameter("IDPERSUA")!=null?req.getParameter("IDPERSUA"):"");
				ficha.setCargoper(req.getParameter("CARGO")!=null?req.getParameter("CARGO"):"");
				ficha.setcusumodi(usuario.getUsuario());
				ficha.setTipoOperacion(req.getParameter("TIPOOPERACION")!=null?req.getParameter("TIPOOPERACION"):"");
				
				
				objFicha.updatePersuasion(con,ficha);
				if(objFicha.isOK()){
					//req.setAttribute("objPersua",ficha);
					//session.setAttribute("objFicha_t",ficha);
					
					req.setAttribute("objFicha_t",ficha);
					req.setAttribute("msg_error","El registro se modificó correctamente");
					strUrl="/servlet/CtrlFicha?opt=39&CAS="+ficha.getcas()+"&AYEAR="+ficha.getayear()+"&CORREL="+ficha.getcorrel();
				}				
			}
			
			
			 else if (strOpt.equals("45")) { // ELIMINAR PERSUACION  28/02/2015
					logger.debug("Dentro ctrlFicha de opt 45");
					logger.debug("El ccas de este usuario es:"+strCas);
					Ficha objFicha=new Ficha();
					Ficha_t fichaAcPersua=(Ficha_t)session.getAttribute("objFicha_t");
					
					
					fichaAcPersua.setIdPersua(req.getParameter("IDPERSUA").toString()!=null?req.getParameter("IDPERSUA").toString():"");
					fichaAcPersua.setbestadoreg(req.getParameter("ESTADO")!=null?req.getParameter("ESTADO"):"");
					fichaAcPersua.setcmso(req.getParameter("CMSO")!=null?req.getParameter("CMSO"):"");
					fichaAcPersua.setCconclu(req.getParameter("CCONCLU")!=null?req.getParameter("CCONCLU"):"");
					fichaAcPersua.setIdConclu(req.getParameter("IDCONCLU")!=null?req.getParameter("IDCONCLU"):"");
					
					
					fichaAcPersua.setcusumodi(usuario.getUsuario());
					fichaAcPersua.setTipoOperacion(req.getParameter("TIPOOPERACION")!=null?req.getParameter("TIPOOPERACION"):"");
					
					objFicha.eliminaPersuasion(con,fichaAcPersua);//borrado logico 1:activo  0:eliminado
					
				if(objFicha.isOK()){	
					 req.setAttribute("objFicha_t",fichaAcPersua);
					  
					  req.setAttribute("msg_error","El registro se eliminó correctamente");
					  strUrl="/servlet/CtrlFicha?opt=39&CAS="+fichaAcPersua.getcas()+"&AYEAR="+fichaAcPersua.getayear()+"&CORREL="+fichaAcPersua.getcorrel();
					
				}				
		    }
			
			else if (strOpt.equals("42")){ //buscar fichero
				logger.debug("Dentro ctrlFicha de la opcion 42");
				Ficha_t fdef=new Ficha_t();
				Ficha objFicha=new Ficha();
				fdef.setcas(req.getParameter("CAS").toString()!=null?req.getParameter("CAS").toString():"");
				fdef.setayear(req.getParameter("AYEAR").toString()!=null?req.getParameter("AYEAR").toString():"");
				fdef.setcorrel(req.getParameter("CORREL").toString()!=null?req.getParameter("CORREL").toString():"");
				fdef.setDfNomb(req.getParameter("DFNOMB").toString()!=null?req.getParameter("DFNOMB").toString():"");
				fdef.setIdArchivo(req.getParameter("IDARCHIVO").toString()!=null?req.getParameter("IDARCHIVO").toString():"");
				
				//Integer correl=Integer.valueOf(session.getAttribute("idcorrelFichero").toString());
			   
				//logger.debug("Dentro ctrlFicha de la opcion 41"+correl);
				
			//	req.setAttribute ("vDocumento", BDTablas.getListadoFicheros(con,correl));
				//req.setAttribute ("vRed", BDTablas.getRed(con));
				//req.setAttribute ("vCas", BDTablas.getCas(con));
				//req.setAttribute ("vAyear", BDTablas.getAyear());
				//strUrl="/jsp/rq_ficha.jsp";
				
				//String sDirectorio = "D:\\archivosCargados";
				String sDirectorio = req.getContextPath()+"/archivosCargados";
				//String sDirectorio = "C:\\Users\\user\\AppData\\Local\\Riid"; 02032015
				//String sDirectorio1 = "C:\\Users\\user\\AppData\\Local\\Riid\\JPG";
				//String sDirectorio2 = "C:\\Users\\user\\AppData\\Local\\Riid\\PDF";
				
				
				//String path = fdef.getDfNomb();
				//String ext = Files.getFileExtension(path);
				//System.out.println(ext); //prints txt
	/*		File f = new File(sDirectorio);
				
				  if (f.exists()){
                      // Recuperamos la lista de ficheros
                      File[] ficheros = f.listFiles();
                      for (int x=0;x<ficheros.length;x++){
                    	      if(ficheros[x].getName().equals("2015_038_4498.pdf")){
		                              System.out.println(ficheros[x].getName());
		                              System.out.println("paso");
		                             
		                               ImageIO.read(new File("2015_038_4498.pdf"));
		                          //    listaArchivosSubidos.get(x).setnum_corr_archivo
		                              HashMap oFilaAdd = new HashMap();
		                              oFilaAdd.put("num_corr_archivo", String.valueOf(x));
		                              oFilaAdd.put("nom_archivo",ficheros[x].getName());
		                             // listaArchivosSubidos.set(x,oFilaAdd);
		                            // listaArchivosSubidos.add(oFilaAdd);
                              }          
                      }
              } else{
                      System.out.println("No existe ese directorio");
              }
				  */
				  
				  
				  File fichero = new File(sDirectorio);
			        if (fichero.exists())
			        {	//fichero.canRead();
			        	 
			        	fdef.setfichero(req.getContextPath()+"/Riid/"+"2015_038_4498.pdf");
			        	//fdef.setfichero(path);
			        	//req.getContextPath()
			         }else{
			        	fdef.setfichero("");
			        }
					
					
					//req.setAttribute ("vAccReal", BDTablas.getAccReal(con));
					//objFicha.getListFdatosDef(con,fdef);
					objFicha.getFicha(con,fdef);
				
				
				Vector vListado = new Vector();
				
				ArchivoImagenBean  t =new ArchivoImagenBean();
			//	Vector v=BDTablas.getListadoFicheros(con,correl);
				
				// while (v.next()) {
				//		Vector v = new Vector();
				//		v.add(0, v.getString(1));
				//		v.add(1, v.getString(2));
				//		vListado.add(v);
				//	  }
				
			//	t.setNombreArchivo(req.getAttribute ("vDocumento").getString(1));
				
				
				// req.setAttribute("objLst2",req.getAttribute ("vDocumento"));
				// strUrl="/jsp/listadoficheros.jsp";
				//strUrl="/servlet/CtrlFicha?opt=25&CAS="+fdef.getcas()+"&AYEAR="+fdef.getayear()+"&CORREL="+fdef.getcorrel();
		
			//	if(objFicha.isOK()){
					session.setAttribute("objFicha_t",fdef);
					session.setAttribute("listaArchivos","listaArchivosSubidos");
					 req.setAttribute("opc","registro");
					req.setAttribute("msg_error","El archivo se grabó correctamente");
					strUrl="/servlet/CtrlFicha?opt=25&CAS="+fdef.getcas()+"&AYEAR="+fdef.getayear()+"&CORREL="+fdef.getcorrel();
			//	}				
			//}else{
		//		req.setAttribute("msg_error","El tamaño del archivo es mayor a 1MB. ");
		//		strUrl="/servlet/CtrlFicha?opt=25&CAS="+fdef.getcas()+"&AYEAR="+fdef.getayear()+"&CORREL="+fdef.getcorrel();
			
			
			
			}else if (strOpt.equals("17")) { // REGISTRO DE LIBRO DE RECLAMACIONES
				logger.debug("Dentro ctrlFicha de la opcion 17");
				
				String caso = StringUtils.defaultString(req.getParameter("caso"));
				String tipoDoc = StringUtils.defaultString(req.getParameter("CTDI"));
				String numeroDoc = StringUtils.defaultString(req.getParameter("DFICDID"));
				String nombre = StringUtils.defaultString(req.getParameter("DNOM"));
				String paterno = StringUtils.defaultString(req.getParameter("DAPEPAT"));
				String materno = StringUtils.defaultString(req.getParameter("DAPEMAT"));
				String direccion = StringUtils.defaultString(req.getParameter("DFICDIR"));
				String email = StringUtils.defaultString(req.getParameter("DFICMAIL"));
				String telefono = StringUtils.defaultString(req.getParameter("DFICTEL"));
				//String unidad1 = StringUtils.defaultString(req.getParameter("CRED"));
				String unidad2 = StringUtils.defaultString(req.getParameter("CCAS"));
				String reclamo = StringUtils.defaultString(req.getParameter("DFICHEC"));
				
				
				Ficha objFicha = new Ficha();
				Ficha_t obj = new Ficha_t();
				obj.setcas(unidad2);
				obj.setdficdid(numeroDoc);
				obj.setdnom(nombre);
				obj.setdapepat(paterno);
				obj.setdapemat(materno);
				obj.setdficdir(direccion);
				obj.setdficmail(email);
				obj.setdfictel(telefono);
				obj.setcred(BDTablas.getRedXCas(con, unidad2));
				obj.setccas(unidad2);
				obj.setdfichec(reclamo);
				obj.setctin(Constantes.CODIGO_LIBRO_RECLAMACIONES);//tipo de ingreso
				obj.setcusucrea(Constantes.USUARIO_LIBRO_RECLAMACIONES);
				obj.setViaIngreso(Constantes.VIA_INGRESO_VIRTUAL);
				obj.setctdi(tipoDoc);
				
				//duplicado de registro dapepatrec
				obj.setctdirec(tipoDoc);
				obj.setdficdidrec(numeroDoc);
				obj.setdnomrec(nombre);
				obj.setdapepatrec(paterno);
				obj.setdapematrec(materno);
				obj.setReclaDir(direccion);
				obj.setReclaMail(email);
				obj.setReclaTel(telefono);
				
			  objFicha.FichaUpdate(con, obj, strPerfil, strCaso);
				if (objFicha.isOK()) {
					if(strCaso.equals(Constantes.OPCION_INSERTAR)){
						if(obj.getcorrel().compareTo("err")!=0 && obj.getayear().compareTo("err")!=0){
							req.setAttribute("objFicha_t", obj);							
							req.setAttribute("msg_error","El registro se grabó correctamente");
							logger.debug("Los datos que requiero: "+obj.getayear()+", el cas "+obj.getcas()+" y el correl= "+obj.getcorrel());
							//strUrl="/servlet/CtrlFicha?opt=38&CAS="+obj.getcas()+"&AYEAR="+obj.getayear()+"&CORREL="+obj.getcorrel();
							strUrl="/htm/librecla_result.jsp";
						}
						else{
							req.setAttribute("msg_error","Error throwable: por favor intente mas tarde");
							logger.debug("Error en CtrlFicha: Sin codigo");
							strUrl = "/error/error_general.jsp";
						}
					}else{
						req.setAttribute("msg_error","Error throwable CtrlFicha Opt2: por favor intente mas tarde");
						logger.debug("Error throwable CtrlFicha Opt2: no tengo opt");
						strUrl = "/error/error.jsp";
					}
				}
				else {
				  req.setAttribute("msg_error"," CtrlFicha strOpt2: Error en la grabacion ");
				  strUrl = "/error/error.jsp"; 
				}
			  
			  
			  
			
		}	else {
			  req.setAttribute("msg_error"," CtrlFicha: Error al llegar... No tengo opt ");
			  strUrl = "/error/error.jsp";
			}
			try {
				con.close();
				con=null;
			} 
			catch(Exception ec){;
			}
			//logger.debug("llege aqui CtrlFicha...");	
			WebUtil.goForward(getServletContext(), req, res, strUrl);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		  req.setAttribute("msg_error","Error en Servlet CtrlFicha <br> " + e.getMessage());
		  WebUtil.goForward(getServletContext(), req, res, "/error/error.jsp");
		  logger.debug("CtrlFicha: Error[" + getClass().getName() + "]." + e.getMessage());
		} 
		finally {
		  conexion.closeConnection(con);
		}
	}

	
	private String getFileName(FileItem fi) {
		//nombre del archivo en el cliente
		//depende del browser puede ser:
		//"archivo.txt" o C:\Mis Documentos\archivo.txt" o "/usr/archivo.txt"
		String clientFileName = fi.getName();
		String fileName = null;
		//para asegurarse de tener solo el nombre del archivo (sin rutas)
		//nos quedamos con el ultimo token tomando como separadores "/" y "\"
		StringTokenizer st = new StringTokenizer(clientFileName, "\\");
		while(st.hasMoreTokens()) {
			fileName = st.nextToken();
		}
		st = new StringTokenizer(fileName, "/");
		while(st.hasMoreTokens()) {
			fileName = st.nextToken();
		}
		return fileName;
	}
	
	private FileItem fileItem = null;
	
	
	public FileItem getFileItem() {
		return fileItem;
	}
	public void setFileItem(FileItem fileItem) {
		this.fileItem = fileItem;
	}
	
	private HttpServletRequest procesaFicheros(HttpServletRequest req, HttpServletResponse res) {
		try {
			//Ruta donde se guardara el fichero
			//File destino = new File("C:\\TEMP\\");
			//String strRealPath = req.getSession().getServletContext().getRealPath("")+"riid\\WebContent\\archivosCargados\\";
			String strRealPath = req.getContextPath()+"/archivosCargados";
			//String strRealPath = "D:\\archivosCargados\\";
			/*
			String relativeWebPath = "archivosCargados";
			String strRealPath = req.getSession().getServletContext().getRealPath(relativeWebPath);
			strRealPath = strRealPath.replaceAll("jakarta", "jakarta-tomcat-5.0.28");
			*///02032015
			logger.debug("strRealPath "+strRealPath);
			File destino = new File(strRealPath);
			//File destino = new File(req.getContextPath()+"\\WebContent\\archivosCargados\\");
			//logger.debug("RUTA: "+req.getContextPath()+"\\archivosCargados\\");s
			// Convertimos el HTTPRequest en un ContextRequest, este paso es necesario en la ultima version,
			// ya que los metodos de las versiones anteriores se han quedado desfasados.
			ServletRequestContext src = new ServletRequestContext(req);
			//Si el formulario es enviado con Multipart
			if(ServletFileUpload.isMultipartContent(src)){
				//Necesario para evitar errores de NullPointerException
				DiskFileItemFactory factory = new DiskFileItemFactory((1024*1024),destino);
				//Creamos un FileUpload
				ServletFileUpload upload = new ServletFileUpload(factory);
				
				
				//Procesamos el request para que nos devuelva una lista con los parametros y ficheros.
				List lista = upload.parseRequest(src);
				File file = null;
				//Recorremos la lista.
				Iterator it = lista.iterator();
				while(it.hasNext()){
					//Rescatamos el fileItem
					FileItem item = (FileItem)it.next();
					//Comprobamos si es un campo de formulario
					if(item.isFormField())
					{
						//Hacemos lo que queramos con el.
						logger.debug(item.getFieldName()+"<br>");
						
						if(item.getFieldName().equals("CAS")){
							req.setAttribute("CAS", item.getString());
						}else if(item.getFieldName().equals("AYEAR")){
							req.setAttribute("AYEAR", item.getString());
						}else if(item.getFieldName().equals("CORREL")){
							req.setAttribute("CORREL", item.getString());
						}else if(item.getFieldName().equals("opt")){
							req.setAttribute("opt", item.getString());
						}else if(item.getFieldName().equals("ext")){
							req.setAttribute("ext", item.getString());
						}else if(item.getFieldName().equals("CACCREAL")){
							req.setAttribute("CACCREAL", item.getString());
						}else if(item.getFieldName().equals("CQUIEN")){
							req.setAttribute("CQUIEN", item.getString());
						}else if(item.getFieldName().equals("FECHAQ")){
							req.setAttribute("CQUIEN", item.getString());
						}else if(item.getFieldName().equals("DCARGO")){
							req.setAttribute("CQUIEN", item.getString());
						}else if(item.getFieldName().equals("DFICOBS")){
							req.setAttribute("CQUIEN", item.getString());
						}
						
						logger.debug(req.getAttribute("opt")+"<br>");
						
						
					}else{
						//Si no, es un fichero y lo subimos al servidor.
						//Primero creamos un objeto file a partir del nombre del fichero.
						
						file = new File(item.getName());
						logger.debug("tamaño: "+Long.toString(item.getSize()/1024));
						req.setAttribute("TAMANO", Long.toString(item.getSize()/1024));
						/*
						String _extension = FilenameUtils.getExtension(item.getName());
						logger.debug("**********************extension del archivo: "+_extension);
						
						
						if((item.getSize()/1024)<=1024){
							if("pdf".equals(_extension)){
							   item.write(new File(destino,req.getAttribute("AYEAR").toString()+"_"+req.getAttribute("CAS").toString()+"_"+req.getAttribute("CORREL").toString()+".pdf"));
							}
							else if ("jpg".equals(_extension)) {  
								
								 item.write(new File(destino,req.getAttribute("AYEAR").toString()+"_"+req.getAttribute("CAS").toString()+"_"+req.getAttribute("CORREL").toString()+".jpg"));
							}
							
						}else{
							
						}*///02032015 se lleva a la parte 37 para grabar los nombres de los archivos- gascarza
						
						// Lo escribimos en el disco usando la ruta donde se guardara el fichero y cogiendo el nombre del file
						// Nota: Se podria hacer usando el objeto item en vez del file directamente 
						// Pero esto puede causar incompatibilidades segun que navegador, ya que 
						// algunos solo pasan el nombre del fichero subido, pero otros como Iexplorer, pasan la ruta absoluta, 
						// y esto crea un pequeño problema al escribir el fichero en el servidor.
						//item.write(new File(destino,file.getName()));
						//item.write(new File(destino,req.getAttribute("AYEAR").toString()+"_"+req.getAttribute("CAS").toString()+"_"+req.getAttribute("CORREL").toString()+".pdf"));
						String fileName;
					    String baseName;
					    String extension;
					  //  FileItemIterator iterator = upload.getItemIterator(req);
					//  while(iterator.hasNext()){ 
					    
					   // FileItemStream itemImagen = (FileItemStream) iterator.next();
					    InputStream inTemp = item.getInputStream();
					  
						fileName = FilenameUtils.getName(item.getName());
						baseName = FilenameUtils.getBaseName(item.getName());
						extension = FilenameUtils.getExtension(item.getName());
						
						int cantidadPaginas = 0;
						
						if("".equals(fileName)) continue;
						
						File tempFile = File.createTempFile(baseName,"."+extension);
		    			String absolutePathTempFile = tempFile.getAbsolutePath();
		    			String tempFileName = tempFile.getName();
		    			String pathTempFile = absolutePathTempFile.substring(0,absolutePathTempFile.lastIndexOf(File.separator))+File.separator;
		    			FileOutputStream os = new FileOutputStream(tempFile);
		    			IOUtils.copy(inTemp, os);
		    			
		    			//File imagen = new File(absolutePathTempFile);
		    			//ruta puede ser: "/home/cmop/Desktop/1.jpg"
		    			//FileInputStream   fis = new FileInputStream(imagen);
		    			//Lo convertimos en un Stream
		    			//stmt.setBinaryStream(1, fis, (int) imagen.length());
		    			
		    			
						
						//FileInputStream fis=(FileInputStream)item.getInputStream();
						InputStream inAct = new FileInputStream(tempFile);
						
						//byte [] dataFichero = IOUtils.toByteArray(inAct);
						//byte []  data = IOUtils.toByteArray(inAct);
						
					
						//ArchivoImagenBean  datafile = new ArchivoImagenBean();
								//Blob blob = null;
				//		try {
				//		    blob = new SerialBlob(IOUtils.toByteArray(inAct));//debugger says myinputstream has contents here.
						    //someObject.setSomeBlobProperty(blob);//debugger says blob is null here
						    
						   
							
				//		} 
				//		catch (SerialException e) {e.printStackTrace();}
				//		catch (SQLException e) {e.printStackTrace();}		
						//blob.setBytes(1, data);		
			//			ficheroBean.setArchivoImagen(data);
						//ficheroBean.setArchivoImagenBlob(blob);
						
			//			inAct.close();
	    				//out.close();
	    				//and added this line:
	    	//			res.flushBuffer();
						//blob.free();
						//StringUtils.leftPad(str, size, padChar)
						logger.debug("carg archivo");
						//HttpSession session = req.getSession(true);
						
						//session.setAttribute("dataFichero",dataFichero);
						//req.setAttribute("dataFichero", data);
						
					//  }
						//logger.debug("<br>destino = "+destino+" getName= "+file.getName());
						//logger.debug("<br><a href='http://191.0.70.7:8080/defensoria/archivosCargados/"+file.getName()+"'  target='_blank'>"+file.getName()+"</a> ");
						//logger.debug("<br>Archivo enviado");
						
						//WebUtil.goForward(getServletContext(), req, res, "/jsp/fdatosdef.jsp");
						
						setFileItem(item);
						
					}//fin else
				}//fin while
				
				
				//ERICK
				
				//String fileName;
			   // String baseName;
			   // String extension;
			    
			   // List<Map<String, Object>> listaArchivos = new ArrayList<Map<String, Object>>(); 
				
			   /* upload.setSizeMax(MAX_FILE_SIZE_BYTES); //tamaño en bytes
				FileItemIterator iterator = upload.getItemIterator(req);
				
				
				while(iterator.hasNext()){
					
					FileItemStream item2 = (FileItemStream) iterator.next();
					InputStream inTemp = item2.openStream();
				
					if(!item2.isFormField()){
						fileName = FilenameUtils.getName(item2.getName());
						baseName = FilenameUtils.getBaseName(item2.getName());
						extension = FilenameUtils.getExtension(item2.getName());
						
						int cantidadPaginas = 0;
						
						if("".equals(fileName)) continue;
						
						File tempFile = File.createTempFile(baseName,"."+extension);
		    			String absolutePathTempFile = tempFile.getAbsolutePath();
		    			String tempFileName = tempFile.getName();
		    			String pathTempFile = absolutePathTempFile.substring(0,absolutePathTempFile.lastIndexOf(File.separator))+File.separator;
		    			FileOutputStream os = new FileOutputStream(tempFile);
		    			IOUtils.copy(inTemp, os);
						
		    			//FileUtil fileUtil = new FileUtil(absolutePathTempFile);
		    			
		    			//if (fileUtil.getTipoArchivo().equals("PDF") || fileUtil.getTipoArchivo().equals("JPG")) {
		    			   //AQUI VALIDACIONES
		    			//}
		    			
		    			//if((item.getSize()/1024)<=1024){
		    			
		    				InputStream inAct = new FileInputStream(tempFile);
		    				byte [] data = IOUtils.toByteArray(inAct);
		    						    				
		    				//HashMap<String, Object> mapaArchivo = new HashMap<String, Object>();
		    				////mapaArchivo.put("data", data);
		    				//mapaArchivo.put("size", data.length);
		    				//mapaArchivo.put("cantidadPaginas", cantidadPaginas);
		    				
		    				req.setAttribute("dataFichero", data);
		    				
		    				
		    				inAct.close();
		    				//out.close();
		    				//and added this line:
		    				///res.flushBuffer();
		    				
		    				
		    				
		    			// }	
		    				
		    				
		    			
					  }
				      
				}//fin erick
				*/
				
			}
		}
		catch(Exception e) {
			logger.debug("Error de Aplicación " + e.getMessage());
		}
		
		return req;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	public ArchivoImagenBean getFicheroBean() {
		return ficheroBean;
	}
	public void setFicheroBean(ArchivoImagenBean ficheroBean) {
		this.ficheroBean = ficheroBean;
	}
	public Blob getBlob() {
		return blob;
	}
	public void setBlob(Blob blob) {
		this.blob = blob;
	}
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(Logger logger) {
		CtrlFicha.logger = logger;
	}
	public static int getMaxFileSizeBytes() {
		return MAX_FILE_SIZE_BYTES;
	}
	
}
