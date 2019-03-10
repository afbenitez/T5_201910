package controller;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import com.opencsv.CSVReader;

import model.utils.Sort;
import model.data_structures.*;
import model.vo.*;
import view.MovingViolationsManagerView;

public class Controller {

	private int cuatrimestre;
	private MovingViolationsManagerView view;
	private MaxColaPrioridad<VOMovingViolations> datos;
	
	private enum Meses
	{
		January(0), February(0), March(0), April(0);

		private int infracciones;

		private Meses(int cantidad)
		{ 
			this.infracciones = cantidad;
		}

		private void contar()
		{ 
			this.infracciones++; 
		}

		private int darInfracciones()
		{ 
			return infracciones; 
		}
	}

	public Controller() 
	{
		view = new MovingViolationsManagerView();
	}

	public void run() 
	{
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		Controller controller = new Controller();
		while(!fin)
		{
			view.printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 0:
				view.printMessage("Ingrese el cuatrimestre (1, 2 o 3)");
				int numeroCuatrimestre = sc.nextInt();
				controller.loadMovingViolations(numeroCuatrimestre);
				break;
			}
		}
	}

	public void loadMovingViolations(int numeroCuatrimestre) 
	{
		CSVReader reader;
		String[] nextLine;
		int streetID=0;
		boolean accidente=false;
		cuatrimestre=numeroCuatrimestre;
		try
		{
			for(Meses meses : Meses.values())
			{
				reader = new CSVReader(new FileReader("./data/Moving_Violations_Issued_in_"+meses+"_2018.csv"));
				nextLine=reader.readNext();  
				while ((nextLine = reader.readNext()) != null) 
				{				

					if(!nextLine[4].equals(""))
					{
						streetID=Integer.parseInt(nextLine[4]);
					}
					if(nextLine[12].equalsIgnoreCase("YES"))
					{
						accidente=true;
					}
					meses.contar();
				}
				view.printMessage("Numero de infracciones en el mes " + (meses.ordinal()+1) + ": " + meses.darInfracciones());
				reader.close();
			}	
		}
		catch(Exception e)
		{ 
			e.printStackTrace(); 
		}
	}
	
	public MaxColaPrioridad generarMuestra(int pMuestra)
	{
		MaxColaPrioridad muestra = new MaxColaPrioridad<>();
	
		for(int i = 0; i < datos.darNumeroElementos(); i++)
		{
			int j = (int) Math.random() * i+1;
			if(j <= datos.darNumeroElementos())
			{
				muestra.agregar(datos.darElementoPos(j));
			}
		}
		
		return muestra;
	}

	/**
	 * Convertir fecha a un objeto LocalDate
	 * @param fecha fecha en formato dd/mm/aaaa con dd para dia, mm para mes y aaaa para agno
	 * @return objeto LD con fecha
	 */
	private static LocalDate convertirFecha(String fecha)
	{
		return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	/**
	 * Convertir fecha y hora a un objeto LocalDateTime
	 * @param fecha fecha en formato dd/mm/aaaaTHH:mm:ss con dd para dia, mm para mes y aaaa para agno, HH para hora, mm para minutos y ss para segundos
	 * @return objeto LDT con fecha y hora integrados
	 */
	private static LocalDateTime convertirFecha_Hora_LDT(String fechaHora)
	{
		return LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'"));
	}
}