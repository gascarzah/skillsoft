
package marco;

import java.util.Hashtable;
import java.util.Vector;

import util.Paginacion;

public abstract class Listado  {  
  protected Paginacion clPaginacion;
  protected Vector vListado;
  protected Hashtable hshLista;
  public abstract Paginacion getPaginacion();
  public abstract Vector getVector();
  public abstract Hashtable getHshLista();
}
