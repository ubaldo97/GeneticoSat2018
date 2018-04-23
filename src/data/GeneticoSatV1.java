/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import data.Grafica.*;

/**
 *
 * @author ruben
 */
public class GeneticoSatV1 {
     private int tamPob;
    private double probMuta;
    private int numGeneraciones;
    private Poblacion pobActual;
    private int porMuestra;

    public GeneticoSatV1(int tamPob, double probMuta, int numGeneraciones) {
        this.tamPob = tamPob;
        this.probMuta = probMuta;
        this.numGeneraciones = numGeneraciones;
        this.pobActual = new Poblacion(tamPob);
        this.porMuestra = 20;
    }
    
    public void evolucionar(){
        //try {
            Poblacion nuevaPoblacion;
            
            //Individuo lectura = Herramientas.sacarMejor();
            
            //this.pobActual.getIndividuos().add(lectura);
            this.pobActual.calcularMayorMenor();
            Individuo mejor = this.pobActual.getMayor();
            
            ArrayList<Integer> datosG = new ArrayList<>();
            
            
            
// agregar el ciclo para las generaciones
for(int g=0; g<this.numGeneraciones;g++){
    // proceso iterativo de construccion de la
    // nueva población
    nuevaPoblacion = new Poblacion();
    // generar el muestreo
    int cantidadM = (int)(this.tamPob*this.porMuestra/100);
    //generarMuestreo(cantidadM,nuevaPoblacion);
    nuevaPoblacion.recibirMuestra(this.pobActual.generarGrupoAleatorio(cantidadM));
    
    int[] mask = Mascaras.generarMascaraAleatoria(100);
    for(int i=cantidadM;i<this.tamPob;i++){
       
        // seleccionar a una madre y un padre
        Individuo madre = Seleccion.seleccionTorneoMax(pobActual);
        Individuo padre = Seleccion.seleccionRuleta(pobActual);
        // cruza
        Individuo nuevoi = Cruza.cruzaBinaria(mask,padre, madre);
        // muta (evaluar la probabilidad)
        if(Math.random()<=this.probMuta){
            Muta.mutaAleatoria(nuevoi);
        }
        // agregamos el individuo a la nueva poblacion
        nuevaPoblacion.getIndividuos().add(nuevoi);
    }
    // actualizamos la población actual
    
    this.pobActual = new Poblacion(nuevaPoblacion);
    if (this.pobActual.getMayor().getFitness()>mejor.getFitness()) {
        mejor = this.pobActual.getMayor();
        datosG.add(mejor.getFitness());
    }
    System.out.println("Mejor "+g+": "+this.pobActual.getMayor().getFitness());
    
}
        
System.out.println("Mejor mejor: "+mejor.getFitness());

         try {
             Herramientas.guardarMejorIndividuo(mejor);
         } catch (IOException ex) {
             Logger.getLogger(GeneticoSatV1.class.getName()).log(Level.SEVERE, null, ex);
         }
    Grafica grafica = new Grafica("Generación","Fitness","Fit");
    grafica.agregarSerie( "fit",datosG);
    grafica.crearYmostrarGrafica();
        
       
       }

    /**
     * @param porMuestra the porMuestra to set
     */
    public void setPorMuestra(int porMuestra) {
        this.porMuestra = porMuestra;
    }
}
