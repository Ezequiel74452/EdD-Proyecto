package Lógica;

import java.util.Iterator;
import Directorio.*;
import TDAArbol.*;
import TDALista.*;
import TDAPila.*;

public class ÁrbolDeArchivos {
	
	private Árbol<Par<Iterable<String>, String>> tree;
	
	/**
	 * Inicializa el árbol vacío.
	 */
	
	public ÁrbolDeArchivos() {
		tree = new Árbol<Par<Iterable<String>, String>>();
	}
	
	/**
	 * Carga el árbol completo según la ruta dada (Punto 1).
	 * @param ruta a partir de la cual se genera el árbol.
	 */
	
	public void cargarÁrbol(String ruta) {
		Directorio D = new Directorio(ruta);
		Par<Iterable<String>, String> p;
		try {
			p = new Par<Iterable<String>, String>(D.archivosDelDirectorio(), D.getRutaCompleta());
			tree.createRoot(p);
			if(tieneSubDir(D)) {
				crearHijos(tree.root(), D);
			}
		} catch (DirectorioException | InvalidOperationException | EmptyTreeException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checkea si un directorio dado tiene al menos un sub directorio. (Usado para cargar el árbol).
	 * @param d directorio a comprobar.
	 * @return true si hay al menos un sub directorio, false si no hay ninguno.
	 */
	
	private boolean tieneSubDir(Directorio d) {
		boolean toReturn = false;
		try {
			toReturn = d.subdirectoriosDelDirectorio().iterator().hasNext();
		} catch (DirectorioException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/**
	 * Crea todos los hijos para todos los sub directorios existentes en el árbol. (Usado para cargar el árbol).
	 * @param padre posición del padre al que se le van a crear los hijos.
	 * @param dirPadre directorio del padre al que se le van a crear los hijos.
	 * @throws DirectorioException si dirPadre es inválido.
	 * @throws InvalidPositionException si la posición de padre es inválida.
	 */
	
	private void crearHijos(Position<Par<Iterable<String>, String>> padre, Directorio dirPadre) {
		try {
			for(String name : dirPadre.subdirectoriosDelDirectorio()) {
				Directorio d2 = new Directorio(name);
				Par<Iterable<String>, String> p2 = new Par<Iterable<String>, String>(d2.archivosDelDirectorio(), d2.getRutaCompleta());
				Position<Par<Iterable<String>, String>> pos = tree.addLastChild(padre, p2);
				if(tieneSubDir(d2)) {
					crearHijos(pos, d2);
				}
			}
		} catch (DirectorioException | InvalidPositionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna todos los archivos de una ruta dada. (Punto 2).
	 * @param ruta es la ruta a examinar
	 * @return lista con las direcciones de todos los archivos en la ruta dada.
	 */
	
	public PositionList<String> archivosRuta(String ruta) throws DirectorioException {
		PositionList<String> l = new ListaDoblementeEnlazada<String>();
		Iterator<Position<Par<Iterable<String>, String>>> it = tree.positions().iterator();
		boolean encontre = false;
		while(it.hasNext() && !encontre) {
			Position<Par<Iterable<String>, String>> pos = it.next();
			if(ruta.equals(pos.element().getRuta())) {
				encontre = true;
				for(String name : pos.element().getArchivos()) {
					l.addLast(name);
				}
			}
		}
		if (encontre == false) {
			throw new DirectorioException("El directorio ingresado no existe en el árbol cargado");
		}
		return l;
	}
	
	/**
	 * Devuelve los sub directorios de una ruta dada. (Punto 3).
	 * @param ruta es la ruta del directorio del que se van a devolver sus sub directorios.
	 * @return lista con los sub directorios de la ruta dada.
	 */
	
	public PositionList<String> subDirInmediatos(String ruta) throws DirectorioException {
		boolean encontre = false;
		PositionList<String> l = new ListaDoblementeEnlazada<String>();
		Iterator<Position<Par<Iterable<String>, String>>> it = tree.positions().iterator();
		while(it.hasNext() && !encontre) {
			Position<Par<Iterable<String>, String>> pos = it.next();
			if(ruta.equals(pos.element().getRuta())) {
				encontre = true;
				Directorio d = new Directorio(ruta);
				if(tieneSubDir(d)) {
					try {
						for(String name : d.subdirectoriosDelDirectorio()) {
							l.addLast(name);
						}
					} catch (DirectorioException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (encontre == false) {
			throw new DirectorioException("La ruta ingresada no se encuentra en el árbol cargado anteriormente.");
		}
		return l;
	}
	
	/**
	 * Devuelve el grado del nodo que corresponde a la ruta dada. (Punto 4).
	 * @param ruta del nodo a examinar.
	 * @return grado del nodo.
	 * @throws DirectorioException si la ruta dada no pertenece al árbol cargado.
	 */
	
	public int gradoNodo(String ruta) throws DirectorioException {
		int toReturn = 0;
		boolean encontre = false;
		Iterator<Position<Par<Iterable<String>, String>>> it = tree.positions().iterator();
		while(it.hasNext() && !encontre) {
			Position<Par<Iterable<String>, String>> pos = it.next();
			if(pos.element().getRuta().equals(ruta)) {
				encontre = true;
				try {
					for(Position<Par<Iterable<String>, String>> pos2 : tree.children(pos)) {
						toReturn++;
					}
				} catch (InvalidPositionException e) {
					e.printStackTrace();
				}
			}
		}
		if(encontre == false) {
			throw new DirectorioException("El directorio ingresado no existe en el árbol cargado");
		}
		return toReturn;
	}
	
	/**
	 * Devuelve el grado del árbol. (Punto 5).
	 * @return grado del árbol.
	 */
	
	public int gradoÁrbol() {
		int toReturn = 0;
		for(Position<Par<Iterable<String>, String>> pos : tree.positions()) {
			int aux = 0;
			try {
				for(Position<Par<Iterable<String>, String>> pos2 : tree.children(pos)) {
					aux++;
				}
			} catch (InvalidPositionException e) {
				e.printStackTrace();
			}
			if (toReturn<aux) {
				toReturn = aux;
			}
		}
		return toReturn;
	}
	
	/**
	 * Devuelve la altura del árbol. (Punto 6).
	 * @return altura del árbol.
	 */
	
	public int altura() {
		int toReturn = 0;
		try {
			toReturn = altura(tree.root());
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/**
	 * Devuelve la altura de una posición dada. (Se usa en altura).
	 * @param v es la posición.
	 * @return altura de la posición.
	 */
	
	private int altura(Position<Par<Iterable<String>, String>> v) {
		int salida = 0;
		try {
			if (!tree.isExternal(v)) {
				int h = 0;
				for (Position<Par<Iterable<String>, String>> w : tree.children(v)) {
					h = Math.max(h, altura(w));
				}
				salida = 1 + h;
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		return salida;
	}
	
	/**
	 * Devuelve la profundidad del nodo perteneciente a una ruta dada. (Punto 7).
	 * @param ruta es la ruta del nodo a evaluar.
	 * @return profundidad del nodo.
	 * @throws DirectorioException si la ruta no pertenece al árbol.
	 */
	
	public int profundidad(String ruta) throws DirectorioException {
		int toReturn = 0;
		try {
			if(!ruta.equals(tree.root().element().getRuta())) {
				boolean encontre = false;
				Iterator<Position<Par<Iterable<String>, String>>> it = tree.positions().iterator();
				while(it.hasNext() && !encontre) {
					Position<Par<Iterable<String>, String>> pos = it.next();
					if(pos.element().getRuta().equals(ruta)) {
						encontre = true;
						while(pos != tree.root()) {
							pos = tree.parent(pos);
							toReturn++;
						}
					}
				}
				if(encontre == false) {
					throw new DirectorioException("La ruta ingresada no existe en el árbol cargado previamente.");
				}
			}
		} catch (EmptyTreeException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/**
	 * Retorna todos los archivos del árbol. (Punto 8)
	 * @return lista con las direcciones de cada archivo del árbol.
	 */
	
	public PositionList<String> allFiles() {
		PositionList<String> l = new ListaDoblementeEnlazada<String>();
		for(Position<Par<Iterable<String>, String>> pos : tree.positions()) {
			for(String name : pos.element().getArchivos()) {
				l.addLast(name);
			}
		}
		return l;
	}
	
	/**
	 * Devuelve el ancestro común de dos rutas absolutas. (Punto 9)
	 * @param ruta1 primera ruta.
	 * @param ruta2 segunda ruta.
	 * @return ruta del ancestro común de ambas rutas.
	 */
	
	public String ancestroComún(String ruta1, String ruta2) {
		String toReturn = "";
		int menor = Math.min(ruta1.length(), ruta2.length());
		Stack<Character> pila1 = new PilaEnlazada<Character>();
		Stack<Character> pila2 = new PilaEnlazada<Character>();
		boolean salir = false;
		for(int i=0; i<menor && !salir; i++) {
			Character c1 = ruta1.charAt(i);
			Character c2 = ruta2.charAt(i);
			if(c1.equals(c2)) {
				pila1.push(c1);
			} else {
				salir = true;
			}
		}
		try {
			Character aux = pila1.pop();
			while (aux != '\\') {
				aux = pila1.pop();
			}
			while(!pila1.isEmpty()) {
				pila2.push(pila1.pop());
			}
			while(!pila2.isEmpty()) {
				toReturn += pila2.pop();
			}
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/**
	 * Dado un directorio y una ruta relativa, devuelve una ruta absoluta. (Punto 10)
	 * @param d directorio principal.
	 * @param rutarel ruta relativa
	 * @return ruta absoluta.
	 */
	
	public String dirAbsoluta(Directorio d, String rutarel) {
		String rutadir = d.getRutaCompleta();
		String toReturn = "";
		Stack<Character> pila1 = new PilaEnlazada<Character>();
		Stack<Character> pila2 = new PilaEnlazada<Character>();
		Stack<Character> aux = new PilaEnlazada<Character>();
		for(int k=0; k<rutadir.length(); k++) {
			pila1.push(rutadir.charAt(k));
		}
		int cont = 0;
		for(int i=0; i<rutarel.length(); i++) {
			if(rutarel.charAt(i) == '.') {
				cont++;
			}
		}
		boolean stop = false;
		for(int l=rutarel.length()-1; l>=0 && !stop; l--) {
			if(rutarel.charAt(l) != '.') {
				aux.push(rutarel.charAt(l));
			} else {
				stop = true;
			}
		}
		cont = cont/2;
		try {
			for(int j=0; j<cont; j++) {
				Character c1 = pila1.pop();
				while(c1 != '\\') {
					c1 = pila1.pop();
				}
			}
			while(!pila1.isEmpty()) {
				pila2.push(pila1.pop());
			}
			while(!pila2.isEmpty()) {
				toReturn += pila2.pop();
			}
			while(!aux.isEmpty()) {
				toReturn+=aux.pop();
			}
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
}
