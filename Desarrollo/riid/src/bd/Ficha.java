package bd;

import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.ResultSetDynaClass;
import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import util.Constantes;
import util.Paginacion;
import bean.ReporteGeneralBean;
import beans.ListaRegistros;
import clases.Ficha_t;
import clases.ListFicha_t;

  public class Ficha {
	private static Logger logger = Logger.getLogger(Ficha.class);
	private boolean bOK = false;
	private String strError = "";

	public boolean isOK() {
	  return bOK;
	}
	public String getStringError() {
	  return strError;
	}

  public synchronized void FichaUpdate(Connection pcon, Ficha_t objMov, String tPerfil, String tUpd) throws SQLException {
		CallableStatement cs = null;
		try {
		  if(tUpd.equals(Constantes.OPCION_INSERTAR)){ // Insert
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_FICHA.SP_ING_DATOSG (?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,  ?,?,?,?,?,?,?,?,?,?, ?,?,?, ?,?,?,?)}");
				cs.setString(1,objMov.getcas());
				cs.registerOutParameter(2,OracleTypes.VARCHAR);
				cs.registerOutParameter(3,OracleTypes.VARCHAR);
				cs.setString(4,objMov.getctdi());
				cs.setString(5,objMov.getdficdid());
				cs.setString(6,objMov.getdapepat());
				cs.setString(7,objMov.getdapemat());
				cs.setString(8,objMov.getdnom());
				cs.setString(9,objMov.getnficeda());
				cs.setString(10,objMov.getddicsex());
				cs.setString(11,objMov.getcficubigeo());
				cs.setString(12,objMov.getdficdir());
				cs.setString(13,objMov.getdfictel());
				cs.setString(14,objMov.getdficmail());
				cs.setString(15,objMov.getctpr());
				cs.setString(16,objMov.getcfictipseg());
				cs.setString(17,objMov.getdapepatrec());
				cs.setString(18,objMov.getdapematrec());
				cs.setString(19,objMov.getdnomrec());
				cs.setString(20,objMov.getdficdidrec());
				cs.setString(21,objMov.getctdirec());
				cs.setString(22,objMov.getcmso());
				cs.setString(23,objMov.getbrevhclini());
				cs.setString(24,objMov.getctin());
				cs.setString(25,objMov.getdfichec());
				cs.setString(26,objMov.getcusucrea());
				cs.setString(27,objMov.getReclaDir());
				cs.setString(28,objMov.getReclaMail());
				cs.setString(29,objMov.getReclaTel());
				cs.setString(30,objMov.getReclaCel());
				cs.setString(31,objMov.getCdelegado());
				cs.setString(32,objMov.getcred());
				cs.setString(33,objMov.getccas());
				
				cs.setString(34,objMov.getTipoIngreso()); //30/01/2015
				cs.setString(35,objMov.getTipoPersona()); //30/01/2015
				cs.setString(36,objMov.getTipoSector()); //30/01/2015
				cs.setString(37,objMov.getrazsoc());//22/02/2015
				
		  }
		  if(tUpd.equals(Constantes.OPCION_MODIFICAR) ){ // Update
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_FICHA.SP_UPD_DATOSG (?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,  ?,?,?,?,?,?,?,?,?,?, ?,?,?, ?,?,?,?) }");
				cs.setString(1,objMov.getcas());
				cs.setString(2,objMov.getayear());
				cs.setString(3,objMov.getcorrel());
				cs.setString(4,objMov.getctdi());
				cs.setString(5,objMov.getdficdid());
				cs.setString(6,objMov.getdapepat());
				cs.setString(7,objMov.getdapemat());
				cs.setString(8,objMov.getdnom());
				cs.setString(9,objMov.getnficeda());
				cs.setString(10,objMov.getddicsex());
				cs.setString(11,objMov.getcficubigeo());
				cs.setString(12,objMov.getdficdir());
				cs.setString(13,objMov.getdfictel());
				cs.setString(14,objMov.getdficmail());
				cs.setString(15,objMov.getctpr());
				cs.setString(16,objMov.getcfictipseg());
				cs.setString(17,objMov.getdapepatrec());
				cs.setString(18,objMov.getdapematrec());
				cs.setString(19,objMov.getdnomrec());
				cs.setString(20,objMov.getdficdidrec());
				cs.setString(21,objMov.getcmso());
				cs.setString(22,objMov.getbrevhclini());
				cs.setString(23,objMov.getctin());
				cs.setString(24,objMov.getdfichec());
				cs.setString(25,objMov.getctdirec());
				cs.setString(26,objMov.getcusucrea());
				cs.setString(27,objMov.getReclaDir());
				cs.setString(28,objMov.getReclaMail());
				cs.setString(29,objMov.getReclaTel());
				cs.setString(30,objMov.getReclaCel());
				cs.setString(31,objMov.getCdelegado());
				cs.setString(32,objMov.getcred());
				cs.setString(33,objMov.getccas());
				
				cs.setString(34,objMov.getTipoIngreso());
				cs.setString(35,objMov.getTipoPersona());
				cs.setString(36,objMov.getTipoSector());
				cs.setString(37,objMov.getrazsoc()); //22/02/2015
		  }
		  if(tUpd.equals(Constantes.OPCION_ELIMINAR) ){ // ELIMINACION LOGICA DE FICHA
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_FICHA.SP_DEL_LOGICO (?,?,?,?,?) }");
				cs.setString(1,objMov.getayear());
				cs.setString(2,objMov.getccas());
				cs.setString(3,objMov.getcorrel());
				cs.setString(4,objMov.getbestadoreg());
				cs.setString(5,objMov.getcusucrea());
		  }
		  cs.executeUpdate();
		  
		  if(tUpd.equals(Constantes.OPCION_INSERTAR) ){
		  	try{
					String res_ayear=cs.getString(2);
					String res_correl = cs.getString(3);
					logger.debug("VALORES OBTENIDOS ==>> correl = "+res_correl+" y el año="+res_ayear);
					objMov.setcorrel(res_correl);
					objMov.setayear(res_ayear);
					
					
					//REGISTRAR SI VIENE DEL LIBRO DE RECLAMACIONES
					if(StringUtils.equalsIgnoreCase(objMov.getctin(), Constantes.CODIGO_LIBRO_RECLAMACIONES)){
						cs = (CallableStatement) pcon.prepareCall("{ call PKG_FICHA.SP_INSERT_LIBRECLA (?,?,?,?,?,?)}");
						cs.setString(1,objMov.getayear());
						cs.setString(2,objMov.getcas());
						cs.setString(3,objMov.getcorrel());
						cs.setString(4,objMov.getViaIngreso());
						 cs.registerOutParameter(5,OracleTypes.VARCHAR);
						 cs.registerOutParameter(6,OracleTypes.VARCHAR);
						cs.executeUpdate();
						
						String numRecla = cs.getString(5);
						String fecha = cs.getString(6);
						logger.debug("numRecla" + numRecla);
						objMov.setHojaReclamacion(numRecla);
						objMov.setFechaRecla(fecha);
					}
					
		  	}
		  	catch(SQLException e) {
		  		e.printStackTrace();
					strError="Ficha: Error en el codigo <br> " +e.getMessage();
					logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
		  	}
		  } 
		  
		  bOK=true;
		}
		catch(SQLException e) {
		  strError="Ficha: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
		  logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
		} 
		finally {
		  conexion.closeCallableStatement(cs);
		}
  }
  
  //actualizar valores de fdatosad.jsp
  public synchronized void UpdateDatosAd(Connection pcon, Ficha_t objMov) throws SQLException {
		  CallableStatement cs = null;
		  try {
					cs = (CallableStatement)
				  pcon.prepareCall("{ call PKG_FICHA.SP_UPD_DATOSAD (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				  cs.setString(1,objMov.getdfichec());
				  cs.setString(2,objMov.getctdiqueja());
				  cs.setString(3,objMov.getddocqueja());
					cs.setString(4,objMov.getdapepatqueja());
					cs.setString(5,objMov.getdapematqueja());
					cs.setString(6,objMov.getdnomqueja());
					cs.setString(7,objMov.getenlservhoscod());
					cs.setString(8,objMov.getdiagcod());
					cs.setString(9,objMov.getccas());
					cs.setString(10,objMov.getcusumodi());
					cs.setString(11,objMov.getcas());//logger.debug("objMov.getcas() "+objMov.getcas());
					cs.setString(12,objMov.getayear());//logger.debug("objMov.getayear() "+objMov.getayear());
					cs.setString(13,objMov.getcorrel());//logger.debug("objMov.getcorrel() "+objMov.getcorrel());
					cs.setString(14,objMov.getcmotivos());//logger.debug("objMov.getcmotivos() "+objMov.getcmotivos());
					cs.setString(15, objMov.getCtem());//logger.debug("objMov.getCtem() "+objMov.getCtem());
					cs.setString(16, objMov.getcmso());//logger.debug("objMov.getcmso() "+objMov.getcmso());
					cs.setString(17, objMov.getGrupOcup());//logger.debug("objMov.getGrupOcup() "+objMov.getGrupOcup());
					cs.setString(18, objMov.getenlarehoscod());//logger.debug("objMov.getenlarehoscod() "+objMov.getenlarehoscod());
					cs.setString(19, objMov.getctma());//logger.debug("objMov.getctma() "+objMov.getctma());    nponce
					cs.setString(20, objMov.getctmc());//logger.debug("objMov.getctmc() "+objMov.getctmc());	nponce		
					cs.setString(21, objMov.getfechaq());//logger.debug("objMov.getfechaq() "+objMov.getfechaq());
					cs.executeUpdate();		
					
					bOK=true;
						 
			}catch(SQLException e) {
			strError="Ficha: No se pudo grabar en la tabla tdatosad, se presentaron errores <br> " +e.getMessage();
			logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
		  } 
		  finally {
			conexion.closeCallableStatement(cs);
		  }
	}
	//graba fichero
	public synchronized void grabaFichero(Connection pcon, Ficha_t objMov) throws SQLException {
				logger.debug("Entrando nomas a updatedatosdef pcon es "+pcon.isClosed());
			  CallableStatement cs = null;
			  try {
						cs = (CallableStatement)
					  pcon.prepareCall("{ call PKG_FICHA.SP_UPD_DATOSDEF_FIC (?,?,?,?,?,?,?,?,?,?,?)}");
					  cs.setString(1,objMov.getdficacc());
					  cs.setString(2,objMov.getcade());
					  cs.setString(3,objMov.getcefi());
						//cs.setString(4,objMov.getfficciecaso());
						cs.setString(4,objMov.getctin());
						cs.setString(5,objMov.getdficobs());
						cs.setString(6,objMov.getbfictipate());
						cs.setString(7,objMov.getbficcer());
						cs.setString(8,objMov.getcusumodi());
						cs.setString(9,objMov.getcas());
						cs.setString(10,objMov.getayear());
						cs.setString(11,objMov.getcorrel());
						cs.executeUpdate();		
						
						bOK=true;
						
						 
				}catch(SQLException e) {
				strError="Ficha: No se pudo grabar en la tabla tdatosad, se presentaron errores <br> " +e.getMessage();
				logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
			  } 
			  finally {
				conexion.closeCallableStatement(cs);
				//conexion.closeConnection(pcon);
			  }
		}

  
	//recogemos la ficha segun cas year y correl
  public Ficha_t getFicha(Connection pcon,Ficha_t objLis){
  StringBuffer query=new StringBuffer();
  Statement stmt=null;
  ResultSet rs=null;
  query.append("select t.CTDI c1, t.dficdid c2, t.dapepat c3, t.dapemat c4, t.dnom c5, t.nficeda c6, t.ddicsex c7, ");
  query.append("substr(t.cficubigeo,0,2) c8, substr(t.cficubigeo,0,4) c9, t.cficubigeo c10, t.dficdir c11, t.dfictel c12, ");
  query.append("t.dficmail c13, t.ctpr c14, t.cfictipseg c15, t.dapepatrec c16, t.dapematrec c17, t.dnomrec c18, t.dficdidrec c19, ");
  query.append("t.cmso c20, t.brevhclini c21, ");
  query.append("(select a.dtdi from dbtdi10 a where a.ctdi=t.ctdi) c22, ");
  query.append("(select c.dd from (select distinct(substr(d.ubicubi,0,2)) cd, d.ubitdpt dd from cpccubit d ) c where c.cd=substr(t.cficubigeo,0,2) and rownum=1)c23, ");
  query.append("(select e.dp from (select distinct(substr(f.ubicubi,0,4)) cp, f.ubitprv dp from cpccubit f )e where e.cp=substr(t.cficubigeo,0,4) and rownum=1)c24, ");
  query.append("(select g.ubitdtr from cpccubit g where g.ubicubi=t.cficubigeo) c25, ");
  query.append("(select h.dtpr from dbtpr10 h where h.btprestareg = '1' and h.ctpr=t.ctpr) c26, ");
  query.append("(select i.delemen from tcod_gendets i where i.tcgc_ctabla = '005' and i.CELEMEN=t.cfictipseg) c27, ");
  query.append("(select j.dmso from dbmso10 j where j.bmsoestareg = '1' and j.cmso=t.cmso) c28,t.dfichec c29,t.ctin c30, ");
  query.append("(select k.dtin from dbtin10 k where k.ctin=t.ctin)c31,t.ctdirec c32,t.dficdidrec c33,(select n.dtdi from dbtdi10 n where n.ctdi=t.ctdirec) c34, ");
  query.append(" t.recladir c35, t.reclamail c36, t.reclatel c37 ,t.reclacel c38, t.delasig c39, (select d.cras from tusuarios u, dmcas10 c, dmras10 d where u.ccas = c.ccas and c.cras = d.cras and u.cusu  = t.delasig) c40, ");
  query.append(" t.reclared c41, t.reclacas c42, t.bestadoreg c43, ");
  query.append(" (select c.ccas from tusuarios u, dmcas10 c, dmras10 d where u.ccas = c.ccas and c.cras = d.cras and u.cusu  = t.delasig) c44, ");
  query.append(" (select v.DELEMEN from tcod_gendets v where v.celemen = t.cfictipseg and v.tcgc_ctabla = '005') c45, ");
  query.append(" to_char(t.fusucrea, 'dd/mm/yyyy HH24:MI:SS  AM') c46, ");
  query.append(" (select u.dapepatusu || ' ' || u.dapematusu || ', ' || u.dnomusu delegado from tusuarios u, dmcas10 c, dmras10 d where u.ccas = c.ccas and c.cras = d.cras and u.cusu  = t.delasig) c47,  ");
  query.append(" (select c.dcas from tusuarios u, dmcas10 c, dmras10 d where u.ccas = c.ccas and c.cras = d.cras and u.cusu  = t.delasig) c48, ");
  query.append(" to_char(t.fusumodi, 'dd/mm/yyyy HH24:MI:SS  AM') c49, ");
  query.append(" t.tipingr c50, t.tippers c51, t.tipsect c52, ");
  query.append(" t.razsoc c53, ");
  query.append(" t.orirecla c54, t.nrorecla c55 "); //libro reclamaciones
  query.append("from tdatosg t where t.cas='"+objLis.getcas()+"' and ");
  query.append("t.ayear='"+objLis.getayear()+"' and t.correl="+objLis.getcorrel());
  
  logger.debug("query: "+query.toString());
  
  try{
  	stmt=pcon.createStatement();
  	rs=stmt.executeQuery(query.toString());
  	if(rs.next()){
  			objLis.setctdi(rs.getString(1));
  			objLis.setdficdid(rs.getString(2));
  			objLis.setdapepat(rs.getString(3));
  			objLis.setdapemat(rs.getString(4));
  			objLis.setdnom(rs.getString(5));
  			objLis.setnficeda(rs.getString(6));
  			objLis.setddicsex(rs.getString(7));
  			objLis.set2ubigeo(rs.getString(8));
  			objLis.set4ubigeo(rs.getString(9));
  			objLis.setcficubigeo(rs.getString(10));
  			objLis.setdficdir(rs.getString(11));
  			objLis.setdfictel(rs.getString(12));
  			objLis.setdficmail(rs.getString(13));
  			objLis.setctpr(rs.getString(14));
  			objLis.setcfictipseg(rs.getString(15));
  			objLis.setdapepatrec(rs.getString(16));
  			objLis.setdapematrec(rs.getString(17));
  			objLis.setdnomrec(rs.getString(18));
  			objLis.setdficdidrec(rs.getString(19));
  			objLis.setcmso(rs.getString(20));
  			objLis.setbrevhclini(rs.getString(21));
  			objLis.setdtdi(rs.getString(22));
  			objLis.setubitdpt(rs.getString(23));
  			objLis.setubitprv(rs.getString(24));
  			objLis.setubitdtr(rs.getString(25));
  			objLis.setdtpr(rs.getString(26));
  			objLis.setdelemen(rs.getString(27));
  			objLis.setdmso(rs.getString(28));
  			objLis.setdfichec(rs.getString(29));
  			objLis.setctin(rs.getString(30));
  			objLis.setdtin(rs.getString(31));
  			objLis.setctdirec(rs.getString(32));
  			objLis.setdficdidrec(rs.getString(33));
  			objLis.setdtdirec(rs.getString(34));
  			objLis.setReclaDir(rs.getString(35));
  			objLis.setReclaMail(rs.getString(36));
  			objLis.setReclaTel(rs.getString(37));
  			objLis.setReclaCel(rs.getString(38));
  			objLis.setCdelegado(rs.getString(39));
  			objLis.setRdelegado(rs.getString(40));
  			objLis.setcred(rs.getString(41));
  			objLis.setccas(rs.getString(42));
  			objLis.setbestadoreg(rs.getString(43));
  			objLis.setCasdelegado(rs.getString(44));
  			objLis.setDfictipseg(rs.getString(45));
  			objLis.setfusucrea(rs.getString(46));
  			objLis.setDescDelegado(rs.getString(47));
  			objLis.setDescRedDelegado(rs.getString(48));
  			objLis.setfusumodi(rs.getString(49));
  			objLis.setTipoIngreso(rs.getString(50));
  			objLis.setTipoPersona(rs.getString(51));
  			objLis.setTipoSector(rs.getString(52));
  			objLis.setrazsoc(rs.getString(53));
  			objLis.setOriRecla(rs.getString(54));
  			objLis.setNroRecla(rs.getString(55));
  			bOK=true;
  	}else{
  			strError="Error en getFicha...!!!!";
  	}
  			
  			
  }catch(Exception e){
					strError = "Se presento un error al buscar el registro " + e.getMessage();
				  logger.debug("Error [" + getClass().getName() + "]. " + e.getMessage());
  }finally{
  		conexion.closeResultSet(rs);
  		conexion.closeStatement(stmt);
  }
  
  return objLis;
  }
  
  //recuperar tdatosad
  public Ficha_t getFdatosAd(Connection pcon,Ficha_t objLis){
	StringBuffer query=new StringBuffer();
	Statement stmt=null;
	ResultSet rs=null;
	query.append("select t.dfichec c1,t.ctdiqueja c2,t.ddocqueja c3,t.dapepatqueja c4,t.dapematqueja c5,t.dnomqueja c6,t.enlservhoscod c7,");
	query.append("t.diagcod c8,t.ccas c9, (select a.enlarehoscod from dmare10 a where a.enlservhoscod=t.enlservhoscod and rownum=1)c10,");
	query.append("'0' c11,(select c.dcas from dmcas10 c where c.ccas=t.ccas and rownum=1)c12,");
	query.append("(select d.cras from dmcas10 d where d.ccas=t.ccas and rownum=1)c13,");
	query.append("(select e.dras from dmras10 e where e.cras=(select d.cras from dmcas10 d where d.ccas=t.ccas) and rownum=1)c14,");
	query.append("(select f.arehosdes from dmare10 f where f.enlservhoscod=t.enlservhoscod and rownum=1)c15,");
	query.append("(select g.servhosdes from dmare10 g where g.enlservhoscod=t.enlservhoscod and rownum=1)c16,");
	query.append("(select h.dtdi from dbtdi10 h where h.ctdi=t.ctdiqueja)c17, t.cmet c18, " +
			"(select cmso from tdatosg where cas="+objLis.getcas()+" and ayear=" +objLis.getayear()+ " and correl="+objLis.getcorrel()+") c19, " +
					"t.grupocu c20, " +
					"(select bestadoreg from tdatosg where cas="+objLis.getcas()+" and ayear="+objLis.getayear()+" and correl="+objLis.getcorrel()+") c21,  " +
							"t.ENLAREHOSCOD c22, ");
	query.append("t.ctma c23, t.ctmc c24, "); //nponce 30/01/2015
	query.append("to_char(t.fechaconclu,'dd/mm/yyyy') c25 "); //nponce 30/01/2015
	query.append("from tdatosad t where t.cas="+objLis.getcas()+" and t.ayear="+objLis.getayear()+" and t.correl="+objLis.getcorrel());
	
	logger.debug("query para tdatosad: "+query.toString());
  
	try{
	  stmt=pcon.createStatement();
	  rs=stmt.executeQuery(query.toString());
	  if(rs.next()){
			  objLis.setdfichec(rs.getString(1));
			  objLis.setctdiqueja(rs.getString(2));
			  objLis.setddocqueja(rs.getString(3));
			  objLis.setdapepatqueja(rs.getString(4));
			  objLis.setdapematqueja(rs.getString(5));
			  objLis.setdnomqueja(rs.getString(6));
			  objLis.setenlservhoscod(rs.getString(7));
			  objLis.setdiagcod(rs.getString(8));
			  //objLis.setccas(rs.getString(9));
			  //extras de apoyo
			  objLis.setenlarehoscod(rs.getString(10));
			  objLis.setdiagdes(rs.getString(11));
			  objLis.setdcas(rs.getString(12));
			  objLis.setcras(rs.getString(13));
			  objLis.setdras(rs.getString(14));
			  objLis.setarehosdes(rs.getString(15));
			  objLis.setservhosdes(rs.getString(16));
			  objLis.setdtdi(rs.getString(17));
			  objLis.setCtem(rs.getString(18));
			  //se obtiene en la nueva modificacion 03072014
			  objLis.setcmso(StringUtils.defaultString(rs.getString(19)));
			//se obtiene en la nueva modificacion 16072014
			  objLis.setGrupOcup(rs.getString(20));
			  objLis.setbestadoreg(rs.getString(21));
			  objLis.setenlarehoscod(rs.getString(22));
			  objLis.setctma(rs.getString(23)); //nponce 30/01/2015
			  objLis.setctmc(rs.getString(24));  //nponce 30/01/2015
			  objLis.setfechaq(rs.getString(25)); //nponce 30/01/2015
			  
			  bOK=true;
	  }else{
			  strError="Error al intentar recuperar de tdatosad...!!!!";
	  }
			
	}catch(Exception e){
					  strError = "Se presento un error al buscar el registro " + e.getMessage();
					logger.debug("Error [" + getClass().getName() + "]. " + e.getMessage());
	}finally{
		  conexion.closeResultSet(rs);
		  conexion.closeStatement(stmt);
	}
  //recupero los factores contribuyentes...
  
	return objLis;
	}
  
 
  //verificar 28082014
  public clases.ListFicha_t getFichaAnula(Connection pcon, clases.ListFicha_t objLis)throws SQLException {
		Statement stmt   = null;
		ResultSet rs = null;
		StringBuffer strSql = new StringBuffer();
		strSql.append("select t.ayear||' - '||t.cas||' - '||t.correl, to_char(t.fusucrea,'dd/mm/yyyy'), t.dnom||' '||t.dapepat||' '||t.dapemat, ");
		strSql.append("dnomusu || ' ' || dapepatusu || ' ' || dapematusu, t.bestadoreg ");
		strSql.append("from tdatosg t, tusuarios a "); 
		strSql.append("where UPPER(t.cusucrea) = UPPER(a.cusu) "); 
		if(objLis.getcond01().compareTo("")!=0){
			strSql.append("and t.ayear= '" + objLis.getcond01() + "' "); 
		}
		if(objLis.getcond02().compareTo("")!=0){
			strSql.append("and t.cas= '" + objLis.getcond02() + "' "); 
		}
		if(objLis.getcond03().compareTo("")!=0){
			strSql.append("and t.correl= '" + objLis.getcond03() + "' "); 
		}
		logger.debug(strSql);
		try {
			stmt = pcon.createStatement();
			rs = stmt.executeQuery(strSql.toString());
			if (rs.next()) {
				objLis.setcodigoda(rs.getString(1));
				objLis.setdda(rs.getString(2));
				objLis.settitular(rs.getString(3));
				objLis.setinputuser(rs.getString(4));
				objLis.setbestadoreg(rs.getString(5));
				bOK=true;
			} 
			else {
				strError = " No se encontró información del registro ";
			}
		 
		}
		catch (Throwable exception) {
		  logger.debug("Error Throwable en getFichaAnula clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
		  strError="No se pudo obtener informacion, se presentaron errores <br>";
		}
		finally {
			conexion.closeResultSet(rs);
			conexion.closeStatement(stmt);
		}
		return objLis;
		  
  }

	public Ficha_t getLisFichaAsignadaxUser(Connection pcon, Ficha_t objLis)throws SQLException {
		Statement stmt   = null;
		ResultSet rs = null;
		StringBuffer strSql = new StringBuffer();
		try {
		  strSql.append("select t.ayear c1, t.cas c2, t.correl c3, to_char(t.fusucrea,'dd/mm/yyyy') c4, t.dapepat c5, ");
		  strSql.append("t.dapemat c6, t.dnom c7, t.dapepatrec c8, t.dapematrec c9, t.dnomrec c10, t.cusucrea c11, ");
		  strSql.append(" (select e.defi from dbefi10 e where e.cefi = t.bestadoreg) c12 ");
		  strSql.append(" from tdatosg t where t.delasig = '"+objLis.getCdelegado()+"' and t.bestadoreg='1' and ");
		  //filtros
		  if(objLis.getcred().compareTo("")!=0){
			  strSql.append("(select b.cras from dmcas10 b,tdatosad c where b.ccas=c.ccas and t.ayear=c.ayear and t.cas=c.cas and t.correl=c.correl)='"+objLis.getcred()+"' and ");
	  	  }
		  if(objLis.getccas().compareTo("")!=0){
			  strSql.append("(select d.ccas from tdatosad d where d.cas=t.cas and d.ayear=t.ayear and d.correl=t.correl)='"+objLis.getccas()+"' and "); 
			}
		  if(objLis.getcas().compareTo("")!=0){
			  strSql.append("t.cas = '" + objLis.getcas() + "' and "); 
			}
		  if(objLis.getayear().compareTo("")!=0){
			  strSql.append("t.ayear = '" + objLis.getayear() + "' and "); 
			}
		  if(objLis.getcorrel().compareTo("")!=0){
			  strSql.append("t.correl = '" + objLis.getcorrel() + "' and "); 
			}
		  if(objLis.getctdi().compareTo("")!=0 && objLis.getdficdid().compareTo("")!=0){
		  	strSql.append("t.ctdi = '" + objLis.getctdi() + "' and t.dficdid = '" + objLis.getdficdid() + "' and ");
			}
			if(objLis.getdapepat().compareTo("")!=0){
				strSql.append("t.dapepat like '" + objLis.getdapepat() + "%' and ");
			}
		  if(objLis.getdapemat().compareTo("")!=0){
				strSql.append("t.dapemat like '" + objLis.getdapemat() + "%' and ");
			}
		  if(objLis.getdnom().compareTo("")!=0){
				strSql.append("t.dnom like '" + objLis.getdnom() + "%' and ");
			}
		  if(objLis.getdapepatrec().compareTo("")!=0){
			  strSql.append("t.dapepatrec like '" + objLis.getdapepatrec() + "%' and ");
		  }
			if(objLis.getdapematrec().compareTo("")!=0){
			  strSql.append("t.dapematrec like '" + objLis.getdapematrec() + "%' and ");
		  }
			if(objLis.getdnomrec().compareTo("")!=0){
			  strSql.append("t.dnomrec like '" + objLis.getdnomrec() + "%' and ");
		  }
			if(objLis.getdda().compareTo("")!=0 && objLis.getdda1().compareTo("")!=0){
				strSql.append("t.fusucrea between to_date('"+ objLis.getdda() +"','dd/mm/yyyy hh24:mi') and  to_date('"+ objLis.getdda1() +" 23:59','dd/mm/yyyy hh24:mi') and ");
			}
		  strSql.append("t.correl is not null ");
		  strSql.append("order by t.fusucrea desc ");
		  logger.debug("lordinola: "+strSql);
		  ListaRegistros lstRegistros = new ListaRegistros();
		  lstRegistros.setConnection(pcon);
		  		 
		  lstRegistros.setNumRegistros(1500);
		  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt("1"), strSql.toString(), false));
		  
		  objLis.setdtotal_reg(Integer.toString( objLis.getHshLista().size()  +  ((Integer.parseInt(objLis.getPag()) - 1)  * 15)));
		  
		  lstRegistros.setNumRegistros(objLis.getiRegxPag());
		  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
		  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
		  
		  if(Integer.parseInt(objLis.getPag())!=1){
				 objLis.setdtotal_reg( Integer.toString( Integer.parseInt( objLis.getdtotal_reg() ) - ((Integer.parseInt(objLis.getPag()) - 1)  * 15) ));
			 }
		  
		  stmt = pcon.createStatement();
		  rs = stmt.executeQuery(strSql.toString());
		  if (rs.next()) {
			  bOK=true;
		  }
		  else{
			  strError = "Según los criterios seleccionados, no hay información";
		  }
		  
		}
		catch (Throwable exception) {
		  logger.debug("Error Throwable en getLisFicha clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
		  strError="No se pudo obtener informacion, se presentaron errores <br>";
		}finally {
			conexion.closeResultSet(rs);
			conexion.closeStatement(stmt);
		}
	  return objLis;
	}

	
	
	public Ficha_t getLisConsulta(Connection pcon, Ficha_t objLis)throws SQLException {
		Statement stmt   = null;
		ResultSet rs = null;
		StringBuffer strSql = new StringBuffer();
		try {
			strSql.append("select t.ayear c1, t.cas c2, t.correl c3, to_char(t.fusucrea,'dd/mm/yyyy') c4, t.dapepat c5, ");
		  strSql.append("t.dapemat c6, t.dnom c7, t.dapepatrec c8, t.dapematrec c9, t.dnomrec c10, t.cusucrea c11, ");
		  strSql.append("(select e.defi from dbefi10 e where e.cefi = t.bestadoreg) c12 ");
		  strSql.append("from tdatosg t where ");
		  //filtros
		  if(objLis.getcred().compareTo("")!=0){
			  strSql.append("(select b.cras from dmcas10 b,tdatosad c where b.ccas=c.cas and t.ayear=c.ayear and t.cas=c.cas and t.correl=c.correl)='"+objLis.getcred()+"' and ");
	  	}
		  if(objLis.getccas().compareTo("")!=0){
			  strSql.append("(select d.cas from tdatosad d where d.cas=t.cas and d.ayear=t.ayear and d.correl=t.correl)='"+objLis.getccas()+"' and "); 
			}
		  if(objLis.getcas().compareTo("")!=0){
			  strSql.append("t.cas = '" + objLis.getcas() + "' and "); 
			}
		  if(objLis.getayear().compareTo("")!=0){
			  strSql.append("t.ayear = '" + objLis.getayear() + "' and "); 
			}
		  if(objLis.getcorrel().compareTo("")!=0){
			  strSql.append("t.correl = '" + objLis.getcorrel() + "' and "); 
			}
		  if(objLis.getctdi().compareTo("")!=0 && objLis.getdficdid().compareTo("")!=0){
		  	strSql.append("t.ctdi = '" + objLis.getctdi() + "' and t.dficdid = '" + objLis.getdficdid() + "' and ");
			}
			if(objLis.getdapepat().compareTo("")!=0){
				strSql.append("t.dapepat like '" + objLis.getdapepat() + "%' and ");
			}
		  if(objLis.getdapemat().compareTo("")!=0){
				strSql.append("t.dapemat like '" + objLis.getdapemat() + "%' and ");
			}
		  if(objLis.getdnom().compareTo("")!=0){
				strSql.append("t.dnom like '" + objLis.getdnom() + "%' and ");
			}
		  if(objLis.getdapepatrec().compareTo("")!=0){
			  strSql.append("t.dapepatrec like '" + objLis.getdapepatrec() + "%' and ");
		  }
			if(objLis.getdapematrec().compareTo("")!=0){
			  strSql.append("t.dapematrec like '" + objLis.getdapematrec() + "%' and ");
		  }
		  if(objLis.getdnomrec().compareTo("")!=0){
			  strSql.append("t.dnomrec like '" + objLis.getdnomrec() + "%' and ");
		  }
		  if(objLis.getdda().compareTo("")!=0 && objLis.getdda1().compareTo("")!=0){
				strSql.append(" t.fusucrea >= to_date('"+ StringUtils.trim(objLis.getdda())+"','dd/mm/yyyy')  and t.fusucrea <= to_date('"+ StringUtils.trim(objLis.getdda1())+"','dd/mm/yyyy')+1 and ");
				
		 }
		  strSql.append("t.correl is not null ");
			strSql.append("order by t.correl desc ");
		  logger.debug(strSql);
		  ListaRegistros lstRegistros = new ListaRegistros();
		  lstRegistros.setConnection(pcon);
		  lstRegistros.setNumRegistros(objLis.getiRegxPag());
		  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
		  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
		  stmt = pcon.createStatement();
		  rs = stmt.executeQuery(strSql.toString());
		  if (rs.next()) {
			  bOK=true;
		  }
		  else{
			  strError = "Según los criterios seleccionados, no hay información";
		  }
		 
		}
		catch (Throwable exception) {
		  logger.debug("Error Throwable en getLisFicha clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
		  strError="No se pudo obtener informacion, se presentaron errores <br>";
		}finally {
			conexion.closeResultSet(rs);
			conexion.closeStatement(stmt);
		}
	  return objLis;
	}


	
	public void getFdatosAdConclusiones(Connection pcon,Ficha_t objLis){//datos conclusiones
		StringBuffer query=new StringBuffer();
		Statement stmt=null;
		ResultSet rs=null;
		query.append("select t.iddatconseg, t.cconclu, t.caccseg, t.daccseg ");
		query.append("from tdatosconclu t where t.cas="+objLis.getcas()+" and t.ayear="+objLis.getayear()+" and t.correl="+objLis.getcorrel());
		
		//logger.debug("query para tdatosad: "+query.toString());
	  
		try{
		  stmt=pcon.createStatement();
		  rs=stmt.executeQuery(query.toString());
		  if(rs.next()){
				  objLis.setIddatconseg(rs.getString(1));
				  objLis.setCconclu(rs.getString(2));
				  objLis.setcAccSeg(rs.getString(3));
				  objLis.setdAccSeg(rs.getString(4));
				  bOK=true;
		  }else{
				  strError="Error al intentar recuperar de tdatosad...!!!!";
		  }
				  
		}catch(Exception e){
						  strError = "Se presento un error en getFdatosAdConclusiones " + e.getMessage();
						logger.debug("Error [" + getClass().getName() + "]. " + e.getMessage());
		}finally{
			  conexion.closeResultSet(rs);
			  conexion.closeStatement(stmt);
		}

		}
	 public synchronized void fichaUpdateConclusiones(Connection pcon, Ficha_t objMov) throws SQLException {
			CallableStatement cs = null;
			try {

			  
					cs = (CallableStatement)
					pcon.prepareCall("{ call PKG_FICHA.SP_UPD_CONCLUSIONES (?,?,?,?,?,?,?) }");
					cs.setString(1,objMov.getcas());
					cs.setString(2,objMov.getayear());
					cs.setString(3,objMov.getcorrel());
					cs.setString(4,objMov.getCfconclu());
					cs.setString(5,objMov.getCconclu());
					cs.setString(6,objMov.getcAccSeg());
					cs.setString(7,objMov.getdAccSeg());
					int i =cs.executeUpdate();
					
					bOK=true;
					
			  
			}catch(SQLException e) {
								strError="Ficha: Error en el codigo <br> " +e.getMessage();
								logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
					  	}
					
					finally {
					  conexion.closeCallableStatement(cs);
					}
	 }
	public synchronized void insertarDatosDef(Connection pcon, Ficha_t objMov) throws SQLException { 
		CallableStatement cs = null;
		try {

		  
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_FICHA.SP_INSERTAR_DATDEF (?,?,?,?,?,?,?,?,?) }");
				cs.setString(1,objMov.getcas());
				cs.setString(2,objMov.getayear());
				cs.setString(3,objMov.getcorrel());
				cs.setString(4,objMov.getCquien());
				cs.setString(5,objMov.getCfecha());
				cs.setString(6,objMov.getGestor());
				cs.setString(7,objMov.getCaccreal());
				cs.setString(8,objMov.getdCargo());
				cs.setString(9,objMov.getcusucrea());
				//cs.setString(8,objMov.getbestadoreg());
				int i =cs.executeUpdate();
				
				bOK=true;
				
		  
		}catch(SQLException e) {
							strError="Ficha: Error en el codigo <br> " +e.getMessage();
							logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
				  	}
				
				finally {
				  conexion.closeCallableStatement(cs);
				}
		
	}
	
	public synchronized void insertarFichero(Connection pcon, Ficha_t objMov, String extension, String usuario) throws SQLException { 
		CallableStatement cs = null;
		try {
			
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_FICHA.SP_INSERT_FICHERO (?,?,?,?,?,?,?) }");
				cs.setString(1,objMov.getcas());
				cs.setString(2,objMov.getayear());
				cs.setString(3,objMov.getcorrel());
				cs.setString(4,objMov.getayear()+"_"+objMov.getcas()+"_"+objMov.getcorrel());
				cs.setString(5,usuario);
				cs.setString(6,extension);
				cs.registerOutParameter(7, OracleTypes.VARCHAR);
				
				int i =cs.executeUpdate();
				String idFichero = cs.getString(7);
				logger.debug("idFichero 1 ===========>>>> "+ idFichero);
				objMov.setIdFichero(idFichero);
				bOK=true;
				
		  
		}catch(SQLException e) {
			e.printStackTrace();
							strError="Ficha: Error en el codigo <br> " +e.getMessage();
							logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
				  	}
				
				finally {
				  conexion.closeCallableStatement(cs);
				}
		
	}
	
	
	public void getListFdatosDef(Connection pcon, Ficha_t objLis)throws SQLException {
		Statement stmt   = null;
		ResultSet rs = null;
		StringBuffer strSql = new StringBuffer();
		strSql.append("select t.dcquien , to_char(t.fcaso, 'dd/mm/yyyy') ,  t.dgestor,  (select r.descaccreal from taccreal r where r.idaccreal = t.caccreal)" +
				", decode(t.bestadoreg,'1','ACTIVO','CERRADO'), t.caccreal,idtdatdef, dcargo ");
		
		strSql.append("from TDATOSDEF t "); 
		strSql.append("where  "); 
		if(objLis.getayear().compareTo("")!=0){
			strSql.append(" t.ayear= '" + objLis.getayear() + "' "); 
		}
		if(objLis.getcas().compareTo("")!=0){
			strSql.append("and t.cas= '" + objLis.getcas() + "' "); 
		}
		if(objLis.getcorrel().compareTo("")!=0){
			strSql.append("and t.correl= '" + objLis.getcorrel() + "' "); 
		}
		strSql.append(" AND t.bestadoreg = '1' order by t.fusucrea desc "); 
		logger.debug(" getListFdatosDef => " +strSql);
		try {
			stmt = pcon.createStatement();
			
			 ListaRegistros lstRegistros = new ListaRegistros();
			 lstRegistros.setConnection(pcon);

			 lstRegistros.setNumRegistros(1500);
			 objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt("1"), strSql.toString(), false));
			 
			
			 objLis.setdtotal_reg(Integer.toString( objLis.getHshLista().size()  +  ((Integer.parseInt(objLis.getPag()) - 1)  * 15)));
			 
			 lstRegistros.setNumRegistros(objLis.getiRegxPag());
			 objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
			 objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
			 
			 
			 if(Integer.parseInt(objLis.getPag())!=1){
				 objLis.setdtotal_reg( Integer.toString( Integer.parseInt( objLis.getdtotal_reg() ) - ((Integer.parseInt(objLis.getPag()) - 1)  * 15) ));
			 }
			 
			 
			rs = stmt.executeQuery(strSql.toString());
			if (rs.next()) {
				bOK=true;
			}
		  
		}
		catch (Throwable exception) {
		  logger.debug("Error Throwable en getListFdatosDef clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
		  strError="No se pudo obtener informacion, se presentaron errores <br>";
		}
		finally {
			conexion.closeResultSet(rs);
			conexion.closeStatement(stmt);
		}
		 
  }
	public synchronized void updateDatosDef(Connection pcon, Ficha_t objMov) throws SQLException { 
			CallableStatement cs = null;
			try {

			  
					cs = (CallableStatement)
					pcon.prepareCall("{ call PKG_FICHA.SP_UPD_DATDEF (?,?,?,?,?,?,?,?,?,?,?) }");
					cs.setString(1,objMov.getcas());
					cs.setString(2,objMov.getayear());
					cs.setString(3,objMov.getcorrel());
					cs.setString(4,objMov.getIddatdef());
					cs.setString(5,objMov.getCquien());
					cs.setString(6,objMov.getCfecha());
					cs.setString(7,objMov.getGestor());
					cs.setString(8,objMov.getCaccreal());
					cs.setString(9,objMov.getdCargo());
					cs.setString(10,objMov.getcusumodi());
					cs.setString(11,objMov.getbestadoreg());
					
					logger.debug(" objMov.getcas() " +objMov.getcas());
					logger.debug(" objMov.getayear() " +objMov.getayear());
					logger.debug(" objMov.getcorrel() " +objMov.getcorrel());
					logger.debug(" objMov.getIddatdef() " +objMov.getIddatdef());
					logger.debug(" objMov.getCquien() " +objMov.getCquien());
					logger.debug(" objMov.getCfecha() " +objMov.getCfecha());
					logger.debug(" objMov.getGestor() " +objMov.getGestor());
					logger.debug(" objMov.getCaccreal() " +objMov.getCaccreal());
					logger.debug(" objMov.getbestadoreg() " +objMov.getbestadoreg());
					logger.debug(" objMov.getdCargo() " +objMov.getdCargo());
					
					
					int i =cs.executeUpdate();
					
					bOK=true;
					
			  
			}catch(SQLException e) {
								strError="Ficha: Error en el codigo <br> " +e.getMessage();
								logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
					  	}
					
					finally {
					  conexion.closeCallableStatement(cs);
					}
			
		}
	
		public synchronized void updatePersuasion(Connection pcon, Ficha_t objMov) throws SQLException { 
		CallableStatement cs = null;
			try {
					
							  
									cs = (CallableStatement)
									pcon.prepareCall("{ call PKG_FICHA.SP_UPD_PERSUASION (?,?,?,?,?,?,?,?,?,?,?) }");
									cs.setString(1,objMov.getcas());
									cs.setString(2,objMov.getayear());
									cs.setString(3,objMov.getcorrel());
									cs.setString(4,objMov.getIdPersua());
									cs.setString(5,objMov.getNombrePrestador());
									cs.setString(6,objMov.getFecharesp());
									cs.setString(7,objMov.getDetalleper());
									cs.setString(8,objMov.getcper());
									cs.setString(9,objMov.getCargoper());
									cs.setString(10,objMov.getcusumodi());
				cs.setString(11,objMov.getbestadoreg());
				
				int i =cs.executeUpdate();
				
				bOK=true;
				
		  
		}catch(SQLException e) {
							strError="Ficha: Error en el codigo <br> " +e.getMessage();
							logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
				  	}
				
				finally {
				  conexion.closeCallableStatement(cs);
				}
		
	}					
	
		
		public synchronized void eliminaPersuasion(Connection pcon, Ficha_t objMov) throws SQLException { 
			CallableStatement cs = null;
				try {
						
								  
										cs = (CallableStatement)
										pcon.prepareCall("{ call PKG_FICHA.SP_UPD_DEL_PERSUASION (?,?,?,?,?) }");
										cs.setString(1,objMov.getcas());
										cs.setString(2,objMov.getayear());
										cs.setString(3,objMov.getcorrel());
										cs.setString(4,objMov.getIdPersua());
										cs.setString(5,objMov.getcusumodi());
											
					
					logger.debug(" objMov.getcas() " +objMov.getcas());
					logger.debug(" objMov.getayear() " +objMov.getayear());
					logger.debug(" objMov.getcorrel() " +objMov.getcorrel());
					logger.debug(" objMov.getIdPersua() " +objMov.getIdPersua());
					logger.debug(" objMov.getcusumodi() " +objMov.getcusumodi());
					
					
					int i =cs.executeUpdate();
					
					bOK=true;
					
			  
			}catch(SQLException e) {
								strError="Ficha: Error en el codigo <br> " +e.getMessage();
								logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
					  	}
					
					finally {
					  conexion.closeCallableStatement(cs);
					}
			
		}		
		
		
		
	public void getConcluSeguimiento(Connection pcon, Ficha_t objLis) throws SQLException {
		Statement stmt   = null;
		ResultSet rs = null;
		StringBuffer strSql = new StringBuffer();
		strSql.append("select t.IDDATCONSEG, ( select c.dconclu from tconclusiones c where c.cconclu= t.CCONCLU), " +
				"( select c.daccseg  from taccseg c where c.idaccseg  = t.Caccseg),  t.DACCSEG , t.CACCSEG, t.CCONCLU, t.dcargo, dcquien ");		
		strSql.append("from TDATOSCONCLU t "); 
		strSql.append("where  "); 
		strSql.append(" t.ayear= '" + objLis.getayear() + "' "); 
		strSql.append("and t.cas= '" + objLis.getcas() + "' "); 
		strSql.append("and t.correl= '" + objLis.getcorrel() + "' "); 
		strSql.append(" AND t.bestadreg = '1' order by t.fusucrea desc "); 

		logger.debug("getConclusion Seguimiento: "+strSql);
		try {
			stmt = pcon.createStatement();
						 
			rs = stmt.executeQuery(strSql.toString());
			if (rs.next()) {
				String idConclu = rs.getString(1);
				String conclu = rs.getString(6);
				logger.debug("conclu "+conclu);
				objLis.setIdConclu(idConclu);
				objLis.setCconclu(conclu);
				//objLis.setbestadoreg(rs.getString(7));
				
				 ListaRegistros lstRegistros = new ListaRegistros();
				 lstRegistros.setConnection(pcon);
				 
				 lstRegistros.setNumRegistros(1500);
				 objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt("1"), strSql.toString(), false));
				 
				 objLis.setdtotal_reg(Integer.toString( objLis.getHshLista().size()  +  ((Integer.parseInt(objLis.getPag()) - 1)  * 15)));
				 
				 lstRegistros.setNumRegistros(objLis.getiRegxPag());
				 objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
				 
				 if(Integer.parseInt(objLis.getPag())!=1){
					 objLis.setdtotal_reg( Integer.toString( Integer.parseInt( objLis.getdtotal_reg() ) - ((Integer.parseInt(objLis.getPag()) - 1)  * 15) ));
				 }
				 
				 objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
				 objLis.setEmpty(true);
				bOK=true;
			}else{
				objLis.setEmpty(false);
			}
		  
		  
		}
		catch (Throwable exception) {
		  logger.debug("Error Throwable en getConcluSeguimiento clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
		  strError="No se pudo obtener informacion, se presentaron errores <br>";
		}
		finally {
			conexion.closeResultSet(rs);
			conexion.closeStatement(stmt);
		}

	}
	public synchronized void fichaInsertarConclusiones(Connection pcon, Ficha_t objMov) throws SQLException { 
		CallableStatement cs = null;
		try {

		  
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_FICHA.SP_INSERT_DATCONCLU (?,?,?,?,?,?,?,?,?,?) }");

				cs.setString(1,objMov.getcas());
				cs.setString(2,objMov.getayear());
				cs.setString(3,objMov.getcorrel());
				cs.setString(4,objMov.getCconclu());
				cs.setString(5,objMov.getcAccSeg());
				cs.setString(6,objMov.getdAccSeg());
				cs.setString(7,objMov.getcusucrea());
				cs.setString(8,objMov.getbestadoreg());
				cs.setString(9,objMov.getdCargo());
				cs.setString(10,objMov.getCquien());
				//cs.registerOutParameter(11,OracleTypes.VARCHAR);
				int i =cs.executeUpdate();
				/*
				if(i>0){
					String idConclu = cs.getString(11);
					objMov.setIdConclu(idConclu);
				}*/
				bOK=true;
				
				
		  
		}catch(SQLException e) {
							strError="Ficha: Error en el codigo <br> " +e.getMessage();
							logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
				  	}
				
				finally {
				  conexion.closeCallableStatement(cs);
				}
		
	}
	
	public void getTipoSolicitud(Connection pcon,Ficha_t objLis){
		StringBuffer query=new StringBuffer();
		Statement stmt=null;
		ResultSet rs=null;
		query.append("select t.cmso, t.bestadoreg, t.delasig ");
		query.append("from tdatosg t where t.cas="+objLis.getcas()+" and t.ayear="+objLis.getayear()+" and t.correl="+objLis.getcorrel());
		
		logger.debug("query para tdatosad: "+query.toString());
	  
		try{
		  stmt=pcon.createStatement();
		  rs=stmt.executeQuery(query.toString());
		  if(rs.next()){
				  /*objLis.setCfconclu(rs.getString(1));
				  objLis.setCconclu(rs.getString(2));
				  objLis.setcAccSeg(rs.getString(3));
				  objLis.setdAccSeg(rs.getString(4));*/
				  objLis.setcmso(rs.getString(1));
				  objLis.setbestadoreg(rs.getString(2));
				  objLis.setCdelegado(rs.getString(3));
				  bOK=true;
		  }else{
				  strError="Error al intentar recuperar de tdatosad...!!!!";
		  }
				  
		}catch(Exception e){
						  strError = "Se presento un error al buscar el registro " + e.getMessage();
						logger.debug("Error [" + getClass().getName() + "]. " + e.getMessage());
		}finally{
			  conexion.closeResultSet(rs);
			  conexion.closeStatement(stmt);
		}

		}
	public void getUltimoRegistro(Connection pcon, Ficha_t objLis) {
		StringBuffer query=new StringBuffer();
		Statement stmt=null;
		ResultSet rs=null;
		query.append("select t.cmso ");
		query.append("from tdatosg t where t.cas="+objLis.getcas()+" and t.ayear="+objLis.getayear()+" and t.correl="+objLis.getcorrel());
		
		//logger.debug("query para tdatosad: "+query.toString());
	  
		try{
		  stmt=pcon.createStatement();
		  rs=stmt.executeQuery(query.toString());
		  if(rs.next()){
				  /*objLis.setCfconclu(rs.getString(1));
				  objLis.setCconclu(rs.getString(2));
				  objLis.setcAccSeg(rs.getString(3));
				  objLis.setdAccSeg(rs.getString(4));*/
				  objLis.setcmso(rs.getString(1));
				  bOK=true;
		  }else{
				  strError="Error al intentar recuperar de tdatosad...!!!!";
		  }
				  
		}catch(Exception e){
						  strError = "Se presento un error al buscar el registro " + e.getMessage();
						logger.debug("Error [" + getClass().getName() + "]. " + e.getMessage());
		}finally{
			  conexion.closeResultSet(rs);
			  conexion.closeStatement(stmt);
		}

		}
	
	 public clases.ListFicha_t getLisFichaEssalud(Connection pcon, ListFicha_t objLis, String strPerfil)throws SQLException {
			StringBuffer strSql = new StringBuffer();
			try {
				strSql.append("select to_char(f.fusucrea, 'dd/mm/yyyy hh24:mi:ss') , f.dapepatrec  || ' ' || " +
						" f.dapematrec || ' ' || f.dnomrec,  decode(f.bestadoreg ,'1','ACTIVO','CERRADO'),  f.cas, f.ayear, f.correl , f.dapepat || ' ' || f.dapemat || ' ' || f.dnom  , f.fusucrea fecha ");
				
				if(strPerfil.equals(Constantes.PERFIL_ADMINISTRADOR) || strPerfil.equals(Constantes.PERFIL_COORDINADOR)){
					strSql.append(" from   tdatosg f, tusuarios u, tperfiles p "); 
					strSql.append(" where f.delasig = u.cusu  and u.cusunivacc = p.cperfil and  f.ctin = '07' and f.bestadoreg = '1' "); 
					strSql.append(" and p.cperfil = '11' "); 
					strSql.append(" union ");
					strSql.append(" select to_char(f.fusucrea, 'dd/mm/yyyy hh24:mi:ss'), f.dapepatrec  || ' ' ||  f.dapematrec || ' ' || f.dnomrec,  "); 
					strSql.append(" decode(f.bestadoreg ,'1','ACTIVO','CERRADO'),  f.cas, f.ayear, f.correl , f.dapepat || ' ' || f.dapemat || ' ' || f.dnom, f.fusucrea fecha ");   
					strSql.append(" from   tdatosg f where f.ctin = '07' and f.bestadoreg = '1' ");  
					strSql.append(" and f.delasig is null   ");  
					
				}else{
					strSql.append("from   tdatosg f, tusuarios u, tperfiles p where f.delasig = u.cusu and p.cperfil = '11' and u.cusunivacc = p.cperfil ");  
					strSql.append("and u.cusu = '"+objLis.getCodUsuario()+"' and f.bestadoreg = '1' ");
					
				}
				
				strSql.append(" order by fecha asc  ");
				

				logger.debug("sql getLisFichaEssalud ==> : "+strSql);
			  ListaRegistros lstRegistros = new ListaRegistros();
			  lstRegistros.setConnection(pcon);
			  lstRegistros.setNumRegistros(marco.Pagina.REG_X_PAG);
			  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
			  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),marco.Pagina.REG_X_PAG));
			  bOK=true;
			  
			}
			catch (Throwable exception) {
			  logger.debug("Error Throwable en getLisFichaPortal clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
			  strError="No se pudo obtener informacion, se presentaron errores <br>";
			}
		  return objLis;
		  }
	 
	 public clases.ListFicha_t getLisFichaPortal(Connection pcon, ListFicha_t objLis)throws SQLException {
			StringBuffer strSql = new StringBuffer();
			try {
				strSql.append("select to_char(f.fusucrea, 'dd/mm/yyyy hh24:mi:ss') , f.dapepatrec  || ' ' || " +
						" f.dapematrec || ' ' || f.dnomrec, (select e.defi from dbefi10 e where e.cefi = f.bestadoreg ) ,  f.cas, f.ayear, f.correl, f.dapepat || ' ' || f.dapemat || ' ' || f.dnom "); 
				strSql.append("from   tdatosg f where f.ctin = '06' and f.bestadoreg = '1' ");
				strSql.append(" and f.delasig is null  ");
				strSql.append(" order by f.fusucrea asc  ");
				

			//	logger.debug("lordinola: "+strSql);
			  ListaRegistros lstRegistros = new ListaRegistros();
			  lstRegistros.setConnection(pcon);
			  lstRegistros.setNumRegistros(marco.Pagina.REG_X_PAG);
			  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
			  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),marco.Pagina.REG_X_PAG));
			  bOK=true;
			  
			}
			catch (Throwable exception) {
			  logger.debug("Error Throwable en getLisFichaPortal clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
			  strError="No se pudo obtener informacion, se presentaron errores <br>";
			}
		  return objLis;
		  }
	 
		public synchronized void actualizarFicha(Connection pcon, Ficha_t objMov) throws SQLException { 
			CallableStatement cs = null;
			try {

			  
					cs = (CallableStatement)
					pcon.prepareCall("{ call PKG_FICHA.SP_UPD_STD_FICHA (?,?,?,?,?) }");

					cs.setString(1,objMov.getcas());
					cs.setString(2,objMov.getayear());
					cs.setString(3,objMov.getcorrel());
					cs.setString(4,objMov.getcusumodi());
					cs.setString(5,objMov.getbestadoreg());
					int i =cs.executeUpdate();
					
					bOK=true;
					
			  
			}catch(SQLException e) {
								strError="Ficha: Error en el codigo <br> " +e.getMessage();
								logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
					  	}
					
					finally {
					  conexion.closeCallableStatement(cs);
					}
			
		}

			public synchronized Map obtenerCondiciones(ListFicha_t filtro) throws SQLException { 
			StringBuffer stb = new StringBuffer();
			Map map = new HashMap();
			if(StringUtils.isNotEmpty(filtro.getcred())){
			stb.append(" and t.reclared = '"+ StringUtils.trim(filtro.getcred())+"' ");
			}
			
			if(StringUtils.isNotEmpty(filtro.getccas())){
			stb.append(" and t.reclacas = '"+ StringUtils.trim(filtro.getccas())+"' ");
			}
			
			if(StringUtils.isNotEmpty(filtro.getFechaInicio())){
			stb.append(" and t.fusucrea >= to_date('"+ StringUtils.trim(filtro.getFechaInicio())+"','dd/mm/yyyy') ");
			}
			
			if(StringUtils.isNotEmpty(filtro.getFechaFin())){
			stb.append(" and t.fusucrea <= to_date('"+ StringUtils.trim(filtro.getFechaFin())+"','dd/mm/yyyy')+1 ");
			}
					
			map.put("condicion", stb.toString());  
			logger.debug("condicion " +stb.toString());
			return map;
		}
			
			public clases.ListFicha_t getLisFicha(Connection pcon, clases.ListFicha_t objLis, Map map)throws SQLException {
				Statement stmt   = null;
				ResultSet rs = null;
				StringBuffer strSql = new StringBuffer();
				try {
					
					
					strSql.append("select t.ayear||'-'||t.cas||'-'||t.correl CODIGOFICHA, ");
					strSql.append("(select a.dtdi from dbtdi10 a where a.ctdi=t.ctdi) TIPODOCUMENTOAGRAVIADO, ");
					strSql.append("t.dficdid NUMERODOCUMENTOAGRAVIADO,  ");
					strSql.append("t.dapepat ApellidoPaternoAgraviado,  ");
					strSql.append("t.dapemat ApellidoMaternoAgraviado,  ");
					strSql.append("t.dnom NombreAgraviado,  ");
					strSql.append("t.nficeda Edad,  ");
					strSql.append("decode(t.ddicsex, 'M','MASCULINO','FEMENINO' ) SEXO, "); 
					strSql.append("t.dficdir DIRECCIONAGRAVIADO,  ");
					strSql.append("t.dfictel TELEFONOAGRAVIADO,  ");
					strSql.append("t.dficmail MAILAGRAVIADO,  ");
					strSql.append("t.dapepatrec ApellidoPaternoSolicitante, "); 
					strSql.append("t.dapematrec ApellidoMaternoSolicitante,  ");
					strSql.append("t.dnomrec NombreSolicitante, ");
					strSql.append("(select c.dd from (select distinct(substr(d.ubicubi,0,2)) cd, d.ubitdpt dd from cpccubit d ) c where c.cd=substr(t.cficubigeo,0,2) and rownum=1) Departamento, "); 
					strSql.append("(select e.dp from (select distinct(substr(f.ubicubi,0,4)) cp, f.ubitprv dp from cpccubit f )e where e.cp=substr(t.cficubigeo,0,4) and rownum=1) Provincia,  ");
					strSql.append("(select g.ubitdtr from cpccubit g where g.ubicubi=t.cficubigeo) Distrito,  ");
					strSql.append("(select h.dtpr from dbtpr10 h where h.btprestareg = '1' and h.ctpr=t.ctpr) TipoPrestacion, "); 
					strSql.append("(select i.delemen from tcod_gendets i where i.tcgc_ctabla = '005' and i.CELEMEN=t.cfictipseg) TipoSeguro, "); 
					strSql.append("(select j.dmso from dbmso10 j where j.bmsoestareg = '1' and j.cmso=t.cmso) TipoSolicitud, ");
					strSql.append("(select k.dtin from dbtin10 k where k.ctin=t.ctin) VIAINGRESO, ");
					strSql.append("(select a.dtdi from dbtdi10 a where a.ctdi=t.ctdirec) TIPODOCUMENTOSOLICITANTE, "); 
					strSql.append("t.dficdidrec NUMERODOCUMENTOSOLICITANTE, ");
					strSql.append("t.recladir DIRECCIONSOLICITANTE,  ");
					strSql.append("t.reclamail MAILSOLICITANTE, "); 
                    strSql.append("t.reclatel TELEFONOSOLICITANTE, ");
					strSql.append("t.reclacel CELULARSOLICITANTE,  ");
					strSql.append("(select d.dras from dmras10 d where t.reclared = d.cras) UNIDADORGANICA1, ");
					strSql.append("(select c.dcas from dmcas10 c, dmras10 d where d.cras  = c.cras  and c.ccas = t.reclacas) UNIDADORGANICA2, ");
					strSql.append("(select f.defi from dbefi10 f where f.cefi = t.bestadoreg ) ESTADO,   ");
					strSql.append("(select e.dmtem from dbtema e where e.cmtem = s.cmet and e.cmso = t.cmso ) tematica, ");
					strSql.append("s.grupocu ocupacion, ");
					strSql.append("s.dapepatqueja APELLIDOPATERNOQUEJADO,s.dapematqueja APELLIDOMATERNOQUEJADO,s.dnomqueja NOMBREQUEJADO, ");
					strSql.append("(select d.dras from tusuarios u, dmcas10 c, dmras10 d where u.ccas = c.ccas and c.cras = d.cras and u.cusu  = t.delasig) UnidadOrganica1Delegado, ");  
					strSql.append("(select c.dcas from tusuarios u, dmcas10 c, dmras10 d where u.ccas = c.ccas and c.cras = d.cras and u.cusu  = t.delasig) UnidadOrganica2Delegado, ");
					strSql.append("t.delasig DELEGADO,  ");
					strSql.append("(select f.arehosdes from dmare10 f where f.enlservhoscod=s.enlservhoscod and rownum=1)AREA, ");
					strSql.append("(select g.servhosdes from dmare10 g where g.enlservhoscod=s.enlservhoscod and rownum=1)SERVICIO, ");
					strSql.append("t.cusucrea USUARIOCREADOR,   ");
					strSql.append("to_char(t.fusucrea, 'dd/mm/yyyy hh24:mi:ss') FECHAREGISTRO,  ");
					strSql.append("t.cusumodi USUARIOMODIFICADOR, ");
					strSql.append("to_char(t.fusumodi,'dd/mm/yyyy hh24:mi:ss') FECHAMODIFICACION, "); 
					strSql.append("t.dfichec DESCRIPCIONHECHOS ");
					strSql.append("from tdatosg t, tdatosad s where t.cas=s.cas and t.ayear=s.ayear and t.correl=s.correl		 ");
					if(!map.isEmpty()){
					strSql.append(MapUtils.getString(map, "condicion", ""));
					}
					strSql.append("order by t.correl desc "); 

				  logger.debug(strSql);
				  ListaRegistros lstRegistros = new ListaRegistros();
				  lstRegistros.setConnection(pcon);
				  
				  lstRegistros.setNumRegistros(objLis.getiRegxPag());
				  
				  
				  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
				  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
				  logger.debug("Nro "+objLis.getHshLista().size());
				  bOK = true;
				}
				catch (Throwable exception) {
				  logger.debug("Error Throwable en getLisFicha clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
				  strError="No se pudo obtener informacion, se presentaron errores <br>";
				}finally {
					  conexion.closeResultSet(rs);
					  conexion.closeStatement(stmt);
					}
			  return objLis;
			  }
			
			public List getListaReporteGeneral(Connection con, ListFicha_t datos){
				ResultSet rs = null;
				CallableStatement cs = null;
				String sql = "{call pkg_ficha.sp_obtener_reporte(?,?,?,?,?)}";
				
				List lista = new ArrayList();
				ReporteGeneralBean reporte = null;
				
				try {
					cs = (CallableStatement)con.prepareCall(sql);
					cs.setString(1, datos.getcred());
					cs.setString(2, datos.getccas());
					cs.setString(3, datos.getFechaInicio());
					cs.setString(4, datos.getFechaFin());
					cs.registerOutParameter(5, OracleTypes.CURSOR);
					
					cs.executeUpdate();
					
					bOK = true;
					
					rs = (ResultSet) cs.getObject(5);
					ResultSetDynaClass rst = new ResultSetDynaClass(rs);
					
					Iterator it = (rst).iterator();
					while (it.hasNext()) {
						reporte = new ReporteGeneralBean();
						DynaBean reportebean = new WrapDynaBean(reporte);
						DynaBean row = (DynaBean) it.next();
						PropertyUtils.copyProperties(reportebean, row);
						lista.add(reporte);						
					}
					//logger.debug("listareporte "+lista.size());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					conexion.closeResultSet(rs);
					  conexion.closeCallableStatement(cs);
				}
				
				return lista;
			}
			
			public Ficha_t getListFichaDiaria(Connection pcon, Ficha_t objLis)throws SQLException {
				Statement stmt   = null;
				ResultSet rs = null;
				StringBuffer strSql = new StringBuffer();
				try {
				  strSql.append("select t.ayear c1, t.cas c2, t.correl c3, to_char(t.fusucrea,'dd/mm/yyyy') c4, t.dapepat c5, ");
				  strSql.append("t.dapemat c6, t.dnom c7, t.dapepatrec c8, t.dapematrec c9, t.dnomrec c10, t.cusucrea c11, ");
				  strSql.append(" (select e.defi from dbefi10 e where e.cefi = t.bestadoreg) c12 ");
				  strSql.append(" from tdatosg t where 1= 1 ");
				  //filtros
				  
				  if(StringUtils.isNotEmpty(objLis.getCdelegado())){
						strSql.append(" and t.delasig = '"+objLis.getCdelegado()+"' ");
					}
				  
				  
					if(StringUtils.isNotEmpty(objLis.getdda())){
						strSql.append(" and to_char(t.fusucrea,'dd/mm/yyyy') = '"+ objLis.getdda() +"'");
					}
				  
				  strSql.append("order by t.fusucrea desc ");
				  logger.debug("lista diaria: "+strSql);		  
				  stmt = pcon.createStatement();
				  rs = stmt.executeQuery(strSql.toString());
				  
				  if (rs.next()) {
					  bOK=true;
				  }
				  else{
					  strError = "Según los criterios seleccionados, no hay información";
				  }
				  
				  if(bOK){
					  
					  ListaRegistros lstRegistros = new ListaRegistros();
					  lstRegistros.setConnection(pcon);
					  		 
					  lstRegistros.setNumRegistros(1500);
					  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt("1"), strSql.toString(), false));
					  
					  objLis.setdtotal_reg(Integer.toString( objLis.getHshLista().size()  +  ((Integer.parseInt(objLis.getPag()) - 1)  * 15)));
					  
					  lstRegistros.setNumRegistros(objLis.getiRegxPag());
					  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
					  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
					  
					  if(Integer.parseInt(objLis.getPag())!=1){
							 objLis.setdtotal_reg( Integer.toString( Integer.parseInt( objLis.getdtotal_reg() ) - ((Integer.parseInt(objLis.getPag()) - 1)  * 15) ));
						 }
				  }
				  
				}
				catch (Throwable exception) {
				  logger.debug("Error Throwable en getLisFichaDiaria clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
				  strError="No se pudo obtener informacion, se presentaron errores <br>";
				}finally {
					conexion.closeResultSet(rs);
					conexion.closeStatement(stmt);
				}
			  return objLis;
			}
