package cas;
import java.io.File;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class Login extends JFrame {

	private JPanel contentPane;
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel loginLabel = new JLabel("Login");
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setBounds(402, 11, 96, 93);
		loginLabel.setFont(new Font("Calibri", Font.PLAIN, 44));
		contentPane.add(loginLabel);
		
		List<User> users = Database.GetListOfUsers(); //Creates list of users, runs method to get data
		String[] arrayOfNames = new String[users.size()]; //Initialising a string array the same size as the string of users
		for (int i =0; i<arrayOfNames.length; i++) {
			arrayOfNames[i] = users.get(i).getUsername();
		}
		JComboBox loginComboBox = new JComboBox(arrayOfNames);
		loginComboBox.setBounds(235, 156, 430, 46);
		contentPane.add(loginComboBox);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<User> users = Database.GetListOfUsers();
				Integer index = loginComboBox.getSelectedIndex();
				User user = users.get(index);
				String userType = users.get(index).getUserType();
				if (userType.equals("customer")) {
					CustomerMenu screen = new CustomerMenu((Customer)user);
					screen.setVisible(true);
					dispose();
				}else {
					AdminMenu screen = new AdminMenu((Admin)user);
					screen.setVisible(true);
					dispose();
				}
			}
		});
		loginButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		loginButton.setBounds(385, 266, 130, 46);
		contentPane.add(loginButton);

		
		
		
	}
}
