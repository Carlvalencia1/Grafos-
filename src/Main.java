import java.util.List;
import java.util.Scanner;
import models.*;

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
                System.out.println("4) Ejecutar Dijkstra");
                System.out.println("5) Buscar centro más cercano con stock");
                System.out.println("6) Salir");
                System.out.print("Indique la opción que desee elegir: ");
                opcion = validarEntrada(entrada, 1, 6);

                switch (opcion) {
                    case 1:
                        try {
                            System.out.println("Indica la cantidad de vertices del grafo");
                            cantidad = validarEntrada(entrada, 1, Integer.MAX_VALUE);

                            for (int i = 0; i < cantidad; i++) {
                                Vertice nuevoVertice = crearVertice(grafo, entrada);
                                if (nuevoVertice != null) {
                                    grafo.agregarNodo(nuevoVertice);
                                }
                            }
                            grafo.agregarAristas();
                        } catch (Exception e) {
                            System.out.println("Error: Ingrese datos numéricos");
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
                            System.out.println("Indique el vértice de origen:");
                            int origen = validarEntrada(entrada, 1, grafo.getNumeroVertices());
                            CaminoMinimo caminoMinimo = new CaminoMinimo(grafo, origen);
                            caminoMinimo.Dijkstra(grafo, origen);
                            System.out.println("Indique el vértice de destino:");
                            int destino = validarEntrada(entrada, 1, grafo.getNumeroVertices());
                            System.out.print("El camino más corto es: ");
                            caminoMinimo.recuperaCamino(destino);
                            System.out.println();
                        } catch (Exception e) {
                            System.out.println("Error: Ingrese datos numéricos");
                            entrada.next();
                        }
                        break;
                    case 5:
                        try {
                            System.out.println("Indique el vértice de origen:");
                            int origen = validarEntrada(entrada, 1, grafo.getNumeroVertices());
                            CaminoMinimo caminoMinimo = new CaminoMinimo(grafo, origen);
                            caminoMinimo.Dijkstra(grafo, origen);
                            Vertice centroMasCercano = grafo.buscarCentroMasCercanoConStock(caminoMinimo, origen);
                            if (centroMasCercano != null) {
                                System.out.println("El centro más cercano con stock es: " + centroMasCercano.getDato());
                            } else {
                                System.out.println("No hay centros con stock disponible.");
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
                System.out.println("Error: Ingrese datos numéricos válidos.");
                entrada.next();
                opcion = 0;
            }
        } while (opcion != 6);
    }

    public static int validarEntrada(Scanner entrada, int min, int max) {
        int valor;
        while (true) {
            try {
                valor = entrada.nextInt();
                if (valor < min || valor > max) {
                    System.out.println("Error: Ingrese un número entre " + min + " y " + max + ".");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese datos numéricos.");
                entrada.next(); // Limpiar la entrada incorrecta
            }
        }
        return valor;
    }

    public static Vertice crearVertice(Grafo grafo, Scanner entrada) {
        Vertice nuevoVertice = null;
        boolean flag;
        List<Vertice> listaDeVertices = grafo.getVertices();

        do {
            flag = false;
            System.out.println("Ingresa el valor del vertice (Multiplo de 14)");
            try {
                int vertice = validarEntrada(entrada, 14, Integer.MAX_VALUE);

                if (vertice % 14 == 0) { // Validación de múltiplo de 14
                    for (int i = 0; i < listaDeVertices.size() && !flag; i++) {
                        if (listaDeVertices.get(i).getDato() == vertice) { // Validación para repetidos
                            System.out.println("El vértice ya existe. Introduzca un valor diferente.");
                            flag = true;
                        }
                    }

                    if (!flag) {
                        System.out.println("Indica el stock del artículo X para el vértice:");
                        int stock = validarEntradaStock(entrada);
                        nuevoVertice = new Vertice(vertice, stock);
                    }
                } else {
                    System.out.println("El valor debe ser múltiplo de 14.");
                    flag = true;
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese datos numéricos");
                entrada.next();
                flag = true;
            }
        } while (flag);

        return nuevoVertice;
    }

    public static int validarEntradaStock(Scanner entrada) {
        int valor;
        while (true) {
            try {
                valor = entrada.nextInt();
                if (valor < 0) {
                    System.out.println("Error: Ingrese un número mayor o igual a 0.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese datos numéricos.");
                entrada.next(); // Limpiar la entrada incorrecta
            }
        }
        return valor;
    }
}
