package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.scene.control.Hyperlink;

public class frontController implements Initializable
{
	private List<Product> results;
	private ObservableList<Product> products = FXCollections.observableArrayList();
	private Product currentProduct;
    //private ProductQueries productQueries;
   // private List<Product> results;
    private List<Integer> alreadyPurchasedProducts;
    //private List<Integer> allProductIDs;
    //private ObservableList<Product> products = FXCollections.observableArrayList();
    private ObservableList<Product> cartItems = FXCollections.observableArrayList();
    
    private Stage warningStage;
    
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
	private ImageView img2;
	@FXML
	private ImageView img3;
	@FXML
	private ListView<Product> descriptor;
	@FXML
	private Label labelTrailer;
	@FXML
	private Hyperlink trailerMedia;
	@FXML
	private Label aboutMovie;
	@FXML
	private TextArea summary;
	@FXML
	private Button addtocart;

	String ID,nam;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String str="select * from movie";
		Statement st=Database.getStatement();
		List<Product> results = null;
		results = new ArrayList<Product>();
       
		
		try {
			ResultSet rs=st.executeQuery(str);
			while(rs.next())
			{
				int movie_id = rs.getInt("MOVIE_ID");
				String movie_name = rs.getString("MOVIE_NAME");
				String genre = rs.getString("GENRE");
				double price = rs.getDouble("PRICE");
				double imdb_rating = rs.getDouble("IMDB_RATING");
				String description = rs.getString("DESCRIPTION");
				String trailer = rs.getString("TRAILER");
				String cover_image = rs.getString("COVER_IMAGE");
				String image_1 = rs.getString("IMAGE_1");
				String image_2 = rs.getString("IMAGE_2");
				String download_link = rs.getString("DOWNLOAD_LINK");
				System.out.println(movie_id);
				System.out.println(movie_name);
				results.add(new Product(movie_id,movie_name,genre,price,imdb_rating,description,trailer,cover_image,image_1,image_2,download_link));
				
			}
			products.addAll(results);
			descriptor.setItems(products);
			descriptor.setPrefWidth(601);
			descriptor.setPrefHeight(186);
			
			descriptor.getSelectionModel().selectedItemProperty().addListener(
	                (observableValue, oldValue, newValue) -> {
	                	summary.setText(((Product) newValue).getDescription());
	                	//img1.setImage(new Image(newValue.getCover_image()));
	                	img2.setImage(new Image(((Product) newValue).getImage1()));
	                	img3.setImage(new Image(((Product) newValue).getImage2()));
	                	Main.trailPath=((Product) newValue).getTrailer();
//	                        if(alreadyPurchasedProducts.contains(newValue.getProductID())) {
//	                            addToCartButton.setText("Already Purchased");
//	                            addToCartButton.setDisable(true);
//	                        } else {
//	                            addToCartButton.setText("Add To Cart");
//	                            addToCartButton.setDisable(false);
//	                        };
	                }        
	        );
			descriptor.getSelectionModel().select(0);// selected item will be first item bydefault
			descriptor.setCellFactory((listview) -> new ImageTextCell());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//////////////adding cart(num)/////////////////////////////////////////		
		ResultSet resultSet=null;
	    List<Integer> results1=null;
	   	Statement statement = Database.getStatement();
	   	
	   	String query= "select * from cart where USER_ID="+Main.sessionId+";";
	    try {
			resultSet= statement.executeQuery(query);
			results1=new ArrayList<Integer>();
		    
		    while(resultSet.next()) {
	        	results1.add(resultSet.getInt("MOVIE_ID"));
	        }
		    String s=String.valueOf(results1.size());
		    int i=Integer.parseInt(s);
		    cart.setText("Cart (" + i + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}
	
	
	// Event Listener on Button[#logout].onAction
	@FXML
	public void processlogout(ActionEvent event) throws IOException 
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
	public void processHouse(ActionEvent event) {
		// TODO Autogenerated
	}
	///////////////////////////////////////////////////////////////////
	
	 public Product getSelectedProduct() {
	        currentProduct = (application.Product) descriptor.getSelectionModel().getSelectedItem();
	        return currentProduct;
	    }
/////////////////changed//////////////////////////////////////	
	 public void processAddToCart(ActionEvent event) throws Exception 
	 {
	        
		ResultSet resultSet=null;
	    List<Integer> results=null;
	   	Statement statement = Database.getStatement();
	   	
	   	
	   	ResultSet resultSet2=null;
	    List<Integer> results2=null;
	   	Statement statement2 = Database.getStatement();
	   	
	   	Product product=getSelectedProduct();
	   	
	   	String query= "select * from cart where USER_ID="+Main.sessionId+";";
	   	String query2= "select * from library where USER_ID="+Main.sessionId+";";
	    resultSet= statement.executeQuery(query);
	    results=new ArrayList<Integer>();
	    
	    resultSet2= statement2.executeQuery(query2);
	    results2=new ArrayList<Integer>();
	    
	    while(resultSet.next()) {
        	results.add(resultSet.getInt("MOVIE_ID"));
        }
	    
	    while(resultSet2.next()) {
        	results2.add(resultSet2.getInt("MOVIE_ID"));
        }
	    
	    String s=String.valueOf(results.size());
	    int i=Integer.parseInt(s);
	    if(results.contains(product.getMovie_id())) 
	    {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(warningStage);
            alert.getDialogPane().setContentText("Movie is already added to the cart");
            alert.getDialogPane().setHeaderText(null);
            alert.showAndWait().filter(response -> response == ButtonType.OK);
        } 
	    else if(results2.contains(product.getMovie_id()))
	    {
	    	Alert alert = new Alert(AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(warningStage);
            alert.getDialogPane().setContentText("This Movie is already Purchased");
            alert.getDialogPane().setHeaderText(null);
            alert.showAndWait().filter(response -> response == ButtonType.OK);
	    }
	    else 
	    {
            //cartItems.add(getSelectedProduct());
            //Product product = getSelectedProduct();
            final String INSERT_QUERY = "INSERT INTO cart (USER_ID,MOVIE_ID) VALUES ('" + Main.sessionId + "','" + product.getMovie_id() + "')";
            statement.executeUpdate(INSERT_QUERY);
            i++;
            
            //System.out.println("Product Added To Cart");
        }
//	    int a=50;
//	    while(a!=0)
//	    {
//	    	a--;
//	    }
	    cart.setText("Cart (" + i + ")");
	 }
	
	
	// Event Listener on Button[#cart].onAction
	@FXML
	public void processCart(ActionEvent event) throws IOException 
	{
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage=new Stage();
		FXMLLoader loader =new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/yourcart.fxml").openStream());
		yourcartController control=(yourcartController)loader.getController();
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
	
	public ObservableList<Product> getCartItems() {
        return cartItems;
    }
	
	////////////////////////////////////////////////////////////////////////
	// Event Listener on Button[#library].onAction
	@FXML
	public void processLibrary(ActionEvent event) throws IOException {
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage=new Stage();
		FXMLLoader loader =new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/library.fxml").openStream());
		libraryController control=(libraryController)loader.getController();
		control.Getuser(Main.sessionId,Main.sessionName);
		//userController usercontrol=(userController)loader.getController();
		//usercontrol.Getuser(loginUserName.getText());
		Scene scene = new Scene(root,900,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
		primaryStage.setResizable(false);
		//primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
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
	// Event Listener on Button[#aboutus].onAction
	@FXML
	public void processAboutUs(ActionEvent event) throws IOException {
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
	
	
	
	
	
	
	
	public void Getuser(String userid,String username)
	{
		 ID = userid;
		nam=username;
		welcome.setText("Welcome "+username);
	}
	public void processTrailer(ActionEvent event1) throws IOException
	{
		Stage primaryStage=new Stage();
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		//primaryStage.initStyle(StageStyle.TRANSPARENT);
		//Parent root = FXMLLoader.load(getClass().getResource("media.fxml"));
		FXMLLoader loader =new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/media.fxml").openStream());
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		//primaryStage.setTitle("App Title bar text here");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent we) {
				
//				mediaController obj= new mediaController();
//				obj.func();
				//Platform.exit();
				mediaController control=(mediaController)loader.getController();
				control.close();
				primaryStage.close();
				
				System.out.println("stage closing::");
				
			}
		});
		//primaryStage.close();
		
//		((Node)event1.getSource()).getScene().getWindow().hide();
//		Stage primaryStage=new Stage();
//		FXMLLoader loader =new FXMLLoader();
//		Pane root = loader.load(getClass().getResource("/application/media.fxml").openStream());
//		//userController usercontrol=(userController)loader.getController();
//		//usercontrol.Getuser(loginUserName.getText());
//		Scene scene = new Scene(root,600,400);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		primaryStage.setMinWidth(400);
//        primaryStage.setMinHeight(600);
//		primaryStage.setResizable(false);
//		primaryStage.setScene(scene);
//		primaryStage.show();
	}
	
	
}
