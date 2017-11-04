
package clases;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import marco.Listado;
import util.Paginacion;


public class Ficha_t extends Listado implements java.io.Serializable {
	public Ficha_t(){}
	
	protected String strOpt="";
	public void setstrOpt(String pstrOpt){strOpt=pstrOpt;}
	public String getstrOpt(){return strOpt;}

	protected String Pag="";
	public void setPag(String pPag){Pag=pPag;}
	public String getPag(){return Pag;}
	
	protected String cred="";
	public void setcred(String pcred){cred=pcred;}
	public String getcred(){return cred;}
	
	protected String dda="";
	public void setdda(String pdda){dda=pdda;}
	public String getdda(){return dda;}
		
	protected String dda1="";
	public void setdda1(String pdda1){dda1=pdda1;}
	public String getdda1(){return dda1;}
	
	protected int iRegxPag = 0;   // numero de registros en la pagina
	public int getiRegxPag(){
		  if (iRegxPag==0) iRegxPag = marco.Pagina.REG_X_PAG;
		  return iRegxPag;
		}
	public void setiRegxPag(int piRegxPag){iRegxPag=piRegxPag;}
	public void setHshLista(Hashtable phshLista){super.hshLista=phshLista;}	
	public void setHshLista1(Hashtable phshLista){super.hshLista=phshLista;}	//persuacion erick 24/02/2015
	public void setHshLista2(Hashtable phshLista){super.hshLista=phshLista;}    //archivos adjuntos erick 24/02/2015
	public void setPaginacion(Paginacion pPaginacion){super.clPaginacion=pPaginacion;}
	public void setVector(Vector pVector){super.vListado=pVector;}

	public Hashtable getHshLista() {return super.hshLista; }
	public Hashtable getHshLista1() {return super.hshLista; } //persuacion erick 24/02/2015
	public Hashtable getHshLista2() {return super.hshLista; } //archivos adjuntos erick 24/02/2015
	public Paginacion getPaginacion(){return super.clPaginacion;}
	public Vector getVector(){return super.vListado;}
	
//	TDATOSG
	protected String ctdi="";
	public void setctdi(String pctdi){ctdi=pctdi;}
	public String getctdi(){return ctdi;}
	
	protected String dficdid="";
	public void setdficdid(String pdficdid){dficdid=pdficdid;}
	public String getdficdid(){return dficdid;}
	
	
	protected String dapepat="";
	public void setdapepat(String pdapepat){dapepat=pdapepat;}
	public String getdapepat(){return dapepat;}
	
	protected String dapemat="";
	public void setdapemat(String pdapemat){dapemat=pdapemat;}
	public String getdapemat(){return dapemat;}
	
	protected String dnom="";
	public void setdnom(String pdnom){dnom=pdnom;}
	public String getdnom(){return dnom;}
		
	protected String nficeda="";
	public void setnficeda(String pnficeda){nficeda=pnficeda;}
	public String getnficeda(){return nficeda;}
	
	protected String ddicsex="";
	public void setddicsex(String pddicsex){ddicsex=pddicsex;}
	public String getddicsex(){return ddicsex;}
	
	
	protected String ubigeo2="";
	public void set2ubigeo(String pubigeo2){ubigeo2=pubigeo2;}
	public String get2ubigeo(){return ubigeo2;}
	//descripcion del ubigeo2="";
	protected String ubitdpt="";
	public void setubitdpt(String pubitdpt){ubitdpt=pubitdpt;}
	public String getubitdpt(){return ubitdpt;}
	
	protected String ubigeo4="";
	public void set4ubigeo(String pubigeo4){ubigeo4=pubigeo4;}
	public String get4ubigeo(){return ubigeo4;}
	//descripcion de ubigeo4
	protected String ubitprv="";
	public void setubitprv(String pubitprv){ubitprv=pubitprv;}
	public String getubitprv(){return ubitprv;};
	
	protected String cficubigeo="";
	public void setcficubigeo(String pcficubigeo){cficubigeo=pcficubigeo;}
	public String getcficubigeo(){return cficubigeo;}
	//descripcion de cficubigeo
	protected String ubitdtr="";
	public void setubitdtr(String pubitdtr){ubitdtr=pubitdtr;}
	public String getubitdtr(){return ubitdtr;}
	
	protected String dficdir="";
	public void setdficdir(String pdficdir){dficdir=pdficdir;}
	public String getdficdir(){return dficdir;}
	