/*
			public List getReporteListFichaDiaria(Connection pcon, String fecha, String unidadOrganica1, String unidadOrganica2, String cdelegado)throws SQLException {
				Statement stmt   = null;
				ResultSet rs = null;
				StringBuffer strSql = new StringBuffer();
				List lista = new ArrayList();
				try {
				  strSql.append("select t.ayear || ' - ' || t.cas || ' - ' || t.correl codigo, to_char(t.fusucrea,'dd/mm/yyyy') fecharegistro,  ");
				  strSql.append("t.dapepat || ' ' || t.dapemat || ', ' || t.dnom solicitante, t.dapepatrec || ' ' || t.dapematrec || ', ' || t.dnomrec involucrado, ");
				  strSql.append(" (select e.defi from dbefi10 e where e.cefi = t.bestadoreg) estado ");
				  strSql.append(" from tdatosg t, tusuarios u where 1= 1 and t.delasig = u.cusu and u.cusunivacc = '08' and u.busuestareg = '1' ");
				  //filtros
				  if(StringUtils.isNotEmpty(unidadOrganica2)){
					  strSql.append(" and u.ccas = '"+unidadOrganica2 +"' ");  
				  }else{
					  strSql.append(" and u.ccas = (select c.ccas from dmcas10 c where  c.cras = '"+ unidadOrganica1+"' )");
				  }
					if(StringUtils.isNotEmpty(fecha)){
						strSql.append(" and to_char(t.fusucrea,'dd/mm/yyyy') = '"+ fecha +"'");
					}
					
					if(StringUtils.isNotEmpty(cdelegado)){
						  
						strSql.append(" and t.delasig = '"+cdelegado+"' ");
					}
				  
				  strSql.append("order by t.fusucrea desc ");
				  logger.debug("lista diaria: "+strSql);		  
				  stmt = pcon.createStatement();
				  rs = stmt.executeQuery(strSql.toString());
				  
				  ResultSetDynaClass rst = new ResultSetDynaClass(rs);
				  ReporteFichaPorDelegadoBean reporte = null;
					Iterator it = (rst).iterator();
					while (it.hasNext()) {
						reporte = new ReporteFichaPorDelegadoBean();
						DynaBean reportebean = new WrapDynaBean(reporte);
						DynaBean row = (DynaBean) it.next();
						PropertyUtils.copyProperties(reportebean, row);
						lista.add(reporte);						
					}
				  
				  
				  
				}
				catch (Throwable exception) {
				  logger.debug("Error Throwable en getLisFichaDiaria clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
				  strError="No se pudo obtener informacion, se presentaron errores <br>";
				}finally {
					conexion.closeResultSet(rs);
					conexion.closeStatement(stmt);
				}
			  return lista;
			}
			
			public List getReporteFichaDiaria(Connection pcon,  String fecha,String cdelegado)throws SQLException {
				Statement stmt   = null;
				ResultSet rs = null;
				StringBuffer strSql = new StringBuffer();
				List lista = new ArrayList();
				try {
				  				  
				  strSql.append("select t.ayear || ' - ' || t.cas || ' - ' || t.correl codigo, to_char(t.fusucrea,'dd/mm/yyyy') fecharegistro,  ");
				  strSql.append("t.dapepat || ' ' || t.dapemat || ', ' || t.dnom solicitante, t.dapepatrec || ' ' || t.dapematrec || ', ' || t.dnomrec involucrado, ");
				  strSql.append(" (select e.defi from dbefi10 e where e.cefi = t.bestadoreg) estado ");
				  strSql.append(" from tdatosg t where 1= 1 ");
				  
				  //filtros
				  
				  if(StringUtils.isNotEmpty(cdelegado)){
						strSql.append(" and t.delasig = '"+cdelegado+"' ");
					}
				  
				  
					if(StringUtils.isNotEmpty(fecha)){
						strSql.append(" and to_char(t.fusucrea,'dd/mm/yyyy') = '"+ fecha +"'");
					}
				  
				  strSql.append("order by t.fusucrea desc ");
				  logger.debug("lista diaria: "+strSql);		  
				  stmt = pcon.createStatement();
				  rs = stmt.executeQuery(strSql.toString());
				  
				  ResultSetDynaClass rst = new ResultSetDynaClass(rs);
				  ReporteFichaPorDelegadoBean reporte = null;
					Iterator it = (rst).iterator();
					while (it.hasNext()) {
						reporte = new ReporteFichaPorDelegadoBean();
						DynaBean reportebean = new WrapDynaBean(reporte);
						DynaBean row = (DynaBean) it.next();
						PropertyUtils.copyProperties(reportebean, row);
						lista.add(reporte);						
					}
				  
				}
				catch (Throwable exception) {
				  logger.debug("Error Throwable en getLisFichaDiaria clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
				  strError="No se pudo obtener informacion, se presentaron errores <br>";
				}finally {
					conexion.closeResultSet(rs);
					conexion.closeStatement(stmt);
				}
			  return lista;
			}*/
			
			public Map getReporteDiario(Connection pcon, String fecha, String unidadOrganica1, String unidadOrganica2, String cdelegado, boolean isFiltroCompleto)throws SQLException {
				
				StringBuffer strSql = new StringBuffer();
				
			    Map map = new HashMap();
				
			    if(isFiltroCompleto){
				  strSql.append(" , tusuarios u where 1= 1 and t.delasig = u.cusu and u.cusunivacc = '08' and u.busuestareg = '1' ");
				  //filtros
				  if(StringUtils.isNotEmpty(unidadOrganica2)){
					  strSql.append(" and u.ccas = '"+unidadOrganica2 +"' ");  
				  }else{
					  strSql.append(" and u.ccas = (select c.ccas from dmcas10 c where  c.cras = '"+ unidadOrganica1+"' )");
				  }
			    }else{
			    	strSql.append(" where 1= 1 ");
			    }
			    
				if(StringUtils.isNotEmpty(fecha)){
					strSql.append(" and to_char(t.fusucrea,'dd/mm/yyyy') = '"+ fecha +"'");
				}
					
				if(StringUtils.isNotEmpty(cdelegado)){
					strSql.append(" and t.delasig = '"+cdelegado+"' ");
				}
				  
				  strSql.append("order by t.fusucrea desc ");
				  logger.debug("lista diaria: "+strSql);		  
				  map.put("condicion", strSql.toString());
				
				
			  return map;
			}
			
			public Ficha_t getListSeguimientoFicha(Connection pcon, Ficha_t objLis)throws SQLException {
				Statement stmt   = null;
				ResultSet rs = null;
				StringBuffer strSql = new StringBuffer();
				try {
				  strSql.append("select t.year, t.cas,  t.correl, t.delasig, t.fecasig from histdelasig t ");
				  strSql.append(" where 1= 1 ");
				  if(StringUtils.isNotEmpty(objLis.getccas())){
				  strSql.append(" and t.cas = "+objLis.getccas());
				  }
				  if(StringUtils.isNotEmpty(objLis.getayear())){
				  strSql.append(" and  t.year="+objLis.getayear());
				  }
				  
				  if(StringUtils.isNotEmpty(objLis.getcorrel())){
					  strSql.append(" and  t.correl="+objLis.getcorrel());
					  }
				  
				  strSql.append("order by t.fecasig desc ");
				  logger.debug("lista seguimiento de expediente: "+strSql);		  
				  stmt = pcon.createStatement();
				  rs = stmt.executeQuery(strSql.toString());
				  
				  if (rs.next()) {
					  bOK=true;
				  }
				  else{
					  strError = "Según los criterios seleccionados, no hay información";
				  }
				  
				  if(bOK){
					  
					  ListaRegistros lstRegistros = new ListaRegistros();
					  lstRegistros.setConnection(pcon);
					  		 
					  lstRegistros.setNumRegistros(1500);
					  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt("1"), strSql.toString(), false));
					  
					  objLis.setdtotal_reg(Integer.toString( objLis.getHshLista().size()  +  ((Integer.parseInt(objLis.getPag()) - 1)  * 15)));
					  
					  lstRegistros.setNumRegistros(objLis.getiRegxPag());
					  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
					  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
					  
					  if(Integer.parseInt(objLis.getPag())!=1){
							 objLis.setdtotal_reg( Integer.toString( Integer.parseInt( objLis.getdtotal_reg() ) - ((Integer.parseInt(objLis.getPag()) - 1)  * 15) ));
						 }
				  }
				  
				}
				catch (Exception exception) {
				  logger.debug("Error Throwable en getLisFichaDiaria clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
				  strError="No se pudo obtener informacion, se presentaron errores <br>";
				}finally {
					conexion.closeResultSet(rs);
					conexion.closeStatement(stmt);
				}
			  return objLis;
			}
			
			//recuperar tpersuas
			  public Ficha_t getFdatosPersuasion(Connection pcon,Ficha_t objLis){
				StringBuffer strSql=new StringBuffer();
				Statement stmt=null;
				ResultSet rs=null;
				strSql.append("select t.idpersua, t.nomper, t.accper, to_char(t.fechper,'dd/mm/yyyy'), t.cargper, t.detaper,(select c.dper from taccpersuas c where c.cper = t.accper) ");
				strSql.append("from tpersuas t where t.cas="+objLis.getcas()+" and t.ayear="+objLis.getayear()+" and t.correl="+objLis.getcorrel());
				strSql.append(" and t.cmso ='" +objLis.getcmso()+"' and t.cconclu = '"+objLis.getCconclu()+"' and t.idconclu="+Integer.parseInt(objLis.getIdConclu())+" and t.bestadoreg= '1' ");
				
				logger.debug("query para tpersuasion: "+strSql.toString());
			  
				try{
					//strSql.append("order by t.fecasig desc ");
					 // logger.debug("lista seguimiento de expediente: "+strSql);		  
					  stmt = pcon.createStatement();
					  rs = stmt.executeQuery(strSql.toString());
					  
					  if (rs.next()) {
						  bOK=true;
					  }
					  else{
						  strError = "Según los criterios seleccionados, no hay información";
					  }
					  
					  if(bOK){
						  
						  ListaRegistros lstRegistros = new ListaRegistros();
						  lstRegistros.setConnection(pcon);
						  		 
						  lstRegistros.setNumRegistros(1500);
						  objLis.setHshLista1(lstRegistros.getListaPag(Integer.parseInt("1"), strSql.toString(), false));
						  
						  objLis.setdtotal_reg(Integer.toString( objLis.getHshLista1().size()  +  ((Integer.parseInt(objLis.getPag()) - 1)  * 15)));
						  
						  lstRegistros.setNumRegistros(objLis.getiRegxPag());
						  objLis.setHshLista1(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
						  objLis.setPaginacion(new Paginacion (objLis.getHshLista1().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
						  
						  if(Integer.parseInt(objLis.getPag())!=1){
								 objLis.setdtotal_reg( Integer.toString( Integer.parseInt( objLis.getdtotal_reg() ) - ((Integer.parseInt(objLis.getPag()) - 1)  * 15) ));
							 }
					  }
					  
						
				}catch(Exception e){
								  strError = "Se presento un error al buscar el registro " + e.getMessage();
								logger.debug("Error [" + getClass().getName() + "]. " + e.getMessage());
				}finally{
					  conexion.closeResultSet(rs);
					  conexion.closeStatement(stmt);
				}
			  //recupero los factores contribuyentes...
			  
				return objLis;
				}
			public void grabarPersuasion(Connection con, Ficha_t ficha)  throws SQLException {
				  CallableStatement cs = null;
				  try {
							cs = (CallableStatement)
						  con.prepareCall("{ call PKG_FICHA.SP_INSERT_PERSUA (?,?,?,?,?,?,?,?,?,?,?,?)}");
						  cs.setString(1,ficha.getayear());
						  cs.setString(2,ficha.getcas());
						  cs.setString(3,ficha.getcorrel());
							cs.setString(4,ficha.getcmso());
							cs.setString(5,ficha.getCconclu());
							cs.setString(6,ficha.getNombrePrestador());
							cs.setString(7,ficha.getcper());
							cs.setString(8,ficha.getFecharesp());
							cs.setString(9,ficha.getCargoper());
							cs.setString(10,ficha.getDetalleper());
							cs.setString(11,ficha.getcusucrea());
							cs.setString(12,ficha.getIdConclu());//SETEAR VARIABLE IDCONCLU
							cs.executeUpdate();		
							
							bOK=true;
								 
					}catch(SQLException e) {
					strError="Ficha: No se pudo grabar en la tabla persuasion, se presentaron errores <br> " +e.getMessage();
					logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
				  } 
				  finally {
					conexion.closeCallableStatement(cs);
				  }
			}
			
			
			//recuperar tficheros
			  public Ficha_t getFdatosFicheros(Connection pcon,Ficha_t objLis){
				StringBuffer strSql=new StringBuffer();
				Statement stmt=null;
				ResultSet rs=null;
				strSql.append("select t.cas, t.ayear, t.correl, t.dfnomb, t.bestadoreg, to_char(t.fusucrea,'dd/mm/yyyy'), t.cusucrea, '0', t.idarchivo, t.fusumodi, t.cusumodi, t.idgestion  ");
				strSql.append("from tficheros t where t.cas="+objLis.getcas()+" and t.ayear="+objLis.getayear()+" and t.correl="+objLis.getcorrel()+"  and  t.bestadoreg='1' order by  t.idarchivo desc");
			
				
				logger.debug("query para getFdatosFicheros: "+strSql.toString());
			  
				try{
					//strSql.append("order by t.fecasig desc ");
					 // logger.debug("lista seguimiento de expediente: "+strSql);		  
					  stmt = pcon.createStatement();
					  rs = stmt.executeQuery(strSql.toString());
					  
					  if (rs.next()) {
						  bOK=true;
					  }
					  else{
						  strError = "Según los criterios seleccionados, no hay información";
					  }
					  
					  if(bOK){
						  
						  ListaRegistros lstRegistros = new ListaRegistros();
						  lstRegistros.setConnection(pcon);
						  		 
						  lstRegistros.setNumRegistros(1500);
						  objLis.setHshLista2(lstRegistros.getListaPag(Integer.parseInt("1"), strSql.toString(), false));
						  
						  objLis.setdtotal_reg(Integer.toString( objLis.getHshLista2().size()  +  ((Integer.parseInt(objLis.getPag()) - 1)  * 15)));
						  
						  lstRegistros.setNumRegistros(objLis.getiRegxPag());
						  objLis.setHshLista2(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
						  objLis.setPaginacion(new Paginacion (objLis.getHshLista2().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
						  
						  if(Integer.parseInt(objLis.getPag())!=1){
								 objLis.setdtotal_reg( Integer.toString( Integer.parseInt( objLis.getdtotal_reg() ) - ((Integer.parseInt(objLis.getPag()) - 1)  * 15) ));
							 }
					  }
					  
						
				}catch(Exception e){
								  strError = "Se presento un error al buscar el registro " + e.getMessage();
								logger.debug("Error [" + getClass().getName() + "]. " + e.getMessage());
				}finally{
					  conexion.closeResultSet(rs);
					  conexion.closeStatement(stmt);
				}
			  //recupero los factores contribuyentes...
			  
				return objLis;
				}
			  
			  
				//recuperar tficheros
			  public Ficha_t actualizarEstadoUploadsdd(Connection pcon,Ficha_t objLis){
				StringBuffer strSql=new StringBuffer();
				Statement stmt=null;
				ResultSet rs=null;
				strSql.append("Update   tficheros  set   bestadoreg='0'   where   idarchivo="+objLis.getIdArchivo());
				//strSql.append(" and  t.idgestion="+Integer.parseInt(objLis.getIdGestion()));
				
				logger.debug("query para tficheros: "+strSql.toString());
			  
				try{
					//strSql.append("order by t.fecasig desc ");
					 // logger.debug("lista seguimiento de expediente: "+strSql);		  
					  stmt = pcon.createStatement();
					  rs = stmt.executeQuery(strSql.toString());
					  
					  if (rs.next()) {
						  bOK=true;
					  }
					  else{
						  strError = "Según los criterios seleccionados, no hay información";
					  }
					  
					  if(bOK){
						  
						  ListaRegistros lstRegistros = new ListaRegistros();
						  lstRegistros.setConnection(pcon);
						  		 
						  lstRegistros.setNumRegistros(1500);
						  objLis.setHshLista2(lstRegistros.getListaPag(Integer.parseInt("1"), strSql.toString(), false));
						  
						  objLis.setdtotal_reg(Integer.toString( objLis.getHshLista2().size()  +  ((Integer.parseInt(objLis.getPag()) - 1)  * 15)));
						  
						  lstRegistros.setNumRegistros(objLis.getiRegxPag());
						  objLis.setHshLista2(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
						  objLis.setPaginacion(new Paginacion (objLis.getHshLista2().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
						  
						  if(Integer.parseInt(objLis.getPag())!=1){
								 objLis.setdtotal_reg( Integer.toString( Integer.parseInt( objLis.getdtotal_reg() ) - ((Integer.parseInt(objLis.getPag()) - 1)  * 15) ));
							 }
					  }
					  
						
				}catch(Exception e){
								  strError = "Se presento un error al buscar el registro " + e.getMessage();
								logger.debug("Error [" + getClass().getName() + "]. " + e.getMessage());
				}finally{
					  conexion.closeResultSet(rs);
					  conexion.closeStatement(stmt);
				}
			  //recupero los factores contribuyentes...
			  
				return objLis;
				}
			  
			  
			//actualiza fichero
			  public synchronized void actualizarEstadoUploads(Connection pcon, Ficha_t objMov) throws SQLException { 
					CallableStatement cs = null;
					try {

						//sp_Upd_datosUploadFicheros
							cs = (CallableStatement)
							pcon.prepareCall("{ call PKG_FICHA.SP_UPD_DATOSUPLOADFICHEROS (?,?,?,?,?) }");
							cs.setString(1,objMov.getcas());
							cs.setString(2,objMov.getayear());
							cs.setString(3,objMov.getcorrel());
							cs.setString(4,objMov.getIdArchivo());
							cs.setString(5,objMov.getcusumodi());
							
							
							logger.debug(" objMov.getcas() " +objMov.getcas());
							logger.debug(" objMov.getayear() " +objMov.getayear());
							logger.debug(" objMov.getcorrel() " +objMov.getcorrel());
							logger.debug(" objMov.getIdArchivo() " +objMov.getIdArchivo());
							logger.debug(" objMov.getcusumodi() " +objMov.getcusumodi());
							
							
							int i =cs.executeUpdate();
							
							bOK=true;
							
					  
					}catch(SQLException e) {
										strError="Ficha: Error en el codigo <br> " +e.getMessage();
										logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
							  	}
							
							finally {
							  conexion.closeCallableStatement(cs);
							}
					
				}
	
			public void insertLibRecla(Connection con, Ficha_t obj, String caso)  throws SQLException { 
				CallableStatement cs = null;
				try {

					if(StringUtils.equals(caso, Constantes.OPCION_INSERTAR)){

						cs = (CallableStatement)
						con.prepareCall("{ call PKG_RECLAMO.SP_INSERT_LIBRECLA (?,?,?,?,?,?,?,?,?,?,?,?) }");
						
						cs.setString(1,obj.getdficdid());
						cs.setString(2,obj.getdnom());
						cs.setString(3,obj.getdapepat());
						cs.setString(4,obj.getdapemat());
						cs.setString(5,obj.getdficdir());
						cs.setString(6,obj.getdficmail());
						cs.setString(7,obj.getdfictel());
						cs.setString(8,obj.getcred());
						cs.setString(9,obj.getccas());
						cs.setString(10,obj.getdfichec());
						cs.registerOutParameter(11,OracleTypes.VARCHAR);
						cs.registerOutParameter(12,OracleTypes.VARCHAR);
						
						logger.debug(" obj.getdficdid() " +obj.getdficdid());
						logger.debug(" obj.getdnom() " +obj.getdnom());
						logger.debug(" obj.getdapepat() " +obj.getdapepat());
						logger.debug(" obj.getdapemat() " +obj.getdapemat());
						logger.debug(" obj.getdficdir() " +obj.getdficdir());
						logger.debug(" obj.getdficmail() " +obj.getdficmail());
						logger.debug(" obj.getdfictel() " +obj.getdfictel());
						logger.debug(" obj.getcred() " +obj.getcred());
						logger.debug(" obj.getccas() " +obj.getccas());
						logger.debug(" obj.getdfichec() " +obj.getdfichec());
						
						int i =cs.executeUpdate();
						
						String idRecla =    cs.getString(11);
						String fechaRecla = cs.getString(12);
						obj.setIdRecla(idRecla);
						obj.setFechaRecla(fechaRecla);
						
						if ( i>0){
							bOK=true;
						}
					}else if(StringUtils.equals(caso, Constantes.OPCION_MODIFICAR)){
						
					}
						
				  
				}catch(SQLException e) {
									strError="Libro de reclamaciones: Error en el codigo <br> " +e.getMessage();
									logger.debug("Error En grabar libro de reclamaciones [" + getClass().getName() + "]. " + e.getMessage());
						  	}
						
						finally {
						  conexion.closeCallableStatement(cs);
						}
				
			}
			public synchronized void deleteDatosDef(Connection pcon, Ficha_t objMov) throws SQLException { 
				CallableStatement cs = null;
				try {

				  
						cs = (CallableStatement)
						pcon.prepareCall("{ call PKG_FICHA.SP_DEL_DATDEF (?,?,?,?,?,?,?,?,?,?,?) }");
						cs.setString(1,objMov.getcas());
						cs.setString(2,objMov.getayear());
						cs.setString(3,objMov.getcorrel());
						cs.setString(4,objMov.getIddatdef());
						cs.setString(5,objMov.getcusumodi());
						cs.setString(6,objMov.getbestadoreg());
						
						logger.debug(" objMov.getcas() " +objMov.getcas());
						logger.debug(" objMov.getayear() " +objMov.getayear());
						logger.debug(" objMov.getcorrel() " +objMov.getcorrel());
						logger.debug(" objMov.getIddatdef() " +objMov.getIddatdef());
						logger.debug(" objMov.getCquien() " +objMov.getCquien());
						logger.debug(" objMov.getCfecha() " +objMov.getCfecha());
						logger.debug(" objMov.getGestor() " +objMov.getGestor());
						logger.debug(" objMov.getCaccreal() " +objMov.getCaccreal());
						logger.debug(" objMov.getbestadoreg() " +objMov.getbestadoreg());
						logger.debug(" objMov.getdCargo() " +objMov.getdCargo());
						
						
						int i =cs.executeUpdate();
						
						bOK=true;
						
				  
				}catch(SQLException e) {
									strError="Ficha: Error en el codigo <br> " +e.getMessage();
									logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
						  	}
						
						finally {
						  conexion.closeCallableStatement(cs);
						}
				
			}
			
			public clases.ListFicha_t getLibRecla(Connection pcon, ListFicha_t objLis)throws SQLException {
				StringBuffer strSql = new StringBuffer();
				try {
					strSql.append("select to_char(f.fusucrea, 'dd/mm/yyyy hh24:mi:ss') , f.dapepatrec  || ' ' || f.dapematrec || ' ' || f.dnomrec, " );
					strSql.append(" (select e.defi from dbefi10 e where e.cefi = f.bestadoreg ) ,  f.cas, f.ayear, f.correl, f.dapepat || ' ' || f.dapemat || ' ' || f.dnom , ");
					strSql.append(" decode(ORIRECLA, '1','VIRTUAL','0','PRESENCIAL','PRESENCIAL') TIPORECLAMO "); 
					strSql.append(" from   tdatosg f where f.ctin = '10' and f.bestadoreg = '1' ");
					strSql.append(" and f.delasig is null  ");
					strSql.append(" order by f.fusucrea asc  ");
					

				  logger.debug("getLibRecla sql ===>>> "+strSql);
				  ListaRegistros lstRegistros = new ListaRegistros();
				  lstRegistros.setConnection(pcon);
				  lstRegistros.setNumRegistros(marco.Pagina.REG_X_PAG);
				  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
				  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),marco.Pagina.REG_X_PAG));
				  bOK=true;
				  
				}
				catch (Throwable exception) {
				  logger.debug("Error Throwable en getLisFichaPortal clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
				  strError="No se pudo obtener informacion, se presentaron errores <br>";
				}
			  return objLis;
			  }
  }