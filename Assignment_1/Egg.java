package Assignment1;

public class Egg extends MarketProduct{
	private int NoOfEggs;
	private int price;
	
	public Egg(String name, int NoOfEggs, int price) {
		super(name); //initializes a MarketProduct with this name
		this.NoOfEggs = NoOfEggs;
		this.price = price;
	}

	public int getCost() {
		double eachprice = (double) price/12;
		double fullcost = eachprice*NoOfEggs;
		
		return (int) (fullcost);
	}
	
	
	public boolean equals(Object obj) {
		if(obj instanceof Egg && this.price==( (Egg) obj).price 
				&& this.getName()==((Egg) obj).getName() 
				&& this.NoOfEggs ==( (Egg) obj).NoOfEggs) {
			return true;
		}
		else {
			return false;
		}        
	}
}
