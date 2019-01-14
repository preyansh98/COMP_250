package Assignment1;

public class Fruit extends MarketProduct{
	private double weight;
	private int priceperkg; 
	
	public Fruit(String name, double weight, int priceperkg) {
		super(name); //init. constructor with name
		this.weight = weight;
		this.priceperkg = priceperkg;
	}
	

	public int getCost() {
		int fruitcost = (int) (weight*priceperkg);
		return fruitcost;
	}


	public boolean equals(Object obj) {
		if(obj instanceof Fruit && this.weight==( (Fruit) obj).weight 
				&& this.getName()==((Fruit) obj).getName() 
				&& this.priceperkg ==( (Fruit) obj).priceperkg) {
			return true;
		}
		else {
			return false;
		}        
	}

}
