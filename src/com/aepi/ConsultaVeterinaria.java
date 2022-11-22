package com.aepi;

import java.util.Scanner;

public class ConsultaVeterinaria {

	public static void main(String[] args) throws ClassNotFoundException {
		Scanner lector = new Scanner(System.in);
		BaseDatos animal = new BaseDatos();
		String nombre = null, tipo = null, sexo = null, peso = null, telefono_duenio = null;
		
		boolean salir = false;
		int opcion;
		
		System.out.println("\nMENÚ DEL PROGRAMA DE LA CLÍNICA");
		do {
			System.out.println("\n\n¿Qué desea hacer ahora? ");
			System.out.println("\n1.Dar de alta a un animal"
					+ "\n2.Actualizar un animal"
					+ "\n3.Eliminar un animal"
					+ "\n4.Visualizar animales por nombre"
					+ "\n5.Visualizar todos los animales"
					+ "\n6.Salir");
			
			opcion = lector.nextInt();
			switch (opcion) {
			case 1: 
				System.out.println("\nHa elegido dar de alta a un animal");
				System.out.print("\nNombre: ");
				nombre = lector.next();
				System.out.print("\nTipo: ");
				tipo = lector.next();
				System.out.print("\nSexo: ");
				sexo = lector.next();
				System.out.print("\nPeso: ");
				peso = lector.next();
				System.out.print("\nTeléfono del dueño: ");
				telefono_duenio = lector.next();
				animal.insertar(nombre, tipo, sexo, peso, telefono_duenio);
			break;
			case 2: 
				System.out.println("\nHa elegido actualizar un animal");
				System.out.print("\nIntroduce el teléfono del dueño: ");
				telefono_duenio = lector.next();
				System.out.print("\nPeso actual: ");
				peso = lector.next();
				animal.actualizar(peso, telefono_duenio);
			break;
			case 3: 
				System.out.println("\nHa elegido eliminar un animal");
				System.out.print("\nIntroduce el teléfono del dueño: ");
				telefono_duenio = lector.next();
				animal.eliminar(telefono_duenio);
			break;
			case 4: 
				System.out.println("\nHa elegido visualizar animales por nombre");
				System.out.print("\nIntroduce el nombre del animal: ");
				nombre = lector.next();
				animal.visualizarPorNombre(nombre);
				System.out.println("\nTODOS REGISTROS DE " + nombre + " MOSTRADOS\n");
				break;
			case 5: 
				System.out.println("\nHa elegido visualizar todos los animales");
				animal.visualizarTodo();
				break;
			case 6: 
				System.out.println("\nHa elegido salir de la app. Gracias por usar la aplicación de la clínica");
				salir = true;
				break;
			default:
				System.out.println("\nDEBES INTRODUCIR UN Nº ENTRE EL 1 Y EL 6\n");
			}
			
		}while(!salir);

	}

}
