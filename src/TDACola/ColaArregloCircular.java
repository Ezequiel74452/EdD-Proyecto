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
	 * Devuelve el tama�o de la cola
	 * @return cantidad de elementos en la cola.
	 */
	
	public int size() {
		return((q.length - f + r) % q.length) ;
	}

	/**
	 * Checkea si la cola est� vac�a.
	 * @return true si la cola est� vac�a, false si no lo est�.
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
			throw new EmptyQueueException("Cola vac�a.");
		}
		else {
			return q[f];
		}
	}

	/**
	 * A�ade un elemento a la cola.
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
	 * Duplica el tama�o del arreglo de la cola e inserta en �l los elementos que conten�a anteriormente.
	 * @param start posici�n en el arreglo, a partir de la cual se realizar� la copia de los elementos.
	 * @return arreglo de elementos con el doble de tama�o que el que se ten�a anteriormente. 
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
	 * Elimina el �ltimo elemento de la cola.
	 * @return elemento eliminado.
	 */
	
	public E dequeue() throws EmptyQueueException {
		if( isEmpty()) {
			throw new EmptyQueueException("Cola vac�a.");
		}
		else {
			E temp = q[f];
			q[f] = null;
			f = (f + 1) % (q.length);
			return temp;
		}
	}
}
