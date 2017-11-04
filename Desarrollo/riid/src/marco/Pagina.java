
package marco;

	public abstract class Pagina  {
		public static final int REG_X_PAG = 15; 
		protected int iTotal;
		protected int iPagina;
		protected int iRegDesde;
		protected int iRegHasta;
		protected boolean bPaginaSgte;
		protected boolean bPaginaAnt;
  	public abstract int getTotal();
		public abstract int getPagina();
		public abstract int getRegDesde();
		public abstract int getRegHasta();
		public abstract boolean getPaginaSgte();
		public abstract boolean getPaginaAnt();

}
