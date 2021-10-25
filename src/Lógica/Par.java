package Lógica;

public class Par<K, V> {
	
	private K archivos;
	private V ruta;
	
	public Par(K a, V r) {
		archivos = a;
		ruta = r;
	}
	
	public K getArchivos() {
		return archivos;
	}
	
	public V getRuta() {
		return ruta;
	}
}
