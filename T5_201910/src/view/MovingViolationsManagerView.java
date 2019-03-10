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
		System.out.println("1. Grafica de tiempo para metodos agregar de las estructuras");
		System.out.println("2. Grafica de tiempo para metodos delMax de las estructuras");

		
		System.out.println("3. Salir");
		System.out.println("Digite el numero de opcion para ejecutar la tarea, luego presione enter: (Ej., 1):");
		
	}
	
	public void printMessage(String mensaje) {
		System.out.println(mensaje);
	}
	
	
	
	public void printMovingViolationsByHourReq10() {
		System.out.println("Porcentaje de infracciones que tuvieron accidentes por hora. 2018");
		System.out.println("Hora| % de accidentes");
		System.out.println("00 | X");
		System.out.println("01 | X");
		System.out.println("02 | XX");
		System.out.println("03 | XXXXX");
		System.out.println("04 | XXXXXXXX");
		System.out.println("05 | XXXXXXXXX");
		System.out.println("06 | XXXXXXXXX");
		System.out.println("07 | XXXXXXXXXX");
		System.out.println("08 | XXXXXXXXXXX");
		System.out.println("09 | XXXXXXXXXXXXX");
		System.out.println("10 | XXXXXXXXXXXXXX");
		System.out.println("11 | XXXXXXXXXXXXXX");
		System.out.println("12 | XXXXXXXXXXXXXXXX");
		System.out.println("13 | XXXX");
		System.out.println("14 | XXXXXX");
		System.out.println("15 | XXXXXXXXXXXXXXXX");
		System.out.println("16 | XXXXXXXXXXX");
		System.out.println("17 | XXXXXX");
		System.out.println("18 | XXXXXXXXXXXXXXXX");
		System.out.println("19 | XXXXXXXXXX");
		System.out.println("20 | XXX");
		System.out.println("21 | XXXXX");
		System.out.println("22 | XXXX");
		System.out.println("23 | XX");
		System.out.println("");
		System.out.println("Cada X representa Y%");
	}
	
	public void printTotalDebtbyMonthReq12() {
		System.out.println("Deuda acumulada por mes de infracciones. 2018");
		System.out.println("Mes| Dinero");
		System.out.println("01| X");
		System.out.println("02| XX");
		System.out.println("03 | XXXXXX");
		System.out.println("04 | XXXXXXXXXX");
		System.out.println("");
		System.out.println("Cada X representa $YYYY USD");
	}
	
}
