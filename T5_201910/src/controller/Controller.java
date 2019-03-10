package controller;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import com.opencsv.CSVReader;

import model.utils.Sort;
import model.vo.*;

import model.data_structures.*;

import view.MovingViolationsManagerView;

public class Controller 
{
	private enum Meses
	{
		January(0),
		February(0),
		March(0), 
		April(0);

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

	private MovingViolationsManagerView view;
	
	private MaxColaPrioridad<LocationVO> cola;
	
	private MaxHeapCP<LocationVO> heap;
	
	private ArregloDinamico<VOMovingViolations> datos;
	
	private ArregloDinamico<LocationVO> muestra;
	
	private VOMovingViolations[] muestraVOMoving;

	public Controller() 
	{
		view = new MovingViolationsManagerView();
		
		cola = new MaxColaPrioridad<LocationVO>();
		
		heap = new MaxHeapCP<LocationVO>(100000);
		datos = new ArregloDinamico<VOMovingViolations>(100000);
		muestra = new ArregloDinamico<LocationVO>(100000);

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
				view.printMessage("Datos de los cuatro meses");
				int numeroCuatrimestre = 1;
				controller.loadMovingViolations(numeroCuatrimestre);
				break;
			case 1:
				view.printMessage("Ingrese el tamaño de la muestra aleatoria");
				int seleccion=sc.nextInt();
				controller.darMuestra(seleccion);
				break;
			case 2: 
				long timeAC = controller.agregarDatosCola(this.muestra);
				System.out.println("Tiempo de "+ timeAC + "milisegundos para agregar los datos de una cola");
				break;

			case 3: 
				long timeAH = controller.agregarDatosHeap(this.muestra); 
				System.out.println("Tiempo de " + timeAH + "milisegundos para agregar los datos de un heap");
				break;
			case 4: 
				long timeEC = controller.eliminarDatosCola();
				System.out.println("Tiempo de" + timeEC + "milisegundos para eliminar los datos de una cola");
				break;
			case 5: 
				long timeEH = controller.eliminarDatosHeap(); 
				System.out.println("Tiempo de "+ timeEH + "milisegundos para eliminar los datos de un heap");
				break;
			case 6: 
				System.out.println("Ingrese la fecha inicial");
				LocalDate fechaInicial  = convertirFecha(sc.nextLine());
				System.out.println("Ingrese la fecha final");
				LocalDate fechaFinal = convertirFecha(sc.nextLine());
				System.out.println("Ingrese un numero de vias con mas infracciones");
				int numero = sc.nextInt();
				ArregloDinamico<LocationVO> respuesta = darLocationVOMayor(fechaInicial, fechaFinal, numero);
				for(int i = 0; i < numero; i++)
				{
					System.out.println(respuesta.darElemento(i).toString());
				}
			case 7:	
				fin=true;
				sc.close();
				break;
			}
		}
	}

	public void loadMovingViolations(int numeroCuatrimestre) 
	{
		CSVReader reader;
		String[] nextLine;

		try
		{
			for(Meses meses : Meses.values())
			{
				reader = new CSVReader(new FileReader("./data/Moving_Violations_Issued_in_" + meses + "_2018.csv"));
				nextLine = reader.readNext();  
				int streetID = 0;
				boolean accidente = false;
				int linea = 0;
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
					if(!nextLine[3].equals(""))
					{
						linea = Integer.parseInt(nextLine[3]);
					}
					VOMovingViolations agregado=new VOMovingViolations(Integer.parseInt(nextLine[0]), nextLine[2],Integer.parseInt(nextLine[8]),accidente, nextLine[13], Double.parseDouble(nextLine[9]),nextLine[12],nextLine[15],nextLine[14], linea,streetID);
					datos.agregar(agregado);

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

	public ArregloDinamico<LocationVO> darMuestra( int num)
	{
		muestraVOMoving  = new VOMovingViolations[num];
		this.muestra = new ArregloDinamico<LocationVO>(num);
		for(int i = 0; i < datos.darTamanio(); i ++)
		{
			if(i<num)
			{
				muestraVOMoving[i]= datos.darElemento(i);
			}
		}
		VOMovingViolations.comparadorAddressID comparator = new VOMovingViolations.comparadorAddressID();
		Sort.sort(muestraVOMoving, comparator);
		VOMovingViolations actual = muestraVOMoving[0];
		int count = 0;
		
		for(int i = 0; i < muestraVOMoving.length; i++)
		{
			if(muestraVOMoving[i].getAddressId() == (actual.getAddressId()))
			{
				count++;
				LocationVO agregar = new LocationVO(actual.getAddressId(), actual.getLocation(), count);
				this.muestra.agregar(agregar);
				view.printMessage("Muestra " + i +" : "+ "  AddresId: " + (agregar.darAddressId()) + "  Location: " +(agregar.darLocation()) + "  Number of Registers: " + (agregar.darNumberOfRegisters()));
				actual = muestraVOMoving[i];
			}
			else
			{
				LocationVO agregar = new LocationVO(actual.getAddressId(), actual.getLocation(), count);
				this.muestra.agregar(agregar);
				view.printMessage("Muestra " + i +" : "+ "  AddresId: " + (agregar.darAddressId()) + "  Location: " +(agregar.darLocation()) + "  Number of Registers: " + (agregar.darNumberOfRegisters()));
				actual = muestraVOMoving[i];
				count = 0;
			}
		}
		
		return this.muestra;
	}

	public long agregarDatosCola(ArregloDinamico<LocationVO> muestra)
	{
		
		long startTime = System.currentTimeMillis();


		for(int i = 0; i < muestra.darTamanio(); i++)
		{
			cola.agregar(muestra.darElemento(i)); 
		}
		
		long endTime = System.currentTimeMillis() - startTime;
		long promedio = endTime/cola.darNumeroElementos();
		view.printMessage("Promedio agregar cola de prioridad es: "+ promedio);
		return promedio;
	}
	public long eliminarDatosCola()
	{
		long startTime = System.currentTimeMillis();

		for(int i = 0; i < muestra.darTamanio(); i++)
		{
			cola.delMax();
		}
		long endTime = System.currentTimeMillis() - startTime;
		long promedio = endTime/cola.darNumeroElementos();
		view.printMessage("Promedio eliminar cola de prioridad es: "+ promedio);
		return promedio;

	}

	public long agregarDatosHeap(ArregloDinamico<LocationVO> muestra)
	{
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < muestra.darTamanio(); i++)
		{
			heap.agregar(muestra.darElemento(i)); 
		}
		long endTime = System.currentTimeMillis() - startTime;
		long promedio = endTime/ heap.darNumeroElementos();
		view.printMessage("Promedio agregar heap es: "+ promedio);
		return promedio;
	}
	
	public long eliminarDatosHeap()
	{
		long startTime = System.currentTimeMillis();
		for(int i = 0; i< muestra.darTamanio(); i++)
		{
			heap.delMax();
		}
		long endTime = System.currentTimeMillis() - startTime;
		long promedio = endTime/heap.darNumeroElementos();
		view.printMessage("Promedio eliminar heap es: "+ promedio);
		return promedio;
	}
	
	public ArregloDinamico<LocationVO> darLocationVOMayor(LocalDate inicial, LocalDate tope, int numeroVias)
	{
		ArregloDinamico<VOMovingViolations> VOMoving = new ArregloDinamico<VOMovingViolations>(300000);
		ArregloDinamico<LocationVO> locationVo = new ArregloDinamico<LocationVO>(300000);
		
		for(int i = 0; i < datos.darTamanio(); i++ )
		{
			VOMovingViolations actual = datos.darElemento(i);
			LocalDateTime fechaActual = convertirFecha_Hora_LDT(actual.getTicketIssueDate());
			LocalDate fecha;
			
			if(fechaActual.getDayOfMonth() < 10 & fechaActual.getMonthValue() < 10)
			{
				fecha = convertirFecha("0"+fechaActual.getDayOfMonth()+""+"/" + "0"+fechaActual.getMonthValue()+"" +"/"+fechaActual.getYear());
			}
			else if(fechaActual.getDayOfMonth() >= 10 && fechaActual.getMonthValue() < 10)
			{
				fecha = convertirFecha(fechaActual.getDayOfMonth()+""+"/" + "0"+fechaActual.getMonthValue()+"" +"/"+fechaActual.getYear());
			}
			else if(fechaActual.getDayOfMonth() < 10 && fechaActual.getMonthValue() >=10)
			{
				fecha = convertirFecha("0"+fechaActual.getDayOfMonth()+""+"/" +fechaActual.getMonthValue()+"" +"/"+fechaActual.getYear());
			}
			else
			{
				fecha = convertirFecha(fechaActual.getDayOfMonth()+""+"/" + fechaActual.getMonthValue()+"" +"/"+fechaActual.getYear());
			}
			if(fecha.compareTo(inicial) >= 0 && fecha.compareTo(tope) <=0)
			{
				VOMoving.agregar(actual);
			}
		}
		
		VOMovingViolations ElementoActual = VOMoving.darElemento(0);
		int count = 0;
		for(int i = 0; i < VOMoving.darTamanio(); i++)
		{
			if(VOMoving.darElemento(i).getAddressId() == ElementoActual.getAddressId())
			{
				count++;
			}
			else
			{
				LocationVO agregar = new LocationVO(ElementoActual.getAddressId(), ElementoActual.getLocation(), count);
				locationVo.agregar(agregar);
				ElementoActual = VOMoving.darElemento(i);
				count = 0;
			}
		}
		
		ArregloDinamico<LocationVO> aux = new ArregloDinamico<LocationVO>(2 * numeroVias);
		long tiempoColaInicial = agregarDatosCola(locationVo);
		long tiempoHeapInicial = agregarDatosHeap(locationVo);
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < numeroVias; i++)
		{
			LocationVO  respuesta =  cola.delMax();
			aux.agregar(respuesta);
		}
		long tiempoCola = (System.currentTimeMillis() - startTime) + tiempoColaInicial;
		long startTime2 = System.currentTimeMillis();
		
		for(int i = 0; i < numeroVias; i++)
		{
			LocationVO  respuesta =  heap.delMax();
			aux.agregar(respuesta);
		}
		long tiempoHeap = (System.currentTimeMillis() - startTime2)+ tiempoHeapInicial;
		System.out.println("El tiempo que tomo para la cola de prioridad fue : " + tiempoCola+ "\n" + "El tiempo que tomo para heap fue de :" + tiempoHeap);

		return aux;
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