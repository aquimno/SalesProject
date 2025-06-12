//in this Java file, the only methods you need to complete are the following:
	//RegisterNewUser()
	//LoginWithCreds()
	//GetAllProducts()
	//GetSalesTotal()
	//SubmitOrder()
	//SubmitNewProduct()

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
public class FinalProjectGUI extends JFrame {
	
	//global variables used by many methods
	GridBagConstraints gbc;
	CustomActionListener cal;
	ComboBoxActionListener cbal;
	String connection = "jdbc:mysql://localhost:3306/finalguiproject?";
	String user = "root";
	String pass = "Qwerty!12345";
	
	//constructor
	public FinalProjectGUI() {

		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		cal = new CustomActionListener();
		cbal = new ComboBoxActionListener();
		DisplaySplashScreen();
	}//end of constructor
	
	//this method removes all of the controls from the content pane;
	//this should not be called except by the pre-defined method!!
	private void ClearScreen()
	{
		this.getContentPane().removeAll();		
	}//end of ClearScreen method
	
	
	
	
	
	
	//this screen is the first screen the user sees!
	JLabel welcomeMessage;
	JButton registerButton;
	JButton loginButton;
	private void DisplaySplashScreen()
	{
		ClearScreen();
		
		welcomeMessage = new JLabel("Welcome to the Shoe Store application!  What function do you want to do?");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		this.add(welcomeMessage, gbc);
		
		registerButton = new JButton("Register New User");
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		registerButton.addActionListener(cal);
		registerButton.setName("register");
		this.add(registerButton, gbc);
		
		loginButton = new JButton("Login with Existing Credentials");
		gbc.gridx = 1;
		loginButton.addActionListener(cal);
		loginButton.setName("login");
		this.add(loginButton, gbc);
		
		this.validate();
		this.pack();
		this.repaint();
	}//end of DisplaySplashScreen method
	
	
	
	
	//this screen lets a user sign up for an account!
	JLabel registerMessage;
	JLabel usernameLabel;
	JLabel passwordLabel;
	JTextField usernameField;
	JTextField passwordField;
	JButton submitButton;
	JTextArea errorMessageArea;
	private void DisplayRegisterScreen()
	{
		ClearScreen();
		
		registerMessage = new JLabel("Please enter a new username and password below.");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(registerMessage, gbc);
		
		usernameLabel = new JLabel("User Name:");
		gbc.gridy++;
		add(usernameLabel, gbc);
		
		usernameField = new JTextField(20);
		gbc.gridy++;
		add(usernameField, gbc);
		
		passwordLabel = new JLabel("Password:");
		gbc.gridy++;
		add(passwordLabel, gbc);
		
		passwordField = new JTextField(20);
		gbc.gridy++;
		add(passwordField, gbc);
		
		submitButton = new JButton("Submit");
		submitButton.setName("newUserRegister");
		submitButton.addActionListener(cal);
		gbc.gridy++;
		add(submitButton, gbc);
		
		errorMessageArea = new JTextArea(10,50);
		gbc.gridy++;
		add(errorMessageArea, gbc);
		
		//all 3 of these are necessary to display the new form!
		this.validate();
		this.pack();
		this.repaint();
	} //end of DisplayRegisterScreen method
	
	
	
	
	
	
	
	
	
	
	
	//this method re-uses some of the controls from the Register User screen.
	//this lets any user signin to their account
	/*
	JLabel usernameLabel;
	JLabel passwordLabel;
	JTextField usernameField;
	JTextField passwordField;
	JButton submitButton;
	JTextArea errorMessageArea;
	*/
	JLabel loginLabel;
	private void DisplayLoginScreen()
	{
		ClearScreen();
		
		loginLabel = new JLabel("Please enter your credentials below.");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(loginLabel, gbc);
		
		usernameLabel = new JLabel("User Name:");
		gbc.gridy++;
		add(usernameLabel, gbc);
		
		usernameField = new JTextField(20);
		gbc.gridy++;
		add(usernameField, gbc);
		
		passwordLabel = new JLabel("Password:");
		gbc.gridy++;
		add(passwordLabel, gbc);
		
		passwordField = new JTextField(20);
		gbc.gridy++;
		add(passwordField, gbc);
		
		submitButton = new JButton("Submit");
		submitButton.setName("loginWithCreds");
		submitButton.addActionListener(cal);
		gbc.gridy++;
		add(submitButton, gbc);
		
		errorMessageArea = new JTextArea(10,50);
		gbc.gridy++;
		add(errorMessageArea, gbc);
		
		this.validate();
		this.pack();
		this.repaint();
	} //end of DisplayLoginScreen method
	
	
	
	
	
	
	
	
	
	
	
