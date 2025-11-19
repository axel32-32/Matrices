package ejercicio;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class E16 {
	static File aFile = new File("C:\\Users\\raroc\\eclipse-workspace\\ud3\\ejercicio\\a.txt");
	static File bFile = new File("C:\\Users\\raroc\\eclipse-workspace\\ud3\\ejercicio\\b.txt");
	
	static double[][] arrayA = null;
	static double[][] arrayB = null;
	static double[][] arrayC = null;

	public static void main(String[] args) throws IOException{ 
		Scanner t = new Scanner(System.in);

		int opcion = 0;
		boolean uno = false, dos = false;
		do {
			imprimirMenu();
			opcion = t.nextInt();
			
			if (opcion==1 || opcion==2) {
				opciones1y2(opcion);
				if(opcion==1&&arrayA == null) {
					uno = true;
					arrayA = haciendoMatrices(1);
				} else if(opcion==2&&arrayB == null) {
					dos = true;
					arrayB = haciendoMatrices(2);
				}
			} else if(opcion==3) {
				if(uno&&dos) {
					System.out.println("Array A: \n");
					mostrarMatrices(arrayA);
					System.out.println("Array B: \n");
					mostrarMatrices(arrayB);
				} else if(uno&&!dos) {
					mostrarMatrices(arrayA);
					System.out.println("Array B:\n\t...");
					
				} else if(!uno&&dos) {
					System.out.println("Array A:\n\t...\n");
					System.out.println("Array B: \n");
					mostrarMatrices(arrayB);
				} else {
					System.out.println("Aún no se ha leido ninguna matriz");
				}
			} else if(opcion ==4) {
				if(uno&&dos) {
					double[][] matrizC = multiplicarMatrices();
					mostrarMatrices(matrizC);	
				} else {
					System.out.println("aun no se han leido todas las matrices");
				}
			}
		} while(opcion!=5);
	}
	
	public static void imprimirMenu() {
		System.out.println("Operacion con Matrices");
		System.out.println("======================");
		
		System.out.println("1. Leer matriz A.");
		System.out.println("2. Leer matriz B.");
		System.out.println("3. Ver matrices.");
		System.out.println("4. Multiplicar A x B.");
		System.out.println("5. Salir");
		
		System.out.println("\tElija una Opcion(1-5):");
	}
	
	public static void opciones1y2(int a) throws IOException{
		if(a!=1 && a!=2) {
			throw new IllegalArgumentException("Valores incorrectos introducidos");
		}
		
		File archivo = a==1? aFile : bFile;
		
		try(Scanner leerFichero = new Scanner(archivo)) {
			if(!leerFichero.hasNextLine()) {
				throw new IOException("El fichero esta vacio");
			}
			String linea = leerFichero.nextLine();
			System.out.println(linea);
		}
	}
	 
	public static double[][] haciendoMatrices(int file) throws IOException{	
		File archivo = null;
		double[][] matriz = null;
		if(file==1) {
			archivo = aFile;
			matriz = arrayA;
		} else {
			archivo = bFile;
			matriz = arrayB;
		}
		
		
		try(Scanner leerFichero = new Scanner(archivo)){
			String linea = leerFichero.nextLine();
			String[] partes = linea.split("\\s+");
			
			int filas = 0, columnas = 0;
			int indiceFilas = 0, indiceColumnas = 0;
			for(int i = 0; i < partes.length; i++) {
				if(i==0) {
					filas = Integer.parseInt(partes[i]);
				} else if(i==1) {
					columnas = Integer.parseInt(partes[i]);
					matriz = new double[filas][columnas];
				} else {
					matriz[indiceFilas][indiceColumnas] = Double.parseDouble(partes[i]);
					if(indiceColumnas != columnas-1) {
						indiceColumnas++;
					} else {
						indiceColumnas = 0;
						indiceFilas++;
					}
				}
			}
			return matriz;
		}	
	}
	
	public static void mostrarMatrices(double[][] matriz) {
		for(int i = 0; i < matriz.length; i++) {
			System.out.print("[");
			for(int j = 0; j < matriz[i].length; j++) {
				System.out.printf("%.3f ", matriz[i][j]);
			}
			System.out.print("]\n");
		}
	}
	
	public static double[][] multiplicarMatrices() {
	    if (arrayA == null || arrayB == null) {
	        System.out.println("Error: alguna matriz no está inicializada.");
	        return null;
	    }

	    if (arrayA[0].length != arrayB.length) {
	        System.out.println("No se pueden multiplicar: columnas de A != filas de B");
	        return null;
	    }

	    arrayC = new double[arrayA.length][arrayB[0].length];

	    for (int i = 0; i < arrayA.length; i++) {              
	        for (int j = 0; j < arrayB[0].length; j++) {       
	            double suma = 0;
	            for (int k = 0; k < arrayA[0].length; k++) {   
	                suma += arrayA[i][k] * arrayB[k][j];
	            }
	            arrayC[i][j] = suma;
	        }
	    }

	    return arrayC;
	}
}