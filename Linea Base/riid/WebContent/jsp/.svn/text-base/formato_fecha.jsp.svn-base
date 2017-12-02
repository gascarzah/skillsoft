<%@page import="java.util.GregorianCalendar"%>
<%
  GregorianCalendar dateInfo = new GregorianCalendar();
  String[] mesNames = {"01","02","03","04","05","06","07","08","09","10","11","12"};
  int ddia = dateInfo.get(dateInfo.DATE);
  String mes = mesNames[dateInfo.get(dateInfo.MONTH)];
  int ano = dateInfo.get(dateInfo.YEAR);
  int hora = dateInfo.get(dateInfo.HOUR);
  int hora12 = hora;
  int minutos = dateInfo.get(dateInfo.MINUTE);
  int segundos = dateInfo.get(dateInfo.SECOND);
  int Am_Pm = dateInfo.get(dateInfo.AM_PM);
  String AmOPm;
  String dia;
  String hor;
  String min;
  String seg;
  if(segundos < 10) seg = "0" + String.valueOf(segundos); else seg = String.valueOf(segundos);
  if(minutos < 10) min = "0" + String.valueOf(minutos); else min = String.valueOf(minutos);
  if (Am_Pm == 0) AmOPm = "AM";
  else {
	AmOPm = "PM";
	hora12 = hora + 12;
  }		  
  if(hora12 < 10) hor = "0" + String.valueOf(hora12); else hor = String.valueOf(hora12);  
  if (ddia < 10) dia = "0" + String.valueOf(ddia); else dia = String.valueOf(ddia);
%>
