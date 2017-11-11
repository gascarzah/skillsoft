
package control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.log4j.Logger;

import bd.Ficha;
import bd.IOBlob;
import bd.conexion;
import beans.WebUtil;
import clases.Ficha_t;
import clases.UsuarioSistema;

public class uploadFichero extends HttpServlet implements Serializable {
	private static Logger logger = Logger.getLogger(uploadFichero.class);
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
				}
	void depura(String cadena){
		logger.debug("Depura: " + cadena);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
			HttpSession session=req.getSession();
			ServletContext ctx=session.getServletContext();
			logger.debug(ctx.getRealPath("/"));
			res.setContentType("text/html;charset=UTF-8");
			UsuarioSistema usuario=(UsuarioSistema)session.getAttribute("sClusuario");
			Vector params=new Vector();
			Vector val=null;
			Ficha_t obj=new Ficha_t();
			Ficha objFicha=new Ficha();
			Connection con=conexion.getConnection();;
			IOBlob blob=new IOBlob();
			InputStream is=null;
			boolean efic=false;
			if(usuario==null){
				logger.debug("Usuario es null");
			}else{
				logger.debug("usuario actual: "+usuario.getUsuario());
			}
			logger.debug("Procesamos uploadFichero para procesar el guardado.");	
			String strUrl="";
			try{
				File destino=new File("D:\\apoyo\\");
				ServletRequestContext src=new ServletRequestContext(req);
				if(ServletFileUpload.isMultipartContent(req)){
					logger.debug("Es multipart");
					DiskFileItemFactory factory=new DiskFileItemFactory();
					factory.setSizeThreshold(1024*1024);
					factory.setRepository(new File(ctx.getRealPath("/")));
					ServletFileUpload upload=new ServletFileUpload(factory);
					List lista=upload.parseRequest(req);
					FileItem fitem=null;
					Iterator it=lista.iterator();
					while(it.hasNext()){
						FileItem item=(FileItem)it.next();
						if(item.isFormField()){
							if(!(item.getFieldName().equals("Cars2") || item.getFieldName().equals("Cars3") || item.getFieldName().equals("Button2"))){
								//val=new Vector();
								//val.add(item.getFieldName());
								//val.add(item.getString());
								//params.add(val);
								
								if(item.getFieldName().equals("DFICACC")){
									obj.setdficacc(item.getString());	
									continue;
								}
								if(item.getFieldName().equals("CADE")){
									obj.setcade(item.getString());
									continue;
								}
								if(item.getFieldName().equals("CEFI")){
									obj.setcefi(item.getString());
									continue;
								}
								if(item.getFieldName().equals("CTIN")){
									obj.setctin(item.getString());
									continue;
								}
								if(item.getFieldName().equals("DFICOBS")){
									obj.setdficobs(item.getString());
									continue;
								}
								
								if(item.getFieldName().equals("CAS")){
									obj.setcas(item.getString());
									continue;
								}
								
								if(item.getFieldName().equals("AYEAR")){
									obj.setayear(item.getString());
									continue;
								}
								
								if(item.getFieldName().equals("CORREL")){
									obj.setcorrel(item.getString());
									continue;
								}
								
								if(item.getFieldName().equals("BFICTIPATE")){
									obj.setbfictipate(item.getString());
									continue;
								}
							}
						}else{
							String na=item.getName()!=null?item.getName():"";
							logger.debug("Ficher0: "+na);
							if(!na.equals("")){
								efic=true;
								fitem=item;
								is=fitem.getInputStream();
								obj.setfichero(na);
								
							}
						}
					}
					/*logger.debug("Vemos al vector que contiene:");
								for(int i=0;i<params.size();i++){
									val=new Vector();
									val=(Vector)params.get(i);
									logger.debug("nombre: "+val.get(0)+" , value: "+val.get(1));
								}
								
								logger.debug("Fin de iteracion");
								obj.setcas((String)((Vector)params.get(0)).get(1));
								obj.setayear((String)((Vector)params.get(1)).get(1));
								obj.setcorrel((String)((Vector)params.get(2)).get(1));
								obj.setdficacc((String)((Vector)params.get(3)).get(1));
								obj.setcade((String)((Vector)params.get(4)).get(1));
								obj.setcefi((String)((Vector)params.get(5)).get(1));
								obj.setctin((String)((Vector)params.get(6)).get(1));
								obj.setdficobs((String)((Vector)params.get(7)).get(1));*/
								//obj.setbfictipate((String)((Vector)params.get(8)).get(1));
								obj.setcusumodi(usuario.getUsuario());
			
								logger.debug("lo que he puesto en los sets:");
								logger.debug("cas: "+obj.getcas());
								logger.debug("ayear: "+obj.getayear());
								logger.debug("correl: "+obj.getcorrel());
								logger.debug("dficacc: "+obj.getdficacc());
								logger.debug("cade: "+obj.getcade());
								logger.debug("cefi: "+obj.getcefi());
								logger.debug("ctin: "+obj.getctin());
								logger.debug("dficobs: "+obj.getdficobs());
								logger.debug("bfictipate: "+obj.getbfictipate());
								logger.debug("cusumodi: "+obj.getcusumodi());
		
								objFicha.grabaFichero(con,obj);
								conexion.closeConnection(con);
								con=conexion.getConnection();
								if(is!=null)blob.insertBLOB(obj,is);
								
								if(objFicha.isOK()){
									String men="Datos guardados correctamente!!";
									req.setAttribute("msg_error",men);
									strUrl="/servlet/CtrlFicha?opt=25&CAS="+obj.getcas()+"&AYEAR="+obj.getayear()+"&CORREL="+obj.getcorrel();
								//strUrl="/jsp/fdatosad.jsp";
								}else{
									req.setAttribute("msg_error","Error en error en grabar en tdatosdef");
									strUrl = "/error/error_general.jsp";
						}					
					
				}
			}catch(SQLException e){
				logger.debug("Error en SQLException: "+e.getMessage());
			}catch(FileUploadException e){
				logger.debug("Error en FileUploadException: "+e.getMessage());
			}finally{
				conexion.closeConnection(con);
			}
			
			//req.setAttribute("msg_error", "La sesion ha finalizado. Ingrese de nuevo.");
			logger.debug("Dentro de uploadRequest....");
			WebUtil.goForward(getServletContext(), req, res, strUrl);
		}
