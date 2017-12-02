
package bd;
import bea.bdUtil.Listado;
public class BeListados extends Listado implements java.io.Serializable{
	
	protected String strCond1 = "";   // Condicion 1
	protected String strCond2 = "";   // Condicion 2
	protected String strCond3 = "";   // Condicion 3
	protected String strCond4 = "";   // Condicion 4
	protected String strCond5 = "";   // Condicion 5
	protected String strError = "";   // Error al general el listado

	public String getCond1(){return strCond1;}
	public String getCond2(){return strCond2;}
	public String getCond3(){return strCond3;}
	public String getCond4(){return strCond4;}
	public String getCond5(){return strCond5;}
	public String getError(){return strError;}
	
	public void setCond1(String pstrCond1){strCond1=pstrCond1;}
	public void setCond2(String pstrCond2){strCond2=pstrCond2;}
	public void setCond3(String pstrCond3){strCond3=pstrCond3;}
	public void setCond4(String pstrCond4){strCond4=pstrCond4;}
	public void setCond5(String pstrCond5){strCond5=pstrCond5;}
	public void setError(String pstrError){strError=pstrError;}
}
