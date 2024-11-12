package aed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BestEffort {
    private Heap<Ciudad> mayorSuperavit;
    private ArrayList<Ciudad> mayorGanancia;
    private ArrayList<Ciudad> mayorPerdida;
    private Ciudad[] ciudades;
    private Heap<Traslado> trasladosMasAntiguos;
    private Heap<Traslado> trasladosMasRedituables;
    private int[] gananciaTotal;

    public BestEffort(int cantCiudades, Traslado[] traslados) {
        
        ciudades = new Ciudad[cantCiudades];
        
        
        for (int i = 0; i < cantCiudades; i++) {
            ciudades[i] = new Ciudad(i); 
        }

        
        mayorGanancia = new ArrayList<>(cantCiudades);
        mayorPerdida = new ArrayList<>(cantCiudades);

        
        for (Ciudad ciudad : ciudades) {
            mayorGanancia.add(ciudad);
            mayorPerdida.add(ciudad);
        }

        
        gananciaTotal = new int[]{0, 0}; 


        ArrayList<Traslado> trasladosarr = new ArrayList<Traslado>(Arrays.asList(traslados));
        ArrayList<Ciudad> ciudadesarr = new ArrayList<Ciudad>(Arrays.asList(ciudades));


        
        mayorSuperavit = new Heap<>(ciudadesarr, CustomComparator.BY_BALANCE);  
        trasladosMasAntiguos = new Heap<>(trasladosarr, CustomComparator.BY_TIMESTAMP);  
        trasladosMasRedituables = new Heap<>(trasladosarr, CustomComparator.BY_GANANCIA);  
    }

    
private void actualizarCiudades(Ciudad[] ciudades, Traslado[] traslados) {
    int j = 0;
    ArrayList<Ciudad> nuevoArreglo = new ArrayList<>();  

    while (j < traslados.length) {
        gananciaTotal[0] = gananciaTotal[0] + traslados[j].getGananciaNeta();
        gananciaTotal[1] += 1;

        
        ciudades[traslados[j].origen].setGanancia(traslados[j].getGananciaNeta());
        ciudades[traslados[j].destino].setPerdida(traslados[j].getGananciaNeta());

        
        ciudades[traslados[j].origen].sumarBalance(traslados[j].getGananciaNeta());
        ciudades[traslados[j].destino].restarBalance(traslados[j].getGananciaNeta());

        
        if (mayorGanancia.get(0).getGanancia() < ciudades[traslados[j].origen].getGanancia()) {
            
            mayorGanancia.clear();  
            mayorGanancia.add(ciudades[traslados[j].origen]);  
        } else if (mayorGanancia.get(0).getGanancia() == ciudades[traslados[j].origen].getGanancia()) {
            
            mayorGanancia.add(ciudades[traslados[j].origen]);
        }

        
        if (mayorPerdida.get(0).getPerdida() < ciudades[traslados[j].origen].getPerdida()) {
            mayorPerdida.clear();nuevoArreglo.add(ciudades[traslados[j].origen]);
            mayorPerdida.add(ciudades[traslados[j].origen]);
        } else if (mayorPerdida.get(0).getPerdida() == ciudades[traslados[j].origen].getPerdida()) {
            mayorPerdida.add(ciudades[traslados[j].origen]);
        }

        
        ciudades[traslados[j].origen].sumarBalance (traslados[j].gananciaNeta);
        ciudades[traslados[j].destino].restarBalance (traslados[j].gananciaNeta);

        
        this.mayorSuperavit.actualizar(ciudades[traslados[j].origen]);
        this.mayorSuperavit.actualizar(ciudades[traslados[j].destino]);

        j++;
    }
    

    
}


    public void registrarTraslados(Traslado[] traslados){
        ArrayList<Traslado> trasladosarr = new ArrayList<Traslado>(Arrays.asList(traslados));

        this.trasladosMasAntiguos.agregar(trasladosarr);
        this.trasladosMasRedituables.agregar(trasladosarr);
    }

    public int[] despacharMasRedituables(int n) {
        int i = 0;
        
        int[] res = new int[n];
        Traslado[] listaDeTraslados = new Traslado[n];
    
        
        if (n > trasladosMasRedituables.getSize()) {  
            while (i < trasladosMasRedituables.getSize()) {
                
                res[i] = trasladosMasRedituables.getRaiz().getId();  
                listaDeTraslados[i] = trasladosMasRedituables.getRaiz();
                trasladosMasRedituables.eliminar();
                i++;
            }
        } else {
            
            while (i < n) {
                
                res[i] = trasladosMasRedituables.getRaiz().getId();  
                listaDeTraslados[i] = trasladosMasRedituables.getRaiz();
                trasladosMasRedituables.eliminar();
                i++;
            }
        }
        actualizarCiudades(ciudades, listaDeTraslados);

        this.trasladosMasAntiguos.eliminarConIds(res);
        return res;                 
    }

    public int[] despacharMasAntiguos(int n){
        int i = 0;
        
        int[] res = new int[n];
        Traslado[] listaDeTraslados = new Traslado[n];
    
        
        if (n > trasladosMasAntiguos.getSize()) {  
            while (i < trasladosMasAntiguos.getSize()) {
                
                res[i] = trasladosMasAntiguos.getRaiz().getId();  
                listaDeTraslados[i] = trasladosMasAntiguos.getRaiz();
                trasladosMasAntiguos.eliminar();
                i++;
            }
        } else {
            
            while (i < n) {
                
                res[i] = trasladosMasAntiguos.getRaiz().getId();  
                listaDeTraslados[i] = trasladosMasAntiguos.getRaiz();
                trasladosMasAntiguos.eliminar();
                i++;
            }
        }
        actualizarCiudades(ciudades, listaDeTraslados);

        this.trasladosMasRedituables.eliminarConIds(res);
        return res;                 
    }

    public int ciudadConMayorSuperavit(){
        
        return 0;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        
        return null;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        
        return null;
    }

    public int gananciaPromedioPorTraslado(){
        
        return 0;
    }
    
}

