package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

public class accountController implements Initializable 
{
	
	private Stage warningStage;
	
	@FXML
	private ImageView img;
	@FXML
	private Label Heading;
	@FXML
	private Label welcome;
	@FXML
	private Button logout;
	@FXML
	private Button house;
	@FXML
	private Button cart;
	@FXML
	private Button library;
	@FXML
	private Button account;
	@FXML
	private Button aboutus;
	@FXML
	private Label details;
	@FXML
	private Label option;
	@FXML
	private Label entry1;
	@FXML
	private TextField username;
	@FXML
	private Label entry2;
	@FXML
	private TextField password;
	@FXML
	private Label entry3;
	@FXML
	private TextField mail;
	@FXML
	private Label Status1;
	@FXML
	private Button updateButton;
	@FXML
	private Label entry4;
	@FXML
	private TextField card;
	@FXML
	private Label entry5;
	@FXML
	private TextField cvv;
	@FXML
	private Label entry6;
	@FXML
	private TextField amount;
	@FXML
	private Label Status2;
	@FXML
	private Button addBalance;
	@FXML
	private Label entry7;
	@FXML
	private Label balance;
	@FXML
	private Label entry21;;
	@FXML
	private TextField password1;
	

	String ID,NAME;
	String pwd = "";
	String Mail = "";
	String Cvv ="";
	String Bal = "";
	String Card = "";
	
	
	public void Getuser(String userid,String usernam)
	{
		ID=userid;
		// NAME=usernam;
		
		
		
		//System.out.println("hiiiiiii"+ID+NAME);
		
		String str="select USER_ID,USER_NAME,EMAIL,PASSWORD,CARD_NO,CVV,BALANCE from user where USER_ID="+ID;
		Statement st=Database.getStatement();
		ResultSet rs = null;
		try 
		{
			rs = st.executeQuery(str);
			System.out.println("hiiiiiii2");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{
			if(rs.next())
			{
				System.out.println("hiiiiiii3");
				//status.setText("");
				//status.setText("HI"+username);
				NAME=rs.getString("USER_NAME");
				 pwd = rs.getString("PASSWORD");
				 Mail = rs.getString("EMAIL");
				 Bal = rs.getString("BALANCE");
				 Card = rs.getString("CARD_NO");
				 Cvv = rs.getString("CVV");
				
					//System.out.println("BOO");
					//success.setText("welcome "+ username);
					//System.out.println("BOOO");
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(pwd+Mail+Cvv+Bal);
		username.setText(NAME);
		//password.setText(pwd);
		mail.setText(Mail);
		//cvv.setText(Cvv);
		amount.setText(Bal);
		
		
		
		balance.setText(Bal);
		//card.setText(Card);
		
		welcome.setText("Welcome "+NAME);
		Main.sessionName=NAME;
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		//String userid=loginUserName.getText();
		//String pass=loginPasswordField.getText();
		
	}
	
	
	// Event Listener on Button[#logout].onAction
	@FXML
	public void processLogOut(ActionEvent event) throws IOException {
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
	// Event Listener on Button[#house].onAction
	@FXML
	public void processHouse(ActionEvent event) throws IOException 
	{
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage=new Stage();
		FXMLLoader loader =new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/front.fxml").openStream());
		frontController control=(frontController)loader.getController();
		control.Getuser(Main.sessionId,Main.sessionName);
		Scene scene = new Scene(root,900,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	// Event Listener on Button[#cart].onAction
	@FXML
	public void processCart(ActionEvent event) throws IOException 
	{
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage=new Stage();
		FXMLLoader loader =new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/yourcart.fxml").openStream());
		//frontController control=(frontController)loader.getController();
		//control.Getuser(Main.sessionId,Main.sessionName);
		Scene scene = new Scene(root,900,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	// Event Listener on Button[#library].onAction
	@FXML
	public void processLibrary(ActionEvent event) throws IOException 
	{
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage=new Stage();
		FXMLLoader loader =new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/library.fxml").openStream());
		libraryController control=(libraryController)loader.getController();
		control.Getuser(Main.sessionId,Main.sessionName);
		Scene scene = new Scene(root,900,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	// Event Listener on Button[#account].onAction
	@FXML
	public void processAccount(ActionEvent event) throws IOException 
	{
		
	}
	// Event Listener on Button[#aboutus].onAction
	@FXML
	public void processAboutUs(ActionEvent event) throws IOException 
	{
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage=new Stage();
		FXMLLoader loader =new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/aboutus.fxml").openStream());
		aboutusController control=(aboutusController)loader.getController();
		control.Getuser(Main.sessionId,Main.sessionName);
		//userController usercontrol=(userController)loader.getController();
		//usercontrol.Getuser(loginUserName.getText());
		Scene scene = new Scene(root,900,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	// Event Listener on Button[#updateButton].onAction
	@FXML
	public void processUpdate(ActionEvent event) throws SQLException, IOException 
	{
		int c=0;
		if(password.getText().equals("") ^ password1.getText().equals(""))
		{
			//Status1.setText("Please enter both password field");
			Alert alert = new Alert(AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            
			alert.initOwner(warningStage);
            alert.getDialogPane().setContentText("Please enter both password field");
            alert.getDialogPane().setHeaderText(null);
            alert.showAndWait().filter(response -> response == ButtonType.OK);
		}
		else if(username.getText().equals("") || mail.getText().equals(""))
		{
			//Status1.setText("Please enter all details");
			Alert alert = new Alert(AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            
			alert.initOwner(warningStage);
            alert.getDialogPane().setContentText("Please enter necessary details");
            alert.getDialogPane().setHeaderText(null);
            alert.showAndWait().filter(response -> response == ButtonType.OK);
		}
		else
		{
			String str=null;
			String usernamee=username.getText();
			String pass=password1.getText();
			String oldpass=password.getText();
			String Email=mail.getText();
			
			Statement st=Database.getStatement();
			if(password.getText().equals(""))
			{
				str="update  user set USER_NAME='"+usernamee+"',EMAIL='"+Email+"' where USER_ID="+ID;
				st.executeUpdate(str);
				Alert alert = new Alert(AlertType.CONFIRMATION);
	            alert.initModality(Modality.APPLICATION_MODAL);
	            
				alert.initOwner(warningStage);
	            alert.getDialogPane().setContentText("Details Updated Succesfully");
	            c=1;
	            alert.getDialogPane().setHeaderText(null);
	            alert.showAndWait().filter(response -> response == ButtonType.OK);
			}
			else
			{
				String temp2=MD5.crypt(oldpass);
				if(temp2.equals(pwd))
				{
					String temp=MD5.crypt(pass);
					str="update  user set USER_NAME='"+usernamee+"',EMAIL='"+Email+"',PASSWORD='"+temp+ "' where USER_ID="+ID;
					st.executeUpdate(str);
					Alert alert = new Alert(AlertType.CONFIRMATION);
		            alert.initModality(Modality.APPLICATION_MODAL);
		            
					alert.initOwner(warningStage);
		            alert.getDialogPane().setContentText("Details Updated Succesfully");
		            c=1;
		            alert.getDialogPane().setHeaderText(null);
		            alert.showAndWait().filter(response -> response == ButtonType.OK);
				}
				else
				{
					//st.executeUpdate(str);
					Alert alert = new Alert(AlertType.ERROR);
		            alert.initModality(Modality.APPLICATION_MODAL);
		            
					alert.initOwner(warningStage);
		            alert.getDialogPane().setContentText("Old Password is incorrect");
		            alert.getDialogPane().setHeaderText(null);
		            alert.showAndWait().filter(response -> response == ButtonType.OK);
				}
				
				
			}
			if(c==1)
			{
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage=new Stage();
				FXMLLoader loader =new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/account.fxml").openStream());
				accountController control=(accountController)loader.getController();
				control.Getuser(Main.sessionId,Main.sessionName);
				Scene scene = new Scene(root,900,600);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setMinWidth(900);
		        primaryStage.setMinHeight(600);
				primaryStage.setResizable(false);
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			
			
			
			
			//Status1.setText("Details Updated Succesfully");
		}
	}
	// Event Listener on Button[#addBalance].onAction
	@FXML
	public void processAddBalance(ActionEvent event) throws SQLException, IOException {
		if(card.getText().equals("") || cvv.getText().equals("")|| amount.getText().equals(""))
		{
			//Status2.setText("Please Enter All Details");
			Alert alert = new Alert(AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            
			alert.initOwner(warningStage);
            alert.getDialogPane().setContentText("Please enter all details");
            alert.getDialogPane().setHeaderText(null);
            alert.showAndWait().filter(response -> response == ButtonType.OK);
		}
		else
		{
			String Card=card.getText();
			String CVVV=cvv.getText();
			String money=amount.getText();
			
			
			String str2="select CARD_NO,CVV,BALANCE  from user where USER_ID="+ID;
			Statement st1=Database.getStatement();
			ResultSet rs=st1.executeQuery(str2);
			if(rs.next())
			{
				//status.setText("");
				//status.setText("HI"+username);
				String DB_Card=rs.getString("CARD_NO");
				String DB_CVVV=rs.getString("CVV");
				String DB_money=rs.getString("BALANCE");
				
				if(Card.equals(DB_Card) && CVVV.equals(DB_CVVV))
				{

					int temp=Integer.parseInt(money);
					int current=Integer.parseInt(DB_money);
					
					int fina=temp+current;
					
					String AMT = String.valueOf(fina);
					String str="update  user set BALANCE="+AMT+ " where USER_ID="+ID;
					Statement st=Database.getStatement();
					st.executeUpdate(str);
					Alert alert = new Alert(AlertType.CONFIRMATION);
		            alert.initModality(Modality.APPLICATION_MODAL);
		            
					alert.initOwner(warningStage);
		            alert.getDialogPane().setContentText("Balance Updated Succesfully");
		            alert.getDialogPane().setHeaderText(null);
		            alert.showAndWait().filter(response -> response == ButtonType.OK);
					//Status2.setText("Money Added Succesfully");
					balance.setText(AMT);
					
					
					((Node)event.getSource()).getScene().getWindow().hide();
					Stage primaryStage=new Stage();
					FXMLLoader loader =new FXMLLoader();
					Pane root = loader.load(getClass().getResource("/application/account.fxml").openStream());
					accountController control=(accountController)loader.getController();
					control.Getuser(Main.sessionId,Main.sessionName);
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
					//Status2.setText("INVALID DETAILS");
					Alert alert = new Alert(AlertType.ERROR);
		            alert.initModality(Modality.APPLICATION_MODAL);
		            
					alert.initOwner(warningStage);
		            alert.getDialogPane().setContentText("INVALID DETAILS");
		            alert.getDialogPane().setHeaderText(null);
		            alert.showAndWait().filter(response -> response == ButtonType.OK);
				}
			}
			
			
		}
	}
	
	

	
}
