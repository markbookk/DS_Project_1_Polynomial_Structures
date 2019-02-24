package edu.uprm.ece.icom4035.list;

public class ArrayListFactory<E> implements ListFactory{

	@Override
	public List newInstance() {
		return new ArrayList<E>();
	}

}
