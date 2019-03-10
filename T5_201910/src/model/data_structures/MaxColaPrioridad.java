package model.data_structures;

import java.util.Iterator;

public class MaxColaPrioridad <T  extends Comparable<T>> implements IColaPrioridad<T> 
{


	private Nodo<T> primero;
	private int numeroElementos;
	private IteradorLista<T> iterador;

	public MaxColaPrioridad() 
	{
		// TODO Auto-generated constructor stub
		primero = null;
		numeroElementos = 0;
		iterador = new IteradorLista<T>(primero);
	}

	@Override
	public Iterator<T> iterator() 
	{
		// TODO Auto-generated method stub
		return iterador;
	}

	@Override
	public int darNumeroElementos()
	{
		// TODO Auto-generated method stub
		return numeroElementos;
	}

	@Override
	public void agregar(T elemento) 
	{		
		Nodo<T> nuevo = new Nodo<T>(elemento);

		if(primero == null)
		{
			primero = nuevo;
		}

		else
		{
			Nodo<T> actual = primero;

			while(actual != null)
			{
				
				if(nuevo.darElemento().compareTo(actual.darElemento()) > 0)
				{
					if(actual == primero)
					{
						primero.cambiarAnterior(nuevo);
						nuevo.cambiarSiguiente(primero);
						primero = nuevo;
					}
					else
					{
						nuevo.cambiarSiguiente(actual);
						nuevo.cambiarAnterior(actual.darAnterior());
						actual.darAnterior().cambiarSiguiente(nuevo);
						actual.cambiarAnterior(nuevo);
					}
					break;
				}

				else if(actual.darElemento().compareTo(nuevo.darElemento()) == 0)
				{
					if(actual.darAnterior() == null)
					{
						if(actual.darSiguiente() != null)
						{
							actual.darSiguiente().cambiarAnterior(null);
						}
						primero=actual.darSiguiente();
					}

					else if(actual.darSiguiente() == null)
					{
						actual.darAnterior().cambiarSiguiente(null);
					}

					else
					{
						actual.darAnterior().cambiarSiguiente(actual.darSiguiente());
						actual.darSiguiente().cambiarAnterior(actual.darAnterior());
					}
					
					numeroElementos -= 2;
					agregar(nuevo.darElemento());
					break;
				}

				else
				{
					if(actual.darSiguiente() != null)
					{
						actual=actual.darSiguiente();
					}
					else
					{
						actual.cambiarSiguiente(nuevo);
						nuevo.cambiarAnterior(actual);
						break;
					}
				}
			}

		}
		numeroElementos++;
	}
	@Override
	public T delMax() 
	{
		// TODO Auto-generated method stub
		Nodo<T> eliminado = null;
 
		if(primero == null)
			return null;

		else
		{
			eliminado = primero;
			primero = primero.darSiguiente();
			return eliminado.darElemento();
		}
	}

	@Override
	public T max() 
	{
		// TODO Auto-generated method stub

		if(primero == null)
			return null;

		else return primero.darElemento();

	}

	@Override
	public boolean esVacia()
	{
		if(primero == null)
			return true;

		else return false;
	}

	public T darElementoPos(int i)
	{
		int avance = 0;
		Nodo<T> actual = primero;
		while( actual!=null && i>avance)
		{
			actual = actual.darSiguiente();
			avance++;
		}

		if(actual!=null)
		{
			return actual.darElemento();
		}

		else return null;
	}

}
