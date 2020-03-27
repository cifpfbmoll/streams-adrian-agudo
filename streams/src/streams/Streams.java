/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ADRI
 */
public class Streams {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
            int opcion=0; 
            int car=0;
            int contadorAlmo=0;
            int contadorCar=0;
            while (opcion !=4){
                System.out.println("\nMENU"+"\n1.-Lectura y escritura del fichero de cartelera byte a byte (byte Streams).\n" +
                "2.-Lectura y escritura de fichero de cartelera carácter a carácter (character Streams).\n" +
                "3.-Lectura y escritura de fichero línea a línea con buffers (character Streams).\n" +
                "4.- Salir");
                Scanner lector=new Scanner (System.in);
                opcion=Integer.parseInt(lector.nextLine());
                switch (opcion){
                    case 1:
                        try{
                            FileInputStream fin=new FileInputStream("C:\\Users\\ADRI\\Desktop\\Practica7.txt");
                             System.out.println("====================================");
                             System.out.println("===== CARTELERA DE CINE FBMOLL =====");
                             System.out.println("====================================");
                             System.out.print("----");
                            do{
                              car=fin.read();
                              switch ((char)car){
                                  case '#':
                                       switch (contadorAlmo){
                                           case 0:
                                               System.out.print("----\nAño:");
                                               break;
                                           case 1: 
                                               System.out.print("\nDirector:");
                                               break;
                                           case 2:
                                               System.out.print("\nDuración:");
                                               break;
                                           case 3: 
                                               System.out.print(" minutos\nSypnosis:");
                                               break;
                                           case 4:
                                               System.out.print("\nReparto:");
                                               break;
                                           case 5:
                                               System.out.print("\nSesión:");
                                               break;
                                       }
                                       contadorAlmo++;
                                       break;
                                  case '{':
                                      System.out.print("\n----");
                                      contadorAlmo=0;
                                      break;
                                  default:   
                                     if ((int)car!=-1){ 
                                        if (contadorCar%62!=0){ 
                                            System.out.print((char)car);
                                        }else{
                                             System.out.print("\n"+(char)car);
                                        }
                                        contadorCar++;
                                     }
                                     break;
                              }
                            }
                            while (car!=-1);         
                        }catch(FileNotFoundException ex){
                            System.err.println("Fichero no encontrado");
                        }catch(IOException ex2){
                            System.err.println(ex2.getMessage());
                        }  
                    break;
                }
            }
    }
    
}
