package model.data_structures;

public class Nodo<T> 
{
	/**
	 * Constante de serialización
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Nodo anterior.
	 */
	//TODO Declarar el nodo anterior.
	
	private Nodo<T> anterior; 
	
	private Nodo<T> siguiente;
	
	private T elemento;
	
	/**
	 * Método constructor del nodo doblemente encadenado
	 * @param elemento elemento que se almacenará en el nodo.
	 */
	public Nodo(T pElemento) 
	{
		elemento = pElemento;
		siguiente = null;
		anterior=null;
	}
	
	/**
	 * Método que retorna el nodo anterior.
	 * @return Nodo anterior.
	 */
	public Nodo<T> darAnterior()
	{
		//TODO Completar el método de acuerdo a la documentación.
		return anterior;
	}
	
	/**
	 * Método que cambia el nodo anterior por el que llega como parámetro.
	 * @param anterior Nuevo nodo anterior.
	 */
	public void cambiarAnterior(Nodo<T> pAnterior)
	{
		//TODO Completar el método de acuerdo a la documentación.
	
		anterior = pAnterior;
	}

	
	/**
	 * Método que retorna el siguiente nodo.
	 * @return Siguiente nodo
	 */
	public Nodo<T> darSiguiente()
	{
		//TODO Completar el método de acuerdo a la documentación.
		return siguiente;
	}

	/**
	 * Método que cambia el siguiente nodo.
	 * <b>post: </b> Se ha cambiado el siguiente nodo
	 * @param siguiente El nuevo siguiente nodo
	 */
	public void cambiarSiguiente(Nodo<T> pSiguiente)
	{
		//TODO Completar el método de acuerdo a la documentación.
		siguiente = pSiguiente;
	}
	
	/**
	 * Método que retorna el elemento almacenado en el nodo.
	 * @return El elemento almacenado en el nodo.
	 */
	public T darElemento()
	{
		//TODO Completar el método de acuerdo a la documentación.
		return elemento;
	}
	
	/**
	 * Cambia el elemento almacenado en el nodo.
	 * @param elemento El nuevo elemento que se almacenará en el nodo.
	 */
	public void cambiarElemento(T pElemento)
	{
		//TODO Completar el método de acuerdo a la documentación.
		elemento = pElemento;
	}
}