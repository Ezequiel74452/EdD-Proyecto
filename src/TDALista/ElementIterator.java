package TDALista;

import java.util.Iterator;

public class ElementIterator<E> implements Iterator<E> {

	protected PositionList<E> lista;
	protected Position<E> cursor;
	
	/**
	 * Inicializa el iterador.
	 * @param l lista a ser iterada.
	 */
	
	public ElementIterator(PositionList<E> l) {
		lista = l;
		if (lista.isEmpty()) {
			cursor = null;
		} else {
			try {
				cursor = lista.first();
			} catch (EmptyListException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Checkea si hay una posición siguiente a la del cursor actual
	 * @return false si cursor es null, true si cursor es distinto de null.
	 */
	
	public boolean hasNext() {
		return cursor != null;
	}

	/**
	 * Pasa el cursor a la siguiente posición, le asigna null cuando no hay más posiciones.
	 * @return elemento en la posición del cursor.
	 */
	
	public E next() {
		E ret = cursor.element();
		try {
			if(cursor != lista.last()) {
				cursor = lista.next(cursor);
			} else {
				cursor = null;
			}
		} catch (InvalidPositionException | BoundaryViolationException | EmptyListException e) {
			System.out.println(e.getMessage());
		}
		return ret;
	}

}