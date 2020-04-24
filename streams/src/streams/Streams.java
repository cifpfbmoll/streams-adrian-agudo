/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;



/**
 *
 * @author ADRI
 */
public class Streams {
    static int contadorAlmo=0;
    static boolean escribirDoc=false;
    static boolean escrito=false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
            int opcion=0; 
            int opcion2=0; 
            String ruta="";
            String rutaDestino="";
            String linea="";
            int i=0;
            while (opcion !=5){
                System.out.print("\n\nMENU"+"\n1.-Lectura y escritura del fichero de cartelera byte a byte (byte Streams).\n" +
                "2.-Lectura y escritura de fichero de cartelera carácter a carácter (character Streams).\n" +
                "3.-Lectura y escritura de fichero línea a línea con buffers (character Streams).\n" +
                "4.-Tratamiento de Objetos.\n" +        
                "5.- Salir" +
                "\n\nSeleccionar opción: ");
                Scanner lector=new Scanner (System.in);
                opcion=Integer.parseInt(lector.nextLine());
                if (opcion!=4 || opcion!=5){
                   ruta=pedirRutaOrigen(); 
                }
                switch (opcion){                        
                    case 1:
                        try{
                            imprimirCabecera();
                            lectorXBytes(ruta);
                            linea=escribir();
                            if (escribirDoc){
                                escritorXBytes(ruta,linea);
                            } 
                        }catch(FileNotFoundException ex){
                            excepcion(333,ruta);
                        }catch(IOException ex2){
                            System.err.println(ex2.getMessage()); 
                        }
                        break;
                    case 2:
                        try{
                            imprimirCabecera();
                            lectorXCaracteres(ruta);
                            linea = escribir();
                            if (escribirDoc) {
                                escritorXCaracteres(ruta,linea);
                            }                           
                        }catch(FileNotFoundException ex){
                            excepcion(333,ruta);      
                        }catch(IOException ex2){
                            System.err.println(ex2.getMessage()); 
                        }
                        break;
                    case 3: 
                        try{
                            imprimirCabecera();
                            lectorLinea(ruta);
                            linea=escribir();
                            if (escribirDoc){
                                escritorLinea(ruta,linea);
                            }
                        }catch(FileNotFoundException ex){
                           excepcion(333,ruta);
                        }catch(IOException ex2){
                            System.err.println(ex2.getMessage()); 
                        }
                        break;    
                    case 4:
                        while(opcion2!=5){
                            System.out.println("1.- Lectura línea a línea y escritura con objetos (obteniendo ficheroSalObj). \n" +
                            "2.- Lectura de objetos (leyendo ficheroSalObj) y escritura de objetos (obteniendo ficheroSalObj2).\n" +
                            "3.- Lectura de objetos (leyendo ficheroSalObj2) y escritura por consola (comprobaremos por consola que nos ha escrito bien los objetos en los pasos anteriores).\n" +
                            "4.- Lectura por consola y escritura de objetos.\n" +
                            "5.- Volver al menú principal.");
                            opcion2=Integer.parseInt(lector.nextLine());
                            switch (opcion2){  
                                case 1:
                                    try{
                                        ruta=pedirRutaOrigen();
                                        BufferedReader lectorBuffer = new BufferedReader(new FileReader(ruta));
                                    }catch(FileNotFoundException ex){
                                        excepcion(333,ruta);
                                    }
                                    break;
                            }          
                        }    
                }
            }
    }        
    public static void caracterAcaracter(int car) throws IOException{
            switch ((char) car) {
                case '#':
                    switch (contadorAlmo) {
                        case 0:
                            System.out.print("----\nAño: ");
                            break;
                        case 1:
                            System.out.print("\nDirector: ");
                            break;
                        case 2:
                            System.out.print("\nDuración: ");
                            break;
                        case 3:
                            System.out.print(" minutos\nSypnosis: ");
                            break;
                        case 4:
                            System.out.print("\nReparto: ");
                            break;
                        case 5:
                            System.out.print("\nSesión: ");
                            break;
                    }
                    contadorAlmo++;
                    break;
                case '{':
                    System.out.print("\n----");
                    contadorAlmo = 0;
                    break;
                default:
                    if ((int) car != -1) {
                        System.out.print((char) car);
                    break;    
                    }
            }
        }   

    public static void imprimirCabecera() {
        if (!escrito){
            System.out.println("\n====================================");
            System.out.println("===== CARTELERA DE CINE FBMOLL =====");
            System.out.println("====================================");
            System.out.print("----");
        }
    }
    
    public static String escribir() {
        Scanner lector=new Scanner(System.in);
        String opcion="";
        String texto=""; 
        System.out.print("\n\nDesea escribir en el documento?(S/N): ");
        opcion=lector.nextLine();
        if (opcion.equals("S")||opcion.equals("s")){
            System.out.print("\n\nIntroducir texto a escribir: ");
            texto=lector.nextLine();
            escribirDoc=true;
            escrito=true;
        }
        return texto;
    }
    
    public static void excepcion(int numError,String ruta){
         if (ruta.trim().isEmpty()) {
            System.err.println("Ruta no introducida");                                
        } else {
           try {
                throw new excepcionFNF(numError);
            } catch (excepcionFNF exNueva) {
                System.err.println(exNueva.getMensaje());
               try {
                   exNueva.escribirErrores(exNueva.getMensaje(),Arrays.toString(exNueva.getStackTrace()));
               } catch (IOException ex) {
                   System.out.println(ex.getMessage());
               }
            } 
        }
    }
    
    public static void lectorXBytes(String ruta) throws FileNotFoundException,IOException{
        FileInputStream fin=new FileInputStream(ruta);
        int caracter; 
        caracter = fin.read();
        while (caracter!=-1){
            caracterAcaracter(caracter);
            caracter = fin.read();
        }
        contadorAlmo=0;
        caracter=0;
        fin.close();
    }
    
    public static void escritorXBytes(String ruta, String linea) throws FileNotFoundException,IOException{
        FileOutputStream fout = new FileOutputStream(ruta);
        for (int i = 0; i < linea.length(); i++) {
            fout.write((int) linea.charAt(i));
        }
        escribirDoc = false;
        fout.close();
    }
    
    public static void lectorXCaracteres(String ruta)throws FileNotFoundException,IOException{
        int caracter;
        File entrada = new File(ruta);
        FileReader lectorFich = new FileReader(entrada);
        caracter = lectorFich.read();
        while (caracter != -1) {
            caracterAcaracter(caracter);
            caracter = lectorFich.read();
        }
        contadorAlmo = 0;
        caracter = 0;
        lectorFich.close();
    } 
    
    public static void escritorXCaracteres(String ruta, String linea)throws FileNotFoundException,IOException{
        File entrada = new File(ruta);
        FileWriter escritorFich = new FileWriter(entrada);
        for (int i = 0; i < linea.length(); i++) {
            escritorFich.write((int) linea.charAt(i));
        }
        escribirDoc = false;
        escritorFich.close();
    } 
    
    public static void lectorLinea(String ruta)throws FileNotFoundException,IOException{
        String linea;
        File entrada = new File(ruta);
        BufferedReader lectorMasMejor = new BufferedReader(new FileReader(entrada));
        linea = lectorMasMejor.readLine();
        while (linea != null) {
            for (int i = 0; i < linea.length(); i++) {
                caracterAcaracter((int) linea.charAt(i));
            }
            linea = lectorMasMejor.readLine();
        }
        contadorAlmo = 0;
        linea = "";
        lectorMasMejor.close();
    }
    
    public static void escritorLinea(String ruta, String linea)throws FileNotFoundException,IOException{
        File entrada = new File(ruta);
        BufferedWriter escritorMasMejor = new BufferedWriter(new FileWriter(entrada));
        escritorMasMejor.write(linea);
        escribirDoc = false;
        escritorMasMejor.close();
    }
    
    public static String pedirRutaOrigen(){
        String ruta="";
        Scanner lector=new Scanner(System.in);
        System.out.print("\nIntroducir ruta del fichero de Origen: ");
        ruta=lector.nextLine();
        return ruta;
    }
    
    public static String pedirRutaDestino(){
        String ruta="";
        Scanner lector=new Scanner(System.in);
        System.out.print("\nIntroducir ruta del fichero de Destino: ");
        ruta=lector.nextLine();
        return ruta;
    }
}
  
            
            
    
    

