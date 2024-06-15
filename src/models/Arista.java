package models;

public class Arista {
    private Vertice inicio;
    private Vertice fin;
    private int peso;

    public Arista(Vertice inicio, Vertice fin, int peso) {
        this.inicio = inicio;
        this.fin = fin;
        this.peso = peso;
    }

    public Vertice getInicio() {
        return inicio;
    }

    public void setInicio(Vertice origen) {
        this.inicio = origen;
    }

    public Vertice getFin() {
        return fin;
    }

    public void setFin(Vertice destino) {
        this.fin = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int valor) {
        this.peso = valor;
    }

    @Override
    public String toString() {
        return "[" + inicio.getDato() + "-" + getPeso() + "-" + fin.getDato() + "]";
    }
}
