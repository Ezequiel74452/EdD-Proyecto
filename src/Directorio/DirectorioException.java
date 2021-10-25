package Directorio;

/**
 * Excepci�n lanzada por la clase Directorio cuando el nombre del directorio no existe al tratar
 * de recuperar sus archivos o carpetas.
 * @author Sergio Alejandro G�mez.
 *
 */

public class DirectorioException extends Exception {
	public DirectorioException(String msg) {
		super(msg);
	}
}
	