	protected String dfictel="";
	public void setdfictel(String pdfictel){dfictel=pdfictel;}
	public String getdfictel(){return dfictel;}
	
	protected String dficmail="";
	public void setdficmail(String pdficmail){dficmail=pdficmail;}
	public String getdficmail(){return dficmail;}	
	
	protected String ctpr="";
	public void setctpr(String pctpr){ctpr=pctpr;}
	public String getctpr(){return ctpr;}	
	
	protected String cfictipseg="";
	public void setcfictipseg(String pcfictipseg){cfictipseg=pcfictipseg;}
	public String getcfictipseg(){return cfictipseg;}		
	
	protected String dapepatrec="";
	public void setdapepatrec(String pdapepatrec){dapepatrec=pdapepatrec;}
	public String getdapepatrec(){return dapepatrec;}		
	
	protected String dapematrec="";
	public void setdapematrec(String pdapematrec){dapematrec=pdapematrec;}
	public String getdapematrec(){return dapematrec;}
	
	protected String dnomrec="";
	public void setdnomrec(String pdnomrec){dnomrec=pdnomrec;}
	public String getdnomrec(){return dnomrec;}
	
	protected String dficdidrec="";
	public void setdficdidrec(String pdficdidrec){dficdidrec=pdficdidrec;}
	public String getdficdidrec(){return dficdidrec;}
	
	protected String cmso="";
	public void setcmso(String pcmso){cmso=pcmso;}
	public String getcmso(){return cmso;}
	
	protected String dfichec="";
	public void setdfichec(String pdfichec){dfichec=pdfichec;};
	public String getdfichec(){return dfichec;};
	
	protected String cusucrea="";
	public void setcusucrea(String pcusucrea){cusucrea=pcusucrea;}
	public String getcusucrea(){return cusucrea;}
	
	protected String fusucrea="";
	public void setfusucrea(String pfusucrea){fusucrea=pfusucrea;}
	public String getfusucrea(){return fusucrea;}
	
	protected String cusumodi="";
	public void setcusumodi(String pcusumodi){cusumodi=pcusumodi;}
	public String getcusumodi(){return cusumodi;}
	
	protected String fusumodi="";
	public void setfusumodi(String pfusumodi){fusumodi=pfusumodi;}
	public String getfusumodi(){return fusumodi;}
	
	protected String bestadoreg="";
	public void setbestadoreg(String pbestadoreg){bestadoreg=pbestadoreg;}
	public String getbestadoreg(){return bestadoreg;}
	
	protected String cas="";
	public void setcas(String pcas){cas=pcas;}
	public String getcas(){return cas;}
	
	protected String ayear="";
	public void setayear(String payear){ayear=payear;}
	public String getayear(){return ayear;}
	
	protected String correl="";
	public void setcorrel(String pcorrel){correl=pcorrel;}
	public String getcorrel(){return correl;}
	
	protected String brevhclini="";
	public void setbrevhclini(String pbrevhclini){brevhclini=pbrevhclini;}
	public String getbrevhclini(){return brevhclini;}
	
	protected String ctdirec="";
	public void setctdirec(String pctdirec){ctdirec=pctdirec;}
	public String getctdirec(){return ctdirec;}
		
	protected String dtdirec="";
	public void setdtdirec(String pdtdirec){dtdirec=pdtdirec;}
	public String getdtdirec(){return dtdirec;}
	
	//campos extras de fdatosg
	protected String dtdi="";
	public void setdtdi(String pdtdi){dtdi=pdtdi;}
	public String getdtdi(){return dtdi;}
	
	protected String dtpr="";
	public void setdtpr(String pdtpr){dtpr=pdtpr;}
	public String getdtpr(){return dtpr;}
	
	protected String delemen="";
	public void setdelemen(String pdelemen){delemen=pdelemen;}
	public String getdelemen(){return delemen;}
	
	protected String dmso="";
	public void setdmso(String pdmso){dmso=pdmso;}
	public String getdmso(){return dmso;}
				
	protected String razsoc="";
	public void setrazsoc(String prazsoc){razsoc=prazsoc;}
	public String getrazsoc(){return razsoc;}
	
	protected String fichero="";
	public void setfichero(String pfichero){fichero=pfichero;}
	public String getfichero(){return fichero;}
	
	protected List lstFichero;
	
