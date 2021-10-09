package TDAPila;

public class Nodo<E> {
	
	private E elemento;
	private Nodo<E> siguiente;
	
	/**
	 * Se crea un nuevo nodo.
	 * @param item es el primer elemento.
	 * @param sig es el nodo siguiente.
	 */
	
	public Nodo(E item, Nodo<E> sig) {
		elemento = item;
		siguiente = sig;
	}
	
	/**
	 * Se crea un nuevo nodo sin siguiente.
	 * @param item es el primer elemento.
	 */
	
	public Nodo(E item) {
		this(item, null);
	}
	
	/**
	 * Se reemplaza el primer elemento.
	 * @param elemento Es el elemento nuevo.
	 */
	
	public void setElemento(E elemento) {
		this.elemento = elemento;
	}
	
	/**
	 * Se añade o se reemplaza al nodo siguiente.
	 * @param siguiente Es el nuevo nodo.
	 */
	
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
	
	/**
	 * Devuelve el primer elemento.
	 * @return primer elemento.
	 */
	
	public E getElemento() {
		return elemento;
	}
	
	/**
	 * Devuelve el nodo siguiente.
	 * @return nodo siguiente.
	 */
	
	public Nodo<E> getSiguiente() {
		return siguiente;
	}
}