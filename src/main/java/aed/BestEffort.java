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


        ArrayList<Traslado> trasladosarr = new Array// Actualizar las ganancias y pérdidas de las ciudades
        // Actualizar los balances
        // Verificar si la ciudad en origen tiene mayor ganancia que la primera en mayorGananciaList<Traslado>(Arrays.asList(traslados));
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

        
        ciudades[traslados[j].origen].setGanancia(traslados[j].getGananciaNeta());
        ciudades[traslados[j].destino].setPerdida(traslados[j].getGananciaNeta());

        
        ciudades[traslados[j].origen].sumarBalance(traslados[j].getGananciaNeta());
        ciudades[traslados[j].destino].restarBalance(traslados[j].getGananciaNeta());

        
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
            mayorPerdida.clear();nuevoArreglo.add(ciudades[traslados[j].origen]);
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
        ArrayList<Traslado> trasladosarr = new ArrayList<Traslado>(Arrays.asList(traslados));

        this.trasladosMasAntiguos.agregar(trasladosarr);
        this.trasladosMasRedituables.agregar(trasladosarr);
    }

    public int[] despacharMasRedituables(int n) {
        int i = 0;
        // Crear un arreglo de Traslados con tamaño n
        int[] res = new int[n];
    
        // Si n es mayor que la cantidad de traslados en trasladosMasRedituables
        if (n > trasladosMasRedituables.getSize()) {  // Asegúrate de tener un método size() en Heap
            while (i < trasladosMasRedituables.getSize()) {
                // Extraemos el primer traslado y lo asignamos al arreglo
                res[i] = trasladosMasRedituables.getRaiz().getId();  // O usa un método adecuado para obtener el primer elemento
                // Eliminar el primer traslado del Heap
                trasladosMasRedituables.eliminar();
                i++;
            }
        } else {
            // Si n es menor o igual a la cantidad de traslados disponibles
            while (i < n) {
                // Extraemos el primer traslado y lo asignamos al arreglo
                res[i] = trasladosMasRedituables.getRaiz().getId();  // O usa un método adecuado para obtener el primer elemento
                // Eliminar el primer traslado del Heap
                trasladosMasRedituables.eliminar();
                i++;
            }
        }
    
        return res;  // Devuelvo el arreglo con los traslados despachados
    }
    
    
        actualizarCiudades(this.ciudades[], res)
        int j = 0;
        while j < res.length

            this.mayorSuperavit = heapify(res[j].destino, balance);
            this.mayorSuperavit = heapify(res[j].origen, balance);

        this.trasladosMasAntiguos.eliminarConIds(res);                
        return res;
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

