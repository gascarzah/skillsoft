
package clases;

import java.util.Hashtable;
import java.util.Vector;

import marco.Listado;
import util.Paginacion;

public class Tablas_t extends Listado implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Tablas_t instance = new Tablas_t();
	
	public static Tablas_t getInstance() {
		return instance;
	}


	protected String strPag  = "";
	protected String strError = "";   // Error al general el listado
	protected int iTotalGen =0;
	protected int iRegxPag = 0;   // numero de registros en la pagina

	protected String strTabla = "";  
	protected String strForm = "";  
	protected String strObj1 = "";  
	protected String strSede = "";  

	protected String strCond1 = "";
	protected String strCond2 = "";
	protected String strCond3 = "";
	protected String strCond4 = "";
	protected String strCond5 = "";
	protected String strOrden = "";

	public String getPag(){ return strPag;}
	public String getError(){return strError;}
	public int getTotalGen(){return iTotalGen;}
	public int getiRegxPag(){
	  if (iRegxPag==0) iRegxPag = marco.Pagina.REG_X_PAG;
	  return iRegxPag;
	}
	public String getCond1(){return strCond1;}
	public String getCond2(){return strCond2;}
	public String getCond3(){return strCond3;}
	public String getCond4(){return strCond4;}
	public String getCond5(){return strCond5;}
	public String getOrden(){return strOrden;}

	public String getTabla(){return strTabla;}
	public String getForm(){return strForm;}
	public String getObj1(){return strObj1;}
	public String getSede(){return strSede;}

	public Hashtable getHshLista() {return super.hshLista; }
	public Paginacion getPaginacion(){return super.clPaginacion;}
	public Vector getVector(){return super.vListado;}

	public void setHshLista(Hashtable phshLista){super.hshLista=phshLista;}  
	public void setPaginacion(Paginacion pPaginacion){super.clPaginacion=pPaginacion;}
	public void setVector(Vector pVector){super.vListado=pVector;}
	public void setPag(String pPag){strPag=pPag;}
	public void setTotalGen(int piTotalGen){iTotalGen=piTotalGen;}
	public void setiRegxPag(int piRegxPag){iRegxPag=piRegxPag;}
	public void setError(String pstrError){strError=pstrError;}
	
	public void setCond1(String pstrCond1){strCond1=pstrCond1;}
	public void setCond2(String pstrCond2){strCond2=pstrCond2;}
	public void setCond3(String pstrCond3){strCond3=pstrCond3;}
	public void setCond4(String pstrCond4){strCond4=pstrCond4;}
	public void setCond5(String pstrCond5){strCond5=pstrCond5;}
	public void setOrden(String pstrOrden){strOrden=pstrOrden;}

	public void setTabla(String pstrTabla){strTabla=pstrTabla;}
	public void setForm(String pstrForm){strForm=pstrForm;}
	public void setObj1(String pstrObj1){strObj1=pstrObj1;}
	public void setSede(String pstrSede){strSede=pstrSede;}


// acciones

	protected String strcodaccion="";
	protected String strdesaccion="";
	protected String strbestadoreg="";

	public Tablas_t(){}
  
	public String getstrcodaccion(){return strcodaccion;}
	public String getstrdesaccion(){return strdesaccion;}
	public String getstrbestadoreg(){return strbestadoreg;}

	public void setstrcodaccion(String pstrcodaccion){strcodaccion=pstrcodaccion;}
	public void setstrdesaccion(String pstrdesaccion){strdesaccion=pstrdesaccion;}
	public void setstrbestadoreg(String pstrbestadoreg){strbestadoreg=pstrbestadoreg;}

