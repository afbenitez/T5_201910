package model.data_structures;

import java.util.Iterator;

public class MaxHeapCP<T extends Comparable<T>> implements IColaPrioridad<T> 
{
	private T[] pq;                  
	private int n;                  

	public MaxHeapCP(int initCapacity) 
	{
		pq = (T[]) new Comparable[initCapacity + 1];
		n = 0;
	}

	public MaxHeapCP(T[] elementos) 
	{
		n = elementos.length;
		pq = (T[]) new Comparable[elementos.length + 1];
		for (int i = 0; i < n; i++)
			pq[i+1] = elementos[i];
		for (int k = n/2; k >= 1; k--)
			sink(k);
	}

	public boolean esVacia() {
		return n == 0;
	}

	public int darNumeroElementos() {
		return n;
	}

	public T max() {
		if (esVacia()) 
			return null;

		return pq[1];
	}

	private void resize(int capacity) {
		assert capacity > n;
		T[] temp = (T[]) new Comparable[capacity];
		for (int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}

	public void agregar(T x) {
		if (n == pq.length - 1) resize(2 * pq.length);

		pq[++n] = x;
		swim(n);
	}

	public T delMax() {
		if (esVacia()) return null;
		T max = pq[1];
		exch(1, n--);
		sink(1);
		pq[n+1] = null;
		if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
		return max;
	}

	private void swim(int k) {
		while (k > 1 && less(k/2, k)) {
			exch(k, k/2);
			k = k/2;
		}
	}

	private void sink(int k) {
		while (2*k <= n) {
			int j = 2*k;
			if (j < n && less(j, j+1)) j++;
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	private boolean less(int i, int j)
	{
		return pq[i].compareTo(pq[j])<0;
	}

	private void exch(int i, int j) 
	{
		T swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}


	private boolean isMaxHeap(int k) {
		if (k > n) return true;
		int left = 2*k;
		int right = 2*k + 1;
		if (left  <= n && less(k, left))  return false;
		if (right <= n && less(k, right)) return false;
		return isMaxHeap(left) && isMaxHeap(right);
	}

	public T darElementoPos(int i)
	{
		return pq[i];
	}
	
	public Iterator<T> iterator() {
		return new HeapIterator();
	}

	private class HeapIterator implements Iterator<T> {

		private MaxHeapCP<T> copy;

		public HeapIterator() {

			for (int i = 1; i <= n; i++)
				copy.agregar(pq[i]);
		}

		public boolean hasNext()  
		{ 
			return !copy.esVacia();                     
		}

		public T next() {
			if (!hasNext()) return null;
			return copy.delMax();
		}
	}

}