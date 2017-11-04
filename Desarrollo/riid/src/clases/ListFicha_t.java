
package clases;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import marco.Listado;
import util.Paginacion;

public class ListFicha_t extends Listado implements java.io.Serializable {

	protected String strPag  = "";
	protected String codigoda="";
	protected String tramite="";
	protected String essalud="";
	protected String plazo="";
	protected String dda="";
	protected String proveido="";
	protected String fecproveido="";
	protected String consejeros="";
	protected String documento="";
	protected String dni="";
	protected String titular="";
	protected String edad="";
	protected String sexo="";
	protected String departamento="";
	protected String provincia="";
	protected String distrito="";
	protected String datos="";
	protected String telefono="";
	protected String email="";
	protected String prestacion="";
	protected String seguro="";
	protected String reclamante="";
	protected String dnireclamante="";
	protected String solicitud="";
	protected String codigo="";
	protected String desasunto="";
	protected String quejado="";
	protected String especialidad="";
	protected String cred="";
	protected String ccas="";
	protected String acciones="";
	protected String defensoriales="";
	protected String estado="";
	protected String fecharesolucion="";
	protected String tingreso="";
	protected String observaciones="";
	protected String atencion="";
	protected String inputuser="";
	protected String desidentidad ="";
	protected String desprestacion ="";
	protected String dessolicitud ="";
	protected String dcodigo="";
	protected String dred ="";
	protected String cenasides ="";
	protected String desaccion ="";
	protected String destado="";
	protected String dtingreso ="";
	protected String ubitdpt ="";
	protected String ubitprv ="";
	protected String ubitdtr="";
	protected String dseguro="";
	protected String dconsejeros="";
	protected String edituser="";
	protected String editdate="";
	protected String bestadoreg="";
	protected String conclusion="";
	protected String dda2="";
	protected String cond01="";
	protected String cond02="";
	protected String cond03="";
	protected String cond04="";
	protected String cond05="";
	protected String cond06="";
	protected String cond07="";
	protected String casUser="";
	protected String hclinica="";
	protected String codigo1="";
	protected String codigo2="";
	protected String enlarehoscod="";
	protected String enlservhoscod="";
	protected String dcodigo1="";
	protected String dcodigo2="";
	protected String denlarehoscod="";
	protected String denlservhoscod="";
	protected String dcas="";
	protected String diagcod="";
	protected String desdiagcod="";


	protected String strError = "";   // Error al general el listado
	protected int iTotalGen =0;
	protected int iRegxPag = 0;   // numero de registros en la pagina

	public ListFicha_t() {}
	
	public String getPag(){ return strPag;}
	public String getcodigoda(){return codigoda;}
	public String gettramite(){return tramite;}
	public String getessalud(){return essalud;}
	public String getplazo(){return plazo;}
	public String getdda(){return dda;}
	public String getproveido(){return proveido;}
	public String getfecproveido(){return fecproveido;}
	public String getconsejeros(){return consejeros;}
	public String getdocumento(){return documento;}
	public String getdni(){return dni;}
	public String gettitular(){return titular;}
	public String getedad(){return edad;}
	public String getsexo(){return sexo;}
	public String getdepartamento(){return departamento;}
	public String getprovincia(){return provincia;}
	public String getdistrito(){return distrito;}
	public String getdatos(){return datos;}
	public String gettelefono(){return telefono;}
	public String getemail(){return email;}
	public String getprestacion(){return prestacion;}
	public String getseguro(){return seguro;}
	public String getreclamante(){return reclamante;}
	public String getdnireclamante(){return dnireclamante;}
	public String getsolicitud(){return solicitud;}
	public String getcodigo(){return codigo;}
	public String getdesasunto(){return desasunto;}
	public String getquejado(){return quejado;}
	public String getespecialidad(){return especialidad;}
	public String getcred(){return cred;}
	public String getccas(){return ccas;}
	public String getacciones(){return acciones;}
	public String getdefensoriales(){return defensoriales;}
	public String getestado(){return estado;}
	public String getfecharesolucion(){return fecharesolucion;}
	public String gettingreso(){return tingreso;}
	public String getobservaciones(){return observaciones;}
	public String getatencion(){return atencion;}
	public String getinputuser(){return inputuser;}
	public String getdesidentidad (){return desidentidad ;}
	public String getdesprestacion (){return desprestacion ;}
	public String getdessolicitud (){return dessolicitud ;}
	public String getdcodigo(){return dcodigo;}
	public String getdred (){return dred ;}
	public String getcenasides (){return cenasides ;}
	public String getdesaccion (){return desaccion ;}
	public String getdestado(){return destado;}
	public String getdtingreso (){return dtingreso ;}
	public String getubitdpt (){return ubitdpt ;}
	public String getubitprv (){return ubitprv ;}
	public String getubitdtr(){return ubitdtr;}
	public String getdseguro(){return dseguro;}
	public String getdconsejeros(){return dconsejeros;}
	public String getedituser(){return edituser;}
	public String geteditdate(){return editdate;}
	public String getbestadoreg(){return bestadoreg;}
	public String getconclusion(){return conclusion;}
	public String getdda2(){return dda2;}
	public String getcond01(){return cond01;}
	public String getcond02(){return cond02;}
	public String getcond03(){return cond03;}
	public String getcond04(){return cond04;}
	public String getcond05(){return cond05;}
	public String getcond06(){return cond06;}
	public String getcond07(){return cond07;}
	public String getcasUser(){return casUser;}
	public String gethclinica(){return hclinica;}
	public String getcodigo1(){return codigo1;}
	public String getcodigo2(){return codigo2;}
	public String getenlarehoscod(){return enlarehoscod;}
	public String getenlservhoscod(){return enlservhoscod;}
	public String getdcodigo1(){return dcodigo1;}
	public String getdcodigo2(){return dcodigo2;}
	public String getdenlarehoscod(){return denlarehoscod;}
	public String getdenlservhoscod(){return denlservhoscod;}
	public String getdcas(){return dcas;}
	public String getdiagcod(){return diagcod;}
	public String getdesdiagcod(){return desdiagcod;}

