package TDALista;

import java.util.Iterator;

public class ListaDoblementeEnlazada<E> implements PositionList<E> {
	
	private DNodo<E> cabeza;
	private DNodo<E> rabo;
	private int tamaño;
	
	/**
	 * Crea una lista vacía.
	 */
	
	public ListaDoblementeEnlazada() {
		cabeza = new DNodo<E>(null);
		rabo = new DNodo<E>(null);
		cabeza.setAnterior(null); 
		cabeza.setSiguiente(rabo);
		rabo.setAnterior(cabeza);
		rabo.setSiguiente(null);
		tamaño = 0;
	}
	
	/**
	 * Inserta un elemento después de la posición pasada por parámetro.
	 * @param p Posición en cuya posición siguiente se insertará element.
	 * @param element Elemento a insertar luego de la posición p.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
	 */
	
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> pos = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(element);
		nuevo.setSiguiente(pos.getSiguiente());
		nuevo.setAnterior(pos);
		nuevo.getSiguiente().setAnterior(nuevo);
		pos.setSiguiente(nuevo);
		tamaño++;
	}
	
	/**
	 * Inserta un elemento antes de la posición pasada como parámetro.
	 * @param p Posición en cuya posición anterior se insertará e. 
	 * @param element Elemento a insertar antes de la posición p.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
	 */
	
	public void addBefore(Position<E> p, E e) throws InvalidPositionException {
		DNodo<E> pos = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(e);
		pos.getAnterior().setSiguiente(nuevo);
		nuevo.setAnterior(pos.getAnterior());
		pos.setAnterior(nuevo);
		nuevo.setSiguiente(pos);
		tamaño++;
	}

	/**
	 * Remueve el elemento que se encuentra en la posición pasada por parámetro.
	 * @param p Posición del elemento a eliminar.
	 * @return element Elemento removido.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
	 */
	
	public E remove(Position<E> p) throws InvalidPositionException {
		DNodo<E> pos = checkPosition(p);
		pos.getAnterior().setSiguiente(pos.getSiguiente());
		pos.getSiguiente().setAnterior(pos.getAnterior());
		tamaño--;
		pos.setAnterior(null);
		pos.setSiguiente(null);
		return pos.element();
	}
	
	/**
	 * Checkea si la posición pasada por parámetro es válida.
	 * @param p Posición a checkear.
	 * @return si la posición es válida devuelve el nodo de dicha posición.
	 * @throws InvalidPositionException si la posición es nula, p fue eliminado previamente o p no es un nodo de la lista.
	 */
	
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		try {
			if(p == null) {
				throw new InvalidPositionException("Posición nula.");
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
		return tamaño;
	}
	
	/**
	 * Checkea si la lista está vacía.
	 * @return true si la lista está vacía, falso si no lo está.
	 */
	
	public boolean isEmpty() {
		return size()==0;
	}

	/**
	 * Devuelve la posición del primer elemento de la lista. 
	 * @return Posición del primer elemento de la lista.
	 * @throws EmptyListException si la lista está vacía.
	 */
	
	public Position<E> first() throws EmptyListException {
		if(isEmpty()) {
			throw new EmptyListException("La lista está vacía.");
		}
		return cabeza.getSiguiente();
	}

	/**
	 * Devuelve la posición del último elemento de la lista. 
	 * @return Posición del último elemento de la lista.
	 * @throws EmptyListException si la lista está vacía.
	 * 
	 */
	
	public Position<E> last() throws EmptyListException {
		if(isEmpty()) {
			throw new EmptyListException("La lista está vacía.");
		}
		return rabo.getAnterior();
	}

	/**
	 * Devuelve la posición del elemento anterior a la posición pasada por parámetro.
	 * @param p Posición a obtener su elemento anterior.
	 * @return Posición del elemento anterior a la posición p.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o la lista está vacía.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde al primer elemento de la lista.
	 */
	
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		if(p == null) {
			throw new InvalidPositionException("Posición inválida.");
		}
		DNodo<E> pos = checkPosition(p);
		if (pos.getAnterior() == cabeza) {
			throw new BoundaryViolationException("La posición ingresada es el primer elemento.");
		}
		return pos.getAnterior();
	}

	/**
	 * Devuelve la posición del elemento siguiente a la posición pasada por parámetro.
	 * @param p Posición a obtener su elemento siguiente.
	 * @return Posición del elemento siguiente a la posición p.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o la lista está vacía.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde al último elemento de la lista.
	 */
	
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		if(p == null) {
			throw new InvalidPositionException("Posición inválida.");
		}
		DNodo<E> pos = checkPosition(p);
		if(pos.getSiguiente() == rabo) {
			throw new BoundaryViolationException("La posición ingresada es el último elemento.");
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
		tamaño++;
	}

	/**
	 * Inserta un elemento al final de la lista.
	 * @param e Elemento a insertar al final de la lista.
	 */
	
	public void addLast(E e) {
		DNodo<E> nuevo = new DNodo<E>(e, rabo, rabo.getAnterior());
		rabo.getAnterior().setSiguiente(nuevo);
		rabo.setAnterior(nuevo);
		tamaño++;
	}

	/**
	 * Establece el elemento e en la posición p pasados por parámetro.
	 * @param p Posición a establecer el elemento e.
	 * @param e Elemento a establecer en la posición p.
	 * @return Elemento que fue reemplazado.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.	 
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
	 * Devuelve una colección iterable de posiciones.
	 * @return colección iterable de posiciones.
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
