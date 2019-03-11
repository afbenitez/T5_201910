package model.data_structures;

public interface IColaPrioridad<T> extends Iterable<T>
{
	int darNumeroElementos();

	void agregar(T pElemento);

	T delMax();

	T max(); 

	boolean esVacia();

}
