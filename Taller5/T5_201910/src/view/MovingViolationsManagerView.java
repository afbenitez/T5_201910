package view;

import java.time.LocalDate; 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import controller.Controller;
import model.vo.VOMovingViolations;


public class MovingViolationsManagerView 
{
	/**
	 * Constante con el número maximo de datos maximo que se deben imprimir en consola
	 */
	public static final int N = 20;
	
	public void printMenu() {
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Taller 5----------------------");
		System.out.println("0. Cargar datos ");
		System.out.println("1. Muestra aleatoria");
		System.out.println("2. Tiempo de agregar datos a la cola");
		System.out.println("3. Tiempo de agregar datos al heap");
		System.out.println("4. Tiempo de eliminar datos de la cola");
		System.out.println("5. Tiempo de eliminar datos del heap");
		System.out.println("6. Grafica de tiempo para metodos de las estructuras");
		System.out.println("7. Salir");
		System.out.println("Digite el numero de opcion para ejecutar la tarea, luego presione enter: (Ej., 1):");
		
	}
	
	public void printMessage(String mensaje) {
		System.out.println(mensaje);
	}
	
	
	
}
