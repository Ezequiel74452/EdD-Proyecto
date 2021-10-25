package Directorio;


/**
 * Programa de prueba para la clase Directorio.
 * @author Sergio Alejandro Gómez
 */
public class TesterDirectorio {
	public static void main(String [] args) {
		try {
			Directorio d = new Directorio("C:\\Users\\ezequ\\Desktop\\Kyoukai no Kanata");
			
			System.out.println("ARCHIVOS:");
			for ( String archivo : d.archivosDelDirectorio() ) {
				System.out.println(archivo);
			}
		
			System.out.println("\nDIRECTORIOS:");
			for ( String dir : d.subdirectoriosDelDirectorio() ) {
				System.out.println(dir);
			}
		} catch(DirectorioException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
