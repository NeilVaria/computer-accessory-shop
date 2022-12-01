package cas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
	List<Stock> shoppingBasket;
	
	public void clearShoppingBasket() {
		shoppingBasket.clear();
		return;
	}
	
	public void addItemToShoppingBasket(Stock item, Integer quantity) {
		for (Stock stock: shoppingBasket) {
			if(item.getBarcode().equals(stock.getBarcode())) {
				stock.setQuantity_in_stock(item.getQuantity_in_stock()+quantity);
				return;
			}
		}
		
		item.setQuantity_in_stock(quantity);
		shoppingBasket.add(item);
	}
	
	public List<Stock> GetAvailableStockInfo(){
		List<Stock> stock = Database.GetStockInfo();
		for(Stock a : shoppingBasket) {
			for(Stock b : stock) {
				if(a.getBarcode().equals(b.getBarcode())) {
					b.setQuantity_in_stock(b.getQuantity_in_stock()-a.getQuantity_in_stock());
				}
			}
		}
		List<Stock> stockAvailable = new ArrayList<Stock>();
		for(Stock s: stock) {
			if(s.getQuantity_in_stock() !=0) {
				stockAvailable.add(s);
			}
		}
		return stockAvailable;
	}
	
	public double GetTotalCost(){
		double total = 0.0;
		for(Stock stock : shoppingBasket) {
			total += (stock.getRetail_price()*stock.getQuantity_in_stock());
		}
		return total;
	}
	
	public List<Stock> getShoppingBasket(){
		return shoppingBasket;
	}
	
	public Customer(String userID, String username, String name, String userType, String house_number, String postcode,
			String city) {
		super(userID, username, name, userType, house_number, postcode, city);
		// TODO Auto-generated constructor stub
		this.shoppingBasket = new ArrayList<Stock>();
	}
	
}
