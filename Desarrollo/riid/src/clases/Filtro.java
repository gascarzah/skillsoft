/**
 * 
 */
package clases;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author especializado
 *
 */
public class Filtro implements FilenameFilter {
 
	 String extension;
	    public Filtro(String extension){
	        this.extension=extension;
	    }
	    public boolean accept(File dir, String name){
	        return name.endsWith(extension);
	        
	    }
	
}
