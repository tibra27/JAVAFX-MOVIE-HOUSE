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


public class deleteController {
	private Stage warningStage;
	@FXML
	private Label heading;
	@FXML
	private Label entry1;
	@FXML
	private Label entry2;
	@FXML
	private TextField movieid;
	@FXML
	private TextField moviename;
	@FXML
	private Button delete;
	@FXML
	private ImageView logo;
	@FXML
	private Label status;
	@FXML
	private Button back;

	// Event Listener on Button[#delete].onAction
	@FXML
	public void processRemove(ActionEvent event) throws IOException, SQLException 
	{
		
		if(movieid.getText().equals("") || moviename.getText().equals(""))
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
			String id=movieid.getText();
			String movie=moviename.getText();
			String str="select MOVIE_ID,MOVIE_NAME from movie where MOVIE_ID="+id +" AND MOVIE_NAME='"+movie+"';";
			System.out.println(str);
			Statement st=Database.getStatement();
			ResultSet rs=st.executeQuery(str);
			if(rs.next())
			{
				String str1="delete from movie where ( MOVIE_ID="+id+" AND MOVIE_NAME='"+movie+"');";
				Statement st1=Database.getStatement();
				st1.executeUpdate(str1);
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		        alert.initModality(Modality.APPLICATION_MODAL);
		        
				alert.initOwner(warningStage);
		        alert.getDialogPane().setContentText("Movie is Removed Successfully");
		        alert.getDialogPane().setHeaderText(null);
		        alert.showAndWait().filter(response -> response == ButtonType.OK);
				status.setText("Removed Successfully");
				
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage=new Stage();
				FXMLLoader loader =new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/delete.fxml").openStream());
				Scene scene = new Scene(root,300,200);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setMinWidth(500);
		        primaryStage.setMinHeight(200);
				primaryStage.setResizable(false);
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			else
			{
				Alert alert = new Alert(AlertType.WARNING);
	            alert.initModality(Modality.APPLICATION_MODAL);
	            
				alert.initOwner(warningStage);
	            alert.getDialogPane().setContentText("Please enter correct details");
	            alert.getDialogPane().setHeaderText(null);
	            alert.showAndWait().filter(response -> response == ButtonType.OK);
			}
		}
		
		
		
		
	}
	// Event Listener on Button[#back].onAction
	@FXML
	public void processadmin(ActionEvent event) throws IOException {
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage=new Stage();
		FXMLLoader loader =new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/addInfo.fxml").openStream());
		
		//addInfoController control=(addInfoController)loader.getController();
		//control.Getuser(loginUserName.getText());
		Scene scene = new Scene(root,542,534);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setMinWidth(542);
        primaryStage.setMinHeight(534);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
