package cas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class CustomerMenu extends JFrame {

	private JPanel contentPanel;
	private JTextField paypalAddressInput;
	private JTextField cardNumberInput;
	private JTextField securityCodeInput;
	private static DefaultTableModel model;
	private static DefaultTableModel model2;
	private Customer user;
	private JTable table;
	private JTable stockTable;
	private JTable basketTable;
	private JTextField searchInput;
	private final DecimalFormat df  = new DecimalFormat("0.00");
	
	void writeStockTable() {
		List<Stock> StockList = user.GetAvailableStockInfo(); //Creates list of stock, runs method to get data
		model.setRowCount(0);
		String[] arrayOfStock = new String[StockList.size()]; //Initialising a string array the same size as the string of stock
		for (int i = 0; i<StockList.size(); i++) {
			if (StockList.get(i).getDevice_name().equals("mouse")) {
				Object[] row = ((Mouse)StockList.get(i)).convertToCustomerObject();
				model.addRow(row);
			}else {
				Object[] row = ((Keyboard)StockList.get(i)).convertToCustomerObject();
				model.addRow(row);
			}
		}
	}
	
	private void filter(String query) {
		TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(model);
		stockTable.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(query));
	}
	
	static void writeBasketTable(List<Stock> basketList) {
		model2.setRowCount(0);
		String[] arrayOfStock = new String[basketList.size()]; //Initialising a string array the same size as the string of stock
		for (int i = 0; i<basketList.size(); i++) {
			if (basketList.get(i).getDevice_name().equals("mouse")) {
				Object[] row = ((Mouse)basketList.get(i)).convertToCustomerObject();
				model2.addRow(row);
			}else {
				Object[] row = ((Keyboard)basketList.get(i)).convertToCustomerObject();
				model2.addRow(row);
			}
		}
	}
	
	
	
	public CustomerMenu(Customer user) {
		this.user = user;
		List<Stock> StockList = Database.GetStockInfo(); //Creates list of stock, runs method to get data
		List<Stock> basketList = new ArrayList<Stock>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 688);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel customerMenuLabel = new JLabel("Customer Menu");
		customerMenuLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
		customerMenuLabel.setBounds(507, 11, 186, 35);
		contentPanel.add(customerMenuLabel);
		
		JLabel totalCostLabel = new JLabel("Total Cost: \u00A3");
		totalCostLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totalCostLabel.setBounds(637, 400, 239, 25);
		contentPanel.add(totalCostLabel);
		
		JLabel basketLabel = new JLabel("Basket\r\n");
		basketLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		basketLabel.setBounds(379, 400, 70, 27);
		contentPanel.add(basketLabel);
		
		JLabel stockLabel = new JLabel("Stock");
		stockLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		stockLabel.setBounds(392, 56, 57, 27);
		contentPanel.add(stockLabel);
		
		JButton addToBasketButton = new JButton("Add To Basket");
		addToBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputQuantity = JOptionPane.showInputDialog("Enter Quantity to add to Basket");
				try{
					Integer quantity = Integer.parseInt(inputQuantity);
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Quantity must be an Integer", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				} 
				
				Integer quantity = Integer.parseInt(inputQuantity);
				Integer index = stockTable.getSelectedRow();
				if((StockList.get(index).getQuantity_in_stock()-quantity)<0) {
					JOptionPane.showMessageDialog(null, "Not enough Items In Stock", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Stock stock = StockList.get(index);
				user.addItemToShoppingBasket(stock, quantity);
				totalCostLabel.setText("Total Cost: \u00A3" + df.format(user.GetTotalCost()));
				writeStockTable();
				writeBasketTable(user.getShoppingBasket());
				
			}
		});
		addToBasketButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addToBasketButton.setBounds(10, 581, 239, 62);
		contentPanel.add(addToBasketButton);
		
		JButton clearBasketButton = new JButton("Clear Basket");
		clearBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.clearShoppingBasket();
				writeStockTable();
				writeBasketTable(user.getShoppingBasket());
				totalCostLabel.setText("Total Cost: \u00A3");
			}
		});
		clearBasketButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		clearBasketButton.setBounds(637, 581, 239, 62);
		contentPanel.add(clearBasketButton);
		
		JLabel paymentTypeLabel = new JLabel("Payment Type");
		paymentTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		paymentTypeLabel.setBounds(968, 38, 145, 35);
		contentPanel.add(paymentTypeLabel);
		
		String[] paymentTypeList = {"PayPal", "Credit Card"};
		JComboBox paymentTypeComboBox = new JComboBox(paymentTypeList);
		paymentTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((paymentTypeComboBox.getSelectedItem().toString().toLowerCase()).equals("credit card")) {
					paypalAddressInput.setEnabled(false);
					cardNumberInput.setEnabled(true);
					securityCodeInput.setEnabled(true);
				}else {
					paypalAddressInput.setEnabled(true);
					cardNumberInput.setEnabled(false);
					securityCodeInput.setEnabled(false);
				}
			}
		});
		paymentTypeLabel.setLabelFor(paymentTypeComboBox);
		paymentTypeComboBox.setBounds(945, 92, 198, 22);
		contentPanel.add(paymentTypeComboBox);
		
		JLabel paypalLabel = new JLabel("PayPal");
		paypalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		paypalLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		paypalLabel.setBounds(968, 143, 145, 35);
		contentPanel.add(paypalLabel);
		
		JLabel creditCardLabel = new JLabel("Credit Card");
		creditCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
		creditCardLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		creditCardLabel.setBounds(968, 280, 145, 35);
		contentPanel.add(creditCardLabel);
		
		paypalAddressInput = new JTextField();
		paypalAddressInput.setBounds(945, 214, 198, 20);
		contentPanel.add(paypalAddressInput);
		paypalAddressInput.setColumns(10);
		
		cardNumberInput = new JTextField();
		cardNumberInput.setColumns(10);
		cardNumberInput.setBounds(945, 360, 125, 20);
		contentPanel.add(cardNumberInput);
		cardNumberInput.setEnabled(false);
		
		securityCodeInput = new JTextField();
		securityCodeInput.setColumns(10);
		securityCodeInput.setBounds(945, 430, 70, 20);
		contentPanel.add(securityCodeInput);
		securityCodeInput.setEnabled(false);
		
		JLabel cardNumberLabel = new JLabel("Card Number:");
		cardNumberLabel.setLabelFor(cardNumberInput);
		cardNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cardNumberLabel.setBounds(945, 335, 90, 14);
		contentPanel.add(cardNumberLabel);
		
		JLabel securityCodeLabel = new JLabel("Security Code:");
		securityCodeLabel.setLabelFor(securityCodeInput);
		securityCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		securityCodeLabel.setBounds(945, 409, 106, 14);
		contentPanel.add(securityCodeLabel);
		
		JLabel emailAddressLabel = new JLabel("Email Address:");
		emailAddressLabel.setLabelFor(paypalAddressInput);
		emailAddressLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailAddressLabel.setBounds(945, 189, 106, 14);
		contentPanel.add(emailAddressLabel);
		
		JButton btnNewButton = new JButton("Checkout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String paymentType = paymentTypeComboBox.getSelectedItem().toString().toLowerCase();
				if (paymentType.equals("paypal")){
					if(!(paypalAddressInput.getText().toString().matches("^(.+)@(.+)$"))) {
						JOptionPane.showMessageDialog(null, "Email Format Incorrect", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
			         JOptionPane.showMessageDialog(null,"£ " + df.format(user.GetTotalCost())+ " Paid Using PayPal, and the delivery address is " + user.getAddress().getHouse_number() + " " + user.getAddress().getPostcode() + " " + user.getAddress().getCity(), "PopUp Dialog", JOptionPane.INFORMATION_MESSAGE);
				}else {
					try{
						Integer cardNumberChk = Integer.parseInt(cardNumberInput.getText());
					}catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Card Number must be an Integer", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					Integer CardNumberNumDigits = String.valueOf(cardNumberInput.getText()).length();
					if (!(CardNumberNumDigits == 6)) {
						JOptionPane.showMessageDialog(null, "Card Number must be 6 digits long", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					try{
						Integer securitycodeChk = Integer.parseInt(securityCodeInput.getText());
					}catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Security Code must be an Integer", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					Integer securityCodeNumDigits = String.valueOf(securityCodeInput.getText()).length();
					if (!(securityCodeNumDigits == 3)) {
						JOptionPane.showMessageDialog(null, "Card Number must be 3 digits long", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showMessageDialog(null,"£ " + df.format(user.GetTotalCost())+ " Paid Using Credit Card, and the delivery address is "  + user.getAddress().getHouse_number() + " " + user.getAddress().getPostcode() + " " + user.getAddress().getCity(), "PopUp Dialog", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
				
				
				Database.removeBasketItemsFromFile(user.getShoppingBasket());
				user.clearShoppingBasket();
				writeBasketTable(user.getShoppingBasket());
				totalCostLabel.setText("Total Cost: \u00A3");
				writeStockTable();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(968, 530, 144, 48);
		contentPanel.add(btnNewButton);
		
		JButton logoutButton = new JButton("LogOut");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login screen = new Login();
				screen.setVisible(true);
				dispose();
			}
		});
		logoutButton.setBounds(1087, 4, 89, 23);
		contentPanel.add(logoutButton);
		
		JLabel welcomeUserLabel = new JLabel("Welcome " + user.getName());
		welcomeUserLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		welcomeUserLabel.setBounds(10, 21, 125, 14);
		contentPanel.add(welcomeUserLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 92, 866, 283);
		contentPanel.add(scrollPane);
		

		
		stockTable = new JTable();
		scrollPane.setViewportView(stockTable);
		model = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		stockTable.setModel(model);
		stockTable.getTableHeader().setReorderingAllowed(false);
		stockTable.getTableHeader().setResizingAllowed(false);
		
		Object[] column = {"Barcode", "Device Name", "Device Type", "Brand", "Colour", "Connectivity", "Quantity In Stock",  "Retail Price", "Layout/Buttons" };
		model.setColumnIdentifiers(column);

		
		writeStockTable();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 434, 866, 118);
		contentPanel.add(scrollPane_1);
		model2 = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		basketTable = new JTable();
		Object[] column2 = {"Barcode", "Device Name", "Device Type", "Brand", "Colour", "Connectivity", "Quantity In Basket",  "Retail Price", "Layout/Buttons" };
		model2.setColumnIdentifiers(column2);
		scrollPane_1.setViewportView(basketTable);
		basketTable.setModel(model2);
		
		searchInput = new JTextField();
		searchInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String query=searchInput.getText().toLowerCase();
				filter(query);
			}
		});
		searchInput.setBounds(103, 64, 212, 20);
		contentPanel.add(searchInput);
		searchInput.setColumns(10);
		
		JLabel searchLabel = new JLabel("Search:");
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		searchLabel.setBounds(40, 63, 57, 20);
		contentPanel.add(searchLabel);
		

		basketTable.getTableHeader().setReorderingAllowed(false);
		basketTable.getTableHeader().setResizingAllowed(false);
	}


}
