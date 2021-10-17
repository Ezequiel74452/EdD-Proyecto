package TDAArbol;

import java.util.Iterator; 
import TDALista.*;

public class Árbol<E> implements Tree<E> {

	protected TNodo<E> raiz;
	protected int size;

	/**
	 * Inicializa un árbol sin raíz y de tamaño 0.
	 */
	
	public Árbol() {
		raiz = null;
		size = 0;
	}

	/**
	 * Checkea que una posición sea válida.
	 * @param p posición a evaluar.
	 * @return posición casteada a TNodo si esta es válida.
	 * @throws InvalidPositionException si la p no es válida.
	 */
	
	private TNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		try {
			if (p == null || isEmpty()) {
				throw new InvalidPositionException("Posición nula");
			}
			return (TNodo<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("Clase casteada no es de tipo Position");
		}
	}
	
	/**
	 * Consulta la cantidad de nodos en el árbol.
	 * @return Cantidad de nodos en el árbol.
	 */
	
	public int size() {
		return size;
	}
	
	/**
	 * Consulta si el árbol está vacío.
	 * @return Verdadero si el árbol está vacío, falso en caso contrario.
	 */
	
	public boolean isEmpty() {
		return raiz == null;
	}

	/**
	 * Devuelve un iterador de los elementos almacenados en el árbol en preorden.
	 * @return Iterador de los elementos almacenados en el árbol.
	 */
	
	public Iterator<E> iterator() {
		PositionList<E> list = new ListaDoblementeEnlazada<E>();
		if (!isEmpty()) {
			preordenIT(list, raiz);
		}
		return list.iterator();
	}

