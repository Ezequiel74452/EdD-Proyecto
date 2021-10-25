package Directorio;

/**
 * Excepción lanzada por la clase Directorio cuando el nombre del directorio no existe al tratar
 * de recuperar sus archivos o carpetas.
 * @author Sergio Alejandro Gómez.
 *
 */

public class DirectorioException extends Exception {
	public DirectorioException(String msg) {
		super(msg);
	}
}
	