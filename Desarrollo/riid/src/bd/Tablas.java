
package bd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import util.Constantes;
import util.Paginacion;
import beans.ListaRegistros;
import clases.Tablas_t;


public class Tablas {
	private static Logger logger = Logger.getLogger(Tablas.class);
  private boolean bOK = false;
  private String strError = "";

  private static Tablas instance = new Tablas();
	
	public static Tablas getInstance() {
		return instance;
	}
  
  public boolean isOK() {
	return bOK;
  }
  public String getStringError() {
	return strError;
  }

  //actualizado 12/08/2014
  public synchronized void AccionesUpdate(Connection pcon, Tablas_t objMov, String tPerfil, String tUpd) throws SQLException {
	CallableStatement cs = null;
		try {
			if(StringUtils.equals(tUpd,Constantes.OPCION_INSERTAR)){ // Insert
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_accreal_insert (?,?) }");
				cs.setString(1,objMov.getstrcodaccion());
				cs.setString(2,objMov.getstrdesaccion());
		  }
			if(StringUtils.equals(tUpd,Constantes.OPCION_MODIFICAR)){ // Update
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_accreal_Update (?,?,?) }");
				cs.setString(1,objMov.getstrcodaccion());
				cs.setString(2,objMov.getstrdesaccion());
				cs.setString(3,objMov.getstrbestadoreg());
		  }
			
			cs.executeUpdate();
		  
