package TDACola;

public class ColaArregloCircular<E> implements Queue<E> {

	protected E[] q;
	protected int f,r;
	
	/**
	 * Inicializa el arreglo con 10 espacios disponibles.
	 */
	
	public ColaArregloCircular() {
		q = (E[]) new Object[10];
		f = 0;
		r = 0;
	}
	
	/**
	 * Devuelve el tamaño de la cola
	 * @return cantidad de elementos en la cola.
	 */
	
	public int size() {
		return((q.length - f + r) % q.length) ;
	}

	/**
	 * Checkea si la cola está vacía.
	 * @return true si la cola está vacía, false si no lo está.
	 */
	
	public boolean isEmpty() {
		return f == r;
	}
	
	/**
	 * Devuelve el elemento en el frente de la cola.
	 * @return elemento al frente de la cola.
	 */
	
	public E front() throws EmptyQueueException {
		if(isEmpty()) {
			throw new EmptyQueueException("Cola vacía.");
		}
		else {
			return q[f];
		}
	}

	/**
	 * Añade un elemento a la cola.
	 */
	
	public void enqueue(E element) {
		if(size() == q.length-1) {
			E[] aux = copiar(f);
			r = size();
			f = 0;
			q = aux;
		}
			q[r] = element;
			r = (r + 1) % q.length;
	}
	
	/**
	 * Duplica el tamaño del arreglo de la cola e inserta en él los elementos que contenía anteriormente.
	 * @param start posición en el arreglo, a partir de la cual se realizará la copia de los elementos.
	 * @return arreglo de elementos con el doble de tamaño que el que se tenía anteriormente. 
	 */
	
	private E[] copiar(int start) {
		int j = 0;
		E[] aux = (E[]) new Object[q.length*2];
		for(int i = f;!(start == r);i++) {
			start = i % q.length;
			aux[j++] = q[start];
		}
		return aux;
	} 

	/**
	 * Elimina el último elemento de la cola.
	 * @return elemento eliminado.
	 */
	
	public E dequeue() throws EmptyQueueException {
		if( isEmpty()) {
			throw new EmptyQueueException("Cola vacía.");
		}
		else {
			E temp = q[f];
			q[f] = null;
			f = (f + 1) % (q.length);
			return temp;
		}
	}
}
