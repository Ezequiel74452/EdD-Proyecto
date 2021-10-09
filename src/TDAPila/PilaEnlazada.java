package TDAPila;

public class PilaEnlazada<E> implements Stack<E> {

	protected Nodo<E> head;
	protected int tama�o;
	
	/**
	 * Se inicializa la pila vac�a.
	 */
	
	public PilaEnlazada() {
		head = null;
		tama�o = 0;
	}
	
	/**
	 * Devuelve el tama�o de la pila.
	 * @return cantidad de elementos en la pila.
	 */
	
	public int size() {
		return tama�o;
	}
	
	/**
	 * Checkea si la pila est� vac�a.
	 * @return true si la pila est� vac�a, false en caso contrario.
	 */
	
	public boolean isEmpty() {
		return tama�o == 0;
	}
	
	/**
	 * Devuelve el elemento que est� en el tope de la pila.
	 * @return elemento en el tope de la pila.
	 * @throws EmptyStackException si la pila est� vac�a.
	 */
	
	public E top() throws EmptyStackException {
		if(isEmpty()) {
			throw new EmptyStackException("La pila est� vac�a.");
		}
		return head.getElemento();
	}

	/**
	 * A�ade un nuevo elemento a la pila.
	 * @param element Elemento a a�adir al tope de la pila.
	 */
	
	public void push(E element) {
		Nodo<E> aux = new Nodo<E>(element, head);
		head = aux;
		tama�o++;
	}

	/**
	 * Elimina el elemento que est� en el tope de la pila.
	 * @return elemento eliminado.
	 * @throws EmptyStackException si la pila estaba vac�a.
	 */
	
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("La pila est� vac�a.");
		}
		E aux = head.getElemento();
		head = head.getSiguiente();
		tama�o--;
		return aux;
	}

}
