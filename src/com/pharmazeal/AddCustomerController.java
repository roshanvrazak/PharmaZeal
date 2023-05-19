package com.pharmazeal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;

import javafx.scene.input.MouseEvent;

import javafx.scene.input.KeyEvent;

import javafx.scene.control.DatePicker;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class AddCustomerController implements Initializable{
	@FXML
	private AnchorPane addCustomer_form;
	@FXML
	private TextField addCustomer_firstname;
	@FXML
	private TextField addCustomer_midname;
	@FXML
	private TextField addCustomer_surname;
	@FXML
	private Button addCustomer_addBtn;
	@FXML
	private Button addCustomer_clearBtn;
	@FXML
	private DatePicker addCustomer_DOB;
	@FXML
	private TextField addCustomer_houseno;
	@FXML
	private TextField addCustomer_streetname;
	@FXML
	private TextField addCustomer_postcode;
	@FXML
	private TextField addCustomer_city;
	@FXML
	private TextField addCustomer_county;
	@FXML
	private TextField addCustomer_country;
	@FXML
	private TextField addCustomer_customerid;
	@FXML
	private TextField addCustomer_allergies;
	@FXML
	private TextField addCustomer_search;
	@FXML
	private TableView addCustomer_tableView;
	@FXML
	private TableColumn addCustomer_col_firstname;
	@FXML
	private TableColumn addCustomer_col_midname;
	@FXML
	private TableColumn addCustomer_col_surname;
	@FXML
	private TableColumn addCustomer_col_DOB;
	@FXML
	private TableColumn addCustomer_col_houseno;
	@FXML
	private TableColumn addCustomer_col_streetname;
	@FXML
	private TableColumn addCustomer_col_postcode;
	@FXML
	private TableColumn addCustomer_col_city;
	@FXML
	private TableColumn addCustomer_col_county;
	@FXML
	private TableColumn addCustomer_col_country;
	@FXML
	private TableColumn addCustomer_col_id;
	@FXML
	private TableColumn addCustomer_col_allergies;

	
	private Connection connect;
	private PreparedStatement prepare;
	private Statement statement;
	private ResultSet result;

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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	addCustomerShowListData();
	addCustomersSearch();
	}
}
