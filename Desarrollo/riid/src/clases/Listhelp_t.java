package clases;

public class Listhelp_t extends Listados_t implements java.io.Serializable {
  protected String strTabla = "";  
  protected String strForm = "";  
  protected String strObj1 = "";  
  protected String strCond1 = "";  
  protected String strCond2 = "";  
  protected String strCond3 = "";  
  protected String strCond4 = "";  
  protected String strCond5 = "";  
  protected String strSede = "";  
  public Listhelp_t() {}
  public String getTabla(){return strTabla;}
  public String getForm(){return strForm;}
  public String getObj1(){return strObj1;}
  public String getCond1(){return strCond1;}
  public String getCond2(){return strCond2;}
  public String getCond3(){return strCond3;}
  public String getCond4(){return strCond4;}
  public String getCond5(){return strCond5;}
  public String getSede(){return strSede;}
  public void setTabla(String pstrTabla){strTabla=pstrTabla;}
  public void setForm(String pstrForm){strForm=pstrForm;}
  public void setObj1(String pstrObj1){strObj1=pstrObj1;}
  public void setCond1(String pstrCond1){strCond1=pstrCond1;}
  public void setCond2(String pstrCond2){strCond2=pstrCond2;}
  public void setCond3(String pstrCond3){strCond3=pstrCond3;}
  public void setCond4(String pstrCond4){strCond4=pstrCond4;}
  public void setCond5(String pstrCond5){strCond5=pstrCond5;}
  public void setSede(String pstrSede){strSede=pstrSede;}
}
