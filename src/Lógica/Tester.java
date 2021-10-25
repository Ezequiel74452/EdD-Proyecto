package Lógica;

import java.util.Scanner;

import Directorio.Directorio;
import Directorio.DirectorioException;

public class Tester {

	public static void main(String[] args) {
		Directorio d = new Directorio("C:\\Users\\ezequ\\Desktop\\Test\\P6\\New folder");
		Scanner in = new Scanner(System.in);
		String str = in.next();
		in.close();
		System.out.println(str);
		ÁrbolDeArchivos a = new ÁrbolDeArchivos();
		a.cargarÁrbol(str);
		// a.archivosRaíz();
		// a.subDirInmediatos();
		// a.allFiles();
		try {
			System.out.println(a.gradoNodo(str));
			System.out.println("Profundidad: "+ a.profundidad("C:\\Users\\ezequ\\Desktop\\Test\\P6\\New folder (4)\\New folder (3)\\New folder\\New folder"));
		} catch (DirectorioException e) {
			e.printStackTrace();
		}
		System.out.println(a.gradoÁrbol());
		System.out.println(a.altura());
		System.out.println("Dir absoluta: "+ a.dirAbsoluta(d, "..\\..\\P4"));
		System.out.println("Ancestro común: "+ a.ancestroComún("C:\\Users\\ezequ\\Desktop\\Test\\P6\\New folder (4)\\New folder (3)\\New folder", "C:\\Users\\ezequ\\Desktop\\Test\\P2\\a"));
	}

}