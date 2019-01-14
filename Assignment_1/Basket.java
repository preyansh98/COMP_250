package Assignment1;

public class Basket {
	private static MarketProduct products[];
	private int i = 0;
	
	public Basket() {
		products = new MarketProduct[i];
	}
	

	
	public MarketProduct[] getProducts() {  
		MarketProduct[] basketcopy = new MarketProduct[products.length];
		
		for (int m = 0; m<products.length; m++) {
			basketcopy[m] = products[m]; //copying the products
		}
		return basketcopy;
	}
	
	public void add(MarketProduct productAdd) {
		MarketProduct[] arrayAdd = new MarketProduct[(products.length) + 1];
		for(int k=0; k<products.length; k++) {
			arrayAdd[k] = products[k];
		}
		arrayAdd[products.length] = productAdd; 
		products = arrayAdd;
	}

	public boolean remove(MarketProduct productRem) {
		boolean result = false;
		if(products == null || products.length == 0) {
			result = false;
			return result;
		}
		else {
		MarketProduct[] arrayRem = new MarketProduct[(products.length) - 1];
		//cycle through array. 
		for(int b = 0; b<products.length; b++) {
			//check if products[b] equals productrem
			if(products[b].equals(productRem) && result == false) {
				result = true;
				//if it is then shift everything from the left
				for(int k = b; k<(products.length)-1; k++) {
					products[k] = products[k+1];
				}
				//then copy everything EXCEPT the last element to the new array
				for(int c = 0; c<products.length-1; c++) {
					arrayRem[c] = products[c];
				}
				//then assign oldArray pointer to newArray
				products = arrayRem;
			}
		}
	return result;}
}
	
	public void clear() {
		MarketProduct[] emptyArray = new MarketProduct[0];
		while(products.length > 0) { //while the basket is not empty, set products to point to an empty basket
			products = emptyArray;
		}
	}
	
	public int getNumOfProducts() {
		int size = products.length;
		return size;
	}
	
	public int getSubTotal() {
		int subtotal = 0; 
		if(products != null) {
		for(int k = 0; k<products.length; k++) {
			int costofeach = (products[k].getCost());
			subtotal = subtotal + costofeach;
		}}
		return subtotal;
	}
	
	public int getTotalTax() {
		int totaltaxablecost = 0;
		int finaltax = 0;
		if(products!=null) {
		for(int k = 0; k<products.length; k++) {
		if(products[k] instanceof Jam) {
			totaltaxablecost+=products[k].getCost();
		}}
		finaltax = (int) (.15 * totaltaxablecost);}
		
		return finaltax;
	}
	
	public int getTotalCost() {
		return (getSubTotal() + getTotalTax());
	}
	
	public String toString() {
		String receipt = "";
		String productslist = "";
		if(products != null) {
		for(int t = 0; t<products.length; t++) {
			productslist+= products[t].getName() + "\t" + helper(products[t].getCost()) + "\n";
			//calling helper to format the cost appropriately
		}}
		receipt+=productslist;
		receipt+="\n";
		receipt+= "Subtotal" + "\t" + helper(getSubTotal());
		receipt+= "\n" + "Total Tax " + "\t" + helper(getTotalTax());
		receipt+="\n" + "\n";
		receipt+= "Total Cost " + "\t" + helper(getTotalCost());
		
		return receipt;
	}
	
	private String helper(double centstobeconverted) {
		String result = "";
		if(centstobeconverted > 0) {
			double converted = centstobeconverted/100; //treating as a double to get cents properly
			String numberinstring = String.format("%,.2f", converted); //formating to 2 decimal places
			result = "$" + numberinstring;
		}
		else {
			result = "-"; //if its 0 or less than 0 (which is an error) a hyphen is shown

		}
		return result;
		}

}
