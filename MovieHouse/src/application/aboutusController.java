package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class aboutusController {
	@FXML
	private ImageView img1;
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
	private ImageView imageAshish;
	@FXML
	private TextArea ashu;
	@FXML
	private ImageView imageAbhishek;
	@FXML
	private TextArea abhi;
	@FXML
	private TextArea mnit;

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
	// Event Listener on Button[#aboutus].onAction
	@FXML
	public void processAboutUs(ActionEvent event) throws IOException 
	{
		
	}
	public void Getuser(String iD, String nam) 
	{
		// TODO Auto-generated method stub
		welcome.setText("Welcome "+nam);
	}
}
