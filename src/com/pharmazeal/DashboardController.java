package com.pharmazeal;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ComboBox;
//import javafx.scene.control.DatePicker;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class DashboardController implements Initializable {

	@FXML
	private AnchorPane main_form;

	@FXML
	private Button close;

	@FXML
	private Button minimize;

	@FXML
	private Button dashboard_btn;

	@FXML
	private Button addMedicine_btn;

	@FXML
	private Button purchase_btn;

	@FXML
	private Button customer_btn;

	@FXML
	private Button staff_btn;

	@FXML
	private Button logout;

	@FXML
	private Label username;

	@FXML
	private AnchorPane dashboard_form;

	@FXML
	private AreaChart<?, ?> dashboard_chart;

	@FXML
	private Label dashboard_availableMed;

	@FXML
	private Label dashboard_totalIncome;

	@FXML
	private Label dashboard_totalCustomers;

	@FXML
	private AnchorPane addMedicines_form;

	@FXML
	private TextField addMedicines_drugid;

	@FXML
	private TextField addMedicines_drugname;

	@FXML
	private TextField addMedicines_condition;

	@FXML
	private TextField addMedicines_unitprice1;

	@FXML
	private TextField addMedicines_stock;

	@FXML
	private TextField addMedicines_idcheck;

	@FXML
	private TextField addMedicines_avail_tunstall;

	@FXML
	private TextField addMedicines_avail_hanley;

	@FXML
	private TextField addMedicines_avail_fenton;

	@FXML
	private TextField addMedicines_avail_longton;

	@FXML
	private TextField addMedicines_avail_stock;

	@FXML
	private DatePicker addMedicines_expiry;

	@FXML
	private TableView<DrugData> addMedicines_tableView1;

	@FXML
	private TableColumn<DrugData, String> addMedicines_col_drugID;

	@FXML
	private TableColumn<DrugData, String> addMedicines_col_drugName;

	@FXML
	private TableColumn<DrugData, String> addMedicines_col_condition;

	@FXML
	private TableColumn<DrugData, String> addMedicines_col_unitPrice;

	@FXML
	private TableColumn<DrugData, String> addMedicines_col_expiry;

	@FXML
	private TableColumn<DrugData, String> addMedicines_col_stock;

	@FXML
	private TextField addMedicines_medicineid;

	@FXML
	private TextField addMedicines_brandname;

	@FXML
	private TextField addMedicines_productname;

	@FXML
	private ComboBox<?> addMedicines_type;

	@FXML
	private ComboBox<?> addMedicines_status;

	@FXML
	private TextField addMedicines_price;

	@FXML
	private ImageView addMedicines_imageView;

	@FXML
	private Button addMedicines_importBtn;

	@FXML
	private Button addMedicines_addBtn;

	@FXML
	private Button addMedicines_updateBtn;

	@FXML
	private Button addMedicines_clearBtn;

	@FXML
	private Button addMedicines_deleteBtn;

	@FXML
	private TextField addMedicines_search;

	@FXML
	private TableView<MedicineData> addMedicines_tableView;

	@FXML
	private TableColumn<MedicineData, String> addMedicines_col_medicineID;

	@FXML
	private TableColumn<MedicineData, String> addMedicines_col_brandName;

	@FXML
	private TableColumn<MedicineData, String> addMedicines_col_productName;

	@FXML
	private TableColumn<MedicineData, String> addMedicines_col_type;

	@FXML
	private TableColumn<MedicineData, String> addMedicines_col_price;

	@FXML
	private TableColumn<MedicineData, String> addMedicines_col_status;

	@FXML
	private TableColumn<MedicineData, String> addMedicines_col_date;

	@FXML
	private AnchorPane purchase_form;

	@FXML
	private ComboBox<?> purchase_type;

	@FXML
	private Spinner<Integer> purchase_quantity;

	@FXML
	private ComboBox<?> purchase_medicineID;

	@FXML
	private ComboBox<?> purchase_brand;

	@FXML
	private ComboBox<?> purchase_productName;

	@FXML
	private Button purchase_addBtn;

	@FXML
	private Label purchase_total;

	@FXML
	private TextField purchase_amount;

	@FXML
	private Label purchase_balance;

	@FXML
	private Button purchase_payBtn;

	@FXML
	private TableView<CustomerData> purchase_tableView;

	@FXML
	private TableColumn<CustomerData, String> purchase_col_medicineid;

	@FXML
	private TableColumn<CustomerData, String> purchase_col_brand;

	@FXML
	private TableColumn<CustomerData, String> purchase_col_productName;

	@FXML
	private TableColumn<CustomerData, String> purchase_col_type;

	@FXML
	private TableColumn<CustomerData, String> purchase_col_qty;

	@FXML
	private TableColumn<CustomerData, String> purchase_col_price;

	@FXML
	private AnchorPane addCustomer_form;

	@FXML
	private TextField addCustomer_customerid;

	@FXML
	private TextField addCustomer_firstname;

	@FXML
	private TextField addCustomer_midname;

	@FXML
	private TextField addCustomer_surname;

	@FXML
	private Button addCustomer_addBtn;

	@FXML
	private Button addCustomer_updateBtn;

	@FXML
	private Button addCustomer_clearBtn;

	@FXML
	private Button addCustomer_deleteBtn;

	@FXML
	private DatePicker addCustomer_DOB;

	@FXML
	private TextField addCustomer_allergies;

	@FXML
	private TextField addCustomer_streetname;

	@FXML
	private TextField addCustomer_postcode;

	@FXML
	private TextField addCustomer_city;

	@FXML
	private TextField addCustomer_county;

	@FXML
	private TextField addCustomer_houseno;

	@FXML
	private TextField addCustomer_country;

	@FXML
	private TextField addCustomer_search;

	@FXML
	private TableView<CustomersData> addCustomer_tableView;

	@FXML
	private TableColumn<CustomersData, String> addCustomer_col_firstname;

	@FXML
	private TableColumn<CustomersData, String> addCustomer_col_midname;

	@FXML
	private TableColumn<CustomersData, String> addCustomer_col_surname;

	@FXML
	private TableColumn<CustomersData, String> addCustomer_col_DOB;

	@FXML
	private TableColumn<CustomersData, String> addCustomer_col_houseno;

	@FXML
	private TableColumn<CustomersData, String> addCustomer_col_streetname;

	@FXML
	private TableColumn<CustomersData, String> addCustomer_col_postcode;

	@FXML
	private TableColumn<CustomersData, String> addCustomer_col_city;

	@FXML
	private TableColumn<CustomersData, String> addCustomer_col_county;

	@FXML
	private TableColumn<CustomersData, String> addCustomer_col_country;

	@FXML
	private TableColumn<CustomersData, String> addCustomer_col_id;

	@FXML
	private TableColumn<CustomersData, String> addCustomer_col_allergies;

	@FXML
	private TableView<DrugData> expired_tableView;

	@FXML
	private TableColumn<DrugData, String> expired_col_drugid;

	@FXML
	private TableColumn<DrugData, String> expired_col_drugname;

	@FXML
	private TableColumn<DrugData, String> expired_col_condition;

	@FXML
	private TableColumn<DrugData, String> expired_col_price;

	@FXML
	private TableColumn<DrugData, String> expired_col_expiry;

	@FXML
	private TableColumn<DrugData, String> expired_col_stock;

	@FXML
	private TableView<DrugData> lowStock_tableView;

	@FXML
	private TableColumn<DrugData, String> lowStock_col_drugid1;

	@FXML
	private TableColumn<DrugData, String> lowStock_col_drugname;

	@FXML
	private TableColumn<DrugData, String> lowStock_col_condition;

	@FXML
	private TableColumn<DrugData, String> lowStock_col_unitprice;

	@FXML
	private TableColumn<DrugData, String> lowStock_col_expiry;

	@FXML
	private TableColumn<DrugData, String> lowStock_col_stock;

	@FXML
	private AnchorPane staffmanage_form;

	// DB Tools
	// *******************
	private Connection connect;
	private PreparedStatement prepare;
	private Statement statement;
	private ResultSet result;

	private Image image;

	// chart dashboard

	public void homeChart() {
		dashboard_chart.getData().clear();
		String sql = "SELECT date, SUM(total_amount) FROM sales_info"
				+ " GROUP BY date ORDER BY TIMESTAMP (date) ASC LIMIT 9";
		connect = Database.connectDB();
		try {
			XYChart.Series chart = new XYChart.Series();
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			while (result.next()) {
				chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
			}
			dashboard_chart.getData().add(chart);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Available Medicines count in dashboard which is available in stoke
	public void homeAM() {
		String sql = "SELECT COUNT(drug_id) FROM drugs WHERE stoke_avail = 'Y'";
		connect = Database.connectDB();
		int countAM = 0;

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			while (result.next()) {
				countAM = result.getInt("COUNT(drug_id)");
			}
			dashboard_availableMed.setText(String.valueOf(countAM));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Total Income Dashboard calculation
	public void homeTI() {
		String sql = "SELECT SUM(total_amount) FROM sales_info";
		connect = Database.connectDB();
		double totalDisplay = 0;

		try {

			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			while (result.next()) {
				totalDisplay = result.getDouble("SUM(total_amount)");
			}
			dashboard_totalIncome.setText("£" + String.valueOf(totalDisplay));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Total Customers count calculation Dashboard
	public void homeTC() {
		String sql = "SELECT COUNT(customer_id) FROM customers";
		connect = Database.connectDB();
		int countTC = 0;

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			while (result.next()) {
				countTC = result.getInt("COUNT(customer_id)");
			}
			dashboard_totalCustomers.setText(String.valueOf(countTC));
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void addMedicinesAdd() {
		String sql = "INSERT INTO drugs (drug_id, drug_name, conditions, unit_price, expiry_date, stock, id_check, "
				+ "stoke_avail, tunstall_avail,fenton_avail,hanley_avail,longton_avail,image) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		connect = Database.connectDB();
		try {

			Alert alert;
			if (addMedicines_drugid.getText().isEmpty() || addMedicines_drugid.getText().isEmpty()
					|| addMedicines_condition.getText().isEmpty() || addMedicines_unitprice1.getText().isEmpty()
					|| addMedicines_expiry.getValue() == null || addMedicines_idcheck.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("please fill all blank fields");
				alert.showAndWait();
			} else {

				// CHECKING IF DATA IS ALREADY PRESENT IN DATABASE

				String checkData = "SELECT drug_id FROM drugs WHERE drug_id ='" + addMedicines_drugid.getText() + "'";
				statement = connect.createStatement();
				result = statement.executeQuery(checkData);
				if (result.next()) {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Medicine ID: " + addMedicines_drugid.getText() + " already exist!");
					alert.showAndWait();
				} else {
					String uri = GetData.path;
					uri = uri.replace("\\", "\\\\");

					prepare = connect.prepareStatement(sql);
					prepare.setString(1, addMedicines_drugid.getText());
					prepare.setString(2, addMedicines_drugname.getText());
					prepare.setString(3, addMedicines_condition.getText());
					prepare.setString(4, addMedicines_unitprice1.getText());
					prepare.setString(5, String.valueOf(addMedicines_expiry.getValue()));
					prepare.setString(6, addMedicines_stock.getText());
					prepare.setString(7, addMedicines_idcheck.getText());
					prepare.setString(8, addMedicines_avail_stock.getText());
					prepare.setString(9, addMedicines_avail_tunstall.getText());
					prepare.setString(10, addMedicines_avail_fenton.getText());
					prepare.setString(11, addMedicines_avail_hanley.getText());
					prepare.setString(12, addMedicines_avail_longton.getText());

					prepare.setString(13, uri);
				}
				prepare.executeUpdate();

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Message");
				alert.setHeaderText(null);
				alert.setContentText("Successfully Added!");
				alert.showAndWait();

				addMedicineShowListData(); // calling function to show data from database
				addMedicineReset();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void addMedicineUpdate() {

		String uri = GetData.path;
		// uri = uri.replace("\\", "\\\\");

		String sql = "UPDATE drugs SET drug_id='" + addMedicines_drugid.getText() + "', drug_name = '"
				+ addMedicines_drugname.getText() + "', conditions ='" + addMedicines_condition.getText()
				+ "', unit_price = '" + addMedicines_unitprice1.getText() + "', expiry_date = '"
				+ String.valueOf(addMedicines_expiry.getValue()) + "',stock = '" + addMedicines_stock.getText()
				+ "',id_check ='" + addMedicines_idcheck.getText() + "',stoke_avail = '"
				+ addMedicines_avail_stock.getText() + "',tunstall_avail ='" + addMedicines_avail_tunstall.getText()
				+ "',fenton_avail ='" + addMedicines_avail_fenton.getText() + "',hanley_avail='"
				+ addMedicines_avail_hanley.getText() + "',longton_avail ='" + addMedicines_avail_longton.getText()
				+ "', image = '" + uri + "' WHERE drug_id = '" + addMedicines_drugid.getText() + "'";
		connect = Database.connectDB();
		try {

			Alert alert;
			if (addMedicines_drugid.getText().isEmpty() || addMedicines_drugid.getText().isEmpty()
					|| addMedicines_condition.getText().isEmpty() || addMedicines_unitprice1.getText().isEmpty()
					|| addMedicines_expiry.getValue() == null || addMedicines_idcheck.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("please fill all blank fields");
				alert.showAndWait();
			} else {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Message");
				alert.setHeaderText(null);
				alert.setContentText(
						"Are you sure you want to UPDATE Medicine ID:" + addMedicines_drugid.getText() + "?");
				Optional<ButtonType> option = alert.showAndWait();

				if (option.get().equals(ButtonType.OK)) {
					statement = connect.createStatement();
					statement.executeUpdate(sql);

					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Updated!");
					alert.showAndWait();

					addMedicineShowListData();
					addMedicineReset();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addMedicineDelete() {
		String sql = "DELETE FROM drugs WHERE drug_id = '" + addMedicines_drugid.getText() + " ' ";

		connect = Database.connectDB();

		try {

			Alert alert;
			if (addMedicines_drugid.getText().isEmpty() || addMedicines_drugid.getText().isEmpty()
					|| addMedicines_condition.getText().isEmpty() || addMedicines_unitprice1.getText().isEmpty()
					|| addMedicines_expiry.getValue() == null || addMedicines_idcheck.getText().isEmpty()) {

				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("please fill all blank fields");
				alert.showAndWait();
			} else {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Message");
				alert.setHeaderText(null);
				alert.setContentText(
						"Are you sure you want to DELETE Medicine ID:" + addMedicines_drugid.getText() + "?");
				Optional<ButtonType> option = alert.showAndWait();

				if (option.get().equals(ButtonType.OK)) {
					statement = connect.createStatement();
					statement.executeUpdate(sql);

					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Deleted!");
					alert.showAndWait();

					addMedicineShowListData();
					addMedicineReset();

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addMedicineReset() {
		addMedicines_drugid.setText("");
		addMedicines_drugname.setText("");
		addMedicines_condition.setText("");
		addMedicines_unitprice1.setText("");
		addMedicines_expiry.setValue(null);
		addMedicines_stock.setText("");
		addMedicines_idcheck.setText("");
		addMedicines_avail_stock.setText("");
		addMedicines_avail_tunstall.setText("");
		addMedicines_avail_fenton.setText("");
		addMedicines_avail_hanley.setText("");
		addMedicines_avail_longton.setText("");
		addMedicines_imageView.setImage(null);
	}

//	// Medicine Type list
//
//	private String[] addMedicineListT = { "Hydrocodone", "Antibiotics", "Metformin", "Losartan", "Albutarol" };
//
//	public void addMedicineListType() {
//		List<String> listT = new ArrayList<>();
//		for (String data : addMedicineListT) {
//			listT.add(data);
//		}
//
//		ObservableList listData = FXCollections.observableArrayList(listT);
//		addMedicines_type.setItems(listData);
//
//	}
//
//	private String[] addMedicineStatus = { "Available", "Not Available" };
//
//	public void addMedicineListStatus() {
//		List<String> listS = new ArrayList<>();
//		for (String data : addMedicineStatus) {
//			listS.add(data);
//		}
//
//		ObservableList listData = FXCollections.observableArrayList(listS);
//		addMedicines_status.setItems(listData);
//	}

	// Image for medicine - choosing image
	public void addMedicineImportImage() {

		FileChooser open = new FileChooser();
		open.setTitle("Import Image File");
//		open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png")); //***** For Windows
		open.getExtensionFilters().add(new ExtensionFilter("All Files", "*.jpeg", "*png", "*jpg")); // for Mac os

		File file = open.showOpenDialog(main_form.getScene().getWindow());

		if (file != null) {
			image = new Image(file.toURI().toString(), 127, 184, false, true);
			addMedicines_imageView.setImage(image);

			GetData.path = file.getAbsolutePath();
		}

	}

	// Getting data of drugs from DB
	public ObservableList<DrugData> addMedicinesListData() {
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

	private ObservableList<DrugData> addMedicineList;

	public void addMedicineShowListData() {
		addMedicineList = addMedicinesListData();

		addMedicines_col_drugID.setCellValueFactory(new PropertyValueFactory<>("drug_id"));

		addMedicines_col_drugName.setCellValueFactory(new PropertyValueFactory<>("drug_name"));

		addMedicines_col_condition.setCellValueFactory(new PropertyValueFactory<>("conditions"));

		addMedicines_col_unitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));

		addMedicines_col_expiry.setCellValueFactory(new PropertyValueFactory<>("expiry"));

		addMedicines_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));

		// addMedicines_col_status.setCellValueFactory(new
		// PropertyValueFactory<>("status"));

//		addMedicines_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

		addMedicines_tableView1.setItems(addMedicineList);

	}

	// Search Option inside Table view AddMedicines
	public void addMedicineSearch() {
		FilteredList<DrugData> filter = new FilteredList<>(addMedicineList, e -> true);
		addMedicines_search.textProperty().addListener((Observable, oldValue, newValue) -> {
			filter.setPredicate(predicateMedicineData -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String searchKey = newValue.toLowerCase();

				if (predicateMedicineData.getDrug_id().toString().contains(searchKey)) {
					return true;
				} else if (predicateMedicineData.getDrug_name().toLowerCase().indexOf(searchKey) > -1) {
					return true;

				} else if (predicateMedicineData.getConditions().toLowerCase().indexOf(searchKey) > -1) {
					return true;
				} else {
					return false;
				}
			});
		});

		SortedList<DrugData> sortList = new SortedList<>(filter);
		sortList.comparatorProperty().bind(addMedicines_tableView1.comparatorProperty());
		addMedicines_tableView1.setItems(sortList);

	}
	
	// Populating Data on textfields while medicine is selected in TableView

	public void addMedicineSelect() {

		DrugData drugData = addMedicines_tableView1.getSelectionModel().getSelectedItem();
		int num = addMedicines_tableView1.getSelectionModel().getSelectedIndex();
		if ((num - 1) < -1) {
			return;
		}
		addMedicines_drugid.setText(String.valueOf(drugData.getDrug_id()));
		addMedicines_drugname.setText(drugData.getDrug_name());
		addMedicines_condition.setText(drugData.getConditions());
		addMedicines_unitprice1.setText(String.valueOf(drugData.getUnit_price()));
		addMedicines_expiry.setValue(LocalDate.parse(String.valueOf(drugData.getExpiry())));
		addMedicines_stock.setText(String.valueOf(drugData.getStock()));
		addMedicines_idcheck.setText(String.valueOf(drugData.getIdCheck()));
		addMedicines_avail_stock.setText(drugData.getStk_avail());
		addMedicines_avail_tunstall.setText(drugData.getTun_avail());
		addMedicines_avail_fenton.setText(drugData.getFen_avail());
		addMedicines_avail_hanley.setText(drugData.getHan_avail());
		addMedicines_avail_longton.setText(drugData.getLong_avail());

//		 Image of Medicine to DB
		String uri = "file:" + drugData.getImage();
		image = new Image(uri, 127, 184, false, true);
		addMedicines_imageView.setImage(image);
		GetData.path = drugData.getImage();

	}

	private double totalP;

	public void purchaseAdd() {
//		System.out.println("inside purchase Add Method");
		purchaseCustomerid();
		String sql = "INSERT INTO customer (customer_id,type,medicine_id,brand,productName,quantity,price,date)"
				+ " VALUES(?,?,?,?,?,?,?,?)";
		connect = Database.connectDB();

		try {
			Alert alert;

			if (purchase_type.getSelectionModel().getSelectedItem() == null
					|| purchase_medicineID.getSelectionModel().getSelectedItem() == null
					|| purchase_brand.getSelectionModel().getSelectedItem() == null
					|| purchase_productName.getSelectionModel().getSelectedItem() == null) {

				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error message");
				alert.setHeaderText(null);
				alert.setContentText("please fill all blank fields");
				alert.showAndWait();
			} else {
//				System.out.println("inside else");
				prepare = connect.prepareStatement(sql);

				prepare.setString(1, String.valueOf(customerId));
				// prepare.setString(2, String.valueOf(customerId));
				prepare.setString(2, (String) purchase_type.getSelectionModel().getSelectedItem());
				prepare.setString(3, (String) purchase_medicineID.getSelectionModel().getSelectedItem());
				prepare.setString(4, (String) purchase_brand.getSelectionModel().getSelectedItem());
				prepare.setString(5, (String) purchase_productName.getSelectionModel().getSelectedItem());
				prepare.setString(6, String.valueOf(qty));

				String checkData = "SELECT price FROM medicine WHERE medicine_id = '"
						+ purchase_medicineID.getSelectionModel().getSelectedItem() + "'";
				statement = connect.createStatement();

				// System.out.println("check Data :" + checkData);

				result = statement.executeQuery(checkData);
				double priceD = 0, totalPrice = 0;
				if (result.next()) {
					priceD = result.getDouble("price");
				}
				totalP = (priceD * qty);
				prepare.setString(7, String.valueOf(totalP));

				Date date = new Date();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				prepare.setString(8, String.valueOf(sqlDate));
//
//				System.out.println("customerId" + String.valueOf(customerId));
//				System.out.println("type" + purchase_type.getSelectionModel().getSelectedItem());
//				System.out.println("MedicineID" + purchase_medicineID.getSelectionModel().getSelectedItem());
//				System.out.println("brand" + purchase_brand.getSelectionModel().getSelectedItem());
//				System.out.println("productName" + purchase_productName.getSelectionModel().getSelectedItem());
//				System.out.println();

				// ************ Need work to resolve the add function
				prepare.executeUpdate();
				purchaseShowListData();
				purchaseDisplayTotal();

			}

			prepare = connect.prepareStatement(sql);
//			result = prepare.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private double totalPriceD;

	public void purchaseDisplayTotal() {
		String sql = "SELECT SUM(price) FROM customer WHERE customer_id = '" + customerId + "'";
		connect = Database.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			if (result.next()) {
				totalPriceD = result.getDouble("SUM(price)");
			}
			purchase_total.setText("£" + String.valueOf(totalPriceD));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private double balance;
	private double amount;

	public void purchaseAmount() {
		if (purchase_amount.getText().isEmpty() || totalPriceD == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Somthing went wrong");
			alert.showAndWait();
		} else {
			amount = Double.parseDouble(purchase_amount.getText());
			if (totalPriceD > amount) {
				purchase_amount.setText("");
			} else {
				balance = (amount - totalPriceD);
				purchase_balance.setText("£" + String.valueOf(balance));
			}
		}
	}

	public void purchasePay() {

		System.out.println("1");
		purchaseCustomerid();
		String sql = "INSERT INTO customer_info (customer_id, total, date) VALUES (?, ?, ?)";
		System.out.println(sql);
		connect = Database.connectDB();

		try {
			Alert alert;
			if (totalPriceD == 0) {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Something went wrong");
				alert.showAndWait();
			} else {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText(null);
				alert.setContentText("Are you sure?");
				Optional<ButtonType> option = alert.showAndWait();

				if (option.get().equals(ButtonType.OK)) {

					prepare = connect.prepareStatement(sql);
					prepare.setString(1, String.valueOf(customerId));
					prepare.setString(2, String.valueOf(totalPriceD));
					Date date = new Date();
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());

					prepare.setString(3, String.valueOf(sqlDate));
					prepare.executeUpdate();

					alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setContentText("Successful");
					alert.showAndWait();

					purchase_amount.setText("");
					purchase_balance.setText("£0.0");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private SpinnerValueFactory<Integer> spinner;

	public void purchaseShowValue() {
		spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);
		purchase_quantity.setValueFactory(spinner);
	}

	private int qty;

	public void purchaseQuantity() {
		qty = purchase_quantity.getValue();
	}

	public ObservableList<CustomerData> purchaseListData() {
		purchaseCustomerid();
		String sql = "SELECT * FROM customer WHERE customer_id ='" + customerId + "'";

		ObservableList<CustomerData> listData = FXCollections.observableArrayList();

		connect = Database.connectDB();
		try {
			CustomerData customerD;
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			while (result.next()) {
				customerD = new CustomerData(result.getInt("customer_id"), result.getString("type"),
						result.getInt("medicine_id"), result.getString("brand"), result.getString("productName"),
						result.getInt("quantity"), result.getDouble("price"), result.getDate("date"));

				listData.add(customerD);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listData;

	}

	private ObservableList<CustomerData> purchaseList;

	public void purchaseShowListData() {
		purchaseList = purchaseListData();

		purchase_col_medicineid.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
		purchase_col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
		purchase_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
		purchase_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
		purchase_col_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

		purchase_tableView.setItems(purchaseList);
	}

	private int customerId;

	public void purchaseCustomerid() {
		String sql = "SELECT customer_id FROM customer";
		connect = Database.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			while (result.next()) {
				customerId = result.getInt("customer_id");
			}
			int cID = 0;
			String checkData = "SELECT customer_id FROM customer_info";

			statement = connect.createStatement();
			result = statement.executeQuery(checkData);

			while (result.next()) {
				cID = result.getInt("customer_id");
			}

			if (customerId == 0) {
				customerId += 1;
			} else if (cID == customerId) {
				customerId = cID + 1;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//
//	public void purchaseType() {
//		String sql = "SELECT * FROM medicine WHERE status='Available' ";
//		connect = Database.connectDB();
//		try {
//			ObservableList listData = FXCollections.observableArrayList();
//			prepare = connect.prepareStatement(sql);
//			result = prepare.executeQuery();
//			while (result.next()) {
//				listData.add(result.getString("type"));
//			}
//			purchase_type.setItems(listData);
//			purchaseMedicineId();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

//	public void purchaseMedicineId() {
//		String sql = "SELECT * FROM medicine WHERE type = '" + purchase_type.getSelectionModel().getSelectedItem()
//				+ "'";
//		connect = Database.connectDB();
//		try {
//			ObservableList listData = FXCollections.observableArrayList();
//			prepare = connect.prepareStatement(sql);
//			result = prepare.executeQuery();
//
//			while (result.next()) {
//				listData.add(result.getString("medicine_id"));
//			}
//			purchase_medicineID.setItems(listData);
//			purchaseBrand();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void purchaseBrand() {
//		String sql = "SELECT * FROM medicine WHERE medicine_id = '"
//				+ purchase_medicineID.getSelectionModel().getSelectedItem() + "'";
//		connect = Database.connectDB();
//		try {
//			ObservableList listData = FXCollections.observableArrayList();
//			prepare = connect.prepareStatement(sql);
//			result = prepare.executeQuery();
//
//			while (result.next()) {
//				listData.add(result.getString("brand"));
//			}
//			purchase_brand.setItems(listData);
//			purchaseProductName();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void purchaseProductName() {
//		String sql = "SELECT * FROM medicine WHERE brand = '" + purchase_brand.getSelectionModel().getSelectedItem()
//				+ "'";
//		connect = Database.connectDB();
//		try {
//			ObservableList listData = FXCollections.observableArrayList();
//			prepare = connect.prepareStatement(sql);
//			result = prepare.executeQuery();
//
//			while (result.next()) {
//				listData.add(result.getString("productName"));
//			}
//			purchase_productName.setItems(listData);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void defaultNav() {
		dashboard_btn.setStyle("-fx-background-color:#8a418c");
		dashboard_form.setVisible(true);
	}

	public ObservableList<CustomersData> addCustomersListData() {
		String sql = "SELECT * FROM customers";
		ObservableList<CustomersData> listData = FXCollections.observableArrayList();

		connect = Database.connectDB();

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			CustomersData cusData;

			while (result.next()) {
				cusData = new CustomersData(result.getInt("customer_id"), result.getString("first_name"),
						result.getString("middle_name"), result.getString("last_name"), result.getDate("dob"),
						result.getString("house_no"), result.getString("street_name"), result.getString("postcode"),
						result.getString("city"), result.getString("county"), result.getString("country"),
						result.getString("allergies"));
				listData.add(cusData);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}

	private ObservableList<CustomersData> addCustomersList;

	public void addCustomerShowListData() {
		addCustomersList = addCustomersListData();

		addCustomer_col_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));

		addCustomer_col_firstname.setCellValueFactory(new PropertyValueFactory<>("first_name"));

		addCustomer_col_midname.setCellValueFactory(new PropertyValueFactory<>("mid_name"));

		addCustomer_col_surname.setCellValueFactory(new PropertyValueFactory<>("last_name"));

		addCustomer_col_DOB.setCellValueFactory(new PropertyValueFactory<>("dob"));

		addCustomer_col_houseno.setCellValueFactory(new PropertyValueFactory<>("house_no"));

		addCustomer_col_streetname.setCellValueFactory(new PropertyValueFactory<>("street_name"));

		addCustomer_col_postcode.setCellValueFactory(new PropertyValueFactory<>("postcode"));

		addCustomer_col_city.setCellValueFactory(new PropertyValueFactory<>("city"));

		addCustomer_col_county.setCellValueFactory(new PropertyValueFactory<>("county"));

		addCustomer_col_country.setCellValueFactory(new PropertyValueFactory<>("country"));

		addCustomer_col_allergies.setCellValueFactory(new PropertyValueFactory<>("allergy"));

		addCustomer_tableView.setItems(addCustomersList);

	}

	public void addCustomersSearch() {
		FilteredList<CustomersData> filter = new FilteredList<>(addCustomersList, e -> true);
		addCustomer_search.textProperty().addListener((Observable, oldValue, newValue) -> {
			filter.setPredicate(predicateCustomersData -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String searchKey = newValue.toLowerCase();

				if (predicateCustomersData.getCustomer_id().toString().contains(searchKey)) {
					return true;
				} else if (predicateCustomersData.getFirst_name().toLowerCase().indexOf(searchKey) > -1) {
					return true;
				} else if (predicateCustomersData.getLast_name().toLowerCase().indexOf(searchKey) > -1) {
					return true;
				} else
					return false;

			});
		});
		SortedList<CustomersData> sortList = new SortedList<>(filter);
		sortList.comparatorProperty().bind(addCustomer_tableView.comparatorProperty());
		addCustomer_tableView.setItems(sortList);

	}

	public void addCustomersSelect() {
		System.out.println("inside addCustomersSelect ");

		CustomersData cusData = (CustomersData) addCustomer_tableView.getSelectionModel().getSelectedItem();
		int num = addCustomer_tableView.getSelectionModel().getSelectedIndex();
		if ((num - 1) < -1) {
			return;
		}
		addCustomer_customerid.setText(String.valueOf(cusData.getCustomer_id()));
		addCustomer_firstname.setText(String.valueOf(cusData.getFirst_name()));
		addCustomer_midname.setText(cusData.getMid_name());
		addCustomer_surname.setText(cusData.getLast_name());
		addCustomer_DOB.setValue(LocalDate.parse(String.valueOf(cusData.getDob())));
		addCustomer_houseno.setText(cusData.getHouse_no());
		addCustomer_streetname.setText(cusData.getStreet_name());
		addCustomer_postcode.setText(cusData.getPostcode());
		addCustomer_city.setText(cusData.getCity());
		addCustomer_county.setText(cusData.getCounty());
		addCustomer_country.setText(cusData.getCountry());
		addCustomer_allergies.setText(cusData.getAllergy());

	}

	public void addCustomersAdd() {
		System.out.println("inside customersAdd");
		String sql = "INSERT INTO customers (customer_id, first_name, middle_name, last_name, dob, house_no, street_name, postcode,city,county,country) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println(sql);
		connect = Database.connectDB();

		try {

			Alert alert;
			if (addCustomer_customerid.getText().isEmpty() || addCustomer_firstname.getText().isEmpty()
					|| addCustomer_surname.getText().isEmpty() || addCustomer_DOB.getValue() == null
					|| addCustomer_houseno.getText().isEmpty() || addCustomer_streetname.getText().isEmpty()
					|| addCustomer_postcode.getText().isEmpty() || addCustomer_city.getText().isEmpty()
					|| addCustomer_county.getText().isEmpty() || addCustomer_country.getText().isEmpty()) {
				System.out.println("inside if");
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("please fill all blank fields");
				alert.showAndWait();
			} else {

				// CHECKING IF DATA IS ALREADY PRESENT IN DATABASE

				String checkData = "SELECT customer_id FROM customers WHERE customer_id ='"
						+ addCustomer_customerid.getText() + "'";
				statement = connect.createStatement();
				result = statement.executeQuery(checkData);
				if (result.next()) {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Customer ID: " + addCustomer_customerid.getText() + " already exist!");
					alert.showAndWait();
				} else {
					prepare = connect.prepareStatement(sql);
					prepare.setString(1, addCustomer_customerid.getText());
					prepare.setString(2, addCustomer_firstname.getText());
					prepare.setString(3, addCustomer_midname.getText());
					prepare.setString(4, addCustomer_surname.getText());
					prepare.setString(5, String.valueOf(addCustomer_DOB.getValue()));
					prepare.setString(6, addCustomer_houseno.getText());
					prepare.setString(7, addCustomer_streetname.getText());
					prepare.setString(8, addCustomer_postcode.getText());
					prepare.setString(9, addCustomer_city.getText());
					prepare.setString(10, addCustomer_county.getText());
					prepare.setString(11, addCustomer_country.getText());

				}

				prepare.executeUpdate();

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Message");
				alert.setHeaderText(null);
				alert.setContentText("Successfully Added!");
				alert.showAndWait();

				addCustomerShowListData(); // calling function to show data from database
				addCustomersReset();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addCustomersUpdate() {
		System.out.println("inside Customer update");

		String sql = "UPDATE customers SET first_name ='" + addCustomer_firstname.getText() + "', middle_name = '"
				+ addCustomer_midname.getText() + "', last_name = '" + addCustomer_surname.getText() + "',dob = '"
				+ addCustomer_DOB.getValue() + "', house_no = '" + addCustomer_houseno.getText() + "',street_name='"
				+ addCustomer_streetname.getText() + "',postcode='" + addCustomer_postcode.getText() + "',city='"
				+ addCustomer_city.getText() + "',county='" + addCustomer_county.getText() + "',country='"
				+ addCustomer_country.getText() + "',allergies='" + addCustomer_allergies.getText()
				+ "' WHERE customer_id = '" + addCustomer_customerid.getText() + "'";

		System.out.println(sql);
		connect = Database.connectDB();
		try {

			Alert alert;
			if (addCustomer_customerid.getText().isEmpty() || addCustomer_firstname.getText().isEmpty()
					|| addCustomer_surname.getText().isEmpty() || addCustomer_DOB.getValue() == null
					|| addCustomer_houseno.getText().isEmpty() || addCustomer_streetname.getText().isEmpty()
					|| addCustomer_postcode.getText().isEmpty() || addCustomer_city.getText().isEmpty()
					|| addCustomer_county.getText().isEmpty() || addCustomer_country.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("please fill all blank fields");
				alert.showAndWait();
			} else {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Message");
				alert.setHeaderText(null);
				alert.setContentText(
						"Are you sure you want to UPDATE Customer ID:" + addCustomer_customerid.getText() + "?");
				Optional<ButtonType> option = alert.showAndWait();

				if (option.get().equals(ButtonType.OK)) {
					statement = connect.createStatement();
					statement.executeUpdate(sql);

					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Updated!");
					alert.showAndWait();

					addCustomerShowListData();
					addCustomersReset();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addCustomersDelete() {
		System.out.println("inside customer Delete");
		String sql = "DELETE FROM customers WHERE customer_id = '" + addCustomer_customerid.getText() + " ' ";

		connect = Database.connectDB();

		try {

			Alert alert;
			if (addCustomer_customerid.getText().isEmpty() || addCustomer_firstname.getText().isEmpty()
					|| addCustomer_surname.getText().isEmpty() || addCustomer_DOB.getValue() == null
					|| addCustomer_houseno.getText().isEmpty() || addCustomer_streetname.getText().isEmpty()
					|| addCustomer_postcode.getText().isEmpty() || addCustomer_city.getText().isEmpty()
					|| addCustomer_county.getText().isEmpty() || addCustomer_country.getText().isEmpty()) {

				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("please fill all blank fields");
				alert.showAndWait();
			} else {
				System.out.println("inside else");
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Message");
				alert.setHeaderText(null);
				alert.setContentText(
						"Are you sure you want to DELETE Customer ID:" + addCustomer_customerid.getText() + "?");
				Optional<ButtonType> option = alert.showAndWait();

				if (option.get().equals(ButtonType.OK)) {
					statement = connect.createStatement();
					statement.executeUpdate(sql);

					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Deleted!");
					alert.showAndWait();

					addCustomerShowListData();
					addCustomersReset();

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addCustomersReset() {
		System.out.println("inside reset");
		addCustomer_customerid.setText("");
		addCustomer_firstname.setText("");
		addCustomer_midname.setText("");
		addCustomer_surname.setText("");
		addCustomer_DOB.setValue(null);
		addCustomer_houseno.setText("");
		addCustomer_streetname.setText("");
		addCustomer_postcode.setText("");
		addCustomer_city.setText("");
		addCustomer_county.setText("");
		addCustomer_country.setText("");
		addCustomer_allergies.setText("");

	}

	public ObservableList<DrugData> DrugListData() {
		System.out.println("2");
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String sql = "SELECT * FROM drugs WHERE expiry_date <='" + date + "'";
		System.out.println(sql);

		ObservableList<DrugData> listData = FXCollections.observableArrayList();

		connect = Database.connectDB();
		try {
			DrugData DrugD;
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			while (result.next()) {

				DrugD = new DrugData(result.getInt("drug_id"), result.getString("drug_name"),
						result.getString("conditions"), result.getDouble("unit_price"), result.getDate("expiry_date"),
						result.getInt("stock"));

				listData.add(DrugD);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(listData);
		return listData;

	}

	private ObservableList<DrugData> ExpiredDrugList;

	public void showExpiredDrugs() {

		ExpiredDrugList = DrugListData();

		expired_col_drugid.setCellValueFactory(new PropertyValueFactory<>("drug_id"));
		expired_col_drugname.setCellValueFactory(new PropertyValueFactory<>("drug_name"));
		expired_col_condition.setCellValueFactory(new PropertyValueFactory<>("conditions"));
		expired_col_price.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
		expired_col_expiry.setCellValueFactory(new PropertyValueFactory<>("expiry"));
		expired_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		expired_tableView.setItems(ExpiredDrugList);
	}

	public ObservableList<DrugData> DrugListStockData() {
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String sql = "SELECT * FROM drugs WHERE stock <= 10";
		System.out.println(sql);

		ObservableList<DrugData> listData = FXCollections.observableArrayList();

		connect = Database.connectDB();
		try {
			DrugData DrugD;
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			while (result.next()) {
				System.out.println(result.getInt("drug_id"));
				DrugD = new DrugData(result.getInt("drug_id"), result.getString("drug_name"),
						result.getString("conditions"), result.getDouble("unit_price"), result.getDate("expiry_date"),
						result.getInt("stock"));

				listData.add(DrugD);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;

	}

	private ObservableList<DrugData> LowStockDrugList;

	public void showLowStockDrugs() {

		LowStockDrugList = DrugListStockData();

		lowStock_col_drugid1.setCellValueFactory(new PropertyValueFactory<>("drug_id"));
		lowStock_col_drugname.setCellValueFactory(new PropertyValueFactory<>("drug_name"));
		lowStock_col_condition.setCellValueFactory(new PropertyValueFactory<>("conditions"));
		lowStock_col_unitprice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
		lowStock_col_expiry.setCellValueFactory(new PropertyValueFactory<>("expiry"));
		lowStock_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));

		lowStock_tableView.setItems(LowStockDrugList);

	}

	public void switchForm(ActionEvent event) {
		if (event.getSource() == dashboard_btn) {
			dashboard_form.setVisible(true);
			addMedicines_form.setVisible(false);
			purchase_form.setVisible(false);
			addCustomer_form.setVisible(false);
//			staffmanage_form.setVisible(false);

			dashboard_btn.setStyle("-fx-background-color:#8a418c");
			addMedicine_btn.setStyle("-fx-background-color:transparent");
			purchase_btn.setStyle("-fx-background-color:transparent");
			customer_btn.setStyle("-fx-background-color:transparent");
			// staff_btn.setStyle("-fx-background-color:transparent");

			homeAM();
			homeTI();
			homeTC();
			homeChart();
		} else if (event.getSource() == addMedicine_btn) { // need to update for latest values in UI
			dashboard_form.setVisible(false);
			addMedicines_form.setVisible(true);
			purchase_form.setVisible(false);
			addCustomer_form.setVisible(false);
//			staffmanage_form.setVisible(false);

			addMedicine_btn.setStyle("-fx-background-color:#8a418c");
			dashboard_btn.setStyle("-fx-background-color:transparent");
			purchase_btn.setStyle("-fx-background-color:transparent");
			customer_btn.setStyle("-fx-background-color:transparent");
			// staff_btn.setStyle("-fx-background-color:transparent");
			addMedicineShowListData();
//			addMedicineListStatus();
//			addMedicineListType();
//			addMedicineSearch();

		} else if (event.getSource() == purchase_btn) {
			dashboard_form.setVisible(false);
			addMedicines_form.setVisible(false);
			purchase_form.setVisible(true);
			addCustomer_form.setVisible(false);
//			staffmanage_form.setVisible(false);

			purchase_btn.setStyle("-fx-background-color:#8a418c");
			dashboard_btn.setStyle("-fx-background-color:transparent");
			addMedicine_btn.setStyle("-fx-background-color:transparent");
			customer_btn.setStyle("-fx-background-color:transparent");
			// staff_btn.setStyle("-fx-background-color:transparent");

//			purchaseType();
//			purchaseMedicineId();
//			purchaseBrand();
//			purchaseProductName();
//			purchaseShowListData();
//			purchaseShowValue();
//			purchaseDisplayTotal();

			showExpiredDrugs();
			showLowStockDrugs();

		} else if (event.getSource() == customer_btn) {
			dashboard_form.setVisible(false);
			addMedicines_form.setVisible(false);
			purchase_form.setVisible(false);
			addCustomer_form.setVisible(true);
//			staffmanage_form.setVisible(false);

			purchase_btn.setStyle("-fx-background-color:transparent");
			customer_btn.setStyle("-fx-background-color:#8a418c");
			dashboard_btn.setStyle("-fx-background-color:transparent");
			addMedicine_btn.setStyle("-fx-background-color:transparent");
			// staff_btn.setStyle("-fx-background-color:transparent");

			addCustomerShowListData();
			addCustomersSearch();

		} // need to implement
		else if (event.getSource() == staff_btn) {
			dashboard_form.setVisible(false);
			addMedicines_form.setVisible(false);
			purchase_form.setVisible(false);
			addCustomer_form.setVisible(false);
			// staffmanage_form.setVisible(true);

			purchase_btn.setStyle("-fx-background-color:transparent");
			customer_btn.setStyle("-fx-background-color:transparent");
			dashboard_btn.setStyle("-fx-background-color:transparent");
			addMedicine_btn.setStyle("-fx-background-color:transparent");
			// staff_btn.setStyle("-fx-background-color:#8a418c");
		}

	}

	public void displayUsername() {
		String user = GetData.username;
		// Setting Username to app with formatting
		username.setText(user.substring(0, 1).toUpperCase() + user.substring(1));

	}

	private double x = 0;
	private double y = 0;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		displayUsername();
		defaultNav();

		homeAM();
		homeTI();
		homeTC();
		homeChart();

		addMedicineShowListData();

		addCustomerShowListData();

	}

}
