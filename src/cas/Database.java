package cas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

interface Database {
	public static List<User> GetListOfUsers(){
		List<User> UserList = new ArrayList<User>();
		try(BufferedReader br = new BufferedReader(new FileReader("Resources/UserAccounts.txt"))) {
		    String line;

		    while ((line = br.readLine()) != null) {
		        String[] parts = line.split(", ");
		        if(parts[6].equals("customer")) {
		        	Customer customer = new Customer(parts[0], parts[1], parts[2], parts[6], parts[3], parts[4], parts[5]);
		        	UserList.add(customer);
		        }else {
		        	Admin admin = new Admin(parts[0], parts[1], parts[2], parts[6], parts[3], parts[4], parts[5]);
		        	UserList.add(admin);
		        }
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UserList;
	}
	
	public static void WriteStockToFile(List<Stock> StockList){
		try {
			FileWriter file = new FileWriter("Resources/Stock.txt");
			BufferedWriter buffer = new BufferedWriter(file);
			String newLines = "";
			for (Stock stock : StockList) {
				String newLine = "";
				newLine += stock.getBarcode();
				newLine += ", " + stock.getDevice_name();
				newLine += ", " + stock.getDevice_type();
				newLine += ", " + stock.getBrand();
				newLine += ", " + stock.getColour();
				newLine += ", " + stock.getConnectivity();
				newLine += ", " + stock.getQuantity_in_stock();
				newLine += ", " + stock.getOriginal_cost();
				newLine += ", " + stock.getRetail_price();
				if (stock.getDevice_name().equals("mouse")) {
					newLine += ", " + ((Mouse) stock).getNumber_of_buttons();
				}else {
					newLine += ", " +((Keyboard) stock).getKeyboard_layout();
				}
				newLines += newLine + "\n";
			}
			buffer.write(newLines);
			buffer.close();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void removeBasketItemsFromFile(List<Stock> basket) {
		List<Stock> stock = GetStockInfo();
		for(int i = 0; i < basket.size(); i++) {
			for(int j = 0; j < stock.size(); j++){
				if(basket.get(i).getBarcode().equals(stock.get(j).getBarcode())) {
					stock.get(j).setQuantity_in_stock(stock.get(j).getQuantity_in_stock() - basket.get(i).getQuantity_in_stock());
					
				}
							}
		}
		WriteStockToFile(stock);
	}
	
	public static List<Stock> GetStockInfo(){
		List<Stock> StockList = new ArrayList<Stock>();
		try(BufferedReader br = new BufferedReader(new FileReader("Resources/Stock.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] parts = line.split(", ");
		    	if(parts[1].equals("mouse")) {
		    		Mouse mouse = new Mouse(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], Integer.parseInt(parts[6].trim()), Float.parseFloat(parts[7].trim()), Float.parseFloat(parts[8].trim()), parts[9]);
		    		StockList.add(mouse);
		    	}else {
		    		Keyboard keyboard = new Keyboard(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], Integer.parseInt(parts[6].trim()), Float.parseFloat(parts[7].trim()), Float.parseFloat(parts[8].trim()), parts[9]);
		    		StockList.add(keyboard);
		    	}
		    }      
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
			boolean isInOrder = false;
			while(!isInOrder) {
				isInOrder = true;
				for(int j = 0; j < StockList.size()-1; j++) {
					if(StockList.get(j).getRetail_price() > StockList.get(j+1).getRetail_price()) {
						if(StockList.get(j).getDevice_name().equals("mouse")) {
							Mouse temp = (Mouse)StockList.get(j);
							StockList.set(j,StockList.get(j+1));
							StockList.set(j+1,temp);
						}else {
							Keyboard temp = (Keyboard)StockList.get(j);
							StockList.set(j,StockList.get(j+1));
							StockList.set(j+1,temp);
						}
						isInOrder = false;	
					}
				}
			}
			
		
		
		
		return StockList;
	}
	

}
