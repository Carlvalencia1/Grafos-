package models;
import java.util.*;

public class Grafo {
    private List<Vertice> listaVertices = new ArrayList<>();

    public void agregarNodo(Vertice verticeNuevo) {
        listaVertices.add(verticeNuevo);
    }

    public List<Vertice> getVertices() {
        return listaVertices;
    }

    public void agregarAristas() {
        int opcion = 0;
        int peso;
        Vertice destino, origen;
        Scanner entrada = new Scanner(System.in);
        do {
            for (int i = 0; i < listaVertices.size(); i++) {
                System.out.println((i + 1) + " )" + listaVertices.get(i).getDato());
            }
            System.out.println("Seleccione el numero del vertice origen ");
            int opcionOrigen = entrada.nextInt();
            origen = listaVertices.get(opcionOrigen - 1);
            System.out.println("Indique el numero del vertice destino ");
            int opcionDestino = entrada.nextInt();
            destino = listaVertices.get(opcionDestino - 1);

            if (existeArista(origen, destino)) {
                System.out.println("La arista entre " + origen.getDato() + " y " + destino.getDato() + " ya existe.");
                continue;
            }

            do {
                System.out.println("Indique el peso ");
                peso = entrada.nextInt();
                if (peso <= 0) {
                    System.out.println("Introduzca un peso positivo.");
                }
            } while (peso <= 0);

            origen.setAristas(new Arista(origen, destino, peso));
            System.out.println("Agregar mas aristas: 1)Si\t2)No");
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

    @Override
    public String toString() {
        return "Grafo [" + getVertices() + "\n";
    }
}
