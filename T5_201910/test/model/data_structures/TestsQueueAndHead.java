package model.data_structures;

import junit.framework.TestCase;
import model.data_structures.*;

public class TestsQueueAndHead extends TestCase
{

	MaxColaPrioridad <Integer> cola;
	MaxHeapCP <Integer> heap;

	public void setUp()
	{
		cola= new MaxColaPrioridad <Integer>();
		cola.agregar(4);
		cola.agregar(5);
		cola.agregar(3);
		
		
		heap= new MaxHeapCP <Integer>(6);
		heap.agregar(4);
		heap.agregar(2);
		heap.agregar(1);
	}

	public void testAgregarCola()
	{
		cola.agregar(2);
		cola.agregar(5);
	
		assertEquals("No se agregó el elemento", 4, cola.darNumeroElementos());
	}

	public void testDelMaxCola()
	{
		cola.agregar(6);
		assertEquals("No se eliminó el elemento con mayor prioridad", 6, cola.delMax().intValue());
	}

	public void testSizeCola() throws Exception
	{
		assertEquals("El tamaño no es el esperado",3,cola.darNumeroElementos());
	}


	public void testAgregarHeap()
	{
		heap.agregar(0);
		assertEquals("No se agregó el elemento", 0, heap.darElementoPos(4).intValue());
	}

	public void testDelMaxHeap()
	{
		heap.agregar(6);
		assertEquals("No se eliminó el elemento con mayor prioridad", 6, heap.delMax().intValue());
	}

	public void testSizeQHeap() throws Exception
	{
		assertEquals("El tamaño no es el esperado",3,heap.darNumeroElementos());
	}
}