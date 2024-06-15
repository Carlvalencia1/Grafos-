import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.*;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion, cantidad;
        Grafo grafo = new Grafo();

        do {
            System.out.println("Menu");
            System.out.println("1) Hacer grafo");
            System.out.println("2) Imprimir grafo");
            System.out.println("3) Imprimir vertices");
            System.out.println("4) Salir");
            System.out.println("Indique la opción que desee elegir");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Indica la cantidad de vertices del grafo");
                    cantidad = entrada.nextInt();
                    for (int i = 0; i < cantidad; i++) {
                        Vertice nuevoVertice = crearVertice(grafo, entrada);
                        if (nuevoVertice != null) {
                            grafo.agregarNodo(nuevoVertice);
                        }
                    }
                    grafo.agregarAristas();
                    break;
                case 2:
                    System.out.println(grafo);
                    break;
                case 3:
                    List<Vertice> listaDeVertices = grafo.getVertices();
                    System.out.println("Los vertices son:");
                    for (Vertice vertice : listaDeVertices) {
                        System.out.print(vertice.getDato() + " ");
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    public static Vertice crearVertice(Grafo grafo, Scanner entrada) {
        Vertice nuevoVertice = null;
        boolean flag;
        List<Vertice> listaDeVertices = grafo.getVertices();

        do {
            flag = false;
            System.out.println("Ingresa el valor del vertice");
            int vertice = entrada.nextInt();

            if (vertice % 14 == 0) { // Validación de múltiplo de 14
                for (Vertice v : listaDeVertices) {
                    if (v.getDato() == vertice) { // Validación para repetidos
                        System.out.println("El vértice ya existe. Introduzca un valor diferente.");
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    nuevoVertice = new Vertice(vertice);
                }
            } else {
                System.out.println("El valor debe ser múltiplo de 14.");
            }
        } while (flag);

        return nuevoVertice;
    }
}
