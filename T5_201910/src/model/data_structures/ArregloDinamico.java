package model.data_structures;


public class ArregloDinamico<T extends Comparable<T>> implements IArregloDinamico 
{
	
	/**
	 * Numero de elementos en el arreglo.
	 */

	private int tamanio;
	/**
	 * Capacidad maxima del arreglo.
	 */

	private int maximo;

	/**
	 * Arreglo de elementos.
	 */

	private T elementos[ ];

	/**
	 * Construye el arreglo con la capacidad dada por parametro.
	 * @param pMaximo Capacidad maxima inicial del arreglo.
	 */

	public ArregloDinamico( int pMaximo )
	{
		elementos = (T[ ]) new Comparable[pMaximo];
		maximo = pMaximo;
		tamanio = 0;
	}

	/**
	 * Da el tamaño actual. 
	 */

	public int darTamanio() 
	{
		return tamanio;
	}
	
	/**
	 * Da el tamaño máximo del arreglo. 
	 */

	public int darTamanoMax()
	{
		return maximo;
	}

	/**
	 * Da el elemento actual. 
	 * @param i posicion del elemento.
	 */
	public T darElemento(int i) 
	{
		return elementos[i];
	}

	/**
	 * Asigna un elemento en la posición dada.
	 * @param n Posicion a asignar.
	 * @param pDato dato a asignar.
	 */

	public void asignar(int n, T pDato)
	{
		elementos[n] = pDato; 
	}

	/**
	 * Agrega un elemento. 
	 * @param pDato dato a agregar.
	 */

	public void agregar( Object pDato )
	{
		if ( tamanio == maximo )
		{  
			maximo = 2 * maximo;
			T [ ] copia = elementos;
			elementos = (T[ ]) new Comparable[maximo];
			for (int i = 0; i < tamanio; i++)
			{
				elementos[i] = copia[i];
			} 
		}	
		elementos[tamanio] = (T)pDato;
		tamanio++;
	}
	

	/**
	 * Da el elemento a buscar. 
	 * @param pDato dato a buscar.
	 */
	public T buscar(T pDato) 
	{
		T result = null; 
		for (int u = 0; u < tamanio && result == null; u++)
		{
			if(elementos[u].compareTo(pDato) == 0)
				result = elementos[u];
		}
		return result;
	}

	/**
	 * Da elemento eliminado. 
	 * @param pDato dato a eliminar.
	 */
	public T eliminar(T pDato) 
	{
		T resultat = null; int l; 
		for (l = 0; l < tamanio && resultat == null; l++)
		{
			if(elementos[l].compareTo(pDato) == 0)
				resultat = elementos[l];
		}
		l--;
		while(elementos[l] != null && l < tamanio-1)
		{
			elementos[l] = elementos[l+1];
			l++;
		}
		elementos[l] = null; 
		tamanio--; 
		return resultat;
	}

}

