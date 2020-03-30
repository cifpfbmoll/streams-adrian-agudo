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
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            int caracter=0;
            String ruta="";
            String linea="";
            int i=0;
            while (opcion !=4){
                System.out.print("\n\nMENU"+"\n1.-Lectura y escritura del fichero de cartelera byte a byte (byte Streams).\n" +
                "2.-Lectura y escritura de fichero de cartelera carácter a carácter (character Streams).\n" +
                "3.-Lectura y escritura de fichero línea a línea con buffers (character Streams).\n" +
                "4.- Salir" +
                "\n\nSeleccionar opción: ");
                Scanner lector=new Scanner (System.in);
                opcion=Integer.parseInt(lector.nextLine());
                if (opcion!=4){
                    System.out.print("\nIntroducir ruta del fichero: ");
                    ruta=lector.nextLine();
                }
                switch (opcion){                        
                    case 1:
                        try{
                            FileInputStream fin=new FileInputStream(ruta);
                            imprimirCabecera();
                            caracter = fin.read();
                            while (caracter!=-1){
                                caracterAcaracter(caracter);
                                caracter = fin.read();
                            }
                            contadorAlmo=0;
                            caracter=0;
                            fin.close();
                            linea=escribir();
                            if (escribirDoc){
                                FileOutputStream fout=new FileOutputStream(ruta);
                                for (i=0;i<linea.length();i++){
                                    fout.write((int)linea.charAt(i));
                                }
                                escribirDoc=false;
                                fout.close();
                            } 
                        }catch(FileNotFoundException ex){
                            System.err.println("Fichero no encontrado");
                        }catch(IOException ex2){
                            System.err.println(ex2.getMessage()); 
                        }
                        break;
                    case 2:
                        try{
                            File entrada=new File(ruta); 
                            FileReader lectorFich=new FileReader(entrada);
                            imprimirCabecera();
                            caracter = lectorFich.read();
                            while (caracter!=-1){
                                caracterAcaracter(caracter);
                                caracter = lectorFich.read();
                            }
                            contadorAlmo=0;
                            caracter=0;
                            lectorFich.close();
                            linea = escribir();
                            if (escribirDoc) {
                                FileWriter escritorFich=new FileWriter(entrada);
                                for (i = 0; i < linea.length(); i++) {
                                    escritorFich.write((int) linea.charAt(i));
                                }
                                escribirDoc = false;
                                escritorFich.close();
                            }                           
                        }catch(FileNotFoundException ex){
                            if (ruta!=null){
                                try{
                                    throw new excepcionFNF(333);
                                }catch (excepcionFNF exNueva){
                                    try {
                                        System.out.println(exNueva.getMensaje());
                                        escribirErrores(exNueva.getMensaje());
                                    } catch (Exception ex1) {
                                        Logger.getLogger(Streams.class.getName()).log(Level.SEVERE, null, ex1);
                                    }
                                }
                            }
                        }catch(IOException ex2){
                            System.err.println(ex2.getMessage()); 
                        }
                        break;
                    case 3: 
                        try{
                            File entrada=new File(ruta); 
                            BufferedReader lectorMasMejor=new BufferedReader(new FileReader(entrada));
                            imprimirCabecera();
                            linea=lectorMasMejor.readLine();
                            while (linea != null){
                                for (i=0;i<linea.length();i++){
                                    caracterAcaracter((int)linea.charAt(i));
                                }
                                linea=lectorMasMejor.readLine();
                            }
                            contadorAlmo=0;
                            caracter=0;
                            linea="";
                            lectorMasMejor.close();
                            linea=escribir();
                            if (escribirDoc){
                                BufferedWriter escritorMasMejor=new BufferedWriter(new FileWriter(entrada));
                                escritorMasMejor.write(linea);      
                                escribirDoc=false;
                                escritorMasMejor.close();
                            }
                        }catch(FileNotFoundException ex){
                            System.err.println("Fichero no encontrado");
                        }catch(IOException ex2){
                            System.err.println(ex2.getMessage()); 
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
    
    public static void escribirErrores(String mensajeDeError) throws Exception{
        File fin=new File("C:\\Users\\ADRI\\Desktop\\Errores.txt");
        BufferedWriter escritor=new BufferedWriter(new FileWriter (fin));
        Date fecha=new Date();
        escritor.append("\n"+mensajeDeError+""+fecha); 
    }
}
  
            
            
    
    

