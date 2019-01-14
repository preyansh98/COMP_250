package a2; 

//Preyansh Kaushik 260790402

import java.math.BigInteger;


public class Polynomial 
{
	private SLinkedList<Term> polynomial;
	public int size()
	{	
		return polynomial.size();
	}

	private Polynomial(SLinkedList<Term> p)
	{
		polynomial = p;
	}
	
	public Polynomial()
	{
		polynomial = new SLinkedList<Term>();
	}
	
	// Returns a deep copy of the object.
	public Polynomial deepClone()
	{	
		return new Polynomial(polynomial.deepClone());
	}
	
	/* 
	 * TODO: Add new term to the polynomial. Also ensure the polynomial is
	 * in decreasing order of exponent.
	 */

	public void addTerm(Term t)

	{	
		BigInteger zeroint = new BigInteger("0");
		if(t.getExponent() >= 0 && !(t.getCoefficient().equals(zeroint))) {
		if (polynomial.isEmpty()) {
			//if the polynomial is empty, add it to the start 
			polynomial.addFirst(t);
		} else {
			boolean isAdded = false;
			int i = 0;
			for (Term thisTerm : polynomial) {
				if(t.getExponent() > thisTerm.getExponent()) {
					polynomial.add(i, t);
					isAdded = true;
					break;
				} else if(t.getExponent() == thisTerm.getExponent()) {
					if(t.getCoefficient().equals(thisTerm.getCoefficient().multiply(new BigInteger("-1")))){
						//cancel the terms
						thisTerm.setCoefficient(new BigInteger("5"));
						polynomial.remove(i);	
						isAdded = true;
						break;
					}
					else {
					BigInteger sum = new BigInteger("0");
					sum = t.getCoefficient().add(thisTerm.getCoefficient());
					thisTerm.setCoefficient(sum);
					isAdded = true;
					break;
				}}
				i++;
			}
			if(isAdded == false) {
				polynomial.addLast(t);
			}
		}}}
	
	public Term getTerm(int index)
	{
		return polynomial.get(index);
	}
	
	//TODO: Add two polynomial without modifying either
	public static Polynomial add(Polynomial p1, Polynomial p2)
	{
		/**** ADD CODE HERE ****/
		//clone the first polynomial 
		Polynomial p1clone = new Polynomial();
		
		p1clone = p1.deepClone();
		
		Polynomial p2clone = new Polynomial();
		
		p2clone = p2.deepClone();	
		
		Polynomial addedpoly = new Polynomial();
		


		
		while(!p1clone.polynomial.isEmpty() || !p2clone.polynomial.isEmpty()) {

			//check if p1clone is empty
			if(p1clone.polynomial.isEmpty()) {
				//addLast the term from p2 into addedpoly
				int p2firstexp = p2clone.polynomial.get(0).getExponent();
				BigInteger p2firstcoeff = p2clone.polynomial.get(0).getCoefficient();
				addedpoly.addTermLast(new Term(p2firstexp, p2firstcoeff));
				p2clone.polynomial.removeFirst(); //removes the first so cycles through same node over and over
			}
			
			else if(p2clone.polynomial.isEmpty()) {
				int p1firstexp = p1clone.polynomial.get(0).getExponent();
				BigInteger p1firstcoeff = p1clone.polynomial.get(0).getCoefficient();
				addedpoly.addTermLast(new Term(p1firstexp, p1firstcoeff));
				p1clone.polynomial.removeFirst();
				
			}
			
			else if(p1clone.polynomial.get(0).getExponent() > p2clone.polynomial.get(0).getExponent()) {
				int p1firstexp = p1clone.polynomial.get(0).getExponent();
				BigInteger p1firstcoeff = p1clone.polynomial.get(0).getCoefficient();
				addedpoly.addTermLast(new Term(p1firstexp, p1firstcoeff));
				p1clone.polynomial.removeFirst();
			}
			else if(p1clone.polynomial.get(0).getExponent() == p2clone.polynomial.get(0).getExponent()) {
				int p1firstexp = p1clone.polynomial.get(0).getExponent();
				BigInteger p1firstcoeff = p1clone.polynomial.get(0).getCoefficient();
				BigInteger p2firstcoeff = p2clone.polynomial.get(0).getCoefficient();
				addedpoly.addTermLast(new Term(p1firstexp, p1firstcoeff.add(p2firstcoeff)));
				p2clone.polynomial.removeFirst();
				p1clone.polynomial.removeFirst();

			}
			else {
				int p2firstexp = p2clone.polynomial.get(0).getExponent();
				BigInteger p2firstcoeff = p2clone.polynomial.get(0).getCoefficient();
				addedpoly.addTermLast(new Term(p2firstexp, p2firstcoeff));
				p2clone.polynomial.removeFirst();
			}
		}
		
		
		
		
		return addedpoly;
	}
	
