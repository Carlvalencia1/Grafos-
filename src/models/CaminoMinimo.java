package models;

import java.util.*;

public class CaminoMinimo {
    static final int INFINITO = Integer.MAX_VALUE;
    private Grafo grafo;
    private int[] distancias;
    private boolean[] visitados;
    private int origen;

    public CaminoMinimo(Grafo grafo, int origen) {
        this.grafo = grafo;
        this.origen = origen - 1; // Ajuste para que el índice sea correcto
        int n = grafo.getNumeroVertices();
        distancias = new int[n];
        visitados = new boolean[n];
        Arrays.fill(distancias, INFINITO);
        distancias[this.origen] = 0;
    }

    public void Dijkstra(Grafo grafo, int origen) {
        int n = grafo.getNumeroVertices();
        int[][] matrizAdyacencia = grafo.getMatrizAdyacencia();

        for (int i = 0; i < n; i++) {
            int u = minDistancia();
            visitados[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visitados[v] && matrizAdyacencia[u][v] != INFINITO &&
                        distancias[u] != INFINITO && distancias[u] + matrizAdyacencia[u][v] < distancias[v]) {
                    distancias[v] = distancias[u] + matrizAdyacencia[u][v];
                }
            }
        }
    }

    private int minDistancia() {
        int min = INFINITO, minIndex = -1;

        for (int v = 0; v < distancias.length; v++) {
            if (!visitados[v] && distancias[v] <= min) {
                min = distancias[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    public void recuperaCamino(int destino) {
        List<Integer> camino = new ArrayList<>();
        int actual = destino - 1; // Ajuste para que el índice sea correcto

        if (distancias[actual] == INFINITO) {
            System.out.println("No hay camino desde " + (origen + 1) + " hasta " + destino);
            return;
        }

        while (actual != origen) {
            camino.add(actual + 1); // Ajuste para mostrar el vértice correcto
            int min = INFINITO;
            int next = -1;
            for (Arista arista : grafo.getVertices().get(actual).getAristas()) {
                int vecino = grafo.getVertices().indexOf(arista.getFin());
                if (distancias[vecino] + arista.getPeso() == distancias[actual] && distancias[vecino] < min) {
                    min = distancias[vecino];
                    next = vecino;
                }
            }
            if (next == -1) {
                System.out.println("No se pudo encontrar un camino válido.");
                return;
            }
            actual = next;
        }

        camino.add(origen + 1); // Añadir el origen al final del camino
        Collections.reverse(camino); // Invertir la lista para mostrar el camino desde el origen hasta el destino
        for (int vertice : camino) {
            System.out.print(vertice + " ");
        }
    }

    public int[] getDistancias() {
        return distancias;
    }
}