		  bOK=true;
		}
		catch(SQLException e) {
			strError="Ficha AccionesUpdate: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
			logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
		} 
		finally {
			conexion.closeCallableStatement(cs);
		}
  }

  public Tablas_t getLisAccionesRealizada(Connection pcon, clases.Tablas_t objLis)throws SQLException {
	Statement stmt   = null;
	ResultSet rs = null;
	StringBuffer strSql = new StringBuffer();
	try {

		strSql.append("select t.idaccreal, t.descaccreal, decode(t.estareg,'1','ACTIVO','INACTIVO') from taccreal t "); 
		if(objLis.getstrcodaccion().compareTo("")!=0 && objLis.getstrdesaccion().compareTo("")==0){
			strSql.append("where t.idaccreal like '" + objLis.getstrcodaccion() + "%' ");
		}
		if(objLis.getstrcodaccion().compareTo("")==0 && objLis.getstrdesaccion().compareTo("")!=0){
			strSql.append("where t.idaccreal like '" + objLis.getstrcodaccion() + "%' and  t.descaccreal like '" + objLis.getstrdesaccion() + "%' "); 
		}
		if(objLis.getstrcodaccion().compareTo("")!=0 && objLis.getstrdesaccion().compareTo("")!=0){
			strSql.append("where t.idaccreal like '" + objLis.getstrcodaccion() + "%' and  t.descaccreal like '" + objLis.getstrdesaccion() + "%' ");
		}
		strSql.append("order by t.idaccreal asc ");

	  logger.debug(strSql);
	  ListaRegistros lstRegistros = new ListaRegistros();
	  lstRegistros.setConnection(pcon);
	  lstRegistros.setNumRegistros(objLis.getiRegxPag());
	  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
	  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
	  stmt = pcon.createStatement();
	  rs = stmt.executeQuery(strSql.toString());
	  if (rs.next()) {
			objLis.setstrcodaccion(rs.getString(1));
			objLis.setstrdesaccion(rs.getString(2));
			objLis.setstrbestadoreg(rs.getString(3));
		  bOK=true;
	  }
	  else{
		  strError = "Según los criterios seleccionados, no hay información";
	  }
	  
	}
	catch (Throwable exception) {
	  logger.debug("Error Throwable en getLisAcciones clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
	  strError="No se pudo obtener informacion, se presentaron errores <br>";
	}finally {
		conexion.closeStatement(stmt);
		conexion.closeResultSet(rs);
	}
  return objLis;
  }

  public Tablas_t getLisUsuarios(Connection pcon, clases.Tablas_t objLis)throws SQLException {
	Statement stmt   = null;
	ResultSet rs = null;
	StringBuffer strSql = new StringBuffer();
	try {
		strSql.append("select nvl(t.cusu,''), nvl(t.cusucla,''), nvl(t.dapepatusu,''), nvl(t.dusumail,''), t.cusunivacc, t.ccas, "); 
		strSql.append("decode(t.busuestareg,'1','ACTIVO','INACTIVO'), c.dperfil, ");
		strSql.append("a.dcascor, b.cras, decode(t.bworkflow,'1','SI','NO'), nvl(t.dapematusu,''), nvl(t.dnomusu,'') ");
		strSql.append("from tusuarios t, dmcas10 a, dmras10 b, tperfiles c ");
		strSql.append("where t.ccas = a.ccas(+) and a.cras = b.cras(+) and t.cusunivacc = c.cperfil "); 
		if(objLis.getUsuUsuario().compareTo("")!=0 && objLis.getUsuDescripcion().compareTo("")==0){
			strSql.append("and t.cusu like '" + objLis.getUsuUsuario() + "%' ");
		}
		if(objLis.getUsuUsuario().compareTo("")==0 && objLis.getUsuDescripcion().compareTo("")!=0){
			strSql.append("and t.dapepatusu like '" + objLis.getUsuDescripcion() + "%' "); 
		}
		if(objLis.getUsuUsuario().compareTo("")!=0 && objLis.getUsuDescripcion().compareTo("")!=0){
			strSql.append("and t.cusu like '" + objLis.getUsuUsuario() + "%' and t.dapepatusu like '" + objLis.getUsuDescripcion() + "%' "); 
		}
		if(objLis.getUsuPerfil().compareTo("")!=0){
			strSql.append("and t.cusunivacc = '" + objLis.getUsuPerfil() + "' "); 
		}
		strSql.append("order by t.cusu asc ");

	  logger.debug(strSql);
	  ListaRegistros lstRegistros = new ListaRegistros();
	  lstRegistros.setConnection(pcon);
	  lstRegistros.setNumRegistros(objLis.getiRegxPag());
	  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
	  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
	  stmt = pcon.createStatement();
	  rs = stmt.executeQuery(strSql.toString());
	  if (rs.next()) {
			objLis.setUsuUsuario(rs.getString(1));
			objLis.setUsuClave(rs.getString(2));
			objLis.setUsuDescripcion(rs.getString(3));
			objLis.setUsuMail(rs.getString(4));
			objLis.setUsuPerfil(rs.getString(5));
			objLis.setUsuCcas(rs.getString(6));
			objLis.setUsuBestadoreg(rs.getString(7));
			objLis.setUsuCred(rs.getString(10));
			objLis.setBworkflow(rs.getString(11));
			objLis.setUsuMat(rs.getString(12));
			objLis.setUsuNom(rs.getString(13));
		  bOK=true;
	  }
	  else{
		  strError = "Según los criterios seleccionados, no hay información";
	  }
	  
	}
	catch (Throwable exception) {
	  logger.debug("Error Throwable en getLisUsuarios clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
	  strError="No se pudo obtener informacion, se presentaron errores <br>";
	}finally {
		conexion.closeStatement(stmt);
		conexion.closeResultSet(rs);
	}
  return objLis;
  }

  public synchronized void UsuariosUpdate(Connection pcon, Tablas_t objMov, String tPerfil, String tUpd) throws SQLException {
	CallableStatement cs = null;
		try {
			if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("I")==0 ){ // Insert
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_USUARIOS.SP_ING_USUARIOS (?,?,?,?,?,?,?) }");
				cs.setString(1,objMov.getUsuUsuario());
				//cs.setString(2,objMov.getUsuClave());
				cs.setString(2,objMov.getUsuDescripcion());
				cs.setString(3,objMov.getUsuMail());
				cs.setString(4,objMov.getUsuPerfil());
				cs.setString(5,objMov.getUsuCcas());
				cs.setString(6,objMov.getUsuMat());
				cs.setString(7,objMov.getUsuNom());
				//cs.setString(7,objMov.getBworkflow());
		  }
			if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("M")==0 ){ // Update
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_USUARIOS.SP_MOD_USUARIOS (?,?,?,?,?,?,?,?,?) }");
				cs.setString(1,objMov.getUsuUsuario());
				cs.setString(2,objMov.getUsuClave());
				cs.setString(3,objMov.getUsuDescripcion());
				cs.setString(4,objMov.getUsuMail());
				cs.setString(5,objMov.getUsuPerfil());
				cs.setString(6,objMov.getUsuCcas());
				//cs.setString(7,objMov.getBworkflow());
				cs.setString(7,objMov.getUsuBestadoreg());
				cs.setString(8,objMov.getUsuMat());
				cs.setString(9,objMov.getUsuNom());
		  }
			if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("E")==0 ){ // ELIMINACION LOGICA DE FICHA
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_ACCIONES_DEL_LOGICO (?,?) }");
				cs.setString(1,objMov.getUsuUsuario());
				cs.setString(2,objMov.getUsuClave());
		  }
			cs.executeUpdate();
		 
		  bOK=true;
		}
		catch(SQLException e) {
			strError="Tablas UsuariosUpdate: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
			logger.debug("Error En Tablas [" + getClass().getName() + "]. " + e.getMessage());
		} 
		finally {
			conexion.closeCallableStatement(cs);
		}
  }

  public Tablas_t getLisAsuntos(Connection pcon, clases.Tablas_t objLis)throws SQLException {
	Statement stmt   = null;
	ResultSet rs = null;
	StringBuffer strSql = new StringBuffer();
	try {
		strSql.append("select t.casu, t.dasudes, t.nasupzo, t.casu, decode(t.casuestareg,'1','ACTIVO','INACTIVO'), t.cmateria, ");
		strSql.append("a.dmateria, decode(a.bestadoreg, '1', 'ACTIVO', 'INACTIVO'), ");		strSql.append("a.cgrupo, b.dgrupo, decode(b.bestadoreg, '1', 'ACTIVO', 'INACTIVO') "); 
		strSql.append("from dbasu10 t, dbasu20 a, dbasu30 b ");
		strSql.append("where t.cmateria = a.cmateria(+) and a.cgrupo = b.cgrupo(+) ");
		
		if(objLis.getAsunCodigo().compareTo("")!=0 ){
			strSql.append("and t.casu like '" + objLis.getAsunCodigo() + "%' "); 
		}
		if(objLis.getAsunDescripcion().compareTo("")!=0){
			strSql.append("and t.dasudes like '" + objLis.getAsunDescripcion() + "%' "); 
		}
		if(objLis.getCmateria().compareTo("")!=0){
			strSql.append("and t.cmateria like '" + objLis.getCmateria() + "%' "); 
		}
		
		if(objLis.getOrden().compareTo("")!=0){
			strSql.append("order by " +objLis.getOrden()+ " asc ");
		}
	  logger.debug(strSql);
	  ListaRegistros lstRegistros = new ListaRegistros();
	  lstRegistros.setConnection(pcon);
	  lstRegistros.setNumRegistros(objLis.getiRegxPag());
	  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
	  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
	  stmt = pcon.createStatement();
	  rs = stmt.executeQuery(strSql.toString());
	  if (rs.next()) {
			objLis.setAsunCodigo(rs.getString(1));
			objLis.setAsunDescripcion(rs.getString(2));
			objLis.setAsunPlazo(rs.getString(3));
			objLis.setAsunClaveusu(rs.getString(4));
			objLis.setAsunBestadoreg(rs.getString(5));
			objLis.setCmateria(rs.getString(6));
			objLis.setDmateria(rs.getString(7));
			objLis.setCond1(rs.getString(8));
			objLis.setCgrupo(rs.getString(9));
			objLis.setDgrupo(rs.getString(10));
			objLis.setCond2(rs.getString(11));
		  bOK=true;
	  }
	  else{
		  strError = "Según los criterios seleccionados, no hay información";
	  }
	  
	}
	catch (Throwable exception) {
	  logger.debug("Error Throwable en getLisAsuntos clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
	  strError="No se pudo obtener informacion, se presentaron errores <br>";
	}finally {
		conexion.closeStatement(stmt);
		conexion.closeResultSet(rs);
	}
  return objLis;
  }

  public synchronized void AsuntosUpdate(Connection pcon, Tablas_t objMov, String tPerfil, String tUpd) throws SQLException {
	CallableStatement cs = null;
		try {
			if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("I")==0 ){ // Insert
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_ASUNTOS_INGRESO (?,?,?,?) }");
				cs.setString(1,objMov.getAsunCodigo());
				cs.setString(2,objMov.getCmateria());
				cs.setString(3,objMov.getAsunDescripcion());
				//cs.setInt(4,0);
				cs.setString(4,objMov.getAsunClaveusu());
		  }
			if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("M")==0 ){ // Update
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_ASUNTOS_UPDATE (?,?,?,?,?) }");
				cs.setString(1,objMov.getAsunCodigo());
				cs.setString(2,objMov.getCmateria());
				cs.setString(3,objMov.getAsunDescripcion());
				//cs.setInt(4,Integer.parseInt(objMov.getAsunPlazo()));
				cs.setString(4,objMov.getAsunBestadoreg());
				cs.setString(5,objMov.getAsunClaveusu());
		  }
			cs.executeUpdate();
		  cs.close();
		  cs=null;
		  bOK=true;
		}
		catch(SQLException e) {
			strError="Tablas AsuntosUpdate: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
			logger.debug("Error En Tablas [" + getClass().getName() + "]. " + e.getMessage());
		} 
		finally {
			conexion.closeCallableStatement(cs);
		}
  }

  public Tablas_t getLisEstados(Connection pcon, clases.Tablas_t objLis)throws SQLException {
	Statement stmt   = null;
	ResultSet rs = null;
	StringBuffer strSql = new StringBuffer();
	try {

		strSql.append("select t.cefi, t.defi, decode(t.befiestareg,'1','ACTIVO','INACTIVO') from dbefi10 t "); 
		if(objLis.getEstEstado().compareTo("")!=0 && objLis.getEstDescripcion().compareTo("")==0){
			strSql.append("where t.cefi = '" + objLis.getEstEstado() + "' ");
		}
		if(objLis.getEstEstado().compareTo("")==0 && objLis.getEstDescripcion().compareTo("")!=0){
			strSql.append("where t.defi like '%" + objLis.getEstDescripcion() + "%' "); 
		}
		if(objLis.getEstEstado().compareTo("")!=0 && objLis.getEstDescripcion().compareTo("")!=0){
			strSql.append("where t.cefi = '" + objLis.getEstEstado() + "' and  t.defi like '" + objLis.getEstDescripcion() + "%' "); 
		}
		strSql.append("order by t.cefi asc ");

	  logger.debug(strSql);
	  ListaRegistros lstRegistros = new ListaRegistros();
	  lstRegistros.setConnection(pcon);
	  lstRegistros.setNumRegistros(objLis.getiRegxPag());
	  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
	  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
	  stmt = pcon.createStatement();
	  rs = stmt.executeQuery(strSql.toString());
	  if (rs.next()) {
			objLis.setEstEstado(rs.getString(1));
			objLis.setEstDescripcion(rs.getString(2));
			objLis.setEstBestadoreg(rs.getString(3));
		  bOK=true;
	  }
	  else{
		  strError = "Según los criterios seleccionados, no hay información";
	  }
	  
	}
	catch (Throwable exception) {
	  logger.debug("Error Throwable en getLisEstados clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
	  strError="No se pudo obtener informacion, se presentaron errores <br>";
	}finally {
		conexion.closeStatement(stmt);
		conexion.closeResultSet(rs);
	}
  return objLis;
  }

  public synchronized void EstadosUpdate(Connection pcon, Tablas_t objMov, String tPerfil, String tUpd) throws SQLException {
	CallableStatement cs = null;
		try {
			if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("I")==0 ){ // Insert
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_ESTADOS_INGRESO (?,?) }");
				cs.setString(1,objMov.getEstEstado());
				cs.setString(2,objMov.getEstDescripcion());
		  }
			if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("M")==0 ){ // Update
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_ESTADOS_UPDATE (?,?,?) }");
				cs.setString(1,objMov.getEstEstado());
				cs.setString(2,objMov.getEstDescripcion());
				cs.setString(3,objMov.getEstBestadoreg());
		  }
			cs.executeUpdate();
		  
		  bOK=true;
		}
		catch(SQLException e) {
			strError="Tablas EstadosUpdate: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
			logger.debug("Error En Tablas [" + getClass().getName() + "]. " + e.getMessage());
		} 
		finally {
			conexion.closeCallableStatement(cs);
		}
  }

  public Tablas_t getLisMotivos(Connection pcon, clases.Tablas_t objLis)throws SQLException {
	Statement stmt   = null;
	ResultSet rs = null;
	StringBuffer strSql = new StringBuffer();
	try {
		strSql.append("select t.cmso, t.dmso, decode(t.bmsoestareg,'1','ACTIVO','INACTIVO') from dbmso10 t "); 
		if(objLis.getMotCod().compareTo("")!=0 && objLis.getMotDesc().compareTo("")==0){
			strSql.append("where t.cmso = '" + objLis.getMotCod() + "' "); 
		}
		if(objLis.getMotCod().compareTo("")==0 && objLis.getMotDesc().compareTo("")!=0){
			strSql.append("where t.dmso like '" + objLis.getMotDesc() + "%' "); 
		}
		if(objLis.getMotCod().compareTo("")!=0 && objLis.getMotDesc().compareTo("")!=0){
			strSql.append("where t.cmso = '" + objLis.getMotCod() + "' and  t.dmso like '" + objLis.getMotDesc() + "%' "); 
		}
		strSql.append("order by t.cmso asc "); 

	  logger.debug(strSql);
	  ListaRegistros lstRegistros = new ListaRegistros();
	  lstRegistros.setConnection(pcon);
	  lstRegistros.setNumRegistros(objLis.getiRegxPag());
	  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
	  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
	  stmt = pcon.createStatement();
	  rs = stmt.executeQuery(strSql.toString());
	  if (rs.next()) {
			objLis.setMotCod(rs.getString(1));
			objLis.setMotDesc(rs.getString(2));
			objLis.setMotBestado(rs.getString(3));
		  bOK=true;
	  }
	  else{
		  strError = "Según los criterios seleccionados, no hay información";
	  }
	  
	}
	catch (Throwable exception) {
	  logger.debug("Error Throwable en getLisMotivos clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
	  strError="No se pudo obtener informacion, se presentaron errores <br>";
	}finally {
		conexion.closeStatement(stmt);
		conexion.closeResultSet(rs);
	}
  return objLis;
  }

  public synchronized void MotivosUpdate(Connection pcon, Tablas_t objMov, String tPerfil, String tUpd) throws SQLException {
	CallableStatement cs = null;
		try {
			if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("I")==0 ){ // Insert
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_MOTIVOS_INGRESO (?,?) }");
				cs.setString(1,objMov.getMotCod());
				cs.setString(2,objMov.getMotDesc());
		  }
			if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("M")==0 ){ // Update
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_MOTIVOS_UPDATE (?,?,?) }");
				cs.setString(1,objMov.getMotCod());
				cs.setString(2,objMov.getMotDesc());
				cs.setString(3,objMov.getMotBestado());
		  }
			cs.executeUpdate();
		  
		  bOK=true;
		}
		catch(SQLException e) {
			strError="Tablas MotivosUpdate: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
			logger.debug("Error En Tablas [" + getClass().getName() + "]. " + e.getMessage());
		} 
		finally {
			conexion.closeCallableStatement(cs);
		}
  }

  public Tablas_t getLisTiposPres(Connection pcon, clases.Tablas_t objLis)throws SQLException {
	Statement stmt   = null;
	ResultSet rs = null;
	StringBuffer strSql = new StringBuffer();
	try {

		strSql.append("select t.ctpr, t.dtpr, decode(t.btprestareg,'1','ACTIVO','INACTIVO') from dbtpr10 t "); 
		if(objLis.getTipCod().compareTo("")!=0 && objLis.getTipDesc().compareTo("")==0){
			strSql.append("where t.ctpr like '" + objLis.getTipCod() + "%' "); 
		}
		if(objLis.getTipCod().compareTo("")==0 && objLis.getTipDesc().compareTo("")!=0){
			strSql.append("where t.dtpr like '" + objLis.getTipDesc() + "%' "); 
		}
		if(objLis.getTipCod().compareTo("")!=0 && objLis.getTipDesc().compareTo("")!=0){
			strSql.append("where t.ctpr like '" + objLis.getTipCod() + "%' and  t.dtpr like '" + objLis.getTipDesc() + "%' "); 
		}
		strSql.append("order by t.ctpr asc "); 

	  logger.debug(strSql);
	  ListaRegistros lstRegistros = new ListaRegistros();
	  lstRegistros.setConnection(pcon);
	  lstRegistros.setNumRegistros(objLis.getiRegxPag());
	  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
	  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
	  stmt = pcon.createStatement();
	  rs = stmt.executeQuery(strSql.toString());
	  if (rs.next()) {
			objLis.setTipCod(rs.getString(1));
			objLis.setTipDesc(rs.getString(2));
			objLis.setTipBestado(rs.getString(3));
		  bOK=true;
	  }
	  else{
		  strError = "Según los criterios seleccionados, no hay información";
	  }
	  
	}
	catch (Throwable exception) {
	  logger.debug("Error Throwable en getLisTiposPres clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
	  strError="No se pudo obtener informacion, se presentaron errores <br>";
	}finally {
		conexion.closeStatement(stmt);
		conexion.closeResultSet(rs);
	}
  return objLis;
  }

  public synchronized void TiposPresUpdate(Connection pcon, Tablas_t objMov, String tPerfil, String tUpd) throws SQLException {
	CallableStatement cs = null;
		try {
			if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("I")==0 ){ // Insert
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_TIPOS_INGRESO (?,?) }");
				cs.setString(1,objMov.getTipCod());
				cs.setString(2,objMov.getTipDesc());
		  }
			if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("M")==0 ){ // Update
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_TIPOS_UPDATE (?,?,?) }");
				cs.setString(1,objMov.getTipCod());
				cs.setString(2,objMov.getTipDesc());
				cs.setString(3,objMov.getTipBestado());
		  }
			cs.executeUpdate();
		  cs.close();
		  cs=null;
		  bOK=true;
		}
		catch(SQLException e) {
			strError="Tablas MotivosUpdate: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
			logger.debug("Error En Tablas [" + getClass().getName() + "]. " + e.getMessage());
		} 
		finally {
			conexion.closeCallableStatement(cs);
		}
  }

  public Tablas_t getLisTiposIng(Connection pcon, clases.Tablas_t objLis)throws SQLException {
	Statement stmt   = null;
	ResultSet rs = null;
	StringBuffer strSql = new StringBuffer();
	try {

		strSql.append("select t.ctin, t.dtin, decode(t.btinestareg,'1','ACTIVO','INACTIVO') from dbtin10 t "); 
		if(objLis.getTipIngCod().compareTo("")!=0 && objLis.getTipIngDesc().compareTo("")==0){
			strSql.append("where t.ctin = '" + objLis.getTipIngCod() + "' "); 
		}
		if(objLis.getTipIngCod().compareTo("")==0 && objLis.getTipIngDesc().compareTo("")!=0){
			strSql.append("where t.dtin like '" + objLis.getTipIngDesc() + "%' "); 
		}
		if(objLis.getTipIngCod().compareTo("")!=0 && objLis.getTipIngDesc().compareTo("")!=0){
			strSql.append("where t.ctin = '" + objLis.getTipIngCod() + "' and  t.dtin like '" + objLis.getTipIngDesc() + "%' "); 
		}
		strSql.append("order by t.ctin asc "); 

	  logger.debug(strSql);
	  ListaRegistros lstRegistros = new ListaRegistros();
	  lstRegistros.setConnection(pcon);
	  lstRegistros.setNumRegistros(objLis.getiRegxPag());
	  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
	  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
	  stmt = pcon.createStatement();
	  rs = stmt.executeQuery(strSql.toString());
	  if (rs.next()) {
			objLis.setTipCod(rs.getString(1));
			objLis.setTipDesc(rs.getString(2));
			objLis.setTipBestado(rs.getString(3));
		  bOK=true;
	  }
	  else{
		  strError = "Según los criterios seleccionados, no hay información";
	  }
	  
	}
	catch (Throwable exception) {
	  logger.debug("Error Throwable en getLisTiposPres clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
	  strError="No se pudo obtener informacion, se presentaron errores <br>";
	}finally {
		conexion.closeStatement(stmt);
		conexion.closeResultSet(rs);
	}
  return objLis;
  }

  public synchronized void TiposIngUpdate(Connection pcon, Tablas_t objMov, String tPerfil, String tUpd) throws SQLException {
	CallableStatement cs = null;
		try {
			if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("I")==0 ){ // Insert
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_TIPOSING_INGRESO (?,?) }");
				cs.setString(1,objMov.getTipIngCod());
				cs.setString(2,objMov.getTipIngDesc());
		  }
			if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("M")==0 ){ // Update
				cs = (CallableStatement)
				pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_TIPOSING_UPDATE (?,?,?) }");
				cs.setString(1,objMov.getTipIngCod());
				cs.setString(2,objMov.getTipIngDesc());
				cs.setString(3,objMov.getTipIngBestado());
		  }
			cs.executeUpdate();
		 
		  bOK=true;
		}
		catch(SQLException e) {
			strError="Tablas MotivosUpdate: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
			logger.debug("Error En Tablas [" + getClass().getName() + "]. " + e.getMessage());
		} 
		finally {
			conexion.closeCallableStatement(cs);
		}
  }

  public Tablas_t getLisMaterias(Connection pcon, clases.Tablas_t objLis)throws SQLException {
		Statement stmt   = null;
		ResultSet rs = null;
		StringBuffer strSql = new StringBuffer();
		try {
			strSql.append("select t.cmateria, t.dmateria, decode(t.bestadoreg,'1','ACTIVO','INACTIVO'), a.dgrupo, t.cgrupo," );			strSql.append("decode(a.bestadoreg, '1', 'ACTIVO', 'INACTIVO') "); 
			strSql.append("from dbasu20 t, dbasu30 a where t.cgrupo = a.cgrupo(+) ");
			if(objLis.getCmateria().compareTo("")!=0 && objLis.getDmateria().compareTo("")==0){
				strSql.append("and t.cmateria like '" + objLis.getCmateria() + "%' "); 
			}
			if(objLis.getCmateria().compareTo("")==0 && objLis.getDmateria().compareTo("")!=0){
				strSql.append("and t.dmateria like '" + objLis.getDmateria() + "%' "); 
			}
			if(objLis.getCmateria().compareTo("")!=0 && objLis.getDmateria().compareTo("")!=0){
				strSql.append("and t.cmateria like '" + objLis.getCmateria() + "%' and t.dmateria like '" + objLis.getDmateria() + "%' "); 
			}
			if(objLis.getOrden().compareTo("")!=0){
				strSql.append("order by " +objLis.getOrden()+ " asc ");
			}
		  logger.debug("QUERY getLisMaterias "+strSql);
		  ListaRegistros lstRegistros = new ListaRegistros();
		  lstRegistros.setConnection(pcon);
		  lstRegistros.setNumRegistros(objLis.getiRegxPag());
		  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
		  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
		  stmt = pcon.createStatement();
		  rs = stmt.executeQuery(strSql.toString());
		  if (rs.next()) {
				objLis.setCmateria(rs.getString(1));
				objLis.setDmateria(rs.getString(2));
				objLis.setBestadoreg(rs.getString(3));
				objLis.setDgrupo(rs.getString(4));
				objLis.setCgrupo(rs.getString(5));
				objLis.setCond1(rs.getString(6));
			  bOK=true;
		  }
		  else{
			  strError = "Según los criterios seleccionados, no hay información";
		  }
		
		}
		catch (Throwable exception) {
		  logger.debug("Error Throwable en getLisMaterias clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
		  strError="No se pudo obtener informacion, se presentaron errores <br>";
		}finally {
			conexion.closeStatement(stmt);
			conexion.closeResultSet(rs);
		}
	  return objLis;
  }

	public synchronized void MateriasUpdate(Connection pcon, Tablas_t objMov, String tPerfil, String tUpd) throws SQLException {
	  CallableStatement cs = null;
	  try {
		  if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("I")==0 ){ // Insert
			  cs = (CallableStatement)
			  pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_MATERIAS_INGRESO (?,?,?) }");
			  cs.setString(1,objMov.getCmateria());
			  cs.setString(2,objMov.getDmateria());
				cs.setString(3,objMov.getCgrupo());
			}
		  if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("M")==0 ){ // Update
			  cs = (CallableStatement)
			  pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_MATERIAS_UPDATE (?,?,?,?) }");
				cs.setString(1,objMov.getCmateria());
				cs.setString(2,objMov.getDmateria());
				cs.setString(3,objMov.getCgrupo());
			  cs.setString(4,objMov.getBestadoreg());
			}
		  cs.executeUpdate();
			
			bOK=true;
	  }
	  catch(SQLException e) {
		  strError="Tablas MateriasUpdate: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
		  logger.debug("Error En Tablas [" + getClass().getName() + "]. " + e.getMessage());
	  } 
	  finally {
		  conexion.closeCallableStatement(cs);
	  }
	}

	public Tablas_t getLisGrupos(Connection pcon, clases.Tablas_t objLis)throws SQLException {
	  Statement stmt   = null;
	  ResultSet rs = null;
	  StringBuffer strSql = new StringBuffer();
	  try {
		  strSql.append("select t.cgrupo, t.dgrupo, decode(t.bestadoreg,'1','ACTIVO','INACTIVO') "); 
		  strSql.append("from dbasu30 t ");
		  if(objLis.getCgrupo().compareTo("")!=0 && objLis.getDgrupo().compareTo("")==0){
			  strSql.append("where t.cgrupo like '" + objLis.getCgrupo() + "%' "); 
		  }
		  if(objLis.getCgrupo().compareTo("")==0 && objLis.getDgrupo().compareTo("")!=0){
			  strSql.append("where t.dgrupo like '" + objLis.getDgrupo() + "%' "); 
		  }
		  if(objLis.getCgrupo().compareTo("")!=0 && objLis.getDgrupo().compareTo("")!=0){
			  strSql.append("where t.cgrupo like '" + objLis.getCgrupo() + "%' and t.dgrupo like '" + objLis.getDgrupo() + "%' "); 
		  }
		  if(objLis.getOrden().compareTo("")!=0){
			  strSql.append("order by " +objLis.getOrden()+ " asc ");
		  }
			logger.debug(strSql);
			ListaRegistros lstRegistros = new ListaRegistros();
			lstRegistros.setConnection(pcon);
			lstRegistros.setNumRegistros(objLis.getiRegxPag());
			objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
			objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
			stmt = pcon.createStatement();
			rs = stmt.executeQuery(strSql.toString());
			if (rs.next()) {
			  objLis.setCgrupo(rs.getString(1));
			  objLis.setDgrupo(rs.getString(2));
			  objLis.setBestadoreg(rs.getString(3));
				bOK=true;
			}
			else{
				strError = "Según los criterios seleccionados, no hay información";
			}
			
	  }
	  catch (Throwable exception) {
			logger.debug("Error Throwable en getLisGrupos clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
			strError="No se pudo obtener informacion, se presentaron errores <br>";
	  }finally {
			conexion.closeStatement(stmt);
			conexion.closeResultSet(rs);
		}
		return objLis;
	}

	public synchronized void GruposUpdate(Connection pcon, Tablas_t objMov, String tPerfil, String tUpd) throws SQLException {
	  CallableStatement cs = null;
	  try {
		  if(tUpd.compareTo("I")==0 ){ // Insert
			  cs = (CallableStatement)
			  pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_GRUPOS_INGRESO (?,?) }");
			  cs.setString(1,objMov.getCgrupo());
			  cs.setString(2,objMov.getDgrupo());
			}
		  if(tUpd.compareTo("M")==0 ){ // Update
			  cs = (CallableStatement)
			  pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_GRUPOS_UPDATE (?,?,?) }");
				cs.setString(1,objMov.getCgrupo());
				cs.setString(2,objMov.getDgrupo());
			  cs.setString(3,objMov.getBestadoreg());
			}
		  cs.executeUpdate();
			
			bOK=true;
	  }
	  catch(SQLException e) {
		  strError="Tablas GruposUpdate: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
		  logger.debug("Error En Tablas [" + getClass().getName() + "]. " + e.getMessage());
	  } 
	  finally {
		  conexion.closeCallableStatement(cs);
	  }
	}

	public Tablas_t getLisPerfiles(Connection pcon, clases.Tablas_t objLis)throws SQLException {
	  Statement stmt   = null;
	  ResultSet rs = null;
	  StringBuffer strSql = new StringBuffer();
	  try {
		  strSql.append("select t.cperfil, t.dperfil, decode(t.bestadoreg,'1','ACTIVO','INACTIVO') "); 
		  strSql.append("from tperfiles t where 1 = 1 ");
		  if(StringUtils.isNotEmpty(objLis.getCperfil())){
			  strSql.append("and t.cperfil = '" + objLis.getCperfil() + "' "); 
		  }
		  if(StringUtils.isNotEmpty(objLis.getDperfil())){
			  strSql.append("and t.dperfil like '" + objLis.getDperfil() + "%' "); 
		  }
		  
		  strSql.append("order by t.dperfil asc ");
			logger.debug(strSql);
			ListaRegistros lstRegistros = new ListaRegistros();
			lstRegistros.setConnection(pcon);
			lstRegistros.setNumRegistros(objLis.getiRegxPag());
			objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
			objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
			stmt = pcon.createStatement();
			rs = stmt.executeQuery(strSql.toString());
			if (rs.next()) {
			  objLis.setCperfil(rs.getString(1));
			  objLis.setDperfil(rs.getString(2));
			  objLis.setBestadoreg(rs.getString(3));
				bOK=true;
			}
			else{
				strError = "Según los criterios seleccionados, no hay información";
			}
			
	  }
	  catch (Throwable exception) {
			logger.debug("Error Throwable en getLisPerfiles clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
			strError="No se pudo obtener informacion, se presentaron errores <br>";
	  }finally {
			conexion.closeStatement(stmt);
			conexion.closeResultSet(rs);
		}
		return objLis;
	}

	public synchronized void PerfilesUpdate(Connection pcon, Tablas_t objMov, String tPerfil, String tUpd) throws SQLException {
	  CallableStatement cs = null;
	  try {
		  if(tUpd.compareTo("I")==0 ){ // Insert
			  cs = (CallableStatement)
			  pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_PERFILES_INGRESO (?,?,?) }");
			  cs.setString(1,objMov.getCperfil());
			  cs.setString(2,objMov.getDperfil());
			  cs.setString(3, objMov.getCusu());
			}
		  if(tUpd.compareTo("M")==0 ){ // Update
			  cs = (CallableStatement)
			  pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_PERFILES_UPDATE (?,?,?,?) }");
				cs.setString(1,objMov.getCperfil());
			  cs.setString(2,objMov.getDperfil());
			  cs.setString(3,objMov.getCusu());
			  cs.setString(4,objMov.getBestadoreg());
			}
		  cs.executeUpdate();
			
			bOK=true;
	  }
	  catch(SQLException e) {
		  strError="Tablas PerfilesUpdate: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
		  logger.debug("Error En Tablas [" + getClass().getName() + "]. " + e.getMessage());
	  } 
	  finally {
		  conexion.closeCallableStatement(cs);
	  }
	}

	  public void getListaTematica(Connection pcon, clases.Tablas_t objLis)throws SQLException {
			Statement stmt   = null;
			ResultSet rs = null;
			StringBuffer strSql = new StringBuffer();
			try {
				strSql.append("SELECT s.cmso, s.dmso, t.cmtem, t.dmtem, decode(t.estadreg, '1', 'ACTIVO','INACTIVO') FROM  dbtema t , dbmso10 s  where  t.cmso = s.cmso ");
				
				if(StringUtils.isNotEmpty(objLis.getCodSolicitud())){
				strSql.append(" and s.cmso = '"+objLis.getCodSolicitud()+"'");
				}
				if(StringUtils.isNotEmpty(objLis.getDescTematica())){
					strSql.append(" and upper(t.dmtem) like '"+objLis.getDescTematica()+"%'");
				}

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
			  logger.debug("Error Throwable en getLisMotivos clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
			  strError="No se pudo obtener informacion, se presentaron errores <br>";
			}finally{
				conexion.closeStatement(stmt);
				conexion.closeResultSet(rs);
			}
		  
		  }
	  //actualizado 13/08/2014
	  public synchronized void tematicaUpdate(Connection pcon, Tablas_t objMov, String tUpd) throws SQLException {
		CallableStatement cs = null;
			try {
				if(StringUtils.equals(tUpd,Constantes.OPCION_INSERTAR)){ // Insert
					cs = (CallableStatement)
					pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_tematica_ingreso (?,?) }");
					cs.setString(1,objMov.getCodSolicitud());
					cs.setString(2,objMov.getDescTematica());
			  }
				if(StringUtils.equals(tUpd,Constantes.OPCION_MODIFICAR)){ // Update
					cs = (CallableStatement)
					pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_tematica_update (?,?,?,?) }");
					cs.setString(1,objMov.getCodSolicitud());
					cs.setString(2,objMov.getCodTematica());
					cs.setString(3,objMov.getDescTematica());
					cs.setString(4,objMov.getBestadoreg());
			  }
				
				cs.executeUpdate();
			  
			  bOK=true;
			}
			catch(SQLException e) {
				strError="Ficha AccionesUpdate: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
				logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
			} 
			finally {
				conexion.closeCallableStatement(cs);
			}
	  }
	  
	  public void getListaConclusiones(Connection pcon, clases.Tablas_t objLis)throws SQLException {
			Statement stmt   = null;
			ResultSet rs = null;
			StringBuffer strSql = new StringBuffer();
			try {
				strSql.append("SELECT s.cmso, s.dmso, t.cconclu, t.dconclu, decode(t.estadreg, '1', 'ACTIVO','INACTIVO') FROM  tconclusiones t , dbmso10 s  where  t.cmso = s.cmso ");
				
				if(StringUtils.isNotEmpty(objLis.getCodSolicitud())){
				strSql.append(" and s.cmso = '"+objLis.getCodSolicitud()+"'");
				}
				if(StringUtils.isNotEmpty(objLis.getDescConclusion())){
					strSql.append(" and t.dconclu like '"+objLis.getDescConclusion()+"%'");
				}

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
			  logger.debug("Error Throwable en getLisMotivos clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
			  strError="No se pudo obtener informacion, se presentaron errores <br>";
			}finally{
				conexion.closeStatement(stmt);
				conexion.closeResultSet(rs);
			}
		  
		  }
	  //actualizado 13/08/2014
	  public synchronized void conclusionesUpdate(Connection pcon, Tablas_t objMov, String tUpd) throws SQLException {
		CallableStatement cs = null;
			try {
				if(StringUtils.equals(tUpd,Constantes.OPCION_INSERTAR)){ // Insert
					cs = (CallableStatement)
					pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_conclusion_ingreso (?,?) }");
					cs.setString(1,objMov.getCodSolicitud());
					cs.setString(2,objMov.getDescConclusion());
			  }
				if(StringUtils.equals(tUpd,Constantes.OPCION_MODIFICAR)){ // Update
					cs = (CallableStatement)
					pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_conclusion_update (?,?,?,?) }");
					cs.setString(1,objMov.getCodSolicitud());
					cs.setString(2,objMov.getCodConclusion());
					cs.setString(3,objMov.getDescConclusion());
					cs.setString(4,objMov.getBestadoreg());
			  }
				
				cs.executeUpdate();
			 
			  bOK=true;
			}
			catch(SQLException e) {
				strError="Ficha AccionesUpdate: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
				logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
			} 
			finally {
				conexion.closeCallableStatement(cs);
			}
	  }
	  
	  
	  public void getListaArea(Connection pcon, clases.Tablas_t objLis)throws SQLException {
			Statement stmt   = null;
			ResultSet rs = null;
			StringBuffer strSql = new StringBuffer();
			try {
				
				strSql.append("select t.enlarehoscod, t.arehosdes, decode(t.estadoreg, '1', 'ACTIVO', 'INACTIVO') from dbarea t where 1 = 1 ");
				
				if(StringUtils.isNotEmpty(objLis.getCodArea())){
				strSql.append(" and t.enlarehoscod = '"+objLis.getCodArea()+"'");
				}
				if(StringUtils.isNotEmpty(objLis.getDescArea())){
					strSql.append(" and t.arehosdes like '"+objLis.getDescArea()+"%'");
				}
				
				strSql.append(" order by t.arehosdes asc");

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
			  logger.debug("Error Throwable en getLisMotivos clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
			  strError="No se pudo obtener informacion, se presentaron errores <br>";
			}finally{
				conexion.closeStatement(stmt);
				conexion.closeResultSet(rs);
			}
		  
		  }
	  
	  
	  public void getListaServicio(Connection pcon, clases.Tablas_t objLis)throws SQLException {
			Statement stmt   = null;
			ResultSet rs = null;
			StringBuffer strSql = new StringBuffer();
			try {
				
				strSql.append("select t.enlarehoscod, (SELECT a.arehosdes from dbarea a where a.enlarehoscod = t.enlarehoscod), t.ENLSERVHOSCOD, t.SERVHOSDES, decode(t.estadoreg, '1', 'ACTIVO', 'INACTIVO') from dmare10 t where 1 = 1 ");
				
				if(StringUtils.isNotEmpty(objLis.getCodServicio())){
				strSql.append(" and t.ENLSERVHOSCOD like '"+objLis.getCodServicio()+"%'");
				}
				if(StringUtils.isNotEmpty(objLis.getDescServicio())){
					strSql.append(" and t.SERVHOSDES like '"+objLis.getDescServicio()+"%'");
				}
				
				strSql.append(" order by t.SERVHOSDES desc");

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
			  logger.debug("Error Throwable en getLisMotivos clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
			  strError="No se pudo obtener informacion, se presentaron errores <br>";
			}finally{
				conexion.closeStatement(stmt);
				conexion.closeResultSet(rs);
			}
		  
		  }
	  
	 
	 
	  //actualizado 14/08/2014
	  public synchronized void areaUpdate(Connection pcon, Tablas_t objMov, String tUpd) throws SQLException {
		CallableStatement cs = null;
			try {
				if(StringUtils.equals(tUpd,Constantes.OPCION_INSERTAR)){ // Insert
					cs = (CallableStatement)
					pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_area_ingreso (?,?) }");
					cs.setString(1,objMov.getCodArea());
					cs.setString(2,objMov.getDescArea());
			  }
				if(StringUtils.equals(tUpd,Constantes.OPCION_MODIFICAR)){ // Update
					cs = (CallableStatement)
					pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_area_update (?,?,?) }");
					cs.setString(1,objMov.getCodArea());
					cs.setString(2,objMov.getDescArea());
					cs.setString(3,objMov.getBestadoreg());
			  }
				
				cs.executeUpdate();
			  
			  bOK=true;
			}
			catch(SQLException e) {
				strError="Ficha AccionesUpdate: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
				logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
			} 
			finally {
				conexion.closeCallableStatement(cs);
			}
	  }	
	  
	  
	  public synchronized void servicioUpdate(Connection pcon, Tablas_t objMov, String tUpd) throws SQLException {
			CallableStatement cs = null;
				try {
					if(StringUtils.equals(tUpd,Constantes.OPCION_INSERTAR)){ // Insert
						cs = (CallableStatement)
						pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_servicio_ingreso (?,?,?) }");
				  }
					if(StringUtils.equals(tUpd,Constantes.OPCION_MODIFICAR)){ // Update
						cs = (CallableStatement)
						pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_servicio_update (?,?,?,?) }");
						cs.setString(4,objMov.getBestadoreg());
				  }
					cs.setString(1,objMov.getCodArea());
					cs.setString(2,objMov.getCodServicio());
					cs.setString(3,objMov.getDescServicio());
					
					cs.executeUpdate();
				  
				  bOK=true;
				}
				catch(SQLException e) {
					strError="Ficha AccionesUpdate: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
					logger.debug("Error En Ficha [" + getClass().getName() + "]. " + e.getMessage());
				} 
				finally {
					conexion.closeCallableStatement(cs);
				}
		  
}
//////////////////////////////////////////////////////
//unidades organicas nivel 01