//			public void guardar(){
//		try {
//			//Ruta donde se guardara el fichero
//			//File destino = new File("C:\\TEMP\\");
//			File destino = new File("D:\\apoyo\\");
//			// Convertimos el HTTPRequest en un ContextRequest, este paso es necesario en la ultima version,
//			// ya que los metodos de las versiones anteriores se han quedado desfasados.
//			ServletRequestContext src = new ServletRequestContext(req);
//			//Si el formulario es enviado con Multipart
//			if(ServletFileUpload.isMultipartContent(src)){
//				//Necesario para evitar errores de NullPointerException
//				DiskFileItemFactory factory = new DiskFileItemFactory((1024*1024),destino);
//				//Creamos un FileUpload
//				ServletFileUpload upload = new ServletFileUpload(factory);
//				//Procesamos el request para que nos devuelva una lista con los parametros y ficheros.
//				List lista = upload.parseRequest(src);
//				File file = null;
//				//Recorremos la lista.
//				Iterator it = lista.iterator();
//				while(it.hasNext()){
//					//Rescatamos el fileItem
//					FileItem item = (FileItem)it.next();
//					//Comprobamos si es un campo de formulario
//					if(item.isFormField())
//						//Hacemos lo que queramos con el.
//						logger.debug(item.getFieldName()+"<br>");
//					else{
//						//Si no, es un fichero y lo subimos al servidor.
//						//Primero creamos un objeto file a partir del nombre del fichero.
//						file = new File(item.getName());
//						// Lo escribimos en el disco usando la ruta donde se guardara el fichero y cogiendo el nombre del file
//						// Nota: Se podria hacer usando el objeto item en vez del file directamente 
//						// Pero esto puede causar incompatibilidades segun que navegador, ya que 
//						// algunos solo pasan el nombre del fichero subido, pero otros como Iexplorer, pasan la ruta absoluta, 
//						// y esto crea un pequeño problema al escribir el fichero en el servidor.
//						item.write(new File(destino,file.getName()));
//						logger.debug("<br>destino = "+destino+" getName= "+file.getName());
//						logger.debug("<br><a href='http://191.0.70.7:8080/defensoria/archivosCargados/"+file.getName()+"'  target='_blank'>"+file.getName()+"</a> ");
//						logger.debug("<br>Archivo enviado");
//					}
//				}
//			}
//		}
//		catch(Exception e) {
//			depura("Error de Aplicación " + e.getMessage());
//			return false;
//		}
//		return true;
//}

	

}