	//TODO: multiply this polynomial by a given term.
	private void multiplyTerm(Term t)
	{	
		if(t.getExponent() >= 0) {
		BigInteger calculatedCoeff = new BigInteger("0");
		BigInteger zero = new BigInteger("0");
		int calculatedExp = 0;
		for(Term thisTerm : polynomial) {
		if(t.getCoefficient().equals(zero)) {
			polynomial.clear();

			
		}
		else {
		calculatedCoeff = (t.getCoefficient().multiply(thisTerm.getCoefficient()));
		calculatedExp = (t.getExponent() + thisTerm.getExponent());
		
		thisTerm.setCoefficient(calculatedCoeff);
		thisTerm.setExponent(calculatedExp);
		}
		}}
	}
	
	//TODO: multiply two polynomials
	public static Polynomial multiply(Polynomial p1, Polynomial p2)
	{

		Polynomial p1copy = p1.deepClone();
		Polynomial p2copy = p2.deepClone();
		Polynomial p2aftermultiplying = new Polynomial();
		
		for(int k = 0; k<p1copy.size(); k++) {
			p2copy.multiplyTerm(p1copy.polynomial.get(k));
			p2aftermultiplying = add(p2copy, p2aftermultiplying);
			
			p2copy = p2.deepClone();
		}
		return p2aftermultiplying;
	}
		
	
	//TODO: evaluate this polynomial.
	// Hint:  The time complexity of eval() must be order O(m), 
	// where m is the largest degree of the polynomial. Notice 
	// that the function SLinkedList.get(index) method is O(m), 
	// so if your eval() method were to call the get(index) 
	// method m times then your eval method would be O(m^2).
	// Instead, use a Java enhanced for loop to iterate through 
	// the terms of an SLinkedList.

	public BigInteger eval(BigInteger x)
	{
        if(!(this.polynomial.isEmpty())){
        int arraysize = this.getTerm(0).getExponent()+1;
		BigInteger[] polyarray = new BigInteger[arraysize];		
	
		BigInteger firstcoeff = this.getTerm(0).getCoefficient();
		int firstexp = this.getTerm(0).getExponent();
		polyarray[0] = firstcoeff;
		
		for(int i = 1; i<arraysize; i++) {
			polyarray[i] = new BigInteger("0");
		}
		
		Polynomial copyofp1 = this.deepClone();
		copyofp1.polynomial.removeFirst();
				
		while(!(copyofp1.size()==0)) {
			BigInteger coefftoadd = copyofp1.getTerm(0).getCoefficient();
			int expforposition = copyofp1.getTerm(0).getExponent();
			copyofp1.polynomial.removeFirst();
			
			polyarray[firstexp - expforposition] = coefftoadd;
					
		}
     	
        BigInteger result = new BigInteger("0");
        result = polyarray[0];
    	
        BigInteger multiplied = new BigInteger("0");
        	
        for (int i=1; i<polyarray.length; i++) {
        	multiplied = (result.multiply(x));
        	result = multiplied.add(polyarray[i]);
        }
        
        return result;}
        else {
        	return (new BigInteger("0"));
        }
}
	
	// Checks if this polynomial is same as the polynomial in the argument
	public boolean checkEqual(Polynomial p)
	{	
		if (polynomial == null || p.polynomial == null || size() != p.size())
			return false;
		
		int index = 0;
		for (Term term0 : polynomial)
		{
			Term term1 = p.getTerm(index);
			
			if (term0.getExponent() != term1.getExponent() ||
				term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || term1 == term0)
					return false;
			
			index++;
		}
		return true;
	}
	
	// This method blindly adds a term to the end of LinkedList polynomial. 
	// Avoid using this method in your implementation as it is only used for testing.
	public void addTermLast(Term t)
	{	
		polynomial.addLast(t);
	}
	
	// This is used for testing multiplyTerm
	public void multiplyTermTest(Term t)
	{
		multiplyTerm(t);
	}
	
	@Override
	public String toString()
	{	
		if (polynomial.size() == 0) return "0";
		return polynomial.toString();
	}
}
