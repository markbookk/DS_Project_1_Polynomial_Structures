package edu.uprm.ece.icom4035.polynomial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterators;

public class PolynomialImp implements Polynomial{
	//keeps track of a list of terms and a ListFactory
	//uses the factory create the list of terms
	//TermListFactory tells this class which factory to use
	
	public PolynomialImp(String poly) {
		String[] splitPoly = poly.split("\\+");
		ArrayList<Term> polyArray = new ArrayList<>();
		
		for (String i: splitPoly) {
			String[] splitTerm = i.split("x");
			
			if (!splitTerm[1].contains("^"))
				
			
			polyArray.add(new TermpImp(Double.parseDouble(splitTerm[0]), Integer.parseInt(splitTerm[1])));
		}
		
		System.exit(0);
		
	}

	@Override
	public Iterator<Term> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial add(Polynomial P2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial subtract(Polynomial P2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial multiply(Polynomial P2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial multiply(double c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial derivative() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial indefiniteIntegral() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double definiteIntegral(double a, double b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int degree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double evaluate(double x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Polynomial P) {
		// TODO Auto-generated method stub
		return false;
	}

}