	public String getError(){return strError;}
	public int getTotalGen(){return iTotalGen;}
	public int getiRegxPag(){
	  if (iRegxPag==0) iRegxPag = marco.Pagina.REG_X_PAG;
	  return iRegxPag;
	}

	public Hashtable getHshLista() {return super.hshLista; }
	public Paginacion getPaginacion(){return super.clPaginacion;}
	public Vector getVector(){return super.vListado;}

	public void setHshLista(Hashtable phshLista){super.hshLista=phshLista;}  
	public void setPaginacion(Paginacion pPaginacion){super.clPaginacion=pPaginacion;}
	public void setVector(Vector pVector){super.vListado=pVector;}
	public void setPag(String pPag){strPag=pPag;}
	public void setcodigoda(String pcodigoda){codigoda=pcodigoda;}
	public void settramite(String ptramite){tramite=ptramite;}
	public void setessalud(String pessalud){essalud=pessalud;}
	public void setplazo(String pplazo){plazo=pplazo;}
	public void setdda(String pdda){dda=pdda;}
	public void setproveido(String pproveido){proveido=pproveido;}
	public void setfecproveido(String pfecproveido){fecproveido=pfecproveido;}
	public void setconsejeros(String pconsejeros){consejeros=pconsejeros;}
	public void setdocumento(String pdocumento){documento=pdocumento;}
	public void setdni(String pdni){dni=pdni;}
	public void settitular(String ptitular){titular=ptitular;}
	public void setedad(String pedad){edad=pedad;}
	public void setsexo(String psexo){sexo=psexo;}
	public void setdepartamento(String pdepartamento){departamento=pdepartamento;}
	public void setprovincia(String pprovincia){provincia=pprovincia;}
	public void setdistrito(String pdistrito){distrito=pdistrito;}
	public void setdatos(String pdatos){datos=pdatos;}
	public void settelefono(String ptelefono){telefono=ptelefono;}
	public void setemail(String pemail){email=pemail;}
	public void setprestacion(String pprestacion){prestacion=pprestacion;}
	public void setseguro(String pseguro){seguro=pseguro;}
	public void setreclamante(String preclamante){reclamante=preclamante;}
	public void setdnireclamante(String pdnireclamante){dnireclamante=pdnireclamante;}
	public void setsolicitud(String psolicitud){solicitud=psolicitud;}
	public void setcodigo(String pcodigo){codigo=pcodigo;}
	public void setdesasunto(String pdesasunto){desasunto=pdesasunto;}
	public void setquejado(String pquejado){quejado=pquejado;}
	public void setespecialidad(String pespecialidad){especialidad=pespecialidad;}
	public void setcred(String pcred){cred=pcred;}
	public void setccas(String pccas){ccas=pccas;}
	public void setacciones(String pacciones){acciones=pacciones;}
	public void setdefensoriales(String pdefensoriales){defensoriales=pdefensoriales;}
	public void setestado(String pestado){estado=pestado;}
	public void setfecharesolucion(String pfecharesolucion){fecharesolucion=pfecharesolucion;}
	public void settingreso(String ptingreso){tingreso=ptingreso;}
	public void setobservaciones(String pobservaciones){observaciones=pobservaciones;}
	public void setatencion(String patencion){atencion=patencion;}
	public void setinputuser(String pinputuser){inputuser=pinputuser;}
	public void setdesidentidad (String pdesidentidad ){desidentidad =pdesidentidad ;}
	public void setdesprestacion (String pdesprestacion ){desprestacion =pdesprestacion ;}
	public void setdessolicitud (String pdessolicitud ){dessolicitud =pdessolicitud ;}
	public void setdcodigo(String pdcodigo){dcodigo=pdcodigo;}
	public void setdred (String pdred ){dred =pdred ;}
	public void setcenasides (String pcenasides ){cenasides =pcenasides ;}
	public void setdesaccion (String pdesaccion ){desaccion =pdesaccion ;}
	public void setdestado(String pdestado){destado=pdestado;}
	public void setdtingreso (String pdtingreso ){dtingreso =pdtingreso ;}
	public void setubitdpt (String pubitdpt ){ubitdpt =pubitdpt ;}
	public void setubitprv (String pubitprv ){ubitprv =pubitprv ;}
	public void setubitdtr(String pubitdtr){ubitdtr=pubitdtr;}
	public void setdseguro(String pdseguro){dseguro=pdseguro;}
	public void setdconsejeros(String pdconsejeros){dconsejeros=pdconsejeros;}
	public void setedituser(String pedituser){edituser=pedituser;}
	public void seteditdate(String peditdate){editdate=peditdate;}
	public void setbestadoreg(String pbestadoreg){bestadoreg=pbestadoreg;}
	public void setconclusion(String pconclusion){conclusion=pconclusion;}
	public void setdda2(String pdda2){dda2=pdda2;}
	public void setcond01(String pcond01){cond01=pcond01;}
	public void setcond02(String pcond02){cond02=pcond02;}
	public void setcond03(String pcond03){cond03=pcond03;}
	public void setcond04(String pcond04){cond04=pcond04;}
	public void setcond05(String pcond05){cond05=pcond05;}
	public void setcond06(String pcond06){cond05=pcond06;}
	public void setcond07(String pcond07){cond05=pcond07;}
	public void setcasUser(String pcasUser){casUser=pcasUser;}
	public void sethclinica(String phclinica){hclinica=phclinica;}
	public void setcodigo1(String pcodigo1){codigo1=pcodigo1;}
	public void setcodigo2(String pcodigo2){codigo2=pcodigo2;}
	public void setenlarehoscod(String penlarehoscod){enlarehoscod=penlarehoscod;}
	public void setenlservhoscod(String penlservhoscod){enlservhoscod=penlservhoscod;}
	public void setdcodigo1(String pdcodigo1){dcodigo1=pdcodigo1;}
	public void setdcodigo2(String pdcodigo2){dcodigo2=pdcodigo2;}
	public void setdenlarehoscod(String pdenlarehoscod){denlarehoscod=pdenlarehoscod;}
	public void setdenlservhoscod(String pdenlservhoscod){denlservhoscod=pdenlservhoscod;}
	public void setdcas(String pdcas){dcas=pdcas;}
	public void setdiagcod(String pdiagcod){diagcod=pdiagcod;}
	public void setdesdiagcod(String pdesdiagcod){desdiagcod=pdesdiagcod;}

	public void setTotalGen(int piTotalGen){iTotalGen=piTotalGen;}
	public void setiRegxPag(int piRegxPag){iRegxPag=piRegxPag;}
	public void setError(String pstrError){strError=pstrError;}

	
	private String cquien;
	private String cfecha;
	private String gestor;
	private String caccreal;

	public String getCquien() {
		return cquien;
	}

	public void setCquien(String cquien) {
		this.cquien = cquien;
	}

	public String getCfecha() {
		return cfecha;
	}

	public void setCfecha(String cfecha) {
		this.cfecha = cfecha;
	}

	public String getGestor() {
		return gestor;
	}

	public void setGestor(String gestor) {
		this.gestor = gestor;
	}

	public String getCaccreal() {
		return caccreal;
	}

	public void setCaccreal(String caccreal) {
		this.caccreal = caccreal;
	}
	
	private String codUsuario;

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	
	private String fechaInicio;
	private String fechaFin;

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	private List lista;

	public List getLista() {
		return lista;
	}

	public void setLista(List lista) {
		this.lista = lista;
	}
}
