package model.data_structures;

public interface IArregloDinamico<T> 
{

	/**
	 * Retornar el numero de elementos en el arreglo.
	 * @return el tamaño del arreglo.
	 */
	int darTamanio( );

	/**
	 * Retornar el elemento en la posición i.
	 * @param i posicion del elemento.
	 * @return elemento de consulta. null si no hay elemento en posicion.
	 */
	T darElemento( int i );

	/**
	 * Agregar un dato de forma compacta (en la primera casilla disponible) .
	 * @param dato nuevo elemento.
	 */
	public void agregar( Object dato );
}
