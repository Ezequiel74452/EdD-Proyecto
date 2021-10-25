package Directorio;

/**
 * Clase para recuperar las carpetas (subdirectorios) y archivos de una carpeta (directorio) del disco.
 * @author Sergio Alejandro Gómez.
 */

import java.io.File;
import java.util.LinkedList;
import java.util.List;


public class Directorio {
	private String rutaCompleta;
	
	/**
	 * Construye un objeto directorio.
	 * El formato de la ruta debe ser completo:
	 * c:\\carpeta1\\carpeta2\\carpeta3
	 * @param rutaCompleta Ruta completa del directorio.
	 */
	public Directorio(String rutaCompleta) {
		this.rutaCompleta = rutaCompleta;
	}
	
	/**
	 * Devuelve la ruta absoluta del directorio a explorar como se especificó en el contructor.
	 * @return La ruta del directorio.
	 */
	public String getRutaCompleta() {
		return rutaCompleta;
	}
	
	/**
	 * Retorna un iterable con los archivos del directorio.
	 * @return Un iterable con los archivos del directorio.
	 * @throws DirectorioException Cuando el directorio no existe.
	 */
	public Iterable<String> archivosDelDirectorio() throws DirectorioException {
		try {
			List<String> resultado = new LinkedList<String>();
			File f = new File(getRutaCompleta());
			String [] nombresDeArchivo = f.list();
			for (String nombre : nombresDeArchivo) {
				String nombreCompleto = getRutaCompleta() + "\\" + nombre; 
				File tmp = new File(nombreCompleto);
				if (tmp.isFile()) {
					resultado.add(nombreCompleto);
				}
			}	
			return resultado;
		} catch(Exception e) {
			throw new DirectorioException("Hubo un error: Verifique que el nombre del directorio esté bien.\n" 
					+ "O que el dispositivo de almacenamiento funcione correctamente.\n"
					+ "Nombre del directorio: " + getRutaCompleta());
		}
	}
	
	/**
	 * Retorna un iterable con los subdirectorios (subcarpetas) del directorio.
	 * @return Un iterable con los subdirectorios (subcarpetas) del directorio.
	 * @throws DirectorioException Cuando el directorio no existe.
	 */
	public Iterable<String> subdirectoriosDelDirectorio() throws DirectorioException {
		try {
			List<String> resultado = new LinkedList<String>();
			File f = new File(getRutaCompleta());
			String [] nombresDeArchivo = f.list();
			for (String nombre : nombresDeArchivo) {
				String nombreCompleto = getRutaCompleta() + "\\" + nombre; 
				File tmp = new File(nombreCompleto);
				if (tmp.isDirectory()) { 
					resultado.add(nombreCompleto);
				}
			}	
			return resultado;
		} catch(Exception e) {
			throw new DirectorioException("Hubo un error: Verifique que el nombre del directorio esté bien.\n" 
					+ "O que el dispositivo de almacenamiento funciones correctamente.\n"
					+ "Nombre del directorio: " + getRutaCompleta());
		}
	}
}
