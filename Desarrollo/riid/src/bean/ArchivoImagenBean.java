/**
 * 
 */
package bean;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

/**
 * @author user
 *
 */
public class ArchivoImagenBean  implements Serializable{

	
	private static final long serialVersionUID = -3590460117945871056L;
	
	private String nombreArchivo;
	private Long tamanoArchivo;
	private String hashArchivo;
	private String codigoEstado;
	private String codigoColor;
	private int cantidadPaginas;
	private String codigoObservacion;
	private byte [] archivoImagen;
	private String codigoUsuarioRegistro;
	private String codigoUsuarioModificacion;
	private Date fechaRegistro;
	private Date fechaModificacion;
    private Blob archivoImagenBlob;
	
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public Long getTamanoArchivo() {
		return tamanoArchivo;
	}
	public void setTamanoArchivo(Long tamanoArchivo) {
		this.tamanoArchivo = tamanoArchivo;
	}
	public String getHashArchivo() {
		return hashArchivo;
	}
	public void setHashArchivo(String hashArchivo) {
		this.hashArchivo = hashArchivo;
	}
	public String getCodigoEstado() {
		return codigoEstado;
	}
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	public String getCodigoColor() {
		return codigoColor;
	}
	public void setCodigoColor(String codigoColor) {
		this.codigoColor = codigoColor;
	}
	public int getCantidadPaginas() {
		return cantidadPaginas;
	}
	public void setCantidadPaginas(int cantidadPaginas) {
		this.cantidadPaginas = cantidadPaginas;
	}
	public String getCodigoObservacion() {
		return codigoObservacion;
	}
	public void setCodigoObservacion(String codigoObservacion) {
		this.codigoObservacion = codigoObservacion;
	}
	public byte[] getArchivoImagen() {
		return archivoImagen;
	}
	public void setArchivoImagen(byte[] archivoImagen) {
		this.archivoImagen = archivoImagen;
	}
	public String getCodigoUsuarioRegistro() {
		return codigoUsuarioRegistro;
	}
	public void setCodigoUsuarioRegistro(String codigoUsuarioRegistro) {
		this.codigoUsuarioRegistro = codigoUsuarioRegistro;
	}
	public String getCodigoUsuarioModificacion() {
		return codigoUsuarioModificacion;
	}
	public void setCodigoUsuarioModificacion(String codigoUsuarioModificacion) {
		this.codigoUsuarioModificacion = codigoUsuarioModificacion;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Blob getArchivoImagenBlob() {
		return archivoImagenBlob;
	}
	public void setArchivoImagenBlob(Blob archivoImagenBlob) {
		this.archivoImagenBlob = archivoImagenBlob;
	}
    
    
	
	
}
