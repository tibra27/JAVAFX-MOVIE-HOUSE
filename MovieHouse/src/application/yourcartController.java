package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class yourcartController {
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
	private Label entry2;
	@FXML
	private Label purchaseDEC;
	@FXML
	private Label entry3;
	@FXML
	private Label entry4;
	@FXML
	private Label checkoutUserName;
	@FXML
	private Label entry5;
	@FXML
	private Label checkoutTotal;
	@FXML
	private Label entry6;
	@FXML
	private Label checkoutBalance;
	@FXML
	private Label Status;
	@FXML
	private Button pButton;
	@FXML
	private Label entry1;
	@FXML private TableView<Product> cartTable;
    @FXML private TableColumn<Product, Product> coverCol;
    @FXML private TableColumn<Product, String> titleCol;
    @FXML private TableColumn<Product, Double> priceCol;    
    @FXML private TableColumn<Product, Product> removeCol;
    double total = 0 ;
    
    private ObservableList<Product> cartItems = FXCollections.observableArrayList();
	 NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
	
	public void initialize () throws Exception
	{
		
		
		welcome.setText("Welcome "+Main.sessionName);
		
        cartTable.setPlaceholder(new Label("Your shopping cart is empty...!"));
        ResultSet resultSet1=null;
    	List<Product> results1=null;
    	 Statement statement = Database.getStatement();
    	 
    	 String query= "select * from movie inner join cart using(MOVIE_ID) where USER_ID="+Main.sessionId;
    	 String query1= "select * from user where USER_ID="+Main.sessionId;
    	 
         resultSet1= statement.executeQuery(query1);
         if(resultSet1.next())
  		{
         checkoutUserName.setText(Main.sessionName);
         checkoutBalance.setText("₹ "+resultSet1.getString("BALANCE"));
 		}
 		
 		
 		
 		 ResultSet resultSet=null;
     	List<Product> results=null;
 		 resultSet= statement.executeQuery(query);
         results = new ArrayList<Product>();
         
         while (resultSet.next()) {
             results.add(new Product(
                     resultSet.getInt("MOVIE_ID"),
                     resultSet.getString("MOVIE_NAME"),
                     resultSet.getString("GENRE"),
                     resultSet.getDouble("PRICE"),
                     resultSet.getDouble("IMDB_RATING"),
                     resultSet.getString("DESCRIPTION"),
                     resultSet.getString("TRAILER"),
                     
                     resultSet.getString("COVER_IMAGE"),
                     resultSet.getString("IMAGE_1"),
                     resultSet.getString("IMAGE_2"),
                     resultSet.getString("DOWNLOAD_LINK")
                     //resultSet.getString("image2")
                     //resultSet.getString("image3"),
                     //resultSet.getString("image4"),
                     //resultSet.getString("exeFile")
             ));
         }
         cartItems.addAll(results);
         cartTable.setItems(cartItems);
         
         for (Product product : cartTable.getItems()) {
             total = total + product.getPrice();
             
         }
         checkoutTotal.setText("₹ "+String.valueOf(total));
         
         
         
         
        // String currencyPrice = currencyFormatter.format(total);
         //subtotalLabel.setText(currencyPrice);    
         
         coverCol.setCellValueFactory(img -> new ReadOnlyObjectWrapper<>(img.getValue()));
         coverCol.setCellFactory(img -> new TableCell<Product, Product>(){
             private final ImageView coverImageView = new ImageView();
             @Override 
             protected void updateItem(Product product, boolean empty) {
                 super.updateItem(product, empty);
                 if(product == null) {
                     setGraphic(null);
                     return;
                 }
                 coverImageView.setImage(new Image(product.getCover_image()));
                 coverImageView.setFitHeight(50);
                 coverImageView.setFitWidth(50);
                 setGraphic(coverImageView);
             }
         });
  //////////////////////////adding title//////////////////////////
         titleCol.setCellValueFactory(new PropertyValueFactory<>("movie_name"));
         titleCol.setCellFactory(col -> new TableCell<Product, String>(){
             @Override
             public void updateItem(String movie_name, boolean empty) {
                 super.updateItem(movie_name, empty);
                 if(empty) {
                     setGraphic(null);
                 } else {
                     
                     Label titleLabel = new Label(movie_name);
                     //priceLabel.alignmentProperty();
                     setGraphic(titleLabel);
                 }
             }
         });
         //authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
         priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
         priceCol.setCellFactory(col -> new TableCell<Product, Double>(){
             @Override
             public void updateItem(Double price, boolean empty) {
                 super.updateItem(price, empty);
                 if(empty) {
                     setGraphic(null);
                 } else {
                     String currencyPrice = currencyFormatter.format(price);
                     //total+=price;
                     //checkoutTotal.setText(String.valueOf(total));
                     Label priceLabel = new Label("₹ "+price);
                     //priceLabel.alignmentProperty();
                     setGraphic(priceLabel);
                 }
             }
         });
         
         removeCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
         removeCol.setCellFactory(param -> new TableCell<Product,Product>(){
             private final Hyperlink removeFromCart = new Hyperlink("");
             @Override
             protected void updateItem(Product product, boolean empty) {
                 super.updateItem(product, empty);
                 if(product == null) {
                     setGraphic(null);
                     return;
                 }
                 HBox removeHBox = new HBox();
                 removeHBox.setAlignment(Pos.CENTER);
                 removeHBox.getChildren().addAll(removeFromCart);
                 setGraphic(removeHBox);
                 System.out.println(getClass().getResourceAsStream("../img/bin.png"));
                 Image deleteIcon = new Image(getClass().getResourceAsStream("../img/bin.png"));
                 //System.out.println(total);
                 final ImageView removeImageView=new ImageView(deleteIcon);
                 removeImageView.setFitHeight(30);
                 removeImageView.setFitWidth(50);
                 removeFromCart.setGraphic(removeImageView);
                 removeFromCart.setStyle("-fx-text-fill: black;");
                 removeFromCart.setOnAction(e -> {
                
        ///////////////////delete query//////////////////////
             	   Statement statement = Database.getStatement();
             	   String query= "delete from cart where USER_ID="+Main.sessionId+" and MOVIE_ID="+product.getMovie_id()+";";
                	try {
						statement.executeUpdate(query);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
         			
                     getTableView().getItems().remove(product);
                     total = total - product.getPrice();
                    // Label priceLabel = new Label("₹"+total);
                    // String currencyPrice = currencyFormatter.format(total);
                     checkoutTotal.setText("₹ "+total);
                    // subtotalLabel.setText(currencyPrice);
                     //cart.setText("Cart (" + String.valueOf(mainController.getCartItems().size()) + ")");
                 });
             }
         });
    }
	
	
	
    
    
    
    
    
	// Event Listener on Button[#logout].onAction
	@FXML
	public void processLogOut(ActionEvent event) throws IOException 
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
	public void processCart(ActionEvent event) 
	{
		// TODO Autogenerated
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
	// Event Listener on Button[#pButton].onAction
	@FXML
	public void processPurchased(ActionEvent event) 
	{ 
		Statement statement = Database.getStatement();
		String query1= "select * from user where USER_ID="+Main.sessionId;
		ResultSet resultSet=null;
    try {
		resultSet= statement.executeQuery(query1);
		
		if(resultSet.next())
		{
			if(total<=resultSet.getDouble("BALANCE") && total>0)
	 		{
	 			//cotrrect it
				double temp;
				temp=resultSet.getDouble("BALANCE")-total;
				String query12= "update user set BALANCE="+temp+"where USER_ID="+Main.sessionId;
	 			//total-=resultSet.getDouble("BALANCE");
				statement.executeUpdate(query12);
	 			checkoutBalance.setText("₹ "+temp);
	 			Status.setText("Purchase Successfully!!!");
	 			
	 			
	 			
	 			String query_a= "select * from cart where USER_ID="+Main.sessionId;
	 			//Statement statement = Database.getStatement();
	 			Statement statement1 = Database.getStatement();
	 			ResultSet fi=statement1.executeQuery(query_a);
	 			//Statement statement3 = Database.getStatement();
	 			while(fi.next())
	 			{
	 				Statement statement3 = Database.getStatement();
	 				String query_b="insert into library values("+Main.sessionId+","+fi.getString("MOVIE_ID")+")";
	 				statement3.executeUpdate(query_b);
	 			}
	 			
	 			String query_c="delete from cart where USER_ID="+Main.sessionId;
	 			Statement statement2 = Database.getStatement();
 				statement2.executeUpdate(query_c);
	 			
 				cartTable.getItems().clear();
 				total=0;
 				checkoutTotal.setText("₹ "+total);
	 		}
	 		else
	 		{
	 			if(total==0)
	 				Status.setText("Cart is empty");
	 			else
	 			Status.setText("Insufficient Balance!! Please add money to purchase.");
	 			
	 		}
			
			
		
		}
 		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
	public ObservableList<Product> getCartItemsForCheckout() 
	{
        return cartTable.getItems();
    }








	public void Getuser(String sessionId, String sessionName) {
		welcome.setText("Welcome "+sessionName);
		
	}
}
