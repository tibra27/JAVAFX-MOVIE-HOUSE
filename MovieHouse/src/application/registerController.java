package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

public class registerController {
	
	@FXML
	private ImageView registerlogo;
	@FXML
	private Label entry1;
	@FXML
	private Label entry2;
	@FXML
	private Label entry3;
	@FXML
	private Label entry4;
	@FXML
	private TextField userid;
	@FXML
	private TextField username;
	@FXML
	private TextField registerEmail;
	@FXML
	private TextField registerCreditCard;
	@FXML
	private Label status;
	@FXML
	private Label head;
	@FXML
	private Label entry5;
	@FXML
	private TextField CVV;
	@FXML
	private Label entry6;
	@FXML
	private Label entry7;
	@FXML
	private PasswordField pass1;
	@FXML
	private PasswordField pass2;
	@FXML
	private Button login;
	
	private Stage warningStage;
	// Event Listener on Button.onAction
	@FXML
	public void processRegister(ActionEvent event) throws SQLException, IOException
	{
		if(userid.getText().equals("") || username.getText().equals("")|| registerEmail.getText().equals("")|| registerCreditCard.getText().equals("")|| CVV.getText().equals("")|| pass1.getText().equals("")|| pass2.getText().equals(""))
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
			
			String str1="select USER_ID	 from user where USER_ID="+userid.getText();
			Statement st1=Database.getStatement();
			ResultSet rs1=st1.executeQuery(str1);
			if(rs1.next())
			{
				//status.setText("");
				//status.setText("HI"+username);
				status.setText("User already exist");
			}
			else
			{
				String id=userid.getText();
				String name=username.getText();
				String email=registerEmail.getText();
				String card=registerCreditCard.getText();
				String cvv=CVV.getText();
				String pwd1=pass1.getText();
				String pwd2=pass2.getText();
				if(!(registerEmail.getText().matches("\\b[a-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,}\\b")))
				{
					status.setText("Enter valid email");
				}
				else if(card.length()!=16)
				{
					status.setText("Enter 16 digit card number");
				}
				else if(cvv.length()!=3)
				{
					status.setText("Enter 3 digit cvv number");
				}
				else if(!pwd1.equals(pwd2))
				{
					status.setText("");
					status.setText("Enter same password");
				}
				else
				{
					String temp=MD5.crypt(pwd1);
					
					String str="insert into user (USER_ID,USER_NAME,EMAIL,PASSWORD,CARD_NO,CVV) values("+id+",'"+name+"','"+email+"','"+temp+"',"+card+","+cvv+")";
					Statement st=Database.getStatement();
					st.executeUpdate(str);
					
						//status.setText("");
						//status.setText("HI"+username);
						//System.out.println("BOO");
						//success.setText("welcome "+ username);
						//System.out.println("BOOO");
					
					Alert alert = new Alert(AlertType.CONFIRMATION);
		            alert.initModality(Modality.APPLICATION_MODAL);
		            
					alert.initOwner(warningStage);
		            alert.getDialogPane().setContentText("Registered Successfully");
		            alert.getDialogPane().setHeaderText(null);
		            alert.showAndWait().filter(response -> response == ButtonType.OK);
					
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
			
		}
	}
	
	@FXML
	public void processlogin(ActionEvent event) throws SQLException, IOException
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
