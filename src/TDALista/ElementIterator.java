package TDALista;

import java.util.Iterator;

public class ElementIterator<E> implements Iterator<E> {

	protected PositionList<E> lista;
	protected Position<E> cursor;
	
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
	
	public boolean hasNext() {
		return cursor != null;
	}

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