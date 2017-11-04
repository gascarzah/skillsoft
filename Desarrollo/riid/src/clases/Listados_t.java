

package clases;
import java.util.Hashtable;
import java.util.Vector;

import marco.Listado;
import util.Paginacion;

public class Listados_t extends Listado implements java.io.Serializable {
  protected String strPag  = "";   
  protected String strCond1 = "";   // Condicion 1
  protected String strCond2 = "";   // Condicion 2
  protected String strCond3 = "";   // Condicion 3
  protected String strCond4 = "";   // Condicion 4
  protected String strCond5 = "";   // Condicion 5
  protected String strCond6 = "";   // Condicion 5
  protected String strError = "";   // Error al general el listado
  protected int iRegxPag = 0;   // numero de registros en la pagina
  protected int iTotalGen =0; // 
  public Listados_t() {}
  public String getPag(){ return strPag;}
  public String getCond1(){return strCond1;}
  public String getCond2(){return strCond2;}
  public String getCond3(){return strCond3;}
  public String getCond4(){return strCond4;}
  public String getCond5(){return strCond5;}
  public String getCond6(){return strCond6;}
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
  public void setCond1(String pstrCond1){strCond1=pstrCond1;}
  public void setCond2(String pstrCond2){strCond2=pstrCond2;}
  public void setCond3(String pstrCond3){strCond3=pstrCond3;}
  public void setCond4(String pstrCond4){strCond4=pstrCond4;}
  public void setCond5(String pstrCond5){strCond5=pstrCond5;}
  public void setCond6(String pstrCond6){strCond6=pstrCond6;}
  public void setTotalGen(int piTotalGen){iTotalGen=piTotalGen;}
  public void setiRegxPag(int piRegxPag){iRegxPag=piRegxPag;}
  public void setError(String pstrError){strError=pstrError;}
}