// usuarios

   private String strUsuUsuario;
   private String strUsuClave;
   private String strUsuDescripcion;
   private String strUsuMail;
   private String strUsuPerfil;
   private String strUsuCcas;
   private String strUsuCred;
   private String strUsuBestadoreg;
   private String strBworkflow;
   private String strUsuMat;
   private String strUsuNom;

   public String getUsuUsuario() {return strUsuUsuario;}
   public String getUsuClave() { return strUsuClave;}
   public String getUsuDescripcion() {return strUsuDescripcion;}
   public String getUsuMail() {return strUsuMail;}
   public String getUsuPerfil() {return strUsuPerfil;}
   public String getUsuCcas() {return strUsuCcas;}
   public String getUsuCred() {return strUsuCred;}
   public String getBworkflow() {return strBworkflow;}
   public String getUsuBestadoreg() {return strUsuBestadoreg;}
   public String getUsuMat() {return strUsuMat;}
   public String getUsuNom() {return strUsuNom;}

   public void setUsuUsuario(String pUsuario) {strUsuUsuario=pUsuario;}
   public void setUsuClave(String pClave) {strUsuClave=pClave;}
   public void setUsuDescripcion(String pDescripcion) {strUsuDescripcion=pDescripcion;}
   public void setUsuMail(String pMail) {strUsuMail=pMail;}
   public void setUsuPerfil(String pPerfil) {strUsuPerfil=pPerfil;}
   public void setUsuCcas(String pCcas) {strUsuCcas=pCcas;}
   public void setUsuCred(String pCred) {strUsuCred=pCred;}
   public void setBworkflow(String pBworkflow) {strBworkflow=pBworkflow;}
   public void setUsuBestadoreg(String pBestadoreg) { strUsuBestadoreg=pBestadoreg;}
   public void setUsuMat(String pMat) {strUsuMat=pMat;}
   public void setUsuNom(String pNom) {strUsuNom=pNom;}
   
// grupos

   private String strCgrupo;
   private String strDgrupo;

   public String getCgrupo() {return strCgrupo;}
   public String getDgrupo() {return strDgrupo;}

   public void setCgrupo(String pCgrupo) {strCgrupo=pCgrupo;}
   public void setDgrupo(String pDgrupo) {strDgrupo=pDgrupo;}

// materias

   private String strCmateria;
   private String strDmateria;
   private String strBestadoreg;

   public String getCmateria() {return strCmateria;}
   public String getDmateria() {return strDmateria;}
   public String getBestadoreg() {return strBestadoreg;}

   public void setCmateria(String pCmateria) {strCmateria=pCmateria;}
   public void setDmateria(String pDmateria) {strDmateria=pDmateria;}
   public void setBestadoreg(String pBestadoreg) {strBestadoreg=pBestadoreg;}

// asuntos

   private String strAsunCodigo;
   private String strAsunDescripcion;
   private String strAsunPlazo;
   private String strAsunClaveusu;
   private String strAsunBestadoreg;

   public String getAsunCodigo() {return strAsunCodigo;}
   public String getAsunDescripcion() {return strAsunDescripcion;}
   public String getAsunPlazo() {return strAsunPlazo;}
   public String getAsunClaveusu() {return strAsunClaveusu;}
   public String getAsunBestadoreg() {return strAsunBestadoreg;}

   public void setAsunCodigo(String pcodigo) {strAsunCodigo=pcodigo;}
   public void setAsunDescripcion(String pdescripcion) {strAsunDescripcion=pdescripcion;}
   public void setAsunPlazo(String pplazo) {strAsunPlazo=pplazo;}
   public void setAsunClaveusu(String pclaveusu) {strAsunClaveusu=pclaveusu;}
   public void setAsunBestadoreg(String pbestadoreg) {strAsunBestadoreg=pbestadoreg;}
   
// estados

   private String strEstEstado;
   private String strEstDescripcion;
   private String strEstBestadoreg;

   public String getEstEstado() {return strEstEstado;}
   public String getEstDescripcion() {return strEstDescripcion;}
   public String getEstBestadoreg() {return strEstBestadoreg;}

   public void setEstEstado(String pEstado) {strEstEstado=pEstado;}
   public void setEstDescripcion(String pDescripcion) {strEstDescripcion=pDescripcion;}
   public void setEstBestadoreg(String pBestadoreg) {strEstBestadoreg=pBestadoreg;}

// motivos

   private String strMotCod;
   private String strMotDesc;
   private String strMotBestado;

   public String getMotCod() {return strMotCod;}
   public String getMotDesc() {return strMotDesc;}
   public String getMotBestado() {return strMotBestado;}

   public void setMotCod(String pstrMotCod) {strMotCod=pstrMotCod;}
   public void setMotDesc(String pstrMotDesc) {strMotDesc=pstrMotDesc;}
   public void setMotBestado(String pstrMotBestado) {strMotBestado=pstrMotBestado;}

