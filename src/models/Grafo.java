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

            try {
                System.out.println("Seleccione el número del vértice origen: ");
                opcionOrigen = entrada.nextInt();
                System.out.println("Indique el número del vértice destino: ");
                opcionDestino = entrada.nextInt();

                if (opcionOrigen == opcionDestino) {
                    System.out.println("Los vertices deben ser distintos");
                    continue;
                }else if (opcionOrigen > listaVertices.size() || opcionDestino > listaVertices.size()){
                    System.out.println("El numero que ingrese debe ser de una de las opciones: del 1 al " + listaVertices.size());
                    continue;
                }else {
                    origen = listaVertices.get(opcionOrigen - 1);
                    destino = listaVertices.get(opcionDestino - 1);
                }

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Error: Ingrese un número válido.");
                continue;
            }

            if (existeArista(origen, destino)) {
                System.out.println("La arista entre " + origen.getDato() + " y " + destino.getDato() + " ya existe.");
                continue;
            }

            do {
                pesoValido = false;
                try{
                    System.out.println("Indique el peso ");
                    peso = entrada.nextInt();

                    if (peso <= 0) {
                        System.out.println("Introduzca un peso positivo.");
                        pesoValido = false;
                    }else {
                        pesoValido = true;
                    }
                }catch (Exception e){
                    System.out.println("Error: Ingrese datos numéricos");
                    entrada.next();
                }
            } while (pesoValido == false);

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

    public void imprimirRecorrido() {
        if(listaVertices.size() == 0) {
            System.out.println("Aún no hay vertices");
        }else {
            Stack<Vertice> pila = new Stack<>();
            ArrayList<Integer> verticesRecorridos = new ArrayList<>();

            pila.push(listaVertices.get(0));

            while(!pila.isEmpty()) {
                Vertice N = pila.pop();
                verticesRecorridos.add(N.getDato());

                List<Arista> vecinos = N.getAristas();

                if (vecinos != null) {
                    for (int i = 0; i < vecinos.size(); i++) {
                        pila.push(vecinos.get(i).getFin());
                    }
                }

            }

            String recorrido = "Recorrido de profundidad: ";
            for (int i = 0; i < verticesRecorridos.size(); i++) {
                recorrido += String.valueOf(verticesRecorridos.get(i));
                recorrido += ", ";
            }
            System.out.println(recorrido);
        }
    }

    @Override
    public String toString() {
        return "Grafo [" + getVertices() + "\n";
    }
}
