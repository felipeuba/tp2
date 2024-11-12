package aed;

import java.util.ArrayList;
import java.util.List;

public class BestEffort {
    private Heap<Ciudad> mayorSuperavit;
    private ArrayList<Ciudad> mayorGanancia;
    private ArrayList<Ciudad> mayorPerdida;
    private ArrayList<Ciudad> ciudades;
    private Heap<Traslado> trasladosMasAntiguos;
    private Heap<Traslado> trasladosMasRedituables;
// NO PODEMOS USAR TUPLAS    private ##### gananciaTotal;

    public BestEffort(int cantCiudades, Traslado[] traslados) {
        ciudades = new Ciudad[cantCiudades];
        for (int i = 0; i < cantCiudades; i++) {
            ciudades[i] = new Ciudad();
        }
        mayorGanancia  = new ArrayList<>(cantCiudades);
        mayorPerdida = new ArrayList<>(cantCiudades);
        //esto evita el aliassing
        for (Ciudad ciudad : ciudades) {
            mayorGanancia.add(ciudad);
            mayorPerdida.add(ciudad);
        }
        gananciaTotal = (0,0);


//FELI, ESTÁ BIEN?

        mayorSuperavit = new Heap<>(new ArrayList<>(List.of(ciudades)), CustomComparator.BY_BALANCE);
        trasladosMasAntiguos = new Heap<>(new ArrayList<>(List.of(traslados)), CustomComparator.BY_TIMESTAMP);
        trasladosMasRedituables = new Heap<>(new ArrayList<>(List.of(traslados)), CustomComparator.BY_GANANCIA);
    }



    public void registrarTraslados(Traslado[] traslados){
        // Implementar
    }

    public int[] despacharMasRedituables(int n){
        // Implementar
        return null;
    }

    public int[] despacharMasAntiguos(int n){
        // Implementar
        return null;
    }

    public int ciudadConMayorSuperavit(){
        // Implementar
        return 0;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        // Implementar
        return null;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        // Implementar
        return null;
    }

    public int gananciaPromedioPorTraslado(){
        // Implementar
        return 0;
    }
    
}
