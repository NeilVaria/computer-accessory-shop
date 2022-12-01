package cas;

import java.text.DecimalFormat;

public class Keyboard extends Stock{	
	private String keyboard_layout;
	private final DecimalFormat df  = new DecimalFormat("0.00");

	public Keyboard(String barcode, String device_name, String device_type, String brand, String colour,
			String connectivity, Integer quantity_in_stock, Float original_cost, Float retail_price,
			String keyboard_layout) {
		super(barcode, device_name, device_type, brand, colour, connectivity, quantity_in_stock, original_cost,
				retail_price);
		this.keyboard_layout = keyboard_layout;
	}
	

	public String getKeyboard_layout() {
		return keyboard_layout;
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
				getKeyboard_layout()};
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
				getKeyboard_layout()};
		return object;
	}

}
