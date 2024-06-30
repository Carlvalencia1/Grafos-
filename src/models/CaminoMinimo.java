package models;

import java.util.*;

public class CaminoMinimo {
    int[] D;
    int[][] pesos;
    int[] P;
    int n, origen;
    boolean[] F;
    Grafo grafo; // Añadido para almacenar el grafo

    public CaminoMinimo(Grafo g, int origen) {
        this.grafo = g; // Asignar el grafo a la variable de clase
        this.n = g.getNumeroVertices();
        this.origen = origen;
        this.pesos = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (Arista a : g.getVertices().get(i).getAristas()) {
                int j = g.getVertices().indexOf(a.getFin());
                pesos[i][j] = a.getPeso();
            }
        }
        this.D = new int[n];
        this.P = new int[n];
        this.F = new boolean[n];
    }

    public void Dijkstra(Grafo g, int s) {
        for (int i = 0; i < n; i++) {
            F[i] = false;
            D[i] = Integer.MAX_VALUE;
            P[i] = -1;
        }
        D[s] = 0;
        for (int i = 0; i < n; i++) {
            int v = minimo();
            F[v] = true;
            for (int w = 0; w < n; w++) {
                if (!F[w] && pesos[v][w] != 0 && D[v] + pesos[v][w] < D[w]) {
                    D[w] = D[v] + pesos[v][w];
                    P[w] = v;
                }
            }
        }
    }

    private int minimo() {
        int mx = Integer.MAX_VALUE;
        int v = -1;
        for (int i = 0; i < n; i++) {
            if (!F[i] && D[i] < mx) {
                mx = D[i];
                v = i;
            }
        }
        return v;
    }

    public void mostrarResultados(Grafo g, int origen) {
        System.out.println("Origen: " + g.getVertices().get(origen).getDato());
        for (int i = 0; i < n; i++) {
            if (i != origen) {
                System.out.print("Distancia al vértice " + g.getVertices().get(i).getDato() + ": " + D[i] + " - Camino: ");
                recuperarCamino(i);
                System.out.println(g.getVertices().get(i).getDato());
            }
        }
    }

    private void recuperarCamino(int v) {
        if (P[v] != -1) {
            recuperarCamino(P[v]);
            System.out.print(grafo.getVertices().get(P[v]).getDato() + " -> "); // Se usa 'grafo' en lugar de 'g'
        }
    }
}
