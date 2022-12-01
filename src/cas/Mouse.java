package cas;

import java.text.DecimalFormat;

public class Mouse extends Stock {	
	private String number_of_buttons;
	private final DecimalFormat df  = new DecimalFormat("0.00");
	
	
	public Mouse(String barcode, String device_name, String device_type, String brand, String colour,
			String connectivity, Integer quantity_in_stock, Float original_cost, Float retail_price,
			String number_of_buttons) {
		super(barcode, device_name, device_type, brand, colour, connectivity, quantity_in_stock, original_cost,
				retail_price);
		this.number_of_buttons = number_of_buttons;
	}


	public String getNumber_of_buttons() {
		return number_of_buttons;
	}

	public Object[] convertToObject() {
		Object[] object = {getBarcode(),
				getDevice_name(),
				getDevice_type(),
				getBrand(),
				getColour(),
				getConnectivity(),
				getQuantity_in_stock(),
				df.format(getOriginal_cost()),
				df.format(getRetail_price()),
				getNumber_of_buttons()};
		return object;
	}
	
	public Object[] convertToCustomerObject() {
		Object[] object = {getBarcode(),
				getDevice_name(),
				getDevice_type(),
				getBrand(),
				getColour(),
				getConnectivity(),
				getQuantity_in_stock(),
				df.format(getRetail_price()),
				getNumber_of_buttons()};
		return object;

}
}
