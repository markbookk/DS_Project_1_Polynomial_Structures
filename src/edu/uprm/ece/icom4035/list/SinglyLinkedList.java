package edu.uprm.ece.icom4035.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List {

	private class ListIterator<E> implements Iterator<E>{
		
		private Node<E> currentNode;
		
		public ListIterator() {
			this.currentNode = (Node<E>)header;
		}
		@Override
		public boolean hasNext() {
			return this.currentNode != null;
		}

		@Override
		public E next() {
			if (this.hasNext()) {
				E result= null;
				result = this.currentNode.getElement();
				this.currentNode = this.currentNode.getNext();
				return result;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		
	}
	
	private static class Node<E>{
		
		private E element;
		private Node<E> next;
		
		public Node(){
			this.element = null;
			this.next = null;
		}
		
		public Node(Object e, Node<E> N) {
			this.element = (E) e;
			this.next = N;
		}

		public E getElement() {
			return element;
		}

		public void setElement(Object e) {
			this.element = (E) e;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}
	}
	
	private Node<E> header;
	private int size;
	
	public SinglyLinkedList() {
		this.header = new Node<E>();
		this.size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		this.checkIndex(index);
		Node<E> target = this.findNode(index);
		return target.getElement();
	}



	@Override
	public boolean remove(int index) throws IndexOutOfBoundsException {
		if (this.isEmpty()) {
			return false;
		}
		this.checkIndex(index);
		if (index == 0) {
			Node<E> temp = this.header.getNext();
			this.header.setNext( this.header.getNext().getNext() );
			temp.setNext(null);
			temp.setElement(null);
			this.size--;
			return true;
		}
		else {
			Node<E> temp1 = this.findNode(index - 1);
			Node<E> temp2 = temp1.getNext();
			E result = temp2.getElement();
			temp1.setNext(temp2.getNext());
			temp2.setNext(null);
			temp2.setElement(null);
			this.size--;
			return true;
		}
	}

	@Override
	public E set(int index, Object e) {
		this.checkIndex(index);
		Node<E> target = this.findNode(index);
		E result = target.getElement();
		target.setElement(e);
		return result;
	}

	@Override
	public void add(int index, Object e) throws IndexOutOfBoundsException {
		if (index == this.size()) {
			this.add(e);
			return;
		}
		this.checkIndex(index);

		if (index == 0) {
			Node<E> newNode = new Node<E>(e, null);
			newNode.setNext(this.header.getNext());
			this.header.setNext(newNode);
		}
		else {
			Node<E> newNode = new Node<E>(e, null);
			Node<E> temp = this.findNode(index-1);
			newNode.setNext(temp.getNext());
			temp.setNext(newNode);
		}
		this.size++;
	}

	@Override
	public void add(Object e) {
		if (this.isEmpty()) {
			this.header.setNext(new Node(e, null));

		}
		else {
			Node<E> newNode = new Node<E>(e,null );
			Node<E> temp = this.findNode(this.size() - 1);
			temp.setNext(newNode);
		}
		this.size++;
	}

	
	private Node<E> findNode(int index) {
		index = index + 1;
		Node<E> temp = this.header;
		int i = 0;
		
		while (i < index) {
			temp = temp.getNext();
			i++;
		}
		return temp;
		
	}

	private void checkIndex(int index) {
		index = index + 1;
		if ((index <= 0) || (index >= this.size()+1)){
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator<E>();
	}

	
	
	
	
	
	@Override
	public boolean remove(Object obj) {
		Node<E> temp = this.header;
		if (temp.getNext() == null)
			return false;
		int count = 0;
		while (!temp.getNext().equals(obj)) {
			temp = temp.getNext();
			count ++;
		}
		if (count >= size()-1)
			return false;
			
		remove(count);
		size --;
		return true;
	}

	@Override
	public int removeAll(Object obj) {
		int amountRemoved = 0;
		int count = 0;
		Node<E> temp = this.header;
		while (temp.getNext() != null) {
			if (temp.getNext().equals(obj)) {
				temp.setNext(temp.getNext().getNext());
				remove(count);
			}
			temp = temp.getNext();
		}
				
		// TODO Auto-generated method stub
		return amountRemoved;
	}

	@Override
	public Object first() {
		if (isEmpty())
			return null;
		return header.getNext();
	}

	@Override
	public Object last() {
		if (isEmpty())
			return null;
		return findNode(size()-1);
	}

	@Override
	public int firstIndex(Object obj) {
		int index = 0;
		Node<E> temp = this.header;
		while (!temp.getNext().equals(obj)) {
			temp = temp.getNext();
			index ++;
		}
		if (temp.getNext().equals(obj))
			return index+1;
		return -1;
	}

	@Override
	public int lastIndex(Object obj) {
		int index = 0;
		int lastIndexPos = -1;
		Node<E> temp = this.header;
		while (temp.getNext() != null) {
			if (temp.getNext().equals(obj))
				lastIndexPos = index;
			temp = temp.getNext();
			index ++;
		}
		if (temp.getNext().equals(obj))
			return index+1;
		return -1;
	}

	@Override
	public boolean contains(Object obj) {
		Node<E> temp = this.header;
		while (!temp.getNext().equals(obj)) {
			temp = temp.getNext();
		}
		if (temp.getNext().equals(obj))
			return true;
		return false;
	}

	@Override
	public void clear() {
		header.setNext(null);
	}

	
}
