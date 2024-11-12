package aed;

public class Ciudad{
    private int ganancia;
    private int perdida;
    private int balance;
    private int id;
    private int posicionHeap;

    public Ciudad(int i){
        this.ganancia = 0;
        this.perdida = 0;
        this.balance = 0;
        this.id = i;
    }
    public int getBalance(){
        return this.balance;
    }
    public int getPerdida(){
        return this.perdida;
    }
    public int getGanancia(){
        return this.ganancia;
    }
    public int getId(){
        return this.id;
    }
    public void setPosicionCiudad(int i){
        this.posicionHeap = i;
    }
    public void setGanancia(int n){
        this.ganancia += n;
    }
    public void setPerdida(int n){
        this.perdida += n;
    }
    public void sumarBalance(int n){
        this.balance += n;
    }
    public void restarBalance(int n){
        this.balance -= n;
    }
}
