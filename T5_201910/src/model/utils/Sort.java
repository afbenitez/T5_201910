package model.utils;

import java.util.Comparator;
import model.vo.VOMovingViolations;


public class Sort 
{
	
    private static Comparator comparadorActual;
	/**
	 * Ordenar datos aplicando el algoritmo MergeSort
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 * @param ultimo 
	 * @param mitad 
	 * @param inicio 
	 * @param aux 
	 */
	public static void ordenarMergeSort( VOMovingViolations[ ] datos, VOMovingViolations[] aux, int inicio, int mitad, int ultimo ) 
	{
		for (int k = inicio; k <= ultimo; k++)
		{	
			aux[k] = datos[k]; 
		}
		
		int i = inicio;
		int j = mitad+1;

		for (int k = inicio; k <= ultimo; k++)
		{
			if      (i > mitad)            
				datos[k] = aux[j++];
			
			else if (j > ultimo)             
				datos[k] = aux[i++];
			
			else if (less(aux[j], aux[i])) 
				datos[k] = aux[j++];
			
			else                          
				datos[k] = aux[i++];
		}
	}
	private static void sortM(VOMovingViolations[]datos, VOMovingViolations[] aux, int inicio, int fin)
	{
		if (fin<=inicio)
			{
				return;
			}
		
		int mid=inicio+(fin-inicio)/2;
		sortM(datos, aux, inicio, mid);
		sortM(datos, aux, mid+1, fin);
		ordenarMergeSort(datos, aux, inicio, mid, fin);
	}

	public static void sort(VOMovingViolations[]datos, Comparator entrada)
	{
		comparadorActual = entrada;
		VOMovingViolations[]aux = new VOMovingViolations[datos.length];
		sortM(datos, aux, 0, datos.length-1);
	}



	/**
	 * Comparar 2 objetos usando la comparacion "natural" de su clase
	 *param v primer objeto de comparacion
	 * @param w segundo objeto de comparacion
	 * @return true si v es menor que w usando el metodo compareTo. false en caso contrario.
	 */
	private static boolean less(VOMovingViolations v, VOMovingViolations w)
	{
		return comparadorActual.compare(v, w) < 0;
	}

	/**
	 * Intercambiar los datos de las posicion i y j
	 * @param datos contenedor de datos
	 * @param i posicion del 1er elemento a intercambiar
	 * @param j posicion del 2o elemento a intercambiar
	 */
	private static void exchange( VOMovingViolations[] datos, int i, int j)
	{
		VOMovingViolations temp = datos[i]; 
		datos[i] = datos[j]; 
		datos[j] = temp;
	}
	
	public static void invertirMuestra( VOMovingViolations[ ] datos )
	{

		VOMovingViolations[] nuevos = new VOMovingViolations[ datos.length ];
		int j = datos.length-1;
		for ( int i = 0 ; i < datos.length-1 ; i++ )
		{
			nuevos[i] = datos[j];
			j--;	
		}
            datos = nuevos;
	
	}


}