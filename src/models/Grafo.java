package models;

import java.util.*;

public class Grafo {
    static final int INFINITO = Integer.MAX_VALUE;
    private List<Vertice> listaVertices = new ArrayList<>();

    public void agregarNodo(Vertice verticeNuevo) {
        listaVertices.add(verticeNuevo);
    }

    public List<Vertice> getVertices() {
        return listaVertices;
    }

    public int getNumeroVertices() {
        return listaVertices.size();
    }

    public int[][] getMatrizAdyacencia() {
        int n = listaVertices.size();
        int[][] matrizAdyacencia = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizAdyacencia[i][j] = INFINITO;
            }
        }
        for (int i = 0; i < n; i++) {
            for (Arista arista : listaVertices.get(i).getAristas()) {
                int destino = listaVertices.indexOf(arista.getFin());
                matrizAdyacencia[i][destino] = arista.getPeso();
            }
        }
        return matrizAdyacencia;
    }

    public void agregarAristas() {
        int opcion = 1;
        int peso = 0;
        int opcionOrigen, opcionDestino;
        Vertice destino = null;
        Vertice origen = null;
        Scanner entrada = new Scanner(System.in);
        boolean pesoValido = false;

        do {
            for (int i = 0; i < listaVertices.size(); i++) {
                System.out.println((i + 1) + " )" + listaVertices.get(i).getDato());
            }

            while (true) {
                try {
                    System.out.println("Seleccione el número del vértice origen: ");
                    opcionOrigen = entrada.nextInt();
                    if (opcionOrigen <= 0 || opcionOrigen > listaVertices.size()) {
                        System.out.println("Error: Ingrese un número válido del 1 al " + listaVertices.size());
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Error: Ingrese datos numéricos");
                    entrada.next();
                }
            }

            while (true) {
                try {
                    System.out.println("Indique el número del vértice destino: ");
                    opcionDestino = entrada.nextInt();
                    if (opcionDestino <= 0 || opcionDestino > listaVertices.size()) {
                        System.out.println("Error: Ingrese un número válido del 1 al " + listaVertices.size());
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Error: Ingrese datos numéricos");
                    entrada.next();
                }
            }

            if (opcionOrigen == opcionDestino) {
                System.out.println("No acepta lazos");
                continue;
            } else {
                origen = listaVertices.get(opcionOrigen - 1);
                destino = listaVertices.get(opcionDestino - 1);
            }

            if (existeArista(origen, destino)) {
                System.out.println("La arista entre " + origen.getDato() + " y " + destino.getDato() + " ya existe.");
                continue;
            }

            do {
                pesoValido = false;
                try {
                    System.out.println("Indique el peso ");
                    peso = entrada.nextInt();
                    if (peso <= 0) {
                        System.out.println("Introduzca un peso positivo.");
                        pesoValido = false;
                    } else {
                        pesoValido = true;
                    }
                } catch (Exception e) {
                    System.out.println("Error: Ingrese datos numéricos");
                    entrada.next();
                }
            } while (!pesoValido);

            origen.setAristas(new Arista(origen, destino, peso));
            System.out.println("Agregar más aristas: 1)Sí\t2)No");
            opcion = entrada.nextInt();

        } while (opcion == 1);
    }

    private boolean existeArista(Vertice origen, Vertice destino) {
        for (Arista arista : origen.getAristas()) {
            if (arista.getFin().equals(destino)) {
                return true;
            }
        }
        return false;
    }

    public void imprimirRecorrido() {
        if (listaVertices.size() == 0) {
            System.out.println("Aún no hay vertices");
        } else {
            Set<Vertice> visitados = new HashSet<>();
            Stack<Vertice> pila = new Stack<>();
            ArrayList<Integer> verticesRecorridos = new ArrayList<>();

            pila.push(listaVertices.get(0));

            while (!pila.isEmpty()) {
                Vertice N = pila.pop();
                if (!visitados.contains(N)) {
                    verticesRecorridos.add(N.getDato());
                    visitados.add(N);

                    List<Arista> vecinos = N.getAristas();

                    if (vecinos != null) {
                        for (Arista arista : vecinos) {
                            Vertice vecino = arista.getFin();
                            if (!visitados.contains(vecino)) {
                                pila.push(vecino);
                            }
                        }
                    }
                }
            }

            String recorrido = "Recorrido de profundidad: ";
            for (int i = 0; i < verticesRecorridos.size(); i++) {
                recorrido += String.valueOf(verticesRecorridos.get(i));
                if (i < verticesRecorridos.size() - 1) {
                    recorrido += ", ";
                }
            }
            System.out.println(recorrido);
        }
    }

    public Vertice buscarCentroMasCercanoConStock(CaminoMinimo caminoMinimo, int origen) {
        int[] distancias = caminoMinimo.getDistancias();
        Vertice centroMasCercano = null;
        int distanciaMinima = INFINITO;

        for (Vertice vertice : listaVertices) {
            if (vertice.getDato() != origen && vertice.getStock() > 0) {
                int distancia = distancias[listaVertices.indexOf(vertice)];
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    centroMasCercano = vertice;
                }
            }
        }

        return centroMasCercano;
    }

    @Override
    public String toString() {
        return "Grafo [" + listaVertices + "\n";
    }
}