	public List getLstFichero() {
		return lstFichero;
	}
	public void setLstFichero(List lstFichero) {
		this.lstFichero = lstFichero;
	}
	
	//TDATOSAD
		
	protected String ddocqueja="";
	public void setddocqueja(String pddocqueja){ddocqueja=pddocqueja;};
	public String getddocqueja(){return ddocqueja;};
	
	protected String dapepatqueja="";
	public void setdapepatqueja(String pdapepatqueja){dapepatqueja=pdapepatqueja;};
	public String getdapepatqueja(){return dapepatqueja;};
	
	protected String dapematqueja="";
	public void setdapematqueja(String pdapematqueja){dapematqueja=pdapematqueja;};
	public String getdapematqueja(){return dapematqueja;};
		
	protected String dnomqueja="";
	public void setdnomqueja(String pdnomqueja){dnomqueja=pdnomqueja;};
	public String getdnomqueja(){return dnomqueja;};
	
	protected String enlservhoscod="";
	public void setenlservhoscod(String penlservhoscod){enlservhoscod=penlservhoscod;};
	public String getenlservhoscod(){return enlservhoscod;};	
	
	
	protected String diagcod="";
	public void setdiagcod(String pdiagcod){diagcod=pdiagcod;};
	public String getdiagcod(){return diagcod;};
	
	protected String ccas="";
	public void setccas(String pccas){ccas=pccas;};
	public String getccas(){return ccas;};
	
	protected String ctdiqueja="";
	public void setctdiqueja(String pctdiqueja){ctdiqueja=pctdiqueja;};
	public String getctdiqueja(){return ctdiqueja;};
	
	
	//extras de tdatosad
	protected String enlarehoscod="";
	public void setenlarehoscod(String penlarehoscod){enlarehoscod=penlarehoscod;};
	public String getenlarehoscod(){return enlarehoscod;}
	
	protected String diagdes="";
	public void setdiagdes(String pdiagdes) {diagdes=pdiagdes;}
	public String getdiagdes(){return diagdes;}
		
	protected String dcas="";
	public void setdcas(String pdcas){dcas=pdcas;};
	public String getdcas(){return dcas;};
	
	protected String cras="";
	public void setcras(String pcras){cras=pcras;};
	public String getcras(){return cras;};
	
	protected String dras="";
	public void setdras(String pdras){dras=pdras;};
	public String getdras(){return dras;};
	
	protected String arehosdes="";
	public void setarehosdes(String parehosdes){arehosdes=parehosdes;};
	public String getarehosdes(){return arehosdes;};
	
	protected String servhosdes="";
	public void setservhosdes(String pservhosdes){servhosdes=pservhosdes;};
	public String getservhosdes(){return servhosdes;};
	
	protected String cmotivos="";
	public void setcmotivos(String pcmotivos){cmotivos=pcmotivos;}
	public String getcmotivos(){return cmotivos;}
	
	protected String dmotivos="";
	public void setdmotivos(String pdmotivos){dmotivos=pdmotivos;}
	public String getdmotivos(){return dmotivos;}
	
	//TDATOSDEF
	protected String total_reg;
	public void setdtotal_reg(String pdtotal_reg){total_reg=pdtotal_reg;}
	public String getdtotal_reg(){return total_reg;}
	
	protected String dficacc;
	public void setdficacc(String pdficacc){dficacc=pdficacc;}
	public String getdficacc(){return dficacc;}
	
	protected String cade;
	public void setcade(String pcade){cade=pcade;}
	public String getcade(){return cade;}
	
	protected String cefi;
	public void setcefi(String pcefi){cefi=pcefi;}
	public String getcefi(){return cefi;}
	
	protected String fficciecaso;
	public void setfficciecaso(String pfficciecaso){fficciecaso=pfficciecaso;}
	public String getfficciecaso(){return fficciecaso;}
	
	protected String ctin;
	public void setctin(String pctin){ctin=pctin;}
	public String getctin(){return ctin;}
	
	protected String dficobs;
	public void setdficobs(String pdficobs){dficobs=pdficobs;}
	public String getdficobs(){return dficobs;}
	
	protected String bfictipate;
	public void setbfictipate(String pbfictipate){bfictipate=pbfictipate;}
	public String getbfictipate(){return bfictipate;}
	
	protected String bficcer;
	public void setbficcer(String pbficcer){bficcer=pbficcer;}
	public String getbficcer(){return bficcer;}
	
