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
                System.out.println("4) Salir");
                System.out.print("Indique la opción que desee elegir: ");
                opcion = entrada.nextInt();

                switch (opcion) {
                    case 1:
                        try {
                            System.out.println("Indica la cantidad de vertices del grafo");
                            cantidad = entrada.nextInt();

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
        } while (opcion != 4);
    }

    public static Vertice crearVertice(Grafo grafo, Scanner entrada) {
        Vertice nuevoVertice = null;
        boolean flag;
        List<Vertice> listaDeVertices = grafo.getVertices();

        do {
            flag = false;
            System.out.println("Ingresa el valor del vertice");
            try {
                int vertice = entrada.nextInt();

                if (vertice % 14 == 0) { // Validación de múltiplo de 14
                    for (int i = 0; i < listaDeVertices.size() && flag == false; i++) {
                        if (listaDeVertices.get(i).getDato() == vertice) { // Validación para repetidos
                            System.out.println("El vértice ya existe. Introduzca un valor diferente.");
                            flag = true;
                        }
                    }

                    if (!flag) {
                        nuevoVertice = new Vertice(vertice);
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


}
