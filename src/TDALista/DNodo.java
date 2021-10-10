package TDALista;

public class DNodo <E> implements Position<E> {

	private E elemento;
	private DNodo<E> siguiente;
	private DNodo<E> anterior;
	
	/**
	 * Crea un nodo.
	 * @param item elemento actual.
	 * @param sig nodo siguiente.
	 * @param ant nodo anterior.
	 */
	
	public DNodo(E item, DNodo<E> sig, DNodo<E> ant) {
		elemento = item;
		siguiente = sig;
		anterior = ant;
	}
	
	/**
	 * Crea un nodo con un elemento, sin siguiente ni anterior.
	 * @param item elemento en el nodo.
	 */
	
	public DNodo(E item) {
		this(item, null, null);
	}
	
	/**
	 * Reemplaza el elemento por el pasado por parámetro.
	 * @param elemento que reemplaza al actual.
	 */
	
	public void setElemento(E elemento) {
		this.elemento = elemento;
	}
	
	/**
	 * Reemplaza el nodo siguiente.
	 * @param siguiente es el nodo que reemplaza al actual siguiente.
	 */
	
	public void setSiguiente(DNodo<E> siguiente) {
		this.siguiente = siguiente;
	}
	
	/**
	 * Reemplaza el nodo anterior.
	 * @param anterior es el nodo que reemplaza al actual anterior.
	 */
	
	public void setAnterior(DNodo<E> anterior) {
		this.anterior = anterior;
	}
	
	/**
	 * Devuelve el elemento actual.
	 * @return elemento en el nodo.
	 */
	
	public E element() {
		return elemento;
	}
	
	/**
	 * Devuelve el nodo siguiente.
	 * @return nodo siguiente.
	 */
	
	public DNodo<E> getSiguiente() {
		return siguiente;
	}
	
	/**
	 * Devuelve el nodo anterior.
	 * @return nodo anterior.
	 */
	
	public DNodo<E> getAnterior() {
		return anterior;
	}
	
}