package cas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Admin extends User{
	
	static void submitData(List<Stock> StockList, Stock item, DefaultTableModel model) {
		for(Stock stockItem : StockList) {
			if(stockItem.getBarcode().equals(item.getBarcode())) {
				if (stockItem.ItemsDifferent(item)==false) {
					JOptionPane.showMessageDialog(null, "Barcode matches existing item, but inputs do not match attributes of that item", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				stockItem.setQuantity_in_stock(stockItem.getQuantity_in_stock() + item.getQuantity_in_stock());
				Database.WriteStockToFile(StockList);
				writeTable(model);
				return;
			}
		}
		
		StockList.add(item);
	    Database.WriteStockToFile(StockList);
	    writeTable(model);
		}
	
	static void writeTable(DefaultTableModel model) {
		List<Stock> StockList = Database.GetStockInfo(); //Creates list of stock, runs method to get data
		List<Object> productList = new ArrayList<Object>();
		model.setRowCount(0);
		String[] arrayOfStock = new String[StockList.size()]; //Initialising a string array the same size as the string of stock
		for (int i = 0; i<StockList.size(); i++) {
			if (StockList.get(i).getDevice_name().equals("mouse")) {
				Object[] row = ((Mouse)StockList.get(i)).convertToObject();
				model.addRow(row);
				productList.add(row);
			}else {
				Object[] row = ((Keyboard)StockList.get(i)).convertToObject();
				model.addRow(row);
				productList.add(row);
			}
		}
	}

	public Admin(String userID, String username, String name, String userType, String house_number, String postcode,
			String city) {
		super(userID, username, name, userType, house_number, postcode, city);
		// TODO Auto-generated constructor stub
	}


}
