package aed;

public class Traslado {
    
    int id;
    int origen;
    int destino;
    int gananciaNeta;
    int timestamp;
    int posicionTimestamp;
    int posicionGanancia;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.gananciaNeta = gananciaNeta;
        this.timestamp = timestamp;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public int getGananciaNeta() {
        return this.gananciaNeta;
    }

    public void setPosicionGanancia(int i) {
        this.posicionGanancia = i;
    }

    public void setPosicionTimestamp(int i) {
        this.posicionTimestamp = i;
    }
    public int getPosicionGanancia() {
        return this.posicionGanancia;
    }

    public int getPosicionTimestamp() {
        return this.posicionTimestamp;
    }
}
