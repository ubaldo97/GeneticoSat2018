/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3sat2018;

import data.GeneticoSatV1;
import data.Herramientas;
import data.Individuo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Cruz Leija
 */
public class Main {

    /**
     * @param args the command line arguments
     * 1	2	-3
        2	-1	-4
        5	2	1

     */
    public static void main(String[] args) throws IOException {
        
        Individuo.clausulas=Herramientas.leeArchivo();
        System.out.println();
        GeneticoSatV1 gen = new GeneticoSatV1(45,0.1,20000);
        gen.evolucionar();
        
        
        
//        try {
//            Individuo.clausulas = Herramientas.leeArchivo();
//            int g[] =new int[100];
//            int g1[] =new int[100];
//            Random ran = new Random();
//            for(int x=0; x<100;x++){
//             g[x]= ran.nextInt(2);
//             g1[x]= ran.nextInt(2);
//            }
//            
//            Individuo i1 = new Individuo(g);
//            Individuo i2 = new Individuo(g1);
//          
//           
//            
//            System.out.println(i1.getFitness());
//            System.out.println(i2.getFitness());
//          
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
   }
    
}