// tipos de prestacion

   private String strTipCod;
   private String strTipDesc;
   private String strTipBestado;

   public String getTipCod() {return strTipCod;}
   public String getTipDesc() {return strTipDesc;}
   public String getTipBestado() {return strTipBestado;}

   public void setTipCod(String pstrTipCod) {strTipCod=pstrTipCod;}
   public void setTipDesc(String pstrTipDesc) {strTipDesc=pstrTipDesc;}
   public void setTipBestado(String pstrTipBestado) {strTipBestado=pstrTipBestado;}

// tipos de ingreso

   private String strTipIngCod;
   private String strTipIngDesc;
   private String strTipIngBestado;

   public String getTipIngCod() {return strTipIngCod;}
   public String getTipIngDesc() {return strTipIngDesc;}
   public String getTipIngBestado() {return strTipIngBestado;}

   public void setTipIngCod(String pstrTipIngCod) {strTipIngCod=pstrTipIngCod;}
   public void setTipIngDesc(String pstrTipIngDesc) {strTipIngDesc=pstrTipIngDesc;}
   public void setTipIngBestado(String pstrTipIngBestado) {strTipIngBestado=pstrTipIngBestado;}
   
   //unidad organica nivel 01
   private String strCras01;
   private String strDras01;
   private String strDrasMed01;
   private String strBrasEstareg01;
   private String strCunoCod01;


//unidad organica nivel 02
   private String strCcasOri02;
   private String strCcas02;
   private String strDcas02;
   private String strDcasCor02;
   private String strDcasDir02;
   private String strCras02;
   private String strCunoCod02;
   private String strBcasEstareg02;

public String getStrCras01() {
	return strCras01;
}
public void setStrCras01(String strCras01) {
	this.strCras01 = strCras01;
}
public String getStrDras01() {
	return strDras01;
}
public void setStrDras01(String strDras01) {
	this.strDras01 = strDras01;
}
public String getStrDrasMed01() {
	return strDrasMed01;
}
public void setStrDrasMed01(String strDrasMed01) {
	this.strDrasMed01 = strDrasMed01;
}
public String getStrBrasEstareg01() {
	return strBrasEstareg01;
}
public void setStrBrasEstareg01(String strBrasEstareg01) {
	this.strBrasEstareg01 = strBrasEstareg01;
}
public String getStrCunoCod01() {
	return strCunoCod01;
}
public void setStrCunoCod01(String strCunoCod01) {
	this.strCunoCod01 = strCunoCod01;
}
public String getStrCcasOri02() {
	return strCcasOri02;
}
public void setStrCcasOri02(String strCcasOri02) {
	this.strCcasOri02 = strCcasOri02;
}
public String getStrCcas02() {
	return strCcas02;
}
public void setStrCcas02(String strCcas02) {
	this.strCcas02 = strCcas02;
}
public String getStrDcas02() {
	return strDcas02;
}
public void setStrDcas02(String strDcas02) {
	this.strDcas02 = strDcas02;
}
public String getStrDcasCor02() {
	return strDcasCor02;
}
public void setStrDcasCor02(String strDcasCor02) {
	this.strDcasCor02 = strDcasCor02;
}
public String getStrDcasDir02() {
	return strDcasDir02;
}
public void setStrDcasDir02(String strDcasDir02) {
	this.strDcasDir02 = strDcasDir02;
}
public String getStrCras02() {
	return strCras02;
}
public void setStrCras02(String strCras02) {
	this.strCras02 = strCras02;
}
public String getStrCunoCod02() {
	return strCunoCod02;
}
public void setStrCunoCod02(String strCunoCod02) {
	this.strCunoCod02 = strCunoCod02;
}
public String getStrBcasEstareg02() {
	return strBcasEstareg02;
}
public void setStrBcasEstareg02(String strBcasEstareg02) {
	this.strBcasEstareg02 = strBcasEstareg02;
}

//Motivos de No Admisibilidad -- NPONCE -- 26/01/2015
private String strCtma01;
private String strDtma01;
private String strBestadoreg01;

public String getStrCtma01() {
	return strCtma01;
}
public void setStrCtma01(String strCtma01) {
	this.strCtma01 = strCtma01;
}

public String getStrDtma01() {
	return strDtma01;
}
public void setStrDtma01(String strDtma01) {
	this.strDtma01 = strDtma01;
}

public String getStrBestadoreg01() {
	return strBestadoreg01;
}
public void setStrBestadoreg01(String strBestadoreg01) {
	this.strBestadoreg01 = strBestadoreg01;
}

