package com.ncr.asr.cencosud.chile.scango;

import java.io.File;
import java.io.IOException;

public class Archivo {

	public static void main(String args[]) {
		System.out.println("Inicia programa");
		File directory = new File("c:/ncr/temp/scangodte/ruta1/ruta2/");
		if(directory.mkdirs()) {
			System.out.println("Crea directorio");
		} else {
			System.out.println("Directorio existe");
		}
		
		File archivo = new File("c:/ncr/temp/scangodte/ruta1/ruta2/file.dat");
		try {
			if(archivo.createNewFile()) {
				System.out.println("crea archivo");
			} else {
				System.out.println("Archivo ya existe");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Borrando archivo");
		if (archivo.delete()) {
			System.out.println("Archivo eliminado");
		}else {
			System.out.println("Error al borrar archivo");
		}
		archivo.delete();
		System.out.println("fin programa");
		
	}
}
