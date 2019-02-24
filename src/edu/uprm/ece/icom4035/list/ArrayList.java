	package edu.uprm.ece.icom4035.list;

import java.util.Collection;
import java.util.Iterator;


public class ArrayList<E> implements List{
	
	private static final int INITCAP = 5;
	private static final int CAPTOAR = 1;
	private static final int MAXEMPTYPOS = 2;
	private E[] elements;
	private int size;
	
	public ArrayList() {
		elements = (E[]) new Object[INITCAP];
		size = 0;
	}
	

	@Override
	public Iterator iterator() {
			
		return new Iterator() {
//			private E current = (size > 0) ? elements[0] : null;
			private int currentIndex = (size > 0) ? 0 : null;

			@Override
			public boolean hasNext() {
				if (currentIndex >= size)
					return false;
				return true;
			}

			@Override
			public Object next() {
				if (hasNext())
					return elements[currentIndex+1];
				return null;
			}
		};
			
	}

	@Override
	public void add(Object obj) {
		if (size() == elements.length)
			changeCapacity(CAPTOAR);
		elements[size()] = (E) obj;
		size ++;

		
	}

	@Override
	public void add(int index, Object obj) {
		if ((index != 0 && isEmpty()) || index > size() || index < 0) {
			throw new IndexOutOfBoundsException("add: invalid index = " + index);
		}
		
//		if (!(index > elements.length))//Check if the index exists or if it will bring error
		if (size() == elements.length)
			changeCapacity(CAPTOAR);
		
//		if (element[index] != null)
		moveDataOnePositionTR(index,size()-1);
		
		elements[index] = (E) obj;
		size ++;
	}


	@Override
	public boolean remove(Object obj) {
		int index = 0;
		for (Object i: elements) {
			if (i.equals(obj)) {
				break;
			}
			index ++;
		}
		if (index == elements.length - 1)
			return false;
		
		if (elements.length - size() >= MAXEMPTYPOS)
			changeCapacity(-CAPTOAR);
		
		moveDataOnePositionTL(index+1,size()-1);
		elements[size-1] = null;//Null the last position
		
		size --;
		return true;
	}

	@Override
	public boolean remove(int index) {
		if (isEmpty() || index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("remove: invalid index = " + index);
		}
		
		if (elements.length - size() >= MAXEMPTYPOS)
			changeCapacity(-CAPTOAR);
		
		moveDataOnePositionTL(index+1,size()-1);
		elements[size-1] = null;//Null the last position
		
		size --;
		return true;

	}

	@Override
	public int removeAll(Object obj) {
		int amountRemoved = 0;
		int index = 0;
		E[] elementsCopy = elements.clone();
		for (Object i: elementsCopy) {
			if (i.equals(obj)) {
				if (elements.length - size() >= MAXEMPTYPOS)
					changeCapacity(-CAPTOAR);
				moveDataOnePositionTL(index+1,size()-1);
				elements[size-1] = null;//Null the last position
				index--; //We subtract because there is one less element in the list
				amountRemoved ++;
				size --;
			}
			index ++;
		}
		
		return amountRemoved;
	}

	@Override
	public Object get(int index) {
		if (isEmpty() || index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("get: invalid index = " + index);
		}
		return elements[index];
	}

	@Override
	public Object set(int index, Object obj) {
		if (isEmpty() || index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("set: invalid index = " + index);
		}
		E previousElement = elements[index];
		elements[index]  = (E) obj;
		return previousElement;
	}

	@Override
	public Object first() {
		if (isEmpty())
			return null;
		return elements[0];
	}

	@Override
	public Object last() {
		if (isEmpty())
			return null;
		return elements[size()-1];
	}

	@Override
	public int firstIndex(Object obj) {
		int index = 0;
		for (Object i: elements) {
			if (i.equals(obj))
				break;
			index ++;
		}
		if (index == elements.length-1)
			return -1;
		return index;
		
	}

	@Override
	public int lastIndex(Object obj) {
		int index = 0;
		int lastIndexPos = -1;
		for (Object i: elements) {
			if (i.equals(obj))
				lastIndexPos = index;
			index ++;
		}
		
		return lastIndexPos;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size () == 0;
	}

	@Override
	public boolean contains(Object obj) {
		for (Object i: elements)
			if (i.equals(obj))
				return true;
		return false;
	}

	@Override
	public void clear() {
		int index = 0;
		for (Object i: elements) {
			if (i != null) {
				elements[index] = null;
			}
			size --;
		}
		
		if (elements.length - size() >= MAXEMPTYPOS)
			changeCapacity(-CAPTOAR);
		
	}
	
	
	//private methods
	
	private void changeCapacity(int change) { 
		int newCapacity = elements.length + change; 
		E[] newElement = (E[]) new Object[newCapacity]; 
		for (int i=0; i<size; i++) { 
			newElement[i] = elements[i]; 
			elements[i] = null; 
		} 
		elements = newElement; 
	}
	
	// useful when adding a new element with the add
	// with two parameters....
	private void moveDataOnePositionTR(int low, int sup) { 
		// pre: 0 <= low <= sup < (element.length - 1)
		for (int pos = sup; pos >= low; pos--)
			elements[pos+1] = elements[pos]; 
	}

	// useful when removing an element from the list...
	private void moveDataOnePositionTL(int low, int sup) { 
		// pre: 0 < low <= sup <= (element.length - 1)
		for (int pos = low; pos <= sup; pos++)
			elements[pos-1] = elements[pos]; 
	}


}
