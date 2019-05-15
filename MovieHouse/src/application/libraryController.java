package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
import java.net.URL;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.libraryController;
//import javafx.application.HostServices;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class libraryController implements Initializable{
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
	private TilePane Tpaine;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		try {
            
            Statement st= Database.getStatement();
            
            String purchased = "SELECT MOVIE_NAME, COVER_IMAGE, DOWNLOAD_LINK FROM movie INNER JOIN library ON movie.MOVIE_ID =  library.MOVIE_ID AND library.USER_ID = " + Main.sessionId;
            ResultSet resultSet = st.executeQuery(purchased);
            if (!resultSet.isBeforeFirst() ) {   
                Label noGames = new Label("You haven't purchased anything.Purchased Movie will be seen here.");
                noGames.setPadding(new Insets(10, 10, 10, 10));
                Tpaine.getChildren().add(noGames);
            }
            
            while(resultSet.next()) {
                String productTitle = resultSet.getString("MOVIE_NAME");
                String productCover = resultSet.getString("COVER_IMAGE");
                String productExe = resultSet.getString("DOWNLOAD_LINK");
                System.out.println(productExe);
                Image gameImg = new Image(productCover);
                ImageView coverImageView = new ImageView(gameImg);
                coverImageView.setFitHeight(120);
                coverImageView.setFitWidth(100);
                final Tooltip tooltip = new Tooltip("Watch " + productTitle);
                Hyperlink downloadMovie = new Hyperlink(productTitle);
                downloadMovie.setContentDisplay(ContentDisplay.TOP);
                downloadMovie.setGraphic(coverImageView);
                downloadMovie.getStyleClass().add("library-tiles");
                Tooltip.install(downloadMovie, tooltip);
                downloadMovie.setCursor(Cursor.DEFAULT);
                Tpaine.getChildren().add(downloadMovie);
                
                downloadMovie.setOnAction((ActionEvent e) -> {
                	
                	
                	
                    try {
                   	
                        Desktop.getDesktop().open(new File(productExe));
                    } catch ( IOException ex) {
                        Logger.getLogger(libraryController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                });
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
		
	}
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
	public void processHouse(ActionEvent event) throws IOException {
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
	public void processLibrary(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#account].onAction
	@FXML
	public void processAccount(ActionEvent event) throws IOException {
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
	
	
	public void Getuser(String iD, String nam) 
	{
		// TODO Auto-generated method stub
		welcome.setText("Welcome "+nam);
	}
	
}
