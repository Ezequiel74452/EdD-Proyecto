package TDALista;

import java.util.Iterator;

public class ListaDoblementeEnlazada<E> implements PositionList<E> {
	
	private DNodo<E> cabeza;
	private DNodo<E> rabo;
	private int tama�o;
	
	/**
	 * Crea una lista vac�a.
	 */
	
	public ListaDoblementeEnlazada() {
		cabeza = new DNodo<E>(null);
		rabo = new DNodo<E>(null);
		cabeza.setAnterior(null); 
		cabeza.setSiguiente(rabo);
		rabo.setAnterior(cabeza);
		rabo.setSiguiente(null);
		tama�o = 0;
	}
	
	/**
	 * Inserta un elemento despu�s de la posici�n pasada por par�metro.
	 * @param p Posici�n en cuya posici�n siguiente se insertar� element.
	 * @param element Elemento a insertar luego de la posici�n p.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */
	
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> pos = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(element);
		nuevo.setSiguiente(pos.getSiguiente());
		nuevo.setAnterior(pos);
		nuevo.getSiguiente().setAnterior(nuevo);
		pos.setSiguiente(nuevo);
		tama�o++;
	}
	
	/**
	 * Inserta un elemento antes de la posici�n pasada como par�metro.
	 * @param p Posici�n en cuya posici�n anterior se insertar� e. 
	 * @param element Elemento a insertar antes de la posici�n p.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */
	
	public void addBefore(Position<E> p, E e) throws InvalidPositionException {
		DNodo<E> pos = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(e);
		pos.getAnterior().setSiguiente(nuevo);
		nuevo.setAnterior(pos.getAnterior());
		pos.setAnterior(nuevo);
		nuevo.setSiguiente(pos);
		tama�o++;
	}

	/**
	 * Remueve el elemento que se encuentra en la posici�n pasada por par�metro.
	 * @param p Posici�n del elemento a eliminar.
	 * @return element Elemento removido.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */
	
	public E remove(Position<E> p) throws InvalidPositionException {
		DNodo<E> pos = checkPosition(p);
		pos.getAnterior().setSiguiente(pos.getSiguiente());
		pos.getSiguiente().setAnterior(pos.getAnterior());
		tama�o--;
		pos.setAnterior(null);
		pos.setSiguiente(null);
		return pos.element();
	}
	
	/**
	 * Checkea si la posici�n pasada por par�metro es v�lida.
	 * @param p Posici�n a checkear.
	 * @return si la posici�n es v�lida devuelve el nodo de dicha posici�n.
	 * @throws InvalidPositionException si la posici�n es nula, p fue eliminado previamente o p no es un nodo de la lista.
	 */
	
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		try {
			if(p == null) {
				throw new InvalidPositionException("Posici�n nula.");
			}
			if (p.element() == null) {
				throw new InvalidPositionException("p eliminada previamente.");
			}
			return (DNodo<E>) p;
		} catch(ClassCastException e) {
			throw new InvalidPositionException("p no es un nodo de lista.");
		}
	}

	/**
	 * Devuelve la cantidad de elementos de la lista.
	 * @return Cantidad de elementos de la lista.
	 */
	
	public int size() {
		return tama�o;
	}
	
	/**
	 * Checkea si la lista est� vac�a.
	 * @return true si la lista est� vac�a, falso si no lo est�.
	 */
	
	public boolean isEmpty() {
		return size()==0;
	}

	/**
	 * Devuelve la posici�n del primer elemento de la lista. 
	 * @return Posici�n del primer elemento de la lista.
	 * @throws EmptyListException si la lista est� vac�a.
	 */
	
	public Position<E> first() throws EmptyListException {
		if(isEmpty()) {
			throw new EmptyListException("La lista est� vac�a.");
		}
		return cabeza.getSiguiente();
	}

	/**
	 * Devuelve la posici�n del �ltimo elemento de la lista. 
	 * @return Posici�n del �ltimo elemento de la lista.
	 * @throws EmptyListException si la lista est� vac�a.
	 * 
	 */
	
	public Position<E> last() throws EmptyListException {
		if(isEmpty()) {
			throw new EmptyListException("La lista est� vac�a.");
		}
		return rabo.getAnterior();
	}

	/**
	 * Devuelve la posici�n del elemento anterior a la posici�n pasada por par�metro.
	 * @param p Posici�n a obtener su elemento anterior.
	 * @return Posici�n del elemento anterior a la posici�n p.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o la lista est� vac�a.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde al primer elemento de la lista.
	 */
	
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		if(p == null) {
			throw new InvalidPositionException("Posici�n inv�lida.");
		}
		DNodo<E> pos = checkPosition(p);
		if (pos.getAnterior() == cabeza) {
			throw new BoundaryViolationException("La posici�n ingresada es el primer elemento.");
		}
		return pos.getAnterior();
	}

	/**
	 * Devuelve la posici�n del elemento siguiente a la posici�n pasada por par�metro.
	 * @param p Posici�n a obtener su elemento siguiente.
	 * @return Posici�n del elemento siguiente a la posici�n p.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o la lista est� vac�a.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde al �ltimo elemento de la lista.
	 */
	
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		if(p == null) {
			throw new InvalidPositionException("Posici�n inv�lida.");
		}
		DNodo<E> pos = checkPosition(p);
		if(pos.getSiguiente() == rabo) {
			throw new BoundaryViolationException("La posici�n ingresada es el �ltimo elemento.");
		}
		return pos.getSiguiente();
	}

	/**
	 * Inserta un elemento al principio de la lista.
	 * @param e Elemento a insertar al principio de la lista.
	 */
	
	public void addFirst(E e) {
		DNodo<E> nuevo = new DNodo<E>(e, cabeza.getSiguiente(), cabeza);
		cabeza.getSiguiente().setAnterior(nuevo);
		cabeza.setSiguiente(nuevo);
		tama�o++;
	}

	/**
	 * Inserta un elemento al final de la lista.
	 * @param e Elemento a insertar al final de la lista.
	 */
	
	public void addLast(E e) {
		DNodo<E> nuevo = new DNodo<E>(e, rabo, rabo.getAnterior());
		rabo.getAnterior().setSiguiente(nuevo);
		rabo.setAnterior(nuevo);
		tama�o++;
	}

	/**
	 * Establece el elemento e en la posici�n p pasados por par�metro.
	 * @param p Posici�n a establecer el elemento e.
	 * @param e Elemento a establecer en la posici�n p.
	 * @return Elemento que fue reemplazado.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.	 
	 */
	
	public E set(Position<E> p, E e) throws InvalidPositionException {
		DNodo<E> pos = checkPosition(p);
		E aux = pos.element();
		pos.setElemento(e);
		return aux;
	}

	/**
	 * Devuelve un iterador de todos los elementos de la lista.
	 * @return iterador con todos los elementos de la lista.
	 */
	
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	/**
	 * Devuelve una colecci�n iterable de posiciones.
	 * @return colecci�n iterable de posiciones.
	 */
	
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> p = new ListaDoblementeEnlazada<Position<E>>(); 
		if(!isEmpty()) {
			try {
				Position<E> pos = first();
				while (pos != last()) {
					p.addLast(pos);
					pos = next(pos);
				}
				p.addLast(pos);
			} catch (EmptyListException | BoundaryViolationException | InvalidPositionException e) {
				System.out.println(e.getMessage());
			} 
		}
		return p;
	}
	
}
