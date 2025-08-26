package colaDoble;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Clase que implementa una cola doble genérica.
 * Permite insertar y eliminar elementos tanto al inicio como al final.
 *
 * @param <Item> Tipo de dato almacenado en la cola.
 */
public class ColaDoble<Item> implements Iterable<Item> {
    private Node<Item> inicio, fin;
    private int tamanyo;
    private static final int INSERTA_INICIO = 1;
    private static final int INSERTA_FINAL = 2;
    private static final int SUPRIME_INICIO = 3;
    private static final int SUPRIME_FINAL = 4;
    private static final int TERMINAR = 5;
    /**
     * Clase privada estática que representa un nodo en la cola doble.
     *
     * @param <Item> Tipo de dato almacenado en el nodo.
     */
    private static class Node<Item> {
        private Item dato;
        private Node<Item> siguiente;
        private Node<Item> anterior;
    }
    /**
     * Constructor que inicializa una cola doble vacía.
     */
    public ColaDoble() {
        inicio = null;
        fin = null;
        tamanyo = 0;
    }
    /**
     * Verifica si la cola está vacía.
     * 
     * @return true si la cola está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return tamanyo == 0;
    }
    /**
     * Retorna el número de elementos en la cola.
     * 
     * @return El tamaño actual de la cola.
     */
    public int tamanyo() {
        return tamanyo;
    }
    /**
     * Inserta un elemento al inicio de la cola.
     * 
     * @param item El elemento a insertar.
     * @throws NullPointerException si se intenta insertar un elemento nulo.
     */
    public void insertaInicio(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node<Item> nuevo = new Node<>();
        nuevo.dato = item;
        if (estaVacia()) {
            inicio = fin = nuevo;
        } else {
            nuevo.siguiente = inicio;
            inicio.anterior = nuevo;
            inicio = nuevo;
        }
        tamanyo++;
    }
    /**
     * Inserta un elemento al final de la cola.
     * 
     * @param item El elemento a insertar.
     * @throws NullPointerException si se intenta insertar un elemento nulo.
     */
    public void insertaFinal(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node<Item> nuevo = new Node<>();
        nuevo.dato = item;
        if (estaVacia()) {
            inicio = fin = nuevo;
        } else {
            nuevo.anterior = fin;
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamanyo++;
    }
    /**
     * Elimina y retorna el elemento al inicio de la cola.
     * 
     * @return El elemento eliminado.
     * @throws NoSuchElementException si la cola está vacía.
     */
    public Item suprimeInicio() {
        if (estaVacia()) {
            throw new NoSuchElementException();
        }
        Item item = inicio.dato;
        inicio = inicio.siguiente;
        if (inicio == null) {
            fin = null;
        } else {
            inicio.anterior = null;
        }
        tamanyo--;
        return item;
    }
    /**
     * Elimina y retorna el elemento al final de la cola.
     * 
     * @return El elemento eliminado.
     * @throws NoSuchElementException si la cola está vacía.
     */
    public Item suprimeFinal() {
        if (estaVacia()) {
            throw new NoSuchElementException();
        }
        Item item = fin.dato;
        fin = fin.anterior;
        if (fin == null) {
            inicio = null;
        } else {
            fin.siguiente = null;
        }
        tamanyo--;
        return item;
    }
    /**
     * Retorna un iterador que recorre la cola desde el inicio hacia el final.
     * 
     * @return Un iterador para los elementos en la cola.
     */
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator(inicio);
    }
    /**
     * Clase privada que implementa el iterador para la cola.
     */
    private class ListIterator implements Iterator<Item> {
        private Node<Item> actual;
        /**
         * Constructor del iterador.
         * 
         * @param nodoInicio El nodo inicial desde donde comienza el iterador.
         */
        ListIterator(Node<Item> nodoInicio) {
            actual = nodoInicio;
        }
        @Override
        public boolean hasNext() {
            return actual != null;
        }
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = actual.dato;
            actual = actual.siguiente;
            return item;
        }
    }
    /**
     * Método principal que permite ejecutar operaciones en la cola desde la consola.
     * Se esperan comandos desde un archivo o desde la entrada estándar.
     * 
     * @param args El archivo de entrada (opcional).
     */
    public static void main(String[] args) {
        ColaDoble<Integer> cola = new ColaDoble<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Opciones: " + INSERTA_INICIO + " (InsertaInicio), " + INSERTA_FINAL + " (InsertaFinal), " + SUPRIME_INICIO + " (SuprimeInicio), " + SUPRIME_FINAL + " (SuprimeFinal), " + TERMINAR + " (Salir)");
        while (true) {
            System.out.println("Elige una opción:");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case INSERTA_INICIO:
                    System.out.println("Inserta un número al inicio:");
                    int numInicio = scanner.nextInt();
                    cola.insertaInicio(numInicio);
                    break;
                case INSERTA_FINAL:
                    System.out.println("Inserta un número al final:");
                    int numFinal = scanner.nextInt();
                    cola.insertaFinal(numFinal);
                    break;
                case SUPRIME_INICIO:
                    try {
                        System.out.println("Elemento suprimido del inicio: " + cola.suprimeInicio());
                    } catch (NoSuchElementException e) {
                        System.out.println("La cola está vacía.");
                    }
                    break;
                case SUPRIME_FINAL:
                    try {
                        System.out.println("Elemento suprimido del final: " + cola.suprimeFinal());
                    } catch (NoSuchElementException e) {
                        System.out.println("La cola está vacía.");
                    }
                    break;
                case TERMINAR:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
