package Assignment1;

public class Jam extends MarketProduct{
	private int NoOfJars;
	private int priceperjar;
	
	public Jam(String name, int NoOfJars, int priceperjar) {
		super(name);
		this.NoOfJars = NoOfJars;
		this.priceperjar = priceperjar;
	}

	public int getCost() {
		int cost = NoOfJars * priceperjar;
		return cost;
	}

	
	public boolean equals(Object obj) {
		if(obj instanceof Jam && this.priceperjar==( (Jam) obj).priceperjar 
				&& this.getName()==((Jam) obj).getName() 
				&& this.NoOfJars ==( (Jam) obj).NoOfJars) {
			return true;
		}
		else {
			return false;
		}        
	}
}