	//mapeo extra para tdatosdef
	protected String idtdatdef;
	
	protected String dade;
	public void setdade(String pdade){dade=pdade;}
	public String getdade(){return dade;}
	
	protected String defi;
	public void setdefi(String pdefi){defi=pdefi;}
	public String getdefi(){return defi;}
	
	protected String dtin;
	public void setdtin(String pdtin){dtin=pdtin;}
	public String getdtin(){return dtin;}
	
	private String ctem;
	public String getCtem() {
		return ctem;
	}
	public void setCtem(String ctem) {
		this.ctem = ctem;
	}
	private String cfconclu;//formas de conclusion
	private String cconclu;//conclusion
	public String getCfconclu() {
		return cfconclu;
	}
	public void setCfconclu(String cfconclu) {
		this.cfconclu = cfconclu;
	}
	public String getCconclu() {
		return cconclu;
	}
	public void setCconclu(String cconclu) {
		this.cconclu = cconclu;
	}
	
	private String cquien;
	private String cfecha;
	private String gestor;
	private String caccreal;
	private String iddatdef;
	public String getIdtdatdef() {return idtdatdef;}
	public void setIdtdatdef(String idtdatdef) {this.idtdatdef = idtdatdef;}
		
	

	private String grupOcup;
	

	
	public String getGrupOcup() {
		return grupOcup;
	}
	public void setGrupOcup(String grupOcup) {
		this.grupOcup = grupOcup;
	}
	public String getIddatdef() {
		return iddatdef;
	}
	public void setIddatdef(String iddatdef) {
		this.iddatdef = iddatdef;
	}
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
	
	
	private String reclaDir;
	private String reclaCel;
	private String reclaTel;
	private String reclaMail;
	public String getReclaDir() {
		return reclaDir;
	}
	public void setReclaDir(String reclaDir) {
		this.reclaDir = reclaDir;
	}
	public String getReclaCel() {
		return reclaCel;
	}
	public void setReclaCel(String reclaCel) {
		this.reclaCel = reclaCel;
	}
	public String getReclaTel() {
		return reclaTel;
	}
	public void setReclaTel(String reclaTel) {
		this.reclaTel = reclaTel;
	}
	public String getReclaMail() {
		return reclaMail;
	}
	public void setReclaMail(String reclaMail) {
		this.reclaMail = reclaMail;
	}
	
	private String cAccSeg;
	private String dAccSeg;
	public String getcAccSeg() {
		return cAccSeg;
	}
	public void setcAccSeg(String cAccSeg) {
		this.cAccSeg = cAccSeg;
	}
	public String getdAccSeg() {
		return dAccSeg;
	}
	public void setdAccSeg(String dAccSeg) {
		this.dAccSeg = dAccSeg;
	}
	
	private String dCargo;
	public String getdCargo() {
		return dCargo;
	}
	public void setdCargo(String dCargo) {
		this.dCargo = dCargo;
	}
	
	private String iddatconseg;
	public String getIddatconseg() {
		return iddatconseg;
	}
	public void setIddatconseg(String iddatconseg) {
		this.iddatconseg = iddatconseg;
	}
	private boolean isEmpty;
	public boolean isEmpty() {
		return isEmpty;
	}
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	private String cdelegado;
	public String getCdelegado() {
		return cdelegado;
	}
	public void setCdelegado(String cdelegado) {
		this.cdelegado = cdelegado;
	}
	private String rdelegado;
	public String getRdelegado() {
		return rdelegado;
	}
	public void setRdelegado(String rdelegado) {
		this.rdelegado = rdelegado;
	}
	private String casdelegado;
	public String getCasdelegado() {
		return casdelegado;
	}
	public void setCasdelegado(String casdelegado) {
		this.casdelegado = casdelegado;
	}
	
	private String dfictipseg="";
	public String getDfictipseg() {
		return dfictipseg;
	}
	public void setDfictipseg(String dfictipseg) {
		this.dfictipseg = dfictipseg;
	}
	
	private String canula="";
	public String getCanula() {
		return canula;
	}
	public void setCanula(String canula) {
		this.canula = canula;
	}
	
	private String descDelegado;
	private String descRedDelegado;
	public String getDescDelegado() {
		return descDelegado;
	}
	public void setDescDelegado(String descDelegado) {
		this.descDelegado = descDelegado;
	}
	public String getDescRedDelegado() {
		return descRedDelegado;
	}
	public void setDescRedDelegado(String descRedDelegado) {
		this.descRedDelegado = descRedDelegado;
	}
	
