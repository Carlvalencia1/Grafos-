package models;

import java.util.*;

public class Vertice {
    private int dato;
    private int stock;
    private List<Arista> listaAristas = new ArrayList<>();

    public Vertice(int dato, int stock) {
        this.dato = dato;
        this.stock = stock;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setAristas(Arista arista) {
        listaAristas.add(arista);
    }

    public List<Arista> getAristas() {
        return listaAristas;
    }

    @Override
    public String toString() {
        return "Vertice [dato=" + dato + ", stock=" + stock + ", aristas=" + listaAristas + "]";
    }
}
