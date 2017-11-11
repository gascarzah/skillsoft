package bean;

import java.io.Serializable;

public class ReporteFichaPorDelegadoBean implements Serializable{

	private String codigo;
	private String fecharegistro;
	private String solicitante;
	private String involucrado;
	private String estado;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getFecharegistro() {
		return fecharegistro;
	}
	public void setFecharegistro(String fecharegistro) {
		this.fecharegistro = fecharegistro;
	}
	public String getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
	public String getInvolucrado() {
		return involucrado;
	}
	public void setInvolucrado(String involucrado) {
		this.involucrado = involucrado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