	/**
	 * Devuelve una colección iterable de las posiciones de los nodos del árbol.
	 * @return Colección iterable de las posiciones de los nodos del árbol.
	 */
	
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> list = new ListaDoblementeEnlazada<Position<E>>();
		if (!isEmpty()) {
			preorden(list, raiz);
		}
		return list;
	}

	/**
	 * Reemplaza el elemento almacenado en la posición dada por el elemento pasado por parámetro. Devuelve el elemento reemplazado.
	 * @param v Posición de un nodo.
	 * @param e Elemento a reemplazar en la posición pasada por parámetro.
	 * @return Elemento reemplazado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(v);
		E removed = pos.element();
		pos.setElemento(e);
		return removed;
	}

	/**
	 * Devuelve la posición de la raíz del árbol.
	 * @return Posición de la raíz del árbol.
	 * @throws EmptyTreeException si el árbol está vacío.
	 */
	
	public Position<E> root() throws EmptyTreeException {
		if (raiz == null) {
			throw new EmptyTreeException("Arbol vacio");
		}
		return raiz;
	}

	/**
	 * Devuelve la posición del nodo padre del nodo correspondiente a una posición dada.
	 * @param v Posición de un nodo.
	 * @return Posición del nodo padre del nodo correspondiente a la posición dada.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde a la raíz del árbol.
	 */
	
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> pos = checkPosition(v);
		TNodo<E> padre = pos.getPadre();
		if (pos == raiz) {
			throw new BoundaryViolationException("No tiene padre");
		}
		return padre;
	}

	/**
	 * Devuelve una colección iterable de los hijos del nodo correspondiente a una posición dada.
	 * @param v Posición de un nodo.
	 * @return Colección iterable de los hijos del nodo correspondiente a la posición pasada por parámetro.
	 * @throws InvalidPositionException si la posición pasada por parómetro es inválida.
	 */
	
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(v);
		PositionList<Position<E>> list = new ListaDoblementeEnlazada<Position<E>>();
		for (TNodo<E> elem : pos.getHijos()) {
			list.addLast(elem);
		}
		return list;
	}

	/**
	 * Consulta si una posición corresponde a un nodo interno.
	 * @param v Posición de un nodo.
	 * @return Verdadero si la posición pasada por parámetro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(v);
		return !pos.getHijos().isEmpty();
	}

	/**
	 * Consulta si una posición dada corresponde a un nodo externo.
	 * @param v Posición de un nodo.
	 * @return Verdadero si la posición pasada por parámetro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(v);
		return pos.getHijos().isEmpty();
	}

	/**
	 * Consulta si una posición dada corresponde a la raíz del árbol.
	 * @param v Posición de un nodo.
	 * @return Verdadero, si la posición pasada por parámetro corresponde a la raíz del árbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(v);
		return pos.getPadre() == null;
	}

	/**
	 * Crea un nodo con rótulo e como raíz del árbol.
	 * @param E Rótulo que se asignará a la raíz del árbol.
	 * @throws InvalidOperationException si el árbol ya tiene un nodo raíz.
	 */
	
	public void createRoot(E e) throws InvalidOperationException {
		if (raiz != null) {
			throw new InvalidOperationException("El arbol ya tiene raiz");
		}
		TNodo<E> nodo = new TNodo<E>(e);
		raiz = nodo;
		size++;
	}

	/**
	 * Agrega un nodo con rótulo e como primer hijo de un nodo dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param padre Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 */
	
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> padre = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e, padre);
		padre.getHijos().addFirst(nuevo);
		size++;
		return nuevo;
	}

	/**
	 * Agrega un nodo con rótulo e como último hijo de un nodo dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 */
	
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> padre = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e, padre);
		padre.getHijos().addLast(nuevo);
		size++;
		return nuevo;
	}

	/**
	 * Agrega un nodo con rótulo e como hijo de un nodo padre dado. El nuevo nodo se agregará delante de otro nodo también dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @param rb Posición del nodo que será el hermano derecho del nuevo nodo.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida, o el árbol está vacío, o la posición rb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermano = checkPosition(rb);
		if (hermano.getPadre() != padre) {
			throw new InvalidPositionException("p no es padre de rb");
		}
		TNodo<E> nuevo = new TNodo<E>(e, padre);
		PositionList<TNodo<E>> hijosPadre = padre.getHijos();
		boolean es = false;
		try {
			Position<TNodo<E>> pp = hijosPadre.first();
			while (pp != null && !es) {
				if (hermano == pp.element()) {
					es = true;
				} else {
					pp = (pp != hijosPadre.last()) ? hijosPadre.next(pp) : null;
				}
			}
			hijosPadre.addBefore(pp, nuevo);
			size++;
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException ex) {
			ex.getMessage();
		}
		return nuevo;
	}

	/**
	 * Agrega un nodo con rótulo e como hijo de un nodo padre dado. El nuevo nodo se agregará a continuación de otro nodo también dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @param lb Posición del nodo que será el hermano izquierdo del nuevo nodo.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida, o el árbol está vacío, o la posición lb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermano = checkPosition(lb);
		if (hermano.getPadre() != padre) {
			throw new InvalidPositionException("p no es padre de lb");
		}
		TNodo<E> nuevo = new TNodo<E>(e, padre);
		PositionList<TNodo<E>> hijosPadre = padre.getHijos();
		boolean es = false;
		try {
			Position<TNodo<E>> pp = hijosPadre.first();
			while (pp != null && !es) {
				if (hermano == pp.element()) {
					es = true;
				} else {
					pp = (pp != hijosPadre.last()) ? hijosPadre.next(pp) : null;
				}
			}
			hijosPadre.addAfter(pp, nuevo);
			size++;
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException ex) {
			ex.getMessage();
		}
		return nuevo;
	}

	/**
	 * Elimina el nodo referenciado por una posición dada, si se trata de un nodo externo. 
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o no corresponde a un nodo externo, o el árbol está vacío.
	 */
	
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		if (isInternal(p)) {
			throw new InvalidPositionException("No es un nodo externo.");
		}
		TNodo<E> nodo = checkPosition(p);
		try {
			if (nodo == raiz) {
				raiz = null;
				size = 0;
			} else {
				TNodo<E> padre = nodo.getPadre();
				PositionList<TNodo<E>> hijos = padre.getHijos();
				Position<TNodo<E>> pos = null;
				boolean encontre = false;
				Iterator<Position<TNodo<E>>> it = hijos.positions().iterator();
				while (it.hasNext() && !encontre) {
					pos = it.next();
					if (pos.element() == nodo) {
						encontre = true;
						hijos.remove(pos);
						nodo.setPadre(null);
						nodo.setElemento(null);
						size--;
					}
				}
				if (!encontre) {
					throw new EmptyListException("P no pertenece al árbol.");
				}
			}
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Elimina el nodo referenciado por una posición dada, si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raíz del árbol, únicamente podrá ser eliminado si tiene un solo hijo, el cual lo reemplazará.
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o no corresponde a un nodo interno o corresponde a la raíz (con más de un hijo), o el árbol está vacío.
	 */
	
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		if (isExternal(p)) {
			throw new InvalidPositionException("p no es un nodo interno");
		}
		if (nodo == raiz) {
			if (nodo.getHijos().size() > 1) {
				throw new InvalidPositionException("el nodo raiz a eliminar no puede tener mas de 1 hijo");
			} else {
				try {
					raiz = nodo.getHijos().first().element();
					nodo.setElemento(null);
					nodo.getHijos().remove(nodo.getHijos().first());
					raiz.setPadre(null);
				} catch (EmptyListException e) {
					System.out.println(e.getMessage());
				}
			}
		} else {
			TNodo<E> pa = nodo.getPadre();
			PositionList<TNodo<E>> hijos = pa.getHijos();
			Iterator<Position<TNodo<E>>> it = hijos.positions().iterator();
			Position<TNodo<E>> me = null;
			boolean encontre = false;
			while (it.hasNext() && !encontre) {
				me = it.next();
				encontre = me.element() == nodo;
			}
			if (!encontre) {
				throw new InvalidPositionException("N no figura como hijo de su padre");
			}
			PositionList<TNodo<E>> hijosN = nodo.getHijos();
			it = hijosN.positions().iterator();
			while (it.hasNext()) {
				TNodo<E> nh = it.next().element();
				nh.setPadre(pa);
				hijos.addBefore(me, nh);
			}
			hijos.remove(me);
			nodo.setPadre(null);
			nodo.setElemento(null);
			it = hijosN.positions().iterator();
			while (it.hasNext()) {
				hijosN.remove(it.next());
			}
			size--;
		}
	}

	/**
	 * Elimina el nodo referenciado por una posición dada. Si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raíz del árbol, únicamente podrá ser eliminado si tiene un solo hijo, el cual lo reemplazará.
	 * @param n Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o corresponde a la raíz (con más de un hijo), o el árbol está vacío.
	 */
	
	public void removeNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> nEliminar = checkPosition(p);
		TNodo<E> padre = nEliminar.getPadre();
		PositionList<TNodo<E>> hijos = nEliminar.getHijos();
		try {
			if (nEliminar == raiz) {
				if (hijos.size() == 0) {
					raiz = null;
				} else {
					if (hijos.size() == 1) {
						TNodo<E> hijo = hijos.remove(hijos.first());
						hijo.setPadre(null);
						raiz = hijo;
					} else
						throw new InvalidPositionException("No se puede eliminar raíz con hijos > 1");
				}
			} else {
				PositionList<TNodo<E>> hermanos = padre.getHijos();
				Position<TNodo<E>> posListaHermanos = hermanos.isEmpty() ? null : hermanos.first();
				while (posListaHermanos != null && posListaHermanos.element() != nEliminar) {
					posListaHermanos = (hermanos.last() == posListaHermanos) ? null : hermanos.next(posListaHermanos);
				}
				if (posListaHermanos == null) {
					throw new InvalidPositionException("La posición p no se encuentra en la lista del padre");
				}
				while (!hijos.isEmpty()) {
					TNodo<E> hijo = hijos.remove(hijos.first());
					hijo.setPadre(padre);
					hermanos.addBefore(posListaHermanos, hijo);
				}
				hermanos.remove(posListaHermanos);
			}
			nEliminar.setPadre(null);
			nEliminar.setElemento(null);
			size--;
		} catch (EmptyListException | BoundaryViolationException e) {
		}
	}

	/**
	 * Recorre el árbol en preorden para almacenar todos los nodos en una colección de posiciones.
	 * @param l Colección a la cual se insertaran las posiciones.
	 * @param r Posición actual en el recorrido.
	 */
	
	private void preorden(PositionList<Position<E>> l, TNodo<E> r) {
		l.addLast(r);
		for (TNodo<E> h : r.getHijos()) {
			preorden(l, h);
		}
	}

	/**
	 * Recorre el árbol en preorden para almacenar todos los nodos en una colección de elementos.
	 * @param l Colección a la cual se insertaran las posiciones.
	 * @param r Posición actual en el recorrido.
	 */
	
	private void preordenIT(PositionList<E> list, TNodo<E> r) {
		list.addLast(r.element());
		for (TNodo<E> h : r.getHijos()) {
			preordenIT(list, h);
		}
	}
	
	/**
	 * Inserta un hijo en una altura dada.
	 * @param alt es la altura en la que se desea insertar el nuevo hijo
	 * @param rotulo es el hijo.
	 */
	
	public void insertarHijoAltura(int alt, E rotulo) {
		for (Position<E> pos : positions()) {
			if (altura(pos) == alt) {
				try {
					addLastChild(pos, rotulo);
				} catch (InvalidPositionException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Devuelve la altura de una posición dada.
	 * @param v es la posición.
	 * @return altura de la posición.
	 */
	
	private int altura(Position<E> v) {
		int salida = 0;
		try {
			if (!isExternal(v)) {
				int h = 0;
				for (Position<E> w : children(v)) {
					h = Math.max(h, altura(w));
				}
				salida = 1 + h;
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		return salida;
	}
}