//Modalidad de Conclusiones -- NPONCE 26/01/2015
private String strCtmc01;
private String strDtmc01;
private String strBestadoreg02;

public String getStrCtmc01() {
	return strCtmc01;
}
public void setStrCtmc01(String strCtmc01) {
	this.strCtmc01 = strCtmc01;
}

public String getStrDtmc01() {
	return strDtmc01;
}
public void setStrDtmc01(String strDtmc01) {
	this.strDtmc01 = strDtmc01;
}

public String getStrBestadoreg02() {
	return strBestadoreg02;
}
public void setStrBestadoreg02(String strBestadoreg02) {
	this.strBestadoreg02 = strBestadoreg02;
}

//Accion de Persuasion (acpersuasion.jsp) -- NPONCE - combo del popup de la pestaña 4 de conclusiones.jsp
private String strCper01;
private String strDper01;
private String strBestadoreg03;

public String getStrCper01() {
	return strCper01;
}
public void setStrCper01(String strCper01) {
	this.strCper01 = strCper01;
}

public String getStrDper01() {
	return strDper01;
}
public void setStrDper01(String strDper01) {
	this.strDper01 = strDper01;
}

public String getStrBestadoreg03() {
	return strBestadoreg03;
}
public void setStrBestadoreg03(String strBestadoreg03) {
	this.strBestadoreg03 = strBestadoreg03;
}

// cargos
   private String strCcargo;
   private String strCessalud;
   private String strCcarpadre;
   private String strCusu;
   private String strDcargo;

   public String getCcargo() {return strCcargo;}
   public String getCessalud() {return strCessalud;}
   public String getCcarpadre() {return strCcarpadre;}
   public String getCusu() {return strCusu;}
   public String getDcargo() {return strDcargo;}

   public void setCcargo(String pstrCcargo) {strCcargo=pstrCcargo;}
   public void setCessalud(String pstrCessalud) {strCessalud=pstrCessalud;}
   public void setCcarpadre(String pstrCcarpadre) {strCcarpadre=pstrCcarpadre;}
   public void setCusu(String pstrCusu) {strCusu=pstrCusu;}
   public void setDcargo(String pstrDcargo) {strDcargo=pstrDcargo;}

// perfiles
   private String strCperfil;
   private String strDperfil;

   public String getCperfil() {return strCperfil;}
   public String getDperfil() {return strDperfil;}

   public void setCperfil(String pstrCperfil) {strCperfil=pstrCperfil;}
   public void setDperfil(String pstrDperfil) {strDperfil=pstrDperfil;}

   //tipo solicitud
   private String codSolicitud;
	private String descSolicitud;
	
	//tipo tematica
	private String codTematica;
	private String descTematica;

	public String getCodSolicitud() {
		return codSolicitud;
	}
	public String getDescSolicitud() {
		return descSolicitud;
	}
	public void setDescSolicitud(String descSolicitud) {
		this.descSolicitud = descSolicitud;
	}
	public String getDescTematica() {
		return descTematica;
	}
	public void setDescTematica(String descTematica) {
		this.descTematica = descTematica;
	}
	public void setCodSolicitud(String codSolicitud) {
		this.codSolicitud = codSolicitud;
	}
	public String getCodTematica() {
		return codTematica;
	}
	public void setCodTematica(String codTematica) {
		this.codTematica = codTematica;
	}
	
	//tipo conclusion
	private String codConclusion;
	public String getCodConclusion() {
		return codConclusion;
	}
	public void setCodConclusion(String codConclusion) {
		this.codConclusion = codConclusion;
	}


	private String descConclusion;

	public String getDescConclusion() {
		return descConclusion;
	}
	public void setDescConclusion(String descConclusion) {
		this.descConclusion = descConclusion;
	}
	
	//area y servicio
	private String codArea;
	private String descArea;
	private String codServicio;
	private String descServicio;
	
	public String getCodArea() {
		return codArea;
	}
	public void setCodArea(String codArea) {
		this.codArea = codArea;
	}
	public String getDescArea() {
		return descArea;
	}
	public void setDescArea(String descArea) {
		this.descArea = descArea;
	}
	public String getCodServicio() {
		return codServicio;
	}
	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}
	public String getDescServicio() {
		return descServicio;
	}
	public void setDescServicio(String descServicio) {
		this.descServicio = descServicio;
	}


	
	
	
   
}