public Tablas_t getListUnidadOrganica01(Connection pcon, clases.Tablas_t objLis)throws SQLException {
	  Statement stmt   = null;
	  ResultSet rs = null;
	  StringBuffer strSql = new StringBuffer();
	  try {
		  strSql.append("select t.cras,t.dras,decode(t.brasestareg,'1','ACTIVO','INACTIVO') "); 
		  strSql.append("from dmras10 t where 1 = 1 ");
		  if(StringUtils.isNotEmpty(objLis.getStrCras01())){
			  strSql.append("and t.cras = '"+objLis.getStrCras01()+"' "); 
		  }
		  if(StringUtils.isNotEmpty(objLis.getStrDras01())){
			  strSql.append("and t.dras like '"+objLis.getStrDras01()+"%' "); 
		  }
		  
		  
		  strSql.append("order by t.dras asc ");
		  
			logger.debug(strSql);
			ListaRegistros lstRegistros = new ListaRegistros();
			lstRegistros.setConnection(pcon);
			lstRegistros.setNumRegistros(objLis.getiRegxPag());
			objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
			objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
			stmt = pcon.createStatement();
			rs = stmt.executeQuery(strSql.toString());
			if (rs.next()) {
				  objLis.setStrCras01(rs.getString(1));
				  objLis.setStrDras01(rs.getString(2));
				  objLis.setBestadoreg(rs.getString(3));
				bOK=true;
			}
			else{
				strError = "Según los criterios seleccionados, no hay información";
			}
			
	  }
	  catch (Throwable exception) {
			logger.debug("Error Throwable en getLisUnidOrg01 clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
			strError="No se pudo obtener informacion, se presentaron errores <br>";
	  }finally {
			conexion.closeStatement(stmt);
			conexion.closeResultSet(rs);
		}
		return objLis;
	}

//falta update
	public synchronized void actualizarUnidadOrganica01(Connection pcon, Tablas_t objMov, String tPerfil, String tUpd) throws SQLException {
	  CallableStatement cs = null;
	  try {
		  if(tUpd.equals("I")){ // Insert
			  cs = (CallableStatement)
			  pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_unidadorganica1_ingreso (?,?,?) }");
			  cs.setString(1,objMov.getStrCras01());
			  cs.setString(2,objMov.getStrDras01());
			  cs.setString(2,objMov.getStrBrasEstareg01());
			  
			}
		  if( tUpd.equals("M") ){ // Update
			  cs = (CallableStatement)
			  pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_unidadorganica1_update (?,?,?) }");
				cs.setString(1,objMov.getStrCras01());
				cs.setString(2,objMov.getStrDras01());
				cs.setString(3,objMov.getStrBrasEstareg01());
			}
		  cs.executeUpdate();
			
			bOK=true;
	  }
	  catch(SQLException e) {
		  strError="Tablas UnidOrg01Update: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
		  logger.debug("Error En Tablas [" + getClass().getName() + "]. " + e.getMessage());
	  } 
	  finally {
		  conexion.closeCallableStatement(cs);
	  }
	}