	//this screen is shown once a user successfully logs in.
	//if the user has the Role of admin (denoted by the value 1 in the table "Users"),
	//then they can see buttons to manage inventory and view a sales report.
	//otherwise, they can see a button to place an order.
	//finally, all users will be able to see a Logout button.
	JLabel selectFormLabel;
	JButton orderFormButton;
	JButton inventoryFormButton;
	JButton salesReportButton;
	JButton logoutButton;
	private void DisplayFormSelectScreen()
	{
		ClearScreen();
		
		selectFormLabel = new JLabel("Welcome, " + loggedInUserName + ", please select a form below.");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(selectFormLabel, gbc);
		
		if(loggedInUserRole == 2)
		{
			orderFormButton = new JButton("Place an Order");
			orderFormButton.setName("placeOrder");
			orderFormButton.addActionListener(cal);
			gbc.gridy++;
			add(orderFormButton, gbc);
		}
		else if(loggedInUserRole == 1)
		{
			inventoryFormButton = new JButton("Manage Inventory");
			inventoryFormButton.setName("inventory");
			inventoryFormButton.addActionListener(cal);
			gbc.gridy++;
			add(inventoryFormButton, gbc);
			
			salesReportButton = new JButton("See Sales Report");
			salesReportButton.setName("salesReport");
			salesReportButton.addActionListener(cal);
			gbc.gridy++;
			add(salesReportButton, gbc);
		}
		
		logoutButton = new JButton("Log Out");
		logoutButton.setName("logout");
		logoutButton.addActionListener(cal);
		gbc.gridy++;
		add(logoutButton, gbc);
		
		this.validate();
		this.pack();
		this.repaint();
	} //end of DisplayFormSelectScreen method
	
	
	
	
	
	
	
	
	
	
	
	
	
	//this screen allows a non-admin user (i.e. has a Role of "2" in the Users table)
	//to order a new item.
	//This screen's comboBox will be populated with data from the database!
	JLabel orderLabel;
	JLabel priceLabel;
	JComboBox productListComboBox;
	JTextField productQtyField;
	JButton placeOrderButton;
	JButton cancelButton;
	private void DisplayOrderScreen()
	{
		productNames = new ArrayList<String>();
		productPrices = new ArrayList<Double>();
		productIDs = new ArrayList<Integer>();
		
		GetAllProducts();
		
		ClearScreen();
		
		orderLabel = new JLabel("Please select the item you want to order and specify the quantity.");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(orderLabel, gbc);
		
		productListComboBox = new JComboBox(productNames.toArray());
		gbc.gridy++;
		productListComboBox.addActionListener(cbal);
		add(productListComboBox, gbc);
		
		//this might throw an error if there are no items in the arraylist.
		//that means you need to complete the "GetAllProducts()" method first :)
		priceLabel = new JLabel("Price: " + productPrices.get(0).toString());
		gbc.gridy++;
		add(priceLabel, gbc);
		
		productQtyField = new JTextField(5);
		gbc.gridy++;
		add(productQtyField, gbc);
		
		placeOrderButton = new JButton("Place Order");
		placeOrderButton.setName("submitOrder");
		placeOrderButton.addActionListener(cal);
		gbc.gridy++;
		add(placeOrderButton, gbc);
		
		cancelButton = new JButton("Go Back");
		cancelButton.setName("return");
		cancelButton.addActionListener(cal);
		gbc.gridy++;
		add(cancelButton, gbc);
		
		
		this.validate();
		this.pack();
		this.repaint();
	}//end of DisplayOrderScreen method
	
	
	
	
	
	
	
	
	
	
	
