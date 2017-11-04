
package util;

import marco.Pagina;


public class Paginacion extends Pagina {
//p_iTotal Registros totales que se muestran en la pagina
//p_iPagina Numero de Pagina a mostrar
//p_iRegPag Registros tope que se deben mostrar en la pagina
	public Paginacion ( int p_iTotal,
											int p_iPagina,
											int p_iRegPag
										) {
	
	//int iMaxHasta = (p_iPagina * p_iRegPag) - p_iTotal;

	super.iTotal = p_iTotal;
	// verificamos si el listado va a tener accesos a los botones ant y sgte
	
	if (p_iPagina != 1) super.bPaginaAnt = true;
	else super.bPaginaAnt = false;
	
	if (super.iTotal < p_iRegPag) super.bPaginaSgte = false;
	else super.bPaginaSgte = true; 
	
	// asignamos el numero  de pagina  a la variable
	
	super.iPagina = p_iPagina;
	
	// calculamos desde qué registro hasta qué registro van a mostrar el listado
	
	super.iRegDesde =  (super.iPagina-1)* p_iRegPag + 1;
	
	if (super.iTotal <= p_iRegPag) 
		 super.iRegHasta = ((p_iPagina - 1)* p_iRegPag) + super.iTotal;
		//super.iRegHasta = ((p_iPagina - 1)* super.REG_X_PAG) + super.iTotal;
	else 
		super.iRegHasta = ((p_iPagina - 1)* p_iRegPag) + (p_iRegPag - super.iTotal);
	}
  
	public int getTotal() {
		return super.iTotal;
	}
	public boolean getPaginaSgte() {
		return super.bPaginaSgte;
	}
	public boolean getPaginaAnt() {
		return super.bPaginaAnt;
	}
	public int getPagina() {
	return super.iPagina;
	}
	public int getRegDesde() {
		return super.iRegDesde;
	}
	public int getRegHasta() {  	
		return super.iRegHasta;
	}
}
