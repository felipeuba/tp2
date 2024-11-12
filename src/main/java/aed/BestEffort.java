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
        // Inicializar el arreglo de ciudades
        ciudades = new Ciudad[cantCiudades];
        
        // Crear las instancias de las ciudades
        for (int i = 0; i < cantCiudades; i++) {
            ciudades[i] = new Ciudad(i); // Asegúrate de que el constructor de Ciudad reciba un ID o similar
        }

        // Inicializar las listas de mayorGanancia y mayorPerdida
        mayorGanancia = new ArrayList<>(cantCiudades);
        mayorPerdida = new ArrayList<>(cantCiudades);

        // Añadir las ciudades a ambas listas
        for (Ciudad ciudad : ciudades) {
            mayorGanancia.add(ciudad);
            mayorPerdida.add(ciudad);
        }

        // Inicializar 'gananciaTotal' como un array de 2 elementos
        gananciaTotal = new int[]{0, 0}; 


        ArrayList<Traslado> trasladosarr = new ArrayList<Traslado>(Arrays.asList(traslados));
        ArrayList<Ciudad> ciudadesarr = new ArrayList<Ciudad>(Arrays.asList(ciudades));


        // Inicializar los heaps
        mayorSuperavit = new Heap<>(ciudadesarr, CustomComparator.BY_BALANCE);  // Pasar el arreglo de ciudades y el comparator
        trasladosMasAntiguos = new Heap<>(trasladosarr, CustomComparator.BY_TIMESTAMP);  // Ejemplo de comparator para Traslado
        trasladosMasRedituables = new Heap<>(trasladosarr, CustomComparator.BY_GANANCIA);  // Otro comparator para Traslado
    }

    // Método para actualizar las ciudades basándose en los traslados
private void actualizarCiudades(Ciudad[] ciudades, Traslado[] traslados) {
    int j = 0;
    ArrayList<Ciudad> nuevoArreglo = new ArrayList<>();  // Inicializa correctamente el ArrayList

    while (j < traslados.length) {
        gananciaTotal[0] = gananciaTotal[0] + traslados[j].getGananciaNeta();
        gananciaTotal[1] += 1;

        // Actualizar las ganancias y pérdidas de las ciudades
        ciudades[traslados[j].origen].setGanancia(traslados[j].getGananciaNeta());
        ciudades[traslados[j].destino].setPerdida(traslados[j].getGananciaNeta());

        // Actualizar los balances
        ciudades[traslados[j].origen].sumarBalance(traslados[j].getGananciaNeta());
        ciudades[traslados[j].destino].restarBalance(traslados[j].getGananciaNeta());

        // Verificar si la ciudad en origen tiene mayor ganancia que la primera en mayorGanancia
        if (mayorGanancia.get(0).getGanancia() < ciudades[traslados[j].origen].getGanancia()) {
            // Si tiene mayor ganancia, la ciudad en origen es la única en la lista
            mayorGanancia.clear();  // Limpiar la lista
            mayorGanancia.add(ciudades[traslados[j].origen]);  // Agregar la ciudad a la lista
        } else if (mayorGanancia.get(0).getGanancia() == ciudades[traslados[j].origen].getGanancia()) {
            // Si tiene ganancia igual, añadir a la lista
            mayorGanancia.add(ciudades[traslados[j].origen]);
        }

        // Verificar para mayorPerdida de la misma manera
        if (mayorPerdida.get(0).getPerdida() < ciudades[traslados[j].origen].getPerdida()) {
            mayorPerdida.clear();
            mayorPerdida.add(ciudades[traslados[j].origen]);
        } else if (mayorPerdida.get(0).getPerdida() == ciudades[traslados[j].origen].getPerdida()) {
            mayorPerdida.add(ciudades[traslados[j].origen]);
        }

        // Actualizar los balances de las ciudades
        ciudades[traslados[j].origen].sumarBalance (traslados[j].gananciaNeta);
        ciudades[traslados[j].destino].restarBalance (traslados[j].gananciaNeta);

        // Añadir las ciudades a nuevoArreglo (no es necesario para mayorGanancia, pero puede ser útil)
        nuevoArreglo.add(ciudades[traslados[j].origen]);
        nuevoArreglo.add(ciudades[traslados[j].destino]);

        j++;
    }

    // Aquí podrías devolver o usar el nuevoArreglo si lo necesitas después
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

