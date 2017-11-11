package util;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

public class Comun {

	public static String getFechaActual(){
		Calendar Cal= Calendar.getInstance(); 
		String fechaActual= StringUtils.leftPad(String.valueOf(Cal.get(Cal.DATE)),2,'0')+""+
							StringUtils.leftPad(String.valueOf((Cal.get(Cal.MONTH)+1)),2,'0')+""+
							StringUtils.leftPad(String.valueOf(Cal.get(Cal.YEAR)),2,'0')+""+
							StringUtils.leftPad(String.valueOf(Cal.get(Cal.HOUR_OF_DAY)),2,'0')+""+
							StringUtils.leftPad(String.valueOf(Cal.get(Cal.MINUTE)),2,'0')+""+
							StringUtils.leftPad(String.valueOf(Cal.get(Cal.SECOND)),2,'0');
		return fechaActual;
	}
	
	public static String getFechaHoy(){
		Calendar Cal= Calendar.getInstance(); 
		String fechaActual= StringUtils.leftPad(String.valueOf(Cal.get(Cal.DATE)),2,'0')+"/"+
							StringUtils.leftPad(String.valueOf((Cal.get(Cal.MONTH)+1)),2,'0')+"/"+
							StringUtils.leftPad(String.valueOf(Cal.get(Cal.YEAR)),2,'0');
							
		return fechaActual;
	}
}
