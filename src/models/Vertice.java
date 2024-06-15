package models;
import java.util.*;

public class Vertice {
    private int dato;
    private List<Arista> listaAristas;

    public Vertice(int valor) {
        this.dato = valor;
        this.listaAristas = new ArrayList<>();
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int valor) {
        this.dato = valor;
    }

    public List<Arista> getAristas() {
        return listaAristas;
    }

    public void setAristas(Arista arista) {
        listaAristas.add(arista);
    }

    @Override
    public String toString() {
        return "\n \t Vertice = " + getDato() + ", Aristas = " + getAristas();
    }
}
