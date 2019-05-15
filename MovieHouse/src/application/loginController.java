package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

public class loginController {
	
	
	private Stage warningStage;
	
	@FXML
	private Label heading;
	@FXML
	private TextField loginUserName;
	@FXML
	private PasswordField loginPasswordField;
	@FXML
	private Button loginButton;
	@FXML
	private ImageView logo;
	@FXML
	private Button registerButton;
	@FXML
	private Label status;

	// Event Listener on Button[#loginButton].onAction
	@FXML
	public void processLogin(ActionEvent event) throws IOException, SQLException 
	{
		if(loginUserName.getText().equals("") || loginPasswordField.getText().equals(""))
		{
			Alert alert = new Alert(AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            
			alert.initOwner(warningStage);
            alert.getDialogPane().setContentText("Please enter all details");
            alert.getDialogPane().setHeaderText(null);
            alert.showAndWait().filter(response -> response == ButtonType.OK);
		}
		else
		{
			String userid=loginUserName.getText();
			String pass1=loginPasswordField.getText();
			String pass=MD5.crypt(pass1);
			String str="select USER_ID,PASSWORD,USER_NAME	 from user where USER_ID='"+userid+"' and PASSWORD	='"+pass+"'";
			Statement st=Database.getStatement();
			ResultSet rs=st.executeQuery(str);
			if(rs.next())
			{
				//status.setText("");
				//status.setText("HI"+username);
				String username = rs.getString("USER_NAME");
				
				Main.sessionId=loginUserName.getText();
				Main.sessionName=username;
				
					//System.out.println("BOO");
					//success.setText("welcome "+ username);
					//System.out.println("BOOO");
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage=new Stage();
				FXMLLoader loader =new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/front.fxml").openStream());
				frontController control=(frontController)loader.getController();
				control.Getuser(loginUserName.getText(),username);
				Scene scene = new Scene(root,900,600);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setMinWidth(900);
		        primaryStage.setMinHeight(600);
				primaryStage.setResizable(false);
				primaryStage.setScene(scene);
				primaryStage.show();
				
			}
			else
			{
				//status.setText("");
				//status.setText(" You are pompian");
				Alert alert = new Alert(AlertType.ERROR);
	            alert.initModality(Modality.APPLICATION_MODAL);
	            
				alert.initOwner(warningStage);
	            alert.getDialogPane().setContentText("Invalid login details");
	            alert.getDialogPane().setHeaderText(null);
	            alert.showAndWait().filter(response -> response == ButtonType.OK);
				
			}
		}
		
	}
	// Event Listener on Button[#registerButton].onAction
	@FXML
	public void processRegister(ActionEvent event1) throws IOException 
	{
		((Node)event1.getSource()).getScene().getWindow().hide();
		Stage primaryStage=new Stage();
		FXMLLoader loader =new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/register.fxml").openStream());
		//userController usercontrol=(userController)loader.getController();
		//usercontrol.Getuser(loginUserName.getText());
		Scene scene = new Scene(root,600,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(600);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void processadmin(ActionEvent event1) throws IOException 
	{
		((Node)event1.getSource()).getScene().getWindow().hide();
		Stage primaryStage=new Stage();
		FXMLLoader loader =new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/adminlogin.fxml").openStream());
		//userController usercontrol=(userController)loader.getController();
		//usercontrol.Getuser(loginUserName.getText());
		Scene scene = new Scene(root,300,200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		//Scene scene = new Scene(root,300,300);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
