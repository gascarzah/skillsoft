
package clases;

public class UsuarioSistema implements java.io.Serializable {
   private String strUsuario;
   private String strClave;
   private String strPerfil;
   private String strDescripcion;
   private String strCcas;
   private String strBworkflow;
   private String strdPerfil;
   private String strdCas;
   private String cras;



public String getCras() {
	return cras;
}
public void setCras(String cras) {
	this.cras = cras;
}
public String getUsuario() { return strUsuario;}
   public String getClave() { return strClave;}
   public String getPerfil() {return strPerfil;}
   public String getDescripcion() {return strDescripcion;}
   public String getCcas() {return strCcas;}
   public String getBworkflow() {return strBworkflow;}
   public String getdPerfil() {return strdPerfil;}
   public String getdCas() {return strdCas;}

   public void setUsuario(String pUsuario) {strUsuario=pUsuario;}
   public void setClave(String pClave) {strClave=pClave;}
   public void setPerfil(String pPerfil) {strPerfil=pPerfil;}
   public void setDescripcion(String pDescripcion) {strDescripcion=pDescripcion;}
   public void setCcas(String pCcas) {strCcas=pCcas;}
   public void setBworkflow(String pBworkflow) {strBworkflow=pBworkflow;}
   public void setdPerfil(String pdPerfil) {strdPerfil=pdPerfil;}
   public void setdCas(String pdCas) { strdCas=pdCas;}
}
