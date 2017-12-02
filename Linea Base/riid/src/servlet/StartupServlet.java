package servlet;

import java.io.File;
import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import bd.BDTablas;
import bd.conexion;

public class StartupServlet extends HttpServlet {
    
    
    public void init(ServletConfig config) throws ServletException{
    	Connection con = conexion.getConnection();
        // Obtiene el parametro de inicio
        //String log4jFile = config.getInitParameter("log4jPropertiesFile");
        
        // Obtiene la ruta real del archivo (ruta absoluta)
        ServletContext context = config.getServletContext();

        context.setAttribute ("vDepartamento", BDTablas.getDepartamento(con));//checked
        context.setAttribute ("vProvincia", BDTablas.getProvincia(con));//checked
        context.setAttribute ("vDistrito", BDTablas.getDistrito(con));//checked
        context.setAttribute ("vDocumento", BDTablas.getIdentidad(con));//checked
        context.setAttribute ("vPrestacion", BDTablas.getPrestacion(con));//checked
        context.setAttribute ("vSeguro", BDTablas.getSeguro(con));//checked
        context.setAttribute ("vSolicitud", BDTablas.getSolicitud(con));//checked
        context.setAttribute ("vTingreso", BDTablas.getTingreso(con));
        context.setAttribute ("vRed", BDTablas.getRed(con));
		context.setAttribute ("vCas", BDTablas.getCas(con));
		
		 // Obtiene el parametro de inicio
        String log4jFile = config.getInitParameter("log4jPropertiesFile");
        
        
        // Obtiene la ruta real del archivo (ruta absoluta)
        log4jFile = context.getRealPath(log4jFile);

        log4jFile = log4jFile.replaceAll("jakarta", "jakarta-tomcat-5.0.28");
        System.out.print("log ridd : "+log4jFile);
        // Carga el log4j.properties si existe y sino carga BasicConfigurator
        if (new File(log4jFile).isFile()) {
            PropertyConfigurator.configure(log4jFile);
        } 
        else {
            BasicConfigurator.configure();
        }

        super.init(config);
    }
}
