package aed;

public class Ciudad{
    private int ganancia;
    private int perdida;
    private int balance;
    private int id;
    private int posicionHeap;

    public Ciudad(){
        this.ganancia = 0;
        this.perdida = 0;
        this.balance = 0;
    }
    public int getBalance(){
        return this.balance;
    }
    public int getId(){
        return this.id;
    }
    public void setPosicionCiudad(int i){
        this.posicionHeap = i;
    }
}
