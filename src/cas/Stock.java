package cas;

import java.util.List;

public class Stock {
	private String barcode;
	private String device_name;
	private String device_type;
	private String brand;
	private String colour;
	private String connectivity;
	private Integer quantity_in_stock;
	private Float original_cost;
	private Float retail_price;
	
	public Boolean ItemsDifferent(Stock stock) {
		if (!barcode.equals(stock.getBarcode())) {
			return false;
		}
		if (!device_type.equals(stock.getDevice_type().toLowerCase())) {
			return false;
		}
		if (!brand.equals(stock.getBrand())) {
			return false;
		}
		if (!colour.equals(stock.getColour())) {
			return false;
		}
		if (!connectivity.equals(stock.getConnectivity())) {
			return false;
		}
		if (!original_cost.equals(stock.getOriginal_cost())) {
			return false;
		}
		if (!retail_price.equals(stock.getRetail_price())) {
			return false;}
		if ((device_name.equals("mouse"))) {
			if (!(((Mouse) this).getNumber_of_buttons().equals(((Mouse) stock).getNumber_of_buttons()))) {
				return false;}
		}
		if ((device_name.equals("keyboard"))) {
			if (!(((Keyboard) this).getKeyboard_layout().equals(((Keyboard) stock).getKeyboard_layout()))) {
				return false;}
		}
		return true;
}
	
	
	public Stock(String barcode, String device_name, String device_type, String brand, String colour,
			String connectivity, Integer quantity_in_stock, Float original_cost, Float retail_price) {
		super();
		this.barcode = barcode;
		this.device_name = device_name;
		this.device_type = device_type;
		this.brand = brand;
		this.colour = colour;
		this.connectivity = connectivity;
		this.quantity_in_stock = quantity_in_stock;
		this.original_cost = original_cost;
		this.retail_price = retail_price;
	}
	public String getDevice_name() {
		return device_name;
	}
	public String getDevice_type() {
		return device_type;
	}
	public String getBarcode() {
		return barcode;
	}
	public String getBrand() {
		return brand;
	}
	public String getColour() {
		return colour;
	}
	public String getConnectivity() {
		return connectivity;
	}
	public Integer getQuantity_in_stock() {
		return quantity_in_stock;
	}
	public Float getOriginal_cost() {
		return original_cost;
	}
	public Float getRetail_price() {
		return retail_price;
	}
	public void setQuantity_in_stock(Integer quantity_in_stock) {
		this.quantity_in_stock = quantity_in_stock;
	}
	
}