//////////////////////////////////////////////////////
//Motivos No Admisibles01

public Tablas_t getListMotivoNoAdmi(Connection pcon, clases.Tablas_t objLis)throws SQLException {
Statement stmt   = null;
ResultSet rs = null;
StringBuffer strSql = new StringBuffer();
try {

strSql.append("select t.ctma, t.dtma, decode(t.estadreg,'1','ACTIVO','INACTIVO') from tmotnoadm t "); 
if(objLis.getStrCtma01().compareTo("")!=0 && objLis.getStrDtma01().compareTo("")==0){
strSql.append("where t.ctma like '" + objLis.getStrCtma01() + "%' "); 
}
if(objLis.getStrCtma01().compareTo("")==0 && objLis.getStrDtma01().compareTo("")!=0){
strSql.append("where t.dtma like '" + objLis.getStrDtma01() + "%' "); 
}
if(objLis.getStrCtma01().compareTo("")!=0 && objLis.getStrDtma01().compareTo("")!=0){
strSql.append("where t.ctma like '" + objLis.getStrCtma01() + "%' and  t.dtma like '" + objLis.getStrDtma01() + "%' "); 
}
strSql.append("order by t.ctma asc "); 

logger.debug(strSql);
ListaRegistros lstRegistros = new ListaRegistros();
lstRegistros.setConnection(pcon);
lstRegistros.setNumRegistros(objLis.getiRegxPag());
objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
stmt = pcon.createStatement();
rs = stmt.executeQuery(strSql.toString());
if (rs.next()) {
objLis.setStrCtma01(rs.getString(1));
objLis.setStrDtma01(rs.getString(2));
objLis.setBestadoreg(rs.getString(3));
bOK=true;
}
else{
strError = "Según los criterios seleccionados, no hay información";
}

}
catch (Throwable exception) {
logger.debug("Error Throwable en getListMotivoNoAdmi clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
strError="No se pudo obtener informacion, se presentaron errores <br>";
}finally {
conexion.closeStatement(stmt);
conexion.closeResultSet(rs);
}
return objLis;
}


//ACTUALIZACION DE LOS MOTIVOS DE NO ADMISIBILIDAD

public synchronized void actualizarMotivosNoAdm01(Connection pcon, Tablas_t objMov, String tPerfil, String tUpd) throws SQLException {
CallableStatement cs = null;
try {
if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("I")==0 ){ // Insert
cs = (CallableStatement)
pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_MotivoNoAdm_ingreso (?,?) }");
cs.setString(1,objMov.getStrCtma01());
cs.setString(2,objMov.getStrDtma01());
}
if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("M")==0 ){ // Update
cs = (CallableStatement)
pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_MotivoNoAdm_update (?,?,?) }");
cs.setString(1,objMov.getStrCtma01());
cs.setString(2,objMov.getStrDtma01());
cs.setString(3,objMov.getBestadoreg());
}
cs.executeUpdate();
cs.close();
cs=null;
bOK=true;
}
catch(SQLException e) {
strError="Tablas actualizarMotivosNoAdm01: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
logger.debug("Error En Tablas [" + getClass().getName() + "]. " + e.getMessage());
} 
finally {
conexion.closeCallableStatement(cs);
}
}

