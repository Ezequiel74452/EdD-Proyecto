package TDAPila;

public class PilaEnlazada<E> implements Stack<E> {

	protected Nodo<E> head;
	protected int tamaño;
	
	/**
	 * Se inicializa la pila vacía.
	 */
	
	public PilaEnlazada() {
		head = null;
		tamaño = 0;
	}
	
	/**
	 * Devuelve el tamaño de la pila.
	 * @return cantidad de elementos en la pila.
	 */
	
	public int size() {
		return tamaño;
	}
	
	/**
	 * Checkea si la pila está vacía.
	 * @return true si la pila está vacía, false en caso contrario.
	 */
	
	public boolean isEmpty() {
		return tamaño == 0;
	}
	
	/**
	 * Devuelve el elemento que está en el tope de la pila.
	 * @return elemento en el tope de la pila.
	 * @throws EmptyStackException si la pila está vacía.
	 */
	
	public E top() throws EmptyStackException {
		if(isEmpty()) {
			throw new EmptyStackException("La pila está vacía.");
		}
		return head.getElemento();
	}

	/**
	 * Añade un nuevo elemento a la pila.
	 * @param element Elemento a añadir al tope de la pila.
	 */
	
	public void push(E element) {
		Nodo<E> aux = new Nodo<E>(element, head);
		head = aux;
		tamaño++;
	}

	/**
	 * Elimina el elemento que está en el tope de la pila.
	 * @return elemento eliminado.
	 * @throws EmptyStackException si la pila estaba vacía.
	 */
	
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("La pila está vacía.");
		}
		E aux = head.getElemento();
		head = head.getSiguiente();
		tamaño--;
		return aux;
	}

}
