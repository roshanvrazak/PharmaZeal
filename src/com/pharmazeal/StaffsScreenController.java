package com.pharmazeal;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeSet;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class StaffsScreenController implements Initializable {

	@FXML
	private AnchorPane main_form;

	@FXML
	private Button close;

	@FXML
	private Button minimize;

	@FXML
	private Button purchase_btn;

	@FXML
	private Button logout;

	@FXML
	private Button availability_btn;

	@FXML
	private Label username;

	@FXML
	private AnchorPane purchase_form;

	@FXML
	private TextField purchase_search;
	@FXML
	private Button remove_btn;

	@FXML
	private TableView<DrugData> purchase_tableView;

	@FXML
	private TableColumn<DrugData, String> purchase_col_drugid;

	@FXML
	private TableColumn<DrugData, String> purchase_col_drugname;

	@FXML
	private TableColumn<DrugData, String> purchase_col_condition;

	@FXML
	private TableColumn<DrugData, String> purchase_col_price;

	@FXML
	private TableColumn<DrugData, String> purchase_col_expirydate;

	@FXML
	private TableColumn<DrugData, String> purchase_col_stock;

	@FXML
	private AnchorPane availability_form;

	@FXML
	private TextField sales_avail_tunstall;

	@FXML
	private TextField sales_avail_hanley;

	@FXML
	private TextField sales_avail_fenton;

	@FXML
	private TextField sales_avail_longton;

	@FXML
	private TextField sales_avail_stock;

	@FXML
	private TableView<DrugData> availability_tableView1;

	@FXML
	private TableColumn<DrugData, String> availability_col_drugid1;

	@FXML
	private TableColumn<DrugData, String> availability_col_drugname1;

	@FXML
	private TableColumn<DrugData, String> availability_col_condition1;

	@FXML
	private TableColumn<DrugData, String> availability_col_price1;

	@FXML
	private TableColumn<DrugData, String> availability_col_expirydate1;

	@FXML
	private TableColumn<DrugData, String> availability_col_stock1;

	@FXML
	private TextField availability_search1;

	@FXML
	private Label txtDate;

	@FXML
	private Label txtTime;

	@FXML
	private ComboBox<?> cust_name;

	@FXML
	private Label cust_id_label;

	@FXML
	private Label cust_allergy_label;

	@FXML
	private Label cust_age;

	@FXML
	private Spinner<Integer> sale_quantity;

	@FXML
	private TableView<SalesData> recepit_tableView;

	@FXML
	private TableColumn<SalesData, String> recepit_col_drugid;

	@FXML
	private TableColumn<SalesData, String> recepit_col_drugname;

	@FXML
	private TableColumn<SalesData, String> recepit_col_expiry;

	@FXML
	private TableColumn<SalesData, String> recepit_col_price;

	@FXML
	private TableColumn<SalesData, String> recepit_col_qty;

	@FXML
	private TableColumn<SalesData, String> recepit_col_total;

	@FXML
	private Label sales_total;

	@FXML
	private TextField sales_amount;

	@FXML
	private Label sales_balance;

	@FXML
	private TextField purchase_searchCustomer;

	@FXML
	private TextField CustomerName1;

	private Connection connect;
	private PreparedStatement prepare;
	private Statement statement;
	private ResultSet result;

	private double x = 0;
	private double y = 0;

	//
	public ObservableList<DrugData> AvailabilityListData() {
		String sql = "SELECT * FROM drugs";
		ObservableList<DrugData> listData = FXCollections.observableArrayList();

		connect = Database.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			DrugData drugData;

			while (result.next()) {

				drugData = new DrugData(result.getInt("drug_id"), result.getString("drug_name"),
						result.getString("conditions"), result.getDouble("unit_price"), result.getDate("expiry_date"),
						result.getInt("stock"), result.getString("id_check"), result.getString("stoke_avail"),
						result.getString("tunstall_avail"), result.getString("fenton_avail"),
						result.getString("hanley_avail"), result.getString("longton_avail"), result.getString("image"));

				listData.add(drugData);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}

	private ObservableList<DrugData> availMedicineList;

	public void availablilityShowListData() {
		availMedicineList = AvailabilityListData();

		availability_col_drugid1.setCellValueFactory(new PropertyValueFactory<>("drug_id"));

		availability_col_drugname1.setCellValueFactory(new PropertyValueFactory<>("drug_name"));

		availability_col_condition1.setCellValueFactory(new PropertyValueFactory<>("conditions"));

		availability_col_price1.setCellValueFactory(new PropertyValueFactory<>("unit_price"));

		availability_col_expirydate1.setCellValueFactory(new PropertyValueFactory<>("expiry"));

		availability_col_stock1.setCellValueFactory(new PropertyValueFactory<>("stock"));

		availability_tableView1.setItems(availMedicineList);

	}

	public void availabilitySelect() {
		System.out.println("inside availabilitySelect");

		DrugData drugData = availability_tableView1.getSelectionModel().getSelectedItem();
		int num = availability_tableView1.getSelectionModel().getSelectedIndex();
		if ((num - 1) < -1) {
			return;
		}
//		System.out.println("stock = "+drugData.getStk_avail());
//		System.out.println("tunstall= "+drugData.getTun_avail());
		sales_avail_stock.setText(drugData.getStk_avail());
		sales_avail_tunstall.setText(drugData.getTun_avail());
		sales_avail_fenton.setText(drugData.getFen_avail());
		sales_avail_hanley.setText(drugData.getHan_avail());
		sales_avail_longton.setText(drugData.getLong_avail());
	}
	//

//	private ObservableList<DrugData> AvailabilityList;

	private ObservableList<DrugData> AvailabilityList = FXCollections.observableArrayList();

	private String searchKey;

	// working
	public void AvailabilitySearch() {

		AvailabilityList = AvailabilityListData();

		FilteredList<DrugData> filteredData = new FilteredList<>(AvailabilityList, e -> true);

		availability_search1.textProperty().addListener((observable, oldValue, newValue) -> {

			filteredData.setPredicate(drugData -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				searchKey = newValue.toLowerCase();

				if (drugData.getDrug_name().toLowerCase().indexOf(searchKey) > -1) {
					return true;
				} else if (drugData.getDrug_id().toString().contains(searchKey)) {
					return true;
				} else {
					return false;
				}
			});

		});

		SortedList<DrugData> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(availability_tableView1.comparatorProperty());
		availability_tableView1.setItems(sortedData);

	}

	public void logout() {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Message");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to logout?");
			Optional<ButtonType> option = alert.showAndWait();

			if (option.get().equals(ButtonType.OK)) {
				// Hide Dashboard
				logout.getScene().getWindow().hide();
				// Logging out to Login screen
				Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);

				root.setOnMousePressed((MouseEvent event) -> {
					x = event.getSceneX();
					y = event.getSceneY();
				});

				root.setOnMouseDragged((MouseEvent event) -> {
					stage.setX(event.getScreenX() - x);
					stage.setY(event.getScreenY() - y);
					stage.setOpacity(.8);
				});

				root.setOnMouseReleased((MouseEvent event) -> {
					stage.setOpacity(1);
				});

				stage.initStyle(StageStyle.TRANSPARENT);
				stage.setScene(scene);
				stage.show();

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void minimize() {
		Stage stage = (Stage) main_form.getScene().getWindow();
		stage.setIconified(true);
	}

	public void close() {
		System.exit(0);
	}

	public void switchForm(ActionEvent event) {
		if (event.getSource() == purchase_btn) {
			purchase_form.setVisible(true);
			availability_form.setVisible(false);

			purchase_btn.setStyle("-fx-background-color:#8a418c");
			availability_btn.setStyle("-fx-background-color:transparent");
			spinnerShowValue();
		} else if (event.getSource() == availability_btn) {
			purchase_form.setVisible(false);
			availability_form.setVisible(true);

			availability_btn.setStyle("-fx-background-color:#8a418c");
			purchase_btn.setStyle("-fx-background-color:transparent");

			availablilityShowListData();
		}
	}

	public void displayUsername() {
		String user = GetData.username;
		// Setting Username to app with formatting
		username.setText(user.substring(0, 1).toUpperCase() + user.substring(1));

	}

	public void defaultNav() {
		purchase_btn.setStyle("-fx-background-color:#8a418c");
		// purchase_form.setVisible(true);
	}

	public void generateDateTime() {
		txtDate.setText(LocalDate.now().toString());
		Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, (e) -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
			txtTime.setText(LocalDateTime.now().format(formatter));
		}), new KeyFrame(Duration.seconds(1.0D)));
		timeline.setCycleCount(-1);
		timeline.play();

	}

	private ObservableList<DrugData> availMedicineListDataSales;

	public void availablilityShowListDataSales() {
		availMedicineListDataSales = AvailabilityListData();

		purchase_col_drugid.setCellValueFactory(new PropertyValueFactory<>("drug_id"));

		purchase_col_drugname.setCellValueFactory(new PropertyValueFactory<>("drug_name"));

		purchase_col_condition.setCellValueFactory(new PropertyValueFactory<>("conditions"));

		purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("unit_price"));

		purchase_col_expirydate.setCellValueFactory(new PropertyValueFactory<>("expiry"));

		purchase_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));

		purchase_tableView.setItems(availMedicineListDataSales);

	}

	private ObservableList<DrugData> SalesAvailabilityList = FXCollections.observableArrayList();

	private String searchKeySales;

	// working
	public void SalesAvailabilitySearch() {
		// System.out.println("working ");

		SalesAvailabilityList = AvailabilityListData();

		FilteredList<DrugData> filteredData = new FilteredList<>(SalesAvailabilityList, e -> true);

		purchase_search.textProperty().addListener((observable, oldValue, newValue) -> {

			filteredData.setPredicate(drugData -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				searchKeySales = newValue.toLowerCase();

				if (drugData.getDrug_name().toLowerCase().indexOf(searchKeySales) > -1) {
					return true;
				} else if (drugData.getDrug_id().toString().contains(searchKeySales)) {
					return true;
				} else {
					return false;
				}
			});

		});

		SortedList<DrugData> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(purchase_tableView.comparatorProperty());
		purchase_tableView.setItems(sortedData);

	}

	private SpinnerValueFactory<Integer> spinner;

	public void spinnerShowValue() {
		spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);
		sale_quantity.setValueFactory(spinner);
	}

	private int qty;

	public void purchaseQuantity() {
		qty = sale_quantity.getValue();
	}

	public ObservableList<DrugData> SalesReceiptListData() {
		System.out.println("2");
		String sql = "SELECT * FROM drugs where drug_id='"
				+ purchase_tableView.getSelectionModel().getSelectedItem().getDrug_id() + "'";
		// System.out.println(sql);
		ObservableList<DrugData> listData = FXCollections.observableArrayList();

		connect = Database.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			DrugData drugData;

			while (result.next()) {

				drugData = new DrugData(result.getInt("drug_id"), result.getString("drug_name"),
						result.getString("conditions"), result.getDouble("unit_price"), result.getDate("expiry_date"),
						result.getInt("stock"), result.getString("id_check"), result.getString("stoke_avail"),
						result.getString("tunstall_avail"), result.getString("fenton_avail"),
						result.getString("hanley_avail"), result.getString("longton_avail"), result.getString("image"));

				listData.add(drugData);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}

	// To keep track of each purchase sessions
	private int sessionId;

	public void salesSessionid() {
		String sql = "SELECT sessionid FROM sales";
		connect = Database.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			while (result.next()) {
				sessionId = result.getInt("sessionid");
			}
			int sID = 0;
			String checkData = "SELECT sessionid FROM sales_info";

			statement = connect.createStatement();
			result = statement.executeQuery(checkData);

			while (result.next()) {
				sID = result.getInt("sessionid");
			}
			if (sessionId == 0) {
				sessionId += 1;
			} else if (sID == sessionId) {
				sessionId = sID + 1;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private double totalP;

	private ObservableList<DrugData> SalesListData;

	public void addToCartData() {// purchaseAdd()

		salesSessionid();
		String sql = "INSERT INTO sales (sessionid,drug_id,drug_name,expiry_date,quantity,price,total)"
				+ " VALUES(?,?,?,?,?,?,?)";
		// String drugIDCheckData= "select drug_id from sales where sessionid= (select
		// max(sessionid) from sales)";
		connect = Database.connectDB();

		try {
			if (qty > 0) {
				prepare = connect.prepareStatement(sql);

				DrugData d = purchase_tableView.getSelectionModel().getSelectedItem();

				prepare.setInt(1, sessionId);
				prepare.setString(2, String.valueOf(d.getDrug_id()));
				prepare.setString(3, String.valueOf(d.getDrug_name()));
				prepare.setDate(4, d.getExpiry());
				prepare.setInt(5, qty);
				prepare.setDouble(6, d.getUnit_price());

				String checkData = "SELECT * FROM drugs WHERE drug_id = '"
						+ purchase_tableView.getSelectionModel().getSelectedItem().getDrug_id() + "'";
				statement = connect.createStatement();
				result = statement.executeQuery(checkData);
				double priceD = 0, totalPrice = 0;
				int stock = 0;
				if (result.next()) {
					priceD = result.getDouble("unit_price");
					stock = result.getInt("stock");
				}
				totalP = (priceD * qty);

				prepare.setDouble(7, totalP);
				// prepare.setString(8, String.valueOf(d.getIdCheck()));
				prepare.executeUpdate();

				showRecepitdata();
				salesDisplayTotal();

				updateStockInventory(stock, qty, d.getDrug_id());
			} else {
				Alert alert;
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Occured");
				alert.setHeaderText(null);
				alert.setContentText("Please fill quantity");
				alert.showAndWait();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateStockInventory(int stock, int qty, int id) {
		int temp = stock - qty;
		String inv = "update drugs set stock='" + temp + "' where drug_id ='" + id + "'";
		connect = Database.connectDB();
		try {
			statement = connect.createStatement();
			statement.executeUpdate(inv);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private double totalPriceD;

	public void salesDisplayTotal() {
		String sql = "SELECT SUM(total) FROM sales WHERE sessionid = '" + sessionId + "'";
		connect = Database.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			if (result.next()) {
				totalPriceD = result.getDouble("SUM(total)");
			}
			sales_total.setText("£" + String.valueOf(totalPriceD));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private double balance;
	private double amount;

	public void salesAmount() {
		if (sales_amount.getText().isEmpty() || totalPriceD == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Somthing went wrong");
			alert.showAndWait();
		} else {
			amount = Double.parseDouble(sales_amount.getText());
			if (totalPriceD > amount) {
				sales_amount.setText("");
			} else {
				balance = (amount - totalPriceD);
				sales_balance.setText("£" + String.valueOf(balance));
			}
		}
	}

	public void salesPay() {
		idCheck();
		salesSessionid();
		String sql = "INSERT INTO sales_info (sessionid, total_amount, date) VALUES (?, ?, ?)";
		connect = Database.connectDB();

		try {
//			System.out.println(cust_id_label.getText());
//			System.out.println(cust_id_label.getText().isEmpty());
			Alert alert;
			if (cust_id_label.getText() == null || cust_id_label.getText().isEmpty()
					|| cust_id_label.getText().trim().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Please select customer");
				alert.showAndWait();
			}

			else if (totalPriceD == 0) {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Something went wrong");
				alert.showAndWait();
			} else {

				// String IDCheck =
				// recepit_tableView.getSelectionModel().getSelectedItem().getSalesIDCheck();

				alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText(null);
				alert.setContentText("Are you sure?");
				Optional<ButtonType> option = alert.showAndWait();

				if (option.get().equals(ButtonType.OK)) {

					prepare = connect.prepareStatement(sql);
					prepare.setString(1, String.valueOf(sessionId));
					prepare.setString(2, String.valueOf(totalPriceD));
					java.util.Date date = new java.util.Date();
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());

					prepare.setString(3, String.valueOf(sqlDate));
					prepare.executeUpdate();

					alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setContentText("Successful");
					alert.showAndWait();

					sales_total.setText("£0.0");
					sales_amount.setText("");
					sales_balance.setText("£0.0");
					// Add resert code for Receipt Table view

				}

			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public ObservableList<SalesData> receiptListData() {
		salesSessionid();
		String sql = "SELECT * FROM sales WHERE sessionid ='" + sessionId + "'";
		ObservableList<SalesData> listData = FXCollections.observableArrayList();

		connect = Database.connectDB();
		try {
			SalesData salesD;
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			while (result.next()) {
				salesD = new SalesData(result.getInt("sessionid"), result.getInt("drug_id"),
						result.getString("drug_name"), result.getDate("expiry_date"), result.getInt("quantity"),
						result.getDouble("price"), result.getDouble("total"), result.getString("check"));
				listData.add(salesD);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listData;

	}

	private ObservableList<SalesData> receiptList;

	public void showRecepitdata() {
		receiptList = receiptListData();

		recepit_col_drugid.setCellValueFactory(new PropertyValueFactory<>("salesDrugId"));
		recepit_col_drugname.setCellValueFactory(new PropertyValueFactory<>("salesDrugName"));
		recepit_col_expiry.setCellValueFactory(new PropertyValueFactory<>("salesExpiryDate"));
		recepit_col_qty.setCellValueFactory(new PropertyValueFactory<>("salesQty"));
		recepit_col_price.setCellValueFactory(new PropertyValueFactory<>("salesPrice"));
		recepit_col_total.setCellValueFactory(new PropertyValueFactory<>("salesTotal"));

		recepit_tableView.setItems(receiptList);
	}

	// implement one more function to delete entries from table
	public void removeFromCart() {

		remove_btn.setOnAction(event -> {
			// Get the selected item
			SalesData selectedItem = recepit_tableView.getSelectionModel().getSelectedItem();

			if (selectedItem != null) {
				// Remove the selected item from the underlying data source

				recepit_tableView.getItems().remove(selectedItem);
				// recepit_tableView.setSelectionModel(null);
				recepit_tableView.refresh();
			}
		});
//		salesAmount();
	}

	// ************ Searching Customer in Ponit of Sales
	// *********************************************************************************************************

	public ObservableList<CustomersData> CustomerListData() {
		System.out.println("inside CustomerListData");
		String sql = "SELECT * FROM customers";
		ObservableList<CustomersData> listData = FXCollections.observableArrayList();

		connect = Database.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			CustomersData customerData;

			while (result.next()) {

				customerData = new CustomersData(result.getInt("customer_id"), result.getString("first_name"),
						result.getString("middle_name"), result.getString("last_name"), result.getDate("dob"),
						result.getString("house_no"), result.getString("street_name"), result.getString("postcode"),
						result.getString("city"), result.getString("county"), result.getString("country"),
						result.getString("allergies"));

				listData.add(customerData);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}

	@FXML
	private Label cust_name2;

	@FXML
	private ComboBox<CustomersData> cust_name3;

	@FXML
	private TableColumn<CustomersData, String> cus_col_id;

	@FXML
	private TableColumn<CustomersData, String> cus_col_name;

	@FXML
	private TableColumn<CustomersData, String> cus_col_lastname;
	@FXML
	private TableView<CustomersData> CustomerTableView;

	private ObservableList<CustomersData> CustomerList = FXCollections.observableArrayList();

	private TextField searchField;

	public void customerSearchSales() {
		System.out.println("inside  customerSearchSales");

		CustomerList = CustomerListData();
		FilteredList<CustomersData> filter = new FilteredList<>(CustomerList, e -> true);
		purchase_searchCustomer.textProperty().addListener((Observable, oldValue, newValue) -> {
			filter.setPredicate(predicateCustomersData -> {
				if (newValue == null || newValue.isEmpty()) {
					System.out.println("1");
					return true;
				}

				String searchKey = newValue.toLowerCase();
				// System.out.println(searchKey);

//				if (predicateCustomersData.getFirst_name().toLowerCase().indexOf(searchKey) > -1) {
//					System.out.println(predicateCustomersData.getFirst_name());
//					return true;
//				} 
//				else 
				if (predicateCustomersData.getCustomer_id().toString().contains(searchKey)) {
					System.out.println(predicateCustomersData.getCustomer_id());
					return true;
				} else {
					return false;
				}
			});

		});
		SortedList<CustomersData> sortList = new SortedList<>(filter);
		sortList.comparatorProperty().bind(CustomerTableView.comparatorProperty());
		CustomerTableView.setItems(sortList);
		salesCustomerShowListData();

//		//
//		FilteredList<CustomersData> filter = new FilteredList<>(addCustomersList, e -> true);
//		addCustomer_search.textProperty().addListener((Observable, oldValue, newValue) -> {
//			filter.setPredicate(predicateCustomersData -> {
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//
//				String searchKey = newValue.toLowerCase();
//
//				if (predicateCustomersData.getCustomer_id().toString().contains(searchKey)) {
//					return true;
//				} else if (predicateCustomersData.getFirst_name().toLowerCase().indexOf(searchKey) > -1) {
//					return true;
//				} else if (predicateCustomersData.getLast_name().toLowerCase().indexOf(searchKey) > -1) {
//					return true;
//				} else
//					return false;
//
//			});
//		});
//		SortedList<CustomersData> sortList = new SortedList<>(filter);
//		sortList.comparatorProperty().bind(addCustomer_tableView.comparatorProperty());
//		addCustomer_tableView.setItems(sortList);

		//
	}

	public void salesCustomerShowListData() {
		System.out.println("inside salesCustomerShowListData");
		CustomerList = CustomerListData();

		cus_col_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));

		cus_col_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));

		cus_col_lastname.setCellValueFactory(new PropertyValueFactory<>("first_name"));

		CustomerTableView.setItems(CustomerList);

	}

	public void salesCustomersSelect() {
		System.out.println("inside addCustomersSelect ");

		CustomersData cusData = (CustomersData) CustomerTableView.getSelectionModel().getSelectedItem();
		int num = CustomerTableView.getSelectionModel().getSelectedIndex();
		if ((num - 1) < -1) {
			return;
		}
		cust_id_label.setText(String.valueOf(cusData.getCustomer_id()));
		cust_name.setPromptText(String.valueOf(cusData.getFirst_name()));
		cust_allergy_label.setText(cusData.getAllergy());

	}

	private ObservableList<CustomersData> CustomersListInSales;

	public void addCustomerShowListDataSales() {
		CustomersListInSales = CustomerListData();

		cus_col_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));

		cus_col_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));

		cus_col_lastname.setCellValueFactory(new PropertyValueFactory<>("last_name"));

		CustomerTableView.setItems(CustomersListInSales);

	}

	// ***************************************************************************************************

	public void customersID() {
		String sql = "SELECT * FROM customers";
		connect = Database.connectDB();

		try {
			ObservableList listData = FXCollections.observableArrayList();
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			while (result.next()) {
				// listData.add(result.getString("first_name"));
				String fullname = result.getString("first_name") + " " + result.getString("last_name");

				listData.add(fullname);
			}
			cust_name.setItems(listData);
			customersName();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void customersName() {
		String selectedName = (String) cust_name.getSelectionModel().getSelectedItem();
		String sql = "SELECT * FROM customers WHERE CONCAT(first_name, ' ', last_name) = ?";

		connect = Database.connectDB();
		try {
			prepare = connect.prepareStatement(sql);
			prepare.setString(1, selectedName);
			result = prepare.executeQuery();
			if (result.next()) {
				String id = result.getString("customer_id");
				String allergy = result.getString("allergies");

				cust_id_label.setText(id);
				cust_allergy_label.setText(allergy);
				idCheck();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int age = 0;

	public void idCheck() {
		System.out.println("inside id check");
		String custId = cust_id_label.getText();
		String sql = "select dob from customers where customer_id='" + custId + "'";
		connect = Database.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			if (result.next()) {
				java.sql.Date dobSql = result.getDate("dob");
				if (dobSql != null) {
					LocalDate dob = dobSql.toLocalDate();

					LocalDate currentDate = LocalDate.now(); // Current date

					Period period = Period.between(dob, currentDate);

					int years = period.getYears();
					age = years;
					System.out.println("age =" + age);
				}

			}
			String checkRequired = "";
			cust_age.setText(Integer.toString(age));
			if (age < 18) {

				System.out.println("inside less than 18");
//
//				System.out.println("drugid=" + drugId);

				connect = Database.connectDB();

				// String drugDetails = "select drug_id from sales where sessionid= (select
				// max(sessionid) from sales)";

				int drugId = recepit_col_drugid.getTableView().getSelectionModel().getSelectedItem().getSalesDrugId();
				String query = "select id_check from drugs where drug_id='" + drugId + "'";
				System.out.println(query);
				try {

					prepare = connect.prepareStatement(query);
					result = prepare.executeQuery();
					while (result.next()) {
						checkRequired = result.getString("id_check");
					}
					System.out.println("check =" + checkRequired);

					if (age < 18 && checkRequired.equals("Y")) {
						System.out.println("inside age <18 and check =Y");
						Alert alert;
						alert = new Alert(AlertType.ERROR);
						alert.setHeaderText(null);
						alert.setContentText("ID Check Required!");
						alert.showAndWait();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void handleButtonClick() {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("AddCustomer.fxml"));

			Scene scene = new Scene(fxmlLoader.load(), 900, 500);
			Stage stage = new Stage();
			stage.setTitle("Add New Customer");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		generateDateTime();
		displayUsername();
		defaultNav();

		spinnerShowValue();
		customersID();
//		customersName();
		availablilityShowListDataSales();
		showRecepitdata();
		salesDisplayTotal();

		availablilityShowListData();
		// addCustomerShowListDataSales();

	}

}
