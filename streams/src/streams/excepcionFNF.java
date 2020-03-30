/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streams;



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
            this.setMensaje("No se encuentra el fichero");
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
        this.codigoError = codigoError;
    }
}
