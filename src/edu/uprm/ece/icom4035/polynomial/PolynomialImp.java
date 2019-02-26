package edu.uprm.ece.icom4035.polynomial;

import java.util.Iterator;
import java.util.Spliterators;

import edu.uprm.ece.icom4035.list.ArrayList;

public class PolynomialImp implements Polynomial{
	//keeps track of a list of terms and a ListFactory
	//uses the factory create the list of terms
	//TermListFactory tells this class which factory to use
	
	public ArrayList<Term> polyList = new ArrayList<>();
	
	public PolynomialImp(String poly) {
		String[] splitPoly = poly.split("\\+");
		
		for (String i: splitPoly) {
			String[] splitTerm = i.split("x");
			
			//If coefficient is zero, skip unless P(y) = 0
			if (splitTerm[0].equals("0") && poly != "0")
				continue;
			
			double coefficient = Double.parseDouble(splitTerm[0]);
			int exponent = 0;
			
			if (splitTerm.length > 1) { //8x^2
				String[] splitExponent = splitTerm[1].split("\\^");
				if (splitExponent.length > 1) { //x^2
					exponent = Integer.parseInt(splitExponent[1]);
				}else { //8x
					exponent = 1;
				}
			}else {
				//Do nothing because exponent = 0 is already true
			}
			
				
			polyList.add(new TermpImp(coefficient, exponent));
		}
		
//		//Debug
//		for (Term i: polyArray) {
//			System.out.println("Info: " + i.getCoefficient() + "\t" + i.getExponent());
//		}
//		System.exit(0);
		
	}

	@Override
	public Iterator<Term> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public Polynomial add(Polynomial P2) {
		PolynomialImp P1n = this;
		PolynomialImp P2n = (PolynomialImp) P2;
		
		
		PolynomialImp pTemp;
		if ( ((Term) (P1n.polyList.get(0))).getExponent() >= ((Term) (P2n.polyList.get(0))).getExponent() ) {
			;//Do nothing
		}else {//Exchanging the variables to start with
			pTemp = P1n;
			P1n = P2n;
			P2n = pTemp;
		}
		
		String polyString = "";
		int count = 0;
		
		for (int i=0; i<P1n.polyList.size(); i++) {
			if (count != 0)
				polyString = polyString + "+";
			
			if ( ((Term) P1n.polyList.get(count)).getExponent() > ((Term) P2n.polyList.get(count)).getExponent() ) { //if exponent is greater
				
				if ( ((Term) P1n.polyList.get(count)).getExponent() > 1 ) {//x^n
					polyString = polyString + ((Term) P1n.polyList.get(count)).getCoefficient() + "x^" + ((Term) P1n.polyList.get(count)).getExponent();
				}else if ( ((Term) P1n.polyList.get(count)).getExponent() == 1) {//x
					polyString = polyString + ((Term) P1n.polyList.get(count)).getCoefficient() + "x";
				}else {//without exponent, only coefficient
					polyString = polyString + ((Term) P1n.polyList.get(count)).getCoefficient();
				}
				
			}
			
			else if ( ((Term) P1n.polyList.get(count)).getExponent() == ((Term) P2n.polyList.get(count)).getExponent() ) { //if exponent is the same
				
				Double coefficientSum = ( ((Term) P1n.polyList.get(count)).getCoefficient() + ((Term) P2n.polyList.get(count)).getCoefficient() ) ;
				
				if ( ((Term) P1n.polyList.get(count)).getExponent() > 1 ) {//x^n
					polyString = polyString + coefficientSum + "x^" + ((Term) P1n.polyList.get(count)).getExponent();
				}else if ( ((Term) P1n.polyList.get(count)).getExponent() == 1) {//x
					polyString = polyString + coefficientSum + "x";
				}else {//without exponent, only coefficient
					polyString = polyString + coefficientSum;
				}
				
			}
			
			
//			System.out.println(sumPoly);
			count ++;
		}
		
		System.out.println(polyString);
		Polynomial output = new PolynomialImp(polyString);
		return output;
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
		if (this.toString().equals(P.toString()))
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		String polyString = "";
		int count = 0;
		for (int i=0; i<polyList.size(); i++) {
			if (count !=0)
				polyString += "+";
			if ( ((Term) this.polyList.get(count)).getExponent() > 1 ) {//x^n
				polyString = polyString + ((Term) this.polyList.get(count)).getCoefficient() + "x^" + ((Term) this.polyList.get(count)).getExponent();
			}else if ( ((Term) this.polyList.get(count)).getExponent() == 1) {//x
				polyString = polyString + ((Term) this.polyList.get(count)).getCoefficient() + "x";
			}else {//without exponent, only coefficient
				polyString = polyString + ((Term) this.polyList.get(count)).getCoefficient();
			}
		
			count ++;
		}
		
		return polyString;
	}

}
