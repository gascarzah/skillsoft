
package bd;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.sql.BLOB;

import org.apache.log4j.Logger;

import clases.Ficha_t;
public class IOBlob {
	
	private static Logger logger = Logger.getLogger(IOBlob.class);
	protected boolean bOK=false;
	
	public boolean isOK(){
		return bOK;
	}
	
/*Método que se encarga de insertar un archivo local de la PC cliente a un campo BLOB de oracle.
 *			La tabla en donde se grabara el fichero tiene la siguiente estructura:
 *				Tabla: TBLOB
 *				Campos: FICHERO IN BLOB, NOMB IN VARCHAR2(50),PK IN NUMBER
 *				Descripcion: FICHERO es el campo BLOB en donde se alojara los bytes del archivo subido de la PC cliente.
 *											NOMB es el nombre del archivo que además contiene la extension del mismo ejem. hola.doc, carta.txt
 *  										PK campo numerico que servirá de primary key.
 * El metodo recibe la conexion a la BD ya establecida, el string de la ruta del fichero en el disco local y pk, que es el id 
 * para el registro.
 * */ 		
public synchronized void  insertBLOB(Ficha_t obj, InputStream is){
				logger.debug("a"+obj.getcas());
				logger.debug("b"+obj.getayear());
				logger.debug("c"+obj.getcorrel());
				logger.debug("d"+obj.getcusumodi());
				logger.debug("e"+obj.getfichero());
				
				Connection pcon=conexion.getConnection();	
				logger.debug("dentro de insertBLOB");
				bOK=false;
				
				//varaibles auxiliares
				File inputBinaryFile=null;
				Statement           stmt                    = null;
				String							sqlText									="";
				PreparedStatement   pstmt										= null;
				ResultSet           rset                    = null;
				BLOB                image                   = null;
				int                 chunkSize;
				byte[]              binaryBuffer;
				long                position;
				int                 bytesRead               = 0;
				int                 bytesWritten            = 0;
				int                 totbytesRead            = 0;
				int                 totbytesWritten         = 0;
        
				try {
					//IMPORTANTE, tenemos que desactivar el commit automatico debido a que el procedimiento hace mas de una sentencia SQL 
					pcon.setAutoCommit(false);
					stmt = pcon.createStatement();
          //referenciamos al fichero en un File y con este File, referenciamos en un FileInputStream  
					inputBinaryFile      = new File(obj.getfichero());
					
        	//PRIMER PASO: Grabar en la tabla tblob el registro con su pk, el nombre del archivo Y UN BLOB VACIO como inicial.
			sqlText="insert into tficheros(cas,ayear,correl,fichero,dfnomb,cusucrea,fusucrea,bestadoreg)values"+
							"('"+obj.getcas()+"','"+obj.getayear()+"','"+obj.getcorrel()+"',empty_blob(),'"+inputBinaryFile.getName()
							+"','"+obj.getcusumodi()+"',sysdate,1)";
						//procedemos a ejecutar el query.
					stmt.executeUpdate(sqlText);
					
//					pcon.prepareStatement(sqlText);
//					pstmt.setString(1,""+obj.getcas());
//					pstmt.setString(2,""+obj.getayear());
//					pstmt.setString(3,""+obj.getcorrel());
//					pstmt.setString(4,""+inputBinaryFile.getName());
//					pstmt.setString(5,""+obj.getcusumodi());
//					pstmt.executeUpdate();
					logger.debug("TRACKING...85!");
        //SEGUNDO PASO: Seleccionar a este BLOB usando FOR UPDATE para poder grabar en el los bytes del fichero local.
        //select fichero from tficheros where cas=000 and ayear=2012 and correl=50
					sqlText  = 
						"SELECT fichero " +
						"FROM   tficheros " +
						"WHERE  cas="+obj.getcas()+
						" and ayear="+obj.getayear()+
						" and correl="+obj.getcorrel()+
						" FOR UPDATE ";
						//ejecutamos el query y obtenemos el BLOB
					rset = stmt.executeQuery(sqlText);
					rset.next();
					//Hacemos el cating (BLOB) 
					image=(BLOB)rset.getBlob(1);
					chunkSize = image.getChunkSize();
					binaryBuffer = new byte[chunkSize];
          //PASO 3: Grabar los bytes del archivo local en el BLOB mediante el siguiente while:  
					position = 1;
					while ((bytesRead = is.read(binaryBuffer)) != -1) {
						bytesWritten = image.putBytes(position, binaryBuffer, bytesRead);
						position        += bytesRead;
						totbytesRead    += bytesRead;
						totbytesWritten += bytesWritten;
					}
					//cerramos el FileInputStream
					is.close();
					//no olvidar que desactivamos el commit automatico, asi que lo hacemos manual:
					pcon.commit();
					rset.close();
					stmt.close();
          logger.debug("Grabacion exitosa");
          bOK=true;					
  			} catch (IOException e) {
					logger.debug("I/O Exception en insertBLOB: ");
					e.printStackTrace();
					logger.debug(e.getMessage());
				
				} catch (SQLException e) {
					logger.debug("SQL Exception en insertBLOB: ");
					logger.debug("SQL:\n" + sqlText);
					e.printStackTrace();
					logger.debug(e.getMessage());
				}finally{
					conexion.closeConnection(pcon);
				}
}		

//Ahora el proceso inverso, recuperar los bytes grabados en el campo BLOB y poner el archivo en el disco local
//de entrada la conexion ya establecida con la BD y el pk del registro.
public synchronized void selectBLOB(Connection pcon,int pk){
	bOK=false;
	FileOutputStream    outputFileOutputStream      = null;
					String              sqlText                     = null;
					Statement           stmt                        = null;
					ResultSet           rset                        = null;
					BLOB                image                       = null;
					long                blobLength;
					long                position;
					int                 chunkSize;
					byte[]              binaryBuffer;
					int                 bytesRead                   = 0;
					int                 bytesWritten                = 0;
					int                 totbytesRead                = 0;
					int                 totbytesWritten             = 0;

					try{
						pcon.setAutoCommit(false);
						stmt = pcon.createStatement();
						//En este caso, el query sera un select segun pk, recuperamos el BLOB y el nombre del archivo
						sqlText = 
							"SELECT nomb,fichero " +
							"FROM   tblob " +
							"WHERE  pk = "+pk +
							" FOR UPDATE";
						rset = stmt.executeQuery(sqlText);
						rset.next();
						//recuperamos el nombre del archivo
						String fnomb=rset.getString("nomb");
						//IMPORTANTE: Aqui es donde decidimos en que carpeta colocaremos nuestro archivo, derrepente C:\\, en
						//este caso esta en D:\\descarga\\+nombre.extension
						File arch=new File("D:\\descarga\\"+fnomb);
						//abrimos nuestro FileOutPutStream para la grabacion de los bytes contenidos en el BLOB
						outputFileOutputStream=new FileOutputStream(arch);
						//Luego obtenemos el BLOB respectivo
						image=(BLOB)rset.getBlob("fichero");  
						blobLength = image.length();
						chunkSize = image.getChunkSize();
						binaryBuffer = new byte[chunkSize];
            //Y con este while recuperamos los bytes y lo ponemos en el FileOutputStream 
						for (position = 1; position <= blobLength; position += chunkSize) {
							//En cada iteracion del while obtenemos una cantidad de bytes...
              bytesRead = image.getBytes(position, chunkSize, binaryBuffer);
							//...que son grabados en el FileOutputStream
							outputFileOutputStream.write(binaryBuffer, 0, bytesRead);
							//actualizamos los punteros...
              totbytesRead += bytesRead;
							totbytesWritten += bytesRead;
							//y procede a seguir grabando. Test de parada: el puntero position llego a blobLength: a la longitud del BLOB.
						}
						//cerramos el flujo de salida.
						outputFileOutputStream.close();
            //Commit y liberamos recursos.
						pcon.commit();
						rset.close();
						stmt.close();
            
						logger.debug("Se leyo el blob correctamente, el archivo esta ubicado en :"+arch);
						bOK=true;
					} catch (IOException e) {
						logger.debug("I/O Exception:");
						e.printStackTrace();
					
					} catch (SQLException e) {
						logger.debug("SQL Exception:");
						logger.debug("SQL:\n" + sqlText);
						e.printStackTrace();
					
					}finally{
					conexion.closeConnection(pcon);
					}
}	
}
