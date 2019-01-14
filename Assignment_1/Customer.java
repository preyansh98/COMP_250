package Assignment1;

public class Customer {
	private String name;
	private int balance; 
	private Basket basketofgoods;
	
	public Customer(String name, int balance) {
		this.name = name;
		this.balance = balance;
		basketofgoods = new Basket();
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}
	
	public Basket getBasket() {
		return basketofgoods; 
	}
	
	public int addFunds(int input) {
		if(input<0) {
			throw new IllegalArgumentException("You can't add negative funds");
			}
		else {
			balance = balance + input; 
			return balance; 
		}
	}
	
	public void addToBasket(MarketProduct product) {
		if(basketofgoods != null) {
		basketofgoods.add(product);
	}}
	
	public boolean removeFromBasket(MarketProduct product) {
		boolean resultofremove = false;
		if(basketofgoods.remove(product) == true) //have to check that the customer's basket isn't cleared. 
		{
		basketofgoods.remove(product); 
		resultofremove=true;
		}
		return resultofremove;
	}
	
	public String checkOut() {
		String basketreceipt = "";
		if(balance < (basketofgoods.getTotalCost())) {
			throw new IllegalStateException("You do not have enough funds to buy all the goods");
		}
		else {
			balance = balance - (basketofgoods.getTotalCost());
			basketreceipt+= basketofgoods.toString();
			basketofgoods.clear();
			return basketreceipt;
		}
	}	
}