	private String tipoPersona;
	private String tipoSector;
	private String tipoIngreso;
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	public String getTipoSector() {
		return tipoSector;
	}
	public void setTipoSector(String tipoSector) {
		this.tipoSector = tipoSector;
	}
	public String getTipoIngreso() {
		return tipoIngreso;
	}
	public void setTipoIngreso(String tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}
	
	
	protected String ctma="";   //nponce 22/01/2015
	public void setctma(String pctma){ctma=pctma;}
	public String getctma(){return ctma;}
	
	protected String ctmc="";  //nponce 22/01/2015
	public void setctmc(String pctmc){ctmc=pctmc;}
	public String getctmc(){return ctmc;}
	
	private String fechaq;     //nponce 27/01/2015
	public String getfechaq() {
		return fechaq;
	}
	public void setfechaq(String fechaq) { //nponce 27/01/2015
		this.fechaq = fechaq;
	}
	
	//PERSUASION
	protected String cper="";   //nponce 28/01/2015 TIPO DE ACCION DE PERSUASION
	public void setcper(String pcper){cper=pcper;}
	public String getcper(){return cper;}

	//23/02/2015
	private String nombrePrestador;
	private String fecharesp;
	private String detalleper;
	private String cargoper;
	private String IdConclu;
	private String IdPersua;
	//01/03/2015
	private String tipoOperacion;
	
	public String getIdPersua() {
		return IdPersua;
	}
	public void setIdPersua(String IdPersua) {
		this.IdPersua = IdPersua;
	}
	
	public String getIdConclu() {
		return IdConclu;
	}
	public void setIdConclu(String IdConclu) {
		this.IdConclu = IdConclu;
	}
	public String getNombrePrestador() {
		return nombrePrestador;
	}
	public void setNombrePrestador(String nombrePrestador) {
		this.nombrePrestador = nombrePrestador;
	}
	public String getFecharesp() {
		return fecharesp;
	}
	public void setFecharesp(String fecharesp) {
		this.fecharesp = fecharesp;
	}
	public String getDetalleper() {
		return detalleper;
	}
	public void setDetalleper(String detalleper) {
		this.detalleper = detalleper;
	}
	public String getCargoper() {
		return cargoper;
	}
	public void setCargoper(String cargoper) {
		this.cargoper = cargoper;
	}
	
	
	//TFICHEROS
	private String  DfNomb;
	private String  IdGestion;
	private String  idFichero;
	
	
	
	public String getIdFichero() {
		return idFichero;
	}
	public void setIdFichero(String idFichero) {
		this.idFichero = idFichero;
	}
	public String getIdGestion() {
		return IdGestion;
	}
	public void setIdGestion(String idGestion) {
		IdGestion = idGestion;
	}
	
	private String IdArchivo;
	public String getIdArchivo() {
		return IdArchivo;
	}
	public void setIdArchivo(String idArchivo) {
		IdArchivo = idArchivo;
	}
	public String getDfNomb() {
		return DfNomb;
	}
	public void setDfNomb(String dfNomb) {
		DfNomb = dfNomb;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	
	private String idRecla;
	private String fechaRecla;
	public String getIdRecla() {
		return idRecla;
	}
	public void setIdRecla(String idRecla) {
		this.idRecla = idRecla;
	}
	public String getFechaRecla() {
		return fechaRecla;
	}
	public void setFechaRecla(String fechaRecla) {
		this.fechaRecla = fechaRecla;
	}
	
	private String viaIngreso;
	public String getViaIngreso() {
		return viaIngreso;
	}
	public void setViaIngreso(String viaIngreso) {
		this.viaIngreso = viaIngreso;
	}
	
	private String hojaReclamacion;
	public String getHojaReclamacion() {
		return hojaReclamacion;
	}
	public void setHojaReclamacion(String hojaReclamacion) {
		this.hojaReclamacion = hojaReclamacion;
	}
	
	//para el libro de reclamaciones
	private String oriRecla;
	private String nroRecla;
	public String getOriRecla() {
		return oriRecla;
	}
	public void setOriRecla(String oriRecla) {
		this.oriRecla = oriRecla;
	}
	public String getNroRecla() {
		return nroRecla;
	}
	public void setNroRecla(String nroRecla) {
		this.nroRecla = nroRecla;
	}
	
	
	
}