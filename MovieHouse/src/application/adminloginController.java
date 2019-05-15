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

public class adminloginController {
	private Stage warningStage;
	
	@FXML
	private Label heading;
	@FXML
	private Label entry1;
	@FXML
	private Label entry2;
	@FXML
	private TextField loginUserName;
	@FXML
	private PasswordField loginPasswordField;
	@FXML
	private Button loginButton;
	
	@FXML
	private Button loginButton2;
	
	@FXML
	private ImageView logo;
	@FXML
	private Label status;

	// Event Listener on Button[#loginButton].onAction
	@FXML
	public void processLogin(ActionEvent event) throws SQLException, IOException 
	{
		if(loginUserName.getText().equals("") || loginPasswordField.getText().equals(""))
		{
			//status.setText("Please enter all details");
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
			String pass=loginPasswordField.getText();
			String str="select id,password	 from admin where id='"+userid+"' and password	='"+pass+"'";
			Statement st=Database.getStatement();
			ResultSet rs=st.executeQuery(str);
			if(rs.next())
			{
				//status.setText("");
				//status.setText("HI"+username);
				
					//System.out.println("BOO");
					//success.setText("welcome "+ username);
					//System.out.println("BOOO");
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
			else
			{
				Alert alert = new Alert(AlertType.ERROR);
	            alert.initModality(Modality.APPLICATION_MODAL);
	            
				alert.initOwner(warningStage);
	            alert.getDialogPane().setContentText("Invalid login details");
	            alert.getDialogPane().setHeaderText(null);
	            alert.showAndWait().filter(response -> response == ButtonType.OK);
			}
		}
	}
	
	public void processLogin2(ActionEvent event) throws SQLException, IOException
	{
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage=new Stage();
		FXMLLoader loader =new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/login.fxml").openStream());
		//userController usercontrol=(userController)loader.getController();
		//usercontrol.Getuser(loginUserName.getText());
		Scene scene = new Scene(root,300,200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(200);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
