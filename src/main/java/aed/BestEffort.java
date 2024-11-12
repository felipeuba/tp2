package aed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BestEffort {
    private Heap<Ciudad> mayorSuperavit;
    private ArrayList<Ciudad> mayorGanancia;
    private ArrayList<Ciudad> mayorPerdida;
    private ArrayList<Integer> mayorGananciaIds;
    private ArrayList<Integer> mayorPerdidaIds;
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
        mayorGananciaIds = new ArrayList<>(cantCiudades);
        mayorPerdidaIds = new ArrayList<>(cantCiudades);

        
        for (Ciudad ciudad : ciudades) {
            mayorGanancia.add(ciudad);
            mayorPerdida.add(ciudad);
            mayorGananciaIds.add(ciudad.getId());
            mayorPerdidaIds.add(ciudad.getId());
        }

        
        gananciaTotal = new int[]{0, 0}; 


        ArrayList<Traslado> trasladosGanancia = new ArrayList<Traslado>(Arrays.asList(traslados));
        ArrayList<Traslado> trasladosTimestamp = new ArrayList<Traslado>(Arrays.asList(traslados));
        ArrayList<Ciudad> ciudadesarr = new ArrayList<Ciudad>(Arrays.asList(ciudades));


        
        mayorSuperavit = new Heap<>(ciudadesarr, CustomComparator.BY_BALANCE);  
        trasladosMasRedituables = new Heap<>(trasladosGanancia, CustomComparator.BY_GANANCIA);  
        trasladosMasAntiguos = new Heap<>(trasladosTimestamp, CustomComparator.BY_TIMESTAMP);  
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
            mayorGananciaIds.clear();
            mayorGanancia.trimToSize();
            mayorGananciaIds.trimToSize();  
            mayorGanancia.add(ciudades[traslados[j].origen]); 
            mayorGananciaIds.add(ciudades[traslados[j].origen].getId());  
        } else if (mayorGanancia.get(0).getGanancia() == ciudades[traslados[j].origen].getGanancia()) {
            mayorGananciaIds.add(ciudades[traslados[j].origen].getId());
            mayorGanancia.add(ciudades[traslados[j].origen]);
        }

        
        if (mayorPerdida.get(0).getPerdida() < ciudades[traslados[j].destino].getPerdida()) {
            mayorPerdida.clear();  
            mayorPerdidaIds.clear();
            mayorPerdida.trimToSize();
            mayorPerdidaIds.trimToSize();  
            mayorPerdida.add(ciudades[traslados[j].destino]); 
            mayorPerdidaIds.add(ciudades[traslados[j].destino].getId()); 

        } else if (mayorPerdida.get(0).getPerdida() == ciudades[traslados[j].destino].getPerdida()) {
            mayorPerdidaIds.add(ciudades[traslados[j].destino].getId());
            mayorPerdida.add(ciudades[traslados[j].destino]);
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
        return this.mayorSuperavit.getRaiz().getId();
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        
        return this.mayorGananciaIds;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        
        return this.mayorPerdidaIds;
    }

    public int gananciaPromedioPorTraslado(){
        
        return (int) (this.gananciaTotal[0] / gananciaTotal[1]);
    }
    
}


    
}

