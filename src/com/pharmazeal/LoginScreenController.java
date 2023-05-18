package com.pharmazeal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.mysql.cj.protocol.Resultset;

public class LoginScreenController implements Initializable {
	@FXML
	private AnchorPane main_form;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button loginBtn;
	@FXML
	private Button close;

	private PreparedStatement pr;
	private Connection con;
	private ResultSet rs;

	private double x = 0;
	private double y = 0;

	public void loginAdmin() {
		String sql = "SELECT * FROM ADMIN WHERE username= ? and password = ?";
		con = Database.connectDB();
		try {
			pr = con.prepareStatement(sql);
			pr.setString(1, username.getText());
			pr.setString(2, password.getText());
			rs = pr.executeQuery();

			Alert alert;

			if (username.getText().isEmpty() || password.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Occured");
				alert.setHeaderText(null);
				alert.setContentText("Please fill blank fields");
				alert.showAndWait();
			}

			else {
				if (rs.next()) {
					// Fetching the Username of user
					GetData.username = username.getText();
					if (username.getText().equals("admin")) {
						alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Message");
						alert.setHeaderText(null);
						alert.setContentText("Login Success");
						alert.showAndWait();

						loginBtn.getScene().getWindow().hide();

						Parent root = (Parent) FXMLLoader.load(DashboardController.class.getResource("Dashboard.fxml"));
						Stage stage = new Stage();
						Scene scene = new Scene(root);

						root.setOnMousePressed((MouseEvent event) -> {
							x = event.getSceneX();
							y = event.getSceneY();
						});

						root.setOnMouseDragged((MouseEvent event) -> {
							stage.setX(event.getScreenX() - x);
							stage.setY(event.getScreenY() - y);
						});
						stage.initStyle(StageStyle.TRANSPARENT);

						stage.setScene(scene);
						stage.show();
					} else if (username.getText().equals("staff")) {
						alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Message");
						alert.setHeaderText(null);
						alert.setContentText("Login Success");
						alert.showAndWait();

						loginBtn.getScene().getWindow().hide();

						Parent root = (Parent) FXMLLoader
								.load(StaffsScreenController.class.getResource("StaffsScreen.fxml"));

						Stage stage = new Stage();
						Scene scene = new Scene(root);

						root.setOnMousePressed((MouseEvent event) -> {
							x = event.getSceneX();
							y = event.getSceneY();
						});

						root.setOnMouseDragged((MouseEvent event) -> {
							stage.setX(event.getScreenX() - x);
							stage.setY(event.getScreenY() - y);
						});
						stage.initStyle(StageStyle.TRANSPARENT);

						stage.setScene(scene);
						stage.show();
					}
				} else {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Occured");
					alert.setHeaderText(null);
					alert.setContentText("Wrong Credentials");
					alert.showAndWait();
				}
			}
		} catch (Exception e) {
		}

	}

	public void close() {
		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
