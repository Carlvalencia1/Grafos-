import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import models.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;
        int cantidad = 0;
        Grafo grafo = new Grafo();

        do {
            try {
                System.out.println("Menu");
                System.out.println("1) Hacer grafo");
                System.out.println("2) Imprimir grafo");
                System.out.println("3) Imprimir recorrido de profundidad");
                System.out.println("4) Encontrar camino más corto (Dijkstra)");
                System.out.println("5) Encontrar centro más cercano con stock");
                System.out.println("6) Salir");
                System.out.print("Indique la opción que desee elegir: ");
                opcion = entrada.nextInt();

                switch (opcion) {
                    case 1:
                        try {
                            System.out.println("Indica la cantidad de vértices del grafo:");
                            cantidad = entrada.nextInt();

                            for (int i = 0; i < cantidad; i++) {
                                Vertice nuevoVertice = crearVertice(grafo, entrada);
                                if (nuevoVertice != null) {
                                    grafo.agregarNodo(nuevoVertice);
                                }
                            }
                            grafo.agregarAristas();
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Ingrese datos numéricos válidos.");
                            entrada.next();
                        }
                        break;

                    case 2:
                        System.out.println(grafo);
                        break;
                    case 3:
                        grafo.imprimirRecorrido();
                        break;
                    case 4:
                        try {
                            System.out.println("Indica el vértice de origen (comenzando desde 1):");
                            int origen = entrada.nextInt() - 1;
                            if (origen >= 0 && origen < grafo.getNumeroVertices()) {
                                CaminoMinimo caminoMinimo = new CaminoMinimo(grafo, origen);
                                caminoMinimo.Dijkstra(grafo, origen);
                                caminoMinimo.mostrarResultados(grafo, origen);
                            } else {
                                System.out.println("Vértice no válido.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error: Ingrese datos numéricos");
                            entrada.next();
                        }
                        break;

                    case 5:
                        try {
                            System.out.println("Indica el vértice que requiere el artículo (comenzando desde 1):");
                            int centro = entrada.nextInt() - 1;
                            if (centro >= 0 && centro < grafo.getNumeroVertices()) {
                                Vertice centroCercano = grafo.centroMasCercanoConStock(centro);
                                if (centroCercano != null) {
                                    System.out.println("El centro más cercano con stock es el vértice: " + centroCercano.getDato());
                                } else {
                                    System.out.println("No hay centros de distribución con stock disponible.");
                                }
                            } else {
                                System.out.println("Vértice no válido.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error: Ingrese datos numéricos");
                            entrada.next();
                        }
                        break;

                    case 6:
                        System.out.println("Saliendo del programa.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese datos numéricos");
                entrada.next();
                opcion = 0;
            }
        } while (opcion != 6);
    }

    public static Vertice crearVertice(Grafo grafo, Scanner entrada) {
        Vertice nuevoVertice = null;
        boolean flag;
        List<Vertice> listaDeVertices = grafo.getVertices();

        do {
            flag = false;
            System.out.println("Ingresa el valor del vértice (múltiplo de 14 y mayor que 0):");
            try {
                int vertice = entrada.nextInt();
                if (vertice > 0 && vertice % 14 == 0) { // Validación de múltiplo de 14 y mayor que 0
                    System.out.println("Ingresa el stock del artículo:");
                    int stock = entrada.nextInt();

                    for (int i = 0; i < listaDeVertices.size() && flag == false; i++) {
                        if (listaDeVertices.get(i).getDato() == vertice) { // Validación para repetidos
                            System.out.println("El vértice ya existe. Introduce un valor diferente.");
                            flag = true;
                        }
                    }

                    if (!flag) {
                        nuevoVertice = new Vertice(vertice, stock);
                    }
                } else {
                    System.out.println("El valor del vértice debe ser mayor que 0 y múltiplo de 14.");
                    flag = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa datos numéricos válidos.");
                entrada.next();
                flag = true;
            }
        } while (flag);

        return nuevoVertice;
    }



}
