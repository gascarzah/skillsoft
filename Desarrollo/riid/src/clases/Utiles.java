package clases;

import java.util.StringTokenizer;

import org.apache.commons.fileupload.FileItem;

public class Utiles {

	
	private String getFileName(FileItem fi) {
		//nombre del archivo en el cliente
		//depende del browser puede ser:
		//"archivo.txt" o C:\Mis Documentos\archivo.txt" o "/usr/archivo.txt"
		String clientFileName = fi.getName();
		String fileName = null;
		//para asegurarse de tener solo el nombre del archivo (sin rutas)
		//nos quedamos con el ultimo token tomando como separadores "/" y "\"
		StringTokenizer st = new StringTokenizer(clientFileName, "\\");
		while(st.hasMoreTokens()) {
			fileName = st.nextToken();
		}
		st = new StringTokenizer(fileName, "/");
		while(st.hasMoreTokens()) {
			fileName = st.nextToken();
		}
		return fileName;
	}

	
	
}
