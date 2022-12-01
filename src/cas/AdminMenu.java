package cas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class AdminMenu extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField barcodeInput;
	private JTextField brandInput;
	private JTextField colourInput;
	private JTextField quantityInStockInput;
	private JTextField originalCostInput;
	private JTextField retailPriceInput;
	private JLabel retailPriceLabel;
	private JTextField numberOfButtonsInput;
	private static DefaultTableModel model;
	private final DecimalFormat df  = new DecimalFormat("0.00");
	

	public AdminMenu(Admin user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel adminMenuLabel = new JLabel("Admin Menu");
		adminMenuLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
		adminMenuLabel.setBounds(385, 10, 150, 30);
		contentPane.add(adminMenuLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 866, 233);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		model = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		Object[] column = {"Barcode", "Device Name", "Device Type", "Brand", "Colour", "Connectivity", "Quantity In Stock", "Original Cost", "Retail Price", "Layout/Buttons" };
		model.setColumnIdentifiers(column);
		table.setModel(model);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		List<Stock> StockList = Database.GetStockInfo(); //Creates list of stock, runs method to get data
		List<Object> productList = new ArrayList<Object>();
		String[] arrayOfStock = new String[StockList.size()]; //Initialising a string array the same size as the string of stock
		Admin.writeTable(model);
		
		
		JButton LogOutButton = new JButton("LogOut");
		LogOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login screen = new Login();
				screen.setVisible(true);
				dispose();
			}
		});
		LogOutButton.setBounds(794, 8, 82, 23);
		contentPane.add(LogOutButton);
		
		barcodeInput = new JTextField();
		barcodeInput.setBounds(95, 348, 96, 20);
		contentPane.add(barcodeInput);
		barcodeInput.setColumns(10);
		
		brandInput = new JTextField();
		brandInput.setColumns(10);
		brandInput.setBounds(95, 379, 96, 20);
		contentPane.add(brandInput);
		
		colourInput = new JTextField();
		colourInput.setColumns(10);
		colourInput.setBounds(95, 410, 96, 20);
		contentPane.add(colourInput);
		
		String[] connectivity = {"Wireless", "Wired"};
		JComboBox connectivityInput = new JComboBox(connectivity);
		connectivityInput.setBounds(95, 441, 96, 20);
		contentPane.add(connectivityInput);
		
		quantityInStockInput = new JTextField();
		quantityInStockInput.setColumns(10);
		quantityInStockInput.setBounds(332, 348, 96, 20);
		contentPane.add(quantityInStockInput);
		
		originalCostInput = new JTextField();
		originalCostInput.setColumns(10);
		originalCostInput.setBounds(332, 379, 96, 20);
		contentPane.add(originalCostInput);
		
		retailPriceInput = new JTextField();
		retailPriceInput.setColumns(10);
		retailPriceInput.setBounds(332, 410, 96, 20);
		contentPane.add(retailPriceInput);
		
		retailPriceLabel = new JLabel("Retail Price");
		retailPriceLabel.setLabelFor(retailPriceInput);
		retailPriceLabel.setBounds(246, 413, 76, 14);
		contentPane.add(retailPriceLabel);
		
		String[] devices = {"Mouse", "Keyboard"};
		JComboBox deviceInput = new JComboBox(devices);
		deviceInput.setBounds(332, 441, 96, 20);
		contentPane.add(deviceInput);
		
		String layouts[] = {"UK", "US"};
		JComboBox keyboardLayoutInput = new JComboBox(layouts);
		keyboardLayoutInput.setBounds(530, 402, 113, 20);
		contentPane.add(keyboardLayoutInput);
		keyboardLayoutInput.setEnabled(false);
		
		
		String keyboardTypes[] = {"Standard", "Flexible", "Gaming"};
		JComboBox keyboardTypeInput = new JComboBox(keyboardTypes);
		keyboardTypeInput.setBounds(530, 356, 113, 20);
		contentPane.add(keyboardTypeInput);
		keyboardTypeInput.setEnabled(false);
		
		String mouseTypes[] = {"Standard", "Gaming"};
		JComboBox mouseTypeInput = new JComboBox(mouseTypes);
		mouseTypeInput.setBounds(680, 356, 113, 20);
		contentPane.add(mouseTypeInput);
		
		numberOfButtonsInput = new JTextField();
		numberOfButtonsInput.setColumns(10);
		numberOfButtonsInput.setBounds(680, 402, 113, 20);
		contentPane.add(numberOfButtonsInput);
		
		deviceInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = deviceInput.getSelectedItem().toString();
				if (value.equals("Mouse")){
					keyboardLayoutInput.setEnabled(false);
					keyboardTypeInput.setEnabled(false);
					mouseTypeInput.setEnabled(true);
					numberOfButtonsInput.setEnabled(true);
				}
				if (value.equals("Keyboard")){
					keyboardLayoutInput.setEnabled(true);
					keyboardTypeInput.setEnabled(true);
					mouseTypeInput.setEnabled(false);
					numberOfButtonsInput.setEnabled(false);
				}
			}
		});
		
		
		
		
		
		JLabel barcodeLabel = new JLabel("Barcode");
		barcodeLabel.setLabelFor(barcodeInput);
		barcodeLabel.setBounds(33, 348, 64, 14);
		contentPane.add(barcodeLabel);
		
		JLabel brandLabel = new JLabel("Brand");
		brandLabel.setLabelFor(brandInput);
		brandLabel.setBounds(43, 379, 54, 14);
		contentPane.add(brandLabel);
		
		JLabel colourLabel = new JLabel("Colour");
		colourLabel.setLabelFor(colourInput);
		colourLabel.setBounds(40, 410, 57, 14);
		contentPane.add(colourLabel);
		
		JLabel connectivityLabel = new JLabel("Connectivity");
		connectivityLabel.setBounds(10, 441, 87, 14);
		contentPane.add(connectivityLabel);
		
		JLabel quantityInStockLabel = new JLabel("Quantity In Stock");
		quantityInStockLabel.setLabelFor(quantityInStockInput);
		quantityInStockLabel.setBounds(217, 350, 105, 17);
		contentPane.add(quantityInStockLabel);
		
		JLabel originalCostLabel = new JLabel("Original Cost\r\n");
		originalCostLabel.setLabelFor(originalCostInput);
		originalCostLabel.setBounds(239, 382, 83, 14);
		contentPane.add(originalCostLabel);
		
		JLabel deviceLabel = new JLabel("Device");
		deviceLabel.setLabelFor(deviceInput);
		deviceLabel.setBounds(269, 444, 53, 14);
		contentPane.add(deviceLabel);
		
		JLabel addNewStockLabel = new JLabel("Add New Stock");
		addNewStockLabel.setFont(new Font("Calibri Light", Font.PLAIN, 26));
		addNewStockLabel.setBounds(366, 305, 166, 30);
		contentPane.add(addNewStockLabel);
		
		JLabel keyboardLayoutLabel = new JLabel("Keyboard Layout");
		keyboardLayoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		keyboardLayoutLabel.setBounds(530, 379, 113, 23);
		contentPane.add(keyboardLayoutLabel);
		
		JLabel numberOfButtonsLabel = new JLabel("Number Of Buttons");
		numberOfButtonsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numberOfButtonsLabel.setBounds(680, 379, 113, 23);
		contentPane.add(numberOfButtonsLabel);
		
		JLabel keyboardTypeLabel = new JLabel("Keyboard Type");
		keyboardTypeLabel.setLabelFor(keyboardTypeInput);
		keyboardTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		keyboardTypeLabel.setBounds(530, 334, 113, 23);
		contentPane.add(keyboardTypeLabel);
		
		JLabel mouseTypeLabel = new JLabel("Mouse Type");
		mouseTypeLabel.setLabelFor(mouseTypeInput);
		mouseTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mouseTypeLabel.setBounds(680, 334, 113, 23);
		contentPane.add(mouseTypeLabel);
		
		JButton addStockButton = new JButton("Add Stock");
		addStockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Integer barcodechk = Integer.parseInt(barcodeInput.getText());
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Barcode must be an Integer", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Integer barcodeNumDigits = String.valueOf(barcodeInput.getText()).length();
				if (!(barcodeNumDigits == 6)) {
					JOptionPane.showMessageDialog(null, "Barcode must be 6 digits long", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String barcode = barcodeInput.getText().toString();
				String deviceType = deviceInput.getSelectedItem().toString().toLowerCase();
				String mouseType = mouseTypeInput.getSelectedItem().toString();
				String keyboardType = keyboardTypeInput.getSelectedItem().toString();
				if(!(brandInput.getText().toString().matches("[a-zA-Z]+"))) {
					JOptionPane.showMessageDialog(null, "Brand must only contain letters", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String brand = brandInput.getText().toString();
				if(!(colourInput.getText().toString().matches("[a-zA-Z]+"))) {
					JOptionPane.showMessageDialog(null, "Colour must only contain letters", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String colour = colourInput.getText().toString().toLowerCase();
				String connectivity = connectivityInput.getSelectedItem().toString().toLowerCase();
				String quantityInStock = quantityInStockInput.getText().toString();
				try{
					Integer quantityInStockchk = Integer.parseInt(quantityInStockInput.getText());
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Stock quantity must be an Integer", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try{
					String originalCostChkFormat = df.format(Double.valueOf((originalCostInput.getText().toString())));
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Original Cost must be in the format 0.00", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String originalCost = df.format(Double.valueOf(originalCostInput.getText().toString()));
				try{
					Float originalCostchk = Float.parseFloat(originalCostInput.getText());
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Original Cost must be a number", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try{
					String retailPriceChkFormat = df.format(Double.valueOf(retailPriceInput.getText().toString()));
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Retail Price must be in the format 0.00", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String retailPrice = df.format(Double.valueOf(retailPriceInput.getText().toString()));
				try{
					Float retailPricechk = Float.parseFloat(retailPriceInput.getText());
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Retail price must be a number", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String keyboardLayout = keyboardLayoutInput.getSelectedItem().toString();
				String numberOfButtons = numberOfButtonsInput.getText().toString();
				
				try{
					Integer numberOfButtonschk = Integer.parseInt(barcodeInput.getText());
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Button number must be an Integer", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (deviceType.equals("mouse")){
					String[] chkAllFieldsFilled = {barcode, deviceType, mouseType, brand, colour, connectivity, quantityInStock, originalCost, retailPrice, numberOfButtons};
					for (String s: chkAllFieldsFilled) {
						if(s.equals("")) {
							JOptionPane.showMessageDialog(null, "All fields must be filled", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}else {
				String[] chkAllFieldsFilled = {barcode, deviceType, keyboardType, brand, colour, connectivity, quantityInStock, originalCost, retailPrice, keyboardLayout};
				for (String s: chkAllFieldsFilled) {
					if(s.equals("")) {
						JOptionPane.showMessageDialog(null, "All fields must be filled", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				}
				if (deviceType.equals("mouse")){
				    Mouse item = new Mouse(barcode, deviceType, mouseType, brand, colour, connectivity, Integer.parseInt(quantityInStock), Float.parseFloat(originalCost), Float.parseFloat(retailPrice), numberOfButtons);
					for (int i = 0; i<StockList.size(); i++) {
						}
				    Admin.submitData(StockList, item, model);
				}
				if (deviceType.equals("keyboard")){
				    Keyboard item = new Keyboard(barcode, deviceType, mouseType, brand, colour, connectivity, Integer.parseInt(quantityInStock), Float.parseFloat(originalCost), Float.parseFloat(retailPrice), keyboardLayout);
					for (int i = 0; i<StockList.size(); i++) {
						}
				    Admin.submitData(StockList, item, model);
				}
			}
		});
		addStockButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		addStockButton.setBounds(617, 440, 89, 23);
		contentPane.add(addStockButton);
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 295, 864, 11);
		contentPane.add(separator);
		
		JLabel welcomeUserLabel = new JLabel("Welcome " + user.getName());
		welcomeUserLabel.setBounds(20, 10, 113, 22);
		contentPane.add(welcomeUserLabel);
		

	}
}
