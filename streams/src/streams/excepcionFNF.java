/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streams;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;



/**
 *
 * @author ADRI
 */
public class excepcionFNF extends Exception{
    private int codigoError;
    private String mensaje;

    public excepcionFNF() {
    }

    public excepcionFNF(int codigoError, String mensaje) {
        this.codigoError = codigoError;
        this.mensaje = mensaje;
    }
    
    public excepcionFNF(int codError) {
        if (codError == 333){
            this.setMensaje("No se encuentra el fichero origen");
        }    
        else if (codError == 555){
            this.setMensaje("No se encuentra fichero salida");
        }
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public int getCodigoError() {
        return codigoError;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setCodigoError(int codError) {
        this.codigoError = codError;
    }
    
    public void escribirErrores(String mensajeDeError,String stacktrace)throws IOException{
            File finErrores=new File("Errores.txt");
            if(!finErrores.exists()){
                finErrores.createNewFile();
            }
            FileWriter escritorErrores=new FileWriter (finErrores, true);
            Date fecha=new Date();
            escritorErrores.write("\n"+mensajeDeError+" "+fecha.toString()+" "+stacktrace+"\n"); 
            escritorErrores.close();  
    }
}
        