//////////////////////////////////////////////////////
//Modalidad de Conclusion

public Tablas_t getListModalConclu(Connection pcon, clases.Tablas_t objLis)throws SQLException {
Statement stmt   = null;
ResultSet rs = null;
StringBuffer strSql = new StringBuffer();
try {

strSql.append("select t.ctmc, t.dtmc, decode(t.estadreg,'1','ACTIVO','INACTIVO') from tmodconclu t "); 
if(objLis.getStrCtmc01().compareTo("")!=0 && objLis.getStrDtmc01().compareTo("")==0){
strSql.append("where t.ctmc like '" + objLis.getStrCtmc01() + "%' "); 
}
if(objLis.getStrCtmc01().compareTo("")==0 && objLis.getStrDtmc01().compareTo("")!=0){
strSql.append("where t.dtmc like '" + objLis.getStrDtmc01() + "%' "); 
}
if(objLis.getStrCtmc01().compareTo("")!=0 && objLis.getStrDtmc01().compareTo("")!=0){
strSql.append("where t.ctmc like '" + objLis.getStrCtmc01() + "%' and  t.dtmc like '" + objLis.getStrDtmc01() + "%' "); 
}
strSql.append("order by t.ctmc asc "); 

logger.debug(strSql);
ListaRegistros lstRegistros = new ListaRegistros();
lstRegistros.setConnection(pcon);
lstRegistros.setNumRegistros(objLis.getiRegxPag());
objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
stmt = pcon.createStatement();
rs = stmt.executeQuery(strSql.toString());
if (rs.next()) {
objLis.setStrCtmc01(rs.getString(1));
objLis.setStrDtmc01(rs.getString(2));
objLis.setBestadoreg(rs.getString(3));
bOK=true;
}
else{
strError = "Según los criterios seleccionados, no hay información";
}

}
catch (Throwable exception) {
logger.debug("Error Throwable en getListModalConclu clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
strError="No se pudo obtener informacion, se presentaron errores <br>";
}finally {
conexion.closeStatement(stmt);
conexion.closeResultSet(rs);
}
return objLis;
}


//ACTUALIZACION DE LAS MODALIDADES DE CONCLUSION

public synchronized void actualizarModalDeConclu01(Connection pcon, Tablas_t objMov, String tPerfil, String tUpd) throws SQLException {
CallableStatement cs = null;
try {
if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("I")==0 ){ // Insert
cs = (CallableStatement)
pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_ModalDeConclu_ingreso (?,?) }");
cs.setString(1,objMov.getStrCtmc01());
cs.setString(2,objMov.getStrDtmc01());
}
if((tPerfil.compareTo("09")==0 || tPerfil.compareTo("00")==0) && tUpd.compareTo("M")==0 ){ // Update
cs = (CallableStatement)
pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_ModalDeConclu_update (?,?,?) }");
cs.setString(1,objMov.getStrCtmc01());
cs.setString(2,objMov.getStrDtmc01());
cs.setString(3,objMov.getBestadoreg());
}
cs.executeUpdate();
cs.close();
cs=null;
bOK=true;
}
catch(SQLException e) {
strError="Tablas actualizarModalDeConclu01: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
logger.debug("Error En Tablas [" + getClass().getName() + "]. " + e.getMessage());
} 
finally {
conexion.closeCallableStatement(cs);
}
}