	//this screen allows an admin user (i.e. with role "1" in the Users table)
	//to add new products to the Product table
	JLabel productNameLabel;
	JTextField productNameField;
	JLabel productPriceLabel;
	JTextField productPriceField;
	//JButton submitButton;
	JButton returnButton;
	private void DisplayInventoryScreen()
	{
		ClearScreen();
		
		productNameLabel = new JLabel("Product Name:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(productNameLabel, gbc);
		
		productNameField = new JTextField(25);
		gbc.gridy++;
		add(productNameField, gbc);
		
		productPriceLabel = new JLabel("Price:");
		gbc.gridy++;
		add(productPriceLabel, gbc);
		
		productPriceField = new JTextField(10);
		gbc.gridy++;
		add(productPriceField, gbc);
		
		submitButton = new JButton("Add New Product");
		submitButton.setName("submitNewProduct");
		submitButton.addActionListener(cal);
		gbc.gridy++;
		add(submitButton, gbc);
		
		returnButton = new JButton("Go Back");
		returnButton.setName("return");
		returnButton.addActionListener(cal);
		gbc.gridy++;
		add(returnButton, gbc);
		
		this.validate();
		this.pack();
		this.repaint();
	}//end of DisplayInventoryScreen method
	
	
	
	
	
	
	
	
	
	
	
	
	
	//this screen allows an admin user (i.e. with role "1" in the Users table)
	//to see a simple report of all sales logged in the database
	JLabel totalSalesLabel;
	//JButton returnButton;
	private void DisplaySalesReportScreen()
	{
		ClearScreen();
		
		GetSalesTotal();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		totalSalesLabel = new JLabel("Total Sales: " + salesTotal);
		add(totalSalesLabel, gbc);
		
		returnButton = new JButton("Return");
		returnButton.setName("return");
		gbc.gridy++;
		returnButton.addActionListener(cal);
		add(returnButton, gbc);
		
		this.validate();
		this.pack();
		this.repaint();
	}//end of DisplaySalesReportScreen method
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	//custom action listener for when a combobox changes values.
	//useful for displaying the price of the selected item.
	class ComboBoxActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox theBox = (JComboBox)e.getSource();
			int selectedIndex = theBox.getSelectedIndex();
			
			priceLabel.setText("Price: " + productPrices.get(selectedIndex).toString());
		}
		
	}
	
	
	//custom action listener for all buttons in the program.  dang!
	class CustomActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton button = (JButton)e.getSource();
			String name = button.getName();
			
			//this button is in the splash screen
			if(name.equals("login"))
				DisplayLoginScreen();
			
			//this button is in the splash screen
			else if (name.equals("register"))
				DisplayRegisterScreen();
			
			//this button is in the Register New User screen
			else if (name.equals("newUserRegister"))
				RegisterNewUser();
			
			//this button is in the Login screen
			else if(name.equals("loginWithCreds"))
				LoginWithCreds();
			
			//this button is in the Form Select screen
			else if (name.equals("placeOrder"))
				DisplayOrderScreen();
			
			//this button is in the Form Select screen
			else if(name.equals("inventory"))
				DisplayInventoryScreen();
			
			//this button is in the Form Select screen
			else if(name.equals("salesReport"))
				DisplaySalesReportScreen();
			
			//this button is found on the Form Select screen
			else if(name.equals("logout"))
				DisplaySplashScreen();
			
			//this button is in the Order form for customers
			else if(name.equals("submitOrder"))
				SubmitOrder();
			
			//this button is found on both the Order form and the Inventory form
			else if(name.equals("return"))
				DisplayFormSelectScreen();
			
			//this button is found on the Inventory screen
			else if(name.equals("submitNewProduct"))
				SubmitNewProduct();
		}
		
	}
	

	/////////////////////////////////////////
	/////////////////////////////////////////
	//Begin completing the method below!//
	//Everything else, above, do not touch!//
	/////////////////////////////////////////
	/////////////////////////////////////////

	//this method calls a stored procedure to insert a new user into the "User" table
	//Any error from the database (e.g. duplicate username) should appear in the "errorMessageArea" JTextArea.
	//On a success, the method "DisplaySplashScreen()" should be called.
	private void RegisterNewUser()
	{
		//TODO: complete this method, which should communicate with the database
		
		try (Connection con = DriverManager.getConnection(connection, user, pass)){
			System.out.println("Successful Connection.");
			
			//collects user inputs to pass through the database
			String username = usernameField.getText();
			String password = passwordField.getText();
			int userRole = 2; //1 means admin, 2 means customer
			
			CallableStatement query = con.prepareCall("CALL RegisterNewUser(?, ?, ?);");
			query.setString(1, username);
			query.setString(2, password);
			query.setInt(3, userRole);
			
			query.executeQuery(); //executes query
			
			DisplaySplashScreen();
			JOptionPane.showMessageDialog(null, "Registration successful!");
		} catch (Exception e){
			e.printStackTrace();
			errorMessageArea.setText("Registration failed: " + e.getMessage());
		}
		
	}
	
	//this method calls a stored procedure to determine if there exists
	//a username / value pair in the database that matches the user's input.
	//If there is, the below variables should be populated, then call the DisplayFormSelectScreen() method.
	//If there is not, then the "errorMessageArea" (JTextArea) should display an 'error' message
	int loggedInUserID;	
	int loggedInUserRole;
	String loggedInUserName;
	private void LoginWithCreds()
	{
		//TODO: complete this method, which should communicate with the database
		try (Connection con = DriverManager.getConnection(connection, user, pass)) {
			String username = usernameField.getText();
			String password = passwordField.getText();
			
			CallableStatement query = con.prepareCall("CALL LoginWithCreds(?, ?);");
			query.setString(1, username);
			query.setString(2, password);
			
			ResultSet rs = query.executeQuery();
			
			if (rs.next()) {
	            // User found – populate variables
	            loggedInUserID = rs.getInt("id");
	            loggedInUserName = rs.getString("username");
	            loggedInUserRole = rs.getInt("userRole");

	            // Success – go to next screen
	            DisplayFormSelectScreen();
	        } else {
	            // No user found – show error
	            errorMessageArea.setText("Invalid username or password.");
	        }
		} catch (Exception e) {
			e.printStackTrace();
			errorMessageArea.setText("Login failed: " + e.getMessage());
		}
		
	}
	
	//this method calls a stored procedure to get a recordset of all products.
    //the below global variables should be used here, as other methods rely on their values.
	ArrayList<String> productNames;
	ArrayList<Double> productPrices;
	ArrayList<Integer> productIDs;
	private void GetAllProducts()
	{
		//TODO: complete this method, which should communicate with the database
		try (Connection con = DriverManager.getConnection(connection, user, pass)) {
			CallableStatement query = con.prepareCall("CALL GetAllProducts();");
			
			ResultSet rs = query.executeQuery();
			
			while(rs.next()) {
				productNames.add(rs.getString("prodName"));
				productIDs.add(rs.getInt("id"));
				productPrices.add(rs.getDouble("price"));
			}
			
			System.out.println(productNames.size());
			System.out.println(productIDs.size());
			System.out.println(productPrices.size());
		} catch (Exception e) {
			e.printStackTrace();

		}
		
	}
	
	//this method calls a stored procedure to determine the Sum of all sales
    //this global variable should be used here, as other methods rely on its value.
	double salesTotal = -1;
	private void GetSalesTotal()
	{
		salesTotal = -1;
		//TODO: complete this method, which should communicate with the database
		try (Connection con = DriverManager.getConnection(connection, user, pass)) {
			CallableStatement query = con.prepareCall("CALL GetSalesTotal();");
			
			ResultSet rs = query.executeQuery();
			
			if (rs.next()) {
				salesTotal = rs.getDouble("totalSales");
			}
		} catch (Exception e) {
			
		}
	}
	
	//this method calls a stored procedure to add a new Sale to the database
	private void SubmitOrder()
	{
		//TODO: complete this method, which should communicate with the database
		try (Connection con = DriverManager.getConnection(connection, user, pass)) {
			int productIndex = productListComboBox.getSelectedIndex();
			int prodID = productIDs.get(productIndex);
			int qty = Integer.parseInt(productQtyField.getText());
			
			CallableStatement query = con.prepareCall("CALL SubmitOrder(?, ?, ?);");
			query.setInt(1, loggedInUserID);
			query.setInt(2, prodID);
			query.setInt(3, qty);
			
			query.execute();
			JOptionPane.showMessageDialog(null, "Order Submitted Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to submit new order.");
		}
	}
	
	//this method calls a stored procedure to add a new product to the database
	private void SubmitNewProduct()
	{
		//TODO: complete this method, which should communicate with the database
		try (Connection con = DriverManager.getConnection(connection, user, pass)) {
			String newProdName = productNameField.getText();
			double newPrice = Double.parseDouble(productPriceField.getText());
			
			CallableStatement query = con.prepareCall("CALL SubmitNewProduct(?, ?);");
			query.setString(1, newProdName);
			query.setDouble(2, newPrice);
			
			query.execute();
			JOptionPane.showMessageDialog(null, "Prduct Added Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to add new product.");
		}
	}
}