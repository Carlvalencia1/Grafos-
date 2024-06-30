package models;

import java.util.*;

public class Vertice {
    private int dato;
    private int stock; // Añadido para el stock del artículo
    private List<Arista> listaAristas;

    public Vertice(int valor, int stock) {
        this.dato = valor;
        this.stock = stock;
        this.listaAristas = new ArrayList<>();
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int valor) {
        this.dato = valor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Arista> getAristas() {
        return listaAristas;
    }

    public void setAristas(Arista arista) {
        listaAristas.add(arista);
    }

    @Override
    public String toString() {
        return "\n \t Vertice = " + getDato() + ", Stock = " + stock + ", Aristas = " + getAristas();
    }
}
