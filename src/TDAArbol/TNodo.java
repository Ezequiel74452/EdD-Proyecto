package TDAArbol;

import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class TNodo<E> implements Position<E> {

	private E elemento;
	private TNodo<E> padre;
	private PositionList<TNodo<E>> hijos;

	/**
	 * Inicializa un nuevo TNodo.
	 * @param e es el elemento del nodo.
	 * @param padre es el nodo padre.
	 */
	
	public TNodo(E e, TNodo<E> padre) {
		elemento = e;
		this.padre = padre;
		hijos = new ListaDoblementeEnlazada<TNodo<E>>();
	}

	/**
	 * Inicializa un nuevo TNodo sin padre.
	 * @param e es el elemento del nodo.
	 */
	
	public TNodo(E e) {
		this(e, null);
	}

	/**
	 * Devuelve el elemento.
	 * @return elemento del nodo.
	 */
	
	public E element() {
		return elemento;
	}

	/**
	 * Devuelve una lista con los hijos del nodo.
	 * @return lista con hijos del nodo.
	 */
	
	public PositionList<TNodo<E>> getHijos() {
		return hijos;
	}

	/**
	 * Reemplaza el elemento del nodo.
	 * @param e elemento que reemplazará al actual.
	 */
	
	public void setElemento(E e) {
		elemento = e;
	}

	/**
	 * Devuelve el nodo padre.
	 * @return nodo padre.
	 */
	
	public TNodo<E> getPadre() {
		return padre;
	}

	/**
	 * Reemplaza el nodo padre.
	 * @param padre es el nuevo padre.
	 */
	
	public void setPadre(TNodo<E> padre) {
		this.padre = padre;
	}
}