//Accion de Persuasion

public Tablas_t getListAccPer(Connection pcon, clases.Tablas_t objLis)throws SQLException {
Statement stmt   = null;
ResultSet rs = null;
StringBuffer strSql = new StringBuffer();
try {

strSql.append("select t.cper, t.dper, decode(t.estadreg,'1','ACTIVO','INACTIVO') from tpersuas t "); 
if(objLis.getStrCper01().compareTo("")!=0 && objLis.getStrDper01().compareTo("")==0){
strSql.append("where t.cper like '" + objLis.getStrCper01() + "%' "); 
}
if(objLis.getStrCper01().compareTo("")==0 && objLis.getStrDper01().compareTo("")!=0){
strSql.append("where t.dper like '" + objLis.getStrDper01() + "%' "); 
}
if(objLis.getStrCper01().compareTo("")!=0 && objLis.getStrDper01().compareTo("")!=0){
strSql.append("where t.cper like '" + objLis.getStrCper01() + "%' and  t.dper like '" + objLis.getStrDper01() + "%' "); 
}
strSql.append("order by t.cper asc "); 

logger.debug(strSql);
ListaRegistros lstRegistros = new ListaRegistros();
lstRegistros.setConnection(pcon);
lstRegistros.setNumRegistros(objLis.getiRegxPag());
objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
stmt = pcon.createStatement();
rs = stmt.executeQuery(strSql.toString());
if (rs.next()) {
objLis.setStrCper01(rs.getString(1));
objLis.setStrDper01(rs.getString(2));
objLis.setBestadoreg(rs.getString(3));
bOK=true;
}
else{
strError = "Según los criterios seleccionados, no hay información";
}

}
catch (Throwable exception) {
logger.debug("Error Throwable en getListModalConclu clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
strError="No se pudo obtener informacion, se presentaron errores <br>";
}finally {
conexion.closeStatement(stmt);
conexion.closeResultSet(rs);
}
return objLis;
}
	
//unidades organicas nivel 02
  
	public Tablas_t getListUnidadOrganica02(Connection pcon, clases.Tablas_t objLis)throws SQLException {
		Statement stmt   = null;
		ResultSet rs = null;
		StringBuffer strSql = new StringBuffer();
		try {
			strSql.append("select t.ccas,t.dcas,decode(t.bcasestareg,'1','ACTIVO','INACTIVO'), t.cras, r.dras from dmcas10 t, dmras10 r where t.cras = r.cras "); 
			if(StringUtils.isNotEmpty(objLis.getStrCcas02())){
				strSql.append("and t.ccas = '" + objLis.getStrCcas02() + "' "); 
			}
			if(objLis.getStrDcas02().compareTo("")!=0){
				strSql.append("and t.dcas like '" + objLis.getStrDcas02() + "%' "); 
			}
			
			strSql.append("order by t.ccas asc "); 

		  logger.debug(strSql);
		  ListaRegistros lstRegistros = new ListaRegistros();
		  lstRegistros.setConnection(pcon);
		  lstRegistros.setNumRegistros(objLis.getiRegxPag());
		  objLis.setHshLista(lstRegistros.getListaPag(Integer.parseInt(objLis.getPag()), strSql.toString(), false));
		  objLis.setPaginacion(new Paginacion (objLis.getHshLista().size(),Integer.parseInt(objLis.getPag()),objLis.getiRegxPag()));
		  stmt = pcon.createStatement();
		  rs = stmt.executeQuery(strSql.toString());
		  if (rs.next()) {
			  objLis.setStrCcas02(rs.getString(1));
				objLis.setStrDcas02(rs.getString(2));
				objLis.setBestadoreg(rs.getString(3));
			  bOK=true;
		  }
		  else{
			  strError = "Según los criterios seleccionados, no hay información";
		  }
		  
		}
		catch (Throwable exception) {
		  logger.debug("Error Throwable en getLisUnidOrg02 clase [" + this.getClass().getName() + "]. " + exception.getMessage()+ " " ) ;   
		  strError="No se pudo obtener informacion, se presentaron errores <br>";
		}finally {
			conexion.closeStatement(stmt);
			conexion.closeResultSet(rs);
		}
	  return objLis;
	  }
//falta actualizar UnidOrg02Update
	  public synchronized void actualizarUnidadOrganica2(Connection pcon, Tablas_t objMov, String tPerfil, String tUpd) throws SQLException {
		CallableStatement cs = null;
			try {
				if( tUpd.equals("I") ){ // Insert
					cs = (CallableStatement)
					pcon.prepareCall("{ call PKG_MANTENIMIENTO.sp_unidadorganica2_ingreso (?,?,?,?) }");
					cs.setString(1,objMov.getStrCras02());
					cs.setString(2,objMov.getStrCcas02());
					cs.setString(3,objMov.getStrDcas02());
					cs.setString(4,objMov.getStrBcasEstareg02());
					
			  }
				if(tUpd.equals("M") ){ // Update
					cs = (CallableStatement)
					pcon.prepareCall("{ call PKG_MANTENIMIENTO.SP_unidadorganica2_UPDATE (?,?,?,?) }");
					cs.setString(1,objMov.getStrCcas02());
					cs.setString(2,objMov.getStrCras02());
					cs.setString(3,objMov.getStrDcas02());
					cs.setString(4,objMov.getStrBcasEstareg02());
			  }
				cs.executeUpdate();
			  
			  bOK=true;
			}
			catch(SQLException e) {
				strError="Tablas UnidOrg02Update: No se pudo grabar el registro, se presentaron errores <br> " +e.getMessage();
				logger.debug("Error En Tablas [" + getClass().getName() + "]. " + e.getMessage());
			} 
			finally {
				conexion.closeCallableStatement(cs);
			}
	  }
}