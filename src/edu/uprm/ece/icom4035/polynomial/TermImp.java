package edu.uprm.ece.icom4035.polynomial;

public class TermImp implements Term{
	
	private double coefficient;
	private int exponent;
	
	public TermImp (double coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}

	@Override
	public double getCoefficient() {
		return this.coefficient;
	}

	@Override
	public int getExponent() {
		return this.exponent;
	}

	@Override
	public double evaluate(double x) {
		// TODO Auto-generated method stub
		return coefficient * Math.pow(x, exponent) ;
	}

}
