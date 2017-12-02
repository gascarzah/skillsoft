
package clases;

import org.apache.log4j.Logger;

public class BeUsuario extends UsuarioSistema implements java.io.Serializable {
	
	private static Logger logger = Logger.getLogger(BeUsuario.class);
   private String strClave;
   private String strUsuario;
   private String strMail;
   private String strPerfil;
   private String strDescripcion;
   private String strBworkflow;
   private String strBestadoreg;

   public String getClave() { return strClave;}
   public String getUsuario() {return strUsuario;}
   public String getMail() {return strMail;}
   public String getPerfil() {return strPerfil;}
   public String getDescripcion() {return strDescripcion;}
   public String getBworkflow() {return strBworkflow;}
   public String getBestadoreg() {return strBestadoreg;}

   public void setClave(String pClave) {strClave=pClave;}
   public void setUsuario(String pUsuario) {strUsuario=pUsuario;}
   public void setMail(String pMail) {strMail=pMail;}
   public void setPerfil(String pPerfil) {strPerfil=pPerfil;}
   public void setDescripcion(String pDescripcion) {strDescripcion=pDescripcion;}
   public void setBworkflow(String pBworkflow) {strBworkflow=pBworkflow;}
   public void setBestadoreg(String pBestadoreg) { strBestadoreg=pBestadoreg;}
}
